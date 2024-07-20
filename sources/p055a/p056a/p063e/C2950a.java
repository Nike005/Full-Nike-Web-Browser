package p055a.p056a.p063e;

import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.e.a */
/* compiled from: StartAppSDK */
public final class C2950a {

    /* renamed from: a */
    public static final Charset f4024a;

    /* renamed from: b */
    public static final Charset f4025b;

    /* renamed from: c */
    public static final Charset f4026c;

    /* renamed from: d */
    public static final Charset f4027d;

    /* renamed from: e */
    public static final Charset f4028e;

    /* renamed from: f */
    public static final Charset f4029f;

    /* renamed from: g */
    public static final C2950a f4030g = new C2950a();

    static {
        Charset forName = Charset.forName("UTF-8");
        C2928h.m6154a((Object) forName, "Charset.forName(\"UTF-8\")");
        f4024a = forName;
        Charset forName2 = Charset.forName("UTF-16");
        C2928h.m6154a((Object) forName2, "Charset.forName(\"UTF-16\")");
        f4025b = forName2;
        Charset forName3 = Charset.forName(CharEncoding.UTF_16BE);
        C2928h.m6154a((Object) forName3, "Charset.forName(\"UTF-16BE\")");
        f4026c = forName3;
        Charset forName4 = Charset.forName("UTF-16LE");
        C2928h.m6154a((Object) forName4, "Charset.forName(\"UTF-16LE\")");
        f4027d = forName4;
        Charset forName5 = Charset.forName("US-ASCII");
        C2928h.m6154a((Object) forName5, "Charset.forName(\"US-ASCII\")");
        f4028e = forName5;
        Charset forName6 = Charset.forName(CharEncoding.ISO_8859_1);
        C2928h.m6154a((Object) forName6, "Charset.forName(\"ISO-8859-1\")");
        f4029f = forName6;
    }

    private C2950a() {
    }
}
