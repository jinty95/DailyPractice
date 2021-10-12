package test.cn.jinty.util;

import cn.jinty.util.BoshUtil;
import org.junit.Test;

/**
 * 狗屁不通生成器 - 测试
 *
 * @author jinty
 * @date 2021/6/2
 **/
public class BoshUtilTest {

    @Test
    public void test() {
        System.out.println(
                BoshUtil.generate(
                        "狗屁不通生成器", 1600
                )
        );
    }

}
