package cn.jinty.entity;

import cn.jinty.enums.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cn.jinty.enums.ResponseCodeEnum.FAIL;
import static cn.jinty.enums.ResponseCodeEnum.SUCCESS;

/**
 * 基础响应体
 *
 * @author Jinty
 * @date 2022/1/14
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    // 响应码：0-成功，1或其它-失败
    private String code;

    // 响应提示
    private String msg;

    // 响应内容
    private T data;

    /**
     * 构造器 - 成功响应
     *
     * @param <T> 类型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(SUCCESS.getCode(), SUCCESS.getDesc(), null);
    }

    /**
     * 构造器 - 成功响应
     *
     * @param data 具体内容
     * @param <T>  类型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SUCCESS.getCode(), SUCCESS.getDesc(), data);
    }

    /**
     * 构造器 - 成功响应
     *
     * @param msg  提示信息
     * @param data 具体内容
     * @param <T>  类型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success(String msg, T data) {
        return new BaseResponse<>(SUCCESS.getCode(), msg, data);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param <T> 类型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail() {
        return new BaseResponse<>(FAIL.getCode(), FAIL.getDesc(), null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param msg 提示信息
     * @param <T> 类型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(String msg) {
        return new BaseResponse<>(FAIL.getCode(), msg, null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param code 响应码
     * @param msg  提示信息
     * @param <T>  类型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(String code, String msg) {
        return new BaseResponse<>(code, msg, null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param error 错误
     * @param <T>   类型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(ResponseCodeEnum error) {
        return new BaseResponse<>(error.getCode(), error.getDesc(), null);
    }

}
