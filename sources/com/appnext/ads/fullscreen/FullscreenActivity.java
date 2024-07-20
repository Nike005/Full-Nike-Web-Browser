package com.appnext.ads.fullscreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.appnext.C4703R;
import com.appnext.ads.C4708a;
import com.appnext.ads.C4711b;
import com.appnext.ads.C4712c;
import com.appnext.base.p082b.C4899d;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4956e;
import com.appnext.core.C4967f;
import com.appnext.core.C4975j;
import com.appnext.core.C4986p;
import com.appnext.core.C4990q;
import com.appnext.core.p086a.C4944b;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

public class FullscreenActivity extends AppnextActivity implements C4762h, C4763i, C4764j, C4956e.C4965a {

    /* renamed from: aB */
    private C4986p f4224aB;

    /* renamed from: aC */
    private boolean f4225aC = true;
    /* access modifiers changed from: private */

    /* renamed from: aD */
    public AppnextAd f4226aD;
    /* access modifiers changed from: private */

    /* renamed from: aE */
    public AppnextAd f4227aE;

    /* renamed from: aF */
    C4711b f4228aF;

    /* renamed from: aG */
    private HashMap<String, Integer> f4229aG;

    /* renamed from: aH */
    private Video f4230aH;

    /* renamed from: aI */
    Runnable f4231aI = new Runnable() {
        public final void run() {
            if (FullscreenActivity.this.userAction != null) {
                FullscreenActivity.this.userAction.mo41317e(FullscreenActivity.this.f4226aD);
            }
            FullscreenActivity.this.report(C4708a.f4157G, "S2");
        }
    };

    /* renamed from: aJ */
    Runnable f4232aJ = new Runnable() {
        public final void run() {
            FullscreenActivity fullscreenActivity = FullscreenActivity.this;
            fullscreenActivity.mo40483a(fullscreenActivity.f4226aD, (C4956e.C4965a) null);
            FullscreenActivity.this.report(C4708a.f4158H, "S2");
        }
    };
    private ArrayList<AppnextAd> ads;
    private boolean finished = false;
    private Handler mHandler;
    private int state = 0;
    private int type;

