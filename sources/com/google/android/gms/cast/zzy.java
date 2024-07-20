package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;

final class zzy implements Runnable {
    private final /* synthetic */ CastRemoteDisplayLocalService zzcg;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcj;

    zzy(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zzcg = castRemoteDisplayLocalService;
        this.zzcj = notificationSettings;
    }

    public final void run() {
        this.zzcg.zza(this.zzcj);
    }
}
