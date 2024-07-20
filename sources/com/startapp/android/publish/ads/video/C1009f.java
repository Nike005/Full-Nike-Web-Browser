package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.p088b.p089a.p090a.p091a.p093b.C5109a;
import com.p088b.p089a.p090a.p091a.p093b.p094a.C5110a;
import com.startapp.android.publish.ads.p017a.C0855c;
import com.startapp.android.publish.ads.video.VideoAdDetails;
import com.startapp.android.publish.ads.video.p022a.C0966b;
import com.startapp.android.publish.ads.video.p023b.C0971b;
import com.startapp.android.publish.ads.video.p023b.C0975c;
import com.startapp.android.publish.ads.video.p024c.p025a.C0989a;
import com.startapp.android.publish.ads.video.tracking.AbsoluteTrackingLink;
import com.startapp.android.publish.ads.video.tracking.ActionTrackingLink;
import com.startapp.android.publish.ads.video.tracking.FractionTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoClickedTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoPausedTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoProgressTrackingParams;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingParams;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1182n;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1131e;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.html.JsInterface;
import com.startapp.android.publish.omsdk.C1247a;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.ads.video.f */
/* compiled from: StartAppSDK */
public class C1009f extends C0855c {

    /* renamed from: A */
    protected int f860A;

    /* renamed from: B */
    protected String f861B = null;

    /* renamed from: C */
    protected Handler f862C = new Handler();

    /* renamed from: D */
    protected Handler f863D = new Handler();

    /* renamed from: E */
    protected Handler f864E = new Handler();

    /* renamed from: F */
    protected Handler f865F = new Handler();

    /* renamed from: G */
    private RelativeLayout f866G;

    /* renamed from: H */
    private RelativeLayout f867H;

    /* renamed from: I */
    private int f868I = 0;

    /* renamed from: J */
    private int f869J = 0;

    /* renamed from: K */
    private boolean f870K = false;

    /* renamed from: L */
    private HashMap<Integer, Boolean> f871L = new HashMap<>();

    /* renamed from: M */
    private HashMap<Integer, Boolean> f872M = new HashMap<>();

    /* renamed from: N */
    private int f873N = 1;

    /* renamed from: O */
    private boolean f874O = false;

    /* renamed from: P */
    private boolean f875P = false;

    /* renamed from: Q */
    private Map<Integer, List<FractionTrackingLink>> f876Q = new HashMap();

    /* renamed from: R */
    private Map<Integer, List<AbsoluteTrackingLink>> f877R = new HashMap();

    /* renamed from: S */
    private long f878S;

    /* renamed from: T */
    private VideoClickedTrackingParams.ClickOrigin f879T;

    /* renamed from: U */
    private long f880U;

    /* renamed from: V */
    private long f881V;
    /* access modifiers changed from: private */

    /* renamed from: W */
    public C5110a f882W;

    /* renamed from: i */
    protected C0975c f883i;

    /* renamed from: j */
    protected VideoView f884j;

    /* renamed from: k */
    protected ProgressBar f885k;

    /* renamed from: l */
    protected boolean f886l = false;

    /* renamed from: m */
    protected int f887m = 0;

    /* renamed from: n */
    protected int f888n = 0;

    /* renamed from: o */
    protected boolean f889o;

    /* renamed from: p */
    protected boolean f890p = false;

    /* renamed from: q */
    protected boolean f891q = false;

    /* renamed from: r */
    protected boolean f892r = false;

    /* renamed from: s */
    protected boolean f893s = false;

    /* renamed from: t */
    protected boolean f894t = false;

    /* renamed from: u */
    protected int f895u = 0;

    /* renamed from: v */
    protected boolean f896v = false;

    /* renamed from: w */
    protected boolean f897w = false;

    /* renamed from: x */
    protected boolean f898x = false;

    /* renamed from: y */
    protected boolean f899y = false;

    /* renamed from: z */
    protected int f900z = 0;

    /* renamed from: com.startapp.android.publish.ads.video.f$a */
    /* compiled from: StartAppSDK */
    private enum C1032a {
        PLAYER,
        POST_ROLL
    }

    /* renamed from: com.startapp.android.publish.ads.video.f$b */
    /* compiled from: StartAppSDK */
    private enum C1033b {
        ON,
        OFF
    }

    /* renamed from: com.startapp.android.publish.ads.video.f$c */
    /* compiled from: StartAppSDK */
    private enum C1034c {
        COMPLETE,
        CLICKED,
        SKIPPED
    }

    /* access modifiers changed from: protected */
    /* renamed from: z */
    public void mo13810z() {
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        super.mo13760a(bundle);
        try {
            this.f878S = System.currentTimeMillis();
            this.f860A = 100 / C1098b.m1303a().mo14754H().mo15032j();
            m1031ac();
            m1053ay();
            m1030ab();
            if (bundle != null && bundle.containsKey("currentPosition")) {
                this.f888n = bundle.getInt("currentPosition");
                this.f868I = bundle.getInt("latestPosition");
                this.f871L = (HashMap) bundle.getSerializable("fractionProgressImpressionsSent");
                this.f872M = (HashMap) bundle.getSerializable("absoluteProgressImpressionsSent");
                this.f886l = bundle.getBoolean("isMuted");
                this.f889o = bundle.getBoolean("shouldSetBg");
                this.f887m = bundle.getInt("replayNum");
                this.f870K = bundle.getBoolean("videoCompletedBroadcastSent", false);
                this.f873N = bundle.getInt("pauseNum");
            }
        } catch (Exception unused) {
            m1049au();
            Context applicationContext = mo13768b().getApplicationContext();
            C1130d dVar = C1130d.EXCEPTION;
            C1132f.m1527a(applicationContext, dVar, "VideoMode.onCreate", "packages : " + mo13780j(), "");
            mo13786p();
        }
    }

