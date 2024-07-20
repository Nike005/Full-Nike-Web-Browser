package org.altbeacon.beacon.logging;

public final class LogManager {
    private static Logger sLogger = Loggers.infoLogger();
    private static boolean sVerboseLoggingEnabled = false;

    public static void setLogger(Logger logger) {
        if (logger != null) {
            sLogger = logger;
            return;
        }
        throw new NullPointerException("Logger may not be null.");
    }

    public static Logger getLogger() {
        return sLogger;
    }

    public static boolean isVerboseLoggingEnabled() {
        return sVerboseLoggingEnabled;
    }

    public static void setVerboseLoggingEnabled(boolean z) {
        sVerboseLoggingEnabled = z;
    }

    /* renamed from: v */
    public static void m6052v(String str, String str2, Object... objArr) {
        sLogger.mo19965v(str, str2, objArr);
    }

    /* renamed from: v */
    public static void m6053v(Throwable th, String str, String str2, Object... objArr) {
        sLogger.mo19966v(th, str, str2, objArr);
    }

    /* renamed from: d */
    public static void m6046d(String str, String str2, Object... objArr) {
        sLogger.mo19959d(str, str2, objArr);
    }

    /* renamed from: d */
    public static void m6047d(Throwable th, String str, String str2, Object... objArr) {
        sLogger.mo19960d(th, str, str2, objArr);
    }

    /* renamed from: i */
    public static void m6050i(String str, String str2, Object... objArr) {
        sLogger.mo19963i(str, str2, objArr);
    }

    /* renamed from: i */
    public static void m6051i(Throwable th, String str, String str2, Object... objArr) {
        sLogger.mo19964i(th, str, str2, objArr);
    }

    /* renamed from: w */
    public static void m6054w(String str, String str2, Object... objArr) {
        sLogger.mo19967w(str, str2, objArr);
    }

    /* renamed from: w */
    public static void m6055w(Throwable th, String str, String str2, Object... objArr) {
        sLogger.mo19968w(th, str, str2, objArr);
    }

    /* renamed from: e */
    public static void m6048e(String str, String str2, Object... objArr) {
        sLogger.mo19961e(str, str2, objArr);
    }

    /* renamed from: e */
    public static void m6049e(Throwable th, String str, String str2, Object... objArr) {
        sLogger.mo19962e(th, str, str2, objArr);
    }

    private LogManager() {
    }
}
