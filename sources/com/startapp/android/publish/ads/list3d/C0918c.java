package com.startapp.android.publish.ads.list3d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.LinkedList;

/* renamed from: com.startapp.android.publish.ads.list3d.c */
/* compiled from: StartAppSDK */
public class C0918c extends AdapterView<Adapter> {

    /* renamed from: A */
    private int f556A = Integer.MIN_VALUE;

    /* renamed from: B */
    private boolean f557B = false;

    /* renamed from: C */
    private boolean f558C = false;

    /* renamed from: D */
    private boolean f559D = false;

    /* renamed from: a */
    protected int f560a = 0;

    /* renamed from: b */
    protected int f561b;

    /* renamed from: c */
    protected int f562c;

    /* renamed from: d */
    protected int f563d;

    /* renamed from: e */
    protected int f564e;

    /* renamed from: f */
    protected int f565f;

    /* renamed from: g */
    protected int f566g;

    /* renamed from: h */
    protected int f567h;

    /* renamed from: i */
    protected int f568i;

    /* renamed from: j */
    protected Dynamics f569j;

    /* renamed from: k */
    protected float f570k = 0.0f;

    /* renamed from: l */
    protected boolean f571l = false;

    /* renamed from: m */
    protected boolean f572m = false;

    /* renamed from: n */
    protected String f573n;

    /* renamed from: o */
    protected String f574o;

    /* renamed from: p */
    public BroadcastReceiver f575p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("getHeight", C0918c.this.getHeight());
            double height = (double) C0918c.this.getHeight();
            double d = (double) intExtra;
            Double.isNaN(height);
            Double.isNaN(d);
            double d2 = height / d;
            C1270g.m2076a(3, C0918c.this.f576q + "Updating Position with Ratio: [" + d2 + "]");
            C0918c cVar = C0918c.this;
            cVar.f560a = intent.getIntExtra("mTouchState", cVar.f560a);
            C0918c cVar2 = C0918c.this;
            cVar2.f561b = intent.getIntExtra("mTouchStartX", cVar2.f561b);
            C0918c cVar3 = C0918c.this;
            cVar3.f562c = intent.getIntExtra("mTouchStartY", cVar3.f562c);
            C0918c cVar4 = C0918c.this;
            cVar4.f566g = intent.getIntExtra("mListRotation", cVar4.f566g);
            C0918c cVar5 = C0918c.this;
            double intExtra2 = (double) intent.getIntExtra("mFirstItemPosition", cVar5.f567h);
            Double.isNaN(intExtra2);
            cVar5.f567h = (int) (intExtra2 * d2);
            C0918c.this.f567h--;
            C0918c cVar6 = C0918c.this;
            double intExtra3 = (double) intent.getIntExtra("mLastItemPosition", cVar6.f568i);
            Double.isNaN(intExtra3);
            cVar6.f568i = (int) (intExtra3 * d2);
            C0918c.this.f568i--;
            C0918c cVar7 = C0918c.this;
            double intExtra4 = (double) intent.getIntExtra("mListTop", cVar7.f564e);
            Double.isNaN(intExtra4);
            cVar7.f564e = (int) (intExtra4 * d2);
            C0918c cVar8 = C0918c.this;
            double intExtra5 = (double) intent.getIntExtra("mListTopStart", cVar8.f563d);
            Double.isNaN(intExtra5);
            cVar8.f563d = (int) (intExtra5 * d2);
            C0918c cVar9 = C0918c.this;
            double intExtra6 = (double) intent.getIntExtra("mListTopOffset", cVar9.f565f);
            Double.isNaN(intExtra6);
            cVar9.f565f = (int) (intExtra6 * d2);
            C0918c.this.f569j = (Dynamics) intent.getParcelableExtra("mDynamics");
            C0918c cVar10 = C0918c.this;
            cVar10.f570k = intent.getFloatExtra("mLastVelocity", cVar10.f570k);
            C0918c.this.f569j.mo13981a(d2);
            C0918c.this.setAdapter(new C0917b(C0918c.this.getContext(), intent.getParcelableArrayListExtra("list"), "home", C0918c.this.f573n, C0918c.this.f574o));
            C0918c.this.f571l = true;
            C0918c.this.f572m = true;
            C0918c cVar11 = C0918c.this;
            cVar11.mo14054a(cVar11.f570k, true);
            C1275b.m2102a(context).mo15479a((BroadcastReceiver) this);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: q */
    public String f576q = "List3DView";

