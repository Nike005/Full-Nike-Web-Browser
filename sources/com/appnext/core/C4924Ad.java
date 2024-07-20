package com.appnext.core;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.appnext.base.Appnext;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.p086a.C4944b;
import com.google.android.gms.security.ProviderInstaller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* renamed from: com.appnext.core.Ad */
public abstract class C4924Ad {
    public static final String ORIENTATION_AUTO = "automatic";
    public static final String ORIENTATION_DEFAULT = "not_set";
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_PORTRAIT = "portrait";

    /* renamed from: fq */
    protected static boolean f4681fq = false;
    private OnAdOpened adOpenedCallback;
    private C4947c adRequest;
    private String cat = "";
    private OnAdClosed closeCallback;
    private int cnt = 50;
    /* access modifiers changed from: protected */
    public Context context;
    private int maxVideoLength = 0;
    private int minVideoLength = 0;
    private boolean mute = false;
    private OnAdClicked onAdClicked;
    private OnAdError onAdError;
    private OnAdLoaded onAdLoaded;
    private String orientation = ORIENTATION_DEFAULT;
    private String pbk = "";
    private String placementID = "";
    private String sessionId = "";
    protected boolean setMute = false;

    public abstract String getAUID();

    public abstract void getECPM(OnECPMLoaded onECPMLoaded);

    public abstract String getTID();

    public abstract String getVID();

    public abstract boolean isAdLoaded();

    public abstract void loadAd();

    public abstract void showAd();

