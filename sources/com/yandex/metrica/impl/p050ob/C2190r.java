package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1897bk;

/* renamed from: com.yandex.metrica.impl.ob.r */
public class C2190r {

    /* renamed from: a */
    private boolean f3776a;

    /* renamed from: b */
    private final String f3777b;

    /* renamed from: c */
    private final String f3778c;

    public C2190r(String str, String str2, boolean z) {
        this.f3776a = z;
        this.f3777b = str;
        this.f3778c = str2;
    }

    /* renamed from: a */
    public String mo17818a() {
        return this.f3778c;
    }

    /* renamed from: b */
    public String mo17819b() {
        return this.f3777b;
    }

    /* renamed from: c */
    public boolean mo17820c() {
        return !this.f3776a && !C1897bk.m4657b(this.f3778c);
    }

    /* renamed from: d */
    public boolean mo17821d() {
        return this.f3776a;
    }

    public String toString() {
        String str = this.f3777b;
        if (this.f3776a) {
            return str;
        }
        return str + "_" + this.f3778c;
    }

    /* renamed from: a */
    public static C2190r m5776a(Context context, CounterConfiguration counterConfiguration, Integer num, String str) {
        String f = counterConfiguration.mo16587f();
        if (!C1894bi.m4622a(f)) {
            str = f;
        } else if (num != null) {
            String[] packagesForUid = context.getPackageManager().getPackagesForUid(num.intValue());
            str = (packagesForUid == null || packagesForUid.length <= 0) ? null : packagesForUid[0];
        }
        if (!C1894bi.m4622a(str)) {
            return new C2190r(str, C1897bk.m4637a(context, counterConfiguration, str), counterConfiguration.mo16554C());
        }
        return null;
    }

    /* renamed from: a */
    public static C2190r m5777a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new C2190r(str, (String) null, true);
        }
        return null;
    }
}
