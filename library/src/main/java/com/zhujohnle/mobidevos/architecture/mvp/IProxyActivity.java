package com.zhujohnle.mobidevos.architecture.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 *
 * 代理activity的属性
 * @auth &{zhujiule}
 * @date 2018/12/7
 * @copyright
 **/
public interface IProxyActivity {

    void onCreate(@Nullable Bundle savedInstanceState) ;

    void onDestroy();
}
