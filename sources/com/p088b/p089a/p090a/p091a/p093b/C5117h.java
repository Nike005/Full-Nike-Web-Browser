package com.p088b.p089a.p090a.p091a.p093b;

import com.p088b.p089a.p090a.p091a.p097e.C5139e;
import java.net.URL;

/* renamed from: com.b.a.a.a.b.h */
public final class C5117h {

    /* renamed from: a */
    private final String f4953a;

    /* renamed from: b */
    private final URL f4954b;

    /* renamed from: c */
    private final String f4955c;

    private C5117h(String str, URL url, String str2) {
        this.f4953a = str;
        this.f4954b = url;
        this.f4955c = str2;
    }

    /* renamed from: a */
    public static C5117h m7018a(String str, URL url, String str2) {
        C5139e.m7138a(str, "VendorKey is null or empty");
        C5139e.m7136a((Object) url, "ResourceURL is null");
        C5139e.m7138a(str2, "VerificationParameters is null or empty");
        return new C5117h(str, url, str2);
    }

    /* renamed from: a */
    public String mo41845a() {
        return this.f4953a;
    }

    /* renamed from: b */
    public URL mo41846b() {
        return this.f4954b;
    }

    /* renamed from: c */
    public String mo41847c() {
        return this.f4955c;
    }
}
