package androidx.media2;

import androidx.collection.ArrayMap;
import androidx.media2.MediaPlayerConnector;
import androidx.media2.MediaSession2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SessionPlaylistAgentImplBase extends MediaPlaylistAgent {
    public static final int END_OF_PLAYLIST = -1;
    public static final int NO_VALID_ITEMS = -2;
    PlayItem mCurrent;
    private MediaSession2.OnDataSourceMissingHelper mDsmHelper;
    final PlayItem mEopPlayItem = new PlayItem(-1, (DataSourceDesc2) null);
    private Map<MediaItem2, DataSourceDesc2> mItemDsdMap = new ArrayMap();
    final Object mLock = new Object();
    private MediaMetadata2 mMetadata;
    MediaPlayerConnector mPlayer;
    private final MyPlayerEventCallback mPlayerCallback;
    private ArrayList<MediaItem2> mPlaylist = new ArrayList<>();
    private int mRepeatMode;
    private final MediaSession2ImplBase mSession;
    private int mShuffleMode;
    ArrayList<MediaItem2> mShuffledList = new ArrayList<>();

    private static int clamp(int i, int i2) {
        if (i < 0) {
            return 0;
        }
        return i > i2 ? i2 : i;
    }

    private class MyPlayerEventCallback extends MediaPlayerConnector.PlayerEventCallback {
        MyPlayerEventCallback() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCurrentDataSourceChanged(androidx.media2.MediaPlayerConnector r4, androidx.media2.DataSourceDesc2 r5) {
            /*
                r3 = this;
                androidx.media2.SessionPlaylistAgentImplBase r0 = androidx.media2.SessionPlaylistAgentImplBase.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                androidx.media2.SessionPlaylistAgentImplBase r1 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                androidx.media2.MediaPlayerConnector r1 = r1.mPlayer     // Catch:{ all -> 0x002d }
                if (r1 == r4) goto L_0x000d
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x000d:
                if (r5 != 0) goto L_0x002b
                androidx.media2.SessionPlaylistAgentImplBase r4 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                androidx.media2.SessionPlaylistAgentImplBase$PlayItem r4 = r4.mCurrent     // Catch:{ all -> 0x002d }
                if (r4 == 0) goto L_0x002b
                androidx.media2.SessionPlaylistAgentImplBase r4 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                androidx.media2.SessionPlaylistAgentImplBase r5 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                androidx.media2.SessionPlaylistAgentImplBase r1 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r1.mCurrent     // Catch:{ all -> 0x002d }
                int r1 = r1.shuffledIdx     // Catch:{ all -> 0x002d }
                r2 = 1
                androidx.media2.SessionPlaylistAgentImplBase$PlayItem r5 = r5.getNextValidPlayItemLocked(r1, r2)     // Catch:{ all -> 0x002d }
                r4.mCurrent = r5     // Catch:{ all -> 0x002d }
                androidx.media2.SessionPlaylistAgentImplBase r4 = androidx.media2.SessionPlaylistAgentImplBase.this     // Catch:{ all -> 0x002d }
                r4.updateCurrentIfNeededLocked()     // Catch:{ all -> 0x002d }
            L_0x002b:
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return
            L_0x002d:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.MyPlayerEventCallback.onCurrentDataSourceChanged(androidx.media2.MediaPlayerConnector, androidx.media2.DataSourceDesc2):void");
        }
    }

    private class PlayItem {
        public DataSourceDesc2 dsd;
        public MediaItem2 mediaItem;
        public int shuffledIdx;

        PlayItem(SessionPlaylistAgentImplBase sessionPlaylistAgentImplBase, int i) {
            this(i, (DataSourceDesc2) null);
        }

        PlayItem(int i, DataSourceDesc2 dataSourceDesc2) {
            this.shuffledIdx = i;
            if (i >= 0) {
                this.mediaItem = SessionPlaylistAgentImplBase.this.mShuffledList.get(i);
                if (dataSourceDesc2 == null) {
                    synchronized (SessionPlaylistAgentImplBase.this.mLock) {
                        this.dsd = SessionPlaylistAgentImplBase.this.retrieveDataSourceDescLocked(this.mediaItem);
                    }
                    return;
                }
                this.dsd = dataSourceDesc2;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isValid() {
            if (this == SessionPlaylistAgentImplBase.this.mEopPlayItem) {
                return true;
            }
            MediaItem2 mediaItem2 = this.mediaItem;
            if (mediaItem2 == null || this.dsd == null) {
                return false;
            }
            if (mediaItem2.getDataSourceDesc() != null && !this.mediaItem.getDataSourceDesc().equals(this.dsd)) {
                return false;
            }
            synchronized (SessionPlaylistAgentImplBase.this.mLock) {
                if (this.shuffledIdx >= SessionPlaylistAgentImplBase.this.mShuffledList.size()) {
                    return false;
                }
                if (this.mediaItem != SessionPlaylistAgentImplBase.this.mShuffledList.get(this.shuffledIdx)) {
                    return false;
                }
                return true;
            }
        }
    }

    SessionPlaylistAgentImplBase(MediaSession2ImplBase mediaSession2ImplBase, MediaPlayerConnector mediaPlayerConnector) {
        if (mediaSession2ImplBase == null) {
            throw new IllegalArgumentException("sessionImpl shouldn't be null");
        } else if (mediaPlayerConnector != null) {
            this.mSession = mediaSession2ImplBase;
            this.mPlayer = mediaPlayerConnector;
            this.mPlayerCallback = new MyPlayerEventCallback();
            this.mPlayer.registerPlayerEventCallback(this.mSession.getCallbackExecutor(), this.mPlayerCallback);
        } else {
            throw new IllegalArgumentException("player shouldn't be null");
        }
    }

    /* access modifiers changed from: package-private */
    public void setPlayer(MediaPlayerConnector mediaPlayerConnector) {
        synchronized (this.mLock) {
            if (mediaPlayerConnector != this.mPlayer) {
                mediaPlayerConnector.unregisterPlayerEventCallback(this.mPlayerCallback);
                this.mPlayer = mediaPlayerConnector;
                mediaPlayerConnector.registerPlayerEventCallback(this.mSession.getCallbackExecutor(), this.mPlayerCallback);
            }
        }
    }

    public void setOnDataSourceMissingHelper(MediaSession2.OnDataSourceMissingHelper onDataSourceMissingHelper) {
        synchronized (this.mLock) {
            this.mDsmHelper = onDataSourceMissingHelper;
        }
    }

    public void clearOnDataSourceMissingHelper() {
        synchronized (this.mLock) {
            this.mDsmHelper = null;
        }
    }

    public List<MediaItem2> getPlaylist() {
        List<MediaItem2> unmodifiableList;
        synchronized (this.mLock) {
            unmodifiableList = Collections.unmodifiableList(this.mPlaylist);
        }
        return unmodifiableList;
    }

    public void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        if (list != null) {
            synchronized (this.mLock) {
                this.mItemDsdMap.clear();
                this.mPlaylist.clear();
                this.mPlaylist.addAll(list);
                applyShuffleModeLocked();
                this.mMetadata = mediaMetadata2;
                this.mCurrent = getNextValidPlayItemLocked(-1, 1);
                updatePlayerDataSourceLocked();
            }
            notifyPlaylistChanged();
            return;
        }
        throw new IllegalArgumentException("list shouldn't be null");
    }

    public MediaMetadata2 getPlaylistMetadata() {
        MediaMetadata2 mediaMetadata2;
        synchronized (this.mLock) {
            mediaMetadata2 = this.mMetadata;
        }
        return mediaMetadata2;
    }

    public void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2) {
        synchronized (this.mLock) {
            if (mediaMetadata2 != this.mMetadata) {
                this.mMetadata = mediaMetadata2;
                notifyPlaylistMetadataChanged();
            }
        }
    }

    public MediaItem2 getCurrentMediaItem() {
        MediaItem2 mediaItem2;
        synchronized (this.mLock) {
            mediaItem2 = this.mCurrent == null ? null : this.mCurrent.mediaItem;
        }
        return mediaItem2;
    }

    public void addPlaylistItem(int i, MediaItem2 mediaItem2) {
        if (mediaItem2 != null) {
            synchronized (this.mLock) {
                int clamp = clamp(i, this.mPlaylist.size());
                this.mPlaylist.add(clamp, mediaItem2);
                if (this.mShuffleMode == 0) {
                    this.mShuffledList.add(clamp, mediaItem2);
                } else {
                    double random = Math.random();
                    double size = (double) (this.mShuffledList.size() + 1);
                    Double.isNaN(size);
                    this.mShuffledList.add((int) (random * size), mediaItem2);
                }
                if (!hasValidItem()) {
                    this.mCurrent = getNextValidPlayItemLocked(-1, 1);
                    updatePlayerDataSourceLocked();
                } else {
                    updateCurrentIfNeededLocked();
                }
            }
            notifyPlaylistChanged();
            return;
        }
        throw new IllegalArgumentException("item shouldn't be null");
    }

    public void removePlaylistItem(MediaItem2 mediaItem2) {
        if (mediaItem2 != null) {
            synchronized (this.mLock) {
                if (this.mPlaylist.remove(mediaItem2)) {
                    this.mShuffledList.remove(mediaItem2);
                    this.mItemDsdMap.remove(mediaItem2);
                    updateCurrentIfNeededLocked();
                    notifyPlaylistChanged();
                    return;
                }
                return;
            }
        }
        throw new IllegalArgumentException("item shouldn't be null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        notifyPlaylistChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void replacePlaylistItem(int r6, androidx.media2.MediaItem2 r7) {
        /*
            r5 = this;
            if (r7 == 0) goto L_0x0058
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            java.util.ArrayList<androidx.media2.MediaItem2> r1 = r5.mPlaylist     // Catch:{ all -> 0x0055 }
            int r1 = r1.size()     // Catch:{ all -> 0x0055 }
            if (r1 > 0) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return
        L_0x000f:
            java.util.ArrayList<androidx.media2.MediaItem2> r1 = r5.mPlaylist     // Catch:{ all -> 0x0055 }
            int r1 = r1.size()     // Catch:{ all -> 0x0055 }
            r2 = 1
            int r1 = r1 - r2
            int r6 = clamp(r6, r1)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<androidx.media2.MediaItem2> r1 = r5.mShuffledList     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<androidx.media2.MediaItem2> r3 = r5.mPlaylist     // Catch:{ all -> 0x0055 }
            java.lang.Object r3 = r3.get(r6)     // Catch:{ all -> 0x0055 }
            int r1 = r1.indexOf(r3)     // Catch:{ all -> 0x0055 }
            java.util.Map<androidx.media2.MediaItem2, androidx.media2.DataSourceDesc2> r3 = r5.mItemDsdMap     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<androidx.media2.MediaItem2> r4 = r5.mShuffledList     // Catch:{ all -> 0x0055 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0055 }
            r3.remove(r4)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<androidx.media2.MediaItem2> r3 = r5.mShuffledList     // Catch:{ all -> 0x0055 }
            r3.set(r1, r7)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList<androidx.media2.MediaItem2> r1 = r5.mPlaylist     // Catch:{ all -> 0x0055 }
            r1.set(r6, r7)     // Catch:{ all -> 0x0055 }
            boolean r6 = r5.hasValidItem()     // Catch:{ all -> 0x0055 }
            if (r6 != 0) goto L_0x004d
            r6 = -1
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r6 = r5.getNextValidPlayItemLocked(r6, r2)     // Catch:{ all -> 0x0055 }
            r5.mCurrent = r6     // Catch:{ all -> 0x0055 }
            r5.updatePlayerDataSourceLocked()     // Catch:{ all -> 0x0055 }
            goto L_0x0050
        L_0x004d:
            r5.updateCurrentIfNeededLocked()     // Catch:{ all -> 0x0055 }
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            r5.notifyPlaylistChanged()
            return
        L_0x0055:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r6
        L_0x0058:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "item shouldn't be null"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.replacePlaylistItem(int, androidx.media2.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToPlaylistItem(androidx.media2.MediaItem2 r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0031
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.hasValidItem()     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x002c
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r2.mCurrent     // Catch:{ all -> 0x002e }
            androidx.media2.MediaItem2 r1 = r1.mediaItem     // Catch:{ all -> 0x002e }
            boolean r1 = r3.equals(r1)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0016
            goto L_0x002c
        L_0x0016:
            java.util.ArrayList<androidx.media2.MediaItem2> r1 = r2.mShuffledList     // Catch:{ all -> 0x002e }
            int r3 = r1.indexOf(r3)     // Catch:{ all -> 0x002e }
            if (r3 >= 0) goto L_0x0020
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x0020:
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = new androidx.media2.SessionPlaylistAgentImplBase$PlayItem     // Catch:{ all -> 0x002e }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x002e }
            r2.mCurrent = r1     // Catch:{ all -> 0x002e }
            r2.updateCurrentIfNeededLocked()     // Catch:{ all -> 0x002e }
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r3
        L_0x0031:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "item shouldn't be null"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.skipToPlaylistItem(androidx.media2.MediaItem2):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToPreviousItem() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.hasValidItem()     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x000b:
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r3.mCurrent     // Catch:{ all -> 0x001f }
            int r1 = r1.shuffledIdx     // Catch:{ all -> 0x001f }
            r2 = -1
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r3.getNextValidPlayItemLocked(r1, r2)     // Catch:{ all -> 0x001f }
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r2 = r3.mEopPlayItem     // Catch:{ all -> 0x001f }
            if (r1 == r2) goto L_0x001d
            r3.mCurrent = r1     // Catch:{ all -> 0x001f }
            r3.updateCurrentIfNeededLocked()     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            return
        L_0x001f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.skipToPreviousItem():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void skipToNextItem() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.hasValidItem()     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0024
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r3.mCurrent     // Catch:{ all -> 0x0026 }
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r2 = r3.mEopPlayItem     // Catch:{ all -> 0x0026 }
            if (r1 != r2) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r3.mCurrent     // Catch:{ all -> 0x0026 }
            int r1 = r1.shuffledIdx     // Catch:{ all -> 0x0026 }
            r2 = 1
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r1 = r3.getNextValidPlayItemLocked(r1, r2)     // Catch:{ all -> 0x0026 }
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r2 = r3.mEopPlayItem     // Catch:{ all -> 0x0026 }
            if (r1 == r2) goto L_0x0022
            r3.mCurrent = r1     // Catch:{ all -> 0x0026 }
            r3.updateCurrentIfNeededLocked()     // Catch:{ all -> 0x0026 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            return
        L_0x0026:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0026 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.skipToNextItem():void");
    }

    public int getRepeatMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mRepeatMode;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0044, code lost:
        notifyRepeatModeChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRepeatMode(int r5) {
        /*
            r4 = this;
            if (r5 < 0) goto L_0x004b
            r0 = 3
            if (r5 <= r0) goto L_0x0006
            goto L_0x004b
        L_0x0006:
            java.lang.Object r1 = r4.mLock
            monitor-enter(r1)
            int r2 = r4.mRepeatMode     // Catch:{ all -> 0x0048 }
            if (r2 != r5) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            return
        L_0x000f:
            r4.mRepeatMode = r5     // Catch:{ all -> 0x0048 }
            if (r5 == 0) goto L_0x003d
            r2 = 1
            if (r5 == r2) goto L_0x002d
            r3 = 2
            if (r5 == r3) goto L_0x001c
            if (r5 == r0) goto L_0x001c
            goto L_0x0043
        L_0x001c:
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r5 = r4.mCurrent     // Catch:{ all -> 0x0048 }
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r0 = r4.mEopPlayItem     // Catch:{ all -> 0x0048 }
            if (r5 != r0) goto L_0x003d
            r5 = -1
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r5 = r4.getNextValidPlayItemLocked(r5, r2)     // Catch:{ all -> 0x0048 }
            r4.mCurrent = r5     // Catch:{ all -> 0x0048 }
            r4.updatePlayerDataSourceLocked()     // Catch:{ all -> 0x0048 }
            goto L_0x003d
        L_0x002d:
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r5 = r4.mCurrent     // Catch:{ all -> 0x0048 }
            if (r5 == 0) goto L_0x0043
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r5 = r4.mCurrent     // Catch:{ all -> 0x0048 }
            androidx.media2.SessionPlaylistAgentImplBase$PlayItem r0 = r4.mEopPlayItem     // Catch:{ all -> 0x0048 }
            if (r5 == r0) goto L_0x0043
            androidx.media2.MediaPlayerConnector r5 = r4.mPlayer     // Catch:{ all -> 0x0048 }
            r5.loopCurrent(r2)     // Catch:{ all -> 0x0048 }
            goto L_0x0043
        L_0x003d:
            androidx.media2.MediaPlayerConnector r5 = r4.mPlayer     // Catch:{ all -> 0x0048 }
            r0 = 0
            r5.loopCurrent(r0)     // Catch:{ all -> 0x0048 }
        L_0x0043:
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            r4.notifyRepeatModeChanged()
            return
        L_0x0048:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0048 }
            throw r5
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.SessionPlaylistAgentImplBase.setRepeatMode(int):void");
    }

    public int getShuffleMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mShuffleMode;
        }
        return i;
    }

    public void setShuffleMode(int i) {
        if (i >= 0 && i <= 2) {
            synchronized (this.mLock) {
                if (this.mShuffleMode != i) {
                    this.mShuffleMode = i;
                    applyShuffleModeLocked();
                    updateCurrentIfNeededLocked();
                    notifyShuffleModeChanged();
                }
            }
        }
    }

    public MediaItem2 getMediaItem(DataSourceDesc2 dataSourceDesc2) {
        synchronized (this.mLock) {
            for (Map.Entry next : this.mItemDsdMap.entrySet()) {
                if (next.getValue() == dataSourceDesc2) {
                    MediaItem2 mediaItem2 = (MediaItem2) next.getKey();
                    return mediaItem2;
                }
            }
            return super.getMediaItem(dataSourceDesc2);
        }
    }

    public int getCurShuffledIndex() {
        int i;
        synchronized (this.mLock) {
            i = hasValidItem() ? this.mCurrent.shuffledIdx : -2;
        }
        return i;
    }

    private boolean hasValidItem() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mCurrent != null;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public DataSourceDesc2 retrieveDataSourceDescLocked(MediaItem2 mediaItem2) {
        DataSourceDesc2 dataSourceDesc = mediaItem2.getDataSourceDesc();
        if (dataSourceDesc != null) {
            this.mItemDsdMap.put(mediaItem2, dataSourceDesc);
            return dataSourceDesc;
        }
        DataSourceDesc2 dataSourceDesc2 = this.mItemDsdMap.get(mediaItem2);
        if (dataSourceDesc2 != null) {
            return dataSourceDesc2;
        }
        MediaSession2.OnDataSourceMissingHelper onDataSourceMissingHelper = this.mDsmHelper;
        if (!(onDataSourceMissingHelper == null || (dataSourceDesc2 = onDataSourceMissingHelper.onDataSourceMissing(this.mSession.getInstance(), mediaItem2)) == null)) {
            this.mItemDsdMap.put(mediaItem2, dataSourceDesc2);
        }
        return dataSourceDesc2;
    }

    /* access modifiers changed from: package-private */
    public PlayItem getNextValidPlayItemLocked(int i, int i2) {
        int size = this.mPlaylist.size();
        if (i == -1) {
            i = i2 > 0 ? -1 : size;
        }
        for (int i3 = 0; i3 < size; i3++) {
            r6 += i2;
            if (r6 < 0 || r6 >= this.mPlaylist.size()) {
                if (this.mRepeatMode != 0) {
                    r6 = r6 < 0 ? this.mPlaylist.size() - 1 : 0;
                } else if (i3 == size - 1) {
                    return null;
                } else {
                    return this.mEopPlayItem;
                }
            }
            DataSourceDesc2 retrieveDataSourceDescLocked = retrieveDataSourceDescLocked(this.mShuffledList.get(r6));
            if (retrieveDataSourceDescLocked != null) {
                return new PlayItem(r6, retrieveDataSourceDescLocked);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateCurrentIfNeededLocked() {
        if (hasValidItem() && !this.mCurrent.isValid()) {
            int indexOf = this.mShuffledList.indexOf(this.mCurrent.mediaItem);
            if (indexOf >= 0) {
                this.mCurrent.shuffledIdx = indexOf;
            } else if (this.mCurrent.shuffledIdx >= this.mShuffledList.size()) {
                this.mCurrent = getNextValidPlayItemLocked(this.mShuffledList.size() - 1, 1);
                updatePlayerDataSourceLocked();
            } else {
                PlayItem playItem = this.mCurrent;
                playItem.mediaItem = this.mShuffledList.get(playItem.shuffledIdx);
                if (retrieveDataSourceDescLocked(this.mCurrent.mediaItem) == null) {
                    this.mCurrent = getNextValidPlayItemLocked(this.mCurrent.shuffledIdx, 1);
                    updatePlayerDataSourceLocked();
                }
            }
        }
    }

    private void updatePlayerDataSourceLocked() {
        PlayItem playItem = this.mCurrent;
        if (playItem != null && playItem != this.mEopPlayItem) {
            if (this.mPlayer.getPlayerState() == 0) {
                this.mPlayer.setDataSource(this.mCurrent.dsd);
            } else {
                this.mPlayer.setNextDataSource(this.mCurrent.dsd);
                this.mPlayer.skipToNext();
            }
            MediaPlayerConnector mediaPlayerConnector = this.mPlayer;
            boolean z = true;
            if (this.mRepeatMode != 1) {
                z = false;
            }
            mediaPlayerConnector.loopCurrent(z);
        }
    }

    private void applyShuffleModeLocked() {
        this.mShuffledList.clear();
        this.mShuffledList.addAll(this.mPlaylist);
        int i = this.mShuffleMode;
        if (i == 1 || i == 2) {
            Collections.shuffle(this.mShuffledList);
        }
    }
}
