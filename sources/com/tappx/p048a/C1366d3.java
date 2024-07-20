package com.tappx.p048a;

import android.content.Context;
import android.os.Process;

/* renamed from: com.tappx.a.d3 */
public class C1366d3 {
    /* renamed from: a */
    public static boolean m2453a(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }
}
