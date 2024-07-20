package acr.browser.lightning.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.wnikebrow_13999769.R;

public class ReadingActivity_ViewBinding implements Unbinder {
    private ReadingActivity target;

    public ReadingActivity_ViewBinding(ReadingActivity readingActivity) {
        this(readingActivity, readingActivity.getWindow().getDecorView());
    }

    public ReadingActivity_ViewBinding(ReadingActivity readingActivity, View view) {
        this.target = readingActivity;
        readingActivity.mTitle = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.textViewTitle, "field 'mTitle'", TextView.class);
        readingActivity.mBody = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.textViewBody, "field 'mBody'", TextView.class);
    }

    public void unbind() {
        ReadingActivity readingActivity = this.target;
        if (readingActivity != null) {
            this.target = null;
            readingActivity.mTitle = null;
            readingActivity.mBody = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
