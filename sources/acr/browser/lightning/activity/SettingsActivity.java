package acr.browser.lightning.activity;

import acr.browser.lightning.app.BrowserApp;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import com.anthonycr.grant.PermissionsManager;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SettingsActivity extends ThemableSettingsActivity {
    private static final List<String> mFragments = new ArrayList(7);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
        LinearLayout linearLayout = (LinearLayout) View.inflate(this, R.layout.toolbar_settings, (ViewGroup) null);
        viewGroup.removeAllViews();
        linearLayout.addView((LinearLayout) viewGroup.getChildAt(0));
        viewGroup.addView(linearLayout);
        setSupportActionBar((Toolbar) linearLayout.findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onBuildHeaders(List<PreferenceActivity.Header> list) {
        loadHeadersFromResource(R.xml.preferences_headers, list);
        mFragments.clear();
        Iterator<PreferenceActivity.Header> it = list.iterator();
        while (it.hasNext()) {
            PreferenceActivity.Header next = it.next();
            if (Build.VERSION.SDK_INT < 21) {
                next.iconRes = R.drawable.empty;
            }
            if (next.titleRes != R.string.debug_title) {
                mFragments.add(next.fragment);
            } else if (BrowserApp.isRelease()) {
                it.remove();
            } else {
                mFragments.add(next.fragment);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isValidFragment(String str) {
        return mFragments.contains(str);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionsManager.getInstance().notifyPermissionsChange(strArr, iArr);
        super.onRequestPermissionsResult(i, strArr, iArr);
    }
}
