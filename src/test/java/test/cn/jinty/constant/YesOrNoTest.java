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
    public void test1() {
        System.out.println(YesOrNo.parseByName("YES"));
        System.out.println(YesOrNo.parseByName("NO"));
        System.out.println(YesOrNo.parseByName("OK"));
        System.out.println(YesOrNo.parseByDesc("是"));
        System.out.println(YesOrNo.parseByDesc("否"));
        System.out.println(YesOrNo.parseByDesc("什么"));
    }

    @Test
    public void test2() {
        System.out.println(YesOrNo.nameToDesc("YES"));
        System.out.println(YesOrNo.nameToDesc("NO"));
        System.out.println(YesOrNo.nameToDesc("OK"));
        System.out.println(YesOrNo.descToName("是"));
        System.out.println(YesOrNo.descToName("否"));
        System.out.println(YesOrNo.descToName("什么"));
    }

    @Test
    public void test3() {
        System.out.println(YesOrNo.containsName("YES"));
        System.out.println(YesOrNo.containsName("NO"));
        System.out.println(YesOrNo.containsName("OK"));
        System.out.println(YesOrNo.containsDesc("是"));
        System.out.println(YesOrNo.containsDesc("否"));
        System.out.println(YesOrNo.containsDesc("什么"));
    }

}
