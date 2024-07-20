package com.tappx.p048a;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.io.File;

/* renamed from: com.tappx.a.a4 */
public class C1315a4 {

    /* renamed from: a */
    private final Context f1601a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public MediaScannerConnection f1602b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String f1603c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public String f1604d;

    /* renamed from: e */
    private final MediaScannerConnection.MediaScannerConnectionClient f1605e = new C1316a();

    /* renamed from: com.tappx.a.a4$a */
    class C1316a implements MediaScannerConnection.MediaScannerConnectionClient {
        C1316a() {
        }

        public void onMediaScannerConnected() {
            if (C1315a4.this.f1602b != null) {
                C1315a4.this.f1602b.scanFile(C1315a4.this.f1603c, C1315a4.this.f1604d);
            }
        }

        public void onScanCompleted(String str, Uri uri) {
            if (C1315a4.this.f1602b != null) {
                C1315a4.this.f1602b.disconnect();
            }
        }
    }

    public C1315a4(Context context) {
        this.f1601a = context.getApplicationContext();
    }

    /* renamed from: a */
    public void mo15529a(String str, String str2) {
        this.f1603c = str;
        this.f1604d = str2;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.f1601a, this.f1605e);
        this.f1602b = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    /* renamed from: a */
    public void mo15528a(File file, String str) {
        mo15529a(file.getAbsolutePath(), str);
    }
}
