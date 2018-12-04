package com.zhujohnle.mobidevos.framework.core.secyrity.nocrash;

/**
 * Created by zhangzheng on 2017/4/5.
 */

public interface OnExceptionCallBack {

    void onThrowException(Thread t, Throwable e, IHandlerException handler);
}
