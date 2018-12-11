package com.zhujohnle.mobidevos.recypage;

import java.util.ArrayList;


/* 针对整个页面都是 列表视图 */
public interface ArrayView<T> extends LoadBaseView {
    void refreshSuccess(ArrayList<T> resultData, int pageNo, int totalSize);

    void loadmoreSuccess(ArrayList<T> resultData, int pageNo, int totalSize);
}
