package com.tappx.p048a;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* renamed from: com.tappx.a.r3 */
public class C1598r3 extends AsyncTask<String, Void, Boolean> {

    /* renamed from: a */
    private final Context f2244a;

    /* renamed from: b */
    private final C1600b f2245b;

    /* renamed from: c */
    private final C1601c f2246c;

    /* renamed from: com.tappx.a.r3$b */
    interface C1600b {
        /* renamed from: a */
        void mo15920a();

        /* renamed from: b */
        void mo15921b();
    }

    /* renamed from: com.tappx.a.r3$c */
    private static final class C1601c {
        private C1601c() {
        }

        /* renamed from: a */
        private String m3319a(String str) {
            return str;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public File m3317a(Context context, String str, File file) {
            FileOutputStream fileOutputStream;
            BufferedInputStream bufferedInputStream;
            File file2;
            URI create = URI.create(str);
            BufferedInputStream bufferedInputStream2 = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(m3319a(str)).openConnection();
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setReadTimeout(15000);
                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (!TextUtils.isEmpty(headerField)) {
                        create = URI.create(headerField);
                    }
                    file2 = new File(file, m3320a(create, httpURLConnection.getHeaderFields()));
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception unused) {
                    fileOutputStream = null;
                    m3321a((Closeable) bufferedInputStream);
                    m3321a((Closeable) fileOutputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                    bufferedInputStream2 = bufferedInputStream;
                    m3321a((Closeable) bufferedInputStream2);
                    m3321a((Closeable) fileOutputStream);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            new C1315a4(context).mo15528a(file2, (String) null);
                            m3321a((Closeable) bufferedInputStream);
                            m3321a((Closeable) fileOutputStream);
                            return file2;
                        }
                    }
                } catch (Exception unused2) {
                    m3321a((Closeable) bufferedInputStream);
                    m3321a((Closeable) fileOutputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream2 = bufferedInputStream;
                    m3321a((Closeable) bufferedInputStream2);
                    m3321a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Exception unused3) {
                fileOutputStream = null;
                bufferedInputStream = null;
                m3321a((Closeable) bufferedInputStream);
                m3321a((Closeable) fileOutputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                m3321a((Closeable) bufferedInputStream2);
                m3321a((Closeable) fileOutputStream);
                throw th;
            }
        }

        /* renamed from: a */
        private void m3321a(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
        }

        /* renamed from: a */
        private String m3320a(URI uri, Map<String, List<String>> map) {
            String path = uri.getPath();
            if (path == null || map == null) {
                return null;
            }
            String name = new File(path).getName();
            List list = map.get("Content-Type");
            if (list == null || list.isEmpty()) {
                return name;
            }
            if (list.get(0) == null) {
                return name;
            }
            for (String str : ((String) list.get(0)).split(";")) {
                if (str.contains("image/")) {
                    String str2 = "." + str.split("/")[1];
                    if (name.endsWith(str2)) {
                        return name;
                    }
                    return name + str2;
                }
            }
            return name;
        }
    }

    public C1598r3(Context context, C1600b bVar) {
        this(context, bVar, new C1601c());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Boolean doInBackground(String... strArr) {
        boolean z = false;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            return false;
        }
        if (this.f2246c.m3317a(this.f2244a, strArr[0], m3312a()) != null) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public C1598r3(Context context, C1600b bVar, C1601c cVar) {
        this.f2244a = context;
        this.f2245b = bVar;
        this.f2246c = cVar;
    }

    /* renamed from: a */
    private File m3312a() {
        File file = new File(Environment.getExternalStorageDirectory(), "Pictures");
        file.mkdirs();
        return file;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        if (bool.booleanValue()) {
            this.f2245b.mo15920a();
        } else {
            this.f2245b.mo15921b();
        }
    }
}
