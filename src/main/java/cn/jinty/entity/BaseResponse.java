package cn.jinty.entity;

import cn.jinty.enums.ErrorEnum;
import cn.jinty.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // 响应码
    private String code;

    // 提示信息
    private String message;

    // 具体内容
    private T content;

    /**
     * 构造器 - 成功响应
     *
     * @param <T> 泛型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), null);
    }

    /**
     * 构造器 - 成功响应
     *
     * @param content 具体内容
     * @param <T>     泛型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success(T content) {
        return new BaseResponse<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getDesc(), content);
    }

    /**
     * 构造器 - 成功响应
     *
     * @param message 提示信息
     * @param content 具体内容
     * @param <T>     泛型
     * @return 成功响应
     */
    public static <T> BaseResponse<T> success(String message, T content) {
        return new BaseResponse<>(ResultEnum.SUCCESS.getCode(), message, content);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param <T> 泛型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail() {
        return new BaseResponse<>(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getDesc(), null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param message 提示信息
     * @param <T>     泛型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(ResultEnum.FAIL.getCode(), message, null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param code    响应码
     * @param message 提示信息
     * @param <T>     泛型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(String code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    /**
     * 构造器 - 失败响应
     *
     * @param error 错误
     * @param <T>   泛型
     * @return 失败响应
     */
    public static <T> BaseResponse<T> fail(ErrorEnum error) {
        return new BaseResponse<>(error.getCode(), error.getDesc(), null);
    }

}
