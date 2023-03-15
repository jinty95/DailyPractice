package test.cn.jinty.util.net;

import cn.jinty.enums.ContentTypeEnum;
import cn.jinty.util.net.HttpUtil;
import cn.jinty.util.net.SslUtil;
import org.junit.Test;

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

        // 只有这种请求(GET+查询参数)可以得到正确的百度搜索结果页面，其它请求都只能得到一个"网络不给力，请稍后重试"页面
        Map<String, String> params = new HashMap<>();
        params.put("wd", "如何学习java");
        try {
            System.out.println("发起GET请求：响应=" + HttpUtil.doGet(url, params));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDoPost() {

        String url = "http://www.baidu.com/s";
        String params = "wd=如何学习java";
        try {
            System.out.println("发起POST请求：响应=" + HttpUtil.doPost(url, params, ContentTypeEnum.URL_ENCODED));
        } catch (Exception e) {
            e.printStackTrace();
        }

        url = "http://www.baidu.com/s";
        params = "{\"wd\":\"如何学习java\"";
        try {
            System.out.println("发起POST请求：响应=" + HttpUtil.doPost(url, params, ContentTypeEnum.JSON));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testIgnoreSsl() {

        String url = "https://127.0.0.1:8443/";
        try {
            SslUtil.ignoreSsl();
            System.out.println(HttpUtil.doGet(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
