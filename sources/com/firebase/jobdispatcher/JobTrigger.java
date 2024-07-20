package com.firebase.jobdispatcher;

public class JobTrigger {

    public static final class ImmediateTrigger extends JobTrigger {
        ImmediateTrigger() {
        }
    }

    public static final class ExecutionWindowTrigger extends JobTrigger {
        private final int mWindowEnd;
        private final int mWindowStart;

        ExecutionWindowTrigger(int i, int i2) {
            this.mWindowStart = i;
            this.mWindowEnd = i2;
        }

        public int getWindowStart() {
            return this.mWindowStart;
        }

        public int getWindowEnd() {
            return this.mWindowEnd;
        }
    }
}
