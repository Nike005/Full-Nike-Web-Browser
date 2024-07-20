package acr.browser.lightning.adapter;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private final List<HistoryItem> historyItemList = new ArrayList();
    private int theme;

    public long getItemId(int i) {
        return (long) i;
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.name = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", TextView.class);
            viewHolder.url = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.url, "field 'url'", TextView.class);
        }

        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.name = null;
                viewHolder.url = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HistoryAdapter(Context context2, int i) {
        this.context = context2;
        this.theme = i;
    }

    public void setBookmarks(List<HistoryItem> list) {
        this.historyItemList.clear();
        this.historyItemList.addAll(list);
    }

    public List<HistoryItem> getHistoryItemList() {
        return this.historyItemList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.downloads_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        HistoryItem historyItem = this.historyItemList.get(i);
        viewHolder.name.setText(historyItem.getTitle());
        viewHolder.name.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
        viewHolder.url.setText(historyItem.getUrl());
        viewHolder.url.setTextColor(BrowserApp.getThemeManager().getDisabledIconColor(this.theme));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131296669)
        TextView name;
        @BindView(2131296856)
        TextView url;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public int getItemCount() {
        return this.historyItemList.size();
    }
}
