package com.zhujohnle.mobidevos.recypage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhujohnle.BaseFragment;
import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.view.ErrView;
import com.zhujohnle.mobidevos.view.StatusLayout;

public abstract class LoadBaseFragment extends BaseFragment {
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;
    private View view;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FLog.d("onViewCreated");
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null) {
            view = super.onCreateView(inflater, container, savedInstanceState);
            isViewCreated = true;
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
            loadData();


        }
    }

    protected abstract void loadData();


    public void showErrorView(String msg) {
        if(getStatusLayout()!=null)
        {
            getStatusLayout().showErr();
            View errView = getStatusLayout().getErrView();
            if(errView!=null&& errView instanceof ErrView){
                ErrView kmErrView = (ErrView) errView;
                kmErrView.setErrMsg(msg);
            }
        }
    }

    public void showEmptyView(){
        if(getStatusLayout()!=null)
        {
            getStatusLayout().showEmpty();
        }
    }

    public void showLoadingView() {
        if(getStatusLayout()!=null)
        {
            getStatusLayout().showLoading();
        }
    }


    public void showNormalView() {
        if(getStatusLayout()!=null)
        {
            getStatusLayout().showNormal();
        }
    }


    public StatusLayout getStatusLayout(){
        return null;
    }
}
