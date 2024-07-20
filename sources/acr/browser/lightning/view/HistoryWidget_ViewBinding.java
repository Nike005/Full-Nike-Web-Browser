package acr.browser.lightning.view;

import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class HistoryWidget_ViewBinding implements Unbinder {
    private HistoryWidget target;

    public HistoryWidget_ViewBinding(HistoryWidget historyWidget, View view) {
        this.target = historyWidget;
        historyWidget.historyGrid = (RecyclerView) C4621Utils.findRequiredViewAsType(view, R.id.historyGrid, "field 'historyGrid'", RecyclerView.class);
        historyWidget.historyCard = (CardView) C4621Utils.findRequiredViewAsType(view, R.id.historyCard, "field 'historyCard'", CardView.class);
        historyWidget.title = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
    }

    public void unbind() {
        HistoryWidget historyWidget = this.target;
        if (historyWidget != null) {
            this.target = null;
            historyWidget.historyGrid = null;
            historyWidget.historyCard = null;
            historyWidget.title = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
