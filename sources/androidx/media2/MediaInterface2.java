package androidx.media2;

import android.os.Bundle;
import androidx.media2.MediaSession2;
import java.util.List;

class MediaInterface2 {

    interface SessionPlaybackControl {
        long getBufferedPosition();

        int getBufferingState();

        long getCurrentPosition();

        long getDuration();

        float getPlaybackSpeed();

        int getPlayerState();

        void pause();

        void play();

        void prepare();

        void reset();

        void seekTo(long j);

        void setPlaybackSpeed(float f);
    }

    interface SessionPlayer extends SessionPlaybackControl, SessionPlaylistControl {
        void notifyError(int i, Bundle bundle);

        void skipBackward();

        void skipForward();
    }

    interface SessionPlaylistControl {
        void addPlaylistItem(int i, MediaItem2 mediaItem2);

        void clearOnDataSourceMissingHelper();

        MediaItem2 getCurrentMediaItem();

        List<MediaItem2> getPlaylist();

        MediaMetadata2 getPlaylistMetadata();

        int getRepeatMode();

        int getShuffleMode();

        void removePlaylistItem(MediaItem2 mediaItem2);

        void replacePlaylistItem(int i, MediaItem2 mediaItem2);

        void setOnDataSourceMissingHelper(MediaSession2.OnDataSourceMissingHelper onDataSourceMissingHelper);

        void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2);

        void setRepeatMode(int i);

        void setShuffleMode(int i);

        void skipToNextItem();

        void skipToPlaylistItem(MediaItem2 mediaItem2);

        void skipToPreviousItem();

        void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2);
    }

    private MediaInterface2() {
    }
}
