package com.appsgeyser.sdk.configuration.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigPhpSdkModel implements Parcelable {
    public static final Parcelable.Creator<ConfigPhpSdkModel> CREATOR = new Parcelable.Creator<ConfigPhpSdkModel>() {
        public ConfigPhpSdkModel createFromParcel(Parcel parcel) {
            return new ConfigPhpSdkModel(parcel);
        }

        public ConfigPhpSdkModel[] newArray(int i) {
            return new ConfigPhpSdkModel[i];
        }
    };
    private boolean active;
    private boolean activeByDefault;

    /* renamed from: id */
    private String f4924id;
    private String tag;
    private String textOfPolicy;

    public int describeContents() {
        return 0;
    }

    public ConfigPhpSdkModel(boolean z, String str, boolean z2, String str2, String str3) {
        this.active = z;
        this.f4924id = str;
        this.activeByDefault = z2;
        this.textOfPolicy = str2;
        this.tag = str3;
    }

    private ConfigPhpSdkModel(Parcel parcel) {
        boolean z = true;
        this.active = parcel.readByte() != 0;
        this.f4924id = parcel.readString();
        this.activeByDefault = parcel.readByte() == 0 ? false : z;
        this.textOfPolicy = parcel.readString();
        this.tag = parcel.readString();
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean z) {
        this.active = z;
    }

    public String getId() {
        return this.f4924id;
    }

    public void setId(String str) {
        this.f4924id = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public boolean isActiveByDefault() {
        return this.activeByDefault;
    }

    public void setActiveByDefault(boolean z) {
        this.activeByDefault = z;
    }

    public String getTextOfPolicy() {
        return this.textOfPolicy;
    }

    public void setTextOfPolicy(String str) {
        this.textOfPolicy = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.active ? (byte) 1 : 0);
        parcel.writeString(this.f4924id);
        parcel.writeByte(this.activeByDefault ? (byte) 1 : 0);
        parcel.writeString(this.textOfPolicy);
        parcel.writeString(this.tag);
    }
}
