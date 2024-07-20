package com.appnext.ads.fullscreen;

import android.content.Context;
import android.content.Intent;
import com.appnext.ads.AdsError;
import com.appnext.ads.C4708a;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4948d;
import com.appnext.core.C4967f;
import com.appnext.core.C4975j;
import com.appnext.core.C4986p;
import com.appnext.core.ECPM;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.callbacks.OnVideoEnded;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

public abstract class Video extends C4924Ad {
    protected static final int FULL_SCREEN_VIDEO = 1;
    protected static final int REWARDED_VIDEO = 2;
    protected static final String TID = "301";
    protected static final String VID = "2.5.1.472";
    public static final String VIDEO_LENGTH_LONG = "30";
    public static final String VIDEO_LENGTH_SHORT = "15";
    private static boolean cacheVideo = true;
    protected static Video currentAd = null;
    /* access modifiers changed from: private */
    public static boolean init = false;
    private static boolean streamingEnable = false;
    private boolean canClose;
    private String language;
    protected long rnd;
    private int rollCaptionTime;
    private boolean showCta;
    /* access modifiers changed from: private */
    public int type;
    /* access modifiers changed from: private */
    public OnAdError userOnAdError;
    private OnVideoEnded videoEnded;
    private String videoLength;

    public String getTID() {
        return TID;
    }

    public String getVID() {
        return "2.5.1.472";
    }

    public Video(Context context, int i, String str) {
        super(context, str);
        this.videoLength = "15";
        this.showCta = true;
        this.rollCaptionTime = -2;
        this.language = "";
        this.canClose = true;
        this.type = i;
        this.rnd = (long) new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
        init();
    }

    public Video(Context context, int i, String str, VideoConfig videoConfig) {
        super(context, str);
        this.videoLength = "15";
        boolean z = true;
        this.showCta = true;
        this.rollCaptionTime = -2;
        this.language = "";
        this.canClose = true;
        this.type = i;
        this.rnd = (long) new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
        init();
        if (videoConfig != null) {
            setPostback(videoConfig.getPostback());
            setCategories(videoConfig.getCategories());
            setOrientation(videoConfig.getOrientation());
            if (videoConfig.showCta != null) {
                setShowCta(videoConfig.isShowCta());
            }
            setRollCaptionTime(videoConfig.getRollCaptionTime());
            setVideoLength(videoConfig.getVideoLength());
            if (videoConfig.mute == null ? false : z) {
                setMute(videoConfig.getMute());
            }
            setMinVideoLength(videoConfig.getMinVideoLength());
            setMaxVideoLength(videoConfig.getMaxVideoLength());
            setLanguage(videoConfig.getLanguage());
        }
    }

    protected Video(Context context, Video video) {
        super(context, video.getPlacementID());
        this.videoLength = "15";
        this.showCta = true;
        this.rollCaptionTime = -2;
        this.language = "";
        this.canClose = true;
        this.type = video.type;
        this.rnd = video.rnd;
        setPostback(video.getPostback());
        setCategories(video.getCategories());
        setOrientation(video.getOrientation());
        setVideoLength(video.getVideoLength());
        setMute(video.getMute());
        setMinVideoLength(video.getMinVideoLength());
        setMaxVideoLength(video.getMaxVideoLength());
        setRollCaptionTime(video.getRollCaptionTime());
        setShowCta(video.isShowCta());
        setLanguage(video.getLanguage());
        setOnVideoEndedCallback(video.getOnVideoEndedCallback());
        setOnAdClosedCallback(video.getOnAdClosedCallback());
        setOnAdErrorCallback(video.getOnAdErrorCallback());
        setOnAdClickedCallback(video.getOnAdClickedCallback());
        setOnAdOpenedCallback(video.getOnAdOpenedCallback());
        setOnAdLoadedCallback(video.getOnAdLoadedCallback());
        setSessionId(video.getSessionId());
    }

