package com.zhujohnle.mobidevos;

import android.content.Context;

import com.zhujohnle.mobidevos.exception.PropertiesLoadException;
import com.zhujohnle.mobidevos.framework.core.config.HttpConfig;
import com.zhujohnle.mobidevos.framework.core.config.HttpConfigFactory;
import com.zhujohnle.mobidevos.utils.LogUtils;

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

   public static boolean isDebug = false;

   public static final int INIT_SUCCESS = 0;

   public static final int INIT_HTTP_PROPERTIES = 1;

   private HttpConfig mHttpconfig;

   public MobiDevOsEngine(Context mContext, boolean isDebug) {
      this.mContext = mContext;
      this.isDebug = isDebug;
   }

   /**
    * 初始化配置相关
    * */
   public int init(){
      HttpConfigFactory mHttpFactory = new HttpConfigFactory();
      try {
         mHttpconfig =  mHttpFactory.loadConfigProperties(null);
      } catch (PropertiesLoadException e) {
         LogUtils.e(e.getMessage(),e);
         return INIT_HTTP_PROPERTIES;
      }
      return INIT_SUCCESS;
   }

   public static MobiDevOsEngine getInstace(){
      return mEngine;
   }

   public static MobiDevOsEngine getInstace(final Context mContext
         , final boolean isDebug) throws Exception {
      if (mContext == null) {
         throw new IllegalAccessException("MobiDevOsEngine need Context init");
      }

      synchronized (mContext) {
         if (mEngine == null) {
            mEngine = new MobiDevOsEngine(mContext, isDebug);
         }
      }
      return mEngine;
   }

   public static Context getContext() {
      return mContext;
   }


   private void initProperties(){

   }
}
