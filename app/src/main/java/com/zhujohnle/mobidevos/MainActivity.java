package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhujohnle.mobidevlibrary.R;
import com.zhujohnle.mobidevos.framework.vinject.VInject;
import com.zhujohnle.mobidevos.framework.vinject.annotation.OnClick;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;
import com.zhujohnle.mobidevos.utils.LogUtils;

@VPageBind(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      VInject.inject(this);

      System.out.print("gods");
   }

   @OnClick(R.id.tv_click)
   public void onClickContent(){
      LogUtils.i("goods");
   }
}
