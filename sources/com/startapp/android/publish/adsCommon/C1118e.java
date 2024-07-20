package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.p088b.p089a.p090a.p091a.C5108b;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.adsCommon.p029b.C1099a;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p034g.p038d.C1146a;
import com.startapp.android.publish.cache.C1186a;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.omsdk.C1248b;
import com.startapp.common.p043a.C1270g;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.startapp.android.publish.adsCommon.e */
/* compiled from: StartAppSDK */
public abstract class C1118e extends C1040Ad {

    /* renamed from: a */
    protected static String f1142a = null;
    private static final long serialVersionUID = 1;
    private String adId = null;
    private List<C1099a> apps;
    private String[] closingUrl = {""};
    private Long delayImpressionInSeconds;
    private int height;
    private String htmlUuid = "";
    public boolean[] inAppBrowserEnabled = {true};
    private boolean isMraidAd = false;
    private int orientation = 0;
    private String[] packageNames = {""};
    private Boolean[] sendRedirectHops = null;
    public boolean[] smartRedirect = {false};
    private String[] trackingClickUrls = {""};
    public String[] trackingUrls = {""};
    private int width;

    /* renamed from: f */
    public String mo14843f() {
        return C1186a.m1756a().mo15067b(this.htmlUuid);
    }

    /* renamed from: g */
    public String mo14844g() {
        return this.htmlUuid;
    }

    /* renamed from: b */
    public void mo14834b(int i) {
        this.width = i;
    }

    /* renamed from: h */
    public int mo14845h() {
        return this.width;
    }

    /* renamed from: c */
    public void mo14836c(int i) {
        this.height = i;
    }

    /* renamed from: c */
    public void mo14837c(String str) {
        this.closingUrl = str.split(",");
    }

    /* renamed from: i */
    public String[] mo14846i() {
        return this.closingUrl;
    }

    /* renamed from: j */
    public int mo14847j() {
        return this.height;
    }

    /* renamed from: a */
    public void mo14831a(int i, int i2) {
        mo14834b(i);
        mo14836c(i2);
    }

    public C1118e(Context context, AdPreferences.Placement placement) {
        super(context, placement);
        if (f1142a == null) {
            mo13829a();
        }
    }

    /* renamed from: a */
    private void mo13829a() {
        f1142a = C1061i.m1203c(getContext());
    }

    /* renamed from: f */
    private String m1435f(String str) {
        try {
            return C5108b.m6982a(C1248b.m1977a(), str);
        } catch (Exception e) {
            C1270g.m2076a(6, e.getMessage());
            C1132f.m1527a(this.context, C1130d.EXCEPTION, "OMSDK: Failed to inject js to html ad.", e.getMessage(), "");
            return str;
        }
    }

    /* renamed from: b */
    public void mo14369b(String str) {
        if (MetaData.getInstance().isOmsdkEnabled()) {
            str = m1435f(str);
        }
        if (C1061i.m1194a(512)) {
            this.htmlUuid = C1186a.m1756a().mo15060a(str);
        }
        String a = mo14830a(str, "@smartRedirect@");
        if (a != null) {
            m1438i(a);
        }
        String a2 = mo14830a(str, "@trackingClickUrl@");
        if (a2 != null) {
            m1440k(a2);
        }
        String a3 = mo14830a(str, "@closeUrl@");
        if (a3 != null) {
            mo14837c(a3);
        }
        String a4 = mo14830a(str, "@tracking@");
        if (a4 != null) {
            m1439j(a4);
        }
        String a5 = mo14830a(str, "@packageName@");
        if (a5 != null) {
            m1441l(a5);
        }
        String a6 = mo14830a(str, "@startappBrowserEnabled@");
        if (a6 != null) {
            m1437h(a6);
        }
        String a7 = mo14830a(str, "@orientation@");
        if (a7 != null && C1061i.m1194a(8)) {
            mo14832a(SplashConfig.Orientation.getByName(a7));
        }
        String a8 = mo14830a(str, "@adInfoEnable@");
        if (a8 != null) {
            m1442m(a8);
        }
        String a9 = mo14830a(str, "@adInfoPosition@");
        if (a9 != null) {
            m1443n(a9);
        }
        String a10 = mo14830a(str, "@ttl@");
        if (a10 != null) {
            mo14838d(a10);
        }
        String a11 = mo14830a(str, "@belowMinCPM@");
        if (a11 != null) {
            m1436g(a11);
        }
        String a12 = mo14830a(str, "@delayImpressionInSeconds@");
        if (a12 != null) {
            m1444o(a12);
        }
        String a13 = mo14830a(str, "@sendRedirectHops@");
        if (a13 != null) {
            mo14840e(a13);
        }
        if (this.smartRedirect.length < this.trackingUrls.length) {
            C1270g.m2076a(6, "Error in smartRedirect array in HTML");
            boolean[] zArr = new boolean[this.trackingUrls.length];
            int i = 0;
            while (true) {
                boolean[] zArr2 = this.smartRedirect;
                if (i >= zArr2.length) {
                    break;
                }
                zArr[i] = zArr2[i];
                i++;
            }
            while (i < this.trackingUrls.length) {
                zArr[i] = false;
                i++;
            }
            this.smartRedirect = zArr;
        }
        mo14835b(C1146a.m1564b(str));
    }