    private void init() {
        getConfig().mo41305q("tid", getTID());
        getConfig().mo41298a(this.context, (C4986p.C4988a) new C4986p.C4988a() {
            /* renamed from: b */
            public final void mo40595b(HashMap<String, Object> hashMap) {
                C4975j.m6859bj().mo41286b(Integer.parseInt(Video.this.getConfig().get("banner_expiration_time")));
            }

            public final void error(String str) {
                C4975j.m6859bj().mo41286b(Integer.parseInt(Video.this.getConfig().get("banner_expiration_time")));
            }
        });
        new Thread(new Runnable() {
            public final void run() {
                if (!Video.init) {
                    boolean unused = Video.init = true;
                    C4967f.m6821a(new File(Video.this.context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/"));
                }
            }
        }).start();
        super.setOnAdErrorCallback(new OnAdError() {
            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void adError(java.lang.String r12) {
                /*
                    r11 = this;
                    int r0 = r12.hashCode()
                    r1 = 1
                    switch(r0) {
                        case -2026653947: goto L_0x0045;
                        case -1958363695: goto L_0x003b;
                        case -1477010874: goto L_0x0031;
                        case -507110949: goto L_0x0027;
                        case 297538105: goto L_0x001d;
                        case 350741825: goto L_0x0013;
                        case 844170097: goto L_0x0009;
                        default: goto L_0x0008;
                    }
                L_0x0008:
                    goto L_0x004f
                L_0x0009:
                    java.lang.String r0 = "Too Slow Connection"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 4
                    goto L_0x0050
                L_0x0013:
                    java.lang.String r0 = "Timeout"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 5
                    goto L_0x0050
                L_0x001d:
                    java.lang.String r0 = "Ad Not Ready"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 6
                    goto L_0x0050
                L_0x0027:
                    java.lang.String r0 = "No market installed on the device"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 3
                    goto L_0x0050
                L_0x0031:
                    java.lang.String r0 = "Connection Error"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 0
                    goto L_0x0050
                L_0x003b:
                    java.lang.String r0 = "No Ads"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 2
                    goto L_0x0050
                L_0x0045:
                    java.lang.String r0 = "Internal error"
                    boolean r0 = r12.equals(r0)
                    if (r0 == 0) goto L_0x004f
                    r0 = 1
                    goto L_0x0050
                L_0x004f:
                    r0 = -1
                L_0x0050:
                    switch(r0) {
                        case 0: goto L_0x0069;
                        case 1: goto L_0x0066;
                        case 2: goto L_0x0063;
                        case 3: goto L_0x0060;
                        case 4: goto L_0x005d;
                        case 5: goto L_0x005a;
                        case 6: goto L_0x0057;
                        default: goto L_0x0053;
                    }
                L_0x0053:
                    java.lang.String r0 = ""
                L_0x0055:
                    r7 = r0
                    goto L_0x006c
                L_0x0057:
                    java.lang.String r0 = "error_ad_not_ready"
                    goto L_0x0055
                L_0x005a:
                    java.lang.String r0 = "error_timeout"
                    goto L_0x0055
                L_0x005d:
                    java.lang.String r0 = "error_slow_connection"
                    goto L_0x0055
                L_0x0060:
                    java.lang.String r0 = "error_no_market"
                    goto L_0x0055
                L_0x0063:
                    java.lang.String r0 = "error_no_ads"
                    goto L_0x0055
                L_0x0066:
                    java.lang.String r0 = "error_internal_error"
                    goto L_0x0055
                L_0x0069:
                    java.lang.String r0 = "error_connection_error"
                    goto L_0x0055
                L_0x006c:
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    java.lang.String r2 = r0.getTID()
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    java.lang.String r3 = r0.getVID()
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    java.lang.String r4 = r0.getAUID()
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    java.lang.String r5 = r0.getPlacementID()
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    java.lang.String r6 = r0.getSessionId()
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    int r0 = r0.type
                    if (r0 != r1) goto L_0x0095
                    java.lang.String r0 = "fullscreen"
                    goto L_0x0097
                L_0x0095:
                    java.lang.String r0 = "rewarded"
                L_0x0097:
                    r8 = r0
                    java.lang.String r9 = ""
                    java.lang.String r10 = ""
                    com.appnext.core.C4967f.m6822a((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10)
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    com.appnext.core.callbacks.OnAdError r0 = r0.userOnAdError
                    if (r0 == 0) goto L_0x00b0
                    com.appnext.ads.fullscreen.Video r0 = com.appnext.ads.fullscreen.Video.this
                    com.appnext.core.callbacks.OnAdError r0 = r0.userOnAdError
                    r0.adError(r12)
                L_0x00b0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.fullscreen.Video.C47203.adError(java.lang.String):void");
            }
        });
    }

    public void showAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C4967f.m6823a(this.context, "android.permission.INTERNET")) {
            int Z = C4967f.m6810Z(getConfig().get("min_internet_connection_video"));
            int Z2 = C4967f.m6810Z(C4967f.m6849o(this.context));
            if (Z2 == -1) {
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                }
            } else if (Z2 >= Z) {
                C4967f.m6822a(getTID(), getVID(), getAUID(), getPlacementID(), getSessionId(), C4708a.f4188c, this.type == 1 ? "fullscreen" : "rewarded", "", "");
                if (C4725b.m6279j().mo40621b((C4924Ad) this)) {
                    Intent intent = new Intent(this.context, FullscreenActivity.class);
                    intent.setFlags(268435456);
                    intent.putExtra("id", getPlacementID());
                    intent.putExtra("type", this.type);
                    currentAd = this;
                    this.context.startActivity(intent);
                    return;
                }
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AdsError.AD_NOT_READY);
                }
                C4725b.m6279j().mo41238a(this.context, (C4924Ad) this, getPlacementID(), (C4948d.C4955a) null);
                C4967f.m6822a(getTID(), getVID(), getAUID(), getPlacementID(), getSessionId(), C4708a.AD_NOT_READY, this.type == 1 ? "fullscreen" : "rewarded", "", "");
            } else if (getOnAdErrorCallback() != null) {
                getOnAdErrorCallback().adError(AppnextError.SLOW_CONNECTION);
            }
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    public void loadAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C4967f.m6823a(this.context, "android.permission.INTERNET")) {
            getConfig().mo41298a(this.context, (C4986p.C4988a) new C4986p.C4988a() {
                /* renamed from: b */
                public final void mo40595b(HashMap<String, Object> hashMap) {
                    Video.this.load();
                }

                public final void error(String str) {
                    Video.this.load();
                }
            });
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    /* access modifiers changed from: private */
    public void load() {
        C4725b.m6279j().mo41238a(this.context, (C4924Ad) this, getPlacementID(), (C4948d.C4955a) new C4948d.C4955a() {
            /* renamed from: a */
            public final <T> void mo40599a(T t) {
                AppnextAd b = C4725b.m6279j().mo40620b(Video.this.context, (C4924Ad) Video.this);
                if (Video.this.getOnAdLoadedCallback() == null) {
                    return;
                }
                if (b != null) {
                    Video.this.getOnAdLoadedCallback().adLoaded(b.getBannerID(), b.getCreativeType());
                } else if (Video.this.getOnAdErrorCallback() != null) {
                    Video.this.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
                }
            }

            public final void error(String str) {
                if (Video.this.getOnAdErrorCallback() != null) {
                    Video.this.getOnAdErrorCallback().adError(str);
                }
            }
        });
    }

    public void getECPM(final OnECPMLoaded onECPMLoaded) {
        if (onECPMLoaded != null) {
            C4725b.m6279j().mo41239a(this.context, (C4924Ad) this, getPlacementID(), (C4948d.C4955a) new C4948d.C4955a() {
                /* renamed from: a */
                public final <T> void mo40599a(T t) {
                    AppnextAd b = C4725b.m6279j().mo40620b(Video.this.context, (C4924Ad) Video.this);
                    if (b != null) {
                        onECPMLoaded.ecpm(new ECPM(b.getECPM(), b.getPPR(), b.getBannerID()));
                    } else {
                        onECPMLoaded.error(AppnextError.NO_ADS);
                    }
                }

                public final void error(String str) {
                    onECPMLoaded.error(str);
                }
            }, false);
            return;
        }
        throw new IllegalArgumentException("Callback cannot be null");
    }

    /* access modifiers changed from: protected */
    public C4986p getConfig() {
        int i = this.type;
        if (i == 2) {
            return C4745f.m6314q();
        }
        if (i == 1) {
            return C4727c.m6296m();
        }
        return null;
    }

    public boolean isAdLoaded() {
        if (getPlacementID().equals("")) {
            return false;
        }
        return C4725b.m6279j().mo40621b((C4924Ad) this);
    }

    public OnVideoEnded getOnVideoEndedCallback() {
        return this.videoEnded;
    }

    public void setOnVideoEndedCallback(OnVideoEnded onVideoEnded) {
        this.videoEnded = onVideoEnded;
    }

    public String getVideoLength() {
        return this.videoLength;
    }

    public void setVideoLength(String str) {
        if (str.equals("15") || str.equals("30")) {
            this.videoLength = str;
            return;
        }
        throw new IllegalArgumentException("Wrong video length");
    }

    public static void setCacheVideo(boolean z) {
        cacheVideo = z;
    }

    public static boolean getCacheVideo() {
        return cacheVideo;
    }

    public static void setStreamingMode(boolean z) {
        streamingEnable = z;
    }

    public static boolean isStreamingModeEnabled() {
        return streamingEnable;
    }

    public void setOnAdErrorCallback(OnAdError onAdError) {
        this.userOnAdError = onAdError;
    }

    public OnAdError getOnAdErrorCallback() {
        return super.getOnAdErrorCallback();
    }

    public int getRollCaptionTime() {
        return this.rollCaptionTime;
    }

    public void setRollCaptionTime(int i) {
        if (i == -1 || (i >= 3 && i <= 10)) {
            this.rollCaptionTime = i;
        }
    }

    public void setParams(String str, String str2) {
        getConfig().mo41307s(str, str2);
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public boolean isShowCta() {
        return this.showCta;
    }

    public void setShowCta(boolean z) {
        this.showCta = z;
    }

    public void setBackButtonCanClose(boolean z) {
        this.canClose = z;
    }

    public boolean isBackButtonCanClose() {
        return this.canClose;
    }

    /* access modifiers changed from: protected */
    public String getSessionId() {
        return super.getSessionId();
    }

    public void destroy() {
        super.destroy();
        this.videoEnded = null;
        try {
            C4967f.m6821a(new File(this.context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + this.rnd + "/"));
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            destroy();
            super.finalize();
        } catch (Throwable unused) {
            super.finalize();
        }
    }
}
