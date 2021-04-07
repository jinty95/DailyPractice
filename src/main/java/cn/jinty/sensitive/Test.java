package cn.jinty.sensitive;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 敏感词测试
 * @Author jinty
 * @Date 2019年9月16日11点31分
 */
public class Test {
    @org.junit.Test
    public void test(){
        Map map = new SensitiveWordLib().getSensitiveWordMap();
        System.out.println(map);
        SensitiveWordFilter filter = new SensitiveWordFilter();
        String content = "真是废物啊！您真的是废物啊！";
        System.out.println(filter.filterSensitiveWord(content));
    }
}
