package test.cn.jinty.enums.common;

import cn.jinty.entity.KeyValue;
import cn.jinty.enums.common.EnumFactory;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * 枚举 - 工厂 - 测试
 *
 * @author Jinty
 * @date 2021/12/7
 */
public class EnumFactoryTest {

    @Test
    public void testGet1() {
        System.out.println(EnumFactory.get("YesNoEnum"));
        System.out.println(EnumFactory.get("OperationEnum"));
        System.out.println(EnumFactory.get("EntityEnum"));
        System.out.println(EnumFactory.get("FileTypeEnum"));
        System.out.println(EnumFactory.get("BinaryUnitEnum"));
        System.out.println(EnumFactory.get("ContentTypeEnum"));
        System.out.println(EnumFactory.get("CycleTypeEnum"));
    }

    @Test
    public void testGet2() {
        List<KeyValue<String, String>> list = EnumFactory.get("ResponseCodeEnum");
        System.out.println(JSON.toJSONString(list));
    }

}
