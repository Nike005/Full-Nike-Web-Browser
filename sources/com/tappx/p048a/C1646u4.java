package com.tappx.p048a;

import android.os.AsyncTask;
import android.os.Build;

/* renamed from: com.tappx.a.u4 */
class C1646u4 {
    /* renamed from: a */
    public static <P> void m3480a(AsyncTask<P, ?, ?> asyncTask, P... pArr) {
        if (Build.VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pArr);
        } else {
            asyncTask.execute(pArr);
        }
    }

    /* renamed from: a */
    public static boolean m3481a(int i, int i2) {
        return (i & i2) != 0;
    }
}
