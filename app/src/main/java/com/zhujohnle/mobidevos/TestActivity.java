package com.zhujohnle.mobidevos;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * @auth &{zhujiule}
 * @date 2018/12/5
 * @copyright
 **/
public class TestActivity extends Activity {


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      System.out.print(1/0);
      finish();
   }

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
      super.onCreate(savedInstanceState, persistentState);
   }
}
