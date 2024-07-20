package androidx.media2.session;

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
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

public final class SessionToken implements VersionedParcelable {
    private static final int MSG_SEND_TOKEN2_FOR_LEGACY_SESSION = 1000;
    private static final String TAG = "SessionToken";
    static final int TYPE_BROWSER_SERVICE_LEGACY = 101;
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    private static final long WAIT_TIME_MS_FOR_SESSION_READY = 300;
    SessionTokenImpl mImpl;

    public interface OnSessionTokenCreatedListener {
        void onSessionTokenCreated(MediaSessionCompat.Token token, SessionToken sessionToken);
    }

    interface SessionTokenImpl extends VersionedParcelable {
        Object getBinder();

        ComponentName getComponentName();

        Bundle getExtras();

        String getPackageName();

        String getServiceName();

        int getType();

        int getUid();

        boolean isLegacySession();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TokenType {
    }

    public SessionToken(Context context, ComponentName componentName) {
        int i;
        if (context == null) {
            throw new NullPointerException("context shouldn't be null");
        } else if (componentName != null) {
            PackageManager packageManager = context.getPackageManager();
            int uid = getUid(packageManager, componentName.getPackageName());
            if (isInterfaceDeclared(packageManager, MediaLibraryService.SERVICE_INTERFACE, componentName)) {
                i = 2;
            } else if (isInterfaceDeclared(packageManager, MediaSessionService.SERVICE_INTERFACE, componentName)) {
                i = 1;
            } else if (isInterfaceDeclared(packageManager, MediaBrowserServiceCompat.SERVICE_INTERFACE, componentName)) {
                i = 101;
            } else {
                throw new IllegalArgumentException(componentName + " doesn't implement none of MediaSessionService, MediaLibraryService, MediaBrowserService nor MediaBrowserServiceCompat. Use service's full name");
            }
            if (i != 101) {
                this.mImpl = new SessionTokenImplBase(componentName, uid, i);
            } else {
                this.mImpl = new SessionTokenImplLegacy(componentName, uid);
            }
        } else {
            throw new NullPointerException("serviceComponent shouldn't be null");
        }
    }

    SessionToken(SessionTokenImpl sessionTokenImpl) {
        this.mImpl = sessionTokenImpl;
    }

    SessionToken() {
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken)) {
            return false;
        }
        return this.mImpl.equals(((SessionToken) obj).mImpl);
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

    public int getType() {
        return this.mImpl.getType();
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public boolean isLegacySession() {
        return this.mImpl.isLegacySession();
    }

    public Object getBinder() {
        return this.mImpl.getBinder();
    }

    public static void createSessionToken(Context context, MediaSessionCompat.Token token, Executor executor, OnSessionTokenCreatedListener onSessionTokenCreatedListener) {
        MediaSessionCompat.Token token2 = token;
        Executor executor2 = executor;
        OnSessionTokenCreatedListener onSessionTokenCreatedListener2 = onSessionTokenCreatedListener;
        if (context == null) {
            throw new NullPointerException("context shouldn't be null");
        } else if (token2 == null) {
            throw new NullPointerException("compatToken shouldn't be null");
        } else if (executor2 == null) {
            throw new NullPointerException("executor shouldn't be null");
        } else if (onSessionTokenCreatedListener2 != null) {
            VersionedParcelable session2Token = token.getSession2Token();
            if (session2Token instanceof SessionToken) {
                notifySessionTokenCreated(executor2, onSessionTokenCreatedListener2, token2, (SessionToken) session2Token);
                return;
            }
            MediaControllerCompat createMediaControllerCompat = createMediaControllerCompat(context, token);
            if (createMediaControllerCompat == null) {
                Log.e(TAG, "Failed to create session token2.");
                return;
            }
            String packageName = createMediaControllerCompat.getPackageName();
            int uid = getUid(context.getPackageManager(), packageName);
            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            final OnSessionTokenCreatedListener onSessionTokenCreatedListener3 = onSessionTokenCreatedListener;
            final MediaControllerCompat mediaControllerCompat = createMediaControllerCompat;
            final MediaSessionCompat.Token token3 = token;
            final String str = packageName;
            final int i = uid;
            final Executor executor3 = executor;
            C43651 r16 = r1;
            final HandlerThread handlerThread2 = handlerThread;
            C43651 r1 = new Handler(handlerThread.getLooper()) {
                public void handleMessage(Message message) {
                    synchronized (onSessionTokenCreatedListener3) {
                        if (message.what == 1000) {
                            mediaControllerCompat.unregisterCallback((MediaControllerCompat.Callback) message.obj);
                            SessionToken sessionToken = new SessionToken(new SessionTokenImplLegacy(token3, str, i));
                            token3.setSession2Token(sessionToken);
                            SessionToken.notifySessionTokenCreated(executor3, onSessionTokenCreatedListener3, token3, sessionToken);
                            SessionToken.quitHandlerThread(handlerThread2);
                        }
                    }
                }
            };
            final OnSessionTokenCreatedListener onSessionTokenCreatedListener4 = onSessionTokenCreatedListener;
            final C43651 r3 = r16;
            C43662 r0 = r1;
            final HandlerThread handlerThread3 = handlerThread;
            C43662 r12 = new MediaControllerCompat.Callback() {
                public void onSessionReady() {
                    SessionToken sessionToken;
                    synchronized (onSessionTokenCreatedListener4) {
                        r3.removeMessages(1000);
                        mediaControllerCompat.unregisterCallback(this);
                        if (token3.getSession2Token() instanceof SessionToken) {
                            sessionToken = (SessionToken) token3.getSession2Token();
                        } else {
                            sessionToken = new SessionToken(new SessionTokenImplLegacy(token3, str, i));
                            token3.setSession2Token(sessionToken);
                        }
                        SessionToken.notifySessionTokenCreated(executor3, onSessionTokenCreatedListener4, token3, sessionToken);
                        SessionToken.quitHandlerThread(handlerThread3);
                    }
                }
            };
            synchronized (onSessionTokenCreatedListener) {
                C43651 r13 = r16;
                createMediaControllerCompat.registerCallback(r0, r13);
                r13.sendMessageDelayed(r13.obtainMessage(1000, r0), WAIT_TIME_MS_FOR_SESSION_READY);
            }
        } else {
            throw new NullPointerException("listener shouldn't be null");
        }
    }

    static void notifySessionTokenCreated(Executor executor, final OnSessionTokenCreatedListener onSessionTokenCreatedListener, final MediaSessionCompat.Token token, final SessionToken sessionToken) {
        executor.execute(new Runnable() {
            public void run() {
                onSessionTokenCreatedListener.onSessionTokenCreated(token, sessionToken);
            }
        });
    }

    static void quitHandlerThread(HandlerThread handlerThread) {
        if (Build.VERSION.SDK_INT >= 18) {
            handlerThread.quitSafely();
        } else {
            handlerThread.quit();
        }
    }

    private static boolean isInterfaceDeclared(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ResolveInfo resolveInfo = queryIntentServices.get(i);
                if (resolveInfo != null && resolveInfo.serviceInfo != null && TextUtils.equals(resolveInfo.serviceInfo.name, componentName.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getUid(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalArgumentException("Cannot find package " + str);
        }
    }

    private static MediaControllerCompat createMediaControllerCompat(Context context, MediaSessionCompat.Token token) {
        try {
            return new MediaControllerCompat(context, token);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to create MediaControllerCompat object.", e);
            return null;
        }
    }
}
