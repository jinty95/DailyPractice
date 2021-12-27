package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 操作
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum OperationEnum implements EnumInterface {

    INSERT("INSERT", "新增"),
    UPDATE("UPDATE", "更新"),
    DELETE("DELETE", "删除"),
    SELECT("SELECT", "查询");

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 构造器
    OperationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    // getter
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
