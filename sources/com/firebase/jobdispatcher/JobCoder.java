package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.firebase.jobdispatcher.JobInvocation;
import com.firebase.jobdispatcher.JobTrigger;

final class JobCoder {
    private final boolean includeExtras;
    private final String prefix;

    JobCoder(String str, boolean z) {
        this.includeExtras = z;
        this.prefix = str;
    }

    /* access modifiers changed from: package-private */
    public Bundle encode(JobParameters jobParameters, Bundle bundle) {
        if (bundle != null) {
            bundle.putInt(this.prefix + "persistent", jobParameters.getLifetime());
            bundle.putBoolean(this.prefix + "recurring", jobParameters.isRecurring());
            bundle.putBoolean(this.prefix + "replace_current", jobParameters.shouldReplaceCurrent());
            bundle.putString(this.prefix + "tag", jobParameters.getTag());
            bundle.putString(this.prefix + NotificationCompat.CATEGORY_SERVICE, jobParameters.getService());
            bundle.putInt(this.prefix + "constraints", Constraint.compact(jobParameters.getConstraints()));
            if (this.includeExtras) {
                bundle.putBundle(this.prefix + "extras", jobParameters.getExtras());
            }
            encodeTrigger(jobParameters.getTrigger(), bundle);
            encodeRetryStrategy(jobParameters.getRetryStrategy(), bundle);
            return bundle;
        }
        throw new IllegalArgumentException("Unexpected null Bundle provided");
    }

    /* access modifiers changed from: package-private */
    public JobInvocation decodeIntentBundle(Bundle bundle) {
        if (bundle == null) {
            Log.e("FJD.ExternalReceiver", "Unexpected null Bundle provided");
            return null;
        }
        Bundle bundle2 = bundle.getBundle("extras");
        if (bundle2 == null) {
            return null;
        }
        return decode(bundle2).build();
    }

    public JobInvocation.Builder decode(Bundle bundle) {
        if (bundle != null) {
            boolean z = bundle.getBoolean(this.prefix + "recurring");
            boolean z2 = bundle.getBoolean(this.prefix + "replace_current");
            int i = bundle.getInt(this.prefix + "persistent");
            int[] uncompact = Constraint.uncompact(bundle.getInt(this.prefix + "constraints"));
            JobTrigger decodeTrigger = decodeTrigger(bundle);
            RetryStrategy decodeRetryStrategy = decodeRetryStrategy(bundle);
            String string = bundle.getString(this.prefix + "tag");
            String string2 = bundle.getString(this.prefix + NotificationCompat.CATEGORY_SERVICE);
            if (string == null || string2 == null || decodeTrigger == null || decodeRetryStrategy == null) {
                return null;
            }
            JobInvocation.Builder builder = new JobInvocation.Builder();
            builder.setTag(string);
            builder.setService(string2);
            builder.setTrigger(decodeTrigger);
            builder.setRetryStrategy(decodeRetryStrategy);
            builder.setRecurring(z);
            builder.setLifetime(i);
            builder.setConstraints(uncompact);
            builder.setReplaceCurrent(z2);
            builder.addExtras(bundle);
            return builder;
        }
        throw new IllegalArgumentException("Unexpected null Bundle provided");
    }

    private JobTrigger decodeTrigger(Bundle bundle) {
        int i = bundle.getInt(this.prefix + "trigger_type");
        if (i == 1) {
            int i2 = bundle.getInt(this.prefix + "window_start");
            return Trigger.executionWindow(i2, bundle.getInt(this.prefix + "window_end"));
        } else if (i == 2) {
            return Trigger.NOW;
        } else {
            if (!Log.isLoggable("FJD.ExternalReceiver", 3)) {
                return null;
            }
            Log.d("FJD.ExternalReceiver", "Unsupported trigger.");
            return null;
        }
    }

    private void encodeTrigger(JobTrigger jobTrigger, Bundle bundle) {
        if (jobTrigger == Trigger.NOW) {
            bundle.putInt(this.prefix + "trigger_type", 2);
        } else if (jobTrigger instanceof JobTrigger.ExecutionWindowTrigger) {
            JobTrigger.ExecutionWindowTrigger executionWindowTrigger = (JobTrigger.ExecutionWindowTrigger) jobTrigger;
            bundle.putInt(this.prefix + "trigger_type", 1);
            bundle.putInt(this.prefix + "window_start", executionWindowTrigger.getWindowStart());
            bundle.putInt(this.prefix + "window_end", executionWindowTrigger.getWindowEnd());
        } else {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
    }

    private RetryStrategy decodeRetryStrategy(Bundle bundle) {
        int i = bundle.getInt(this.prefix + "retry_policy");
        if (i != 1 && i != 2) {
            return RetryStrategy.DEFAULT_EXPONENTIAL;
        }
        int i2 = bundle.getInt(this.prefix + "initial_backoff_seconds");
        return new RetryStrategy(i, i2, bundle.getInt(this.prefix + "maximum_backoff_seconds"));
    }

    private void encodeRetryStrategy(RetryStrategy retryStrategy, Bundle bundle) {
        if (retryStrategy == null) {
            retryStrategy = RetryStrategy.DEFAULT_EXPONENTIAL;
        }
        bundle.putInt(this.prefix + "retry_policy", retryStrategy.getPolicy());
        bundle.putInt(this.prefix + "initial_backoff_seconds", retryStrategy.getInitialBackoff());
        bundle.putInt(this.prefix + "maximum_backoff_seconds", retryStrategy.getMaximumBackoff());
    }
}
