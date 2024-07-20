package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.support.p064v4.media.session.MediaSessionCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzw extends zzk {
    private final MediaRouter zzca;
    private final Map<MediaRouteSelector, Set<MediaRouter.Callback>> zzjc = new HashMap();

    public zzw(MediaRouter mediaRouter) {
        this.zzca = mediaRouter;
    }

    public final void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
        this.zzca.setMediaSessionCompat(mediaSessionCompat);
    }

    public final void zza(Bundle bundle, int i) {
        MediaRouteSelector fromBundle = MediaRouteSelector.fromBundle(bundle);
        for (MediaRouter.Callback addCallback : this.zzjc.get(fromBundle)) {
            this.zzca.addCallback(fromBundle, addCallback, i);
        }
    }

    public final void zza(Bundle bundle, zzl zzl) {
        MediaRouteSelector fromBundle = MediaRouteSelector.fromBundle(bundle);
        if (!this.zzjc.containsKey(fromBundle)) {
            this.zzjc.put(fromBundle, new HashSet());
        }
        this.zzjc.get(fromBundle).add(new zzv(zzl));
    }

    public final void zzaj() {
        MediaRouter mediaRouter = this.zzca;
        mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
    }

    public final boolean zzak() {
        return this.zzca.getSelectedRoute().getId().equals(this.zzca.getDefaultRoute().getId());
    }

    public final String zzal() {
        return this.zzca.getSelectedRoute().getId();
    }

    public final void zzam() {
        for (Set<MediaRouter.Callback> it : this.zzjc.values()) {
            for (MediaRouter.Callback removeCallback : it) {
                this.zzca.removeCallback(removeCallback);
            }
        }
        this.zzjc.clear();
    }

    public final boolean zzb(Bundle bundle, int i) {
        return this.zzca.isRouteAvailable(MediaRouteSelector.fromBundle(bundle), i);
    }

    public final void zzd(Bundle bundle) {
        for (MediaRouter.Callback removeCallback : this.zzjc.get(MediaRouteSelector.fromBundle(bundle))) {
            this.zzca.removeCallback(removeCallback);
        }
    }

    public final void zzk(String str) {
        for (MediaRouter.RouteInfo next : this.zzca.getRoutes()) {
            if (next.getId().equals(str)) {
                this.zzca.selectRoute(next);
                return;
            }
        }
    }

    public final Bundle zzl(String str) {
        for (MediaRouter.RouteInfo next : this.zzca.getRoutes()) {
            if (next.getId().equals(str)) {
                return next.getExtras();
            }
        }
        return null;
    }

    public final int zzm() {
        return 12451009;
    }
}
