package com.yandex.metrica;

import com.yandex.metrica.impl.p050ob.C1957b;
import com.yandex.metrica.impl.p050ob.C2015c;
import com.yandex.metrica.impl.p050ob.C2056d;
import com.yandex.metrica.impl.p050ob.C2140f;
import java.io.IOException;
import java.util.Arrays;

/* renamed from: com.yandex.metrica.c */
public interface C1781c {

    /* renamed from: com.yandex.metrica.c$a */
    public static final class C1782a extends C2056d {

        /* renamed from: b */
        public C1795f f2764b;

        /* renamed from: c */
        public C1786d[] f2765c;

        /* renamed from: d */
        public C1783a[] f2766d;

        /* renamed from: e */
        public C1785c[] f2767e;

        /* renamed from: f */
        public String[] f2768f;

        /* renamed from: g */
        public C1794e[] f2769g;

        /* renamed from: com.yandex.metrica.c$a$f */
        public static final class C1795f extends C2056d {

            /* renamed from: b */
            public long f2834b;

            /* renamed from: c */
            public int f2835c;

            /* renamed from: d */
            public long f2836d;

            public C1795f() {
                mo16690d();
            }

            /* renamed from: d */
            public C1795f mo16690d() {
                this.f2834b = 0;
                this.f2835c = 0;
                this.f2836d = 0;
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                bVar.mo17189a(1, this.f2834b);
                bVar.mo17207c(2, this.f2835c);
                long j = this.f2836d;
                if (j != 0) {
                    bVar.mo17202b(3, j);
                }
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                int c = super.mo16676c() + C1957b.m4847c(1, this.f2834b) + C1957b.m4854f(2, this.f2835c);
                long j = this.f2836d;
                return j != 0 ? c + C1957b.m4850d(3, j) : c;
            }
        }

        /* renamed from: com.yandex.metrica.c$a$b */
        public static final class C1784b extends C2056d {

            /* renamed from: b */
            public double f2773b;

            /* renamed from: c */
            public double f2774c;

            /* renamed from: d */
            public long f2775d;

            /* renamed from: e */
            public int f2776e;

            /* renamed from: f */
            public int f2777f;

            /* renamed from: g */
            public int f2778g;

            /* renamed from: h */
            public int f2779h;

            /* renamed from: i */
            public int f2780i;

            public C1784b() {
                mo16679d();
            }

            /* renamed from: d */
            public C1784b mo16679d() {
                this.f2773b = 0.0d;
                this.f2774c = 0.0d;
                this.f2775d = 0;
                this.f2776e = 0;
                this.f2777f = 0;
                this.f2778g = 0;
                this.f2779h = 0;
                this.f2780i = 0;
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                bVar.mo17187a(1, this.f2773b);
                bVar.mo17187a(2, this.f2774c);
                long j = this.f2775d;
                if (j != 0) {
                    bVar.mo17189a(3, j);
                }
                int i = this.f2776e;
                if (i != 0) {
                    bVar.mo17201b(4, i);
                }
                int i2 = this.f2777f;
                if (i2 != 0) {
                    bVar.mo17201b(5, i2);
                }
                int i3 = this.f2778g;
                if (i3 != 0) {
                    bVar.mo17201b(6, i3);
                }
                int i4 = this.f2779h;
                if (i4 != 0) {
                    bVar.mo17188a(7, i4);
                }
                int i5 = this.f2780i;
                if (i5 != 0) {
                    bVar.mo17188a(8, i5);
                }
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                int c = super.mo16676c() + C1957b.m4848d(1) + C1957b.m4848d(2);
                long j = this.f2775d;
                if (j != 0) {
                    c += C1957b.m4847c(3, j);
                }
                int i = this.f2776e;
                if (i != 0) {
                    c += C1957b.m4853e(4, i);
                }
                int i2 = this.f2777f;
                if (i2 != 0) {
                    c += C1957b.m4853e(5, i2);
                }
                int i3 = this.f2778g;
                if (i3 != 0) {
                    c += C1957b.m4853e(6, i3);
                }
                int i4 = this.f2779h;
                if (i4 != 0) {
                    c += C1957b.m4849d(7, i4);
                }
                int i5 = this.f2780i;
                return i5 != 0 ? c + C1957b.m4849d(8, i5) : c;
            }
        }

