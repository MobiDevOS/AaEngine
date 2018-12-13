package com.zhujohnle.mobidevos.framework.core.config;

import android.text.TextUtils;

import com.zhujohnle.mobidevos.AaEngine;
import com.zhujohnle.mobidevos.exception.PropertiesLoadException;
import com.zhujohnle.mobidevos.framework.core.log.FLog;

import java.io.InputStream;
import java.util.Properties;

/**
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright
 **/
public class HttpConfigFactory extends ConfigFactory {


   public HttpConfig loadProperties(String propertiesFileName) throws PropertiesLoadException {
      Properties mProperties;
      if(TextUtils.isEmpty(propertiesFileName)){
         //加载默认的httpconfig文件
         mProperties =  getProperties("httpConfig.properties");
      }else{
         mProperties = getProperties(propertiesFileName);
      }
      if(mProperties==null){
         throw new PropertiesLoadException("http配置加载异常");
      }
      return (HttpConfig) setUpFiled(mProperties);
   }



   private  Properties getProperties(String fileName) {
      Properties props = new Properties();
      try {
         InputStream in =  AaEngine.getContext().getAssets().open(fileName);
         props.load(in);
      } catch (Exception e1) {
         FLog.i(e1.getMessage());
         return null;
      }
      return props;
   }


   @Override
   public HttpConfig loadConfigProperties(String mProperties) throws PropertiesLoadException {
      return loadProperties(mProperties);
   }

   @Override
   protected MobiDevOsConfig getConfig() {
      return new HttpConfig();
   }
}
