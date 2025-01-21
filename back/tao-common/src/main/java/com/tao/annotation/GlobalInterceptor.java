package com.tao.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface GlobalInterceptor {
    boolean checkLogin() default false;
    boolean checkParams() default false;
}
