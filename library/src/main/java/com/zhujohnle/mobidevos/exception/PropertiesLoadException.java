package com.zhujohnle.mobidevos.exception;

/**
 * @auth &{zhujiule}
 * @date 2018/12/6
 * @copyright
 **/
public class PropertiesLoadException extends BaseException {

   private static final long serialVersionUID = 1L;

   public PropertiesLoadException() {
   }

   public PropertiesLoadException(String detailMessage) {
      super(detailMessage);
   }

   public PropertiesLoadException(String detailMessage, Throwable throwable) {
      super(detailMessage, throwable);
   }

   public PropertiesLoadException(Throwable throwable) {
      super(throwable);
   }
}
