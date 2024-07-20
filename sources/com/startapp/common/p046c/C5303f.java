package com.startapp.common.p046c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.startapp.common.c.f */
/* compiled from: StartAppSDK */
public @interface C5303f {
    /* renamed from: a */
    boolean mo45477a() default false;

    /* renamed from: b */
    Class mo45478b() default Object.class;

    /* renamed from: c */
    Class mo45479c() default String.class;

    /* renamed from: d */
    Class mo45480d() default String.class;

    /* renamed from: e */
    Class mo45481e() default String.class;

    /* renamed from: f */
    String mo45482f() default "";
}
