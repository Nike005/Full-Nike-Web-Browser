package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;

/* renamed from: com.appnext.core.h */
public final class C4973h extends C4984o {

    /* renamed from: am */
    String f4819am;

    /* renamed from: an */
    String f4820an;

    /* renamed from: ao */
    String f4821ao;

    /* renamed from: ap */
    String f4822ap;

    /* renamed from: aq */
    String f4823aq;

    /* renamed from: ar */
    String f4824ar;

    /* renamed from: as */
    String f4825as;

    /* renamed from: at */
    ResultReceiver f4826at;
    String guid;

    public C4973h() {
    }

    public C4973h(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4819am = str;
        this.f4820an = str2;
        this.guid = str3;
        this.f4821ao = str4;
        this.f4822ap = str5;
        this.f4826at = resultReceiver;
        this.f4823aq = str6;
        this.f4824ar = str7;
        this.f4825as = str8;
    }

    /* renamed from: a */
    public final void mo41284a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, ResultReceiver resultReceiver) {
        this.f4819am = str;
        this.f4820an = str2;
        this.guid = str3;
        this.f4821ao = str4;
        this.f4822ap = str5;
        this.f4826at = null;
        this.f4823aq = str6;
        this.f4824ar = str7;
        this.f4825as = str8;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40470a(Intent intent) {
        intent.putExtra("added_info", AdsService.ADD_PACK);
        intent.putExtra("package", this.f4819am);
        intent.putExtra("link", this.f4820an);
        intent.putExtra("guid", this.guid);
        intent.putExtra("bannerid", this.f4821ao);
        intent.putExtra("placementid", this.f4822ap);
        intent.putExtra("receiver", this.f4826at);
        intent.putExtra("vid", this.f4823aq);
        intent.putExtra("tid", this.f4824ar);
        intent.putExtra("auid", this.f4825as);
    }

    /* renamed from: a */
    public final void mo40469a(Context context) {
        super.mo40469a(context);
        this.f4826at = null;
    }
}
