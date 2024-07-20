package com.appsgeyser.sdk.ads.fastTrack;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class FastTrackSdkModel {
    @SerializedName("additional_reporting_params")
    private Map<String, String> additionalReportingParams;
    @SerializedName("app_id")
    private String appId;
    @SerializedName("banner_placement_id")
    private String bannerPlacementId;
    @SerializedName("banner_placements_refresh_timer_map")
    private Map<String, Integer> bannerPlacementsRefreshTimerMap;
    @SerializedName("content_rating")
    private String contentRating;
    @SerializedName("custom_banner_activated")
    private boolean customBannerActivated;
    @SerializedName("custom_fullscreen_activated")
    private boolean customFullscreenActivated;
    @SerializedName("custom_rewarded_activated")
    private boolean customRewardedActivated;
    @SerializedName("banner_refresh_timer")
    private Integer defaultBannerRefreshTimer;
    @SerializedName("fullscreen_intensity")
    private Integer defaultFullscreenIntensity;
    @SerializedName("fullscreen_frequency_timer")
    private Integer fullscreenFrequencyTimer;
    @SerializedName("fullscreen_pending_delay_timer")
    private Integer fullscreenPendingDelayTimer;
    @SerializedName("fullscreen_placement_id")
    private String fullscreenPlacementId;
    @SerializedName("fullscreen_placements_intensity_map")
    private Map<String, Integer> fullscreenPlacementsIntensityMap;
    @SerializedName("name")
    private String name;
    @SerializedName("native_ads_placement_id")
    private String nativeAdsPlacementId;
    @SerializedName("native_placements_activation_map")
    private Map<String, Boolean> nativePlacementsActivationMap;
    @SerializedName("pub_ids")
    private String[] publisherIds;
    @SerializedName("rewarded_placements_activation_map")
    private Map<String, Boolean> rewardedPlacementsActivationMap;
    @SerializedName("rewarded_video_placement_id")
    private String rewardedVideoPlacementId;
    @SerializedName("startapp_id")
    private String startAppId;
    @SerializedName("token")
    private String token;

    public FastTrackSdkModel(String str, String str2, String[] strArr, String str3, String str4, Integer num, Map<String, Integer> map, Integer num2, Integer num3, String str5, Integer num4, Map<String, Integer> map2, String str6, Map<String, Boolean> map3, String str7, Map<String, Boolean> map4, boolean z, boolean z2, boolean z3, Map<String, String> map5, String str8, String str9) {
        this.name = str;
        this.appId = str2;
        this.publisherIds = strArr;
        this.token = str3;
        this.fullscreenPlacementId = str4;
        this.defaultFullscreenIntensity = num;
        this.fullscreenPlacementsIntensityMap = map;
        this.fullscreenFrequencyTimer = num2;
        this.fullscreenPendingDelayTimer = num3;
        this.bannerPlacementId = str5;
        this.defaultBannerRefreshTimer = num4;
        this.bannerPlacementsRefreshTimerMap = map2;
        this.rewardedVideoPlacementId = str6;
        this.rewardedPlacementsActivationMap = map3;
        this.nativeAdsPlacementId = str7;
        this.nativePlacementsActivationMap = map4;
        this.customBannerActivated = z;
        this.customFullscreenActivated = z2;
        this.customRewardedActivated = z3;
        this.additionalReportingParams = map5;
        this.startAppId = str8;
        this.contentRating = str9;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String[] getPublisherIds() {
        return this.publisherIds;
    }

    public void setPublisherIds(String[] strArr) {
        this.publisherIds = strArr;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getFullscreenPlacementId() {
        return this.fullscreenPlacementId;
    }

    public void setFullscreenPlacementId(String str) {
        this.fullscreenPlacementId = str;
    }

    public Integer getDefaultFullscreenIntensity() {
        return this.defaultFullscreenIntensity;
    }

    public void setDefaultFullscreenIntensity(Integer num) {
        this.defaultFullscreenIntensity = num;
    }

    public String getBannerPlacementId() {
        return this.bannerPlacementId;
    }

    public void setBannerPlacementId(String str) {
        this.bannerPlacementId = str;
    }

    public Integer getDefaultBannerRefreshTimer() {
        return this.defaultBannerRefreshTimer;
    }

    public void setDefaultBannerRefreshTimer(Integer num) {
        this.defaultBannerRefreshTimer = num;
    }

    public String getRewardedVideoPlacementId() {
        return this.rewardedVideoPlacementId;
    }

    public void setRewardedVideoPlacementId(String str) {
        this.rewardedVideoPlacementId = str;
    }

    public boolean isCustomBannerActivated() {
        return this.customBannerActivated;
    }

    public void setCustomBannerActivated(boolean z) {
        this.customBannerActivated = z;
    }

    public boolean isCustomFullscreenActivated() {
        return this.customFullscreenActivated;
    }

    public void setCustomFullscreenActivated(boolean z) {
        this.customFullscreenActivated = z;
    }

    public boolean isCustomRewardedActivated() {
        return this.customRewardedActivated;
    }

    public void setCustomRewardedActivated(boolean z) {
        this.customRewardedActivated = z;
    }

    public Map<String, String> getAdditionalReportingParams() {
        return this.additionalReportingParams;
    }

    public void setAdditionalReportingParams(Map<String, String> map) {
        this.additionalReportingParams = map;
    }

    public Map<String, Integer> getFullscreenPlacementsIntensityMap() {
        return this.fullscreenPlacementsIntensityMap;
    }

    public void setFullscreenPlacementsIntensityMap(Map<String, Integer> map) {
        this.fullscreenPlacementsIntensityMap = map;
    }

    public Integer getFullscreenFrequencyTimer() {
        return this.fullscreenFrequencyTimer;
    }

    public void setFullscreenFrequencyTimer(Integer num) {
        this.fullscreenFrequencyTimer = num;
    }

    public Integer getFullscreenPendingDelayTimer() {
        return this.fullscreenPendingDelayTimer;
    }

    public void setFullscreenPendingDelayTimer(Integer num) {
        this.fullscreenPendingDelayTimer = num;
    }

    public Map<String, Integer> getBannerPlacementsRefreshTimerMap() {
        return this.bannerPlacementsRefreshTimerMap;
    }

    public void setBannerPlacementsRefreshTimerMap(Map<String, Integer> map) {
        this.bannerPlacementsRefreshTimerMap = map;
    }

    public Map<String, Boolean> getRewardedPlacementsActivationMap() {
        return this.rewardedPlacementsActivationMap;
    }

    public void setRewardedPlacementsActivationMap(Map<String, Boolean> map) {
        this.rewardedPlacementsActivationMap = map;
    }

    public String getNativeAdsPlacementId() {
        return this.nativeAdsPlacementId;
    }

    public void setNativeAdsPlacementId(String str) {
        this.nativeAdsPlacementId = str;
    }

    public Map<String, Boolean> getNativePlacementsActivationMap() {
        return this.nativePlacementsActivationMap;
    }

    public void setNativePlacementsActivationMap(Map<String, Boolean> map) {
        this.nativePlacementsActivationMap = map;
    }

    public String getStartAppId() {
        return this.startAppId;
    }

    public void setStartAppId(String str) {
        this.startAppId = str;
    }

    public String getContentRating() {
        return this.contentRating;
    }

    public void setContentRating(String str) {
        this.contentRating = str;
    }
}
