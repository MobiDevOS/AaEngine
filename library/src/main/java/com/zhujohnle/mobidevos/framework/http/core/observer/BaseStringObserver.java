package com.zhujohnle.mobidevos.framework.http.core.observer;


import com.zhujohnle.mobidevos.exception.ApiException;
import com.zhujohnle.mobidevos.framework.http.core.subscriber.IStringSubscriber;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Allen on 2017/10/31.
 *
 * @author Allen
 *         <p>
 *         结果不做处理直接返回string
 */

public abstract class BaseStringObserver implements Observer<String>, IStringSubscriber {

    /**
     * 是否隐藏toast
     *
     * @return
     */
    protected boolean isHideToast() {
        return false;
    }

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(String string) {
        doOnNext(string);
    }

    @Override
    public void onError(Throwable e) {
        String error = ApiException.handleException(e).getMessage();
        doOnError(error);
    }

    @Override
    public void onComplete() {
        doOnCompleted();
    }

}
