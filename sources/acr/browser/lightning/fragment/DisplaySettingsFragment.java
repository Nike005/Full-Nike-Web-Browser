package acr.browser.lightning.fragment;

import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.dialog.BrowserDialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.wnikebrow_13999769.R;
import java.io.FileNotFoundException;

public class DisplaySettingsFragment extends LightningPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final float LARGE = 22.0f;
    private static final float MEDIUM = 18.0f;
    private static final String SETTINGS_DRAWERTABS = "cb_drawertabs";
    private static final String SETTINGS_FULLSCREEN = "fullscreen";
    private static final String SETTINGS_HIDESTATUSBAR = "fullScreenOption";
    private static final String SETTINGS_HOMEPAGEBACKGROUND = "setHomePageBackground";
    private static final String SETTINGS_OVERVIEWMODE = "overViewMode";
    private static final String SETTINGS_REFLOW = "text_reflow";
    private static final String SETTINGS_SWAPTABS = "cb_swapdrawers";
    private static final String SETTINGS_TEXTSIZE = "text_size";
    private static final String SETTINGS_THEME = "app_theme";
    private static final String SETTINGS_VIEWPORT = "wideViewPort";
    private static final float SMALL = 14.0f;
    private static final String TAG = "DisplaySettingsFragment";
    private static final float XLARGE = 26.0f;
    private static final float XSMALL = 10.0f;
    private static final float XXLARGE = 30.0f;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public int mCurrentTheme;
    /* access modifiers changed from: private */
    public String[] mThemeOptions;

    /* access modifiers changed from: private */
    public static float getTextSize(int i) {
        if (i == 0) {
            return XSMALL;
        }
        if (i == 1) {
            return SMALL;
        }
        if (i == 3) {
            return LARGE;
        }
        if (i == 4) {
            return XLARGE;
        }
        if (i != 5) {
            return 18.0f;
        }
        return XXLARGE;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preference_display);
        this.mActivity = getActivity();
        initPrefs();
    }

    private void initPrefs() {
        this.mThemeOptions = getResources().getStringArray(R.array.themes);
        this.mCurrentTheme = this.mPreferenceManager.getUseTheme();
        Preference findPreference = findPreference(SETTINGS_TEXTSIZE);
        Preference findPreference2 = findPreference(SETTINGS_HOMEPAGEBACKGROUND);
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) findPreference(SETTINGS_HIDESTATUSBAR);
        CheckBoxPreference checkBoxPreference2 = (CheckBoxPreference) findPreference(SETTINGS_FULLSCREEN);
        CheckBoxPreference checkBoxPreference3 = (CheckBoxPreference) findPreference(SETTINGS_VIEWPORT);
        CheckBoxPreference checkBoxPreference4 = (CheckBoxPreference) findPreference(SETTINGS_OVERVIEWMODE);
        CheckBoxPreference checkBoxPreference5 = (CheckBoxPreference) findPreference(SETTINGS_REFLOW);
        CheckBoxPreference checkBoxPreference6 = (CheckBoxPreference) findPreference(SETTINGS_DRAWERTABS);
        CheckBoxPreference checkBoxPreference7 = (CheckBoxPreference) findPreference(SETTINGS_SWAPTABS);
        findPreference2.setOnPreferenceClickListener(this);
        findPreference.setOnPreferenceClickListener(this);
        checkBoxPreference.setOnPreferenceChangeListener(this);
        checkBoxPreference2.setOnPreferenceChangeListener(this);
        checkBoxPreference3.setOnPreferenceChangeListener(this);
        checkBoxPreference4.setOnPreferenceChangeListener(this);
        checkBoxPreference5.setOnPreferenceChangeListener(this);
        checkBoxPreference6.setOnPreferenceChangeListener(this);
        checkBoxPreference7.setOnPreferenceChangeListener(this);
        checkBoxPreference.setChecked(this.mPreferenceManager.getHideStatusBarEnabled());
        checkBoxPreference2.setChecked(this.mPreferenceManager.getFullScreenEnabled());
        checkBoxPreference3.setChecked(this.mPreferenceManager.getUseWideViewportEnabled());
        checkBoxPreference4.setChecked(this.mPreferenceManager.getOverviewModeEnabled());
        checkBoxPreference5.setChecked(this.mPreferenceManager.getTextReflowEnabled());
        checkBoxPreference6.setChecked(this.mPreferenceManager.getShowTabsInDrawer(true));
        checkBoxPreference7.setChecked(this.mPreferenceManager.getBookmarksAndTabsSwapped());
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceClick(android.preference.Preference r6) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getKey()
            int r0 = r6.hashCode()
            r1 = -1037596717(0xffffffffc22787d3, float:-41.88264)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002f
            r1 = -248373282(0xfffffffff1321fde, float:-8.820297E29)
            if (r0 == r1) goto L_0x0025
            r1 = 1843099179(0x6ddb762b, float:8.490009E27)
            if (r0 == r1) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            java.lang.String r0 = "app_theme"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 0
            goto L_0x003a
        L_0x0025:
            java.lang.String r0 = "setHomePageBackground"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 2
            goto L_0x003a
        L_0x002f:
            java.lang.String r0 = "text_size"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0039
            r6 = 1
            goto L_0x003a
        L_0x0039:
            r6 = -1
        L_0x003a:
            if (r6 == 0) goto L_0x0048
            if (r6 == r4) goto L_0x0045
            if (r6 == r3) goto L_0x0041
            return r2
        L_0x0041:
            r5.imagePicker()
            return r4
        L_0x0045:
            r5.textSizePicker()
        L_0x0048:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.DisplaySettingsFragment.onPreferenceClick(android.preference.Preference):boolean");
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        boolean equals = obj instanceof Boolean ? Boolean.TRUE.equals(obj) : false;
        String key = preference.getKey();
        char c = 65535;
        switch (key.hashCode()) {
            case -1998425137:
                if (key.equals(SETTINGS_DRAWERTABS)) {
                    c = 5;
                    break;
                }
                break;
            case -1442669860:
                if (key.equals(SETTINGS_OVERVIEWMODE)) {
                    c = 3;
                    break;
                }
                break;
            case -730941133:
                if (key.equals(SETTINGS_REFLOW)) {
                    c = 4;
                    break;
                }
                break;
            case -600769351:
                if (key.equals(SETTINGS_VIEWPORT)) {
                    c = 2;
                    break;
                }
                break;
            case 87133391:
                if (key.equals(SETTINGS_SWAPTABS)) {
                    c = 6;
                    break;
                }
                break;
            case 110066619:
                if (key.equals(SETTINGS_FULLSCREEN)) {
                    c = 1;
                    break;
                }
                break;
            case 2028948528:
                if (key.equals(SETTINGS_HIDESTATUSBAR)) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mPreferenceManager.setHideStatusBarEnabled(equals);
                return true;
            case 1:
                this.mPreferenceManager.setFullScreenEnabled(equals);
                return true;
            case 2:
                this.mPreferenceManager.setUseWideViewportEnabled(equals);
                return true;
            case 3:
                this.mPreferenceManager.setOverviewModeEnabled(equals);
                return true;
            case 4:
                this.mPreferenceManager.setTextReflowEnabled(equals);
                return true;
            case 5:
                this.mPreferenceManager.setShowTabsInDrawer(equals);
                return true;
            case 6:
                this.mPreferenceManager.setBookmarkAndTabsSwapped(equals);
                return true;
            default:
                return false;
        }
    }

    private void rewardedTextSizePicker() {
        boolean z = System.currentTimeMillis() - this.mPreferenceManager.getRewardedVideoLastViewTime() > ((long) ((this.mPreferenceManager.getRewardedVideoInterval() * 60) * 1000));
        if (!this.mPreferenceManager.getRewardedVideoOnChangeTheme() || !z) {
            textSizePicker();
        } else {
            AppsgeyserSDK.getFastTrackAdsController().showRewardedVideoWithDialog(new FastTrackBaseAdapter.RewardedVideoListener() {
                boolean isVideoFinished;

                public void onVideoClicked() {
                }

                public void onVideoOpened() {
                }

                public void onVideoClosed() {
                    DisplaySettingsFragment.this.mPreferenceManager.setRewardedVideoLastViewTime(System.currentTimeMillis());
                    if (this.isVideoFinished) {
                        DisplaySettingsFragment.this.textSizePicker();
                    }
                }

                public void onVideoError(String str) {
                    DisplaySettingsFragment.this.textSizePicker();
                }

                public void onVideoFinished() {
                    this.isVideoFinished = true;
                }

                public void onVideoDeactivated() {
                    DisplaySettingsFragment.this.textSizePicker();
                }
            }, Constants.TAG_REWARDED_TEXT_SIZE_PICKER, getResources().getString(R.string.to_applay_change_watch_video), getResources().getString(R.string.action_ok), getResources().getString(R.string.action_cancel));
        }
    }

    /* access modifiers changed from: private */
    public void textSizePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LinearLayout linearLayout = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_seek_bar, (ViewGroup) null);
        final SeekBar seekBar = (SeekBar) linearLayout.findViewById(R.id.text_size_seekbar);
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.untitled);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        textView.setGravity(1);
        linearLayout.addView(textView);
        seekBar.setOnSeekBarChangeListener(new TextSeekBarListener(textView));
        seekBar.setMax(5);
        seekBar.setProgress(5 - this.mPreferenceManager.getTextSize());
        builder.setView((View) linearLayout);
        builder.setTitle((int) R.string.title_text_size);
        builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DisplaySettingsFragment.this.mPreferenceManager.setTextSize(5 - seekBar.getProgress());
            }
        });
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    private void imagePicker() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 1);
        Log.d(TAG, "imagePicker");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            try {
                BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(intent.getData())).compress(Bitmap.CompressFormat.JPEG, 90, getActivity().openFileOutput("back.jpg", 0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.mPreferenceManager.setBackgroundUrl("back.jpg");
        }
    }

    private void themePicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle((CharSequence) getResources().getString(R.string.theme));
        builder.setSingleChoiceItems((CharSequence[]) this.mThemeOptions, this.mPreferenceManager.getUseTheme(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DisplaySettingsFragment.this.mPreferenceManager.setUseTheme(i);
                int length = DisplaySettingsFragment.this.mThemeOptions.length;
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (DisplaySettingsFragment.this.mCurrentTheme != DisplaySettingsFragment.this.mPreferenceManager.getUseTheme()) {
                    DisplaySettingsFragment.this.getActivity().onBackPressed();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (DisplaySettingsFragment.this.mCurrentTheme != DisplaySettingsFragment.this.mPreferenceManager.getUseTheme()) {
                    DisplaySettingsFragment.this.getActivity().onBackPressed();
                }
            }
        });
        BrowserDialog.setDialogSize(this.mActivity, builder.show());
    }

    private static class TextSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        private final TextView mSample;

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        public TextSeekBarListener(TextView textView) {
            this.mSample = textView;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.mSample.setTextSize(DisplaySettingsFragment.getTextSize(i));
        }
    }
}
