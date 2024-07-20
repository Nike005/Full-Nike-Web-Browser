package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.HomePageWidget;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.BindView;

public class WeatherWidget implements HomePageWidget {
    public static final String PICTURE = "weatherDetailed";
    public static final String SIMPLE = "weatherSimple";
    public static final String THIN = "weatherFlat";
    @BindView(2131296426)
    public TextView celsiusButton;
    @BindView(2131296564)
    public ViewGroup imagePanel;
    @BindView(2131296593)
    public TextView location;
    @BindView(2131296807)
    public TextView temperature;
    @BindView(2131296808)
    public LinearLayout temperaturePanel;
    private View view;
    @BindView(2131296867)
    public ImageView weatherIcon;
    @BindView(2131296865)
    public TextView weatherText;
    @BindView(2131296866)
    public CardView weatherWidget;

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WeatherWidget(android.content.Context r6, java.lang.String r7, int r8) {
        /*
            r5 = this;
            r5.<init>()
            android.view.LayoutInflater r6 = android.view.LayoutInflater.from(r6)
            int r0 = r7.hashCode()
            r1 = -467747644(0xffffffffe41ebcc4, float:-1.171275E22)
            java.lang.String r2 = "weatherSimple"
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x0031
            r1 = 126390669(0x788918d, float:2.0548553E-34)
            if (r0 == r1) goto L_0x0027
            r1 = 1572114118(0x5db48ec6, float:1.62631926E18)
            if (r0 == r1) goto L_0x001f
            goto L_0x003b
        L_0x001f:
            boolean r0 = r7.equals(r2)
            if (r0 == 0) goto L_0x003b
            r0 = 0
            goto L_0x003c
        L_0x0027:
            java.lang.String r0 = "weatherFlat"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003b
            r0 = 2
            goto L_0x003c
        L_0x0031:
            java.lang.String r0 = "weatherDetailed"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x003b
            r0 = 1
            goto L_0x003c
        L_0x003b:
            r0 = -1
        L_0x003c:
            r1 = 0
            if (r0 == 0) goto L_0x0058
            if (r0 == r4) goto L_0x004e
            if (r0 == r3) goto L_0x0044
            goto L_0x0061
        L_0x0044:
            r0 = 2131493040(0x7f0c00b0, float:1.8609549E38)
            android.view.View r6 = r6.inflate(r0, r1)
            r5.view = r6
            goto L_0x0061
        L_0x004e:
            r0 = 2131493039(0x7f0c00af, float:1.8609547E38)
            android.view.View r6 = r6.inflate(r0, r1)
            r5.view = r6
            goto L_0x0061
        L_0x0058:
            r0 = 2131493038(0x7f0c00ae, float:1.8609545E38)
            android.view.View r6 = r6.inflate(r0, r1)
            r5.view = r6
        L_0x0061:
            android.view.View r6 = r5.view
            butterknife.ButterKnife.bind((java.lang.Object) r5, (android.view.View) r6)
            boolean r6 = r7.equals(r2)
            if (r6 == 0) goto L_0x0082
            androidx.cardview.widget.CardView r6 = r5.weatherWidget
            acr.browser.lightning.config.ThemeManager r7 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            acr.browser.lightning.config.Config r0 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r0 = r0.getWeatherWidgetColor()
            int r7 = r7.getTransparentColor(r0)
            r6.setCardBackgroundColor((int) r7)
            goto L_0x008f
        L_0x0082:
            androidx.cardview.widget.CardView r6 = r5.weatherWidget
            acr.browser.lightning.config.Config r7 = acr.browser.lightning.app.BrowserApp.getConfig()
            int r7 = r7.getWeatherWidgetColor()
            r6.setCardBackgroundColor((int) r7)
        L_0x008f:
            android.widget.TextView r6 = r5.location
            acr.browser.lightning.config.ThemeManager r7 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            int r7 = r7.getIconColor(r8)
            r6.setTextColor(r7)
            android.widget.TextView r6 = r5.temperature
            acr.browser.lightning.config.ThemeManager r7 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            int r7 = r7.getIconColor(r8)
            r6.setTextColor(r7)
            android.widget.TextView r6 = r5.weatherText
            acr.browser.lightning.config.ThemeManager r7 = acr.browser.lightning.app.BrowserApp.getThemeManager()
            int r7 = r7.getIconColor(r8)
            r6.setTextColor(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.WeatherWidget.<init>(android.content.Context, java.lang.String, int):void");
    }

    public View getView() {
        return this.view;
    }

    public void setMargins(int i, int i2) {
        ((LinearLayout.LayoutParams) this.view.getLayoutParams()).setMargins(i, i, i, i);
        this.weatherWidget.setRadius((float) i2);
    }

    public TextView getTemperature() {
        return this.temperature;
    }

    public ImageView getWeatherIcon() {
        return this.weatherIcon;
    }

    public TextView getLocation() {
        return this.location;
    }

    public TextView getWeatherText() {
        return this.weatherText;
    }

    public CardView getWeatherWidget() {
        return this.weatherWidget;
    }

    public TextView getCelsiusButton() {
        return this.celsiusButton;
    }

    public LinearLayout getTemperaturePanel() {
        return this.temperaturePanel;
    }

    public ViewGroup getImagePanel() {
        return this.imagePanel;
    }

    public Integer getOrderId() {
        return Integer.valueOf(BrowserApp.getConfig().getWeatherWidgetOrderId());
    }

    public int compareTo(HomePageWidget homePageWidget) {
        return getOrderId().compareTo(homePageWidget.getOrderId());
    }
}
