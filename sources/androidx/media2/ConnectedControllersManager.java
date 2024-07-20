package androidx.media2;

import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media.MediaSessionManager;
import androidx.media2.MediaSession2;
import java.util.ArrayList;
import java.util.List;

class ConnectedControllersManager<T> {
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = "MS2ControllerMgr";
    private final ArrayMap<MediaSession2.ControllerInfo, SessionCommandGroup2> mAllowedCommandGroupMap = new ArrayMap<>();
    private final ArrayMap<T, MediaSession2.ControllerInfo> mControllers = new ArrayMap<>();
    private final ArrayMap<MediaSession2.ControllerInfo, T> mKeys = new ArrayMap<>();
    private final Object mLock = new Object();
    final MediaSession2.MediaSession2Impl mSessionImpl;

    ConnectedControllersManager(MediaSession2.MediaSession2Impl mediaSession2Impl) {
        this.mSessionImpl = mediaSession2Impl;
    }

    public void addController(T t, MediaSession2.ControllerInfo controllerInfo, SessionCommandGroup2 sessionCommandGroup2) {
        if (t != null && controllerInfo != null) {
            synchronized (this.mLock) {
                this.mAllowedCommandGroupMap.put(controllerInfo, sessionCommandGroup2);
                this.mControllers.put(t, controllerInfo);
                this.mKeys.put(controllerInfo, t);
            }
        } else if (DEBUG) {
            throw new IllegalArgumentException("key nor controller shouldn't be null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateAllowedCommands(androidx.media2.MediaSession2.ControllerInfo r4, androidx.media2.SessionCommandGroup2 r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            androidx.collection.ArrayMap<androidx.media2.MediaSession2$ControllerInfo, androidx.media2.SessionCommandGroup2> r1 = r3.mAllowedCommandGroupMap     // Catch:{ all -> 0x002e }
            boolean r1 = r1.containsKey(r4)     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x0027
            boolean r5 = DEBUG     // Catch:{ all -> 0x002e }
            if (r5 == 0) goto L_0x0025
            java.lang.String r5 = "MS2ControllerMgr"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x002e }
            r1.<init>()     // Catch:{ all -> 0x002e }
            java.lang.String r2 = "Cannot update allowed command for disconnected controller "
            r1.append(r2)     // Catch:{ all -> 0x002e }
            r1.append(r4)     // Catch:{ all -> 0x002e }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x002e }
            android.util.Log.d(r5, r4)     // Catch:{ all -> 0x002e }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0027:
            androidx.collection.ArrayMap<androidx.media2.MediaSession2$ControllerInfo, androidx.media2.SessionCommandGroup2> r1 = r3.mAllowedCommandGroupMap     // Catch:{ all -> 0x002e }
            r1.put(r4, r5)     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.ConnectedControllersManager.updateAllowedCommands(androidx.media2.MediaSession2$ControllerInfo, androidx.media2.SessionCommandGroup2):void");
    }

    public void removeController(T t) {
        MediaSession2.ControllerInfo remove;
        if (t != null) {
            synchronized (this.mLock) {
                remove = this.mControllers.remove(t);
                this.mKeys.remove(remove);
                this.mAllowedCommandGroupMap.remove(remove);
            }
            notifyDisconnected(remove);
        }
    }

    public void removeController(MediaSession2.ControllerInfo controllerInfo) {
        if (controllerInfo != null) {
            synchronized (this.mLock) {
                this.mControllers.remove(this.mKeys.remove(controllerInfo));
                this.mAllowedCommandGroupMap.remove(controllerInfo);
            }
            notifyDisconnected(controllerInfo);
        }
    }

    private void notifyDisconnected(final MediaSession2.ControllerInfo controllerInfo) {
        if (DEBUG) {
            Log.d(TAG, "Controller " + controllerInfo + " is disconnected");
        }
        if (!this.mSessionImpl.isClosed() && controllerInfo != null) {
            this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    if (!ConnectedControllersManager.this.mSessionImpl.isClosed()) {
                        ConnectedControllersManager.this.mSessionImpl.getCallback().onDisconnected(ConnectedControllersManager.this.mSessionImpl.getInstance(), controllerInfo);
                    }
                }
            });
        }
    }

    public List<MediaSession2.ControllerInfo> getConnectedControllers() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            for (int i = 0; i < this.mControllers.size(); i++) {
                arrayList.add(this.mControllers.valueAt(i));
            }
        }
        return arrayList;
    }

    public boolean isConnected(MediaSession2.ControllerInfo controllerInfo) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mKeys.get(controllerInfo) != null;
        }
        return z;
    }

    public boolean isAllowedCommand(MediaSession2.ControllerInfo controllerInfo, SessionCommand2 sessionCommand2) {
        SessionCommandGroup2 sessionCommandGroup2;
        synchronized (this.mLock) {
            sessionCommandGroup2 = this.mAllowedCommandGroupMap.get(controllerInfo);
        }
        return sessionCommandGroup2 != null && sessionCommandGroup2.hasCommand(sessionCommand2);
    }

    public boolean isAllowedCommand(MediaSession2.ControllerInfo controllerInfo, int i) {
        SessionCommandGroup2 sessionCommandGroup2;
        synchronized (this.mLock) {
            sessionCommandGroup2 = this.mAllowedCommandGroupMap.get(controllerInfo);
        }
        return sessionCommandGroup2 != null && sessionCommandGroup2.hasCommand(i);
    }

    public MediaSession2.ControllerInfo getController(T t) {
        if (t == null) {
            return null;
        }
        synchronized (this.mLock) {
            if (t instanceof MediaSessionManager.RemoteUserInfo) {
                MediaSessionManager.RemoteUserInfo remoteUserInfo = (MediaSessionManager.RemoteUserInfo) t;
                int i = 0;
                while (i < this.mControllers.size()) {
                    MediaSessionManager.RemoteUserInfo remoteUserInfo2 = (MediaSessionManager.RemoteUserInfo) this.mControllers.keyAt(i);
                    if (!remoteUserInfo.getPackageName().equals(remoteUserInfo2.getPackageName()) || remoteUserInfo.getUid() != remoteUserInfo2.getUid()) {
                        i++;
                    } else {
                        MediaSession2.ControllerInfo valueAt = this.mControllers.valueAt(i);
                        return valueAt;
                    }
                }
                return null;
            }
            MediaSession2.ControllerInfo controllerInfo = this.mControllers.get(t);
            return controllerInfo;
        }
    }
}
