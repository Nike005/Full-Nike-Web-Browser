package com.google.android.gms.cast.framework.media;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

public abstract class NotificationActionsProvider {
    private final Context zzgs;
    private final zzf zzma = new zza();

    private class zza extends zzg {
        private zza() {
        }

        public final int[] getCompactViewActionIndices() {
            return NotificationActionsProvider.this.getCompactViewActionIndices();
        }

        public final List<NotificationAction> getNotificationActions() {
            return NotificationActionsProvider.this.getNotificationActions();
        }

        public final IObjectWrapper zzaw() {
            return ObjectWrapper.wrap(NotificationActionsProvider.this);
        }

        public final int zzm() {
            return 12451009;
        }
    }

    public NotificationActionsProvider(Context context) {
        this.zzgs = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        return this.zzgs;
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    public final zzf zzbi() {
        return this.zzma;
    }
}
