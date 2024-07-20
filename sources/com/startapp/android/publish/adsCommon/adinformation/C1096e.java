package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import android.graphics.Bitmap;
import com.startapp.android.publish.adsCommon.Utils.C1049a;
import com.startapp.common.C1249a;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.adinformation.e */
/* compiled from: StartAppSDK */
public class C1096e implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private transient Bitmap f1081a;

    /* renamed from: b */
    private transient Bitmap f1082b;

    /* renamed from: c */
    private transient Bitmap f1083c = null;
    private int height = 1;
    private String imageFallbackUrl = "";
    private String imageUrlSecured = "";
    private String name;
    private int width = 1;

    private C1096e() {
    }

    /* renamed from: a */
    public String mo14734a() {
        return this.name;
    }

    /* renamed from: a */
    public Bitmap mo14733a(Context context) {
        if (this.f1083c == null) {
            Bitmap f = mo14745f();
            this.f1083c = f;
            if (f == null) {
                this.f1083c = mo14739b(context);
            }
        }
        return this.f1083c;
    }

    /* renamed from: b */
    public int mo14738b() {
        return this.width;
    }

    /* renamed from: c */
    public int mo14742c() {
        return this.height;
    }

    /* renamed from: a */
    public void mo14735a(int i) {
        this.width = i;
    }

    /* renamed from: b */
    public void mo14740b(int i) {
        this.height = i;
    }

    /* renamed from: d */
    public String mo14743d() {
        String str = this.imageUrlSecured;
        return str != null ? str : "";
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo14744e() {
        mo14736a((Bitmap) null);
        new C1249a(mo14743d(), new C1249a.C1252a() {
            /* renamed from: a */
            public void mo13933a(Bitmap bitmap, int i) {
                C1096e.this.mo14736a(bitmap);
            }
        }, 0).mo15438a();
    }

    /* renamed from: a */
    public void mo14737a(String str) {
        this.imageFallbackUrl = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14741b(String str) {
        this.name = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14736a(Bitmap bitmap) {
        this.f1081a = bitmap;
        if (bitmap != null) {
            this.f1083c = bitmap;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public Bitmap mo14745f() {
        return this.f1081a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public String mo14746g() {
        return this.imageFallbackUrl;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Bitmap mo14739b(Context context) {
        if (this.f1082b == null) {
            this.f1082b = C1049a.m1129a(context, mo14746g());
        }
        return this.f1082b;
    }

    /* renamed from: c */
    public static C1096e m1285c(String str) {
        C1096e eVar = new C1096e();
        eVar.mo14741b(str);
        return eVar;
    }
}
