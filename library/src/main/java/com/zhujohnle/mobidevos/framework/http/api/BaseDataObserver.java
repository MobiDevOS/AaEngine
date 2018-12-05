package com.zhujohnle.mobidevos.framework.http.api;

import com.zhujohnle.mobidevos.exception.ApiException;
import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.framework.http.core.RxHttpManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by Allen on 2017/10/27.
 *
 * @author Allen
 * <p>
 * 基类BaseObserver使用BaseData
 */

public abstract class BaseDataObserver<T> implements Observer<ResponseBean<T>>, IDataSubscriber<T> {

    /**
     * 是否隐藏toast
     *
     * @return
     */
    protected boolean isHideToast() {
        return false;
    }


    /**
     * 标记网络请求的tag
     * tag下的一组或一个请求，用来处理一个页面的所以请求或者某个请求
     * 设置一个tag就行就可以取消当前页面所有请求或者某个请求了
     *
     * @return string
     */
    protected abstract  String getTag();

    @Override
    public void onSubscribe(Disposable d) {
        RxHttpManager.get().add(getTag(), d);
        doOnSubscribe(d);
    }

    @Override
    public void onNext(ResponseBean<T> responseBean) {
//        if (responseBean.getResult() == ConstantValue.FAIL) {
//            EventBusUtil.sendEvent(true, EventBusTag.INVALID);
//        }
        doOnNext(responseBean);

    }

    @Override
    public void onError(Throwable e) {
        FLog.eJson(e.toString());
        String error = ApiException.handleException(e).getMessage();
        setError(error);
    }

    @Override
    public void onComplete() {
        RxHttpManager.get().remove(getTag());
        doOnCompleted();
    }


    private void setError(String errorMsg) {
        doOnError(errorMsg);
    }

}
