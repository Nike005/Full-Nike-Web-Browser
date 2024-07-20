package com.tappx.p048a;

import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* renamed from: com.tappx.a.h4 */
public enum C1438h4 {
    CLOSE("close"),
    EXPAND("expand") {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return b4Var == C1328b4.INLINE;
        }
    },
    USE_CUSTOM_CLOSE("usecustomclose"),
    OPEN(AbstractCircuitBreaker.PROPERTY_NAME) {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return true;
        }
    },
    RESIZE("resize") {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return true;
        }
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO("playVideo") {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return b4Var == C1328b4.INLINE;
        }
    },
    STORE_PICTURE("storePicture") {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return true;
        }
    },
    CREATE_CALENDAR_EVENT("createCalendarEvent") {
        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo15848a(C1328b4 b4Var) {
            return true;
        }
    },
    UNSPECIFIED("");
    

    /* renamed from: a */
    private final String f1914a;

    /* renamed from: a */
    static C1438h4 m2794a(String str) {
        for (C1438h4 h4Var : values()) {
            if (h4Var.f1914a.equals(str)) {
                return h4Var;
            }
        }
        return UNSPECIFIED;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15848a(C1328b4 b4Var) {
        return false;
    }

    private C1438h4(String str) {
        this.f1914a = str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo15847a() {
        return this.f1914a;
    }
}
