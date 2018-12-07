package com.zhujohnle.mobidevos.framework.vinject;

import android.app.Activity;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;

import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.framework.vinject.annotation.EventBase;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VBind;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @auth &{zhujiule}
 * @date 2018/12/4
 * @copyright
 **/

public class VInject {

   public static void inject(View view) {
      injectObject(view, new ViewFinder(view));
   }

   public static void inject(Activity activity) {
      injectObject(activity, new ViewFinder(activity));
   }

   public static void inject(PreferenceActivity preferenceActivity) {
      injectObject(preferenceActivity, new ViewFinder(preferenceActivity));
   }

   public static void inject(Object handler, View view) {
      injectObject(handler, new ViewFinder(view));
   }

   public static void inject(Object handler, Activity activity) {
      injectObject(handler, new ViewFinder(activity));
   }

   public static void inject(Object handler, PreferenceGroup preferenceGroup) {
      injectObject(handler, new ViewFinder(preferenceGroup));
   }

   public static void inject(Object handler, PreferenceActivity preferenceActivity) {
      injectObject(handler, new ViewFinder(preferenceActivity));
   }

   @SuppressWarnings("ConstantConditions")
   private static void injectObject(Object handler, ViewFinder finder) {

      Class<?> handlerType = handler.getClass();

      // inject ContentView
      VPageBind contentView = handlerType.getAnnotation(VPageBind.class);
      if (contentView != null) {
         try {
            Method setContentViewMethod = handlerType.getMethod("setContentView", int.class);
            setContentViewMethod.invoke(handler, contentView.value());
         } catch (Throwable e) {
            FLog.e(e.getMessage(), e);
         }
      }

      // inject view
      Field[] fields = handlerType.getDeclaredFields();
      if (fields != null && fields.length > 0) {
         for (Field field : fields) {
            VBind viewInject = field.getAnnotation(VBind.class);
            if (viewInject != null) {
               try {
                  View view = finder.findViewById(viewInject.value(), viewInject.parentId());
                  if (view != null) {
                     field.setAccessible(true);
                     field.set(handler, view);
                  }
               } catch (Throwable e) {
                  FLog.e(e.getMessage(), e);
               }
            }
         }
      }

      // inject event
      Method[] methods = handlerType.getDeclaredMethods();
      if (methods != null && methods.length > 0) {
         for (Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            if (annotations != null && annotations.length > 0) {
               for (Annotation annotation : annotations) {
                  Class<?> annType = annotation.annotationType();
                  if (annType.getAnnotation(EventBase.class) != null) {
                     method.setAccessible(true);
                     try {
                        // ProGuard：-keep class * extends java.lang.annotation.Annotation { *; }
                        Method valueMethod = annType.getDeclaredMethod("value");
                        Method parentIdMethod = null;
                        try {
                           parentIdMethod = annType.getDeclaredMethod("parentId");
                        } catch (Throwable e) {
                        }
                        Object values = valueMethod.invoke(annotation);
                        Object parentIds = parentIdMethod == null ? null : parentIdMethod.invoke(annotation);
                        int parentIdsLen = parentIds == null ? 0 : Array.getLength(parentIds);
                        int len = Array.getLength(values);
                        for (int i = 0; i < len; i++) {
                           ViewInjectInfo info = new ViewInjectInfo();
                           info.value = Array.get(values, i);
                           info.parentId = parentIdsLen > i ? (Integer) Array.get(parentIds, i) : 0;
                           EventListenerManager.addEventMethod(finder, info, annotation, handler, method);
                        }
                     } catch (Throwable e) {
                        FLog.e(e.getMessage(), e);
                     }
                  }
               }
            }
         }
      }
   }


}