        /* renamed from: com.yandex.metrica.c$a$d */
        public static final class C1786d extends C2056d {

            /* renamed from: e */
            private static volatile C1786d[] f2784e;

            /* renamed from: b */
            public long f2785b;

            /* renamed from: c */
            public C1792b f2786c;

            /* renamed from: d */
            public C1787a[] f2787d;

            /* renamed from: com.yandex.metrica.c$a$d$c */
            public static final class C1793c extends C2056d {

                /* renamed from: f */
                private static volatile C1793c[] f2823f;

                /* renamed from: b */
                public String f2824b;

                /* renamed from: c */
                public int f2825c;

                /* renamed from: d */
                public String f2826d;

                /* renamed from: e */
                public boolean f2827e;

                /* renamed from: d */
                public static C1793c[] m4033d() {
                    if (f2823f == null) {
                        synchronized (C2015c.f3328a) {
                            if (f2823f == null) {
                                f2823f = new C1793c[0];
                            }
                        }
                    }
                    return f2823f;
                }

                public C1793c() {
                    mo16688e();
                }

                /* renamed from: e */
                public C1793c mo16688e() {
                    this.f2824b = "";
                    this.f2825c = 0;
                    this.f2826d = "";
                    this.f2827e = false;
                    this.f3445a = -1;
                    return this;
                }

                /* renamed from: a */
                public void mo16675a(C1957b bVar) throws IOException {
                    bVar.mo17191a(1, this.f2824b);
                    int i = this.f2825c;
                    if (i != 0) {
                        bVar.mo17207c(2, i);
                    }
                    if (!this.f2826d.equals("")) {
                        bVar.mo17191a(3, this.f2826d);
                    }
                    boolean z = this.f2827e;
                    if (z) {
                        bVar.mo17192a(4, z);
                    }
                    super.mo16675a(bVar);
                }

                /* access modifiers changed from: protected */
                /* renamed from: c */
                public int mo16676c() {
                    int c = super.mo16676c() + C1957b.m4843b(1, this.f2824b);
                    int i = this.f2825c;
                    if (i != 0) {
                        c += C1957b.m4854f(2, i);
                    }
                    if (!this.f2826d.equals("")) {
                        c += C1957b.m4843b(3, this.f2826d);
                    }
                    return this.f2827e ? c + C1957b.m4852e(4) : c;
                }
            }

            /* renamed from: com.yandex.metrica.c$a$d$b */
            public static final class C1792b extends C2056d {

                /* renamed from: b */
                public C1795f f2820b;

                /* renamed from: c */
                public String f2821c;

                /* renamed from: d */
                public int f2822d;

                public C1792b() {
                    mo16687d();
                }

                /* renamed from: d */
                public C1792b mo16687d() {
                    this.f2820b = null;
                    this.f2821c = "";
                    this.f2822d = 0;
                    this.f3445a = -1;
                    return this;
                }

                /* renamed from: a */
                public void mo16675a(C1957b bVar) throws IOException {
                    C1795f fVar = this.f2820b;
                    if (fVar != null) {
                        bVar.mo17190a(1, (C2056d) fVar);
                    }
                    bVar.mo17191a(2, this.f2821c);
                    int i = this.f2822d;
                    if (i != 0) {
                        bVar.mo17188a(5, i);
                    }
                    super.mo16675a(bVar);
                }

                /* access modifiers changed from: protected */
                /* renamed from: c */
                public int mo16676c() {
                    int c = super.mo16676c();
                    C1795f fVar = this.f2820b;
                    if (fVar != null) {
                        c += C1957b.m4842b(1, (C2056d) fVar);
                    }
                    int b = c + C1957b.m4843b(2, this.f2821c);
                    int i = this.f2822d;
                    return i != 0 ? b + C1957b.m4849d(5, i) : b;
                }
            }

            /* renamed from: com.yandex.metrica.c$a$d$a */
            public static final class C1787a extends C2056d {

                /* renamed from: m */
                private static volatile C1787a[] f2788m;

                /* renamed from: b */
                public long f2789b;

                /* renamed from: c */
                public long f2790c;

                /* renamed from: d */
                public int f2791d;

                /* renamed from: e */
                public String f2792e;

                /* renamed from: f */
                public byte[] f2793f;

