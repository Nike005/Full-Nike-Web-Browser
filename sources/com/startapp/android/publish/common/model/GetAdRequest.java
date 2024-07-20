package com.startapp.android.publish.common.model;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.util.Pair;
import com.mopub.mobileads.StartAppCustomEventUtils;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.network.ImpressionData;
import com.startapp.android.mediation.admob.StartAppNative;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.SDKAdPreferences;
import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.adsCommon.Utils.C1059g;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p028a.C1069b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1261c;
import java.util.HashSet;
import java.util.Set;

/* compiled from: StartAppSDK */
public class GetAdRequest extends BaseRequest {
    private int adsDisplayed;
    private int adsNumber = 1;
    private String advertiserId = null;
    private String age;

    /* renamed from: ai */
    private Boolean f1419ai;

    /* renamed from: as */
    private Boolean f1420as;
    private Set<String> campaignExclude = null;
    private Set<String> categories = null;
    private Set<String> categoriesExclude = null;
    private boolean contentAd;
    private String country = null;
    private boolean engInclude = true;
    private SDKAdPreferences.Gender gender;
    private Boolean isAutoDateTimeEnabled;
    private boolean isDefaultMetaData = true;
    private boolean isDisableTwoClicks = C1098b.m1303a().mo14750D();
    private boolean isHardwareAccelerated = true;
    private String keywords;
    private Double minCpm;
    private String moreImg;
    private int offset = 0;
    private Set<String> packageExclude = null;
    private Set<String> packageInclude = null;
    private AdPreferences.Placement placement;
    private String primaryImg;
    private String profileId;
    private Pair<String, String> simpleToken;
    private String template;
    private boolean testMode;
    private long timeSinceSessionStart = (System.currentTimeMillis() - C1059g.m1157d().mo14636b());
    private C1040Ad.AdType type = null;

    /* compiled from: StartAppSDK */
    protected enum VideoRequestMode {
        INTERSTITIAL,
        REWARDED
    }

    /* compiled from: StartAppSDK */
    protected enum VideoRequestType {
        ENABLED,
        DISABLED,
        FORCED,
        FORCED_NONVAST
    }

    public GetAdRequest() {
        if (!C1061i.m1193a()) {
            this.adsDisplayed = C1069b.m1217a().mo14658d();
        }
        this.profileId = MetaData.getInstance().getProfileId();
    }

    public AdPreferences.Placement getPlacement() {
        return this.placement;
    }

    public void setPlacement(AdPreferences.Placement placement2) {
        this.placement = placement2;
    }

    public boolean isTestMode() {
        return this.testMode;
    }

    /* access modifiers changed from: protected */
    public boolean isDisableTwoClicks() {
        return this.isDisableTwoClicks;
    }

    public void setDisableTwoClicks(boolean z) {
        this.isDisableTwoClicks = z;
    }

    public void setTestMode(boolean z) {
        this.testMode = z;
    }

    public SDKAdPreferences.Gender getGender() {
        return this.gender;
    }

    public void setGender(SDKAdPreferences.Gender gender2) {
        this.gender = gender2;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String str) {
        this.age = str;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String str) {
        this.keywords = str;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String str) {
        this.template = str;
    }

    public int getAdsNumber() {
        return this.adsNumber;
    }

    public void setAdsNumber(int i) {
        this.adsNumber = i;
    }

    public String getPrimaryImg() {
        return this.primaryImg;
    }

    public void setPrimaryImg(String str) {
        this.primaryImg = str;
    }

    public String getMoreImg() {
        return this.moreImg;
    }

    public void setMoreImg(String str) {
        this.moreImg = str;
    }

    public boolean isContentAd() {
        return this.contentAd;
    }

    public void setContentAd(boolean z) {
        this.contentAd = z;
    }

