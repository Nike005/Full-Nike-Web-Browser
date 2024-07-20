package acr.browser.lightning.view;

import android.view.View;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.google.android.material.tabs.TabLayout;
import com.wnikebrow_13999769.R;

public class HomepageView_ViewBinding implements Unbinder {
    private HomepageView target;

    public HomepageView_ViewBinding(HomepageView homepageView, View view) {
        this.target = homepageView;
        homepageView.backgroundImage = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.backgroundImage, "field 'backgroundImage'", ImageView.class);
        homepageView.pager = (ViewPager) C4621Utils.findRequiredViewAsType(view, R.id.pager, "field 'pager'", ViewPager.class);
        homepageView.tabLayout = (TabLayout) C4621Utils.findRequiredViewAsType(view, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    }

    public void unbind() {
        HomepageView homepageView = this.target;
        if (homepageView != null) {
            this.target = null;
            homepageView.backgroundImage = null;
            homepageView.pager = null;
            homepageView.tabLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
