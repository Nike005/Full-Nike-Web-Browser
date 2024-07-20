package com.yandex.metrica;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.C1816ae;
import com.yandex.metrica.impl.C1817af;
import com.yandex.metrica.impl.C1827ag;
import com.yandex.metrica.impl.utils.C2228j;

public class MetricaService extends Service {

    /* renamed from: a */
    private C1778b f2726a = new C1778b() {
        /* renamed from: a */
        public void mo16643a(int i) {
            MetricaService.this.stopSelfResult(i);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1816ae f2727b;

    /* renamed from: c */
    private final IMetricaService.Stub f2728c = new IMetricaService.Stub() {
        public void reportEvent(String str, int i, String str2, Bundle bundle) throws RemoteException {
            MetricaService.this.f2727b.mo16784a(getCallingUid(), str, i, str2, bundle);
        }

        public void reportData(Bundle bundle) throws RemoteException {
            MetricaService.this.f2727b.mo16783a(getCallingUid(), bundle);
        }
    };

    /* renamed from: com.yandex.metrica.MetricaService$b */
    public interface C1778b {
        /* renamed from: a */
        void mo16643a(int i);
    }

    /* renamed from: com.yandex.metrica.MetricaService$a */
    static class C1777a extends Binder {
        C1777a() {
        }
    }

    public void onCreate() {
        super.onCreate();
        C2228j.m5959a(getApplicationContext());
        C1817af afVar = new C1817af(new C1827ag(getApplicationContext(), this.f2726a));
        this.f2727b = afVar;
        afVar.mo16782a();
    }

    public void onStart(Intent intent, int i) {
        this.f2727b.mo16786a(intent, i);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.f2727b.mo16787a(intent, i, i2);
        return 2;
    }

    public IBinder onBind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return new C1777a();
        }
        this.f2727b.mo16785a(intent);
        return this.f2728c;
    }

    public void onRebind(Intent intent) {
        super.onRebind(intent);
        this.f2727b.mo16789b(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f2727b.mo16788b();
    }

    public boolean onUnbind(Intent intent) {
        if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(intent.getAction())) {
            return false;
        }
        if (intent == null || intent.getData() == null) {
            return false;
        }
        this.f2727b.mo16790c(intent);
        return true;
    }
}
