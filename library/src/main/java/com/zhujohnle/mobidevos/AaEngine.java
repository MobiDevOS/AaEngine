package com.zhujohnle.mobidevos;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zhujohnle.mobidevos.framework.http.HttpEngine;

/**
 * MobiDevOs引擎入口
 *
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class AaEngine {

   //全局application入口
   private static Context mContext;

   private static AaEngine mEngine;

   private HttpEngine mHttpEngine;

   public AaEngine(Context mContext) {
      this.mContext = mContext;
   }

   public static AaEngine getInstace(){
      return mEngine;
   }

   public static AaEngine getInstace(@NonNull Context mContext)  {
      if(mContext==null){
         throw new IllegalArgumentException("u can't instantiate me...");
      }

      synchronized (mContext) {
         if (mEngine == null) {
            mEngine = new AaEngine(mContext);
         }
      }
      return mEngine;
   }

   public static Context getContext() {
      if(mContext==null){
         throw new IllegalArgumentException(" please init Engine first");
      }
      return mContext;
   }


   public void initProperties(boolean isDebug){
      mHttpEngine = new HttpEngine(isDebug);
   }

   public HttpEngine getHttpEngine(){
      if(mHttpEngine==null){
         throw new IllegalArgumentException(" please init Engine first");
      }
      return mHttpEngine;
   }
}
