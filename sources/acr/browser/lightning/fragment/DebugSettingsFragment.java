package acr.browser.lightning.fragment;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class DebugSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private static final String LEAK_CANARY = "leak_canary_enabled";
    @Inject
    PreferenceManager mPreferenceManager;
    private SwitchPreference mSwitchLeakCanary;

    public boolean onPreferenceClick(Preference preference) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
        addPreferencesFromResource(R.xml.preference_debug);
        SwitchPreference switchPreference = (SwitchPreference) findPreference(LEAK_CANARY);
        this.mSwitchLeakCanary = switchPreference;
        switchPreference.setChecked(this.mPreferenceManager.getUseLeakCanary());
        this.mSwitchLeakCanary.setOnPreferenceChangeListener(this);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        String key = preference.getKey();
        if (((key.hashCode() == -45875466 && key.equals(LEAK_CANARY)) ? (char) 0 : 65535) != 0) {
            return false;
        }
        boolean equals = Boolean.TRUE.equals(obj);
        this.mPreferenceManager.setUseLeakCanary(equals);
        Activity activity = getActivity();
        if (activity != null) {
            C3245Utils.showSnackbar(activity, (int) R.string.app_restart);
        }
        this.mSwitchLeakCanary.setChecked(equals);
        return true;
    }
}
