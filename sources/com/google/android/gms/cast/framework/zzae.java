package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzae<T extends Session> extends zzy {
    private final SessionManagerListener<T> zzik;
    private final Class<T> zzil;

    public zzae(SessionManagerListener<T> sessionManagerListener, Class<T> cls) {
        this.zzik = sessionManagerListener;
        this.zzil = cls;
    }

    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionStarting((Session) this.zzil.cast(session));
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionStartFailed((Session) this.zzil.cast(session), i);
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionStarted((Session) this.zzil.cast(session), str);
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionResumed((Session) this.zzil.cast(session), z);
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionEnding((Session) this.zzil.cast(session));
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionEnded((Session) this.zzil.cast(session), i);
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionResuming((Session) this.zzil.cast(session), str);
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionResumeFailed((Session) this.zzil.cast(session), i);
        }
    }

    public final void zzd(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (this.zzil.isInstance(session) && (sessionManagerListener = this.zzik) != null) {
            sessionManagerListener.onSessionSuspended((Session) this.zzil.cast(session), i);
        }
    }

    public final int zzm() {
        return 12451009;
    }

    public final IObjectWrapper zzn() {
        return ObjectWrapper.wrap(this.zzik);
    }
}
