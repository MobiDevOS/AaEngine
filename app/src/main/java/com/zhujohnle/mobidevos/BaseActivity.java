package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.zhujohnle.mobidevos.architecture.mvp.BasePresenter;
import com.zhujohnle.mobidevos.architecture.mvp.BaseView;
import com.zhujohnle.mobidevos.architecture.mvp.PresenterProxy;

/**
 * @auth &{zhujiule}
 * @date 2018/12/10
 * @copyright
 **/
public abstract  class BaseActivity  extends FragmentActivity implements BaseView {

   private PresenterProxy mLogicProxy;
   protected BasePresenter mPresenter;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      mLogicProxy = new PresenterProxy();
      initPresenter();
   }


   @Override
   protected void onStart() {
      super.onStart();

      if (mPresenter != null && !mPresenter.isViewBind()) {
         mLogicProxy.bind(getContact(), this);
      }
   }

   protected void initPresenter() {
      mLogicProxy = new PresenterProxy();
      if (getContact() != null)
         mPresenter = getLogicImpl();
   }


   //获得该页面的实例
   public <T> T getLogicImpl() {
      return mLogicProxy.bind(getContact(), this);
   }

   public abstract Class getContact();

   @Override
   protected void onDestroy() {
      super.onDestroy();

      if(mLogicProxy!=null){
         mLogicProxy.unbind(getContact(),this);
      }


   }
}
