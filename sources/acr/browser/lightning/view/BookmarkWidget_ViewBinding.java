package acr.browser.lightning.view;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class BookmarkWidget_ViewBinding implements Unbinder {
    private BookmarkWidget target;

    public BookmarkWidget_ViewBinding(BookmarkWidget bookmarkWidget, View view) {
        this.target = bookmarkWidget;
        bookmarkWidget.bookmarksGrid = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.bookmarks_grid, "field 'bookmarksGrid'", RecyclerView.class);
        bookmarkWidget.bookmarksCard = (CardView) C4621Utils.findRequiredViewAsType(view, R.id.bookmarksCard, "field 'bookmarksCard'", CardView.class);
    }

    public void unbind() {
        BookmarkWidget bookmarkWidget = this.target;
        if (bookmarkWidget != null) {
            this.target = null;
            bookmarkWidget.bookmarksGrid = null;
            bookmarkWidget.bookmarksCard = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
