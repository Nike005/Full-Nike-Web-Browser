package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class zzcg extends zzcm {
    private final List<zzdn> zzus = Collections.synchronizedList(new ArrayList());

    public zzcg(String str, String str2, String str3) {
        super(str, str2, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdn zzdn) {
        this.zzus.add(zzdn);
    }

    public void zzcm() {
        synchronized (this.zzus) {
            for (zzdn zzq : this.zzus) {
                zzq.zzq(2002);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final List<zzdn> zzcn() {
        return this.zzus;
    }
}
