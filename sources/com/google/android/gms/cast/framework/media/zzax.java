package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

final class zzax implements RemoteMediaClient.MediaChannelResult {
    private final /* synthetic */ Status zzal;

    zzax(RemoteMediaClient.zzc zzc, Status status) {
        this.zzal = status;
    }

    public final JSONObject getCustomData() {
        return null;
    }

    public final Status getStatus() {
        return this.zzal;
    }
}
