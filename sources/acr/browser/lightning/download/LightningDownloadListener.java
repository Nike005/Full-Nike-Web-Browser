package acr.browser.lightning.download;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Activity;
import android.content.DialogInterface;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class LightningDownloadListener implements DownloadListener {
    private static final String TAG = "LightningDownloader";
    @Inject
    DownloadsModel downloadsModel;
    /* access modifiers changed from: private */
    public final Activity mActivity;
    @Inject
    PreferenceManager mPreferenceManager;

    public LightningDownloadListener(Activity activity) {
        BrowserApp.getAppComponent().inject(this);
        this.mActivity = activity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        final String str5 = str;
        final String str6 = str3;
        final String str7 = str4;
        final long j2 = j;
        final String str8 = str2;
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this.mActivity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, (PermissionsResultAction) new PermissionsResultAction() {
            public void onDenied(String str) {
            }

            public void onGranted() {
                String str;
                String guessFileName = URLUtil.guessFileName(str5, str6, str7);
                if (j2 > 0) {
                    str = Formatter.formatFileSize(LightningDownloadListener.this.mActivity, j2);
                } else {
                    str = LightningDownloadListener.this.mActivity.getString(R.string.unknown_size);
                }
                C31341 r2 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -1) {
                            DownloadHandler.onDownloadStart(LightningDownloadListener.this.mActivity, LightningDownloadListener.this.mPreferenceManager, str5, str8, str6, str7, LightningDownloadListener.this.downloadsModel);
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(LightningDownloadListener.this.mActivity);
                BrowserDialog.setDialogSize(LightningDownloadListener.this.mActivity, builder.setTitle((CharSequence) guessFileName).setMessage((CharSequence) LightningDownloadListener.this.mActivity.getString(R.string.dialog_download, new Object[]{str})).setPositiveButton((CharSequence) LightningDownloadListener.this.mActivity.getResources().getString(R.string.action_download), (DialogInterface.OnClickListener) r2).setNegativeButton((CharSequence) LightningDownloadListener.this.mActivity.getResources().getString(R.string.action_cancel), (DialogInterface.OnClickListener) r2).show());
                Log.i(LightningDownloadListener.TAG, "Downloading: " + guessFileName);
            }
        });
    }
}
