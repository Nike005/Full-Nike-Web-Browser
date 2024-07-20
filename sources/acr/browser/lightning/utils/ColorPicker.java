package acr.browser.lightning.utils;

import android.graphics.Color;

public class ColorPicker {
    private static int[] colors = {Color.parseColor("#f44336"), Color.parseColor("#9C27B0"), Color.parseColor("#009688"), Color.parseColor("#673AB7"), Color.parseColor("#E91E63"), Color.parseColor("#3F51B5"), Color.parseColor("#2196F3"), Color.parseColor("#4CAF50"), Color.parseColor("#8BC34A"), Color.parseColor("#FF9800"), Color.parseColor("#FF5722")};

    public static int getColor(long j) {
        int[] iArr = colors;
        return iArr[Math.abs((int) (j % ((long) iArr.length)))];
    }
}
