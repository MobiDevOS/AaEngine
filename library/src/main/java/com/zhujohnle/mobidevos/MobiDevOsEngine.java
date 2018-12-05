package com.zhujohnle.mobidevos;

import android.content.Context;

/**
 * MobiDevOs引擎入口
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class MobiDevOsEngine {

   //全局application入口
   private static Context mContext;

   private static MobiDevOsEngine mEngine;

   public MobiDevOsEngine(Context mContext){
      this.mContext = mContext;
   }

   public static  MobiDevOsEngine getInstace(final Context mContext) throws Exception{
      if(mContext==null){
         throw new IllegalAccessException("MobiDevOsEngine need Context init");
      }

      synchronized (mContext){
         if(mEngine==null){
            mEngine = new MobiDevOsEngine(mContext);
         }
      }
      return mEngine;
   }

   public static Context getContext(){
      return mContext;
   }

}
