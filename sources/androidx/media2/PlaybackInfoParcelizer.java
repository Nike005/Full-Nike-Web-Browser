package androidx.media2;

import androidx.media.AudioAttributesCompat;
import androidx.media2.MediaController2;
import androidx.versionedparcelable.VersionedParcel;

public final class PlaybackInfoParcelizer {
    public static MediaController2.PlaybackInfo read(VersionedParcel versionedParcel) {
        MediaController2.PlaybackInfo playbackInfo = new MediaController2.PlaybackInfo();
        playbackInfo.mPlaybackType = versionedParcel.readInt(playbackInfo.mPlaybackType, 1);
        playbackInfo.mControlType = versionedParcel.readInt(playbackInfo.mControlType, 2);
        playbackInfo.mMaxVolume = versionedParcel.readInt(playbackInfo.mMaxVolume, 3);
        playbackInfo.mCurrentVolume = versionedParcel.readInt(playbackInfo.mCurrentVolume, 4);
        playbackInfo.mAudioAttrsCompat = (AudioAttributesCompat) versionedParcel.readVersionedParcelable(playbackInfo.mAudioAttrsCompat, 5);
        return playbackInfo;
    }

    public static void write(MediaController2.PlaybackInfo playbackInfo, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(playbackInfo.mPlaybackType, 1);
        versionedParcel.writeInt(playbackInfo.mControlType, 2);
        versionedParcel.writeInt(playbackInfo.mMaxVolume, 3);
        versionedParcel.writeInt(playbackInfo.mCurrentVolume, 4);
        versionedParcel.writeVersionedParcelable(playbackInfo.mAudioAttrsCompat, 5);
    }
}
