package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zhujohnle.mobidevlibrary.R;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      findViewById(R.id.tv_click).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            int i = 10/0;
            Log.i("aaa","bbb");
         }
      });
      System.out.print("gods");

   }
}
