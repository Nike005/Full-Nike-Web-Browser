package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.internal.cast.zzev;

final class zzj {
    private final Rect zzkf = new Rect();
    private final int zzkg;
    private final int zzkh;
    private final int zzki;
    private final int zzkj;
    private final zza zzkk;

    zzj(zza zza) {
        this.zzkk = (zza) zzev.checkNotNull(zza);
        Resources resources = zza.getResources();
        this.zzkg = resources.getDimensionPixelSize(C0069R.dimen.cast_libraries_material_featurehighlight_inner_radius);
        this.zzkh = resources.getDimensionPixelOffset(C0069R.dimen.cast_libraries_material_featurehighlight_inner_margin);
        this.zzki = resources.getDimensionPixelSize(C0069R.dimen.cast_libraries_material_featurehighlight_text_max_width);
        this.zzkj = resources.getDimensionPixelSize(C0069R.dimen.cast_libraries_material_featurehighlight_text_horizontal_offset);
    }

    private final int zza(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = i4 - i;
        int i6 = i2 - i4;
        int i7 = i4 - (i3 / 2);
        int i8 = this.zzkj;
        int i9 = i5 <= i6 ? i7 + i8 : i7 - i8;
        return i9 - marginLayoutParams.leftMargin < i ? i + marginLayoutParams.leftMargin : (i9 + i3) + marginLayoutParams.rightMargin > i2 ? (i2 - i3) - marginLayoutParams.rightMargin : i9;
    }

    private final void zza(View view, int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.min((i - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, this.zzki), 1073741824), View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE));
    }

    /* access modifiers changed from: package-private */
    public final void zza(Rect rect, Rect rect2) {
        View zzaq = this.zzkk.zzaq();
        boolean z = false;
        if (rect.isEmpty() || rect2.isEmpty()) {
            zzaq.layout(0, 0, 0, 0);
        } else {
            int centerY = rect.centerY();
            int centerX = rect.centerX();
            if (centerY < rect2.centerY()) {
                z = true;
            }
            int max = Math.max(this.zzkg * 2, rect.height()) / 2;
            int i = this.zzkh;
            int i2 = centerY + max + i;
            if (z) {
                zza(zzaq, rect2.width(), rect2.bottom - i2);
                int zza = zza(zzaq, rect2.left, rect2.right, zzaq.getMeasuredWidth(), centerX);
                zzaq.layout(zza, i2, zzaq.getMeasuredWidth() + zza, zzaq.getMeasuredHeight() + i2);
            } else {
                int i3 = (centerY - max) - i;
                zza(zzaq, rect2.width(), i3 - rect2.top);
                int zza2 = zza(zzaq, rect2.left, rect2.right, zzaq.getMeasuredWidth(), centerX);
                zzaq.layout(zza2, i3 - zzaq.getMeasuredHeight(), zzaq.getMeasuredWidth() + zza2, i3);
            }
        }
        this.zzkf.set(zzaq.getLeft(), zzaq.getTop(), zzaq.getRight(), zzaq.getBottom());
        this.zzkk.zzar().zzb(rect, this.zzkf);
        this.zzkk.zzas().zza(rect);
    }
}
