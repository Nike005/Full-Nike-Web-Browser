package com.anthonycr.bonsai;

import android.os.Handler;
import android.os.Looper;

class ThreadScheduler implements Scheduler {
    private final Handler handler;

    ThreadScheduler(Looper looper) {
        this.handler = new Handler(looper);
    }

    public synchronized void execute(Runnable runnable) {
        this.handler.post(runnable);
    }
}
