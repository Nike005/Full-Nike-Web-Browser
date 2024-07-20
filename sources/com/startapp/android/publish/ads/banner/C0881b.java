package com.startapp.android.publish.ads.banner;

import android.content.Context;
import android.util.AttributeSet;

/* renamed from: com.startapp.android.publish.ads.banner.b */
/* compiled from: StartAppSDK */
class C0881b {

    /* renamed from: a */
    private Context f466a;

    /* renamed from: b */
    private String f467b;

    C0881b(Context context, AttributeSet attributeSet) {
        this.f466a = context;
        this.f467b = m589a(attributeSet, "adTag");
    }

    /* renamed from: a */
    private String m589a(AttributeSet attributeSet, String str) {
        String str2;
        try {
            int attributeResourceValue = attributeSet.getAttributeResourceValue((String) null, str, -1);
            if (attributeResourceValue != -1) {
                str2 = this.f466a.getResources().getString(attributeResourceValue);
            } else {
                str2 = attributeSet.getAttributeValue((String) null, str);
            }
            return str2;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public String mo13904a() {
        return this.f467b;
    }
}
