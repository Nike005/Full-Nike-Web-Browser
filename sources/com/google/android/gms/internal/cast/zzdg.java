package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;

public final class zzdg {
    private static boolean zzwk = false;
    private final String mTag;
    private final boolean zzwl;
    private boolean zzwm;
    private boolean zzwn;
    private String zzwo;

    public zzdg(String str) {
        this(str, false);
    }

    private zzdg(String str, boolean z) {
        Preconditions.checkNotEmpty(str, "The log tag cannot be null or empty.");
        this.mTag = str;
        this.zzwl = str.length() <= 23;
        this.zzwm = false;
        this.zzwn = false;
    }

    private final String zza(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.zzwo)) {
            return str;
        }
        String valueOf = String.valueOf(this.zzwo);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final boolean zzcy() {
        if (!this.zzwm) {
            return this.zzwl && Log.isLoggable(this.mTag, 3);
        }
        return true;
    }

    /* renamed from: d */
    public final void mo6870d(String str, Object... objArr) {
        if (zzcy()) {
            Log.d(this.mTag, zza(str, objArr));
        }
    }

    /* renamed from: e */
    public final void mo6871e(String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr));
    }

    /* renamed from: i */
    public final void mo6872i(String str, Object... objArr) {
        Log.i(this.mTag, zza(str, objArr));
    }

    /* renamed from: w */
    public final void mo6873w(String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr));
    }

    public final void zza(Throwable th, String str, Object... objArr) {
        if (zzcy()) {
            Log.d(this.mTag, zza(str, objArr), th);
        }
    }

    public final void zzb(Throwable th, String str, Object... objArr) {
        Log.w(this.mTag, zza(str, objArr), th);
    }

    public final void zzc(Throwable th, String str, Object... objArr) {
        Log.e(this.mTag, zza(str, objArr), th);
    }

    public final void zzk(boolean z) {
        this.zzwm = true;
    }

    public final void zzt(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = null;
        } else {
            str2 = String.format("[%s] ", new Object[]{str});
        }
        this.zzwo = str2;
    }
}
