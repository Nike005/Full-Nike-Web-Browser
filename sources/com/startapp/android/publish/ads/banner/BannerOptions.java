package com.startapp.android.publish.ads.banner;

import acr.browser.lightning.activity.BrowserActivity;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
public class BannerOptions implements Serializable {
    private static final long serialVersionUID = 1;
    private int adsNumber = 4;
    private int delayFaceTime = 5000;
    @C5303f(mo45478b = Effect.class)
    private Effect effect = Effect.ROTATE_3D;
    private int height = 50;
    private float heightRatio = 1.0f;
    private int htmlAdsNumber = 10;
    private float minScale = 0.88f;
    private int minViewabilityPercentage = 50;
    private int refreshRate = BrowserActivity.ONE_MINUTE_BY_MILLISECONDS;
    private int refreshRate3D = BrowserActivity.ONE_MINUTE_BY_MILLISECONDS;
    private boolean rotateThroughOnStart = true;
    private int rotateThroughSpeedMult = 2;
    private int scalePower = 4;
    private int stepSize = 5;
    private int timeBetweenFrames = 25;
    private int width = 300;
    private float widthRatio = 1.0f;

    /* compiled from: StartAppSDK */
    public enum Effect {
        ROTATE_3D(1),
        EXCHANGE(2),
        FLY_IN(3);
        
        private int index;

        private Effect(int i) {
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public int getRotationMultiplier() {
            return (int) Math.pow(2.0d, (double) (this.index - 1));
        }

        public static Effect getByIndex(int i) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i2 = 0; i2 < values.length; i2++) {
                if (values[i2].getIndex() == i) {
                    effect = values[i2];
                }
            }
            return effect;
        }

        public static Effect getByName(String str) {
            Effect effect = ROTATE_3D;
            Effect[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    effect = values[i];
                }
            }
            return effect;
        }
    }

    public BannerOptions() {
    }

    public BannerOptions(BannerOptions bannerOptions) {
        this.width = bannerOptions.width;
        this.height = bannerOptions.height;
        this.timeBetweenFrames = bannerOptions.timeBetweenFrames;
        this.stepSize = bannerOptions.stepSize;
        this.delayFaceTime = bannerOptions.delayFaceTime;
        this.adsNumber = bannerOptions.adsNumber;
        this.htmlAdsNumber = bannerOptions.htmlAdsNumber;
        this.refreshRate3D = bannerOptions.refreshRate3D;
        this.widthRatio = bannerOptions.widthRatio;
        this.heightRatio = bannerOptions.heightRatio;
        this.minScale = bannerOptions.minScale;
        this.scalePower = bannerOptions.scalePower;
        this.effect = bannerOptions.effect;
        this.rotateThroughOnStart = bannerOptions.rotateThroughOnStart;
        this.rotateThroughSpeedMult = bannerOptions.rotateThroughSpeedMult;
        this.refreshRate = bannerOptions.refreshRate;
    }

    /* renamed from: a */
    public void mo13878a(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    /* renamed from: a */
    public int mo13877a() {
        return this.timeBetweenFrames;
    }

    /* renamed from: b */
    public int mo13879b() {
        return this.stepSize;
    }

    /* renamed from: c */
    public int mo13880c() {
        return this.delayFaceTime;
    }

    /* renamed from: d */
    public int mo13881d() {
        return this.width;
    }

    /* renamed from: e */
    public int mo13882e() {
        return this.height;
    }

    /* renamed from: f */
    public int mo13884f() {
        return this.adsNumber;
    }

    /* renamed from: g */
    public int mo13885g() {
        return this.htmlAdsNumber;
    }

    /* renamed from: h */
    public int mo13886h() {
        return this.refreshRate3D;
    }

    /* renamed from: i */
    public int mo13887i() {
        return this.refreshRate;
    }

    /* renamed from: j */
    public float mo13888j() {
        return this.widthRatio;
    }

    /* renamed from: k */
    public float mo13889k() {
        return this.heightRatio;
    }

    /* renamed from: l */
    public float mo13890l() {
        return this.minScale;
    }

    /* renamed from: m */
    public int mo13891m() {
        return this.scalePower;
    }

    /* renamed from: n */
    public Effect mo13892n() {
        return this.effect;
    }

    /* renamed from: o */
    public boolean mo13893o() {
        return this.rotateThroughOnStart;
    }

    /* renamed from: p */
    public int mo13894p() {
        return this.rotateThroughSpeedMult;
    }

    /* renamed from: q */
    public int mo13895q() {
        return this.minViewabilityPercentage;
    }

    public boolean equals(Object obj) {
        BannerOptions bannerOptions = (BannerOptions) obj;
        return bannerOptions.mo13884f() == mo13884f() && bannerOptions.mo13885g() == mo13885g() && bannerOptions.mo13886h() == mo13886h() && bannerOptions.mo13880c() == mo13880c() && bannerOptions.mo13882e() == mo13882e() && bannerOptions.mo13879b() == mo13879b() && bannerOptions.mo13877a() == mo13877a() && bannerOptions.mo13881d() == mo13881d() && bannerOptions.mo13887i() == mo13887i();
    }
}
