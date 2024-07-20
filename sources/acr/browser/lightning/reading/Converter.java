package acr.browser.lightning.reading;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class Converter {
    private static final String ISO = "ISO-8859-1";

    /* renamed from: K2 */
    private static final int f4056K2 = 2048;
    private static final String TAG = "Converter";
    private static final String UTF8 = "UTF-8";
    private String encoding;
    private int maxBytes = 500000;
    private String url;

    public Converter(String str) {
        this.url = str;
    }

    public Converter() {
    }

    public Converter setMaxBytes(int i) {
        this.maxBytes = i;
        return this;
    }

    public static String extractEncoding(String str) {
        String str2 = "";
        for (String trim : str != null ? str.split(";") : new String[0]) {
            String lowerCase = trim.trim().toLowerCase(Locale.getDefault());
            if (lowerCase.startsWith("charset=")) {
                str2 = lowerCase.substring(8);
            }
        }
        return str2.isEmpty() ? "ISO-8859-1" : str2;
    }

    public String getEncoding() {
        String str = this.encoding;
        if (str == null) {
            return "";
        }
        return str.toLowerCase(Locale.getDefault());
    }

    public String streamToString(InputStream inputStream) {
        return streamToString(inputStream, this.maxBytes, this.encoding);
    }

    public String streamToString(InputStream inputStream, String str) {
        return streamToString(inputStream, this.maxBytes, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00fd A[SYNTHETIC, Splitter:B:55:0x00fd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String streamToString(java.io.InputStream r7, int r8, java.lang.String r9) {
        /*
            r6 = this;
            java.lang.String r0 = "Converter"
            r6.encoding = r9
            java.lang.String r1 = "UTF-8"
            if (r9 == 0) goto L_0x000e
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x0010
        L_0x000e:
            r6.encoding = r1
        L_0x0010:
            r9 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00d0 }
            r3 = 2048(0x800, float:2.87E-42)
            r2.<init>(r7, r3)     // Catch:{ IOException -> 0x00d0 }
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r7.<init>()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r9 = 4096(0x1000, float:5.74E-42)
            r2.mark(r9)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            java.lang.String r9 = "charset="
            java.lang.String r4 = r6.encoding     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            java.lang.String r9 = detectCharset(r9, r7, r2, r4)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            if (r9 == 0) goto L_0x002f
            r6.encoding = r9     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            goto L_0x0046
        L_0x002f:
            java.lang.String r9 = "no charset found in first stage"
            android.util.Log.d(r0, r9)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            java.lang.String r9 = "encoding="
            java.lang.String r4 = r6.encoding     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            java.lang.String r9 = detectCharset(r9, r7, r2, r4)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            if (r9 == 0) goto L_0x0041
            r6.encoding = r9     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            goto L_0x0046
        L_0x0041:
            java.lang.String r9 = "no charset found in second stage"
            android.util.Log.d(r0, r9)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
        L_0x0046:
            java.lang.String r9 = r6.encoding     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            boolean r9 = java.nio.charset.Charset.isSupported(r9)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            if (r9 == 0) goto L_0x004f
            goto L_0x0086
        L_0x004f:
            java.io.UnsupportedEncodingException r9 = new java.io.UnsupportedEncodingException     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            java.lang.String r4 = r6.encoding     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            r9.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x0057 }
            throw r9     // Catch:{ UnsupportedEncodingException -> 0x0057 }
        L_0x0057:
            r9 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r4.<init>()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r5 = "Using default encoding:UTF-8 problem:"
            r4.append(r5)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r9 = r9.getMessage()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r4.append(r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r9 = " encoding:"
            r4.append(r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r9 = r6.encoding     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r4.append(r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r9 = 32
            r4.append(r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r9 = r6.url     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r4.append(r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r9 = r4.toString()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            android.util.Log.d(r0, r9)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r6.encoding = r1     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
        L_0x0086:
            int r9 = r7.size()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            byte[] r1 = new byte[r3]     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
        L_0x008c:
            if (r9 < r8) goto L_0x00ad
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r9.<init>()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r1 = "Maxbyte of "
            r9.append(r1)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r9.append(r8)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r8 = " exceeded! Maybe html is now broken but try it nevertheless. Url: "
            r9.append(r8)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r8 = r6.url     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r9.append(r8)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r8 = r9.toString()     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            android.util.Log.d(r0, r8)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            goto L_0x00b3
        L_0x00ad:
            int r3 = r2.read(r1)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            if (r3 >= 0) goto L_0x00c2
        L_0x00b3:
            java.lang.String r8 = r6.encoding     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            java.lang.String r7 = r7.toString(r8)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            r2.close()     // Catch:{ Exception -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00c1:
            return r7
        L_0x00c2:
            int r9 = r9 + r3
            r4 = 0
            r7.write(r1, r4, r3)     // Catch:{ IOException -> 0x00cb, all -> 0x00c8 }
            goto L_0x008c
        L_0x00c8:
            r7 = move-exception
            r9 = r2
            goto L_0x00fb
        L_0x00cb:
            r7 = move-exception
            r9 = r2
            goto L_0x00d1
        L_0x00ce:
            r7 = move-exception
            goto L_0x00fb
        L_0x00d0:
            r7 = move-exception
        L_0x00d1:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r8.<init>()     // Catch:{ all -> 0x00ce }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00ce }
            r8.append(r7)     // Catch:{ all -> 0x00ce }
            java.lang.String r7 = " url:"
            r8.append(r7)     // Catch:{ all -> 0x00ce }
            java.lang.String r7 = r6.url     // Catch:{ all -> 0x00ce }
            r8.append(r7)     // Catch:{ all -> 0x00ce }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x00ce }
            android.util.Log.e(r0, r7)     // Catch:{ all -> 0x00ce }
            if (r9 == 0) goto L_0x00f8
            r9.close()     // Catch:{ Exception -> 0x00f4 }
            goto L_0x00f8
        L_0x00f4:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00f8:
            java.lang.String r7 = ""
            return r7
        L_0x00fb:
            if (r9 == 0) goto L_0x0105
            r9.close()     // Catch:{ Exception -> 0x0101 }
            goto L_0x0105
        L_0x0101:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0105:
            goto L_0x0107
        L_0x0106:
            throw r7
        L_0x0107:
            goto L_0x0106
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.reading.Converter.streamToString(java.io.InputStream, int, java.lang.String):java.lang.String");
    }

    private static String detectCharset(String str, ByteArrayOutputStream byteArrayOutputStream, BufferedInputStream bufferedInputStream, String str2) throws IOException {
        int i;
        byte[] bArr = new byte[2048];
        int i2 = 0;
        while (i2 < 2048) {
            int read = bufferedInputStream.read(bArr);
            if (read < 0) {
                break;
            }
            i2 += read;
            byteArrayOutputStream.write(bArr, 0, read);
        }
        String byteArrayOutputStream2 = byteArrayOutputStream.toString(str2);
        int indexOf = byteArrayOutputStream2.indexOf(str);
        int length = str.length();
        if (indexOf <= 0) {
            return null;
        }
        int i3 = indexOf + length;
        char charAt = byteArrayOutputStream2.charAt(i3);
        if (charAt == '\'') {
            indexOf++;
            i = byteArrayOutputStream2.indexOf(39, indexOf + length);
        } else if (charAt == '\"') {
            indexOf++;
            i = byteArrayOutputStream2.indexOf(34, indexOf + length);
        } else {
            int indexOf2 = byteArrayOutputStream2.indexOf(34, i3);
            int i4 = Integer.MAX_VALUE;
            if (indexOf2 < 0) {
                indexOf2 = Integer.MAX_VALUE;
            }
            int indexOf3 = byteArrayOutputStream2.indexOf(32, i3);
            if (indexOf3 >= 0) {
                i4 = indexOf3;
            }
            int min = Math.min(indexOf2, i4);
            int indexOf4 = byteArrayOutputStream2.indexOf(39, i3);
            i = indexOf4 > 0 ? Math.min(min, indexOf4) : min;
        }
        int i5 = indexOf + length;
        if (i <= i5 || i >= i5 + 40) {
            return null;
        }
        String encodingCleanup = SHelper.encodingCleanup(byteArrayOutputStream2.substring(i5, i));
        try {
            bufferedInputStream.reset();
            byteArrayOutputStream.reset();
            return encodingCleanup;
        } catch (IOException e) {
            Log.e(TAG, "Couldn't reset stream to re-read with new encoding " + encodingCleanup + ' ' + e.toString());
            return null;
        }
    }
}
