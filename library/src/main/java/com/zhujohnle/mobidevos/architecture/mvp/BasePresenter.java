package com.zhujohnle.mobidevos.architecture.mvp;

/**
 * @auth &{zhujiule}
 * @date 2018/12/7
 * @copyright
 **/
public abstract class BasePresenter {

   BaseView mBaseView;

   public BaseView getView(){
      return mBaseView;
   }

   public abstract void attachView(BaseView mBaseView);

   public abstract void detachView();

}
