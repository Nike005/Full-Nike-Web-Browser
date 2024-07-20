package com.google.android.gms.cast.framework.media;

import android.app.Dialog;
import android.content.DialogInterface;

final class zzaz implements DialogInterface.OnClickListener {
    private final /* synthetic */ TracksChooserDialogFragment zzoq;

    zzaz(TracksChooserDialogFragment tracksChooserDialogFragment) {
        this.zzoq = tracksChooserDialogFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.zzoq.zzon != null) {
            this.zzoq.zzon.cancel();
            Dialog unused = this.zzoq.zzon = null;
        }
    }
}
