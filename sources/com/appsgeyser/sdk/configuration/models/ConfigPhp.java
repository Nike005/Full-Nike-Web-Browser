package com.appsgeyser.sdk.configuration.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Map;

public class ConfigPhp implements Parcelable {
    public static final Parcelable.Creator<ConfigPhp> CREATOR = new Parcelable.Creator<ConfigPhp>() {
        public ConfigPhp createFromParcel(Parcel parcel) {
            return new ConfigPhp(parcel);
        }

        public ConfigPhp[] newArray(int i) {
            return new ConfigPhp[i];
        }
    };
    @SerializedName("about_screen_description")
    private String aboutScreenDescription;
    @SerializedName("about_screen_description_type")
    private String aboutScreenDescriptionType;
    private FastTrackSdkModel activeAdsSDK;
    @SerializedName("additional_js_code")
    private String additionalJsCode;
    private Map<String, AdNetworkSdkModel> adsNetworkSdk;
    @SerializedName("app_ban_active")
    private boolean appBanActive;
    private ConfigPhpSdkModel appsgeyserSdk;
    private int countOfTry;
    private String country;
    @SerializedName("custom_html_about_active")
    private boolean customAboutActive;
    private String eulaBeginning;
    private long fullScreenDelay;
    private int fullscreenBannerCountToShow;
    private Map<String, AdNetworkSdkModel> fullscreenSdk;
    private boolean inAppPurchasesActive;
    @SerializedName("period_days")
    private int inactivityDaysPeriod;
    @SerializedName("turn_on_inactivity_reminder")
    private boolean inactivityReminderEnabled;
    @SerializedName("text_reminder")
    private String inactivityReminderText;
    @SerializedName("enable_about_screen")
    private boolean isAboutScreenEnabled;
    @SerializedName("startup_confirmation_dialog")
    private boolean isAdvertisingTermsDialog;
    private boolean isOnResumeFSEnabled;
    private boolean isOnTouchFSEnabled;
    private boolean isTakeOffFSEnabled;
    private boolean pushNotificationsActive;
    private boolean rateMyAppActive;
    private int rateMyAppSessionsCount;
    private int rateMyAppThreshold;
    private Map<String, AdNetworkSdkModel> rewardedVideoSdk;
    @SerializedName("startup_dialog_allowing_use_if_decline")
    private boolean startupELUAConfirmationDialogAllow;
    private Map<String, String> statUrls;

    public int describeContents() {
        return 0;
    }

    private ConfigPhp(Parcel parcel) {
        boolean z = true;
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = RewardedVideo.VIDEO_MODE_DEFAULT;
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
        this.appsgeyserSdk = (ConfigPhpSdkModel) parcel.readParcelable(ConfigPhpSdkModel.class.getClassLoader());
        this.isAboutScreenEnabled = parcel.readByte() != 0;
        this.isAdvertisingTermsDialog = parcel.readByte() != 0;
        this.country = parcel.readString();
        this.eulaBeginning = parcel.readString();
        this.pushNotificationsActive = parcel.readByte() != 0;
        this.countOfTry = parcel.readInt();
        this.aboutScreenDescriptionType = parcel.readString();
        this.aboutScreenDescription = parcel.readString();
        this.startupELUAConfirmationDialogAllow = parcel.readByte() != 0;
        this.rateMyAppActive = parcel.readByte() != 0;
        this.customAboutActive = parcel.readByte() != 0;
        this.appBanActive = parcel.readByte() != 0;
        this.inAppPurchasesActive = parcel.readByte() != 0;
        this.additionalJsCode = parcel.readString();
        this.inactivityReminderEnabled = parcel.readByte() == 0 ? false : z;
        this.inactivityDaysPeriod = parcel.readInt();
        this.inactivityReminderText = parcel.readString();
    }

    public static ConfigPhp parseFromJson(String str) throws JsonSyntaxException {
        Gson create = new GsonBuilder().setLenient().create();
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        jsonReader.setLenient(true);
        return (ConfigPhp) create.fromJson(jsonReader, (Type) ConfigPhp.class);
    }

