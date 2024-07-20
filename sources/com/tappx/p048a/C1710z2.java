package com.tappx.p048a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.tappx.p048a.C1522m1;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/* renamed from: com.tappx.a.z2 */
public class C1710z2 implements C1700y2 {

    /* renamed from: h */
    private static volatile C1700y2 f2580h;

    /* renamed from: a */
    private final Context f2581a;

    /* renamed from: b */
    private final C1522m1.C1523a f2582b;

    /* renamed from: c */
    private C1713c f2583c;

    /* renamed from: d */
    private String f2584d;

    /* renamed from: e */
    private String f2585e;

    /* renamed from: f */
    private String f2586f;

    /* renamed from: g */
    private String f2587g = "";

    /* renamed from: com.tappx.a.z2$a */
    class C1711a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ boolean f2588a;

        C1711a(boolean z) {
            this.f2588a = z;
        }

        public void run() {
            C1710z2.this.m3756a(this.f2588a);
        }
    }

    /* renamed from: com.tappx.a.z2$b */
    class C1712b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f2590a;

        C1712b(String str) {
            this.f2590a = str;
        }

        public void run() {
            C1710z2.this.m3770f(this.f2590a);
        }
    }

    /* renamed from: com.tappx.a.z2$c */
    private enum C1713c {
        NORMAL,
        GETCLASS,
        TESTINSTALL_APP,
        TESTINSTALL_MANUAL
    }

    C1710z2(Context context, C1522m1.C1523a aVar) {
        this.f2581a = context.getApplicationContext();
        this.f2582b = aVar;
        this.f2583c = C1713c.NORMAL;
    }

    /* renamed from: b */
    private void m3761b(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("token");
            if (stringExtra == null) {
                stringExtra = "";
            }
            char c = 65535;
            int hashCode = stringExtra.hashCode();
            if (hashCode != -1982207016) {
                if (hashCode != 210735) {
                    if (hashCode != 149971738) {
                        if (hashCode == 471550256) {
                            if (stringExtra.equals("TAPPX_AUX")) {
                                c = 3;
                            }
                        }
                    } else if (stringExtra.equals("TAPPX_INSTALL_GETCLASS")) {
                        c = 0;
                    }
                } else if (stringExtra.equals("TAPPX_INSTALL_TESTMODE_APP")) {
                    c = 1;
                }
            } else if (stringExtra.equals("TAPPX_INSTALL_TESTMODE_MANUAL")) {
                c = 2;
            }
            if (c == 0) {
                this.f2583c = C1713c.GETCLASS;
            } else if (c == 1) {
                this.f2583c = C1713c.TESTINSTALL_APP;
            } else if (c == 2) {
                this.f2583c = C1713c.TESTINSTALL_MANUAL;
            } else if (c != 3) {
                this.f2583c = C1713c.NORMAL;
            } else {
                this.f2583c = C1713c.GETCLASS;
            }
        } catch (Exception e) {
            m3765c("no string token");
            C1467j0.m2871b("ERROR01: " + e.getMessage(), new Object[0]);
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m3765c(String str) {
        C1713c cVar = this.f2583c;
        if (cVar != C1713c.NORMAL && cVar != C1713c.TESTINSTALL_APP) {
            C1467j0.m2872c(str, new Object[0]);
        }
    }

    /* renamed from: d */
    private void m3767d(String str) {
        m3765c("SavedReferrer = " + C1674w2.m3545a(this.f2581a, "sp_tappx_referrer", "NotFound"));
        if (str != null && str.length() > 0) {
            int indexOf = str.indexOf("referrer=");
            if (indexOf > 0) {
                str = str.substring(indexOf + 9);
            }
            String[] split = URLDecoder.decode(str, "UTF-8").split("&");
            if (split != null) {
                for (String split2 : split) {
                    String[] split3 = split2.split("=");
                    if (split3.length > 1) {
                        if ("tappxs".equalsIgnoreCase(split3[0])) {
                            this.f2584d = split3[1];
                        } else if ("tappxp".equalsIgnoreCase(split3[0])) {
                            this.f2585e = split3[1];
                        } else if ("ord".equalsIgnoreCase(split3[0])) {
                            this.f2586f = split3[1];
                        }
                        m3765c(split3[0] + "=" + split3[1]);
                    }
                }
                m3765c("Referrer = " + str);
            }
        }
    }

    /* renamed from: e */
    private void m3769e(String str) {
        String str2;
        boolean z;
        if (this.f2583c == C1713c.NORMAL) {
            str2 = C1674w2.m3545a(this.f2581a, "sp_tappx_referrer", "NotFound");
        } else {
            str2 = "NotFound";
        }
        if ("NotFound".equals(str2)) {
            m3755a("sp_tappx_referrer", str);
            String str3 = "";
            if (str3.equals(this.f2584d) || str3.equals(this.f2585e)) {
                m3755a("sp_tappx_referrer_send", "0");
                z = false;
            } else {
                str3 = m3758b();
                m3755a("sp_tappx_install_id", str3);
                z = true;
            }
            if (!z) {
                return;
            }
            if (this.f2583c == C1713c.NORMAL) {
                m3762b(false);
            } else {
                m3771g(str3);
            }
        } else {
            m3765c("old_referrer = " + str2);
            m3762b(false);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m3770f(String str) {
        C1467j0.m2869a("Trying to Track Install", new Object[0]);
        String a = m3750a("-" + str + ":" + (System.currentTimeMillis() / 1000), 2);
        try {
            a = URLEncoder.encode(a, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
        }
        C1467j0.m2869a("ti->snd", new Object[0]);
        Bundle b = m3757b(m3768e() + a);
        if (b != null) {
            String a2 = this.f2582b.mo15964a();
            String a3 = m3749a(b.getCharSequence("HTML"), "");
            String a4 = m3749a(b.getCharSequence("ERROR"), "");
            if (this.f2583c == C1713c.NORMAL) {
                return;
            }
            if (a4 == null || a4.length() <= 0) {
                C1467j0.m2869a("TrackInstall result: " + a3, new Object[0]);
                if (m3749a(b.getCharSequence("HTML"), "").trim().equals("1")) {
                    C1467j0.m2869a("ti->ok", new Object[0]);
                    C1467j0.m2869a("Install Tracked", new Object[0]);
                } else {
                    C1467j0.m2869a("ti->ko", new Object[0]);
                    C1467j0.m2875f("Install NOT Tracked", new Object[0]);
                }
                if (C1713c.TESTINSTALL_APP.equals(this.f2583c)) {
                    try {
                        Intent intent = new Intent();
                        intent.addFlags(268435456);
                        intent.setPackage("com.tappx.TrackingTestApp");
                        intent.setAction("com.tappx.TrackingTestApp.TEST_INSTALL");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.TEXT", a2 + "|" + m3763c() + "|" + this.f2581a.getApplicationContext().getPackageName());
                        this.f2581a.startActivity(intent);
                    } catch (Exception e) {
                        C1467j0.m2869a("Error sending to TestApp: " + e.getMessage(), new Object[0]);
                    }
                }
            } else {
                C1467j0.m2871b("TrackInstall Error: " + a4, new Object[0]);
            }
        }
    }

    /* renamed from: g */
    private void m3771g(String str) {
        C1686x2.m3599a(new C1712b(str));
    }

    /* renamed from: a */
    public static final C1700y2 m3747a(Context context) {
        if (f2580h == null) {
            synchronized (C1710z2.class) {
                if (f2580h == null) {
                    f2580h = new C1710z2(context.getApplicationContext(), C1522m1.C1523a.m3023a(context));
                }
            }
        }
        return f2580h;
    }

    /* renamed from: c */
    private String m3764c(Intent intent) {
        Uri data;
        String str = "";
        try {
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                stringExtra = str;
            }
            try {
                if (stringExtra.length() == 0 && (data = intent.getData()) != null) {
                    try {
                        String query = data.getQuery();
                        if (query == null) {
                            return str;
                        }
                        return query;
                    } catch (Exception e) {
                        m3765c("02. no URI referrer");
                        C1467j0.m2871b("ERROR02: " + e.getMessage(), new Object[0]);
                    }
                }
                return stringExtra;
            } catch (Exception e2) {
                e = e2;
                str = stringExtra;
                m3765c("01. No string referrer");
                C1467j0.m2871b("ERROR01: " + e.getMessage(), new Object[0]);
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            m3765c("01. No string referrer");
            C1467j0.m2871b("ERROR01: " + e.getMessage(), new Object[0]);
            return str;
        }
    }

    /* renamed from: a */
    public void mo16288a() {
        m3762b(true);
    }

    /* renamed from: a */
    public void mo16289a(Intent intent) {
        m3761b(intent);
        this.f2584d = "";
        this.f2585e = "";
        this.f2586f = "";
        this.f2587g = "";
        if (this.f2583c != C1713c.GETCLASS) {
            String c = m3764c(intent);
            if (c == null || c.length() <= 0) {
                if ("NotFound".equals(C1674w2.m3545a(this.f2581a, "sp_tappx_referrer", "NotFound"))) {
                    m3755a("sp_tappx_referrer", c);
                    if ("NotFound".equals(C1674w2.m3545a(this.f2581a, "sp_tappx_referrer_send", "NotFound"))) {
                        m3755a("sp_tappx_referrer_send", "0");
                    }
                } else if (this.f2583c == C1713c.NORMAL) {
                    m3762b(false);
                }
                m3765c("Mode: " + this.f2583c);
                m3765c("Not referrer INFO received.");
                m3765c("SavedReferrer = " + C1674w2.m3545a(this.f2581a, "sp_tappx_referrer", "NotFound"));
                m3765c("InstallSend   = " + C1674w2.m3545a(this.f2581a, "sp_tappx_referrer_send", "NotFound"));
                return;
            }
            try {
                m3767d(c);
                m3769e(c);
            } catch (UnsupportedEncodingException e) {
                m3765c("No string referrer");
                C1467j0.m2871b("ERROR: " + e.getMessage(), new Object[0]);
            }
        }
    }

    /* renamed from: c */
    private String m3763c() {
        if (!"".equals(this.f2587g)) {
            return this.f2587g;
        }
        try {
            String d = m3766d();
            if (!d.isEmpty() && d.contains("tappx.com")) {
                this.f2587g = m3752a(d + HlsSegmentFormat.f5078TS, 2, 6).getString(HlsSegmentFormat.f5078TS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"".equals(this.f2587g)) {
            return this.f2587g;
        }
        C1467j0.m2875f("Using device timestamp!", new Object[0]);
        return "" + (System.currentTimeMillis() / 1000);
    }

    /* renamed from: d */
    private String m3766d() {
        return C1400f.m2603b("L6AMiu9M3Gzzgb1DcC9zrNWKirwrdRZWS7ho5031f9E0pLEIRwh4cyjVdbI6wKX/");
    }

    /* renamed from: e */
    private String m3768e() {
        return C1400f.m2603b("wB98799JR2eOU8JQBj+AirJiMR1odQqWWeVt5DvdwLDbO/6GMnE3dISVriMmbsHg");
    }

    /* renamed from: b */
    private void m3762b(boolean z) {
        C1686x2.m3599a(new C1711a(z));
    }

    /* renamed from: b */
    public static String m3760b(String str, boolean z) {
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), 0);
        } catch (UnsupportedEncodingException e) {
            if (z) {
                e.printStackTrace();
            }
            return "";
        }
    }

    /* renamed from: a */
    private void m3755a(String str, String str2) {
        if (this.f2583c == C1713c.NORMAL) {
            C1674w2.m3546b(this.f2581a, str, str2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3756a(boolean z) {
        if (this.f2583c == C1713c.NORMAL && !"1".equals(C1674w2.m3545a(this.f2581a, "sp_tappx_referrer_send", "NotFound"))) {
            String a = C1674w2.m3545a(this.f2581a, "sp_tappx_install_id", "NotFound");
            if ("NotFound".equals(a) || "".equals(a)) {
                C1674w2.m3546b(this.f2581a, "sp_tappx_referrer_send", "0");
                return;
            }
            if (!z) {
                C1467j0.m2869a("Trying to Track Install", new Object[0]);
            } else {
                C1467j0.m2869a("Re-Trying to Track PENDING Install", new Object[0]);
            }
            String a2 = m3750a(a + ":" + (System.currentTimeMillis() / 1000), 2);
            try {
                a2 = URLEncoder.encode(a2, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
            C1467j0.m2869a("ti->snd", new Object[0]);
            Bundle b = m3757b(m3768e() + a2);
            if (b == null) {
                return;
            }
            if (m3749a(b.getCharSequence("HTML"), "").trim().equals("1")) {
                C1674w2.m3546b(this.f2581a, "sp_tappx_referrer_send", "1");
                C1467j0.m2869a("ti->ok", new Object[0]);
                C1467j0.m2869a("Install Tracked", new Object[0]);
                return;
            }
            C1467j0.m2869a("ti->ko", new Object[0]);
            C1467j0.m2875f("Install NOT Tracked", new Object[0]);
        }
    }

    /* renamed from: b */
    private Bundle m3757b(String str) {
        String str2;
        String str3;
        String str4 = "";
        try {
            str2 = mo16302a(str);
            str3 = str4;
        } catch (Exception e) {
            str3 = str4 + e.getMessage() + StringUtils.f3949LF;
            str2 = str4;
        }
        Bundle bundle = new Bundle();
        if (str2 == null) {
            str2 = str4;
        }
        bundle.putString("HTML", str2);
        if (str3 != null) {
            str4 = str3;
        }
        bundle.putString("ERROR", str4);
        return bundle;
    }

    /* renamed from: b */
    private String m3758b() {
        String str;
        String str2;
        C1522m1 b = this.f2582b.mo15965b();
        try {
            str = Settings.Secure.getString(this.f2581a.getContentResolver(), "android_id");
        } catch (Exception unused) {
            str = "unspecified_" + m3748a(8);
        }
        try {
            if (b.f2072d != null) {
                str2 = b.f2072d;
                return ("2:" + m3763c() + ":" + InternalAvidAdSessionContext.AVID_API_LEVEL + ":" + "Native" + ":" + b.f2069a.trim() + ":" + str.trim() + ":" + this.f2584d.trim() + ":" + this.f2585e.trim() + "::" + this.f2586f.trim() + ":" + str2).trim();
            }
        } catch (Exception unused2) {
        }
        str2 = "";
        return ("2:" + m3763c() + ":" + InternalAvidAdSessionContext.AVID_API_LEVEL + ":" + "Native" + ":" + b.f2069a.trim() + ":" + str.trim() + ":" + this.f2584d.trim() + ":" + this.f2585e.trim() + "::" + this.f2586f.trim() + ":" + str2).trim();
    }

    /* renamed from: a */
    private String m3749a(CharSequence charSequence, String str) {
        return charSequence != null ? charSequence.toString() : str;
    }

    /* renamed from: a */
    private String m3750a(String str, int i) {
        String str2 = "";
        if (!str2.equals(str)) {
            String b = m3760b(str, this.f2583c != C1713c.NORMAL);
            if (!str2.equals(b)) {
                str2 = m3748a(3) + b;
            } else {
                str2 = b;
            }
        }
        return i > 1 ? m3750a(str2, i - 1) : str2;
    }

    /* renamed from: a */
    public static String m3748a(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            double random = Math.random();
            double d = (double) 62;
            Double.isNaN(d);
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".charAt((int) (random * d)));
        }
        return sb.toString();
    }

    /* renamed from: b */
    private String m3759b(String str, int i, int i2) {
        String a = mo16302a(str);
        for (int i3 = 0; i3 < i; i3++) {
            a = m3751a(a.substring(i2), false);
        }
        return a;
    }

    /* renamed from: a */
    public String mo16302a(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(true);
        httpURLConnection.connect();
        InputStreamReader inputStreamReader = new InputStreamReader((InputStream) httpURLConnection.getContent());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str2 = "";
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                try {
                    break;
                } catch (Exception unused) {
                }
            } else {
                str2 = str2 + readLine;
            }
        }
        bufferedReader.close();
        inputStreamReader.close();
        httpURLConnection.disconnect();
        return str2;
    }

    /* renamed from: a */
    private JSONObject m3752a(String str, int i, int i2) {
        return new JSONObject(m3759b(str, i, i2));
    }

    /* renamed from: a */
    private static String m3751a(String str, boolean z) {
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (z) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
