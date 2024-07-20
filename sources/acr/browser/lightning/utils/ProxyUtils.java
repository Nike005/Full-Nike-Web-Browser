package acr.browser.lightning.utils;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Activity;
import android.content.DialogInterface;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AlertDialog;
import com.wnikebrow_13999769.R;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import info.guardianproject.netcipher.webkit.WebkitProxy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProxyUtils {
    private static final String TAG = "ProxyUtils";
    @Inject
    PreferenceManager mPreferences;

    public boolean isProxyReady(Activity activity) {
        return true;
    }

    @Inject
    public ProxyUtils() {
        BrowserApp.getAppComponent().inject(this);
    }

    public void checkForProxy(final Activity activity) {
        boolean useProxy = this.mPreferences.getUseProxy();
        boolean isOrbotInstalled = OrbotHelper.isOrbotInstalled(activity);
        boolean z = isOrbotInstalled && !this.mPreferences.getCheckedForTor();
        if (!useProxy && z) {
            if (z) {
                this.mPreferences.setCheckedForTor(true);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            if (isOrbotInstalled) {
                builder.setTitle((CharSequence) activity.getResources().getString(R.string.http_proxy)).setSingleChoiceItems((CharSequence[]) activity.getResources().getStringArray(R.array.proxy_choices_array), this.mPreferences.getProxyChoice(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ProxyUtils.this.mPreferences.setProxyChoice(i);
                    }
                }).setPositiveButton((CharSequence) activity.getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ProxyUtils.this.mPreferences.getUseProxy()) {
                            ProxyUtils.this.initializeProxy(activity);
                        }
                    }
                });
            } else {
                C32383 r1 = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == -2) {
                            ProxyUtils.this.mPreferences.setProxyChoice(0);
                        } else if (i == -1) {
                            ProxyUtils.this.mPreferences.setProxyChoice(1);
                            ProxyUtils.this.initializeProxy(activity);
                        }
                    }
                };
                builder.setMessage((int) R.string.use_tor_prompt).setPositiveButton((int) R.string.yes, (DialogInterface.OnClickListener) r1).setNegativeButton((int) R.string.no, (DialogInterface.OnClickListener) r1);
            }
            BrowserDialog.setDialogSize(activity, builder.show());
        }
    }

    /* access modifiers changed from: private */
    public void initializeProxy(Activity activity) {
        int i;
        String str;
        int proxyChoice = this.mPreferences.getProxyChoice();
        if (proxyChoice != 0) {
            if (proxyChoice == 1) {
                if (!OrbotHelper.isOrbotRunning(activity)) {
                    OrbotHelper.requestStartTor(activity);
                }
                i = 8118;
                str = "localhost";
            } else if (proxyChoice != 2) {
                str = this.mPreferences.getProxyHost();
                i = this.mPreferences.getProxyPort();
            } else {
                str = this.mPreferences.getProxyHost();
                i = this.mPreferences.getProxyPort();
            }
            try {
                WebkitProxy.setProxy(BrowserApp.class.getName(), activity.getApplicationContext(), (WebView) null, str, i);
            } catch (Exception e) {
                Log.d(TAG, "error enabling web proxying", e);
            }
        }
    }

    public void updateProxySettings(Activity activity) {
        if (this.mPreferences.getUseProxy()) {
            initializeProxy(activity);
            return;
        }
        try {
            WebkitProxy.resetProxy(BrowserApp.class.getName(), activity.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int setProxyChoice(int i, Activity activity) {
        if (i != 1 || OrbotHelper.isOrbotInstalled(activity)) {
            return i;
        }
        C3245Utils.showSnackbar(activity, (int) R.string.install_orbot);
        return 0;
    }
}
