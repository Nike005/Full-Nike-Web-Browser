package com.appsgeyser.sdk.p087ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import com.appsgeyser.sdk.C5051R;

/* renamed from: com.appsgeyser.sdk.ui.AppsgeyserProgressDialog */
public class AppsgeyserProgressDialog extends Dialog {
    public AppsgeyserProgressDialog(Context context) {
        super(context);
        setCancelable(false);
        setContentView(C5051R.layout.appsgeysersdk_progress_dialog);
    }

    public void show(Context context) {
        try {
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            if (Build.VERSION.SDK_INT >= 17) {
                if (!appCompatActivity.isDestroyed() || !appCompatActivity.isFinishing()) {
                    super.show();
                }
            } else if (!appCompatActivity.isFinishing()) {
                super.show();
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
