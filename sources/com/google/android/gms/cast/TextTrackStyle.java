package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzcu;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle extends AbstractSafeParcelable {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final Parcelable.Creator<TextTrackStyle> CREATOR = new zzbr();
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    private int backgroundColor;
    private int edgeColor;
    private int edgeType;
    private float fontScale;
    private int fontStyle;
    private int foregroundColor;
    private int windowColor;
    private int zzgh;
    private int zzgi;
    private String zzgj;
    private int zzgk;
    private String zzj;
    private JSONObject zzp;

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, (String) null, -1, -1, (String) null);
    }

    TextTrackStyle(float f, int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, int i8, int i9, String str2) {
        this.fontScale = f;
        this.foregroundColor = i;
        this.backgroundColor = i2;
        this.edgeType = i3;
        this.edgeColor = i4;
        this.zzgh = i5;
        this.windowColor = i6;
        this.zzgi = i7;
        this.zzgj = str;
        this.zzgk = i8;
        this.fontStyle = i9;
        this.zzj = str2;
        if (str2 != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
    }

    public static TextTrackStyle fromSystemSettings(Context context) {
        TextTrackStyle textTrackStyle = new TextTrackStyle();
        if (!PlatformVersion.isAtLeastKitKat()) {
            return textTrackStyle;
        }
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        textTrackStyle.setFontScale(captioningManager.getFontScale());
        CaptioningManager.CaptionStyle userStyle = captioningManager.getUserStyle();
        textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
        textTrackStyle.setForegroundColor(userStyle.foregroundColor);
        int i = userStyle.edgeType;
        if (i == 1) {
            textTrackStyle.setEdgeType(1);
        } else if (i != 2) {
            textTrackStyle.setEdgeType(0);
        } else {
            textTrackStyle.setEdgeType(2);
        }
        textTrackStyle.setEdgeColor(userStyle.edgeColor);
        Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (Typeface.MONOSPACE.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(1);
            } else if (Typeface.SANS_SERIF.equals(typeface) || !Typeface.SERIF.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(0);
            } else {
                textTrackStyle.setFontGenericFamily(2);
            }
            boolean isBold = typeface.isBold();
            boolean isItalic = typeface.isItalic();
            if (isBold && isItalic) {
                textTrackStyle.setFontStyle(3);
            } else if (isBold) {
                textTrackStyle.setFontStyle(1);
            } else if (isItalic) {
                textTrackStyle.setFontStyle(2);
            } else {
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }

    private static String zzd(int i) {
        return String.format("#%02X%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.alpha(i))});
    }

    private static int zzh(String str) {
        if (str != null && str.length() == 9 && str.charAt(0) == '#') {
            try {
                return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        JSONObject jSONObject;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) obj;
        if ((this.zzp == null) != (textTrackStyle.zzp == null)) {
            return false;
        }
        JSONObject jSONObject2 = this.zzp;
        return (jSONObject2 == null || (jSONObject = textTrackStyle.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject2, jSONObject)) && this.fontScale == textTrackStyle.fontScale && this.foregroundColor == textTrackStyle.foregroundColor && this.backgroundColor == textTrackStyle.backgroundColor && this.edgeType == textTrackStyle.edgeType && this.edgeColor == textTrackStyle.edgeColor && this.zzgh == textTrackStyle.zzgh && this.zzgi == textTrackStyle.zzgi && zzcu.zza(this.zzgj, textTrackStyle.zzgj) && this.zzgk == textTrackStyle.zzgk && this.fontStyle == textTrackStyle.fontStyle;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    public final int getEdgeColor() {
        return this.edgeColor;
    }

    public final int getEdgeType() {
        return this.edgeType;
    }

    public final String getFontFamily() {
        return this.zzgj;
    }

    public final int getFontGenericFamily() {
        return this.zzgk;
    }

    public final float getFontScale() {
        return this.fontScale;
    }

    public final int getFontStyle() {
        return this.fontStyle;
    }

    public final int getForegroundColor() {
        return this.foregroundColor;
    }

    public final int getWindowColor() {
        return this.windowColor;
    }

    public final int getWindowCornerRadius() {
        return this.zzgi;
    }

    public final int getWindowType() {
        return this.zzgh;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.fontScale), Integer.valueOf(this.foregroundColor), Integer.valueOf(this.backgroundColor), Integer.valueOf(this.edgeType), Integer.valueOf(this.edgeColor), Integer.valueOf(this.zzgh), Integer.valueOf(this.windowColor), Integer.valueOf(this.zzgi), this.zzgj, Integer.valueOf(this.zzgk), Integer.valueOf(this.fontStyle), String.valueOf(this.zzp));
    }

    public final void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final void setEdgeColor(int i) {
        this.edgeColor = i;
    }

    public final void setEdgeType(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.edgeType = i;
    }

    public final void setFontFamily(String str) {
        this.zzgj = str;
    }

    public final void setFontGenericFamily(int i) {
        if (i < 0 || i > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.zzgk = i;
    }

    public final void setFontScale(float f) {
        this.fontScale = f;
    }

    public final void setFontStyle(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.fontStyle = i;
    }

    public final void setForegroundColor(int i) {
        this.foregroundColor = i;
    }

    public final void setWindowColor(int i) {
        this.windowColor = i;
    }

    public final void setWindowCornerRadius(int i) {
        if (i >= 0) {
            this.zzgi = i;
            return;
        }
        throw new IllegalArgumentException("invalid windowCornerRadius");
    }

    public final void setWindowType(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.zzgh = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r0.put("fontGenericFamily", r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject toJson() {
        /*
            r8 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "fontScale"
            float r2 = r8.fontScale     // Catch:{ JSONException -> 0x00e7 }
            double r2 = (double) r2     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
            int r1 = r8.foregroundColor     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x001c
            java.lang.String r1 = "foregroundColor"
            int r2 = r8.foregroundColor     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = zzd(r2)     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x001c:
            int r1 = r8.backgroundColor     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x002b
            java.lang.String r1 = "backgroundColor"
            int r2 = r8.backgroundColor     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = zzd(r2)     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x002b:
            int r1 = r8.edgeType     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = "NONE"
            r3 = 3
            r4 = 1
            r5 = 2
            java.lang.String r6 = "edgeType"
            if (r1 == 0) goto L_0x004f
            if (r1 == r4) goto L_0x004c
            if (r1 == r5) goto L_0x0049
            if (r1 == r3) goto L_0x0046
            r7 = 4
            if (r1 == r7) goto L_0x0040
            goto L_0x0052
        L_0x0040:
            java.lang.String r1 = "DEPRESSED"
        L_0x0042:
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x00e7 }
            goto L_0x0052
        L_0x0046:
            java.lang.String r1 = "RAISED"
            goto L_0x0042
        L_0x0049:
            java.lang.String r1 = "DROP_SHADOW"
            goto L_0x0042
        L_0x004c:
            java.lang.String r1 = "OUTLINE"
            goto L_0x0042
        L_0x004f:
            r0.put(r6, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x0052:
            int r1 = r8.edgeColor     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x0061
            java.lang.String r1 = "edgeColor"
            int r6 = r8.edgeColor     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r6 = zzd(r6)     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00e7 }
        L_0x0061:
            int r1 = r8.zzgh     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r6 = "NORMAL"
            java.lang.String r7 = "windowType"
            if (r1 == 0) goto L_0x0078
            if (r1 == r4) goto L_0x0074
            if (r1 == r5) goto L_0x006e
            goto L_0x007b
        L_0x006e:
            java.lang.String r1 = "ROUNDED_CORNERS"
            r0.put(r7, r1)     // Catch:{ JSONException -> 0x00e7 }
            goto L_0x007b
        L_0x0074:
            r0.put(r7, r6)     // Catch:{ JSONException -> 0x00e7 }
            goto L_0x007b
        L_0x0078:
            r0.put(r7, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x007b:
            int r1 = r8.windowColor     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x008a
            java.lang.String r1 = "windowColor"
            int r2 = r8.windowColor     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = zzd(r2)     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x008a:
            int r1 = r8.zzgh     // Catch:{ JSONException -> 0x00e7 }
            if (r1 != r5) goto L_0x0095
            java.lang.String r1 = "windowRoundedCornerRadius"
            int r2 = r8.zzgi     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x0095:
            java.lang.String r1 = r8.zzgj     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x00a0
            java.lang.String r1 = "fontFamily"
            java.lang.String r2 = r8.zzgj     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x00a0:
            int r1 = r8.zzgk     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = "fontGenericFamily"
            switch(r1) {
                case 0: goto L_0x00bd;
                case 1: goto L_0x00ba;
                case 2: goto L_0x00b7;
                case 3: goto L_0x00b4;
                case 4: goto L_0x00b1;
                case 5: goto L_0x00ae;
                case 6: goto L_0x00a8;
                default: goto L_0x00a7;
            }
        L_0x00a7:
            goto L_0x00c0
        L_0x00a8:
            java.lang.String r1 = "SMALL_CAPITALS"
        L_0x00aa:
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00e7 }
            goto L_0x00c0
        L_0x00ae:
            java.lang.String r1 = "CURSIVE"
            goto L_0x00aa
        L_0x00b1:
            java.lang.String r1 = "CASUAL"
            goto L_0x00aa
        L_0x00b4:
            java.lang.String r1 = "MONOSPACED_SERIF"
            goto L_0x00aa
        L_0x00b7:
            java.lang.String r1 = "SERIF"
            goto L_0x00aa
        L_0x00ba:
            java.lang.String r1 = "MONOSPACED_SANS_SERIF"
            goto L_0x00aa
        L_0x00bd:
            java.lang.String r1 = "SANS_SERIF"
            goto L_0x00aa
        L_0x00c0:
            int r1 = r8.fontStyle     // Catch:{ JSONException -> 0x00e7 }
            java.lang.String r2 = "fontStyle"
            if (r1 == 0) goto L_0x00d9
            if (r1 == r4) goto L_0x00d6
            if (r1 == r5) goto L_0x00d3
            if (r1 == r3) goto L_0x00cd
            goto L_0x00dc
        L_0x00cd:
            java.lang.String r1 = "BOLD_ITALIC"
        L_0x00cf:
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00e7 }
            goto L_0x00dc
        L_0x00d3:
            java.lang.String r1 = "ITALIC"
            goto L_0x00cf
        L_0x00d6:
            java.lang.String r1 = "BOLD"
            goto L_0x00cf
        L_0x00d9:
            r0.put(r2, r6)     // Catch:{ JSONException -> 0x00e7 }
        L_0x00dc:
            org.json.JSONObject r1 = r8.zzp     // Catch:{ JSONException -> 0x00e7 }
            if (r1 == 0) goto L_0x00e7
            java.lang.String r1 = "customData"
            org.json.JSONObject r2 = r8.zzp     // Catch:{ JSONException -> 0x00e7 }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00e7 }
        L_0x00e7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.TextTrackStyle.toJson():org.json.JSONObject");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, getFontScale());
        SafeParcelWriter.writeInt(parcel, 3, getForegroundColor());
        SafeParcelWriter.writeInt(parcel, 4, getBackgroundColor());
        SafeParcelWriter.writeInt(parcel, 5, getEdgeType());
        SafeParcelWriter.writeInt(parcel, 6, getEdgeColor());
        SafeParcelWriter.writeInt(parcel, 7, getWindowType());
        SafeParcelWriter.writeInt(parcel, 8, getWindowColor());
        SafeParcelWriter.writeInt(parcel, 9, getWindowCornerRadius());
        SafeParcelWriter.writeString(parcel, 10, getFontFamily(), false);
        SafeParcelWriter.writeInt(parcel, 11, getFontGenericFamily());
        SafeParcelWriter.writeInt(parcel, 12, getFontStyle());
        SafeParcelWriter.writeString(parcel, 13, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final void zze(JSONObject jSONObject) throws JSONException {
        int i;
        this.fontScale = (float) jSONObject.optDouble("fontScale", 1.0d);
        this.foregroundColor = zzh(jSONObject.optString("foregroundColor"));
        this.backgroundColor = zzh(jSONObject.optString("backgroundColor"));
        if (jSONObject.has("edgeType")) {
            String string = jSONObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.edgeType = 0;
            } else if ("OUTLINE".equals(string)) {
                this.edgeType = 1;
            } else if ("DROP_SHADOW".equals(string)) {
                this.edgeType = 2;
            } else if ("RAISED".equals(string)) {
                this.edgeType = 3;
            } else if ("DEPRESSED".equals(string)) {
                this.edgeType = 4;
            }
        }
        this.edgeColor = zzh(jSONObject.optString("edgeColor"));
        if (jSONObject.has("windowType")) {
            String string2 = jSONObject.getString("windowType");
            if ("NONE".equals(string2)) {
                this.zzgh = 0;
            } else if ("NORMAL".equals(string2)) {
                this.zzgh = 1;
            } else if ("ROUNDED_CORNERS".equals(string2)) {
                this.zzgh = 2;
            }
        }
        this.windowColor = zzh(jSONObject.optString("windowColor"));
        if (this.zzgh == 2) {
            this.zzgi = jSONObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.zzgj = jSONObject.optString("fontFamily", (String) null);
        if (jSONObject.has("fontGenericFamily")) {
            String string3 = jSONObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string3)) {
                this.zzgk = 0;
            } else if ("MONOSPACED_SANS_SERIF".equals(string3)) {
                this.zzgk = 1;
            } else if ("SERIF".equals(string3)) {
                this.zzgk = 2;
            } else if ("MONOSPACED_SERIF".equals(string3)) {
                this.zzgk = 3;
            } else if ("CASUAL".equals(string3)) {
                this.zzgk = 4;
            } else {
                if ("CURSIVE".equals(string3)) {
                    i = 5;
                } else if ("SMALL_CAPITALS".equals(string3)) {
                    i = 6;
                }
                this.zzgk = i;
            }
        }
        if (jSONObject.has("fontStyle")) {
            String string4 = jSONObject.getString("fontStyle");
            if ("NORMAL".equals(string4)) {
                this.fontStyle = 0;
            } else if ("BOLD".equals(string4)) {
                this.fontStyle = 1;
            } else if ("ITALIC".equals(string4)) {
                this.fontStyle = 2;
            } else if ("BOLD_ITALIC".equals(string4)) {
                this.fontStyle = 3;
            }
        }
        this.zzp = jSONObject.optJSONObject("customData");
    }
}
