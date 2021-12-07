package test.cn.jinty.constant;

import cn.jinty.constant.EnumFactory;
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
    }

}
