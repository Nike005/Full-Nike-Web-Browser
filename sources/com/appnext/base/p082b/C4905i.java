package com.appnext.base.p082b;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Set;

/* renamed from: com.appnext.base.b.i */
public final class C4905i {
    private static final String TAG = "LibrarySettings";

    /* renamed from: fA */
    public static final String f4636fA = "_cycles";

    /* renamed from: fB */
    public static final String f4637fB = "google_ads_id";

    /* renamed from: fC */
    public static final String f4638fC = "lat";

    /* renamed from: fD */
    public static final String f4639fD = "lib_shared_preferences";

    /* renamed from: fE */
    private static final C4905i f4640fE = new C4905i();

    /* renamed from: fy */
    public static final String f4641fy = "_lastupdate";

    /* renamed from: fz */
    public static final String f4642fz = "_lastcollectedtime";

    /* renamed from: fx */
    private SharedPreferences f4643fx;
    private Context mContext;

    private C4905i() {
        Context context = C4901e.getContext();
        this.mContext = context;
        if (context != null) {
            this.f4643fx = context.getSharedPreferences(TAG, 0);
        }
    }

    /* renamed from: aR */
    public static C4905i m6591aR() {
        return f4640fE;
    }

    public final void clear() {
        this.f4643fx.edit().clear().apply();
    }

    public final void init(Context context) {
        if (context != null) {
            this.mContext = context;
            this.f4643fx = context.getSharedPreferences(f4639fD, 0);
        }
    }

    public final String getString(String str, String str2) {
        SharedPreferences sharedPreferences = this.f4643fx;
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }

    public final long getLong(String str, long j) {
        SharedPreferences sharedPreferences = this.f4643fx;
        return sharedPreferences != null ? sharedPreferences.getLong(str, j) : j;
    }

    public final int getInt(String str, int i) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(str, 0);
        }
        return 0;
    }

    public final boolean getBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.f4643fx;
        return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
    }

    public final void putLong(String str, long j) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j).apply();
        }
    }

    public final void putInt(String str, int i) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    public final void putBoolean(String str, boolean z) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, true).apply();
        }
    }

    public final void putString(String str, String str2) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    public final Set<String> getStringSet(String str, Set<String> set) {
        SharedPreferences sharedPreferences = this.f4643fx;
        return sharedPreferences != null ? sharedPreferences.getStringSet(str, set) : set;
    }

    public final void putStringSet(String str, Set<String> set) {
        SharedPreferences sharedPreferences = this.f4643fx;
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str);
            this.f4643fx.edit().putStringSet(str, set).apply();
        }
    }

    /* renamed from: aS */
    private SharedPreferences m6592aS() {
        return this.mContext.getSharedPreferences(TAG, 0);
    }
}
