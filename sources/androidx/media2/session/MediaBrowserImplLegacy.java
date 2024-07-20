package androidx.media2.session;

import android.content.Context;
import android.os.Bundle;
import android.support.p064v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.session.MediaBrowser;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class MediaBrowserImplLegacy extends MediaControllerImplLegacy implements MediaBrowser.MediaBrowserImpl {
    private static final String TAG = "MB2ImplLegacy";
    final HashMap<MediaLibraryService.LibraryParams, MediaBrowserCompat> mBrowserCompats = new HashMap<>();
    private final HashMap<String, List<SubscribeCallback>> mSubscribeCallbacks = new HashMap<>();

    MediaBrowserImplLegacy(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken) {
        super(context, mediaBrowser, sessionToken);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowser getMediaBrowser() {
        return (MediaBrowser) this.mInstance;
    }

    public void close() {
        synchronized (this.mLock) {
            for (MediaBrowserCompat disconnect : this.mBrowserCompats.values()) {
                disconnect.disconnect();
            }
            this.mBrowserCompats.clear();
            super.close();
        }
    }

    public ListenableFuture<LibraryResult> getLibraryRoot(final MediaLibraryService.LibraryParams libraryParams) {
        final ResolvableFuture create = ResolvableFuture.create();
        MediaBrowserCompat browserCompat = getBrowserCompat(libraryParams);
        if (browserCompat != null) {
            create.set(new LibraryResult(0, createRootMediaItem(browserCompat), (MediaLibraryService.LibraryParams) null));
        } else {
            this.mHandler.post(new Runnable() {
                public void run() {
                    MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(MediaBrowserImplLegacy.this.getContext(), MediaBrowserImplLegacy.this.getConnectedToken().getComponentName(), new GetLibraryRootCallback(create, libraryParams), MediaUtils.convertToRootHints(libraryParams));
                    synchronized (MediaBrowserImplLegacy.this.mLock) {
                        MediaBrowserImplLegacy.this.mBrowserCompats.put(libraryParams, mediaBrowserCompat);
                    }
                    mediaBrowserCompat.connect();
                }
            });
        }
        return create;
    }

    public ListenableFuture<LibraryResult> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        SubscribeCallback subscribeCallback = new SubscribeCallback();
        synchronized (this.mLock) {
            List list = this.mSubscribeCallbacks.get(str);
            if (list == null) {
                list = new ArrayList();
                this.mSubscribeCallbacks.put(str, list);
            }
            list.add(subscribeCallback);
        }
        browserCompat.subscribe(str, getExtras(libraryParams), subscribeCallback);
        return LibraryResult.createFutureWithResult(0);
    }

    public ListenableFuture<LibraryResult> unsubscribe(String str) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        synchronized (this.mLock) {
            List list = this.mSubscribeCallbacks.get(str);
            if (list == null) {
                ListenableFuture<LibraryResult> createFutureWithResult = LibraryResult.createFutureWithResult(-3);
                return createFutureWithResult;
            }
            for (int i = 0; i < list.size(); i++) {
                browserCompat.unsubscribe(str, (MediaBrowserCompat.SubscriptionCallback) list.get(i));
            }
            return LibraryResult.createFutureWithResult(0);
        }
    }

    public ListenableFuture<LibraryResult> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        ResolvableFuture create = ResolvableFuture.create();
        Bundle createBundle = createBundle(libraryParams);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
        browserCompat.subscribe(str, createBundle, new GetChildrenCallback(create, str));
        return create;
    }

    public ListenableFuture<LibraryResult> getItem(String str) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        final ResolvableFuture create = ResolvableFuture.create();
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() {
            public void onItemLoaded(final MediaBrowserCompat.MediaItem mediaItem) {
                MediaBrowserImplLegacy.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (mediaItem != null) {
                            create.set(new LibraryResult(0, MediaUtils.convertToMediaItem(mediaItem), (MediaLibraryService.LibraryParams) null));
                        } else {
                            create.set(new LibraryResult(-3));
                        }
                    }
                });
            }

            public void onError(String str) {
                MediaBrowserImplLegacy.this.mHandler.post(new Runnable() {
                    public void run() {
                        create.set(new LibraryResult(-1));
                    }
                });
            }
        });
        return create;
    }

    public ListenableFuture<LibraryResult> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        browserCompat.search(str, getExtras(libraryParams), new MediaBrowserCompat.SearchCallback() {
            public void onSearchResult(final String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
                MediaBrowserImplLegacy.this.getMediaBrowser().notifyBrowserCallback(new MediaBrowser.BrowserCallbackRunnable() {
                    public void run(MediaBrowser.BrowserCallback browserCallback) {
                        browserCallback.onSearchResultChanged(MediaBrowserImplLegacy.this.getMediaBrowser(), str, list.size(), (MediaLibraryService.LibraryParams) null);
                    }
                });
            }

            public void onError(final String str, Bundle bundle) {
                MediaBrowserImplLegacy.this.getMediaBrowser().notifyBrowserCallback(new MediaBrowser.BrowserCallbackRunnable() {
                    public void run(MediaBrowser.BrowserCallback browserCallback) {
                        browserCallback.onSearchResultChanged(MediaBrowserImplLegacy.this.getMediaBrowser(), str, 0, (MediaLibraryService.LibraryParams) null);
                    }
                });
            }
        });
        return LibraryResult.createFutureWithResult(0);
    }

    public ListenableFuture<LibraryResult> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return LibraryResult.createFutureWithResult(-100);
        }
        final ResolvableFuture create = ResolvableFuture.create();
        Bundle createBundle = createBundle(libraryParams);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
        browserCompat.search(str, createBundle, new MediaBrowserCompat.SearchCallback() {
            public void onSearchResult(String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
                MediaBrowserImplLegacy.this.mHandler.post(new Runnable() {
                    public void run() {
                        create.set(new LibraryResult(0, MediaUtils.convertMediaItemListToMediaItemList(list), (MediaLibraryService.LibraryParams) null));
                    }
                });
            }

            public void onError(String str, Bundle bundle) {
                MediaBrowserImplLegacy.this.mHandler.post(new Runnable() {
                    public void run() {
                        create.set(new LibraryResult(-1));
                    }
                });
            }
        });
        return create;
    }

    private MediaBrowserCompat getBrowserCompat(MediaLibraryService.LibraryParams libraryParams) {
        MediaBrowserCompat mediaBrowserCompat;
        synchronized (this.mLock) {
            mediaBrowserCompat = this.mBrowserCompats.get(libraryParams);
        }
        return mediaBrowserCompat;
    }

    private static Bundle createBundle(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams == null || libraryParams.getExtras() == null) {
            return new Bundle();
        }
        return new Bundle(libraryParams.getExtras());
    }

    private static Bundle getExtras(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams != null) {
            return libraryParams.getExtras();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public MediaItem createRootMediaItem(MediaBrowserCompat mediaBrowserCompat) {
        return new MediaItem.Builder().setMetadata(new MediaMetadata.Builder().putString("android.media.metadata.MEDIA_ID", mediaBrowserCompat.getRoot()).putLong(MediaMetadata.METADATA_KEY_BROWSABLE, 0).putLong(MediaMetadata.METADATA_KEY_PLAYABLE, 0).setExtras(mediaBrowserCompat.getExtras()).build()).build();
    }

    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        final MediaLibraryService.LibraryParams mParams;
        final ResolvableFuture<LibraryResult> mResult;

        GetLibraryRootCallback(ResolvableFuture<LibraryResult> resolvableFuture, MediaLibraryService.LibraryParams libraryParams) {
            this.mResult = resolvableFuture;
            this.mParams = libraryParams;
        }

        public void onConnected() {
            MediaBrowserCompat mediaBrowserCompat;
            synchronized (MediaBrowserImplLegacy.this.mLock) {
                mediaBrowserCompat = MediaBrowserImplLegacy.this.mBrowserCompats.get(this.mParams);
            }
            if (mediaBrowserCompat == null) {
                this.mResult.set(new LibraryResult(-1));
            } else {
                this.mResult.set(new LibraryResult(0, MediaBrowserImplLegacy.this.createRootMediaItem(mediaBrowserCompat), MediaUtils.convertToLibraryParams(MediaBrowserImplLegacy.this.mContext, mediaBrowserCompat.getExtras())));
            }
        }

        public void onConnectionSuspended() {
            onConnectionFailed();
        }

        public void onConnectionFailed() {
            this.mResult.set(new LibraryResult(-3));
            MediaBrowserImplLegacy.this.close();
        }
    }

    private class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        SubscribeCallback() {
        }

        public void onError(String str) {
            onChildrenLoaded(str, (List<MediaBrowserCompat.MediaItem>) null, (Bundle) null);
        }

        public void onError(String str, Bundle bundle) {
            onChildrenLoaded(str, (List<MediaBrowserCompat.MediaItem>) null, bundle);
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoaded(str, list, (Bundle) null);
        }

        public void onChildrenLoaded(final String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "SubscribeCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat != null && list != null) {
                final int size = list.size();
                final MediaLibraryService.LibraryParams convertToLibraryParams = MediaUtils.convertToLibraryParams(MediaBrowserImplLegacy.this.mContext, browserCompat.getNotifyChildrenChangedOptions());
                MediaBrowserImplLegacy.this.getMediaBrowser().notifyBrowserCallback(new MediaBrowser.BrowserCallbackRunnable() {
                    public void run(MediaBrowser.BrowserCallback browserCallback) {
                        browserCallback.onChildrenChanged(MediaBrowserImplLegacy.this.getMediaBrowser(), str, size, convertToLibraryParams);
                    }
                });
            }
        }
    }

    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        final ResolvableFuture<LibraryResult> mFuture;
        final String mParentId;

        GetChildrenCallback(ResolvableFuture<LibraryResult> resolvableFuture, String str) {
            this.mFuture = resolvableFuture;
            this.mParentId = str;
        }

        public void onError(String str) {
            this.mFuture.set(new LibraryResult(-1));
        }

        public void onError(String str, Bundle bundle) {
            this.mFuture.set(new LibraryResult(-1));
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoaded(str, list, (Bundle) null);
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "GetChildrenCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                this.mFuture.set(new LibraryResult(-100));
                return;
            }
            browserCompat.unsubscribe(this.mParentId, this);
            ArrayList arrayList = new ArrayList();
            if (list == null) {
                this.mFuture.set(new LibraryResult(-1));
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(MediaUtils.convertToMediaItem(list.get(i)));
            }
            this.mFuture.set(new LibraryResult(0, (List<MediaItem>) arrayList, (MediaLibraryService.LibraryParams) null));
        }
    }
}
