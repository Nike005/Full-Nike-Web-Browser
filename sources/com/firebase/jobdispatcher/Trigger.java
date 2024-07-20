package com.firebase.jobdispatcher;

import com.firebase.jobdispatcher.JobTrigger;

public final class Trigger {
    public static final JobTrigger.ImmediateTrigger NOW = new JobTrigger.ImmediateTrigger();

    public static JobTrigger.ExecutionWindowTrigger executionWindow(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Window start can't be less than 0");
        } else if (i2 >= i) {
            return new JobTrigger.ExecutionWindowTrigger(i, i2);
        } else {
            throw new IllegalArgumentException("Window end can't be less than window start");
        }
    }
}
