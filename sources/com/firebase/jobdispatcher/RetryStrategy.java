package com.firebase.jobdispatcher;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class RetryStrategy {
    public static final RetryStrategy DEFAULT_EXPONENTIAL = new RetryStrategy(1, 30, 3600);
    public static final RetryStrategy DEFAULT_LINEAR = new RetryStrategy(2, 30, 3600);
    public static final int RETRY_POLICY_EXPONENTIAL = 1;
    public static final int RETRY_POLICY_LINEAR = 2;
    private final int mInitialBackoff;
    private final int mMaximumBackoff;
    private final int mPolicy;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RetryPolicy {
    }

    RetryStrategy(int i, int i2, int i3) {
        this.mPolicy = i;
        this.mInitialBackoff = i2;
        this.mMaximumBackoff = i3;
    }

    public int getPolicy() {
        return this.mPolicy;
    }

    public int getInitialBackoff() {
        return this.mInitialBackoff;
    }

    public int getMaximumBackoff() {
        return this.mMaximumBackoff;
    }

    static final class Builder {
        private final ValidationEnforcer mValidator;

        Builder(ValidationEnforcer validationEnforcer) {
            this.mValidator = validationEnforcer;
        }

        public RetryStrategy build(int i, int i2, int i3) {
            RetryStrategy retryStrategy = new RetryStrategy(i, i2, i3);
            this.mValidator.ensureValid(retryStrategy);
            return retryStrategy;
        }
    }
}
