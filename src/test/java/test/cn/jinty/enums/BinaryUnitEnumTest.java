package test.cn.jinty.enums;

import cn.jinty.enums.BinaryUnitEnum;
import org.junit.Test;

/**
 * 枚举 - 二进制单位 - 测试
 *
 * @author Jinty
 * @date 2022/4/22
 **/
public class BinaryUnitEnumTest {

    @Test
    public void testValues() {
        for (BinaryUnitEnum one : BinaryUnitEnum.values()) {
            System.out.println(one);
        }
    }

    @Test
    public void testLongValue() {
        // long类型最大支持二进制单位为EB
        for (BinaryUnitEnum one : BinaryUnitEnum.values()) {
            System.out.printf("1%s=%dB%n", one.getCode(), one.getBytes().longValue());
        }
    }

}
