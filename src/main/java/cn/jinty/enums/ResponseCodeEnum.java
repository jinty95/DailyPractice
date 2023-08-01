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
    HTTP_OK("200", "请求成功"),
    HTTP_MOVED_PERMANENTLY("301", "永久重定向"),
    HTTP_MOVED_TEMPORARILY("302", "临时重定向"),
    HTTP_BAD_REQUEST("400", "报文错误"),
    HTTP_UNAUTHORIZED("401", "未通过认证"),
    HTTP_FORBIDDEN("403", "无权限访问"),
    HTTP_NOT_FOUND("404", "资源不存在"),
    HTTP_INTERNAL_SERVER_ERROR("500", "服务器内部错误"),
    HTTP_BAD_GATEWAY("502", "网关错误"),
    HTTP_SERVICE_UNAVAILABLE("503", "服务器暂不可用"),

    // 业务
    BIZ_LOGIN_FAIL("10000", "登录失败"),
    BIZ_USER_NOT_FOUND("10001", "用户不存在"),
    BIZ_PASSWORD_ERROR("10002", "密码错误"),
    BIZ_CAPTCHA_ERROR("10003", "验证码错误"),
    BIZ_LOGIN_ATTEMPT_EXCEED("10004", "尝试登录次数已达上限"),
    BIZ_LOGIN_EXPIRED("10005", "登录已过期"),

    ;

    // 编码
    private final String code;
    // 描述
    private final String desc;

}
