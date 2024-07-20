package androidx.media2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.support.p064v4.media.session.MediaControllerCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

public final class SessionToken2 {
    static final String KEY_PACKAGE_NAME = "android.media.token.package_name";
    static final String KEY_SERVICE_NAME = "android.media.token.service_name";
    static final String KEY_SESSION_BINDER = "android.media.token.session_binder";
    static final String KEY_SESSION_ID = "android.media.token.session_id";
    static final String KEY_TOKEN_LEGACY = "android.media.token.LEGACY";
    static final String KEY_TYPE = "android.media.token.type";
    static final String KEY_UID = "android.media.token.uid";
    private static final int MSG_SEND_TOKEN2_FOR_LEGACY_SESSION = 1000;
    private static final String TAG = "SessionToken2";
    static final int TYPE_BROWSER_SERVICE_LEGACY = 101;
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    static final int UID_UNKNOWN = -1;
    private static final long WAIT_TIME_MS_FOR_SESSION_READY = 300;
    private final SessionToken2Impl mImpl;

    public interface OnSessionToken2CreatedListener {
        void onSessionToken2Created(MediaSessionCompat.Token token, SessionToken2 sessionToken2);
    }

    interface SessionToken2Impl {
        Object getBinder();

        ComponentName getComponentName();

        String getPackageName();

        String getServiceName();

        String getSessionId();

        int getType();

        int getUid();

        boolean isLegacySession();

        Bundle toBundle();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TokenType {
    }

    public SessionToken2(Context context, ComponentName componentName) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        int uid = getUid(packageManager, componentName.getPackageName());
        String sessionIdFromService = getSessionIdFromService(packageManager, MediaLibraryService2.SERVICE_INTERFACE, componentName);
        if (sessionIdFromService != null) {
            i = 2;
        } else {
            sessionIdFromService = getSessionIdFromService(packageManager, MediaSessionService2.SERVICE_INTERFACE, componentName);
            if (sessionIdFromService != null) {
                i = 1;
            } else {
                sessionIdFromService = getSessionIdFromService(packageManager, MediaBrowserServiceCompat.SERVICE_INTERFACE, componentName);
                i = 101;
            }
        }
        if (sessionIdFromService == null) {
            throw new IllegalArgumentException(componentName + " doesn't implement none of" + " MediaSessionService2, MediaLibraryService2, MediaBrowserService nor" + " MediaBrowserServiceCompat. Use service's full name.");
        } else if (i != 101) {
            this.mImpl = new SessionToken2ImplBase(componentName, uid, sessionIdFromService, i);
        } else {
            this.mImpl = new SessionToken2ImplLegacy(componentName, uid, sessionIdFromService);
        }
    }

