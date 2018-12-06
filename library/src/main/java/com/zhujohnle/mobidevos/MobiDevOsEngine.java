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
public class MobiDevOsEngine {

   //全局application入口
   private static Context mContext;

   private static MobiDevOsEngine mEngine;

   private HttpEngine mHttpEngine;

   public MobiDevOsEngine(Context mContext) {
      this.mContext = mContext;
   }

   public static MobiDevOsEngine getInstace(){
      return mEngine;
   }

   public static MobiDevOsEngine getInstace(@NonNull Context mContext)  {

      synchronized (mContext) {
         if (mEngine == null) {
            mEngine = new MobiDevOsEngine(mContext);
         }
      }
      return mEngine;
   }

   public static Context getContext() {
      return mContext;
   }


   public void initProperties(boolean isDebug){
      mHttpEngine = new HttpEngine(isDebug);
   }

   public HttpEngine getHttpEngine(){
      return mHttpEngine;
   }
}
