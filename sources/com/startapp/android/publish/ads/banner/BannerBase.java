package com.startapp.android.publish.ads.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.mobileads.VastIconXmlManager;
import com.startapp.android.publish.adsCommon.C1155i;
import com.startapp.android.publish.adsCommon.C1184o;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p028a.C1068a;
import com.startapp.android.publish.adsCommon.p028a.C1069b;
import com.startapp.android.publish.adsCommon.p028a.C1073f;
import com.startapp.android.publish.adsCommon.p028a.C1074g;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1274i;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: StartAppSDK */
public abstract class BannerBase extends RelativeLayout {
    private static final String TAG = "BannerLayout";
    protected AdPreferences adPreferences;
    protected C1073f adRulesResult;
    protected String adTag;
    private boolean attachedToWindow;
    private boolean clicked;
    /* access modifiers changed from: protected */
    public boolean drawn;
    private String error;
    private boolean firstLoad;
    protected int innerBanner3dId;
    protected int innerBannerStandardId;
    protected int offset;
    private boolean shouldReloadBanner;
    private C0878a task;
    private Timer timer;
    private C1184o viewabilityChecker;
    /* access modifiers changed from: private */
    public Handler visibilityHandler;

    /* access modifiers changed from: protected */
    public abstract int getBannerId();

    /* access modifiers changed from: protected */
    public abstract String getBannerName();

    /* access modifiers changed from: protected */
    public abstract int getHeightInDp();

    /* access modifiers changed from: protected */
    public abstract int getOffset();

    /* access modifiers changed from: protected */
    public abstract int getRefreshRate();

    /* access modifiers changed from: protected */
    public abstract int getWidthInDp();

    /* access modifiers changed from: protected */
    public abstract void initRuntime();

    /* access modifiers changed from: protected */
    public abstract void reload();

    public abstract void setAdTag(String str);

    /* access modifiers changed from: protected */
    public abstract void setBannerId(int i);

    /* renamed from: com.startapp.android.publish.ads.banner.BannerBase$a */
    /* compiled from: StartAppSDK */
    class C0878a extends TimerTask {
        C0878a() {
        }

        public void run() {
            BannerBase.this.post(new Runnable() {
                public void run() {
                    if (BannerBase.this.isShown() || (BannerBase.this.adRulesResult != null && !BannerBase.this.adRulesResult.mo14664a())) {
                        C1270g.m2078a(BannerBase.TAG, 3, "REFRESH");
                        BannerBase.this.load();
                    }
                }
            });
        }
    }

    public BannerBase(Context context) {
        super(context);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        int nextInt = new Random().nextInt(DefaultOggSeeker.MATCH_BYTE_RANGE) + 159868227;
        this.innerBanner3dId = nextInt;
        this.innerBannerStandardId = nextInt + 1;
        this.adTag = null;
        this.visibilityHandler = new Handler();
        this.viewabilityChecker = new C1184o();
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new C0878a();
    }

    public BannerBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attachedToWindow = false;
        this.offset = 0;
        this.firstLoad = true;
        this.drawn = false;
        int nextInt = new Random().nextInt(DefaultOggSeeker.MATCH_BYTE_RANGE) + 159868227;
        this.innerBanner3dId = nextInt;
        this.innerBannerStandardId = nextInt + 1;
        this.adTag = null;
        this.visibilityHandler = new Handler();
        this.viewabilityChecker = new C1184o();
        this.clicked = false;
        this.shouldReloadBanner = false;
        this.timer = new Timer();
        this.task = new C0878a();
        setBannerAttrs(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (!isInEditMode()) {
            initRuntime();
        } else {
            initDebug();
        }
    }

    private void initDebug() {
        setMinimumWidth(C1060h.m1162a(getContext(), getWidthInDp()));
        setMinimumHeight(C1060h.m1162a(getContext(), getHeightInDp()));
        setBackgroundColor(Color.rgb(169, 169, 169));
        TextView textView = new TextView(getContext());
        textView.setText(getBannerName());
        textView.setTextColor(-16777216);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        addView(textView, layoutParams);
    }

    /* access modifiers changed from: protected */
    public String getAdTag() {
        return this.adTag;
    }

    /* access modifiers changed from: protected */
    public void loadBanner() {
        scheduleReloadTask();
        load();
    }

    /* access modifiers changed from: protected */
    public void load() {
        clearVisibilityHandler();
        if (this.adRulesResult == null || C1074g.m1233a().mo14667b().mo14662a()) {
            C1073f a = C1074g.m1233a().mo14667b().mo14661a(AdPreferences.Placement.INAPP_BANNER, getAdTag());
            this.adRulesResult = a;
            if (a.mo14664a()) {
                reload();
                return;
            }
            setVisibility(4);
            if (Constants.m1978a().booleanValue()) {
                C1274i.m2100a().mo15477a(getContext(), this.adRulesResult.mo14665b());
            }
        } else if (this.adRulesResult.mo14664a()) {
            reload();
        }
    }

