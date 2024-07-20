package p054rx.schedulers;

import p054rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.ImmediateScheduler */
public final class ImmediateScheduler extends Scheduler {
    public Scheduler.Worker createWorker() {
        return null;
    }

    private ImmediateScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
