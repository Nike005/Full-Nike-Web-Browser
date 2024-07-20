package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.impl.utils.C2221f;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.a */
public class C1807a {

    /* renamed from: a */
    private JSONObject f2880a = new JSONObject();

    /* renamed from: b */
    private long f2881b;

    /* renamed from: c */
    private boolean f2882c;

    /* renamed from: d */
    private C2221f.C2222a f2883d = C2221f.C2222a.m5942d();

    /* renamed from: e */
    private final C2221f f2884e = new C2221f();

    /* renamed from: com.yandex.metrica.impl.a$a */
    public static final class C1808a {

        /* renamed from: a */
        public final String f2885a;

        /* renamed from: b */
        public final long f2886b;

        public C1808a(String str, long j) {
            this.f2885a = str;
            this.f2886b = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                C1808a aVar = (C1808a) obj;
                if (this.f2886b != aVar.f2886b) {
                    return false;
                }
                String str = this.f2885a;
                String str2 = aVar.f2885a;
                return str == null ? str2 == null : str.equals(str2);
            }
        }

        public int hashCode() {
            String str = this.f2885a;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.f2886b;
            return (hashCode * 31) + ((int) (j ^ (j >>> 32)));
        }
    }

    public C1807a(String str, long j) {
        this.f2881b = j;
        try {
            this.f2880a = new JSONObject(str);
        } catch (JSONException unused) {
            this.f2880a = new JSONObject();
            this.f2881b = 0;
        }
    }

    /* renamed from: a */
    public synchronized void mo16754a() {
        this.f2880a = new JSONObject();
        this.f2881b = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo16756a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.yandex.metrica.impl.utils.f r0 = r3.f2884e     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f$a r1 = r3.f2883d     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            int r1 = r1.mo17919b()     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r2 = "App Environment"
            java.lang.String r4 = r0.mo17913a(r4, r1, r2)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f r0 = r3.f2884e     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            com.yandex.metrica.impl.utils.f$a r1 = r3.f2883d     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            int r1 = r1.mo17920c()     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r2 = "App Environment"
            java.lang.String r5 = r0.mo17913a(r5, r1, r2)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            org.json.JSONObject r0 = r3.f2880a     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            boolean r0 = r0.has(r4)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r0 == 0) goto L_0x0038
            org.json.JSONObject r0 = r3.f2880a     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            java.lang.String r0 = r0.getString(r4)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r5 == 0) goto L_0x0033
            boolean r0 = r5.equals(r0)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
            if (r0 != 0) goto L_0x0036
        L_0x0033:
            r3.mo16758b(r4, r5)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
        L_0x0036:
            monitor-exit(r3)
            return
        L_0x0038:
            if (r5 == 0) goto L_0x003d
            r3.mo16758b(r4, r5)     // Catch:{ JSONException -> 0x0042, all -> 0x003f }
        L_0x003d:
            monitor-exit(r3)
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x0042:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1807a.mo16756a(java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public synchronized void mo16755a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            mo16756a(str, bundle.getString(str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void mo16758b(String str, String str2) throws JSONException {
        if (this.f2880a.length() >= this.f2883d.mo17918a()) {
            if (this.f2883d.mo17918a() != this.f2880a.length() || !this.f2880a.has(str)) {
                this.f2884e.mo17917b(str, this.f2883d.mo17918a(), "App Environment");
                return;
            }
        }
        this.f2880a.put(str, str2);
        this.f2882c = true;
    }

    /* renamed from: b */
    public synchronized C1808a mo16757b() {
        if (this.f2882c) {
            this.f2881b++;
            this.f2882c = false;
        }
        return new C1808a(this.f2880a.toString(), this.f2881b);
    }

    public synchronized String toString() {
        return "Map size " + this.f2880a.length() + ". Is changed " + this.f2882c + ". Current revision " + this.f2881b;
    }
}
