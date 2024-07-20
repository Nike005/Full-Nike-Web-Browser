package androidx.media2.player.subtitle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.media2.player.C4017R;

class SubtitleView extends View {
    private static final int COLOR_BEVEL_DARK = Integer.MIN_VALUE;
    private static final int COLOR_BEVEL_LIGHT = -2130706433;
    private static final float INNER_PADDING_RATIO = 0.125f;
    private Layout.Alignment mAlignment;
    private int mBackgroundColor;
    private final float mCornerRadius;
    private int mEdgeColor;
    private int mEdgeType;
    private int mForegroundColor;
    private boolean mHasMeasurements;
    private int mInnerPaddingX;
    private int mLastMeasuredWidth;
    private StaticLayout mLayout;
    private final RectF mLineBounds;
    private final float mOutlineWidth;
    private Paint mPaint;
    private final float mShadowOffsetX;
    private final float mShadowOffsetY;
    private final float mShadowRadius;
    private float mSpacingAdd;
    private float mSpacingMult;
    private final SpannableStringBuilder mText;
    private TextPaint mTextPaint;

    SubtitleView(Context context) {
        this(context, (AttributeSet) null);
    }

    SubtitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    SubtitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLineBounds = new RectF();
        this.mText = new SpannableStringBuilder();
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mInnerPaddingX = 0;
        Resources resources = getContext().getResources();
        this.mCornerRadius = (float) resources.getDimensionPixelSize(C4017R.dimen.subtitle_corner_radius);
        this.mOutlineWidth = (float) resources.getDimensionPixelSize(C4017R.dimen.subtitle_outline_width);
        this.mShadowRadius = (float) resources.getDimensionPixelSize(C4017R.dimen.subtitle_shadow_radius);
        float dimensionPixelSize = (float) resources.getDimensionPixelSize(C4017R.dimen.subtitle_shadow_offset);
        this.mShadowOffsetX = dimensionPixelSize;
        this.mShadowOffsetY = dimensionPixelSize;
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setAntiAlias(true);
        this.mTextPaint.setSubpixelText(true);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    public void setText(int i) {
        setText(getContext().getText(i));
    }

    public void setText(CharSequence charSequence) {
        this.mText.clear();
        this.mText.append(charSequence);
        this.mHasMeasurements = false;
        requestLayout();
        invalidate();
    }

    public void setForegroundColor(int i) {
        this.mForegroundColor = i;
        invalidate();
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        invalidate();
    }

    public void setEdgeType(int i) {
        this.mEdgeType = i;
        invalidate();
    }

    public void setEdgeColor(int i) {
        this.mEdgeColor = i;
        invalidate();
    }

    public void setTextSize(float f) {
        if (this.mTextPaint.getTextSize() != f) {
            this.mTextPaint.setTextSize(f);
            this.mInnerPaddingX = (int) ((f * INNER_PADDING_RATIO) + 0.5f);
            this.mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    public void setTypeface(Typeface typeface) {
        if (typeface != null && !typeface.equals(this.mTextPaint.getTypeface())) {
            this.mTextPaint.setTypeface(typeface);
            this.mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    public void setAlignment(Layout.Alignment alignment) {
        if (this.mAlignment != alignment) {
            this.mAlignment = alignment;
            this.mHasMeasurements = false;
            requestLayout();
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (computeMeasurements(View.MeasureSpec.getSize(i))) {
            StaticLayout staticLayout = this.mLayout;
            setMeasuredDimension(staticLayout.getWidth() + getPaddingLeft() + getPaddingRight() + (this.mInnerPaddingX * 2), staticLayout.getHeight() + getPaddingTop() + getPaddingBottom());
            return;
        }
        setMeasuredDimension(16777216, 16777216);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        computeMeasurements(i3 - i);
    }

    private boolean computeMeasurements(int i) {
        if (this.mHasMeasurements && i == this.mLastMeasuredWidth) {
            return true;
        }
        int paddingLeft = i - ((getPaddingLeft() + getPaddingRight()) + (this.mInnerPaddingX * 2));
        if (paddingLeft <= 0) {
            return false;
        }
        this.mHasMeasurements = true;
        this.mLastMeasuredWidth = paddingLeft;
        if (Build.VERSION.SDK_INT >= 23) {
            SpannableStringBuilder spannableStringBuilder = this.mText;
            StaticLayout.Builder lineSpacing = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), this.mTextPaint, paddingLeft).setAlignment(this.mAlignment).setLineSpacing(this.mSpacingAdd, this.mSpacingMult);
            if (Build.VERSION.SDK_INT >= 28) {
                lineSpacing.setUseLineSpacingFromFallbacks(true);
            }
            this.mLayout = lineSpacing.build();
        } else {
            SpannableStringBuilder spannableStringBuilder2 = this.mText;
            this.mLayout = new StaticLayout(spannableStringBuilder2, 0, spannableStringBuilder2.length(), this.mTextPaint, paddingLeft, this.mAlignment, this.mSpacingMult, this.mSpacingAdd, true);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i;
        StaticLayout staticLayout = this.mLayout;
        if (staticLayout != null) {
            int save = canvas.save();
            int i2 = this.mInnerPaddingX;
            canvas.translate((float) (getPaddingLeft() + i2), (float) getPaddingTop());
            int lineCount = staticLayout.getLineCount();
            TextPaint textPaint = this.mTextPaint;
            Paint paint = this.mPaint;
            RectF rectF = this.mLineBounds;
            if (Color.alpha(this.mBackgroundColor) > 0) {
                float f = this.mCornerRadius;
                float lineTop = (float) staticLayout.getLineTop(0);
                paint.setColor(this.mBackgroundColor);
                paint.setStyle(Paint.Style.FILL);
                for (int i3 = 0; i3 < lineCount; i3++) {
                    float f2 = (float) i2;
                    rectF.left = staticLayout.getLineLeft(i3) - f2;
                    rectF.right = staticLayout.getLineRight(i3) + f2;
                    rectF.top = lineTop;
                    rectF.bottom = (float) staticLayout.getLineBottom(i3);
                    lineTop = rectF.bottom;
                    canvas.drawRoundRect(rectF, f, f, paint);
                }
            }
            int i4 = this.mEdgeType;
            boolean z = true;
            if (i4 == 1) {
                textPaint.setStrokeJoin(Paint.Join.ROUND);
                textPaint.setStrokeWidth(this.mOutlineWidth);
                textPaint.setColor(this.mEdgeColor);
                textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                staticLayout.draw(canvas);
            } else if (i4 == 2) {
                textPaint.setShadowLayer(this.mShadowRadius, this.mShadowOffsetX, this.mShadowOffsetY, this.mEdgeColor);
            } else if (i4 == 3 || i4 == 4) {
                if (i4 != 3) {
                    z = false;
                }
                int i5 = -1;
                if (z) {
                    i = -1;
                } else {
                    i = this.mEdgeColor;
                }
                if (z) {
                    i5 = this.mEdgeColor;
                }
                float f3 = this.mShadowRadius / 2.0f;
                textPaint.setColor(this.mForegroundColor);
                textPaint.setStyle(Paint.Style.FILL);
                float f4 = -f3;
                textPaint.setShadowLayer(this.mShadowRadius, f4, f4, i);
                staticLayout.draw(canvas);
                textPaint.setShadowLayer(this.mShadowRadius, f3, f3, i5);
            }
            textPaint.setColor(this.mForegroundColor);
            textPaint.setStyle(Paint.Style.FILL);
            staticLayout.draw(canvas);
            textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            canvas.restoreToCount(save);
        }
    }
}
