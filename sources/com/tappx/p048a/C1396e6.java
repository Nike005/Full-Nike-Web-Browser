package com.tappx.p048a;

import acr.browser.lightning.utils.UrlUtils;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tappx.p048a.C1445h5;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.tappx.a.e6 */
public class C1396e6 implements C1445h5 {

    /* renamed from: a */
    private final Map<String, C1397a> f1789a;

    /* renamed from: b */
    private long f1790b;

    /* renamed from: c */
    private final C1399c f1791c;

    /* renamed from: d */
    private final int f1792d;

    /* renamed from: com.tappx.a.e6$c */
    public interface C1399c {
        /* renamed from: a */
        File mo15759a();
    }

    public C1396e6(C1399c cVar, int i) {
        this.f1789a = new LinkedHashMap(16, 0.75f, true);
        this.f1790b = 0;
        this.f1791c = cVar;
        this.f1792d = i;
    }

    /* renamed from: d */
    private String m2586d(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    /* renamed from: e */
    private void m2587e(String str) {
        C1397a remove = this.f1789a.remove(str);
        if (remove != null) {
            this.f1790b -= remove.f1793a;
        }
    }

    /* renamed from: a */
    public synchronized C1445h5.C1446a mo15747a(String str) {
        C1398b bVar;
        C1397a aVar = this.f1789a.get(str);
        if (aVar == null) {
            return null;
        }
        File b = mo15751b(str);
        try {
            bVar = new C1398b(new BufferedInputStream(mo15748a(b)), b.length());
            C1397a a = C1397a.m2595a(bVar);
            if (!TextUtils.equals(str, a.f1794b)) {
                C1318a6.m2233b("%s: key=%s, found=%s", b.getAbsolutePath(), str, a.f1794b);
                m2587e(str);
                bVar.close();
                return null;
            }
            C1445h5.C1446a a2 = aVar.mo15754a(m2580a(bVar, bVar.mo15756a()));
            bVar.close();
            return a2;
        } catch (IOException e) {
            C1318a6.m2233b("%s: %s", b.getAbsolutePath(), e.toString());
            mo15753c(str);
            return null;
        } catch (Throwable th) {
            bVar.close();
            throw th;
        }
    }

    /* renamed from: b */
    public File mo15751b(String str) {
        return new File(this.f1791c.mo15759a(), m2586d(str));
    }

    /* renamed from: c */
    public synchronized void mo15753c(String str) {
        boolean delete = mo15751b(str).delete();
        m2587e(str);
        if (!delete) {
            C1318a6.m2233b("Could not delete cache entry for key=%s, filename=%s", str, m2586d(str));
        }
    }

    /* renamed from: b */
    private void m2583b() {
        if (!this.f1791c.mo15759a().exists()) {
            C1318a6.m2233b("Re-initializing cache after external clearing.", new Object[0]);
            this.f1789a.clear();
            this.f1790b = 0;
            mo15749a();
        }
    }

    /* renamed from: com.tappx.a.e6$b */
    static class C1398b extends FilterInputStream {

        /* renamed from: a */
        private final long f1801a;

        /* renamed from: b */
        private long f1802b;

        C1398b(InputStream inputStream, long j) {
            super(inputStream);
            this.f1801a = j;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public long mo15756a() {
            return this.f1801a - this.f1802b;
        }

        public int read() {
            int read = super.read();
            if (read != -1) {
                this.f1802b++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.f1802b += (long) read;
            }
            return read;
        }
    }

    /* renamed from: com.tappx.a.e6$a */
    static class C1397a {

        /* renamed from: a */
        long f1793a;

        /* renamed from: b */
        final String f1794b;

        /* renamed from: c */
        final String f1795c;

        /* renamed from: d */
        final long f1796d;

        /* renamed from: e */
        final long f1797e;

        /* renamed from: f */
        final long f1798f;

        /* renamed from: g */
        final long f1799g;

        /* renamed from: h */
        final List<C1528m5> f1800h;

        private C1397a(String str, String str2, long j, long j2, long j3, long j4, List<C1528m5> list) {
            this.f1794b = str;
            this.f1795c = "".equals(str2) ? null : str2;
            this.f1796d = j;
            this.f1797e = j2;
            this.f1798f = j3;
            this.f1799g = j4;
            this.f1800h = list;
        }

        /* renamed from: a */
        private static List<C1528m5> m2596a(C1445h5.C1446a aVar) {
            List<C1528m5> list = aVar.f1922h;
            if (list != null) {
                return list;
            }
            return C1416f6.m2683a(aVar.f1921g);
        }

        /* renamed from: a */
        static C1397a m2595a(C1398b bVar) {
            if (C1396e6.m2581b((InputStream) bVar) == 538247942) {
                return new C1397a(C1396e6.m2582b(bVar), C1396e6.m2582b(bVar), C1396e6.m2584c((InputStream) bVar), C1396e6.m2584c((InputStream) bVar), C1396e6.m2584c((InputStream) bVar), C1396e6.m2584c((InputStream) bVar), C1396e6.m2574a(bVar));
            }
            throw new IOException();
        }

        C1397a(String str, C1445h5.C1446a aVar) {
            this(str, aVar.f1916b, aVar.f1917c, aVar.f1918d, aVar.f1919e, aVar.f1920f, m2596a(aVar));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1445h5.C1446a mo15754a(byte[] bArr) {
            C1445h5.C1446a aVar = new C1445h5.C1446a();
            aVar.f1915a = bArr;
            aVar.f1916b = this.f1795c;
            aVar.f1917c = this.f1796d;
            aVar.f1918d = this.f1797e;
            aVar.f1919e = this.f1798f;
            aVar.f1920f = this.f1799g;
            aVar.f1921g = C1416f6.m2684a(this.f1800h);
            aVar.f1922h = Collections.unmodifiableList(this.f1800h);
            return aVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15755a(OutputStream outputStream) {
            try {
                C1396e6.m2575a(outputStream, 538247942);
                C1396e6.m2577a(outputStream, this.f1794b);
                C1396e6.m2577a(outputStream, this.f1795c == null ? "" : this.f1795c);
                C1396e6.m2576a(outputStream, this.f1796d);
                C1396e6.m2576a(outputStream, this.f1797e);
                C1396e6.m2576a(outputStream, this.f1798f);
                C1396e6.m2576a(outputStream, this.f1799g);
                C1396e6.m2579a(this.f1800h, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                C1318a6.m2233b(UrlUtils.QUERY_PLACE_HOLDER, e.toString());
                return false;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public OutputStream mo15752b(File file) {
        return new FileOutputStream(file);
    }

    /* renamed from: b */
    static int m2581b(InputStream inputStream) {
        return (m2573a(inputStream) << 24) | (m2573a(inputStream) << 0) | 0 | (m2573a(inputStream) << 8) | (m2573a(inputStream) << 16);
    }

    /* renamed from: c */
    private void m2585c() {
        if (this.f1790b >= ((long) this.f1792d)) {
            if (C1318a6.f1610b) {
                C1318a6.m2235d("Pruning old cache entries.", new Object[0]);
            }
            long j = this.f1790b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, C1397a>> it = this.f1789a.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                C1397a aVar = (C1397a) it.next().getValue();
                if (mo15751b(aVar.f1794b).delete()) {
                    this.f1790b -= aVar.f1793a;
                } else {
                    String str = aVar.f1794b;
                    C1318a6.m2233b("Could not delete cache entry for key=%s, filename=%s", str, m2586d(str));
                }
                it.remove();
                i++;
                if (((float) this.f1790b) < ((float) this.f1792d) * 0.9f) {
                    break;
                }
            }
            if (C1318a6.f1610b) {
                C1318a6.m2235d("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.f1790b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    /* renamed from: b */
    static String m2582b(C1398b bVar) {
        return new String(m2580a(bVar, m2584c((InputStream) bVar)), "UTF-8");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0057 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo15749a() {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tappx.a.e6$c r0 = r9.f1791c     // Catch:{ all -> 0x005f }
            java.io.File r0 = r0.mo15759a()     // Catch:{ all -> 0x005f }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x005f }
            r2 = 0
            if (r1 != 0) goto L_0x0024
            boolean r1 = r0.mkdirs()     // Catch:{ all -> 0x005f }
            if (r1 != 0) goto L_0x0022
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x005f }
            java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ all -> 0x005f }
            r1[r2] = r0     // Catch:{ all -> 0x005f }
            java.lang.String r0 = "Unable to create cache dir %s"
            com.tappx.p048a.C1318a6.m2234c(r0, r1)     // Catch:{ all -> 0x005f }
        L_0x0022:
            monitor-exit(r9)
            return
        L_0x0024:
            java.io.File[] r0 = r0.listFiles()     // Catch:{ all -> 0x005f }
            if (r0 != 0) goto L_0x002c
            monitor-exit(r9)
            return
        L_0x002c:
            int r1 = r0.length     // Catch:{ all -> 0x005f }
        L_0x002d:
            if (r2 >= r1) goto L_0x005d
            r3 = r0[r2]     // Catch:{ all -> 0x005f }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0057 }
            com.tappx.a.e6$b r6 = new com.tappx.a.e6$b     // Catch:{ IOException -> 0x0057 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0057 }
            java.io.InputStream r8 = r9.mo15748a((java.io.File) r3)     // Catch:{ IOException -> 0x0057 }
            r7.<init>(r8)     // Catch:{ IOException -> 0x0057 }
            r6.<init>(r7, r4)     // Catch:{ IOException -> 0x0057 }
            com.tappx.a.e6$a r7 = com.tappx.p048a.C1396e6.C1397a.m2595a((com.tappx.p048a.C1396e6.C1398b) r6)     // Catch:{ all -> 0x0052 }
            r7.f1793a = r4     // Catch:{ all -> 0x0052 }
            java.lang.String r4 = r7.f1794b     // Catch:{ all -> 0x0052 }
            r9.m2578a((java.lang.String) r4, (com.tappx.p048a.C1396e6.C1397a) r7)     // Catch:{ all -> 0x0052 }
            r6.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005a
        L_0x0052:
            r4 = move-exception
            r6.close()     // Catch:{ IOException -> 0x0057 }
            throw r4     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            r3.delete()     // Catch:{ all -> 0x005f }
        L_0x005a:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x005d:
            monitor-exit(r9)
            return
        L_0x005f:
            r0 = move-exception
            monitor-exit(r9)
            goto L_0x0063
        L_0x0062:
            throw r0
        L_0x0063:
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1396e6.mo15749a():void");
    }

    /* renamed from: c */
    static long m2584c(InputStream inputStream) {
        return ((((long) m2573a(inputStream)) & 255) << 0) | 0 | ((((long) m2573a(inputStream)) & 255) << 8) | ((((long) m2573a(inputStream)) & 255) << 16) | ((((long) m2573a(inputStream)) & 255) << 24) | ((((long) m2573a(inputStream)) & 255) << 32) | ((((long) m2573a(inputStream)) & 255) << 40) | ((((long) m2573a(inputStream)) & 255) << 48) | ((255 & ((long) m2573a(inputStream))) << 56);
    }

    public C1396e6(C1399c cVar) {
        this(cVar, 5242880);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        if (r0.delete() == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0068, code lost:
        com.tappx.p048a.C1318a6.m2233b("Could not clean up file %s", r0.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0075, code lost:
        m2583b();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0062 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo15750a(java.lang.String r8, com.tappx.p048a.C1445h5.C1446a r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            long r0 = r7.f1790b     // Catch:{ all -> 0x007a }
            byte[] r2 = r9.f1915a     // Catch:{ all -> 0x007a }
            int r3 = r2.length     // Catch:{ all -> 0x007a }
            long r3 = (long) r3     // Catch:{ all -> 0x007a }
            long r0 = r0 + r3
            int r3 = r7.f1792d     // Catch:{ all -> 0x007a }
            long r4 = (long) r3     // Catch:{ all -> 0x007a }
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x001d
            int r0 = r2.length     // Catch:{ all -> 0x007a }
            float r0 = (float) r0
            float r1 = (float) r3
            r2 = 1063675494(0x3f666666, float:0.9)
            float r1 = r1 * r2
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x001d
            monitor-exit(r7)
            return
        L_0x001d:
            java.io.File r0 = r7.mo15751b((java.lang.String) r8)     // Catch:{ all -> 0x007a }
            r1 = 0
            r2 = 1
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0062 }
            java.io.OutputStream r4 = r7.mo15752b((java.io.File) r0)     // Catch:{ IOException -> 0x0062 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0062 }
            com.tappx.a.e6$a r4 = new com.tappx.a.e6$a     // Catch:{ IOException -> 0x0062 }
            r4.<init>(r8, r9)     // Catch:{ IOException -> 0x0062 }
            boolean r5 = r4.mo15755a((java.io.OutputStream) r3)     // Catch:{ IOException -> 0x0062 }
            if (r5 == 0) goto L_0x004c
            byte[] r9 = r9.f1915a     // Catch:{ IOException -> 0x0062 }
            r3.write(r9)     // Catch:{ IOException -> 0x0062 }
            r3.close()     // Catch:{ IOException -> 0x0062 }
            long r5 = r0.length()     // Catch:{ IOException -> 0x0062 }
            r4.f1793a = r5     // Catch:{ IOException -> 0x0062 }
            r7.m2578a((java.lang.String) r8, (com.tappx.p048a.C1396e6.C1397a) r4)     // Catch:{ IOException -> 0x0062 }
            r7.m2585c()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0078
        L_0x004c:
            r3.close()     // Catch:{ IOException -> 0x0062 }
            java.lang.String r8 = "Failed to write header for %s"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch:{ IOException -> 0x0062 }
            java.lang.String r3 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0062 }
            r9[r1] = r3     // Catch:{ IOException -> 0x0062 }
            com.tappx.p048a.C1318a6.m2233b(r8, r9)     // Catch:{ IOException -> 0x0062 }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ IOException -> 0x0062 }
            r8.<init>()     // Catch:{ IOException -> 0x0062 }
            throw r8     // Catch:{ IOException -> 0x0062 }
        L_0x0062:
            boolean r8 = r0.delete()     // Catch:{ all -> 0x007a }
            if (r8 != 0) goto L_0x0075
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch:{ all -> 0x007a }
            java.lang.String r9 = r0.getAbsolutePath()     // Catch:{ all -> 0x007a }
            r8[r1] = r9     // Catch:{ all -> 0x007a }
            java.lang.String r9 = "Could not clean up file %s"
            com.tappx.p048a.C1318a6.m2233b(r9, r8)     // Catch:{ all -> 0x007a }
        L_0x0075:
            r7.m2583b()     // Catch:{ all -> 0x007a }
        L_0x0078:
            monitor-exit(r7)
            return
        L_0x007a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1396e6.mo15750a(java.lang.String, com.tappx.a.h5$a):void");
    }

    /* renamed from: a */
    private void m2578a(String str, C1397a aVar) {
        if (!this.f1789a.containsKey(str)) {
            this.f1790b += aVar.f1793a;
        } else {
            this.f1790b += aVar.f1793a - this.f1789a.get(str).f1793a;
        }
        this.f1789a.put(str, aVar);
    }

    /* renamed from: a */
    static byte[] m2580a(C1398b bVar, long j) {
        long a = bVar.mo15756a();
        if (j >= 0 && j <= a) {
            int i = (int) j;
            if (((long) i) == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(bVar).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public InputStream mo15748a(File file) {
        return new FileInputStream(file);
    }

    /* renamed from: a */
    private static int m2573a(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    /* renamed from: a */
    static void m2575a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    /* renamed from: a */
    static void m2576a(OutputStream outputStream, long j) {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    /* renamed from: a */
    static void m2577a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        m2576a(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    /* renamed from: a */
    static void m2579a(List<C1528m5> list, OutputStream outputStream) {
        if (list != null) {
            m2575a(outputStream, list.size());
            for (C1528m5 next : list) {
                m2577a(outputStream, next.mo15969a());
                m2577a(outputStream, next.mo15970b());
            }
            return;
        }
        m2575a(outputStream, 0);
    }

    /* renamed from: a */
    static List<C1528m5> m2574a(C1398b bVar) {
        int b = m2581b((InputStream) bVar);
        if (b >= 0) {
            List<C1528m5> emptyList = b == 0 ? Collections.emptyList() : new ArrayList<>();
            for (int i = 0; i < b; i++) {
                emptyList.add(new C1528m5(m2582b(bVar).intern(), m2582b(bVar).intern()));
            }
            return emptyList;
        }
        throw new IOException("readHeaderList size=" + b);
    }
}
