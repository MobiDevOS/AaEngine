package com.zhujohnle.mobidevos.framework.core.secyrity.nocrash;

/**
 * Created by zhangzheng on 2017/4/5.
 */

public class HandlerExceptionFactory implements IHandlerExceptionFactory {
    @Override
    public IHandlerException get(Throwable e) {
        if(e instanceof IllegalStateException){
            return new EndCurrenPagerHandler();
        }

        if(e instanceof SecurityException){
            return new KillAppHandler();
        }

        return new IgnoreHandler();
    }
}