    /* renamed from: a */
    public void mo13802a(WebView webView) {
        super.mo13802a(webView);
        webView.setBackgroundColor(33554431);
        C1261c.m2028a(webView, (Paint) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: A */
    public void mo13796A() {
        this.f890p = true;
        if (this.f891q && mo14389X()) {
            mo14372G();
        } else if (mo14384S()) {
            m1019a((View) this.f426d);
        }
        if (mo14390Y()) {
            mo14376K();
        }
        if (mo14384S()) {
            m1033ae();
        }
        VideoAdDetails U = mo14386U();
        if (MetaData.getInstance().isOmsdkEnabled() && this.f427e == null && U != null && U.getAdVerifications() != null && U.getAdVerifications().getAdVerification() != null) {
            this.f427e = C1247a.m1970a(this.f426d.getContext(), mo14386U().getAdVerifications());
            if (this.f427e != null) {
                this.f882W = C5110a.m6985a(this.f427e);
                View a = this.f403a.mo14712a();
                if (a != null) {
                    this.f427e.mo41831b(a);
                }
                this.f427e.mo41831b(this.f426d);
                this.f427e.mo41831b(this.f867H);
                this.f427e.mo41829a(this.f884j);
                this.f427e.mo41828a();
                C5109a.m6983a(this.f427e).mo41817a();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: G */
    public void mo14372G() {
        if (this.f892r) {
            m1019a((View) this.f884j);
            if (!mo14384S()) {
                m1034af();
            }
        }
    }

    /* renamed from: u */
    public void mo13791u() {
        super.mo13791u();
        if (!mo13768b().isFinishing()) {
            m1029aa();
        }
    }

    /* renamed from: aa */
    private void m1029aa() {
        if (this.f884j == null) {
            m1016a(mo13768b().getApplicationContext());
        }
        if (this.f883i == null) {
            this.f883i = new C0971b(this.f884j);
        }
        this.f891q = false;
        this.f866G.setBackgroundColor(-16777216);
        mo14373H();
        if (mo14384S()) {
            this.f403a.mo14712a().setVisibility(0);
            this.f884j.setVisibility(4);
        } else {
            int i = this.f888n;
            if (i != 0) {
                this.f883i.mo14272a(i);
                m1058b(VideoPausedTrackingParams.PauseOrigin.EXTERNAL);
            }
        }
        this.f883i.mo14269a((C0975c.C0981f) new C0975c.C0981f() {
            /* renamed from: a */
            public void mo14287a() {
                C1009f.this.f897w = true;
                if (C1009f.this.f890p && C1009f.this.f891q) {
                    C1009f.this.mo14372G();
                }
                if (C1009f.this.mo14390Y()) {
                    C1009f.this.mo14376K();
                }
            }
        });
        this.f883i.mo14267a((C0975c.C0979d) new C0975c.C0979d() {
            /* renamed from: a */
            public void mo14285a() {
                if (!C1009f.this.mo14384S()) {
                    C1009f.this.mo14393a(C1034c.COMPLETE);
                }
                C1009f.this.f883i.mo14275c();
            }
        });
        C101514 r0 = new C0975c.C0978c() {
            /* renamed from: a */
            public void mo14284a(int i) {
                if (C1009f.this.f896v && C1009f.this.f897w && C1009f.this.f883i != null && C1009f.this.f883i.mo14277e() != 0) {
                    C1270g.m2078a("VideoMode", 3, "buffered percent = [" + i + "]");
                    C1009f.this.f895u = i;
                    int d = (C1009f.this.f883i.mo14276d() * 100) / C1009f.this.f883i.mo14277e();
                    if (C1009f.this.mo14379N()) {
                        if (!C1009f.this.f898x && C1009f.this.mo14390Y()) {
                            C1009f.this.mo14376K();
                        } else if (C1009f.this.f895u == 100 || C1009f.this.f895u - d > C1098b.m1303a().mo14754H().mo15032j()) {
                            C1009f.this.mo14374I();
                        }
                    } else if (C1009f.this.f895u < 100 && C1009f.this.f895u - d <= C1098b.m1303a().mo14754H().mo15033k()) {
                        C1009f.this.mo14375J();
                    }
                }
            }
        };
        this.f883i.mo14268a((C0975c.C0980e) new C0975c.C0980e() {
            /* renamed from: a */
            public boolean mo14286a(C0975c.C0982g gVar) {
                C1009f.this.f897w = false;
                if (!C1009f.this.f896v || C1009f.this.f900z > C1009f.this.f860A || gVar.mo14290c() <= 0 || !gVar.mo14289b().equals(C0971b.C0973a.MEDIA_ERROR_IO.toString())) {
                    C1009f.this.mo14392a(gVar);
                } else {
                    C1009f.this.f900z++;
                    C1009f.this.mo14377L();
                    C1009f.this.f883i.mo14270a(C1009f.this.mo14386U().getLocalVideoPath());
                    C1009f.this.f883i.mo14272a(gVar.mo14290c());
                }
                return true;
            }
        });
        this.f883i.mo14265a((C0975c.C0977b) new C0975c.C0977b() {
        });
        this.f883i.mo14266a((C0975c.C0978c) r0);
        this.f883i.mo14264a((C0975c.C0976a) new C0975c.C0976a() {
        });
        C1261c.m2025a((View) this.f884j, (C1261c.C1264a) new C1261c.C1264a() {
            /* renamed from: a */
            public void mo14402a(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                C1009f.this.f891q = true;
                if (C1009f.this.f890p && C1009f.this.mo14389X()) {
                    C1009f.this.mo14372G();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: H */
    public void mo14373H() {
        boolean i = C1098b.m1303a().mo14754H().mo15031i();
        String localVideoPath = mo14386U().getLocalVideoPath();
        if (localVideoPath != null) {
            this.f883i.mo14270a(localVideoPath);
            if (i && C0984c.m898a().mo14294b(localVideoPath)) {
                C1270g.m2078a("VideoMode", 3, "progressive video from local file");
                this.f896v = true;
                this.f899y = true;
                this.f895u = C1098b.m1303a().mo14754H().mo15033k();
            }
        } else if (i) {
            C1270g.m2078a("VideoMode", 3, "progressive video from url");
            String videoUrl = mo14386U().getVideoUrl();
            C0984c.m898a().mo14293a(videoUrl);
            this.f883i.mo14270a(videoUrl);
            this.f896v = true;
            mo14377L();
        } else {
            mo14393a(C1034c.SKIPPED);
        }
        if (this.f861B == null) {
            this.f861B = this.f896v ? InternalAvidAdSessionContext.AVID_API_LEVEL : "1";
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: I */
    public void mo14374I() {
        C1270g.m2078a("VideoMode", 3, "progressive video resumed, buffered percent: [" + Integer.toString(this.f895u) + "]");
        this.f883i.mo14271a();
        mo14378M();
    }

    /* access modifiers changed from: protected */
    /* renamed from: J */
    public void mo14375J() {
        C1270g.m2078a("VideoMode", 3, "progressive video paused, buffered percent: [" + Integer.toString(this.f895u) + "]");
        this.f883i.mo14274b();
        mo14377L();
    }

    /* access modifiers changed from: protected */
    /* renamed from: K */
    public void mo14376K() {
        this.f898x = true;
        m1035ag();
        if (mo14384S()) {
            this.f883i.mo14274b();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (C1009f.this.f883i != null) {
                    C1009f.this.f883i.mo14271a();
                    if (C1009f.this.f882W != null) {
                        C1009f.this.f882W.mo41820a((float) C1009f.this.f883i.mo14277e(), C1009f.this.f886l ? 0.0f : 1.0f);
                    }
                    C1009f.this.f892r = true;
                    C1009f.this.mo14378M();
                    new Handler().post(new Runnable() {
                        public void run() {
                            C1009f.this.mo14372G();
                        }
                    });
                }
            }
        }, m1032ad());
        if (this.f888n == 0) {
            this.f862C.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (C1009f.this.f883i == null) {
                            return;
                        }
                        if (C1009f.this.f883i.mo14276d() > 0) {
                            C1009f.this.mo14398f(0);
                            C1009f.this.mo14399g(0);
                            if (C1009f.this.f887m == 0) {
                                C1009f.this.mo14391Z();
                                C1275b.m2102a((Context) C1009f.this.mo13768b()).mo15481a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                            }
                        } else if (!C1009f.this.f893s) {
                            C1009f.this.f862C.postDelayed(this, 100);
                        }
                    } catch (Exception e) {
                        C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.startVideoPlayback", e.getMessage()), C1009f.this.m1052ax());
                        C1009f.this.mo13786p();
                    }
                }
            }, 100);
        }
        m1041am();
        m1044ap();
        m1036ah();
        m1037ai();
        this.f403a.mo14712a().setVisibility(4);
        mo14388W();
    }

    /* access modifiers changed from: protected */
    /* renamed from: L */
    public void mo14377L() {
        if (!mo14379N()) {
            this.f894t = false;
            this.f865F.postDelayed(new Runnable() {
                public void run() {
                    try {
                        C1009f.this.f885k.setVisibility(0);
                        if (C1009f.this.f882W != null) {
                            C1009f.this.f882W.mo41825f();
                        }
                        C1009f.this.f865F.postDelayed(new Runnable() {
                            public void run() {
                                C1270g.m2078a("VideoMode", 5, "Buffering timeout reached");
                                try {
                                    C1009f.this.mo14378M();
                                    C1009f.this.f894t = true;
                                    C1009f.this.mo14392a(new C0975c.C0982g(C0975c.C0983h.BUFFERING_TIMEOUT, "Buffering timeout reached", C1009f.this.f888n));
                                } catch (Exception e) {
                                    C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.startBufferingIndicator", e.getMessage()), "");
                                }
                            }
                        }, C1098b.m1303a().mo14754H().mo15029g());
                    } catch (Exception e) {
                        C1009f.this.mo14378M();
                        C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.startBufferingIndicator", e.getMessage()), C1009f.this.m1052ax());
                    }
                }
            }, C1098b.m1303a().mo14754H().mo15028f());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: M */
    public void mo14378M() {
        this.f865F.removeCallbacksAndMessages((Object) null);
        if (mo14379N()) {
            this.f885k.setVisibility(8);
            C5110a aVar = this.f882W;
            if (aVar != null) {
                aVar.mo41826g();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: N */
    public boolean mo14379N() {
        ProgressBar progressBar = this.f885k;
        return progressBar != null && progressBar.isShown();
    }

    /* renamed from: ab */
    private void m1030ab() {
        this.f886l = mo14386U().isVideoMuted() || C1098b.m1303a().mo14754H().mo15035m().equals("muted");
    }

    /* renamed from: ac */
    private void m1031ac() {
        if (!mo13777g().equals("back")) {
            return;
        }
        if (C1098b.m1303a().mo14754H().mo15023a().equals(C1182n.C1183a.BOTH)) {
            this.f874O = true;
            this.f875P = true;
        } else if (C1098b.m1303a().mo14754H().mo15023a().equals(C1182n.C1183a.SKIP)) {
            this.f874O = true;
            this.f875P = false;
        } else if (C1098b.m1303a().mo14754H().mo15023a().equals(C1182n.C1183a.CLOSE)) {
            this.f874O = false;
            this.f875P = true;
        } else if (C1098b.m1303a().mo14754H().mo15023a().equals(C1182n.C1183a.DISABLED)) {
            this.f874O = false;
            this.f875P = false;
        } else {
            this.f874O = false;
            this.f875P = false;
        }
    }

    /* renamed from: ad */
    private long m1032ad() {
        long currentTimeMillis = System.currentTimeMillis() - this.f878S;
        if (this.f888n == 0 && this.f887m == 0 && currentTimeMillis < 500) {
            return Math.max(200, 500 - currentTimeMillis);
        }
        return 0;
    }

    /* renamed from: ae */
    private void m1033ae() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.f883i != null);
        mo13803a("videoApi.setReplayEnabled", objArr);
        mo13803a("videoApi.setMode", C1032a.POST_ROLL + "_" + mo14386U().getPostRollType());
        mo13803a("videoApi.setCloseable", true);
    }

    /* renamed from: af */
    private void m1034af() {
        mo13803a("videoApi.setClickableVideo", Boolean.valueOf(mo14386U().isClickable()));
        mo13803a("videoApi.setMode", C1032a.PLAYER.toString());
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(mo14386U().isCloseable() || this.f875P);
        mo13803a("videoApi.setCloseable", objArr);
        mo13803a("videoApi.setSkippable", Boolean.valueOf(m1051aw()));
    }

    /* renamed from: ag */
    private void m1035ag() {
        mo13803a("videoApi.setVideoDuration", Integer.valueOf(this.f883i.mo14277e() / 1000));
        mo14381P();
        m1038aj();
        mo13803a("videoApi.setVideoCurrentPosition", Integer.valueOf(this.f888n / 1000));
    }

    /* access modifiers changed from: protected */
    /* renamed from: O */
    public void mo14380O() {
        mo13803a("videoApi.setVideoCurrentPosition", 0);
        mo13803a("videoApi.setSkipTimer", 0);
    }

    /* renamed from: a */
    private void m1019a(View view) {
        mo13803a("videoApi.setVideoFrame", Integer.valueOf(C1060h.m1171b(mo13768b(), view.getLeft())), Integer.valueOf(C1060h.m1171b(mo13768b(), view.getTop())), Integer.valueOf(C1060h.m1171b(mo13768b(), view.getWidth())), Integer.valueOf(C1060h.m1171b(mo13768b(), view.getHeight())));
    }

    /* renamed from: ah */
    private void m1036ah() {
        this.f863D.post(new Runnable() {
            public void run() {
                int P = C1009f.this.mo14381P();
                if (P >= 1000) {
                    C1009f.this.f863D.postDelayed(this, C1009f.this.mo14395c(P));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: P */
    public int mo14381P() {
        int ak = m1039ak();
        int i = ak / 1000;
        if (i > 0 && ak % 1000 < 100) {
            i--;
        }
        mo13803a("videoApi.setVideoRemainingTimer", Integer.valueOf(i));
        return ak;
    }

    /* renamed from: ai */
    private void m1037ai() {
        m1038aj();
        this.f863D.post(new Runnable() {

            /* renamed from: b */
            private boolean f918b;

            /* renamed from: c */
            private final int f919c = C1009f.this.mo14397e(C1098b.m1303a().mo14754H().mo15026d());

            public void run() {
                try {
                    int d = C1009f.this.mo14396d(C1009f.this.f883i.mo14276d() + 50);
                    if (d >= 0 && !this.f918b) {
                        if (d != 0) {
                            if (C1009f.this.f888n < C1009f.this.mo14386U().getSkippableAfter() * 1000) {
                                C1009f.this.mo13803a("videoApi.setSkipTimer", Integer.valueOf(d));
                            }
                        }
                        this.f918b = true;
                        C1009f.this.mo13803a("videoApi.setSkipTimer", 0);
                    }
                    if (C1009f.this.f896v && C1009f.this.f883i.mo14276d() >= this.f919c) {
                        C1009f.this.mo14385T();
                    }
                    int d2 = (C1009f.this.f883i.mo14276d() + 50) / 1000;
                    C1009f.this.mo13803a("videoApi.setVideoCurrentPosition", Integer.valueOf(d2));
                    if (d2 < C1009f.this.f883i.mo14277e() / 1000) {
                        C1009f.this.f863D.postDelayed(this, C1009f.this.mo14382Q());
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    /* renamed from: aj */
    private void m1038aj() {
        mo13803a("videoApi.setSkipTimer", Integer.valueOf(mo14396d(this.f888n + 50)));
    }

    /* renamed from: ak */
    private int m1039ak() {
        if (this.f883i.mo14276d() != this.f883i.mo14277e() || mo14384S()) {
            return this.f883i.mo14277e() - this.f883i.mo14276d();
        }
        return this.f883i.mo14277e();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public long mo14395c(int i) {
        int i2 = 1000;
        int i3 = i % 1000;
        if (i3 != 0) {
            i2 = i3;
        }
        return (long) (i2 + 50);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public long mo14382Q() {
        return (long) (1000 - (this.f883i.mo14276d() % 1000));
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public int mo14396d(int i) {
        int skippableAfter;
        if (!this.f874O && this.f887m <= 0 && (skippableAfter = (mo14386U().getSkippableAfter() * 1000) - i) > 0) {
            return (skippableAfter / 1000) + 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14393a(C1034c cVar) {
        C5110a aVar;
        C5110a aVar2;
        if (cVar == C1034c.COMPLETE && (aVar2 = this.f882W) != null) {
            aVar2.mo41823d();
        }
        if (cVar == C1034c.SKIPPED && (aVar = this.f882W) != null) {
            aVar.mo41827h();
        }
        if (cVar == C1034c.SKIPPED || cVar == C1034c.CLICKED) {
            this.f862C.removeCallbacksAndMessages((Object) null);
            this.f864E.removeCallbacksAndMessages((Object) null);
            C0975c cVar2 = this.f883i;
            if (cVar2 != null) {
                this.f868I = cVar2.mo14276d();
                this.f883i.mo14274b();
            }
        } else {
            this.f868I = this.f869J;
            mo14385T();
        }
        this.f863D.removeCallbacksAndMessages((Object) null);
        this.f871L.clear();
        this.f872M.clear();
        if (cVar == C1034c.CLICKED) {
            m1040al();
            return;
        }
        if (mo14386U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            m1033ae();
            this.f403a.mo14712a().setVisibility(0);
        }
        if (mo14386U().getPostRollType() == VideoAdDetails.PostRollType.IMAGE) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (!C1009f.this.f894t) {
                        C1009f.this.f884j.setVisibility(4);
                    }
                }
            }, 1000);
        } else if (mo14386U().getPostRollType() == VideoAdDetails.PostRollType.NONE) {
            mo13786p();
        }
        m1040al();
        if (mo14386U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            m1054az();
        }
    }

    /* renamed from: al */
    private void m1040al() {
        this.f888n = -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public void mo14383R() {
        this.f888n = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: S */
    public boolean mo14384S() {
        return this.f888n == -1;
    }

    /* renamed from: am */
    private void m1041am() {
        this.f869J = this.f883i.mo14277e();
        m1042an();
        m1043ao();
    }

    /* renamed from: an */
    private void m1042an() {
        for (Integer intValue : this.f876Q.keySet()) {
            final int intValue2 = intValue.intValue();
            m1018a(mo14397e(intValue2), this.f862C, (Runnable) new Runnable() {
                public void run() {
                    try {
                        C1009f.this.mo14398f(intValue2);
                    } catch (Exception e) {
                        C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.scheduleFractionProgressEvents", e.getMessage()), C1009f.this.m1052ax());
                    }
                }
            });
        }
    }

    /* renamed from: ao */
    private void m1043ao() {
        for (Integer intValue : this.f877R.keySet()) {
            final int intValue2 = intValue.intValue();
            m1018a(intValue2, this.f862C, (Runnable) new Runnable() {
                public void run() {
                    try {
                        C1009f.this.mo14399g(intValue2);
                    } catch (Exception e) {
                        C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.scheduleAbsoluteProgressEvents", e.getMessage()), C1009f.this.m1052ax());
                    }
                }
            });
        }
    }

    /* renamed from: ap */
    private void m1044ap() {
        if (!this.f896v) {
            m1018a(mo14397e(C1098b.m1303a().mo14754H().mo15026d()), this.f864E, (Runnable) new Runnable() {
                public void run() {
                    try {
                        C1009f.this.mo14385T();
                    } catch (Exception e) {
                        C1132f.m1529a(C1009f.this.mo13768b().getApplicationContext(), new C1131e(C1130d.EXCEPTION, "VideoMode.scheduleVideoListenerEvents", e.getMessage()), C1009f.this.m1052ax());
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private void m1018a(int i, Handler handler, Runnable runnable) {
        int i2 = this.f888n;
        if (i2 < i) {
            handler.postDelayed(runnable, (long) (i - i2));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public int mo14397e(int i) {
        return (this.f869J * i) / 100;
    }

    /* access modifiers changed from: protected */
    /* renamed from: T */
    public void mo14385T() {
        if (m1045aq() && !this.f870K && this.f887m == 0) {
            this.f870K = true;
            C1270g.m2078a("VideoMode", 3, "Sending rewarded video completion broadcast.");
            if (C1275b.m2102a((Context) mo13768b()).mo15481a(new Intent("com.startapp.android.OnVideoCompleted"))) {
                C1270g.m2078a("VideoMode", 3, "Rewarded video completion broadcast sent successfully.");
            }
            m1025aA();
        }
    }

    /* renamed from: aq */
    private boolean m1045aq() {
        return mo13793w().getType() == C1040Ad.AdType.REWARDED_VIDEO;
    }

    /* renamed from: b */
    public void mo13770b(Bundle bundle) {
        super.mo13770b(bundle);
        bundle.putInt("currentPosition", this.f888n);
        bundle.putInt("latestPosition", this.f868I);
        bundle.putSerializable("fractionProgressImpressionsSent", this.f871L);
        bundle.putSerializable("absoluteProgressImpressionsSent", this.f872M);
        bundle.putBoolean("isMuted", this.f886l);
        bundle.putBoolean("shouldSetBg", this.f889o);
        bundle.putInt("replayNum", this.f887m);
        bundle.putInt("pauseNum", this.f873N);
        bundle.putBoolean("videoCompletedBroadcastSent", this.f870K);
    }

    /* access modifiers changed from: protected */
    /* renamed from: U */
    public VideoAdDetails mo14386U() {
        return ((C1008e) mo13793w()).mo14370d();
    }

    /* renamed from: s */
    public void mo13789s() {
        if (!mo14384S() && !mo13768b().isFinishing() && !this.f875P && !this.f874O) {
            m1023a(VideoPausedTrackingParams.PauseOrigin.EXTERNAL);
        }
        m1050av();
        this.f862C.removeCallbacksAndMessages((Object) null);
        this.f863D.removeCallbacksAndMessages((Object) null);
        this.f864E.removeCallbacksAndMessages((Object) null);
        mo14378M();
        this.f889o = true;
        super.mo13789s();
    }

    /* renamed from: p */
    public void mo13786p() {
        super.mo13786p();
        if (this.f899y) {
            C0984c.m898a().mo14295c(mo14386U().getLocalVideoPath());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: y */
    public JsInterface mo13809y() {
        return new VideoJsInterface(mo13768b(), this.f429g, this.f429g, m1048at(), m1047as(), m1046ar(), new C1117b(mo13784n()), mo13766a(0));
    }

    /* renamed from: ar */
    private Runnable m1046ar() {
        return new Runnable() {
            public void run() {
                C1009f fVar = C1009f.this;
                fVar.f886l = !fVar.f886l;
                C1009f.this.mo14388W();
                C1009f fVar2 = C1009f.this;
                fVar2.mo14394a(fVar2.f886l);
            }
        };
    }

    /* renamed from: as */
    private Runnable m1047as() {
        return new Runnable() {
            public void run() {
                C1009f.this.mo14387V();
            }
        };
    }

    /* access modifiers changed from: protected */
    /* renamed from: V */
    public void mo14387V() {
        if (mo14379N()) {
            mo14378M();
        }
        mo14393a(C1034c.SKIPPED);
        m1026aB();
    }

    /* renamed from: at */
    private Runnable m1048at() {
        return new Runnable() {
            public void run() {
                C1009f.this.f887m++;
                C1009f.this.f884j.setVisibility(0);
                C1009f.this.f889o = false;
                C1009f.this.mo14383R();
                C1009f.this.mo14380O();
                C1009f.this.mo14373H();
            }
        };
    }

    /* renamed from: a */
    private RelativeLayout m1016a(Context context) {
        this.f880U = System.currentTimeMillis();
        this.f867H = (RelativeLayout) mo13768b().findViewById(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        VideoView videoView = new VideoView(context);
        this.f884j = videoView;
        videoView.setId(100);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        ProgressBar progressBar = new ProgressBar(context, (AttributeSet) null, 16843399);
        this.f885k = progressBar;
        progressBar.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(14);
        layoutParams3.addRule(15);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.f866G = relativeLayout;
        relativeLayout.setId(1475346436);
        mo13768b().setContentView(this.f866G);
        this.f866G.addView(this.f884j, layoutParams2);
        this.f866G.addView(this.f867H, layoutParams);
        this.f866G.addView(this.f885k, layoutParams3);
        if (AdsConstants.m1125a().booleanValue()) {
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(12);
            layoutParams4.addRule(14);
            this.f866G.addView(m1055b(context), layoutParams4);
        }
        this.f403a.mo14712a().setVisibility(4);
        return this.f866G;
    }

    /* renamed from: b */
    private View m1055b(Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("url=" + mo14386U().getVideoUrl());
        TextView textView = new TextView(context);
        textView.setBackgroundColor(-16777216);
        C1261c.m2023a((View) textView, 0.5f);
        textView.setTextColor(-7829368);
        textView.setSingleLine(false);
        textView.setText(sb.toString());
        return textView;
    }

    /* access modifiers changed from: protected */
    /* renamed from: W */
    public void mo14388W() {
        String str;
        C0975c cVar = this.f883i;
        if (cVar != null) {
            try {
                if (this.f886l) {
                    cVar.mo14273a(true);
                } else {
                    cVar.mo14273a(false);
                }
            } catch (IllegalStateException unused) {
            }
        }
        Object[] objArr = new Object[1];
        if (this.f886l) {
            str = C1033b.OFF.toString();
        } else {
            str = C1033b.ON.toString();
        }
        objArr[0] = str;
        mo13803a("videoApi.setSound", objArr);
    }

    /* renamed from: a */
    private void m1023a(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        C0975c cVar = this.f883i;
        if (cVar != null) {
            int d = cVar.mo14276d();
            this.f888n = d;
            this.f868I = d;
            this.f883i.mo14274b();
            C5110a aVar = this.f882W;
            if (aVar != null) {
                aVar.mo41824e();
            }
        }
        m1060c(pauseOrigin);
    }

    /* renamed from: b */
    private void m1058b(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        m1061d(pauseOrigin);
        this.f873N++;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14392a(C0975c.C0982g gVar) {
        C0989a aVar;
        C1132f.m1527a(mo13768b(), C1130d.VIDEO_MEDIA_PLAYER_ERROR, gVar.mo14288a().toString(), gVar.mo14289b(), m1052ax());
        int i = C101413.f905a[gVar.mo14288a().ordinal()];
        if (i == 1) {
            aVar = C0989a.GeneralLinearError;
        } else if (i == 2) {
            aVar = C0989a.TimeoutMediaFileURI;
        } else if (i != 3) {
            aVar = C0989a.UndefinedError;
        } else {
            aVar = C0989a.MediaFileDisplayError;
        }
        m1020a(aVar);
        if ((this.f896v ? this.f883i.mo14276d() : this.f888n) == 0) {
            C1103c.m1382a((Context) mo13768b(), mo13778h(), mo13784n(), this.f887m, AdDisplayListener.NotDisplayedReason.VIDEO_ERROR.toString());
            if (!this.f896v) {
                C1038h.m1119b(mo13768b());
            } else if (!gVar.mo14288a().equals(C0975c.C0983h.BUFFERING_TIMEOUT)) {
                C1038h.m1119b(mo13768b());
            }
        }
        if ((!m1045aq() || this.f870K) && mo14386U().getPostRollType() != VideoAdDetails.PostRollType.NONE) {
            mo14393a(C1034c.SKIPPED);
            return;
        }
        m1049au();
        mo13786p();
    }

    /* renamed from: com.startapp.android.publish.ads.video.f$13 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C101413 {

        /* renamed from: a */
        static final /* synthetic */ int[] f905a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.startapp.android.publish.ads.video.b.c$h[] r0 = com.startapp.android.publish.ads.video.p023b.C0975c.C0983h.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f905a = r0
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.p023b.C0975c.C0983h.SERVER_DIED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f905a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.p023b.C0975c.C0983h.BUFFERING_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f905a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.p023b.C0975c.C0983h.PLAYER_CREATION     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f905a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.video.b.c$h r1 = com.startapp.android.publish.ads.video.p023b.C0975c.C0983h.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.C1009f.C101413.<clinit>():void");
        }
    }

    /* renamed from: au */
    private void m1049au() {
        Intent intent = new Intent("com.startapp.android.ShowFailedDisplayBroadcastListener");
        intent.putExtra("showFailedReason", AdDisplayListener.NotDisplayedReason.VIDEO_ERROR);
        C1275b.m2102a((Context) mo13768b()).mo15481a(intent);
        this.f893s = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: X */
    public boolean mo14389X() {
        C0975c cVar = this.f883i;
        return cVar != null && cVar.mo14278f();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Y */
    public boolean mo14390Y() {
        if (!this.f896v) {
            if (!mo14389X() || !this.f890p) {
                return false;
            }
            return true;
        } else if (this.f895u < C1098b.m1303a().mo14754H().mo15033k() || !mo14389X() || !this.f890p) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: av */
    private void m1050av() {
        C1270g.m2078a("VideoMode", 3, "Releasing video player");
        C0975c cVar = this.f883i;
        if (cVar != null) {
            cVar.mo14279g();
            this.f883i = null;
        }
    }

    /* renamed from: r */
    public boolean mo13788r() {
        if (mo14384S()) {
            mo13797B();
            return false;
        }
        int d = mo14396d(this.f883i.mo14276d() + 50);
        if (m1051aw() && d == 0) {
            mo14387V();
            return true;
        } else if (!mo14386U().isCloseable() && !this.f875P) {
            return true;
        } else {
            mo13797B();
            return false;
        }
    }

    /* renamed from: aw */
    private boolean m1051aw() {
        return this.f887m > 0 || mo14386U().isSkippable() || this.f874O;
    }

    /* renamed from: h */
    private int m1062h(int i) {
        int i2 = this.f869J;
        if (i2 > 0) {
            return (i * 100) / i2;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: ax */
    public String m1052ax() {
        try {
            String[] h = mo13778h();
            if (h != null && h.length > 0) {
                return C1103c.m1404e(h[0]);
            }
            C1270g.m2078a("VideoMode", 5, "dParam is not available.");
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: q */
    public void mo13787q() {
        if (!this.f893s) {
            super.mo13787q();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13804a(String str, boolean z) {
        if (!TextUtils.isEmpty(mo14386U().getClickUrl())) {
            str = mo14386U().getClickUrl();
            z = true;
        }
        this.f879T = mo14384S() ? VideoClickedTrackingParams.ClickOrigin.POSTROLL : VideoClickedTrackingParams.ClickOrigin.VIDEO;
        C1270g.m2078a("VideoMode", 3, "Video clicked from: " + this.f879T);
        if (this.f879T == VideoClickedTrackingParams.ClickOrigin.VIDEO) {
            mo14393a(C1034c.CLICKED);
        }
        m1022a(this.f879T);
        return super.mo13804a(str, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: B */
    public void mo13797B() {
        if (this.f893s) {
            C1270g.m2078a("VideoMode", 3, "Not sending close events due to media player error");
        } else if (mo14384S() || this.f884j == null) {
            m1027aC();
            super.mo13797B();
        } else {
            m1028aD();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: E */
    public String mo13800E() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f881V = currentTimeMillis;
        double d = (double) (currentTimeMillis - this.f880U);
        Double.isNaN(d);
        return String.valueOf(d / 1000.0d);
    }

    /* renamed from: ay */
    private void m1053ay() {
        FractionTrackingLink[] fractionTrackingUrls = mo14386U().getVideoTrackingDetails().getFractionTrackingUrls();
        if (fractionTrackingUrls != null) {
            for (FractionTrackingLink fractionTrackingLink : fractionTrackingUrls) {
                List list = this.f876Q.get(Integer.valueOf(fractionTrackingLink.getFraction()));
                if (list == null) {
                    list = new ArrayList();
                    this.f876Q.put(Integer.valueOf(fractionTrackingLink.getFraction()), list);
                }
                list.add(fractionTrackingLink);
            }
        }
        AbsoluteTrackingLink[] absoluteTrackingUrls = mo14386U().getVideoTrackingDetails().getAbsoluteTrackingUrls();
        if (absoluteTrackingUrls != null) {
            for (AbsoluteTrackingLink absoluteTrackingLink : absoluteTrackingUrls) {
                List list2 = this.f877R.get(Integer.valueOf(absoluteTrackingLink.getVideoOffsetMillis()));
                if (list2 == null) {
                    list2 = new ArrayList();
                    this.f877R.put(Integer.valueOf(absoluteTrackingLink.getVideoOffsetMillis()), list2);
                }
                list2.add(absoluteTrackingLink);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public C1117b mo13799D() {
        return new VideoTrackingParams(mo13784n(), 0, this.f887m, this.f861B);
    }

    /* access modifiers changed from: protected */
    /* renamed from: F */
    public long mo13801F() {
        if (mo13785o() != null) {
            return TimeUnit.SECONDS.toMillis(mo13785o().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABVideoImpressionDelayInSeconds());
    }

    /* access modifiers changed from: protected */
    /* renamed from: Z */
    public void mo14391Z() {
        super.mo13810z();
        m1024a(mo14386U().getVideoTrackingDetails().getImpressionUrls(), new VideoTrackingParams(mo13784n(), 0, this.f887m, this.f861B), 0, "impression");
        m1024a(mo14386U().getVideoTrackingDetails().getCreativeViewUrls(), new VideoTrackingParams(mo13784n(), 0, this.f887m, this.f861B), 0, "creativeView");
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo14398f(int i) {
        if (this.f871L.get(Integer.valueOf(i)) == null) {
            if (this.f876Q.containsKey(Integer.valueOf(i))) {
                List list = this.f876Q.get(Integer.valueOf(i));
                C1270g.m2078a("VideoMode", 3, "Sending fraction progress event with fraction: " + i + ", total: " + list.size());
                m1024a((VideoTrackingLink[]) list.toArray(new FractionTrackingLink[list.size()]), new VideoProgressTrackingParams(mo13784n(), i, this.f887m, this.f861B), mo14397e(i), "fraction");
                C5110a aVar = this.f882W;
                if (aVar != null) {
                    if (i == 25) {
                        aVar.mo41818a();
                    } else if (i == 50) {
                        aVar.mo41821b();
                    } else if (i == 75) {
                        aVar.mo41822c();
                    }
                }
            }
            this.f871L.put(Integer.valueOf(i), true);
            return;
        }
        C1270g.m2078a("VideoMode", 3, "Fraction progress event already sent for fraction: " + i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public void mo14399g(int i) {
        if (this.f872M.get(Integer.valueOf(i)) == null) {
            if (this.f877R.containsKey(Integer.valueOf(i))) {
                List list = this.f877R.get(Integer.valueOf(i));
                C1270g.m2078a("VideoMode", 3, "Sending absolute progress event with video progress: " + i + ", total: " + list.size());
                m1024a((VideoTrackingLink[]) list.toArray(new AbsoluteTrackingLink[list.size()]), new VideoProgressTrackingParams(mo13784n(), i, this.f887m, this.f861B), i, "absolute");
            }
            this.f872M.put(Integer.valueOf(i), true);
            return;
        }
        C1270g.m2078a("VideoMode", 3, "Absolute progress event already sent for video progress: " + i);
    }

    /* renamed from: az */
    private void m1054az() {
        C1270g.m2078a("VideoMode", 3, "Sending postroll impression event");
        m1024a(mo14386U().getVideoTrackingDetails().getVideoPostRollImpressionUrls(), new VideoTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f861B), this.f868I, "postrollImression");
    }

    /* renamed from: aA */
    private void m1025aA() {
        C1270g.m2078a("VideoMode", 3, "Sending rewarded event");
        m1024a(mo14386U().getVideoTrackingDetails().getVideoRewardedUrls(), new VideoTrackingParams(mo13784n(), C1098b.m1303a().mo14754H().mo15026d(), this.f887m, this.f861B), mo14397e(C1098b.m1303a().mo14754H().mo15026d()), "rewarded");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14394a(boolean z) {
        ActionTrackingLink[] actionTrackingLinkArr;
        StringBuilder sb = new StringBuilder();
        sb.append("Sending sound ");
        sb.append(z ? "muted " : "unmuted ");
        sb.append("event");
        C1270g.m2078a("VideoMode", 3, sb.toString());
        if (z) {
            actionTrackingLinkArr = mo14386U().getVideoTrackingDetails().getSoundMuteUrls();
        } else {
            actionTrackingLinkArr = mo14386U().getVideoTrackingDetails().getSoundUnmuteUrls();
        }
        m1024a(actionTrackingLinkArr, new VideoTrackingParams(mo13784n(), m1062h(this.f883i.mo14276d()), this.f887m, this.f861B), this.f883i.mo14276d(), "sound");
        C5110a aVar = this.f882W;
        if (aVar != null) {
            aVar.mo41819a(z ? 0.0f : 1.0f);
        }
    }

    /* renamed from: aB */
    private void m1026aB() {
        C1270g.m2078a("VideoMode", 3, "Sending skip event");
        m1024a(mo14386U().getVideoTrackingDetails().getVideoSkippedUrls(), new VideoTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f861B), this.f868I, "skipped");
    }

    /* renamed from: c */
    private void m1060c(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        C1270g.m2078a("VideoMode", 3, "Sending pause event with origin: " + pauseOrigin);
        m1024a(mo14386U().getVideoTrackingDetails().getVideoPausedUrls(), new VideoPausedTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f873N, pauseOrigin, this.f861B), this.f868I, "paused");
    }

    /* renamed from: d */
    private void m1061d(VideoPausedTrackingParams.PauseOrigin pauseOrigin) {
        C1270g.m2078a("VideoMode", 3, "Sending resume event with pause origin: " + pauseOrigin);
        m1024a(mo14386U().getVideoTrackingDetails().getVideoResumedUrls(), new VideoPausedTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f873N, pauseOrigin, this.f861B), this.f868I, "resumed");
    }

    /* renamed from: aC */
    private void m1027aC() {
        C1270g.m2078a("VideoMode", 3, "Sending postroll closed event");
        m1024a(mo14386U().getVideoTrackingDetails().getVideoPostRollClosedUrls(), new VideoTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f861B), this.f868I, "postrollClosed");
    }

    /* renamed from: aD */
    private void m1028aD() {
        C1270g.m2078a("VideoMode", 3, "Sending video closed event");
        m1024a(mo14386U().getVideoTrackingDetails().getVideoClosedUrls(), new VideoTrackingParams(mo13784n(), m1062h(this.f883i.mo14276d()), this.f887m, this.f861B), this.f883i.mo14276d(), "closed");
    }

    /* renamed from: a */
    private void m1022a(VideoClickedTrackingParams.ClickOrigin clickOrigin) {
        C1270g.m2078a("VideoMode", 3, "Sending video clicked event with origin: " + clickOrigin.toString());
        m1024a(mo14386U().getVideoTrackingDetails().getVideoClickTrackingUrls(), new VideoClickedTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, clickOrigin, this.f861B), this.f868I, "clicked");
    }

    /* renamed from: a */
    private void m1024a(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, int i, String str) {
        C1038h.m1117a((Context) mo13768b(), new C0966b(videoTrackingLinkArr, videoTrackingParams, mo14386U().getVideoUrl(), i).mo14258a(str).mo14256a());
    }

    /* renamed from: a */
    private void m1020a(C0989a aVar) {
        C1270g.m2078a("VideoMode", 3, "Sending internal video event");
        C1038h.m1117a((Context) mo13768b(), new C0966b(mo14386U().getVideoTrackingDetails().getInlineErrorTrackingUrls(), new VideoTrackingParams(mo13784n(), m1062h(this.f868I), this.f887m, this.f861B), mo14386U().getVideoUrl(), this.f868I).mo14257a(aVar).mo14258a("error").mo14256a());
    }
}