                /* renamed from: g */
                public C1784b f2794g;

                /* renamed from: h */
                public C1789b f2795h;

                /* renamed from: i */
                public String f2796i;

                /* renamed from: j */
                public C1788a f2797j;

                /* renamed from: k */
                public int f2798k;

                /* renamed from: l */
                public int f2799l;

                /* renamed from: com.yandex.metrica.c$a$d$a$b */
                public static final class C1789b extends C2056d {

                    /* renamed from: b */
                    public C1790a[] f2803b;

                    /* renamed from: c */
                    public C1793c[] f2804c;

                    /* renamed from: d */
                    public int f2805d;

                    /* renamed from: e */
                    public String f2806e;

                    /* renamed from: f */
                    public C1791b f2807f;

                    /* renamed from: com.yandex.metrica.c$a$d$a$b$a */
                    public static final class C1790a extends C2056d {

                        /* renamed from: k */
                        private static volatile C1790a[] f2808k;

                        /* renamed from: b */
                        public int f2809b;

                        /* renamed from: c */
                        public int f2810c;

                        /* renamed from: d */
                        public int f2811d;

                        /* renamed from: e */
                        public int f2812e;

                        /* renamed from: f */
                        public int f2813f;

                        /* renamed from: g */
                        public String f2814g;

                        /* renamed from: h */
                        public boolean f2815h;

                        /* renamed from: i */
                        public int f2816i;

                        /* renamed from: j */
                        public int f2817j;

                        /* renamed from: d */
                        public static C1790a[] m4023d() {
                            if (f2808k == null) {
                                synchronized (C2015c.f3328a) {
                                    if (f2808k == null) {
                                        f2808k = new C1790a[0];
                                    }
                                }
                            }
                            return f2808k;
                        }

                        public C1790a() {
                            mo16685e();
                        }

                        /* renamed from: e */
                        public C1790a mo16685e() {
                            this.f2809b = -1;
                            this.f2810c = 0;
                            this.f2811d = -1;
                            this.f2812e = -1;
                            this.f2813f = -1;
                            this.f2814g = "";
                            this.f2815h = false;
                            this.f2816i = 0;
                            this.f2817j = -1;
                            this.f3445a = -1;
                            return this;
                        }

                        /* renamed from: a */
                        public void mo16675a(C1957b bVar) throws IOException {
                            int i = this.f2809b;
                            if (i != -1) {
                                bVar.mo17201b(1, i);
                            }
                            int i2 = this.f2810c;
                            if (i2 != 0) {
                                bVar.mo17207c(2, i2);
                            }
                            int i3 = this.f2811d;
                            if (i3 != -1) {
                                bVar.mo17201b(3, i3);
                            }
                            int i4 = this.f2812e;
                            if (i4 != -1) {
                                bVar.mo17201b(4, i4);
                            }
                            int i5 = this.f2813f;
                            if (i5 != -1) {
                                bVar.mo17201b(5, i5);
                            }
                            if (!this.f2814g.equals("")) {
                                bVar.mo17191a(6, this.f2814g);
                            }
                            boolean z = this.f2815h;
                            if (z) {
                                bVar.mo17192a(7, z);
                            }
                            int i6 = this.f2816i;
                            if (i6 != 0) {
                                bVar.mo17188a(8, i6);
                            }
                            int i7 = this.f2817j;
                            if (i7 != -1) {
                                bVar.mo17201b(9, i7);
                            }
                            super.mo16675a(bVar);
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: c */
                        public int mo16676c() {
                            int c = super.mo16676c();
                            int i = this.f2809b;
                            if (i != -1) {
                                c += C1957b.m4853e(1, i);
                            }
                            int i2 = this.f2810c;
                            if (i2 != 0) {
                                c += C1957b.m4854f(2, i2);
                            }
                            int i3 = this.f2811d;
                            if (i3 != -1) {
                                c += C1957b.m4853e(3, i3);
                            }
                            int i4 = this.f2812e;
                            if (i4 != -1) {
                                c += C1957b.m4853e(4, i4);
                            }
                            int i5 = this.f2813f;
                            if (i5 != -1) {
                                c += C1957b.m4853e(5, i5);
                            }
                            if (!this.f2814g.equals("")) {
                                c += C1957b.m4843b(6, this.f2814g);
                            }
                            if (this.f2815h) {
                                c += C1957b.m4852e(7);
                            }
                            int i6 = this.f2816i;
                            if (i6 != 0) {
                                c += C1957b.m4849d(8, i6);
                            }
                            int i7 = this.f2817j;
                            return i7 != -1 ? c + C1957b.m4853e(9, i7) : c;
                        }
                    }

