package com.appnext.base.services.p083a;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.services.OperationService;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

/* renamed from: com.appnext.base.services.a.a */
public final class C4918a extends C4920c {

    /* renamed from: eE */
    private AlarmManager f4671eE;
    private Context mContext;

    public C4918a(Context context) {
        try {
            this.mContext = context.getApplicationContext();
            this.f4671eE = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public final void mo41063b(C4887c cVar) {
        try {
            this.f4671eE.cancel(PendingIntent.getService(this.mContext, cVar.mo40963ap().hashCode(), new Intent(this.mContext, OperationService.class), 268435456));
            this.mContext.stopService(new Intent(this.mContext, OperationService.class));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: g */
    public final void mo41065g(List<C4887c> list) {
        if (list != null) {
            try {
                for (C4887c b : list) {
                    mo41063b(b);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41061a(C4887c cVar, long j, long j2) {
        m6669a(cVar.mo40963ap(), cVar.getKey(), j, j2, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo41064b(C4887c cVar, long j, long j2) {
        m6669a(cVar.mo40963ap(), cVar.getKey(), j, DateUtils.MILLIS_PER_DAY, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo41062a(C4887c cVar, long j, Bundle bundle) {
        m6669a(cVar.mo40963ap(), cVar.getKey(), j, 0, bundle);
    }

    /* renamed from: a */
    private void m6669a(String str, String str2, long j, long j2, Bundle bundle) {
        try {
            Intent intent = new Intent(this.mContext, OperationService.class);
            intent.putExtra(C4899d.f4620fg, str2);
            if (bundle != null) {
                intent.putExtra(C4920c.f4674eH, bundle);
            }
            int hashCode = str.hashCode();
            String.valueOf(str);
            PendingIntent service = PendingIntent.getService(this.mContext, hashCode, intent, 134217728);
            if (j2 > 0) {
                this.f4671eE.setInexactRepeating(1, j, j2, service);
            } else {
                this.f4671eE.set(1, j, service);
            }
        } catch (Throwable unused) {
        }
    }
}
