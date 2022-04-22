package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 实体(数据表)
 *
 * @author Jinty
 * @date 2021/12/10
 **/
@Getter
@AllArgsConstructor
public enum EntityEnum implements EnumInterface<String> {

    USER("USER", "用户"),
    USER_GROUP("USER_GROUP", "用户组"),
    ROLE("ROLE", "角色"),
    PERMISSION("PERMISSION", "权限");

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
