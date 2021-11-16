package test.cn.jinty.constant;

import cn.jinty.constant.YesOrNo;
import org.junit.Test;

/**
 * 枚举 - 是否 - 测试
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public class YesOrNoTest {

    @Test
    public void test() {
        System.out.println(YesOrNo.parse("YES"));
        System.out.println(YesOrNo.parseToDesc("YES"));
        System.out.println(YesOrNo.parse("NO"));
        System.out.println(YesOrNo.parseToDesc("NO"));
        System.out.println(YesOrNo.parse("OK"));
        System.out.println(YesOrNo.parseToDesc("OK"));
        System.out.println(YesOrNo.contains("YES"));
        System.out.println(YesOrNo.contains("NO"));
        System.out.println(YesOrNo.contains("OK"));
    }

}