                    /* renamed from: com.yandex.metrica.c$a$d$a$b$b */
                    public static final class C1791b extends C2056d {

                        /* renamed from: b */
                        public String f2818b;

                        /* renamed from: c */
                        public int f2819c;

                        public C1791b() {
                            mo16686d();
                        }

                        /* renamed from: d */
                        public C1791b mo16686d() {
                            this.f2818b = "";
                            this.f2819c = 0;
                            this.f3445a = -1;
                            return this;
                        }

                        /* renamed from: a */
                        public void mo16675a(C1957b bVar) throws IOException {
                            bVar.mo17191a(1, this.f2818b);
                            int i = this.f2819c;
                            if (i != 0) {
                                bVar.mo17188a(2, i);
                            }
                            super.mo16675a(bVar);
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: c */
                        public int mo16676c() {
                            int c = super.mo16676c() + C1957b.m4843b(1, this.f2818b);
                            int i = this.f2819c;
                            return i != 0 ? c + C1957b.m4849d(2, i) : c;
                        }
                    }

                    public C1789b() {
                        mo16684d();
                    }

                    /* renamed from: d */
                    public C1789b mo16684d() {
                        this.f2803b = C1790a.m4023d();
                        this.f2804c = C1793c.m4033d();
                        this.f2805d = 2;
                        this.f2806e = "";
                        this.f2807f = null;
                        this.f3445a = -1;
                        return this;
                    }

                    /* renamed from: a */
                    public void mo16675a(C1957b bVar) throws IOException {
                        C1790a[] aVarArr = this.f2803b;
                        int i = 0;
                        if (aVarArr != null && aVarArr.length > 0) {
                            int i2 = 0;
                            while (true) {
                                C1790a[] aVarArr2 = this.f2803b;
                                if (i2 >= aVarArr2.length) {
                                    break;
                                }
                                C1790a aVar = aVarArr2[i2];
                                if (aVar != null) {
                                    bVar.mo17190a(1, (C2056d) aVar);
                                }
                                i2++;
                            }
                        }
                        C1793c[] cVarArr = this.f2804c;
                        if (cVarArr != null && cVarArr.length > 0) {
                            while (true) {
                                C1793c[] cVarArr2 = this.f2804c;
                                if (i >= cVarArr2.length) {
                                    break;
                                }
                                C1793c cVar = cVarArr2[i];
                                if (cVar != null) {
                                    bVar.mo17190a(2, (C2056d) cVar);
                                }
                                i++;
                            }
                        }
                        int i3 = this.f2805d;
                        if (i3 != 2) {
                            bVar.mo17188a(3, i3);
                        }
                        if (!this.f2806e.equals("")) {
                            bVar.mo17191a(4, this.f2806e);
                        }
                        C1791b bVar2 = this.f2807f;
                        if (bVar2 != null) {
                            bVar.mo17190a(5, (C2056d) bVar2);
                        }
                        super.mo16675a(bVar);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: c */
                    public int mo16676c() {
                        int c = super.mo16676c();
                        C1790a[] aVarArr = this.f2803b;
                        int i = 0;
                        if (aVarArr != null && aVarArr.length > 0) {
                            int i2 = 0;
                            while (true) {
                                C1790a[] aVarArr2 = this.f2803b;
                                if (i2 >= aVarArr2.length) {
                                    break;
                                }
                                C1790a aVar = aVarArr2[i2];
                                if (aVar != null) {
                                    c += C1957b.m4842b(1, (C2056d) aVar);
                                }
                                i2++;
                            }
                        }
                        C1793c[] cVarArr = this.f2804c;
                        if (cVarArr != null && cVarArr.length > 0) {
                            while (true) {
                                C1793c[] cVarArr2 = this.f2804c;
                                if (i >= cVarArr2.length) {
                                    break;
                                }
                                C1793c cVar = cVarArr2[i];
                                if (cVar != null) {
                                    c += C1957b.m4842b(2, (C2056d) cVar);
                                }
                                i++;
                            }
                        }
                        int i3 = this.f2805d;
                        if (i3 != 2) {
                            c += C1957b.m4849d(3, i3);
                        }
                        if (!this.f2806e.equals("")) {
                            c += C1957b.m4843b(4, this.f2806e);
                        }
                        C1791b bVar = this.f2807f;
                        return bVar != null ? c + C1957b.m4842b(5, (C2056d) bVar) : c;
                    }
                }

