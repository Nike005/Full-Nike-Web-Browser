package acr.browser.lightning.fragment;

import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.dialog.BrowserDialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import androidx.appcompat.app.AlertDialog;
import com.wnikebrow_13999769.R;
import java.util.Arrays;

public class AdvancedSettingsFragment extends LightningPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final String SETTINGS_COOKIESINKOGNITO = "incognito_cookies";
    private static final String SETTINGS_ENABLECOOKIES = "allow_cookies";
    private static final String SETTINGS_NEWWINDOW = "allow_new_window";
    private static final String SETTINGS_RENDERINGMODE = "rendering_mode";
    private static final String SETTINGS_RESTORETABS = "restore_tabs";
    private static final String SETTINGS_TEXTENCODING = "text_encoding";
    private static final String SETTINGS_URLCONTENT = "url_contents";
    private CheckBoxPreference cbAllowPopups;
    private CheckBoxPreference cbcookiesInkognito;
    private CheckBoxPreference cbenablecookies;
    private CheckBoxPreference cbrestoreTabs;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public CharSequence[] mUrlOptions;
    /* access modifiers changed from: private */
    public Preference renderingmode;
    /* access modifiers changed from: private */
    public Preference textEncoding;
    /* access modifiers changed from: private */
    public Preference urlcontent;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preference_advanced);
        this.mActivity = getActivity();
        initPrefs();
    }

    private void initPrefs() {
        this.renderingmode = findPreference(SETTINGS_RENDERINGMODE);
        this.textEncoding = findPreference(SETTINGS_TEXTENCODING);
        this.urlcontent = findPreference(SETTINGS_URLCONTENT);
        this.cbAllowPopups = (CheckBoxPreference) findPreference(SETTINGS_NEWWINDOW);
        this.cbenablecookies = (CheckBoxPreference) findPreference(SETTINGS_ENABLECOOKIES);
        this.cbcookiesInkognito = (CheckBoxPreference) findPreference(SETTINGS_COOKIESINKOGNITO);
        this.cbrestoreTabs = (CheckBoxPreference) findPreference(SETTINGS_RESTORETABS);
        this.renderingmode.setOnPreferenceClickListener(this);
        this.textEncoding.setOnPreferenceClickListener(this);
        this.urlcontent.setOnPreferenceClickListener(this);
        this.cbAllowPopups.setOnPreferenceChangeListener(this);
        this.cbenablecookies.setOnPreferenceChangeListener(this);
        this.cbcookiesInkognito.setOnPreferenceChangeListener(this);
        this.cbrestoreTabs.setOnPreferenceChangeListener(this);
        int renderingMode = this.mPreferenceManager.getRenderingMode();
        if (renderingMode == 0) {
            this.renderingmode.setSummary(getString(R.string.name_normal));
        } else if (renderingMode == 1) {
            this.renderingmode.setSummary(getString(R.string.name_inverted));
        } else if (renderingMode == 2) {
            this.renderingmode.setSummary(getString(R.string.name_grayscale));
        } else if (renderingMode == 3) {
            this.renderingmode.setSummary(getString(R.string.name_inverted_grayscale));
        } else if (renderingMode == 4) {
            this.renderingmode.setSummary(getString(R.string.name_increase_contrast));
        }
        this.textEncoding.setSummary(this.mPreferenceManager.getTextEncoding());
        this.mUrlOptions = getResources().getStringArray(R.array.url_content_array);
        this.urlcontent.setSummary(this.mUrlOptions[this.mPreferenceManager.getUrlBoxContentChoice()]);
        this.cbAllowPopups.setChecked(this.mPreferenceManager.getPopupsEnabled());
        this.cbenablecookies.setChecked(this.mPreferenceManager.getCookiesEnabled());
        this.cbcookiesInkognito.setChecked(this.mPreferenceManager.getIncognitoCookiesEnabled());
        this.cbrestoreTabs.setChecked(this.mPreferenceManager.getRestoreLostTabsEnabled());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceClick(android.preference.Preference r6) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getKey()
            int r0 = r6.hashCode()
            r1 = -1727184010(0xffffffff990d4376, float:-7.303153E-24)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002f
            r1 = -996641814(0xffffffffc49873ea, float:-1219.6223)
            if (r0 == r1) goto L_0x0025
            r1 = 72653861(0x4549c25, float:2.4992178E-36)
            if (r0 == r1) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            java.lang.String r0 = "text_encoding"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 2
            goto L_0x003a
        L_0x0025:
            java.lang.String r0 = "url_contents"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 1
            goto L_0x003a
        L_0x002f:
            java.lang.String r0 = "rendering_mode"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 0
            goto L_0x003a
        L_0x0039:
            r6 = -1
        L_0x003a:
            if (r6 == 0) goto L_0x0049
            if (r6 == r4) goto L_0x0045
            if (r6 == r3) goto L_0x0041
            return r2
        L_0x0041:
            r5.textEncodingPicker()
            return r4
        L_0x0045:
            r5.urlBoxPicker()
            return r4
        L_0x0049:
            r5.renderPicker()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.AdvancedSettingsFragment.onPreferenceClick(android.preference.Preference):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceChange(android.preference.Preference r6, java.lang.Object r7) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getKey()
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -931384836: goto L_0x002e;
                case 202060697: goto L_0x0024;
                case 475993637: goto L_0x001a;
                case 1648984719: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0038
        L_0x0010:
            java.lang.String r0 = "restore_tabs"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 3
            goto L_0x0039
        L_0x001a:
            java.lang.String r0 = "allow_new_window"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 0
            goto L_0x0039
        L_0x0024:
            java.lang.String r0 = "allow_cookies"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 1
            goto L_0x0039
        L_0x002e:
            java.lang.String r0 = "incognito_cookies"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 2
            goto L_0x0039
        L_0x0038:
            r6 = -1
        L_0x0039:
            if (r6 == 0) goto L_0x0081
            if (r6 == r4) goto L_0x006c
            if (r6 == r3) goto L_0x0057
            if (r6 == r2) goto L_0x0042
            return r1
        L_0x0042:
            acr.browser.lightning.preference.PreferenceManager r6 = r5.mPreferenceManager
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
            r6.setRestoreLostTabsEnabled(r0)
            android.preference.CheckBoxPreference r6 = r5.cbrestoreTabs
            boolean r7 = r7.booleanValue()
            r6.setChecked(r7)
            return r4
        L_0x0057:
            acr.browser.lightning.preference.PreferenceManager r6 = r5.mPreferenceManager
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
            r6.setIncognitoCookiesEnabled(r0)
            android.preference.CheckBoxPreference r6 = r5.cbcookiesInkognito
            boolean r7 = r7.booleanValue()
            r6.setChecked(r7)
            return r4
        L_0x006c:
            acr.browser.lightning.preference.PreferenceManager r6 = r5.mPreferenceManager
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
            r6.setCookiesEnabled(r0)
            android.preference.CheckBoxPreference r6 = r5.cbenablecookies
            boolean r7 = r7.booleanValue()
            r6.setChecked(r7)
            return r4
        L_0x0081:
            acr.browser.lightning.preference.PreferenceManager r6 = r5.mPreferenceManager
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r0 = r7.booleanValue()
            r6.setPopupsEnabled(r0)
            android.preference.CheckBoxPreference r6 = r5.cbAllowPopups
            boolean r7 = r7.booleanValue()
            r6.setChecked(r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.AdvancedSettingsFragment.onPreferenceChange(android.preference.Preference, java.lang.Object):boolean");
    }

    private void renderPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.rendering_mode));
        builder.setSingleChoiceItems(new CharSequence[]{this.mActivity.getString(R.string.name_normal), this.mActivity.getString(R.string.name_inverted), this.mActivity.getString(R.string.name_grayscale), this.mActivity.getString(R.string.name_inverted_grayscale), this.mActivity.getString(R.string.name_increase_contrast)}, this.mPreferenceManager.getRenderingMode(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdvancedSettingsFragment.this.mPreferenceManager.setRenderingMode(i);
                if (i == 0) {
                    AdvancedSettingsFragment.this.renderingmode.setSummary(AdvancedSettingsFragment.this.getString(R.string.name_normal));
                } else if (i == 1) {
                    AdvancedSettingsFragment.this.renderingmode.setSummary(AdvancedSettingsFragment.this.getString(R.string.name_inverted));
                } else if (i == 2) {
                    AdvancedSettingsFragment.this.renderingmode.setSummary(AdvancedSettingsFragment.this.getString(R.string.name_grayscale));
                } else if (i == 3) {
                    AdvancedSettingsFragment.this.renderingmode.setSummary(AdvancedSettingsFragment.this.getString(R.string.name_inverted_grayscale));
                } else if (i == 4) {
                    AdvancedSettingsFragment.this.renderingmode.setSummary(AdvancedSettingsFragment.this.getString(R.string.name_increase_contrast));
                }
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    private void textEncodingPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.text_encoding));
        builder.setSingleChoiceItems((CharSequence[]) Constants.TEXT_ENCODINGS, Arrays.asList(Constants.TEXT_ENCODINGS).indexOf(this.mPreferenceManager.getTextEncoding()), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdvancedSettingsFragment.this.mPreferenceManager.setTextEncoding(Constants.TEXT_ENCODINGS[i]);
                AdvancedSettingsFragment.this.textEncoding.setSummary(Constants.TEXT_ENCODINGS[i]);
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    private void urlBoxPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.url_contents));
        builder.setSingleChoiceItems(this.mUrlOptions, this.mPreferenceManager.getUrlBoxContentChoice(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdvancedSettingsFragment.this.mPreferenceManager.setUrlBoxContentChoice(i);
                if (i < AdvancedSettingsFragment.this.mUrlOptions.length) {
                    AdvancedSettingsFragment.this.urlcontent.setSummary(AdvancedSettingsFragment.this.mUrlOptions[i]);
                }
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }
}
