package com.appnext.core.result;

import com.appnext.core.AppnextAd;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4986p;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.appnext.core.result.c */
public interface C5012c {
    /* renamed from: A */
    String mo40742A();

    /* renamed from: B */
    String mo40743B();

    /* renamed from: C */
    C4986p mo40744C();

    /* renamed from: D */
    C4924Ad mo40745D();

    /* renamed from: E */
    C5010a mo40746E();

    JSONObject getConfigParams() throws JSONException;

    String getPlacementId();

    AppnextAd getSelectedAd();

    /* renamed from: z */
    String mo40750z();
}