    /* renamed from: c */
    private static void m6248c() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Fragment fragment;
        if (bundle != null) {
            this.f4229aG = (HashMap) bundle.getSerializable("templates");
            this.state = bundle.getInt("state");
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Configuration configuration = getResources().getConfiguration();
            configuration.setLayoutDirection(new Locale("en"));
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
        super.onCreate(bundle);
        if (Video.currentAd == null) {
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        if (Video.currentAd instanceof RewardedVideo) {
            this.f4230aH = new RewardedVideo((Context) this, (RewardedVideo) Video.currentAd);
        } else {
            this.f4230aH = new FullScreenVideo((Context) this, (FullScreenVideo) Video.currentAd);
        }
        String orientation = m6249d().getOrientation();
        char c = 65535;
        switch (orientation.hashCode()) {
            case 729267099:
                if (orientation.equals(C4924Ad.ORIENTATION_PORTRAIT)) {
                    c = 3;
                    break;
                }
                break;
            case 1430647483:
                if (orientation.equals(C4924Ad.ORIENTATION_LANDSCAPE)) {
                    c = 2;
                    break;
                }
                break;
            case 1673671211:
                if (orientation.equals(C4924Ad.ORIENTATION_AUTO)) {
                    c = 1;
                    break;
                }
                break;
            case 2129065206:
                if (orientation.equals(C4924Ad.ORIENTATION_DEFAULT)) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0 || c == 1) {
            if (getResources().getConfiguration().orientation == 2) {
                setRequestedOrientation(6);
            } else {
                setRequestedOrientation(7);
            }
        } else if (c == 2) {
            setRequestedOrientation(6);
        } else if (c == 3) {
            setRequestedOrientation(7);
        }
        this.mHandler = new Handler();
        this.placementID = getIntent().getExtras().getString("id");
        int i = getIntent().getExtras().getInt("type");
        this.type = i;
        if (i == 1) {
            this.f4224aB = C4727c.m6296m();
        } else {
            this.f4224aB = C4745f.m6314q();
        }
        this.f4225aC = Boolean.parseBoolean(this.f4224aB.get("can_close"));
        if (m6249d() instanceof FullScreenVideo) {
            this.f4225aC = ((FullScreenVideo) m6249d()).isBackButtonCanClose();
        }
        String str = "";
        if (bundle == null) {
            ArrayList<AppnextAd> f = C4725b.m6279j().mo40624f(m6249d());
            this.ads = f;
            if (f == null) {
                onError(AppnextError.NO_ADS);
                finish();
                return;
            }
            this.f4226aD = mo40482a(f, this.placementID, str);
        } else {
            this.ads = (ArrayList) bundle.getSerializable("ads");
            this.f4226aD = (AppnextAd) bundle.getSerializable("currentAd");
        }
        if (this.f4226aD == null) {
            onError(AppnextError.NO_ADS);
            finish();
            return;
        }
        setContentView(C4703R.layout.apnxt_video_activity);
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            if (m6249d() instanceof RewardedVideo) {
                str = ((RewardedVideo) m6249d()).getMode();
            }
            if (str.equals(RewardedVideo.VIDEO_MODE_DEFAULT)) {
                str = getConfig().get("default_mode");
            }
            if (this.type != 2 || !str.equals("multi")) {
                if (this.type == 2) {
                    report("normal");
                }
                fragment = new C4746g();
                bundle2.putBoolean("showCta", m6249d().isShowCta());
                this.state = 1;
            } else {
                fragment = new C4737e();
                bundle2.putInt(C4899d.f4625fl, ((RewardedVideo) m6249d()).getMultiTimerLength());
                report("multi");
            }
            fragment.setArguments(bundle2);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.add(C4703R.C4706id.f4140ll, fragment, "fragment");
            try {
                beginTransaction.commit();
            } catch (Exception unused) {
                finish();
                return;
            }
        } else {
            this.finished = bundle.getBoolean("finished", true);
        }
        this.f4693gl = (RelativeLayout) findViewById(C4703R.C4706id.f4140ll);
        this.userAction = new C4990q(this, new C4990q.C4999a() {
            public final void report(String str) {
                FullscreenActivity.this.report(str);
            }

            /* renamed from: e */
            public final C4924Ad mo40517e() {
                return Video.currentAd;
            }

            /* renamed from: f */
            public final AppnextAd mo40518f() {
                return FullscreenActivity.this.f4227aE;
            }

            /* renamed from: g */
            public final C4986p mo40519g() {
                return FullscreenActivity.this.getConfig();
            }
        });
    }

    /* access modifiers changed from: protected */
    public final void onError(String str) {
        if (m6249d() != null && m6249d().getOnAdErrorCallback() != null) {
            m6249d().getOnAdErrorCallback().adError(str);
        }
    }

