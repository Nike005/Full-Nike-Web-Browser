package com.startapp.p005a.p006a.p010d;

import java.util.regex.Pattern;
import org.slf4j.Marker;

/* renamed from: com.startapp.a.a.d.d */
/* compiled from: StartAppSDK */
public class C0814d implements C0813c {

    /* renamed from: a */
    private final Pattern f345a = Pattern.compile("\\+");

    /* renamed from: b */
    private final Pattern f346b = Pattern.compile("/");

    /* renamed from: c */
    private final Pattern f347c = Pattern.compile("=");

    /* renamed from: d */
    private final Pattern f348d = Pattern.compile("_");

    /* renamed from: e */
    private final Pattern f349e = Pattern.compile("\\*");

    /* renamed from: f */
    private final Pattern f350f = Pattern.compile("#");

    /* renamed from: a */
    public String mo13700a(String str) {
        return this.f347c.matcher(this.f346b.matcher(this.f345a.matcher(str).replaceAll("_")).replaceAll(Marker.ANY_MARKER)).replaceAll("#");
    }
}
