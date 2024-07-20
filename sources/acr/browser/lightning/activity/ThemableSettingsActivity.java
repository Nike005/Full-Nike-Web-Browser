package acr.browser.lightning.activity;

import acr.browser.lightning.C2972R;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.preference.PreferenceManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import javax.inject.Inject;

public abstract class ThemableSettingsActivity extends AppCompatPreferenceActivity {
    @Inject
    PreferenceManager mPreferences;
    private int mTheme;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        BrowserApp.getAppComponent().inject(this);
        int useTheme = this.mPreferences.getUseTheme();
        this.mTheme = useTheme;
        if (useTheme == 0 || useTheme == 1) {
            setTheme(C2972R.style.Theme_SettingsTheme);
            getWindow().setBackgroundDrawable(new ColorDrawable(BrowserApp.getThemeManager().getBackgroundColor(this.mTheme)));
        } else if (useTheme == 2) {
            setTheme(C2972R.style.Theme_SettingsTheme_Dark);
            getWindow().setBackgroundDrawable(new ColorDrawable(BrowserApp.getThemeManager().getBackgroundColor(this.mTheme)));
        } else if (useTheme == 3) {
            setTheme(C2972R.style.Theme_SettingsTheme_Black);
            getWindow().setBackgroundDrawable(new ColorDrawable(BrowserApp.getThemeManager().getBackgroundColor(this.mTheme)));
        }
        super.onCreate(bundle);
        resetPreferences();
    }

    private void resetPreferences() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (this.mPreferences.getUseBlackStatusBar()) {
            getWindow().setStatusBarColor(-16777216);
        } else {
            getWindow().setStatusBarColor(BrowserApp.getThemeManager().getStatusBarColor(this.mPreferences.getUseTheme()));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        resetPreferences();
        if (this.mPreferences.getUseTheme() != this.mTheme) {
            restart();
        }
    }

    private void restart() {
        recreate();
    }
}
