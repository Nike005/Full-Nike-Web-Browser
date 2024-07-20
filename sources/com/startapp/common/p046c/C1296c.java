package com.startapp.common.p046c;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.startapp.common.c.c */
/* compiled from: StartAppSDK */
public class C1296c {
    /* renamed from: a */
    public static String m2183a(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations != null && declaredAnnotations.length > 0) {
            Annotation annotation = field.getDeclaredAnnotations()[0];
            if (annotation.annotationType().equals(C5303f.class)) {
                C5303f fVar = (C5303f) annotation;
                if (!"".equals(fVar.mo45482f())) {
                    return fVar.mo45482f();
                }
            }
        }
        return field.getName();
    }

    /* renamed from: b */
    public static boolean m2185b(Field field) {
        return Map.class.isAssignableFrom(field.getType());
    }

    /* renamed from: c */
    public static boolean m2186c(Field field) {
        return List.class.isAssignableFrom(field.getType());
    }

    /* renamed from: d */
    public static boolean m2187d(Field field) {
        return Set.class.isAssignableFrom(field.getType());
    }

    /* renamed from: e */
    public static boolean m2188e(Field field) {
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        if (declaredAnnotations == null || declaredAnnotations.length == 0) {
            return false;
        }
        Annotation annotation = field.getDeclaredAnnotations()[0];
        if (!annotation.annotationType().equals(C5303f.class)) {
            return false;
        }
        return ((C5303f) annotation).mo45477a();
    }

    /* renamed from: a */
    public static boolean m2184a(Object obj) {
        Class<?> cls = obj.getClass();
        return cls.equals(Boolean.class) || cls.equals(Integer.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(Double.class) || cls.equals(Long.class) || cls.equals(Float.class) || cls.equals(String.class);
    }
}
