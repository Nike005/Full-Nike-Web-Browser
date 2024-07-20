package com.danikula.videocache;

import android.content.Context;
import android.net.Uri;
import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.danikula.videocache.file.TotalCountLruDiskUsage;
import com.danikula.videocache.file.TotalSizeLruDiskUsage;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxyCacheServer {
    private static final Logger LOG = LoggerFactory.getLogger("HttpProxyCacheServer");
    private static final String PROXY_HOST = "127.0.0.1";
    private final Object clientsLock;
    private final Map<String, HttpProxyCacheServerClients> clientsMap;
    private final Config config;
    private final Pinger pinger;
    private final int port;
    private final ServerSocket serverSocket;
    private final ExecutorService socketProcessor;
    private final Thread waitConnectionThread;

    public HttpProxyCacheServer(Context context) {
        this(new Builder(context).buildConfig());
    }

    private HttpProxyCacheServer(Config config2) {
        this.clientsLock = new Object();
        this.socketProcessor = Executors.newFixedThreadPool(8);
        this.clientsMap = new ConcurrentHashMap();
        this.config = (Config) Preconditions.checkNotNull(config2);
        try {
            ServerSocket serverSocket2 = new ServerSocket(0, 8, InetAddress.getByName(PROXY_HOST));
            this.serverSocket = serverSocket2;
            int localPort = serverSocket2.getLocalPort();
            this.port = localPort;
            IgnoreHostProxySelector.install(PROXY_HOST, localPort);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread = new Thread(new WaitRequestsRunnable(countDownLatch));
            this.waitConnectionThread = thread;
            thread.start();
            countDownLatch.await();
            this.pinger = new Pinger(PROXY_HOST, this.port);
            Logger logger = LOG;
            logger.info("Proxy cache server started. Is it alive? " + isAlive());
        } catch (IOException | InterruptedException e) {
            this.socketProcessor.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    public String getProxyUrl(String str) {
        return getProxyUrl(str, true);
    }

    public String getProxyUrl(String str, boolean z) {
        if (!z || !isCached(str)) {
            return isAlive() ? appendToProxyUrl(str) : str;
        }
        File cacheFile = getCacheFile(str);
        touchFileSafely(cacheFile);
        return Uri.fromFile(cacheFile).toString();
    }

    public void registerCacheListener(CacheListener cacheListener, String str) {
        Preconditions.checkAllNotNull(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).registerCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener, String str) {
        Preconditions.checkAllNotNull(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).unregisterCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        Preconditions.checkNotNull(cacheListener);
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients unregisterCacheListener : this.clientsMap.values()) {
                unregisterCacheListener.unregisterCacheListener(cacheListener);
            }
        }
    }

    public boolean isCached(String str) {
        Preconditions.checkNotNull(str, "Url can't be null!");
        return getCacheFile(str).exists();
    }

    public void shutdown() {
        LOG.info("Shutdown proxy server");
        shutdownClients();
        this.config.sourceInfoStorage.release();
        this.waitConnectionThread.interrupt();
        try {
            if (!this.serverSocket.isClosed()) {
                this.serverSocket.close();
            }
        } catch (IOException e) {
            onError(new ProxyCacheException("Error shutting down proxy server", e));
        }
    }

    private boolean isAlive() {
        return this.pinger.ping(3, 70);
    }

    private String appendToProxyUrl(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", new Object[]{PROXY_HOST, Integer.valueOf(this.port), ProxyCacheUtils.encode(str)});
    }

    private File getCacheFile(String str) {
        return new File(this.config.cacheRoot, this.config.fileNameGenerator.generate(str));
    }

    private void touchFileSafely(File file) {
        try {
            this.config.diskUsage.touch(file);
        } catch (IOException e) {
            Logger logger = LOG;
            logger.error("Error touching file " + file, (Throwable) e);
        }
    }

    private void shutdownClients() {
        synchronized (this.clientsLock) {
            for (HttpProxyCacheServerClients shutdown : this.clientsMap.values()) {
                shutdown.shutdown();
            }
            this.clientsMap.clear();
        }
    }

    /* access modifiers changed from: private */
    public void waitForRequest() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.serverSocket.accept();
                Logger logger = LOG;
                logger.debug("Accept new socket " + accept);
                this.socketProcessor.submit(new SocketProcessorRunnable(accept));
            } catch (IOException e) {
                onError(new ProxyCacheException("Error during waiting connection", e));
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0060 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processSocket(java.net.Socket r6) {
        /*
            r5 = this;
            java.lang.String r0 = "Opened connections: "
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            com.danikula.videocache.GetRequest r1 = com.danikula.videocache.GetRequest.read(r1)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            org.slf4j.Logger r2 = LOG     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            r3.<init>()     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            java.lang.String r4 = "Request to cache proxy:"
            r3.append(r4)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            r3.append(r1)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            java.lang.String r3 = r3.toString()     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            r2.debug(r3)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            java.lang.String r2 = r1.uri     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            java.lang.String r2 = com.danikula.videocache.ProxyCacheUtils.decode(r2)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            com.danikula.videocache.Pinger r3 = r5.pinger     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            boolean r3 = r3.isPingRequest(r2)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            if (r3 == 0) goto L_0x0034
            com.danikula.videocache.Pinger r1 = r5.pinger     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            r1.responseToPing(r6)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            goto L_0x003b
        L_0x0034:
            com.danikula.videocache.HttpProxyCacheServerClients r2 = r5.getClients(r2)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
            r2.processRequest(r1, r6)     // Catch:{ SocketException -> 0x0060, ProxyCacheException -> 0x004a, IOException -> 0x0048 }
        L_0x003b:
            r5.releaseSocket(r6)
            org.slf4j.Logger r6 = LOG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x0071
        L_0x0046:
            r1 = move-exception
            goto L_0x0083
        L_0x0048:
            r1 = move-exception
            goto L_0x004b
        L_0x004a:
            r1 = move-exception
        L_0x004b:
            com.danikula.videocache.ProxyCacheException r2 = new com.danikula.videocache.ProxyCacheException     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = "Error processing request"
            r2.<init>(r3, r1)     // Catch:{ all -> 0x0046 }
            r5.onError(r2)     // Catch:{ all -> 0x0046 }
            r5.releaseSocket(r6)
            org.slf4j.Logger r6 = LOG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto L_0x0071
        L_0x0060:
            org.slf4j.Logger r1 = LOG     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = "Closing socket… Socket is closed by client."
            r1.debug(r2)     // Catch:{ all -> 0x0046 }
            r5.releaseSocket(r6)
            org.slf4j.Logger r6 = LOG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x0071:
            r1.append(r0)
            int r0 = r5.getClientsCount()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r6.debug(r0)
            return
        L_0x0083:
            r5.releaseSocket(r6)
            org.slf4j.Logger r6 = LOG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            int r0 = r5.getClientsCount()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r6.debug(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.HttpProxyCacheServer.processSocket(java.net.Socket):void");
    }

    private HttpProxyCacheServerClients getClients(String str) throws ProxyCacheException {
        HttpProxyCacheServerClients httpProxyCacheServerClients;
        synchronized (this.clientsLock) {
            httpProxyCacheServerClients = this.clientsMap.get(str);
            if (httpProxyCacheServerClients == null) {
                httpProxyCacheServerClients = new HttpProxyCacheServerClients(str, this.config);
                this.clientsMap.put(str, httpProxyCacheServerClients);
            }
        }
        return httpProxyCacheServerClients;
    }

    private int getClientsCount() {
        int i;
        synchronized (this.clientsLock) {
            i = 0;
            for (HttpProxyCacheServerClients clientsCount : this.clientsMap.values()) {
                i += clientsCount.getClientsCount();
            }
        }
        return i;
    }

    private void releaseSocket(Socket socket) {
        closeSocketInput(socket);
        closeSocketOutput(socket);
        closeSocket(socket);
    }

    private void closeSocketInput(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException unused) {
            LOG.debug("Releasing input stream… Socket is closed by client.");
        } catch (IOException e) {
            onError(new ProxyCacheException("Error closing socket input stream", e));
        }
    }

    private void closeSocketOutput(Socket socket) {
        try {
            if (!socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            LOG.warn("Failed to close socket on proxy side: {}. It seems client have already closed connection.", (Object) e.getMessage());
        }
    }

    private void closeSocket(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            onError(new ProxyCacheException("Error closing socket", e));
        }
    }

    private void onError(Throwable th) {
        LOG.error("HttpProxyCacheServer error", th);
    }

    private final class WaitRequestsRunnable implements Runnable {
        private final CountDownLatch startSignal;

        public WaitRequestsRunnable(CountDownLatch countDownLatch) {
            this.startSignal = countDownLatch;
        }

        public void run() {
            this.startSignal.countDown();
            HttpProxyCacheServer.this.waitForRequest();
        }
    }

    private final class SocketProcessorRunnable implements Runnable {
        private final Socket socket;

        public SocketProcessorRunnable(Socket socket2) {
            this.socket = socket2;
        }

        public void run() {
            HttpProxyCacheServer.this.processSocket(this.socket);
        }
    }

    public static final class Builder {
        private static final long DEFAULT_MAX_SIZE = 536870912;
        private File cacheRoot;
        private DiskUsage diskUsage = new TotalSizeLruDiskUsage(DEFAULT_MAX_SIZE);
        private FileNameGenerator fileNameGenerator = new Md5FileNameGenerator();
        private SourceInfoStorage sourceInfoStorage;

        public Builder(Context context) {
            this.sourceInfoStorage = SourceInfoStorageFactory.newSourceInfoStorage(context);
            this.cacheRoot = StorageUtils.getIndividualCacheDirectory(context);
        }

        public Builder cacheDirectory(File file) {
            this.cacheRoot = (File) Preconditions.checkNotNull(file);
            return this;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator2) {
            this.fileNameGenerator = (FileNameGenerator) Preconditions.checkNotNull(fileNameGenerator2);
            return this;
        }

        public Builder maxCacheSize(long j) {
            this.diskUsage = new TotalSizeLruDiskUsage(j);
            return this;
        }

        public Builder maxCacheFilesCount(int i) {
            this.diskUsage = new TotalCountLruDiskUsage(i);
            return this;
        }

        public Builder diskUsage(DiskUsage diskUsage2) {
            this.diskUsage = (DiskUsage) Preconditions.checkNotNull(diskUsage2);
            return this;
        }

        public HttpProxyCacheServer build() {
            return new HttpProxyCacheServer(buildConfig());
        }

        /* access modifiers changed from: private */
        public Config buildConfig() {
            return new Config(this.cacheRoot, this.fileNameGenerator, this.diskUsage, this.sourceInfoStorage);
        }
    }
}
