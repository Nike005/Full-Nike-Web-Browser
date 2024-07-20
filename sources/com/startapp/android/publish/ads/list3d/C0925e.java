package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.common.model.AdDetails;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.list3d.e */
/* compiled from: StartAppSDK */
public class C0925e {

    /* renamed from: a */
    private C0914a f598a = new C0914a();

    /* renamed from: b */
    private List<ListItem> f599b;

    /* renamed from: c */
    private String f600c = "";

    /* renamed from: a */
    public void mo14087a() {
        this.f599b = new ArrayList();
        this.f600c = "";
    }

    /* renamed from: a */
    public void mo14090a(AdDetails adDetails) {
        ListItem listItem = new ListItem(adDetails);
        this.f599b.add(listItem);
        this.f598a.mo14037a(this.f599b.size() - 1, listItem.mo14008a(), listItem.mo14017i());
    }

    /* renamed from: b */
    public void mo14092b() {
        this.f598a.mo14042b();
    }

    /* renamed from: c */
    public void mo14094c() {
        this.f598a.mo14043c();
    }

    /* renamed from: d */
    public void mo14095d() {
        this.f598a.mo14044d();
    }

    /* renamed from: e */
    public List<ListItem> mo14096e() {
        return this.f599b;
    }

    /* renamed from: a */
    public Bitmap mo14086a(int i, String str, String str2) {
        return this.f598a.mo14037a(i, str, str2);
    }

    /* renamed from: a */
    public void mo14088a(Context context, String str, String str2, C1117b bVar, long j) {
        C0914a aVar = this.f598a;
        aVar.mo14039a(context, str, str2 + this.f600c, bVar, j);
    }

    /* renamed from: a */
    public void mo14091a(String str) {
        C0914a aVar = this.f598a;
        aVar.mo14041a(str + this.f600c);
    }

    /* renamed from: a */
    public void mo14089a(C0927g gVar, boolean z) {
        this.f598a.mo14040a(gVar, z);
    }

    /* renamed from: b */
    public void mo14093b(String str) {
        this.f600c = str;
    }
}