    public ConfigPhp(ConfigPhpSdkModel configPhpSdkModel, String str, String str2, boolean z, int i, Map<String, String> map, boolean z2, boolean z3, FastTrackSdkModel fastTrackSdkModel, String str3, String str4, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String str5, boolean z9, int i2, String str6) {
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = RewardedVideo.VIDEO_MODE_DEFAULT;
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
        this.appsgeyserSdk = configPhpSdkModel;
        this.country = str;
        this.eulaBeginning = str2;
        this.pushNotificationsActive = z;
        this.countOfTry = i;
        this.statUrls = map;
        this.isAboutScreenEnabled = z2;
        this.isAdvertisingTermsDialog = z3;
        this.activeAdsSDK = fastTrackSdkModel;
        this.aboutScreenDescription = str3;
        this.aboutScreenDescriptionType = str4;
        this.startupELUAConfirmationDialogAllow = z4;
        this.rateMyAppActive = z5;
        this.customAboutActive = z6;
        this.appBanActive = z7;
        this.inAppPurchasesActive = z8;
        this.additionalJsCode = str5;
        this.inactivityReminderEnabled = z9;
        this.inactivityDaysPeriod = i2;
        this.inactivityReminderText = str6;
    }

    public ConfigPhp() {
        this.isAboutScreenEnabled = true;
        this.aboutScreenDescriptionType = RewardedVideo.VIDEO_MODE_DEFAULT;
        this.startupELUAConfirmationDialogAllow = true;
        this.isTakeOffFSEnabled = false;
        this.isOnResumeFSEnabled = false;
        this.isOnTouchFSEnabled = true;
        this.fullScreenDelay = -1;
        this.fullscreenBannerCountToShow = 1;
    }

    public ConfigPhpSdkModel getAppsgeyserSdk() {
        return this.appsgeyserSdk;
    }

    public String getAboutScreenDescriptionType() {
        return this.aboutScreenDescriptionType;
    }

    public String getAboutScreenDescription() {
        return this.aboutScreenDescription;
    }

    public boolean getStartupELUAConfirmationDialogAllow() {
        return this.startupELUAConfirmationDialogAllow;
    }

    public void setAppsgeyserSdk(ConfigPhpSdkModel configPhpSdkModel) {
        this.appsgeyserSdk = configPhpSdkModel;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getEulaBeginning() {
        return this.eulaBeginning;
    }

    public void setEulaBeginning(String str) {
        this.eulaBeginning = str;
    }

    public int getCountOfTry() {
        return this.countOfTry;
    }

    public void setCountOfTry(int i) {
        this.countOfTry = i;
    }

    public Map<String, String> getStatUrls() {
        return this.statUrls;
    }

    public void setStatUrls(Map<String, String> map) {
        this.statUrls = map;
    }

    public boolean isAboutScreenEnabled() {
        return this.isAboutScreenEnabled;
    }

    public void setAboutScreenEnabled(boolean z) {
        this.isAboutScreenEnabled = z;
    }

    public boolean isPushNotificationsActive() {
        return this.pushNotificationsActive;
    }

    public void setPushNotificationsActive(boolean z) {
        this.pushNotificationsActive = z;
    }

    public boolean isAdvertisingTermsDialog() {
        return this.isAdvertisingTermsDialog;
    }

    public void setAdvertisingTermsDialog(boolean z) {
        this.isAdvertisingTermsDialog = z;
    }

    public boolean isInAppPurchasesActive() {
        return this.inAppPurchasesActive;
    }

    public void setInAppPurchasesActive(boolean z) {
        this.inAppPurchasesActive = z;
    }

    public boolean isAppBanActive() {
        return this.appBanActive;
    }

    public void setAppBanActive(boolean z) {
        this.appBanActive = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.appsgeyserSdk, i);
        parcel.writeByte(this.isAboutScreenEnabled ? (byte) 1 : 0);
        parcel.writeByte(this.isAdvertisingTermsDialog ? (byte) 1 : 0);
        parcel.writeString(this.country);
        parcel.writeString(this.eulaBeginning);
        parcel.writeByte(this.pushNotificationsActive ? (byte) 1 : 0);
        parcel.writeInt(this.countOfTry);
        parcel.writeString(this.aboutScreenDescriptionType);
        parcel.writeString(this.aboutScreenDescription);
        parcel.writeByte(this.startupELUAConfirmationDialogAllow ? (byte) 1 : 0);
        parcel.writeByte(this.rateMyAppActive ? (byte) 1 : 0);
        parcel.writeByte(this.customAboutActive ? (byte) 1 : 0);
        parcel.writeByte(this.appBanActive ? (byte) 1 : 0);
        parcel.writeByte(this.inAppPurchasesActive ? (byte) 1 : 0);
        parcel.writeString(this.additionalJsCode);
        parcel.writeByte(this.inactivityReminderEnabled ? (byte) 1 : 0);
        parcel.writeInt(this.inactivityDaysPeriod);
        parcel.writeString(this.inactivityReminderText);
    }

