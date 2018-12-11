package com.zhujohnle.mobidevos.architecture.mvp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现view和presenter的契约关联
 * @auth &{zhujiule}
 * @date 2018/12/7
 * @copyright
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Contact {
   Class value();
}
