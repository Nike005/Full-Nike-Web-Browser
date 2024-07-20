package androidx.media2.session;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import androidx.media2.common.MediaItem;
import androidx.media2.common.ParcelImplListSlice;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.VideoSize;
import androidx.media2.session.IMediaSession;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaSession;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.List;

class ConnectionResult extends CustomVersionedParcelable {
    SessionCommandGroup mAllowedCommands;
    long mBufferedPositionMs;
    MediaItem mCurrentMediaItem;
    int mCurrentMediaItemIndex;
    int mNextMediaItemIndex;
    MediaItem mParcelableCurrentMediaItem;
    MediaController.PlaybackInfo mPlaybackInfo;
    float mPlaybackSpeed;
    int mPlayerState;
    ParcelImplListSlice mPlaylistSlice;
    long mPositionEventTimeMs;
    long mPositionMs;
    int mPreviousMediaItemIndex;
    int mRepeatMode;
    SessionPlayer.TrackInfo mSelectedAudioTrack;
    SessionPlayer.TrackInfo mSelectedMetadataTrack;
    SessionPlayer.TrackInfo mSelectedSubtitleTrack;
    SessionPlayer.TrackInfo mSelectedVideoTrack;
    PendingIntent mSessionActivity;
    IBinder mSessionBinder;
    IMediaSession mSessionStub;
    int mShuffleMode;
    Bundle mTokenExtras;
    List<SessionPlayer.TrackInfo> mTrackInfos;
    int mVersion;
    VideoSize mVideoSize;

    ConnectionResult() {
    }

    ConnectionResult(MediaSessionStub mediaSessionStub, MediaSession.MediaSessionImpl mediaSessionImpl, SessionCommandGroup sessionCommandGroup) {
        this.mSessionStub = mediaSessionStub;
        this.mPlayerState = mediaSessionImpl.getPlayerState();
        this.mCurrentMediaItem = mediaSessionImpl.getCurrentMediaItem();
        this.mPositionEventTimeMs = SystemClock.elapsedRealtime();
        this.mPositionMs = mediaSessionImpl.getCurrentPosition();
        this.mPlaybackSpeed = mediaSessionImpl.getPlaybackSpeed();
        this.mBufferedPositionMs = mediaSessionImpl.getBufferedPosition();
        this.mPlaybackInfo = mediaSessionImpl.getPlaybackInfo();
        this.mRepeatMode = mediaSessionImpl.getRepeatMode();
        this.mShuffleMode = mediaSessionImpl.getShuffleMode();
        this.mSessionActivity = mediaSessionImpl.getSessionActivity();
        this.mCurrentMediaItemIndex = mediaSessionImpl.getCurrentMediaItemIndex();
        this.mPreviousMediaItemIndex = mediaSessionImpl.getPreviousMediaItemIndex();
        this.mNextMediaItemIndex = mediaSessionImpl.getNextMediaItemIndex();
        this.mTokenExtras = mediaSessionImpl.getToken().getExtras();
        this.mVideoSize = mediaSessionImpl.getVideoSize();
        this.mTrackInfos = mediaSessionImpl.getTrackInfo();
        this.mSelectedVideoTrack = mediaSessionImpl.getSelectedTrack(1);
        this.mSelectedAudioTrack = mediaSessionImpl.getSelectedTrack(2);
        this.mSelectedSubtitleTrack = mediaSessionImpl.getSelectedTrack(4);
        this.mSelectedMetadataTrack = mediaSessionImpl.getSelectedTrack(5);
        if (sessionCommandGroup == null || !sessionCommandGroup.hasCommand((int) SessionCommand.COMMAND_CODE_PLAYER_GET_PLAYLIST)) {
            this.mPlaylistSlice = null;
        } else {
            this.mPlaylistSlice = MediaUtils.convertMediaItemListToParcelImplListSlice(mediaSessionImpl.getPlaylist());
        }
        this.mAllowedCommands = sessionCommandGroup;
        this.mVersion = 0;
    }

    public IMediaSession getSessionStub() {
        return this.mSessionStub;
    }

    public PendingIntent getSessionActivity() {
        return this.mSessionActivity;
    }

    public int getPlayerState() {
        return this.mPlayerState;
    }

    public MediaItem getCurrentMediaItem() {
        return this.mCurrentMediaItem;
    }

    public long getPositionEventTimeMs() {
        return this.mPositionEventTimeMs;
    }

    public long getPositionMs() {
        return this.mPositionMs;
    }

    public float getPlaybackSpeed() {
        return this.mPlaybackSpeed;
    }

    public long getBufferedPositionMs() {
        return this.mBufferedPositionMs;
    }

    public MediaController.PlaybackInfo getPlaybackInfo() {
        return this.mPlaybackInfo;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public int getShuffleMode() {
        return this.mShuffleMode;
    }

    public ParcelImplListSlice getPlaylistSlice() {
        return this.mPlaylistSlice;
    }

    public SessionCommandGroup getAllowedCommands() {
        return this.mAllowedCommands;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public int getCurrentMediaItemIndex() {
        return this.mCurrentMediaItemIndex;
    }

    public int getPreviousMediaItemIndex() {
        return this.mPreviousMediaItemIndex;
    }

    public int getNextMediaItemIndex() {
        return this.mNextMediaItemIndex;
    }

    public Bundle getTokenExtras() {
        return this.mTokenExtras;
    }

    public VideoSize getVideoSize() {
        return this.mVideoSize;
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        return this.mTrackInfos;
    }

    public SessionPlayer.TrackInfo getSelectedVideoTrack() {
        return this.mSelectedVideoTrack;
    }

    public SessionPlayer.TrackInfo getSelectedAudioTrack() {
        return this.mSelectedAudioTrack;
    }

    public SessionPlayer.TrackInfo getSelectedSubtitleTrack() {
        return this.mSelectedSubtitleTrack;
    }

    public SessionPlayer.TrackInfo getSelectedMetadataTrack() {
        return this.mSelectedMetadataTrack;
    }

    public void onPreParceling(boolean z) {
        this.mSessionBinder = (IBinder) this.mSessionStub;
        this.mParcelableCurrentMediaItem = MediaUtils.upcastForPreparceling(this.mCurrentMediaItem);
    }

    public void onPostParceling() {
        this.mSessionStub = IMediaSession.Stub.asInterface(this.mSessionBinder);
        this.mSessionBinder = null;
        this.mCurrentMediaItem = this.mParcelableCurrentMediaItem;
        this.mParcelableCurrentMediaItem = null;
    }
}