    SessionToken2(SessionToken2Impl sessionToken2Impl) {
        this.mImpl = sessionToken2Impl;
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2)) {
            return false;
        }
        return this.mImpl.equals(((SessionToken2) obj).mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }

    public int getUid() {
        return this.mImpl.getUid();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    public String getServiceName() {
        return this.mImpl.getServiceName();
    }

    public ComponentName getComponentName() {
        return this.mImpl.getComponentName();
    }

    public String getId() {
        return this.mImpl.getSessionId();
    }

    public int getType() {
        return this.mImpl.getType();
    }

    public boolean isLegacySession() {
        return this.mImpl.isLegacySession();
    }

    public Object getBinder() {
        return this.mImpl.getBinder();
    }

    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }

    public static SessionToken2 fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt(KEY_TYPE, -1) == 100) {
            return new SessionToken2(SessionToken2ImplLegacy.fromBundle(bundle));
        }
        return new SessionToken2(SessionToken2ImplBase.fromBundle(bundle));
    }

    public static void createSessionToken2(Context context, MediaSessionCompat.Token token, Executor executor, OnSessionToken2CreatedListener onSessionToken2CreatedListener) {
        if (context == null) {
            throw new IllegalArgumentException("context shouldn't be null");
        } else if (token == null) {
            throw new IllegalArgumentException("token shouldn't be null");
        } else if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        } else if (onSessionToken2CreatedListener != null) {
            try {
                Bundle sessionToken2Bundle = token.getSessionToken2Bundle();
                if (sessionToken2Bundle != null) {
                    notifySessionToken2Created(executor, onSessionToken2CreatedListener, token, fromBundle(sessionToken2Bundle));
                    return;
                }
                MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(context, token);
                SessionToken2 sessionToken2 = new SessionToken2(new SessionToken2ImplLegacy(token, mediaControllerCompat.getPackageName(), getUid(context.getPackageManager(), mediaControllerCompat.getPackageName())));
                HandlerThread handlerThread = new HandlerThread(TAG);
                handlerThread.start();
                final OnSessionToken2CreatedListener onSessionToken2CreatedListener2 = onSessionToken2CreatedListener;
                final MediaControllerCompat mediaControllerCompat2 = mediaControllerCompat;
                final MediaSessionCompat.Token token2 = token;
                final SessionToken2 sessionToken22 = sessionToken2;
                final Executor executor2 = executor;
                final HandlerThread handlerThread2 = handlerThread;
                C38641 r0 = new Handler(handlerThread.getLooper()) {
                    public void handleMessage(Message message) {
                        synchronized (onSessionToken2CreatedListener2) {
                            if (message.what == 1000) {
                                mediaControllerCompat2.unregisterCallback((MediaControllerCompat.Callback) message.obj);
                                token2.setSessionToken2Bundle(sessionToken22.toBundle());
                                SessionToken2.notifySessionToken2Created(executor2, onSessionToken2CreatedListener2, token2, sessionToken22);
                                if (Build.VERSION.SDK_INT >= 18) {
                                    handlerThread2.quitSafely();
                                } else {
                                    handlerThread2.quit();
                                }
                            }
                        }
                    }
                };
                final OnSessionToken2CreatedListener onSessionToken2CreatedListener3 = onSessionToken2CreatedListener;
                final C38641 r2 = r0;
                final MediaControllerCompat mediaControllerCompat3 = mediaControllerCompat;
                final MediaSessionCompat.Token token3 = token;
                final SessionToken2 sessionToken23 = sessionToken2;
                final Executor executor3 = executor;
                final HandlerThread handlerThread3 = handlerThread;
                C38652 r02 = new MediaControllerCompat.Callback() {
                    public void onSessionReady() {
                        synchronized (onSessionToken2CreatedListener3) {
                            r2.removeMessages(1000);
                            mediaControllerCompat3.unregisterCallback(this);
                            if (token3.getSessionToken2Bundle() == null) {
                                token3.setSessionToken2Bundle(sessionToken23.toBundle());
                            }
                            SessionToken2.notifySessionToken2Created(executor3, onSessionToken2CreatedListener3, token3, SessionToken2.fromBundle(token3.getSessionToken2Bundle()));
                            if (Build.VERSION.SDK_INT >= 18) {
                                handlerThread3.quitSafely();
                            } else {
                                handlerThread3.quit();
                            }
                        }
                    }
                };
                synchronized (onSessionToken2CreatedListener) {
                    mediaControllerCompat.registerCallback(r02, r0);
                    r0.sendMessageDelayed(r0.obtainMessage(1000, r02), WAIT_TIME_MS_FOR_SESSION_READY);
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Failed to create session token2.", e);
            }
        } else {
            throw new IllegalArgumentException("listener shouldn't be null");
        }
    }

    public static String getSessionId(ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.serviceInfo == null) {
            return null;
        }
        if (resolveInfo.serviceInfo.metaData == null) {
            return "";
        }
        return resolveInfo.serviceInfo.metaData.getString(MediaSessionService2.SERVICE_META_DATA_SESSION_ID, "");
    }

    static void notifySessionToken2Created(Executor executor, final OnSessionToken2CreatedListener onSessionToken2CreatedListener, final MediaSessionCompat.Token token, final SessionToken2 sessionToken2) {
        executor.execute(new Runnable() {
            public void run() {
                onSessionToken2CreatedListener.onSessionToken2Created(token, sessionToken2);
            }
        });
    }

    private static String getSessionIdFromService(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices == null) {
            return null;
        }
        for (int i = 0; i < queryIntentServices.size(); i++) {
            ResolveInfo resolveInfo = queryIntentServices.get(i);
            if (resolveInfo != null && resolveInfo.serviceInfo != null && TextUtils.equals(resolveInfo.serviceInfo.name, componentName.getClassName())) {
                return getSessionId(resolveInfo);
            }
        }
        return null;
    }

    private static int getUid(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalArgumentException("Cannot find package " + str);
        }
    }
}
