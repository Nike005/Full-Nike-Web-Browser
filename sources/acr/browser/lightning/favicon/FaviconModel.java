package acr.browser.lightning.favicon;

import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.FileUtils;
import acr.browser.lightning.utils.Preconditions;
import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.util.LruCache;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FaviconModel {
    private static final String TAG = "FaviconModel";
    /* access modifiers changed from: private */
    public final Application mApplication;
    private final LruCache<String, Bitmap> mFaviconCache = new LruCache<String, Bitmap>((int) FileUtils.megabytesToBytes(1)) {
        /* access modifiers changed from: protected */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };
    /* access modifiers changed from: private */
    public final ImageFetcher mImageFetcher = new ImageFetcher();

    @Inject
    FaviconModel(Application application) {
        this.mApplication = application;
    }

    /* access modifiers changed from: private */
    public Bitmap getFaviconFromMemCache(String str) {
        Bitmap bitmap;
        Preconditions.checkNonNull(str);
        synchronized (this.mFaviconCache) {
            bitmap = this.mFaviconCache.get(str);
        }
        return bitmap;
    }

    private void addFaviconToMemCache(String str, Bitmap bitmap) {
        Preconditions.checkNonNull(str);
        Preconditions.checkNonNull(bitmap);
        synchronized (this.mFaviconCache) {
            this.mFaviconCache.put(str, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public static File createFaviconCacheFile(Application application, Uri uri) {
        FaviconUtils.assertUriSafe(uri);
        String valueOf = String.valueOf(uri.getHost().hashCode());
        File cacheDir = application.getCacheDir();
        return new File(cacheDir, valueOf + ".png");
    }

    public Single<Bitmap> faviconForUrl(final String str, final Bitmap bitmap, boolean z) {
        return Single.create(new SingleAction<Bitmap>() {
            public void onSubscribe(SingleSubscriber<Bitmap> singleSubscriber) {
                Uri safeUri = FaviconUtils.safeUri(str);
                if (safeUri == null) {
                    singleSubscriber.onItem(C3245Utils.padFavicon(bitmap));
                    singleSubscriber.onComplete();
                    return;
                }
                File access$100 = FaviconModel.createFaviconCacheFile(FaviconModel.this.mApplication, safeUri);
                Bitmap access$200 = FaviconModel.this.getFaviconFromMemCache(str);
                if (access$100.exists() && access$200 == null) {
                    access$200 = FaviconModel.this.mImageFetcher.retrieveFaviconFromCache(access$100);
                }
                if (access$200 == null) {
                    singleSubscriber.onItem(C3245Utils.padFavicon(bitmap));
                    singleSubscriber.onComplete();
                    return;
                }
                singleSubscriber.onItem(C3245Utils.padFavicon(access$200));
                singleSubscriber.onComplete();
            }
        });
    }

    public Completable cacheFaviconForUrl(final Bitmap bitmap, final String str) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                FileOutputStream fileOutputStream;
                IOException e;
                Uri safeUri = FaviconUtils.safeUri(str);
                if (safeUri == null) {
                    completableSubscriber.onComplete();
                    return;
                }
                Log.d(FaviconModel.TAG, "Caching icon for " + safeUri.getHost());
                try {
                    fileOutputStream = new FileOutputStream(FaviconModel.createFaviconCacheFile(FaviconModel.this.mApplication, safeUri));
                    try {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        fileOutputStream.flush();
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (IOException e3) {
                    fileOutputStream = null;
                    e = e3;
                    try {
                        Log.e(FaviconModel.TAG, "Unable to cache favicon", e);
                        C3245Utils.close((Closeable) fileOutputStream);
                    } catch (Throwable th) {
                        th = th;
                        C3245Utils.close((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    fileOutputStream = null;
                    th = th2;
                    C3245Utils.close((Closeable) fileOutputStream);
                    throw th;
                }
                C3245Utils.close((Closeable) fileOutputStream);
            }
        });
    }
}
