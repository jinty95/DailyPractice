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
    public void testGetByType1() {
        String[] types = {"YesNoEnum", "OperationEnum", "EntityEnum", "FileTypeEnum",
                "BinaryUnitEnum", "ContentTypeEnum", "CycleTypeEnum", "TimeUnitEnum"};
        for (String type : types) {
            System.out.println(EnumFactory.getByType(type));
        }
    }

    @Test
    public void testGetByType2() {
        List<KeyValue<String, String>> list = EnumFactory.getByType("ResponseCodeEnum");
        System.out.println(JSON.toJSONString(list));
        System.out.println();
        list.forEach(System.out::println);
    }

    @Test
    public void testGetByType3() {
        EnumFactory.getByType("SpecialCharEnum").forEach(System.out::println);
    }

    @Test
    public void testExistTypeAndCode() {
        System.out.println(EnumFactory.existTypeAndCode("YesNoEnum", (byte) 0));
        System.out.println(EnumFactory.existTypeAndCode("YesNoEnum", (byte) 1));
        System.out.println(EnumFactory.existTypeAndCode("OperationEnum", "INSERT"));
        System.out.println(EnumFactory.existTypeAndCode("OperationEnum", "SELECT"));
        System.out.println(EnumFactory.existTypeAndCode("OperationEnum", "MODIFY"));
    }

    @Test
    public void testExistTypeAndDesc() {
        System.out.println(EnumFactory.existTypeAndDesc("YesNoEnum", "否"));
        System.out.println(EnumFactory.existTypeAndDesc("YesNoEnum", "是"));
        System.out.println(EnumFactory.existTypeAndDesc("OperationEnum", "新增"));
        System.out.println(EnumFactory.existTypeAndDesc("OperationEnum", "查询"));
        System.out.println(EnumFactory.existTypeAndDesc("OperationEnum", "更改"));
    }

    @Test
    public void testGetByTypeAndCode() {
        System.out.println(EnumFactory.getByTypeAndCode("YesNoEnum", (byte) 0));
        System.out.println(EnumFactory.getByTypeAndCode("YesNoEnum", (byte) 1));
        System.out.println(EnumFactory.getByTypeAndCode("OperationEnum", "INSERT"));
        System.out.println(EnumFactory.getByTypeAndCode("OperationEnum", "SELECT"));
        System.out.println(EnumFactory.getByTypeAndCode("OperationEnum", "MODIFY"));
    }

    @Test
    public void testGetByTypeAndDesc() {
        System.out.println(EnumFactory.getByTypeAndDesc("YesNoEnum", "否"));
        System.out.println(EnumFactory.getByTypeAndDesc("YesNoEnum", "是"));
        System.out.println(EnumFactory.getByTypeAndDesc("OperationEnum", "新增"));
        System.out.println(EnumFactory.getByTypeAndDesc("OperationEnum", "查询"));
        System.out.println(EnumFactory.getByTypeAndDesc("OperationEnum", "更改"));
    }

    @Test
    public void testTypeAndCodeToDesc() {
        System.out.println(EnumFactory.typeAndCodeToDesc("YesNoEnum", (byte) 0));
        System.out.println(EnumFactory.typeAndCodeToDesc("YesNoEnum", (byte) 1));
        System.out.println(EnumFactory.typeAndCodeToDesc("OperationEnum", "INSERT"));
        System.out.println(EnumFactory.typeAndCodeToDesc("OperationEnum", "SELECT"));
        System.out.println(EnumFactory.typeAndCodeToDesc("OperationEnum", "MODIFY"));
    }

    @Test
    public void testTypeAndDescToCode() {
        System.out.println((Object) EnumFactory.typeAndDescToCode("YesNoEnum", "否"));
        System.out.println((Object) EnumFactory.typeAndDescToCode("YesNoEnum", "是"));
        System.out.println((Object) EnumFactory.typeAndDescToCode("OperationEnum", "新增"));
        System.out.println((Object) EnumFactory.typeAndDescToCode("OperationEnum", "查询"));
        System.out.println((Object) EnumFactory.typeAndDescToCode("OperationEnum", "更改"));
    }

}
