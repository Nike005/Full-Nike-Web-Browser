package com.appnext.banners;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.appnext.core.AdsService;
import com.appnext.core.C4984o;

/* renamed from: com.appnext.banners.i */
public final class C4875i extends C4984o {

    /* renamed from: am */
    String f4542am;

    /* renamed from: an */
    String f4543an;

    /* renamed from: ao */
    String f4544ao;

    /* renamed from: ap */
    String f4545ap;

    /* renamed from: aq */
    String f4546aq;

    /* renamed from: ar */
    String f4547ar;

    /* renamed from: as */
    String f4548as;

    /* renamed from: at */
    ResultReceiver f4549at;
    String guid;

    public C4875i() {
    }

    public C4875i(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4542am = str;
        this.f4543an = str2;
        this.guid = str3;
        this.f4544ao = str4;
        this.f4545ap = str5;
        this.f4549at = resultReceiver;
        this.f4546aq = str6;
        this.f4547ar = str7;
        this.f4548as = str8;
    }

    /* renamed from: a */
    public final void mo40937a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4542am = str;
        this.f4543an = str2;
        this.guid = str3;
        this.f4544ao = str4;
        this.f4545ap = str5;
        this.f4549at = resultReceiver;
        this.f4546aq = str6;
        this.f4547ar = str7;
        this.f4548as = str8;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40470a(Intent intent) {
        intent.putExtra("added_info", AdsService.ADD_PACK);
        intent.putExtra("package", this.f4542am);
        intent.putExtra("link", this.f4543an);
        intent.putExtra("guid", this.guid);
        intent.putExtra("bannerid", this.f4544ao);
        intent.putExtra("placementid", this.f4545ap);
        intent.putExtra("receiver", this.f4549at);
        intent.putExtra("vid", this.f4546aq);
        intent.putExtra("tid", this.f4547ar);
        intent.putExtra("auid", this.f4548as);
    }

    /* renamed from: a */
    public final void mo40469a(Context context) {
        super.mo40469a(context);
        this.f4549at = null;
    }
}