    /* renamed from: r */
    private Adapter f577r;

    /* renamed from: s */
    private VelocityTracker f578s;

    /* renamed from: t */
    private Runnable f579t;

    /* renamed from: u */
    private final LinkedList<View> f580u = new LinkedList<>();

    /* renamed from: v */
    private Runnable f581v;

    /* renamed from: w */
    private Rect f582w;

    /* renamed from: x */
    private Camera f583x;

    /* renamed from: y */
    private Matrix f584y;

    /* renamed from: z */
    private Paint f585z;

    public View getSelectedView() {
        return null;
    }

    public C0918c(Context context, AttributeSet attributeSet, String str, String str2) {
        super(context, attributeSet);
        this.f573n = str;
        this.f574o = str2;
    }

    public void setTag(String str) {
        this.f576q = str;
    }

    /* renamed from: a */
    public void mo14053a() {
        this.f571l = true;
    }

    public void setHint(boolean z) {
        this.f558C = z;
    }

    /* renamed from: b */
    public boolean mo14057b() {
        return this.f558C;
    }

    /* renamed from: c */
    public boolean mo14058c() {
        return this.f557B;
    }

    public void setFade(boolean z) {
        this.f557B = z;
    }

    public void setAdapter(Adapter adapter) {
        if (m713d() && mo14058c()) {
            C1261c.m2023a((View) this, 0.0f);
        }
        this.f577r = adapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    public Adapter getAdapter() {
        return this.f577r;
    }

    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    /* renamed from: d */
    private boolean m713d() {
        return C1261c.m2029a();
    }

    public void setDynamics(Dynamics dynamics) {
        Dynamics dynamics2 = this.f569j;
        if (dynamics2 != null) {
            dynamics.mo13983a(dynamics2.mo13980a(), this.f569j.mo13987b(), AnimationUtils.currentAnimationTimeMillis());
        }
        this.f569j = dynamics;
    }

    /* renamed from: e */
    private void m714e() {
        if (!this.f559D) {
            this.f559D = true;
            dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 0, 0.0f, 0.0f, 0));
            postDelayed(new Runnable() {
                public void run() {
                    C0918c.this.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 2, 0.0f, -20.0f, 0));
                    C0918c.this.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, -20.0f, 0));
                }
            }, 5);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getChildCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            float f = 0.0f;
            if (action == 1) {
                int i = this.f560a;
                if (i == 1) {
                    m705b((int) motionEvent.getX(), (int) motionEvent.getY());
                } else if (i == 2) {
                    this.f578s.addMovement(motionEvent);
                    this.f578s.computeCurrentVelocity(1000);
                    f = this.f578s.getYVelocity();
                    this.f570k = f;
                }
                m704b(f);
            } else if (action != 2) {
                m704b(0.0f);
            } else {
                if (this.f560a == 1) {
                    m706b(motionEvent);
                }
                if (this.f560a == 2) {
                    this.f578s.addMovement(motionEvent);
                    mo14055a(((int) motionEvent.getY()) - this.f562c);
                }
            }
        } else {
            if (m713d()) {
                C1261c.m2024a((View) this, 1500);
            }
            m701a(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f571l && this.f577r != null) {
            if (getChildCount() == 0) {
                if (mo14057b()) {
                    this.f564e = getHeight() / 3;
                }
                if (!this.f572m) {
                    this.f568i = -1;
                } else {
                    int i5 = this.f567h;
                    this.f568i = i5;
                    this.f567h = i5 + 1;
                }
                m709c(this.f564e, 0);
            } else {
                int a = (this.f564e + this.f565f) - mo14052a(getChildAt(0));
                m708c(a);
                m711d(a);
            }
            m717h();
            if (mo14057b()) {
                m714e();
            }
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return super.drawChild(canvas, view, j);
        }
        int top = view.getTop();
        int left = view.getLeft();
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float height2 = (float) (getHeight() / 2);
        float f = (((float) (top + height)) - height2) / height2;
        float cos = (float) (1.0d - ((1.0d - Math.cos((double) f)) * 0.15000000596046448d));
        float f2 = (((float) this.f566g) - (f * 20.0f)) % 90.0f;
        if (f2 < 0.0f) {
            f2 += 90.0f;
        }
        float f3 = f2;
        if (f3 < 45.0f) {
            Canvas canvas2 = canvas;
            Bitmap bitmap = drawingCache;
            int i = top;
            int i2 = left;
            int i3 = width;
            int i4 = height;
            float f4 = cos;
            m700a(canvas2, bitmap, i, i2, i3, i4, f4, f3 - 90.0f);
            m700a(canvas2, bitmap, i, i2, i3, i4, f4, f3);
            return false;
        }
        Canvas canvas3 = canvas;
        Bitmap bitmap2 = drawingCache;
        int i5 = top;
        int i6 = left;
        int i7 = width;
        int i8 = height;
        float f5 = cos;
        m700a(canvas3, bitmap2, i5, i6, i7, i8, f5, f3);
        m700a(canvas3, bitmap2, i5, i6, i7, i8, f5, f3 - 90.0f);
        return false;
    }

    /* renamed from: a */
    private void m700a(Canvas canvas, Bitmap bitmap, int i, int i2, int i3, int i4, float f, float f2) {
        if (this.f583x == null) {
            this.f583x = new Camera();
        }
        this.f583x.save();
        this.f583x.translate(0.0f, 0.0f, (float) i4);
        this.f583x.rotateX(f2);
        float f3 = (float) (-i4);
        this.f583x.translate(0.0f, 0.0f, f3);
        if (this.f584y == null) {
            this.f584y = new Matrix();
        }
        this.f583x.getMatrix(this.f584y);
        this.f583x.restore();
        this.f584y.preTranslate((float) (-i3), f3);
        this.f584y.postScale(f, f);
        this.f584y.postTranslate((float) (i2 + i3), (float) (i + i4));
        if (this.f585z == null) {
            Paint paint = new Paint();
            this.f585z = paint;
            paint.setAntiAlias(true);
            this.f585z.setFilterBitmap(true);
        }
        this.f585z.setColorFilter(m698a(f2));
        canvas.drawBitmap(bitmap, this.f584y, this.f585z);
    }

    /* renamed from: a */
    private LightingColorFilter m698a(float f) {
        double d = (double) f;
        Double.isNaN(d);
        double cos = Math.cos((d * 3.141592653589793d) / 180.0d);
        int i = ((int) (cos * 200.0d)) + 55;
        int pow = (int) (Math.pow(cos, 200.0d) * 70.0d);
        if (i > 255) {
            i = 255;
        }
        if (pow > 255) {
            pow = 255;
        }
        return new LightingColorFilter(Color.rgb(i, i, i), Color.rgb(pow, pow, pow));
    }

    /* renamed from: a */
    private void m701a(MotionEvent motionEvent) {
        removeCallbacks(this.f579t);
        this.f561b = (int) motionEvent.getX();
        this.f562c = (int) motionEvent.getY();
        this.f563d = mo14052a(getChildAt(0)) - this.f565f;
        m716g();
        VelocityTracker obtain = VelocityTracker.obtain();
        this.f578s = obtain;
        obtain.addMovement(motionEvent);
        this.f560a = 1;
    }

    /* renamed from: b */
    private void m704b(float f) {
        mo14054a(f, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14054a(float f, boolean z) {
        if (this.f578s != null || z) {
            VelocityTracker velocityTracker = this.f578s;
            if (velocityTracker != null) {
                velocityTracker.recycle();
            }
            this.f578s = null;
            removeCallbacks(this.f581v);
            if (this.f579t == null) {
                this.f579t = new Runnable() {
                    public void run() {
                        if (C0918c.this.f569j != null) {
                            View childAt = C0918c.this.getChildAt(0);
                            if (childAt != null) {
                                C0918c cVar = C0918c.this;
                                cVar.f563d = cVar.mo14052a(childAt) - C0918c.this.f565f;
                                C0918c.this.f569j.mo13985a(AnimationUtils.currentAnimationTimeMillis());
                                C0918c cVar2 = C0918c.this;
                                cVar2.mo14055a(((int) cVar2.f569j.mo13980a()) - C0918c.this.f563d);
                            }
                            if (!C0918c.this.f569j.mo13986a(0.5f, 0.4f)) {
                                C0918c.this.postDelayed(this, 16);
                            }
                        }
                    }
                };
            }
            Dynamics dynamics = this.f569j;
            if (dynamics != null) {
                if (!z) {
                    dynamics.mo13983a((float) this.f564e, f, AnimationUtils.currentAnimationTimeMillis());
                }
                post(this.f579t);
            }
            this.f560a = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f579t);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14055a(int i) {
        int i2 = this.f563d + i;
        this.f564e = i2;
        this.f566g = (-(i2 * 270)) / getHeight();
        m715f();
        requestLayout();
    }

    /* renamed from: f */
    private void m715f() {
        int i;
        int i2 = this.f566g;
        int i3 = i2 % 90;
        if (i3 < 45) {
            i = ((-(i2 - i3)) * getHeight()) / 270;
        } else {
            i = ((-((i2 + 90) - i3)) * getHeight()) / 270;
        }
        if (this.f556A == Integer.MIN_VALUE && this.f568i == this.f577r.getCount() - 1 && m707c(getChildAt(getChildCount() - 1)) < getHeight()) {
            this.f556A = i;
        }
        if (i > 0) {
            i = 0;
        } else {
            int i4 = this.f556A;
            if (i < i4) {
                i = i4;
            }
        }
        float f = (float) i;
        this.f569j.mo13982a(f);
        this.f569j.mo13988b(f);
    }

    /* renamed from: g */
    private void m716g() {
        if (this.f581v == null) {
            this.f581v = new Runnable() {
                public void run() {
                    if (C0918c.this.f560a == 1) {
                        C0918c cVar = C0918c.this;
                        int a = cVar.mo14051a(cVar.f561b, C0918c.this.f562c);
                        if (a != -1) {
                            C0918c.this.mo14056b(a);
                        }
                    }
                }
            };
        }
        postDelayed(this.f581v, (long) ViewConfiguration.getLongPressTimeout());
    }

    /* renamed from: b */
    private boolean m706b(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int i = this.f561b;
        if (x >= i - 10 && x <= i + 10) {
            int i2 = this.f562c;
            if (y >= i2 - 10 && y <= i2 + 10) {
                return false;
            }
        }
        removeCallbacks(this.f581v);
        this.f560a = 2;
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo14051a(int i, int i2) {
        if (this.f582w == null) {
            this.f582w = new Rect();
        }
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).getHitRect(this.f582w);
            if (this.f582w.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    /* renamed from: b */
    private void m705b(int i, int i2) {
        int a = mo14051a(i, i2);
        if (a != -1) {
            View childAt = getChildAt(a);
            int i3 = this.f567h + a;
            performItemClick(childAt, i3, this.f577r.getItemId(i3));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14056b(int i) {
        View childAt = getChildAt(i);
        int i2 = this.f567h + i;
        long itemId = this.f577r.getItemId(i2);
        AdapterView.OnItemLongClickListener onItemLongClickListener = getOnItemLongClickListener();
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClick(this, childAt, i2, itemId);
        }
    }

    /* renamed from: c */
    private void m708c(int i) {
        int childCount = getChildCount();
        if (this.f568i != this.f577r.getCount() - 1 && childCount > 1) {
            View childAt = getChildAt(0);
            while (childAt != null && m707c(childAt) + i < 0) {
                removeViewInLayout(childAt);
                childCount--;
                this.f580u.addLast(childAt);
                this.f567h++;
                this.f565f += m710d(childAt);
                childAt = childCount > 1 ? getChildAt(0) : null;
            }
        }
        if (this.f567h != 0 && childCount > 1) {
            View childAt2 = getChildAt(childCount - 1);
            while (childAt2 != null && mo14052a(childAt2) + i > getHeight()) {
                removeViewInLayout(childAt2);
                childCount--;
                this.f580u.addLast(childAt2);
                this.f568i--;
                childAt2 = childCount > 1 ? getChildAt(childCount - 1) : null;
            }
        }
    }

    /* renamed from: d */
    private void m711d(int i) {
        m709c(m707c(getChildAt(getChildCount() - 1)), i);
        m712d(mo14052a(getChildAt(0)), i);
    }

    /* renamed from: c */
    private void m709c(int i, int i2) {
        while (i + i2 < getHeight() && this.f568i < this.f577r.getCount() - 1) {
            int i3 = this.f568i + 1;
            this.f568i = i3;
            View view = this.f577r.getView(i3, getCachedView(), this);
            m702a(view, 0);
            i += m710d(view);
        }
    }

    /* renamed from: d */
    private void m712d(int i, int i2) {
        int i3;
        while (i + i2 > 0 && (i3 = this.f567h) > 0) {
            int i4 = i3 - 1;
            this.f567h = i4;
            View view = this.f577r.getView(i4, getCachedView(), this);
            m702a(view, 1);
            int d = m710d(view);
            i -= d;
            this.f565f -= d;
        }
    }

    /* renamed from: a */
    private void m702a(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i2 = i == 1 ? 0 : -1;
        view.setDrawingCacheEnabled(true);
        addViewInLayout(view, i2, layoutParams, true);
        view.measure(((int) (((float) getWidth()) * 0.85f)) | 1073741824, 0);
    }

    /* renamed from: h */
    private void m717h() {
        int i = this.f564e + this.f565f;
        float width = ((float) getWidth()) * 0.0f;
        float height = 1.0f / (((float) getHeight()) * 0.9f);
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            double d = (double) width;
            double d2 = (double) height;
            Double.isNaN(d2);
            double d3 = (double) i;
            Double.isNaN(d3);
            double sin = Math.sin(d2 * 6.283185307179586d * d3);
            Double.isNaN(d);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int width2 = ((int) (d * sin)) + ((getWidth() - measuredWidth) / 2);
            int b = m703b(childAt);
            int i3 = i + b;
            childAt.layout(width2, i3, measuredWidth + width2, i3 + measuredHeight);
            i += measuredHeight + (b * 2);
        }
    }

    private View getCachedView() {
        if (this.f580u.size() != 0) {
            return this.f580u.removeFirst();
        }
        return null;
    }

    /* renamed from: b */
    private int m703b(View view) {
        return (int) ((((float) view.getMeasuredHeight()) * 0.35000002f) / 2.0f);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo14052a(View view) {
        return view.getTop() - m703b(view);
    }

    /* renamed from: c */
    private int m707c(View view) {
        return view.getBottom() + m703b(view);
    }

    /* renamed from: d */
    private int m710d(View view) {
        return view.getMeasuredHeight() + (m703b(view) * 2);
    }

    public int getFirstItemPosition() {
        return this.f567h;
    }

    public int getLastItemPosition() {
        return this.f568i;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }
}
