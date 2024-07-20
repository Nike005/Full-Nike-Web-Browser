package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.HomePageWidget;
import android.view.View;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class NewsWidget implements HomePageWidget {
    public static final String LIST = "newsList";
    public static final String LIST_REVERSED = "newsListReversed";
    public static final String LIST_TRANSPARENT = "newsListTransparent";
    public static final String LIST_TRANSPARENT_REVERSED = "newsListTransparentReversed";
    @BindView(2131296412)
    CardView newsCard;
    @BindView(2131296673)
    RecyclerView newsList;
    private View view;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NewsWidget(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            r3.<init>()
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0 = 2131492995(0x7f0c0083, float:1.8609458E38)
            r1 = 0
            android.view.View r4 = r4.inflate(r0, r1)
            r3.view = r4
            butterknife.ButterKnife.bind((java.lang.Object) r3, (android.view.View) r4)
            int r4 = r5.hashCode()
            r0 = 3
            r1 = 2
            r2 = 1
            switch(r4) {
                case -1354943871: goto L_0x003d;
                case -1110189325: goto L_0x0033;
                case 712871779: goto L_0x0029;
                case 1394609681: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0047
        L_0x001f:
            java.lang.String r4 = "newsList"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 0
            goto L_0x0048
        L_0x0029:
            java.lang.String r4 = "newsListTransparentReversed"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 3
            goto L_0x0048
        L_0x0033:
            java.lang.String r4 = "newsListReversed"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 1
            goto L_0x0048
        L_0x003d:
            java.lang.String r4 = "newsListTransparent"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0047
            r4 = 2
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
            androidx.cardview.widget.CardView r4 = r3.newsCard
            acr.browser.lightning.config.ThemeManager r5 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            acr.browser.lightning.config.Config r0 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r0 = r0.getNewsWidgetColor()
            int r5 = r5.getTransparentColor(r0)
            r4.setCardBackgroundColor((int) r5)
            goto L_0x0074
        L_0x0067:
            androidx.cardview.widget.CardView r4 = r3.newsCard
            acr.browser.lightning.config.Config r5 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r5 = r5.getNewsWidgetColor()
            r4.setCardBackgroundColor((int) r5)
        L_0x0074:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.NewsWidget.<init>(android.content.Context, java.lang.String):void");
    }

    public Integer getOrderId() {
        return Integer.valueOf(BrowserApp.getConfig().getNewsWidgetOrderId());
    }

    public View getView() {
        return this.view;
    }

    public void setMargins(int i, int i2) {
        ((LinearLayout.LayoutParams) this.view.getLayoutParams()).setMargins(i, i, i, i);
        this.newsCard.setRadius((float) i2);
    }

    public int compareTo(HomePageWidget homePageWidget) {
        return getOrderId().compareTo(homePageWidget.getOrderId());
    }
}
