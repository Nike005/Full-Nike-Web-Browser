package com.startapp.android.publish.p016a;

import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1113d;
import com.startapp.android.publish.adsCommon.C1148h;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p029b.C1102c;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.common.model.GetAdResponse;
import com.startapp.common.C1275b;
import com.startapp.common.C1301e;
import com.startapp.common.p043a.C1270g;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.startapp.android.publish.a.a */
/* compiled from: StartAppSDK */
public abstract class C0847a extends C1113d {

    /* renamed from: g */
    private int f400g = 0;

    /* renamed from: h */
    private Set<String> f401h = new HashSet();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo13753a(C1040Ad ad);

    public C0847a(Context context, C1040Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        super(context, ad, adPreferences, adEventListener, placement);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public Object mo13756e() {
        GetAdRequest a = mo13957a();
        if (a == null) {
            return null;
        }
        if (this.f401h.size() == 0) {
            this.f401h.add(this.f1133a.getPackageName());
        }
        boolean z = false;
        if (this.f400g > 0) {
            a.setEngInclude(false);
        }
        a.setPackageExclude(this.f401h);
        if (this.f400g == 0) {
            z = true;
        }
        a.setEngInclude(z);
        try {
            return (GetAdResponse) C1167a.m1618a(this.f1133a, AdsConstants.m1126a(AdsConstants.AdApiType.JSON, mo14818f()), a, (Map<String, String>) null, GetAdResponse.class);
        } catch (C1301e e) {
            C1270g.m2079a("AppPresence", 6, "Unable to handle GetAdsSetService command!!!!", e);
            this.f1138f = e.getMessage();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13755a(Object obj) {
        GetAdResponse getAdResponse = (GetAdResponse) obj;
        boolean z = false;
        if (obj == null) {
            this.f1138f = "Empty Response";
            C1270g.m2078a("AppPresence", 6, "Error Empty Response");
            return false;
        } else if (!getAdResponse.isValidResponse()) {
            this.f1138f = getAdResponse.getErrorMessage();
            C1270g.m2078a("AppPresence", 6, "Error msg = [" + this.f1138f + "]");
            return false;
        } else {
            C1148h hVar = (C1148h) this.f1134b;
            List<AdDetails> a = C1102c.m1360a(this.f1133a, getAdResponse.getAdsDetails(), this.f400g, this.f401h);
            hVar.mo14936a(a);
            hVar.setAdInfoOverride(getAdResponse.getAdInfoOverride());
            if (getAdResponse.getAdsDetails() != null && getAdResponse.getAdsDetails().size() > 0) {
                z = true;
            }
            if (!z) {
                this.f1138f = "Empty Response";
            } else if (a.size() == 0 && this.f400g == 0) {
                C1270g.m2078a("AppPresence", 3, "Packages exists - another request");
                return m446b();
            }
            return z;
        }
    }

    /* renamed from: b */
    private boolean m446b() {
        this.f400g++;
        return mo14817d().booleanValue();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.f1134b.hashCode());
        intent.putExtra("adResult", bool);
        C1275b.m2102a(this.f1133a).mo15481a(intent);
        if (bool.booleanValue()) {
            mo13753a((C1040Ad) (C1148h) this.f1134b);
            this.f1136d.onReceiveAd(this.f1134b);
        }
    }
}
