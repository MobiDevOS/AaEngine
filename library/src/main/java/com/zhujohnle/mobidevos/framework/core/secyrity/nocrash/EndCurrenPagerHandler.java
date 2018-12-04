package com.zhujohnle.mobidevos.framework.core.secyrity.nocrash;

import android.app.Activity;

/**
 * Created by zhangzheng on 2017/4/5.
 */

public class EndCurrenPagerHandler implements IHandlerException {
    @Override
    public boolean handler(Throwable e) {
        Activity currenActivity = WindowManagerGlobal.getInstance().getCurrenActivity();
        if (currenActivity != null) {
            currenActivity.finish();
        }
        return false;
    }
}
