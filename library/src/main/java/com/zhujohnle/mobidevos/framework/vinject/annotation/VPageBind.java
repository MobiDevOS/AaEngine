package com.zhujohnle.mobidevos.framework.vinject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @auth &{zhujiule}
 * @date 2018/12/4
 * @copyright
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VPageBind {
   int value();
}
