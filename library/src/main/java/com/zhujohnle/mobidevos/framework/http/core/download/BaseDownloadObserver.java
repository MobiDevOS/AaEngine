package com.zhujohnle.mobidevos.framework.http.core.download;


import android.support.annotation.NonNull;

import com.zhujohnle.mobidevos.exception.ApiException;
import com.zhujohnle.mobidevos.utils.ToastUtils;

import io.reactivex.Observer;
import okhttp3.ResponseBody;


/**
 * Created by allen on 2017/6/13.
 *
 * @author Allen
 */

public abstract class BaseDownloadObserver implements Observer<ResponseBody> {

    /**
     * 失败回调
     *
     * @param errorMsg 错误信息
     */
    protected abstract void doOnError(String errorMsg);


    @Override
    public void onError(@NonNull Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    private void setError(String errorMsg) {
        ToastUtils.showLong(errorMsg);
        doOnError(errorMsg);
    }

}
