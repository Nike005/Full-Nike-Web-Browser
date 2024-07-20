package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.p067os.EnvironmentCompat;
import com.yandex.metrica.impl.C1837al;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C1906d;
import com.yandex.metrica.impl.p050ob.C2100dz;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.eb */
final class C2108eb extends C2099dy implements C1906d {

    /* renamed from: a */
    private static final SparseArray<String> f3621a = new SparseArray<String>() {
        {
            put(0, (Object) null);
            put(7, "1xRTT");
            put(4, "CDMA");
            put(2, "EDGE");
            put(14, "eHRPD");
            put(5, "EVDO rev.0");
            put(6, "EVDO rev.A");
            put(12, "EVDO rev.B");
            put(1, "GPRS");
            put(8, "HSDPA");
            put(10, "HSPA");
            put(15, "HSPA+");
            put(9, "HSUPA");
            put(11, "iDen");
            put(3, "UMTS");
            put(12, "EVDO rev.B");
            if (C1897bk.m4650a(11)) {
                put(14, "eHRPD");
                put(13, "LTE");
                if (C1897bk.m4650a(13)) {
                    put(15, "HSPA+");
                }
            }
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final TelephonyManager f3622b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public PhoneStateListener f3623c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f3624d = false;

    /* renamed from: e */
    private final C1906d.C1907a<C2118eg> f3625e = new C1906d.C1907a<>();

    /* renamed from: f */
    private final C1906d.C1907a<C2100dz[]> f3626f = new C1906d.C1907a<>();

    /* renamed from: g */
    private final Handler f3627g;

    /* renamed from: h */
    private final Context f3628h;

    protected C2108eb(Context context) {
        this.f3628h = context;
        this.f3622b = (TelephonyManager) context.getSystemService("phone");
        HandlerThread handlerThread = new HandlerThread("TelephonyProviderThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f3627g = handler;
        handler.post(new Runnable() {
            public void run() {
                PhoneStateListener unused = C2108eb.this.f3623c = new C2113a(C2108eb.this, (byte) 0);
            }
        });
    }

    /* renamed from: a */
    public synchronized void mo17690a() {
        this.f3627g.post(new Runnable() {
            public void run() {
                if (!C2108eb.this.f3624d) {
                    boolean unused = C2108eb.this.f3624d = true;
                    try {
                        if (C2108eb.this.f3623c != null) {
                            C2108eb.this.f3622b.listen(C2108eb.this.f3623c, 256);
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        });
    }

    /* renamed from: b */
    public synchronized void mo17691b() {
        this.f3627g.post(new Runnable() {
            public void run() {
                if (C2108eb.this.f3624d) {
                    boolean unused = C2108eb.this.f3624d = false;
                    try {
                        if (C2108eb.this.f3623c != null) {
                            C2108eb.this.f3622b.listen(C2108eb.this.f3623c, 0);
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        });
    }

    /* renamed from: a */
    public synchronized void mo17675a(C2119eh ehVar) {
        if (ehVar != null) {
            ehVar.mo16879a(mo17692c());
        }
    }

    /* renamed from: a */
    public synchronized void mo17674a(C2107ea eaVar) {
        if (eaVar != null) {
            eaVar.mo17151a(m5534g());
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.eb$a */
    private class C2113a extends PhoneStateListener {
        private C2113a() {
        }

        /* synthetic */ C2113a(C2108eb ebVar, byte b) {
            this();
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            C2108eb.this.m5528a(signalStrength);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized C2118eg mo17692c() {
        C2118eg egVar;
        if (!this.f3625e.mo17102b()) {
            if (!this.f3625e.mo17103c()) {
                egVar = this.f3625e.mo17100a();
            }
        }
        egVar = new C2118eg(mo17693d(), mo17694e(), mo17695f());
        if (egVar.mo17706b().mo17676a() == null && !this.f3625e.mo17102b()) {
            egVar.mo17706b().mo17677a(this.f3625e.mo17100a().mo17706b().mo17676a());
        }
        this.f3625e.mo17101a(egVar);
        return egVar;
    }

    /* renamed from: g */
    private synchronized C2100dz[] m5534g() {
        C2100dz[] dzVarArr;
        C2100dz.C2102b bVar;
        if (!this.f3626f.mo17102b()) {
            if (!this.f3626f.mo17103c()) {
                dzVarArr = this.f3626f.mo17100a();
            }
        }
        ArrayList arrayList = new ArrayList();
        if (C1897bk.m4650a(17) && C1837al.m4248a(this.f3628h, "android.permission.ACCESS_COARSE_LOCATION")) {
            List<CellInfo> allCellInfo = this.f3622b.getAllCellInfo();
            if (!C1897bk.m4652a((Collection) allCellInfo)) {
                for (int i = 0; i < allCellInfo.size(); i++) {
                    CellInfo cellInfo = allCellInfo.get(i);
                    C2100dz dzVar = null;
                    if (cellInfo instanceof CellInfoGsm) {
                        bVar = new C2100dz.C2103c();
                    } else if (cellInfo instanceof CellInfoCdma) {
                        bVar = new C2100dz.C2101a();
                    } else if (cellInfo instanceof CellInfoLte) {
                        bVar = new C2100dz.C2104d();
                    } else {
                        bVar = (!C1897bk.m4650a(18) || !(cellInfo instanceof CellInfoWcdma)) ? null : new C2100dz.C2105e();
                    }
                    if (bVar != null) {
                        dzVar = bVar.mo17688a(cellInfo);
                    }
                    if (dzVar != null) {
                        arrayList.add(dzVar);
                    }
                }
            }
        }
        dzVarArr = arrayList.size() <= 0 ? new C2100dz[]{mo17692c().mo17706b()} : (C2100dz[]) arrayList.toArray(new C2100dz[arrayList.size()]);
        this.f3626f.mo17101a(dzVarArr);
        return dzVarArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m5528a(SignalStrength signalStrength) {
        int i;
        if (!this.f3625e.mo17102b() && !this.f3625e.mo17103c()) {
            C2100dz b = this.f3625e.mo17100a().mo17706b();
            if (signalStrength.isGsm()) {
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                i = 99 == gsmSignalStrength ? -1 : (gsmSignalStrength * 2) - 113;
            } else {
                int cdmaDbm = signalStrength.getCdmaDbm();
                i = signalStrength.getEvdoDbm();
                if (-120 == i) {
                    i = cdmaDbm;
                } else if (-120 != cdmaDbm) {
                    i = Math.min(cdmaDbm, i);
                }
            }
            b.mo17677a(Integer.valueOf(i));
        }
    }

    /* renamed from: h */
    private Integer m5535h() {
        try {
            String substring = this.f3622b.getNetworkOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                return Integer.valueOf(Integer.parseInt(substring));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: i */
    private Integer m5536i() {
        try {
            String substring = this.f3622b.getNetworkOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                return Integer.valueOf(Integer.parseInt(substring));
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: j */
    private Integer m5537j() {
        try {
            String substring = this.f3622b.getSimOperator().substring(0, 3);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(substring));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: k */
    private Integer m5538k() {
        try {
            String substring = this.f3622b.getSimOperator().substring(3);
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(substring));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: l */
    private Integer m5539l() {
        int cid;
        try {
            if (!C1837al.m4247a(this.f3628h) || -1 == (cid = ((GsmCellLocation) this.f3622b.getCellLocation()).getCid())) {
                return null;
            }
            return Integer.valueOf(cid);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: m */
    private Integer m5540m() {
        int lac;
        try {
            if (!C1837al.m4247a(this.f3628h) || -1 == (lac = ((GsmCellLocation) this.f3622b.getCellLocation()).getLac())) {
                return null;
            }
            return Integer.valueOf(lac);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: n */
    private String m5541n() {
        try {
            return f3621a.get(this.f3622b.getNetworkType(), EnvironmentCompat.MEDIA_UNKNOWN);
        } catch (Exception unused) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    /* renamed from: o */
    private String m5542o() {
        try {
            if (C1837al.m4248a(this.f3628h, "android.permission.READ_PHONE_STATE")) {
                return this.f3622b.getDeviceId();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: p */
    private List<String> m5543p() {
        HashSet hashSet = new HashSet();
        try {
            if (C1837al.m4248a(this.f3628h, "android.permission.READ_PHONE_STATE")) {
                for (int i = 0; i < 10; i++) {
                    String deviceId = this.f3622b.getDeviceId(i);
                    if (deviceId != null) {
                        hashSet.add(deviceId);
                    }
                }
            }
        } catch (Exception unused) {
        }
        return new ArrayList(hashSet);
    }

    /* renamed from: q */
    private boolean m5544q() {
        if (!C1837al.m4248a(this.f3628h, "android.permission.READ_PHONE_STATE")) {
            return false;
        }
        try {
            return this.f3622b.isNetworkRoaming();
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2100dz mo17693d() {
        return new C2100dz(m5535h(), m5536i(), m5540m(), m5539l(), this.f3622b.getNetworkOperatorName(), m5541n(), (Integer) null, true, 0, (Integer) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public List<C2116ee> mo17694e() {
        ArrayList arrayList = new ArrayList();
        if (C1897bk.m4650a(23)) {
            arrayList.addAll(m5546s());
            if (arrayList.size() == 0) {
                arrayList.add(m5545r());
            }
        } else {
            arrayList.add(m5545r());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public List<String> mo17695f() {
        ArrayList arrayList = new ArrayList();
        if (C1897bk.m4650a(23)) {
            arrayList.addAll(m5543p());
        } else {
            arrayList.add(m5542o());
        }
        return arrayList;
    }

    /* renamed from: r */
    private C2116ee m5545r() {
        return new C2116ee(m5537j(), m5538k(), m5544q(), this.f3622b.getSimOperatorName(), (String) null);
    }

    /* renamed from: s */
    private List<C2116ee> m5546s() {
        ArrayList arrayList = new ArrayList();
        if (C1837al.m4248a(this.f3628h, "android.permission.READ_PHONE_STATE")) {
            try {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.f3628h).getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null) {
                    for (SubscriptionInfo eeVar : activeSubscriptionInfoList) {
                        arrayList.add(new C2116ee(eeVar));
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }
}
