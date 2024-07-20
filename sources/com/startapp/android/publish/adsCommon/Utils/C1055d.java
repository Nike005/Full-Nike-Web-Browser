package com.startapp.android.publish.adsCommon.Utils;

import com.startapp.common.C1301e;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.text.Typography;
import org.slf4j.Marker;

/* renamed from: com.startapp.android.publish.adsCommon.Utils.d */
/* compiled from: StartAppSDK */
public class C1055d extends C1056e {

    /* renamed from: a */
    private List<NameValueObject> f984a;

    public C1055d() {
        this.f984a = null;
        this.f984a = new ArrayList();
    }

    /* renamed from: a */
    public void mo14627a(String str, Object obj, boolean z, boolean z2) {
        if (z && obj == null) {
            throw new C1301e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (obj != null && !obj.toString().equals("")) {
            try {
                NameValueObject nameValueObject = new NameValueObject();
                nameValueObject.setName(str);
                String obj2 = obj.toString();
                if (z2) {
                    obj2 = URLEncoder.encode(obj2, "UTF-8");
                }
                nameValueObject.setValue(obj2);
                this.f984a.add(nameValueObject);
            } catch (UnsupportedEncodingException e) {
                if (z) {
                    throw new C1301e("failed encoding value: [" + obj + "]", e);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo14628a(String str, Set<String> set, boolean z, boolean z2) {
        if (z && set == null) {
            throw new C1301e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (set != null) {
            NameValueObject nameValueObject = new NameValueObject();
            nameValueObject.setName(str);
            HashSet hashSet = new HashSet();
            for (String next : set) {
                if (z2) {
                    try {
                        next = URLEncoder.encode(next, "UTF-8");
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                hashSet.add(next);
            }
            if (!z || hashSet.size() != 0) {
                nameValueObject.setValueSet(hashSet);
                this.f984a.add(nameValueObject);
                return;
            }
            throw new C1301e("failed encoding value: [" + set + "]", (Throwable) null);
        }
    }

    public String toString() {
        Set<String> valueSet;
        StringBuilder sb = new StringBuilder();
        if (this.f984a == null) {
            return sb.toString();
        }
        sb.append('?');
        for (NameValueObject next : this.f984a) {
            if (next.getValue() != null) {
                sb.append(next.getName());
                sb.append('=');
                sb.append(next.getValue());
                sb.append(Typography.amp);
            } else if (!(next.getValueSet() == null || (valueSet = next.getValueSet()) == null)) {
                for (String append : valueSet) {
                    sb.append(next.getName());
                    sb.append('=');
                    sb.append(append);
                    sb.append(Typography.amp);
                }
            }
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().replace(Marker.ANY_NON_NULL_MARKER, "%20");
    }
}
