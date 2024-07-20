package com.p088b.p089a.p090a.p091a.p093b;

import android.webkit.WebView;
import com.p088b.p089a.p090a.p091a.p097e.C5139e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.b.a.a.a.b.d */
public final class C5113d {

    /* renamed from: a */
    private final C5116g f4938a;

    /* renamed from: b */
    private final WebView f4939b;

    /* renamed from: c */
    private final List<C5117h> f4940c;

    /* renamed from: d */
    private final String f4941d;

    /* renamed from: e */
    private final String f4942e;

    /* renamed from: f */
    private final C5114e f4943f;

    private C5113d(C5116g gVar, WebView webView, String str, List<C5117h> list, String str2) {
        C5114e eVar;
        ArrayList arrayList = new ArrayList();
        this.f4940c = arrayList;
        this.f4938a = gVar;
        this.f4939b = webView;
        this.f4941d = str;
        if (list != null) {
            arrayList.addAll(list);
            eVar = C5114e.NATIVE;
        } else {
            eVar = C5114e.HTML;
        }
        this.f4943f = eVar;
        this.f4942e = str2;
    }

    /* renamed from: a */
    public static C5113d m7007a(C5116g gVar, WebView webView, String str) {
        C5139e.m7136a((Object) gVar, "Partner is null");
        C5139e.m7136a((Object) webView, "WebView is null");
        if (str != null) {
            C5139e.m7137a(str, 256, "CustomReferenceData is greater than 256 characters");
        }
        return new C5113d(gVar, webView, (String) null, (List<C5117h>) null, str);
    }

    /* renamed from: a */
    public static C5113d m7008a(C5116g gVar, String str, List<C5117h> list, String str2) {
        C5139e.m7136a((Object) gVar, "Partner is null");
        C5139e.m7136a((Object) str, "OMID JS script content is null");
        C5139e.m7136a((Object) list, "VerificationScriptResources is null");
        if (str2 != null) {
            C5139e.m7137a(str2, 256, "CustomReferenceData is greater than 256 characters");
        }
        return new C5113d(gVar, (WebView) null, str, list, str2);
    }

    /* renamed from: a */
    public C5116g mo41835a() {
        return this.f4938a;
    }

    /* renamed from: b */
    public List<C5117h> mo41836b() {
        return Collections.unmodifiableList(this.f4940c);
    }

    /* renamed from: c */
    public WebView mo41837c() {
        return this.f4939b;
    }

    /* renamed from: d */
    public String mo41838d() {
        return this.f4942e;
    }

    /* renamed from: e */
    public String mo41839e() {
        return this.f4941d;
    }

    /* renamed from: f */
    public C5114e mo41840f() {
        return this.f4943f;
    }
}
