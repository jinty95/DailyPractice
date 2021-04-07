package cn.jinty.https;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HTTPS工具类
 *
 * @author jinty
 * @date 2020/9/7
 **/
public class HttpsUtils {

    private static final int TIMEOUT = 45000;
    public static final String ENCODING = "UTF-8";

    /**
     * 创建HTTP连接
     *
     * @param url 地址
     * @param method 方法
     * @param headerParameters 头信息
     * @param body 请求体
     * @return http连接
     * @throws Exception 异常
     */
    private static HttpURLConnection createConnection(String url,
                                                      String method, Map<String, String> headerParameters, String body)
            throws Exception {

        // 建立连接
        URL Url = new URL(url);
        // 信任所有证书
        SslUtils.ignoreSsl();
        HttpURLConnection httpConnection = (HttpURLConnection) Url
                .openConnection();

        // 设置请求时间
        httpConnection.setConnectTimeout(TIMEOUT);

        // 设置 header
        if (headerParameters != null) {
            Iterator<String> iteratorHeader = headerParameters.keySet()
                    .iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                httpConnection.setRequestProperty(key,
                        headerParameters.get(key));
            }
        }
        httpConnection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded;charset=" + ENCODING);

        // 设置请求方法
        httpConnection.setRequestMethod(method);
        httpConnection.setDoOutput(true);
        httpConnection.setDoInput(true);

        // 写query数据流
        if (!(body == null || body.trim().equals(""))) {
            OutputStream writer = httpConnection.getOutputStream();
            try {
                writer.write(body.getBytes(ENCODING));
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }

        // 请求结果
        int responseCode = httpConnection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception(responseCode
                    + ":"
                    + inputStream2String(httpConnection.getErrorStream(),
                    ENCODING));
        }

        return httpConnection;

    }

    /**
     * HTTP请求
     *
     * @param address 地址
     * @param method 方法
     * @param headerParameters 头信息
     * @param body 请求内容
     * @return
     * @throws Exception
     */
    public static String proxyHttpRequest(String address, String method,
                                          Map<String, String> headerParameters, String body) throws Exception {
        String result = null;
        HttpURLConnection httpConnection = null;

        try {
            httpConnection = createConnection(address, method,
                    headerParameters, body);

            String encoding = "UTF-8";
            if (httpConnection.getContentType() != null
                    && httpConnection.getContentType().indexOf("charset=") >= 0) {
                encoding = httpConnection.getContentType()
                        .substring(
                                httpConnection.getContentType().indexOf(
                                        "charset=") + 8);
            }
            result = inputStream2String(httpConnection.getInputStream(),
                    encoding);

        } catch (Exception e) {
            throw e;
        } finally {
            if (httpConnection != null) {
                httpConnection.disconnect();
            }
        }
        return result;
    }

    /**
     * POST请求
     * @param address 请求地址
     * @param parameters 请求参数
     * @return
     * @throws Exception
     */
    public static String post(String address,
                              Map<String, String> parameters) throws Exception {
        return proxyHttpRequest(address, "POST", null,
                getRequestBody(parameters));
    }

    /**
     * GET请求
     * @param address 请求地址
     * @param parameters 请求参数
     * @return
     * @throws Exception
     */
    public static String get(String address,
                             Map<String, String> parameters) throws Exception {
        return proxyHttpRequest(address + "?"
                + getRequestBody(parameters), "GET", null, null);
    }

    /*  以下为内部函数  */

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    private static String getRequestBody(Map<String, String> params) {
        return getRequestBody(params, true);
    }

    /**
     * 将参数化为 body
     * @param params
     * @return
     */
    private static String getRequestBody(Map<String, String> params,
                                        boolean urlEncode) {
        StringBuilder body = new StringBuilder();

        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);

            if (urlEncode) {
                try {
                    body.append(key + "=" + URLEncoder.encode(value, ENCODING)
                            + "&");
                } catch (UnsupportedEncodingException e) {
                    // e.printStackTrace();
                }
            } else {
                body.append(key + "=" + value + "&");
            }
        }

        if (body.length() == 0) {
            return "";
        }
        return body.substring(0, body.length() - 1);
    }

    /**
     * 读取inputStream 到 string
     * @param input
     * @param encoding
     * @return
     * @throws IOException
     */
    private static String inputStream2String(InputStream input, String encoding)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                encoding));
        StringBuilder result = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            result.append(temp);
        }
        return result.toString();
    }

    //====================================================================
    //============================= 测试调用 ==============================
    //====================================================================
    public static void main(String[] args) {

        try {

            //请求地址
            String address = "https://www.baidu.com";

            //请求参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("ie", "utf-8");
            params.put("tn", "baidu");

            //调用get请求
            String res = get(address, params);
            System.out.println(res);//打印返回参数

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

