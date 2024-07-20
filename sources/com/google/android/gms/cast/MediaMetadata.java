package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new zzak();
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final String[] zzdp = {null, "String", "int", "double", "ISO-8601 date String"};
    private static final zza zzdq = new zza().zza(KEY_CREATION_DATE, "creationDateTime", 4).zza(KEY_RELEASE_DATE, "releaseDate", 4).zza(KEY_BROADCAST_DATE, "originalAirdate", 4).zza(KEY_TITLE, "title", 1).zza(KEY_SUBTITLE, "subtitle", 1).zza(KEY_ARTIST, "artist", 1).zza(KEY_ALBUM_ARTIST, "albumArtist", 1).zza(KEY_ALBUM_TITLE, "albumName", 1).zza(KEY_COMPOSER, "composer", 1).zza(KEY_DISC_NUMBER, "discNumber", 2).zza(KEY_TRACK_NUMBER, "trackNumber", 2).zza(KEY_SEASON_NUMBER, "season", 2).zza(KEY_EPISODE_NUMBER, "episode", 2).zza(KEY_SERIES_TITLE, "seriesTitle", 1).zza(KEY_STUDIO, "studio", 1).zza(KEY_WIDTH, "width", 2).zza(KEY_HEIGHT, "height", 2).zza(KEY_LOCATION_NAME, "location", 1).zza(KEY_LOCATION_LATITUDE, "latitude", 3).zza(KEY_LOCATION_LONGITUDE, "longitude", 3);
    private final Bundle zzdr;
    private int zzds;
    private final List<WebImage> zzz;

    private static class zza {
        private final Map<String, String> zzdt = new HashMap();
        private final Map<String, String> zzdu = new HashMap();
        private final Map<String, Integer> zzdv = new HashMap();

        public final zza zza(String str, String str2, int i) {
            this.zzdt.put(str, str2);
            this.zzdu.put(str2, str);
            this.zzdv.put(str, Integer.valueOf(i));
            return this;
        }

        public final String zze(String str) {
            return this.zzdt.get(str);
        }

        public final String zzf(String str) {
            return this.zzdu.get(str);
        }

        public final int zzg(String str) {
            Integer num = this.zzdv.get(str);
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i) {
        this(new ArrayList(), new Bundle(), i);
    }

    MediaMetadata(List<WebImage> list, Bundle bundle, int i) {
        this.zzz = list;
        this.zzdr = bundle;
        this.zzds = i;
    }

    private static void zza(String str, int i) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            int zzg = zzdq.zzg(str);
            if (zzg != i && zzg != 0) {
                String str2 = zzdp[i];
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length());
                sb.append("Value for ");
                sb.append(str);
                sb.append(" must be a ");
                sb.append(str2);
                throw new IllegalArgumentException(sb.toString());
            }
            return;
        }
        throw new IllegalArgumentException("null and empty keys are not allowed");
    }

    private final void zza(JSONObject jSONObject, String... strArr) {
        try {
            for (String str : strArr) {
                if (this.zzdr.containsKey(str)) {
                    int zzg = zzdq.zzg(str);
                    if (zzg != 1) {
                        if (zzg == 2) {
                            jSONObject.put(zzdq.zze(str), this.zzdr.getInt(str));
                        } else if (zzg == 3) {
                            jSONObject.put(zzdq.zze(str), this.zzdr.getDouble(str));
                        } else if (zzg != 4) {
                        }
                    }
                    jSONObject.put(zzdq.zze(str), this.zzdr.getString(str));
                }
            }
            for (String str2 : this.zzdr.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj = this.zzdr.get(str2);
                    if (!(obj instanceof String)) {
                        if (!(obj instanceof Integer)) {
                            if (!(obj instanceof Double)) {
                            }
                        }
                    }
                    jSONObject.put(str2, obj);
                }
            }
        } catch (JSONException unused) {
        }
    }

    private final boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private final void zzb(JSONObject jSONObject, String... strArr) {
        Bundle bundle;
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!"metadataType".equals(next)) {
                    String zzf = zzdq.zzf(next);
                    if (zzf == null) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof String) {
                            this.zzdr.putString(next, (String) obj);
                        } else if (obj instanceof Integer) {
                            this.zzdr.putInt(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            this.zzdr.putDouble(next, ((Double) obj).doubleValue());
                        }
                    } else if (hashSet.contains(zzf)) {
                        try {
                            Object obj2 = jSONObject.get(next);
                            if (obj2 != null) {
                                int zzg = zzdq.zzg(zzf);
                                if (zzg != 1) {
                                    if (zzg != 2) {
                                        if (zzg != 3) {
                                            if (zzg == 4) {
                                                if ((obj2 instanceof String) && zzdp.zzu((String) obj2) != null) {
                                                    bundle = this.zzdr;
                                                }
                                            }
                                        } else if (obj2 instanceof Double) {
                                            this.zzdr.putDouble(zzf, ((Double) obj2).doubleValue());
                                        }
                                    } else if (obj2 instanceof Integer) {
                                        this.zzdr.putInt(zzf, ((Integer) obj2).intValue());
                                    }
                                } else if (obj2 instanceof String) {
                                    bundle = this.zzdr;
                                }
                                bundle.putString(zzf, (String) obj2);
                            }
                        } catch (JSONException unused) {
                        }
                    }
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public void addImage(WebImage webImage) {
        this.zzz.add(webImage);
    }

    public void clear() {
        this.zzdr.clear();
        this.zzz.clear();
    }

    public void clearImages() {
        this.zzz.clear();
    }

    public boolean containsKey(String str) {
        return this.zzdr.containsKey(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return zza(this.zzdr, mediaMetadata.zzdr) && this.zzz.equals(mediaMetadata.zzz);
    }

    public Calendar getDate(String str) {
        zza(str, 4);
        String string = this.zzdr.getString(str);
        if (string != null) {
            return zzdp.zzu(string);
        }
        return null;
    }

    public String getDateAsString(String str) {
        zza(str, 4);
        return this.zzdr.getString(str);
    }

    public double getDouble(String str) {
        zza(str, 3);
        return this.zzdr.getDouble(str);
    }

    public List<WebImage> getImages() {
        return this.zzz;
    }

    public int getInt(String str) {
        zza(str, 2);
        return this.zzdr.getInt(str);
    }

    public int getMediaType() {
        return this.zzds;
    }

    public String getString(String str) {
        zza(str, 1);
        return this.zzdr.getString(str);
    }

    public boolean hasImages() {
        List<WebImage> list = this.zzz;
        return list != null && !list.isEmpty();
    }

    public int hashCode() {
        int i = 17;
        for (String str : this.zzdr.keySet()) {
            i = (i * 31) + this.zzdr.get(str).hashCode();
        }
        return (i * 31) + this.zzz.hashCode();
    }

    public Set<String> keySet() {
        return this.zzdr.keySet();
    }

    public void putDate(String str, Calendar calendar) {
        zza(str, 4);
        this.zzdr.putString(str, zzdp.zza(calendar));
    }

    public void putDouble(String str, double d) {
        zza(str, 3);
        this.zzdr.putDouble(str, d);
    }

    public void putInt(String str, int i) {
        zza(str, 2);
        this.zzdr.putInt(str, i);
    }

    public void putString(String str, String str2) {
        zza(str, 1);
        this.zzdr.putString(str, str2);
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zzds);
        } catch (JSONException unused) {
        }
        zzdp.zza(jSONObject, this.zzz);
        int i = this.zzds;
        if (i == 0) {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            zza(jSONObject, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            zza(jSONObject, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i != 4) {
            zza(jSONObject, new String[0]);
        } else {
            zza(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
        return jSONObject;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getImages(), false);
        SafeParcelWriter.writeBundle(parcel, 3, this.zzdr, false);
        SafeParcelWriter.writeInt(parcel, 4, getMediaType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zze(JSONObject jSONObject) {
        clear();
        this.zzds = 0;
        try {
            this.zzds = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        zzdp.zza(this.zzz, jSONObject);
        int i = this.zzds;
        if (i == 0) {
            zzb(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 1) {
            zzb(jSONObject, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i == 2) {
            zzb(jSONObject, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i == 3) {
            zzb(jSONObject, KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i != 4) {
            zzb(jSONObject, new String[0]);
        } else {
            zzb(jSONObject, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        }
    }
}
