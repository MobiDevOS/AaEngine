package com.zhujohnle.mobidevos.framework.http;

import android.app.Application;

import com.zhujohnle.mobidevos.MobiDevOsEngine;
import com.zhujohnle.mobidevos.framework.http.core.OkHttpConfig;
import com.zhujohnle.mobidevos.framework.http.core.interceptor.TokenInterceptor;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * http 及https 协议引擎入口
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class HttpEngine {

   private static final int READTIMEOUT = 60;
   private static final  int WRITETIMEOUT = 60;
   private static final int CONNECTTIMEOUT = 20;


   //设置全局的http请求标准
   public static void initGlobalHttpReuqest(String host, Map<String, Object> headerMaps) {
//     todo
// 获取证书
//        InputStream cerInputStream = null;
//        InputStream bksInputStream = null;
//        try {
//            cerInputStream = getAssets().open("YourSSL.cer");
//            bksInputStream = getAssets().open("your.bks");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

      OkHttpClient okHttpClient = new OkHttpConfig
            .Builder()
            //全局的请求头信息
            .setHeaders(headerMaps)
            //开启缓存策略(默认false)
            //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
            //2、在没有网络的时候，去读缓存中的数据。
            /*    .setCache(true)
                //全局持久话cookie,保存本地每次都会携带在header中（默认false）
                .setSaveCookie(true)*/
            //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
            //.setAddInterceptor(null)
            //全局ssl证书认证
            //1、信任所有证书,不安全有风险（默认信任所有证书）
            //.setSslSocketFactory()
            //2、使用预埋证书，校验服务端证书（自签名证书）
            //.setSslSocketFactory(cerInputStream)
            //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
            //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
            //全局超时配置
            .setReadTimeout(READTIMEOUT)
            //全局超时配置
            .setWriteTimeout(WRITETIMEOUT)
            //全局超时配置
            .setConnectTimeout(CONNECTTIMEOUT)
            .setAddInterceptor(new TokenInterceptor())
            //全局是否打开请求log日志
            .setDebug(MobiDevOsEngine.isDebug)
            .build();
      RxHttpUtils
            .getInstance()
            .init((Application) MobiDevOsEngine.getContext())
            .config()
            //配置全局baseUrl
            .setBaseUrl(host)
            //开启全局配置
            .setOkClient(okHttpClient);

//        如果以上OkHttpClient的配置满足不了你，传入自己的 OkHttpClient 自行设置
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//
//        builder
//                .addInterceptor(log_interceptor)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .connectTimeout(10, TimeUnit.SECONDS);
//
//        RxHttpUtils
//                .getInstance()
//                .config()
//                .setBaseUrl(BuildConfig.BASE_URL)
//                .setOkClient(builder.build());
   }



   public static <P> P getApiEngine(Class<P> mClas){
      return RxHttpUtils.createApi(mClas);
   }




}
