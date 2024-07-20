package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.internal.cast.zzev;

public class HelpTextView extends LinearLayout implements zzi {
    private TextView zzjx;
    private TextView zzjy;

    public HelpTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private static void zza(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public View asView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.zzjx = (TextView) zzev.checkNotNull((TextView) findViewById(C0069R.C0071id.cast_featurehighlight_help_text_header_view));
        this.zzjy = (TextView) zzev.checkNotNull((TextView) findViewById(C0069R.C0071id.cast_featurehighlight_help_text_body_view));
    }

    public void setText(CharSequence charSequence, CharSequence charSequence2) {
        zza(this.zzjx, charSequence);
        zza(this.zzjy, charSequence2);
    }
}
