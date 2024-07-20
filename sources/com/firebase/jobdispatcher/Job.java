package com.firebase.jobdispatcher;

import android.os.Bundle;

public final class Job implements JobParameters {
    private final int[] mConstraints;
    private Bundle mExtras;
    private final int mLifetime;
    private final boolean mRecurring;
    private final boolean mReplaceCurrent;
    private final RetryStrategy mRetryStrategy;
    private final String mService;
    private final String mTag;
    private final JobTrigger mTrigger;

    private Job(Builder builder) {
        this.mService = builder.mServiceClass != null ? builder.mServiceClass.getName() : null;
        this.mExtras = builder.mExtras;
        this.mTag = builder.mTag;
        this.mTrigger = builder.mTrigger;
        this.mRetryStrategy = builder.mRetryStrategy;
        this.mLifetime = builder.mLifetime;
        this.mRecurring = builder.mRecurring;
        this.mConstraints = builder.mConstraints != null ? builder.mConstraints : new int[0];
        this.mReplaceCurrent = builder.mReplaceCurrent;
    }

    public int[] getConstraints() {
        return this.mConstraints;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public RetryStrategy getRetryStrategy() {
        return this.mRetryStrategy;
    }

    public boolean shouldReplaceCurrent() {
        return this.mReplaceCurrent;
    }

    public String getTag() {
        return this.mTag;
    }

    public JobTrigger getTrigger() {
        return this.mTrigger;
    }

    public int getLifetime() {
        return this.mLifetime;
    }

    public boolean isRecurring() {
        return this.mRecurring;
    }

    public String getService() {
        return this.mService;
    }

    public static final class Builder implements JobParameters {
        /* access modifiers changed from: private */
        public int[] mConstraints;
        /* access modifiers changed from: private */
        public Bundle mExtras;
        /* access modifiers changed from: private */
        public int mLifetime = 1;
        /* access modifiers changed from: private */
        public boolean mRecurring = false;
        /* access modifiers changed from: private */
        public boolean mReplaceCurrent = false;
        /* access modifiers changed from: private */
        public RetryStrategy mRetryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        /* access modifiers changed from: private */
        public Class<? extends JobService> mServiceClass;
        /* access modifiers changed from: private */
        public String mTag;
        /* access modifiers changed from: private */
        public JobTrigger mTrigger = Trigger.NOW;
        private final ValidationEnforcer mValidator;

        Builder(ValidationEnforcer validationEnforcer) {
            this.mValidator = validationEnforcer;
        }

        public Builder addConstraint(int i) {
            int[] iArr = this.mConstraints;
            int length = iArr == null ? 1 : iArr.length + 1;
            int[] iArr2 = new int[length];
            int[] iArr3 = this.mConstraints;
            if (!(iArr3 == null || iArr3.length == 0)) {
                System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
            }
            iArr2[length - 1] = i;
            this.mConstraints = iArr2;
            return this;
        }

        public Builder setReplaceCurrent(boolean z) {
            this.mReplaceCurrent = z;
            return this;
        }

        public Job build() {
            this.mValidator.ensureValid((JobParameters) this);
            return new Job(this);
        }

        public String getService() {
            return this.mServiceClass.getName();
        }

        public Builder setService(Class<? extends JobService> cls) {
            this.mServiceClass = cls;
            return this;
        }

        public String getTag() {
            return this.mTag;
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public JobTrigger getTrigger() {
            return this.mTrigger;
        }

        public Builder setTrigger(JobTrigger jobTrigger) {
            this.mTrigger = jobTrigger;
            return this;
        }

        public int getLifetime() {
            return this.mLifetime;
        }

        public Builder setLifetime(int i) {
            this.mLifetime = i;
            return this;
        }

        public boolean isRecurring() {
            return this.mRecurring;
        }

        public Builder setRecurring(boolean z) {
            this.mRecurring = z;
            return this;
        }

        public int[] getConstraints() {
            int[] iArr = this.mConstraints;
            return iArr == null ? new int[0] : iArr;
        }

        public Builder setConstraints(int... iArr) {
            this.mConstraints = iArr;
            return this;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public RetryStrategy getRetryStrategy() {
            return this.mRetryStrategy;
        }

        public Builder setRetryStrategy(RetryStrategy retryStrategy) {
            this.mRetryStrategy = retryStrategy;
            return this;
        }

        public boolean shouldReplaceCurrent() {
            return this.mReplaceCurrent;
        }
    }
}
