package cn.jinty.exception;

/**
 * 自定义业务异常
 * 因为RuntimeException内部都是构造函数，无法被子类继承，所以子类要提供同样的构造函数，在函数内调用父类的构造函数
 *
 * @author Jinty
 * @date 2023/6/14
 **/
public class BizException extends RuntimeException {

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

}
