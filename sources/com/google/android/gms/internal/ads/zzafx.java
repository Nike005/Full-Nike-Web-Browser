package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import com.appnext.core.C4924Ad;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafx {
    private int mOrientation = -1;
    private String zzbhy;
    private boolean zzbtn = false;
    private final zzaef zzbuc;
    private List<String> zzcab;
    private String zzcae;
    private String zzchw;
    private String zzchx;
    private List<String> zzchy;
    private String zzchz;
    private String zzcia;
    private String zzcib;
    private List<String> zzcic;
    private List<String> zzcid;
    private long zzcie = -1;
    private boolean zzcif = false;
    private final long zzcig = -1;
    private long zzcih = -1;
    private boolean zzcii = false;
    private boolean zzcij = false;
    private boolean zzcik = false;
    private boolean zzcil = true;
    private boolean zzcim = true;
    private String zzcin = "";
    private boolean zzcio = false;
    private zzaig zzcip;
    private List<String> zzciq;
    private List<String> zzcir;
    private boolean zzcis = false;
    private boolean zzcit = false;
    private String zzciu;
    private List<String> zzciv;
    private boolean zzciw;
    private String zzcix;
    private zzaiq zzciy;
    private boolean zzciz;
    private boolean zzcja;
    private boolean zzcjb;
    private boolean zzcjc;
    private zzael zzxe;

    public zzafx(zzaef zzaef, String str) {
        this.zzchx = str;
        this.zzbuc = zzaef;
    }

    private static String zza(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return -1;
        }
        String str2 = (String) list.get(0);
        try {
            return (long) (Float.parseFloat(str2) * 1000.0f);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length());
            sb.append("Could not parse float from ");
            sb.append(str);
            sb.append(" header: ");
            sb.append(str2);
            zzakb.zzdk(sb.toString());
            return -1;
        }
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        String str2;
        List list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = (String) list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public final zzaej zza(long j, boolean z) {
        int i;
        String str;
        zzaef zzaef = this.zzbuc;
        String str2 = this.zzchx;
        String str3 = this.zzbhy;
        List<String> list = this.zzchy;
        List<String> list2 = this.zzcic;
        long j2 = this.zzcie;
        boolean z2 = this.zzcif;
        List<String> list3 = this.zzcab;
        long j3 = this.zzcih;
        int i2 = this.mOrientation;
        String str4 = this.zzchw;
        String str5 = this.zzcia;
        String str6 = this.zzcib;
        String str7 = str5;
        boolean z3 = this.zzcii;
        boolean z4 = this.zzcij;
        boolean z5 = this.zzcik;
        boolean z6 = this.zzcil;
        String str8 = this.zzcin;
        boolean z7 = this.zzcio;
        boolean z8 = this.zzbtn;
        zzaig zzaig = this.zzcip;
        List<String> list4 = this.zzciq;
        List<String> list5 = this.zzcir;
        boolean z9 = this.zzcis;
        zzael zzael = this.zzxe;
        boolean z10 = this.zzcit;
        String str9 = this.zzciu;
        List<String> list6 = this.zzciv;
        boolean z11 = this.zzciw;
        String str10 = this.zzcix;
        zzaiq zzaiq = this.zzciy;
        String str11 = this.zzchz;
        boolean z12 = this.zzcim;
        boolean z13 = this.zzciz;
        boolean z14 = this.zzcja;
        if (z) {
            str = str4;
            i = 2;
        } else {
            str = str4;
            i = 1;
        }
        String str12 = str10;
        zzaiq zzaiq2 = zzaiq;
        String str13 = str11;
        boolean z15 = z12;
        boolean z16 = z13;
        zzael zzael2 = zzael;
        boolean z17 = z10;
        String str14 = str9;
        List<String> list7 = list6;
        boolean z18 = z11;
        boolean z19 = z8;
        zzaig zzaig2 = zzaig;
        List<String> list8 = list5;
        boolean z20 = z9;
        boolean z21 = z5;
        boolean z22 = z6;
        String str15 = str8;
        boolean z23 = z7;
        String str16 = str7;
        boolean z24 = z3;
        return new zzaej(zzaef, str2, str3, list, list2, j2, z2, -1, list3, j3, i2, str, j, str16, str6, z24, z4, z21, z22, false, str15, z23, z19, zzaig2, list4, list8, z20, zzael2, z17, str14, list7, z18, str12, zzaiq2, str13, z15, z16, z14, i, this.zzcjb, this.zzcid, this.zzcjc, this.zzcae);
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzbhy = str2;
        zzl(map);
    }

    public final void zzl(Map<String, List<String>> map) {
        int zzrl;
        this.zzchw = zza(map, "X-Afma-Ad-Size");
        this.zzcix = zza(map, "X-Afma-Ad-Slot-Size");
        List<String> zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzchy = zzc;
        }
        this.zzchz = zza(map, "X-Afma-Debug-Signals");
        List list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzcia = (String) list.get(0);
        }
        List<String> zzc2 = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc2 != null) {
            this.zzcic = zzc2;
        }
        List<String> zzc3 = zzc(map, "X-Afma-Downloaded-Impression-Urls");
        if (zzc3 != null) {
            this.zzcid = zzc3;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzcie = zzb;
        }
        this.zzcif |= zzd(map, "X-Afma-Mediation");
        List<String> zzc4 = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc4 != null) {
            this.zzcab = zzc4;
        }
        long zzb2 = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb2 != -1) {
            this.zzcih = zzb2;
        }
        List list2 = map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = (String) list2.get(0);
            if (C4924Ad.ORIENTATION_PORTRAIT.equalsIgnoreCase(str)) {
                zzrl = zzbv.zzem().zzrm();
            } else if (C4924Ad.ORIENTATION_LANDSCAPE.equalsIgnoreCase(str)) {
                zzrl = zzbv.zzem().zzrl();
            }
            this.mOrientation = zzrl;
        }
        this.zzcib = zza(map, "X-Afma-ActiveView");
        List list3 = map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.zzcik = Boolean.valueOf((String) list3.get(0)).booleanValue();
        }
        this.zzcii |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzcij = "native".equals(zza(map, "X-Afma-Ad-Format"));
        List list4 = map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.zzcil = Boolean.valueOf((String) list4.get(0)).booleanValue();
        }
        List list5 = map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.zzcim = Boolean.valueOf((String) list5.get(0)).booleanValue();
        }
        List list6 = map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.zzcin = (String) list6.get(0);
        }
        String zza = zza(map, "X-Afma-Fluid");
        if (zza != null && zza.equals("height")) {
            this.zzcio = true;
        }
        this.zzbtn = "native_express".equals(zza(map, "X-Afma-Ad-Format"));
        this.zzcip = zzaig.zzce(zza(map, "X-Afma-Rewards"));
        if (this.zzciq == null) {
            this.zzciq = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzcir == null) {
            this.zzcir = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzcis |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzcit |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzciu = zza(map, "Set-Cookie");
        String zza2 = zza(map, "X-Afma-Auto-Protection-Configuration");
        if (zza2 == null || TextUtils.isEmpty(zza2)) {
            Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzcia)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzcia);
            }
            boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzaum)).booleanValue();
            String builder = buildUpon.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(builder).length() + 31);
            sb.append(builder);
            sb.append("&navigationURL={NAVIGATION_URL}");
            this.zzxe = new zzael(booleanValue, Arrays.asList(new String[]{sb.toString()}));
        } else {
            try {
                this.zzxe = zzael.zzl(new JSONObject(zza2));
            } catch (JSONException e) {
                zzakb.zzc("Error parsing configuration JSON", e);
                this.zzxe = new zzael();
            }
        }
        List<String> zzc5 = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc5 != null) {
            this.zzciv = zzc5;
        }
        String zza3 = zza(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zza3)) {
            try {
                this.zzciy = zzaiq.zzo(new JSONObject(zza3));
            } catch (JSONException e2) {
                zzakb.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzciw |= zzd(map, "X-Afma-Render-In-Browser");
        String zza4 = zza(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zza4)) {
            try {
                this.zzciz = new JSONObject(zza4).getBoolean("never_pool");
            } catch (JSONException e3) {
                zzakb.zzc("Error parsing interstitial pool header", e3);
            }
        }
        this.zzcja = zzd(map, "X-Afma-Custom-Close-Blocked");
        this.zzcjb = zzd(map, "X-Afma-Enable-Omid");
        this.zzcjc = zzd(map, "X-Afma-Disable-Closable-Area");
        this.zzcae = zza(map, "X-Afma-Omid-Settings");
    }
}
