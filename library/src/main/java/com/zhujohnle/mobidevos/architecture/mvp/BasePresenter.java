package com.zhujohnle.mobidevos.architecture.mvp;

/**
 * @auth &{zhujiule}
 * @date 2018/12/7
 * @copyright
 **/
public abstract class BasePresenter <T extends BaseView>  {


   private T mView;

   public void attachView(T mvpView) {
      this.mView = mvpView;
   }

   public void detachView() {
      this.mView = null;
   }



   public boolean isViewBind() {
      return mView != null;
   }


   public T getView() {
      return mView;
   }




}
