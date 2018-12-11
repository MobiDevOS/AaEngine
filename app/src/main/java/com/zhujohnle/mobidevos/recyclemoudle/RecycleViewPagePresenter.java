package com.zhujohnle.mobidevos.recyclemoudle;

import com.zhujohnle.mobidevos.beans.RecycleViewData;
import com.zhujohnle.mobidevos.recypage.ArrayPresenter;

import java.util.ArrayList;

/**
 * @auth &{zhujiule}
 * @date 2018/12/11
 * @copyright 】
 **/


public class RecycleViewPagePresenter extends ArrayPresenter<RecyclePageContact.PageView,RecycleViewData> {

   public void  actionRequestRefreshSuccess(String key,int pageNo ,int pageSize){
      ArrayList<RecycleViewData> mList = new ArrayList<>();
      RecycleViewData mData = new RecycleViewData();
      mData.setName("zhule");
      mData.setValue("test");
      mList.add(mData);

      successView(true,mList,pageNo,pageSize);
   }

   public void  actionRequestLoadMoreSuccess(String key,int pageNo ,int pageSize){
      ArrayList<RecycleViewData> mList = new ArrayList<>();
      RecycleViewData mData = new RecycleViewData();
      mData.setName("zhule");
      mData.setValue("test");
      mList.add(mData);

      successView(false,mList,pageNo,pageSize);
   }


   public void  actionRequesdEmpty(boolean isRefresh){
      emptyView(isRefresh);
   }

   public void  actionRequesdError(boolean isRefresh){
      errView(isRefresh,"我异常了");
   }


}
