package test.cn.jinty.enums;

import cn.jinty.enums.YesNoEnum;
import org.junit.Test;

/**
 * 枚举 - 是否 - 测试
 *
 * @author Jinty
 * @date 2021/11/16
 **/
public class YesNoEnumTest {

    @Test
    public void test1() {
        System.out.println(YesNoEnum.parseByCode((byte) 1));
        System.out.println(YesNoEnum.parseByCode((byte) 0));
        System.out.println(YesNoEnum.parseByCode((byte) -1));
        System.out.println(YesNoEnum.parseByDesc("是"));
        System.out.println(YesNoEnum.parseByDesc("否"));
        System.out.println(YesNoEnum.parseByDesc("什么"));
    }

    @Test
    public void test2() {
        System.out.println(YesNoEnum.codeToDesc((byte) 1));
        System.out.println(YesNoEnum.codeToDesc((byte) 0));
        System.out.println(YesNoEnum.codeToDesc((byte) -1));
        System.out.println(YesNoEnum.descToCode("是"));
        System.out.println(YesNoEnum.descToCode("否"));
        System.out.println(YesNoEnum.descToCode("什么"));
    }

    @Test
    public void test3() {
        System.out.println(YesNoEnum.containsCode((byte) 1));
        System.out.println(YesNoEnum.containsCode((byte) 0));
        System.out.println(YesNoEnum.containsCode((byte) -1));
        System.out.println(YesNoEnum.containsDesc("是"));
        System.out.println(YesNoEnum.containsDesc("否"));
        System.out.println(YesNoEnum.containsDesc("什么"));
    }

}
