package test.cn.jinty.enums.common;

import cn.jinty.enums.common.EnumFactory;
import org.junit.Test;

/**
 * 枚举 - 工厂 - 测试
 *
 * @author Jinty
 * @date 2021/12/7
 */
public class EnumFactoryTest {

    @Test
    public void testGet() {
        System.out.println(EnumFactory.get("YesNoEnum"));
        System.out.println(EnumFactory.get("ResultEnum"));
        System.out.println(EnumFactory.get("ErrorEnum"));
        System.out.println(EnumFactory.get("OperationEnum"));
        System.out.println(EnumFactory.get("EntityEnum"));
        System.out.println(EnumFactory.get("FileTypeEnum"));
        System.out.println(EnumFactory.get("BinaryUnitEnum"));
        System.out.println(EnumFactory.get("ContentTypeEnum"));
    }

}
