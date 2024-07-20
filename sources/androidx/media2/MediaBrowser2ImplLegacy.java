package androidx.media2;

import android.content.Context;
import android.os.Bundle;
import android.support.p064v4.media.MediaBrowserCompat;
import androidx.media2.MediaBrowser2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

class MediaBrowser2ImplLegacy extends MediaController2ImplLegacy implements MediaBrowser2.MediaBrowser2Impl {
    final HashMap<Bundle, MediaBrowserCompat> mBrowserCompats = new HashMap<>();
    private final HashMap<String, List<SubscribeCallback>> mSubscribeCallbacks = new HashMap<>();

    MediaBrowser2ImplLegacy(Context context, MediaBrowser2 mediaBrowser2, SessionToken2 sessionToken2, Executor executor, MediaBrowser2.BrowserCallback browserCallback) {
        super(context, mediaBrowser2, sessionToken2, executor, browserCallback);
    }

    public MediaBrowser2 getInstance() {
        return (MediaBrowser2) super.getInstance();
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

    public void getLibraryRoot(final Bundle bundle) {
        final MediaBrowserCompat browserCompat = getBrowserCompat(bundle);
        if (browserCompat != null) {
            getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    MediaBrowser2ImplLegacy.this.getCallback().onGetLibraryRootDone(MediaBrowser2ImplLegacy.this.getInstance(), bundle, browserCompat.getRoot(), browserCompat.getExtras());
                }
            });
        } else {
            getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(MediaBrowser2ImplLegacy.this.getContext(), MediaBrowser2ImplLegacy.this.getSessionToken().getComponentName(), new GetLibraryRootCallback(bundle), bundle);
                    synchronized (MediaBrowser2ImplLegacy.this.mLock) {
                        MediaBrowser2ImplLegacy.this.mBrowserCompats.put(bundle, mediaBrowserCompat);
                    }
                    mediaBrowserCompat.connect();
                }
            });
        }
    }

    public void subscribe(String str, Bundle bundle) {
        if (str != null) {
            MediaBrowserCompat browserCompat = getBrowserCompat();
            if (browserCompat != null) {
                SubscribeCallback subscribeCallback = new SubscribeCallback();
                synchronized (this.mLock) {
                    List list = this.mSubscribeCallbacks.get(str);
                    if (list == null) {
                        list = new ArrayList();
                        this.mSubscribeCallbacks.put(str, list);
                    }
                    list.add(subscribeCallback);
                }
                browserCompat.subscribe(str, bundle, subscribeCallback);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("parentId shouldn't be null");
    }

    public void unsubscribe(String str) {
        if (str != null) {
            MediaBrowserCompat browserCompat = getBrowserCompat();
            if (browserCompat != null) {
                synchronized (this.mLock) {
                    List list = this.mSubscribeCallbacks.get(str);
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            browserCompat.unsubscribe(str, (MediaBrowserCompat.SubscriptionCallback) list.get(i));
                        }
                        return;
                    }
                    return;
                }
            }
            return;
        }
        throw new IllegalArgumentException("parentId shouldn't be null");
    }

    public void getChildren(String str, int i, int i2, Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId shouldn't be null");
        } else if (i < 0) {
            throw new IllegalArgumentException("page shouldn't be negative");
        } else if (i2 >= 1) {
            MediaBrowserCompat browserCompat = getBrowserCompat();
            if (browserCompat != null) {
                Bundle createBundle = createBundle(bundle);
                createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
                createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
                browserCompat.subscribe(str, createBundle, new GetChildrenCallback(str, i, i2));
            }
        } else {
            throw new IllegalArgumentException("pageSize shouldn't be less than 1");
        }
    }

    public void getItem(final String str) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat != null) {
            browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() {
                public void onItemLoaded(final MediaBrowserCompat.MediaItem mediaItem) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onGetItemDone(MediaBrowser2ImplLegacy.this.getInstance(), str, MediaUtils2.convertToMediaItem2(mediaItem));
                        }
                    });
                }

                public void onError(String str) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onGetItemDone(MediaBrowser2ImplLegacy.this.getInstance(), str, (MediaItem2) null);
                        }
                    });
                }
            });
        }
    }

    public void search(String str, Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat != null) {
            browserCompat.search(str, bundle, new MediaBrowserCompat.SearchCallback() {
                public void onSearchResult(final String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onSearchResultChanged(MediaBrowser2ImplLegacy.this.getInstance(), str, list.size(), (Bundle) null);
                        }
                    });
                }

                public void onError(final String str, Bundle bundle) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onSearchResultChanged(MediaBrowser2ImplLegacy.this.getInstance(), str, 0, (Bundle) null);
                        }
                    });
                }
            });
        }
    }

    public void getSearchResult(String str, final int i, final int i2, Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat != null) {
            Bundle createBundle = createBundle(bundle);
            createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
            createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
            browserCompat.search(str, createBundle, new MediaBrowserCompat.SearchCallback() {
                public void onSearchResult(final String str, Bundle bundle, final List<MediaBrowserCompat.MediaItem> list) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onGetSearchResultDone(MediaBrowser2ImplLegacy.this.getInstance(), str, i, i2, MediaUtils2.convertMediaItemListToMediaItem2List(list), (Bundle) null);
                        }
                    });
                }

                public void onError(final String str, Bundle bundle) {
                    MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            MediaBrowser2ImplLegacy.this.getCallback().onGetSearchResultDone(MediaBrowser2ImplLegacy.this.getInstance(), str, i, i2, (List<MediaItem2>) null, (Bundle) null);
                        }
                    });
                }
            });
        }
    }

    public MediaBrowser2.BrowserCallback getCallback() {
        return (MediaBrowser2.BrowserCallback) super.getCallback();
    }

    private MediaBrowserCompat getBrowserCompat(Bundle bundle) {
        MediaBrowserCompat mediaBrowserCompat;
        synchronized (this.mLock) {
            mediaBrowserCompat = this.mBrowserCompats.get(bundle);
        }
        return mediaBrowserCompat;
    }

    private Bundle createBundle(Bundle bundle) {
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }

    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        final Bundle mExtras;

        GetLibraryRootCallback(Bundle bundle) {
            this.mExtras = bundle;
        }

        public void onConnected() {
            MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    MediaBrowserCompat mediaBrowserCompat;
                    synchronized (MediaBrowser2ImplLegacy.this.mLock) {
                        mediaBrowserCompat = MediaBrowser2ImplLegacy.this.mBrowserCompats.get(GetLibraryRootCallback.this.mExtras);
                    }
                    if (mediaBrowserCompat != null) {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetLibraryRootDone(MediaBrowser2ImplLegacy.this.getInstance(), GetLibraryRootCallback.this.mExtras, mediaBrowserCompat.getRoot(), mediaBrowserCompat.getExtras());
                    }
                }
            });
        }

        public void onConnectionSuspended() {
            MediaBrowser2ImplLegacy.this.close();
        }

        public void onConnectionFailed() {
            MediaBrowser2ImplLegacy.this.close();
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
            MediaBrowserCompat browserCompat = MediaBrowser2ImplLegacy.this.getBrowserCompat();
            if (browserCompat != null && list != null) {
                final int size = list.size();
                final Bundle notifyChildrenChangedOptions = browserCompat.getNotifyChildrenChangedOptions();
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onChildrenChanged(MediaBrowser2ImplLegacy.this.getInstance(), str, size, notifyChildrenChangedOptions);
                    }
                });
            }
        }
    }

    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        final int mPage;
        final int mPageSize;
        final String mParentId;

        GetChildrenCallback(String str, int i, int i2) {
            this.mParentId = str;
            this.mPage = i;
            this.mPageSize = i2;
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
            final ArrayList arrayList;
            if (list == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    arrayList2.add(MediaUtils2.convertToMediaItem2(list.get(i)));
                }
                arrayList = arrayList2;
            }
            MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    MediaBrowserCompat browserCompat = MediaBrowser2ImplLegacy.this.getBrowserCompat();
                    if (browserCompat != null) {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetChildrenDone(MediaBrowser2ImplLegacy.this.getInstance(), str, GetChildrenCallback.this.mPage, GetChildrenCallback.this.mPageSize, arrayList, (Bundle) null);
                        browserCompat.unsubscribe(GetChildrenCallback.this.mParentId, GetChildrenCallback.this);
                    }
                }
            });
        }
    }
}
