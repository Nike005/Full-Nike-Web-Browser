package org.altbeacon.beacon.logging;

import android.util.Log;

final class InfoAndroidLogger extends AbstractAndroidLogger {
    /* renamed from: d */
    public void mo19959d(String str, String str2, Object... objArr) {
    }

    /* renamed from: d */
    public void mo19960d(Throwable th, String str, String str2, Object... objArr) {
    }

    /* renamed from: v */
    public void mo19965v(String str, String str2, Object... objArr) {
    }

    /* renamed from: v */
    public void mo19966v(Throwable th, String str, String str2, Object... objArr) {
    }

    InfoAndroidLogger() {
    }

    /* renamed from: i */
    public void mo19963i(String str, String str2, Object... objArr) {
        Log.i(str, formatString(str2, objArr));
    }

    /* renamed from: i */
    public void mo19964i(Throwable th, String str, String str2, Object... objArr) {
        Log.i(str, formatString(str2, objArr), th);
    }

    /* renamed from: w */
    public void mo19967w(String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr));
    }

    /* renamed from: w */
    public void mo19968w(Throwable th, String str, String str2, Object... objArr) {
        Log.w(str, formatString(str2, objArr), th);
    }

    /* renamed from: e */
    public void mo19961e(String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr));
    }

    /* renamed from: e */
    public void mo19962e(Throwable th, String str, String str2, Object... objArr) {
        Log.e(str, formatString(str2, objArr), th);
    }
}
