package test.cn.jinty.enums;

import cn.jinty.enums.BinaryUnitEnum;
import org.junit.Test;

import java.math.BigDecimal;

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

    @Test
    public void testTransferUnit() {
        BigDecimal sourceNum = BigDecimal.valueOf(4.4);
        BinaryUnitEnum sourceUnit = BinaryUnitEnum.GB;
        for (BinaryUnitEnum targetUnit : BinaryUnitEnum.values()) {
            BigDecimal targetNum = BinaryUnitEnum.transferUnit(sourceNum, sourceUnit, targetUnit);
            System.out.printf("%s%s=%s%s%n", sourceNum, sourceUnit.getCode(), targetNum, targetUnit.getCode());
        }
    }

}
