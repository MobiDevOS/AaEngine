package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhujohnle.mobidevlibrary.R;
import com.zhujohnle.mobidevos.beans.RecycleViewData;
import com.zhujohnle.mobidevos.framework.vinject.VInject;
import com.zhujohnle.mobidevos.framework.vinject.annotation.OnClick;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VBind;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;
import com.zhujohnle.mobidevos.recypage.ArrayLazyActivity;
import com.zhujohnle.mobidevos.view.StatusLayout;

/**
 * @auth &{zhujiule}
 * @date 2018/12/11
 * @copyright
 **/

@VPageBind(R.layout.activity_page)
public class RecyclePageActivity extends ArrayLazyActivity<RecycleViewData> {


   @VBind(R.id.statuslayout)
   StatusLayout mSmartLayout;

   @VBind(R.id.refreshLayout)
   SmartRefreshLayout mRefreshLayout;

   @OnClick(R.id.showLogic)
   void onClickShowLogic(){
      showNormalView();
   }

   @OnClick(R.id.showNoData)
   void onClickShowNoData(){
      showEmptyView();
   }

   @OnClick(R.id.showNoError)
   void onClickShowError(){
      showErrorView("我异常了");
   }


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      VInject.inject(this);
   }

   @Override
   public Class getContact() {
      return null;
   }

   @Override
   public SmartRefreshLayout getSmartRefreshLayout() {
      return mRefreshLayout;
   }

   @Override
   public StatusLayout getStatusLayout() {
      return mSmartLayout;
   }

   @Override
   public void notifyDataSetChanged() {

   }
}
