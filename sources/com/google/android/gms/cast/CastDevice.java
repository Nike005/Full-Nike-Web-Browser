package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = new zzn();
    private int status;
    private String zzam;
    private String zzan;
    private InetAddress zzao;
    private String zzap;
    private String zzaq;
    private String zzar;
    private int zzas;
    private List<WebImage> zzat;
    private int zzau;
    private String zzav;
    private String zzaw;
    private int zzax;
    private String zzay;
    private byte[] zzaz;

    CastDevice(String str, String str2, String str3, String str4, String str5, int i, List<WebImage> list, int i2, int i3, String str6, String str7, int i4, String str8, byte[] bArr) {
        this.zzam = zza(str);
        String zza = zza(str2);
        this.zzan = zza;
        if (!TextUtils.isEmpty(zza)) {
            try {
                this.zzao = InetAddress.getByName(this.zzan);
            } catch (UnknownHostException e) {
                String str9 = this.zzan;
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder(String.valueOf(str9).length() + 48 + String.valueOf(message).length());
                sb.append("Unable to convert host address (");
                sb.append(str9);
                sb.append(") to ipaddress: ");
                sb.append(message);
                Log.i("CastDevice", sb.toString());
            }
        }
        this.zzap = zza(str3);
        this.zzaq = zza(str4);
        this.zzar = zza(str5);
        this.zzas = i;
        this.zzat = list != null ? list : new ArrayList<>();
        this.zzau = i2;
        this.status = i3;
        this.zzav = zza(str6);
        this.zzaw = str7;
        this.zzax = i4;
        this.zzay = str8;
        this.zzaz = bArr;
    }

    public static CastDevice getFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(CastDevice.class.getClassLoader());
        return (CastDevice) bundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    }

    private static String zza(String str) {
        return str == null ? "" : str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        String str = this.zzam;
        return str == null ? castDevice.zzam == null : zzcu.zza(str, castDevice.zzam) && zzcu.zza(this.zzao, castDevice.zzao) && zzcu.zza(this.zzaq, castDevice.zzaq) && zzcu.zza(this.zzap, castDevice.zzap) && zzcu.zza(this.zzar, castDevice.zzar) && this.zzas == castDevice.zzas && zzcu.zza(this.zzat, castDevice.zzat) && this.zzau == castDevice.zzau && this.status == castDevice.status && zzcu.zza(this.zzav, castDevice.zzav) && zzcu.zza(Integer.valueOf(this.zzax), Integer.valueOf(castDevice.zzax)) && zzcu.zza(this.zzay, castDevice.zzay) && zzcu.zza(this.zzaw, castDevice.zzaw) && zzcu.zza(this.zzar, castDevice.getDeviceVersion()) && this.zzas == castDevice.getServicePort() && ((this.zzaz == null && castDevice.zzaz == null) || Arrays.equals(this.zzaz, castDevice.zzaz));
    }

    public String getDeviceId() {
        return this.zzam.startsWith("__cast_nearby__") ? this.zzam.substring(16) : this.zzam;
    }

    public String getDeviceVersion() {
        return this.zzar;
    }

    public String getFriendlyName() {
        return this.zzap;
    }

    public WebImage getIcon(int i, int i2) {
        WebImage webImage = null;
        if (this.zzat.isEmpty()) {
            return null;
        }
        if (i <= 0 || i2 <= 0) {
            return this.zzat.get(0);
        }
        WebImage webImage2 = null;
        for (WebImage next : this.zzat) {
            int width = next.getWidth();
            int height = next.getHeight();
            if (width < i || height < i2) {
                if (width < i && height < i2) {
                    if (webImage2 == null || (webImage2.getWidth() < width && webImage2.getHeight() < height)) {
                        webImage2 = next;
                    }
                }
            } else if (webImage == null || (webImage.getWidth() > width && webImage.getHeight() > height)) {
                webImage = next;
            }
        }
        return webImage != null ? webImage : webImage2 != null ? webImage2 : this.zzat.get(0);
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.zzat);
    }

    public Inet4Address getIpAddress() {
        InetAddress inetAddress = this.zzao;
        if (inetAddress != null && (inetAddress instanceof Inet4Address)) {
            return (Inet4Address) this.zzao;
        }
        return null;
    }

    public String getModelName() {
        return this.zzaq;
    }

    public int getServicePort() {
        return this.zzas;
    }

    public boolean hasCapabilities(int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int hasCapability : iArr) {
            if (!hasCapability(hasCapability)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCapability(int i) {
        return (this.zzau & i) == i;
    }

    public boolean hasIcons() {
        return !this.zzat.isEmpty();
    }

    public int hashCode() {
        String str = this.zzam;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public boolean isOnLocalNetwork() {
        return !this.zzam.startsWith("__cast_nearby__");
    }

    public boolean isSameDevice(CastDevice castDevice) {
        String str;
        String str2;
        if (castDevice == null) {
            return false;
        }
        if (!TextUtils.isEmpty(getDeviceId()) && !getDeviceId().startsWith("__cast_ble__") && !TextUtils.isEmpty(castDevice.getDeviceId()) && !castDevice.getDeviceId().startsWith("__cast_ble__")) {
            str = getDeviceId();
            str2 = castDevice.getDeviceId();
        } else if (TextUtils.isEmpty(this.zzay) || TextUtils.isEmpty(castDevice.zzay)) {
            return false;
        } else {
            str = this.zzay;
            str2 = castDevice.zzay;
        }
        return zzcu.zza(str, str2);
    }

    public void putInBundle(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
        }
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.zzap, this.zzam});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzam, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzan, false);
        SafeParcelWriter.writeString(parcel, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(parcel, 5, getModelName(), false);
        SafeParcelWriter.writeString(parcel, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(parcel, 7, getServicePort());
        SafeParcelWriter.writeTypedList(parcel, 8, getIcons(), false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzau);
        SafeParcelWriter.writeInt(parcel, 10, this.status);
        SafeParcelWriter.writeString(parcel, 11, this.zzav, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzaw, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzax);
        SafeParcelWriter.writeString(parcel, 14, this.zzay, false);
        SafeParcelWriter.writeByteArray(parcel, 15, this.zzaz, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
