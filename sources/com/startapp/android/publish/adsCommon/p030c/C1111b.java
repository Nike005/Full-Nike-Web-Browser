package com.startapp.android.publish.adsCommon.p030c;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.startapp.common.C1298d;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.adsCommon.c.b */
/* compiled from: StartAppSDK */
public class C1111b {

    /* renamed from: a */
    protected Context f1127a;

    /* renamed from: b */
    protected C1298d f1128b;

    /* renamed from: c */
    protected C1110a f1129c = new C1110a();

    /* renamed from: d */
    protected BluetoothAdapter f1130d = m1420d();

    /* renamed from: e */
    protected BroadcastReceiver f1131e;

    public C1111b(Context context, C1298d dVar) {
        this.f1127a = context;
        this.f1128b = dVar;
    }

    /* renamed from: a */
    public void mo14812a(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.f1130d;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            this.f1128b.mo14939a((Object) null);
            return;
        }
        this.f1129c.mo14810a(m1419c());
        if (!z || !C1261c.m2031a(this.f1127a, "android.permission.BLUETOOTH_ADMIN")) {
            this.f1128b.mo14939a(mo14813b());
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        BroadcastReceiver e = m1421e();
        this.f1131e = e;
        try {
            this.f1127a.registerReceiver(e, intentFilter);
            this.f1130d.startDiscovery();
        } catch (Exception e2) {
            C1270g.m2076a(3, "BluetoothManager - start() " + e2.getMessage());
            this.f1130d.cancelDiscovery();
            this.f1128b.mo14939a(mo14813b());
        }
    }

    /* renamed from: a */
    public void mo14811a() {
        BluetoothAdapter bluetoothAdapter;
        if (C1261c.m2031a(this.f1127a, "android.permission.BLUETOOTH_ADMIN") && this.f1131e != null && (bluetoothAdapter = this.f1130d) != null) {
            bluetoothAdapter.cancelDiscovery();
            try {
                this.f1127a.unregisterReceiver(this.f1131e);
            } catch (Exception e) {
                C1270g.m2076a(3, "BluetoothManager - stop() " + e.getMessage());
            }
            this.f1131e = null;
        }
    }

    /* renamed from: c */
    private Set<BluetoothDevice> m1419c() {
        HashSet hashSet = new HashSet();
        try {
            if (C1261c.m2031a(this.f1127a, "android.permission.BLUETOOTH") && this.f1130d.isEnabled()) {
                return this.f1130d.getBondedDevices();
            }
        } catch (Exception e) {
            C1270g.m2076a(6, "Unable to get devices " + e.getMessage());
        }
        return hashSet;
    }

    /* renamed from: d */
    private BluetoothAdapter m1420d() {
        if (C1261c.m2031a(this.f1127a, "android.permission.BLUETOOTH")) {
            return BluetoothAdapter.getDefaultAdapter();
        }
        return null;
    }

    /* renamed from: e */
    private BroadcastReceiver m1421e() {
        return new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.bluetooth.device.action.FOUND".equals(action)) {
                    C1111b.this.f1129c.mo14809a((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
                    C1111b.this.mo14811a();
                    C1111b.this.f1128b.mo14939a(C1111b.this.mo14813b());
                }
            }
        };
    }

    /* renamed from: b */
    public JSONObject mo14813b() {
        try {
            return this.f1129c.mo14808a();
        } catch (Exception unused) {
            return null;
        }
    }
}
