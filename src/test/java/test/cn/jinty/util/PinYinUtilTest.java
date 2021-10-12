package test.cn.jinty.util;

import cn.jinty.util.PinYinUtil;
import org.junit.Test;

/**
 * 汉字转拼音 - 测试
 *
 * @author jinty
 * @date 2021/5/26
 **/
public class PinYinUtilTest {

    @Test
    public void test() {
        System.out.println(PinYinUtil.chineseToPinYin("我是谁"));
        System.out.println(PinYinUtil.chineseToShortPinYin("我是谁"));
    }

}