    /* renamed from: g */
    private void m1436g(String str) {
        if (Arrays.asList(str.split(",")).contains("false")) {
            this.belowMinCPM = false;
        } else {
            this.belowMinCPM = true;
        }
    }

    /* renamed from: h */
    private void m1437h(String str) {
        String[] split = str.split(",");
        this.inAppBrowserEnabled = new boolean[split.length];
        for (int i = 0; i < split.length; i++) {
            if (split[i].compareTo("false") == 0) {
                this.inAppBrowserEnabled[i] = false;
            } else {
                this.inAppBrowserEnabled[i] = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo14830a(String str, String str2) {
        return C1061i.m1179a(str, str2, str2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14832a(SplashConfig.Orientation orientation2) {
        this.orientation = 0;
        boolean a = C1061i.m1194a(8);
        if (orientation2 == null) {
            return;
        }
        if (a && orientation2.equals(SplashConfig.Orientation.PORTRAIT)) {
            this.orientation = 1;
        } else if (a && orientation2.equals(SplashConfig.Orientation.LANDSCAPE)) {
            this.orientation = 2;
        }
    }

    /* renamed from: i */
    private void m1438i(String str) {
        String[] split = str.split(",");
        this.smartRedirect = new boolean[split.length];
        for (int i = 0; i < split.length; i++) {
            if (split[i].compareTo(CleanerProperties.BOOL_ATT_TRUE) == 0) {
                this.smartRedirect[i] = true;
            } else {
                this.smartRedirect[i] = false;
            }
        }
    }

    /* renamed from: d */
    public boolean mo14839d(int i) {
        if (i < 0) {
            return false;
        }
        boolean[] zArr = this.smartRedirect;
        if (i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    /* renamed from: k */
    public boolean[] mo14848k() {
        return this.inAppBrowserEnabled;
    }

    /* renamed from: e */
    public boolean mo14841e(int i) {
        boolean[] zArr = this.inAppBrowserEnabled;
        if (zArr == null || i < 0 || i >= zArr.length) {
            return true;
        }
        return zArr[i];
    }

    /* renamed from: j */
    private void m1439j(String str) {
        this.trackingUrls = str.split(",");
    }

    /* renamed from: l */
    public String[] mo14849l() {
        return this.trackingUrls;
    }

    /* renamed from: m */
    public String[] mo14850m() {
        return this.trackingClickUrls;
    }

    /* renamed from: k */
    private void m1440k(String str) {
        this.trackingClickUrls = str.split(",");
    }

    /* renamed from: n */
    public int mo14851n() {
        return this.orientation;
    }

    /* renamed from: l */
    private void m1441l(String str) {
        this.packageNames = str.split(",");
    }

    /* renamed from: o */
    public String[] mo14852o() {
        return this.packageNames;
    }

    /* renamed from: a */
    public void mo14833a(List<C1099a> list) {
        this.apps = list;
    }

    /* renamed from: m */
    private void m1442m(String str) {
        getAdInfoOverride().mo14726a(Boolean.parseBoolean(str));
    }

    /* renamed from: n */
    private void m1443n(String str) {
        getAdInfoOverride().mo14725a(AdInformationPositions.Position.getByName(str));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a_ */
    public String mo13831a_() {
        return f1142a;
    }

    /* renamed from: d */
    public void mo14838d(String str) {
        Long l = null;
        for (String str2 : str.split(",")) {
            if (!str2.equals("")) {
                try {
                    long parseLong = Long.parseLong(str2);
                    if (parseLong > 0 && (l == null || parseLong < l.longValue())) {
                        l = Long.valueOf(parseLong);
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
    }

    /* renamed from: p */
    public Long mo14853p() {
        return this.delayImpressionInSeconds;
    }

    /* renamed from: o */
    private void m1444o(String str) {
        if (str != null && !str.equals("")) {
            try {
                this.delayImpressionInSeconds = Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* renamed from: q */
    public Boolean[] mo14854q() {
        return this.sendRedirectHops;
    }

    /* renamed from: f */
    public Boolean mo14842f(int i) {
        Boolean[] boolArr = this.sendRedirectHops;
        if (boolArr == null || i < 0 || i >= boolArr.length) {
            return null;
        }
        return boolArr[i];
    }

    /* renamed from: e */
    public void mo14840e(String str) {
        if (str != null && !str.equals("")) {
            String[] split = str.split(",");
            this.sendRedirectHops = new Boolean[split.length];
            for (int i = 0; i < split.length; i++) {
                if (split[i].compareTo(CleanerProperties.BOOL_ATT_TRUE) == 0) {
                    this.sendRedirectHops[i] = true;
                } else if (split[i].compareTo("false") == 0) {
                    this.sendRedirectHops[i] = false;
                } else {
                    this.sendRedirectHops[i] = null;
                }
            }
        }
    }

    /* renamed from: r */
    public boolean mo14855r() {
        return this.isMraidAd;
    }

    /* renamed from: b */
    public void mo14835b(boolean z) {
        this.isMraidAd = z;
    }
}
