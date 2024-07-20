package acr.browser.lightning.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.anthonycr.progress.AnimatedProgressBar;
import com.wnikebrow_13999769.R;

public class BrowserActivity_ViewBinding implements Unbinder {
    private BrowserActivity target;

    public BrowserActivity_ViewBinding(BrowserActivity browserActivity) {
        this(browserActivity, browserActivity.getWindow().getDecorView());
    }

    public BrowserActivity_ViewBinding(BrowserActivity browserActivity, View view) {
        this.target = browserActivity;
        browserActivity.mDrawerLayout = (DrawerLayout) C4621Utils.findRequiredViewAsType(view, R.id.drawer_layout, "field 'mDrawerLayout'", DrawerLayout.class);
        browserActivity.mBrowserFrame = (FrameLayout) C4621Utils.findRequiredViewAsType(view, R.id.content_frame, "field 'mBrowserFrame'", FrameLayout.class);
        browserActivity.mDrawerLeft = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.left_drawer, "field 'mDrawerLeft'", ViewGroup.class);
        browserActivity.mDrawerRight = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.right_drawer, "field 'mDrawerRight'", ViewGroup.class);
        browserActivity.mUiLayout = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.ui_layout, "field 'mUiLayout'", ViewGroup.class);
        browserActivity.mToolbarLayout = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.toolbar_layout, "field 'mToolbarLayout'", ViewGroup.class);
        browserActivity.mProgressBar = (AnimatedProgressBar) C4621Utils.findRequiredViewAsType(view, R.id.progress_view, "field 'mProgressBar'", AnimatedProgressBar.class);
        browserActivity.mSearchBar = (RelativeLayout) C4621Utils.findRequiredViewAsType(view, R.id.search_bar, "field 'mSearchBar'", RelativeLayout.class);
        browserActivity.mToolbar = (Toolbar) C4621Utils.findRequiredViewAsType(view, R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    }

    public void unbind() {
        BrowserActivity browserActivity = this.target;
        if (browserActivity != null) {
            this.target = null;
            browserActivity.mDrawerLayout = null;
            browserActivity.mBrowserFrame = null;
            browserActivity.mDrawerLeft = null;
            browserActivity.mDrawerRight = null;
            browserActivity.mUiLayout = null;
            browserActivity.mToolbarLayout = null;
            browserActivity.mProgressBar = null;
            browserActivity.mSearchBar = null;
            browserActivity.mToolbar = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
