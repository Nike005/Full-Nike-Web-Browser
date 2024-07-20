package com.tappx.p048a;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.mopub.common.Constants;

/* renamed from: com.tappx.a.z3 */
public class C1714z3 {

    /* renamed from: com.tappx.a.z3$b */
    private static final class C1716b extends AsyncTask<String, Void, Uri> {

        /* renamed from: a */
        private final Context f2597a;

        /* renamed from: a */
        private String m3776a(String str) {
            return str;
        }

        /* renamed from: b */
        private boolean m3778b(Uri uri) {
            return "about".equalsIgnoreCase(uri.getScheme());
        }

        /* renamed from: c */
        private boolean m3779c(Uri uri) {
            String scheme = uri.getScheme();
            return Constants.HTTP.equalsIgnoreCase(scheme) || Constants.HTTPS.equalsIgnoreCase(scheme);
        }

        /* renamed from: d */
        private void m3780d(Uri uri) {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (C1588q3.m3285a(this.f2597a, intent)) {
                m3777a(intent);
            }
        }

        /* renamed from: e */
        private void m3781e(Uri uri) {
            m3780d(uri);
        }

        /* renamed from: f */
        private void m3782f(Uri uri) {
            if (!m3778b(uri)) {
                if (m3779c(uri)) {
                    m3780d(uri);
                } else {
                    m3781e(uri);
                }
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Uri doInBackground(String... strArr) {
            String a = m3776a(strArr[0]);
            if (a == null) {
                return null;
            }
            return Uri.parse(a);
        }

        private C1716b(Context context) {
            this.f2597a = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Uri uri) {
            if (uri != null) {
                m3782f(uri);
            }
        }

        /* renamed from: a */
        private void m3777a(Intent intent) {
            if (!(this.f2597a instanceof Activity)) {
                intent.addFlags(268435456);
            }
            try {
                this.f2597a.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16305a(Context context, String str) {
        C1646u4.m3480a(new C1716b(context), (P[]) new String[]{str});
    }
}
