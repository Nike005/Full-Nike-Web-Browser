package com.startapp.android.publish.common.metaData;

import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.common.metaData.g */
/* compiled from: StartAppSDK */
public class C1236g implements Serializable {
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private C1226a ambientTemperatureSensor = new C1226a(14);
    private boolean enabled = false;
    @C5303f(mo45477a = true)
    private C1226a gravitySensor = new C1226a(9);
    @C5303f(mo45477a = true)
    private C1226a gyroscopeUncalibratedSensor = new C1226a(18);
    @C5303f(mo45477a = true)
    private C1226a lightSensor = new C1226a(3);
    @C5303f(mo45477a = true)
    private C1226a linearAccelerationSensor = new C1226a(9);
    @C5303f(mo45477a = true)
    private C1226a magneticFieldSensor = new C1226a(3);
    @C5303f(mo45477a = true)
    private C1226a pressureSensor = new C1226a(9);
    @C5303f(mo45477a = true)
    private C1226a relativeHumiditySensor = new C1226a(14);
    @C5303f(mo45477a = true)
    private C1226a rotationVectorSensor = new C1226a(9);
    private int timeoutInSec = 10;

    /* renamed from: a */
    public int mo15251a() {
        return this.timeoutInSec;
    }

    /* renamed from: b */
    public boolean mo15252b() {
        return this.enabled;
    }

    /* renamed from: c */
    public C1226a mo15253c() {
        return this.ambientTemperatureSensor;
    }

    /* renamed from: d */
    public C1226a mo15254d() {
        return this.gravitySensor;
    }

    /* renamed from: e */
    public C1226a mo15255e() {
        return this.lightSensor;
    }

    /* renamed from: f */
    public C1226a mo15256f() {
        return this.linearAccelerationSensor;
    }

    /* renamed from: g */
    public C1226a mo15257g() {
        return this.magneticFieldSensor;
    }

    /* renamed from: h */
    public C1226a mo15258h() {
        return this.pressureSensor;
    }

    /* renamed from: i */
    public C1226a mo15259i() {
        return this.relativeHumiditySensor;
    }

    /* renamed from: j */
    public C1226a mo15260j() {
        return this.rotationVectorSensor;
    }

    /* renamed from: k */
    public C1226a mo15261k() {
        return this.gyroscopeUncalibratedSensor;
    }
}
