package com.startapp.android.publish.ads.p017a;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.splash.C0950g;
import com.startapp.android.publish.ads.video.C1009f;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adinformation.C1084b;
import com.startapp.android.publish.adsCommon.adinformation.C1093c;
import com.startapp.android.publish.cache.C1186a;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.inappbrowser.C1242a;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.ads.a.b */
/* compiled from: StartAppSDK */
public abstract class C0851b {

    /* renamed from: a */
    protected C1084b f403a = null;

    /* renamed from: b */
    protected AdPreferences.Placement f404b;

    /* renamed from: c */
    protected boolean f405c = false;

    /* renamed from: d */
    private Intent f406d;

    /* renamed from: e */
    private Activity f407e;

    /* renamed from: f */
    private BroadcastReceiver f408f = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            C0851b.this.mo13786p();
        }
    };

    /* renamed from: g */
    private String[] f409g;

    /* renamed from: h */
    private boolean[] f410h;

    /* renamed from: i */
    private boolean[] f411i = {true};

    /* renamed from: j */
    private String f412j;

    /* renamed from: k */
    private String[] f413k;

    /* renamed from: l */
    private String[] f414l;

    /* renamed from: m */
    private String[] f415m;

    /* renamed from: n */
    private C1040Ad f416n;

    /* renamed from: o */
    private String f417o;

    /* renamed from: p */
    private boolean f418p;

    /* renamed from: q */
    private C1093c f419q;

    /* renamed from: r */
    private String f420r;

    /* renamed from: s */
    private Long f421s;

    /* renamed from: t */
    private Boolean[] f422t = null;

    /* renamed from: a */
    public void mo13759a(Configuration configuration) {
    }

    /* renamed from: a */
    public boolean mo13767a(int i, KeyEvent keyEvent) {
        return false;
    }

    /* renamed from: b */
    public void mo13770b(Bundle bundle) {
    }

    /* renamed from: c */
    public void mo13773c(Bundle bundle) {
    }

    /* renamed from: r */
    public boolean mo13788r() {
        return false;
    }

    /* renamed from: t */
    public void mo13790t() {
    }

    /* renamed from: u */
    public abstract void mo13791u();

    /* renamed from: com.startapp.android.publish.ads.a.b$3 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C08543 {

        /* renamed from: a */
        static final /* synthetic */ int[] f425a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r0 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f425a = r0
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OFFER_WALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f425a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f425a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OVERLAY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f425a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f425a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_FULL_SCREEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f425a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_BROWSER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.p017a.C0851b.C08543.<clinit>():void");
        }
    }

    /* renamed from: a */
    public static C0851b m452a(Activity activity, Intent intent, AdPreferences.Placement placement) {
        C0851b bVar = null;
        switch (C08543.f425a[placement.ordinal()]) {
            case 1:
                if (C1061i.m1194a(128) || C1061i.m1194a(64)) {
                    bVar = new C0869e();
                    break;
                }
            case 2:
            case 3:
                if (!C1061i.m1194a(4) || !intent.getBooleanExtra("videoAd", false)) {
                    if (!intent.getBooleanExtra("mraidAd", false)) {
                        bVar = new C0870f();
                        break;
                    } else {
                        bVar = new C0863d();
                        break;
                    }
                } else {
                    bVar = new C1009f();
                    break;
                }
                break;
            case 4:
                if (C1061i.m1194a(8)) {
                    bVar = new C0950g();
                    break;
                }
                break;
            case 5:
            case 6:
                if (C1061i.m1194a(256)) {
                    Uri data = intent.getData();
                    if (data != null) {
                        bVar = new C1242a(data.toString());
                        break;
                    } else {
                        return null;
                    }
                }
                break;
            default:
                bVar = new C0850a();
                break;
        }
        bVar.m454a(intent);
        bVar.m453a(activity);
        bVar.m462c(intent.getStringExtra("position"));
        bVar.m461b(intent.getStringArrayExtra("tracking"));
        bVar.m463c(intent.getStringArrayExtra("trackingClickUrl"));
        bVar.m464d(intent.getStringArrayExtra("packageNames"));
        bVar.m459a(intent.getStringArrayExtra("closingUrl"));
        bVar.mo13765a(intent.getBooleanArrayExtra("smartRedirect"));
        bVar.mo13771b(intent.getBooleanArrayExtra("browserEnabled"));
        String stringExtra = intent.getStringExtra("htmlUuid");
        if (stringExtra != null) {
            if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                bVar.mo13763a(C1186a.m1756a().mo15067b(stringExtra));
            } else {
                bVar.mo13763a(C1186a.m1756a().mo15072c(stringExtra));
            }
        }
        bVar.mo14394a(intent.getBooleanExtra("isSplash", false));
        bVar.m455a((C1093c) intent.getSerializableExtra("adInfoOverride"));
        bVar.mo13806b(intent.getStringExtra("adTag"));
        bVar.m456a(placement);
        bVar.m459a(intent.getStringArrayExtra("closingUrl"));
        if (bVar.mo13774d() == null) {
            bVar.mo13765a(new boolean[]{true});
        }
        if (bVar.mo13775e() == null) {
            bVar.mo13771b(new boolean[]{true});
        }
        bVar.mo13762a((C1040Ad) intent.getSerializableExtra("ad"));
        long longExtra = intent.getLongExtra("delayImpressionSeconds", -1);
        if (longExtra != -1) {
            bVar.m457a(Long.valueOf(longExtra));
        }
        bVar.mo13764a((Boolean[]) intent.getSerializableExtra("sendRedirectHops"));
        C1270g.m2078a("GenericMode", 3, "Placement=[" + bVar.mo13781k() + "]");
        return bVar;
    }

    /* renamed from: a */
    private void m459a(String[] strArr) {
        this.f409g = strArr;
    }

    /* renamed from: a */
    private void m454a(Intent intent) {
        this.f406d = intent;
    }

    /* renamed from: a */
    private void m456a(AdPreferences.Placement placement) {
        this.f404b = placement;
    }

    /* renamed from: a */
    private void mo14394a(boolean z) {
        this.f418p = z;
    }

    /* renamed from: b */
    private void mo13806b(String str) {
        this.f420r = str;
    }

    /* renamed from: a */
    public Intent mo13758a() {
        return this.f406d;
    }

    /* renamed from: a */
    private void m453a(Activity activity) {
        this.f407e = activity;
    }

    /* renamed from: a */
    private void m455a(C1093c cVar) {
        this.f419q = cVar;
    }

    /* renamed from: b */
    public Activity mo13768b() {
        return this.f407e;
    }

    /* renamed from: c */
    public void mo13772c() {
        this.f405c = true;
    }

    /* renamed from: c */
    private void m462c(String str) {
        this.f412j = str;
    }

    /* renamed from: b */
    private void m461b(String[] strArr) {
        this.f413k = strArr;
    }

    /* renamed from: c */
    private void m463c(String[] strArr) {
        this.f414l = strArr;
    }

    /* renamed from: d */
    private void m464d(String[] strArr) {
        this.f415m = strArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13763a(String str) {
        this.f417o = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13765a(boolean[] zArr) {
        this.f410h = zArr;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public boolean[] mo13774d() {
        return this.f410h;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo13771b(boolean[] zArr) {
        this.f411i = zArr;
    }

    /* renamed from: e */
    public boolean[] mo13775e() {
        return this.f411i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13766a(int i) {
        boolean[] zArr = this.f411i;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo13776f() {
        return this.f417o;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public String mo13777g() {
        return this.f412j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String[] mo13778h() {
        return this.f413k;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String[] mo13779i() {
        return this.f414l;
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public String[] mo13780j() {
        return this.f415m;
    }

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public AdPreferences.Placement mo13781k() {
        return this.f404b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public String[] mo13782l() {
        return this.f409g;
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public C1093c mo13783m() {
        return this.f419q;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public String mo13784n() {
        return this.f420r;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13761a(RelativeLayout relativeLayout) {
        C1084b bVar = new C1084b(mo13768b(), C1084b.C1092b.LARGE, mo13781k(), mo13783m());
        this.f403a = bVar;
        bVar.mo14713a(relativeLayout);
    }

    /* renamed from: o */
    public Long mo13785o() {
        return this.f421s;
    }

    /* renamed from: a */
    private void m457a(Long l) {
        this.f421s = l;
    }

    /* renamed from: b */
    public Boolean mo13769b(int i) {
        Boolean[] boolArr = this.f422t;
        if (boolArr == null || i < 0 || i >= boolArr.length) {
            return null;
        }
        return boolArr[i];
    }

    /* renamed from: a */
    public void mo13764a(Boolean[] boolArr) {
        this.f422t = boolArr;
    }

    /* renamed from: p */
    public void mo13786p() {
        mo13768b().runOnUiThread(new Runnable() {
            public void run() {
                C0851b.this.mo13768b().finish();
            }
        });
    }

    /* renamed from: q */
    public void mo13787q() {
        C1275b.m2102a((Context) mo13768b()).mo15481a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        C1275b.m2102a((Context) mo13768b()).mo15480a(this.f408f, new IntentFilter("com.startapp.android.CloseAdActivity"));
    }

    /* renamed from: s */
    public void mo13789s() {
        mo13786p();
    }

    /* renamed from: v */
    public void mo13792v() {
        if (this.f408f != null) {
            C1275b.m2102a((Context) mo13768b()).mo15479a(this.f408f);
        }
        this.f408f = null;
    }

    /* renamed from: w */
    public C1040Ad mo13793w() {
        return this.f416n;
    }

    /* renamed from: a */
    public void mo13762a(C1040Ad ad) {
        this.f416n = ad;
    }
}
