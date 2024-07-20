package androidx.media2;

import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.MediaController2;
import androidx.media2.MediaLibraryService2;
import androidx.media2.MediaSession2;
import java.util.ArrayList;
import java.util.List;

class MediaLibraryService2LegacyStub extends MediaSessionService2LegacyStub {
    private static final boolean DEBUG = false;
    private static final String TAG = "MLS2LegacyStub";
    private final MediaSession2.ControllerInfo mControllersForAll = new MediaSession2.ControllerInfo(new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Process.myPid(), Process.myUid()), false, new BrowserLegacyCbForAll(this));
    final MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl mLibrarySessionImpl;

    MediaLibraryService2LegacyStub(Context context, MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSessionCompat.Token token) {
        super(context, mediaLibrarySessionImpl, token);
        this.mLibrarySessionImpl = mediaLibrarySessionImpl;
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaLibraryService2.LibraryRoot onGetLibraryRoot;
        if (super.onGetRoot(str, i, bundle) == null) {
            return null;
        }
        MediaSession2.ControllerInfo currentController = getCurrentController();
        if (!getConnectedControllersManager().isAllowedCommand(currentController, 31) || (onGetLibraryRoot = this.mLibrarySessionImpl.getCallback().onGetLibraryRoot(this.mLibrarySessionImpl.getInstance(), currentController, bundle)) == null) {
            return MediaUtils2.sDefaultBrowserRoot;
        }
        return new MediaBrowserServiceCompat.BrowserRoot(onGetLibraryRoot.getRootId(), onGetLibraryRoot.getExtras());
    }

    public void onSubscribe(final String str, final Bundle bundle) {
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, 34)) {
                    MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onSubscribe(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str, bundle);
                }
            }
        });
    }

    public void onUnsubscribe(final String str) {
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, 35)) {
                    MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onUnsubscribe(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str);
                }
            }
        });
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, (Bundle) null);
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, Bundle bundle) {
        result.detach();
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        final Bundle bundle2 = bundle;
        final String str2 = str;
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (!MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, 29)) {
                    result2.sendError((Bundle) null);
                    return;
                }
                Bundle bundle = bundle2;
                if (bundle != null) {
                    bundle.setClassLoader(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getContext().getClassLoader());
                    try {
                        int i = bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE);
                        int i2 = bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE);
                        if (i > 0 && i2 > 0) {
                            result2.sendResult(MediaUtils2.convertToMediaItemList(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onGetChildren(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, i, i2, bundle2)));
                            return;
                        }
                    } catch (BadParcelableException unused) {
                    }
                }
                result2.sendResult(MediaUtils2.convertToMediaItemList(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onGetChildren(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, 0, Integer.MAX_VALUE, (Bundle) null)));
            }
        });
    }

    public void onLoadItem(final String str, final MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        result.detach();
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (!MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, 30)) {
                    result.sendError((Bundle) null);
                    return;
                }
                MediaItem2 onGetItem = MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onGetItem(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str);
                if (onGetItem == null) {
                    result.sendResult(null);
                } else {
                    result.sendResult(MediaUtils2.convertToMediaItem(onGetItem));
                }
            }
        });
    }

    public void onSearch(String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        if (currentController.getControllerCb() instanceof BrowserLegacyCb) {
            result.detach();
            final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
            final String str2 = str;
            final Bundle bundle2 = bundle;
            this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    if (!MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, 33)) {
                        result2.sendError((Bundle) null);
                        return;
                    }
                    ((BrowserLegacyCb) currentController.getControllerCb()).registerSearchRequest(currentController, str2, bundle2, result2);
                    MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onSearch(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, bundle2);
                }
            });
        }
    }

    public void onCustomAction(String str, Bundle bundle, MediaBrowserServiceCompat.Result<Bundle> result) {
        if (result != null) {
            result.detach();
        }
        final MediaSession2.ControllerInfo currentController = getCurrentController();
        final String str2 = str;
        final MediaBrowserServiceCompat.Result<Bundle> result2 = result;
        final Bundle bundle2 = bundle;
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                SessionCommand2 sessionCommand2 = new SessionCommand2(str2, (Bundle) null);
                if (!MediaLibraryService2LegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, sessionCommand2)) {
                    MediaBrowserServiceCompat.Result result = result2;
                    if (result != null) {
                        result.sendError((Bundle) null);
                        return;
                    }
                    return;
                }
                MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onCustomCommand(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, sessionCommand2, bundle2, result2 == null ? null : new CustomActionResultReceiver(result2));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public MediaSession2.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        return new MediaSession2.ControllerInfo(remoteUserInfo, this.mManager.isTrustedForMediaControl(remoteUserInfo), new BrowserLegacyCb(remoteUserInfo));
    }

    /* access modifiers changed from: package-private */
    public MediaSession2.ControllerInfo getControllersForAll() {
        return this.mControllersForAll;
    }

    private MediaSession2.ControllerInfo getCurrentController() {
        return getConnectedControllersManager().getController(getCurrentBrowserInfo());
    }

    private static class CustomActionResultReceiver extends ResultReceiver {
        private MediaBrowserServiceCompat.Result<Bundle> mResult;

        CustomActionResultReceiver(MediaBrowserServiceCompat.Result<Bundle> result) {
            super((Handler) null);
            this.mResult = result;
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            this.mResult.sendResult(bundle);
        }
    }

    private static class SearchRequest {
        public final MediaSession2.ControllerInfo mController;
        public final Bundle mExtras;
        public final String mQuery;
        public final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        public final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> mResult;

        SearchRequest(MediaSession2.ControllerInfo controllerInfo, MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            this.mController = controllerInfo;
            this.mRemoteUserInfo = remoteUserInfo;
            this.mQuery = str;
            this.mExtras = bundle;
            this.mResult = result;
        }
    }

    private static abstract class BaseBrowserLegacyCb extends MediaSession2.ControllerCb {
        /* access modifiers changed from: package-private */
        public final void onAllowedCommandsChanged(SessionCommandGroup2 sessionCommandGroup2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onBufferingStateChanged(MediaItem2 mediaItem2, int i, long j) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onCurrentMediaItemChanged(MediaItem2 mediaItem2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onCustomLayoutChanged(List<MediaSession2.CommandButton> list) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onDisconnected() throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onError(int i, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onGetChildrenDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onGetItemDone(String str, MediaItem2 mediaItem2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onGetSearchResultDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaybackInfoChanged(MediaController2.PlaybackInfo playbackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlayerStateChanged(long j, long j2, int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaylistChanged(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaylistMetadataChanged(MediaMetadata2 mediaMetadata2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onRepeatModeChanged(int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onRoutesInfoChanged(List<Bundle> list) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onSeekCompleted(long j, long j2, long j3) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onShuffleModeChanged(int i) throws RemoteException {
        }

        private BaseBrowserLegacyCb() {
        }
    }

    private class BrowserLegacyCb extends BaseBrowserLegacyCb {
        private final Object mLock = new Object();
        private final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        private final List<SearchRequest> mSearchRequests = new ArrayList();

        BrowserLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            super();
            this.mRemoteUserInfo = remoteUserInfo;
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
            MediaLibraryService2LegacyStub.this.notifyChildrenChanged(this.mRemoteUserInfo, str, bundle);
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
            final ArrayList arrayList = new ArrayList();
            synchronized (this.mLock) {
                for (int size = this.mSearchRequests.size() - 1; size >= 0; size--) {
                    SearchRequest searchRequest = this.mSearchRequests.get(size);
                    if (ObjectsCompat.equals(this.mRemoteUserInfo, searchRequest.mRemoteUserInfo) && searchRequest.mQuery.equals(str)) {
                        arrayList.add(searchRequest);
                        this.mSearchRequests.remove(size);
                    }
                }
                if (arrayList.size() != 0) {
                    MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            int i;
                            int i2;
                            int i3;
                            int i4;
                            int i5 = 0;
                            while (i5 < arrayList.size()) {
                                SearchRequest searchRequest = (SearchRequest) arrayList.get(i5);
                                if (searchRequest.mExtras != null) {
                                    try {
                                        searchRequest.mExtras.setClassLoader(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getContext().getClassLoader());
                                        i2 = searchRequest.mExtras.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                                        i = searchRequest.mExtras.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                                    } catch (BadParcelableException unused) {
                                        searchRequest.mResult.sendResult(null);
                                        return;
                                    }
                                } else {
                                    i2 = 0;
                                    i = 0;
                                }
                                if (i2 < 0 || i < 1) {
                                    i4 = 0;
                                    i3 = Integer.MAX_VALUE;
                                } else {
                                    i4 = i2;
                                    i3 = i;
                                }
                                List<MediaItem2> onGetSearchResult = MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getCallback().onGetSearchResult(MediaLibraryService2LegacyStub.this.mLibrarySessionImpl.getInstance(), searchRequest.mController, searchRequest.mQuery, i4, i3, searchRequest.mExtras);
                                if (onGetSearchResult == null) {
                                    searchRequest.mResult.sendResult(null);
                                    return;
                                } else {
                                    searchRequest.mResult.sendResult(MediaUtils2.convertToMediaItemList(onGetSearchResult));
                                    i5++;
                                }
                            }
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void registerSearchRequest(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            synchronized (this.mLock) {
                this.mSearchRequests.add(new SearchRequest(controllerInfo, controllerInfo.getRemoteUserInfo(), str, bundle, result));
            }
        }
    }

    private static class BrowserLegacyCbForAll extends BaseBrowserLegacyCb {
        private final MediaBrowserServiceCompat mService;

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
        }

        BrowserLegacyCbForAll(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
            this.mService = mediaBrowserServiceCompat;
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                this.mService.notifyChildrenChanged(str);
            } else {
                this.mService.notifyChildrenChanged(str, bundle);
            }
        }
    }
}
