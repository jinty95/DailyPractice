package cn.jinty.annotation;

import java.lang.annotation.*;

/**
 * 注解 - 缓存 (将方法入参作为key，出参作为value)
 *
 * @author Jinty
 * @date 2022/5/25
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cache {

    // key前缀
    String prefix() default "";

    // key过期时间
    long expireTime() default -1L;

}
