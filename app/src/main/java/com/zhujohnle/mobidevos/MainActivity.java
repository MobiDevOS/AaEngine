package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhujohnle.mobidevlibrary.R;
import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.framework.vinject.VInject;
import com.zhujohnle.mobidevos.framework.vinject.annotation.OnClick;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;

@VPageBind(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      VInject.inject(this);

      new Thread(new Runnable() {
         @Override
         public void run() {
            System.out.print(1/0);
         }
      }).start();


   }

   @OnClick(R.id.tv_click)
   public void onClickContent(){
      FLog.i("goods");
      //startActivity(new Intent(MainActivity.this,TestActivity.class));

   }
}
