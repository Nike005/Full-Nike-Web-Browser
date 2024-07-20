package com.startapp.android.publish.omsdk;

import android.content.Context;
import android.webkit.WebView;
import com.p088b.p089a.p090a.p091a.C5102a;
import com.p088b.p089a.p090a.p091a.p093b.C5111b;
import com.p088b.p089a.p090a.p091a.p093b.C5112c;
import com.p088b.p089a.p090a.p091a.p093b.C5113d;
import com.p088b.p089a.p090a.p091a.p093b.C5115f;
import com.p088b.p089a.p090a.p091a.p093b.C5116g;
import com.p088b.p089a.p090a.p091a.p093b.C5117h;
import com.startapp.android.publish.GeneratedConstants;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.omsdk.a */
/* compiled from: StartAppSDK */
public class C1247a {
    /* renamed from: a */
    private static String m1973a() {
        return "";
    }

    /* renamed from: a */
    public static C5111b m1971a(WebView webView) {
        if (!m1975a(webView.getContext())) {
            return null;
        }
        return m1972a(C5113d.m7007a(m1976b(), webView, m1973a()), false);
    }

    /* renamed from: a */
    public static C5111b m1970a(Context context, AdVerification adVerification) {
        if (!m1975a(context)) {
            return null;
        }
        if (adVerification == null) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "OMSDK: Verification details can't be null!", "", "");
            return null;
        }
        String a = C1248b.m1977a();
        List<VerificationDetails> adVerification2 = adVerification.getAdVerification();
        ArrayList arrayList = new ArrayList(adVerification2.size());
        for (VerificationDetails next : adVerification2) {
            URL a2 = m1974a(context, next.getVerificationScriptUrl());
            if (a2 != null) {
                arrayList.add(C5117h.m7018a(next.getVendorKey(), a2, next.getVerificationParameters()));
            }
        }
        return m1972a(C5113d.m7008a(m1976b(), a, arrayList, m1973a()), true);
    }

    /* renamed from: a */
    private static C5111b m1972a(C5113d dVar, boolean z) {
        return C5111b.m6998a(C5112c.m7003a(C5115f.NATIVE, z ? C5115f.NATIVE : C5115f.NONE, false), dVar);
    }

    /* renamed from: b */
    private static C5116g m1976b() {
        return C5116g.m7015a("StartApp", GeneratedConstants.INAPP_VERSION);
    }

    /* renamed from: a */
    private static URL m1974a(Context context, String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            C1130d dVar = C1130d.EXCEPTION;
            C1132f.m1527a(context, dVar, "OMSDK: can't create URL - " + str, e.getMessage(), "");
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m1975a(Context context) {
        try {
            if (C5102a.m6972b() || C5102a.m6971a(C5102a.m6970a(), context)) {
                return true;
            }
            C1132f.m1527a(context, C1130d.EXCEPTION, "OMSDK: Failed to activate sdk.", "", "");
            return false;
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "OMSDK: Failed to activate sdk.", e.getMessage(), "");
            return false;
        }
    }
}
