package androidx.media2.exoplayer.external.util;

import android.text.TextUtils;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class Log {
    public static final int LOG_LEVEL_ALL = 0;
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_INFO = 1;
    public static final int LOG_LEVEL_OFF = Integer.MAX_VALUE;
    public static final int LOG_LEVEL_WARNING = 2;
    private static int logLevel = 0;
    private static boolean logStackTraces = true;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    @interface LogLevel {
    }

    private Log() {
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public boolean getLogStackTraces() {
        return logStackTraces;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    public static void setLogStackTraces(boolean z) {
        logStackTraces = z;
    }

    /* renamed from: d */
    public static void m6204d(String str, String str2) {
        if (logLevel == 0) {
            android.util.Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m6205d(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m6204d(str, appendThrowableMessage(str2, th));
        } else if (logLevel == 0) {
            android.util.Log.d(str, str2, th);
        }
    }

    /* renamed from: i */
    public static void m6208i(String str, String str2) {
        if (logLevel <= 1) {
            android.util.Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m6209i(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m6208i(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 1) {
            android.util.Log.i(str, str2, th);
        }
    }

    /* renamed from: w */
    public static void m6210w(String str, String str2) {
        if (logLevel <= 2) {
            android.util.Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m6211w(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m6210w(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 2) {
            android.util.Log.w(str, str2, th);
        }
    }

    /* renamed from: e */
    public static void m6206e(String str, String str2) {
        if (logLevel <= 3) {
            android.util.Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m6207e(String str, String str2, Throwable th) {
        if (!logStackTraces) {
            m6206e(str, appendThrowableMessage(str2, th));
        } else if (logLevel <= 3) {
            android.util.Log.e(str, str2, th);
        }
    }

    private static String appendThrowableMessage(String str, Throwable th) {
        if (th == null) {
            return str;
        }
        String message = th.getMessage();
        if (TextUtils.isEmpty(message)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(message).length());
        sb.append(str);
        sb.append(" - ");
        sb.append(message);
        return sb.toString();
    }
}
