package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.android.billingclient.util.BillingHelper;

public class ProxyBillingActivity extends Activity {
    static final String KEY_RESULT_RECEIVER = "result_receiver";
    private static final int REQUEST_CODE_LAUNCH_ACTIVITY = 100;
    private static final String TAG = "ProxyBillingActivity";
    private ResultReceiver mResultReceiver;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        PendingIntent pendingIntent;
        super.onCreate(bundle);
        if (bundle == null) {
            BillingHelper.logVerbose(TAG, "Launching Play Store billing flow");
            this.mResultReceiver = (ResultReceiver) getIntent().getParcelableExtra(KEY_RESULT_RECEIVER);
            if (getIntent().hasExtra(BillingHelper.RESPONSE_BUY_INTENT_KEY)) {
                pendingIntent = (PendingIntent) getIntent().getParcelableExtra(BillingHelper.RESPONSE_BUY_INTENT_KEY);
            } else {
                pendingIntent = getIntent().hasExtra(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY) ? (PendingIntent) getIntent().getParcelableExtra(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY) : null;
            }
            try {
                startIntentSenderForResult(pendingIntent.getIntentSender(), 100, new Intent(), 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                BillingHelper.logWarn(TAG, "Got exception while trying to start a purchase flow: " + e);
                this.mResultReceiver.send(6, (Bundle) null);
                finish();
            }
        } else {
            BillingHelper.logVerbose(TAG, "Launching Play Store billing flow from savedInstanceState");
            this.mResultReceiver = (ResultReceiver) bundle.getParcelable(KEY_RESULT_RECEIVER);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(KEY_RESULT_RECEIVER, this.mResultReceiver);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            int responseCodeFromIntent = BillingHelper.getResponseCodeFromIntent(intent, TAG);
            if (!(i2 == -1 && responseCodeFromIntent == 0)) {
                BillingHelper.logWarn(TAG, "Activity finished with resultCode " + i2 + " and billing's responseCode: " + responseCodeFromIntent);
            }
            this.mResultReceiver.send(responseCodeFromIntent, intent == null ? null : intent.getExtras());
        } else {
            BillingHelper.logWarn(TAG, "Got onActivityResult with wrong requestCode: " + i + "; skipping...");
        }
        finish();
    }
}
