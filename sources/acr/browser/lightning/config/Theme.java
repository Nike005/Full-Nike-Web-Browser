package acr.browser.lightning.config;

public class Theme {
    private int backgroundColor;
    private int colorAccent;
    private int colorPrimary;
    private int colorPrimaryDark;
    private int disabledIconColor;
    private int dividerColor;
    private int drawerBackground;
    private int iconColor;
    private int statusBarColor;

    public Theme(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.colorPrimary = i;
        this.colorPrimaryDark = i2;
        this.colorAccent = i3;
        this.drawerBackground = i4;
        this.dividerColor = i5;
        this.statusBarColor = i6;
        this.backgroundColor = i7;
        this.iconColor = i8;
        this.disabledIconColor = i9;
    }

    public int getColorPrimary() {
        return this.colorPrimary;
    }

    public void setColorPrimary(int i) {
        this.colorPrimary = i;
    }

    public int getColorPrimaryDark() {
        return this.colorPrimaryDark;
    }

    public void setColorPrimaryDark(int i) {
        this.colorPrimaryDark = i;
    }

    public int getColorAccent() {
        return this.colorAccent;
    }

    public void setColorAccent(int i) {
        this.colorAccent = i;
    }

    public int getDrawerBackground() {
        return this.drawerBackground;
    }

    public void setDrawerBackground(int i) {
        this.drawerBackground = i;
    }

    public int getDividerColor() {
        return this.dividerColor;
    }

    public void setDividerColor(int i) {
        this.dividerColor = i;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }

    public void setStatusBarColor(int i) {
        this.statusBarColor = i;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public int getIconColor() {
        return this.iconColor;
    }

    public void setIconColor(int i) {
        this.iconColor = i;
    }

    public int getDisabledIconColor() {
        return this.disabledIconColor;
    }

    public void setDisabledIconColor(int i) {
        this.disabledIconColor = i;
    }
}
