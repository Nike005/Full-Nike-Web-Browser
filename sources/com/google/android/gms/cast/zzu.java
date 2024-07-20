package com.google.android.gms.cast;

import androidx.mediarouter.media.MediaRouter;

final class zzu extends MediaRouter.Callback {
    private final /* synthetic */ CastRemoteDisplayLocalService zzcg;

    zzu(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzcg = castRemoteDisplayLocalService;
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        String str;
        this.zzcg.zzb("onRouteUnselected");
        if (this.zzcg.zzbw == null) {
            castRemoteDisplayLocalService = this.zzcg;
            str = "onRouteUnselected, no device was selected";
        } else if (!CastDevice.getFromBundle(routeInfo.getExtras()).getDeviceId().equals(this.zzcg.zzbw.getDeviceId())) {
            castRemoteDisplayLocalService = this.zzcg;
            str = "onRouteUnselected, device does not match";
        } else {
            CastRemoteDisplayLocalService.stopService();
            return;
        }
        castRemoteDisplayLocalService.zzb(str);
    }
}
