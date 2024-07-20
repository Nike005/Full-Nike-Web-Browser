package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.MediaLibraryService2;
import androidx.media2.MediaSession2;
import androidx.media2.MediaSession2ImplBase;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

class MediaLibrarySessionImplBase extends MediaSession2ImplBase implements MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl {
    private final ArrayMap<MediaSession2.ControllerCb, Set<String>> mSubscriptions = new ArrayMap<>();

    MediaLibrarySessionImplBase(MediaSession2 mediaSession2, Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
        super(mediaSession2, context, str, mediaPlayerConnector, mediaPlaylistAgent, pendingIntent, executor, sessionCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat createLegacyBrowserService(Context context, SessionToken2 sessionToken2, MediaSessionCompat.Token token) {
        return new MediaLibraryService2LegacyStub(context, this, token);
    }

    public MediaLibraryService2.MediaLibrarySession getInstance() {
        return (MediaLibraryService2.MediaLibrarySession) super.getInstance();
    }

    public MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionCallback getCallback() {
        return (MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionCallback) super.getCallback();
    }

    /* access modifiers changed from: package-private */
    public MediaLibraryService2LegacyStub getLegacyBrowserService() {
        return (MediaLibraryService2LegacyStub) super.getLegacyBrowserService();
    }

    public List<MediaSession2.ControllerInfo> getConnectedControllers() {
        List<MediaSession2.ControllerInfo> connectedControllers = super.getConnectedControllers();
        connectedControllers.addAll(getLegacyBrowserService().getConnectedControllersManager().getConnectedControllers());
        return connectedControllers;
    }

    public void notifyChildrenChanged(final String str, final int i, final Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (i >= 0) {
            notifyToAllControllers(new MediaSession2ImplBase.NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    if (MediaLibrarySessionImplBase.this.isSubscribed(controllerCb, str)) {
                        controllerCb.onChildrenChanged(str, i, bundle);
                    }
                }
            });
        } else {
            throw new IllegalArgumentException("itemCount shouldn't be negative");
        }
    }

    public void notifyChildrenChanged(MediaSession2.ControllerInfo controllerInfo, String str, int i, Bundle bundle) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (i >= 0) {
            final String str2 = str;
            final MediaSession2.ControllerInfo controllerInfo2 = controllerInfo;
            final int i2 = i;
            final Bundle bundle2 = bundle;
            notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    if (MediaLibrarySessionImplBase.this.isSubscribed(controllerCb, str2)) {
                        controllerCb.onChildrenChanged(str2, i2, bundle2);
                    } else if (MediaSession2ImplBase.DEBUG) {
                        Log.d("MS2ImplBase", "Skipping notifyChildrenChanged() to " + controllerInfo2 + " because it hasn't subscribed");
                        MediaLibrarySessionImplBase.this.dumpSubscription();
                    }
                }
            });
        } else {
            throw new IllegalArgumentException("itemCount shouldn't be negative");
        }
    }

    public void notifySearchResultChanged(MediaSession2.ControllerInfo controllerInfo, final String str, final int i, final Bundle bundle) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (!TextUtils.isEmpty(str)) {
            notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onSearchResultChanged(str, i, bundle);
                }
            });
        } else {
            throw new IllegalArgumentException("query shouldn't be empty");
        }
    }

    public void onGetLibraryRootOnExecutor(MediaSession2.ControllerInfo controllerInfo, final Bundle bundle) {
        final MediaLibraryService2.LibraryRoot onGetLibraryRoot = getCallback().onGetLibraryRoot(getInstance(), controllerInfo, bundle);
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                String str;
                Bundle bundle = bundle;
                MediaLibraryService2.LibraryRoot libraryRoot = onGetLibraryRoot;
                Bundle bundle2 = null;
                if (libraryRoot == null) {
                    str = null;
                } else {
                    str = libraryRoot.getRootId();
                }
                MediaLibraryService2.LibraryRoot libraryRoot2 = onGetLibraryRoot;
                if (libraryRoot2 != null) {
                    bundle2 = libraryRoot2.getExtras();
                }
                controllerCb.onGetLibraryRootDone(bundle, str, bundle2);
            }
        });
    }

    public void onGetItemOnExecutor(MediaSession2.ControllerInfo controllerInfo, final String str) {
        final MediaItem2 onGetItem = getCallback().onGetItem(getInstance(), controllerInfo, str);
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onGetItemDone(str, onGetItem);
            }
        });
    }

    public void onGetChildrenOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle) {
        final List<MediaItem2> onGetChildren = getCallback().onGetChildren(getInstance(), controllerInfo, str, i, i2, bundle);
        if (onGetChildren == null || onGetChildren.size() <= i2) {
            final String str2 = str;
            final int i3 = i;
            final int i4 = i2;
            final Bundle bundle2 = bundle;
            notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onGetChildrenDone(str2, i3, i4, onGetChildren, bundle2);
                }
            });
            return;
        }
        throw new IllegalArgumentException("onGetChildren() shouldn't return media items more than pageSize. result.size()=" + onGetChildren.size() + " pageSize=" + i2);
    }

    public void onSubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
        synchronized (this.mLock) {
            Set set = this.mSubscriptions.get(controllerInfo.getControllerCb());
            if (set == null) {
                set = new HashSet();
                this.mSubscriptions.put(controllerInfo.getControllerCb(), set);
            }
            set.add(str);
        }
        getCallback().onSubscribe(getInstance(), controllerInfo, str, bundle);
    }

    public void onUnsubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str) {
        getCallback().onUnsubscribe(getInstance(), controllerInfo, str);
        synchronized (this.mLock) {
            this.mSubscriptions.remove(controllerInfo.getControllerCb());
        }
    }

    public void onSearchOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
        getCallback().onSearch(getInstance(), controllerInfo, str, bundle);
    }

    public void onGetSearchResultOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle) {
        final List<MediaItem2> onGetSearchResult = getCallback().onGetSearchResult(getInstance(), controllerInfo, str, i, i2, bundle);
        if (onGetSearchResult == null || onGetSearchResult.size() <= i2) {
            final String str2 = str;
            final int i3 = i;
            final int i4 = i2;
            final Bundle bundle2 = bundle;
            notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onGetSearchResultDone(str2, i3, i4, onGetSearchResult, bundle2);
                }
            });
            return;
        }
        throw new IllegalArgumentException("onGetSearchResult() shouldn't return media items more than pageSize. result.size()=" + onGetSearchResult.size() + " pageSize=" + i2);
    }

    /* access modifiers changed from: package-private */
    public void notifyToAllControllers(MediaSession2ImplBase.NotifyRunnable notifyRunnable) {
        super.notifyToAllControllers(notifyRunnable);
        notifyToController(getLegacyBrowserService().getControllersForAll(), notifyRunnable);
    }

    /* access modifiers changed from: package-private */
    public boolean isSubscribed(MediaSession2.ControllerCb controllerCb, String str) {
        synchronized (this.mLock) {
            Set set = this.mSubscriptions.get(controllerCb);
            if (set != null) {
                if (set.contains(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void dumpSubscription() {
        if (DEBUG) {
            synchronized (this.mLock) {
                Log.d("MS2ImplBase", "Dumping subscription, controller sz=" + this.mSubscriptions.size());
                for (int i = 0; i < this.mSubscriptions.size(); i++) {
                    Log.d("MS2ImplBase", "  controller " + this.mSubscriptions.valueAt(i));
                    for (String str : this.mSubscriptions.valueAt(i)) {
                        Log.d("MS2ImplBase", "  - " + str);
                    }
                }
            }
        }
    }
}
