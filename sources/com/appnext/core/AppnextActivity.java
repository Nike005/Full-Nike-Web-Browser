package com.appnext.core;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.appnext.core.C4956e;

public abstract class AppnextActivity extends Activity {
    protected String banner = "";

    /* renamed from: gj */
    protected String f4691gj = "";

    /* renamed from: gk */
    private RelativeLayout f4692gk;

    /* renamed from: gl */
    protected RelativeLayout f4693gl;
    protected String guid = "";
    /* access modifiers changed from: protected */
    public Handler handler;
    /* access modifiers changed from: protected */
    public String placementID;
    /* access modifiers changed from: protected */
    public C4990q userAction;

    /* access modifiers changed from: protected */
    public abstract C4986p getConfig();

    /* access modifiers changed from: protected */
    public abstract void onError(String str);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        new Thread(new Runnable() {
            public final void run() {
                if (!C4967f.m6854s(AppnextActivity.this)) {
                    AppnextActivity.this.finish();
                    AppnextActivity.this.runOnUiThread(new Runnable() {
                        public final void run() {
                            AppnextActivity.this.onError(AppnextError.CONNECTION_ERROR);
                        }
                    });
                }
            }
        }).start();
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        super.onCreate(bundle);
        this.handler = new Handler();
    }

    /* access modifiers changed from: protected */
    /* renamed from: aY */
    public final void mo41113aY() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility ^= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility ^= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    /* renamed from: aZ */
    public final void mo41114aZ() {
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() | 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo40483a(AppnextAd appnextAd, C4956e.C4965a aVar) {
        C4990q qVar = this.userAction;
        if (qVar != null && appnextAd != null) {
            qVar.mo41311a(appnextAd, appnextAd.getImpressionURL(), aVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo40484b(AppnextAd appnextAd, C4956e.C4965a aVar) {
        C4990q qVar = this.userAction;
        if (qVar != null && appnextAd != null) {
            qVar.mo41312a(appnextAd, appnextAd.getAppURL(), this.placementID, aVar);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41112a(ViewGroup viewGroup, Drawable drawable) {
        if (this.f4692gk != null) {
            mo41115ba();
        }
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.f4692gk = relativeLayout;
        relativeLayout.setBackgroundColor(Color.parseColor("#77ffffff"));
        viewGroup.addView(this.f4692gk);
        this.f4692gk.getLayoutParams().height = -1;
        this.f4692gk.getLayoutParams().width = -1;
        ProgressBar progressBar = new ProgressBar(this, (AttributeSet) null, 16842871);
        progressBar.setIndeterminateDrawable(drawable);
        progressBar.setIndeterminate(true);
        this.f4692gk.addView(progressBar);
        RotateAnimation rotateAnimation = new RotateAnimation(360.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(-1);
        progressBar.setAnimation(rotateAnimation);
        ((RelativeLayout.LayoutParams) progressBar.getLayoutParams()).addRule(13, -1);
        this.f4692gk.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
            }
        });
        this.handler.postDelayed(new Runnable() {
            public final void run() {
                AppnextActivity.this.mo41115ba();
            }
        }, 8000);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ba */
    public final void mo41115ba() {
        RelativeLayout relativeLayout = this.f4692gk;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
            this.f4692gk.removeAllViewsInLayout();
            if (this.f4692gk.getParent() != null) {
                ((RelativeLayout) this.f4692gk.getParent()).removeView(this.f4692gk);
            }
        }
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacks((Runnable) null);
        }
        this.f4692gk = null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r2 = this;
            super.onDestroy()
            r0 = 0
            android.os.Handler r1 = r2.handler     // Catch:{ all -> 0x000b }
            r1.removeCallbacks(r0)     // Catch:{ all -> 0x000b }
            r2.handler = r0     // Catch:{ all -> 0x000b }
        L_0x000b:
            com.appnext.core.q r1 = r2.userAction     // Catch:{ all -> 0x0012 }
            r1.destroy()     // Catch:{ all -> 0x0012 }
            r2.userAction = r0     // Catch:{ all -> 0x0012 }
        L_0x0012:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.AppnextActivity.onDestroy():void");
    }
}
