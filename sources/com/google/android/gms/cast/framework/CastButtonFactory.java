package com.google.android.gms.cast.framework;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdg;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CastButtonFactory {
    private static final zzdg zzbd = new zzdg("CastButtonFactory");
    private static final List<WeakReference<MenuItem>> zzgp = new ArrayList();
    private static final List<WeakReference<MediaRouteButton>> zzgq = new ArrayList();

    private CastButtonFactory() {
    }

    public static MenuItem setUpMediaRouteButton(Context context, Menu menu, int i) {
        return zza(context, menu, i, (MediaRouteDialogFactory) null);
    }

    public static void setUpMediaRouteButton(Context context, MediaRouteButton mediaRouteButton) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (mediaRouteButton != null) {
            zza(context, mediaRouteButton, (MediaRouteDialogFactory) null);
            zzgq.add(new WeakReference(mediaRouteButton));
        }
    }

    private static MenuItem zza(Context context, Menu menu, int i, MediaRouteDialogFactory mediaRouteDialogFactory) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(menu);
        MenuItem findItem = menu.findItem(i);
        if (findItem != null) {
            try {
                zza(context, findItem, (MediaRouteDialogFactory) null);
                zzgp.add(new WeakReference(findItem));
                return findItem;
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", new Object[]{Integer.valueOf(i)}));
            }
        } else {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", new Object[]{Integer.valueOf(i)}));
        }
    }

    public static void zza(Context context) {
        for (WeakReference next : zzgp) {
            try {
                if (next.get() != null) {
                    zza(context, (MenuItem) next.get(), (MediaRouteDialogFactory) null);
                }
            } catch (IllegalArgumentException e) {
                zzbd.mo6873w("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e);
            }
        }
        for (WeakReference next2 : zzgq) {
            if (next2.get() != null) {
                zza(context, (MediaRouteButton) next2.get(), (MediaRouteDialogFactory) null);
            }
        }
    }

    private static void zza(Context context, MenuItem menuItem, MediaRouteDialogFactory mediaRouteDialogFactory) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mediaRouteActionProvider != null) {
            CastContext zzb = CastContext.zzb(context);
            if (zzb != null) {
                mediaRouteActionProvider.setRouteSelector(zzb.getMergedSelector());
                return;
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    private static void zza(Context context, MediaRouteButton mediaRouteButton, MediaRouteDialogFactory mediaRouteDialogFactory) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        CastContext zzb = CastContext.zzb(context);
        if (zzb != null) {
            mediaRouteButton.setRouteSelector(zzb.getMergedSelector());
        }
    }
}
