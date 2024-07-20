package androidx.media2.session;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaSession;
import androidx.media2.session.SessionToken;
import androidx.versionedparcelable.VersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaController implements AutoCloseable {
    private static final String TAG = "MediaController";
    final ControllerCallback mCallback;
    final Executor mCallbackExecutor;
    boolean mClosed;
    private final List<Pair<ControllerCallback, Executor>> mExtraCallbacks = new ArrayList();
    MediaControllerImpl mImpl;
    final Object mLock = new Object();
    Long mTimeDiff;

    public interface ControllerCallbackRunnable {
        void run(ControllerCallback controllerCallback);
    }

    interface MediaControllerImpl extends AutoCloseable {
        ListenableFuture<SessionResult> addPlaylistItem(int i, String str);

        ListenableFuture<SessionResult> adjustVolume(int i, int i2);

        ListenableFuture<SessionResult> deselectTrack(SessionPlayer.TrackInfo trackInfo);

        ListenableFuture<SessionResult> fastForward();

        SessionCommandGroup getAllowedCommands();

        MediaBrowserCompat getBrowserCompat();

        long getBufferedPosition();

        int getBufferingState();

        SessionToken getConnectedToken();

        Context getContext();

        MediaItem getCurrentMediaItem();

        int getCurrentMediaItemIndex();

        long getCurrentPosition();

        long getDuration();

        int getNextMediaItemIndex();

        PlaybackInfo getPlaybackInfo();

        float getPlaybackSpeed();

        int getPlayerState();

        List<MediaItem> getPlaylist();

        MediaMetadata getPlaylistMetadata();

        int getPreviousMediaItemIndex();

        int getRepeatMode();

        SessionPlayer.TrackInfo getSelectedTrack(int i);

        PendingIntent getSessionActivity();

        int getShuffleMode();

        List<SessionPlayer.TrackInfo> getTrackInfo();

        VideoSize getVideoSize();

        boolean isConnected();

        ListenableFuture<SessionResult> pause();

        ListenableFuture<SessionResult> play();

        ListenableFuture<SessionResult> playFromMediaId(String str, Bundle bundle);

        ListenableFuture<SessionResult> playFromSearch(String str, Bundle bundle);

        ListenableFuture<SessionResult> playFromUri(Uri uri, Bundle bundle);

        ListenableFuture<SessionResult> prepare();

        ListenableFuture<SessionResult> prepareFromMediaId(String str, Bundle bundle);

        ListenableFuture<SessionResult> prepareFromSearch(String str, Bundle bundle);

        ListenableFuture<SessionResult> prepareFromUri(Uri uri, Bundle bundle);

        ListenableFuture<SessionResult> removePlaylistItem(int i);

        ListenableFuture<SessionResult> replacePlaylistItem(int i, String str);

        ListenableFuture<SessionResult> rewind();

        ListenableFuture<SessionResult> seekTo(long j);

        ListenableFuture<SessionResult> selectTrack(SessionPlayer.TrackInfo trackInfo);

        ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle);

        ListenableFuture<SessionResult> setMediaItem(String str);

        ListenableFuture<SessionResult> setPlaybackSpeed(float f);

        ListenableFuture<SessionResult> setPlaylist(List<String> list, MediaMetadata mediaMetadata);

        ListenableFuture<SessionResult> setRating(String str, Rating rating);

        ListenableFuture<SessionResult> setRepeatMode(int i);

        ListenableFuture<SessionResult> setShuffleMode(int i);

        ListenableFuture<SessionResult> setSurface(Surface surface);

        ListenableFuture<SessionResult> setVolumeTo(int i, int i2);

        ListenableFuture<SessionResult> skipBackward();

        ListenableFuture<SessionResult> skipForward();

        ListenableFuture<SessionResult> skipToNextItem();

        ListenableFuture<SessionResult> skipToPlaylistItem(int i);

        ListenableFuture<SessionResult> skipToPreviousItem();

        ListenableFuture<SessionResult> updatePlaylistMetadata(MediaMetadata mediaMetadata);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeFlags {
    }

    MediaController(Context context, SessionToken sessionToken, Bundle bundle, Executor executor, ControllerCallback controllerCallback) {
        if (context == null) {
            throw new NullPointerException("context shouldn't be null");
        } else if (sessionToken != null) {
            this.mCallback = controllerCallback;
            this.mCallbackExecutor = executor;
            synchronized (this.mLock) {
                this.mImpl = createImpl(context, sessionToken, bundle);
            }
        } else {
            throw new NullPointerException("token shouldn't be null");
        }
    }

    MediaController(final Context context, MediaSessionCompat.Token token, final Bundle bundle, Executor executor, ControllerCallback controllerCallback) {
        if (context == null) {
            throw new NullPointerException("context shouldn't be null");
        } else if (token != null) {
            this.mCallback = controllerCallback;
            this.mCallbackExecutor = executor;
            SessionToken.createSessionToken(context, token, executor, new SessionToken.OnSessionTokenCreatedListener() {
                public void onSessionTokenCreated(MediaSessionCompat.Token token, SessionToken sessionToken) {
                    synchronized (MediaController.this.mLock) {
                        if (!MediaController.this.mClosed) {
                            MediaController.this.mImpl = MediaController.this.createImpl(context, sessionToken, bundle);
                        } else {
                            MediaController.this.notifyControllerCallback(new ControllerCallbackRunnable() {
                                public void run(ControllerCallback controllerCallback) {
                                    controllerCallback.onDisconnected(MediaController.this);
                                }
                            });
                        }
                    }
                }
            });
        } else {
            throw new NullPointerException("token shouldn't be null");
        }
    }

    /* access modifiers changed from: package-private */
    public MediaControllerImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle) {
        if (sessionToken.isLegacySession()) {
            return new MediaControllerImplLegacy(context, this, sessionToken);
        }
        return new MediaControllerImplBase(context, this, sessionToken, bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaControllerImpl getImpl() {
        MediaControllerImpl mediaControllerImpl;
        synchronized (this.mLock) {
            mediaControllerImpl = this.mImpl;
        }
        return mediaControllerImpl;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000f, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock     // Catch:{ Exception -> 0x0018 }
            monitor-enter(r0)     // Catch:{ Exception -> 0x0018 }
            boolean r1 = r2.mClosed     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return
        L_0x0009:
            r1 = 1
            r2.mClosed = r1     // Catch:{ all -> 0x0015 }
            androidx.media2.session.MediaController$MediaControllerImpl r1 = r2.mImpl     // Catch:{ all -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ Exception -> 0x0018 }
            goto L_0x0018
        L_0x0015:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r1     // Catch:{ Exception -> 0x0018 }
        L_0x0018:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaController.close():void");
    }

    public SessionToken getConnectedToken() {
        if (isConnected()) {
            return getImpl().getConnectedToken();
        }
        return null;
    }

    public boolean isConnected() {
        MediaControllerImpl impl = getImpl();
        return impl != null && impl.isConnected();
    }

    public ListenableFuture<SessionResult> play() {
        if (isConnected()) {
            return getImpl().play();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> pause() {
        if (isConnected()) {
            return getImpl().pause();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> prepare() {
        if (isConnected()) {
            return getImpl().prepare();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> fastForward() {
        if (isConnected()) {
            return getImpl().fastForward();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> rewind() {
        if (isConnected()) {
            return getImpl().rewind();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> skipForward() {
        if (isConnected()) {
            return getImpl().skipForward();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> skipBackward() {
        if (isConnected()) {
            return getImpl().skipBackward();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> seekTo(long j) {
        if (isConnected()) {
            return getImpl().seekTo(j);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> playFromMediaId(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().playFromMediaId(str, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> playFromSearch(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().playFromSearch(str, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> playFromUri(Uri uri, Bundle bundle) {
        if (uri == null) {
            throw new NullPointerException("uri shouldn't be null");
        } else if (isConnected()) {
            return getImpl().playFromUri(uri, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> prepareFromMediaId(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().prepareFromMediaId(str, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> prepareFromSearch(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().prepareFromSearch(str, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> prepareFromUri(Uri uri, Bundle bundle) {
        if (uri == null) {
            throw new NullPointerException("uri shouldn't be null");
        } else if (isConnected()) {
            return getImpl().prepareFromUri(uri, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> setVolumeTo(int i, int i2) {
        if (isConnected()) {
            return getImpl().setVolumeTo(i, i2);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> adjustVolume(int i, int i2) {
        if (isConnected()) {
            return getImpl().adjustVolume(i, i2);
        }
        return createDisconnectedFuture();
    }

    public PendingIntent getSessionActivity() {
        if (isConnected()) {
            return getImpl().getSessionActivity();
        }
        return null;
    }

    public int getPlayerState() {
        if (isConnected()) {
            return getImpl().getPlayerState();
        }
        return 0;
    }

    public long getDuration() {
        if (isConnected()) {
            return getImpl().getDuration();
        }
        return Long.MIN_VALUE;
    }

    public long getCurrentPosition() {
        if (isConnected()) {
            return getImpl().getCurrentPosition();
        }
        return Long.MIN_VALUE;
    }

    public float getPlaybackSpeed() {
        if (isConnected()) {
            return getImpl().getPlaybackSpeed();
        }
        return 0.0f;
    }

    public ListenableFuture<SessionResult> setPlaybackSpeed(float f) {
        if (f == 0.0f) {
            throw new IllegalArgumentException("speed must not be zero");
        } else if (isConnected()) {
            return getImpl().setPlaybackSpeed(f);
        } else {
            return createDisconnectedFuture();
        }
    }

    public int getBufferingState() {
        if (isConnected()) {
            return getImpl().getBufferingState();
        }
        return 0;
    }

    public long getBufferedPosition() {
        if (isConnected()) {
            return getImpl().getBufferedPosition();
        }
        return Long.MIN_VALUE;
    }

    public PlaybackInfo getPlaybackInfo() {
        if (isConnected()) {
            return getImpl().getPlaybackInfo();
        }
        return null;
    }

    public ListenableFuture<SessionResult> setRating(String str, Rating rating) {
        if (str == null) {
            throw new NullPointerException("mediaId shouldn't be null");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (rating == null) {
            throw new NullPointerException("rating shouldn't be null");
        } else if (isConnected()) {
            return getImpl().setRating(str, rating);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        if (sessionCommand == null) {
            throw new NullPointerException("command shouldn't be null");
        } else if (sessionCommand.getCommandCode() != 0) {
            throw new IllegalArgumentException("command should be a custom command");
        } else if (isConnected()) {
            return getImpl().sendCustomCommand(sessionCommand, bundle);
        } else {
            return createDisconnectedFuture();
        }
    }

    public List<MediaItem> getPlaylist() {
        if (isConnected()) {
            return getImpl().getPlaylist();
        }
        return null;
    }

    public ListenableFuture<SessionResult> setPlaylist(List<String> list, MediaMetadata mediaMetadata) {
        if (list != null) {
            int i = 0;
            while (i < list.size()) {
                if (!TextUtils.isEmpty(list.get(i))) {
                    i++;
                } else {
                    throw new IllegalArgumentException("list shouldn't contain empty id, index=" + i);
                }
            }
            if (isConnected()) {
                return getImpl().setPlaylist(list, mediaMetadata);
            }
            return createDisconnectedFuture();
        }
        throw new NullPointerException("list shouldn't be null");
    }

    public ListenableFuture<SessionResult> setMediaItem(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().setMediaItem(str);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> updatePlaylistMetadata(MediaMetadata mediaMetadata) {
        if (isConnected()) {
            return getImpl().updatePlaylistMetadata(mediaMetadata);
        }
        return createDisconnectedFuture();
    }

    public MediaMetadata getPlaylistMetadata() {
        if (isConnected()) {
            return getImpl().getPlaylistMetadata();
        }
        return null;
    }

    public ListenableFuture<SessionResult> addPlaylistItem(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().addPlaylistItem(i, str);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> removePlaylistItem(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (isConnected()) {
            return getImpl().removePlaylistItem(i);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<SessionResult> replacePlaylistItem(int i, String str) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().replacePlaylistItem(i, str);
        } else {
            return createDisconnectedFuture();
        }
    }

    public MediaItem getCurrentMediaItem() {
        if (isConnected()) {
            return getImpl().getCurrentMediaItem();
        }
        return null;
    }

    public int getCurrentMediaItemIndex() {
        if (isConnected()) {
            return getImpl().getCurrentMediaItemIndex();
        }
        return -1;
    }

    public int getPreviousMediaItemIndex() {
        if (isConnected()) {
            return getImpl().getPreviousMediaItemIndex();
        }
        return -1;
    }

    public int getNextMediaItemIndex() {
        if (isConnected()) {
            return getImpl().getNextMediaItemIndex();
        }
        return -1;
    }

    public ListenableFuture<SessionResult> skipToPreviousPlaylistItem() {
        if (isConnected()) {
            return getImpl().skipToPreviousItem();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> skipToNextPlaylistItem() {
        if (isConnected()) {
            return getImpl().skipToNextItem();
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<SessionResult> skipToPlaylistItem(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (isConnected()) {
            return getImpl().skipToPlaylistItem(i);
        } else {
            return createDisconnectedFuture();
        }
    }

    public int getRepeatMode() {
        if (isConnected()) {
            return getImpl().getRepeatMode();
        }
        return 0;
    }

    public ListenableFuture<SessionResult> setRepeatMode(int i) {
        if (isConnected()) {
            return getImpl().setRepeatMode(i);
        }
        return createDisconnectedFuture();
    }

    public int getShuffleMode() {
        if (isConnected()) {
            return getImpl().getShuffleMode();
        }
        return 0;
    }

    public ListenableFuture<SessionResult> setShuffleMode(int i) {
        if (isConnected()) {
            return getImpl().setShuffleMode(i);
        }
        return createDisconnectedFuture();
    }

    public VideoSize getVideoSize() {
        return isConnected() ? getImpl().getVideoSize() : new VideoSize(0, 0);
    }

    public ListenableFuture<SessionResult> setSurface(Surface surface) {
        if (isConnected()) {
            return getImpl().setSurface(surface);
        }
        return createDisconnectedFuture();
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        if (isConnected()) {
            return getImpl().getTrackInfo();
        }
        return null;
    }

    public ListenableFuture<SessionResult> selectTrack(SessionPlayer.TrackInfo trackInfo) {
        if (trackInfo != null) {
            return isConnected() ? getImpl().selectTrack(trackInfo) : createDisconnectedFuture();
        }
        throw new NullPointerException("TrackInfo shouldn't be null");
    }

    public ListenableFuture<SessionResult> deselectTrack(SessionPlayer.TrackInfo trackInfo) {
        if (trackInfo != null) {
            return isConnected() ? getImpl().deselectTrack(trackInfo) : createDisconnectedFuture();
        }
        throw new NullPointerException("TrackInfo shouldn't be null");
    }

    public SessionPlayer.TrackInfo getSelectedTrack(int i) {
        if (isConnected()) {
            return getImpl().getSelectedTrack(i);
        }
        return null;
    }

    public void setTimeDiff(Long l) {
        this.mTimeDiff = l;
    }

    public void registerExtraCallback(Executor executor, ControllerCallback controllerCallback) {
        if (executor == null) {
            throw new NullPointerException("executor shouldn't be null");
        } else if (controllerCallback != null) {
            boolean z = false;
            synchronized (this.mLock) {
                Iterator<Pair<ControllerCallback, Executor>> it = this.mExtraCallbacks.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().first == controllerCallback) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!z) {
                    this.mExtraCallbacks.add(new Pair(controllerCallback, executor));
                }
            }
            if (z) {
                Log.w(TAG, "registerExtraCallback: the callback already exists");
            }
        } else {
            throw new NullPointerException("callback shouldn't be null");
        }
    }

    public void unregisterExtraCallback(ControllerCallback controllerCallback) {
        if (controllerCallback != null) {
            boolean z = false;
            synchronized (this.mLock) {
                int size = this.mExtraCallbacks.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    } else if (this.mExtraCallbacks.get(size).first == controllerCallback) {
                        this.mExtraCallbacks.remove(size);
                        z = true;
                        break;
                    } else {
                        size--;
                    }
                }
            }
            if (!z) {
                Log.w(TAG, "unregisterExtraCallback: no such callback found");
                return;
            }
            return;
        }
        throw new NullPointerException("callback shouldn't be null");
    }

    public List<Pair<ControllerCallback, Executor>> getExtraCallbacks() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mExtraCallbacks);
        }
        return arrayList;
    }

    public SessionCommandGroup getAllowedCommands() {
        if (!isConnected()) {
            return null;
        }
        return getImpl().getAllowedCommands();
    }

    private static ListenableFuture<SessionResult> createDisconnectedFuture() {
        return SessionResult.createFutureWithResult(-100);
    }

    public void notifyControllerCallback(final ControllerCallbackRunnable controllerCallbackRunnable) {
        Executor executor;
        if (!(this.mCallback == null || (executor = this.mCallbackExecutor) == null)) {
            executor.execute(new Runnable() {
                public void run() {
                    controllerCallbackRunnable.run(MediaController.this.mCallback);
                }
            });
        }
        for (Pair next : getExtraCallbacks()) {
            final ControllerCallback controllerCallback = (ControllerCallback) next.first;
            Executor executor2 = (Executor) next.second;
            if (controllerCallback == null) {
                Log.e(TAG, "notifyControllerCallback: mExtraCallbacks contains a null ControllerCallback! Ignoring...");
            } else if (executor2 == null) {
                Log.e(TAG, "notifyControllerCallback: mExtraCallbacks contains a null Executor! Ignoring...");
            } else {
                executor2.execute(new Runnable() {
                    public void run() {
                        controllerCallbackRunnable.run(controllerCallback);
                    }
                });
            }
        }
    }

    public static final class Builder extends BuilderBase<MediaController, Builder, ControllerCallback> {
        public Builder(Context context) {
            super(context);
        }

        public Builder setSessionToken(SessionToken sessionToken) {
            return (Builder) super.setSessionToken(sessionToken);
        }

        public Builder setSessionCompatToken(MediaSessionCompat.Token token) {
            return (Builder) super.setSessionCompatToken(token);
        }

        public Builder setControllerCallback(Executor executor, ControllerCallback controllerCallback) {
            return (Builder) super.setControllerCallback(executor, controllerCallback);
        }

        public MediaController build() {
            if (this.mToken == null && this.mCompatToken == null) {
                throw new IllegalArgumentException("token and compat token shouldn't be both null");
            } else if (this.mToken != null) {
                return new MediaController(this.mContext, this.mToken, this.mConnectionHints, this.mCallbackExecutor, this.mCallback);
            } else {
                return new MediaController(this.mContext, this.mCompatToken, this.mConnectionHints, this.mCallbackExecutor, this.mCallback);
            }
        }
    }

    static abstract class BuilderBase<T extends MediaController, U extends BuilderBase<T, U, C>, C extends ControllerCallback> {
        ControllerCallback mCallback;
        Executor mCallbackExecutor;
        MediaSessionCompat.Token mCompatToken;
        Bundle mConnectionHints;
        final Context mContext;
        SessionToken mToken;

        /* access modifiers changed from: package-private */
        public abstract T build();

        BuilderBase(Context context) {
            if (context != null) {
                this.mContext = context;
                return;
            }
            throw new NullPointerException("context shouldn't be null");
        }

        public U setSessionToken(SessionToken sessionToken) {
            if (sessionToken != null) {
                this.mToken = sessionToken;
                this.mCompatToken = null;
                return this;
            }
            throw new NullPointerException("token shouldn't be null");
        }

        public U setSessionCompatToken(MediaSessionCompat.Token token) {
            if (token != null) {
                this.mCompatToken = token;
                this.mToken = null;
                return this;
            }
            throw new NullPointerException("compatToken shouldn't be null");
        }

        public U setConnectionHints(Bundle bundle) {
            if (bundle != null) {
                this.mConnectionHints = new Bundle(bundle);
                return this;
            }
            throw new NullPointerException("connectionHints shouldn't be null");
        }

        public U setControllerCallback(Executor executor, C c) {
            if (executor == null) {
                throw new NullPointerException("executor shouldn't be null");
            } else if (c != null) {
                this.mCallbackExecutor = executor;
                this.mCallback = c;
                return this;
            } else {
                throw new NullPointerException("callback shouldn't be null");
            }
        }
    }

    public static abstract class ControllerCallback {
        public void onAllowedCommandsChanged(MediaController mediaController, SessionCommandGroup sessionCommandGroup) {
        }

        public void onBufferingStateChanged(MediaController mediaController, MediaItem mediaItem, int i) {
        }

        public void onConnected(MediaController mediaController, SessionCommandGroup sessionCommandGroup) {
        }

        public void onCurrentMediaItemChanged(MediaController mediaController, MediaItem mediaItem) {
        }

        public void onDisconnected(MediaController mediaController) {
        }

        public void onPlaybackCompleted(MediaController mediaController) {
        }

        public void onPlaybackInfoChanged(MediaController mediaController, PlaybackInfo playbackInfo) {
        }

        public void onPlaybackSpeedChanged(MediaController mediaController, float f) {
        }

        public void onPlayerStateChanged(MediaController mediaController, int i) {
        }

        public void onPlaylistChanged(MediaController mediaController, List<MediaItem> list, MediaMetadata mediaMetadata) {
        }

        public void onPlaylistMetadataChanged(MediaController mediaController, MediaMetadata mediaMetadata) {
        }

        public void onRepeatModeChanged(MediaController mediaController, int i) {
        }

        public void onSeekCompleted(MediaController mediaController, long j) {
        }

        public int onSetCustomLayout(MediaController mediaController, List<MediaSession.CommandButton> list) {
            return -6;
        }

        public void onShuffleModeChanged(MediaController mediaController, int i) {
        }

        public void onSubtitleData(MediaController mediaController, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        public void onTrackDeselected(MediaController mediaController, SessionPlayer.TrackInfo trackInfo) {
        }

        public void onTrackInfoChanged(MediaController mediaController, List<SessionPlayer.TrackInfo> list) {
        }

        public void onTrackSelected(MediaController mediaController, SessionPlayer.TrackInfo trackInfo) {
        }

        public void onVideoSizeChanged(MediaController mediaController, MediaItem mediaItem, VideoSize videoSize) {
        }

        public SessionResult onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
            return new SessionResult(-6);
        }
    }

    public static final class PlaybackInfo implements VersionedParcelable {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        AudioAttributesCompat mAudioAttrsCompat;
        int mControlType;
        int mCurrentVolume;
        int mMaxVolume;
        int mPlaybackType;

        PlaybackInfo() {
        }

        PlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.mPlaybackType = i;
            this.mAudioAttrsCompat = audioAttributesCompat;
            this.mControlType = i2;
            this.mMaxVolume = i3;
            this.mCurrentVolume = i4;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public AudioAttributesCompat getAudioAttributes() {
            return this.mAudioAttrsCompat;
        }

        public int getControlType() {
            return this.mControlType;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }

        public int hashCode() {
            return ObjectsCompat.hash(Integer.valueOf(this.mPlaybackType), Integer.valueOf(this.mControlType), Integer.valueOf(this.mMaxVolume), Integer.valueOf(this.mCurrentVolume), this.mAudioAttrsCompat);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PlaybackInfo)) {
                return false;
            }
            PlaybackInfo playbackInfo = (PlaybackInfo) obj;
            if (this.mPlaybackType == playbackInfo.mPlaybackType && this.mControlType == playbackInfo.mControlType && this.mMaxVolume == playbackInfo.mMaxVolume && this.mCurrentVolume == playbackInfo.mCurrentVolume && ObjectsCompat.equals(this.mAudioAttrsCompat, playbackInfo.mAudioAttrsCompat)) {
                return true;
            }
            return false;
        }

        static PlaybackInfo createPlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            return new PlaybackInfo(i, audioAttributesCompat, i2, i3, i4);
        }
    }
}
