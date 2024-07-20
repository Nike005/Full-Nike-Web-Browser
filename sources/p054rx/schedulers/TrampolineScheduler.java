package p054rx.schedulers;

import p054rx.Scheduler;

@Deprecated
/* renamed from: rx.schedulers.TrampolineScheduler */
public final class TrampolineScheduler extends Scheduler {
    public Scheduler.Worker createWorker() {
        return null;
    }

    private TrampolineScheduler() {
        throw new IllegalStateException("No instances!");
    }
}
