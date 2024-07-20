package acr.browser.lightning.utils;

import android.view.View;

public interface HomePageWidget extends Comparable<HomePageWidget> {
    Integer getOrderId();

    View getView();

    void setMargins(int i, int i2);
}