    public Set<String> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<String> set) {
        this.categories = set;
    }

    public double getMinCpm() {
        return this.minCpm.doubleValue();
    }

    public void setMinCpm(double d) {
        this.minCpm = Double.valueOf(d);
    }

    public void addCategory(String str) {
        if (this.categories == null) {
            this.categories = new HashSet();
        }
        this.categories.add(str);
    }

    public Set<String> getCategoriesExclude() {
        return this.categoriesExclude;
    }

    public void setCategoriesExclude(Set<String> set) {
        this.categoriesExclude = set;
    }

    public void addCategoryExclude(String str) {
        if (this.categoriesExclude == null) {
            this.categoriesExclude = new HashSet();
        }
        this.categoriesExclude.add(str);
    }

    public Set<String> getPackageExclude() {
        return this.packageExclude;
    }

    public void setPackageExclude(Set<String> set) {
        this.packageExclude = set;
    }

    public Set<String> getPackageInclude() {
        return this.packageInclude;
    }

    public void setPackageInclude(Set<String> set) {
        this.packageInclude = set;
    }

    public Set<String> getCampaignExclude() {
        return this.campaignExclude;
    }

    public boolean hasCampaignExclude() {
        Set<String> set = this.campaignExclude;
        return set != null && set.size() > 0;
    }

    public void setCampaignExclude(Set<String> set) {
        this.campaignExclude = set;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public Pair<String, String> getSimpleToken() {
        return this.simpleToken;
    }

    public void setSimpleToken(Pair<String, String> pair) {
        this.simpleToken = pair;
    }

    public boolean isEngInclude() {
        return this.engInclude;
    }

    public void setEngInclude(boolean z) {
        this.engInclude = z;
    }

    public Boolean getAi() {
        return this.f1419ai;
    }

    public void setAi(Boolean bool) {
        this.f1419ai = bool;
    }

    public Boolean getAs() {
        return this.f1420as;
    }

    public void setAs(Boolean bool) {
        this.f1420as = bool;
    }

    public void setRetry(int i) {
        this.retry = Integer.valueOf(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GetAdRequest [");
        sb.append("placement=" + this.placement);
        sb.append(", testMode=" + this.testMode);
        sb.append(", gender=" + this.gender);
        sb.append(", age=" + this.age);
        sb.append(", ai=" + this.f1419ai);
        sb.append(", as=" + this.f1420as);
        sb.append(", keywords=" + this.keywords);
        sb.append(", template=" + this.template);
        sb.append(", adsNumber=" + this.adsNumber);
        sb.append(", offset=" + this.offset);
        sb.append(", categories=" + this.categories);
        sb.append(", categoriesExclude=" + this.categoriesExclude);
        sb.append(", packageExclude=" + this.packageExclude);
        sb.append(", packageInclude=" + this.packageInclude);
        sb.append(", simpleToken=" + this.simpleToken);
        sb.append(", engInclude=" + this.engInclude);
        sb.append(", country=" + this.country);
        sb.append(", advertiserId=" + this.advertiserId);
        sb.append(", type=" + this.type);
        sb.append(", minCpm=" + this.minCpm);
        sb.append(", sessionStartTime=" + this.timeSinceSessionStart);
        sb.append(", adsDisplayed=" + this.adsDisplayed);
        sb.append(", profileId=" + this.profileId);
        sb.append(", hardwareAccelerated=" + this.isHardwareAccelerated);
        sb.append(", primaryImg=" + this.primaryImg);
        sb.append(", moreImg=" + this.moreImg);
        sb.append(", contentAd=" + this.contentAd);
        sb.append(", defaultMetaData=" + this.isDefaultMetaData);
        sb.append("]");
        return sb.toString();
    }

    public void fillAdPreferences(Context context, AdPreferences adPreferences, AdPreferences.Placement placement2, Pair<String, String> pair) {
        this.placement = placement2;
        this.simpleToken = pair;
        this.f1419ai = adPreferences.getAi();
        this.f1420as = adPreferences.getAs();
        this.age = adPreferences.getAge(context);
        this.gender = adPreferences.getGender(context);
        this.keywords = adPreferences.getKeywords();
        this.testMode = adPreferences.isTestMode();
        this.categories = adPreferences.getCategories();
        this.categoriesExclude = adPreferences.getCategoriesExclude();
        this.isHardwareAccelerated = adPreferences.isHardwareAccelerated();
        this.isAutoDateTimeEnabled = Boolean.valueOf(C1261c.m2042c(context));
        this.minCpm = adPreferences.getMinCpm();
        this.isDefaultMetaData = !MetaData.isLoadedFromServer(context);
        fillLocationDetails(adPreferences, context);
        setCountry(adPreferences.country);
        setAdvertiser(adPreferences.advertiserId);
        setTemplate(adPreferences.template);
        setType(adPreferences.type);
        setPackageInclude(adPreferences.packageInclude);
    }

    public boolean isAdTypeVideo() {
        return getType() == C1040Ad.AdType.VIDEO || getType() == C1040Ad.AdType.REWARDED_VIDEO;
    }

    public C1056e getNameValueMap() {
        C1056e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new C1055d();
        }
        addParams(nameValueMap);
        return nameValueMap;
    }

    private void addParams(C1056e eVar) {
        eVar.mo14631a("placement", (Object) this.placement.name(), true);
        eVar.mo14631a("testMode", (Object) Boolean.toString(this.testMode), false);
        eVar.mo14631a("gender", (Object) this.gender, false);
        eVar.mo14631a("age", (Object) this.age, false);
        eVar.mo14631a("keywords", (Object) this.keywords, false);
        eVar.mo14631a("template", (Object) this.template, false);
        eVar.mo14631a("adsNumber", (Object) Integer.toString(this.adsNumber), false);
        eVar.mo14632a(StartAppNative.EXTRAS_CATEGORY, this.categories, false);
        eVar.mo14632a("categoryExclude", this.categoriesExclude, false);
        eVar.mo14632a("packageExclude", this.packageExclude, false);
        eVar.mo14632a("campaignExclude", this.campaignExclude, false);
        eVar.mo14631a(VastIconXmlManager.OFFSET, (Object) Integer.toString(this.offset), false);
        eVar.mo14631a("ai", (Object) this.f1419ai, false);
        eVar.mo14631a("as", (Object) this.f1420as, false);
        eVar.mo14631a("minCPM", (Object) C1061i.m1178a(this.minCpm), false);
        eVar.mo14631a("twoClicks", (Object) Boolean.valueOf(!this.isDisableTwoClicks), false);
        eVar.mo14631a("engInclude", (Object) Boolean.toString(this.engInclude), false);
        if (getType() == C1040Ad.AdType.INTERSTITIAL || getType() == C1040Ad.AdType.RICH_TEXT) {
            eVar.mo14631a("type", (Object) this.type, false);
        }
        eVar.mo14631a("timeSinceSessionStart", (Object) Long.valueOf(this.timeSinceSessionStart), true);
        eVar.mo14631a("adsDisplayed", (Object) Integer.valueOf(this.adsDisplayed), true);
        eVar.mo14631a("profileId", (Object) this.profileId, false);
        eVar.mo14631a("hardwareAccelerated", (Object) Boolean.valueOf(this.isHardwareAccelerated), false);
        eVar.mo14631a("dts", (Object) this.isAutoDateTimeEnabled, false);
        eVar.mo14631a("downloadingMode", (Object) "CACHE", false);
        eVar.mo14631a("primaryImg", (Object) this.primaryImg, false);
        eVar.mo14631a("moreImg", (Object) this.moreImg, false);
        eVar.mo14631a(StartAppCustomEventUtils.SERVER_EXTRA_CONTENT_AD, (Object) Boolean.toString(this.contentAd), false);
        String d = C1253a.m1989d();
        eVar.mo14631a(C1253a.m1981a(), (Object) d, true);
        String c = C1253a.m1987c();
        eVar.mo14627a(c, (Object) C1253a.m1986b(getProductId() + this.placement.name() + getSessionId() + getSdkVersion() + d), true, false);
        if (getCountry() != null) {
            eVar.mo14631a(ImpressionData.COUNTRY, (Object) getCountry(), false);
        }
        if (getAdvertiserId() != null) {
            eVar.mo14631a("advertiserId", (Object) getAdvertiserId(), false);
        }
        if (getPackageInclude() != null) {
            eVar.mo14632a("packageInclude", getPackageInclude(), false);
        }
        eVar.mo14631a("defaultMetaData", (Object) Boolean.valueOf(this.isDefaultMetaData), true);
        eVar.mo14631a((String) this.simpleToken.first, this.simpleToken.second, false);
    }

    /* compiled from: StartAppSDK */
    static class CellScanResult {
        private static final char DELIMITER = ',';
        private NeighboringCellInfo cellInfo;

        public CellScanResult(NeighboringCellInfo neighboringCellInfo) {
            this.cellInfo = neighboringCellInfo;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            NeighboringCellInfo neighboringCellInfo = this.cellInfo;
            if (neighboringCellInfo != null) {
                sb.append(neighboringCellInfo.getCid());
                sb.append(DELIMITER);
                sb.append(this.cellInfo.getRssi());
                sb.append(DELIMITER);
                sb.append(this.cellInfo.getPsc());
                sb.append(DELIMITER);
                sb.append(this.cellInfo.getNetworkType());
                sb.append(DELIMITER);
                sb.append(this.cellInfo.getLac());
            }
            return sb.toString();
        }
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setAdvertiser(String str) {
        this.advertiserId = str;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAdvertiserId() {
        return this.advertiserId;
    }

    public C1040Ad.AdType getType() {
        return this.type;
    }

    public void setType(C1040Ad.AdType adType) {
        this.type = adType;
    }
}
