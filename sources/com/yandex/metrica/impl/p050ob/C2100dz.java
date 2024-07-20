package com.yandex.metrica.impl.p050ob;

import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;

/* renamed from: com.yandex.metrica.impl.ob.dz */
public final class C2100dz {

    /* renamed from: a */
    private Integer f3606a;

    /* renamed from: b */
    private final Integer f3607b;

    /* renamed from: c */
    private final Integer f3608c;

    /* renamed from: d */
    private final Integer f3609d;

    /* renamed from: e */
    private final Integer f3610e;

    /* renamed from: f */
    private final String f3611f;

    /* renamed from: g */
    private final String f3612g;

    /* renamed from: h */
    private final boolean f3613h;

    /* renamed from: i */
    private final int f3614i;

    /* renamed from: j */
    private final Integer f3615j;

    /* renamed from: com.yandex.metrica.impl.ob.dz$b */
    static abstract class C2102b {

        /* renamed from: a */
        static final Integer f3616a = Integer.MAX_VALUE;

        /* renamed from: b */
        static final Integer f3617b = Integer.MAX_VALUE;

        /* renamed from: c */
        static final Integer f3618c = Integer.MAX_VALUE;

        /* renamed from: d */
        static final Integer f3619d = Integer.MAX_VALUE;

        /* renamed from: e */
        static final Integer f3620e = Integer.MAX_VALUE;

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public abstract C2100dz mo17688a(CellInfo cellInfo);

        C2102b() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C2100dz mo17689a(Integer num, Integer num2, CellSignalStrength cellSignalStrength, Integer num3, Integer num4, boolean z, int i, Integer num5) {
            Integer num6;
            Integer num7;
            Integer num8;
            Integer num9;
            Integer num10 = num;
            Integer num11 = num2;
            Integer num12 = num3;
            Integer num13 = num4;
            Integer num14 = num5;
            if (num10 != null) {
                if (num10 == f3616a) {
                    num10 = null;
                }
                num6 = num10;
            } else {
                num6 = null;
            }
            if (num11 != null) {
                if (num11 == f3617b) {
                    num11 = null;
                }
                num7 = num11;
            } else {
                num7 = null;
            }
            Integer valueOf = cellSignalStrength != null ? Integer.valueOf(cellSignalStrength.getDbm()) : null;
            if (num13 != null) {
                if (num13 == f3618c) {
                    num13 = null;
                }
                num8 = num13;
            } else {
                num8 = null;
            }
            if (num12 != null) {
                if (num12 == f3619d) {
                    num12 = null;
                }
                num9 = num12;
            } else {
                num9 = null;
            }
            return new C2100dz(num8, num9, num7, num6, (String) null, (String) null, valueOf, z, i, (num14 == null || num14 == f3620e) ? null : num14);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dz$c */
    static class C2103c extends C2102b {
        C2103c() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C2100dz mo17688a(CellInfo cellInfo) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            return mo17689a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoGsm.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoGsm.isRegistered(), 1, (Integer) null);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dz$a */
    static class C2101a extends C2102b {
        C2101a() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C2100dz mo17688a(CellInfo cellInfo) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            return mo17689a((Integer) null, (Integer) null, cellInfoCdma.getCellSignalStrength(), (Integer) null, (Integer) null, cellInfoCdma.isRegistered(), 2, (Integer) null);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dz$d */
    static class C2104d extends C2102b {
        C2104d() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C2100dz mo17688a(CellInfo cellInfo) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
            return mo17689a(Integer.valueOf(cellIdentity.getCi()), Integer.valueOf(cellIdentity.getTac()), cellInfoLte.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoLte.isRegistered(), 4, Integer.valueOf(cellIdentity.getPci()));
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dz$e */
    static class C2105e extends C2102b {
        C2105e() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C2100dz mo17688a(CellInfo cellInfo) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
            return mo17689a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoWcdma.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoWcdma.isRegistered(), 3, Integer.valueOf(cellIdentity.getPsc()));
        }
    }

    public C2100dz(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5, boolean z, int i, Integer num6) {
        this.f3607b = num;
        this.f3608c = num2;
        this.f3609d = num3;
        this.f3610e = num4;
        this.f3611f = str;
        this.f3612g = str2;
        this.f3606a = num5;
        this.f3613h = z;
        this.f3614i = i;
        this.f3615j = num6;
    }

    /* renamed from: a */
    public Integer mo17676a() {
        return this.f3606a;
    }

    /* renamed from: b */
    public Integer mo17678b() {
        return this.f3607b;
    }

    /* renamed from: c */
    public Integer mo17679c() {
        return this.f3608c;
    }

    /* renamed from: d */
    public Integer mo17680d() {
        return this.f3609d;
    }

    /* renamed from: e */
    public Integer mo17681e() {
        return this.f3610e;
    }

    /* renamed from: f */
    public String mo17682f() {
        return this.f3611f;
    }

    /* renamed from: g */
    public String mo17683g() {
        return this.f3612g;
    }

    /* renamed from: h */
    public boolean mo17684h() {
        return this.f3613h;
    }

    /* renamed from: a */
    public void mo17677a(Integer num) {
        this.f3606a = num;
    }

    /* renamed from: i */
    public int mo17685i() {
        return this.f3614i;
    }

    /* renamed from: j */
    public Integer mo17686j() {
        return this.f3615j;
    }

    public String toString() {
        return "CellDescription{mSignalStrength=" + this.f3606a + ", mMobileCountryCode=" + this.f3607b + ", mMobileNetworkCode=" + this.f3608c + ", mLocationAreaCode=" + this.f3609d + ", mCellId=" + this.f3610e + ", mOperatorName='" + this.f3611f + '\'' + ", mNetworkType='" + this.f3612g + '\'' + ", mConnected=" + this.f3613h + ", mCellType=" + this.f3614i + ", mPci=" + this.f3615j + '}';
    }
}
