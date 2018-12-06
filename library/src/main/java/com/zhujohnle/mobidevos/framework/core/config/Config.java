package com.zhujohnle.mobidevos.framework.core.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数的配置
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright
 **/


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {

   String name() default "";

   String type() default "";
}
