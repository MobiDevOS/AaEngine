package com.zhujohnle.mobidevos.framework.core.eventbus;
public interface IBus {
    void register(Object object);

    void unregister(Object object);

    void post(IEvent event);

    void postSticky(IEvent event);
}
