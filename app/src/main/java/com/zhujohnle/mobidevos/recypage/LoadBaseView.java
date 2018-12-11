package com.zhujohnle.mobidevos.recypage;


import com.zhujohnle.mobidevos.architecture.mvp.BaseView;

/**
 * 一个普通适配的页面 包含数据为空时候的布局 ，基础视图，错误视图，加载中视图
 * */
public interface LoadBaseView extends BaseView {

    /*展示空视图*/
    void showEmptyView();

    /*展示加载异常视图*/
    void showErrorView(String msg);

    /*展示加载中视图*/
    void showLoadingView();

    /*展示普通页面业务视图*/
    void showNormalView();

}
