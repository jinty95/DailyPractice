package test.cn.jinty.constant;

import cn.jinty.constant.YesOrNoEnum;
import org.junit.Test;

/**
 * 枚举 - 是否 - 测试
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public class YesOrNoEnumTest {

    @Test
    public void test1() {
        System.out.println(YesOrNoEnum.parseByName("YES"));
        System.out.println(YesOrNoEnum.parseByName("NO"));
        System.out.println(YesOrNoEnum.parseByName("OK"));
        System.out.println(YesOrNoEnum.parseByDesc("是"));
        System.out.println(YesOrNoEnum.parseByDesc("否"));
        System.out.println(YesOrNoEnum.parseByDesc("什么"));
    }

    @Test
    public void test2() {
        System.out.println(YesOrNoEnum.nameToDesc("YES"));
        System.out.println(YesOrNoEnum.nameToDesc("NO"));
        System.out.println(YesOrNoEnum.nameToDesc("OK"));
        System.out.println(YesOrNoEnum.descToName("是"));
        System.out.println(YesOrNoEnum.descToName("否"));
        System.out.println(YesOrNoEnum.descToName("什么"));
    }

    @Test
    public void test3() {
        System.out.println(YesOrNoEnum.containsName("YES"));
        System.out.println(YesOrNoEnum.containsName("NO"));
        System.out.println(YesOrNoEnum.containsName("OK"));
        System.out.println(YesOrNoEnum.containsDesc("是"));
        System.out.println(YesOrNoEnum.containsDesc("否"));
        System.out.println(YesOrNoEnum.containsDesc("什么"));
    }

}
