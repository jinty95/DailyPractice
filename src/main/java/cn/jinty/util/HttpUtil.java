package cn.jinty.util;

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
     */
    public static String doGet(String url) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
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
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
     */
    public static String doGet(String url, Map<String, String> params) {
        String result = null;
        try {
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
            result = doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
