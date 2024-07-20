package com.appnext.banners;

import com.appnext.core.C4947c;
import java.io.Serializable;

public class BannerAdRequest extends C4947c implements Serializable {
    public static final String TYPE_ALL = "all";
    public static final String TYPE_STATIC = "static";
    public static final String TYPE_VIDEO = "video";
    public static final String VIDEO_LENGTH_LONG = "30";
    public static final String VIDEO_LENGTH_SHORT = "15";
    private boolean autoPlay = false;

    /* renamed from: cS */
    private int f4445cS = 0;

    /* renamed from: cT */
    private int f4446cT = 0;
    private String categories = "";
    private boolean clickEnabled = true;
    private String creativeType = TYPE_ALL;
    private boolean mute = true;
    private String postback = "";
    private String videoLength = "15";

    public BannerAdRequest() {
    }

    BannerAdRequest(BannerAdRequest bannerAdRequest) {
        this.categories = bannerAdRequest.categories;
        this.postback = bannerAdRequest.postback;
        this.creativeType = bannerAdRequest.creativeType;
        this.autoPlay = bannerAdRequest.autoPlay;
        this.mute = bannerAdRequest.mute;
        this.videoLength = bannerAdRequest.videoLength;
        this.f4445cS = bannerAdRequest.f4445cS;
        this.f4446cT = bannerAdRequest.f4446cT;
        this.clickEnabled = bannerAdRequest.clickEnabled;
    }

    public BannerAdRequest setCategories(String str) {
        this.categories = str;
        return this;
    }

    public BannerAdRequest setPostback(String str) {
        this.postback = str;
        return this;
    }

    public BannerAdRequest setCreativeType(String str) {
        this.creativeType = str;
        return this;
    }

    public BannerAdRequest setAutoPlay(boolean z) {
        this.autoPlay = z;
        return this;
    }

    public BannerAdRequest setMute(boolean z) {
        this.mute = z;
        return this;
    }

    public BannerAdRequest setVideoLength(String str) {
        this.videoLength = str;
        return this;
    }

    public BannerAdRequest setVidMin(int i) {
        this.f4445cS = i;
        return this;
    }

    public BannerAdRequest setVidMax(int i) {
        this.f4446cT = i;
        return this;
    }

    public BannerAdRequest setClickEnabled(boolean z) {
        this.clickEnabled = z;
        return this;
    }

    public String getCategories() {
        return this.categories;
    }

    public String getPostback() {
        return this.postback;
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean isMute() {
        return this.mute;
    }

    public String getVideoLength() {
        return this.videoLength;
    }

    public int getVidMin() {
        return this.f4445cS;
    }

    public int getVidMax() {
        return this.f4446cT;
    }

    public boolean isClickEnabled() {
        return this.clickEnabled;
    }
}
