package cn.jinty.util;

import okhttp3.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
     * @throws Exception 异常
     */
    public static String doGet(String url) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result;
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
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\r\n");
            }
            result = sb.toString();
        } finally {
            // 关闭资源
            if (br != null) {
                br.close();
            }
            if (is != null) {
                is.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * 发起GET请求
     *
     * @param url    请求路径
     * @param params 请求参数
     * @return 响应结果
     * @throws Exception 异常
     */
    public static String doGet(String url, Map<String, String> params) throws Exception {
        // 查询参数拼在url后面
        if (params != null) {
            StringBuilder sb = new StringBuilder(url);
            sb.append("?");
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8")).append("&");
            }
            url = sb.substring(0, sb.length() - 1);
        }
        // 发起请求并解析响应
        return doGet(url);
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
    public static String send(String url, String json) throws IOException {
        String result = null;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json; charset=utf-8")
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
