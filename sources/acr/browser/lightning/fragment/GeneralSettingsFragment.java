package acr.browser.lightning.fragment;

import acr.browser.lightning.BuildConfig;
import acr.browser.lightning.activity.MainActivity;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.download.DownloadHandler;
import acr.browser.lightning.notifiction.WeatherNotification;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.utils.ThemeUtils;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RemoteViews;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import com.onesignal.OneSignalDbContract;
import com.wnikebrow_13999769.R;

public class GeneralSettingsFragment extends LightningPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final int API = Build.VERSION.SDK_INT;
    private static final String SETTINGS_ADS = "cb_ads";
    private static final String SETTINGS_COLORMODE = "cb_colormode";
    private static final String SETTINGS_DOWNLOAD = "download";
    private static final String SETTINGS_FLASH = "cb_flash";
    private static final String SETTINGS_HOME = "home";
    private static final String SETTINGS_IMAGES = "cb_images";
    private static final String SETTINGS_JAVASCRIPT = "cb_javascript";
    public static final String SETTINGS_NOTIFICATION_SEARCH_BAR = "cb_notification_search_bar";
    public static final String SETTINGS_NOTIFICATION_WEATHER = "cb_notification_weather";
    private static final String SETTINGS_PROXY = "proxy";
    private static final String SETTINGS_SEARCHENGINE = "search";
    private static final String SETTINGS_SUGGESTIONS = "suggestions_choice";
    private static final String SETTINGS_USERAGENT = "agent";
    /* access modifiers changed from: private */
    public Preference downloadloc;
    /* access modifiers changed from: private */
    public Preference home;
    /* access modifiers changed from: private */
    public Activity mActivity;
    private int mAgentChoice;
    private String mDownloadLocation;
    private String mHomepage;
    private CharSequence[] mProxyChoices;
    private Preference notificationSearchBar;
    /* access modifiers changed from: private */
    public Preference proxy;
    /* access modifiers changed from: private */
    public Preference searchengine;
    /* access modifiers changed from: private */
    public Preference searchsSuggestions;
    /* access modifiers changed from: private */
    public Preference useragent;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preference_general);
        this.mActivity = getActivity();
        initPrefs();
    }

    private void initPrefs() {
        this.proxy = findPreference(SETTINGS_PROXY);
        this.useragent = findPreference(SETTINGS_USERAGENT);
        this.downloadloc = findPreference(SETTINGS_DOWNLOAD);
        this.home = findPreference(SETTINGS_HOME);
        this.searchengine = findPreference(SETTINGS_SEARCHENGINE);
        this.searchsSuggestions = findPreference(SETTINGS_SUGGESTIONS);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(SETTINGS_FLASH);
        CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) findPreference(SETTINGS_ADS);
        CheckBoxPreference checkBoxPreference3 = (CheckBoxPreference) findPreference(SETTINGS_NOTIFICATION_SEARCH_BAR);
        CheckBoxPreference checkBoxPreference4 = (CheckBoxPreference) findPreference(SETTINGS_NOTIFICATION_WEATHER);
        CheckBoxPreference checkBoxPreference5 = (CheckBoxPreference) findPreference(SETTINGS_IMAGES);
        CheckBoxPreference checkBoxPreference6 = (CheckBoxPreference) findPreference(SETTINGS_JAVASCRIPT);
        this.proxy.setOnPreferenceClickListener(this);
        this.useragent.setOnPreferenceClickListener(this);
        this.downloadloc.setOnPreferenceClickListener(this);
        this.home.setOnPreferenceClickListener(this);
        this.searchsSuggestions.setOnPreferenceClickListener(this);
        this.searchengine.setOnPreferenceClickListener(this);
        checkBoxPreference.setOnPreferenceChangeListener(this);
        checkBoxPreference2.setOnPreferenceChangeListener(this);
        checkBoxPreference3.setOnPreferenceChangeListener(this);
        checkBoxPreference4.setOnPreferenceChangeListener(this);
        checkBoxPreference5.setOnPreferenceChangeListener(this);
        checkBoxPreference6.setOnPreferenceChangeListener(this);
        this.mAgentChoice = this.mPreferenceManager.getUserAgentChoice();
        this.mHomepage = this.mPreferenceManager.getHomepage();
        this.mDownloadLocation = this.mPreferenceManager.getDownloadDirectory();
        this.mProxyChoices = getResources().getStringArray(R.array.proxy_choices_array);
        int proxyChoice = this.mPreferenceManager.getProxyChoice();
        if (proxyChoice == 2) {
            Preference preference = this.proxy;
            preference.setSummary(this.mPreferenceManager.getProxyHost() + ':' + this.mPreferenceManager.getProxyPort());
        } else {
            this.proxy.setSummary(this.mProxyChoices[proxyChoice]);
        }
        boolean z = false;
        if (API >= 19) {
            this.mPreferenceManager.setFlashSupport(0);
        }
        setSearchEngineSummary(this.mPreferenceManager.getSearchChoice());
        this.downloadloc.setSummary(this.mDownloadLocation);
        int i = C317719.f4054xed2266b5[this.mPreferenceManager.getSearchSuggestionChoice().ordinal()];
        if (i == 1) {
            this.searchsSuggestions.setSummary(R.string.powered_by_google);
        } else if (i == 2) {
            this.searchsSuggestions.setSummary(R.string.powered_by_duck);
        } else if (i == 3) {
            this.searchsSuggestions.setSummary(R.string.powered_by_baidu);
        } else if (i == 4) {
            this.searchsSuggestions.setSummary(R.string.search_suggestions_off);
        }
        if (this.mHomepage.contains(Constants.SCHEME_HOMEPAGE)) {
            this.home.setSummary(getResources().getString(R.string.action_homepage));
        } else if (this.mHomepage.contains(Constants.SCHEME_BLANK)) {
            this.home.setSummary(getResources().getString(R.string.action_blank));
        } else if (this.mHomepage.contains(Constants.SCHEME_BOOKMARKS)) {
            this.home.setSummary(getResources().getString(R.string.action_bookmarks));
        } else {
            this.home.setSummary(this.mHomepage);
        }
        int i2 = this.mAgentChoice;
        if (i2 == 1) {
            this.useragent.setSummary(getResources().getString(R.string.agent_default));
        } else if (i2 == 2) {
            this.useragent.setSummary(getResources().getString(R.string.agent_desktop));
        } else if (i2 == 3) {
            this.useragent.setSummary(getResources().getString(R.string.agent_mobile));
        } else if (i2 == 4) {
            this.useragent.setSummary(getResources().getString(R.string.agent_custom));
        }
        int flashSupport = this.mPreferenceManager.getFlashSupport();
        boolean blockImagesEnabled = this.mPreferenceManager.getBlockImagesEnabled();
        boolean javaScriptEnabled = this.mPreferenceManager.getJavaScriptEnabled();
        checkBoxPreference2.setEnabled(true);
        checkBoxPreference3.setEnabled(true);
        checkBoxPreference3.setChecked(this.mPreferenceManager.getNotificationSearchBarEnabled());
        if (BrowserApp.getConfig().isWeatherWidgetEnabled()) {
            checkBoxPreference4.setEnabled(true);
            checkBoxPreference4.setChecked(this.mPreferenceManager.getNotificationWeatherEnabled());
        } else {
            ((PreferenceCategory) getPreferenceScreen().findPreference("general_settings")).removePreference(checkBoxPreference4);
        }
        if (API < 19) {
            checkBoxPreference.setEnabled(true);
        } else {
            checkBoxPreference.setEnabled(false);
            checkBoxPreference.setSummary(R.string.flash_not_supported);
        }
        checkBoxPreference5.setChecked(blockImagesEnabled);
        checkBoxPreference6.setChecked(javaScriptEnabled);
        checkBoxPreference.setChecked(flashSupport > 0);
        if (BuildConfig.FULL_VERSION && this.mPreferenceManager.getAdBlockEnabled()) {
            z = true;
        }
        checkBoxPreference2.setChecked(z);
    }

    /* renamed from: acr.browser.lightning.fragment.GeneralSettingsFragment$19 */
    static /* synthetic */ class C317719 {

        /* renamed from: $SwitchMap$acr$browser$lightning$preference$PreferenceManager$Suggestion */
        static final /* synthetic */ int[] f4054xed2266b5;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                acr.browser.lightning.preference.PreferenceManager$Suggestion[] r0 = acr.browser.lightning.preference.PreferenceManager.Suggestion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4054xed2266b5 = r0
                acr.browser.lightning.preference.PreferenceManager$Suggestion r1 = acr.browser.lightning.preference.PreferenceManager.Suggestion.SUGGESTION_GOOGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f4054xed2266b5     // Catch:{ NoSuchFieldError -> 0x001d }
                acr.browser.lightning.preference.PreferenceManager$Suggestion r1 = acr.browser.lightning.preference.PreferenceManager.Suggestion.SUGGESTION_DUCK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f4054xed2266b5     // Catch:{ NoSuchFieldError -> 0x0028 }
                acr.browser.lightning.preference.PreferenceManager$Suggestion r1 = acr.browser.lightning.preference.PreferenceManager.Suggestion.SUGGESTION_BAIDU     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f4054xed2266b5     // Catch:{ NoSuchFieldError -> 0x0033 }
                acr.browser.lightning.preference.PreferenceManager$Suggestion r1 = acr.browser.lightning.preference.PreferenceManager.Suggestion.SUGGESTION_NONE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.GeneralSettingsFragment.C317719.<clinit>():void");
        }
    }

    private void searchUrlPicker() {
        BrowserDialog.showEditText(this.mActivity, R.string.custom_url, R.string.custom_url, this.mPreferenceManager.getSearchUrl(), R.string.action_ok, new BrowserDialog.EditorListener() {
            public void onClick(String str) {
                GeneralSettingsFragment.this.mPreferenceManager.setSearchUrl(str);
                Preference access$100 = GeneralSettingsFragment.this.searchengine;
                access$100.setSummary(GeneralSettingsFragment.this.mActivity.getString(R.string.custom_url) + ": " + str);
            }
        });
    }

    private void getFlashChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) this.mActivity.getResources().getString(R.string.title_flash));
        builder.setMessage((CharSequence) getResources().getString(R.string.flash)).setCancelable(true).setPositiveButton((CharSequence) getResources().getString(R.string.action_manual), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GeneralSettingsFragment.this.mPreferenceManager.setFlashSupport(1);
            }
        }).setNegativeButton((CharSequence) getResources().getString(R.string.action_auto), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GeneralSettingsFragment.this.mPreferenceManager.setFlashSupport(2);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                GeneralSettingsFragment.this.mPreferenceManager.setFlashSupport(0);
            }
        });
        AlertDialog create = builder.create();
        create.show();
        BrowserDialog.setDialogSize(this.mActivity, create);
    }

    private void proxyChoicePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((int) R.string.http_proxy);
        builder.setSingleChoiceItems(this.mProxyChoices, this.mPreferenceManager.getProxyChoice(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GeneralSettingsFragment.this.setProxyChoice(i);
            }
        });
        builder.setPositiveButton((int) R.string.action_ok, (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    /* access modifiers changed from: private */
    public void setProxyChoice(int i) {
        if (i == 1) {
            i = ProxyUtils.setProxyChoice(i, this.mActivity);
        } else if (i == 2) {
            manualProxyPicker();
        }
        this.mPreferenceManager.setProxyChoice(i);
        CharSequence[] charSequenceArr = this.mProxyChoices;
        if (i < charSequenceArr.length) {
            this.proxy.setSummary(charSequenceArr[i]);
        }
    }

    private void manualProxyPicker() {
        View inflate = this.mActivity.getLayoutInflater().inflate(R.layout.dialog_manual_proxy, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.proxyHost);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.proxyPort);
        editText2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(Integer.toString(Integer.MAX_VALUE).length() - 1)});
        editText.setText(this.mPreferenceManager.getProxyHost());
        editText2.setText(Integer.toString(this.mPreferenceManager.getProxyPort()));
        BrowserDialog.setDialogSize(this.mActivity, new AlertDialog.Builder(this.mActivity).setTitle((int) R.string.manual_proxy).setView(inflate).setPositiveButton((int) R.string.action_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int i2;
                String obj = editText.getText().toString();
                try {
                    i2 = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException unused) {
                    i2 = GeneralSettingsFragment.this.mPreferenceManager.getProxyPort();
                }
                GeneralSettingsFragment.this.mPreferenceManager.setProxyHost(obj);
                GeneralSettingsFragment.this.mPreferenceManager.setProxyPort(i2);
                Preference access$300 = GeneralSettingsFragment.this.proxy;
                access$300.setSummary(obj + ':' + i2);
            }
        }).show());
    }

    /* access modifiers changed from: private */
    public void searchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.title_search_engine));
        builder.setSingleChoiceItems(new CharSequence[]{getResources().getString(R.string.custom_url), "Google", "Ask", "Bing", "Yahoo", "StartPage", "StartPage (Mobile)", "DuckDuckGo (Privacy)", "DuckDuckGo Lite (Privacy)", "Baidu (Chinese)", "Yandex (Russian)"}, this.mPreferenceManager.getSearchChoice(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GeneralSettingsFragment.this.mPreferenceManager.setSearchChoice(i);
                GeneralSettingsFragment.this.mPreferenceManager.setSearchEngineApplied(true);
                GeneralSettingsFragment.this.setSearchEngineSummary(i);
            }
        });
        builder.setPositiveButton((int) R.string.action_ok, (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void homepageDialog() {
        /*
            r7 = this;
            androidx.appcompat.app.AlertDialog$Builder r0 = new androidx.appcompat.app.AlertDialog$Builder
            android.app.Activity r1 = r7.mActivity
            r0.<init>(r1)
            r1 = 2131755329(0x7f100141, float:1.9141534E38)
            r0.setTitle((int) r1)
            acr.browser.lightning.preference.PreferenceManager r1 = r7.mPreferenceManager
            java.lang.String r1 = r1.getHomepage()
            r7.mHomepage = r1
            int r2 = r1.hashCode()
            r3 = -1145275824(0xffffffffbbbc7a50, float:-0.0057518855)
            r4 = 0
            r5 = 2
            r6 = 1
            if (r2 == r3) goto L_0x0040
            r3 = 322841383(0x133e2b27, float:2.4002647E-27)
            if (r2 == r3) goto L_0x0036
            r3 = 1396069548(0x533654ac, float:7.8310461E11)
            if (r2 == r3) goto L_0x002c
            goto L_0x004a
        L_0x002c:
            java.lang.String r2 = "about:home"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004a
            r1 = 0
            goto L_0x004b
        L_0x0036:
            java.lang.String r2 = "about:blank"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004a
            r1 = 1
            goto L_0x004b
        L_0x0040:
            java.lang.String r2 = "about:bookmarks"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x004a
            r1 = 2
            goto L_0x004b
        L_0x004a:
            r1 = -1
        L_0x004b:
            if (r1 == 0) goto L_0x0056
            if (r1 == r6) goto L_0x0055
            if (r1 == r5) goto L_0x0053
            r4 = 3
            goto L_0x0056
        L_0x0053:
            r4 = 2
            goto L_0x0056
        L_0x0055:
            r4 = 1
        L_0x0056:
            r1 = 2130903045(0x7f030005, float:1.7412897E38)
            acr.browser.lightning.fragment.GeneralSettingsFragment$8 r2 = new acr.browser.lightning.fragment.GeneralSettingsFragment$8
            r2.<init>()
            r0.setSingleChoiceItems((int) r1, (int) r4, (android.content.DialogInterface.OnClickListener) r2)
            android.content.res.Resources r1 = r7.getResources()
            r2 = 2131755086(0x7f10004e, float:1.9141041E38)
            java.lang.String r1 = r1.getString(r2)
            r2 = 0
            r0.setPositiveButton((java.lang.CharSequence) r1, (android.content.DialogInterface.OnClickListener) r2)
            androidx.appcompat.app.AlertDialog r0 = r0.show()
            android.app.Activity r1 = r7.mActivity
            acr.browser.lightning.dialog.BrowserDialog.setDialogSize(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.GeneralSettingsFragment.homepageDialog():void");
    }

    private void suggestionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.search_suggestions));
        int i = C317719.f4054xed2266b5[this.mPreferenceManager.getSearchSuggestionChoice().ordinal()];
        int i2 = 2;
        if (i == 1) {
            i2 = 0;
        } else if (i == 2) {
            i2 = 1;
        } else if (i != 3) {
            i2 = 3;
        }
        builder.setSingleChoiceItems((int) R.array.suggestions, i2, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GeneralSettingsFragment.this.mPreferenceManager.setSearchSuggestionChoice(PreferenceManager.Suggestion.SUGGESTION_GOOGLE);
                    GeneralSettingsFragment.this.searchsSuggestions.setSummary(R.string.powered_by_google);
                } else if (i == 1) {
                    GeneralSettingsFragment.this.mPreferenceManager.setSearchSuggestionChoice(PreferenceManager.Suggestion.SUGGESTION_DUCK);
                    GeneralSettingsFragment.this.searchsSuggestions.setSummary(R.string.powered_by_duck);
                } else if (i == 2) {
                    GeneralSettingsFragment.this.mPreferenceManager.setSearchSuggestionChoice(PreferenceManager.Suggestion.SUGGESTION_BAIDU);
                    GeneralSettingsFragment.this.searchsSuggestions.setSummary(R.string.powered_by_baidu);
                } else if (i == 3) {
                    GeneralSettingsFragment.this.mPreferenceManager.setSearchSuggestionChoice(PreferenceManager.Suggestion.SUGGESTION_NONE);
                    GeneralSettingsFragment.this.searchsSuggestions.setSummary(R.string.search_suggestions_off);
                }
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    /* access modifiers changed from: private */
    public void homePicker() {
        String homepage = this.mPreferenceManager.getHomepage();
        this.mHomepage = homepage;
        BrowserDialog.showEditText(this.mActivity, R.string.title_custom_homepage, R.string.title_custom_homepage, !homepage.startsWith(Constants.ABOUT) ? this.mHomepage : "https://www.google.com", R.string.action_ok, new BrowserDialog.EditorListener() {
            public void onClick(String str) {
                GeneralSettingsFragment.this.mPreferenceManager.setHomepage(str);
                GeneralSettingsFragment.this.home.setSummary(str);
            }
        });
    }

    private void downloadLocDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.title_download_location));
        String downloadDirectory = this.mPreferenceManager.getDownloadDirectory();
        this.mDownloadLocation = downloadDirectory;
        builder.setSingleChoiceItems((int) R.array.download_folder, downloadDirectory.contains(Environment.DIRECTORY_DOWNLOADS) ^ true ? 1 : 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    GeneralSettingsFragment.this.mPreferenceManager.setDownloadDirectory(DownloadHandler.DEFAULT_DOWNLOAD_PATH);
                    GeneralSettingsFragment.this.downloadloc.setSummary(DownloadHandler.DEFAULT_DOWNLOAD_PATH);
                } else if (i == 1) {
                    GeneralSettingsFragment.this.downPicker();
                }
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    private void agentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.title_user_agent));
        int userAgentChoice = this.mPreferenceManager.getUserAgentChoice();
        this.mAgentChoice = userAgentChoice;
        builder.setSingleChoiceItems((int) R.array.user_agent, userAgentChoice - 1, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                GeneralSettingsFragment.this.mPreferenceManager.setUserAgentChoice(i + 1);
                if (i == 0) {
                    GeneralSettingsFragment.this.useragent.setSummary(GeneralSettingsFragment.this.getResources().getString(R.string.agent_default));
                } else if (i == 1) {
                    GeneralSettingsFragment.this.useragent.setSummary(GeneralSettingsFragment.this.getResources().getString(R.string.agent_desktop));
                } else if (i == 2) {
                    GeneralSettingsFragment.this.useragent.setSummary(GeneralSettingsFragment.this.getResources().getString(R.string.agent_mobile));
                } else if (i == 3) {
                    GeneralSettingsFragment.this.useragent.setSummary(GeneralSettingsFragment.this.getResources().getString(R.string.agent_custom));
                    GeneralSettingsFragment.this.agentPicker();
                }
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) null);
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    /* access modifiers changed from: private */
    public void agentPicker() {
        BrowserDialog.showEditText(this.mActivity, R.string.title_user_agent, R.string.title_user_agent, this.mPreferenceManager.getUserAgentString(""), R.string.action_ok, new BrowserDialog.EditorListener() {
            public void onClick(String str) {
                GeneralSettingsFragment.this.mPreferenceManager.setUserAgentString(str);
                GeneralSettingsFragment.this.useragent.setSummary(GeneralSettingsFragment.this.mActivity.getString(R.string.agent_custom));
            }
        });
    }

    /* access modifiers changed from: private */
    public void downPicker() {
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.dialog_edit_text, (ViewGroup) null);
        final EditText editText = (EditText) inflate.findViewById(R.id.dialog_edit_text);
        int color = ContextCompat.getColor(this.mActivity, R.color.error_red);
        int textColor = ThemeUtils.getTextColor(this.mActivity);
        editText.setTextColor(textColor);
        editText.addTextChangedListener(new DownloadLocationTextWatcher(editText, color, textColor));
        editText.setText(this.mPreferenceManager.getDownloadDirectory());
        BrowserDialog.setDialogSize(this.mActivity, new AlertDialog.Builder(this.mActivity).setTitle((int) R.string.title_download_location).setView(inflate).setPositiveButton((int) R.string.action_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String addNecessarySlashes = DownloadHandler.addNecessarySlashes(editText.getText().toString());
                GeneralSettingsFragment.this.mPreferenceManager.setDownloadDirectory(addNecessarySlashes);
                GeneralSettingsFragment.this.downloadloc.setSummary(addNecessarySlashes);
            }
        }).show());
    }

    /* access modifiers changed from: private */
    public void setSearchEngineSummary(int i) {
        switch (i) {
            case 0:
                searchUrlPicker();
                return;
            case 1:
                this.searchengine.setSummary("Google");
                return;
            case 2:
                this.searchengine.setSummary("Ask");
                return;
            case 3:
                this.searchengine.setSummary("Bing");
                return;
            case 4:
                this.searchengine.setSummary("Yahoo");
                return;
            case 5:
                this.searchengine.setSummary("StartPage");
                return;
            case 6:
                this.searchengine.setSummary("StartPage (Mobile)");
                return;
            case 7:
                this.searchengine.setSummary("DuckDuckGo");
                return;
            case 8:
                this.searchengine.setSummary("DuckDuckGo Lite");
                return;
            case 9:
                this.searchengine.setSummary("Baidu");
                return;
            case 10:
                this.searchengine.setSummary("Yandex");
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceClick(android.preference.Preference r8) {
        /*
            r7 = this;
            java.lang.String r8 = r8.getKey()
            int r0 = r8.hashCode()
            r1 = 0
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r0) {
                case -906336856: goto L_0x0044;
                case 3208415: goto L_0x003a;
                case 92750597: goto L_0x0030;
                case 106941038: goto L_0x0026;
                case 1427818632: goto L_0x001c;
                case 2139097329: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x004e
        L_0x0012:
            java.lang.String r0 = "suggestions_choice"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 5
            goto L_0x004f
        L_0x001c:
            java.lang.String r0 = "download"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 2
            goto L_0x004f
        L_0x0026:
            java.lang.String r0 = "proxy"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 0
            goto L_0x004f
        L_0x0030:
            java.lang.String r0 = "agent"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 1
            goto L_0x004f
        L_0x003a:
            java.lang.String r0 = "home"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 3
            goto L_0x004f
        L_0x0044:
            java.lang.String r0 = "search"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x004e
            r8 = 4
            goto L_0x004f
        L_0x004e:
            r8 = -1
        L_0x004f:
            if (r8 == 0) goto L_0x00cb
            if (r8 == r6) goto L_0x00c7
            if (r8 == r5) goto L_0x00c3
            if (r8 == r4) goto L_0x00bf
            if (r8 == r3) goto L_0x0060
            if (r8 == r2) goto L_0x005c
            return r1
        L_0x005c:
            r7.suggestionsDialog()
            return r6
        L_0x0060:
            acr.browser.lightning.preference.PreferenceManager r8 = r7.mPreferenceManager
            boolean r8 = r8.getRewardedVideoOnChangeTheme()
            if (r8 == 0) goto L_0x00bb
            acr.browser.lightning.fragment.GeneralSettingsFragment$15 r8 = new acr.browser.lightning.fragment.GeneralSettingsFragment$15
            r8.<init>()
            com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController r0 = com.appsgeyser.sdk.AppsgeyserSDK.getFastTrackAdsController()
            java.lang.String r1 = "RW_searchEngine"
            boolean r0 = r0.getRewardedPlacementActivationStatus(r1)
            if (r0 == 0) goto L_0x00b7
            androidx.appcompat.app.AlertDialog$Builder r0 = new androidx.appcompat.app.AlertDialog$Builder
            android.app.Activity r1 = r7.mActivity
            r0.<init>(r1)
            android.content.res.Resources r1 = r7.getResources()
            r2 = 2131755528(0x7f100208, float:1.9141938E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setTitle((java.lang.CharSequence) r1)
            android.content.res.Resources r1 = r7.getResources()
            r2 = 2131755086(0x7f10004e, float:1.9141041E38)
            java.lang.String r1 = r1.getString(r2)
            acr.browser.lightning.fragment.GeneralSettingsFragment$16 r2 = new acr.browser.lightning.fragment.GeneralSettingsFragment$16
            r2.<init>(r8)
            r0.setPositiveButton((java.lang.CharSequence) r1, (android.content.DialogInterface.OnClickListener) r2)
            acr.browser.lightning.fragment.GeneralSettingsFragment$17 r8 = new acr.browser.lightning.fragment.GeneralSettingsFragment$17
            r8.<init>()
            java.lang.String r1 = "cancel"
            r0.setNegativeButton((java.lang.CharSequence) r1, (android.content.DialogInterface.OnClickListener) r8)
            acr.browser.lightning.fragment.GeneralSettingsFragment$18 r8 = new acr.browser.lightning.fragment.GeneralSettingsFragment$18
            r8.<init>()
            r0.setOnCancelListener(r8)
            r0.show()
            goto L_0x00be
        L_0x00b7:
            r7.searchDialog()
            goto L_0x00be
        L_0x00bb:
            r7.searchDialog()
        L_0x00be:
            return r6
        L_0x00bf:
            r7.homepageDialog()
            return r6
        L_0x00c3:
            r7.downloadLocDialog()
            return r6
        L_0x00c7:
            r7.agentDialog()
            return r6
        L_0x00cb:
            r7.proxyChoicePicker()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.GeneralSettingsFragment.onPreferenceClick(android.preference.Preference):boolean");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean equals = obj instanceof Boolean ? Boolean.TRUE.equals(obj) : false;
        String key = preference.getKey();
        char c = 65535;
        switch (key.hashCode()) {
            case -1367249712:
                if (key.equals(SETTINGS_ADS)) {
                    c = 1;
                    break;
                }
                break;
            case 337861648:
                if (key.equals(SETTINGS_FLASH)) {
                    c = 0;
                    break;
                }
                break;
            case 685391088:
                if (key.equals(SETTINGS_NOTIFICATION_SEARCH_BAR)) {
                    c = 4;
                    break;
                }
                break;
            case 836967552:
                if (key.equals(SETTINGS_NOTIFICATION_WEATHER)) {
                    c = 5;
                    break;
                }
                break;
            case 1970575960:
                if (key.equals(SETTINGS_IMAGES)) {
                    c = 2;
                    break;
                }
                break;
            case 2060386637:
                if (key.equals(SETTINGS_JAVASCRIPT)) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c != 0) {
            if (c == 1) {
                this.mPreferenceManager.setAdBlockEnabled(equals);
                return true;
            } else if (c == 2) {
                this.mPreferenceManager.setBlockImagesEnabled(equals);
                return true;
            } else if (c == 3) {
                this.mPreferenceManager.setJavaScriptEnabled(equals);
                return true;
            } else if (c == 4) {
                this.mPreferenceManager.setNotificationSearchEnabled(equals);
                if (equals) {
                    createSearchBarNotice();
                } else {
                    deleteSearchBarNotice();
                }
                return true;
            } else if (c != 5) {
                return false;
            } else {
                this.mPreferenceManager.setNotificationWeatherEnabled(equals);
                WeatherNotification weatherNotification = new WeatherNotification(getActivity(), this.mPreferenceManager.getWeatherData(), this.mPreferenceManager);
                if (equals) {
                    weatherNotification.show();
                } else {
                    weatherNotification.remove();
                }
                return true;
            }
        } else if (C3245Utils.isFlashInstalled(this.mActivity) || !equals) {
            if (equals) {
                getFlashChoice();
            } else {
                this.mPreferenceManager.setFlashSupport(0);
            }
            return true;
        } else {
            C3245Utils.createInformativeDialog(this.mActivity, R.string.title_warning, R.string.dialog_adobe_not_installed);
            this.mPreferenceManager.setFlashSupport(0);
            return false;
        }
    }

    private static class DownloadLocationTextWatcher implements TextWatcher {
        private final int errorColor;
        private final EditText getDownload;
        private final int regularColor;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public DownloadLocationTextWatcher(EditText editText, int i, int i2) {
            this.getDownload = editText;
            this.errorColor = i;
            this.regularColor = i2;
        }

        public void afterTextChanged(Editable editable) {
            if (!DownloadHandler.isWriteAccessAvailable(editable.toString())) {
                this.getDownload.setTextColor(this.errorColor);
            } else {
                this.getDownload.setTextColor(this.regularColor);
            }
        }
    }

    public void createSearchBarNotice() {
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(getActivity()).setSmallIcon(R.drawable.ic_search_white_24dp).setContent(new RemoteViews(getActivity().getPackageName(), R.layout.notification_search_bar)).setOngoing(true);
        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
        intent.putExtra("focus", true);
        TaskStackBuilder create = TaskStackBuilder.create(getActivity().getApplicationContext());
        create.addParentStack((Class<?>) MainActivity.class);
        create.addNextIntent(intent);
        ongoing.setContentIntent(PendingIntent.getActivity(getActivity().getApplicationContext(), (int) System.currentTimeMillis(), intent, 0));
        ((NotificationManager) getActivity().getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).notify(MainActivity.SEARCH_BAR_NOTIFICATION_ID, ongoing.build());
    }

    public void deleteSearchBarNotice() {
        ((NotificationManager) getActivity().getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).cancel(MainActivity.SEARCH_BAR_NOTIFICATION_ID);
    }
}
