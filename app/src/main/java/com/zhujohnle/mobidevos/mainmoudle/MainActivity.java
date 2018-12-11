package com.zhujohnle.mobidevos.mainmoudle;

import android.content.Intent;
import android.os.Bundle;

import com.zhujohnle.mobidevlibrary.R;
import com.zhujohnle.mobidevos.BaseActivity;
import com.zhujohnle.mobidevos.RecyclePageActivity;
import com.zhujohnle.mobidevos.framework.core.log.FLog;
import com.zhujohnle.mobidevos.framework.vinject.VInject;
import com.zhujohnle.mobidevos.framework.vinject.annotation.OnClick;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;

@VPageBind(R.layout.activity_main)
public class MainActivity extends BaseActivity {

   MainPresenter mainPresenter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      VInject.inject(this);

    //  mainPresenter = (MainPresenter) mPresenter;
   }

   @Override
   public Class getContact() {
      return MainContact.class;
   }

   @OnClick(R.id.tv_click)
   public void onClickContent(){
      FLog.i("goods");
      startActivity(new Intent(MainActivity.this,RecyclePageActivity.class));

   }
}
