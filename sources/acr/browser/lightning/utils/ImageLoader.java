package acr.browser.lightning.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import java.io.InputStream;
import java.net.URL;

public class ImageLoader {
    private static ImageLoader INSTANCE;
    /* access modifiers changed from: private */
    public LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>(5242880) {
        /* access modifiers changed from: protected */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    public interface ImageLoadedListener {
        void onImageLoaded(Bitmap bitmap);
    }

    public static ImageLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageLoader();
        }
        return INSTANCE;
    }

    private ImageLoader() {
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public synchronized void loadImage(final String str, final ImageLoadedListener imageLoadedListener) {
        Bitmap bitmap = this.imageCache.get(str);
        if (bitmap != null) {
            imageLoadedListener.onImageLoaded(bitmap);
        } else {
            new AsyncTask<String, Void, Bitmap>() {
                /* access modifiers changed from: protected */
                public Bitmap doInBackground(String... strArr) {
                    String str = strArr[0];
                    Bitmap bitmap = (Bitmap) ImageLoader.this.imageCache.get(str);
                    if (bitmap != null) {
                        return bitmap;
                    }
                    try {
                        InputStream openStream = new URL(str).openStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(openStream, (Rect) null, options);
                        options.inSampleSize = ImageLoader.calculateInSampleSize(options, 200, 200);
                        options.inJustDecodeBounds = false;
                        openStream.close();
                        InputStream openStream2 = new URL(str).openStream();
                        bitmap = BitmapFactory.decodeStream(openStream2, (Rect) null, options);
                        openStream2.close();
                        return bitmap;
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                        return bitmap;
                    }
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(Bitmap bitmap) {
                    if (bitmap != null) {
                        imageLoadedListener.onImageLoaded(bitmap);
                        ImageLoader.this.imageCache.put(str, bitmap);
                    }
                }
            }.execute(new String[]{str});
        }
    }
}
