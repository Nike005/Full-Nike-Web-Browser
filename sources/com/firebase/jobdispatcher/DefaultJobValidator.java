package com.firebase.jobdispatcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcel;
import com.firebase.jobdispatcher.JobTrigger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DefaultJobValidator implements JobValidator {
    public static final int MAX_EXTRAS_SIZE_BYTES = 10240;
    public static final int MAX_TAG_LENGTH = 100;
    private final Context context;

    public DefaultJobValidator(Context context2) {
        this.context = context2;
    }

    private static int measureBundleSize(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        int dataSize = obtain.dataSize();
        obtain.recycle();
        return dataSize;
    }

    private static List<String> mergeErrorLists(List<String> list, List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        list.addAll(list2);
        return list;
    }

    private static List<String> addError(List<String> list, String str) {
        if (str == null) {
            return list;
        }
        if (list == null) {
            return getMutableSingletonList(str);
        }
        Collections.addAll(list, new String[]{str});
        return list;
    }

    private static List<String> addErrorsIf(boolean z, List<String> list, String str) {
        return z ? addError(list, str) : list;
    }

    public List<String> validate(JobParameters jobParameters) {
        List<String> mergeErrorLists = mergeErrorLists(mergeErrorLists((List<String>) null, validate(jobParameters.getTrigger())), validate(jobParameters.getRetryStrategy()));
        if (jobParameters.isRecurring() && jobParameters.getTrigger() == Trigger.NOW) {
            mergeErrorLists = addError(mergeErrorLists, "ImmediateTriggers can't be used with recurring jobs");
        }
        List<String> mergeErrorLists2 = mergeErrorLists(mergeErrorLists, validateForTransport(jobParameters.getExtras()));
        if (jobParameters.getLifetime() > 1) {
            mergeErrorLists2 = mergeErrorLists(mergeErrorLists2, validateForPersistence(jobParameters.getExtras()));
        }
        return mergeErrorLists(mergeErrorLists(mergeErrorLists2, validateTag(jobParameters.getTag())), validateService(jobParameters.getService()));
    }

    public List<String> validate(JobTrigger jobTrigger) {
        if (jobTrigger == Trigger.NOW || (jobTrigger instanceof JobTrigger.ExecutionWindowTrigger)) {
            return null;
        }
        return getMutableSingletonList("Unknown trigger provided");
    }

    public List<String> validate(RetryStrategy retryStrategy) {
        int policy = retryStrategy.getPolicy();
        int initialBackoff = retryStrategy.getInitialBackoff();
        int maximumBackoff = retryStrategy.getMaximumBackoff();
        boolean z = false;
        List<String> addErrorsIf = addErrorsIf(300 > maximumBackoff, addErrorsIf(maximumBackoff < initialBackoff, addErrorsIf((policy == 1 || policy == 2) ? false : true, (List<String>) null, "Unknown retry policy provided"), "Maximum backoff must be greater than or equal to initial backoff"), "Maximum backoff must be greater than 300s (5 minutes)");
        if (initialBackoff < 30) {
            z = true;
        }
        return addErrorsIf(z, addErrorsIf, "Initial backoff must be at least 30s");
    }

    private List<String> validateForPersistence(Bundle bundle) {
        List<String> list = null;
        if (bundle != null) {
            for (String validateExtrasType : bundle.keySet()) {
                list = addError(list, validateExtrasType(bundle, validateExtrasType));
            }
        }
        return list;
    }

    private List<String> validateForTransport(Bundle bundle) {
        int measureBundleSize;
        if (bundle == null || (measureBundleSize = measureBundleSize(bundle)) <= 10240) {
            return null;
        }
        return getMutableSingletonList(String.format(Locale.US, "Extras too large: %d bytes is > the max (%d bytes)", new Object[]{Integer.valueOf(measureBundleSize), Integer.valueOf(MAX_EXTRAS_SIZE_BYTES)}));
    }

    private String validateExtrasType(Bundle bundle, String str) {
        Object obj = bundle.get(str);
        Class<?> cls = null;
        if (obj == null || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean)) {
            return null;
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        if (obj != null) {
            cls = obj.getClass();
        }
        objArr[0] = cls;
        objArr[1] = str;
        return String.format(locale, "Received value of type '%s' for key '%s', but only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean", objArr);
    }

    private List<String> validateService(String str) {
        if (str == null || str.isEmpty()) {
            return getMutableSingletonList("Service can't be empty");
        }
        Context context2 = this.context;
        if (context2 == null) {
            return getMutableSingletonList("Context is null, can't query PackageManager");
        }
        PackageManager packageManager = context2.getPackageManager();
        if (packageManager == null) {
            return getMutableSingletonList("PackageManager is null, can't validate service");
        }
        String str2 = "Couldn't find a registered service with the name " + str + ". Is it declared in the manifest with the right intent-filter?";
        Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
        intent.setClassName(this.context, str);
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return getMutableSingletonList(str2);
        }
        for (ResolveInfo next : queryIntentServices) {
            if (next.serviceInfo != null && next.serviceInfo.enabled) {
                return null;
            }
        }
        return getMutableSingletonList(str2);
    }

    private List<String> validateTag(String str) {
        if (str == null) {
            return getMutableSingletonList("Tag can't be null");
        }
        if (str.length() > 100) {
            return getMutableSingletonList("Tag must be shorter than 100");
        }
        return null;
    }

    private static List<String> getMutableSingletonList(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return arrayList;
    }
}
