package acr.browser.lightning.view;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class HomePageMainTab_ViewBinding implements Unbinder {
    private HomePageMainTab target;

    public HomePageMainTab_ViewBinding(HomePageMainTab homePageMainTab, View view) {
        this.target = homePageMainTab;
        homePageMainTab.widgetsPanel = (LinearLayout) C4621Utils.findRequiredViewAsType(view, R.id.widgetsPanel, "field 'widgetsPanel'", LinearLayout.class);
    }

    public void unbind() {
        HomePageMainTab homePageMainTab = this.target;
        if (homePageMainTab != null) {
            this.target = null;
            homePageMainTab.widgetsPanel = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
