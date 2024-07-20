package androidx.media2.session;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import androidx.collection.ArrayMap;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.common.MediaParcelUtils;
import androidx.media2.session.IMediaSessionService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionService;
import androidx.versionedparcelable.ParcelImpl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MediaSessionServiceImplBase implements MediaSessionService.MediaSessionServiceImpl {
    private static final boolean DEBUG = true;
    private static final String TAG = "MSS2ImplBase";
    MediaSessionService mInstance;
    private final Object mLock = new Object();
    private MediaNotificationHandler mNotificationHandler;
    private Map<String, MediaSession> mSessions = new ArrayMap();
    MediaSessionServiceStub mStub;

    MediaSessionServiceImplBase() {
    }

    public void onCreate(MediaSessionService mediaSessionService) {
        synchronized (this.mLock) {
            this.mInstance = mediaSessionService;
            this.mStub = new MediaSessionServiceStub(this);
            this.mNotificationHandler = new MediaNotificationHandler(mediaSessionService);
        }
    }

    public IBinder onBind(Intent intent) {
        MediaSessionService instance = getInstance();
        if (instance == null) {
            Log.w(TAG, "Service hasn't created before onBind()");
            return null;
        }
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != -2044151856) {
            if (hashCode == 1665850838 && action.equals(MediaBrowserServiceCompat.SERVICE_INTERFACE)) {
                c = 1;
            }
        } else if (action.equals(MediaSessionService.SERVICE_INTERFACE)) {
            c = 0;
        }
        if (c == 0) {
            return getServiceBinder();
        }
        if (c != 1) {
            return null;
        }
        MediaSession onGetSession = instance.onGetSession(new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(MediaBrowserServiceCompat.SERVICE_INTERFACE, 0, 0), false, (MediaSession.ControllerCb) null, (Bundle) null));
        if (onGetSession == null) {
            Log.d(TAG, "Rejecting incoming connection request from legacy media browsers.");
            return null;
        }
        addSession(onGetSession);
        return onGetSession.getLegacyBrowerServiceBinder();
    }

    public void onDestroy() {
        synchronized (this.mLock) {
            this.mInstance = null;
            if (this.mStub != null) {
                this.mStub.close();
                this.mStub = null;
            }
        }
    }

    public void addSession(MediaSession mediaSession) {
        MediaSession mediaSession2;
        MediaNotificationHandler mediaNotificationHandler;
        synchronized (this.mLock) {
            mediaSession2 = this.mSessions.get(mediaSession.getId());
            if (mediaSession2 != null) {
                if (mediaSession2 != mediaSession) {
                    throw new IllegalArgumentException("Session ID should be unique");
                }
            }
            this.mSessions.put(mediaSession.getId(), mediaSession);
        }
        if (mediaSession2 == null) {
            synchronized (this.mLock) {
                mediaNotificationHandler = this.mNotificationHandler;
            }
            mediaNotificationHandler.onPlayerStateChanged(mediaSession, mediaSession.getPlayer().getPlayerState());
            mediaSession.getCallback().setForegroundServiceEventCallback(mediaNotificationHandler);
        }
    }

    public void removeSession(MediaSession mediaSession) {
        synchronized (this.mLock) {
            this.mSessions.remove(mediaSession.getId());
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (!(intent == null || intent.getAction() == null)) {
            String action = intent.getAction();
            char c = 65535;
            if (action.hashCode() == 1997055314 && action.equals("android.intent.action.MEDIA_BUTTON")) {
                c = 0;
            }
            if (c == 0) {
                MediaSessionService instance = getInstance();
                if (instance == null) {
                    Log.wtf(TAG, "Service hasn't created");
                }
                MediaSession session = MediaSession.getSession(intent.getData());
                if (session == null) {
                    session = instance.onGetSession(new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo("android.intent.action.MEDIA_BUTTON", 0, 0), false, (MediaSession.ControllerCb) null, (Bundle) null));
                }
                if (session == null) {
                    Log.d(TAG, "Rejecting wake-up of the service from media key events.");
                } else {
                    KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                    if (keyEvent != null) {
                        session.getSessionCompat().getController().dispatchMediaButtonEvent(keyEvent);
                    }
                }
            }
        }
        return 1;
    }

    public MediaSessionService.MediaNotification onUpdateNotification(MediaSession mediaSession) {
        MediaNotificationHandler mediaNotificationHandler;
        synchronized (this.mLock) {
            mediaNotificationHandler = this.mNotificationHandler;
        }
        if (mediaNotificationHandler != null) {
            return mediaNotificationHandler.onUpdateNotification(mediaSession);
        }
        throw new IllegalStateException("Service hasn't created");
    }

    public List<MediaSession> getSessions() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            arrayList.addAll(this.mSessions.values());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public MediaSessionService getInstance() {
        MediaSessionService mediaSessionService;
        synchronized (this.mLock) {
            mediaSessionService = this.mInstance;
        }
        return mediaSessionService;
    }

    /* access modifiers changed from: package-private */
    public IBinder getServiceBinder() {
        IBinder asBinder;
        synchronized (this.mLock) {
            asBinder = this.mStub != null ? this.mStub.asBinder() : null;
        }
        return asBinder;
    }

    private static final class MediaSessionServiceStub extends IMediaSessionService.Stub implements AutoCloseable {
        final Handler mHandler;
        final MediaSessionManager mMediaSessionManager;
        final WeakReference<MediaSessionServiceImplBase> mServiceImpl;

        MediaSessionServiceStub(MediaSessionServiceImplBase mediaSessionServiceImplBase) {
            this.mServiceImpl = new WeakReference<>(mediaSessionServiceImplBase);
            this.mHandler = new Handler(mediaSessionServiceImplBase.getInstance().getMainLooper());
            this.mMediaSessionManager = MediaSessionManager.getSessionManager(mediaSessionServiceImplBase.getInstance());
        }

        public void connect(IMediaController iMediaController, ParcelImpl parcelImpl) {
            final String str;
            final Bundle bundle;
            if (((MediaSessionServiceImplBase) this.mServiceImpl.get()) == null) {
                Log.d(MediaSessionServiceImplBase.TAG, "ServiceImpl isn't available");
                return;
            }
            int callingPid = Binder.getCallingPid();
            final int callingUid = Binder.getCallingUid();
            long clearCallingIdentity = Binder.clearCallingIdentity();
            ConnectionRequest connectionRequest = (ConnectionRequest) MediaParcelUtils.fromParcelable(parcelImpl);
            if (callingPid == 0) {
                callingPid = connectionRequest.getPid();
            }
            final int i = callingPid;
            if (parcelImpl == null) {
                str = null;
            } else {
                str = connectionRequest.getPackageName();
            }
            if (parcelImpl == null) {
                bundle = null;
            } else {
                bundle = connectionRequest.getConnectionHints();
            }
            try {
                final IMediaController iMediaController2 = iMediaController;
                this.mHandler.post(new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:41:0x00a6  */
                    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b2  */
                    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r14 = this;
                            java.lang.String r0 = "Notifying the controller of its disconnection"
                            java.lang.String r1 = "MSS2ImplBase"
                            r2 = 0
                            r3 = 1
                            androidx.media2.session.MediaSessionServiceImplBase$MediaSessionServiceStub r4 = androidx.media2.session.MediaSessionServiceImplBase.MediaSessionServiceStub.this     // Catch:{ all -> 0x00af }
                            java.lang.ref.WeakReference<androidx.media2.session.MediaSessionServiceImplBase> r4 = r4.mServiceImpl     // Catch:{ all -> 0x00af }
                            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x00af }
                            androidx.media2.session.MediaSessionServiceImplBase r4 = (androidx.media2.session.MediaSessionServiceImplBase) r4     // Catch:{ all -> 0x00af }
                            if (r4 != 0) goto L_0x0020
                            java.lang.String r4 = "ServiceImpl isn't available"
                            android.util.Log.d(r1, r4)     // Catch:{ all -> 0x00af }
                            android.util.Log.d(r1, r0)
                            androidx.media2.session.IMediaController r0 = r7     // Catch:{ RemoteException -> 0x001f }
                            r0.onDisconnected(r2)     // Catch:{ RemoteException -> 0x001f }
                        L_0x001f:
                            return
                        L_0x0020:
                            androidx.media2.session.MediaSessionService r4 = r4.getInstance()     // Catch:{ all -> 0x00af }
                            if (r4 != 0) goto L_0x0034
                            java.lang.String r4 = "Service isn't available"
                            android.util.Log.d(r1, r4)     // Catch:{ all -> 0x00af }
                            android.util.Log.d(r1, r0)
                            androidx.media2.session.IMediaController r0 = r7     // Catch:{ RemoteException -> 0x0033 }
                            r0.onDisconnected(r2)     // Catch:{ RemoteException -> 0x0033 }
                        L_0x0033:
                            return
                        L_0x0034:
                            androidx.media.MediaSessionManager$RemoteUserInfo r5 = new androidx.media.MediaSessionManager$RemoteUserInfo     // Catch:{ all -> 0x00af }
                            java.lang.String r6 = r3     // Catch:{ all -> 0x00af }
                            int r7 = r4     // Catch:{ all -> 0x00af }
                            int r8 = r5     // Catch:{ all -> 0x00af }
                            r5.<init>(r6, r7, r8)     // Catch:{ all -> 0x00af }
                            androidx.media2.session.MediaSessionServiceImplBase$MediaSessionServiceStub r6 = androidx.media2.session.MediaSessionServiceImplBase.MediaSessionServiceStub.this     // Catch:{ all -> 0x00af }
                            androidx.media.MediaSessionManager r6 = r6.mMediaSessionManager     // Catch:{ all -> 0x00af }
                            boolean r6 = r6.isTrustedForMediaControl(r5)     // Catch:{ all -> 0x00af }
                            androidx.media2.session.MediaSession$ControllerInfo r7 = new androidx.media2.session.MediaSession$ControllerInfo     // Catch:{ all -> 0x00af }
                            r8 = 0
                            android.os.Bundle r9 = r6     // Catch:{ all -> 0x00af }
                            r7.<init>(r5, r6, r8, r9)     // Catch:{ all -> 0x00af }
                            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00af }
                            r5.<init>()     // Catch:{ all -> 0x00af }
                            java.lang.String r6 = "Handling incoming connection request from the controller="
                            r5.append(r6)     // Catch:{ all -> 0x00af }
                            r5.append(r7)     // Catch:{ all -> 0x00af }
                            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00af }
                            android.util.Log.d(r1, r5)     // Catch:{ all -> 0x00af }
                            androidx.media2.session.MediaSession r8 = r4.onGetSession(r7)     // Catch:{ Exception -> 0x009e }
                            if (r8 != 0) goto L_0x0086
                            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
                            r4.<init>()     // Catch:{ Exception -> 0x009e }
                            java.lang.String r5 = "Rejecting incoming connection request from the controller="
                            r4.append(r5)     // Catch:{ Exception -> 0x009e }
                            r4.append(r7)     // Catch:{ Exception -> 0x009e }
                            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x009e }
                            android.util.Log.w(r1, r4)     // Catch:{ Exception -> 0x009e }
                            android.util.Log.d(r1, r0)
                            androidx.media2.session.IMediaController r0 = r7     // Catch:{ RemoteException -> 0x0085 }
                            r0.onDisconnected(r2)     // Catch:{ RemoteException -> 0x0085 }
                        L_0x0085:
                            return
                        L_0x0086:
                            r4.addSession(r8)     // Catch:{ Exception -> 0x009e }
                            androidx.media2.session.IMediaController r9 = r7     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            java.lang.String r10 = r3     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            int r11 = r4     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            int r12 = r5     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            android.os.Bundle r13 = r6     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            r8.handleControllerConnectionFromService(r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x009b, all -> 0x0098 }
                            r3 = 0
                            goto L_0x00a4
                        L_0x0098:
                            r4 = move-exception
                            r3 = 0
                            goto L_0x00b0
                        L_0x009b:
                            r4 = move-exception
                            r3 = 0
                            goto L_0x009f
                        L_0x009e:
                            r4 = move-exception
                        L_0x009f:
                            java.lang.String r5 = "Failed to add a session to session service"
                            android.util.Log.w(r1, r5, r4)     // Catch:{ all -> 0x00af }
                        L_0x00a4:
                            if (r3 == 0) goto L_0x00ae
                            android.util.Log.d(r1, r0)
                            androidx.media2.session.IMediaController r0 = r7     // Catch:{ RemoteException -> 0x00ae }
                            r0.onDisconnected(r2)     // Catch:{ RemoteException -> 0x00ae }
                        L_0x00ae:
                            return
                        L_0x00af:
                            r4 = move-exception
                        L_0x00b0:
                            if (r3 == 0) goto L_0x00ba
                            android.util.Log.d(r1, r0)
                            androidx.media2.session.IMediaController r0 = r7     // Catch:{ RemoteException -> 0x00ba }
                            r0.onDisconnected(r2)     // Catch:{ RemoteException -> 0x00ba }
                        L_0x00ba:
                            throw r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaSessionServiceImplBase.MediaSessionServiceStub.C43171.run():void");
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }

        public void close() {
            this.mServiceImpl.clear();
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }
}
