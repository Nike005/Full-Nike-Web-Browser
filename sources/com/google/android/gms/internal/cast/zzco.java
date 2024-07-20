package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

final class zzco implements Cast.ApplicationConnectionResult {
    private final Status zzge;
    private final ApplicationMetadata zzvr;
    private final String zzvs;
    private final String zzvt;
    private final boolean zzvu;

    public zzco(Status status) {
        this(status, (ApplicationMetadata) null, (String) null, (String) null, false);
    }

    public zzco(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zzge = status;
        this.zzvr = applicationMetadata;
        this.zzvs = str;
        this.zzvt = str2;
        this.zzvu = z;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzvr;
    }

    public final String getApplicationStatus() {
        return this.zzvs;
    }

    public final String getSessionId() {
        return this.zzvt;
    }

    public final Status getStatus() {
        return this.zzge;
    }

    public final boolean getWasLaunched() {
        return this.zzvu;
    }
}
