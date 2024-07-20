package com.appsgeyser.sdk.configuration.models;

import com.google.gson.annotations.SerializedName;

public class AdNetworkSdkModel {
    @SerializedName("app_id")
    private String appId;
    @SerializedName("banner_id")
    private String bannerId;
    @SerializedName("custom_placement_id")
    private String customPlacementId;
    @SerializedName("active")
    private boolean isActive;
    @SerializedName("loading_timeout")
    private int loadingTimeout;
    @SerializedName("placement_id")
    private String placementId;
    @SerializedName("priority")
    private int priority;
    @SerializedName("uniq_id")
    private String uniqueId;

    public AdNetworkSdkModel(boolean z, String str, String str2, String str3, String str4, String str5, int i, int i2) {
        this.isActive = z;
        this.placementId = str;
        this.bannerId = str2;
        this.uniqueId = str3;
        this.appId = str4;
        this.customPlacementId = str5;
        this.priority = i;
        this.loadingTimeout = i2;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean z) {
        this.isActive = z;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public void setPlacementId(String str) {
        this.placementId = str;
    }

    public String getBannerId() {
        return this.bannerId;
    }

    public void setBannerId(String str) {
        this.bannerId = str;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getCustomPlacementId() {
        return this.customPlacementId;
    }

    public void setCustomPlacementId(String str) {
        this.customPlacementId = str;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public int getLoadingTimeout() {
        return this.loadingTimeout;
    }

    public void setLoadingTimeout(int i) {
        this.loadingTimeout = i;
    }
}
