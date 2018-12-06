package com.zhujohnle.mobidevos.framework.core.config;

/**
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright
 **/
public class HttpConfig extends MobiDevOsConfig{

   @Config(name = "http.connectTimeOut")
   public int connectTimeOut = 20;

   @Config(name = "http.readTimeOut" )
   public int readTimeOut = 20;

   @Config(name = "http.writeTimeOut" )
   public int writeTimeOut = 20;

   public int getWriteTimeOut() {
      return writeTimeOut;
   }

   public void setWriteTimeOut(int writeTimeOut) {
      this.writeTimeOut = writeTimeOut;
   }

   public int getConnectTimeOut() {
      return connectTimeOut;
   }

   public void setConnectTimeOut(int connectTimeOut) {
      this.connectTimeOut = connectTimeOut;
   }

   public int getReadTimeOut() {
      return readTimeOut;
   }

   public void setReadTimeOut(int readTimeOut) {
      this.readTimeOut = readTimeOut;
   }
}
