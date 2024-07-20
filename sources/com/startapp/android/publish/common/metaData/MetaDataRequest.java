package com.startapp.android.publish.common.metaData;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1261c;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: StartAppSDK */
public class MetaDataRequest extends BaseRequest {
    private String apkHash;
    private int daysSinceFirstSession;
    private long firstInstalledAppTS;
    private Integer ian;
    private float paidAmount;
    private boolean payingUser;
    private String profileId = MetaData.getInstance().getProfileId();
    private C1224a reason;
    private Pair<String, String> simpleToken;
    private int totalSessions;

    /* renamed from: com.startapp.android.publish.common.metaData.MetaDataRequest$a */
    /* compiled from: StartAppSDK */
    public enum C1224a {
        LAUNCH(1),
        APP_IDLE(2),
        IN_APP_PURCHASE(3),
        CUSTOM(4),
        PERIODIC(5),
        PAS(6);
        
        private int index;

        private C1224a(int i) {
            this.index = i;
        }
    }

    public MetaDataRequest(Context context, C1224a aVar) {
        this.totalSessions = C1166k.m1608a(context, "totalSessions", (Integer) 0).intValue();
        this.daysSinceFirstSession = calcDaysSinceFirstSession(context);
        this.paidAmount = C1166k.m1607a(context, "inAppPurchaseAmount", Float.valueOf(0.0f)).floatValue();
        this.payingUser = C1166k.m1606a(context, "payingUser", (Boolean) false).booleanValue();
        this.reason = aVar;
        this.apkHash = calcApkHash(context, C1166k.m1605a(context), C1174m.m1649a().mo14997e(), new C1061i());
        setIan(context);
        this.simpleToken = C1168l.m1637c();
        this.firstInstalledAppTS = C1168l.m1627a();
    }

    private int calcDaysSinceFirstSession(Context context) {
        return millisToDays(System.currentTimeMillis() - C1166k.m1609a(context, "firstSessionTime", Long.valueOf(System.currentTimeMillis())).longValue());
    }

    private int millisToDays(long j) {
        return (int) (j / DateUtils.MILLIS_PER_DAY);
    }

    public int getTotalSessions() {
        return this.totalSessions;
    }

    public void setTotalSessions(int i) {
        this.totalSessions = i;
    }

    public String getApkHash() {
        return this.apkHash;
    }

    public void setApkHash(String str) {
        this.apkHash = str;
    }

    public int getDaysSinceFirstSession() {
        return this.daysSinceFirstSession;
    }

    public void setDaysSinceFirstSession(int i) {
        this.daysSinceFirstSession = i;
    }

    public boolean isPayingUser() {
        return this.payingUser;
    }

    public void setPayingUser(boolean z) {
        this.payingUser = z;
    }

    public float getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(float f) {
        this.paidAmount = f;
    }

    public C1224a getReason() {
        return this.reason;
    }

    public void setReason(C1224a aVar) {
        this.reason = aVar;
    }

    public int getIan() {
        return this.ian.intValue();
    }

    public void setIan(Context context) {
        int f = C1261c.m2045f(context);
        if (f > 0) {
            this.ian = Integer.valueOf(f);
        }
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public String toString() {
        return "MetaDataRequest [totalSessions=" + this.totalSessions + ", daysSinceFirstSession=" + this.daysSinceFirstSession + ", payingUser=" + this.payingUser + ", paidAmount=" + this.paidAmount + ", reason=" + this.reason + ", profileId=" + this.profileId + "]";
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
        eVar.mo14631a(C1253a.m1981a(), (Object) C1253a.m1989d(), true);
        eVar.mo14631a("totalSessions", (Object) Integer.valueOf(this.totalSessions), true);
        eVar.mo14631a("daysSinceFirstSession", (Object) Integer.valueOf(this.daysSinceFirstSession), true);
        eVar.mo14631a("payingUser", (Object) Boolean.valueOf(this.payingUser), true);
        eVar.mo14631a("profileId", (Object) this.profileId, false);
        eVar.mo14631a("paidAmount", (Object) Float.valueOf(this.paidAmount), true);
        eVar.mo14631a("reason", (Object) this.reason, true);
        String str = this.apkHash;
        if (str != null) {
            eVar.mo14631a("apkHash", (Object) str, false);
        }
        eVar.mo14631a("ian", (Object) this.ian, false);
        eVar.mo14631a((String) this.simpleToken.first, this.simpleToken.second, false);
        long j = this.firstInstalledAppTS;
        if (j > 0 && j < Long.MAX_VALUE) {
            eVar.mo14631a("firstInstalledAppTS", (Object) Long.valueOf(j), false);
        }
    }

    public static String calcApkHash(Context context, SharedPreferences sharedPreferences, boolean z, C1061i iVar) {
        String string = sharedPreferences.getString("shared_prefs_app_apk_hash", (String) null);
        if (!TextUtils.isEmpty(string) && !z) {
            return string;
        }
        String a = iVar.mo14638a("SHA-256", context);
        sharedPreferences.edit().putString("shared_prefs_app_apk_hash", a).commit();
        return a;
    }
}
