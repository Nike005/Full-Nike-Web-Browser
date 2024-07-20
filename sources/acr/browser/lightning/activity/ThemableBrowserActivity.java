package acr.browser.lightning.activity;

import acr.browser.lightning.C2972R;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.preference.PreferenceManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import javax.inject.Inject;

public abstract class ThemableBrowserActivity extends AppCompatActivity {
    @Inject
    PreferenceManager mPreferences;
    private boolean mShouldRunOnResumeActions = false;
    private boolean mShowTabsInDrawer;
    private int mTheme;

    /* access modifiers changed from: protected */
    public abstract boolean isIncognito();

    /* access modifiers changed from: package-private */
    public void onWindowVisibleToUserAfterResume() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        BrowserApp.getAppComponent().inject(this);
        this.mTheme = this.mPreferences.getUseTheme();
        this.mShowTabsInDrawer = this.mPreferences.getShowTabsInDrawer(!isTablet());
        if (isIncognito()) {
            this.mTheme = 2;
        }
        int i = this.mTheme;
        if (i == 0 || i == 1) {
            setTheme(C2972R.style.Theme_LightTheme);
        } else if (i == 2) {
            setTheme(C2972R.style.Theme_DarkTheme);
        } else if (i == 3) {
            setTheme(C2972R.style.Theme_BlackTheme);
        }
        super.onCreate(bundle);
        resetPreferences();
    }

    public PreferenceManager getmPreferences() {
        return this.mPreferences;
    }

    private void resetPreferences() {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (this.mPreferences.getUseBlackStatusBar()) {
            getWindow().setStatusBarColor(-16777216);
        } else {
            getWindow().setStatusBarColor(BrowserApp.getThemeManager().getStatusBarColor(this.mTheme));
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.mShouldRunOnResumeActions) {
            this.mShouldRunOnResumeActions = false;
            onWindowVisibleToUserAfterResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        resetPreferences();
        this.mShouldRunOnResumeActions = true;
        int useTheme = this.mPreferences.getUseTheme();
        if (isIncognito()) {
            useTheme = 2;
        }
        boolean showTabsInDrawer = this.mPreferences.getShowTabsInDrawer(true ^ isTablet());
        if (useTheme != this.mTheme || this.mShowTabsInDrawer != showTabsInDrawer) {
            restart();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isTablet() {
        return (getResources().getConfiguration().screenLayout & 15) == 4;
    }

    /* access modifiers changed from: package-private */
    public void restart() {
        finish();
        startActivity(new Intent(this, getClass()));
    }
}
