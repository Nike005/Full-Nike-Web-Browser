package acr.browser.lightning.view;

import acr.browser.lightning.utils.ThemeUtils;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import androidx.core.content.ContextCompat;
import com.wnikebrow_13999769.R;

public class BackgroundDrawable extends TransitionDrawable {
    private boolean mSelected;

    public BackgroundDrawable(Context context) {
        super(new Drawable[]{new ColorDrawable(ContextCompat.getColor(context, R.color.transparent)), new ColorDrawable(ThemeUtils.getColor(context, R.attr.selectedBackground))});
    }

    public void startTransition(int i) {
        if (!this.mSelected) {
            super.startTransition(i);
        }
        this.mSelected = true;
    }

    public void reverseTransition(int i) {
        if (this.mSelected) {
            super.reverseTransition(i);
        }
        this.mSelected = false;
    }
}
