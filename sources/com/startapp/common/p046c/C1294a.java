package com.startapp.common.p046c;

import com.startapp.common.p043a.C1270g;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.startapp.common.c.a */
/* compiled from: StartAppSDK */
public class C1294a extends InputStream {

    /* renamed from: a */
    public static Map<String, Class> f1557a;

    /* renamed from: b */
    private InputStream f1558b = null;

    /* renamed from: c */
    private String f1559c;

    @Deprecated
    public final int read() {
        return 0;
    }

    public C1294a(String str) {
        this.f1559c = str;
    }

    public void close() {
        super.close();
        InputStream inputStream = this.f1558b;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /* renamed from: a */
    public final <T> T mo15503a(Class<T> cls, JSONObject jSONObject) {
        try {
            return m2175b(cls, jSONObject);
        } catch (C1297d e) {
            C1270g.m2079a("JSONInputStream", 6, "Error while trying to parse object " + cls.toString(), e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02d8, code lost:
        throw new com.startapp.common.p046c.C1297d("Unknown error occurred ", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x02d9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02da, code lost:
        r13 = r3;
        r17 = r4;
        r16 = r5;
        r18 = r6;
        r14 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x02d0 A[ExcHandler: all (r0v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:67:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0163 A[Catch:{ Exception -> 0x02d9, all -> 0x02d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01a2 A[Catch:{ Exception -> 0x02d9, all -> 0x02d0 }] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T m2175b(java.lang.Class<T> r21, org.json.JSONObject r22) {
        /*
            r20 = this;
            r8 = r20
            r0 = r21
            java.lang.String r9 = "JSONInputStream"
            java.lang.String r1 = "Problem instantiating object class "
            java.io.InputStream r2 = r8.f1558b
            if (r2 != 0) goto L_0x0019
            java.lang.String r2 = r8.f1559c
            if (r2 == 0) goto L_0x0011
            goto L_0x0019
        L_0x0011:
            com.startapp.common.c.d r0 = new com.startapp.common.c.d
            java.lang.String r1 = "Can't read object, the input is null."
            r0.<init>(r1)
            throw r0
        L_0x0019:
            java.lang.String r2 = r8.f1559c
            r10 = 0
            if (r2 != 0) goto L_0x0030
            java.io.InputStream r2 = r8.f1558b     // Catch:{ Exception -> 0x0027 }
            java.lang.String r2 = m2170a((java.io.InputStream) r2, (java.lang.String) r10)     // Catch:{ Exception -> 0x0027 }
            r8.f1559c = r2     // Catch:{ Exception -> 0x0027 }
            goto L_0x0030
        L_0x0027:
            r0 = move-exception
            com.startapp.common.c.d r1 = new com.startapp.common.c.d
            java.lang.String r2 = "Can't read input stream."
            r1.<init>(r2, r0)
            throw r1
        L_0x0030:
            if (r22 != 0) goto L_0x0044
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x003b }
            java.lang.String r3 = r8.f1559c     // Catch:{ JSONException -> 0x003b }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x003b }
            r11 = r2
            goto L_0x0046
        L_0x003b:
            r0 = move-exception
            com.startapp.common.c.d r1 = new com.startapp.common.c.d
            java.lang.String r2 = "Can't deserialize the object. Failed to create JSON object."
            r1.<init>(r2, r0)
            throw r1
        L_0x0044:
            r11 = r22
        L_0x0046:
            java.lang.Class<com.startapp.common.c.e> r2 = com.startapp.common.p046c.C5302e.class
            java.lang.annotation.Annotation r2 = r0.getAnnotation(r2)     // Catch:{ Exception -> 0x0313 }
            com.startapp.common.c.e r2 = (com.startapp.common.p046c.C5302e) r2     // Catch:{ Exception -> 0x0313 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0313 }
            r4 = 9
            java.lang.String r12 = "."
            r13 = 2
            r14 = 1
            r15 = 0
            if (r3 < r4) goto L_0x0079
            java.lang.Class<java.net.HttpCookie> r3 = java.net.HttpCookie.class
            boolean r3 = r0.equals(r3)     // Catch:{ Exception -> 0x0313 }
            if (r3 == 0) goto L_0x0079
            java.lang.reflect.Constructor[] r1 = r21.getDeclaredConstructors()     // Catch:{ Exception -> 0x0313 }
            r1 = r1[r15]     // Catch:{ Exception -> 0x0313 }
            r1.setAccessible(r14)     // Catch:{ Exception -> 0x0313 }
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ Exception -> 0x0313 }
            java.lang.String r4 = "name"
            r3[r15] = r4     // Catch:{ Exception -> 0x0313 }
            java.lang.String r4 = "value"
            r3[r14] = r4     // Catch:{ Exception -> 0x0313 }
            java.lang.Object r1 = r1.newInstance(r3)     // Catch:{ Exception -> 0x0313 }
            goto L_0x00df
        L_0x0079:
            boolean r3 = r21.isPrimitive()     // Catch:{ Exception -> 0x0313 }
            if (r3 == 0) goto L_0x0084
            java.lang.Object r0 = r21.newInstance()     // Catch:{ Exception -> 0x0313 }
            return r0
        L_0x0084:
            java.lang.Class<com.startapp.common.c.e> r3 = com.startapp.common.p046c.C5302e.class
            java.lang.annotation.Annotation r3 = r0.getAnnotation(r3)     // Catch:{ Exception -> 0x0313 }
            if (r3 == 0) goto L_0x00d0
            boolean r3 = r2.mo45476c()     // Catch:{ Exception -> 0x0313 }
            if (r3 == 0) goto L_0x0093
            goto L_0x00d0
        L_0x0093:
            boolean r3 = r2.mo45476c()     // Catch:{ Exception -> 0x0313 }
            if (r3 != 0) goto L_0x00ce
            java.lang.String r0 = r2.mo45474a()     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.String r0 = r11.getString(r0)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.String r2 = r2.mo45475b()     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            r3.<init>()     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            r3.append(r2)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            r3.append(r12)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            r3.append(r0)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.String r0 = r3.toString()     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            java.lang.Object r0 = r8.m2175b(r0, r11)     // Catch:{ ClassNotFoundException -> 0x00c7, JSONException -> 0x00c0 }
            return r0
        L_0x00c0:
            r0 = move-exception
            com.startapp.common.c.d r2 = new com.startapp.common.c.d     // Catch:{ Exception -> 0x0313 }
            r2.<init>(r1, r0)     // Catch:{ Exception -> 0x0313 }
            throw r2     // Catch:{ Exception -> 0x0313 }
        L_0x00c7:
            r0 = move-exception
            com.startapp.common.c.d r2 = new com.startapp.common.c.d     // Catch:{ Exception -> 0x0313 }
            r2.<init>(r1, r0)     // Catch:{ Exception -> 0x0313 }
            throw r2     // Catch:{ Exception -> 0x0313 }
        L_0x00ce:
            r7 = r10
            goto L_0x00e0
        L_0x00d0:
            java.lang.Class[] r1 = new java.lang.Class[r15]     // Catch:{ Exception -> 0x0313 }
            java.lang.reflect.Constructor r1 = r0.getDeclaredConstructor(r1)     // Catch:{ Exception -> 0x0313 }
            r1.setAccessible(r14)     // Catch:{ Exception -> 0x0313 }
            java.lang.Object[] r3 = new java.lang.Object[r15]     // Catch:{ Exception -> 0x0313 }
            java.lang.Object r1 = r1.newInstance(r3)     // Catch:{ Exception -> 0x0313 }
        L_0x00df:
            r7 = r1
        L_0x00e0:
            java.lang.reflect.Field[] r1 = r21.getDeclaredFields()
            if (r2 == 0) goto L_0x00f0
            boolean r2 = r2.mo45476c()
            if (r2 == 0) goto L_0x00f0
            java.lang.reflect.Field[] r1 = r8.m2174a(r0, (java.lang.reflect.Field[]) r1)
        L_0x00f0:
            r6 = r1
            int r5 = r6.length
            r4 = 0
        L_0x00f3:
            if (r4 >= r5) goto L_0x0311
            r0 = r6[r4]
            int r1 = r0.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isStatic(r1)
            if (r2 != 0) goto L_0x02fa
            boolean r1 = java.lang.reflect.Modifier.isTransient(r1)
            if (r1 == 0) goto L_0x0109
            goto L_0x02fa
        L_0x0109:
            java.lang.String r3 = com.startapp.common.p046c.C1296c.m2183a((java.lang.reflect.Field) r0)
            boolean r1 = r11.has(r3)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r1 == 0) goto L_0x02ab
            r0.setAccessible(r14)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.annotation.Annotation[] r1 = r0.getDeclaredAnnotations()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            int r1 = r1.length     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r1 <= 0) goto L_0x014e
            java.lang.annotation.Annotation[] r1 = r0.getDeclaredAnnotations()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r1 = r1[r15]     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r2 = r1.annotationType()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class<com.startapp.common.c.f> r13 = com.startapp.common.p046c.C5303f.class
            boolean r2 = r2.equals(r13)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r2 == 0) goto L_0x014e
            com.startapp.common.c.f r1 = (com.startapp.common.p046c.C5303f) r1     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r2 = r1.mo45478b()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r13 = r1.mo45480d()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r16 = r1.mo45479c()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            boolean r17 = r1.mo45477a()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r1 = r1.mo45481e()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r18 = 1
            r19 = r16
            r16 = r1
            r1 = r19
            goto L_0x0157
        L_0x014e:
            r1 = r10
            r2 = r1
            r13 = r2
            r16 = r13
            r17 = 0
            r18 = 0
        L_0x0157:
            java.lang.Class r15 = r0.getType()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class<com.startapp.common.c.e> r14 = com.startapp.common.p046c.C5302e.class
            java.lang.annotation.Annotation r14 = r15.getAnnotation(r14)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r14 == 0) goto L_0x01a2
            java.lang.Class r1 = r0.getType()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class<com.startapp.common.c.e> r2 = com.startapp.common.p046c.C5302e.class
            java.lang.annotation.Annotation r1 = r1.getAnnotation(r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            com.startapp.common.c.e r1 = (com.startapp.common.p046c.C5302e) r1     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            org.json.JSONObject r2 = r11.getJSONObject(r3)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.String r13 = r1.mo45474a()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.String r2 = r2.getString(r13)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.String r1 = r1.mo45475b()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r13.<init>()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r13.append(r1)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r13.append(r12)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r13.append(r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.String r1 = r13.toString()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            org.json.JSONObject r2 = r11.getJSONObject(r3)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Object r1 = r8.m2175b(r1, r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r0.set(r7, r1)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            goto L_0x02fa
        L_0x01a2:
            if (r17 == 0) goto L_0x01b5
            java.lang.Class r1 = r0.getType()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            org.json.JSONObject r2 = r11.getJSONObject(r3)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.lang.Object r1 = r8.m2175b(r1, r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r0.set(r7, r1)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            goto L_0x02fa
        L_0x01b5:
            if (r18 == 0) goto L_0x0242
            java.lang.Class<java.util.Map> r14 = java.util.Map.class
            boolean r14 = r14.isAssignableFrom(r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r14 != 0) goto L_0x01c7
            java.lang.Class<java.util.Collection> r14 = java.util.Collection.class
            boolean r14 = r14.isAssignableFrom(r2)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r14 == 0) goto L_0x0242
        L_0x01c7:
            java.lang.Class<java.util.HashMap> r14 = java.util.HashMap.class
            boolean r14 = r2.equals(r14)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            if (r14 == 0) goto L_0x01f5
            org.json.JSONObject r14 = r11.getJSONObject(r3)     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            java.util.Iterator r15 = r14.keys()     // Catch:{ Exception -> 0x02d9, all -> 0x02d0 }
            r2 = r1
            r1 = r20
            r21 = r2
            r2 = r13
            r13 = r3
            r3 = r21
            r17 = r4
            r4 = r16
            r16 = r5
            r5 = r0
            r18 = r6
            r6 = r14
            r14 = r7
            r7 = r15
            java.util.Map r1 = r1.m2171a(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x01f5:
            r21 = r1
            r13 = r3
            r17 = r4
            r16 = r5
            r18 = r6
            r14 = r7
            java.lang.Class<java.util.ArrayList> r1 = java.util.ArrayList.class
            boolean r1 = r2.equals(r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x0216
            org.json.JSONArray r1 = r11.getJSONArray(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r3 = r21
            java.util.List r1 = r8.m2176b(r3, (java.lang.reflect.Field) r0, (org.json.JSONArray) r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x0216:
            r3 = r21
            java.lang.Class<java.util.HashSet> r1 = java.util.HashSet.class
            boolean r1 = r2.equals(r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x022d
            org.json.JSONArray r1 = r11.getJSONArray(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.util.Set r1 = r8.m2173a(r3, (org.json.JSONArray) r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x022d:
            java.lang.Class<java.util.EnumSet> r1 = java.util.EnumSet.class
            boolean r1 = r2.equals(r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x0301
            org.json.JSONArray r1 = r11.getJSONArray(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.util.Set r1 = r8.m2172a(r3, (java.lang.reflect.Field) r0, (org.json.JSONArray) r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x0242:
            r13 = r3
            r17 = r4
            r16 = r5
            r18 = r6
            r14 = r7
            java.lang.Class r1 = r0.getType()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            boolean r1 = r1.isEnum()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x0263
            java.lang.Object r1 = r11.get(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.Enum r1 = r8.m2165a((java.lang.String) r1, (java.lang.Class) r2)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x0263:
            java.lang.Class r1 = r0.getType()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            boolean r1 = r1.isPrimitive()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x027e
            java.lang.Object r1 = r11.get(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.Class r2 = r0.getType()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.Object r1 = m2169a(r11, r0, r1, r2)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x027e:
            java.lang.Class r1 = r0.getType()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            boolean r1 = r1.isArray()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r1 == 0) goto L_0x0291
            java.lang.Object r1 = r8.m2167a((org.json.JSONObject) r11, r2, (java.lang.reflect.Field) r0)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x0291:
            java.lang.Object r1 = r11.get(r13)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.Class r2 = r0.getType()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.Object r1 = m2166a((java.lang.Object) r1, (java.lang.Class<?>) r2)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            boolean r2 = r1.equals(r10)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r2 == 0) goto L_0x02a7
            r0.set(r14, r10)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x02a7:
            r0.set(r14, r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x02ab:
            r13 = r3
            r17 = r4
            r16 = r5
            r18 = r6
            r14 = r7
            java.lang.Boolean r0 = com.startapp.common.Constants.m1978a()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            if (r0 == 0) goto L_0x0301
            r0 = 4
            java.lang.String r1 = "Field [%s] doesn't exist. Keeping default value."
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            r2 = 0
            r3[r2] = r13     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r9, (int) r0, (java.lang.String) r1)     // Catch:{ Exception -> 0x02ce, all -> 0x02d0 }
            goto L_0x0301
        L_0x02ce:
            r0 = move-exception
            goto L_0x02e2
        L_0x02d0:
            r0 = move-exception
            com.startapp.common.c.d r1 = new com.startapp.common.c.d
            java.lang.String r2 = "Unknown error occurred "
            r1.<init>(r2, r0)
            throw r1
        L_0x02d9:
            r0 = move-exception
            r13 = r3
            r17 = r4
            r16 = r5
            r18 = r6
            r14 = r7
        L_0x02e2:
            r1 = 6
            r2 = 2
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r13
            java.lang.String r0 = r0.toString()
            r5 = 1
            r3[r5] = r0
            java.lang.String r0 = "Failed to get field [%s] %s"
            java.lang.String r0 = java.lang.String.format(r0, r3)
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r9, (int) r1, (java.lang.String) r0)
            goto L_0x0304
        L_0x02fa:
            r17 = r4
            r16 = r5
            r18 = r6
            r14 = r7
        L_0x0301:
            r2 = 2
            r4 = 0
            r5 = 1
        L_0x0304:
            int r0 = r17 + 1
            r4 = r0
            r7 = r14
            r5 = r16
            r6 = r18
            r13 = 2
            r14 = 1
            r15 = 0
            goto L_0x00f3
        L_0x0311:
            r14 = r7
            return r14
        L_0x0313:
            r0 = move-exception
            com.startapp.common.c.d r1 = new com.startapp.common.c.d
            java.lang.String r2 = "Can't deserialize the object. Failed to instantiate object."
            r1.<init>(r2, r0)
            goto L_0x031d
        L_0x031c:
            throw r1
        L_0x031d:
            goto L_0x031c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.p046c.C1294a.m2175b(java.lang.Class, org.json.JSONObject):java.lang.Object");
    }

    /* renamed from: a */
    private <T> Field[] m2174a(Class<T> cls, Field[] fieldArr) {
        int length = fieldArr.length;
        Field[] declaredFields = cls.getSuperclass().getDeclaredFields();
        int length2 = declaredFields.length;
        Field[] fieldArr2 = new Field[(length + length2)];
        System.arraycopy(fieldArr, 0, fieldArr2, 0, length);
        System.arraycopy(declaredFields, 0, fieldArr2, length, length2);
        return fieldArr2;
    }

    /* renamed from: a */
    private Enum<?> m2165a(String str, Class cls) {
        return Enum.valueOf(cls, str);
    }

    /* renamed from: a */
    private Object m2168a(JSONObject jSONObject, Field field) {
        Object obj;
        JSONArray jSONArray = jSONObject.getJSONArray(C1296c.m2183a(field));
        int length = jSONArray.length();
        Class cls = f1557a.get(field.getType().getSimpleName());
        Object newInstance = Array.newInstance((Class) cls.getField("TYPE").get((Object) null), length);
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            Class<String> cls2 = String.class;
            if (cls.equals(Character.class)) {
                cls2 = Character.TYPE;
            }
            Constructor constructor = cls.getConstructor(new Class[]{cls2});
            if (cls.equals(Character.class)) {
                obj = constructor.newInstance(new Object[]{Character.valueOf(string.charAt(0))});
            } else {
                obj = constructor.newInstance(new Object[]{string});
            }
            Array.set(newInstance, i, obj);
        }
        return newInstance;
    }

    /* renamed from: a */
    private <T> Object m2167a(JSONObject jSONObject, Class<T> cls, Field field) {
        if (cls != null) {
            return m2177b(jSONObject, cls, field);
        }
        return m2168a(jSONObject, field);
    }

    /* renamed from: b */
    private <T> T[] m2177b(JSONObject jSONObject, Class<T> cls, Field field) {
        JSONArray jSONArray = jSONObject.getJSONArray(C1296c.m2183a(field));
        int length = jSONArray.length();
        Object newInstance = Array.newInstance(cls, length);
        for (int i = 0; i < length; i++) {
            Array.set(newInstance, i, m2175b(cls, jSONArray.getJSONObject(i)));
        }
        return (Object[]) newInstance;
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Class<K>, java.lang.Object, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <K, V> java.util.Map<K, V> m2171a(java.lang.Class<K> r4, java.lang.Class<V> r5, java.lang.Class r6, java.lang.reflect.Field r7, org.json.JSONObject r8, java.util.Iterator<K> r9) {
        /*
            r3 = this;
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
        L_0x0005:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x0070
            java.lang.Object r0 = r9.next()
            java.lang.Class<java.lang.Integer> r1 = java.lang.Integer.class
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0027
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            int r1 = java.lang.Integer.parseInt(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = r4.cast(r1)
            goto L_0x0028
        L_0x0027:
            r1 = r0
        L_0x0028:
            boolean r2 = r4.isEnum()
            if (r2 == 0) goto L_0x0036
            java.lang.String r1 = r1.toString()
            java.lang.Enum r1 = r3.m2165a((java.lang.String) r1, (java.lang.Class) r4)
        L_0x0036:
            java.lang.String r0 = (java.lang.String) r0
            org.json.JSONObject r2 = r8.optJSONObject(r0)
            if (r2 != 0) goto L_0x0068
            org.json.JSONArray r2 = r8.optJSONArray(r0)
            if (r2 != 0) goto L_0x0060
            boolean r2 = r5.isEnum()
            if (r2 == 0) goto L_0x0058
            java.lang.Object r0 = r8.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Enum r0 = r3.m2165a((java.lang.String) r0, (java.lang.Class) r5)
            r7.put(r1, r0)
            goto L_0x0005
        L_0x0058:
            java.lang.Object r0 = r8.get(r0)
            r7.put(r1, r0)
            goto L_0x0005
        L_0x0060:
            java.util.Set r0 = r3.m2173a(r6, (org.json.JSONArray) r2)
            r7.put(r1, r0)
            goto L_0x0005
        L_0x0068:
            java.lang.Object r0 = r3.m2175b(r5, r2)
            r7.put(r1, r0)
            goto L_0x0005
        L_0x0070:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.p046c.C1294a.m2171a(java.lang.Class, java.lang.Class, java.lang.Class, java.lang.reflect.Field, org.json.JSONObject, java.util.Iterator):java.util.Map");
    }

    /* renamed from: a */
    private <V> Set<V> m2173a(Class<V> cls, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                hashSet.add(jSONArray.get(i));
            } else {
                hashSet.add(m2175b(cls, optJSONObject));
            }
        }
        return hashSet;
    }

    /* renamed from: a */
    private <V> Set m2172a(Class<V> cls, Field field, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(m2165a(jSONArray.getString(i), (Class) cls));
        }
        return hashSet;
    }

    /* renamed from: b */
    private <V> List<V> m2176b(Class<V> cls, Field field, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                arrayList.add(jSONArray.get(i));
            } else {
                arrayList.add(m2175b(cls, optJSONObject));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static Object m2166a(Object obj, Class<?> cls) {
        if (obj.getClass().equals(cls)) {
            return obj;
        }
        if (!cls.equals(Integer.class)) {
            return (!cls.equals(Long.class) || !obj.getClass().equals(Integer.class)) ? obj : Long.valueOf(((Integer) obj).longValue());
        }
        if (obj.getClass().equals(Double.class)) {
            return Integer.valueOf(((Double) obj).intValue());
        }
        if (obj.getClass().equals(Long.class)) {
            return Integer.valueOf(((Long) obj).intValue());
        }
        return obj;
    }

    /* renamed from: a */
    private static Object m2169a(JSONObject jSONObject, Field field, Object obj, Class<?> cls) {
        if (obj.getClass().equals(cls)) {
            return obj;
        }
        if (obj.getClass().equals(String.class)) {
            if (cls.equals(Integer.TYPE)) {
                return Integer.valueOf(jSONObject.getInt(C1296c.m2183a(field)));
            }
            return obj;
        } else if (cls.equals(Integer.TYPE)) {
            return Integer.valueOf(((Number) obj).intValue());
        } else {
            if (cls.equals(Float.TYPE)) {
                return Float.valueOf(((Number) obj).floatValue());
            }
            if (cls.equals(Long.TYPE)) {
                return Long.valueOf(((Number) obj).longValue());
            }
            return cls.equals(Double.TYPE) ? Double.valueOf(((Number) obj).doubleValue()) : obj;
        }
    }

    /* renamed from: a */
    private static String m2170a(InputStream inputStream, String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
            } catch (Exception e) {
                C1270g.m2078a("JSONInputStream", 6, String.format("Can't create BufferedReader [%s]", new Object[]{e.toString()}));
                throw e;
            }
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            } catch (IOException e2) {
                C1270g.m2078a("JSONInputStream", 6, String.format("Can't Can't read input stream [%s]", new Object[]{e2.toString()}));
                throw e2;
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        f1557a = hashMap;
        hashMap.put("int[]", Integer.class);
        f1557a.put("long[]", Long.class);
        f1557a.put("double[]", Double.class);
        f1557a.put("float[]", Float.class);
        f1557a.put("bool[]", Boolean.class);
        f1557a.put("char[]", Character.class);
        f1557a.put("byte[]", Byte.class);
        f1557a.put("void[]", Void.class);
        f1557a.put("short[]", Short.class);
    }
}
