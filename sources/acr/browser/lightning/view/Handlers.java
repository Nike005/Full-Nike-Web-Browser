package acr.browser.lightning.view;

import android.os.Handler;
import android.os.Looper;

public final class Handlers {
    public static final Handler MAIN = new Handler(Looper.getMainLooper());

    private Handlers() {
    }

    static {
        if (Looper.getMainLooper() == null) {
            Looper.prepareMainLooper();
        }
    }
}
