package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.Preconditions;

public final class zzv extends MediaRouter.Callback {
    private static final zzdg zzbd = new zzdg("MediaRouterCallback");
    private final zzl zzjb;

    public zzv(zzl zzl) {
        this.zzjb = (zzl) Preconditions.checkNotNull(zzl);
    }

    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjb.zza(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onRouteAdded", zzl.class.getSimpleName());
        }
    }

    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjb.zzb(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onRouteChanged", zzl.class.getSimpleName());
        }
    }

    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjb.zzc(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onRouteRemoved", zzl.class.getSimpleName());
        }
    }

    public final void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjb.zzd(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onRouteSelected", zzl.class.getSimpleName());
        }
    }

    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        try {
            this.zzjb.zza(routeInfo.getId(), routeInfo.getExtras(), i);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onRouteUnselected", zzl.class.getSimpleName());
        }
    }
}
