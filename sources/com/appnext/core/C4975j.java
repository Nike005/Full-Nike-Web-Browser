package com.appnext.core;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/* renamed from: com.appnext.core.j */
public final class C4975j {

    /* renamed from: hp */
    private static C4975j f4836hp;

    /* renamed from: be */
    private int f4837be = 24;
    /* access modifiers changed from: private */

    /* renamed from: hq */
    public HashMap<String, SharedPreferences> f4838hq = new HashMap<>();

    private C4975j() {
    }

    /* renamed from: d */
    public final void mo41287d(final Context context, final String str) {
        if (!this.f4838hq.containsKey(str.replace("/", ""))) {
            new Thread(new Runnable() {
                public final void run() {
                    HashMap a = C4975j.this.f4838hq;
                    String str = str;
                    Context context = context;
                    a.put(str, context.getSharedPreferences("apnxt_cap" + str.replace("/", ""), 0));
                }
            }).start();
        }
    }

    /* renamed from: bj */
    public static synchronized C4975j m6859bj() {
        C4975j jVar;
        synchronized (C4975j.class) {
            if (f4836hp == null) {
                f4836hp = new C4975j();
            }
            jVar = f4836hp;
        }
        return jVar;
    }

    /* renamed from: n */
    public final void mo41288n(String str, String str2) {
        this.f4838hq.get(str2).edit().putLong(str, System.currentTimeMillis()).apply();
    }

    /* renamed from: o */
    public final boolean mo41289o(String str, String str2) {
        long j = this.f4838hq.get(str2).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - ((long) (this.f4837be * 3600000)) <= j;
    }

    /* renamed from: p */
    public final boolean mo41290p(String str, String str2) {
        long j = this.f4838hq.get(str2).getLong(str, -1);
        return j != -1 && System.currentTimeMillis() - 120000 <= j;
    }

    /* renamed from: ab */
    public final void mo41285ab(String str) {
        this.f4838hq.get(str).edit().clear().apply();
    }

    /* renamed from: b */
    public final void mo41286b(int i) {
        this.f4837be = i;
    }
}
