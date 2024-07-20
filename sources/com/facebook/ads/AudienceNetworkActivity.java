package com.facebook.ads;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import com.facebook.ads.internal.api.AudienceNetworkActivityApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;

public class AudienceNetworkActivity extends Activity {
    private AudienceNetworkActivityApi mAudienceNetworkActivityApi;
    private final AudienceNetworkActivityApi mAudienceNetworkActivityParentApi = new AudienceNetworkActivityApi() {
        public void onCreate(Bundle bundle) {
            AudienceNetworkActivity.super.onCreate(bundle);
        }

        public void onStart() {
            AudienceNetworkActivity.super.onStart();
        }

        public void onPause() {
            AudienceNetworkActivity.super.onPause();
        }

        public void onResume() {
            AudienceNetworkActivity.super.onResume();
        }

        public void onStop() {
            AudienceNetworkActivity.super.onStop();
        }

        public void onSaveInstanceState(Bundle bundle) {
            AudienceNetworkActivity.super.onSaveInstanceState(bundle);
        }

        public void finish(int i) {
            AudienceNetworkActivity.super.finish();
        }

        public void onDestroy() {
            AudienceNetworkActivity.super.onDestroy();
        }

        public void onBackPressed() {
            AudienceNetworkActivity.super.onBackPressed();
        }

        public void onConfigurationChanged(Configuration configuration) {
            AudienceNetworkActivity.super.onConfigurationChanged(configuration);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return AudienceNetworkActivity.super.onTouchEvent(motionEvent);
        }
    };

    public void onCreate(Bundle bundle) {
        AudienceNetworkActivityApi createAudienceNetworkActivity = DynamicLoaderFactory.makeLoader(this).createAudienceNetworkActivity(this, this.mAudienceNetworkActivityParentApi);
        this.mAudienceNetworkActivityApi = createAudienceNetworkActivity;
        createAudienceNetworkActivity.onCreate(bundle);
    }

    public void onStart() {
        this.mAudienceNetworkActivityApi.onStart();
    }

    public void onResume() {
        this.mAudienceNetworkActivityApi.onResume();
    }

    public void onPause() {
        this.mAudienceNetworkActivityApi.onPause();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.mAudienceNetworkActivityApi.onSaveInstanceState(bundle);
    }

    public void finish() {
        this.mAudienceNetworkActivityApi.finish(0);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mAudienceNetworkActivityApi.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mAudienceNetworkActivityApi.onDestroy();
    }

    public void onBackPressed() {
        this.mAudienceNetworkActivityApi.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.mAudienceNetworkActivityApi.onConfigurationChanged(configuration);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mAudienceNetworkActivityApi.onTouchEvent(motionEvent);
    }
}
