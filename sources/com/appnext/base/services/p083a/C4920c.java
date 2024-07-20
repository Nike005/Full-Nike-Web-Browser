package com.appnext.base.services.p083a;

import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.operations.C4912b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4905i;
import com.appnext.base.p082b.C4906j;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

/* renamed from: com.appnext.base.services.a.c */
public abstract class C4920c {

    /* renamed from: eH */
    public static final String f4674eH = "more_data";

    /* renamed from: eI */
    private static final long f4675eI = 1000;

    /* renamed from: eJ */
    private static final long f4676eJ = 60000;

    /* renamed from: eK */
    private static final long f4677eK = 3600000;

    /* renamed from: eL */
    private static final long f4678eL = 86400000;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo41061a(C4887c cVar, long j, long j2);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo41062a(C4887c cVar, long j, Bundle bundle);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo41063b(C4887c cVar);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo41064b(C4887c cVar, long j, long j2);

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public abstract void mo41065g(List<C4887c> list);

    /* renamed from: a */
    public final void mo41066a(C4887c cVar, boolean z, Bundle bundle) {
        if (cVar != null) {
            try {
                if (TextUtils.isEmpty(cVar.mo40958ak())) {
                    return;
                }
                if (!cVar.mo40958ak().equals(C4899d.f4619ff)) {
                    long j = 0;
                    if (cVar.mo40959al() != null && cVar.mo40960am().equals(C4899d.f4625fl)) {
                        if (!z) {
                            j = m6681D(cVar.mo40959al());
                        }
                        if (j != -1) {
                            mo41064b(cVar, j + m6682a(-1800000, CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS), 86400000);
                        }
                    } else if (cVar.mo40962ao() != null && cVar.mo40962ao().equals(C4899d.f4627fn)) {
                        long g = (long) C4906j.m6605g(cVar.mo40959al(), cVar.mo40960am());
                        if (g != -1) {
                            C4905i aR = C4905i.m6591aR();
                            long j2 = aR.getLong(cVar.getKey() + C4905i.f4641fy, 0);
                            if (j2 != 0) {
                                if (!z) {
                                    mo41061a(cVar, g + j2, g);
                                    return;
                                }
                            }
                            mo41061a(cVar, System.currentTimeMillis(), g);
                        }
                    } else if (cVar.mo40962ao() != null && cVar.mo40962ao().equals(C4899d.f4626fm)) {
                        mo41062a(cVar, System.currentTimeMillis(), (Bundle) null);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: D */
    private static long m6681D(String str) {
        try {
            if (str.length() != 4) {
                return -1;
            }
            int parseInt = Integer.parseInt(str.substring(0, 2));
            int parseInt2 = Integer.parseInt(str.substring(2, 4));
            Calendar instance = Calendar.getInstance();
            instance.set(11, parseInt);
            instance.set(12, parseInt2);
            instance.set(13, 0);
            if (new Date().after(instance.getTime())) {
                instance.add(5, 1);
            }
            return instance.getTimeInMillis();
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: a */
    private static long m6682a(long j, long j2) {
        try {
            return CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS - ((long) new Random().nextInt((int) (Math.abs(-1800000) + CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS)));
        } catch (Throwable unused) {
            return -1800000;
        }
    }

    /* renamed from: c */
    public final void mo41067c(C4887c cVar) {
        if (cVar != null) {
            try {
                C4912b.m6644aI().mo41054b(cVar.getKey(), cVar, (Bundle) null, (Object) null);
                mo41063b(cVar);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: h */
    public final void mo41068h(List<C4887c> list) {
        if (list != null) {
            try {
                for (C4887c next : list) {
                    C4912b.m6644aI().mo41054b(next.getKey(), next, (Bundle) null, (Object) null);
                }
                mo41065g(list);
            } catch (Throwable unused) {
            }
        }
    }
}
