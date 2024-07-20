package androidx.media2;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.MediaDescriptionCompat;
import android.support.p064v4.media.MediaMetadataCompat;
import android.support.p064v4.media.RatingCompat;
import android.support.p064v4.media.session.MediaControllerCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.MediaController2;
import androidx.media2.MediaItem2;
import androidx.media2.MediaMetadata2;
import androidx.media2.MediaSession2;
import androidx.versionedparcelable.ParcelImpl;
import androidx.versionedparcelable.ParcelUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MediaUtils2 {
    public static final String TAG = "MediaUtils2";
    public static final MediaBrowserServiceCompat.BrowserRoot sDefaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot(MediaLibraryService2.SERVICE_INTERFACE, (Bundle) null);

    public static int convertToPlaybackStateCompatState(int i, int i2) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return 7;
        }
        return i2 != 2 ? 3 : 6;
    }

    public static int convertToPlayerState(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 6:
                return 1;
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
                return 2;
            default:
                return 3;
        }
    }

    public static int toBufferingState(int i) {
        if (i != 3) {
            return i != 6 ? 0 : 2;
        }
        return 3;
    }

    private MediaUtils2() {
    }

    public static MediaBrowserCompat.MediaItem convertToMediaItem(MediaItem2 mediaItem2) {
        MediaDescriptionCompat mediaDescriptionCompat;
        if (mediaItem2 == null) {
            return null;
        }
        MediaMetadata2 metadata = mediaItem2.getMetadata();
        if (metadata == null) {
            mediaDescriptionCompat = new MediaDescriptionCompat.Builder().setMediaId(mediaItem2.getMediaId()).build();
        } else {
            MediaDescriptionCompat.Builder extras = new MediaDescriptionCompat.Builder().setMediaId(mediaItem2.getMediaId()).setSubtitle(metadata.getText("android.media.metadata.DISPLAY_SUBTITLE")).setDescription(metadata.getText("android.media.metadata.DISPLAY_DESCRIPTION")).setIconBitmap(metadata.getBitmap("android.media.metadata.DISPLAY_ICON")).setExtras(metadata.getExtras());
            String string = metadata.getString("android.media.metadata.TITLE");
            if (string != null) {
                extras.setTitle(string);
            } else {
                extras.setTitle(metadata.getString("android.media.metadata.DISPLAY_TITLE"));
            }
            String string2 = metadata.getString("android.media.metadata.DISPLAY_ICON_URI");
            if (string2 != null) {
                extras.setIconUri(Uri.parse(string2));
            }
            String string3 = metadata.getString("android.media.metadata.MEDIA_URI");
            if (string3 != null) {
                extras.setMediaUri(Uri.parse(string3));
            }
            mediaDescriptionCompat = extras.build();
        }
        return new MediaBrowserCompat.MediaItem(mediaDescriptionCompat, mediaItem2.getFlags());
    }

    public static List<MediaBrowserCompat.MediaItem> convertToMediaItemList(List<MediaItem2> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToMediaItem(list.get(i)));
        }
        return arrayList;
    }

    public static MediaItem2 convertToMediaItem2(MediaBrowserCompat.MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getMediaId() == null) {
            return null;
        }
        return new MediaItem2.Builder(mediaItem.getFlags()).setMediaId(mediaItem.getMediaId()).setMetadata(convertToMediaMetadata2(mediaItem.getDescription())).build();
    }

    public static MediaItem2 convertToMediaItem2(MediaSessionCompat.QueueItem queueItem) {
        if (queueItem != null) {
            MediaDescriptionCompat description = queueItem.getDescription();
            return new MediaItem2.Builder(2).setMetadata(convertToMediaMetadata2(description)).setUuid(createUuidByQueueIdAndMediaId(queueItem.getQueueId(), description.getMediaId())).build();
        }
        throw new IllegalArgumentException("item shouldn't be null");
    }

    public static UUID createUuidByQueueIdAndMediaId(long j, String str) {
        return new UUID(j, str == null ? 0 : (long) str.hashCode());
    }

    public static MediaItem2 convertToMediaItem2(MediaMetadataCompat mediaMetadataCompat) {
        MediaMetadata2 convertToMediaMetadata2 = convertToMediaMetadata2(mediaMetadataCompat);
        if (convertToMediaMetadata2 == null || convertToMediaMetadata2.getMediaId() == null) {
            return null;
        }
        return new MediaItem2.Builder(2).setMetadata(convertToMediaMetadata2).build();
    }

    public static MediaItem2 convertToMediaItem2(MediaDescriptionCompat mediaDescriptionCompat) {
        MediaMetadata2 convertToMediaMetadata2 = convertToMediaMetadata2(mediaDescriptionCompat);
        if (convertToMediaMetadata2 == null || convertToMediaMetadata2.getMediaId() == null) {
            return null;
        }
        return new MediaItem2.Builder(2).setMetadata(convertToMediaMetadata2).build();
    }

    public static List<MediaItem2> convertMediaItemListToMediaItem2List(List<MediaBrowserCompat.MediaItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToMediaItem2(list.get(i)));
        }
        return arrayList;
    }

    public static List<MediaItem2> convertQueueItemListToMediaItem2List(List<MediaSessionCompat.QueueItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToMediaItem2(list.get(i)));
        }
        return arrayList;
    }

    public static MediaSessionCompat.QueueItem convertToQueueItem(MediaItem2 mediaItem2) {
        MediaDescriptionCompat mediaDescriptionCompat;
        if (mediaItem2.getMetadata() == null) {
            mediaDescriptionCompat = new MediaDescriptionCompat.Builder().setMediaId(mediaItem2.getMediaId()).build();
        } else {
            mediaDescriptionCompat = convertToMediaMetadataCompat(mediaItem2.getMetadata()).getDescription();
        }
        return new MediaSessionCompat.QueueItem(mediaDescriptionCompat, mediaItem2.getUuid().getMostSignificantBits());
    }

    public static List<MediaSessionCompat.QueueItem> convertToQueueItemList(List<MediaItem2> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToQueueItem(list.get(i)));
        }
        return arrayList;
    }

    public static List<MediaItem2> convertParcelImplListToMediaItem2List(List<ParcelImpl> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ParcelImpl parcelImpl = list.get(i);
            if (parcelImpl != null) {
                arrayList.add((MediaItem2) ParcelUtils.fromParcelable(parcelImpl));
            }
        }
        return arrayList;
    }

    public static MediaMetadata2 convertToMediaMetadata2(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return null;
        }
        MediaMetadata2.Builder builder = new MediaMetadata2.Builder();
        builder.putString("android.media.metadata.MEDIA_ID", mediaDescriptionCompat.getMediaId());
        CharSequence title = mediaDescriptionCompat.getTitle();
        if (title != null) {
            builder.putText("android.media.metadata.DISPLAY_TITLE", title);
        }
        if (mediaDescriptionCompat.getDescription() != null) {
            builder.putText("android.media.metadata.DISPLAY_DESCRIPTION", mediaDescriptionCompat.getDescription());
        }
        CharSequence subtitle = mediaDescriptionCompat.getSubtitle();
        if (subtitle != null) {
            builder.putText("android.media.metadata.DISPLAY_SUBTITLE", subtitle);
        }
        Bitmap iconBitmap = mediaDescriptionCompat.getIconBitmap();
        if (iconBitmap != null) {
            builder.putBitmap("android.media.metadata.DISPLAY_ICON", iconBitmap);
        }
        Uri iconUri = mediaDescriptionCompat.getIconUri();
        if (iconUri != null) {
            builder.putText("android.media.metadata.DISPLAY_ICON_URI", iconUri.toString());
        }
        if (mediaDescriptionCompat.getExtras() != null) {
            builder.setExtras(mediaDescriptionCompat.getExtras());
        }
        Uri mediaUri = mediaDescriptionCompat.getMediaUri();
        if (mediaUri != null) {
            builder.putText("android.media.metadata.MEDIA_URI", mediaUri.toString());
        }
        return builder.build();
    }

    public static MediaMetadata2 convertToMediaMetadata2(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return null;
        }
        return new MediaMetadata2(mediaMetadataCompat.getBundle());
    }

    public static MediaMetadata2 convertToMediaMetadata2(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return new MediaMetadata2.Builder().putString("android.media.metadata.TITLE", charSequence.toString()).build();
    }

    public static MediaMetadataCompat convertToMediaMetadataCompat(MediaMetadata2 mediaMetadata2) {
        if (mediaMetadata2 == null) {
            return null;
        }
        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
        Bundle bundle = mediaMetadata2.toBundle();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof CharSequence) {
                builder.putText(str, (CharSequence) obj);
            } else if (obj instanceof Rating2) {
                builder.putRating(str, convertToRatingCompat((Rating2) obj));
            } else if (obj instanceof Bitmap) {
                builder.putBitmap(str, (Bitmap) obj);
            } else if (obj instanceof Long) {
                builder.putLong(str, ((Long) obj).longValue());
            }
        }
        return builder.build();
    }

    public static MediaMetadataCompat convertToMediaMetadataCompat(MediaDescriptionCompat mediaDescriptionCompat) {
        return convertToMediaMetadataCompat(convertToMediaMetadata2(mediaDescriptionCompat));
    }

    public static Rating2 convertToRating2(RatingCompat ratingCompat) {
        if (ratingCompat == null) {
            return null;
        }
        if (!ratingCompat.isRated()) {
            return Rating2.newUnratedRating(ratingCompat.getRatingStyle());
        }
        switch (ratingCompat.getRatingStyle()) {
            case 1:
                return Rating2.newHeartRating(ratingCompat.hasHeart());
            case 2:
                return Rating2.newThumbRating(ratingCompat.isThumbUp());
            case 3:
            case 4:
            case 5:
                return Rating2.newStarRating(ratingCompat.getRatingStyle(), ratingCompat.getStarRating());
            case 6:
                return Rating2.newPercentageRating(ratingCompat.getPercentRating());
            default:
                return null;
        }
    }

    public static RatingCompat convertToRatingCompat(Rating2 rating2) {
        if (rating2 == null) {
            return null;
        }
        if (!rating2.isRated()) {
            return RatingCompat.newUnratedRating(rating2.getRatingStyle());
        }
        switch (rating2.getRatingStyle()) {
            case 1:
                return RatingCompat.newHeartRating(rating2.hasHeart());
            case 2:
                return RatingCompat.newThumbRating(rating2.isThumbUp());
            case 3:
            case 4:
            case 5:
                return RatingCompat.newStarRating(rating2.getRatingStyle(), rating2.getStarRating());
            case 6:
                return RatingCompat.newPercentageRating(rating2.getPercentRating());
            default:
                return null;
        }
    }

    public static List<ParcelImpl> convertCommandButtonListToParcelImplList(List<MediaSession2.CommandButton> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add((ParcelImpl) ParcelUtils.toParcelable(list.get(i)));
        }
        return arrayList;
    }

    public static List<ParcelImpl> convertMediaItem2ListToParcelImplList(List<MediaItem2> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MediaItem2 mediaItem2 = list.get(i);
            if (mediaItem2 != null) {
                arrayList.add((ParcelImpl) ParcelUtils.toParcelable(mediaItem2));
            }
        }
        return arrayList;
    }

    public static MediaController2.PlaybackInfo toPlaybackInfo2(MediaControllerCompat.PlaybackInfo playbackInfo) {
        return MediaController2.PlaybackInfo.createPlaybackInfo(playbackInfo.getPlaybackType(), new AudioAttributesCompat.Builder().setLegacyStreamType(playbackInfo.getAudioStream()).build(), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
    }

    public static boolean isUnparcelableBundle(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        bundle.setClassLoader(MediaUtils2.class.getClassLoader());
        try {
            bundle.size();
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static void keepUnparcelableBundlesOnly(List<Bundle> list) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (isUnparcelableBundle(list.get(size))) {
                    list.remove(size);
                }
            }
        }
    }
}