                /* renamed from: com.yandex.metrica.c$a$d$a$a */
                public static final class C1788a extends C2056d {

                    /* renamed from: b */
                    public String f2800b;

                    /* renamed from: c */
                    public String f2801c;

                    /* renamed from: d */
                    public String f2802d;

                    public C1788a() {
                        mo16683d();
                    }

                    /* renamed from: d */
                    public C1788a mo16683d() {
                        this.f2800b = "";
                        this.f2801c = "";
                        this.f2802d = "";
                        this.f3445a = -1;
                        return this;
                    }

                    /* renamed from: a */
                    public void mo16675a(C1957b bVar) throws IOException {
                        bVar.mo17191a(1, this.f2800b);
                        if (!this.f2801c.equals("")) {
                            bVar.mo17191a(2, this.f2801c);
                        }
                        if (!this.f2802d.equals("")) {
                            bVar.mo17191a(3, this.f2802d);
                        }
                        super.mo16675a(bVar);
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: c */
                    public int mo16676c() {
                        int c = super.mo16676c() + C1957b.m4843b(1, this.f2800b);
                        if (!this.f2801c.equals("")) {
                            c += C1957b.m4843b(2, this.f2801c);
                        }
                        return !this.f2802d.equals("") ? c + C1957b.m4843b(3, this.f2802d) : c;
                    }
                }

                /* renamed from: d */
                public static C1787a[] m4013d() {
                    if (f2788m == null) {
                        synchronized (C2015c.f3328a) {
                            if (f2788m == null) {
                                f2788m = new C1787a[0];
                            }
                        }
                    }
                    return f2788m;
                }

                public C1787a() {
                    mo16682e();
                }

                /* renamed from: e */
                public C1787a mo16682e() {
                    this.f2789b = 0;
                    this.f2790c = 0;
                    this.f2791d = 0;
                    this.f2792e = "";
                    this.f2793f = C2140f.f3683b;
                    this.f2794g = null;
                    this.f2795h = null;
                    this.f2796i = "";
                    this.f2797j = null;
                    this.f2798k = 0;
                    this.f2799l = 0;
                    this.f3445a = -1;
                    return this;
                }

                /* renamed from: a */
                public void mo16675a(C1957b bVar) throws IOException {
                    bVar.mo17189a(1, this.f2789b);
                    bVar.mo17189a(2, this.f2790c);
                    bVar.mo17201b(3, this.f2791d);
                    if (!this.f2792e.equals("")) {
                        bVar.mo17191a(4, this.f2792e);
                    }
                    if (!Arrays.equals(this.f2793f, C2140f.f3683b)) {
                        bVar.mo17193a(5, this.f2793f);
                    }
                    C1784b bVar2 = this.f2794g;
                    if (bVar2 != null) {
                        bVar.mo17190a(6, (C2056d) bVar2);
                    }
                    C1789b bVar3 = this.f2795h;
                    if (bVar3 != null) {
                        bVar.mo17190a(7, (C2056d) bVar3);
                    }
                    if (!this.f2796i.equals("")) {
                        bVar.mo17191a(8, this.f2796i);
                    }
                    C1788a aVar = this.f2797j;
                    if (aVar != null) {
                        bVar.mo17190a(9, (C2056d) aVar);
                    }
                    int i = this.f2798k;
                    if (i != 0) {
                        bVar.mo17201b(10, i);
                    }
                    int i2 = this.f2799l;
                    if (i2 != 0) {
                        bVar.mo17188a(12, i2);
                    }
                    super.mo16675a(bVar);
                }

