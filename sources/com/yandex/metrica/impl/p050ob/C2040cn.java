package com.yandex.metrica.impl.p050ob;

import java.util.concurrent.Executor;

/* renamed from: com.yandex.metrica.impl.ob.cn */
public class C2040cn implements Executor {
    public void execute(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }
}
