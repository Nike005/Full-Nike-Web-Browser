package acr.browser.lightning.utils;

import acr.browser.lightning.activity.MainActivity;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.download.DownloadHandler;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.google.android.material.snackbar.Snackbar;
import com.wnikebrow_13999769.R;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: acr.browser.lightning.utils.Utils */
public final class C3245Utils {
    private static final String TAG = "Utils";

    public static int mixTwoColors(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return (((int) ((((float) (i & 255)) * f) + (((float) (i2 & 255)) * f2))) & 255) | ((((int) ((((float) ((i >> 16) & 255)) * f) + (((float) ((i2 >> 16) & 255)) * f2))) & 255) << 16) | -16777216 | ((((int) ((((float) ((i >> 8) & 255)) * f) + (((float) ((i2 >> 8) & 255)) * f2))) & 255) << 8);
    }

    public static boolean doesSupportHeaders() {
        return Build.VERSION.SDK_INT >= 19;
    }

    public static void downloadFile(Activity activity, PreferenceManager preferenceManager, String str, String str2, String str3, DownloadsModel downloadsModel) {
        final String str4 = str;
        final Activity activity2 = activity;
        final PreferenceManager preferenceManager2 = preferenceManager;
        final String str5 = str2;
        final String str6 = str3;
        final DownloadsModel downloadsModel2 = downloadsModel;
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, (PermissionsResultAction) new PermissionsResultAction() {
            public void onGranted() {
                String guessFileName = URLUtil.guessFileName(str4, (String) null, (String) null);
                DownloadHandler.onDownloadStart(activity2, preferenceManager2, str4, str5, str6, (String) null, downloadsModel2);
                Log.i(C3245Utils.TAG, "Downloading: " + guessFileName);
            }

            public void onDenied(String str) {
                Log.i(C3245Utils.TAG, "Denied: " + str);
            }
        });
    }

    public static Intent newEmailIntent(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.CC", str4);
        intent.setType("message/rfc822");
        return intent;
    }

    public static void createInformativeDialog(Activity activity, int i, int i2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(i);
        builder.setMessage(i2).setCancelable(true).setPositiveButton((CharSequence) activity.getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog create = builder.create();
        create.show();
        BrowserDialog.setDialogSize(activity, create);
    }

    public static void showSnackbar(Activity activity, int i) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById == null) {
            Log.e(TAG, "showSnackbar", new NullPointerException("Unable to find android.R.id.content"));
        } else {
            Snackbar.make(findViewById, i, -1).show();
        }
    }

    public static void showSnackbar(Activity activity, String str) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById == null) {
            Log.e(TAG, "showSnackbar", new NullPointerException("Unable to find android.R.id.content"));
        } else {
            Snackbar.make(findViewById, (CharSequence) str, -1).show();
        }
    }

    public static void showToast(Context context, int i) {
        Toast.makeText(context, i, 0).show();
    }

    public static int dpToPx(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static String getDomainName(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return "";
        }
        boolean startsWith = str.startsWith(Constants.HTTPS);
        int indexOf = str.indexOf(47, 8);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        try {
            str2 = new URI(str).getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            str2 = null;
        }
        if (str2 == null || str2.isEmpty()) {
            return str;
        }
        if (!startsWith) {
            return str2.startsWith("www.") ? str2.substring(4) : str2;
        }
        return Constants.HTTPS + str2;
    }

    public static void trimCache(Context context) {
        try {
            File cacheDir = context.getCacheDir();
            if (cacheDir != null && cacheDir.isDirectory()) {
                deleteDir(cacheDir);
            }
        } catch (Exception unused) {
        }
    }

    private static boolean deleteDir(File file) {
        if (file != null && file.isDirectory()) {
            for (String file2 : file.list()) {
                if (!deleteDir(new File(file, file2))) {
                    return false;
                }
            }
        }
        if (file == null || !file.delete()) {
            return false;
        }
        return true;
    }

    public static Bitmap padFavicon(Bitmap bitmap) {
        int dpToPx = dpToPx(4.0f);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth() + dpToPx, bitmap.getHeight() + dpToPx, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        float f = (float) (dpToPx / 2);
        canvas.drawBitmap(bitmap, f, f, new Paint(2));
        return createBitmap;
    }

    public static boolean isColorTooDark(int i) {
        double d = (double) ((float) ((i >> 8) & 255));
        Double.isNaN(d);
        double d2 = (double) ((float) (i & 255));
        Double.isNaN(d2);
        int i2 = ((((int) (((float) ((i >> 16) & 255)) * 0.3f)) & 255) + (((int) (d * 0.59d)) & 255) + (((int) (d2 * 0.11d)) & 255)) & 255;
        return ((i2 << 8) + i2) + (i2 << 16) < 7500402;
    }

    public static File createImageFile() throws IOException {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return File.createTempFile("JPEG_" + format + '_', ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    public static boolean isFlashInstalled(Context context) {
        try {
            if (context.getPackageManager().getApplicationInfo("com.adobe.flashplayer", 0) != null) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void drawTrapezoid(Canvas canvas, int i, int i2, boolean z) {
        float f;
        float f2;
        float f3;
        float f4;
        Canvas canvas2 = canvas;
        int i3 = i;
        int i4 = i2;
        Paint paint = new Paint();
        paint.setColor(i3);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        if (z) {
            LinearGradient linearGradient = r1;
            LinearGradient linearGradient2 = new LinearGradient(0.0f, ((float) canvas.getHeight()) * 0.9f, 0.0f, (float) canvas.getHeight(), i, mixTwoColors(-16777216, i3, 0.5f), Shader.TileMode.CLAMP);
            paint.setShader(linearGradient);
        } else {
            paint.setShader((Shader) null);
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        double d = (double) height;
        double tan = Math.tan(1.0471975511965976d);
        Double.isNaN(d);
        int i5 = (int) (d / tan);
        Path path = new Path();
        path.reset();
        float f5 = (float) height;
        path.moveTo(0.0f, f5);
        float f6 = (float) width;
        path.lineTo(f6, f5);
        float f7 = (float) (width - i5);
        path.lineTo(f7, 0.0f);
        float f8 = (float) i5;
        path.lineTo(f8, 0.0f);
        path.close();
        canvas2.drawPath(path, paint);
        Paint paint2 = new Paint();
        paint2.setColor(i4);
        paint2.setStrokeWidth(1.0f);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        if (z) {
            float height2 = ((float) canvas.getHeight()) * 0.9f;
            int mixTwoColors = mixTwoColors(-16777216, i4, 0.5f);
            f = f8;
            f2 = f7;
            f4 = f6;
            int i6 = mixTwoColors;
            f3 = 0.0f;
            paint2.setShader(new LinearGradient(0.0f, height2, 0.0f, (float) canvas.getHeight(), i2, i6, Shader.TileMode.CLAMP));
        } else {
            f = f8;
            f2 = f7;
            f4 = f6;
            f3 = 0.0f;
            paint2.setShader((Shader) null);
        }
        Path path2 = new Path();
        path2.reset();
        path2.moveTo(f3, f5);
        path2.lineTo(f4, f5);
        path2.lineTo(f2, f3);
        path2.lineTo(f, f3);
        path2.close();
        canvas2.drawPath(path2, paint2);
    }

    public static void createShortcut(Activity activity, HistoryItem historyItem) {
        if (!TextUtils.isEmpty(historyItem.getUrl())) {
            Log.d(TAG, "Creating shortcut: " + historyItem.getTitle() + ' ' + historyItem.getUrl());
            Intent intent = new Intent(activity, MainActivity.class);
            intent.setData(Uri.parse(historyItem.getUrl()));
            String string = TextUtils.isEmpty(historyItem.getTitle()) ? activity.getString(R.string.untitled) : historyItem.getTitle();
            Intent intent2 = new Intent();
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", string);
            intent2.putExtra("android.intent.extra.shortcut.ICON", historyItem.getBitmap());
            intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            activity.sendBroadcast(intent2);
            showSnackbar(activity, (int) R.string.message_added_to_homescreen);
        }
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

    public static String guessFileExtension(String str) {
        int lastIndexOf = str.lastIndexOf(46) + 1;
        if (lastIndexOf <= 0 || str.length() <= lastIndexOf) {
            return null;
        }
        return str.substring(lastIndexOf, str.length());
    }
}
