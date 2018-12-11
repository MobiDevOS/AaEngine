package com.zhujohnle.mobidevos.recyclemoudle;

import com.zhujohnle.mobidevos.architecture.mvp.Contact;
import com.zhujohnle.mobidevos.beans.RecycleViewData;
import com.zhujohnle.mobidevos.recypage.ArrayView;

/**
 * @auth &{zhujiule}
 * @date 2018/12/11
 * @copyright
 **/

@Contact(RecycleViewPagePresenter.class)
public interface RecyclePageContact  {

   public interface PageView extends ArrayView<RecycleViewData>{

   }
}
