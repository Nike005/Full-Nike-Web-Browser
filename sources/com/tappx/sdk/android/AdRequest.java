package com.tappx.sdk.android;

public final class AdRequest {

    /* renamed from: a */
    private String f2599a = "native";

    /* renamed from: b */
    private String f2600b = null;

    /* renamed from: c */
    private String f2601c = null;

    /* renamed from: d */
    private int f2602d = -1;

    /* renamed from: e */
    private int f2603e = -1;

    /* renamed from: f */
    private Gender f2604f = Gender.UNKNOWN;

    /* renamed from: g */
    private MaritalStatus f2605g = MaritalStatus.UNKNOWN;

    /* renamed from: h */
    private boolean f2606h;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER,
        UNKNOWN
    }

    public enum MaritalStatus {
        SINGLE,
        LIVING_COMMON,
        MARRIED,
        DIVORCED,
        WIDOWED,
        UNKNOWN
    }

    public AdRequest age(int i) {
        this.f2603e = i;
        return this;
    }

    public AdRequest gender(Gender gender) {
        this.f2604f = gender;
        return this;
    }

    public int getAge() {
        return this.f2603e;
    }

    public Gender getGender() {
        return this.f2604f;
    }

    public String getKeywords() {
        return this.f2601c;
    }

    public MaritalStatus getMaritalStatus() {
        return this.f2605g;
    }

    public String getMediator() {
        return this.f2600b;
    }

    public String getSdkType() {
        return this.f2599a;
    }

    public int getYearOfBirth() {
        return this.f2602d;
    }

    public boolean isUseTestAds() {
        return this.f2606h;
    }

    public AdRequest keywords(String str) {
        this.f2601c = str;
        return this;
    }

    public AdRequest maritalStatus(MaritalStatus maritalStatus) {
        this.f2605g = maritalStatus;
        return this;
    }

    public AdRequest mediator(String str) {
        this.f2600b = str;
        return this;
    }

    public AdRequest sdkType(String str) {
        this.f2599a = str;
        return this;
    }

    public AdRequest useTestAds(boolean z) {
        this.f2606h = z;
        return this;
    }

    public AdRequest yearOfBirth(int i) {
        this.f2602d = i;
        return this;
    }
}
