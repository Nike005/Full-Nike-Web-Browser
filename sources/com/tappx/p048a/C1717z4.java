package com.tappx.p048a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.tappx.sdk.android.AdActivity;
import com.tappx.sdk.android.InterstitialAdActivity;

/* renamed from: com.tappx.a.z4 */
class C1717z4 {
    /* renamed from: a */
    public static void m3785a(Context context, String str, C1414f5 f5Var, int i) {
        Class cls;
        if (!f5Var.mo15809f() || !m3786a()) {
            cls = AdActivity.class;
        } else {
            cls = InterstitialAdActivity.class;
        }
        Intent intent = new Intent(context, cls);
        C1701y3.m3707a(intent, str);
        intent.putExtra("aavc_fagZVUC6pOQOxawVwpVy", f5Var);
        intent.putExtra("aavc_otZMuRlffpTHI9DsaLyI", i);
        intent.addFlags(268435456);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            String name = cls.getName();
            String simpleName = cls.getSimpleName();
            C1467j0.m2871b(C1400f.m2603b("dfKcWOaG8KPoMfm5zts08Qlu05+R8BIzO3YcOMbimy7M7b66oYD1J20myZSpOoOWRYcUsjDmTjtwSPWh2TgTXA"), name, simpleName);
        }
    }

    /* renamed from: a */
    private static boolean m3786a() {
        return Build.VERSION.SDK_INT < 26;
    }
}
