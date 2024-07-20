package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.z */
class C0366z {

    /* renamed from: a */
    String f237a = "{}";

    /* renamed from: b */
    private C0370c f238b = new C0370c();

    /* renamed from: c */
    private JSONObject f239c;

    /* renamed from: d */
    private Rect f240d;

    /* renamed from: e */
    private Rect f241e;

    /* renamed from: f */
    private JSONObject f242f;

    /* renamed from: g */
    private JSONObject f243g;

    /* renamed from: h */
    private Map<String, Object> f244h = new HashMap();

    /* renamed from: com.moat.analytics.mobile.mpub.z$a */
    static class C0368a {

        /* renamed from: a */
        int f245a = 0;

        /* renamed from: b */
        final Set<Rect> f246b = new HashSet();

        /* renamed from: c */
        boolean f247c = false;

        C0368a() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.z$b */
    private static class C0369b {

        /* renamed from: a */
        final View f248a;

        /* renamed from: b */
        final Rect f249b;

        C0369b(View view, C0369b bVar) {
            this.f248a = view;
            this.f249b = bVar != null ? C0366z.m316b(view, bVar.f249b.left, bVar.f249b.top) : C0366z.m326k(view);
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.z$c */
    private static class C0370c {

        /* renamed from: a */
        Rect f250a = new Rect(0, 0, 0, 0);

        /* renamed from: b */
        double f251b = 0.0d;

        /* renamed from: c */
        double f252c = 0.0d;

        C0370c() {
        }
    }

    C0366z() {
    }

    /* renamed from: a */
    static int m302a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList<Rect> arrayList = new ArrayList<>();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() {
            /* renamed from: a */
            public int compare(Rect rect, Rect rect2) {
                return Integer.valueOf(rect.top).compareTo(Integer.valueOf(rect2.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        while (i < arrayList2.size() - 1) {
            int i3 = i + 1;
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i3))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i3)).intValue(), rect.bottom);
                int i4 = rect.top;
                for (Rect rect4 : arrayList) {
                    if (Rect.intersects(rect4, rect3)) {
                        if (rect4.bottom > i4) {
                            i2 += rect3.width() * (rect4.bottom - Math.max(i4, rect4.top));
                            i4 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i = i3;
        }
        return i2;
    }

    /* renamed from: a */
    private static Rect m303a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    /* renamed from: a */
    static Rect m304a(View view) {
        return view != null ? m326k(view) : new Rect(0, 0, 0, 0);
    }

    /* renamed from: a */
    static C0368a m306a(Rect rect, View view) {
        C0368a aVar = new C0368a();
        try {
            ArrayDeque<View> i = m324i(view);
            if (i != null) {
                if (!i.isEmpty()) {
                    C0336p.m232b(2, "VisibilityInfo", view, "starting covering rect search");
                    C0369b bVar = null;
                    loop0:
                    while (true) {
                        if (i.isEmpty()) {
                            break;
                        }
                        View pollLast = i.pollLast();
                        C0369b bVar2 = new C0369b(pollLast, bVar);
                        if (pollLast.getParent() != null) {
                            if (pollLast.getParent() instanceof ViewGroup) {
                                ViewGroup viewGroup = (ViewGroup) pollLast.getParent();
                                int childCount = viewGroup.getChildCount();
                                boolean z = false;
                                for (int i2 = 0; i2 < childCount; i2++) {
                                    if (aVar.f245a >= 500) {
                                        C0336p.m228a(3, "VisibilityInfo", (Object) null, "Short-circuiting cover retrieval, reached max");
                                        break loop0;
                                    }
                                    View childAt = viewGroup.getChildAt(i2);
                                    if (childAt == pollLast) {
                                        z = true;
                                    } else {
                                        aVar.f245a++;
                                        if (m312a(childAt, pollLast, z)) {
                                            m317b(new C0369b(childAt, bVar), rect, aVar);
                                            if (aVar.f247c) {
                                                return aVar;
                                            }
                                        } else {
                                            continue;
                                        }
                                    }
                                }
                                continue;
                            } else {
                                continue;
                            }
                        }
                        bVar = bVar2;
                    }
                    return aVar;
                }
            }
            return aVar;
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    /* renamed from: a */
    private static C0370c m307a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        C0370c cVar = new C0370c();
        int b = m313b(rect);
        if (view != null && z && z2 && !z3 && b > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (m311a(view, rect2)) {
                int b2 = m313b(rect2);
                if (b2 < b) {
                    C0336p.m232b(2, "VisibilityInfo", (Object) null, "Ad is clipped");
                }
                if (view.getRootView() instanceof ViewGroup) {
                    cVar.f250a = rect2;
                    C0368a a = m306a(rect2, view);
                    if (a.f247c) {
                        cVar.f252c = 1.0d;
                    } else {
                        int a2 = m302a(rect2, a.f246b);
                        if (a2 > 0) {
                            double d = (double) a2;
                            double d2 = (double) b2;
                            Double.isNaN(d2);
                            Double.isNaN(d);
                            cVar.f252c = d / (d2 * 1.0d);
                        }
                        double d3 = (double) (b2 - a2);
                        double d4 = (double) b;
                        Double.isNaN(d4);
                        Double.isNaN(d3);
                        cVar.f251b = d3 / (d4 * 1.0d);
                    }
                }
            }
        }
        return cVar;
    }

    /* renamed from: a */
    private static Map<String, String> m308a(Rect rect) {
        HashMap hashMap = new HashMap();
        hashMap.put(AvidJSONUtil.KEY_X, String.valueOf(rect.left));
        hashMap.put(AvidJSONUtil.KEY_Y, String.valueOf(rect.top));
        hashMap.put("w", String.valueOf(rect.right - rect.left));
        hashMap.put("h", String.valueOf(rect.bottom - rect.top));
        return hashMap;
    }

    /* renamed from: a */
    private static Map<String, String> m309a(Rect rect, DisplayMetrics displayMetrics) {
        return m308a(m314b(rect, displayMetrics));
    }

    /* renamed from: a */
    private static void m310a(C0369b bVar, Rect rect, C0368a aVar) {
        Rect rect2 = bVar.f249b;
        if (rect2.setIntersect(rect, rect2)) {
            if (Build.VERSION.SDK_INT >= 22) {
                Rect rect3 = new Rect(0, 0, 0, 0);
                if (m311a(bVar.f248a, rect3)) {
                    Rect rect4 = bVar.f249b;
                    if (rect4.setIntersect(rect3, rect4)) {
                        rect2 = rect4;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (C0351w.m264a().f199c) {
                C0336p.m232b(2, "VisibilityInfo", bVar.f248a, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", new Object[]{bVar.f248a.getClass().getName(), rect2.toString(), Float.valueOf(bVar.f248a.getAlpha())}));
            }
            aVar.f246b.add(rect2);
            if (rect2.contains(rect)) {
                aVar.f247c = true;
            }
        }
    }

    /* renamed from: a */
    private static boolean m311a(View view, Rect rect) {
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int[] iArr2 = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    /* renamed from: a */
    private static boolean m312a(View view, View view2, boolean z) {
        return z ? Build.VERSION.SDK_INT < 21 || view.getZ() >= view2.getZ() : Build.VERSION.SDK_INT >= 21 && view.getZ() > view2.getZ();
    }

    /* renamed from: b */
    private static int m313b(Rect rect) {
        return rect.width() * rect.height();
    }

    /* renamed from: b */
    private static Rect m314b(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static Rect m316b(View view, int i, int i2) {
        int left = i + view.getLeft();
        int top = i2 + view.getTop();
        return new Rect(left, top, view.getWidth() + left, view.getHeight() + top);
    }

    /* renamed from: b */
    private static void m317b(C0369b bVar, Rect rect, C0368a aVar) {
        if (m323h(bVar.f248a)) {
            boolean z = true;
            if (bVar.f248a instanceof ViewGroup) {
                int i = 0;
                boolean z2 = !ViewGroup.class.equals(bVar.f248a.getClass().getSuperclass()) || !m325j(bVar.f248a);
                ViewGroup viewGroup = (ViewGroup) bVar.f248a;
                int childCount = viewGroup.getChildCount();
                while (i < childCount) {
                    int i2 = aVar.f245a + 1;
                    aVar.f245a = i2;
                    if (i2 <= 500) {
                        m317b(new C0369b(viewGroup.getChildAt(i), bVar), rect, aVar);
                        if (!aVar.f247c) {
                            i++;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                z = z2;
            }
            if (z) {
                m310a(bVar, rect, aVar);
            }
        }
    }

    /* renamed from: c */
    private static boolean m318c(View view) {
        return Build.VERSION.SDK_INT >= 19 ? view != null && view.isAttachedToWindow() : (view == null || view.getWindowToken() == null) ? false : true;
    }

    /* renamed from: d */
    private static boolean m319d(View view) {
        return view != null && view.hasWindowFocus();
    }

    /* renamed from: e */
    private static boolean m320e(View view) {
        return view == null || !view.isShown();
    }

    /* renamed from: f */
    private static float m321f(View view) {
        if (view == null) {
            return 0.0f;
        }
        return m322g(view);
    }

    /* renamed from: g */
    private static float m322g(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && ((double) alpha) != 0.0d && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    /* renamed from: h */
    private static boolean m323h(View view) {
        return view.isShown() && ((double) view.getAlpha()) > 0.0d;
    }

    /* renamed from: i */
    private static ArrayDeque<View> m324i(View view) {
        ArrayDeque<View> arrayDeque = new ArrayDeque<>();
        int i = 0;
        View view2 = view;
        while (true) {
            if (view2.getParent() == null && view2 != view.getRootView()) {
                break;
            }
            i++;
            if (i <= 50) {
                arrayDeque.add(view2);
                if (!(view2.getParent() instanceof View)) {
                    break;
                }
                view2 = (View) view2.getParent();
            } else {
                C0336p.m228a(3, "VisibilityInfo", (Object) null, "Short-circuiting chain retrieval, reached max");
                break;
            }
        }
        return arrayDeque;
    }

    /* renamed from: j */
    private static boolean m325j(View view) {
        return Build.VERSION.SDK_INT < 19 || view.getBackground() == null || view.getBackground().getAlpha() == 0;
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public static Rect m326k(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    /* renamed from: l */
    private static DisplayMetrics m327l(View view) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 17 || C0298a.f53a == null || (activity = (Activity) C0298a.f53a.get()) == null) {
            return view.getContext().getResources().getDisplayMetrics();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0107 A[Catch:{ Exception -> 0x013e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo10471a(java.lang.String r12, android.view.View r13) {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = "{}"
            if (r13 == 0) goto L_0x0144
            android.util.DisplayMetrics r2 = m327l(r13)     // Catch:{ Exception -> 0x013e }
            boolean r3 = m318c(r13)     // Catch:{ Exception -> 0x013e }
            boolean r4 = m319d(r13)     // Catch:{ Exception -> 0x013e }
            boolean r5 = m320e(r13)     // Catch:{ Exception -> 0x013e }
            float r6 = m321f(r13)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "dr"
            float r8 = r2.density     // Catch:{ Exception -> 0x013e }
            java.lang.Float r8 = java.lang.Float.valueOf(r8)     // Catch:{ Exception -> 0x013e }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "dv"
            com.moat.analytics.mobile.mpub.l r8 = com.moat.analytics.mobile.mpub.C0328l.m199a()     // Catch:{ Exception -> 0x013e }
            double r8 = r8.mo10452b()     // Catch:{ Exception -> 0x013e }
            java.lang.Double r8 = java.lang.Double.valueOf(r8)     // Catch:{ Exception -> 0x013e }
            r0.put(r7, r8)     // Catch:{ Exception -> 0x013e }
            java.lang.String r7 = "adKey"
            r0.put(r7, r12)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "isAttached"
            r7 = 0
            r8 = 1
            if (r3 == 0) goto L_0x0046
            r9 = 1
            goto L_0x0047
        L_0x0046:
            r9 = 0
        L_0x0047:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "inFocus"
            if (r4 == 0) goto L_0x0054
            r9 = 1
            goto L_0x0055
        L_0x0054:
            r9 = 0
        L_0x0055:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "isHidden"
            if (r5 == 0) goto L_0x0062
            r9 = 1
            goto L_0x0063
        L_0x0062:
            r9 = 0
        L_0x0063:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r9)     // Catch:{ Exception -> 0x013e }
            java.lang.String r12 = "opacity"
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ Exception -> 0x013e }
            r0.put(r12, r6)     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r12 = m303a((android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r6 = m304a((android.view.View) r13)     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r13 = m307a(r13, r6, r3, r4, r5)     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r3 = r11.f239c     // Catch:{ Exception -> 0x013e }
            if (r3 == 0) goto L_0x00a3
            double r3 = r13.f251b     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r5 = r11.f238b     // Catch:{ Exception -> 0x013e }
            double r9 = r5.f251b     // Catch:{ Exception -> 0x013e }
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 != 0) goto L_0x00a3
            android.graphics.Rect r3 = r13.f250a     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r4 = r11.f238b     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r4 = r4.f250a     // Catch:{ Exception -> 0x013e }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x013e }
            if (r3 == 0) goto L_0x00a3
            double r3 = r13.f252c     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r5 = r11.f238b     // Catch:{ Exception -> 0x013e }
            double r9 = r5.f252c     // Catch:{ Exception -> 0x013e }
            int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x00b5
        L_0x00a3:
            r11.f238b = r13     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            com.moat.analytics.mobile.mpub.z$c r4 = r11.f238b     // Catch:{ Exception -> 0x013e }
            android.graphics.Rect r4 = r4.f250a     // Catch:{ Exception -> 0x013e }
            java.util.Map r4 = m309a((android.graphics.Rect) r4, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r3.<init>(r4)     // Catch:{ Exception -> 0x013e }
            r11.f239c = r3     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00b5:
            java.lang.String r3 = "coveredPercent"
            double r4 = r13.f252c     // Catch:{ Exception -> 0x013e }
            java.lang.Double r13 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x013e }
            r0.put(r3, r13)     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r13 = r11.f243g     // Catch:{ Exception -> 0x013e }
            if (r13 == 0) goto L_0x00cc
            android.graphics.Rect r13 = r11.f241e     // Catch:{ Exception -> 0x013e }
            boolean r13 = r12.equals(r13)     // Catch:{ Exception -> 0x013e }
            if (r13 != 0) goto L_0x00da
        L_0x00cc:
            r11.f241e = r12     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r13 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map r12 = m309a((android.graphics.Rect) r12, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r13.<init>(r12)     // Catch:{ Exception -> 0x013e }
            r11.f243g = r13     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00da:
            org.json.JSONObject r12 = r11.f242f     // Catch:{ Exception -> 0x013e }
            if (r12 == 0) goto L_0x00e6
            android.graphics.Rect r12 = r11.f240d     // Catch:{ Exception -> 0x013e }
            boolean r12 = r6.equals(r12)     // Catch:{ Exception -> 0x013e }
            if (r12 != 0) goto L_0x00f4
        L_0x00e6:
            r11.f240d = r6     // Catch:{ Exception -> 0x013e }
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map r13 = m309a((android.graphics.Rect) r6, (android.util.DisplayMetrics) r2)     // Catch:{ Exception -> 0x013e }
            r12.<init>(r13)     // Catch:{ Exception -> 0x013e }
            r11.f242f = r12     // Catch:{ Exception -> 0x013e }
            r7 = 1
        L_0x00f4:
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.f244h     // Catch:{ Exception -> 0x013e }
            if (r12 == 0) goto L_0x0103
            java.util.Map<java.lang.String, java.lang.Object> r12 = r11.f244h     // Catch:{ Exception -> 0x013e }
            boolean r12 = r0.equals(r12)     // Catch:{ Exception -> 0x013e }
            if (r12 != 0) goto L_0x0101
            goto L_0x0103
        L_0x0101:
            r8 = r7
            goto L_0x0105
        L_0x0103:
            r11.f244h = r0     // Catch:{ Exception -> 0x013e }
        L_0x0105:
            if (r8 == 0) goto L_0x0144
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch:{ Exception -> 0x013e }
            java.util.Map<java.lang.String, java.lang.Object> r13 = r11.f244h     // Catch:{ Exception -> 0x013e }
            r12.<init>(r13)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "screen"
            org.json.JSONObject r0 = r11.f243g     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "view"
            org.json.JSONObject r0 = r11.f242f     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "visible"
            org.json.JSONObject r0 = r11.f239c     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "maybe"
            org.json.JSONObject r0 = r11.f239c     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r13 = "visiblePercent"
            com.moat.analytics.mobile.mpub.z$c r0 = r11.f238b     // Catch:{ Exception -> 0x013e }
            double r2 = r0.f251b     // Catch:{ Exception -> 0x013e }
            java.lang.Double r0 = java.lang.Double.valueOf(r2)     // Catch:{ Exception -> 0x013e }
            r12.accumulate(r13, r0)     // Catch:{ Exception -> 0x013e }
            java.lang.String r1 = r12.toString()     // Catch:{ Exception -> 0x013e }
            r11.f237a = r1     // Catch:{ Exception -> 0x013e }
            goto L_0x0144
        L_0x013e:
            r12 = move-exception
            com.moat.analytics.mobile.mpub.C0330n.m214a(r12)
            r11.f237a = r1
        L_0x0144:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.C0366z.mo10471a(java.lang.String, android.view.View):void");
    }
}
