package com.zhujohnle.mobidevos.framework.core.eventbus;

public class EventBusEngine {
    private static IBus innerBus;
    private static IBus externalBus;

    public static void setBus(IBus bus) {
        if (externalBus == null && bus != null) {
            externalBus = bus;
        }
    }

    public static IBus getBus() {
        if (innerBus == null) {
            synchronized (EventBusEngine.class) {
                if (innerBus == null) {
                    if (externalBus != null) {
                        innerBus = externalBus;
                    } else {
                        innerBus = new RxBusImpl();
                    }
                }
            }
        }
        return innerBus;
    }
}
