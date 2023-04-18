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
        String[] keys = {"YesNoEnum", "OperationEnum", "EntityEnum", "FileTypeEnum",
                "BinaryUnitEnum", "ContentTypeEnum", "CycleTypeEnum", "TimeUnitEnum"};
        for (String key : keys) {
            System.out.println(EnumFactory.get(key));
        }
    }

    @Test
    public void testGet2() {
        List<KeyValue<String, String>> list = EnumFactory.get("ResponseCodeEnum");
        System.out.println(JSON.toJSONString(list));
        System.out.println();
        list.forEach(System.out::println);
    }

    @Test
    public void testGet3() {
        EnumFactory.get("SpecialCharEnum").forEach(System.out::println);
    }

}