    public Map<String, AdNetworkSdkModel> getAdsNetworkSdk() {
        return this.adsNetworkSdk;
    }

    public void setAdsNetworkSdk(Map<String, AdNetworkSdkModel> map) {
        this.adsNetworkSdk = map;
    }

    public boolean isOfferWallEnabled() {
        Map<String, AdNetworkSdkModel> map = this.adsNetworkSdk;
        if (map != null && map.size() > 0) {
            for (AdNetworkSdkModel isActive : this.adsNetworkSdk.values()) {
                if (isActive.isActive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isRewardedVideoEnabled() {
        Map<String, AdNetworkSdkModel> map = this.rewardedVideoSdk;
        if (map != null && map.size() > 0) {
            for (AdNetworkSdkModel isActive : this.rewardedVideoSdk.values()) {
                if (isActive.isActive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Map<String, AdNetworkSdkModel> getRewardedVideoSdk() {
        return this.rewardedVideoSdk;
    }

    public void setRewardedVideoSdk(Map<String, AdNetworkSdkModel> map) {
        this.rewardedVideoSdk = map;
    }

    public long getFullScreenDelay() {
        return this.fullScreenDelay;
    }

    public void setFullScreenDelay(long j) {
        this.fullScreenDelay = j;
    }

    public Map<String, AdNetworkSdkModel> getFullscreenSdk() {
        return this.fullscreenSdk;
    }

    public boolean isTakeOffFSEnabled() {
        return this.isTakeOffFSEnabled;
    }

    public boolean isOnResumeFSEnabled() {
        return this.isOnResumeFSEnabled;
    }

    public boolean isOnTouchFSEnabled() {
        return this.isOnTouchFSEnabled;
    }

    public int getFullscreenBannerCountToShow() {
        return this.fullscreenBannerCountToShow;
    }

    public void setFullscreenBannerCountToShow(int i) {
        this.fullscreenBannerCountToShow = i;
    }

    public FastTrackSdkModel getActiveAdsSDK() {
        return this.activeAdsSDK;
    }

    public boolean isRateMyAppActive() {
        return this.rateMyAppActive;
    }

    public void setRateMyAppActive(boolean z) {
        this.rateMyAppActive = z;
    }

    public int getRateMyAppSessionsCount() {
        return this.rateMyAppSessionsCount;
    }

    public void setRateMyAppSessionsCount(int i) {
        this.rateMyAppSessionsCount = i;
    }

    public int getRateMyAppThreshold() {
        return this.rateMyAppThreshold;
    }

    public void setRateMyAppThreshold(int i) {
        this.rateMyAppThreshold = i;
    }

    public boolean isCustomAboutActive() {
        return this.customAboutActive;
    }

    public void setCustomAboutActive(boolean z) {
        this.customAboutActive = z;
    }

    public String getAdditionalJsCode() {
        return this.additionalJsCode;
    }

    public void setAdditionalJsCode(String str) {
        this.additionalJsCode = str;
    }

    public boolean isInactivityReminderEnabled() {
        return this.inactivityReminderEnabled;
    }

    public void setInactivityReminderEnabled(boolean z) {
        this.inactivityReminderEnabled = z;
    }

    public int getInactivityDaysPeriod() {
        int i = this.inactivityDaysPeriod;
        if (i != 0) {
            return i;
        }
        return 1;
    }

    public void setInactivityDaysPeriod(int i) {
        this.inactivityDaysPeriod = i;
    }

    public String getInactivityReminderText() {
        return this.inactivityReminderText;
    }

    public void setInactivityReminderText(String str) {
        this.inactivityReminderText = str;
    }
}
