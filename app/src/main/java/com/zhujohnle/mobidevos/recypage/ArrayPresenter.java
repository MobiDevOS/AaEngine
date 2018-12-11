package com.zhujohnle.mobidevos.recypage;

import com.zhujohnle.mobidevos.architecture.mvp.BasePresenter;
import com.zhujohnle.mobidevos.utils.ToastUtils;

import java.util.ArrayList;

/**
 * 作者:zft
 * 日期:2018/8/2 0002.
 */
public abstract class ArrayPresenter<T extends ArrayView, V> extends BasePresenter<T> {
    public boolean isFirst = true;

    public void showLoading(boolean isRefresh) {
        if (isFirst) {
            if (getView() != null) {
                getView().showLoadingView();
            }
        }
    }

    public void successView(boolean isRefresh, ArrayList<V> resultData, int pageNo, int totalPage) {
        if (isRefresh) {
            if (getView() != null) {
                getView().refreshSuccess(resultData,pageNo,totalPage);
            }
        } else {
            if (getView() != null) {
                getView().loadmoreSuccess(resultData,pageNo,totalPage );
            }
        }
        if (isFirst) {
            isFirst = false;
        }
    }

    public void emptyView(boolean isRefresh) {
        if (getView() != null && isRefresh) {
            getView().showEmptyView();
            isFirst = true;
        } else {
        }
    }

    public void errView(boolean isRefresh, String errMsg) {
        if (getView() != null && isRefresh) {
            getView().showErrorView(errMsg);
            isFirst = true;
        } else {
            ToastUtils.showShort(errMsg);
        }
    }

}
