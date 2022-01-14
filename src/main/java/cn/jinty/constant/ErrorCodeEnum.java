package cn.jinty.constant;

import cn.jinty.constant.common.EnumInterface;

/**
 * 枚举 - 错误码
 *
 * @author Jinty
 * @date 2021/12/10
 **/
public enum ErrorCodeEnum implements EnumInterface<String> {

    NOT_FOUND("404", "数据不存在"),
    INTERNAL_SERVER_ERROR("500", "内部错误");

    // 编码
    private final String code;
    // 描述
    private final String desc;

    // 构造器
    ErrorCodeEnum(String code, String desc) {
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
