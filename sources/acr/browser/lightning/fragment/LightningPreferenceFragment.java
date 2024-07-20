package acr.browser.lightning.fragment;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.preference.PreferenceManager;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import javax.inject.Inject;

public class LightningPreferenceFragment extends PreferenceFragment {
    @Inject
    PreferenceManager mPreferenceManager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
    }
}
