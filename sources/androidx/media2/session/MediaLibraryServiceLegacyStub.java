package androidx.media2.session;

import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import java.util.ArrayList;
import java.util.List;

class MediaLibraryServiceLegacyStub extends MediaSessionServiceLegacyStub {
    private static final boolean DEBUG = false;
    private static final String TAG = "MLS2LegacyStub";
    private final MediaSession.ControllerInfo mControllersForAll = new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Process.myPid(), Process.myUid()), false, new BrowserLegacyCbForAll(this), (Bundle) null);
    final MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl mLibrarySessionImpl;

    MediaLibraryServiceLegacyStub(Context context, MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl mediaLibrarySessionImpl, MediaSessionCompat.Token token) {
        super(context, mediaLibrarySessionImpl, token);
        this.mLibrarySessionImpl = mediaLibrarySessionImpl;
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaSession.ControllerInfo currentController;
        LibraryResult onGetLibraryRoot;
        if (super.onGetRoot(str, i, bundle) == null || (currentController = getCurrentController()) == null) {
            return null;
        }
        if (!getConnectedControllersManager().isAllowedCommand(currentController, 50000) || (onGetLibraryRoot = this.mLibrarySessionImpl.getCallback().onGetLibraryRoot(this.mLibrarySessionImpl.getInstance(), currentController, MediaUtils.convertToLibraryParams(this.mLibrarySessionImpl.getContext(), bundle))) == null || onGetLibraryRoot.getResultCode() != 0 || onGetLibraryRoot.getMediaItem() == null) {
            return MediaUtils.sDefaultBrowserRoot;
        }
        MediaMetadata metadata = onGetLibraryRoot.getMediaItem().getMetadata();
        return new MediaBrowserServiceCompat.BrowserRoot(metadata != null ? metadata.getString("android.media.metadata.MEDIA_ID") : "", MediaUtils.convertToRootHints(onGetLibraryRoot.getLibraryParams()));
    }

    public void onSubscribe(final String str, final Bundle bundle) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSubscribe(): Ignoring empty id from " + currentController);
            return;
        }
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
                    MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onSubscribe(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str, MediaUtils.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext(), bundle));
                }
            }
        });
    }

    public void onUnsubscribe(final String str) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onUnsubscribe(): Ignoring empty id from " + currentController);
            return;
        }
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
                    MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onUnsubscribe(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str);
                }
            }
        });
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, (Bundle) null);
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, Bundle bundle) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onLoadChildren(): Ignoring empty parentId from " + currentController);
            result.sendError((Bundle) null);
            return;
        }
        result.detach();
        final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        final Bundle bundle2 = bundle;
        final String str2 = str;
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (!MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
                    result2.sendError((Bundle) null);
                    return;
                }
                Bundle bundle = bundle2;
                if (bundle != null) {
                    bundle.setClassLoader(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext().getClassLoader());
                    try {
                        int i = bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE);
                        int i2 = bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE);
                        if (i > 0 && i2 > 0) {
                            LibraryResult onGetChildren = MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onGetChildren(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, i, i2, MediaUtils.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext(), bundle2));
                            if (onGetChildren != null) {
                                if (onGetChildren.getResultCode() == 0) {
                                    result2.sendResult(MediaUtils.truncateListBySize(MediaUtils.convertToMediaItemList(onGetChildren.getMediaItems()), 262144));
                                    return;
                                }
                            }
                            result2.sendResult(null);
                            return;
                        }
                    } catch (BadParcelableException unused) {
                    }
                }
                LibraryResult onGetChildren2 = MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onGetChildren(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, 0, Integer.MAX_VALUE, (MediaLibraryService.LibraryParams) null);
                if (onGetChildren2 == null || onGetChildren2.getResultCode() != 0) {
                    result2.sendResult(null);
                } else {
                    result2.sendResult(MediaUtils.truncateListBySize(MediaUtils.convertToMediaItemList(onGetChildren2.getMediaItems()), 262144));
                }
            }
        });
    }

    public void onLoadItem(final String str, final MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty itemId from " + currentController);
            result.sendError((Bundle) null);
            return;
        }
        result.detach();
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (!MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
                    result.sendError((Bundle) null);
                    return;
                }
                LibraryResult onGetItem = MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onGetItem(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str);
                if (onGetItem == null || onGetItem.getResultCode() != 0) {
                    result.sendResult(null);
                } else {
                    result.sendResult(MediaUtils.convertToMediaItem(onGetItem.getMediaItem()));
                }
            }
        });
    }

    public void onSearch(String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        final MediaSession.ControllerInfo currentController = getCurrentController();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty query from " + currentController);
            result.sendError((Bundle) null);
        } else if (currentController.getControllerCb() instanceof BrowserLegacyCb) {
            result.detach();
            final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
            final String str2 = str;
            final Bundle bundle2 = bundle;
            this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    if (!MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
                        result2.sendError((Bundle) null);
                        return;
                    }
                    ((BrowserLegacyCb) currentController.getControllerCb()).registerSearchRequest(currentController, str2, bundle2, result2);
                    MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onSearch(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, str2, MediaUtils.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext(), bundle2));
                }
            });
        }
    }

    public void onCustomAction(String str, Bundle bundle, MediaBrowserServiceCompat.Result<Bundle> result) {
        if (result != null) {
            result.detach();
        }
        final MediaSession.ControllerInfo currentController = getCurrentController();
        final String str2 = str;
        final MediaBrowserServiceCompat.Result<Bundle> result2 = result;
        final Bundle bundle2 = bundle;
        this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                SessionCommand sessionCommand = new SessionCommand(str2, (Bundle) null);
                if (!MediaLibraryServiceLegacyStub.this.getConnectedControllersManager().isAllowedCommand(currentController, sessionCommand)) {
                    MediaBrowserServiceCompat.Result result = result2;
                    if (result != null) {
                        result.sendError((Bundle) null);
                        return;
                    }
                    return;
                }
                SessionResult onCustomCommand = MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onCustomCommand(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), currentController, sessionCommand, bundle2);
                if (onCustomCommand != null) {
                    result2.sendResult(onCustomCommand.getCustomCommandResult());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        return new MediaSession.ControllerInfo(remoteUserInfo, this.mManager.isTrustedForMediaControl(remoteUserInfo), new BrowserLegacyCb(remoteUserInfo), (Bundle) null);
    }

    /* access modifiers changed from: package-private */
    public MediaSession.ControllerInfo getControllersForAll() {
        return this.mControllersForAll;
    }

    private MediaSession.ControllerInfo getCurrentController() {
        return getConnectedControllersManager().getController(getCurrentBrowserInfo());
    }

    private static class SearchRequest {
        public final MediaSession.ControllerInfo mController;
        public final Bundle mExtras;
        public final String mQuery;
        public final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        public final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> mResult;

        SearchRequest(MediaSession.ControllerInfo controllerInfo, MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            this.mController = controllerInfo;
            this.mRemoteUserInfo = remoteUserInfo;
            this.mQuery = str;
            this.mExtras = bundle;
            this.mResult = result;
        }
    }

    private static abstract class BaseBrowserLegacyCb extends MediaSession.ControllerCb {
        /* access modifiers changed from: package-private */
        public final void onAllowedCommandsChanged(int i, SessionCommandGroup sessionCommandGroup) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onBufferingStateChanged(int i, MediaItem mediaItem, int i2, long j, long j2, long j3) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onCurrentMediaItemChanged(int i, MediaItem mediaItem, int i2, int i3, int i4) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onDisconnected(int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaybackCompleted(int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaybackInfoChanged(int i, MediaController.PlaybackInfo playbackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onPlayerResult(int i, SessionPlayer.PlayerResult playerResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaylistChanged(int i, List<MediaItem> list, MediaMetadata mediaMetadata, int i2, int i3, int i4) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onSubtitleData(int i, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        /* access modifiers changed from: package-private */
        public final void onTrackDeselected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(int i, List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onTrackSelected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void onVideoSizeChanged(int i, MediaItem mediaItem, VideoSize videoSize) {
        }

        /* access modifiers changed from: package-private */
        public final void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public final void setCustomLayout(int i, List<MediaSession.CommandButton> list) throws RemoteException {
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
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(this.mRemoteUserInfo, str, libraryParams != null ? libraryParams.getExtras() : null);
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
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
                    MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            int i;
                            int i2;
                            int i3;
                            int i4;
                            for (int i5 = 0; i5 < arrayList.size(); i5++) {
                                SearchRequest searchRequest = (SearchRequest) arrayList.get(i5);
                                if (searchRequest.mExtras != null) {
                                    try {
                                        searchRequest.mExtras.setClassLoader(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext().getClassLoader());
                                        i2 = searchRequest.mExtras.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                                        i = searchRequest.mExtras.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                                    } catch (BadParcelableException unused) {
                                        searchRequest.mResult.sendResult(null);
                                        return;
                                    }
                                } else {
                                    i2 = 0;
                                    i = Integer.MAX_VALUE;
                                }
                                if (i2 < 0 || i < 1) {
                                    i4 = 0;
                                    i3 = Integer.MAX_VALUE;
                                } else {
                                    i4 = i2;
                                    i3 = i;
                                }
                                LibraryResult onGetSearchResult = MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getCallback().onGetSearchResult(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getInstance(), searchRequest.mController, searchRequest.mQuery, i4, i3, MediaUtils.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.mLibrarySessionImpl.getContext(), searchRequest.mExtras));
                                if (onGetSearchResult == null || onGetSearchResult.getResultCode() != 0) {
                                    searchRequest.mResult.sendResult(null);
                                } else {
                                    searchRequest.mResult.sendResult(MediaUtils.truncateListBySize(MediaUtils.convertToMediaItemList(onGetSearchResult.getMediaItems()), 262144));
                                }
                            }
                        }
                    });
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void registerSearchRequest(MediaSession.ControllerInfo controllerInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            synchronized (this.mLock) {
                this.mSearchRequests.add(new SearchRequest(controllerInfo, controllerInfo.getRemoteUserInfo(), str, bundle, result));
            }
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mRemoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != BrowserLegacyCb.class) {
                return false;
            }
            return ObjectsCompat.equals(this.mRemoteUserInfo, ((BrowserLegacyCb) obj).mRemoteUserInfo);
        }
    }

    private static class BrowserLegacyCbForAll extends BaseBrowserLegacyCb {
        private final MediaBrowserServiceCompat mService;

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        BrowserLegacyCbForAll(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            super();
            this.mService = mediaBrowserServiceCompat;
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            if (libraryParams == null || libraryParams.getExtras() == null) {
                this.mService.notifyChildrenChanged(str);
            } else {
                this.mService.notifyChildrenChanged(str, libraryParams.getExtras());
            }
        }
    }
}
