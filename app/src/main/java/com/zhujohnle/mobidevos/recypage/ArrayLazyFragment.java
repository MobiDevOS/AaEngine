package com.zhujohnle.mobidevos.recypage;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:zft
 * 日期:2018/8/2 0002.
 */
public abstract class ArrayLazyFragment<T> extends LoadBaseFragment {
    public List<T> mDatas = new ArrayList<>();

    //    请求参数
    public int mPageNum = 1;       //分页页数
    public int mPageSize = 6;     //分页条数

    public abstract SmartRefreshLayout getSmartRefreshLayout();

    public abstract void notifyDataSetChanged();

    public void refreshSuccess(ArrayList<T> resultData, int pageNo, int totalPage) {
        mPageNum = 2;
        mDatas.clear();
        mDatas.addAll(resultData);
        notifyDataSetChanged();
        checkLoadmore(resultData, true,pageNo ,totalPage );
        getSmartRefreshLayout().finishRefresh();
        getStatusLayout().showNormal();
    }

    public void loadmoreSuccess(ArrayList<T> resultData, int pageNo, int totalPage) {

        mPageNum++;
        mDatas.addAll(resultData);
        notifyDataSetChanged();
        checkLoadmore(resultData, false,pageNo ,totalPage);
        getSmartRefreshLayout().finishLoadMore();

        getStatusLayout().showNormal();

    }


    @Override
    public void showEmptyView() {
        super.showEmptyView();
        getSmartRefreshLayout().finishRefresh();
        getSmartRefreshLayout().finishLoadMore();
    }

    private void checkLoadmore(ArrayList<T> resultData, boolean b, int pageNo, int totalPage) {
        if (resultData == null) {
            getSmartRefreshLayout().setNoMoreData(true);//恢复上拉状态
            return;
        }
        if (totalPage >pageNo) {
//                getSmartRefreshLayout().setEnableLoadMore(true);
            getSmartRefreshLayout().setNoMoreData(false);//恢复上拉状态
        } else {
//                getSmartRefreshLayout().setEnableLoadMore(false);
            if (b) {
                getSmartRefreshLayout().setNoMoreData(true);

            } else {
                getSmartRefreshLayout().finishLoadMoreWithNoMoreData();//设置之后，将不会再触发加载事件
            }
        }
    }

}