    public C4924Ad(final Context context2, String str) {
        if (context2 == null) {
            throw new IllegalArgumentException("Context cannot be null");
        } else if (str != null) {
            this.context = context2;
            setPlacementID(str);
            C4975j.m6859bj().mo41287d(context2, str);
            if (C4967f.m6833bd().equals("")) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public final void run() {
                        C4967f.m6847m(context2);
                    }
                });
            }
            new Thread(new Runnable() {
                public final void run() {
                    try {
                        if (Build.VERSION.SDK_INT <= 19) {
                            ProviderInstaller.installIfNeeded(context2.getApplicationContext());
                        }
                    } catch (Throwable unused) {
                    }
                    C4924Ad.this.setSessionId(C4967f.m6852q(context2));
                }
            }).start();
            C4944b.m6738bp();
            new Thread(new Runnable() {
                public final void run() {
                    Appnext.init(context2);
                }
            }).start();
        } else {
            throw new IllegalArgumentException("placementID cannot be null");
        }
    }

    protected C4924Ad(C4924Ad ad) {
        this.context = ad.context;
        setPlacementID(ad.getPlacementID());
        setCategories(ad.cat);
        setPostback(ad.getPostback());
        setCount(ad.getCount());
        setMinVideoLength(ad.getMinVideoLength());
        setMaxVideoLength(ad.getMaxVideoLength());
        setSessionId(ad.getSessionId());
        this.onAdClicked = ad.onAdClicked;
        this.onAdError = ad.onAdError;
        this.onAdLoaded = ad.onAdLoaded;
        this.closeCallback = ad.closeCallback;
        this.adOpenedCallback = ad.adOpenedCallback;
    }

    public String getPlacementID() {
        return this.placementID;
    }

    /* access modifiers changed from: protected */
    public void setPlacementID(String str) {
        this.placementID = str;
    }

    public OnAdClicked getOnAdClickedCallback() {
        return this.onAdClicked;
    }

    public OnAdError getOnAdErrorCallback() {
        return this.onAdError;
    }

    public OnAdLoaded getOnAdLoadedCallback() {
        return this.onAdLoaded;
    }

    public OnAdClosed getOnAdClosedCallback() {
        return this.closeCallback;
    }

    public void setOnAdClickedCallback(OnAdClicked onAdClicked2) {
        this.onAdClicked = onAdClicked2;
    }

    public void setOnAdErrorCallback(OnAdError onAdError2) {
        this.onAdError = onAdError2;
    }

    public void setOnAdLoadedCallback(OnAdLoaded onAdLoaded2) {
        this.onAdLoaded = onAdLoaded2;
    }

    public void setOnAdClosedCallback(OnAdClosed onAdClosed) {
        this.closeCallback = onAdClosed;
    }

    public OnAdOpened getOnAdOpenedCallback() {
        return this.adOpenedCallback;
    }

    public void setOnAdOpenedCallback(OnAdOpened onAdOpened) {
        this.adOpenedCallback = onAdOpened;
    }

    public void setCategories(String str) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        try {
            if (str.equals(URLDecoder.decode(str, "UTF-8"))) {
                str = URLEncoder.encode(str, "UTF-8");
            }
            str2 = str;
        } catch (UnsupportedEncodingException unused) {
        }
        this.cat = str2;
    }

    public void setPostback(String str) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        try {
            if (str.equals(URLDecoder.decode(str, "UTF-8"))) {
                str = URLEncoder.encode(str, "UTF-8");
            }
            str2 = str;
        } catch (UnsupportedEncodingException unused) {
        }
        this.pbk = str2;
    }

    public void setMute(boolean z) {
        this.setMute = true;
        this.mute = z;
    }

    public String getCategories() {
        return this.cat;
    }

    public String getPostback() {
        return this.pbk;
    }

    public boolean getMute() {
        return this.mute;
    }

    /* access modifiers changed from: protected */
    public int getCount() {
        return this.cnt;
    }

    /* access modifiers changed from: protected */
    public void setCount(int i) {
        this.cnt = i;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void setOrientation(String str) {
        if (str == null) {
            throw new IllegalArgumentException("orientation type");
        } else if (str.equals(ORIENTATION_AUTO) || str.equals(ORIENTATION_DEFAULT) || str.equals(ORIENTATION_LANDSCAPE) || str.equals(ORIENTATION_PORTRAIT)) {
            this.orientation = str;
        } else {
            throw new IllegalArgumentException("Wrong orientation type");
        }
    }

    public int getMaxVideoLength() {
        return this.maxVideoLength;
    }

    public void setMaxVideoLength(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Max Length must be higher than 0");
        } else if (i <= 0 || getMinVideoLength() <= 0 || i >= getMinVideoLength()) {
            this.maxVideoLength = i;
        } else {
            throw new IllegalArgumentException("Max Length cannot be lower than min length");
        }
    }

    public int getMinVideoLength() {
        return this.minVideoLength;
    }

    public void setMinVideoLength(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Min Length must be higher than 0");
        } else if (i <= 0 || getMaxVideoLength() <= 0 || i <= getMaxVideoLength()) {
            this.minVideoLength = i;
        } else {
            throw new IllegalArgumentException("Min Length cannot be higher than max length");
        }
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public C4947c getAdRequest() {
        return this.adRequest;
    }

    /* access modifiers changed from: protected */
    public void setAdRequest(C4947c cVar) {
        this.adRequest = cVar;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return this.sessionId;
    }

    /* access modifiers changed from: protected */
    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isInstance(obj) && !obj.getClass().isInstance(this)) {
            return false;
        }
        if (!(obj instanceof C4924Ad)) {
            return super.equals(obj);
        }
        C4924Ad ad = (C4924Ad) obj;
        return ad.getPlacementID().equals(getPlacementID()) && ad.getCategories().equals(getCategories()) && ad.getPostback().equals(getPostback()) && ad.getMinVideoLength() == getMinVideoLength() && ad.getMaxVideoLength() == getMaxVideoLength() && ad.getCount() == getCount();
    }

    public int hashCode() {
        return (((((((((getPlacementID().hashCode() * 31) + getCategories().hashCode()) * 31) + getPostback().hashCode()) * 31) + getCount()) * 31) + getMinVideoLength()) * 31) + getMaxVideoLength();
    }

    public void destroy() {
        this.context = null;
        this.onAdClicked = null;
        this.onAdError = null;
        this.onAdLoaded = null;
        this.closeCallback = null;
        this.adOpenedCallback = null;
    }
}
