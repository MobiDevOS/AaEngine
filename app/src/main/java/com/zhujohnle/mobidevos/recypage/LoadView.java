package com.zhujohnle.mobidevos.recypage;

public interface LoadView<T> extends LoadBaseView {
    void requestSuccess(T t);
}
