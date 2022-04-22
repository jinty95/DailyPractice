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
    public void testBytes() {
        for (BinaryUnitEnum one : BinaryUnitEnum.values()) {
            System.out.println(one);
        }
    }

}
