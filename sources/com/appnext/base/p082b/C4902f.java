package com.appnext.base.p082b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.text.TextUtils;
import com.appnext.core.C4967f;

/* renamed from: com.appnext.base.b.f */
public class C4902f {
    public static final String TAG = C4902f.class.getSimpleName();

    /* renamed from: e */
    public static NetworkInfo m6574e(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    /* renamed from: a */
    public static boolean m6573a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || context.checkPermission(str, Process.myPid(), Process.myUid()) != 0) {
            return false;
        }
        return true;
    }

    public static String getKey() {
        C4904h.m6584aO();
        return C4904h.m6581J(C4967f.m6827b(C4901e.getContext(), false));
    }
}