                /* access modifiers changed from: protected */
                /* renamed from: c */
                public int mo16676c() {
                    int c = super.mo16676c() + C1957b.m4847c(1, this.f2789b) + C1957b.m4847c(2, this.f2790c) + C1957b.m4853e(3, this.f2791d);
                    if (!this.f2792e.equals("")) {
                        c += C1957b.m4843b(4, this.f2792e);
                    }
                    if (!Arrays.equals(this.f2793f, C2140f.f3683b)) {
                        c += C1957b.m4844b(5, this.f2793f);
                    }
                    C1784b bVar = this.f2794g;
                    if (bVar != null) {
                        c += C1957b.m4842b(6, (C2056d) bVar);
                    }
                    C1789b bVar2 = this.f2795h;
                    if (bVar2 != null) {
                        c += C1957b.m4842b(7, (C2056d) bVar2);
                    }
                    if (!this.f2796i.equals("")) {
                        c += C1957b.m4843b(8, this.f2796i);
                    }
                    C1788a aVar = this.f2797j;
                    if (aVar != null) {
                        c += C1957b.m4842b(9, (C2056d) aVar);
                    }
                    int i = this.f2798k;
                    if (i != 0) {
                        c += C1957b.m4853e(10, i);
                    }
                    int i2 = this.f2799l;
                    return i2 != 0 ? c + C1957b.m4849d(12, i2) : c;
                }
            }

            /* renamed from: d */
            public static C1786d[] m4009d() {
                if (f2784e == null) {
                    synchronized (C2015c.f3328a) {
                        if (f2784e == null) {
                            f2784e = new C1786d[0];
                        }
                    }
                }
                return f2784e;
            }

            public C1786d() {
                mo16681e();
            }

            /* renamed from: e */
            public C1786d mo16681e() {
                this.f2785b = 0;
                this.f2786c = null;
                this.f2787d = C1787a.m4013d();
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                bVar.mo17189a(1, this.f2785b);
                C1792b bVar2 = this.f2786c;
                if (bVar2 != null) {
                    bVar.mo17190a(2, (C2056d) bVar2);
                }
                C1787a[] aVarArr = this.f2787d;
                if (aVarArr != null && aVarArr.length > 0) {
                    int i = 0;
                    while (true) {
                        C1787a[] aVarArr2 = this.f2787d;
                        if (i >= aVarArr2.length) {
                            break;
                        }
                        C1787a aVar = aVarArr2[i];
                        if (aVar != null) {
                            bVar.mo17190a(3, (C2056d) aVar);
                        }
                        i++;
                    }
                }
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                int c = super.mo16676c() + C1957b.m4847c(1, this.f2785b);
                C1792b bVar = this.f2786c;
                if (bVar != null) {
                    c += C1957b.m4842b(2, (C2056d) bVar);
                }
                C1787a[] aVarArr = this.f2787d;
                if (aVarArr != null && aVarArr.length > 0) {
                    int i = 0;
                    while (true) {
                        C1787a[] aVarArr2 = this.f2787d;
                        if (i >= aVarArr2.length) {
                            break;
                        }
                        C1787a aVar = aVarArr2[i];
                        if (aVar != null) {
                            c += C1957b.m4842b(3, (C2056d) aVar);
                        }
                        i++;
                    }
                }
                return c;
            }
        }

        /* renamed from: com.yandex.metrica.c$a$a */
        public static final class C1783a extends C2056d {

            /* renamed from: d */
            private static volatile C1783a[] f2770d;

            /* renamed from: b */
            public String f2771b;

            /* renamed from: c */
            public String f2772c;

            /* renamed from: d */
            public static C1783a[] m3998d() {
                if (f2770d == null) {
                    synchronized (C2015c.f3328a) {
                        if (f2770d == null) {
                            f2770d = new C1783a[0];
                        }
                    }
                }
                return f2770d;
            }

            public C1783a() {
                mo16678e();
            }

            /* renamed from: e */
            public C1783a mo16678e() {
                this.f2771b = "";
                this.f2772c = "";
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                bVar.mo17191a(1, this.f2771b);
                bVar.mo17191a(2, this.f2772c);
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                return super.mo16676c() + C1957b.m4843b(1, this.f2771b) + C1957b.m4843b(2, this.f2772c);
            }
        }

        /* renamed from: com.yandex.metrica.c$a$c */
        public static final class C1785c extends C2056d {

            /* renamed from: d */
            private static volatile C1785c[] f2781d;

