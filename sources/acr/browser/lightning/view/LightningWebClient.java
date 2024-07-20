package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.utils.AdBlock;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.IntentUtils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.utils.UrlUtils;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.MailTo;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.MimeTypeMap;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.wnikebrow_13999769.R;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class LightningWebClient extends WebViewClient {
    private static final String TAG = "LightningWebClient";
    private final Activity mActivity;
    @Inject
    AdBlock mAdBlock;
    private final IntentUtils mIntentUtils;
    /* access modifiers changed from: private */
    public volatile boolean mIsRunning = false;
    private final LightningView mLightningView;
    @Inject
    ProxyUtils mProxyUtils;
    private final UIController mUIController;
    /* access modifiers changed from: private */
    public float mZoomScale = 0.0f;

    LightningWebClient(Activity activity, LightningView lightningView) {
        BrowserApp.getAppComponent().inject(this);
        Preconditions.checkNonNull(activity);
        Preconditions.checkNonNull(lightningView);
        this.mActivity = activity;
        this.mUIController = (UIController) activity;
        this.mLightningView = lightningView;
        this.mAdBlock.updatePreference();
        this.mIntentUtils = new IntentUtils(activity);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (this.mAdBlock.isAd(webResourceRequest.getUrl().toString())) {
            return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream("".getBytes()));
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (this.mAdBlock.isAd(str)) {
            return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream("".getBytes()));
        }
        return null;
    }

    public void onPageFinished(WebView webView, String str) {
        this.mUIController.onPageLoadFinish();
        this.mUIController.updateUrl(str, true);
        this.mUIController.setBackButtonEnabled(webView.canGoBack());
        this.mUIController.setForwardButtonEnabled(webView.canGoForward());
        webView.postInvalidate();
        if (webView.getTitle() == null || webView.getTitle().isEmpty()) {
            this.mLightningView.getTitleInfo().setTitle(this.mActivity.getString(R.string.untitled));
        } else {
            this.mLightningView.getTitleInfo().setTitle(webView.getTitle());
        }
        if (Build.VERSION.SDK_INT >= 19 && this.mLightningView.getInvertePage()) {
            webView.evaluateJavascript(Constants.JAVASCRIPT_INVERT_PAGE, (ValueCallback) null);
        }
        this.mUIController.tabChanged(this.mLightningView);
        this.mLightningView.setRefreshing(false);
        injectScriptFile(webView, "js/script.js");
        String additionalJsCode = AppsgeyserSDK.getAdditionalJsCode();
        if (additionalJsCode != null && !additionalJsCode.isEmpty()) {
            webView.loadUrl("javascript:(function(){ " + additionalJsCode + " })()");
        }
    }

    private void injectScriptFile(WebView webView, String str) {
        try {
            InputStream open = this.mActivity.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            String encodeToString = Base64.encodeToString(bArr, 2);
            webView.loadUrl("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var script = document.createElement('script');script.type = 'text/javascript';script.innerHTML = window.atob('" + encodeToString + "');parent.appendChild(script)})()");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.mLightningView.getTitleInfo().setFavicon((Bitmap) null);
        this.mLightningView.setView(str);
        if (UrlUtils.isStartPageUrl(str)) {
            this.mUIController.updateProgress(100);
        }
        this.mUIController.updateUrl(str, false);
        this.mUIController.showActionBar();
        this.mUIController.tabChanged(this.mLightningView);
    }

    public void onReceivedHttpAuthRequest(WebView webView, final HttpAuthHandler httpAuthHandler, String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.dialog_auth_request, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.auth_request_username_edittext);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.auth_request_password_edittext);
        ((TextView) inflate.findViewById(R.id.auth_request_realm_textview)).setText(this.mActivity.getString(R.string.label_realm, new Object[]{str2}));
        builder.setView(inflate).setTitle((int) R.string.title_sign_in).setCancelable(true).setPositiveButton((int) R.string.title_sign_in, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                httpAuthHandler.proceed(editText.getText().toString().trim(), editText2.getText().toString().trim());
                Log.d(LightningWebClient.TAG, "Attempting HTTP Authentication");
            }
        }).setNegativeButton((int) R.string.action_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                httpAuthHandler.cancel();
            }
        });
        AlertDialog create = builder.create();
        create.show();
        BrowserDialog.setDialogSize(this.mActivity, create);
    }

    public void onScaleChanged(final WebView webView, float f, final float f2) {
        if (webView.isShown() && this.mLightningView.mPreferences.getTextReflowEnabled() && Build.VERSION.SDK_INT >= 19 && !this.mIsRunning && Math.abs(100.0f - ((100.0f / this.mZoomScale) * f2)) > 2.5f && !this.mIsRunning) {
            this.mIsRunning = webView.postDelayed(new Runnable() {
                public void run() {
                    float unused = LightningWebClient.this.mZoomScale = f2;
                    webView.evaluateJavascript(Constants.JAVASCRIPT_TEXT_REFLOW, new ValueCallback<String>() {
                        public void onReceiveValue(String str) {
                            boolean unused = LightningWebClient.this.mIsRunning = false;
                        }
                    });
                }
            }, 100);
        }
    }

    private static List<Integer> getAllSslErrorMessageCodes(SslError sslError) {
        ArrayList arrayList = new ArrayList(1);
        if (sslError.hasError(4)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_date_invalid));
        }
        if (sslError.hasError(1)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_expired));
        }
        if (sslError.hasError(2)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_domain_mismatch));
        }
        if (sslError.hasError(0)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_not_yet_valid));
        }
        if (sslError.hasError(3)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_untrusted));
        }
        if (sslError.hasError(5)) {
            arrayList.add(Integer.valueOf(R.string.message_certificate_invalid));
        }
        return arrayList;
    }

    public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        List<Integer> allSslErrorMessageCodes = getAllSslErrorMessageCodes(sslError);
        StringBuilder sb = new StringBuilder();
        for (Integer intValue : allSslErrorMessageCodes) {
            sb.append(" - ");
            sb.append(this.mActivity.getString(intValue.intValue()));
            sb.append(10);
        }
        String string = this.mActivity.getString(R.string.message_insecure_connection, new Object[]{sb.toString()});
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) this.mActivity.getString(R.string.title_warning));
        builder.setMessage((CharSequence) string).setCancelable(true).setPositiveButton((CharSequence) this.mActivity.getString(R.string.action_yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                sslErrorHandler.proceed();
            }
        }).setNegativeButton((CharSequence) this.mActivity.getString(R.string.action_no), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                sslErrorHandler.cancel();
            }
        });
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    public void onFormResubmission(WebView webView, final Message message, final Message message2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) this.mActivity.getString(R.string.title_form_resubmission));
        builder.setMessage((CharSequence) this.mActivity.getString(R.string.message_form_resubmission)).setCancelable(true).setPositiveButton((CharSequence) this.mActivity.getString(R.string.action_yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                message2.sendToTarget();
            }
        }).setNegativeButton((CharSequence) this.mActivity.getString(R.string.action_no), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                message.sendToTarget();
            }
        });
        AlertDialog create = builder.create();
        create.show();
        BrowserDialog.setDialogSize(this.mActivity, create);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return shouldOverrideLoading(webView, webResourceRequest.getUrl().toString()) || super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return shouldOverrideLoading(webView, str) || super.shouldOverrideUrlLoading(webView, str);
    }

    private boolean shouldOverrideLoading(WebView webView, String str) {
        if (!this.mProxyUtils.isProxyReady(this.mActivity)) {
            return true;
        }
        Map<String, String> requestHeaders = this.mLightningView.getRequestHeaders();
        if (this.mLightningView.isIncognito()) {
            return continueLoadingUrl(webView, str, requestHeaders);
        }
        if (str.startsWith(Constants.ABOUT)) {
            return continueLoadingUrl(webView, str, requestHeaders);
        }
        if (isMailOrIntent(str, webView) || this.mIntentUtils.startActivityForUrl(webView, str)) {
            return true;
        }
        return continueLoadingUrl(webView, str, requestHeaders);
    }

    private boolean continueLoadingUrl(WebView webView, String str, Map<String, String> map) {
        if (map.isEmpty() || !C3245Utils.doesSupportHeaders()) {
            return false;
        }
        webView.loadUrl(str, map);
        return true;
    }

    private boolean isMailOrIntent(String str, WebView webView) {
        Intent intent;
        if (str.startsWith("mailto:")) {
            MailTo parse = MailTo.parse(str);
            this.mActivity.startActivity(C3245Utils.newEmailIntent(parse.getTo(), parse.getSubject(), parse.getBody(), parse.getCc()));
            webView.reload();
            return true;
        } else if (str.startsWith("intent://")) {
            try {
                intent = Intent.parseUri(str, 1);
            } catch (URISyntaxException unused) {
                intent = null;
            }
            if (intent == null) {
                return false;
            }
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setComponent((ComponentName) null);
            if (Build.VERSION.SDK_INT >= 15) {
                intent.setSelector((Intent) null);
            }
            try {
                this.mActivity.startActivity(intent);
            } catch (ActivityNotFoundException unused2) {
                Log.e(TAG, "ActivityNotFoundException");
            }
            return true;
        } else if (!str.startsWith(Constants.FILE)) {
            return false;
        } else {
            File file = new File(str.replace(Constants.FILE, ""));
            if (!file.exists()) {
                return false;
            }
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(C3245Utils.guessFileExtension(file.toString()));
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.addFlags(1073741824);
            intent2.addFlags(1);
            intent2.setDataAndType(FileProvider.getUriForFile(this.mActivity, this.mActivity.getApplicationContext().getPackageName() + ".fileprovider", file), mimeTypeFromExtension);
            try {
                this.mActivity.startActivity(intent2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
