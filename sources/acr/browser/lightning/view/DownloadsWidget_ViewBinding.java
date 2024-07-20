package acr.browser.lightning.view;

import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class DownloadsWidget_ViewBinding implements Unbinder {
    private DownloadsWidget target;

    public DownloadsWidget_ViewBinding(DownloadsWidget downloadsWidget, View view) {
        this.target = downloadsWidget;
        downloadsWidget.downloadsGrid = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.downloads_grid, "field 'downloadsGrid'", RecyclerView.class);
        downloadsWidget.downloadsCard = (CardView) C4621Utils.findRequiredViewAsType(view, R.id.downloadsCard, "field 'downloadsCard'", CardView.class);
        downloadsWidget.title = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
    }

    public void unbind() {
        DownloadsWidget downloadsWidget = this.target;
        if (downloadsWidget != null) {
            this.target = null;
            downloadsWidget.downloadsGrid = null;
            downloadsWidget.downloadsCard = null;
            downloadsWidget.title = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
