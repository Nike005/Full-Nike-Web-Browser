package p055a.p056a.p063e;

import java.io.Serializable;
import java.util.Set;
import java.util.regex.Pattern;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.e.b */
/* compiled from: StartAppSDK */
public final class C2951b implements Serializable {

    /* renamed from: a */
    public static final C2952a f4031a = new C2952a((C2925e) null);
    private Set<? extends Object> _options;
    private final Pattern nativePattern;

    public C2951b(Pattern pattern) {
        C2928h.m6157b(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C2951b(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            p055a.p056a.p058b.p060b.C2928h.m6157b(r2, r0)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.lang.String r0 = "Pattern.compile(pattern)"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r2, (java.lang.String) r0)
            r1.<init>((java.util.regex.Pattern) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p055a.p056a.p063e.C2951b.<init>(java.lang.String):void");
    }

    /* renamed from: a */
    public final boolean mo24982a(CharSequence charSequence) {
        C2928h.m6157b(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public String toString() {
        String pattern = this.nativePattern.toString();
        C2928h.m6154a((Object) pattern, "nativePattern.toString()");
        return pattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        C2928h.m6154a((Object) pattern, "nativePattern.pattern()");
        return new C2953b(pattern, this.nativePattern.flags());
    }

    /* renamed from: a.a.e.b$b */
    /* compiled from: StartAppSDK */
    private static final class C2953b implements Serializable {

        /* renamed from: a */
        public static final C2954a f4032a = new C2954a((C2925e) null);
        private static final long serialVersionUID = 0;
        private final int flags;
        private final String pattern;

        /* renamed from: a.a.e.b$b$a */
        /* compiled from: StartAppSDK */
        public static final class C2954a {
            private C2954a() {
            }

            public /* synthetic */ C2954a(C2925e eVar) {
                this();
            }
        }

        public C2953b(String str, int i) {
            C2928h.m6157b(str, "pattern");
            this.pattern = str;
            this.flags = i;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            C2928h.m6154a((Object) compile, "Pattern.compile(pattern, flags)");
            return new C2951b(compile);
        }
    }

    /* renamed from: a.a.e.b$a */
    /* compiled from: StartAppSDK */
    public static final class C2952a {
        private C2952a() {
        }

        public /* synthetic */ C2952a(C2925e eVar) {
            this();
        }
    }
}
