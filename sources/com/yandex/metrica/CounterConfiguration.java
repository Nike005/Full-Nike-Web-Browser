package com.yandex.metrica;

import android.content.ContentValues;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.yandex.metrica.impl.C1880bc;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C2241y;
import com.yandex.metrica.impl.utils.C2223g;
import java.util.List;
import java.util.Map;

public class CounterConfiguration implements Parcelable {
    public static final Parcelable.Creator<CounterConfiguration> CREATOR = new Parcelable.Creator<CounterConfiguration>() {
        public /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CounterConfiguration[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CounterConfiguration(parcel);
        }
    };

    /* renamed from: a */
    private ContentValues f2716a;

    /* renamed from: b */
    private ResultReceiver f2717b = null;

    public int describeContents() {
        return 0;
    }

    /* renamed from: com.yandex.metrica.CounterConfiguration$a */
    public enum C1774a {
        UNDEFINED(-1),
        FALSE(0),
        TRUE(1);
        

        /* renamed from: d */
        public final int f2723d;

        private C1774a(int i) {
            this.f2723d = i;
        }

        /* renamed from: a */
        public static C1774a m3973a(int i) {
            if (i == -1) {
                return UNDEFINED;
            }
            if (i == 0) {
                return FALSE;
            }
            if (i != 1) {
                return UNDEFINED;
            }
            return TRUE;
        }
    }

    public CounterConfiguration(CounterConfiguration counterConfiguration) {
        ContentValues contentValues = new ContentValues();
        this.f2716a = contentValues;
        contentValues.putAll(counterConfiguration.f2716a);
        this.f2717b = counterConfiguration.f2717b;
    }

