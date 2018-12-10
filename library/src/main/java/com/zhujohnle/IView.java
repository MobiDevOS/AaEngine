package com.zhujohnle;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @auth &{zhujiule}
 * @date 2018/12/10
 * @copyright 杭州物恋网科技有限公司
 **/
public class IView extends View {
   public IView(Context context) {
      super(context);
   }

   public IView(Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public IView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public IView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
   }
   public void attachView(){

   }
}
