package com.tappx.p048a;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.tappx.a.j4 */
public class C1475j4 {

    /* renamed from: a */
    private static final Logger f1982a = Logger.getLogger("com.tappx.mraid");

    /* renamed from: b */
    private static final C1477b f1983b = new C1477b((C1476a) null);

    /* renamed from: com.tappx.a.j4$a */
    static class C1476a implements Filter {
        C1476a() {
        }

        public boolean isLoggable(LogRecord logRecord) {
            return true;
        }
    }

    /* renamed from: com.tappx.a.j4$b */
    private static final class C1477b extends Handler {

        /* renamed from: a */
        private static final Map<Level, Integer> f1984a;

        static {
            HashMap hashMap = new HashMap(7);
            f1984a = hashMap;
            hashMap.put(Level.FINEST, 2);
            f1984a.put(Level.FINER, 2);
            f1984a.put(Level.FINE, 2);
            f1984a.put(Level.CONFIG, 3);
            f1984a.put(Level.INFO, 4);
            f1984a.put(Level.WARNING, 5);
            f1984a.put(Level.SEVERE, 6);
        }

        private C1477b() {
        }

        public void close() {
        }

        public void flush() {
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int intValue = f1984a.containsKey(logRecord.getLevel()) ? f1984a.get(logRecord.getLevel()).intValue() : 2;
                String str = logRecord.getMessage() + StringUtils.f3949LF;
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    str = str + Log.getStackTraceString(thrown);
                }
                Log.println(intValue, "TappxMraid", str);
            }
        }

        /* synthetic */ C1477b(C1476a aVar) {
            this();
        }
    }

    static {
        f1982a.setUseParentHandlers(false);
        f1982a.setLevel(Level.ALL);
        f1983b.setLevel(Level.FINE);
        f1983b.setFilter(new C1476a());
        LogManager.getLogManager().addLogger(f1982a);
        m2887a(f1982a, (Handler) f1983b);
    }

    /* renamed from: a */
    public static void m2885a(String str) {
        m2886a(str, (Throwable) null);
    }

    /* renamed from: b */
    public static void m2888b(String str) {
        m2889b(str, (Throwable) null);
    }

    /* renamed from: c */
    public static void m2890c(String str) {
        m2891c(str, (Throwable) null);
    }

    /* renamed from: d */
    public static void m2892d(String str) {
        m2893d(str, (Throwable) null);
    }

    /* renamed from: a */
    public static void m2886a(String str, Throwable th) {
        f1982a.log(Level.CONFIG, str, th);
    }

    /* renamed from: b */
    public static void m2889b(String str, Throwable th) {
        f1982a.log(Level.SEVERE, str, th);
    }

    /* renamed from: c */
    public static void m2891c(String str, Throwable th) {
        f1982a.log(Level.FINE, str, th);
    }

    /* renamed from: d */
    public static void m2893d(String str, Throwable th) {
        f1982a.log(Level.WARNING, str, th);
    }

    /* renamed from: a */
    private static void m2887a(Logger logger, Handler handler) {
        Handler[] handlers = logger.getHandlers();
        int length = handlers.length;
        int i = 0;
        while (i < length) {
            if (!handlers[i].equals(handler)) {
                i++;
            } else {
                return;
            }
        }
        logger.addHandler(handler);
    }
}
