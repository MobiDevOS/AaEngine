package com.zhujohnle.mobidevos.httpenginer;

import com.zhujohnle.mobidevos.framework.http.HttpEngine;
import com.zhujohnle.mobidevos.framework.http.RxHttpUtils;
import com.zhujohnle.mobidevos.framework.http.api.DataObserver;
import com.zhujohnle.mobidevos.framework.http.api.ResponseBean;
import com.zhujohnle.mobidevos.framework.http.core.interceptor.Transformer;
import com.zhujohnle.mobidevos.utils.GsonUtils;

import io.reactivex.Observable;

/**
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright 杭州物恋网科技有限公司
 **/
public class TestReuqust {

   public void testGet(){
      HttpEngine.getApiEngine(Api.class).getUserInfo("123");
   }

   public void testPost(){
      Object mObject = new Object();
      String body = GsonUtils.toJson(mObject);
      HttpEngine.getApiEngine(Api.class).postUseInfo(body);
   }

   private void dealData(Observable<ResponseBean> mObservable){

      mObservable.compose(Transformer.<ResponseBean>switchSchedulers())
            //todo 注意可以传入dialog 用作在内部管理
            .subscribe(new DataObserver() {
               @Override
               public void onNext(Object o) {

               }
               @Override
               public boolean isHideToast() {
                  return true;
               }

               @Override
               protected String getTag() {
                  return null;
               }

               @Override
               public void doOnNext(ResponseBean responseBean) {
               }

               @Override
               protected void onError(String errorMsg) {
               }
            });
   }


   public void cancalTag(String tag){
      RxHttpUtils.cancel(tag);
      //or RxHttpUtils.cancelAll();
   }
}
