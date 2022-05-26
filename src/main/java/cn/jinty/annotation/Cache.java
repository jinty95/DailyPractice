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

    String key() default "";

    long expireTime() default -1L;

}
