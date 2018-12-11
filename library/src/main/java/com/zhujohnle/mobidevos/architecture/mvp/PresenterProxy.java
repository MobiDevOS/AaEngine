package com.zhujohnle.mobidevos.architecture.mvp;

import java.lang.annotation.Annotation;

/**
 * @auth &{zhujiule}
 * @date 2018/12/9
 * @copyright
 **/
public class PresenterProxy {

      private BasePresenter mBasePresenter;

      public PresenterProxy() {

      }

      public void init(Class... clss) {
         for (Class cls : clss) {
            if (cls.isAnnotationPresent(Contact.class)) {
               for (Annotation ann : cls.getDeclaredAnnotations()) {
                  if (ann instanceof Contact) {
                     try {
                        mBasePresenter = (BasePresenter) ((Contact) ann).value().newInstance();
                     } catch (InstantiationException e) {
                        e.printStackTrace();
                     } catch (IllegalAccessException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }
         }
      }

      // 初始化presenter add map
      public <T> T bind(Class clzz, BaseView var1) {
         if (mBasePresenter == null) {
            init(clzz);
         }
         if (var1 != mBasePresenter.getView()) {
            if (mBasePresenter.getView() != null) {
               mBasePresenter.detachView();
            }
            mBasePresenter.attachView(var1);
         }
         return (T) mBasePresenter;
      }

      // 解除绑定 移除map
      public void unbind(Class clzz, BaseView var1) {

         if (mBasePresenter != null && var1 == mBasePresenter.getView()) {
            if (mBasePresenter.getView() != null) {
               mBasePresenter.detachView();
            }
            mBasePresenter = null;
         }
      }
}
