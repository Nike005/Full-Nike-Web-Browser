package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.HomePageWidget;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class HistoryWidget implements HomePageWidget {
    public static final String LIST = "historyList";
    public static final String LIST_TRANSPARENT = "historyListTransparent";
    @BindView(2131296547)
    public CardView historyCard;
    @BindView(2131296548)
    public RecyclerView historyGrid;
    @BindView(2131296834)
    public TextView title;
    private View view;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HistoryWidget(android.content.Context r2, java.lang.String r3, int r4) {
        /*
            r1 = this;
            r1.<init>()
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)
            r4 = 2131492965(0x7f0c0065, float:1.8609397E38)
            r0 = 0
            android.view.View r2 = r2.inflate(r4, r0)
            r1.view = r2
            butterknife.ButterKnife.bind((java.lang.Object) r1, (android.view.View) r2)
            int r2 = r3.hashCode()
            r4 = -1010041184(0xffffffffc3cbfea0, float:-407.98926)
            r0 = 1
            if (r2 == r4) goto L_0x002e
            r4 = 351772498(0x14f79f52, float:2.5003467E-26)
            if (r2 == r4) goto L_0x0024
            goto L_0x0038
        L_0x0024:
            java.lang.String r2 = "historyList"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0038
            r2 = 0
            goto L_0x0039
        L_0x002e:
            java.lang.String r2 = "historyListTransparent"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0038
            r2 = 1
            goto L_0x0039
        L_0x0038:
            r2 = -1
        L_0x0039:
            if (r2 == 0) goto L_0x0054
            if (r2 == r0) goto L_0x003e
            goto L_0x0061
        L_0x003e:
            androidx.cardview.widget.CardView r2 = r1.historyCard
            acr.browser.lightning.config.ThemeManager r3 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            acr.browser.lightning.config.Config r4 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r4 = r4.getHistoryWidgetColor()
            int r3 = r3.getTransparentColor(r4)
            r2.setCardBackgroundColor((int) r3)
            goto L_0x0061
        L_0x0054:
            androidx.cardview.widget.CardView r2 = r1.historyCard
            acr.browser.lightning.config.Config r3 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r3 = r3.getHistoryWidgetColor()
            r2.setCardBackgroundColor((int) r3)
        L_0x0061:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.HistoryWidget.<init>(android.content.Context, java.lang.String, int):void");
    }

    public View getView() {
        return this.view;
    }

    public void setMargins(int i, int i2) {
        ((LinearLayout.LayoutParams) this.view.getLayoutParams()).setMargins(i, i, i, i);
        this.historyCard.setRadius((float) i2);
    }

    public Integer getOrderId() {
        return Integer.valueOf(BrowserApp.getConfig().getHistoryWidgetOrderId());
    }

    public int compareTo(HomePageWidget homePageWidget) {
        return getOrderId().compareTo(homePageWidget.getOrderId());
    }
}
