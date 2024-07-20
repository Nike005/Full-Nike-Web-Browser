package com.appnext.core;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ResultReceiver;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdsService extends IntentService {
    public static final int ADD_PACK = 8348;
    public static final int START_APP = 8346;
    /* access modifiers changed from: private */

    /* renamed from: gf */
    public static HashMap<String, C4983n> f4685gf = new HashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: gg */
    public Runnable f4686gg = new Runnable() {
        public final void run() {
            try {
                for (Map.Entry entry : new HashMap(AdsService.f4685gf).entrySet()) {
                    C4983n nVar = (C4983n) entry.getValue();
                    if (AdsService.this.m6698Q(nVar.f4848am)) {
                        new Bundle().putAll(nVar.f4849hz);
                        AdsService.this.mo41103a(nVar);
                        AdsService.f4685gf.remove(entry.getKey());
                        AdsService.this.startActivity(AdsService.this.getPackageManager().getLaunchIntentForPackage(nVar.f4848am));
                    }
                }
                if (AdsService.this.mHandler == null) {
                    return;
                }
                if (AdsService.f4685gf.entrySet().size() > 0) {
                    AdsService.this.mHandler.postDelayed(AdsService.this.f4686gg, 10000);
                    return;
                }
                AdsService.this.mHandler.removeCallbacksAndMessages((Object) null);
                Handler unused = AdsService.this.mHandler = null;
            } catch (Throwable unused2) {
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler;
    Messenger mMessenger = new Messenger(new C4930a());

    public boolean onUnbind(Intent intent) {
        return false;
    }

    /* renamed from: com.appnext.core.AdsService$a */
    class C4930a extends Handler {
        C4930a() {
        }

        public final void handleMessage(Message message) {
            if (message.what != 8348) {
                super.handleMessage(message);
                return;
            }
            Bundle data = message.getData();
            AdsService.this.addPack(data.getString("package"), data, (ResultReceiver) data.getParcelable("receiver"));
            new StringBuilder("Package added: ").append(data.getString("package"));
        }
    }

    public AdsService() {
        super("AdsService");
    }

    public void onDestroy() {
        super.onDestroy();
        f4685gf.clear();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.mHandler = null;
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent.getIntExtra("added_info", 0) == 8348) {
            addPack(intent.getStringExtra("package"), intent.getExtras(), (ResultReceiver) intent.getParcelableExtra("receiver"));
            new StringBuilder("Package added: ").append(intent.getStringExtra("package"));
        }
    }

    public void addPack(String str, Bundle bundle, ResultReceiver resultReceiver) {
        if (f4685gf == null) {
            f4685gf = new HashMap<>();
        }
        if (f4685gf.containsKey(str)) {
            C4983n nVar = f4685gf.get(str);
            if (nVar == null) {
                addPack(str, bundle, resultReceiver);
                return;
            }
            nVar.f4849hz = bundle;
            f4685gf.put(str, nVar);
            return;
        }
        C4983n nVar2 = new C4983n();
        nVar2.f4848am = str;
        nVar2.f4849hz = bundle;
        f4685gf.put(str, nVar2);
        if (this.mHandler == null) {
            Handler handler = new Handler();
            this.mHandler = handler;
            handler.postDelayed(this.f4686gg, 10000);
        }
    }

    /* renamed from: a */
    private void m6701a(String str, Bundle bundle, ResultReceiver resultReceiver) {
        C4983n nVar = f4685gf.get(str);
        if (nVar == null) {
            addPack(str, bundle, resultReceiver);
            return;
        }
        nVar.f4849hz = bundle;
        f4685gf.put(str, nVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: Q */
    public boolean m6698Q(String str) {
        try {
            getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void mo41103a(final C4983n nVar) {
        new Thread(new Runnable() {
            public final void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("guid", nVar.f4849hz.getString("guid"));
                String str = "";
                hashMap.put("zone", nVar.f4849hz.getString("zone") == null ? str : nVar.f4849hz.getString("zone"));
                hashMap.put("adsID", C4967f.m6827b((Context) AdsService.this, true));
                hashMap.put("isApk", "0");
                hashMap.put("bannerid", nVar.f4849hz.getString("bannerid"));
                hashMap.put("placementid", nVar.f4849hz.getString("placementid"));
                hashMap.put("vid", nVar.f4849hz.getString("vid"));
                hashMap.put("tid", nVar.f4849hz.getString("tid", str));
                hashMap.put("osid", "100");
                hashMap.put("auid", nVar.f4849hz.getString("auid", str));
                String installerPackageName = AdsService.this.getPackageManager().getInstallerPackageName(nVar.f4848am);
                if (installerPackageName != null) {
                    str = installerPackageName;
                }
                hashMap.put("installer", str);
                try {
                    C4967f.m6815a("https://admin.appnext.com/AdminService.asmx/SetOpenV1", (HashMap<String, String>) hashMap);
                } catch (IOException unused) {
                }
            }
        }).start();
    }

    public IBinder onBind(Intent intent) {
        return this.mMessenger.getBinder();
    }
}
