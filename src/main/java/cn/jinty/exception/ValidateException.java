package cn.jinty.exception;

/**
 * 校验异常
 *
 * @author Jinty
 * @date 2023/6/14
 **/
public class ValidateException extends RuntimeException {

    public ValidateException() {
        super();
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

}