    /* access modifiers changed from: protected */
    public final C4986p getConfig() {
        if (this.f4224aB == null) {
            if (this.type == 1) {
                this.f4224aB = C4727c.m6296m();
            } else {
                this.f4224aB = C4745f.m6314q();
            }
        }
        return this.f4224aB;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40483a(AppnextAd appnextAd, C4956e.C4965a aVar) {
        super.mo40483a(appnextAd, (C4956e.C4965a) new C4956e.C4965a() {
            public final void error(String str) {
            }

            public final void onMarket(String str) {
            }
        });
    }

    /* renamed from: a */
    private Uri m6238a() {
        String str;
        String videoUrl = C4725b.getVideoUrl(this.f4226aD, m6249d().getVideoLength());
        String c = C4725b.m6277c(videoUrl);
        if (Video.getCacheVideo()) {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + m6249d().rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            Uri parse = Uri.parse(str + c);
            new StringBuilder("playing video ").append(parse.getPath());
            return parse;
        }
        Uri parse2 = Uri.parse(videoUrl);
        new StringBuilder("playing video from web: ").append(videoUrl);
        new StringBuilder("file not found: ").append(file.getAbsolutePath());
        return parse2;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("finished", this.finished);
        bundle.putInt("type", this.type);
        bundle.putSerializable("templates", this.f4229aG);
        bundle.putSerializable("ads", this.ads);
        bundle.putInt("state", this.state);
        bundle.putSerializable("currentAd", this.f4226aD);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.finished = bundle.getBoolean("finished", true);
        this.type = bundle.getInt("type");
        this.f4229aG = (HashMap) bundle.getSerializable("templates");
    }

    /* renamed from: b */
    private boolean m6245b() {
        return m6249d().isBackButtonCanClose();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.f4231aI);
        this.mHandler.removeCallbacks(this.f4232aJ);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() | 2;
        if (Build.VERSION.SDK_INT >= 16) {
            systemUiVisibility |= 4;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            systemUiVisibility |= 4096;
        }
        getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|5|6|(1:8)|9|10|11|(1:13)|14|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0047 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b A[Catch:{ all -> 0x0052 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r4 = this;
            super.onDestroy()
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x0037 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0037 }
            r1.<init>()     // Catch:{ all -> 0x0037 }
            java.io.File r2 = r4.getFilesDir()     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x0037 }
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "/data/appnext/videos/"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "tmp/vid"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            com.appnext.ads.fullscreen.Video r2 = r4.m6249d()     // Catch:{ all -> 0x0037 }
            long r2 = r2.rnd     // Catch:{ all -> 0x0037 }
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0037 }
            r0.<init>(r1)     // Catch:{ all -> 0x0037 }
            com.appnext.core.C4967f.m6821a((java.io.File) r0)     // Catch:{ all -> 0x0037 }
        L_0x0037:
            r0 = 0
            android.os.Handler r1 = r4.mHandler     // Catch:{ all -> 0x0047 }
            if (r1 == 0) goto L_0x0041
            android.os.Handler r1 = r4.mHandler     // Catch:{ all -> 0x0047 }
            r1.removeCallbacksAndMessages(r0)     // Catch:{ all -> 0x0047 }
        L_0x0041:
            r4.mHandler = r0     // Catch:{ all -> 0x0047 }
            r4.f4227aE = r0     // Catch:{ all -> 0x0047 }
            r4.f4226aD = r0     // Catch:{ all -> 0x0047 }
        L_0x0047:
            com.appnext.ads.b r1 = r4.f4228aF     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            com.appnext.ads.b r1 = r4.f4228aF     // Catch:{ all -> 0x0052 }
            r1.mo40469a((android.content.Context) r4)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            r4.f4228aF = r0     // Catch:{ all -> 0x0052 }
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.fullscreen.FullscreenActivity.onDestroy():void");
    }

    /* renamed from: a */
    private void m6241a(AppnextAd appnextAd) {
        mo40484b(appnextAd, (C4956e.C4965a) this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo40484b(AppnextAd appnextAd, C4956e.C4965a aVar) {
        if (appnextAd != null) {
            this.f4227aE = appnextAd;
            if (m6249d().getOnAdClickedCallback() != null) {
                m6249d().getOnAdClickedCallback().adClicked();
            }
            if (this.finished || !(m6249d() instanceof RewardedVideo)) {
                super.mo40484b(appnextAd, aVar);
            }
        }
    }

    public void onMarket(String str) {
        mo41115ba();
        this.finished = true;
        Collections.shuffle(this.ads, new Random(System.nanoTime()));
        this.ads.remove(this.f4226aD);
        this.ads.add(0, this.f4226aD);
        C4728d dVar = new C4728d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(C4703R.C4706id.f4140ll, dVar, "fragment");
        try {
            beginTransaction.commit();
        } catch (Exception unused) {
            finish();
        }
    }

    public void error(String str) {
        mo41115ba();
        report(C4708a.f4151A);
    }

    private void onClose() {
        try {
            C4725b.m6279j().mo40616a(this.f4226aD.getBannerID(), (C4924Ad) m6249d());
            if (!(m6249d() == null || m6249d().getOnAdClosedCallback() == null)) {
                m6249d().getOnAdClosedCallback().onAdClosed();
            }
            Video.currentAd = null;
        } catch (Throwable unused) {
        }
    }

    public void videoStarted() {
        this.mHandler.postDelayed(this.f4231aI, Long.parseLong(this.f4224aB.get("postpone_impression_sec")) * 1000);
        if (Boolean.parseBoolean(this.f4224aB.get("pview"))) {
            this.mHandler.postDelayed(this.f4232aJ, Long.parseLong(this.f4224aB.get("postpone_vta_sec")) * 1000);
        }
        if (m6249d() != null && m6249d().getOnAdOpenedCallback() != null) {
            m6249d().getOnAdOpenedCallback().adOpened();
        }
    }

    public void videoEnded() {
        this.state = 2;
        this.finished = true;
        if (!(m6249d() == null || m6249d().getOnVideoEndedCallback() == null)) {
            m6249d().getOnVideoEndedCallback().videoEnded();
        }
        new Thread(new Runnable() {
            public final void run() {
                RewardedServerSidePostback rewardedServerSidePostback;
                if ((FullscreenActivity.this.m6249d() instanceof RewardedVideo) && (rewardedServerSidePostback = ((RewardedVideo) FullscreenActivity.this.m6249d()).getRewardedServerSidePostback()) != null) {
                    HashMap<String, String> p = rewardedServerSidePostback.mo40548p();
                    p.put("placementId", FullscreenActivity.this.placementID);
                    try {
                        C4967f.m6815a("https://admin.appnext.com/adminService.asmx/SetRewards", p);
                    } catch (IOException unused) {
                    }
                }
            }
        }).start();
        Collections.shuffle(this.ads, new Random(System.nanoTime()));
        this.ads.remove(this.f4226aD);
        this.ads.add(0, this.f4226aD);
        C4728d dVar = new C4728d();
        this.state = 2;
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(C4703R.C4706id.f4140ll, dVar, "fragment");
        try {
            beginTransaction.commit();
            AppnextAd appnextAd = this.f4227aE;
            if (appnextAd != null) {
                super.mo40484b(appnextAd, this);
                report(C4708a.f4167Q);
                return;
            }
            report(C4708a.f4166P);
            if (Integer.parseInt(getConfig().get("clickType_b")) > new Random(System.nanoTime()).nextInt(100)) {
                installClicked(getSelectedAd());
            }
        } catch (Exception unused) {
            finish();
        }
    }

    public void videoSelected(AppnextAd appnextAd) {
        this.f4226aD = appnextAd;
        C4746g gVar = new C4746g();
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putBoolean("showCta", m6249d().isShowCta());
        gVar.setArguments(bundle);
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(C4703R.C4706id.f4140ll, gVar, "fragment");
        try {
            beginTransaction.commit();
        } catch (Exception unused) {
            finish();
        }
    }

    public ArrayList<AppnextAd> getPreRollAds() {
        if (this.ads == null) {
            this.ads = C4725b.m6279j().mo40624f(m6249d());
        }
        ArrayList<AppnextAd> arrayList = new ArrayList<>();
        AppnextAd a = mo40482a(this.ads, this.placementID, "");
        arrayList.add(a);
        AppnextAd a2 = mo40482a(this.ads, this.placementID, a.getBannerID());
        if (a2 != null && !a2.getBannerID().equals(arrayList.get(0).getBannerID())) {
            arrayList.add(a2);
        }
        return arrayList;
    }

    public void privacyClicked() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(C4967f.m6844g(this.f4226aD)));
            intent.setFlags(268435456);
            startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public void installClicked(AppnextAd appnextAd) {
        if (this.state == 1 && !isRewarded()) {
            mo41112a((ViewGroup) this.f4693gl, getResources().getDrawable(C4703R.C4705drawable.apnxt_loader));
        }
        mo40484b(appnextAd, (C4956e.C4965a) this);
    }

    public void closeClicked() {
        if (this.state != 1 || isRewarded() || Integer.parseInt(getConfig().get("clickType_a")) <= new Random(System.nanoTime()).nextInt(100)) {
            onClose();
            finish();
            return;
        }
        installClicked(getSelectedAd());
    }

    public AppnextAd getSelectedAd() {
        return this.f4226aD;
    }

    public boolean showClose() {
        return (m6249d() instanceof FullScreenVideo) && ((FullScreenVideo) m6249d()).isShowClose();
    }

    public C4986p getConfigManager() {
        return getConfig();
    }

    public int getTemplate(String str) {
        if (this.f4229aG == null) {
            this.f4229aG = new HashMap<>();
        }
        if (!this.f4229aG.containsKey(str)) {
            String a = C4712c.m6236a(getConfig().get(str));
            Resources resources = getResources();
            int identifier = resources.getIdentifier("apnxt_" + a, "layout", getPackageName());
            if (identifier == 0) {
                Resources resources2 = getResources();
                identifier = resources2.getIdentifier("apnxt_" + str.toLowerCase() + "t1", "layout", getPackageName());
            }
            this.f4229aG.put(str, Integer.valueOf(identifier));
        }
        return this.f4229aG.get(str).intValue();
    }

    public boolean getMute() {
        return m6249d().getMute();
    }

    public void report(String str, String str2) {
        m6244b(str, getResources().getResourceEntryName(getTemplate(str2)));
    }

    public ArrayList<AppnextAd> getPostRollAds() {
        return this.ads;
    }

    public boolean isRewarded() {
        return m6249d() instanceof RewardedVideo;
    }

    public String getLanguage() {
        return this.f4230aH.getLanguage();
    }

    public String getCtaText() {
        String buttonText = new FullscreenAd(this.f4226aD).getButtonText();
        AppnextAd appnextAd = this.f4226aD;
        String str = C4944b.f4722hY;
        if (appnextAd == null || !buttonText.equals("")) {
            C4944b bp = C4944b.m6738bp();
            String language = this.f4230aH.getLanguage();
            if (!isInstalled()) {
                str = C4944b.f4721hX;
            }
            return bp.mo41225b(language, str, buttonText);
        } else if (isInstalled()) {
            return C4944b.m6738bp().mo41225b(this.f4230aH.getLanguage(), str, "Open");
        } else {
            return C4944b.m6738bp().mo41225b(this.f4230aH.getLanguage(), C4944b.f4721hX, "Install");
        }
    }

    public boolean isInstalled() {
        try {
            return C4967f.m6842c(this, this.f4226aD.getAdPackage());
        } catch (Throwable unused) {
            return false;
        }
    }

    public int getCaptionTextTime() {
        return m6249d().getRollCaptionTime();
    }

    public long closeDelay() {
        if (m6249d() instanceof FullScreenVideo) {
            return ((FullScreenVideo) m6249d()).getCloseDelay();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final AppnextAd mo40482a(ArrayList<AppnextAd> arrayList, String str, String str2) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (m6246b(next) && !m6242a(next.getBannerID(), str) && !next.getBannerID().equals(str2)) {
                return next;
            }
        }
        C4975j.m6859bj().mo41285ab(str);
        Iterator<AppnextAd> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AppnextAd next2 = it2.next();
            if (m6246b(next2) && !m6242a(next2.getBannerID(), str)) {
                return next2;
            }
        }
        return null;
    }

    /* renamed from: a */
    protected static boolean m6242a(String str, String str2) {
        return C4975j.m6859bj().mo41289o(str, str2);
    }

    /* renamed from: b */
    private static boolean m6246b(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        Resources resources = getResources();
        m6244b(str, resources.getResourceEntryName(getTemplate("S" + (this.state + 1))));
    }

    /* renamed from: b */
    private void m6244b(String str, String str2) {
        try {
            C4967f.m6822a(m6249d().getTID(), m6249d().getVID(), m6249d().getAUID(), this.placementID, m6249d().getSessionId(), str, str2, this.f4226aD != null ? this.f4226aD.getBannerID() : "", this.f4226aD != null ? this.f4226aD.getCampaignID() : "");
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public Video m6249d() {
        if (Video.currentAd != null) {
            return Video.currentAd;
        }
        return this.f4230aH;
    }

    public void onBackPressed() {
        if (m6249d().isBackButtonCanClose()) {
            onClose();
            super.onBackPressed();
        }
    }

    public Uri getSelectedVideoUri() {
        String str;
        String videoUrl = C4725b.getVideoUrl(this.f4226aD, m6249d().getVideoLength());
        String c = C4725b.m6277c(videoUrl);
        if (Video.getCacheVideo()) {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + m6249d().rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            Uri parse = Uri.parse(str + c);
            new StringBuilder("playing video ").append(parse.getPath());
            return parse;
        }
        Uri parse2 = Uri.parse(videoUrl);
        new StringBuilder("playing video from web: ").append(videoUrl);
        new StringBuilder("file not found: ").append(file.getAbsolutePath());
        return parse2;
    }
}
