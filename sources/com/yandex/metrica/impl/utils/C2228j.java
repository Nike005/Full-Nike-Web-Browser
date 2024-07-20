package com.yandex.metrica.impl.utils;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.C1781c;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;
import java.util.Locale;

/* renamed from: com.yandex.metrica.impl.utils.j */
public final class C2228j extends C2218c {

    /* renamed from: a */
    private static final int[] f3898a = {3, 6, 4};

    /* renamed from: b */
    private static final C2228j f3899b = new C2228j();

    /* renamed from: c */
    private static String f3900c = "";

    /* renamed from: d */
    public String mo17910d() {
        return "AppMetrica";
    }

    public C2228j() {
        super(false);
    }

    /* renamed from: f */
    public static C2228j m5960f() {
        return f3899b;
    }

    /* renamed from: a */
    public static void m5959a(Context context) {
        f3900c = String.format("[%s] : ", new Object[]{context.getPackageName()});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo17908c(String str, Object[] objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public String mo17911e() {
        return C1894bi.m4626b(f3900c, "");
    }

    /* renamed from: a */
    public void mo17927a(C1915h hVar, String str) {
        if (C2208p.m5876b(hVar.mo17118c())) {
            StringBuilder sb = new StringBuilder(str);
            sb.append(": ");
            sb.append(hVar.mo17115a());
            if (C2208p.m5880c(hVar.mo17118c()) && !TextUtils.isEmpty(hVar.mo17117b())) {
                sb.append(" with value ");
                sb.append(hVar.mo17117b());
            }
            mo17902a(sb.toString());
        }
    }

    /* renamed from: a */
    public void mo17926a(C1781c.C1782a.C1786d dVar, String str) {
        for (C1781c.C1782a.C1786d.C1787a a : dVar.f2787d) {
            mo17925a(a, str);
        }
    }

    /* renamed from: a */
    public void mo17925a(C1781c.C1782a.C1786d.C1787a aVar, String str) {
        String str2;
        int[] iArr = f3898a;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (aVar.f2791d == iArr[i]) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": ");
            if (aVar.f2791d == 3 && TextUtils.isEmpty(aVar.f2792e)) {
                str2 = C2208p.C2209a.EVENT_TYPE_NATIVE_CRASH.mo17885b();
            } else if (aVar.f2791d == 4) {
                StringBuilder sb2 = new StringBuilder(aVar.f2792e);
                if (aVar.f2793f != null) {
                    String str3 = new String(aVar.f2793f);
                    if (!TextUtils.isEmpty(str3)) {
                        sb2.append(" with value ");
                        sb2.append(str3);
                    }
                }
                str2 = sb2.toString();
            } else {
                str2 = aVar.f2792e;
            }
            sb.append(str2);
            mo17902a(sb.toString());
        }
    }
}
