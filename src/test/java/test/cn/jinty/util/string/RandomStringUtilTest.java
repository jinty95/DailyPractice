package test.cn.jinty.util.string;

import cn.jinty.util.string.RandomStringUtil;
import org.junit.Test;

/**
 * 随机字符串 - 工具类 - 测试
 *
 * @author Jinty
 * @date 2023/6/20
 **/
public class RandomStringUtilTest {

    @Test
    public void testRandom() {
        System.out.println("随机数字字符串");
        System.out.println(RandomStringUtil.randomDigit(5));
        System.out.println(RandomStringUtil.randomDigit(10));
        System.out.println(RandomStringUtil.randomDigit(12));
        System.out.println("随机字母字符串");
        System.out.println(RandomStringUtil.randomLetter(5));
        System.out.println(RandomStringUtil.randomLetter(10));
        System.out.println(RandomStringUtil.randomLetter(12));
        System.out.println("随机数字字母字符串");
        System.out.println(RandomStringUtil.random(5));
        System.out.println(RandomStringUtil.random(10));
        System.out.println(RandomStringUtil.random(12));
    }

}
