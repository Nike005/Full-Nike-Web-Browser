package com.tappx.p048a;

import com.tappx.sdk.android.AdRequest;

/* renamed from: com.tappx.a.k0 */
public class C1482k0 {

    /* renamed from: a */
    private static final String f1988a = null;

    /* renamed from: b */
    private static final String f1989b = null;

    /* renamed from: com.tappx.a.k0$a */
    static /* synthetic */ class C1483a {

        /* renamed from: a */
        static final /* synthetic */ int[] f1990a;

        /* renamed from: b */
        static final /* synthetic */ int[] f1991b;

        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006e */
        static {
            /*
                com.tappx.sdk.android.AdRequest$MaritalStatus[] r0 = com.tappx.sdk.android.AdRequest.MaritalStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1991b = r0
                r1 = 1
                com.tappx.sdk.android.AdRequest$MaritalStatus r2 = com.tappx.sdk.android.AdRequest.MaritalStatus.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f1991b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.sdk.android.AdRequest$MaritalStatus r3 = com.tappx.sdk.android.AdRequest.MaritalStatus.LIVING_COMMON     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = f1991b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.sdk.android.AdRequest$MaritalStatus r4 = com.tappx.sdk.android.AdRequest.MaritalStatus.MARRIED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = f1991b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.sdk.android.AdRequest$MaritalStatus r5 = com.tappx.sdk.android.AdRequest.MaritalStatus.DIVORCED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r4 = f1991b     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.sdk.android.AdRequest$MaritalStatus r5 = com.tappx.sdk.android.AdRequest.MaritalStatus.WIDOWED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r6 = 5
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r4 = f1991b     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.sdk.android.AdRequest$MaritalStatus r5 = com.tappx.sdk.android.AdRequest.MaritalStatus.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6 = 6
                r4[r5] = r6     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.tappx.sdk.android.AdRequest$Gender[] r4 = com.tappx.sdk.android.AdRequest.Gender.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f1990a = r4
                com.tappx.sdk.android.AdRequest$Gender r5 = com.tappx.sdk.android.AdRequest.Gender.MALE     // Catch:{ NoSuchFieldError -> 0x005a }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = f1990a     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.tappx.sdk.android.AdRequest$Gender r4 = com.tappx.sdk.android.AdRequest.Gender.FEMALE     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = f1990a     // Catch:{ NoSuchFieldError -> 0x006e }
                com.tappx.sdk.android.AdRequest$Gender r1 = com.tappx.sdk.android.AdRequest.Gender.OTHER     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = f1990a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.tappx.sdk.android.AdRequest$Gender r1 = com.tappx.sdk.android.AdRequest.Gender.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1482k0.C1483a.<clinit>():void");
        }
    }

    /* renamed from: a */
    public C1325b2 mo15893a(String str, C1654v1 v1Var, String str2, AdRequest adRequest) {
        C1325b2 b2Var = new C1325b2();
        b2Var.mo15552a(str2);
        b2Var.mo15556b(v1Var.mo16228a());
        b2Var.mo15558c(str);
        b2Var.mo15568h(adRequest.getSdkType());
        b2Var.mo15566g(adRequest.getMediator());
        b2Var.mo15562e(adRequest.getKeywords());
        b2Var.mo15555b(adRequest.getYearOfBirth());
        b2Var.mo15551a(adRequest.getAge());
        b2Var.mo15560d(m2900a(adRequest.getGender()));
        b2Var.mo15564f(m2901a(adRequest.getMaritalStatus()));
        b2Var.mo15553a(adRequest.isUseTestAds());
        return b2Var;
    }

    /* renamed from: a */
    private String m2900a(AdRequest.Gender gender) {
        if (gender == null) {
            return null;
        }
        int i = C1483a.f1990a[gender.ordinal()];
        if (i == 1) {
            return "M";
        }
        if (i != 2) {
            return i != 3 ? f1988a : "O";
        }
        return "F";
    }

    /* renamed from: a */
    private String m2901a(AdRequest.MaritalStatus maritalStatus) {
        if (maritalStatus == null) {
            return null;
        }
        int i = C1483a.f1991b[maritalStatus.ordinal()];
        if (i == 1) {
            return "S";
        }
        if (i == 2) {
            return "L";
        }
        if (i == 3) {
            return "M";
        }
        if (i != 4) {
            return i != 5 ? f1989b : "W";
        }
        return "D";
    }
}
