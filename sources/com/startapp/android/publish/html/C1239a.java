package com.startapp.android.publish.html;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1113d;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p029b.C1099a;
import com.startapp.android.publish.adsCommon.p029b.C1100b;
import com.startapp.android.publish.adsCommon.p029b.C1102c;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p034g.p038d.C1146a;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.common.C1275b;
import com.startapp.common.C1301e;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1271h;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.startapp.android.publish.html.a */
/* compiled from: StartAppSDK */
public abstract class C1239a extends C1113d {

    /* renamed from: g */
    protected Set<String> f1421g = new HashSet();

    /* renamed from: h */
    protected GetAdRequest f1422h;

    /* renamed from: i */
    private Set<String> f1423i = new HashSet();

    /* renamed from: j */
    private List<C1099a> f1424j = new ArrayList();

    /* renamed from: k */
    private int f1425k = 0;

    /* renamed from: l */
    private boolean f1426l;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo14259a(GetAdRequest getAdRequest) {
        return getAdRequest != null;
    }

    public C1239a(Context context, C1040Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement, boolean z) {
        super(context, ad, adPreferences, adEventListener, placement);
        this.f1426l = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public Object mo13756e() {
        GetAdRequest a = mo13957a();
        this.f1422h = a;
        if (mo14259a(a)) {
            if (this.f1423i.size() == 0) {
                this.f1423i.add(this.f1133a.getPackageName());
            }
            this.f1422h.setPackageExclude(this.f1423i);
            this.f1422h.setCampaignExclude(this.f1421g);
            if (this.f1425k > 0) {
                this.f1422h.setEngInclude(false);
                if (MetaData.getInstance().getSimpleTokenConfig().mo15265b(this.f1133a)) {
                    C1168l.m1636b(this.f1133a);
                }
            }
            try {
                return C1167a.m1619a(this.f1133a, AdsConstants.m1126a(AdsConstants.AdApiType.HTML, mo14818f()), this.f1422h, (Map<String, String>) null);
            } catch (C1301e e) {
                if (!MetaData.getInstance().getInvalidNetworkCodesInfoEvents().contains(Integer.valueOf(e.mo15512a()))) {
                    C1132f.m1527a(this.f1133a, C1130d.EXCEPTION, "BaseHtmlService.sendCommand - network failure", e.getMessage(), "");
                }
                C1270g.m2079a("BaseHtmlService", 6, "Unable to handle GetHtmlAdService command!!!!", e);
                this.f1138f = e.getMessage();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13755a(Object obj) {
        C1270g.m2078a("BaseHtmlService", 4, "Handle Html Response");
        try {
            this.f1424j = new ArrayList();
            String a = ((C1271h.C1272a) obj).mo15466a();
            if (TextUtils.isEmpty(a)) {
                if (this.f1138f == null) {
                    if (this.f1422h == null || !this.f1422h.isAdTypeVideo()) {
                        this.f1138f = "Empty Ad";
                    } else {
                        this.f1138f = "Video isn't available";
                    }
                }
                return false;
            }
            List<C1099a> a2 = C1102c.m1362a(a, this.f1425k);
            if (C1098b.m1303a().mo14751E() ? C1102c.m1359a(this.f1133a, a2, this.f1425k, this.f1423i, this.f1424j).booleanValue() : false) {
                return mo15407g();
            }
            ((C1118e) this.f1134b).mo14833a(a2);
            return mo15406a(a);
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        C1270g.m2078a("BaseHtmlService", 4, "Html onPostExecute, result=[" + bool + "]");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14261b(Boolean bool) {
        super.mo14261b(bool);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15405a(boolean z) {
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.f1134b.hashCode());
        intent.putExtra("adResult", z);
        C1275b.m2102a(this.f1133a).mo15481a(intent);
        if (!z || this.f1134b == null) {
            C1270g.m2078a("BaseHtmlService", 6, "Html onPostExecute failed error=[" + this.f1138f + "]");
        } else if (this.f1426l) {
            C1061i.m1185a(this.f1133a, ((C1118e) this.f1134b).mo14843f(), (C1061i.C1066a) new C1061i.C1066a() {
                /* renamed from: a */
                public void mo14645a() {
                    C1239a.this.f1136d.onReceiveAd(C1239a.this.f1134b);
                }

                /* renamed from: a */
                public void mo14646a(String str) {
                    C1239a.this.f1134b.setErrorMessage(str);
                    C1239a.this.f1136d.onFailedToReceiveAd(C1239a.this.f1134b);
                }
            });
        } else if (z) {
            this.f1136d.onReceiveAd(this.f1134b);
        } else {
            this.f1136d.onFailedToReceiveAd(this.f1134b);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo15406a(String str) {
        C1118e eVar = (C1118e) this.f1134b;
        if (C1146a.m1564b(str)) {
            str = C1146a.m1561a(str);
        }
        eVar.mo14369b(str);
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public boolean mo15407g() {
        C1270g.m2078a("BaseHtmlService", 3, "At least one package is present. sending another request to AdPlatform");
        this.f1425k++;
        new C1100b(this.f1133a, this.f1424j).mo14795a();
        return mo14817d().booleanValue();
    }
}
