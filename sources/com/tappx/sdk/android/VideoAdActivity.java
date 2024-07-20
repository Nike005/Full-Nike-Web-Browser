package com.tappx.sdk.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import com.mopub.common.AdType;
import com.tappx.p048a.C1543n3;
import com.tappx.p048a.C1575p4;

public class VideoAdActivity extends Activity implements C1543n3.C1544a {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    /* renamed from: a */
    private C1543n3 f2641a;

    /* renamed from: a */
    static Intent m3800a(Context context, String str) {
        Intent intent = new Intent(context, VideoAdActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("video_view_class_name", AdType.MRAID);
        intent.putExtra("video_url", str);
        return intent;
    }

    public static void startMraid(Context context, String str) {
        try {
            context.startActivity(m3800a(context, str));
        } catch (ActivityNotFoundException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16000a(i, i2, intent);
        }
    }

    public void onBackPressed() {
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null && n3Var.mo16004a()) {
            super.onBackPressed();
            this.f2641a.mo16010f();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16001a(configuration);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        try {
            C1543n3 a = m3801a(bundle);
            this.f2641a = a;
            a.mo16011g();
        } catch (Exception unused) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16012h();
        }
        super.onDestroy();
        mo16371a();
    }

    public void onFinish() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16013i();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16014j();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        C1543n3 n3Var = this.f2641a;
        if (n3Var != null) {
            n3Var.mo16002a(bundle);
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    /* renamed from: a */
    private C1543n3 m3801a(Bundle bundle) {
        return new C1575p4(this, getIntent().getExtras(), bundle, this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16371a() {
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (audioManager != null) {
            audioManager.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
    }
}
