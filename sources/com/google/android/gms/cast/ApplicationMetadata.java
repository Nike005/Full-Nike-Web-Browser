package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzcu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationMetadata extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zzd();
    private String name;
    private List<String> zzaa;
    private String zzab;
    private Uri zzac;
    private String zzy;
    private List<WebImage> zzz;

    private ApplicationMetadata() {
        this.zzz = new ArrayList();
        this.zzaa = new ArrayList();
    }

    ApplicationMetadata(String str, String str2, List<WebImage> list, List<String> list2, String str3, Uri uri) {
        this.zzy = str;
        this.name = str2;
        this.zzz = list;
        this.zzaa = list2;
        this.zzab = str3;
        this.zzac = uri;
    }

    public boolean areNamespacesSupported(List<String> list) {
        List<String> list2 = this.zzaa;
        return list2 != null && list2.containsAll(list);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ApplicationMetadata)) {
            return false;
        }
        ApplicationMetadata applicationMetadata = (ApplicationMetadata) obj;
        return zzcu.zza(this.zzy, applicationMetadata.zzy) && zzcu.zza(this.zzz, applicationMetadata.zzz) && zzcu.zza(this.name, applicationMetadata.name) && zzcu.zza(this.zzaa, applicationMetadata.zzaa) && zzcu.zza(this.zzab, applicationMetadata.zzab) && zzcu.zza(this.zzac, applicationMetadata.zzac);
    }

    public String getApplicationId() {
        return this.zzy;
    }

    public List<WebImage> getImages() {
        return this.zzz;
    }

    public String getName() {
        return this.name;
    }

    public String getSenderAppIdentifier() {
        return this.zzab;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zzaa);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzy, this.name, this.zzz, this.zzaa, this.zzab, this.zzac);
    }

    public boolean isNamespaceSupported(String str) {
        List<String> list = this.zzaa;
        return list != null && list.contains(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("applicationId: ");
        sb.append(this.zzy);
        sb.append(", name: ");
        sb.append(this.name);
        sb.append(", images.count: ");
        List<WebImage> list = this.zzz;
        int i = 0;
        sb.append(list == null ? 0 : list.size());
        sb.append(", namespaces.count: ");
        List<String> list2 = this.zzaa;
        if (list2 != null) {
            i = list2.size();
        }
        sb.append(i);
        sb.append(", senderAppIdentifier: ");
        sb.append(this.zzab);
        sb.append(", senderAppLaunchUrl: ");
        sb.append(this.zzac);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getApplicationId(), false);
        SafeParcelWriter.writeString(parcel, 3, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getImages(), false);
        SafeParcelWriter.writeStringList(parcel, 5, getSupportedNamespaces(), false);
        SafeParcelWriter.writeString(parcel, 6, getSenderAppIdentifier(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzac, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
