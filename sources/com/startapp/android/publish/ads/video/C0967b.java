package com.startapp.android.publish.ads.video;

import android.content.Context;
import com.mopub.common.AdType;
import com.startapp.android.publish.ads.video.C0984c;
import com.startapp.android.publish.ads.video.C1035g;
import com.startapp.android.publish.ads.video.C1038h;
import com.startapp.android.publish.ads.video.p024c.p025a.C0989a;
import com.startapp.android.publish.ads.video.p024c.p025a.C0996c;
import com.startapp.android.publish.ads.video.p024c.p025a.C0999d;
import com.startapp.android.publish.ads.video.p024c.p027b.C1003b;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.cache.C1186a;
import com.startapp.android.publish.cache.C1195c;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.html.C1239a;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1271h;
import com.startapp.common.p046c.C1295b;

/* renamed from: com.startapp.android.publish.ads.video.b */
/* compiled from: StartAppSDK */
public class C0967b extends C1239a {

    /* renamed from: i */
    private long f685i = System.currentTimeMillis();

    /* renamed from: j */
    private volatile C1195c f686j;

    /* renamed from: k */
    private C0996c f687k;

    /* renamed from: l */
    private int f688l = 0;

    public C0967b(Context context, C1040Ad ad, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OVERLAY, true);
        C0996c cVar;
        if (C1098b.m1303a().mo14754H().mo15040r() == 0) {
            cVar = new C0996c(context);
        } else {
            cVar = new C0999d(context, C1098b.m1303a().mo14754H().mo15041s());
        }
        this.f687k = cVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13755a(Object obj) {
        C1271h.C1272a aVar = (C1271h.C1272a) obj;
        String str = null;
        if (aVar == null || !aVar.mo15468b().toLowerCase().contains(AdType.STATIC_NATIVE)) {
            if (aVar != null) {
                str = aVar.mo15466a();
            }
            if (C1098b.m1303a().mo14754H().mo15030h() && m844b(str)) {
                m843b(false);
            }
            return super.mo13755a(obj);
        }
        if (C1098b.m1303a().mo14754H().mo15030h() && !this.f1422h.hasCampaignExclude()) {
            m843b(true);
        }
        try {
            VASTJson vASTJson = (VASTJson) C1295b.m2179a(aVar.mo15466a(), VASTJson.class);
            if (vASTJson == null || vASTJson.getVastTag() == null) {
                return m839a("no VAST wrapper in json", (Throwable) null, true);
            }
            C1003b bVar = new C1003b(C1098b.m1303a().mo14754H().mo15036n(), C1098b.m1303a().mo14754H().mo15037o());
            C0989a a = bVar.mo14362a(this.f1133a, vASTJson.getVastTag(), this.f687k);
            ((C1008e) this.f1134b).mo14368a(bVar.mo14363a(), this.f1134b.getType() != C1040Ad.AdType.REWARDED_VIDEO);
            if (vASTJson.getTtlSec() != null) {
                ((C1008e) this.f1134b).mo14838d(vASTJson.getTtlSec());
            }
            if (a == C0989a.ErrorNone) {
                m838a(C0989a.SAProcessSuccess);
                aVar.mo15467a(vASTJson.getAdmTag());
                aVar.mo15469b("text/html");
                return super.mo13755a((Object) aVar);
            }
            m838a(a);
            if (vASTJson.getCampaignId() != null) {
                this.f1421g.add(vASTJson.getCampaignId());
            }
            this.f688l++;
            ((C1008e) this.f1134b).mo14371e();
            if (System.currentTimeMillis() - this.f685i >= ((long) C1098b.m1303a().mo14754H().mo15038p())) {
                return m839a("VAST retry timeout", (Throwable) null, false);
            }
            if (this.f688l > C1098b.m1303a().mo14754H().mo15039q()) {
                return m839a("VAST too many excludes", (Throwable) null, false);
            }
            return mo14817d().booleanValue();
        } catch (Exception e) {
            return m839a("VAST json parsing", e, true);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(final Boolean bool) {
        super.mo13754a(bool);
        if (!bool.booleanValue() || !m845h()) {
            mo15405a(bool.booleanValue());
            return;
        }
        if (C1098b.m1303a().mo14754H().mo15031i()) {
            super.mo14261b(bool);
        }
        mo14260b().setVideoMuted(this.f1135c.isVideoMuted());
        C1004d.m1000a().mo14365a(this.f1133a.getApplicationContext(), mo14260b().getVideoUrl(), new C1035g.C1037a() {
            /* renamed from: a */
            public void mo14262a(String str) {
                if (str != null) {
                    if (!str.equals("downloadInterrupted")) {
                        C0967b.super.mo14261b(bool);
                        C0967b.this.mo14260b().setLocalVideoPath(str);
                    }
                    C0967b.this.mo15405a(bool.booleanValue());
                    return;
                }
                C0967b.this.mo15405a(false);
                C0967b.this.f1136d.onFailedToReceiveAd(C0967b.this.f1134b);
                C0967b.this.m838a(C0989a.FileNotFound);
            }
        }, new C0984c.C0987a() {
            /* renamed from: a */
            public void mo14263a(String str) {
                C0967b.this.mo14260b().setLocalVideoPath(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo14259a(GetAdRequest getAdRequest) {
        C1038h.C1039a a;
        if (!super.mo14259a(getAdRequest)) {
            return false;
        }
        if (!getAdRequest.isAdTypeVideo() || (a = C1038h.m1114a(this.f1133a)) == C1038h.C1039a.ELIGIBLE) {
            return true;
        }
        this.f1138f = a.mo14417a();
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        return mo14815b((GetAdRequest) new C0964a());
    }

    /* renamed from: h */
    private boolean m845h() {
        return mo14260b() != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14261b(Boolean bool) {
        if (!m845h()) {
            super.mo14261b(bool);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public VideoAdDetails mo14260b() {
        return ((C1008e) this.f1134b).mo14370d();
    }

    /* renamed from: b */
    private void m843b(boolean z) {
        AdPreferences adPreferences;
        if ((this.f1134b.getType() != C1040Ad.AdType.REWARDED_VIDEO && this.f1134b.getType() != C1040Ad.AdType.VIDEO) || z) {
            if (this.f1135c == null) {
                adPreferences = new AdPreferences();
            } else {
                adPreferences = new AdPreferences(this.f1135c);
            }
            AdPreferences adPreferences2 = adPreferences;
            adPreferences2.setType((this.f1134b.getType() == C1040Ad.AdType.REWARDED_VIDEO || this.f1134b.getType() == C1040Ad.AdType.VIDEO) ? C1040Ad.AdType.VIDEO_NO_VAST : C1040Ad.AdType.NON_VIDEO);
            C1195c a = C1186a.m1756a().mo15055a(this.f1133a, (StartAppAd) null, this.f1137e, adPreferences2, (AdEventListener) null);
            if (z) {
                this.f686j = a;
            }
        }
    }

    /* renamed from: b */
    private boolean m844b(String str) {
        return C1061i.m1179a(str, "@videoJson@", "@videoJson@") != null;
    }

    /* JADX WARNING: type inference failed for: r2v9, types: [com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[]] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m838a(com.startapp.android.publish.ads.video.p024c.p025a.C0989a r10) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.startapp.android.publish.ads.video.VideoAdDetails r2 = r9.mo14260b()     // Catch:{ Exception -> 0x00bb }
            if (r2 == 0) goto L_0x001f
            com.startapp.android.publish.ads.video.VideoAdDetails r2 = r9.mo14260b()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingDetails r2 = r2.getVideoTrackingDetails()     // Catch:{ Exception -> 0x00bb }
            if (r2 == 0) goto L_0x001f
            com.startapp.android.publish.ads.video.VideoAdDetails r1 = r9.mo14260b()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingDetails r1 = r1.getVideoTrackingDetails()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.tracking.ActionTrackingLink[] r1 = r1.getInlineErrorTrackingUrls()     // Catch:{ Exception -> 0x00bb }
        L_0x001f:
            if (r1 == 0) goto L_0x00c9
            int r2 = r1.length     // Catch:{ Exception -> 0x00bb }
            if (r2 <= 0) goto L_0x00c9
            com.startapp.android.publish.ads.video.c.a.a r2 = com.startapp.android.publish.ads.video.p024c.p025a.C0989a.SAShowBeforeVast     // Catch:{ Exception -> 0x00bb }
            r3 = 0
            if (r10 == r2) goto L_0x002d
            com.startapp.android.publish.ads.video.c.a.a r2 = com.startapp.android.publish.ads.video.p024c.p025a.C0989a.SAProcessSuccess     // Catch:{ Exception -> 0x00bb }
            if (r10 != r2) goto L_0x0093
        L_0x002d:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x0085 }
            r2.<init>()     // Catch:{ Exception -> 0x0085 }
            java.net.URL r4 = new java.net.URL     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.common.metaData.MetaData r5 = com.startapp.android.publish.common.metaData.MetaData.getInstance()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = r5.getAdPlatformHost()     // Catch:{ Exception -> 0x0085 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = r4.getHost()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r5 = "\\."
            java.lang.String[] r4 = r4.split(r5)     // Catch:{ Exception -> 0x0085 }
            r5 = 1
            r4 = r4[r5]     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ Exception -> 0x0085 }
            int r5 = r1.length     // Catch:{ Exception -> 0x0085 }
            r6 = 0
        L_0x0052:
            if (r6 >= r5) goto L_0x0070
            r7 = r1[r6]     // Catch:{ Exception -> 0x0085 }
            java.lang.String r8 = r7.getTrackingUrl()     // Catch:{ Exception -> 0x0085 }
            if (r8 == 0) goto L_0x006d
            java.lang.String r8 = r7.getTrackingUrl()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r8 = r8.toLowerCase()     // Catch:{ Exception -> 0x0085 }
            boolean r8 = r8.contains(r4)     // Catch:{ Exception -> 0x0085 }
            if (r8 == 0) goto L_0x006d
            r2.add(r7)     // Catch:{ Exception -> 0x0085 }
        L_0x006d:
            int r6 = r6 + 1
            goto L_0x0052
        L_0x0070:
            int r4 = r2.size()     // Catch:{ Exception -> 0x0085 }
            if (r4 <= 0) goto L_0x0084
            int r4 = r2.size()     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[] r4 = new com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[r4]     // Catch:{ Exception -> 0x0085 }
            java.lang.Object[] r2 = r2.toArray(r4)     // Catch:{ Exception -> 0x0085 }
            com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[] r2 = (com.startapp.android.publish.ads.video.tracking.VideoTrackingLink[]) r2     // Catch:{ Exception -> 0x0085 }
            r1 = r2
            goto L_0x0093
        L_0x0084:
            return
        L_0x0085:
            r2 = move-exception
            android.content.Context r4 = r9.f1133a     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.adsCommon.f.d r5 = com.startapp.android.publish.adsCommon.p033f.C1130d.EXCEPTION     // Catch:{ Exception -> 0x00bb }
            java.lang.String r6 = "GetVideoEnabledService.sendVideoErrorEvent filter sa links"
            java.lang.String r2 = r2.getMessage()     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.adsCommon.p033f.C1132f.m1527a(r4, r5, r6, r2, r0)     // Catch:{ Exception -> 0x00bb }
        L_0x0093:
            com.startapp.android.publish.ads.video.tracking.VideoTrackingParams r2 = new com.startapp.android.publish.ads.video.tracking.VideoTrackingParams     // Catch:{ Exception -> 0x00bb }
            java.lang.String r4 = "1"
            r2.<init>(r0, r3, r3, r4)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.b r4 = new com.startapp.android.publish.ads.video.a.b     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.VideoAdDetails r5 = r9.mo14260b()     // Catch:{ Exception -> 0x00bb }
            java.lang.String r5 = r5.getVideoUrl()     // Catch:{ Exception -> 0x00bb }
            r4.<init>(r1, r2, r5, r3)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r1 = "error"
            com.startapp.android.publish.ads.video.a.b r1 = r4.mo14258a((java.lang.String) r1)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.b r10 = r1.mo14257a((com.startapp.android.publish.ads.video.p024c.p025a.C0989a) r10)     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.a.a r10 = r10.mo14256a()     // Catch:{ Exception -> 0x00bb }
            android.content.Context r1 = r9.f1133a     // Catch:{ Exception -> 0x00bb }
            com.startapp.android.publish.ads.video.C1038h.m1117a((android.content.Context) r1, (com.startapp.android.publish.ads.video.p022a.C0965a) r10)     // Catch:{ Exception -> 0x00bb }
            goto L_0x00c9
        L_0x00bb:
            r10 = move-exception
            android.content.Context r1 = r9.f1133a
            com.startapp.android.publish.adsCommon.f.d r2 = com.startapp.android.publish.adsCommon.p033f.C1130d.EXCEPTION
            java.lang.String r10 = r10.getMessage()
            java.lang.String r3 = "GetVideoEnabledService.sendVideoErrorEvent"
            com.startapp.android.publish.adsCommon.p033f.C1132f.m1527a(r1, r2, r3, r10, r0)
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.video.C0967b.m838a(com.startapp.android.publish.ads.video.c.a.a):void");
    }

    /* renamed from: a */
    private boolean m839a(String str, Throwable th, boolean z) {
        C1270g.m2079a("GetVideoEnabledService", 6, str, th);
        if (z) {
            C1132f.m1527a(this.f1133a, C1130d.EXCEPTION, str, th != null ? th.getMessage() : "", "");
        }
        C1136g a = C1186a.m1756a().mo15053a(this.f686j);
        if (a instanceof C1118e) {
            C1271h.C1272a aVar = new C1271h.C1272a();
            aVar.mo15469b("text/html");
            aVar.mo15467a(((C1118e) a).mo14843f());
            return super.mo13755a((Object) aVar);
        }
        this.f1134b.setErrorMessage(this.f1138f);
        return false;
    }
}
