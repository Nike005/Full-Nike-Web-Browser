package acr.browser.lightning.view;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class NewsWidget_ViewBinding implements Unbinder {
    private NewsWidget target;

    public NewsWidget_ViewBinding(NewsWidget newsWidget, View view) {
        this.target = newsWidget;
        newsWidget.newsCard = (CardView) C4621Utils.findRequiredViewAsType(view, R.id.card, "field 'newsCard'", CardView.class);
        newsWidget.newsList = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.news_list, "field 'newsList'", RecyclerView.class);
    }

    public void unbind() {
        NewsWidget newsWidget = this.target;
        if (newsWidget != null) {
            this.target = null;
            newsWidget.newsCard = null;
            newsWidget.newsList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
