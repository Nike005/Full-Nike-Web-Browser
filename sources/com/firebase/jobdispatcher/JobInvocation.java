package com.firebase.jobdispatcher;

import android.os.Bundle;

final class JobInvocation implements JobParameters {
    private final int[] mConstraints;
    private final Bundle mExtras;
    private final int mLifetime;
    private final boolean mRecurring;
    private final boolean mReplaceCurrent;
    private final RetryStrategy mRetryStrategy;
    private final String mService;
    private final String mTag;
    private final JobTrigger mTrigger;

    private JobInvocation(Builder builder) {
        this.mTag = builder.mTag;
        this.mService = builder.mService;
        this.mTrigger = builder.mTrigger;
        this.mRetryStrategy = builder.mRetryStrategy;
        this.mRecurring = builder.mRecurring;
        this.mLifetime = builder.mLifetime;
        this.mConstraints = builder.mConstraints;
        this.mExtras = builder.mExtras;
        this.mReplaceCurrent = builder.mReplaceCurrent;
    }

    public String getService() {
        return this.mService;
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

    static final class Builder {
        /* access modifiers changed from: private */
        public int[] mConstraints;
        /* access modifiers changed from: private */
        public final Bundle mExtras = new Bundle();
        /* access modifiers changed from: private */
        public int mLifetime;
        /* access modifiers changed from: private */
        public boolean mRecurring;
        /* access modifiers changed from: private */
        public boolean mReplaceCurrent;
        /* access modifiers changed from: private */
        public RetryStrategy mRetryStrategy;
        /* access modifiers changed from: private */
        public String mService;
        /* access modifiers changed from: private */
        public String mTag;
        /* access modifiers changed from: private */
        public JobTrigger mTrigger;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public JobInvocation build() {
            if (this.mTag != null && this.mService != null && this.mTrigger != null) {
                return new JobInvocation(this);
            }
            throw new IllegalArgumentException("Required fields were not populated.");
        }

        public Builder setTag(String str) {
            this.mTag = str;
            return this;
        }

        public Builder setService(String str) {
            this.mService = str;
            return this;
        }

        public Builder setTrigger(JobTrigger jobTrigger) {
            this.mTrigger = jobTrigger;
            return this;
        }

        public Builder setRecurring(boolean z) {
            this.mRecurring = z;
            return this;
        }

        public Builder setLifetime(int i) {
            this.mLifetime = i;
            return this;
        }

        public Builder setConstraints(int[] iArr) {
            this.mConstraints = iArr;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            if (bundle != null) {
                this.mExtras.putAll(bundle);
            }
            return this;
        }

        public Builder setRetryStrategy(RetryStrategy retryStrategy) {
            this.mRetryStrategy = retryStrategy;
            return this;
        }

        public Builder setReplaceCurrent(boolean z) {
            this.mReplaceCurrent = z;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        JobInvocation jobInvocation = (JobInvocation) obj;
        if (!this.mTag.equals(jobInvocation.mTag) || !this.mService.equals(jobInvocation.mService)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mTag.hashCode() * 31) + this.mService.hashCode();
    }
}
