package com.zhujohnle.mobidevos.framework.core.config;

import com.zhujohnle.mobidevos.exception.PropertiesLoadException;
import com.zhujohnle.mobidevos.utils.LogUtils;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright
 **/
public abstract class ConfigFactory {



    abstract MobiDevOsConfig loadConfigProperties(String  mProperties) throws PropertiesLoadException;


   protected abstract  MobiDevOsConfig getConfig();

   public MobiDevOsConfig setUpFiled(Properties mProperties){
      MobiDevOsConfig mConfig = getConfig();

      Class<?> mConnfigClass = mConfig.getClass();
      //full value
      Field[] fields = mConnfigClass.getDeclaredFields();

      for(int i=0;i<fields.length;i++){
         if (fields != null && fields.length > 0) {
            for (Field field : fields) {
               field.setAccessible(true);
               Config mConfigAnnotation = field.getAnnotation(Config.class);
               String name = mConfigAnnotation.name();
               try {
                  String value = mProperties.getProperty(name);
                  String fieldName = field.getType().getName();
                  if(fieldName.equals("String")){
                     field.set(mConfig,value);
                  }else if(fieldName.equals("int")){
                     field.set(mConfig,Integer.parseInt(value));
                  }else if(fieldName.equals("boolean")){
                     field.set(mConfig,Boolean.parseBoolean(value));
                  }else{
                       continue;
                  }
               } catch (IllegalAccessException e) {
                  String errorMsg = e.getMessage();
                  LogUtils.e("参数："+name+"初始化异常将使用默认参数",e);
               }
            }
         }
      }
      return mConfig;

   }
}
