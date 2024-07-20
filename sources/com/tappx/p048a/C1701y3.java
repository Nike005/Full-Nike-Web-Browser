package com.tappx.p048a;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

/* renamed from: com.tappx.a.y3 */
public class C1701y3 {

    /* renamed from: a */
    private static SparseArray<String> f2562a = new SparseArray<>();

    /* renamed from: b */
    private static int f2563b = Integer.MIN_VALUE;

    /* renamed from: a */
    public static void m3707a(Intent intent, String str) {
        if (str.length() > 262144) {
            intent.putExtra("aavc_5orHkZouKDEIkayJNWLC", m3705a(str));
        } else {
            intent.putExtra("ipc_aavc_bkN4RpcYmIsYuf4eZQOt", str);
        }
    }

    /* renamed from: a */
    public static String m3706a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras.containsKey("ipc_aavc_bkN4RpcYmIsYuf4eZQOt")) {
            return extras.getString("ipc_aavc_bkN4RpcYmIsYuf4eZQOt");
        }
        return f2562a.get(extras.getInt("aavc_5orHkZouKDEIkayJNWLC"));
    }

    /* renamed from: a */
    private static int m3705a(String str) {
        int a = m3704a();
        f2562a.put(a, str);
        return a;
    }

    /* renamed from: a */
    public static synchronized int m3704a() {
        int i;
        synchronized (C1701y3.class) {
            do {
                i = f2563b + 1;
                f2563b = i;
            } while (i == 0);
        }
        return i;
    }
}
