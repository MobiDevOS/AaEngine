package com.zhujohnle.mobidevos.framework.http.core.interceptor;

import com.zhujohnle.mobidevos.framework.http.HttpEngineKeys;
import com.zhujohnle.mobidevos.utils.SPUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class TokenInterceptor implements Interceptor {


   @Override
   public Response intercept(Chain chain) throws IOException {
      Request.Builder request = chain.request().newBuilder();
      String token = SPUtils.getInstance().getString("TOEKN");
      if (token != null) {
         request.addHeader(HttpEngineKeys.TOKEN, token);
      }
      return chain.proceed(request.build());
   }

}
