package com.firebase.jobdispatcher;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.RetryStrategy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class FirebaseJobDispatcher {
    public static final int CANCEL_RESULT_NO_DRIVER_AVAILABLE = 2;
    public static final int CANCEL_RESULT_SUCCESS = 0;
    public static final int CANCEL_RESULT_UNKNOWN_ERROR = 1;
    public static final int SCHEDULE_RESULT_BAD_SERVICE = 4;
    public static final int SCHEDULE_RESULT_NO_DRIVER_AVAILABLE = 2;
    public static final int SCHEDULE_RESULT_SUCCESS = 0;
    public static final int SCHEDULE_RESULT_UNKNOWN_ERROR = 1;
    public static final int SCHEDULE_RESULT_UNSUPPORTED_TRIGGER = 3;
    private final Driver mDriver;
    private RetryStrategy.Builder mRetryStrategyBuilder;
    private final ValidationEnforcer mValidator;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CancelResult {
    }

    public static final class ScheduleFailedException extends RuntimeException {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScheduleResult {
    }

    public FirebaseJobDispatcher(Driver driver) {
        this.mDriver = driver;
        ValidationEnforcer validationEnforcer = new ValidationEnforcer(driver.getValidator());
        this.mValidator = validationEnforcer;
        this.mRetryStrategyBuilder = new RetryStrategy.Builder(validationEnforcer);
    }

    public int schedule(Job job) {
        if (!this.mDriver.isAvailable()) {
            return 2;
        }
        return this.mDriver.schedule(job);
    }

    public int cancel(String str) {
        if (!this.mDriver.isAvailable()) {
            return 2;
        }
        return this.mDriver.cancel(str);
    }

    public int cancelAll() {
        if (!this.mDriver.isAvailable()) {
            return 2;
        }
        return this.mDriver.cancelAll();
    }

    public void mustSchedule(Job job) {
        if (schedule(job) != 0) {
            throw new ScheduleFailedException();
        }
    }

    public ValidationEnforcer getValidator() {
        return this.mValidator;
    }

    public Job.Builder newJobBuilder() {
        return new Job.Builder(this.mValidator);
    }

    public RetryStrategy newRetryStrategy(int i, int i2, int i3) {
        return this.mRetryStrategyBuilder.build(i, i2, i3);
    }
}
