package com.project.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author liangyuding
 * @date 2020-03-10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
