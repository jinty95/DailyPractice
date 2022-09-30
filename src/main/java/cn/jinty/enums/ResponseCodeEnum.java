package cn.jinty.enums;

import cn.jinty.enums.common.EnumInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举 - 响应码
 *
 * @author Jinty
 * @date 2021/12/10
 **/
@Getter
@AllArgsConstructor
public enum ResponseCodeEnum implements EnumInterface<String> {

    // 通用
    SUCCESS("0", "成功"),
    FAIL("1", "失败"),

    // HTTP
    BAD_REQUEST("400", "请求报文错误"),
    UNAUTHORIZED("401", "请求未通过认证"),
    FORBIDDEN("403", "请求被拒绝"),
    NOT_FOUND("404", "请求资源不存在"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    SERVICE_UNAVAILABLE("503", "服务器暂不可用"),

    // 业务
    LOGIN_FAIL("10000", "登录失败"),
    USER_NOT_FOUND("10001", "用户不存在"),
    PASSWORD_ERROR("10002", "密码错误"),
    CAPTCHA_ERROR("10003", "验证码错误"),
    LOGIN_ATTEMPT_EXCEED("10004", "尝试登录次数已达上限"),
    LOGIN_EXPIRED("10005", "登录已过期"),

    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
