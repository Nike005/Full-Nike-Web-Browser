package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zze;

public abstract class Session {
    private static final zzdg zzbd = new zzdg("Session");
    private final zzt zzif;
    private final zza zzig;

    private class zza extends zzac {
        private zza() {
        }

        public final void end(boolean z) {
            Session.this.end(z);
        }

        public final long getSessionRemainingTimeMs() {
            return Session.this.getSessionRemainingTimeMs();
        }

        public final void onResuming(Bundle bundle) {
            Session.this.onResuming(bundle);
        }

        public final void onStarting(Bundle bundle) {
            Session.this.onStarting(bundle);
        }

        public final void resume(Bundle bundle) {
            Session.this.resume(bundle);
        }

        public final void start(Bundle bundle) {
            Session.this.start(bundle);
        }

        public final IObjectWrapper zzaa() {
            return ObjectWrapper.wrap(Session.this);
        }

        public final int zzm() {
            return 12451009;
        }
    }

    protected Session(Context context, String str, String str2) {
        zza zza2 = new zza();
        this.zzig = zza2;
        this.zzif = zze.zza(context, str, str2, (zzab) zza2);
    }

    /* access modifiers changed from: protected */
    public abstract void end(boolean z);

    public final String getCategory() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.getCategory();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getCategory", zzt.class.getSimpleName());
            return null;
        }
    }

    public final String getSessionId() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.getSessionId();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getSessionId", zzt.class.getSimpleName());
            return null;
        }
    }

    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return 0;
    }

    public boolean isConnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isConnected();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isConnected", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isConnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isConnecting();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isConnecting", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isDisconnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isDisconnected();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isDisconnected", zzt.class.getSimpleName());
            return true;
        }
    }

    public boolean isDisconnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isDisconnecting();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isDisconnecting", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isResuming() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isResuming();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isResuming", zzt.class.getSimpleName());
            return false;
        }
    }

    public boolean isSuspended() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzif.isSuspended();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isSuspended", zzt.class.getSimpleName());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailedToResumeSession(int i) {
        try {
            this.zzif.notifyFailedToResumeSession(i);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifyFailedToResumeSession", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailedToStartSession(int i) {
        try {
            this.zzif.notifyFailedToStartSession(i);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifyFailedToStartSession", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionEnded(int i) {
        try {
            this.zzif.notifySessionEnded(i);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifySessionEnded", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionResumed(boolean z) {
        try {
            this.zzif.notifySessionResumed(z);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifySessionResumed", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionStarted(String str) {
        try {
            this.zzif.notifySessionStarted(str);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifySessionStarted", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public final void notifySessionSuspended(int i) {
        try {
            this.zzif.notifySessionSuspended(i);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "notifySessionSuspended", zzt.class.getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public void onResuming(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void onStarting(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public abstract void resume(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void start(Bundle bundle);

    public final IObjectWrapper zzy() {
        try {
            return this.zzif.zzy();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getWrappedObject", zzt.class.getSimpleName());
            return null;
        }
    }
}
