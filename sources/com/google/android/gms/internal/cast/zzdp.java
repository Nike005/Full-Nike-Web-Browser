package com.google.android.gms.internal.cast;

import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdp {
    private static final zzdg zzbd = new zzdg("MetadataUtils");
    private static final String[] zzxq;
    private static final String zzxr;

    static {
        String[] strArr = {"Z", "+hh", "+hhmm", "+hh:mm"};
        zzxq = strArr;
        String valueOf = String.valueOf(strArr[0]);
        zzxr = valueOf.length() != 0 ? "yyyyMMdd'T'HHmmss".concat(valueOf) : new String("yyyyMMdd'T'HHmmss");
    }

    public static String zza(Calendar calendar) {
        if (calendar == null) {
            zzbd.mo6870d("Calendar object cannot be null", new Object[0]);
            return null;
        }
        String str = zzxr;
        if (calendar.get(11) == 0 && calendar.get(12) == 0 && calendar.get(13) == 0) {
            str = "yyyyMMdd";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String format = simpleDateFormat.format(calendar.getTime());
        return format.endsWith("+0000") ? format.replace("+0000", zzxq[0]) : format;
    }

    public static void zza(List<WebImage> list, JSONObject jSONObject) {
        try {
            list.clear();
            JSONArray jSONArray = jSONObject.getJSONArray("images");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    list.add(new WebImage(jSONArray.getJSONObject(i)));
                } catch (IllegalArgumentException unused) {
                }
            }
        } catch (JSONException unused2) {
        }
    }

    public static void zza(JSONObject jSONObject, List<WebImage> list) {
        if (list != null && !list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (WebImage json : list) {
                jSONArray.put(json.toJson());
            }
            try {
                jSONObject.put("images", jSONArray);
            } catch (JSONException unused) {
            }
        }
    }

    public static Calendar zzu(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            zzbd.mo6870d("Input string is empty or null", new Object[0]);
            return null;
        }
        String zzv = zzv(str);
        if (TextUtils.isEmpty(zzv)) {
            zzbd.mo6870d("Invalid date format", new Object[0]);
            return null;
        }
        String zzw = zzw(str);
        if (!TextUtils.isEmpty(zzw)) {
            String valueOf = String.valueOf(zzv);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(zzw).length());
            sb.append(valueOf);
            sb.append("T");
            sb.append(zzw);
            zzv = sb.toString();
            str2 = zzw.length() == 6 ? "yyyyMMdd'T'HHmmss" : zzxr;
        } else {
            str2 = "yyyyMMdd";
        }
        Calendar instance = GregorianCalendar.getInstance();
        try {
            instance.setTime(new SimpleDateFormat(str2).parse(zzv));
            return instance;
        } catch (ParseException e) {
            zzbd.mo6870d("Error parsing string: %s", e.getMessage());
            return null;
        }
    }

    private static String zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbd.mo6870d("Input string is empty or null", new Object[0]);
            return null;
        }
        try {
            return str.substring(0, 8);
        } catch (IndexOutOfBoundsException e) {
            zzbd.mo6872i("Error extracting the date: %s", e.getMessage());
            return null;
        }
    }

    private static String zzw(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            zzbd.mo6870d("string is empty or null", new Object[0]);
            return null;
        }
        int indexOf = str.indexOf(84);
        int i = indexOf + 1;
        if (indexOf != 8) {
            zzbd.mo6870d("T delimeter is not found", new Object[0]);
            return null;
        }
        try {
            String substring = str.substring(i);
            if (substring.length() == 6) {
                return substring;
            }
            char charAt = substring.charAt(6);
            if (charAt == '+' || charAt == '-') {
                int length = substring.length();
                if (length == zzxq[1].length() + 6 || length == zzxq[2].length() + 6 || length == zzxq[3].length() + 6) {
                    z = true;
                }
                if (z) {
                    return substring.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
                }
            } else if (charAt != 'Z' || substring.length() != zzxq[0].length() + 6) {
                return null;
            } else {
                String valueOf = String.valueOf(substring.substring(0, substring.length() - 1));
                return "+0000".length() != 0 ? valueOf.concat("+0000") : new String(valueOf);
            }
            return null;
        } catch (IndexOutOfBoundsException e) {
            zzbd.mo6870d("Error extracting the time substring: %s", e.getMessage());
            return null;
        }
    }
}
