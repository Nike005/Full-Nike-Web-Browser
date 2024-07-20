package acr.browser.lightning.download;

import acr.browser.lightning.BuildConfig;
import acr.browser.lightning.activity.MainActivity;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.database.downloads.DownloadItem;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.wnikebrow_13999769.R;
import java.io.File;
import java.io.IOException;

public class DownloadHandler {
    private static final String COOKIE_REQUEST_HEADER = "Cookie";
    public static final String DEFAULT_DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    private static final String TAG = "DownloadHandler";
    private static final String sFileExtension = ".txt";
    private static final String sFileName = "test";

    public static void onDownloadStart(Activity activity, PreferenceManager preferenceManager, String str, String str2, String str3, String str4, DownloadsModel downloadsModel) {
        Log.d(TAG, "DOWNLOAD: Trying to download from URL: " + str);
        Log.d(TAG, "DOWNLOAD: Content disposition: " + str3);
        Log.d(TAG, "DOWNLOAD: Mimetype: " + str4);
        Log.d(TAG, "DOWNLOAD: User agent: " + str2);
        if (str3 == null || !str3.regionMatches(true, 0, "attachment", 0, 10)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            intent.addFlags(268435456);
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setComponent((ComponentName) null);
            if (Build.VERSION.SDK_INT >= 15) {
                intent.setSelector((Intent) null);
            }
            ResolveInfo resolveActivity = activity.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity != null && (BuildConfig.APPLICATION_ID.equals(resolveActivity.activityInfo.packageName) || MainActivity.class.getName().equals(resolveActivity.activityInfo.name))) {
                try {
                    activity.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                }
            }
        }
        onDownloadStartNoStream(activity, preferenceManager, str, str2, str3, str4, downloadsModel);
    }

    private static String encodePath(String str) {
        boolean z;
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            char c = charArray[i];
            if (c == '[' || c == ']' || c == '|') {
                z = true;
            } else {
                i++;
            }
        }
        if (!z) {
            return str;
        }
        StringBuilder sb = new StringBuilder("");
        for (char c2 : charArray) {
            if (c2 == '[' || c2 == ']' || c2 == '|') {
                sb.append('%');
                sb.append(Integer.toHexString(c2));
            } else {
                sb.append(c2);
            }
        }
        return sb.toString();
    }

    private static void onDownloadStartNoStream(Activity activity, PreferenceManager preferenceManager, String str, String str2, String str3, String str4, DownloadsModel downloadsModel) {
        int i;
        String str5;
        Activity activity2 = activity;
        String str6 = str;
        String str7 = str4;
        String guessFileName = URLUtil.guessFileName(str6, str3, str7);
        String externalStorageState = Environment.getExternalStorageState();
        if (!externalStorageState.equals("mounted")) {
            if (externalStorageState.equals("shared")) {
                str5 = activity.getString(R.string.download_sdcard_busy_dlg_msg);
                i = R.string.download_sdcard_busy_dlg_title;
            } else {
                str5 = activity.getString(R.string.download_no_sdcard_dlg_msg);
                i = R.string.download_no_sdcard_dlg_title;
            }
            BrowserDialog.setDialogSize(activity, new AlertDialog.Builder(activity).setTitle(i).setIcon(17301543).setMessage((CharSequence) str5).setPositiveButton((int) R.string.action_ok, (DialogInterface.OnClickListener) null).show());
            return;
        }
        try {
            WebAddress webAddress = new WebAddress(str6);
            webAddress.setPath(encodePath(webAddress.getPath()));
            String webAddress2 = webAddress.toString();
            try {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(webAddress2));
                String addNecessarySlashes = addNecessarySlashes(preferenceManager.getDownloadDirectory());
                Uri parse = Uri.parse(addNecessarySlashes);
                File file = new File(parse.getPath());
                if (!file.isDirectory() && !file.mkdirs()) {
                    C3245Utils.showSnackbar(activity, (int) R.string.problem_location_download);
                } else if (!isWriteAccessAvailable(parse)) {
                    C3245Utils.showSnackbar(activity, (int) R.string.problem_location_download);
                } else {
                    String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(C3245Utils.guessFileExtension(guessFileName));
                    Log.d(TAG, "New mimetype: " + mimeTypeFromExtension);
                    request.setMimeType(mimeTypeFromExtension);
                    request.setDestinationUri(Uri.parse(Constants.FILE + addNecessarySlashes + guessFileName));
                    request.setVisibleInDownloadsUi(true);
                    request.allowScanningByMediaScanner();
                    request.setDescription(webAddress.getHost());
                    String cookie = CookieManager.getInstance().getCookie(str6);
                    request.addRequestHeader(COOKIE_REQUEST_HEADER, cookie);
                    request.setNotificationVisibility(0);
                    if (str7 == null) {
                        Log.d(TAG, "Mimetype is null");
                        if (!TextUtils.isEmpty(webAddress2)) {
                            new FetchUrlMimeType(activity, request, webAddress2, cookie, str2, downloadsModel).start();
                            return;
                        }
                        return;
                    }
                    Log.d(TAG, "Valid mimetype, attempting to download");
                    try {
                        ((DownloadManager) activity.getSystemService("download")).enqueue(request);
                    } catch (IllegalArgumentException e) {
                        Log.e(TAG, "Unable to enqueue request", e);
                        C3245Utils.showSnackbar(activity, (int) R.string.cannot_download);
                    } catch (SecurityException unused) {
                        C3245Utils.showSnackbar(activity, (int) R.string.problem_location_download);
                    }
                    C3245Utils.showSnackbar(activity, activity.getString(R.string.download_pending) + ' ' + guessFileName);
                    downloadsModel.addDownloadIfNotExists(new DownloadItem(str6, guessFileName, "")).subscribe(new SingleOnSubscribe<Boolean>() {
                        public void onItem(Boolean bool) {
                            if (bool != null && !bool.booleanValue()) {
                                Log.i(DownloadHandler.TAG, "error saving download to database");
                            }
                        }
                    });
                }
            } catch (IllegalArgumentException unused2) {
                C3245Utils.showSnackbar(activity, (int) R.string.cannot_download);
            }
        } catch (Exception e2) {
            Log.e(TAG, "Exception while trying to parse url '" + str6 + '\'', e2);
            C3245Utils.showSnackbar(activity, (int) R.string.problem_download);
        }
    }

    public static boolean isWriteAccessAvailable(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String firstRealParentDirectory = getFirstRealParentDirectory(addNecessarySlashes(str));
        File file = new File(firstRealParentDirectory + sFileName + sFileExtension);
        int i = 0;
        while (i < 100) {
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        return true;
                    }
                    file.delete();
                    return true;
                } catch (IOException unused) {
                    return false;
                }
            } else {
                file = new File(firstRealParentDirectory + sFileName + '-' + i + sFileExtension);
                i++;
            }
        }
        return file.canWrite();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        r4 = r4.substring(0, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getFirstRealParentDirectory(java.lang.String r4) {
        /*
        L_0x0000:
            java.lang.String r0 = "/"
            if (r4 == 0) goto L_0x0034
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L_0x000b
            goto L_0x0034
        L_0x000b:
            java.lang.String r4 = addNecessarySlashes(r4)
            java.io.File r1 = new java.io.File
            r1.<init>(r4)
            boolean r1 = r1.isDirectory()
            if (r1 != 0) goto L_0x0033
            r1 = 47
            int r2 = r4.lastIndexOf(r1)
            if (r2 <= 0) goto L_0x0032
            r3 = 0
            java.lang.String r4 = r4.substring(r3, r2)
            int r1 = r4.lastIndexOf(r1)
            if (r1 <= 0) goto L_0x0032
            java.lang.String r4 = r4.substring(r3, r1)
            goto L_0x0000
        L_0x0032:
            return r0
        L_0x0033:
            return r4
        L_0x0034:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.download.DownloadHandler.getFirstRealParentDirectory(java.lang.String):java.lang.String");
    }

    private static boolean isWriteAccessAvailable(Uri uri) {
        File file = new File(uri.getPath());
        try {
            if (!file.createNewFile()) {
                return true;
            }
            file.delete();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static String addNecessarySlashes(String str) {
        if (str == null || str.length() == 0) {
            return "/";
        }
        if (str.charAt(str.length() - 1) != '/') {
            str = str + '/';
        }
        if (str.charAt(0) == '/') {
            return str;
        }
        return '/' + str;
    }
}
