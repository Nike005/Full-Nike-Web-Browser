package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import com.google.android.gms.cast.AdBreakInfo;
import java.util.List;

public final class zzam extends View {
    private List<AdBreakInfo> zzdf;
    private final int zzpn;
    private int zzpo = 1;
    private Paint zzpp;

    public zzam(Context context) {
        super(context);
        long j;
        Context context2 = getContext();
        if (context2 == null) {
            j = Math.round(3.0d);
        } else {
            double d = (double) context2.getResources().getDisplayMetrics().density;
            Double.isNaN(d);
            j = Math.round(d * 3.0d);
        }
        this.zzpn = (int) j;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onDraw(android.graphics.Canvas r9) {
        /*
            r8 = this;
            monitor-enter(r8)
            super.onDraw(r9)     // Catch:{ all -> 0x0076 }
            java.util.List<com.google.android.gms.cast.AdBreakInfo> r0 = r8.zzdf     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0074
            java.util.List<com.google.android.gms.cast.AdBreakInfo> r0 = r8.zzdf     // Catch:{ all -> 0x0076 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0076 }
            if (r0 == 0) goto L_0x0011
            goto L_0x0074
        L_0x0011:
            int r0 = r8.getMeasuredHeight()     // Catch:{ all -> 0x0076 }
            float r0 = (float) r0     // Catch:{ all -> 0x0076 }
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            int r0 = java.lang.Math.round(r0)     // Catch:{ all -> 0x0076 }
            int r1 = r8.getMeasuredWidth()     // Catch:{ all -> 0x0076 }
            int r2 = r8.getPaddingLeft()     // Catch:{ all -> 0x0076 }
            int r1 = r1 - r2
            int r2 = r8.getPaddingRight()     // Catch:{ all -> 0x0076 }
            int r1 = r1 - r2
            java.util.List<com.google.android.gms.cast.AdBreakInfo> r2 = r8.zzdf     // Catch:{ all -> 0x0076 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0076 }
        L_0x0031:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0076 }
            if (r3 == 0) goto L_0x0072
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0076 }
            com.google.android.gms.cast.AdBreakInfo r3 = (com.google.android.gms.cast.AdBreakInfo) r3     // Catch:{ all -> 0x0076 }
            if (r3 == 0) goto L_0x0031
            long r3 = r3.getPlaybackPositionInMs()     // Catch:{ all -> 0x0076 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0031
            int r5 = r8.zzpo     // Catch:{ all -> 0x0076 }
            long r5 = (long) r5
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0031
            double r3 = (double) r3
            double r5 = (double) r1
            java.lang.Double.isNaN(r3)
            java.lang.Double.isNaN(r5)
            double r3 = r3 * r5
            int r5 = r8.zzpo     // Catch:{ all -> 0x0076 }
            double r5 = (double) r5
            java.lang.Double.isNaN(r5)
            double r3 = r3 / r5
            int r3 = (int) r3
            int r4 = r8.getPaddingLeft()     // Catch:{ all -> 0x0076 }
            int r4 = r4 + r3
            float r3 = (float) r4     // Catch:{ all -> 0x0076 }
            float r4 = (float) r0     // Catch:{ all -> 0x0076 }
            int r5 = r8.zzpn     // Catch:{ all -> 0x0076 }
            float r5 = (float) r5     // Catch:{ all -> 0x0076 }
            android.graphics.Paint r6 = r8.zzpp     // Catch:{ all -> 0x0076 }
            r9.drawCircle(r3, r4, r5, r6)     // Catch:{ all -> 0x0076 }
            goto L_0x0031
        L_0x0072:
            monitor-exit(r8)
            return
        L_0x0074:
            monitor-exit(r8)
            return
        L_0x0076:
            r9 = move-exception
            monitor-exit(r8)
            goto L_0x007a
        L_0x0079:
            throw r9
        L_0x007a:
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzam.onDraw(android.graphics.Canvas):void");
    }

    public final synchronized void zzb(List<AdBreakInfo> list, int i) {
        this.zzdf = list;
        Paint paint = new Paint(1);
        this.zzpp = paint;
        paint.setColor(-1);
        this.zzpp.setStyle(Paint.Style.FILL);
        invalidate();
    }

    public final synchronized void zzj(int i) {
        this.zzpo = i;
    }
}
