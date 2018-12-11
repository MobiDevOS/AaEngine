package com.zhujohnle.mobidevos.recypage;


import com.zhujohnle.mobidevos.architecture.mvp.BasePresenter;
import com.zhujohnle.mobidevos.utils.ToastUtils;

public abstract class LoadBasePresenter<T extends LoadView, V> extends BasePresenter<T> {


    public void showLoading() {
        if (getView() != null) {
            getView().showLoadingView();
        }
    }

    public void emptyView() {
        if (getView() != null) {
            getView().showEmptyView();
        } else {
        }
    }

    public void successView(V resultData) {
        if (getView() != null) {
            getView().requestSuccess(resultData);
        }
        if (getView() != null) {
            getView().showNormalView();
        }
    }

    public void errView(String errMsg) {
        if (getView() != null) {
            getView().showErrorView(errMsg);
        } else {
            ToastUtils.showShort(errMsg);
        }
    }

}