            /* renamed from: b */
            public String f2782b;

            /* renamed from: c */
            public String f2783c;

            /* renamed from: d */
            public static C1785c[] m4005d() {
                if (f2781d == null) {
                    synchronized (C2015c.f3328a) {
                        if (f2781d == null) {
                            f2781d = new C1785c[0];
                        }
                    }
                }
                return f2781d;
            }

            public C1785c() {
                mo16680e();
            }

            /* renamed from: e */
            public C1785c mo16680e() {
                this.f2782b = "";
                this.f2783c = "";
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                bVar.mo17191a(1, this.f2782b);
                bVar.mo17191a(2, this.f2783c);
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                return super.mo16676c() + C1957b.m4843b(1, this.f2782b) + C1957b.m4843b(2, this.f2783c);
            }
        }

        /* renamed from: com.yandex.metrica.c$a$e */
        public static final class C1794e extends C2056d {

            /* renamed from: g */
            private static volatile C1794e[] f2828g;

            /* renamed from: b */
            public int f2829b;

            /* renamed from: c */
            public int f2830c;

            /* renamed from: d */
            public String f2831d;

            /* renamed from: e */
            public boolean f2832e;

            /* renamed from: f */
            public String f2833f;

            /* renamed from: d */
            public static C1794e[] m4037d() {
                if (f2828g == null) {
                    synchronized (C2015c.f3328a) {
                        if (f2828g == null) {
                            f2828g = new C1794e[0];
                        }
                    }
                }
                return f2828g;
            }

            public C1794e() {
                mo16689e();
            }

            /* renamed from: e */
            public C1794e mo16689e() {
                this.f2829b = 0;
                this.f2830c = 0;
                this.f2831d = "";
                this.f2832e = false;
                this.f2833f = "";
                this.f3445a = -1;
                return this;
            }

            /* renamed from: a */
            public void mo16675a(C1957b bVar) throws IOException {
                int i = this.f2829b;
                if (i != 0) {
                    bVar.mo17201b(1, i);
                }
                int i2 = this.f2830c;
                if (i2 != 0) {
                    bVar.mo17201b(2, i2);
                }
                if (!this.f2831d.equals("")) {
                    bVar.mo17191a(3, this.f2831d);
                }
                boolean z = this.f2832e;
                if (z) {
                    bVar.mo17192a(4, z);
                }
                if (!this.f2833f.equals("")) {
                    bVar.mo17191a(5, this.f2833f);
                }
                super.mo16675a(bVar);
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public int mo16676c() {
                int c = super.mo16676c();
                int i = this.f2829b;
                if (i != 0) {
                    c += C1957b.m4853e(1, i);
                }
                int i2 = this.f2830c;
                if (i2 != 0) {
                    c += C1957b.m4853e(2, i2);
                }
                if (!this.f2831d.equals("")) {
                    c += C1957b.m4843b(3, this.f2831d);
                }
                if (this.f2832e) {
                    c += C1957b.m4852e(4);
                }
                return !this.f2833f.equals("") ? c + C1957b.m4843b(5, this.f2833f) : c;
            }
        }

        public C1782a() {
            mo16677d();
        }

        /* renamed from: d */
        public C1782a mo16677d() {
            this.f2764b = null;
            this.f2765c = C1786d.m4009d();
            this.f2766d = C1783a.m3998d();
            this.f2767e = C1785c.m4005d();
            this.f2768f = C2140f.f3682a;
            this.f2769g = C1794e.m4037d();
            this.f3445a = -1;
            return this;
        }

