package acr.browser.lightning.fragment;

import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class TabsFragment_ViewBinding implements Unbinder {
    private TabsFragment target;

    public TabsFragment_ViewBinding(TabsFragment tabsFragment, View view) {
        this.target = tabsFragment;
        tabsFragment.mRecyclerView = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.tabs_list, "field 'mRecyclerView'", RecyclerView.class);
        tabsFragment.tabsBackground = (LinearLayout) C4621Utils.findRequiredViewAsType(view, R.id.tabs_background, "field 'tabsBackground'", LinearLayout.class);
    }

    public void unbind() {
        TabsFragment tabsFragment = this.target;
        if (tabsFragment != null) {
            this.target = null;
            tabsFragment.mRecyclerView = null;
            tabsFragment.tabsBackground = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
