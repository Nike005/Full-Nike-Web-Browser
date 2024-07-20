package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.HomePageWidget;
import android.view.View;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class BookmarkWidget implements HomePageWidget {
    public static final String BIG_GRID = "bookmarkFlatGrid";
    public static final String GRID = "bookmarkGrid";
    public static final String LIST = "bookmarkList";
    public static final String LIST_2_COLUMNS = "bookmarkList2Columns";
    @BindView(2131296389)
    public CardView bookmarksCard;
    @BindView(2131296391)
    public RecyclerView bookmarksGrid;
    private View view;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BookmarkWidget(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            r4.<init>()
            android.view.LayoutInflater r5 = android.view.LayoutInflater.from(r5)
            int r0 = r6.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -952103499: goto L_0x0030;
                case 1412759103: goto L_0x0026;
                case 1950882204: goto L_0x001c;
                case 1951022836: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x003a
        L_0x0012:
            java.lang.String r0 = "bookmarkList"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x003a
            r6 = 2
            goto L_0x003b
        L_0x001c:
            java.lang.String r0 = "bookmarkGrid"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x003a
            r6 = 0
            goto L_0x003b
        L_0x0026:
            java.lang.String r0 = "bookmarkList2Columns"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x003a
            r6 = 3
            goto L_0x003b
        L_0x0030:
            java.lang.String r0 = "bookmarkFlatGrid"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x003a
            r6 = 1
            goto L_0x003b
        L_0x003a:
            r6 = -1
        L_0x003b:
            if (r6 == 0) goto L_0x0044
            if (r6 == r3) goto L_0x0044
            if (r6 == r2) goto L_0x0044
            if (r6 == r1) goto L_0x0044
            goto L_0x004e
        L_0x0044:
            r6 = 2131492924(0x7f0c003c, float:1.8609314E38)
            r0 = 0
            android.view.View r5 = r5.inflate(r6, r0)
            r4.view = r5
        L_0x004e:
            android.view.View r5 = r4.view
            butterknife.ButterKnife.bind((java.lang.Object) r4, (android.view.View) r5)
            androidx.cardview.widget.CardView r5 = r4.bookmarksCard
            acr.browser.lightning.config.ThemeManager r6 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            acr.browser.lightning.config.Config r0 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r0 = r0.getBookmarkWidgetColor()
            int r6 = r6.getTransparentColor(r0)
            r5.setCardBackgroundColor((int) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.BookmarkWidget.<init>(android.content.Context, java.lang.String):void");
    }

    public View getView() {
        return this.view;
    }

    public void setMargins(int i, int i2) {
        ((LinearLayout.LayoutParams) this.view.getLayoutParams()).setMargins(i, i, i, i);
        this.bookmarksCard.setRadius((float) i2);
    }

    public Integer getOrderId() {
        return Integer.valueOf(BrowserApp.getConfig().getBookmarkWidgetOrderId());
    }

    public int compareTo(HomePageWidget homePageWidget) {
        return getOrderId().compareTo(homePageWidget.getOrderId());
    }
}
