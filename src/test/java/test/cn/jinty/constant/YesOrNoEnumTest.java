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
        System.out.println(YesOrNoEnum.parseByCode("Y"));
        System.out.println(YesOrNoEnum.parseByCode("N"));
        System.out.println(YesOrNoEnum.parseByCode("OK"));
        System.out.println(YesOrNoEnum.parseByDesc("是"));
        System.out.println(YesOrNoEnum.parseByDesc("否"));
        System.out.println(YesOrNoEnum.parseByDesc("什么"));
    }

    @Test
    public void test2() {
        System.out.println(YesOrNoEnum.codeToDesc("Y"));
        System.out.println(YesOrNoEnum.codeToDesc("N"));
        System.out.println(YesOrNoEnum.codeToDesc("OK"));
        System.out.println(YesOrNoEnum.descToCode("是"));
        System.out.println(YesOrNoEnum.descToCode("否"));
        System.out.println(YesOrNoEnum.descToCode("什么"));
    }

    @Test
    public void test3() {
        System.out.println(YesOrNoEnum.containsCode("Y"));
        System.out.println(YesOrNoEnum.containsCode("N"));
        System.out.println(YesOrNoEnum.containsCode("OK"));
        System.out.println(YesOrNoEnum.containsDesc("是"));
        System.out.println(YesOrNoEnum.containsDesc("否"));
        System.out.println(YesOrNoEnum.containsDesc("什么"));
    }

}
