package acr.browser.lightning.fragment;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.WebUtils;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.webkit.WebView;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Schedulers;
import com.wnikebrow_13999769.R;

public class PrivacySettingsFragment extends LightningPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final String SETTINGS_CACHEEXIT = "clear_cache_exit";
    private static final String SETTINGS_CLEARCACHE = "clear_cache";
    private static final String SETTINGS_CLEARCOOKIES = "clear_cookies";
    private static final String SETTINGS_CLEARHISTORY = "clear_history";
    private static final String SETTINGS_CLEARWEBSTORAGE = "clear_webstorage";
    private static final String SETTINGS_COOKIEEXIT = "clear_cookies_exit";
    private static final String SETTINGS_DONOTTRACK = "do_not_track";
    private static final String SETTINGS_HISTORYEXIT = "clear_history_exit";
    private static final String SETTINGS_IDENTIFYINGHEADERS = "remove_identifying_headers";
    private static final String SETTINGS_LOCATION = "location";
    private static final String SETTINGS_SAVEPASSWORD = "password";
    private static final String SETTINGS_THIRDPCOOKIES = "third_party";
    private static final String SETTINGS_WEBSTORAGEEXIT = "clear_webstorage_exit";
    private Activity mActivity;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
        addPreferencesFromResource(R.xml.preference_privacy);
        this.mActivity = getActivity();
        initPrefs();
    }

    private void initPrefs() {
        Preference findPreference = findPreference(SETTINGS_CLEARCACHE);
        Preference findPreference2 = findPreference(SETTINGS_CLEARHISTORY);
        Preference findPreference3 = findPreference(SETTINGS_CLEARCOOKIES);
        Preference findPreference4 = findPreference(SETTINGS_CLEARWEBSTORAGE);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference("location");
        CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) findPreference(SETTINGS_THIRDPCOOKIES);
        CheckBoxPreference checkBoxPreference3 = (CheckBoxPreference) findPreference(SETTINGS_SAVEPASSWORD);
        CheckBoxPreference checkBoxPreference4 = (CheckBoxPreference) findPreference(SETTINGS_CACHEEXIT);
        CheckBoxPreference checkBoxPreference5 = (CheckBoxPreference) findPreference(SETTINGS_HISTORYEXIT);
        CheckBoxPreference checkBoxPreference6 = (CheckBoxPreference) findPreference(SETTINGS_COOKIEEXIT);
        CheckBoxPreference checkBoxPreference7 = (CheckBoxPreference) findPreference(SETTINGS_WEBSTORAGEEXIT);
        CheckBoxPreference checkBoxPreference8 = (CheckBoxPreference) findPreference(SETTINGS_DONOTTRACK);
        CheckBoxPreference checkBoxPreference9 = (CheckBoxPreference) findPreference(SETTINGS_IDENTIFYINGHEADERS);
        findPreference.setOnPreferenceClickListener(this);
        findPreference2.setOnPreferenceClickListener(this);
        findPreference3.setOnPreferenceClickListener(this);
        findPreference4.setOnPreferenceClickListener(this);
        checkBoxPreference.setOnPreferenceChangeListener(this);
        checkBoxPreference2.setOnPreferenceChangeListener(this);
        checkBoxPreference3.setOnPreferenceChangeListener(this);
        checkBoxPreference4.setOnPreferenceChangeListener(this);
        checkBoxPreference5.setOnPreferenceChangeListener(this);
        checkBoxPreference6.setOnPreferenceChangeListener(this);
        checkBoxPreference7.setOnPreferenceChangeListener(this);
        checkBoxPreference8.setOnPreferenceChangeListener(this);
        checkBoxPreference9.setOnPreferenceChangeListener(this);
        checkBoxPreference.setChecked(this.mPreferenceManager.getLocationEnabled());
        checkBoxPreference3.setChecked(this.mPreferenceManager.getSavePasswordsEnabled());
        checkBoxPreference4.setChecked(this.mPreferenceManager.getClearCacheExit());
        checkBoxPreference5.setChecked(this.mPreferenceManager.getClearHistoryExitEnabled());
        checkBoxPreference6.setChecked(this.mPreferenceManager.getClearCookiesExitEnabled());
        checkBoxPreference2.setChecked(this.mPreferenceManager.getBlockThirdPartyCookiesEnabled());
        checkBoxPreference7.setChecked(this.mPreferenceManager.getClearWebStorageExitEnabled());
        boolean z = true;
        checkBoxPreference8.setChecked(this.mPreferenceManager.getDoNotTrackEnabled() && C3245Utils.doesSupportHeaders());
        checkBoxPreference9.setChecked(this.mPreferenceManager.getRemoveIdentifyingHeadersEnabled() && C3245Utils.doesSupportHeaders());
        checkBoxPreference8.setEnabled(C3245Utils.doesSupportHeaders());
        checkBoxPreference9.setEnabled(C3245Utils.doesSupportHeaders());
        checkBoxPreference9.setSummary("X-Requested-With, X-Wap-Profile");
        if (Build.VERSION.SDK_INT < 21) {
            z = false;
        }
        checkBoxPreference2.setEnabled(z);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceClick(android.preference.Preference r6) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getKey()
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1812683614: goto L_0x002e;
                case -1787428195: goto L_0x0024;
                case -1356829223: goto L_0x001a;
                case -1258153200: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0038
        L_0x0010:
            java.lang.String r0 = "clear_cache"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 0
            goto L_0x0039
        L_0x001a:
            java.lang.String r0 = "clear_webstorage"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 3
            goto L_0x0039
        L_0x0024:
            java.lang.String r0 = "clear_cookies"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 2
            goto L_0x0039
        L_0x002e:
            java.lang.String r0 = "clear_history"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 1
            goto L_0x0039
        L_0x0038:
            r6 = -1
        L_0x0039:
            if (r6 == 0) goto L_0x004e
            if (r6 == r4) goto L_0x004a
            if (r6 == r3) goto L_0x0046
            if (r6 == r2) goto L_0x0042
            return r1
        L_0x0042:
            r5.clearWebStorage()
            return r4
        L_0x0046:
            r5.clearCookiesDialog()
            return r4
        L_0x004a:
            r5.clearHistoryDialog()
            return r4
        L_0x004e:
            r5.clearCache()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.PrivacySettingsFragment.onPreferenceClick(android.preference.Preference):boolean");
    }

    private void clearHistoryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.title_clear_history));
        BrowserDialog.setDialogSize(this.mActivity, builder.setMessage((CharSequence) getResources().getString(R.string.dialog_history)).setPositiveButton((CharSequence) getResources().getString(R.string.action_yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrivacySettingsFragment.this.clearHistory().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        C3245Utils.showSnackbar(PrivacySettingsFragment.this.getActivity(), (int) R.string.message_clear_history);
                    }
                });
            }
        }).setNegativeButton((CharSequence) getResources().getString(R.string.action_no), (DialogInterface.OnClickListener) null).show());
    }

    private void clearCookiesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.title_clear_cookies));
        builder.setMessage((CharSequence) getResources().getString(R.string.dialog_cookies)).setPositiveButton((CharSequence) getResources().getString(R.string.action_yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PrivacySettingsFragment.this.clearCookies().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                        C3245Utils.showSnackbar(PrivacySettingsFragment.this.getActivity(), (int) R.string.message_cookies_cleared);
                    }
                });
            }
        }).setNegativeButton((CharSequence) getResources().getString(R.string.action_no), (DialogInterface.OnClickListener) null).show();
    }

    private void clearCache() {
        WebView webView = new WebView(this.mActivity);
        webView.clearCache(true);
        webView.destroy();
        C3245Utils.showSnackbar(this.mActivity, (int) R.string.message_cache_cleared);
    }

    /* access modifiers changed from: private */
    public Completable clearHistory() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                Activity activity = PrivacySettingsFragment.this.getActivity();
                if (activity != null) {
                    WebUtils.clearHistory(activity);
                    completableSubscriber.onComplete();
                }
                completableSubscriber.onError(new RuntimeException("Activity was null in clearHistory"));
            }
        });
    }

    /* access modifiers changed from: private */
    public Completable clearCookies() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                Activity activity = PrivacySettingsFragment.this.getActivity();
                if (activity != null) {
                    WebUtils.clearCookies(activity);
                    completableSubscriber.onComplete();
                }
                completableSubscriber.onError(new RuntimeException("Activity was null in clearCookies"));
            }
        });
    }

    private void clearWebStorage() {
        WebUtils.clearWebStorage();
        C3245Utils.showSnackbar(getActivity(), (int) R.string.message_web_storage_cleared);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceChange(android.preference.Preference r4, java.lang.Object r5) {
        /*
            r3 = this;
            java.lang.String r4 = r4.getKey()
            int r0 = r4.hashCode()
            r1 = 0
            r2 = 1
            switch(r0) {
                case -1730123221: goto L_0x005f;
                case -1207453342: goto L_0x0054;
                case -1188207973: goto L_0x004a;
                case -548501120: goto L_0x0040;
                case 439491086: goto L_0x0036;
                case 1020380100: goto L_0x002c;
                case 1216985755: goto L_0x0022;
                case 1338949869: goto L_0x0018;
                case 1901043637: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0069
        L_0x000e:
            java.lang.String r0 = "location"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 0
            goto L_0x006a
        L_0x0018:
            java.lang.String r0 = "clear_cache_exit"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 3
            goto L_0x006a
        L_0x0022:
            java.lang.String r0 = "password"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 2
            goto L_0x006a
        L_0x002c:
            java.lang.String r0 = "clear_webstorage_exit"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 6
            goto L_0x006a
        L_0x0036:
            java.lang.String r0 = "third_party"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 1
            goto L_0x006a
        L_0x0040:
            java.lang.String r0 = "clear_cookies_exit"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 5
            goto L_0x006a
        L_0x004a:
            java.lang.String r0 = "clear_history_exit"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 4
            goto L_0x006a
        L_0x0054:
            java.lang.String r0 = "remove_identifying_headers"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 8
            goto L_0x006a
        L_0x005f:
            java.lang.String r0 = "do_not_track"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0069
            r4 = 7
            goto L_0x006a
        L_0x0069:
            r4 = -1
        L_0x006a:
            switch(r4) {
                case 0: goto L_0x00ce;
                case 1: goto L_0x00c2;
                case 2: goto L_0x00b6;
                case 3: goto L_0x00aa;
                case 4: goto L_0x009e;
                case 5: goto L_0x0092;
                case 6: goto L_0x0086;
                case 7: goto L_0x007a;
                case 8: goto L_0x006e;
                default: goto L_0x006d;
            }
        L_0x006d:
            return r1
        L_0x006e:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setRemoveIdentifyingHeadersEnabled(r5)
            return r2
        L_0x007a:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setDoNotTrackEnabled(r5)
            return r2
        L_0x0086:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setClearWebStorageExitEnabled(r5)
            return r2
        L_0x0092:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setClearCookiesExitEnabled(r5)
            return r2
        L_0x009e:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setClearHistoryExitEnabled(r5)
            return r2
        L_0x00aa:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setClearCacheExit(r5)
            return r2
        L_0x00b6:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setSavePasswordsEnabled(r5)
            return r2
        L_0x00c2:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setBlockThirdPartyCookiesEnabled(r5)
            return r2
        L_0x00ce:
            acr.browser.lightning.preference.PreferenceManager r4 = r3.mPreferenceManager
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            r4.setLocationEnabled(r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.PrivacySettingsFragment.onPreferenceChange(android.preference.Preference, java.lang.Object):boolean");
    }
}
