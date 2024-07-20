package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzcu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzan();
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    private MediaInfo zzdi;
    private double zzdl;
    private long[] zzdm;
    private long zzec;
    private int zzed;
    private int zzee;
    private int zzef;
    private long zzeg;
    private long zzeh;
    private double zzei;
    private boolean zzej;
    private int zzek;
    private int zzel;
    private int zzem;
    private final ArrayList<MediaQueueItem> zzen;
    private boolean zzeo;
    private AdBreakStatus zzep;
    private VideoInfo zzeq;
    private final SparseArray<Integer> zzer;
    private String zzj;
    private JSONObject zzp;

    MediaStatus(MediaInfo mediaInfo, long j, int i, double d, int i2, int i3, long j2, long j3, double d2, boolean z, long[] jArr, int i4, int i5, String str, int i6, List<MediaQueueItem> list, boolean z2, AdBreakStatus adBreakStatus, VideoInfo videoInfo) {
        String str2 = str;
        List<MediaQueueItem> list2 = list;
        this.zzen = new ArrayList<>();
        this.zzer = new SparseArray<>();
        this.zzdi = mediaInfo;
        this.zzec = j;
        this.zzed = i;
        this.zzdl = d;
        this.zzee = i2;
        this.zzef = i3;
        this.zzeg = j2;
        this.zzeh = j3;
        this.zzei = d2;
        this.zzej = z;
        this.zzdm = jArr;
        this.zzek = i4;
        this.zzel = i5;
        this.zzj = str2;
        if (str2 != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzem = i6;
        if (list2 != null && !list.isEmpty()) {
            zza((MediaQueueItem[]) list2.toArray(new MediaQueueItem[list.size()]));
        }
        this.zzeo = z2;
        this.zzep = adBreakStatus;
        this.zzeq = videoInfo;
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this((MediaInfo) null, 0, 0, 0.0d, 0, 0, 0, 0, 0.0d, false, (long[]) null, 0, 0, (String) null, 0, (List<MediaQueueItem>) null, false, (AdBreakStatus) null, (VideoInfo) null);
        zza(jSONObject, 0);
    }

    private final void zza(MediaQueueItem[] mediaQueueItemArr) {
        this.zzen.clear();
        this.zzer.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
            this.zzen.add(mediaQueueItem);
            this.zzer.put(mediaQueueItem.getItemId(), Integer.valueOf(i));
        }
    }

    private static boolean zza(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return i4 != 2;
            }
            if (i2 != 3) {
                return true;
            }
        }
        return i3 == 0;
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        if ((this.zzp == null) == (mediaStatus.zzp == null) && this.zzec == mediaStatus.zzec && this.zzed == mediaStatus.zzed && this.zzdl == mediaStatus.zzdl && this.zzee == mediaStatus.zzee && this.zzef == mediaStatus.zzef && this.zzeg == mediaStatus.zzeg && this.zzei == mediaStatus.zzei && this.zzej == mediaStatus.zzej && this.zzek == mediaStatus.zzek && this.zzel == mediaStatus.zzel && this.zzem == mediaStatus.zzem && Arrays.equals(this.zzdm, mediaStatus.zzdm) && zzcu.zza(Long.valueOf(this.zzeh), Long.valueOf(mediaStatus.zzeh)) && zzcu.zza(this.zzen, mediaStatus.zzen) && zzcu.zza(this.zzdi, mediaStatus.zzdi)) {
            JSONObject jSONObject2 = this.zzp;
            return (jSONObject2 == null || (jSONObject = mediaStatus.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject2, jSONObject)) && this.zzeo == mediaStatus.isPlayingAd();
        }
    }

    public long[] getActiveTrackIds() {
        return this.zzdm;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzep;
    }

    public AdBreakInfo getCurrentAdBreak() {
        List<AdBreakInfo> adBreaks;
        AdBreakStatus adBreakStatus = this.zzep;
        if (!(adBreakStatus == null || this.zzdi == null)) {
            String breakId = adBreakStatus.getBreakId();
            if (!TextUtils.isEmpty(breakId) && (adBreaks = this.zzdi.getAdBreaks()) != null && !adBreaks.isEmpty()) {
                for (AdBreakInfo next : adBreaks) {
                    if (breakId.equals(next.getId())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        List<AdBreakClipInfo> adBreakClips;
        AdBreakStatus adBreakStatus = this.zzep;
        if (!(adBreakStatus == null || this.zzdi == null)) {
            String breakClipId = adBreakStatus.getBreakClipId();
            if (!TextUtils.isEmpty(breakClipId) && (adBreakClips = this.zzdi.getAdBreakClips()) != null && !adBreakClips.isEmpty()) {
                for (AdBreakClipInfo next : adBreakClips) {
                    if (breakClipId.equals(next.getId())) {
                        return next;
                    }
                }
            }
        }
        return null;
    }

    public int getCurrentItemId() {
        return this.zzed;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public int getIdleReason() {
        return this.zzef;
    }

    public Integer getIndexById(int i) {
        return this.zzer.get(i);
    }

    public MediaQueueItem getItemById(int i) {
        Integer num = this.zzer.get(i);
        if (num == null) {
            return null;
        }
        return this.zzen.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i) {
        if (i < 0 || i >= this.zzen.size()) {
            return null;
        }
        return this.zzen.get(i);
    }

    public int getLoadingItemId() {
        return this.zzek;
    }

    public MediaInfo getMediaInfo() {
        return this.zzdi;
    }

    public double getPlaybackRate() {
        return this.zzdl;
    }

    public int getPlayerState() {
        return this.zzee;
    }

    public int getPreloadedItemId() {
        return this.zzel;
    }

    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public int getQueueItemCount() {
        return this.zzen.size();
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzen;
    }

    public int getQueueRepeatMode() {
        return this.zzem;
    }

    public long getStreamPosition() {
        return this.zzeg;
    }

    public double getStreamVolume() {
        return this.zzei;
    }

    public VideoInfo getVideoInfo() {
        return this.zzeq;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdi, Long.valueOf(this.zzec), Integer.valueOf(this.zzed), Double.valueOf(this.zzdl), Integer.valueOf(this.zzee), Integer.valueOf(this.zzef), Long.valueOf(this.zzeg), Long.valueOf(this.zzeh), Double.valueOf(this.zzei), Boolean.valueOf(this.zzej), Integer.valueOf(Arrays.hashCode(this.zzdm)), Integer.valueOf(this.zzek), Integer.valueOf(this.zzel), String.valueOf(this.zzp), Integer.valueOf(this.zzem), this.zzen, Boolean.valueOf(this.zzeo));
    }

    public boolean isMediaCommandSupported(long j) {
        return (j & this.zzeh) != 0;
    }

    public boolean isMute() {
        return this.zzej;
    }

    public boolean isPlayingAd() {
        return this.zzeo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzec);
        SafeParcelWriter.writeInt(parcel, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(parcel, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(parcel, 6, getPlayerState());
        SafeParcelWriter.writeInt(parcel, 7, getIdleReason());
        SafeParcelWriter.writeLong(parcel, 8, getStreamPosition());
        SafeParcelWriter.writeLong(parcel, 9, this.zzeh);
        SafeParcelWriter.writeDouble(parcel, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(parcel, 11, isMute());
        SafeParcelWriter.writeLongArray(parcel, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(parcel, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(parcel, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(parcel, 15, this.zzj, false);
        SafeParcelWriter.writeInt(parcel, 16, this.zzem);
        SafeParcelWriter.writeTypedList(parcel, 17, this.zzen, false);
        SafeParcelWriter.writeBoolean(parcel, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(parcel, 19, getAdBreakStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 20, getVideoInfo(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02c2, code lost:
        if (r15 == false) goto L_0x02e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02fd  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x032e  */
    /* JADX WARNING: Removed duplicated region for block: B:210:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(org.json.JSONObject r14, int r15) throws org.json.JSONException {
        /*
            r13 = this;
            java.lang.String r0 = "mediaSessionId"
            long r0 = r14.getLong(r0)
            long r2 = r13.zzec
            r4 = 0
            r5 = 1
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0012
            r13.zzec = r0
            r0 = 1
            goto L_0x0013
        L_0x0012:
            r0 = 0
        L_0x0013:
            java.lang.String r1 = "playerState"
            boolean r2 = r14.has(r1)
            r3 = 3
            r6 = 2
            if (r2 == 0) goto L_0x0091
            java.lang.String r1 = r14.getString(r1)
            java.lang.String r2 = "IDLE"
            boolean r2 = r1.equals(r2)
            r7 = 4
            if (r2 == 0) goto L_0x002c
            r1 = 1
            goto L_0x004b
        L_0x002c:
            java.lang.String r2 = "PLAYING"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0036
            r1 = 2
            goto L_0x004b
        L_0x0036:
            java.lang.String r2 = "PAUSED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0040
            r1 = 3
            goto L_0x004b
        L_0x0040:
            java.lang.String r2 = "BUFFERING"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004a
            r1 = 4
            goto L_0x004b
        L_0x004a:
            r1 = 0
        L_0x004b:
            int r2 = r13.zzee
            if (r1 == r2) goto L_0x0053
            r13.zzee = r1
            r0 = r0 | 2
        L_0x0053:
            if (r1 != r5) goto L_0x0091
            java.lang.String r1 = "idleReason"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x0091
            java.lang.String r1 = r14.getString(r1)
            java.lang.String r2 = "CANCELLED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x006b
            r7 = 2
            goto L_0x0089
        L_0x006b:
            java.lang.String r2 = "INTERRUPTED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0075
            r7 = 3
            goto L_0x0089
        L_0x0075:
            java.lang.String r2 = "FINISHED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x007f
            r7 = 1
            goto L_0x0089
        L_0x007f:
            java.lang.String r2 = "ERROR"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r7 = 0
        L_0x0089:
            int r1 = r13.zzef
            if (r7 == r1) goto L_0x0091
            r13.zzef = r7
            r0 = r0 | 2
        L_0x0091:
            java.lang.String r1 = "playbackRate"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00a7
            double r1 = r14.getDouble(r1)
            double r7 = r13.zzdl
            int r9 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r9 == 0) goto L_0x00a7
            r13.zzdl = r1
            r0 = r0 | 2
        L_0x00a7:
            java.lang.String r1 = "currentTime"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00c9
            r2 = r15 & 2
            if (r2 != 0) goto L_0x00c9
            double r1 = r14.getDouble(r1)
            r7 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r1 = r1 * r7
            long r1 = (long) r1
            long r7 = r13.zzeg
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00c9
            r13.zzeg = r1
            r0 = r0 | 2
        L_0x00c9:
            java.lang.String r1 = "supportedMediaCommands"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x00df
            long r1 = r14.getLong(r1)
            long r7 = r13.zzeh
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00df
            r13.zzeh = r1
            r0 = r0 | 2
        L_0x00df:
            java.lang.String r1 = "volume"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x010c
            r15 = r15 & r5
            if (r15 != 0) goto L_0x010c
            org.json.JSONObject r15 = r14.getJSONObject(r1)
            java.lang.String r1 = "level"
            double r1 = r15.getDouble(r1)
            double r7 = r13.zzei
            int r9 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x00fe
            r13.zzei = r1
            r0 = r0 | 2
        L_0x00fe:
            java.lang.String r1 = "muted"
            boolean r15 = r15.getBoolean(r1)
            boolean r1 = r13.zzej
            if (r15 == r1) goto L_0x010c
            r13.zzej = r15
            r0 = r0 | 2
        L_0x010c:
            java.lang.String r15 = "activeTrackIds"
            boolean r1 = r14.has(r15)
            r2 = 0
            if (r1 == 0) goto L_0x014c
            org.json.JSONArray r15 = r14.getJSONArray(r15)
            int r1 = r15.length()
            long[] r7 = new long[r1]
            r8 = 0
        L_0x0120:
            if (r8 >= r1) goto L_0x012b
            long r9 = r15.getLong(r8)
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x0120
        L_0x012b:
            long[] r15 = r13.zzdm
            if (r15 != 0) goto L_0x0131
        L_0x012f:
            r15 = 1
            goto L_0x0147
        L_0x0131:
            int r15 = r15.length
            if (r15 == r1) goto L_0x0135
            goto L_0x012f
        L_0x0135:
            r15 = 0
        L_0x0136:
            if (r15 >= r1) goto L_0x0146
            long[] r8 = r13.zzdm
            r9 = r8[r15]
            r11 = r7[r15]
            int r8 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r8 == 0) goto L_0x0143
            goto L_0x012f
        L_0x0143:
            int r15 = r15 + 1
            goto L_0x0136
        L_0x0146:
            r15 = 0
        L_0x0147:
            if (r15 == 0) goto L_0x0154
            r13.zzdm = r7
            goto L_0x0154
        L_0x014c:
            long[] r15 = r13.zzdm
            r7 = r2
            if (r15 == 0) goto L_0x0153
            r15 = 1
            goto L_0x0154
        L_0x0153:
            r15 = 0
        L_0x0154:
            if (r15 == 0) goto L_0x015a
            r13.zzdm = r7
            r0 = r0 | 2
        L_0x015a:
            java.lang.String r15 = "customData"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x016c
            org.json.JSONObject r15 = r14.getJSONObject(r15)
            r13.zzp = r15
            r13.zzj = r2
            r0 = r0 | 2
        L_0x016c:
            java.lang.String r15 = "media"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x0197
            org.json.JSONObject r15 = r14.getJSONObject(r15)
            com.google.android.gms.cast.MediaInfo r1 = new com.google.android.gms.cast.MediaInfo
            r1.<init>((org.json.JSONObject) r15)
            com.google.android.gms.cast.MediaInfo r2 = r13.zzdi
            if (r2 == 0) goto L_0x0189
            if (r2 == 0) goto L_0x018d
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L_0x018d
        L_0x0189:
            r13.zzdi = r1
            r0 = r0 | 2
        L_0x018d:
            java.lang.String r1 = "metadata"
            boolean r15 = r15.has(r1)
            if (r15 == 0) goto L_0x0197
            r0 = r0 | 4
        L_0x0197:
            java.lang.String r15 = "currentItemId"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x01ab
            int r15 = r14.getInt(r15)
            int r1 = r13.zzed
            if (r1 == r15) goto L_0x01ab
            r13.zzed = r15
            r0 = r0 | 2
        L_0x01ab:
            java.lang.String r15 = "preloadedItemId"
            int r15 = r14.optInt(r15, r4)
            int r1 = r13.zzel
            if (r1 == r15) goto L_0x01b9
            r13.zzel = r15
            r0 = r0 | 16
        L_0x01b9:
            java.lang.String r15 = "loadingItemId"
            int r15 = r14.optInt(r15, r4)
            int r1 = r13.zzek
            if (r1 == r15) goto L_0x01c7
            r13.zzek = r15
            r0 = r0 | 2
        L_0x01c7:
            com.google.android.gms.cast.MediaInfo r15 = r13.zzdi
            r1 = -1
            if (r15 != 0) goto L_0x01ce
            r15 = -1
            goto L_0x01d2
        L_0x01ce:
            int r15 = r15.getStreamType()
        L_0x01d2:
            int r2 = r13.zzee
            int r7 = r13.zzef
            int r8 = r13.zzek
            boolean r15 = zza(r2, r7, r8, r15)
            if (r15 != 0) goto L_0x02c5
            java.lang.String r15 = "repeatMode"
            boolean r2 = r14.has(r15)
            if (r2 == 0) goto L_0x0232
            int r2 = r13.zzem
            java.lang.String r15 = r14.getString(r15)
            int r7 = r15.hashCode()
            switch(r7) {
                case -1118317585: goto L_0x0212;
                case -962896020: goto L_0x0208;
                case 1645938909: goto L_0x01fe;
                case 1645952171: goto L_0x01f4;
                default: goto L_0x01f3;
            }
        L_0x01f3:
            goto L_0x021b
        L_0x01f4:
            java.lang.String r7 = "REPEAT_OFF"
            boolean r15 = r15.equals(r7)
            if (r15 == 0) goto L_0x021b
            r1 = 0
            goto L_0x021b
        L_0x01fe:
            java.lang.String r7 = "REPEAT_ALL"
            boolean r15 = r15.equals(r7)
            if (r15 == 0) goto L_0x021b
            r1 = 1
            goto L_0x021b
        L_0x0208:
            java.lang.String r7 = "REPEAT_SINGLE"
            boolean r15 = r15.equals(r7)
            if (r15 == 0) goto L_0x021b
            r1 = 2
            goto L_0x021b
        L_0x0212:
            java.lang.String r7 = "REPEAT_ALL_AND_SHUFFLE"
            boolean r15 = r15.equals(r7)
            if (r15 == 0) goto L_0x021b
            r1 = 3
        L_0x021b:
            if (r1 == 0) goto L_0x0229
            if (r1 == r5) goto L_0x0227
            if (r1 == r6) goto L_0x0225
            if (r1 == r3) goto L_0x022a
            r3 = r2
            goto L_0x022a
        L_0x0225:
            r3 = 2
            goto L_0x022a
        L_0x0227:
            r3 = 1
            goto L_0x022a
        L_0x0229:
            r3 = 0
        L_0x022a:
            int r15 = r13.zzem
            if (r15 == r3) goto L_0x0232
            r13.zzem = r3
            r15 = 1
            goto L_0x0233
        L_0x0232:
            r15 = 0
        L_0x0233:
            java.lang.String r1 = "items"
            boolean r2 = r14.has(r1)
            if (r2 == 0) goto L_0x02c2
            org.json.JSONArray r1 = r14.getJSONArray(r1)
            int r2 = r1.length()
            android.util.SparseArray r3 = new android.util.SparseArray
            r3.<init>()
            r6 = 0
        L_0x0249:
            if (r6 >= r2) goto L_0x025f
            org.json.JSONObject r7 = r1.getJSONObject(r6)
            java.lang.String r8 = "itemId"
            int r7 = r7.getInt(r8)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3.put(r6, r7)
            int r6 = r6 + 1
            goto L_0x0249
        L_0x025f:
            com.google.android.gms.cast.MediaQueueItem[] r6 = new com.google.android.gms.cast.MediaQueueItem[r2]
            r7 = 0
        L_0x0262:
            if (r7 >= r2) goto L_0x02b6
            java.lang.Object r8 = r3.get(r7)
            java.lang.Integer r8 = (java.lang.Integer) r8
            org.json.JSONObject r9 = r1.getJSONObject(r7)
            int r10 = r8.intValue()
            com.google.android.gms.cast.MediaQueueItem r10 = r13.getItemById(r10)
            if (r10 == 0) goto L_0x028e
            boolean r9 = r10.zzf(r9)
            r15 = r15 | r9
            r6[r7] = r10
            int r8 = r8.intValue()
            java.lang.Integer r8 = r13.getIndexById(r8)
            int r8 = r8.intValue()
            if (r7 == r8) goto L_0x02b3
            goto L_0x02b2
        L_0x028e:
            int r15 = r8.intValue()
            int r8 = r13.zzed
            if (r15 != r8) goto L_0x02ab
            com.google.android.gms.cast.MediaInfo r15 = r13.zzdi
            if (r15 == 0) goto L_0x02ab
            com.google.android.gms.cast.MediaQueueItem$Builder r8 = new com.google.android.gms.cast.MediaQueueItem$Builder
            r8.<init>((com.google.android.gms.cast.MediaInfo) r15)
            com.google.android.gms.cast.MediaQueueItem r15 = r8.build()
            r6[r7] = r15
            r15 = r6[r7]
            r15.zzf(r9)
            goto L_0x02b2
        L_0x02ab:
            com.google.android.gms.cast.MediaQueueItem r15 = new com.google.android.gms.cast.MediaQueueItem
            r15.<init>((org.json.JSONObject) r9)
            r6[r7] = r15
        L_0x02b2:
            r15 = 1
        L_0x02b3:
            int r7 = r7 + 1
            goto L_0x0262
        L_0x02b6:
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r1 = r13.zzen
            int r1 = r1.size()
            if (r1 == r2) goto L_0x02bf
            r15 = 1
        L_0x02bf:
            r13.zza(r6)
        L_0x02c2:
            if (r15 == 0) goto L_0x02e1
            goto L_0x02df
        L_0x02c5:
            r13.zzed = r4
            r13.zzek = r4
            r13.zzel = r4
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r15 = r13.zzen
            boolean r15 = r15.isEmpty()
            if (r15 != 0) goto L_0x02e1
            r13.zzem = r4
            java.util.ArrayList<com.google.android.gms.cast.MediaQueueItem> r15 = r13.zzen
            r15.clear()
            android.util.SparseArray<java.lang.Integer> r15 = r13.zzer
            r15.clear()
        L_0x02df:
            r0 = r0 | 8
        L_0x02e1:
            java.lang.String r15 = "breakStatus"
            org.json.JSONObject r15 = r14.optJSONObject(r15)
            com.google.android.gms.cast.AdBreakStatus r15 = com.google.android.gms.cast.AdBreakStatus.zzc(r15)
            com.google.android.gms.cast.AdBreakStatus r1 = r13.zzep
            if (r1 != 0) goto L_0x02f1
            if (r15 != 0) goto L_0x02fb
        L_0x02f1:
            com.google.android.gms.cast.AdBreakStatus r1 = r13.zzep
            if (r1 == 0) goto L_0x0304
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x0304
        L_0x02fb:
            if (r15 == 0) goto L_0x02fe
            r4 = 1
        L_0x02fe:
            r13.zzeo = r4
            r13.zzep = r15
            r0 = r0 | 32
        L_0x0304:
            java.lang.String r15 = "videoInfo"
            org.json.JSONObject r15 = r14.optJSONObject(r15)
            com.google.android.gms.cast.VideoInfo r15 = com.google.android.gms.cast.VideoInfo.zzg(r15)
            com.google.android.gms.cast.VideoInfo r1 = r13.zzeq
            if (r1 != 0) goto L_0x0314
            if (r15 != 0) goto L_0x031e
        L_0x0314:
            com.google.android.gms.cast.VideoInfo r1 = r13.zzeq
            if (r1 == 0) goto L_0x0322
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x0322
        L_0x031e:
            r13.zzeq = r15
            r0 = r0 | 64
        L_0x0322:
            java.lang.String r15 = "breakInfo"
            boolean r1 = r14.has(r15)
            if (r1 == 0) goto L_0x0337
            com.google.android.gms.cast.MediaInfo r1 = r13.zzdi
            if (r1 == 0) goto L_0x0337
            org.json.JSONObject r14 = r14.getJSONObject(r15)
            r1.zzd((org.json.JSONObject) r14)
            r0 = r0 | 2
        L_0x0337:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaStatus.zza(org.json.JSONObject, int):int");
    }

    public final void zzf(boolean z) {
        this.zzeo = z;
    }

    public final long zzj() {
        return this.zzec;
    }

    public final boolean zzk() {
        MediaInfo mediaInfo = this.zzdi;
        return zza(this.zzee, this.zzef, this.zzek, mediaInfo == null ? -1 : mediaInfo.getStreamType());
    }
}
