package acr.browser.lightning.fragment;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class BookmarksFragment_ViewBinding implements Unbinder {
    private BookmarksFragment target;

    public BookmarksFragment_ViewBinding(BookmarksFragment bookmarksFragment, View view) {
        this.target = bookmarksFragment;
        bookmarksFragment.mBookmarksListView = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.right_drawer_list, "field 'mBookmarksListView'", RecyclerView.class);
        bookmarksFragment.mBookmarkTitleImage = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.starIcon, "field 'mBookmarkTitleImage'", ImageView.class);
        bookmarksFragment.mBookmarkImage = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.icon_star, "field 'mBookmarkImage'", ImageView.class);
    }

    public void unbind() {
        BookmarksFragment bookmarksFragment = this.target;
        if (bookmarksFragment != null) {
            this.target = null;
            bookmarksFragment.mBookmarksListView = null;
            bookmarksFragment.mBookmarkTitleImage = null;
            bookmarksFragment.mBookmarkImage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
