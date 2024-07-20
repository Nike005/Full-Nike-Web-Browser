package com.anthonycr.bonsai;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class Schedulers {
    private static Scheduler immediateScheduler;
    private static Scheduler ioScheduler;
    private static Scheduler mainScheduler;
    private static Scheduler workerScheduler;

    private Schedulers() {
        throw new UnsupportedOperationException("This class is not instantiable");
    }

    private static class WorkerScheduler implements Scheduler {
        private final Executor worker;

        private WorkerScheduler() {
            this.worker = Executors.newFixedThreadPool(4);
        }

        public void execute(Runnable runnable) {
            this.worker.execute(runnable);
        }
    }

    private static class SingleThreadedScheduler implements Scheduler {
        private final Executor singleThreadExecutor;

        private SingleThreadedScheduler() {
            this.singleThreadExecutor = Executors.newSingleThreadExecutor();
        }

        public void execute(Runnable runnable) {
            this.singleThreadExecutor.execute(runnable);
        }
    }

    private static class ExecutorScheduler implements Scheduler {
        private final Executor backingExecutor;

        public ExecutorScheduler(Executor executor) {
            this.backingExecutor = executor;
        }

        public void execute(Runnable runnable) {
            this.backingExecutor.execute(runnable);
        }
    }

    private static class ImmediateScheduler implements Scheduler {
        private ImmediateScheduler() {
        }

        public void execute(Runnable runnable) {
            runnable.run();
        }
    }

    public static Scheduler from(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    public static Scheduler from(Handler handler) {
        return new ThreadScheduler(handler.getLooper());
    }

    public static Scheduler immediate() {
        if (immediateScheduler == null) {
            immediateScheduler = new ImmediateScheduler();
        }
        return immediateScheduler;
    }

    public static Scheduler newSingleThreadedScheduler() {
        return new SingleThreadedScheduler();
    }

    public static Scheduler worker() {
        if (workerScheduler == null) {
            workerScheduler = new WorkerScheduler();
        }
        return workerScheduler;
    }

    public static Scheduler main() {
        if (mainScheduler == null) {
            mainScheduler = new ThreadScheduler(Looper.getMainLooper());
        }
        return mainScheduler;
    }

    /* renamed from: io */
    public static Scheduler m6232io() {
        if (ioScheduler == null) {
            ioScheduler = new SingleThreadedScheduler();
        }
        return ioScheduler;
    }
}
