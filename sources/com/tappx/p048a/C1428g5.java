package com.tappx.p048a;

import android.content.Intent;

/* renamed from: com.tappx.a.g5 */
public class C1428g5 extends C1718z5 {

    /* renamed from: b */
    private Intent f1882b;

    public C1428g5(C1590q5 q5Var) {
        super(q5Var);
    }

    public String getMessage() {
        if (this.f1882b != null) {
            return "User needs to (re)enter credentials.";
        }
        return super.getMessage();
    }
}
