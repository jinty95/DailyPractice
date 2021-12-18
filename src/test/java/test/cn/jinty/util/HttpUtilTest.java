package test.cn.jinty.util;

import cn.jinty.util.HttpUtil;
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
        System.out.println("第一个请求结果："+HttpUtil.doGet(url));

        Map<String, String> params = new HashMap<>();
        params.put("wd", "如何学习java");
        System.out.println("第二个请求结果："+HttpUtil.doGet(url, params));

    }

}
