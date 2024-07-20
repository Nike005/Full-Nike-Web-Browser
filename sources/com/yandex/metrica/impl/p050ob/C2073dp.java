package com.yandex.metrica.impl.p050ob;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import com.appnext.base.p078a.p081c.C4892d;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.utils.C2217b;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.slf4j.Marker;

/* renamed from: com.yandex.metrica.impl.ob.dp */
public class C2073dp implements Runnable {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final ServiceConnection f3550a = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    /* renamed from: b */
    private final Handler f3551b = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                C2073dp.this.mo17617e();
                try {
                    C2073dp.this.f3553d.unbindService(C2073dp.this.f3550a);
                } catch (Exception unused) {
                    YandexMetrica.getReporter(C2073dp.this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_unbind_has_thrown_exception");
                }
            }
        }
    };

    /* renamed from: c */
    private HashMap<String, C2088c> f3552c = new HashMap<String, C2088c>() {
        {
            put("p", new C2088c() {
                /* renamed from: a */
                public C2087b mo17624a(Uri uri, Socket socket) {
                    return new C2085a(uri, socket);
                }
            });
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Context f3553d;

    /* renamed from: e */
    private boolean f3554e;

    /* renamed from: f */
    private ServerSocket f3555f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final C2089dq f3556g = new C2089dq();
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C2091ds f3557h;

    /* renamed from: i */
    private Thread f3558i;

    /* renamed from: com.yandex.metrica.impl.ob.dp$b */
    abstract class C2087b {

        /* renamed from: b */
        Uri f3570b;

        /* renamed from: c */
        Socket f3571c;

        /* renamed from: a */
        public abstract void mo17631a();

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17632a(Throwable th) {
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17633b() {
        }

        /* JADX WARNING: type inference failed for: r2v0, types: [java.net.Socket, java.util.Map<java.lang.String, java.lang.String>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        C2087b(android.net.Uri r1, java.util.Map<java.lang.String, java.lang.String> r2) {
            /*
                r0 = this;
                r0.<init>()
                r0.f3570b = r1
                r0.f3571c = r2
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2073dp.C2087b.<init>(android.net.Uri, java.net.Socket):void");
        }

        /* renamed from: a */
        private static void m5439a(OutputStream outputStream) throws IOException {
            outputStream.write("\r\n".getBytes());
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17635a(String str, Map<String, String> map, byte[] bArr) {
            BufferedOutputStream bufferedOutputStream = null;
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(this.f3571c.getOutputStream());
                try {
                    bufferedOutputStream2.write(str.getBytes());
                    m5439a((OutputStream) bufferedOutputStream2);
                    for (Map.Entry next : map.entrySet()) {
                        m5440a((OutputStream) bufferedOutputStream2, (String) next.getKey(), (String) next.getValue());
                    }
                    m5440a((OutputStream) bufferedOutputStream2, "Content-Length", String.valueOf(bArr.length));
                    m5439a((OutputStream) bufferedOutputStream2);
                    bufferedOutputStream2.write(bArr);
                    bufferedOutputStream2.flush();
                    mo17633b();
                    C1897bk.m4645a((Closeable) bufferedOutputStream2);
                } catch (IOException e) {
                    e = e;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        mo17632a((Throwable) e);
                        C1897bk.m4645a((Closeable) bufferedOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        C1897bk.m4645a((Closeable) bufferedOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedOutputStream = bufferedOutputStream2;
                    C1897bk.m4645a((Closeable) bufferedOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                mo17632a((Throwable) e);
                C1897bk.m4645a((Closeable) bufferedOutputStream);
            }
        }

        /* renamed from: a */
        private static void m5440a(OutputStream outputStream, String str, String str2) throws IOException {
            outputStream.write((str + ": " + str2).getBytes());
            m5439a(outputStream);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dp$c */
    static abstract class C2088c {
        /* renamed from: a */
        public abstract C2087b mo17624a(Uri uri, Socket socket);

        C2088c() {
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.dp$a */
    class C2085a extends C2087b {
        /* JADX WARNING: type inference failed for: r3v0, types: [java.net.Socket, java.util.Map<java.lang.String, java.lang.String>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        C2085a(android.net.Uri r2, java.util.Map<java.lang.String, java.lang.String> r3) {
            /*
                r0 = this;
                com.yandex.metrica.impl.p050ob.C2073dp.this = r1
                r0.<init>(r2, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2073dp.C2085a.<init>(com.yandex.metrica.impl.ob.dp, android.net.Uri, java.net.Socket):void");
        }

        /* renamed from: a */
        public void mo17631a() {
            if (C2073dp.this.f3557h.mo17644b().equals(this.f3570b.getQueryParameter(C4892d.COLUMN_TYPE))) {
                mo17635a("HTTP/1.1 200 OK", (Map<String, String>) new HashMap<String, String>() {
                    {
                        put("Content-Type", "text/plain; charset=utf-8");
                        put("Access-Control-Allow-Origin", Marker.ANY_MARKER);
                        put("Access-Control-Allow-Methods", "GET");
                    }
                }, mo17634c());
            } else {
                YandexMetrica.getReporter(C2073dp.this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_request_with_wrong_token");
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17633b() {
            YandexMetrica.getReporter(C2073dp.this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_sync_succeed", (Map<String, Object>) C2073dp.m5412c(this.f3571c.getLocalPort()));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17632a(Throwable th) {
            YandexMetrica.getReporter(C2073dp.this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportError("socket_io_exception_during_sync", th);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public byte[] mo17634c() {
            try {
                return Base64.encode(new C2217b().mo17897a(C2073dp.this.f3556g.mo17636a().getBytes()), 0);
            } catch (JSONException unused) {
                return new byte[0];
            }
        }
    }

    C2073dp(Context context) {
        this.f3553d = context;
        C2174g.m5753a().mo17805a(this, C2188p.class, C2182k.m5767a(new C2181j<C2188p>() {
            /* renamed from: a */
            public void mo16931a(C2188p pVar) {
                C2073dp.this.f3556g.mo17637a(pVar.f3773a);
            }
        }).mo17813a(new C2179h<C2188p>() {
            /* renamed from: a */
            public boolean mo17625a(C2188p pVar) {
                return !C2073dp.this.f3553d.getPackageName().equals(pVar.f3774b);
            }
        }).mo17814a());
        C2174g.m5753a().mo17805a(this, C2186n.class, C2182k.m5767a(new C2181j<C2186n>() {
            /* renamed from: a */
            public void mo16931a(C2186n nVar) {
                C2073dp.this.f3556g.mo17638b(nVar.f3771a);
            }
        }).mo17814a());
        C2174g.m5753a().mo17805a(this, C2184l.class, C2182k.m5767a(new C2181j<C2184l>() {
            /* renamed from: a */
            public void mo16931a(C2184l lVar) {
                C2073dp.this.f3556g.mo17639c(lVar.f3769a);
            }
        }).mo17814a());
        C2174g.m5753a().mo17805a(this, C2185m.class, C2182k.m5767a(new C2181j<C2185m>() {
            /* renamed from: a */
            public void mo16931a(C2185m mVar) {
                C2073dp.this.f3556g.mo17640d(mVar.f3770a);
            }
        }).mo17814a());
        C2174g.m5753a().mo17805a(this, C2187o.class, C2182k.m5767a(new C2181j<C2187o>() {
            /* renamed from: a */
            public void mo16931a(C2187o oVar) {
                C2073dp.this.mo17613a(oVar.f3772a);
                C2073dp.this.mo17615c();
            }
        }).mo17814a());
    }

    /* renamed from: a */
    public void mo17612a() {
        if (this.f3554e) {
            mo17614b();
            Handler handler = this.f3551b;
            handler.sendMessageDelayed(handler.obtainMessage(100), this.f3557h.mo17643a() * 1000);
        }
    }

    /* renamed from: b */
    public void mo17614b() {
        this.f3551b.removeMessages(100);
    }

    /* renamed from: c */
    public synchronized void mo17615c() {
        if (!(this.f3554e || this.f3557h == null)) {
            this.f3554e = true;
            mo17616d();
            Thread thread = new Thread(this);
            this.f3558i = thread;
            thread.start();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17613a(C2091ds dsVar) {
        this.f3557h = dsVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17616d() {
        Intent intent = new Intent(this.f3553d, MetricaService.class);
        intent.setAction("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER");
        try {
            if (!this.f3553d.bindService(intent, this.f3550a, 1)) {
                YandexMetrica.getReporter(this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_failed");
            }
        } catch (Exception unused) {
            YandexMetrica.getReporter(this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_bind_has_thrown_exception");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo17617e() {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            r2.f3554e = r0     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            java.lang.Thread r0 = r2.f3558i     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r1 = 0
            if (r0 == 0) goto L_0x0010
            java.lang.Thread r0 = r2.f3558i     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.interrupt()     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.f3558i = r1     // Catch:{ IOException -> 0x0020, all -> 0x001d }
        L_0x0010:
            java.net.ServerSocket r0 = r2.f3555f     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            if (r0 == 0) goto L_0x001b
            java.net.ServerSocket r0 = r2.f3555f     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r0.close()     // Catch:{ IOException -> 0x0020, all -> 0x001d }
            r2.f3555f = r1     // Catch:{ IOException -> 0x0020, all -> 0x001d }
        L_0x001b:
            monitor-exit(r2)
            return
        L_0x001d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x0020:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2073dp.mo17617e():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.net.Socket] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae A[SYNTHETIC, Splitter:B:34:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bc A[SYNTHETIC, Splitter:B:43:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c3 A[SYNTHETIC, Splitter:B:49:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0008 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r9 = this;
            java.net.ServerSocket r0 = r9.mo17618f()
            r9.f3555f = r0
            if (r0 == 0) goto L_0x00c8
        L_0x0008:
            boolean r0 = r9.f3554e
            if (r0 == 0) goto L_0x00c8
            r0 = 0
            java.net.ServerSocket r1 = r9.f3555f     // Catch:{ IOException -> 0x00c0, all -> 0x00b6 }
            java.net.Socket r1 = r1.accept()     // Catch:{ IOException -> 0x00c0, all -> 0x00b6 }
            r2 = 1000(0x3e8, float:1.401E-42)
            r1.setSoTimeout(r2)     // Catch:{ all -> 0x00a9 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00a9 }
            r2.<init>()     // Catch:{ all -> 0x00a9 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00a9 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ all -> 0x00a9 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ all -> 0x00a9 }
            r4.<init>(r5)     // Catch:{ all -> 0x00a9 }
            r3.<init>(r4)     // Catch:{ all -> 0x00a9 }
            java.lang.String r4 = r3.readLine()     // Catch:{ all -> 0x00a7 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00a7 }
            if (r5 != 0) goto L_0x009a
            java.lang.String r5 = "GET /"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x00a7 }
            if (r5 == 0) goto L_0x004f
            r0 = 47
            int r0 = r4.indexOf(r0)     // Catch:{ all -> 0x00a7 }
            int r0 = r0 + 1
            r5 = 32
            int r5 = r4.indexOf(r5, r0)     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r4.substring(r0, r5)     // Catch:{ all -> 0x00a7 }
        L_0x004f:
            android.net.Uri r4 = android.net.Uri.parse(r0)     // Catch:{ all -> 0x00a7 }
        L_0x0053:
            java.lang.String r5 = r3.readLine()     // Catch:{ all -> 0x00a7 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00a7 }
            if (r6 != 0) goto L_0x0072
            java.lang.String r6 = ": "
            int r6 = r5.indexOf(r6)     // Catch:{ all -> 0x00a7 }
            r7 = 0
            java.lang.String r7 = r5.substring(r7, r6)     // Catch:{ all -> 0x00a7 }
            int r6 = r6 + 2
            java.lang.String r5 = r5.substring(r6)     // Catch:{ all -> 0x00a7 }
            r2.put(r7, r5)     // Catch:{ all -> 0x00a7 }
            goto L_0x0053
        L_0x0072:
            java.util.HashMap<java.lang.String, com.yandex.metrica.impl.ob.dp$c> r2 = r9.f3552c     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = r4.getPath()     // Catch:{ all -> 0x00a7 }
            java.lang.Object r2 = r2.get(r5)     // Catch:{ all -> 0x00a7 }
            com.yandex.metrica.impl.ob.dp$c r2 = (com.yandex.metrica.impl.p050ob.C2073dp.C2088c) r2     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x0088
            com.yandex.metrica.impl.ob.dp$b r0 = r2.mo17624a(r4, r1)     // Catch:{ all -> 0x00a7 }
            r0.mo17631a()     // Catch:{ all -> 0x00a7 }
            goto L_0x009a
        L_0x0088:
            android.content.Context r2 = r9.f3553d     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = "20799a27-fa80-4b36-b2db-0f8141f24180"
            com.yandex.metrica.IReporter r2 = com.yandex.metrica.YandexMetrica.getReporter(r2, r4)     // Catch:{ all -> 0x00a7 }
            java.lang.String r4 = "socket_request_to_unknown_path"
            com.yandex.metrica.impl.ob.dp$2 r5 = new com.yandex.metrica.impl.ob.dp$2     // Catch:{ all -> 0x00a7 }
            r5.<init>(r0)     // Catch:{ all -> 0x00a7 }
            r2.reportEvent((java.lang.String) r4, (java.util.Map<java.lang.String, java.lang.Object>) r5)     // Catch:{ all -> 0x00a7 }
        L_0x009a:
            r3.close()     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x0008
        L_0x00a4:
            goto L_0x0008
        L_0x00a7:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a9:
            r2 = move-exception
            r3 = r0
            r0 = r2
        L_0x00ac:
            if (r3 == 0) goto L_0x00b1
            r3.close()     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
        L_0x00b1:
            throw r0     // Catch:{ IOException -> 0x00b4, all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            goto L_0x00ba
        L_0x00b4:
            r0 = r1
            goto L_0x00c1
        L_0x00b6:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L_0x00ba:
            if (r1 == 0) goto L_0x00bf
            r1.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00bf:
            throw r0
        L_0x00c0:
        L_0x00c1:
            if (r0 == 0) goto L_0x0008
            r0.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x0008
        L_0x00c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2073dp.run():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public ServerSocket mo17618f() {
        Iterator<Integer> it = this.f3557h.mo17645c().iterator();
        ServerSocket serverSocket = null;
        Integer num = null;
        while (serverSocket == null && it.hasNext()) {
            try {
                Integer next = it.next();
                if (next != null) {
                    try {
                        serverSocket = mo17611a(next.intValue());
                    } catch (SocketException unused) {
                        num = next;
                        YandexMetrica.getReporter(this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", (Map<String, Object>) m5412c(num.intValue()));
                    } catch (IOException unused2) {
                    }
                }
                num = next;
            } catch (SocketException unused3) {
                YandexMetrica.getReporter(this.f3553d, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("socket_port_already_in_use", (Map<String, Object>) m5412c(num.intValue()));
            } catch (IOException unused4) {
            }
        }
        return serverSocket;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ServerSocket mo17611a(int i) throws IOException {
        return new ServerSocket(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static HashMap<String, Object> m5412c(int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("port", String.valueOf(i));
        return hashMap;
    }
}
