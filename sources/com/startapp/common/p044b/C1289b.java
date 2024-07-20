package com.startapp.common.p044b;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.common.b.b */
/* compiled from: StartAppSDK */
public class C1289b {

    /* renamed from: a */
    private final C1291a f1545a;

    private C1289b(C1291a aVar) {
        this.f1545a = aVar;
    }

    /* renamed from: a */
    public int mo15486a() {
        return this.f1545a.f1546a;
    }

    /* renamed from: b */
    public Map<String, String> mo15487b() {
        return this.f1545a.f1547b;
    }

    /* renamed from: c */
    public long mo15488c() {
        return this.f1545a.f1548c;
    }

    /* renamed from: d */
    public long mo15489d() {
        return this.f1545a.f1549d;
    }

    /* renamed from: e */
    public boolean mo15490e() {
        return this.f1545a.f1550e;
    }

    /* renamed from: f */
    public boolean mo15491f() {
        return this.f1545a.f1551f;
    }

    public String toString() {
        return "RunnerRequest: " + this.f1545a.f1546a + StringUtils.SPACE + this.f1545a.f1548c + StringUtils.SPACE + this.f1545a.f1550e + StringUtils.SPACE + this.f1545a.f1549d + StringUtils.SPACE + this.f1545a.f1547b;
    }

    /* renamed from: com.startapp.common.b.b$a */
    /* compiled from: StartAppSDK */
    public static class C1291a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f1546a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public Map<String, String> f1547b = new HashMap();
        /* access modifiers changed from: private */

        /* renamed from: c */
        public long f1548c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public long f1549d = 100;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public boolean f1550e = false;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public boolean f1551f = false;

        public C1291a(int i) {
            this.f1546a = i;
        }

        /* renamed from: a */
        public C1291a mo15495a(Map<String, String> map) {
            if (map != null) {
                this.f1547b.putAll(map);
            }
            return this;
        }

        /* renamed from: a */
        public C1291a mo15494a(String str, String str2) {
            this.f1547b.put(str, str2);
            return this;
        }

        /* renamed from: a */
        public C1291a mo15493a(long j) {
            this.f1548c = j;
            return this;
        }

        /* renamed from: a */
        public C1291a mo15496a(boolean z) {
            this.f1550e = z;
            return this;
        }

        /* renamed from: b */
        public C1291a mo15498b(boolean z) {
            this.f1551f = z;
            return this;
        }

        /* renamed from: a */
        public C1289b mo15497a() {
            return new C1289b(this);
        }
    }
}
