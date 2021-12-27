package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 - 实体(数据表)
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum EntityEnum implements EnumInterface {

    USER("USER", "用户"),
    ROLE("ROLE", "角色"),
    PERMISSION("PERMISSION", "权限");

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 构造器
    EntityEnum(String code, String desc) {
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
