package acr.browser.lightning.fragment;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.wnikebrow_13999769.R;

public class AboutSettingsFragment extends PreferenceFragment {
    private static final String SETTINGS_VERSION = "pref_version";
    private Activity mActivity;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preference_about);
        this.mActivity = getActivity();
        findPreference(SETTINGS_VERSION).setSummary(getVersion());
    }

    private String getVersion() {
        try {
            return this.mActivity.getPackageManager().getPackageInfo(this.mActivity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}
