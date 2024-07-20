package com.truenet.android.p049a;

import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.util.List;
import p055a.p056a.p057a.C2903g;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.f */
/* compiled from: StartAppSDK */
public final class C1750f {
    /* renamed from: a */
    public static final int m3854a(TelephonyManager telephonyManager) {
        C2928h.m6157b(telephonyManager, "$receiver");
        if (Build.VERSION.SDK_INT < 26) {
            return m3856c(telephonyManager);
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        CellInfo cellInfo = allCellInfo != null ? (CellInfo) C2903g.m6127d(allCellInfo) : null;
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            C2928h.m6154a((Object) cellIdentity, "info.cellIdentity");
            return cellIdentity.getCid();
        } else if (!(cellInfo instanceof CellInfoCdma)) {
            return m3856c(telephonyManager);
        } else {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            C2928h.m6154a((Object) cellIdentity2, "info.cellIdentity");
            return cellIdentity2.getBasestationId();
        }
    }

    /* renamed from: b */
    public static final int m3855b(TelephonyManager telephonyManager) {
        C2928h.m6157b(telephonyManager, "$receiver");
        if (Build.VERSION.SDK_INT < 26) {
            return m3857d(telephonyManager);
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        CellInfo cellInfo = allCellInfo != null ? (CellInfo) C2903g.m6127d(allCellInfo) : null;
        if (!(cellInfo instanceof CellInfoGsm)) {
            return m3857d(telephonyManager);
        }
        CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
        C2928h.m6154a((Object) cellIdentity, "info.cellIdentity");
        return cellIdentity.getLac();
    }

    /* renamed from: c */
    private static final int m3856c(TelephonyManager telephonyManager) {
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            return ((GsmCellLocation) cellLocation).getCid();
        }
        if (cellLocation instanceof CdmaCellLocation) {
            return ((CdmaCellLocation) cellLocation).getBaseStationId();
        }
        return -1;
    }

    /* renamed from: d */
    private static final int m3857d(TelephonyManager telephonyManager) {
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            return ((GsmCellLocation) cellLocation).getLac();
        }
        return -1;
    }
}
