package androidx.media2.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionImplBase;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

class MediaLibrarySessionImplBase extends MediaSessionImplBase implements MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl {
    private final ArrayMap<MediaSession.ControllerCb, Set<String>> mSubscriptions = new ArrayMap<>();

    MediaLibrarySessionImplBase(MediaSession mediaSession, Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, MediaSession.SessionCallback sessionCallback, Bundle bundle) {
        super(mediaSession, context, str, sessionPlayer, pendingIntent, executor, sessionCallback, bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat createLegacyBrowserService(Context context, SessionToken sessionToken, MediaSessionCompat.Token token) {
        return new MediaLibraryServiceLegacyStub(context, this, token);
    }

    public MediaLibraryService.MediaLibrarySession getInstance() {
        return (MediaLibraryService.MediaLibrarySession) super.getInstance();
    }

    public MediaLibraryService.MediaLibrarySession.MediaLibrarySessionCallback getCallback() {
        return (MediaLibraryService.MediaLibrarySession.MediaLibrarySessionCallback) super.getCallback();
    }

    /* access modifiers changed from: package-private */
    public MediaLibraryServiceLegacyStub getLegacyBrowserService() {
        return (MediaLibraryServiceLegacyStub) super.getLegacyBrowserService();
    }

    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        List<MediaSession.ControllerInfo> connectedControllers = super.getConnectedControllers();
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            connectedControllers.addAll(legacyBrowserService.getConnectedControllersManager().getConnectedControllers());
        }
        return connectedControllers;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        if (super.isConnected(controllerInfo)) {
            return true;
        }
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            return legacyBrowserService.getConnectedControllersManager().isConnected(controllerInfo);
        }
        return false;
    }

    public void notifyChildrenChanged(final String str, final int i, final MediaLibraryService.LibraryParams libraryParams) {
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImplBase.RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                if (MediaLibrarySessionImplBase.this.isSubscribed(controllerCb, str)) {
                    controllerCb.onChildrenChanged(i, str, i, libraryParams);
                }
            }
        });
    }

    public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        final String str2 = str;
        final MediaSession.ControllerInfo controllerInfo2 = controllerInfo;
        final int i2 = i;
        final MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImplBase.RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                if (MediaLibrarySessionImplBase.this.isSubscribed(controllerCb, str2)) {
                    controllerCb.onChildrenChanged(i, str2, i2, libraryParams2);
                } else if (MediaSessionImplBase.DEBUG) {
                    Log.d("MSImplBase", "Skipping notifyChildrenChanged() to " + controllerInfo2 + " because it hasn't subscribed");
                    MediaLibrarySessionImplBase.this.dumpSubscription();
                }
            }
        });
    }

    public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, final String str, final int i, final MediaLibraryService.LibraryParams libraryParams) {
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImplBase.RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.onSearchResultChanged(i, str, i, libraryParams);
            }
        });
    }

    private LibraryResult ensureNonNullResult(LibraryResult libraryResult) {
        if (libraryResult != null) {
            return libraryResult;
        }
        throw new RuntimeException("LibraryResult shouldn't be null");
    }

    private LibraryResult ensureNonNullResultWithValidList(LibraryResult libraryResult, int i) {
        LibraryResult ensureNonNullResult = ensureNonNullResult(libraryResult);
        if (ensureNonNullResult.getResultCode() == 0) {
            List<MediaItem> mediaItems = ensureNonNullResult.getMediaItems();
            if (mediaItems == null) {
                throw new RuntimeException("List shouldn't be null for the success");
            } else if (mediaItems.size() <= i) {
                for (MediaItem isValidItem : mediaItems) {
                    if (!isValidItem(isValidItem)) {
                        return new LibraryResult(-1);
                    }
                }
            } else {
                throw new RuntimeException("List shouldn't contain items more than pageSize, size=" + ensureNonNullResult.getMediaItems().size() + ", pageSize" + i);
            }
        }
        return ensureNonNullResult;
    }

    private LibraryResult ensureNonNullResultWithValidItem(LibraryResult libraryResult) {
        LibraryResult ensureNonNullResult = ensureNonNullResult(libraryResult);
        return (ensureNonNullResult.getResultCode() != 0 || isValidItem(ensureNonNullResult.getMediaItem())) ? ensureNonNullResult : new LibraryResult(-1);
    }

    private boolean isValidItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            throw new RuntimeException("Item shouldn't be null for the success");
        } else if (!TextUtils.isEmpty(mediaItem.getMediaId())) {
            MediaMetadata metadata = mediaItem.getMetadata();
            if (metadata == null) {
                throw new RuntimeException("Metadata of an item shouldn't be null for the success");
            } else if (!metadata.containsKey(MediaMetadata.METADATA_KEY_BROWSABLE)) {
                throw new RuntimeException("METADATA_KEY_BROWSABLE should be specified in metadata of an item");
            } else if (metadata.containsKey(MediaMetadata.METADATA_KEY_PLAYABLE)) {
                return true;
            } else {
                throw new RuntimeException("METADATA_KEY_PLAYABLE should be specified in metadata of an item");
            }
        } else {
            throw new RuntimeException("Media ID of an item shouldn't be empty for the success");
        }
    }

    public LibraryResult onGetLibraryRootOnExecutor(MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams) {
        return ensureNonNullResultWithValidItem(getCallback().onGetLibraryRoot(getInstance(), controllerInfo, libraryParams));
    }

    public LibraryResult onGetItemOnExecutor(MediaSession.ControllerInfo controllerInfo, String str) {
        return ensureNonNullResultWithValidItem(getCallback().onGetItem(getInstance(), controllerInfo, str));
    }

    public LibraryResult onGetChildrenOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        return ensureNonNullResultWithValidList(getCallback().onGetChildren(getInstance(), controllerInfo, str, i, i2, libraryParams), i2);
    }

    public int onSubscribeOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        synchronized (this.mLock) {
            Set set = this.mSubscriptions.get(controllerInfo.getControllerCb());
            if (set == null) {
                set = new HashSet();
                this.mSubscriptions.put(controllerInfo.getControllerCb(), set);
            }
            set.add(str);
        }
        int onSubscribe = getCallback().onSubscribe(getInstance(), controllerInfo, str, libraryParams);
        if (onSubscribe != 0) {
            synchronized (this.mLock) {
                this.mSubscriptions.remove(controllerInfo.getControllerCb());
            }
        }
        return onSubscribe;
    }

    public int onUnsubscribeOnExecutor(MediaSession.ControllerInfo controllerInfo, String str) {
        int onUnsubscribe = getCallback().onUnsubscribe(getInstance(), controllerInfo, str);
        synchronized (this.mLock) {
            this.mSubscriptions.remove(controllerInfo.getControllerCb());
        }
        return onUnsubscribe;
    }

    public int onSearchOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        return getCallback().onSearch(getInstance(), controllerInfo, str, libraryParams);
    }

    public LibraryResult onGetSearchResultOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        return ensureNonNullResultWithValidList(getCallback().onGetSearchResult(getInstance(), controllerInfo, str, i, i2, libraryParams), i2);
    }

    /* access modifiers changed from: package-private */
    public void dispatchRemoteControllerTaskWithoutReturn(MediaSessionImplBase.RemoteControllerTask remoteControllerTask) {
        super.dispatchRemoteControllerTaskWithoutReturn(remoteControllerTask);
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            dispatchRemoteControllerTaskWithoutReturn(legacyBrowserService.getControllersForAll(), remoteControllerTask);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSubscribed(MediaSession.ControllerCb controllerCb, String str) {
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
                Log.d("MSImplBase", "Dumping subscription, controller sz=" + this.mSubscriptions.size());
                for (int i = 0; i < this.mSubscriptions.size(); i++) {
                    Log.d("MSImplBase", "  controller " + this.mSubscriptions.valueAt(i));
                    for (String str : this.mSubscriptions.valueAt(i)) {
                        Log.d("MSImplBase", "  - " + str);
                    }
                }
            }
        }
    }
}
