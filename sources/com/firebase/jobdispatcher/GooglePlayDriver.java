package com.firebase.jobdispatcher;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public final class GooglePlayDriver implements Driver {
    private static final String ACTION_SCHEDULE = "com.google.android.gms.gcm.ACTION_SCHEDULE";
    static final String BACKEND_PACKAGE = "com.google.android.gms";
    private static final String BUNDLE_PARAM_COMPONENT = "component";
    private static final String BUNDLE_PARAM_SCHEDULER_ACTION = "scheduler_action";
    private static final String BUNDLE_PARAM_TAG = "tag";
    private static final String BUNDLE_PARAM_TOKEN = "app";
    private static final String INTENT_PARAM_SOURCE = "source";
    private static final String INTENT_PARAM_SOURCE_VERSION = "source_version";
    private static final int JOB_DISPATCHER_SOURCE_CODE = 8;
    private static final int JOB_DISPATCHER_SOURCE_VERSION_CODE = 1;
    private static final String SCHEDULER_ACTION_CANCEL_ALL = "CANCEL_ALL";
    private static final String SCHEDULER_ACTION_CANCEL_TASK = "CANCEL_TASK";
    private static final String SCHEDULER_ACTION_SCHEDULE_TASK = "SCHEDULE_TASK";
    private final boolean mAvailable = true;
    private final Context mContext;
    private final PendingIntent mToken;
    private final JobValidator mValidator;
    private final GooglePlayJobWriter mWriter;

    public boolean isAvailable() {
        return true;
    }

    public GooglePlayDriver(Context context) {
        this.mContext = context;
        this.mToken = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        this.mWriter = new GooglePlayJobWriter();
        this.mValidator = new DefaultJobValidator(context);
    }

    public int schedule(Job job) {
        this.mContext.sendBroadcast(createScheduleRequest(job));
        return 0;
    }

    public int cancel(String str) {
        this.mContext.sendBroadcast(createCancelRequest(str));
        return 0;
    }

    public int cancelAll() {
        this.mContext.sendBroadcast(createBatchCancelRequest());
        return 0;
    }

    /* access modifiers changed from: protected */
    public Intent createCancelRequest(String str) {
        Intent createSchedulerIntent = createSchedulerIntent(SCHEDULER_ACTION_CANCEL_TASK);
        createSchedulerIntent.putExtra(BUNDLE_PARAM_TAG, str);
        createSchedulerIntent.putExtra(BUNDLE_PARAM_COMPONENT, new ComponentName(this.mContext, getReceiverClass()));
        return createSchedulerIntent;
    }

    /* access modifiers changed from: protected */
    public Intent createBatchCancelRequest() {
        Intent createSchedulerIntent = createSchedulerIntent(SCHEDULER_ACTION_CANCEL_ALL);
        createSchedulerIntent.putExtra(BUNDLE_PARAM_COMPONENT, new ComponentName(this.mContext, getReceiverClass()));
        return createSchedulerIntent;
    }

    /* access modifiers changed from: protected */
    public Class<GooglePlayReceiver> getReceiverClass() {
        return GooglePlayReceiver.class;
    }

    public JobValidator getValidator() {
        return this.mValidator;
    }

    private Intent createScheduleRequest(JobParameters jobParameters) {
        Intent createSchedulerIntent = createSchedulerIntent(SCHEDULER_ACTION_SCHEDULE_TASK);
        createSchedulerIntent.putExtras(this.mWriter.writeToBundle(jobParameters, createSchedulerIntent.getExtras()));
        return createSchedulerIntent;
    }

    private Intent createSchedulerIntent(String str) {
        Intent intent = new Intent(ACTION_SCHEDULE);
        intent.setPackage("com.google.android.gms");
        intent.putExtra(BUNDLE_PARAM_SCHEDULER_ACTION, str);
        intent.putExtra(BUNDLE_PARAM_TOKEN, this.mToken);
        intent.putExtra("source", 8);
        intent.putExtra(INTENT_PARAM_SOURCE_VERSION, 1);
        return intent;
    }
}
