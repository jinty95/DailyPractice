package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 错误码
 *
 * @author Jinty
 * @date 2021/12/10
 **/
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum implements EnumInterface<String> {

    NOT_FOUND("404", "数据不存在"),
    INTERNAL_SERVER_ERROR("500", "内部错误");

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
