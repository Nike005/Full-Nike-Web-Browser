package acr.browser.lightning.download;

import acr.browser.lightning.database.downloads.DownloadsModel;
import android.app.Activity;
import android.app.DownloadManager;

class FetchUrlMimeType extends Thread {
    private static final String TAG = "FetchUrlMimeType";
    /* access modifiers changed from: private */
    public final Activity mContext;
    private final String mCookies;
    private final DownloadsModel mDownloadsModel;
    private final DownloadManager.Request mRequest;
    private final String mUri;
    private final String mUserAgent;

    public FetchUrlMimeType(Activity activity, DownloadManager.Request request, String str, String str2, String str3, DownloadsModel downloadsModel) {
        this.mContext = activity;
        this.mRequest = request;
        this.mUri = str;
        this.mCookies = str2;
        this.mUserAgent = str3;
        this.mDownloadsModel = downloadsModel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006b A[SYNTHETIC, Splitter:B:34:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r6 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch:{ IOException | IllegalArgumentException -> 0x0067, all -> 0x0062 }
            java.lang.String r2 = r6.mUri     // Catch:{ IOException | IllegalArgumentException -> 0x0067, all -> 0x0062 }
            r1.<init>(r2)     // Catch:{ IOException | IllegalArgumentException -> 0x0067, all -> 0x0062 }
            java.net.URLConnection r1 = r1.openConnection()     // Catch:{ IOException | IllegalArgumentException -> 0x0067, all -> 0x0062 }
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException | IllegalArgumentException -> 0x0067, all -> 0x0062 }
            java.lang.String r2 = r6.mCookies     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            if (r2 == 0) goto L_0x0028
            java.lang.String r2 = r6.mCookies     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            boolean r2 = r2.isEmpty()     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            if (r2 != 0) goto L_0x0028
            java.lang.String r2 = "Cookie"
            java.lang.String r3 = r6.mCookies     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            r1.addRequestProperty(r2, r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            java.lang.String r2 = "User-Agent"
            java.lang.String r3 = r6.mUserAgent     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            r1.setRequestProperty(r2, r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
        L_0x0028:
            r1.connect()     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            int r2 = r1.getResponseCode()     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r2 != r3) goto L_0x0059
            java.lang.String r2 = "Content-Type"
            java.lang.String r2 = r1.getHeaderField(r2)     // Catch:{ IOException | IllegalArgumentException -> 0x0060 }
            if (r2 == 0) goto L_0x004a
            r3 = 59
            int r3 = r2.indexOf(r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0057 }
            r4 = -1
            if (r3 == r4) goto L_0x004b
            r4 = 0
            java.lang.String r2 = r2.substring(r4, r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0057 }
            goto L_0x004b
        L_0x004a:
            r2 = r0
        L_0x004b:
            java.lang.String r3 = "Content-Disposition"
            java.lang.String r3 = r1.getHeaderField(r3)     // Catch:{ IOException | IllegalArgumentException -> 0x0057 }
            if (r3 == 0) goto L_0x0054
            goto L_0x0055
        L_0x0054:
            r3 = r0
        L_0x0055:
            r0 = r2
            goto L_0x005a
        L_0x0057:
            goto L_0x0069
        L_0x0059:
            r3 = r0
        L_0x005a:
            if (r1 == 0) goto L_0x007d
            r1.disconnect()
            goto L_0x007d
        L_0x0060:
            r2 = r0
            goto L_0x0069
        L_0x0062:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0070
        L_0x0067:
            r1 = r0
            r2 = r1
        L_0x0069:
            if (r1 == 0) goto L_0x0076
            r1.disconnect()     // Catch:{ all -> 0x006f }
            goto L_0x0076
        L_0x006f:
            r0 = move-exception
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.disconnect()
        L_0x0075:
            throw r0
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r1.disconnect()
        L_0x007b:
            r3 = r0
            r0 = r2
        L_0x007d:
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x00b4
            java.lang.String r2 = "text/plain"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 != 0) goto L_0x0091
            java.lang.String r2 = "application/octet-stream"
            boolean r2 = r0.equalsIgnoreCase(r2)
            if (r2 == 0) goto L_0x00a6
        L_0x0091:
            android.webkit.MimeTypeMap r2 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r4 = r6.mUri
            java.lang.String r4 = acr.browser.lightning.utils.C3245Utils.guessFileExtension(r4)
            java.lang.String r2 = r2.getMimeTypeFromExtension(r4)
            if (r2 == 0) goto L_0x00a6
            android.app.DownloadManager$Request r4 = r6.mRequest
            r4.setMimeType(r2)
        L_0x00a6:
            java.lang.String r2 = r6.mUri
            java.lang.String r0 = android.webkit.URLUtil.guessFileName(r2, r3, r0)
            android.app.DownloadManager$Request r2 = r6.mRequest
            java.lang.String r3 = android.os.Environment.DIRECTORY_DOWNLOADS
            r2.setDestinationInExternalPublicDir(r3, r0)
            goto L_0x00b5
        L_0x00b4:
            r0 = r1
        L_0x00b5:
            android.app.Activity r2 = r6.mContext
            java.lang.String r3 = "download"
            java.lang.Object r2 = r2.getSystemService(r3)
            android.app.DownloadManager r2 = (android.app.DownloadManager) r2
            android.app.DownloadManager$Request r3 = r6.mRequest     // Catch:{ IllegalArgumentException -> 0x00d2, SecurityException -> 0x00c5 }
            r2.enqueue(r3)     // Catch:{ IllegalArgumentException -> 0x00d2, SecurityException -> 0x00c5 }
            goto L_0x00e6
        L_0x00c5:
            com.anthonycr.bonsai.Scheduler r2 = com.anthonycr.bonsai.Schedulers.main()
            acr.browser.lightning.download.FetchUrlMimeType$2 r3 = new acr.browser.lightning.download.FetchUrlMimeType$2
            r3.<init>()
            r2.execute(r3)
            goto L_0x00e6
        L_0x00d2:
            r2 = move-exception
            java.lang.String r3 = "FetchUrlMimeType"
            java.lang.String r4 = "Unable to enqueue request"
            android.util.Log.e(r3, r4, r2)
            com.anthonycr.bonsai.Scheduler r2 = com.anthonycr.bonsai.Schedulers.main()
            acr.browser.lightning.download.FetchUrlMimeType$1 r3 = new acr.browser.lightning.download.FetchUrlMimeType$1
            r3.<init>()
            r2.execute(r3)
        L_0x00e6:
            acr.browser.lightning.database.downloads.DownloadsModel r2 = r6.mDownloadsModel
            acr.browser.lightning.database.downloads.DownloadItem r3 = new acr.browser.lightning.database.downloads.DownloadItem
            java.lang.String r4 = r6.mUri
            r3.<init>(r4, r0, r1)
            com.anthonycr.bonsai.Single r1 = r2.addDownloadIfNotExists(r3)
            acr.browser.lightning.download.FetchUrlMimeType$3 r2 = new acr.browser.lightning.download.FetchUrlMimeType$3
            r2.<init>()
            r1.subscribe(r2)
            com.anthonycr.bonsai.Scheduler r1 = com.anthonycr.bonsai.Schedulers.main()
            acr.browser.lightning.download.FetchUrlMimeType$4 r2 = new acr.browser.lightning.download.FetchUrlMimeType$4
            r2.<init>(r0)
            r1.execute(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.download.FetchUrlMimeType.run():void");
    }
}