    /* access modifiers changed from: private */
    public void clearVisibilityHandler() {
        try {
            this.visibilityHandler.removeCallbacksAndMessages((Object) null);
        } catch (Exception e) {
            C1270g.m2078a(TAG, 6, "BannerBase.reload - removeCallbacksAndMessages failed " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldSendImpression(C1155i iVar) {
        return iVar != null && !iVar.mo14949c();
    }

    /* access modifiers changed from: protected */
    public int getMinViewabilityPercentage() {
        return C0901c.m627a().mo13973b().mo13895q();
    }

    /* access modifiers changed from: protected */
    public boolean isVisible() {
        return this.viewabilityChecker.mo15042a(this, getMinViewabilityPercentage());
    }

    /* access modifiers changed from: protected */
    public void startVisibilityRunnable(final C1155i iVar) {
        if (shouldSendImpression(iVar)) {
            C1270g.m2078a(TAG, 3, "BannerBase.startVisibilityRunnable - run visibility check");
            new Runnable() {

                /* renamed from: c */
                private boolean f461c = true;

                public void run() {
                    try {
                        if (BannerBase.this.shouldSendImpression(iVar)) {
                            boolean isVisible = BannerBase.this.isVisible();
                            if (isVisible && this.f461c) {
                                this.f461c = false;
                                iVar.mo14945a();
                            } else if (!isVisible && !this.f461c) {
                                this.f461c = true;
                                iVar.mo14947b();
                            }
                            BannerBase.this.visibilityHandler.postDelayed(this, 100);
                            return;
                        }
                        BannerBase.this.clearVisibilityHandler();
                    } catch (Exception e) {
                        C1270g.m2078a(BannerBase.TAG, 6, "BannerBase.startVisibilityRunnable.run - runnable error " + e.getMessage());
                        BannerBase.this.clearVisibilityHandler();
                    }
                }
            }.run();
        }
    }

    private void setBannerAttrs(Context context, AttributeSet attributeSet) {
        setAdTag(new C0881b(context, attributeSet).mo13904a());
    }

    private void scheduleReloadTask() {
        if (this.attachedToWindow && !isInEditMode()) {
            C0878a aVar = this.task;
            if (aVar != null) {
                aVar.cancel();
            }
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
            }
            this.task = new C0878a();
            Timer timer3 = new Timer();
            this.timer = timer3;
            timer3.scheduleAtFixedRate(this.task, (long) getRefreshRate(), (long) getRefreshRate());
        }
    }

    private void cancelReloadTask() {
        if (!isInEditMode()) {
            C0878a aVar = this.task;
            if (aVar != null) {
                aVar.cancel();
            }
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
            }
            this.task = null;
            this.timer = null;
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        if (isClicked()) {
            setClicked(false);
            this.shouldReloadBanner = true;
        }
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("bannerId", getBannerId());
        bundle.putParcelable("upperState", onSaveInstanceState);
        bundle.putSerializable("adRulesResult", this.adRulesResult);
        bundle.putSerializable("adPreferences", this.adPreferences);
        bundle.putInt(VastIconXmlManager.OFFSET, this.offset);
        bundle.putBoolean("firstLoad", this.firstLoad);
        bundle.putBoolean("shouldReloadBanner", this.shouldReloadBanner);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        setBannerId(bundle.getInt("bannerId"));
        this.adRulesResult = (C1073f) bundle.getSerializable("adRulesResult");
        this.adPreferences = (AdPreferences) bundle.getSerializable("adPreferences");
        this.offset = bundle.getInt(VastIconXmlManager.OFFSET);
        this.firstLoad = bundle.getBoolean("firstLoad");
        this.shouldReloadBanner = bundle.getBoolean("shouldReloadBanner");
        super.onRestoreInstanceState(bundle.getParcelable("upperState"));
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        C1270g.m2078a(TAG, 3, "onAttachedToWindow");
        this.attachedToWindow = true;
        scheduleReloadTask();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C1270g.m2078a(TAG, 3, "onDetachedFromWindow");
        this.attachedToWindow = false;
        cancelReloadTask();
        clearVisibilityHandler();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        C1270g.m2078a(TAG, 3, "onWindowFocusChanged");
        if (z) {
            if (this.shouldReloadBanner) {
                this.shouldReloadBanner = false;
                load();
            }
            this.attachedToWindow = true;
            scheduleReloadTask();
            return;
        }
        this.attachedToWindow = false;
        cancelReloadTask();
    }

    public boolean isFirstLoad() {
        return this.firstLoad;
    }

    public void setFirstLoad(boolean z) {
        this.firstLoad = z;
    }

    /* access modifiers changed from: protected */
    public void addDisplayEventOnLoad() {
        if (isFirstLoad() || C1074g.m1233a().mo14667b().mo14662a()) {
            setFirstLoad(false);
            C1069b.m1217a().mo14655a(new C1068a(AdPreferences.Placement.INAPP_BANNER, getAdTag()));
        }
    }

    /* access modifiers changed from: protected */
    public void setHardwareAcceleration(AdPreferences adPreferences2) {
        C1061i.m1190a(adPreferences2, "hardwareAccelerated", C1261c.m2034a((View) this, this.attachedToWindow));
    }

    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked(boolean z) {
        this.clicked = z;
    }

    public void setErrorMessage(String str) {
        this.error = str;
    }

    public String getErrorMessage() {
        return this.error;
    }
}
