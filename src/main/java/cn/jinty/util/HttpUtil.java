package cn.jinty.util;

import cn.jinty.enums.ContentTypeEnum;
import okhttp3.*;

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
            // 发起请求
            connection.connect();
            // 解析响应
            return resolveResponse(connection);
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
        if (params != null) {
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            for (String key : params.keySet()) {
                String encodeKey = URLEncoder.encode(key, "UTF-8");
                String encodeVal = URLEncoder.encode(params.get(key), "UTF-8");
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
            // 写入请求体，发起请求
            os.write(params.getBytes());
            // 解析响应
            return resolveResponse(connection);
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
     * 解析响应
     *
     * @param connection HTTP连接
     * @return 响应结果
     * @throws IOException IO异常
     */
    private static String resolveResponse(HttpURLConnection connection) throws IOException {
        // 获取响应码 (301或302需要重定向，发一个新的请求)
        if (connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM
                || connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
            // 获取响应头
            Map<String, List<String>> responseHeaders = connection.getHeaderFields();
            // 获取重定向链接
            String newUrl = responseHeaders.get("Location").get(0);
            // 发起一个新的请求
            return doGet(newUrl);
        }
        // 获取响应体
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

    // OKHTTP客户端
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    /**
     * 发起请求
     *
     * @param request 请求
     * @return 响应
     * @throws IOException IO异常
     */
    public static Response send(Request request) throws IOException {
        if (request == null) {
            throw new IllegalArgumentException("HTTP请求不能为空");
        }
        Call call = OK_HTTP_CLIENT.newCall(request);
        return call.execute();
    }

    /**
     * 发起请求 - JSON请求体
     *
     * @param url  请求路径
     * @param json 请求体
     * @return 响应体
     * @throws IOException IO异常
     */
    public static String sendJson(String url, String json) throws IOException {
        String result = null;
        MediaType JSON = MediaType.parse(ContentTypeEnum.JSON.getCode());
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", ContentTypeEnum.JSON.getCode())
                .post(body)
                .build();
        Response response = send(request);
        if (response.isSuccessful()) {
            if (response.body() != null) {
                result = response.body().string();
            }
        }
        return result;
    }

}
