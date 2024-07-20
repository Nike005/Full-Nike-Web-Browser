package p054rx.schedulers;

import p054rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.NewThreadScheduler */
public final class NewThreadScheduler extends Scheduler {
    public Scheduler.Worker createWorker() {
        return null;
    }

    private NewThreadScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
