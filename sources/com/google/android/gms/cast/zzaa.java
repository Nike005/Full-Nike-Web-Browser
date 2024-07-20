package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zzaa implements OnCompleteListener<Void> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzcg;

    zzaa(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzcg = castRemoteDisplayLocalService;
    }

    public final void onComplete(Task<Void> task) {
        if (!task.isSuccessful()) {
            this.zzcg.zzb("Unable to stop the remote display, result unsuccessful");
            if (this.zzcg.zzbq.get() != null) {
                ((CastRemoteDisplayLocalService.Callbacks) this.zzcg.zzbq.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        } else {
            this.zzcg.zzb("remote display stopped");
        }
        Display unused = this.zzcg.zzbx = null;
    }
}
