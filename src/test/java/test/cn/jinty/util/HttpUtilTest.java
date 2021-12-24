package test.cn.jinty.util;

import cn.jinty.entity.KeyValue;
import cn.jinty.util.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP - 工具类 - 测试
 *
 * @author Jinty
 * @date 2021/12/18
 */
public class HttpUtilTest {

    @Test
    public void testDoGet() {

        String url = "http://www.baidu.com/s";
        try {
            System.out.println("发起GET请求：响应=" + HttpUtil.doGet(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("wd", "如何学习java");
        try {
            System.out.println("发起GET请求：响应=" + HttpUtil.doGet(url, params));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSend() {

        // GET请求
        String url = "http://www.baidu.com/s";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;
        try {
            response = HttpUtil.send(request);
            System.out.printf("发起请求：请求=%s, 响应=%s%n", request, response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // POST请求 普通表单
        RequestBody body = new FormBody.Builder()
                .add("wd", "如何学习java")
                .build();
        request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();
        try {
            response = HttpUtil.send(request);
            System.out.printf("发起请求：请求=%s, 响应=%s%n", request, response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  POST请求 JSON
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        body = RequestBody.create(JSON, "{\"key\":1}");
        request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json; charset=utf-8")
                .post(body)
                .build();
        try {
            response = HttpUtil.send(request);
            System.out.printf("发起请求：请求=%s, 响应=%s%n", request, response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSendJson() {

        String url = "http://www.baidu.com/s";
        String json = JSONObject.toJSONString(new KeyValue<>("name", "me"));
        System.out.println("url=" + url);
        System.out.println("body=" + json);
        try {
            System.out.println("result=" + HttpUtil.send(url, json));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
