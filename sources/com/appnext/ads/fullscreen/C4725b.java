package com.appnext.ads.fullscreen;

import android.content.Context;
import android.net.Uri;
import android.util.Pair;
import com.appnext.ads.C4708a;
import com.appnext.base.p082b.C4899d;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4940a;
import com.appnext.core.C4948d;
import com.appnext.core.C4967f;
import com.appnext.core.C4972g;
import com.appnext.core.C4986p;
import com.appnext.core.p086a.C4944b;
import info.guardianproject.netcipher.proxy.TorServiceUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import org.altbeacon.beacon.BeaconManager;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.appnext.ads.fullscreen.b */
public final class C4725b extends C4948d {

    /* renamed from: aL */
    private static C4725b f4253aL;

    /* renamed from: aM */
    private final int f4254aM = 30;

    /* renamed from: aN */
    private HashMap<C4924Ad, String> f4255aN = new HashMap<>();

    /* renamed from: j */
    public static synchronized C4725b m6279j() {
        C4725b bVar;
        synchronized (C4725b.class) {
            if (f4253aL == null) {
                f4253aL = new C4725b();
            }
            bVar = f4253aL;
        }
        return bVar;
    }

    private C4725b() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo40613a(Context context, C4924Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : TorServiceUtils.CHMOD_EXE_VALUE);
        sb.append("&vidmin=");
        Object obj = "";
        sb.append(ad == null ? obj : Integer.valueOf(ad.getMinVideoLength()));
        sb.append("&vidmax=");
        if (ad != null) {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        sb.append(obj);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo40610a(Context context, C4972g gVar) {
        int i;
        AppnextAd appnextAd = (AppnextAd) gVar;
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCampaignGoal().equals(C4944b.f4721hX) || !C4967f.m6842c(context, fullscreenAd.getAdPackage())) {
            i = (!fullscreenAd.getCampaignGoal().equals(C4944b.f4722hY) || C4967f.m6842c(context, fullscreenAd.getAdPackage())) ? 0 : 2;
        } else {
            i = 1;
        }
        int b = m6275b(context, appnextAd);
        if (i == 0 && b == 0) {
            return 0;
        }
        return i != 0 ? i : b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo40619a(C4924Ad ad) {
        return super.mo40619a(ad) && m6278e(ad);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo40618a(Context context, C4924Ad ad, ArrayList<?> arrayList) {
        return mo40612a(context, ad, (ArrayList<AppnextAd>) arrayList, "") != null;
    }

    /* renamed from: a */
    private void m6274a(Context context, C4924Ad ad, AppnextAd appnextAd) throws Exception {
        String str;
        if (!appnextAd.getImageURL().equals("")) {
            C4967f.m6809Y(appnextAd.getImageURL());
        }
        if (!appnextAd.getWideImageURL().equals("")) {
            C4967f.m6809Y(appnextAd.getWideImageURL());
        }
        Video video = (Video) ad;
        String videoUrl = getVideoUrl(appnextAd, video.getVideoLength());
        String c = m6277c(videoUrl);
        if (Video.getCacheVideo()) {
            str = context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/";
        } else {
            str = context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/" + "tmp/vid" + video.rnd + "/";
        }
        File file = new File(str + c);
        if (file.exists()) {
            file.setLastModified(System.currentTimeMillis());
            StringBuilder sb = new StringBuilder();
            sb.append(file.getPath());
            sb.append(" exists");
            this.f4255aN.put(ad, file.getAbsolutePath());
        } else if (!Video.isStreamingModeEnabled()) {
            new File(str).mkdirs();
            URL url = new URL(videoUrl);
            url.openConnection().connect();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream(), 1024);
            FileOutputStream fileOutputStream = new FileOutputStream(str + c + C4899d.f4612eY);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    StringBuilder sb2 = new StringBuilder("downloaded ");
                    sb2.append(str);
                    sb2.append(c);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bufferedInputStream.close();
                    File file2 = new File(str + c + C4899d.f4612eY);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(c);
                    file2.renameTo(new File(sb3.toString()));
                    file2.delete();
                    this.f4255aN.put(ad, file.getAbsolutePath());
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private void m6273a(Context context, C4924Ad ad) {
        int i;
        int i2;
        try {
            File[] listFiles = new File(context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/").listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
                }

                /* renamed from: a */
                public static int m6295a(File file, File file2) {
                    return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                }
            });
            if (Video.getCacheVideo()) {
                if (ad instanceof FullScreenVideo) {
                    i2 = Integer.parseInt(C4727c.m6296m().get("num_saved_videos"));
                } else {
                    i2 = Integer.parseInt(C4745f.m6314q().get("num_saved_videos"));
                }
                i = i2 - 1;
            } else {
                i = 0;
            }
            if (listFiles.length > i) {
                for (int i3 = 0; i3 < listFiles.length - i; i3++) {
                    listFiles[i3].delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40615a(C4924Ad ad, String str, String str2) {
        if (ad != null) {
            C4967f.m6822a(ad.getTID(), ad.getVID(), ad.getAUID(), str2, str, C4708a.f4196k, "sdk", "", "");
        } else {
            C4967f.m6822a("300", "2.5.1.472", TorServiceUtils.CHMOD_EXE_VALUE, str2, str, C4708a.f4196k, "sdk", "", "");
        }
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final <T> void mo40617a(String str, C4924Ad ad, T t) {
        C4967f.m6822a(ad.getTID(), ad.getVID(), ad.getAUID(), str, ((Video) ad).getSessionId(), C4708a.f4195j, "sdk", "", "");
    }

    protected static String getVideoUrl(AppnextAd appnextAd, String str) {
        String str2;
        if (str.equals("30")) {
            str2 = appnextAd.getVideoUrlHigh30Sec();
            if (str2.equals("")) {
                str2 = appnextAd.getVideoUrlHigh();
            }
        } else {
            str2 = appnextAd.getVideoUrlHigh();
            if (str2.equals("")) {
                str2 = appnextAd.getVideoUrlHigh30Sec();
            }
        }
        StringBuilder sb = new StringBuilder("returning video url for: ");
        sb.append(appnextAd.getBannerID());
        sb.append(" with len: ");
        sb.append(str);
        sb.append(" url: ");
        sb.append(str2);
        return str2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40616a(String str, C4924Ad ad) {
        super.mo40616a(str, ad);
        if (this.f4255aN.containsKey(ad)) {
            this.f4255aN.remove(ad);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo40621b(C4924Ad ad) {
        try {
            if (!mo41246h(ad) || mo41249k(ad).mo41212aU().longValue() + mo41248j(ad) + BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD < System.currentTimeMillis() || !m6278e(ad)) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final C4986p mo40622c(C4924Ad ad) {
        return ad instanceof RewardedVideo ? C4745f.m6314q() : C4727c.m6296m();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final boolean mo40623d(C4924Ad ad) {
        try {
            return mo40619a(ad) && m6278e(ad);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: e */
    private boolean m6278e(C4924Ad ad) {
        if (Video.isStreamingModeEnabled()) {
            return true;
        }
        if (!this.f4255aN.containsKey(ad)) {
            return false;
        }
        return new File(this.f4255aN.get(ad)).exists();
    }

    /* renamed from: b */
    private static boolean m6276b(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final AppnextAd mo40620b(Context context, C4924Ad ad) {
        return mo40611a(context, ad, "");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final AppnextAd mo40611a(Context context, C4924Ad ad, String str) {
        ArrayList<?> ads;
        if (mo41249k(ad) == null || (ads = mo41249k(ad).getAds()) == null) {
            return null;
        }
        return mo40612a(context, ad, (ArrayList<AppnextAd>) ads, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final AppnextAd mo40612a(Context context, C4924Ad ad, ArrayList<AppnextAd> arrayList, String str) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (m6276b(next) && !m6747a(next.getBannerID(), ad.getPlacementID()) && !next.getBannerID().equals(str)) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public final ArrayList<AppnextAd> mo40624f(C4924Ad ad) {
        return mo41249k(ad).getAds();
    }

    /* renamed from: a */
    private static int m6272a(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCampaignGoal().equals(C4944b.f4721hX) || !C4967f.m6842c(context, fullscreenAd.getAdPackage())) {
            return (!fullscreenAd.getCampaignGoal().equals(C4944b.f4722hY) || C4967f.m6842c(context, fullscreenAd.getAdPackage())) ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: b */
    private static int m6275b(Context context, AppnextAd appnextAd) {
        FullscreenAd fullscreenAd = new FullscreenAd(appnextAd);
        if (!fullscreenAd.getCptList().equals("") && !fullscreenAd.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(fullscreenAd.getCptList());
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (C4967f.m6842c(context, jSONArray.getString(i))) {
                        return 0;
                    }
                }
                return 3;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    /* renamed from: c */
    protected static String m6277c(String str) {
        String substring = str.substring(str.lastIndexOf("/") + 1);
        if (substring.contains("?")) {
            substring = substring.substring(0, substring.indexOf("?"));
        }
        try {
            String queryParameter = Uri.parse(str).getQueryParameter("rnd");
            if (queryParameter == null || queryParameter.equals("")) {
                return substring;
            }
            return substring.substring(0, substring.lastIndexOf(46)) + "_" + queryParameter + substring.substring(substring.lastIndexOf(46));
        } catch (Throwable unused) {
            return substring;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40614a(Context context, C4924Ad ad, C4940a aVar) throws Exception {
        AppnextAd a;
        int i;
        int i2;
        try {
            File[] listFiles = new File(context.getFilesDir().getAbsolutePath() + "/data/appnext/videos/").listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                public final /* synthetic */ int compare(Object obj, Object obj2) {
                    return Long.valueOf(((File) obj).lastModified()).compareTo(Long.valueOf(((File) obj2).lastModified()));
                }

                /* renamed from: a */
                public static int m6295a(File file, File file2) {
                    return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                }
            });
            if (Video.getCacheVideo()) {
                if (ad instanceof FullScreenVideo) {
                    i2 = Integer.parseInt(C4727c.m6296m().get("num_saved_videos"));
                } else {
                    i2 = Integer.parseInt(C4745f.m6314q().get("num_saved_videos"));
                }
                i = i2 - 1;
            } else {
                i = 0;
            }
            if (listFiles.length > i) {
                for (int i3 = 0; i3 < listFiles.length - i; i3++) {
                    listFiles[i3].delete();
                }
            }
        } catch (Throwable unused) {
        }
        AppnextAd appnextAd = null;
        try {
            AppnextAd b = mo40620b(context, ad);
            if (b != null) {
                m6274a(context, ad, b);
                if (ad instanceof RewardedVideo) {
                    String mode = ((RewardedVideo) ad).getMode();
                    if (mode.equals(RewardedVideo.VIDEO_MODE_DEFAULT)) {
                        mode = C4745f.m6314q().get("default_mode");
                    }
                    if (mode.equals("multi") && (a = mo40611a(context, ad, b.getBannerID())) != null) {
                        m6274a(context, ad, a);
                        return;
                    }
                    return;
                }
                return;
            }
            throw new Exception("No video ads");
        } catch (Throwable th) {
            if (appnextAd != null) {
                mo40616a(appnextAd.getBannerID(), ad);
            }
            throw th;
        }
    }
}
