package com.startapp.android.publish.adsCommon.p041j;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import com.startapp.android.publish.common.metaData.C1226a;
import com.startapp.android.publish.common.metaData.C1236g;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.C1298d;
import java.util.HashMap;
import org.json.JSONArray;

/* renamed from: com.startapp.android.publish.adsCommon.j.b */
/* compiled from: StartAppSDK */
public class C1163b {

    /* renamed from: a */
    protected C1162a f1238a = new C1162a();

    /* renamed from: b */
    protected SensorManager f1239b;

    /* renamed from: c */
    protected C1298d f1240c;

    /* renamed from: d */
    private HashMap<Integer, C1165a> f1241d = null;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public int f1242e;

    /* renamed from: f */
    private SensorEventListener f1243f = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            if (C1163b.this.f1238a.mo14956a(sensorEvent) == C1163b.this.f1242e) {
                C1163b.this.mo14959b();
                if (C1163b.this.f1240c != null) {
                    C1163b.this.f1240c.mo14939a(C1163b.this.mo14960c());
                }
            }
        }
    };

    public C1163b(Context context, C1298d dVar) {
        this.f1239b = (SensorManager) context.getSystemService("sensor");
        this.f1240c = dVar;
        this.f1242e = 0;
        m1599d();
    }

    /* renamed from: a */
    public void mo14958a() {
        Sensor defaultSensor;
        for (Integer intValue : this.f1241d.keySet()) {
            int intValue2 = intValue.intValue();
            C1165a aVar = this.f1241d.get(Integer.valueOf(intValue2));
            if (Build.VERSION.SDK_INT >= aVar.mo14963a() && (defaultSensor = this.f1239b.getDefaultSensor(intValue2)) != null) {
                this.f1239b.registerListener(this.f1243f, defaultSensor, aVar.mo14964b());
                this.f1242e++;
            }
        }
    }

    /* renamed from: b */
    public void mo14959b() {
        this.f1239b.unregisterListener(this.f1243f);
    }

    /* renamed from: c */
    public JSONArray mo14960c() {
        try {
            return this.f1238a.mo14957a();
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: d */
    private void m1599d() {
        this.f1241d = new HashMap<>();
        C1236g sensorsConfig = MetaData.getInstance().getSensorsConfig();
        m1598a(13, sensorsConfig.mo15253c());
        m1598a(9, sensorsConfig.mo15254d());
        m1598a(5, sensorsConfig.mo15255e());
        m1598a(10, sensorsConfig.mo15256f());
        m1598a(2, sensorsConfig.mo15257g());
        m1598a(6, sensorsConfig.mo15258h());
        m1598a(12, sensorsConfig.mo15259i());
        m1598a(11, sensorsConfig.mo15260j());
        m1598a(16, sensorsConfig.mo15261k());
    }

    /* renamed from: a */
    private void m1598a(int i, C1226a aVar) {
        if (aVar.mo15241c()) {
            this.f1241d.put(Integer.valueOf(i), new C1165a(aVar.mo15240b(), aVar.mo15239a()));
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.j.b$a */
    /* compiled from: StartAppSDK */
    private class C1165a {

        /* renamed from: b */
        private int f1246b;

        /* renamed from: c */
        private int f1247c;

        public C1165a(int i, int i2) {
            this.f1246b = i;
            this.f1247c = i2;
        }

        /* renamed from: a */
        public int mo14963a() {
            return this.f1246b;
        }

        /* renamed from: b */
        public int mo14964b() {
            return this.f1247c;
        }
    }
}
