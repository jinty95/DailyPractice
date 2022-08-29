package cn.jinty.annotation;

import java.lang.annotation.*;

/**
 * 字段名称
 *
 * @author Jinty
 * @date 2022/8/29
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FieldName {

    // 名称
    String value() default "";

}
