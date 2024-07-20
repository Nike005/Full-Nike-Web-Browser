package com.appnext.ads.fullscreen;

import java.io.Serializable;
import java.util.HashMap;

public class RewardedServerSidePostback implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: bo */
    private String f4238bo = "";

    /* renamed from: bp */
    private String f4239bp = "";

    /* renamed from: bq */
    private String f4240bq = "";

    /* renamed from: br */
    private String f4241br = "";

    /* renamed from: bs */
    private String f4242bs = "";

    public RewardedServerSidePostback() {
    }

    public RewardedServerSidePostback(String str, String str2, String str3, String str4, String str5) {
        this.f4238bo = str;
        this.f4239bp = str2;
        this.f4240bq = str3;
        this.f4241br = str4;
        this.f4242bs = str5;
    }

    public String getRewardsTransactionId() {
        return this.f4238bo;
    }

    public void setRewardsTransactionId(String str) {
        this.f4238bo = str;
    }

    public String getRewardsUserId() {
        return this.f4239bp;
    }

    public void setRewardsUserId(String str) {
        this.f4239bp = str;
    }

    public String getRewardsRewardTypeCurrency() {
        return this.f4240bq;
    }

    public void setRewardsRewardTypeCurrency(String str) {
        this.f4240bq = str;
    }

    public String getRewardsAmountRewarded() {
        return this.f4241br;
    }

    public void setRewardsAmountRewarded(String str) {
        this.f4241br = str;
    }

    public String getRewardsCustomParameter() {
        return this.f4242bs;
    }

    public void setRewardsCustomParameter(String str) {
        this.f4242bs = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: p */
    public final HashMap<String, String> mo40548p() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("rewardsTransactionId", this.f4238bo);
        hashMap.put("rewardsUserId", this.f4239bp);
        hashMap.put("rewardsRewardTypeCurrency", this.f4240bq);
        hashMap.put("rewardsAmountRewarded", this.f4241br);
        hashMap.put("rewardsCustomParameter", this.f4242bs);
        return hashMap;
    }
}
