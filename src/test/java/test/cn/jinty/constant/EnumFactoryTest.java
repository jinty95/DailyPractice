package test.cn.jinty.constant;

import cn.jinty.constant.common.EnumFactory;
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
        System.out.println(EnumFactory.get("YesOrNoEnum"));
        System.out.println(EnumFactory.get("ResultEnum"));
        System.out.println(EnumFactory.get("OperationEnum"));
        System.out.println(EnumFactory.get("EntityEnum"));
        System.out.println(EnumFactory.get("ErrorCodeEnum"));
    }

}
