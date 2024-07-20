package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.file.FileCache;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

class HttpProxyCache extends ProxyCache {
    private static final float NO_CACHE_BARRIER = 0.2f;
    private final FileCache cache;
    private CacheListener listener;
    private final HttpUrlSource source;

    public HttpProxyCache(HttpUrlSource httpUrlSource, FileCache fileCache) {
        super(httpUrlSource, fileCache);
        this.cache = fileCache;
        this.source = httpUrlSource;
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listener = cacheListener;
    }

    public void processRequest(GetRequest getRequest, Socket socket) throws IOException, ProxyCacheException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(newResponseHeaders(getRequest).getBytes("UTF-8"));
        long j = getRequest.rangeOffset;
        if (isUseCache(getRequest)) {
            responseWithCache(bufferedOutputStream, j);
        } else {
            responseWithoutCache(bufferedOutputStream, j);
        }
    }

    private boolean isUseCache(GetRequest getRequest) throws ProxyCacheException {
        long length = this.source.length();
        boolean z = length > 0;
        long available = this.cache.available();
        if (!z || !getRequest.partial || ((float) getRequest.rangeOffset) <= ((float) available) + (((float) length) * NO_CACHE_BARRIER)) {
            return true;
        }
        return false;
    }

    private String newResponseHeaders(GetRequest getRequest) throws IOException, ProxyCacheException {
        String mime = this.source.getMime();
        boolean z = !TextUtils.isEmpty(mime);
        long available = this.cache.isCompleted() ? this.cache.available() : this.source.length();
        boolean z2 = available >= 0;
        long j = getRequest.partial ? available - getRequest.rangeOffset : available;
        boolean z3 = z2 && getRequest.partial;
        StringBuilder sb = new StringBuilder();
        sb.append(getRequest.partial ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n");
        sb.append("Accept-Ranges: bytes\n");
        String str = "";
        sb.append(z2 ? format("Content-Length: %d\n", Long.valueOf(j)) : str);
        sb.append(z3 ? format("Content-Range: bytes %d-%d/%d\n", Long.valueOf(getRequest.rangeOffset), Long.valueOf(available - 1), Long.valueOf(available)) : str);
        if (z) {
            str = format("Content-Type: %s\n", mime);
        }
        sb.append(str);
        sb.append(StringUtils.f3949LF);
        return sb.toString();
    }

    private void responseWithCache(OutputStream outputStream, long j) throws ProxyCacheException, IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = read(bArr, j, 8192);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private void responseWithoutCache(OutputStream outputStream, long j) throws ProxyCacheException, IOException {
        HttpUrlSource httpUrlSource = new HttpUrlSource(this.source);
        try {
            httpUrlSource.open((long) ((int) j));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = httpUrlSource.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    outputStream.flush();
                    return;
                }
            }
        } finally {
            httpUrlSource.close();
        }
    }

    private String format(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* access modifiers changed from: protected */
    public void onCachePercentsAvailableChanged(int i) {
        CacheListener cacheListener = this.listener;
        if (cacheListener != null) {
            cacheListener.onCacheAvailable(this.cache.file, this.source.getUrl(), i);
        }
    }
}
