package androidx.media2;

import android.media.AudioAttributes;
import android.media.DeniedByServerException;
import android.media.MediaDataSource;
import android.media.MediaDrm;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.MediaTimestamp;
import android.media.PlaybackParams;
import android.media.ResourceBusyException;
import android.media.SubtitleData;
import android.media.SyncParams;
import android.media.TimedMetaData;
import android.media.UnsupportedSchemeException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Preconditions;
import androidx.media.AudioAttributesCompat;
import androidx.media2.MediaPlayer2;
import androidx.media2.MediaPlayerConnector;
import androidx.media2.PlaybackParams2;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public final class MediaPlayer2Impl extends MediaPlayer2 {
    private static final int SOURCE_STATE_ERROR = -1;
    private static final int SOURCE_STATE_INIT = 0;
    private static final int SOURCE_STATE_PREPARED = 2;
    private static final int SOURCE_STATE_PREPARING = 1;
    private static final String TAG = "MediaPlayer2Impl";
    static ArrayMap<Integer, Integer> sErrorEventMap;
    static ArrayMap<Integer, Integer> sInfoEventMap;
    static ArrayMap<Integer, Integer> sStateMap;
    Task mCurrentTask;
    private Pair<Executor, MediaPlayer2.DrmEventCallback> mDrmEventCallbackRecord;
    private final Handler mEndPositionHandler;
    private HandlerThread mHandlerThread;
    private final Object mLock = new Object();
    MediaPlayerConnectorImpl mMediaPlayerConnectorImpl;
    private Pair<Executor, MediaPlayer2.EventCallback> mMp2EventCallbackRecord;
    private final ArrayDeque<Task> mPendingTasks = new ArrayDeque<>();
    MediaPlayerSourceQueue mPlayer;
    private ArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> mPlayerEventCallbackMap = new ArrayMap<>();
    private final Handler mTaskHandler;
    final Object mTaskLock = new Object();

    private interface DrmEventNotifier {
        void notify(MediaPlayer2.DrmEventCallback drmEventCallback);
    }

    private interface Mp2EventNotifier {
        void notify(MediaPlayer2.EventCallback eventCallback);
    }

    private interface PlayerEventNotifier {
        void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback);
    }

    public float getMaxPlayerVolume() {
        return 1.0f;
    }

    static {
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        sInfoEventMap = arrayMap;
        arrayMap.put(1, 1);
        sInfoEventMap.put(2, 1);
        sInfoEventMap.put(3, 3);
        sInfoEventMap.put(700, 700);
        sInfoEventMap.put(701, 701);
        sInfoEventMap.put(702, 702);
        sInfoEventMap.put(800, 800);
        sInfoEventMap.put(801, 801);
        sInfoEventMap.put(802, 802);
        sInfoEventMap.put(804, 804);
        sInfoEventMap.put(805, 805);
        sInfoEventMap.put(901, 901);
        sInfoEventMap.put(902, 902);
        ArrayMap<Integer, Integer> arrayMap2 = new ArrayMap<>();
        sErrorEventMap = arrayMap2;
        arrayMap2.put(1, 1);
        sErrorEventMap.put(200, 200);
        sErrorEventMap.put(-1004, -1004);
        sErrorEventMap.put(-1007, -1007);
        sErrorEventMap.put(-1010, -1010);
        sErrorEventMap.put(-110, -110);
        ArrayMap<Integer, Integer> arrayMap3 = new ArrayMap<>();
        sStateMap = arrayMap3;
        arrayMap3.put(1001, 0);
        sStateMap.put(1002, 1);
        sStateMap.put(1003, 1);
        sStateMap.put(1004, 2);
        sStateMap.put(1005, 3);
    }

    /* access modifiers changed from: package-private */
    public void handleDataSourceError(final DataSourceError dataSourceError) {
        if (dataSourceError != null) {
            notifyMediaPlayer2Event(new Mp2EventNotifier() {
                public void notify(MediaPlayer2.EventCallback eventCallback) {
                    eventCallback.onError(MediaPlayer2Impl.this, dataSourceError.mDSD, dataSourceError.mWhat, dataSourceError.mExtra);
                }
            });
        }
    }

    public MediaPlayer2Impl() {
        HandlerThread handlerThread = new HandlerThread("MediaPlayer2TaskThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        Looper looper = this.mHandlerThread.getLooper();
        this.mEndPositionHandler = new Handler(looper);
        this.mTaskHandler = new Handler(looper);
        this.mPlayer = new MediaPlayerSourceQueue();
    }

    public MediaPlayerConnector getMediaPlayerConnector() {
        MediaPlayerConnectorImpl mediaPlayerConnectorImpl;
        synchronized (this.mLock) {
            if (this.mMediaPlayerConnectorImpl == null) {
                this.mMediaPlayerConnectorImpl = new MediaPlayerConnectorImpl();
            }
            mediaPlayerConnectorImpl = this.mMediaPlayerConnectorImpl;
        }
        return mediaPlayerConnectorImpl;
    }

    public void close() {
        this.mPlayer.release();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mHandlerThread = null;
        }
    }

    public void play() {
        addTask(new Task(5, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.play();
            }
        });
    }

    public void prepare() {
        addTask(new Task(6, true) {
            /* access modifiers changed from: package-private */
            public void process() throws IOException {
                MediaPlayer2Impl.this.mPlayer.prepareAsync();
            }
        });
    }

    public void pause() {
        addTask(new Task(4, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.pause();
            }
        });
    }

    public void skipToNext() {
        addTask(new Task(29, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.skipToNext();
            }
        });
    }

    public long getCurrentPosition() {
        return this.mPlayer.getCurrentPosition();
    }

    public long getDuration() {
        return this.mPlayer.getDuration();
    }

    public long getBufferedPosition() {
        return this.mPlayer.getBufferedPosition();
    }

    public int getState() {
        return this.mPlayer.getMediaPlayer2State();
    }

    /* access modifiers changed from: package-private */
    public int getPlayerState() {
        return this.mPlayer.getPlayerState();
    }

    /* access modifiers changed from: package-private */
    public int getBufferingState() {
        return this.mPlayer.getBufferingState();
    }

    public void setAudioAttributes(final AudioAttributesCompat audioAttributesCompat) {
        addTask(new Task(16, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAudioAttributes(audioAttributesCompat);
            }
        });
    }

    public AudioAttributesCompat getAudioAttributes() {
        return this.mPlayer.getAudioAttributes();
    }

    public void setDataSource(final DataSourceDesc2 dataSourceDesc2) {
        addTask(new Task(19, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                Preconditions.checkArgument(dataSourceDesc2 != null, "the DataSourceDesc2 cannot be null");
                try {
                    MediaPlayer2Impl.this.mPlayer.setFirst(dataSourceDesc2);
                } catch (IOException e) {
                    Log.e(MediaPlayer2Impl.TAG, "process: setDataSource", e);
                }
            }
        });
    }

    public void setNextDataSource(final DataSourceDesc2 dataSourceDesc2) {
        addTask(new Task(22, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                Preconditions.checkArgument(dataSourceDesc2 != null, "the DataSourceDesc2 cannot be null");
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.setNext(dataSourceDesc2));
            }
        });
    }

    public void setNextDataSources(final List<DataSourceDesc2> list) {
        addTask(new Task(23, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                List list = list;
                if (list == null || list.size() == 0) {
                    throw new IllegalArgumentException("data source list cannot be null or empty.");
                }
                for (DataSourceDesc2 dataSourceDesc2 : list) {
                    if (dataSourceDesc2 == null) {
                        throw new IllegalArgumentException("DataSourceDesc2 in the source list cannot be null.");
                    }
                }
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.setNextMultiple(list));
            }
        });
    }

    public DataSourceDesc2 getCurrentDataSource() {
        return this.mPlayer.getFirst().getDSD();
    }

    public void loopCurrent(final boolean z) {
        addTask(new Task(3, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setLooping(z);
            }
        });
    }

    public void setPlayerVolume(final float f) {
        addTask(new Task(26, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setVolume(f);
            }
        });
    }

    public float getPlayerVolume() {
        return this.mPlayer.getVolume();
    }

    /* access modifiers changed from: package-private */
    public void registerPlayerEventCallback(Executor executor, MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
        if (playerEventCallback == null) {
            throw new IllegalArgumentException("Illegal null PlayerEventCallback");
        } else if (executor != null) {
            synchronized (this.mLock) {
                this.mPlayerEventCallbackMap.put(playerEventCallback, executor);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the PlayerEventCallback");
        }
    }

    /* access modifiers changed from: package-private */
    public void unregisterPlayerEventCallback(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
        if (playerEventCallback != null) {
            synchronized (this.mLock) {
                this.mPlayerEventCallbackMap.remove(playerEventCallback);
            }
            return;
        }
        throw new IllegalArgumentException("Illegal null PlayerEventCallback");
    }

    public void notifyWhenCommandLabelReached(final Object obj) {
        addTask(new Task(1000, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onCommandLabelReached(MediaPlayer2Impl.this, obj);
                    }
                });
            }
        });
    }

    public void setSurface(final Surface surface) {
        addTask(new Task(27, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setSurface(surface);
            }
        });
    }

    public void clearPendingCommands() {
        synchronized (this.mTaskLock) {
            this.mPendingTasks.clear();
        }
    }

    private void addTask(Task task) {
        Task peekLast;
        synchronized (this.mTaskLock) {
            if (task.mMediaCallType == 14 && (peekLast = this.mPendingTasks.peekLast()) != null && peekLast.mMediaCallType == task.mMediaCallType) {
                peekLast.mSkip = true;
            }
            this.mPendingTasks.add(task);
            processPendingTask_l();
        }
    }

    /* access modifiers changed from: package-private */
    public void processPendingTask_l() {
        if (this.mCurrentTask == null && !this.mPendingTasks.isEmpty()) {
            Task removeFirst = this.mPendingTasks.removeFirst();
            this.mCurrentTask = removeFirst;
            this.mTaskHandler.post(removeFirst);
        }
    }

    static void handleDataSource(MediaPlayerSource mediaPlayerSource) throws IOException {
        final DataSourceDesc2 dsd = mediaPlayerSource.getDSD();
        Preconditions.checkArgument(dsd != null, "the DataSourceDesc2 cannot be null");
        MediaPlayer player = mediaPlayerSource.getPlayer();
        int type = dsd.getType();
        if (type == 1) {
            player.setDataSource(new MediaDataSource() {
                CallbackDataSource2 mDataSource = ((CallbackDataSourceDesc2) dsd).getCallbackDataSource2();

                public int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
                    return this.mDataSource.readAt(j, bArr, i, i2);
                }

                public long getSize() throws IOException {
                    return this.mDataSource.getSize();
                }

                public void close() throws IOException {
                    this.mDataSource.close();
                }
            });
        } else if (type == 2) {
            FileDataSourceDesc2 fileDataSourceDesc2 = (FileDataSourceDesc2) dsd;
            player.setDataSource(fileDataSourceDesc2.getFileDescriptor(), fileDataSourceDesc2.getFileDescriptorOffset(), fileDataSourceDesc2.getFileDescriptorLength());
        } else if (type == 3) {
            UriDataSourceDesc2 uriDataSourceDesc2 = (UriDataSourceDesc2) dsd;
            player.setDataSource(uriDataSourceDesc2.getUriContext(), uriDataSourceDesc2.getUri(), uriDataSourceDesc2.getUriHeaders(), uriDataSourceDesc2.getUriCookies());
        }
    }

    public int getVideoWidth() {
        return this.mPlayer.getVideoWidth();
    }

    public int getVideoHeight() {
        return this.mPlayer.getVideoHeight();
    }

    public PersistableBundle getMetrics() {
        return this.mPlayer.getMetrics();
    }

    public void setPlaybackParams(final PlaybackParams2 playbackParams2) {
        addTask(new Task(24, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.setPlaybackParamsInternal(playbackParams2.getPlaybackParams());
            }
        });
    }

    public PlaybackParams2 getPlaybackParams() {
        return new PlaybackParams2.Builder(this.mPlayer.getPlaybackParams()).build();
    }

    public void seekTo(long j, int i) {
        final long j2 = j;
        final int i2 = i;
        addTask(new Task(14, true) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.seekTo(j2, i2);
            }
        });
    }

    public MediaTimestamp2 getTimestamp() {
        return this.mPlayer.getTimestamp();
    }

    public void reset() {
        this.mPlayer.reset();
    }

    public void setAudioSessionId(final int i) {
        addTask(new Task(17, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAudioSessionId(i);
            }
        });
    }

    public int getAudioSessionId() {
        return this.mPlayer.getAudioSessionId();
    }

    public void attachAuxEffect(final int i) {
        addTask(new Task(1, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.attachAuxEffect(i);
            }
        });
    }

    public void setAuxEffectSendLevel(final float f) {
        addTask(new Task(18, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.setAuxEffectSendLevel(f);
            }
        });
    }

    public static final class TrackInfoImpl extends MediaPlayer2.TrackInfo {
        final MediaFormat mFormat;
        final int mTrackType;

        public int getTrackType() {
            return this.mTrackType;
        }

        public String getLanguage() {
            String string = this.mFormat.getString("language");
            return string == null ? "und" : string;
        }

        public MediaFormat getFormat() {
            int i = this.mTrackType;
            if (i == 3 || i == 4) {
                return this.mFormat;
            }
            return null;
        }

        TrackInfoImpl(int i, MediaFormat mediaFormat) {
            this.mTrackType = i;
            this.mFormat = mediaFormat;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append(getClass().getName());
            sb.append('{');
            int i = this.mTrackType;
            if (i == 1) {
                sb.append("VIDEO");
            } else if (i == 2) {
                sb.append("AUDIO");
            } else if (i == 3) {
                sb.append("TIMEDTEXT");
            } else if (i != 4) {
                sb.append("UNKNOWN");
            } else {
                sb.append("SUBTITLE");
            }
            sb.append(", " + this.mFormat.toString());
            sb.append("}");
            return sb.toString();
        }
    }

    public List<MediaPlayer2.TrackInfo> getTrackInfo() {
        MediaPlayer.TrackInfo[] trackInfo = this.mPlayer.getTrackInfo();
        ArrayList arrayList = new ArrayList();
        for (MediaPlayer.TrackInfo trackInfo2 : trackInfo) {
            arrayList.add(new TrackInfoImpl(trackInfo2.getTrackType(), trackInfo2.getFormat()));
        }
        return arrayList;
    }

    public int getSelectedTrack(int i) {
        return this.mPlayer.getSelectedTrack(i);
    }

    public void selectTrack(final int i) {
        addTask(new Task(15, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.selectTrack(i);
            }
        });
    }

    public void deselectTrack(final int i) {
        addTask(new Task(2, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                MediaPlayer2Impl.this.mPlayer.deselectTrack(i);
            }
        });
    }

    public void setEventCallback(Executor executor, MediaPlayer2.EventCallback eventCallback) {
        if (eventCallback == null) {
            throw new IllegalArgumentException("Illegal null EventCallback");
        } else if (executor != null) {
            synchronized (this.mLock) {
                this.mMp2EventCallbackRecord = new Pair<>(executor, eventCallback);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the EventCallback");
        }
    }

    public void clearEventCallback() {
        synchronized (this.mLock) {
            this.mMp2EventCallbackRecord = null;
        }
    }

    public void setOnDrmConfigHelper(final MediaPlayer2.OnDrmConfigHelper onDrmConfigHelper) {
        this.mPlayer.setOnDrmConfigHelper(new MediaPlayer.OnDrmConfigHelper() {
            public void onDrmConfig(MediaPlayer mediaPlayer) {
                DataSourceDesc2 dataSourceDesc2;
                MediaPlayerSource sourceForPlayer = MediaPlayer2Impl.this.mPlayer.getSourceForPlayer(mediaPlayer);
                if (sourceForPlayer == null) {
                    dataSourceDesc2 = null;
                } else {
                    dataSourceDesc2 = sourceForPlayer.getDSD();
                }
                onDrmConfigHelper.onDrmConfig(MediaPlayer2Impl.this, dataSourceDesc2);
            }
        });
    }

    public void setDrmEventCallback(Executor executor, MediaPlayer2.DrmEventCallback drmEventCallback) {
        if (drmEventCallback == null) {
            throw new IllegalArgumentException("Illegal null EventCallback");
        } else if (executor != null) {
            synchronized (this.mLock) {
                this.mDrmEventCallbackRecord = new Pair<>(executor, drmEventCallback);
            }
        } else {
            throw new IllegalArgumentException("Illegal null Executor for the EventCallback");
        }
    }

    public void clearDrmEventCallback() {
        synchronized (this.mLock) {
            this.mDrmEventCallbackRecord = null;
        }
    }

    public MediaPlayer2.DrmInfo getDrmInfo() {
        MediaPlayer.DrmInfo drmInfo = this.mPlayer.getDrmInfo();
        if (drmInfo == null) {
            return null;
        }
        return new DrmInfoImpl(drmInfo.getPssh(), drmInfo.getSupportedSchemes());
    }

    public void prepareDrm(final UUID uuid) {
        addTask(new Task(1001, false) {
            /* access modifiers changed from: package-private */
            public void process() {
                final int i;
                try {
                    MediaPlayer2Impl.this.mPlayer.prepareDrm(uuid);
                    i = 0;
                } catch (ResourceBusyException unused) {
                    i = 5;
                } catch (MediaPlayer.ProvisioningServerErrorException unused2) {
                    i = 2;
                } catch (MediaPlayer.ProvisioningNetworkErrorException unused3) {
                    i = 1;
                } catch (UnsupportedSchemeException unused4) {
                    i = 4;
                } catch (Exception unused5) {
                    i = 3;
                }
                MediaPlayer2Impl.this.notifyDrmEvent(new DrmEventNotifier() {
                    public void notify(MediaPlayer2.DrmEventCallback drmEventCallback) {
                        drmEventCallback.onDrmPrepared(MediaPlayer2Impl.this, C370623.this.mDSD, i);
                    }
                });
            }
        });
    }

    public void releaseDrm() throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.releaseDrm();
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    public MediaDrm.KeyRequest getDrmKeyRequest(byte[] bArr, byte[] bArr2, String str, int i, Map<String, String> map) throws MediaPlayer2.NoDrmSchemeException {
        try {
            return this.mPlayer.getKeyRequest(bArr, bArr2, str, i, map);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    public byte[] provideDrmKeyResponse(byte[] bArr, byte[] bArr2) throws MediaPlayer2.NoDrmSchemeException, DeniedByServerException {
        try {
            return this.mPlayer.provideKeyResponse(bArr, bArr2);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    public void restoreDrmKeys(byte[] bArr) throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.restoreKeys(bArr);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    public String getDrmPropertyString(String str) throws MediaPlayer2.NoDrmSchemeException {
        try {
            return this.mPlayer.getDrmPropertyString(str);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    public void setDrmPropertyString(String str, String str2) throws MediaPlayer2.NoDrmSchemeException {
        try {
            this.mPlayer.setDrmPropertyString(str, str2);
        } catch (MediaPlayer.NoDrmSchemeException e) {
            throw new MediaPlayer2.NoDrmSchemeException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void setPlaybackParamsInternal(final PlaybackParams playbackParams) {
        PlaybackParams playbackParams2;
        try {
            playbackParams2 = this.mPlayer.getPlaybackParams();
        } catch (IllegalStateException unused) {
            playbackParams2 = null;
        }
        this.mPlayer.setPlaybackParams(playbackParams);
        if (playbackParams2 != null && playbackParams2.getSpeed() != playbackParams.getSpeed()) {
            notifyPlayerEvent(new PlayerEventNotifier() {
                public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                    playerEventCallback.onPlaybackSpeedChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, playbackParams.getSpeed());
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyMediaPlayer2Event(final Mp2EventNotifier mp2EventNotifier) {
        final Pair<Executor, MediaPlayer2.EventCallback> pair;
        synchronized (this.mLock) {
            pair = this.mMp2EventCallbackRecord;
        }
        if (pair != null) {
            ((Executor) pair.first).execute(new Runnable() {
                public void run() {
                    mp2EventNotifier.notify((MediaPlayer2.EventCallback) pair.second);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyPlayerEvent(final PlayerEventNotifier playerEventNotifier) {
        ArrayMap arrayMap;
        synchronized (this.mLock) {
            arrayMap = new ArrayMap((SimpleArrayMap) this.mPlayerEventCallbackMap);
        }
        int size = arrayMap.size();
        for (int i = 0; i < size; i++) {
            final MediaPlayerConnector.PlayerEventCallback playerEventCallback = (MediaPlayerConnector.PlayerEventCallback) arrayMap.keyAt(i);
            ((Executor) arrayMap.valueAt(i)).execute(new Runnable() {
                public void run() {
                    playerEventNotifier.notify(playerEventCallback);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyDrmEvent(final DrmEventNotifier drmEventNotifier) {
        final Pair<Executor, MediaPlayer2.DrmEventCallback> pair;
        synchronized (this.mLock) {
            pair = this.mDrmEventCallbackRecord;
        }
        if (pair != null) {
            ((Executor) pair.first).execute(new Runnable() {
                public void run() {
                    drmEventNotifier.notify((MediaPlayer2.DrmEventCallback) pair.second);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void setEndPositionTimerIfNeeded(final MediaPlayer.OnCompletionListener onCompletionListener, final MediaPlayerSource mediaPlayerSource, MediaTimestamp mediaTimestamp) {
        if (mediaPlayerSource == this.mPlayer.getFirst()) {
            this.mEndPositionHandler.removeCallbacksAndMessages((Object) null);
            DataSourceDesc2 dsd = mediaPlayerSource.getDSD();
            if (dsd.getEndPosition() != 576460752303423487L && mediaTimestamp.getMediaClockRate() > 0.0f) {
                long endPosition = (long) (((float) (dsd.getEndPosition() - ((mediaTimestamp.getAnchorMediaTimeUs() + ((System.nanoTime() - mediaTimestamp.getAnchorSytemNanoTime()) / 1000)) / 1000))) / mediaTimestamp.getMediaClockRate());
                Handler handler = this.mEndPositionHandler;
                C371228 r2 = new Runnable() {
                    public void run() {
                        if (MediaPlayer2Impl.this.mPlayer.getFirst() == mediaPlayerSource) {
                            MediaPlayer2Impl.this.mPlayer.pause();
                            onCompletionListener.onCompletion(mediaPlayerSource.getPlayer());
                        }
                    }
                };
                if (endPosition < 0) {
                    endPosition = 0;
                }
                handler.postDelayed(r2, endPosition);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setUpListeners(final MediaPlayerSource mediaPlayerSource) {
        MediaPlayer player = mediaPlayerSource.getPlayer();
        final C371329 r1 = new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.onPrepared(mediaPlayer));
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), 100, 0);
                    }
                });
                MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                    public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                        playerEventCallback.onMediaPrepared(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, mediaPlayerSource.getDSD());
                    }
                });
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mMediaCallType == 6 && MediaPlayer2Impl.this.mCurrentTask.mDSD == mediaPlayerSource.getDSD() && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                        MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(0);
                        MediaPlayer2Impl.this.mCurrentTask = null;
                        MediaPlayer2Impl.this.processPendingTask_l();
                    }
                }
            }
        };
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                if (mediaPlayerSource.getDSD().getStartPosition() != 0) {
                    mediaPlayerSource.getPlayer().seekTo((long) ((int) mediaPlayerSource.getDSD().getStartPosition()), 3);
                } else {
                    r1.onPrepared(mediaPlayer);
                }
            }
        });
        player.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, final int i, final int i2) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onVideoSizeChanged(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), i, i2);
                    }
                });
            }
        });
        player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                if (i == 3) {
                    MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                        public void notify(MediaPlayer2.EventCallback eventCallback) {
                            eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), 3, 0);
                        }
                    });
                    return false;
                } else if (i == 701) {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mediaPlayer, 2);
                    return false;
                } else if (i != 702) {
                    return false;
                } else {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mediaPlayer, 1);
                    return false;
                }
            }
        });
        final C372233 r2 = new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
                mediaPlayer2Impl.handleDataSourceError(mediaPlayer2Impl.mPlayer.onCompletion(mediaPlayer));
            }
        };
        player.setOnCompletionListener(r2);
        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
                MediaPlayer2Impl.this.mPlayer.onError(mediaPlayer);
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                        MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(Integer.MIN_VALUE);
                        MediaPlayer2Impl.this.mCurrentTask = null;
                        MediaPlayer2Impl.this.processPendingTask_l();
                    }
                }
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onError(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), ((Integer) MediaPlayer2Impl.sErrorEventMap.getOrDefault(Integer.valueOf(i), 1)).intValue(), i2);
                    }
                });
                return true;
            }
        });
        player.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (mediaPlayerSource.mMp2State != 1001 || mediaPlayerSource.getDSD().getStartPosition() == 0) {
                    synchronized (MediaPlayer2Impl.this.mTaskLock) {
                        if (MediaPlayer2Impl.this.mCurrentTask != null && MediaPlayer2Impl.this.mCurrentTask.mMediaCallType == 14 && MediaPlayer2Impl.this.mCurrentTask.mNeedToWaitForEventToComplete) {
                            MediaPlayer2Impl.this.mCurrentTask.sendCompleteNotification(0);
                            MediaPlayer2Impl.this.mCurrentTask = null;
                            MediaPlayer2Impl.this.processPendingTask_l();
                        }
                    }
                    final long currentPosition = MediaPlayer2Impl.this.getCurrentPosition();
                    MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                        public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                            playerEventCallback.onSeekCompleted(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, currentPosition);
                        }
                    });
                    return;
                }
                r1.onPrepared(mediaPlayer);
            }
        });
        player.setOnTimedMetaDataAvailableListener(new MediaPlayer.OnTimedMetaDataAvailableListener() {
            public void onTimedMetaDataAvailable(MediaPlayer mediaPlayer, final TimedMetaData timedMetaData) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onTimedMetaDataAvailable(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), new TimedMetaData2(timedMetaData));
                    }
                });
            }
        });
        player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            public boolean onInfo(final MediaPlayer mediaPlayer, final int i, final int i2) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        if (i == 2) {
                            MediaPlayer2Impl.this.mPlayer.onStartedAsNext(mediaPlayer);
                            return;
                        }
                        eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), ((Integer) MediaPlayer2Impl.sInfoEventMap.getOrDefault(Integer.valueOf(i), 1)).intValue(), i2);
                    }
                });
                return true;
            }
        });
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mediaPlayer, final int i) {
                if (i >= 100) {
                    MediaPlayer2Impl.this.mPlayer.setBufferingState(mediaPlayer, 3);
                }
                mediaPlayerSource.mBufferedPercentage.set(i);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), 704, i);
                    }
                });
            }
        });
        player.setOnMediaTimeDiscontinuityListener(new MediaPlayer.OnMediaTimeDiscontinuityListener() {
            public void onMediaTimeDiscontinuity(MediaPlayer mediaPlayer, final MediaTimestamp mediaTimestamp) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onMediaTimeDiscontinuity(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), new MediaTimestamp2(mediaTimestamp));
                    }
                });
                MediaPlayer2Impl.this.setEndPositionTimerIfNeeded(r2, mediaPlayerSource, mediaTimestamp);
            }
        });
        player.setOnSubtitleDataListener(new MediaPlayer.OnSubtitleDataListener() {
            public void onSubtitleData(MediaPlayer mediaPlayer, final SubtitleData subtitleData) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onSubtitleData(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), new SubtitleData2(subtitleData));
                    }
                });
            }
        });
        player.setOnDrmInfoListener(new MediaPlayer.OnDrmInfoListener() {
            public void onDrmInfo(MediaPlayer mediaPlayer, final MediaPlayer.DrmInfo drmInfo) {
                MediaPlayer2Impl.this.notifyDrmEvent(new DrmEventNotifier() {
                    public void notify(MediaPlayer2.DrmEventCallback drmEventCallback) {
                        drmEventCallback.onDrmInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), new DrmInfoImpl(drmInfo.getPssh(), drmInfo.getSupportedSchemes()));
                    }
                });
            }
        });
    }

    public static final class DrmInfoImpl extends MediaPlayer2.DrmInfo {
        private Map<UUID, byte[]> mMapPssh;
        private UUID[] mSupportedSchemes;

        public Map<UUID, byte[]> getPssh() {
            return this.mMapPssh;
        }

        public List<UUID> getSupportedSchemes() {
            return Arrays.asList(this.mSupportedSchemes);
        }

        DrmInfoImpl(Map<UUID, byte[]> map, UUID[] uuidArr) {
            this.mMapPssh = map;
            this.mSupportedSchemes = uuidArr;
        }

        private DrmInfoImpl(Parcel parcel) {
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl(" + parcel + ") size " + parcel.dataSize());
            int readInt = parcel.readInt();
            byte[] bArr = new byte[readInt];
            parcel.readByteArray(bArr);
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() PSSH: " + arrToHex(bArr));
            this.mMapPssh = parsePSSH(bArr, readInt);
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() PSSH: " + this.mMapPssh);
            int readInt2 = parcel.readInt();
            this.mSupportedSchemes = new UUID[readInt2];
            for (int i = 0; i < readInt2; i++) {
                byte[] bArr2 = new byte[16];
                parcel.readByteArray(bArr2);
                this.mSupportedSchemes[i] = bytesToUUID(bArr2);
                Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() supportedScheme[" + i + "]: " + this.mSupportedSchemes[i]);
            }
            Log.v(MediaPlayer2Impl.TAG, "DrmInfoImpl() Parcel psshsize: " + readInt + " supportedDRMsCount: " + readInt2);
        }

        private DrmInfoImpl makeCopy() {
            return new DrmInfoImpl(this.mMapPssh, this.mSupportedSchemes);
        }

        private String arrToHex(byte[] bArr) {
            String str = "0x";
            for (int i = 0; i < bArr.length; i++) {
                str = str + String.format("%02x", new Object[]{Byte.valueOf(bArr[i])});
            }
            return str;
        }

        private UUID bytesToUUID(byte[] bArr) {
            long j = 0;
            long j2 = 0;
            for (int i = 0; i < 8; i++) {
                int i2 = (7 - i) * 8;
                j |= (((long) bArr[i]) & 255) << i2;
                j2 |= (((long) bArr[i + 8]) & 255) << i2;
            }
            return new UUID(j, j2);
        }

        private Map<UUID, byte[]> parsePSSH(byte[] bArr, int i) {
            byte b;
            byte b2;
            byte[] bArr2 = bArr;
            HashMap hashMap = new HashMap();
            int i2 = i;
            int i3 = 0;
            int i4 = 0;
            while (i2 > 0) {
                if (i2 < 16) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse UUID: (%d < 16) pssh: %d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i)}));
                    return null;
                }
                int i5 = i3 + 16;
                UUID bytesToUUID = bytesToUUID(Arrays.copyOfRange(bArr2, i3, i5));
                int i6 = i2 - 16;
                if (i6 < 4) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse datalen: (%d < 4) pssh: %d", new Object[]{Integer.valueOf(i6), Integer.valueOf(i)}));
                    return null;
                }
                int i7 = i5 + 4;
                byte[] copyOfRange = Arrays.copyOfRange(bArr2, i5, i7);
                if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                    b2 = ((copyOfRange[2] & 255) << 16) | ((copyOfRange[3] & 255) << 24) | ((copyOfRange[1] & 255) << 8);
                    b = copyOfRange[0];
                } else {
                    b2 = ((copyOfRange[1] & 255) << 16) | ((copyOfRange[0] & 255) << 24) | ((copyOfRange[2] & 255) << 8);
                    b = copyOfRange[3];
                }
                byte b3 = b2 | (b & 255);
                int i8 = i6 - 4;
                if (i8 < b3) {
                    Log.w(MediaPlayer2Impl.TAG, String.format("parsePSSH: len is too short to parse data: (%d < %d) pssh: %d", new Object[]{Integer.valueOf(i8), Integer.valueOf(b3), Integer.valueOf(i)}));
                    return null;
                }
                int i9 = i7 + b3;
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr2, i7, i9);
                i2 = i8 - b3;
                Log.v(MediaPlayer2Impl.TAG, String.format("parsePSSH[%d]: <%s, %s> pssh: %d", new Object[]{Integer.valueOf(i4), bytesToUUID, arrToHex(copyOfRange2), Integer.valueOf(i)}));
                i4++;
                hashMap.put(bytesToUUID, copyOfRange2);
                i3 = i9;
            }
            return hashMap;
        }
    }

    private abstract class Task implements Runnable {
        DataSourceDesc2 mDSD;
        final int mMediaCallType;
        final boolean mNeedToWaitForEventToComplete;
        boolean mSkip;

        /* access modifiers changed from: package-private */
        public abstract void process() throws IOException, MediaPlayer2.NoDrmSchemeException;

        Task(int i, boolean z) {
            this.mMediaCallType = i;
            this.mNeedToWaitForEventToComplete = z;
        }

        public void run() {
            boolean z;
            synchronized (MediaPlayer2Impl.this.mTaskLock) {
                z = this.mSkip;
            }
            int i = 1;
            if (!z) {
                try {
                    if (this.mMediaCallType == 1000 || MediaPlayer2Impl.this.getState() != 1005) {
                        process();
                        i = 0;
                    }
                } catch (IllegalStateException unused) {
                } catch (IllegalArgumentException unused2) {
                    i = 2;
                } catch (SecurityException unused3) {
                    i = 3;
                } catch (IOException unused4) {
                    i = 4;
                } catch (Exception unused5) {
                    i = Integer.MIN_VALUE;
                }
            } else {
                i = 5;
            }
            this.mDSD = MediaPlayer2Impl.this.getCurrentDataSource();
            if (!this.mNeedToWaitForEventToComplete || i != 0 || z) {
                sendCompleteNotification(i);
                synchronized (MediaPlayer2Impl.this.mTaskLock) {
                    MediaPlayer2Impl.this.mCurrentTask = null;
                    MediaPlayer2Impl.this.processPendingTask_l();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void sendCompleteNotification(final int i) {
            if (this.mMediaCallType < 1000) {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onCallCompleted(MediaPlayer2Impl.this, Task.this.mDSD, Task.this.mMediaCallType, i);
                    }
                });
            }
        }
    }

    private static class DataSourceError {
        final DataSourceDesc2 mDSD;
        final int mExtra;
        final int mWhat;

        DataSourceError(DataSourceDesc2 dataSourceDesc2, int i, int i2) {
            this.mDSD = dataSourceDesc2;
            this.mWhat = i;
            this.mExtra = i2;
        }
    }

    private class MediaPlayerSource {
        final AtomicInteger mBufferedPercentage = new AtomicInteger(0);
        int mBufferingState = 0;
        volatile DataSourceDesc2 mDSD;
        int mMp2State = 1001;
        boolean mPlayPending;
        MediaPlayer mPlayer;
        int mPlayerState = 0;
        boolean mSetAsNextPlayer;
        int mSourceState = 0;

        MediaPlayerSource(DataSourceDesc2 dataSourceDesc2) {
            this.mDSD = dataSourceDesc2;
            MediaPlayer2Impl.this.setUpListeners(this);
        }

        /* access modifiers changed from: package-private */
        public DataSourceDesc2 getDSD() {
            return this.mDSD;
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer getPlayer() {
            if (this.mPlayer == null) {
                this.mPlayer = new MediaPlayer();
            }
            return this.mPlayer;
        }
    }

    private class MediaPlayerSourceQueue {
        AudioAttributesCompat mAudioAttributes;
        Integer mAudioSessionId;
        Integer mAuxEffect;
        Float mAuxEffectSendLevel;
        boolean mLooping;
        PlaybackParams mPlaybackParams;
        PlaybackParams mPlaybackParamsToSetAfterSetDataSource;
        List<MediaPlayerSource> mQueue = new ArrayList();
        Surface mSurface;
        SyncParams mSyncParams;
        Float mVolume = Float.valueOf(1.0f);

        MediaPlayerSourceQueue() {
            this.mQueue.add(new MediaPlayerSource((DataSourceDesc2) null));
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer getCurrentPlayer() {
            return this.mQueue.get(0).getPlayer();
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayerSource getFirst() {
            return this.mQueue.get(0);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setFirst(DataSourceDesc2 dataSourceDesc2) throws IOException {
            if (this.mQueue.isEmpty()) {
                this.mQueue.add(0, new MediaPlayerSource(dataSourceDesc2));
            } else {
                this.mQueue.get(0).mDSD = dataSourceDesc2;
                MediaPlayer2Impl.this.setUpListeners(this.mQueue.get(0));
            }
            MediaPlayer2Impl.handleDataSource(this.mQueue.get(0));
            if (this.mPlaybackParamsToSetAfterSetDataSource != null) {
                getCurrentPlayer().setPlaybackParams(this.mPlaybackParamsToSetAfterSetDataSource);
                this.mPlaybackParams = this.mPlaybackParamsToSetAfterSetDataSource;
                this.mPlaybackParamsToSetAfterSetDataSource = null;
            }
            MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                    playerEventCallback.onCurrentDataSourceChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, MediaPlayerSourceQueue.this.mQueue.get(0).mDSD);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError setNext(DataSourceDesc2 dataSourceDesc2) {
            if (this.mQueue.isEmpty() || getFirst().getDSD() == null) {
                throw new IllegalStateException();
            }
            while (this.mQueue.size() >= 2) {
                this.mQueue.remove(1).mPlayer.release();
            }
            this.mQueue.add(1, new MediaPlayerSource(dataSourceDesc2));
            return prepareAt(1);
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError setNextMultiple(List<DataSourceDesc2> list) {
            if (this.mQueue.isEmpty() || getFirst().getDSD() == null) {
                throw new IllegalStateException();
            }
            while (this.mQueue.size() >= 2) {
                this.mQueue.remove(1).mPlayer.release();
            }
            ArrayList arrayList = new ArrayList();
            for (DataSourceDesc2 mediaPlayerSource : list) {
                arrayList.add(new MediaPlayerSource(mediaPlayerSource));
            }
            this.mQueue.addAll(1, arrayList);
            return prepareAt(1);
        }

        /* access modifiers changed from: package-private */
        public synchronized void play() {
            final MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
            if (mediaPlayerSource.mSourceState == 2) {
                mediaPlayerSource.getPlayer().start();
                setMp2State(mediaPlayerSource.getPlayer(), 1004);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), 2, 0);
                    }
                });
            } else {
                throw new IllegalStateException();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepare() {
            getCurrentPlayer().prepareAsync();
        }

        /* access modifiers changed from: package-private */
        public synchronized void release() {
            getCurrentPlayer().release();
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepareAsync() {
            MediaPlayer currentPlayer = getCurrentPlayer();
            currentPlayer.prepareAsync();
            setBufferingState(currentPlayer, 2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void pause() {
            MediaPlayerSource first = getFirst();
            if (first.mMp2State == 1002) {
                first.mPlayer.start();
            }
            first.mPlayer.pause();
            setMp2State(first.mPlayer, 1003);
        }

        /* access modifiers changed from: package-private */
        public synchronized long getCurrentPosition() {
            if (getFirst().mMp2State != 1001) {
            } else {
                throw new IllegalStateException();
            }
            return (long) getCurrentPlayer().getCurrentPosition();
        }

        /* access modifiers changed from: package-private */
        public synchronized long getDuration() {
            if (getFirst().mMp2State != 1001) {
            } else {
                throw new IllegalStateException();
            }
            return (long) getCurrentPlayer().getDuration();
        }

        /* access modifiers changed from: package-private */
        public synchronized long getBufferedPosition() {
            MediaPlayerSource mediaPlayerSource;
            if (getFirst().mMp2State != 1001) {
                mediaPlayerSource = this.mQueue.get(0);
            } else {
                throw new IllegalStateException();
            }
            return (((long) mediaPlayerSource.getPlayer().getDuration()) * ((long) mediaPlayerSource.mBufferedPercentage.get())) / 100;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAudioAttributes(AudioAttributesCompat audioAttributesCompat) {
            AudioAttributes audioAttributes;
            this.mAudioAttributes = audioAttributesCompat;
            if (audioAttributesCompat == null) {
                audioAttributes = null;
            } else {
                audioAttributes = (AudioAttributes) audioAttributesCompat.unwrap();
            }
            getCurrentPlayer().setAudioAttributes(audioAttributes);
        }

        /* access modifiers changed from: package-private */
        public synchronized AudioAttributesCompat getAudioAttributes() {
            return this.mAudioAttributes;
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError onPrepared(MediaPlayer mediaPlayer) {
            boolean z = false;
            for (int i = 0; i < this.mQueue.size(); i++) {
                MediaPlayerSource mediaPlayerSource = this.mQueue.get(i);
                if (mediaPlayer == mediaPlayerSource.getPlayer()) {
                    if (i == 0) {
                        if (mediaPlayerSource.mPlayPending) {
                            mediaPlayerSource.mPlayPending = false;
                            mediaPlayerSource.getPlayer().start();
                            setMp2State(mediaPlayerSource.getPlayer(), 1004);
                        } else {
                            setMp2State(mediaPlayerSource.getPlayer(), 1002);
                        }
                    }
                    mediaPlayerSource.mSourceState = 2;
                    setBufferingState(mediaPlayerSource.getPlayer(), 1);
                    if (i == 1) {
                        MediaPlayer.TrackInfo[] trackInfo = mediaPlayer.getTrackInfo();
                        int length = trackInfo.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (trackInfo[i2].getTrackType() == 1) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z) {
                            getCurrentPlayer().setNextMediaPlayer(mediaPlayerSource.mPlayer);
                            mediaPlayerSource.mSetAsNextPlayer = true;
                        }
                    }
                    return prepareAt(i + 1);
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError onCompletion(MediaPlayer mediaPlayer) {
            final MediaPlayerSource first = getFirst();
            if (!this.mLooping || mediaPlayer != first.mPlayer) {
                if (mediaPlayer == first.mPlayer) {
                    MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                        public void notify(MediaPlayer2.EventCallback eventCallback) {
                            eventCallback.onInfo(MediaPlayer2Impl.this, first.getDSD(), 5, 0);
                        }
                    });
                } else {
                    Log.w(MediaPlayer2Impl.TAG, "Playback complete event from next player. Ignoring.");
                }
                if (this.mQueue.isEmpty() || mediaPlayer != first.mPlayer) {
                    Log.w(MediaPlayer2Impl.TAG, "Invalid playback complete callback from " + mediaPlayer.toString());
                    return null;
                } else if (this.mQueue.size() == 1) {
                    setMp2State(mediaPlayer, 1003);
                    final DataSourceDesc2 dsd = this.mQueue.get(0).getDSD();
                    MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                        public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                            playerEventCallback.onCurrentDataSourceChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, (DataSourceDesc2) null);
                        }
                    });
                    MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                        public void notify(MediaPlayer2.EventCallback eventCallback) {
                            eventCallback.onInfo(MediaPlayer2Impl.this, dsd, 6, 0);
                        }
                    });
                    return null;
                } else if (this.mQueue.get(1).mSetAsNextPlayer) {
                    return null;
                } else {
                    moveToNext();
                    return playCurrent();
                }
            } else {
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, first.getDSD(), 7, 0);
                    }
                });
                first.mPlayer.seekTo((int) first.getDSD().getStartPosition());
                first.mPlayer.start();
                setMp2State(mediaPlayer, 1004);
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void onStartedAsNext(MediaPlayer mediaPlayer) {
            if (this.mQueue.size() >= 2 && this.mQueue.get(1).mPlayer == mediaPlayer) {
                moveToNext();
                final MediaPlayerSource first = getFirst();
                setMp2State(first.getPlayer(), 1004);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, first.getDSD(), 2, 0);
                    }
                });
                prepareAt(1);
                applyProperties();
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void moveToNext() {
            MediaPlayerSource remove = this.mQueue.remove(0);
            remove.getPlayer().release();
            if (!this.mQueue.isEmpty()) {
                final MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
                if (remove.mPlayerState != mediaPlayerSource.mPlayerState) {
                    MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                        public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                            playerEventCallback.onPlayerStateChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, mediaPlayerSource.mPlayerState);
                        }
                    });
                }
                MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                    public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                        playerEventCallback.onCurrentDataSourceChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, mediaPlayerSource.mDSD);
                    }
                });
            } else {
                throw new IllegalStateException("player/source queue emptied");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized DataSourceError playCurrent() {
            DataSourceError dataSourceError;
            dataSourceError = null;
            applyProperties();
            final MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
            if (mediaPlayerSource.mSourceState == 2) {
                mediaPlayerSource.getPlayer().start();
                setMp2State(mediaPlayerSource.getPlayer(), 1004);
                MediaPlayer2Impl.this.notifyMediaPlayer2Event(new Mp2EventNotifier() {
                    public void notify(MediaPlayer2.EventCallback eventCallback) {
                        eventCallback.onInfo(MediaPlayer2Impl.this, mediaPlayerSource.getDSD(), 2, 0);
                    }
                });
                prepareAt(1);
            } else {
                if (mediaPlayerSource.mSourceState == 0) {
                    dataSourceError = prepareAt(0);
                }
                mediaPlayerSource.mPlayPending = true;
            }
            return dataSourceError;
        }

        /* access modifiers changed from: package-private */
        public synchronized void applyProperties() {
            MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
            if (this.mSurface != null) {
                mediaPlayerSource.getPlayer().setSurface(this.mSurface);
            }
            if (this.mVolume != null) {
                mediaPlayerSource.getPlayer().setVolume(this.mVolume.floatValue(), this.mVolume.floatValue());
            }
            if (this.mAudioAttributes != null) {
                mediaPlayerSource.getPlayer().setAudioAttributes((AudioAttributes) this.mAudioAttributes.unwrap());
            }
            if (this.mAuxEffect != null) {
                mediaPlayerSource.getPlayer().attachAuxEffect(this.mAuxEffect.intValue());
            }
            if (this.mAuxEffectSendLevel != null) {
                mediaPlayerSource.getPlayer().setAuxEffectSendLevel(this.mAuxEffectSendLevel.floatValue());
            }
            if (this.mSyncParams != null) {
                mediaPlayerSource.getPlayer().setSyncParams(this.mSyncParams);
            }
            if (this.mPlaybackParams != null) {
                mediaPlayerSource.getPlayer().setPlaybackParams(this.mPlaybackParams);
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void onError(MediaPlayer mediaPlayer) {
            setMp2State(mediaPlayer, 1005);
            setBufferingState(mediaPlayer, 0);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(4:20|21|22|23) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r1 = r5.getDSD();
            setMp2State(r5.getPlayer(), 1005);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
            return new androidx.media2.MediaPlayer2Impl.DataSourceError(r1, 1, -1010);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
            return null;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized androidx.media2.MediaPlayer2Impl.DataSourceError prepareAt(int r5) {
            /*
                r4 = this;
                monitor-enter(r4)
                r0 = 2
                java.util.List<androidx.media2.MediaPlayer2Impl$MediaPlayerSource> r1 = r4.mQueue     // Catch:{ all -> 0x0064 }
                int r1 = r1.size()     // Catch:{ all -> 0x0064 }
                int r0 = java.lang.Math.min(r0, r1)     // Catch:{ all -> 0x0064 }
                r1 = 0
                if (r5 >= r0) goto L_0x0062
                java.util.List<androidx.media2.MediaPlayer2Impl$MediaPlayerSource> r0 = r4.mQueue     // Catch:{ all -> 0x0064 }
                java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0064 }
                androidx.media2.MediaPlayer2Impl$MediaPlayerSource r0 = (androidx.media2.MediaPlayer2Impl.MediaPlayerSource) r0     // Catch:{ all -> 0x0064 }
                int r0 = r0.mSourceState     // Catch:{ all -> 0x0064 }
                if (r0 != 0) goto L_0x0062
                if (r5 == 0) goto L_0x0024
                int r0 = r4.getPlayerState()     // Catch:{ all -> 0x0064 }
                if (r0 != 0) goto L_0x0024
                goto L_0x0062
            L_0x0024:
                java.util.List<androidx.media2.MediaPlayer2Impl$MediaPlayerSource> r0 = r4.mQueue     // Catch:{ all -> 0x0064 }
                java.lang.Object r5 = r0.get(r5)     // Catch:{ all -> 0x0064 }
                androidx.media2.MediaPlayer2Impl$MediaPlayerSource r5 = (androidx.media2.MediaPlayer2Impl.MediaPlayerSource) r5     // Catch:{ all -> 0x0064 }
                r0 = 1
                java.lang.Integer r2 = r4.mAudioSessionId     // Catch:{ Exception -> 0x004c }
                if (r2 == 0) goto L_0x003e
                android.media.MediaPlayer r2 = r5.getPlayer()     // Catch:{ Exception -> 0x004c }
                java.lang.Integer r3 = r4.mAudioSessionId     // Catch:{ Exception -> 0x004c }
                int r3 = r3.intValue()     // Catch:{ Exception -> 0x004c }
                r2.setAudioSessionId(r3)     // Catch:{ Exception -> 0x004c }
            L_0x003e:
                r5.mSourceState = r0     // Catch:{ Exception -> 0x004c }
                androidx.media2.MediaPlayer2Impl.handleDataSource(r5)     // Catch:{ Exception -> 0x004c }
                android.media.MediaPlayer r2 = r5.getPlayer()     // Catch:{ Exception -> 0x004c }
                r2.prepareAsync()     // Catch:{ Exception -> 0x004c }
                monitor-exit(r4)
                return r1
            L_0x004c:
                androidx.media2.DataSourceDesc2 r1 = r5.getDSD()     // Catch:{ all -> 0x0064 }
                android.media.MediaPlayer r5 = r5.getPlayer()     // Catch:{ all -> 0x0064 }
                r2 = 1005(0x3ed, float:1.408E-42)
                r4.setMp2State(r5, r2)     // Catch:{ all -> 0x0064 }
                androidx.media2.MediaPlayer2Impl$DataSourceError r5 = new androidx.media2.MediaPlayer2Impl$DataSourceError     // Catch:{ all -> 0x0064 }
                r2 = -1010(0xfffffffffffffc0e, float:NaN)
                r5.<init>(r1, r0, r2)     // Catch:{ all -> 0x0064 }
                monitor-exit(r4)
                return r5
            L_0x0062:
                monitor-exit(r4)
                return r1
            L_0x0064:
                r5 = move-exception
                monitor-exit(r4)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaPlayer2Impl.MediaPlayerSourceQueue.prepareAt(int):androidx.media2.MediaPlayer2Impl$DataSourceError");
        }

        /* access modifiers changed from: package-private */
        public synchronized void skipToNext() {
            if (this.mQueue.size() > 1) {
                MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
                moveToNext();
                if (mediaPlayerSource.mPlayerState == 2 || mediaPlayerSource.mPlayPending) {
                    playCurrent();
                }
            } else {
                throw new IllegalStateException("No next source available");
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void setLooping(boolean z) {
            this.mLooping = z;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(4:8|9|10|11) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            r1.mPlaybackParamsToSetAfterSetDataSource = r2;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void setPlaybackParams(android.media.PlaybackParams r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                android.media.MediaPlayer r0 = r1.getCurrentPlayer()     // Catch:{ IllegalStateException -> 0x000e }
                r0.setPlaybackParams(r2)     // Catch:{ IllegalStateException -> 0x000e }
                r1.mPlaybackParams = r2     // Catch:{ all -> 0x000c }
                monitor-exit(r1)
                return
            L_0x000c:
                r2 = move-exception
                goto L_0x0012
            L_0x000e:
                r1.mPlaybackParamsToSetAfterSetDataSource = r2     // Catch:{ all -> 0x000c }
                monitor-exit(r1)
                return
            L_0x0012:
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaPlayer2Impl.MediaPlayerSourceQueue.setPlaybackParams(android.media.PlaybackParams):void");
        }

        /* access modifiers changed from: package-private */
        public synchronized float getVolume() {
            return this.mVolume.floatValue();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setVolume(float f) {
            this.mVolume = Float.valueOf(f);
            getCurrentPlayer().setVolume(f, f);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setSurface(Surface surface) {
            this.mSurface = surface;
            getCurrentPlayer().setSurface(surface);
        }

        /* access modifiers changed from: package-private */
        public synchronized int getVideoWidth() {
            return getCurrentPlayer().getVideoWidth();
        }

        /* access modifiers changed from: package-private */
        public synchronized int getVideoHeight() {
            return getCurrentPlayer().getVideoHeight();
        }

        /* access modifiers changed from: package-private */
        public synchronized PersistableBundle getMetrics() {
            return getCurrentPlayer().getMetrics();
        }

        /* access modifiers changed from: package-private */
        public synchronized PlaybackParams getPlaybackParams() {
            return getCurrentPlayer().getPlaybackParams();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setSyncParams(SyncParams syncParams) {
            getCurrentPlayer().setSyncParams(syncParams);
            this.mSyncParams = syncParams;
        }

        /* access modifiers changed from: package-private */
        public synchronized SyncParams getSyncParams() {
            return getCurrentPlayer().getSyncParams();
        }

        /* access modifiers changed from: package-private */
        public synchronized void seekTo(long j, int i) {
            getCurrentPlayer().seekTo(j, i);
        }

        /* access modifiers changed from: package-private */
        public synchronized void reset() {
            MediaPlayerSource mediaPlayerSource = this.mQueue.get(0);
            mediaPlayerSource.getPlayer().reset();
            mediaPlayerSource.mBufferedPercentage.set(0);
            this.mVolume = Float.valueOf(1.0f);
            this.mSurface = null;
            this.mAuxEffect = null;
            this.mAuxEffectSendLevel = null;
            this.mAudioAttributes = null;
            this.mAudioSessionId = null;
            this.mSyncParams = null;
            this.mPlaybackParams = null;
            this.mLooping = false;
            setMp2State(mediaPlayerSource.getPlayer(), 1001);
            setBufferingState(mediaPlayerSource.getPlayer(), 0);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaTimestamp2 getTimestamp() {
            MediaTimestamp2 mediaTimestamp2;
            MediaTimestamp timestamp = getCurrentPlayer().getTimestamp();
            if (timestamp == null) {
                mediaTimestamp2 = null;
            } else {
                mediaTimestamp2 = new MediaTimestamp2(timestamp);
            }
            return mediaTimestamp2;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAudioSessionId(int i) {
            getCurrentPlayer().setAudioSessionId(i);
            this.mAudioSessionId = Integer.valueOf(i);
        }

        /* access modifiers changed from: package-private */
        public synchronized int getAudioSessionId() {
            return getCurrentPlayer().getAudioSessionId();
        }

        /* access modifiers changed from: package-private */
        public synchronized void attachAuxEffect(int i) {
            getCurrentPlayer().attachAuxEffect(i);
            this.mAuxEffect = Integer.valueOf(i);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAuxEffectSendLevel(float f) {
            getCurrentPlayer().setAuxEffectSendLevel(f);
            this.mAuxEffectSendLevel = Float.valueOf(f);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer.TrackInfo[] getTrackInfo() {
            return getCurrentPlayer().getTrackInfo();
        }

        /* access modifiers changed from: package-private */
        public synchronized int getSelectedTrack(int i) {
            return getCurrentPlayer().getSelectedTrack(i);
        }

        /* access modifiers changed from: package-private */
        public synchronized void selectTrack(int i) {
            getCurrentPlayer().selectTrack(i);
        }

        /* access modifiers changed from: package-private */
        public synchronized void deselectTrack(int i) {
            getCurrentPlayer().deselectTrack(i);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayer.DrmInfo getDrmInfo() {
            return getCurrentPlayer().getDrmInfo();
        }

        /* access modifiers changed from: package-private */
        public synchronized void prepareDrm(UUID uuid) throws ResourceBusyException, MediaPlayer.ProvisioningServerErrorException, MediaPlayer.ProvisioningNetworkErrorException, UnsupportedSchemeException {
            getCurrentPlayer().prepareDrm(uuid);
        }

        /* access modifiers changed from: package-private */
        public synchronized void releaseDrm() throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().stop();
            getCurrentPlayer().releaseDrm();
        }

        /* access modifiers changed from: package-private */
        public synchronized byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws DeniedByServerException, MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().provideKeyResponse(bArr, bArr2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void restoreKeys(byte[] bArr) throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().restoreKeys(bArr);
        }

        /* access modifiers changed from: package-private */
        public synchronized String getDrmPropertyString(String str) throws MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().getDrmPropertyString(str);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setDrmPropertyString(String str, String str2) throws MediaPlayer.NoDrmSchemeException {
            getCurrentPlayer().setDrmPropertyString(str, str2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setOnDrmConfigHelper(MediaPlayer.OnDrmConfigHelper onDrmConfigHelper) {
            getCurrentPlayer().setOnDrmConfigHelper(onDrmConfigHelper);
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaDrm.KeyRequest getKeyRequest(byte[] bArr, byte[] bArr2, String str, int i, Map<String, String> map) throws MediaPlayer.NoDrmSchemeException {
            return getCurrentPlayer().getKeyRequest(bArr, bArr2, str, i, map);
        }

        /* access modifiers changed from: package-private */
        public synchronized void setMp2State(MediaPlayer mediaPlayer, int i) {
            for (MediaPlayerSource next : this.mQueue) {
                if (next.getPlayer() == mediaPlayer) {
                    if (next.mMp2State != i) {
                        next.mMp2State = i;
                        final int intValue = MediaPlayer2Impl.sStateMap.get(Integer.valueOf(i)).intValue();
                        if (next.mPlayerState != intValue) {
                            next.mPlayerState = intValue;
                            MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                                public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                                    playerEventCallback.onPlayerStateChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, intValue);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void setBufferingState(MediaPlayer mediaPlayer, final int i) {
            for (final MediaPlayerSource next : this.mQueue) {
                if (next.getPlayer() == mediaPlayer) {
                    if (next.mBufferingState != i) {
                        next.mBufferingState = i;
                        MediaPlayer2Impl.this.notifyPlayerEvent(new PlayerEventNotifier() {
                            public void notify(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
                                playerEventCallback.onBufferingStateChanged(MediaPlayer2Impl.this.mMediaPlayerConnectorImpl, next.getDSD(), i);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized int getMediaPlayer2State() {
            return this.mQueue.get(0).mMp2State;
        }

        /* access modifiers changed from: package-private */
        public synchronized int getBufferingState() {
            return this.mQueue.get(0).mBufferingState;
        }

        /* access modifiers changed from: package-private */
        public synchronized int getPlayerState() {
            return this.mQueue.get(0).mPlayerState;
        }

        /* access modifiers changed from: package-private */
        public synchronized MediaPlayerSource getSourceForPlayer(MediaPlayer mediaPlayer) {
            for (MediaPlayerSource next : this.mQueue) {
                if (next.getPlayer() == mediaPlayer) {
                    return next;
                }
            }
            return null;
        }
    }

    private class MediaPlayerConnectorImpl extends MediaPlayerConnector {
        MediaPlayerConnectorImpl() {
        }

        public void play() {
            MediaPlayer2Impl.this.play();
        }

        public void prepare() {
            MediaPlayer2Impl.this.prepare();
        }

        public void pause() {
            MediaPlayer2Impl.this.pause();
        }

        public void reset() {
            MediaPlayer2Impl.this.reset();
        }

        public void skipToNext() {
            MediaPlayer2Impl.this.skipToNext();
        }

        public void seekTo(long j) {
            MediaPlayer2Impl.this.seekTo(j);
        }

        public long getCurrentPosition() {
            try {
                return MediaPlayer2Impl.this.getCurrentPosition();
            } catch (IllegalStateException unused) {
                return -1;
            }
        }

        public long getDuration() {
            try {
                return MediaPlayer2Impl.this.getDuration();
            } catch (IllegalStateException unused) {
                return -1;
            }
        }

        public long getBufferedPosition() {
            try {
                return MediaPlayer2Impl.this.getBufferedPosition();
            } catch (IllegalStateException unused) {
                return -1;
            }
        }

        public int getPlayerState() {
            return MediaPlayer2Impl.this.getPlayerState();
        }

        public int getBufferingState() {
            return MediaPlayer2Impl.this.getBufferingState();
        }

        public void setAudioAttributes(AudioAttributesCompat audioAttributesCompat) {
            MediaPlayer2Impl.this.setAudioAttributes(audioAttributesCompat);
        }

        public AudioAttributesCompat getAudioAttributes() {
            return MediaPlayer2Impl.this.getAudioAttributes();
        }

        public void setDataSource(DataSourceDesc2 dataSourceDesc2) {
            MediaPlayer2Impl.this.setDataSource(dataSourceDesc2);
        }

        public void setNextDataSource(DataSourceDesc2 dataSourceDesc2) {
            MediaPlayer2Impl.this.setNextDataSource(dataSourceDesc2);
        }

        public void setNextDataSources(List<DataSourceDesc2> list) {
            MediaPlayer2Impl.this.setNextDataSources(list);
        }

        public DataSourceDesc2 getCurrentDataSource() {
            return MediaPlayer2Impl.this.getCurrentDataSource();
        }

        public void loopCurrent(boolean z) {
            MediaPlayer2Impl.this.loopCurrent(z);
        }

        public void setPlaybackSpeed(float f) {
            MediaPlayer2Impl mediaPlayer2Impl = MediaPlayer2Impl.this;
            mediaPlayer2Impl.setPlaybackParams(new PlaybackParams2.Builder(mediaPlayer2Impl.getPlaybackParams().getPlaybackParams()).setSpeed(f).build());
        }

        public float getPlaybackSpeed() {
            try {
                return MediaPlayer2Impl.this.getPlaybackParams().getSpeed().floatValue();
            } catch (IllegalStateException unused) {
                return super.getPlaybackSpeed();
            }
        }

        public void setPlayerVolume(float f) {
            MediaPlayer2Impl.this.setPlayerVolume(f);
        }

        public float getPlayerVolume() {
            return MediaPlayer2Impl.this.getPlayerVolume();
        }

        public void registerPlayerEventCallback(Executor executor, MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
            MediaPlayer2Impl.this.registerPlayerEventCallback(executor, playerEventCallback);
        }

        public void unregisterPlayerEventCallback(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
            MediaPlayer2Impl.this.unregisterPlayerEventCallback(playerEventCallback);
        }

        public void close() throws Exception {
            MediaPlayer2Impl.this.close();
        }
    }
}
