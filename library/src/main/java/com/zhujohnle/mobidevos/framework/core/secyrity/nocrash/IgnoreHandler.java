package com.zhujohnle.mobidevos.framework.core.secyrity.nocrash;


/**
 * Created by zhangzheng on 2017/4/5.
 */

public class IgnoreHandler implements IHandlerException{
    @Override
    public boolean handler(Throwable e) {
        return false;
    }
}
