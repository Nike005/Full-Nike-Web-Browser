package com.firebase.jobdispatcher;

import android.os.Bundle;
import com.firebase.jobdispatcher.JobTrigger;

final class GooglePlayJobWriter {
    static final int LEGACY_NETWORK_ANY = 2;
    static final int LEGACY_NETWORK_CONNECTED = 0;
    static final int LEGACY_NETWORK_UNMETERED = 1;
    static final int LEGACY_RETRY_POLICY_EXPONENTIAL = 0;
    static final int LEGACY_RETRY_POLICY_LINEAR = 1;
    static final String REQUEST_PARAM_EXTRAS = "extras";
    static final String REQUEST_PARAM_PERSISTED = "persisted";
    static final String REQUEST_PARAM_REQUIRED_NETWORK = "requiredNetwork";
    static final String REQUEST_PARAM_REQUIRES_CHARGING = "requiresCharging";
    static final String REQUEST_PARAM_REQUIRES_IDLE = "requiresIdle";
    static final String REQUEST_PARAM_RETRY_STRATEGY = "retryStrategy";
    static final String REQUEST_PARAM_RETRY_STRATEGY_INITIAL_BACKOFF_SECONDS = "initial_backoff_seconds";
    static final String REQUEST_PARAM_RETRY_STRATEGY_MAXIMUM_BACKOFF_SECONDS = "maximum_backoff_seconds";
    static final String REQUEST_PARAM_RETRY_STRATEGY_POLICY = "retry_policy";
    static final String REQUEST_PARAM_SERVICE = "service";
    static final String REQUEST_PARAM_TAG = "tag";
    static final String REQUEST_PARAM_TRIGGER_TYPE = "trigger_type";
    static final String REQUEST_PARAM_TRIGGER_WINDOW_END = "window_end";
    static final String REQUEST_PARAM_TRIGGER_WINDOW_FLEX = "period_flex";
    static final String REQUEST_PARAM_TRIGGER_WINDOW_PERIOD = "period";
    static final String REQUEST_PARAM_TRIGGER_WINDOW_START = "window_start";
    static final String REQUEST_PARAM_UPDATE_CURRENT = "update_current";
    private JobCoder jobCoder = new JobCoder("com.firebase.jobdispatcher.", false);

    private int convertConstraintsToLegacyNetConstant(int i) {
        int i2 = 2;
        if ((i & 2) == 2) {
            i2 = 0;
        }
        if ((i & 1) == 1) {
            return 1;
        }
        return i2;
    }

    private int convertRetryPolicyToLegacyVersion(int i) {
        return i != 2 ? 0 : 1;
    }

    GooglePlayJobWriter() {
    }

    private static void writeExecutionWindowTriggerToBundle(JobParameters jobParameters, Bundle bundle, JobTrigger.ExecutionWindowTrigger executionWindowTrigger) {
        bundle.putInt(REQUEST_PARAM_TRIGGER_TYPE, 1);
        if (jobParameters.isRecurring()) {
            bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_PERIOD, (long) executionWindowTrigger.getWindowEnd());
            bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_FLEX, (long) (executionWindowTrigger.getWindowEnd() - executionWindowTrigger.getWindowStart()));
            return;
        }
        bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_START, (long) executionWindowTrigger.getWindowStart());
        bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_END, (long) executionWindowTrigger.getWindowEnd());
    }

    private static void writeImmediateTriggerToBundle(Bundle bundle) {
        bundle.putInt(REQUEST_PARAM_TRIGGER_TYPE, 2);
        bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_START, 0);
        bundle.putLong(REQUEST_PARAM_TRIGGER_WINDOW_END, 30);
    }

    public Bundle writeToBundle(JobParameters jobParameters, Bundle bundle) {
        bundle.putString(REQUEST_PARAM_TAG, jobParameters.getTag());
        bundle.putBoolean(REQUEST_PARAM_UPDATE_CURRENT, jobParameters.shouldReplaceCurrent());
        bundle.putBoolean(REQUEST_PARAM_PERSISTED, jobParameters.getLifetime() == 2);
        bundle.putString("service", GooglePlayReceiver.class.getName());
        writeTriggerToBundle(jobParameters, bundle);
        writeConstraintsToBundle(jobParameters, bundle);
        writeRetryStrategyToBundle(jobParameters, bundle);
        Bundle extras = jobParameters.getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        bundle.putBundle(REQUEST_PARAM_EXTRAS, this.jobCoder.encode(jobParameters, extras));
        return bundle;
    }

    private void writeRetryStrategyToBundle(JobParameters jobParameters, Bundle bundle) {
        RetryStrategy retryStrategy = jobParameters.getRetryStrategy();
        Bundle bundle2 = new Bundle();
        bundle2.putInt(REQUEST_PARAM_RETRY_STRATEGY_POLICY, convertRetryPolicyToLegacyVersion(retryStrategy.getPolicy()));
        bundle2.putInt(REQUEST_PARAM_RETRY_STRATEGY_INITIAL_BACKOFF_SECONDS, retryStrategy.getInitialBackoff());
        bundle2.putInt(REQUEST_PARAM_RETRY_STRATEGY_MAXIMUM_BACKOFF_SECONDS, retryStrategy.getMaximumBackoff());
        bundle.putBundle(REQUEST_PARAM_RETRY_STRATEGY, bundle2);
    }

    private void writeTriggerToBundle(JobParameters jobParameters, Bundle bundle) {
        JobTrigger trigger = jobParameters.getTrigger();
        if (trigger == Trigger.NOW) {
            writeImmediateTriggerToBundle(bundle);
        } else if (trigger instanceof JobTrigger.ExecutionWindowTrigger) {
            writeExecutionWindowTriggerToBundle(jobParameters, bundle, (JobTrigger.ExecutionWindowTrigger) trigger);
        } else {
            throw new IllegalArgumentException("Unknown trigger: " + trigger.getClass());
        }
    }

    private void writeConstraintsToBundle(JobParameters jobParameters, Bundle bundle) {
        int compact = Constraint.compact(jobParameters.getConstraints());
        boolean z = true;
        bundle.putBoolean(REQUEST_PARAM_REQUIRES_CHARGING, (compact & 4) == 4);
        if ((compact & 8) != 8) {
            z = false;
        }
        bundle.putBoolean(REQUEST_PARAM_REQUIRES_IDLE, z);
        bundle.putInt(REQUEST_PARAM_REQUIRED_NETWORK, convertConstraintsToLegacyNetConstant(compact));
    }
}
