package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.HomePageWidget;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class DownloadsWidget implements HomePageWidget {
    public static final String GRID = "downloadsGrid";
    public static final String GRID_TRANSPARENT = "downloadsGridTransparent";
    public static final String LIST = "downloadsList";
    public static final String LIST_TRANSPARENT = "downloadsListTransparent";
    @BindView(2131296486)
    public CardView downloadsCard;
    @BindView(2131296487)
    public RecyclerView downloadsGrid;
    @BindView(2131296834)
    public TextView title;
    private View view;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DownloadsWidget(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            r3.<init>()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0 = 2131492956(0x7f0c005c, float:1.8609379E38)
            r1 = 0
            android.view.View r4 = r4.inflate(r0, r1)
            r3.view = r4
            butterknife.ButterKnife.bind((java.lang.Object) r3, (android.view.View) r4)
            int r4 = r5.hashCode()
            r0 = 3
            r1 = 2
            r2 = 1
            switch(r4) {
                case -1270120087: goto L_0x003d;
                case 49011409: goto L_0x0033;
                case 49152041: goto L_0x0029;
                case 395701697: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0047
        L_0x001f:
            java.lang.String r4 = "downloadsGridTransparent"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 2
            goto L_0x0048
        L_0x0029:
            java.lang.String r4 = "downloadsList"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 1
            goto L_0x0048
        L_0x0033:
            java.lang.String r4 = "downloadsGrid"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 0
            goto L_0x0048
        L_0x003d:
            java.lang.String r4 = "downloadsListTransparent"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 3
            goto L_0x0048
        L_0x0047:
            r4 = -1
        L_0x0048:
            if (r4 == 0) goto L_0x0067
            if (r4 == r2) goto L_0x0067
            if (r4 == r1) goto L_0x0051
            if (r4 == r0) goto L_0x0051
            goto L_0x0074
        L_0x0051:
            androidx.cardview.widget.CardView r4 = r3.downloadsCard
            acr.browser.lightning.config.ThemeManager r5 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            acr.browser.lightning.config.Config r0 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r0 = r0.getDownloadsWidgetColor()
            int r5 = r5.getTransparentColor(r0)
            r4.setCardBackgroundColor((int) r5)
            goto L_0x0074
        L_0x0067:
            androidx.cardview.widget.CardView r4 = r3.downloadsCard
            acr.browser.lightning.config.Config r5 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r5 = r5.getDownloadsWidgetColor()
            r4.setCardBackgroundColor((int) r5)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.DownloadsWidget.<init>(android.content.Context, java.lang.String):void");
    }

    public View getView() {
        return this.view;
    }

    public void setMargins(int i, int i2) {
        ((LinearLayout.LayoutParams) this.view.getLayoutParams()).setMargins(i, i, i, i);
        this.downloadsCard.setRadius((float) i2);
    }

    public Integer getOrderId() {
        return Integer.valueOf(BrowserApp.getConfig().getDownloadsWidgetOrderId());
    }

    public int compareTo(HomePageWidget homePageWidget) {
        return getOrderId().compareTo(homePageWidget.getOrderId());
    }
}