    public CounterConfiguration() {
        ContentValues contentValues = new ContentValues();
        this.f2716a = contentValues;
        contentValues.put("CFG_DISPATCH_PERIOD", 90);
        this.f2716a.put("CFG_MAX_REPORTS_COUNT", 7);
        this.f2716a.put("CFG_SESSION_TIMEOUT", 10);
        this.f2716a.put("CFG_REPORTS", true);
        this.f2716a.put("CFG_REPORTS_CRASHES", true);
        this.f2716a.put("CFG_REPORTS_NATIVE_CRASHES", true);
        this.f2716a.put("CFG_REPORT_LOCATION", true);
        this.f2716a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(C1774a.FALSE.f2723d));
        this.f2716a.putNull("CFG_HOST_URL");
        this.f2716a.putNull("CFG_CUSTOM_HOSTS");
        this.f2716a.putNull("CFG_MANUAL_LOCATION");
        this.f2716a.putNull("CFG_APP_VERSION");
        this.f2716a.putNull("CFG_APP_VERSION_CODE");
        this.f2716a.putNull("CFG_API_KEY");
        this.f2716a.putNull("CFG_PACKAGE_NAME");
        this.f2716a.putNull("CFG_UUID");
        this.f2716a.putNull("CFG_DEVICE_ID");
        this.f2716a.putNull("CFG_DEVICE_SIZE_TYPE");
        this.f2716a.putNull("CFG_CLIDS");
        this.f2716a.putNull("CFG_IS_FIRST_ACTIVATION_AS_UPDATE");
        this.f2716a.put("CFG_MAIN_REPORTER", true);
        this.f2716a.put("CFG_IS_LOG_ENABLED", false);
        this.f2716a.put("CFG_APP_FRAMEWORK", C1880bc.m4540c());
    }

    /* renamed from: a */
    public void mo16566a(C1797e eVar) {
        boolean z = false;
        if (eVar.getSessionTimeout() != null) {
            mo16576c(eVar.getSessionTimeout().intValue());
        }
        if (eVar.getLocation() != null) {
            mo16560a(eVar.getLocation());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            mo16578c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            mo16582d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            mo16570a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            mo16574b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.mo16703e() != null) {
            mo16565a(eVar.mo16703e());
        }
        if (eVar.mo16707i() != null) {
            mo16559a(eVar.mo16707i().intValue());
        }
        if (eVar.mo16706h() != null) {
            mo16572b(eVar.mo16706h().intValue());
        }
        if (!C1894bi.m4622a(eVar.getAppVersion())) {
            mo16591g(eVar.getAppVersion());
        }
        if (eVar.mo16702d() != null) {
            mo16580d(eVar.mo16702d().intValue());
        }
        if (eVar.mo16701c() != null) {
            mo16568a(eVar.mo16701c());
        }
        if (eVar.mo16709k() != null) {
            mo16589f(eVar.mo16709k().booleanValue());
        }
        if (eVar.mo16710l() != null) {
            mo16592g(eVar.mo16710l().booleanValue());
        }
        if (eVar.mo16704f() != null) {
            mo16569a(eVar.mo16704f());
        }
        if (eVar.mo16705g() != null) {
            z = true;
        }
        if (z) {
            mo16594h(eVar.mo16705g());
        }
        if (eVar.isFirstActivationAsUpdate()) {
            this.f2716a.put("CFG_IS_FIRST_ACTIVATION_AS_UPDATE", true);
        }
    }

    /* renamed from: a */
    public void mo16563a(ResultReceiver resultReceiver) {
        this.f2717b = resultReceiver;
    }

    /* renamed from: a */
    public ResultReceiver mo16558a() {
        return this.f2717b;
    }

    /* renamed from: a */
    public void mo16559a(int i) {
        this.f2716a.put("CFG_DISPATCH_PERIOD", Integer.valueOf(i));
    }

    /* renamed from: b */
    public int mo16571b() {
        return this.f2716a.getAsInteger("CFG_DISPATCH_PERIOD").intValue();
    }

    /* renamed from: b */
    public void mo16572b(int i) {
        ContentValues contentValues = this.f2716a;
        if (i <= 0) {
            i = Integer.MAX_VALUE;
        }
        contentValues.put("CFG_MAX_REPORTS_COUNT", Integer.valueOf(i));
    }

    /* renamed from: c */
    public int mo16575c() {
        return this.f2716a.getAsInteger("CFG_MAX_REPORTS_COUNT").intValue();
    }

    /* renamed from: c */
    public void mo16576c(int i) {
        this.f2716a.put("CFG_SESSION_TIMEOUT", Integer.valueOf(Math.max(10, i)));
    }

    /* renamed from: d */
    public int mo16579d() {
        return this.f2716a.getAsInteger("CFG_SESSION_TIMEOUT").intValue();
    }

    /* renamed from: a */
    public void mo16565a(C1779a aVar) {
        this.f2716a.put("CFG_DEVICE_SIZE_TYPE", aVar == null ? null : aVar.mo16673a());
    }

    /* renamed from: e */
    public C1779a mo16584e() {
        return C1779a.m3992a(this.f2716a.getAsString("CFG_DEVICE_SIZE_TYPE"));
    }

    /* renamed from: a */
    public void mo16567a(String str) {
        C1897bk.m4647a(str);
        this.f2716a.put("CFG_API_KEY", str);
    }

    /* renamed from: b */
    public void mo16573b(String str) {
        this.f2716a.put("CFG_API_KEY", str);
    }

    /* renamed from: c */
    public void mo16577c(String str) {
        this.f2716a.put("CFG_PACKAGE_NAME", str);
    }

    /* renamed from: f */
    public String mo16587f() {
        return this.f2716a.getAsString("CFG_PACKAGE_NAME");
    }

    /* renamed from: d */
    public void mo16581d(String str) {
        this.f2716a.put("CFG_UUID", str);
    }

    /* renamed from: g */
    public String mo16590g() {
        return this.f2716a.getAsString("CFG_UUID");
    }

    /* renamed from: e */
    public void mo16585e(String str) {
        this.f2716a.put("CFG_DEVICE_ID", str);
    }

    /* renamed from: h */
    public String mo16593h() {
        return this.f2716a.getAsString("CFG_DEVICE_ID");
    }

    /* renamed from: f */
    public void mo16588f(String str) {
        this.f2716a.put("CFG_POSSIBLE_DEVICE_ID", str);
    }

    /* renamed from: i */
    public String mo16596i() {
        return this.f2716a.getAsString("CFG_POSSIBLE_DEVICE_ID");
    }

    /* renamed from: j */
    public String mo16597j() {
        return this.f2716a.getAsString("CFG_API_KEY");
    }

    /* renamed from: a */
    public void mo16570a(boolean z) {
        this.f2716a.put("CFG_REPORTS_CRASHES", Boolean.valueOf(z));
    }

    /* renamed from: k */
    public boolean mo16598k() {
        return this.f2716a.getAsBoolean("CFG_REPORTS_CRASHES").booleanValue();
    }

    /* renamed from: b */
    public void mo16574b(boolean z) {
        this.f2716a.put("CFG_REPORTS_NATIVE_CRASHES", Boolean.valueOf(z));
    }

    /* renamed from: l */
    public boolean mo16599l() {
        return this.f2716a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES").booleanValue();
    }

    /* renamed from: c */
    public void mo16578c(boolean z) {
        this.f2716a.put("CFG_REPORT_LOCATION", Boolean.valueOf(z));
    }

    /* renamed from: m */
    public boolean mo16600m() {
        return this.f2716a.getAsBoolean("CFG_REPORT_LOCATION").booleanValue();
    }

    /* renamed from: a */
    public void mo16568a(List<String> list) {
        this.f2716a.put("CFG_CUSTOM_HOSTS", C2223g.m5947a(list));
    }

    /* renamed from: n */
    public List<String> mo16601n() {
        String asString = this.f2716a.getAsString("CFG_CUSTOM_HOSTS");
        if (TextUtils.isEmpty(asString)) {
            return null;
        }
        return C2223g.m5951b(asString);
    }

    /* renamed from: g */
    public void mo16591g(String str) {
        this.f2716a.put("CFG_APP_VERSION", str);
    }

    /* renamed from: o */
    public String mo16602o() {
        return this.f2716a.getAsString("CFG_APP_VERSION");
    }

    /* renamed from: d */
    public void mo16580d(int i) {
        this.f2716a.put("CFG_APP_VERSION_CODE", String.valueOf(i));
    }

    /* renamed from: p */
    public String mo16603p() {
        return this.f2716a.getAsString("CFG_APP_VERSION_CODE");
    }

    /* renamed from: d */
    public void mo16582d(boolean z) {
        this.f2716a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf((z ? C1774a.TRUE : C1774a.FALSE).f2723d));
    }

    /* renamed from: com.yandex.metrica.CounterConfiguration$2 */
    static /* synthetic */ class C17732 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2718a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.yandex.metrica.CounterConfiguration$a[] r0 = com.yandex.metrica.CounterConfiguration.C1774a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2718a = r0
                com.yandex.metrica.CounterConfiguration$a r1 = com.yandex.metrica.CounterConfiguration.C1774a.TRUE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2718a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.CounterConfiguration$a r1 = com.yandex.metrica.CounterConfiguration.C1774a.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2718a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.CounterConfiguration$a r1 = com.yandex.metrica.CounterConfiguration.C1774a.FALSE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.CounterConfiguration.C17732.<clinit>():void");
        }
    }

    /* renamed from: q */
    public boolean mo16604q() {
        return C17732.f2718a[mo16605r().ordinal()] == 1;
    }

    /* renamed from: r */
    public C1774a mo16605r() {
        return m3910a(this.f2716a.get("CFG_COLLECT_INSTALLED_APPS"));
    }

    /* renamed from: a */
    public void mo16560a(Location location) {
        this.f2716a.put("CFG_MANUAL_LOCATION", C2241y.m5988b(location));
    }

    /* renamed from: e */
    public void mo16586e(boolean z) {
        this.f2716a.put("CFG_IS_LOG_ENABLED", Boolean.valueOf(z));
    }

    /* renamed from: s */
    public boolean mo16606s() {
        if (this.f2716a.containsKey("CFG_IS_LOG_ENABLED")) {
            return this.f2716a.getAsBoolean("CFG_IS_LOG_ENABLED").booleanValue();
        }
        return false;
    }

    /* renamed from: t */
    public Location mo16607t() {
        Location a = C2241y.m5984a(this.f2716a.getAsByteArray("CFG_MANUAL_LOCATION"));
        if (a != null || !mo16553B()) {
            return a;
        }
        Double z = mo16614z();
        Double A = mo16552A();
        Location location = new Location("NONE");
        location.setLatitude(z.doubleValue());
        location.setLongitude(A.doubleValue());
        location.setTime(System.currentTimeMillis());
        return location;
    }

    /* renamed from: a */
    public void mo16569a(Map<String, String> map) {
        this.f2716a.put("CFG_CLIDS", C2223g.m5948a((Map) map));
    }

    /* renamed from: u */
    public Map<String, String> mo16608u() {
        return C2223g.m5949a(this.f2716a.getAsString("CFG_CLIDS"));
    }

    /* renamed from: v */
    public String mo16609v() {
        return this.f2716a.getAsString("CFG_DISTRIBUTION_REFERRER");
    }

    /* renamed from: h */
    public void mo16594h(String str) {
        this.f2716a.put("CFG_DISTRIBUTION_REFERRER", str);
    }

    /* renamed from: w */
    public boolean mo16610w() {
        Boolean asBoolean = this.f2716a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION");
        if (asBoolean != null) {
            return asBoolean.booleanValue();
        }
        return false;
    }

    /* renamed from: x */
    public boolean mo16612x() {
        Boolean asBoolean = this.f2716a.getAsBoolean("CFG_PERMISSIONS_COLLECTING");
        if (asBoolean == null) {
            return true;
        }
        return asBoolean.booleanValue();
    }

    /* renamed from: f */
    public void mo16589f(boolean z) {
        this.f2716a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", Boolean.valueOf(z));
    }

    /* renamed from: g */
    public void mo16592g(boolean z) {
        this.f2716a.put("CFG_PERMISSIONS_COLLECTING", Boolean.valueOf(z));
    }

    /* renamed from: y */
    public boolean mo16613y() {
        if (this.f2716a.get("CFG_IS_FIRST_ACTIVATION_AS_UPDATE") != null) {
            return this.f2716a.getAsBoolean("CFG_IS_FIRST_ACTIVATION_AS_UPDATE").booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: z */
    public Double mo16614z() {
        return this.f2716a.getAsDouble("CFG_LOCATION_LATITUDE");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: A */
    public Double mo16552A() {
        return this.f2716a.getAsDouble("CFG_LOCATION_LONGITUDE");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: B */
    public boolean mo16553B() {
        boolean z = true;
        boolean z2 = this.f2716a.getAsDouble("CFG_LOCATION_LONGITUDE") != null;
        if (this.f2716a.getAsDouble("CFG_LOCATION_LATITUDE") == null) {
            z = false;
        }
        return z2 & z;
    }

    public CounterConfiguration(Parcel parcel) {
        mo16562a(parcel);
    }

    /* renamed from: a */
    public void mo16562a(Parcel parcel) {
        this.f2716a = (ContentValues) parcel.readParcelable(ContentValues.class.getClass().getClassLoader());
        this.f2717b = (ResultReceiver) parcel.readParcelable(ResultReceiver.class.getClass().getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2716a, 0);
        ResultReceiver resultReceiver = this.f2717b;
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        parcel.writeParcelable((ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain), 0);
    }

    /* renamed from: h */
    public void mo16595h(boolean z) {
        this.f2716a.put("CFG_MAIN_REPORTER", Boolean.valueOf(z));
    }

    /* renamed from: C */
    public boolean mo16554C() {
        Boolean asBoolean = this.f2716a.getAsBoolean("CFG_MAIN_REPORTER");
        if (asBoolean != null) {
            return asBoolean.booleanValue();
        }
        return true;
    }

    /* renamed from: D */
    public boolean mo16555D() {
        return C1897bk.m4657b(mo16597j());
    }

    /* renamed from: E */
    public String mo16556E() {
        return this.f2716a.getAsString("CFG_APP_FRAMEWORK");
    }

    /* renamed from: F */
    public Bundle mo16557F() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("COUNTER_CFG_OBJ", this);
        return bundle;
    }

    /* renamed from: a */
    public void mo16564a(CounterConfiguration counterConfiguration) {
        if (this.f2716a.containsKey("CFG_DISPATCH_PERIOD")) {
            this.f2716a.put("CFG_DISPATCH_PERIOD", counterConfiguration.f2716a.getAsInteger("CFG_DISPATCH_PERIOD"));
        }
        if (this.f2716a.containsKey("CFG_SESSION_TIMEOUT")) {
            this.f2716a.put("CFG_SESSION_TIMEOUT", counterConfiguration.f2716a.getAsInteger("CFG_SESSION_TIMEOUT"));
        }
        if (this.f2716a.containsKey("CFG_MAX_REPORTS_COUNT")) {
            this.f2716a.put("CFG_MAX_REPORTS_COUNT", counterConfiguration.f2716a.getAsInteger("CFG_MAX_REPORTS_COUNT"));
        }
        if (this.f2716a.containsKey("CFG_REPORTS_CRASHES")) {
            this.f2716a.put("CFG_REPORTS_CRASHES", counterConfiguration.f2716a.getAsBoolean("CFG_REPORTS_CRASHES"));
        }
        if (this.f2716a.containsKey("CFG_REPORTS_NATIVE_CRASHES")) {
            this.f2716a.put("CFG_REPORTS_NATIVE_CRASHES", counterConfiguration.f2716a.getAsBoolean("CFG_REPORTS_NATIVE_CRASHES"));
        }
        if (this.f2716a.containsKey("CFG_REPORT_LOCATION")) {
            this.f2716a.put("CFG_REPORT_LOCATION", counterConfiguration.f2716a.getAsBoolean("CFG_REPORT_LOCATION"));
        }
        if (this.f2716a.containsKey("CFG_MANUAL_LOCATION")) {
            this.f2716a.put("CFG_MANUAL_LOCATION", counterConfiguration.f2716a.getAsByteArray("CFG_MANUAL_LOCATION"));
        }
        if (this.f2716a.containsKey("CFG_COLLECT_INSTALLED_APPS")) {
            this.f2716a.put("CFG_COLLECT_INSTALLED_APPS", Integer.valueOf(m3910a(counterConfiguration.f2716a.get("CFG_COLLECT_INSTALLED_APPS")).f2723d));
        }
        if (this.f2716a.containsKey("CFG_DEVICE_SIZE_TYPE")) {
            this.f2716a.put("CFG_DEVICE_SIZE_TYPE", counterConfiguration.f2716a.getAsString("CFG_DEVICE_SIZE_TYPE"));
        }
        if (this.f2716a.containsKey("CFG_IS_LOG_ENABLED")) {
            this.f2716a.put("CFG_IS_LOG_ENABLED", counterConfiguration.f2716a.getAsBoolean("CFG_IS_LOG_ENABLED"));
        }
        if (this.f2716a.containsKey("CFG_CLIDS")) {
            this.f2716a.put("CFG_CLIDS", counterConfiguration.f2716a.getAsString("CFG_CLIDS"));
        }
        if (this.f2716a.containsKey("CFG_AUTO_PRELOAD_INFO_DETECTION")) {
            this.f2716a.put("CFG_AUTO_PRELOAD_INFO_DETECTION", counterConfiguration.f2716a.getAsBoolean("CFG_AUTO_PRELOAD_INFO_DETECTION"));
        }
        if (this.f2716a.containsKey("CFG_PERMISSIONS_COLLECTING")) {
            this.f2716a.put("CFG_PERMISSIONS_COLLECTING", counterConfiguration.f2716a.getAsBoolean("CFG_PERMISSIONS_COLLECTING"));
        }
    }

    /* renamed from: a */
    public void mo16561a(Bundle bundle) {
        if (bundle != null) {
            if (bundle.getInt("CFG_DISPATCH_PERIOD") != 0) {
                mo16559a(bundle.getInt("CFG_DISPATCH_PERIOD"));
            }
            if (bundle.getInt("CFG_SESSION_TIMEOUT") != 0) {
                mo16576c(bundle.getInt("CFG_SESSION_TIMEOUT"));
            }
            if (bundle.getInt("CFG_MAX_REPORTS_COUNT") != 0) {
                mo16572b(bundle.getInt("CFG_MAX_REPORTS_COUNT"));
            }
            if (bundle.getString("CFG_API_KEY") != null && !"-1".equals(bundle.getString("CFG_API_KEY"))) {
                mo16567a(bundle.getString("CFG_API_KEY"));
            }
        }
    }

    /* renamed from: a */
    private static C1774a m3910a(Object obj) {
        if (obj != null) {
            if (obj instanceof Integer) {
                return C1774a.m3973a(((Integer) obj).intValue());
            }
            if (obj instanceof Boolean) {
                return ((Boolean) obj).booleanValue() ? C1774a.TRUE : C1774a.FALSE;
            }
        }
        return C1774a.UNDEFINED;
    }

    /* renamed from: b */
    public static CounterConfiguration m3911b(Bundle bundle) {
        CounterConfiguration counterConfiguration = null;
        if (bundle != null) {
            try {
                counterConfiguration = (CounterConfiguration) bundle.getParcelable("COUNTER_CFG_OBJ");
            } catch (Throwable unused) {
                return null;
            }
        }
        if (counterConfiguration == null) {
            counterConfiguration = new CounterConfiguration();
        }
        counterConfiguration.mo16561a(bundle);
        return counterConfiguration;
    }
}
