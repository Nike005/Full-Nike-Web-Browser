package p055a.p056a.p058b.p060b;

import java.util.Arrays;
import java.util.List;
import p055a.p056a.C2916b;

/* renamed from: a.a.b.b.h */
/* compiled from: StartAppSDK */
public class C2928h {
    private C2928h() {
    }

    /* renamed from: a */
    public static void m6153a() {
        throw ((C2916b) m6151a(new C2916b()));
    }

    /* renamed from: a */
    public static void m6154a(Object obj, String str) {
        if (obj == null) {
            throw ((IllegalStateException) m6151a(new IllegalStateException(str + " must not be null")));
        }
    }

    /* renamed from: b */
    public static void m6157b(Object obj, String str) {
        if (obj == null) {
            m6155a(str);
        }
    }

    /* renamed from: a */
    private static void m6155a(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        throw ((IllegalArgumentException) m6151a(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + "." + methodName + ", parameter " + str)));
    }

    /* renamed from: a */
    public static boolean m6156a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    /* renamed from: a */
    private static <T extends Throwable> T m6151a(T t) {
        return m6152a(t, C2928h.class.getName());
    }

    /* renamed from: a */
    static <T extends Throwable> T m6152a(T t, String str) {
        StackTraceElement[] stackTrace = t.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        List subList = Arrays.asList(stackTrace).subList(i + 1, length);
        t.setStackTrace((StackTraceElement[]) subList.toArray(new StackTraceElement[subList.size()]));
        return t;
    }
}
