package com.zhujohnle.mobidevos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhujohnle.mobidevlibrary.R;
import com.zhujohnle.mobidevos.beans.RecycleViewData;
import com.zhujohnle.mobidevos.framework.android.adapter.vadapter.BaseRecyclerAdapter;
import com.zhujohnle.mobidevos.framework.android.adapter.vadapter.BaseRecyclerHolder;
import com.zhujohnle.mobidevos.framework.vinject.VInject;
import com.zhujohnle.mobidevos.framework.vinject.annotation.OnClick;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VBind;
import com.zhujohnle.mobidevos.framework.vinject.annotation.VPageBind;
import com.zhujohnle.mobidevos.recyclemoudle.RecyclePageContact;
import com.zhujohnle.mobidevos.recyclemoudle.RecycleViewPagePresenter;
import com.zhujohnle.mobidevos.recypage.ArrayLazyActivity;
import com.zhujohnle.mobidevos.view.StatusLayout;

/**
 * @auth &{zhujiule}
 * @date 2018/12/11
 * @copyright
 **/

@VPageBind(R.layout.activity_page)
public class RecyclePageActivity extends ArrayLazyActivity<RecycleViewData>   implements RecyclePageContact.PageView ,OnRefreshLoadMoreListener {


   @VBind(R.id.statuslayout)
   StatusLayout mSmartLayout;

   @VBind(R.id.refreshLayout)
   SmartRefreshLayout mRefreshLayout;

   @VBind(R.id.recyclerView)
   RecyclerView mRecycleView;

   BaseRecyclerAdapter<RecycleViewData> mRecycleAdapter;

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


   RecycleViewPagePresenter mRecyPresenter;


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      VInject.inject(this);

      mRecycleView.setLayoutManager(new LinearLayoutManager(this));
      mRecycleAdapter = new BaseRecyclerAdapter<RecycleViewData>(this,mDatas,R.layout.item_recycle) {
         @Override
         public void convert(BaseRecyclerHolder mHolder, RecycleViewData item, int position, boolean isScrolling) {
            TextView mTv = mHolder.getView(R.id.tv_item);
            mTv.setText(item.getName());
         }
      };
      mRecycleView.setAdapter(mRecycleAdapter);

      mRecyPresenter = (RecycleViewPagePresenter)mPresenter;
      mRefreshLayout.setOnRefreshListener(this);
      mRefreshLayout.setOnLoadMoreListener(this);
   }

   @Override
   public Class getContact() {
      return RecyclePageContact.class;
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
      mRecycleAdapter.notifyDataSetChanged();
   }

   @Override
   public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
      mRecyPresenter.actionRequestLoadMoreSuccess("zhule",1,10);
   }

   @Override
   public void onRefresh(@NonNull RefreshLayout refreshLayout) {
      mRecyPresenter.actionRequestRefreshSuccess("zhule",1,10);

   }
}
