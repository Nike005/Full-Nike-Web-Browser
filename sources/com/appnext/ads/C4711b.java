package com.appnext.ads;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.appnext.core.AdsService;
import com.appnext.core.C4984o;

/* renamed from: com.appnext.ads.b */
public final class C4711b extends C4984o {

    /* renamed from: am */
    String f4212am;

    /* renamed from: an */
    String f4213an;

    /* renamed from: ao */
    String f4214ao;

    /* renamed from: ap */
    String f4215ap;

    /* renamed from: aq */
    String f4216aq;

    /* renamed from: ar */
    String f4217ar;

    /* renamed from: as */
    String f4218as;

    /* renamed from: at */
    ResultReceiver f4219at;
    String guid;

    public C4711b() {
    }

    public C4711b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4212am = str;
        this.f4213an = str2;
        this.guid = str3;
        this.f4214ao = str4;
        this.f4215ap = str5;
        this.f4219at = resultReceiver;
        this.f4216aq = str6;
        this.f4217ar = str7;
        this.f4218as = str8;
    }

    /* renamed from: a */
    public final void mo40471a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4212am = str;
        this.f4213an = str2;
        this.guid = str3;
        this.f4214ao = str4;
        this.f4215ap = str5;
        this.f4219at = resultReceiver;
        this.f4216aq = str6;
        this.f4217ar = str7;
        this.f4218as = str8;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40470a(Intent intent) {
        intent.putExtra("added_info", AdsService.ADD_PACK);
        intent.putExtra("package", this.f4212am);
        intent.putExtra("link", this.f4213an);
        intent.putExtra("guid", this.guid);
        intent.putExtra("bannerid", this.f4214ao);
        intent.putExtra("placementid", this.f4215ap);
        intent.putExtra("receiver", this.f4219at);
        intent.putExtra("vid", this.f4216aq);
        intent.putExtra("tid", this.f4217ar);
        intent.putExtra("auid", this.f4218as);
    }

    /* renamed from: a */
    public final void mo40469a(Context context) {
        super.mo40469a(context);
        this.f4219at = null;
    }
}
