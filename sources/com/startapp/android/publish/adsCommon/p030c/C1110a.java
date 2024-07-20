package com.startapp.android.publish.adsCommon.p030c;

import android.bluetooth.BluetoothDevice;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.adsCommon.c.a */
/* compiled from: StartAppSDK */
public class C1110a {

    /* renamed from: a */
    private Set<BluetoothDevice> f1125a;

    /* renamed from: b */
    private Set<BluetoothDevice> f1126b;

    /* renamed from: a */
    public void mo14809a(BluetoothDevice bluetoothDevice) {
        if (this.f1126b == null) {
            this.f1126b = new HashSet();
        }
        this.f1126b.add(bluetoothDevice);
    }

    /* renamed from: a */
    public void mo14810a(Set<BluetoothDevice> set) {
        this.f1125a = set;
    }

    /* renamed from: a */
    public JSONObject mo14808a() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f1125a != null && this.f1125a.size() > 0) {
                jSONObject.put("paired", m1415b(this.f1125a));
            }
            if (this.f1126b != null && this.f1126b.size() > 0) {
                jSONObject.put("available", m1415b(this.f1126b));
            }
        } catch (Exception unused) {
        }
        if (jSONObject.length() > 0) {
            return jSONObject;
        }
        return null;
    }

    /* renamed from: b */
    private JSONArray m1415b(Set<BluetoothDevice> set) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (BluetoothDevice next : set) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bluetoothClass", next.getBluetoothClass().getDeviceClass());
                jSONObject.put("name", next.getName());
                jSONObject.put("mac", next.getAddress());
                jSONObject.put("bondState", next.getBondState());
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Exception unused) {
            return null;
        }
    }
}
