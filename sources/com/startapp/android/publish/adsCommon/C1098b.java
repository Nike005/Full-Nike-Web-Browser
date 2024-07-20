package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p028a.C1072e;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.MetaDataStyle;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.b */
/* compiled from: StartAppSDK */
public class C1098b implements Serializable {

    /* renamed from: a */
    public static final Integer f1085a = 18;

    /* renamed from: b */
    public static final Integer f1086b = -1;

    /* renamed from: c */
    public static final Set<String> f1087c = new HashSet(Arrays.asList(new String[]{"BOLD"}));

    /* renamed from: d */
    public static final Integer f1088d = -16777216;

    /* renamed from: e */
    public static final Integer f1089e = -14803426;

    /* renamed from: f */
    public static final Integer f1090f = -1;

    /* renamed from: g */
    private static transient Object f1091g = new Object();

    /* renamed from: h */
    private static transient C1098b f1092h = new C1098b();
    private static final long serialVersionUID = 1;
    private String acMetadataUpdateVersion = AdsConstants.f961h;
    @C5303f(mo45477a = true)
    private C1072e adRules = new C1072e();
    private boolean appPresence = true;
    private boolean autoInterstitialEnabled = true;
    private Integer backgroundGradientBottom = -14606047;
    private Integer backgroundGradientTop = -14606047;
    private int defaultActivitiesBetweenAds = 1;
    private int defaultSecondsBetweenAds = 0;
    private boolean disableInAppStore = false;
    private boolean disableReturnAd = false;
    private boolean disableSplashAd = false;
    private boolean disableTwoClicks = false;
    private boolean enableForceExternalBrowser = true;
    private boolean enableSmartRedirect = true;
    private boolean enforceForeground = false;
    private int forceExternalBrowserDaysInterval = 7;
    private Integer fullpageOfferWallProbability = 100;
    private Integer fullpageOverlayProbability = 0;
    private Integer homeProbability3D = 80;
    private Integer itemDescriptionTextColor = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_COLOR;
    @C5303f(mo45478b = HashSet.class)
    private Set<String> itemDescriptionTextDecoration = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_DECORATION;
    private Integer itemDescriptionTextSize = MetaDataStyle.DEFAULT_ITEM_DESC_TEXT_SIZE;
    private Integer itemGradientBottom = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_BOTTOM);
    private Integer itemGradientTop = Integer.valueOf(MetaDataStyle.DEFAULT_ITEM_TOP);
    private Integer itemTitleTextColor = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_COLOR;
    @C5303f(mo45478b = HashSet.class)
    private Set<String> itemTitleTextDecoration = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_DECORATION;
    private Integer itemTitleTextSize = MetaDataStyle.DEFAULT_ITEM_TITLE_TEXT_SIZE;
    private Integer maxAds = 10;
    private Integer poweredByBackgroundColor = f1089e;
    private Integer poweredByTextColor = f1090f;
    private Integer probability3D = 0;
    private long returnAdMinBackgroundTime = 300;
    private long smartRedirectLoadedTimeout = 1000;
    private int smartRedirectTimeout = 5;
    @C5303f(mo45478b = HashMap.class, mo45479c = MetaDataStyle.class)
    private HashMap<String, MetaDataStyle> templates = new HashMap<>();
    private Integer titleBackgroundColor = -14803426;
    private String titleContent = "Recommended for you";
    private Integer titleLineColor = f1088d;
    private Integer titleTextColor = f1086b;
    @C5303f(mo45478b = HashSet.class)
    private Set<String> titleTextDecoration = f1087c;
    private Integer titleTextSize = f1085a;
    @C5303f(mo45477a = true)
    private C1182n video = new C1182n();

    /* renamed from: a */
    public static C1098b m1303a() {
        return f1092h;
    }

    /* renamed from: a */
    public static void m1304a(Context context) {
        C1098b bVar = (C1098b) C1267e.m2057a(context, "StartappAdsMetadata", C1098b.class);
        C1098b bVar2 = new C1098b();
        if (bVar != null) {
            boolean a = C1061i.m1198a(bVar, bVar2);
            if (!bVar.m1301O() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "AdsCommonMetaData", "", "");
            }
            bVar.m1302P();
            f1092h = bVar;
            return;
        }
        f1092h = bVar2;
    }

    /* renamed from: O */
    private boolean m1301O() {
        return !AdsConstants.f961h.equals(this.acMetadataUpdateVersion);
    }

    /* renamed from: P */
    private void m1302P() {
        this.adRules.mo14663b();
    }

    /* renamed from: b */
    public int mo14762b() {
        return this.fullpageOfferWallProbability.intValue();
    }

    /* renamed from: c */
    public int mo14763c() {
        return this.fullpageOverlayProbability.intValue();
    }

    /* renamed from: d */
    public int mo14764d() {
        return this.probability3D.intValue();
    }

    /* renamed from: e */
    public int mo14765e() {
        return this.backgroundGradientTop.intValue();
    }

    /* renamed from: a */
    public MetaDataStyle mo14761a(String str) {
        return this.templates.get(str);
    }

    /* renamed from: f */
    public int mo14766f() {
        return this.backgroundGradientBottom.intValue();
    }

    /* renamed from: g */
    public int mo14767g() {
        return this.maxAds.intValue();
    }

    /* renamed from: h */
    public Integer mo14768h() {
        return this.titleBackgroundColor;
    }

    /* renamed from: i */
    public String mo14769i() {
        return this.titleContent;
    }

    /* renamed from: j */
    public Integer mo14770j() {
        return this.titleTextSize;
    }

    /* renamed from: k */
    public Integer mo14771k() {
        return this.titleTextColor;
    }

    /* renamed from: l */
    public Set<String> mo14772l() {
        return this.titleTextDecoration;
    }

    /* renamed from: m */
    public Integer mo14773m() {
        return this.titleLineColor;
    }

    /* renamed from: n */
    public int mo14774n() {
        return this.itemGradientTop.intValue();
    }

    /* renamed from: o */
    public int mo14775o() {
        return this.itemGradientBottom.intValue();
    }

    /* renamed from: p */
    public Integer mo14776p() {
        return this.itemTitleTextSize;
    }

    /* renamed from: q */
    public Integer mo14777q() {
        return this.itemTitleTextColor;
    }

    /* renamed from: r */
    public Set<String> mo14778r() {
        return this.itemTitleTextDecoration;
    }

    /* renamed from: s */
    public Integer mo14779s() {
        return this.itemDescriptionTextSize;
    }

    /* renamed from: t */
    public Integer mo14780t() {
        return this.itemDescriptionTextColor;
    }

    /* renamed from: u */
    public Set<String> mo14781u() {
        return this.itemDescriptionTextDecoration;
    }

    /* renamed from: v */
    public Integer mo14782v() {
        return this.poweredByBackgroundColor;
    }

    /* renamed from: w */
    public Integer mo14783w() {
        return this.poweredByTextColor;
    }

    /* renamed from: x */
    public long mo14784x() {
        return TimeUnit.SECONDS.toMillis(this.returnAdMinBackgroundTime);
    }

    /* renamed from: y */
    public boolean mo14785y() {
        return this.disableReturnAd;
    }

    /* renamed from: z */
    public boolean mo14786z() {
        return this.disableSplashAd;
    }

    /* renamed from: A */
    public long mo14747A() {
        return TimeUnit.SECONDS.toMillis((long) this.smartRedirectTimeout);
    }

    /* renamed from: B */
    public long mo14748B() {
        return this.smartRedirectLoadedTimeout;
    }

    /* renamed from: C */
    public boolean mo14749C() {
        return this.enableSmartRedirect;
    }

    /* renamed from: D */
    public boolean mo14750D() {
        return this.disableTwoClicks;
    }

    /* renamed from: E */
    public boolean mo14751E() {
        return this.appPresence;
    }

    /* renamed from: F */
    public C1072e mo14752F() {
        return this.adRules;
    }

    /* renamed from: G */
    public boolean mo14753G() {
        return this.disableInAppStore;
    }

    /* renamed from: H */
    public C1182n mo14754H() {
        return this.video;
    }

    /* renamed from: I */
    public boolean mo14755I() {
        return this.autoInterstitialEnabled;
    }

    /* renamed from: J */
    public int mo14756J() {
        return this.defaultActivitiesBetweenAds;
    }

    /* renamed from: K */
    public int mo14757K() {
        return this.defaultSecondsBetweenAds;
    }

    /* renamed from: L */
    public int mo14758L() {
        return this.forceExternalBrowserDaysInterval;
    }

    /* renamed from: M */
    public boolean mo14759M() {
        return this.enableForceExternalBrowser;
    }

    /* renamed from: N */
    public boolean mo14760N() {
        return this.enforceForeground;
    }

    /* renamed from: a */
    public static void m1305a(Context context, C1098b bVar) {
        synchronized (f1091g) {
            bVar.acMetadataUpdateVersion = AdsConstants.f961h;
            f1092h = bVar;
            C1267e.m2061a(context, "StartappAdsMetadata", (Serializable) bVar);
        }
    }
}
