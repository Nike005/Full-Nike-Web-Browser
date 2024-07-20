package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.favicon.FaviconModel;
import acr.browser.lightning.utils.Preconditions;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class LightningChromeClient extends WebChromeClient {
    private static final String[] PERMISSIONS = {"android.permission.ACCESS_FINE_LOCATION"};
    /* access modifiers changed from: private */
    public final Activity mActivity;
    @Inject
    FaviconModel mFaviconModel;
    private final LightningView mLightningView;
    private final UIController mUIController;

    LightningChromeClient(Activity activity, LightningView lightningView) {
        Preconditions.checkNonNull(activity);
        Preconditions.checkNonNull(lightningView);
        BrowserApp.getAppComponent().inject(this);
        this.mActivity = activity;
        this.mUIController = (UIController) activity;
        this.mLightningView = lightningView;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (this.mLightningView.isShown()) {
            this.mUIController.updateProgress(i);
        }
    }

    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        this.mLightningView.getTitleInfo().setFavicon(bitmap);
        this.mUIController.tabChanged(this.mLightningView);
        cacheFavicon(webView.getUrl(), bitmap);
    }

    private void cacheFavicon(String str, Bitmap bitmap) {
        if (bitmap != null && str != null && Uri.parse(str).getHost() != null) {
            this.mFaviconModel.cacheFaviconForUrl(bitmap, str).subscribeOn(Schedulers.m6232io()).subscribe();
        }
    }

    public void onReceivedTitle(WebView webView, String str) {
        if (str == null || str.isEmpty()) {
            this.mLightningView.getTitleInfo().setTitle(this.mActivity.getString(R.string.untitled));
        } else {
            this.mLightningView.getTitleInfo().setTitle(str);
        }
        this.mUIController.tabChanged(this.mLightningView);
        if (webView != null && webView.getUrl() != null) {
            this.mUIController.updateHistory(str, webView.getUrl());
        }
    }

    public void onGeolocationPermissionsShowPrompt(final String str, final GeolocationPermissions.Callback callback) {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this.mActivity, PERMISSIONS, (PermissionsResultAction) new PermissionsResultAction() {
            public void onDenied(String str) {
            }

            public void onGranted() {
                String str;
                AlertDialog.Builder builder = new AlertDialog.Builder(LightningChromeClient.this.mActivity);
                builder.setTitle((CharSequence) LightningChromeClient.this.mActivity.getString(R.string.location));
                if (str.length() > 50) {
                    str = str.subSequence(0, 50) + "...";
                } else {
                    str = str;
                }
                builder.setMessage((CharSequence) str + LightningChromeClient.this.mActivity.getString(R.string.message_location)).setCancelable(true).setPositiveButton((CharSequence) LightningChromeClient.this.mActivity.getString(R.string.action_allow), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, true, true);
                    }
                }).setNegativeButton((CharSequence) LightningChromeClient.this.mActivity.getString(R.string.action_dont_allow), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        callback.invoke(str, false, true);
                    }
                });
                AlertDialog create = builder.create();
                create.show();
                BrowserDialog.setDialogSize(LightningChromeClient.this.mActivity, create);
            }
        });
    }

    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        this.mUIController.onCreateWindow(message);
        return true;
    }

    public void onCloseWindow(WebView webView) {
        this.mUIController.onCloseWindow(this.mLightningView);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        this.mUIController.openFileChooser(valueCallback);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        this.mUIController.openFileChooser(valueCallback);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.mUIController.openFileChooser(valueCallback);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.mUIController.showFileChooser(valueCallback);
        return true;
    }

    public Bitmap getDefaultVideoPoster() {
        return BitmapFactory.decodeResource(this.mActivity.getResources(), 17301616);
    }

    public View getVideoLoadingProgressView() {
        return LayoutInflater.from(this.mActivity).inflate(R.layout.video_loading_progress, (ViewGroup) null);
    }

    public void onHideCustomView() {
        this.mUIController.onHideCustomView();
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.mUIController.onShowCustomView(view, customViewCallback);
    }

    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.mUIController.onShowCustomView(view, customViewCallback, i);
    }
}
