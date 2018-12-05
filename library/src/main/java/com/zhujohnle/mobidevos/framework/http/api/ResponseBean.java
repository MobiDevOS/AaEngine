package com.zhujohnle.mobidevos.framework.http.api;

/**
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class ResponseBean<T> {
   private T body;

   public T getBody() {
      return body;
   }

   public void setBody(T body) {
      this.body = body;
   }
}
