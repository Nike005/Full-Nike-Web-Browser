package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUrlSource implements Source {
    private static final Logger LOG = LoggerFactory.getLogger("HttpUrlSource");
    private static final int MAX_REDIRECTS = 5;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    public HttpUrlSource(String str) {
        this(str, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String str, SourceInfoStorage sourceInfoStorage2) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage2);
        SourceInfo sourceInfo2 = sourceInfoStorage2.get(str);
        this.sourceInfo = sourceInfo2 == null ? new SourceInfo(str, -2147483648L, ProxyCacheUtils.getSupposablyMime(str)) : sourceInfo2;
    }

    public HttpUrlSource(HttpUrlSource httpUrlSource) {
        this.sourceInfo = httpUrlSource.sourceInfo;
        this.sourceInfoStorage = httpUrlSource.sourceInfoStorage;
    }

    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    public void open(long j) throws ProxyCacheException {
        try {
            HttpURLConnection openConnection = openConnection(j, -1);
            this.connection = openConnection;
            String contentType = openConnection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            SourceInfo sourceInfo2 = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(this.connection, j, this.connection.getResponseCode()), contentType);
            this.sourceInfo = sourceInfo2;
            this.sourceInfoStorage.put(sourceInfo2.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + j, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection httpURLConnection, long j, int i) throws IOException {
        long contentLength = getContentLength(httpURLConnection);
        if (i == 200) {
            return contentLength;
        }
        if (i == 206) {
            return contentLength + j;
        }
        return this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection httpURLConnection) {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        if (headerField == null) {
            return -1;
        }
        return Long.parseLong(headerField);
    }

    public void close() throws ProxyCacheException {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (ArrayIndexOutOfBoundsException e2) {
                LOG.error("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", (Throwable) e2);
            }
        }
    }

    public int read(byte[] bArr) throws ProxyCacheException {
        InputStream inputStream2 = this.inputStream;
        if (inputStream2 != null) {
            try {
                return inputStream2.read(bArr, 0, bArr.length);
            } catch (InterruptedIOException e) {
                throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
            } catch (IOException e2) {
                throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
            }
        } else {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fetchContentInfo() throws com.danikula.videocache.ProxyCacheException {
        /*
            r7 = this;
            org.slf4j.Logger r0 = LOG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Read content info from "
            r1.append(r2)
            com.danikula.videocache.SourceInfo r2 = r7.sourceInfo
            java.lang.String r2 = r2.url
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.debug(r1)
            r0 = 0
            r2 = 10000(0x2710, float:1.4013E-41)
            r3 = 0
            java.net.HttpURLConnection r0 = r7.openConnection(r0, r2)     // Catch:{ IOException -> 0x0068, all -> 0x0065 }
            long r1 = r7.getContentLength(r0)     // Catch:{ IOException -> 0x0063 }
            java.lang.String r4 = r0.getContentType()     // Catch:{ IOException -> 0x0063 }
            java.io.InputStream r3 = r0.getInputStream()     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.SourceInfo r5 = new com.danikula.videocache.SourceInfo     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.SourceInfo r6 = r7.sourceInfo     // Catch:{ IOException -> 0x0063 }
            java.lang.String r6 = r6.url     // Catch:{ IOException -> 0x0063 }
            r5.<init>(r6, r1, r4)     // Catch:{ IOException -> 0x0063 }
            r7.sourceInfo = r5     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.sourcestorage.SourceInfoStorage r1 = r7.sourceInfoStorage     // Catch:{ IOException -> 0x0063 }
            java.lang.String r2 = r5.url     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.SourceInfo r4 = r7.sourceInfo     // Catch:{ IOException -> 0x0063 }
            r1.put(r2, r4)     // Catch:{ IOException -> 0x0063 }
            org.slf4j.Logger r1 = LOG     // Catch:{ IOException -> 0x0063 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0063 }
            r2.<init>()     // Catch:{ IOException -> 0x0063 }
            java.lang.String r4 = "Source info fetched: "
            r2.append(r4)     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.SourceInfo r4 = r7.sourceInfo     // Catch:{ IOException -> 0x0063 }
            r2.append(r4)     // Catch:{ IOException -> 0x0063 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0063 }
            r1.debug(r2)     // Catch:{ IOException -> 0x0063 }
            com.danikula.videocache.ProxyCacheUtils.close(r3)
            if (r0 == 0) goto L_0x008c
            goto L_0x0089
        L_0x0061:
            r1 = move-exception
            goto L_0x008d
        L_0x0063:
            r1 = move-exception
            goto L_0x006a
        L_0x0065:
            r1 = move-exception
            r0 = r3
            goto L_0x008d
        L_0x0068:
            r1 = move-exception
            r0 = r3
        L_0x006a:
            org.slf4j.Logger r2 = LOG     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r4.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = "Error fetching info from "
            r4.append(r5)     // Catch:{ all -> 0x0061 }
            com.danikula.videocache.SourceInfo r5 = r7.sourceInfo     // Catch:{ all -> 0x0061 }
            java.lang.String r5 = r5.url     // Catch:{ all -> 0x0061 }
            r4.append(r5)     // Catch:{ all -> 0x0061 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0061 }
            r2.error((java.lang.String) r4, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0061 }
            com.danikula.videocache.ProxyCacheUtils.close(r3)
            if (r0 == 0) goto L_0x008c
        L_0x0089:
            r0.disconnect()
        L_0x008c:
            return
        L_0x008d:
            com.danikula.videocache.ProxyCacheUtils.close(r3)
            if (r0 == 0) goto L_0x0095
            r0.disconnect()
        L_0x0095:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpUrlSource.fetchContentInfo():void");
    }

    private HttpURLConnection openConnection(long j, int i) throws IOException, ProxyCacheException {
        String str;
        HttpURLConnection httpURLConnection;
        boolean z;
        String str2 = this.sourceInfo.url;
        int i2 = 0;
        do {
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Open connection ");
            if (j > 0) {
                str = " with offset " + j;
            } else {
                str = "";
            }
            sb.append(str);
            sb.append(" to ");
            sb.append(str2);
            logger.debug(sb.toString());
            httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
            if (j > 0) {
                httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-");
            }
            if (i > 0) {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
            }
            int responseCode = httpURLConnection.getResponseCode();
            z = responseCode == 301 || responseCode == 302 || responseCode == 303;
            if (z) {
                str2 = httpURLConnection.getHeaderField("Location");
                i2++;
                httpURLConnection.disconnect();
            }
            if (i2 > 5) {
                throw new ProxyCacheException("Too many redirects: " + i2);
            }
        } while (z);
        return httpURLConnection;
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
