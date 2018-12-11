package com.zhujohnle.mobidevos.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @auth &{zhujiule}
 * @date 2018/12/11
 * @copyright
 **/
public class MStatusLayout extends StatusLayout {
   public MStatusLayout(@NonNull Context context) {
      super(context);
   }

   public MStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   public MStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void onFinishInflate() {
      super.onFinishInflate();
      setEmptyView(new EmptyView(getContext()));
      setLoading(new LoadingView(getContext()));
      setErrView(new ErrView(getContext()));
   }


}
