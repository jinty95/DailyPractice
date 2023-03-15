package cn.jinty.util.net;

import cn.jinty.enums.ContentTypeEnum;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * HTTP - 工具类
 *
 * @author Jinty
 * @date 2021/12/18
 */
public final class HttpUtil {

    private HttpUtil() {
    }

    /**
     * 发起GET请求
     *
     * @param url 请求路径
     * @return 响应结果
     * @throws IOException IO异常
     */
    public static String doGet(String url) throws IOException {
        HttpURLConnection connection = null;
        try {
            // 创建URL对象
            URL urlObj = new URL(url);
            // 创建连接对象
            connection = (HttpURLConnection) urlObj.openConnection();
            // 设置连接参数
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);
            // 建立TCP连接 (还未发起Http请求)
            connection.connect();
            // 发起Http请求，解析响应
            return sendRequestAndResolveResponse(connection);
        } finally {
            // 断开连接
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 发起GET请求
     *
     * @param url    请求路径
     * @param params 请求参数
     * @return 响应结果
     * @throws IOException IO异常
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        // 查询参数拼在url后面
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String encodeKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                String encodeVal = URLEncoder.encode(entry.getValue(), "UTF-8");
                sb.append(encodeKey).append("=").append(encodeVal).append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }
        // 发起请求并解析响应
        return doGet(url);
    }

    /**
     * 发起POST请求
     *
     * @param url         请求路径
     * @param params      请求参数
     * @param contentType 请求体类型
     * @return 响应结果
     * @throws IOException IO异常
     */
    public static String doPost(String url, String params, ContentTypeEnum contentType) throws IOException {
        HttpURLConnection connection = null;
        OutputStream os = null;
        try {
            // 创建URL对象
            URL urlObj = new URL(url);
            // 创建连接对象
            connection = (HttpURLConnection) urlObj.openConnection();
            // 设置连接参数
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置请求头
            connection.setRequestProperty("Content-Type", contentType.getCode());
            // 获取连接输出流
            os = connection.getOutputStream();
            // 写入请求体
            os.write(params.getBytes());
            // 发起Http请求，解析响应
            return sendRequestAndResolveResponse(connection);
        } finally {
            // 关闭资源
            if (os != null) {
                os.close();
            }
            // 断开连接
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 发起Http请求，解析响应
     * (Http请求在调用HttpURLConnection的getInputStream()函数时发送)
     *
     * @param connection HTTP连接
     * @return 响应结果
     * @throws IOException IO异常
     */
    private static String sendRequestAndResolveResponse(HttpURLConnection connection) throws IOException {
        // 获取响应码 (301或302需要重定向，发一个新的请求)
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
            // 获取响应头
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();
            // 获取重定向链接
            String newUrl = responseHeaders.get("Location").get(0);
            // 发起一个新的请求
            return doGet(newUrl);
        }
        // 返回200时，获取响应体
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream is = connection.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
                return sb.toString();
            }
        }
        // 返回其它响应码时
        throw new IOException(String.format("error! responseCode = %d", responseCode));
    }

}
