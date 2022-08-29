package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 操作
 *
 * @author Jinty
 * @date 2021/12/10
 **/
@Getter
@AllArgsConstructor
public enum OperationEnum implements EnumInterface<String> {

    INSERT("INSERT", "新增"),
    UPDATE("UPDATE", "更新"),
    DELETE("DELETE", "删除"),
    SELECT("SELECT", "查询"),
    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