        /* renamed from: a */
        public void mo16675a(C1957b bVar) throws IOException {
            C1795f fVar = this.f2764b;
            if (fVar != null) {
                bVar.mo17190a(1, (C2056d) fVar);
            }
            C1786d[] dVarArr = this.f2765c;
            int i = 0;
            if (dVarArr != null && dVarArr.length > 0) {
                int i2 = 0;
                while (true) {
                    C1786d[] dVarArr2 = this.f2765c;
                    if (i2 >= dVarArr2.length) {
                        break;
                    }
                    C1786d dVar = dVarArr2[i2];
                    if (dVar != null) {
                        bVar.mo17190a(3, (C2056d) dVar);
                    }
                    i2++;
                }
            }
            C1783a[] aVarArr = this.f2766d;
            if (aVarArr != null && aVarArr.length > 0) {
                int i3 = 0;
                while (true) {
                    C1783a[] aVarArr2 = this.f2766d;
                    if (i3 >= aVarArr2.length) {
                        break;
                    }
                    C1783a aVar = aVarArr2[i3];
                    if (aVar != null) {
                        bVar.mo17190a(7, (C2056d) aVar);
                    }
                    i3++;
                }
            }
            C1785c[] cVarArr = this.f2767e;
            if (cVarArr != null && cVarArr.length > 0) {
                int i4 = 0;
                while (true) {
                    C1785c[] cVarArr2 = this.f2767e;
                    if (i4 >= cVarArr2.length) {
                        break;
                    }
                    C1785c cVar = cVarArr2[i4];
                    if (cVar != null) {
                        bVar.mo17190a(8, (C2056d) cVar);
                    }
                    i4++;
                }
            }
            String[] strArr = this.f2768f;
            if (strArr != null && strArr.length > 0) {
                int i5 = 0;
                while (true) {
                    String[] strArr2 = this.f2768f;
                    if (i5 >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i5];
                    if (str != null) {
                        bVar.mo17191a(9, str);
                    }
                    i5++;
                }
            }
            C1794e[] eVarArr = this.f2769g;
            if (eVarArr != null && eVarArr.length > 0) {
                while (true) {
                    C1794e[] eVarArr2 = this.f2769g;
                    if (i >= eVarArr2.length) {
                        break;
                    }
                    C1794e eVar = eVarArr2[i];
                    if (eVar != null) {
                        bVar.mo17190a(10, (C2056d) eVar);
                    }
                    i++;
                }
            }
            super.mo16675a(bVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public int mo16676c() {
            int c = super.mo16676c();
            C1795f fVar = this.f2764b;
            if (fVar != null) {
                c += C1957b.m4842b(1, (C2056d) fVar);
            }
            C1786d[] dVarArr = this.f2765c;
            int i = 0;
            if (dVarArr != null && dVarArr.length > 0) {
                int i2 = 0;
                while (true) {
                    C1786d[] dVarArr2 = this.f2765c;
                    if (i2 >= dVarArr2.length) {
                        break;
                    }
                    C1786d dVar = dVarArr2[i2];
                    if (dVar != null) {
                        c += C1957b.m4842b(3, (C2056d) dVar);
                    }
                    i2++;
                }
            }
            C1783a[] aVarArr = this.f2766d;
            if (aVarArr != null && aVarArr.length > 0) {
                int i3 = 0;
                while (true) {
                    C1783a[] aVarArr2 = this.f2766d;
                    if (i3 >= aVarArr2.length) {
                        break;
                    }
                    C1783a aVar = aVarArr2[i3];
                    if (aVar != null) {
                        c += C1957b.m4842b(7, (C2056d) aVar);
                    }
                    i3++;
                }
            }
            C1785c[] cVarArr = this.f2767e;
            if (cVarArr != null && cVarArr.length > 0) {
                int i4 = 0;
                while (true) {
                    C1785c[] cVarArr2 = this.f2767e;
                    if (i4 >= cVarArr2.length) {
                        break;
                    }
                    C1785c cVar = cVarArr2[i4];
                    if (cVar != null) {
                        c += C1957b.m4842b(8, (C2056d) cVar);
                    }
                    i4++;
                }
            }
            String[] strArr = this.f2768f;
            if (strArr != null && strArr.length > 0) {
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (true) {
                    String[] strArr2 = this.f2768f;
                    if (i5 >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i5];
                    if (str != null) {
                        i7++;
                        i6 += C1957b.m4846b(str);
                    }
                    i5++;
                }
                c = c + i6 + (i7 * 1);
            }
            C1794e[] eVarArr = this.f2769g;
            if (eVarArr != null && eVarArr.length > 0) {
                while (true) {
                    C1794e[] eVarArr2 = this.f2769g;
                    if (i >= eVarArr2.length) {
                        break;
                    }
                    C1794e eVar = eVarArr2[i];
                    if (eVar != null) {
                        c += C1957b.m4842b(10, (C2056d) eVar);
                    }
                    i++;
                }
            }
            return c;
        }
    }
}
