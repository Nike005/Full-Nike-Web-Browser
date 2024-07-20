package com.google.android.exoplayer2.drm;

import android.text.TextUtils;
import com.google.android.exoplayer2.C5211C;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private static final int MAX_MANUAL_REDIRECTS = 5;
    private final HttpDataSource.Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(String str, HttpDataSource.Factory factory) {
        this(str, false, factory);
    }

    public HttpMediaDrmCallback(String str, boolean z, HttpDataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.defaultLicenseUrl = str;
        this.forceDefaultLicenseUrl = z;
        this.keyRequestProperties = new HashMap();
    }

    public void setKeyRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }

    public void clearKeyRequestProperty(String str) {
        Assertions.checkNotNull(str);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.remove(str);
        }
    }

    public void clearAllKeyRequestProperties() {
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.clear();
        }
    }

    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return executePost(this.dataSourceFactory, provisionRequest.getDefaultUrl() + "&signedRequest=" + Util.fromUtf8Bytes(provisionRequest.getData()), (byte[]) null, (Map<String, String>) null);
    }

    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        String str;
        String licenseServerUrl = keyRequest.getLicenseServerUrl();
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(licenseServerUrl)) {
            licenseServerUrl = this.defaultLicenseUrl;
        }
        HashMap hashMap = new HashMap();
        if (C5211C.PLAYREADY_UUID.equals(uuid)) {
            str = "text/xml";
        } else {
            str = C5211C.CLEARKEY_UUID.equals(uuid) ? "application/json" : "application/octet-stream";
        }
        hashMap.put("Content-Type", str);
        if (C5211C.PLAYREADY_UUID.equals(uuid)) {
            hashMap.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }
        synchronized (this.keyRequestProperties) {
            hashMap.putAll(this.keyRequestProperties);
        }
        return executePost(this.dataSourceFactory, licenseServerUrl, keyRequest.getData(), hashMap);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006d A[Catch:{ InvalidResponseCodeException -> 0x0053, all -> 0x0051 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0075 A[LOOP:1: B:7:0x002d->B:29:0x0075, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e A[EDGE_INSN: B:30:0x007e->B:31:? ?: BREAK  , SYNTHETIC, Splitter:B:30:0x007e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] executePost(com.google.android.exoplayer2.upstream.HttpDataSource.Factory r17, java.lang.String r18, byte[] r19, java.util.Map<java.lang.String, java.lang.String> r20) throws java.io.IOException {
        /*
            com.google.android.exoplayer2.upstream.HttpDataSource r1 = r17.createDataSource()
            if (r20 == 0) goto L_0x002a
            java.util.Set r0 = r20.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000e:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002a
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r1.setRequestProperty(r3, r2)
            goto L_0x000e
        L_0x002a:
            r0 = r18
            r3 = 0
        L_0x002d:
            com.google.android.exoplayer2.upstream.DataSpec r15 = new com.google.android.exoplayer2.upstream.DataSpec
            android.net.Uri r5 = android.net.Uri.parse(r0)
            r6 = 2
            r8 = 0
            r10 = 0
            r12 = -1
            r14 = 0
            r0 = 1
            r4 = r15
            r7 = r19
            r2 = r15
            r15 = r0
            r4.<init>(r5, r6, r7, r8, r10, r12, r14, r15)
            com.google.android.exoplayer2.upstream.DataSourceInputStream r4 = new com.google.android.exoplayer2.upstream.DataSourceInputStream
            r4.<init>(r1, r2)
            byte[] r0 = com.google.android.exoplayer2.util.Util.toByteArray(r4)     // Catch:{ InvalidResponseCodeException -> 0x0053 }
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r4)
            return r0
        L_0x0051:
            r0 = move-exception
            goto L_0x007f
        L_0x0053:
            r0 = move-exception
            r2 = r0
            int r0 = r2.responseCode     // Catch:{ all -> 0x0051 }
            r5 = 307(0x133, float:4.3E-43)
            if (r0 == r5) goto L_0x0061
            int r0 = r2.responseCode     // Catch:{ all -> 0x0051 }
            r5 = 308(0x134, float:4.32E-43)
            if (r0 != r5) goto L_0x0069
        L_0x0061:
            int r0 = r3 + 1
            r5 = 5
            if (r3 >= r5) goto L_0x0068
            r3 = 1
            goto L_0x006b
        L_0x0068:
            r3 = r0
        L_0x0069:
            r0 = r3
            r3 = 0
        L_0x006b:
            if (r3 == 0) goto L_0x0072
            java.lang.String r3 = getRedirectUrl(r2)     // Catch:{ all -> 0x0051 }
            goto L_0x0073
        L_0x0072:
            r3 = 0
        L_0x0073:
            if (r3 == 0) goto L_0x007e
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r4)
            r16 = r3
            r3 = r0
            r0 = r16
            goto L_0x002d
        L_0x007e:
            throw r2     // Catch:{ all -> 0x0051 }
        L_0x007f:
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r4)
            goto L_0x0084
        L_0x0083:
            throw r0
        L_0x0084:
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.HttpMediaDrmCallback.executePost(com.google.android.exoplayer2.upstream.HttpDataSource$Factory, java.lang.String, byte[], java.util.Map):byte[]");
    }

    private static String getRedirectUrl(HttpDataSource.InvalidResponseCodeException invalidResponseCodeException) {
        List list;
        Map<String, List<String>> map = invalidResponseCodeException.headerFields;
        if (map == null || (list = map.get("Location")) == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }
}
