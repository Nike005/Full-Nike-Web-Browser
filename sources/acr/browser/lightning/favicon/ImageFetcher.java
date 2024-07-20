package acr.browser.lightning.favicon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.File;
import okhttp3.OkHttpClient;

class ImageFetcher {
    private static final String TAG = "ImageFetcher";
    private final OkHttpClient mHttpClient = new OkHttpClient();
    private final BitmapFactory.Options mLoaderOptions = new BitmapFactory.Options();

    ImageFetcher() {
    }

    /* access modifiers changed from: package-private */
    public Bitmap retrieveFaviconFromCache(File file) {
        return BitmapFactory.decodeFile(file.getPath(), this.mLoaderOptions);
    }

    /* access modifiers changed from: package-private */
    public Bitmap retrieveBitmapFromDomain(Uri uri) {
        FaviconUtils.assertUriSafe(uri);
        return retrieveBitmapFromUrl(uri.getScheme() + "://" + uri.getHost() + "/favicon.ico");
    }

    /* access modifiers changed from: package-private */
    public Bitmap retrieveBitmapFromGoogle(Uri uri) {
        FaviconUtils.assertUriSafe(uri);
        return retrieveBitmapFromUrl("https://www.google.com/s2/favicons?domain_url=" + uri.getHost());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.graphics.Bitmap} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.InputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap retrieveBitmapFromUrl(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            android.graphics.BitmapFactory$Options r1 = r6.mLoaderOptions     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            r2 = 1
            r1.inSampleSize = r2     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            android.graphics.BitmapFactory$Options r1 = r6.mLoaderOptions     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            r1.inJustDecodeBounds = r2     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.Request$Builder r1 = new okhttp3.Request$Builder     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            r1.<init>()     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.Request$Builder r1 = r1.url((java.lang.String) r7)     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.Request r1 = r1.build()     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.OkHttpClient r2 = r6.mHttpClient     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.Call r2 = r2.newCall(r1)     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.Response r2 = r2.execute()     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            okhttp3.ResponseBody r2 = r2.body()     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            if (r2 != 0) goto L_0x002e
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r0)
        L_0x002a:
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r0)
            return r0
        L_0x002e:
            java.io.InputStream r3 = r2.byteStream()     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            android.graphics.BitmapFactory$Options r4 = r6.mLoaderOptions     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            android.graphics.BitmapFactory.decodeStream(r3, r0, r4)     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            r2.close()     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            r2 = 1103101952(0x41c00000, float:24.0)
            int r2 = acr.browser.lightning.utils.C3245Utils.dpToPx(r2)     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            android.graphics.BitmapFactory$Options r4 = r6.mLoaderOptions     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            android.graphics.BitmapFactory$Options r5 = r6.mLoaderOptions     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            int r2 = acr.browser.lightning.utils.C3245Utils.calculateInSampleSize(r5, r2, r2)     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            r4.inSampleSize = r2     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            android.graphics.BitmapFactory$Options r2 = r6.mLoaderOptions     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            r4 = 0
            r2.inJustDecodeBounds = r4     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            okhttp3.OkHttpClient r2 = r6.mHttpClient     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            okhttp3.Call r1 = r2.newCall(r1)     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            okhttp3.Response r1 = r1.execute()     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            okhttp3.ResponseBody r1 = r1.body()     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            if (r1 != 0) goto L_0x0063
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r3)
            goto L_0x002a
        L_0x0063:
            java.io.InputStream r2 = r1.byteStream()     // Catch:{ IOException -> 0x007f, all -> 0x007b }
            android.graphics.BitmapFactory$Options r4 = r6.mLoaderOptions     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r2, r0, r4)     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
            r1.close()     // Catch:{ IOException -> 0x0079, all -> 0x0077 }
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r3)
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r2)
            goto L_0x00a5
        L_0x0077:
            r7 = move-exception
            goto L_0x007d
        L_0x0079:
            r1 = r0
            goto L_0x0081
        L_0x007b:
            r7 = move-exception
            r2 = r0
        L_0x007d:
            r0 = r3
            goto L_0x00a7
        L_0x007f:
            r1 = r0
            r2 = r1
        L_0x0081:
            r0 = r3
            goto L_0x0088
        L_0x0083:
            r7 = move-exception
            r2 = r0
            goto L_0x00a7
        L_0x0086:
            r1 = r0
            r2 = r1
        L_0x0088:
            java.lang.String r3 = "ImageFetcher"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a6 }
            r4.<init>()     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = "Unable to download icon: "
            r4.append(r5)     // Catch:{ all -> 0x00a6 }
            r4.append(r7)     // Catch:{ all -> 0x00a6 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x00a6 }
            android.util.Log.d(r3, r7)     // Catch:{ all -> 0x00a6 }
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r0)
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r2)
            r0 = r1
        L_0x00a5:
            return r0
        L_0x00a6:
            r7 = move-exception
        L_0x00a7:
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r0)
            acr.browser.lightning.utils.C3245Utils.close((java.io.Closeable) r2)
            goto L_0x00af
        L_0x00ae:
            throw r7
        L_0x00af:
            goto L_0x00ae
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.favicon.ImageFetcher.retrieveBitmapFromUrl(java.lang.String):android.graphics.Bitmap");
    }
}
