package com.yandex.metrica.impl;

import android.os.Bundle;
import com.yandex.metrica.CounterConfiguration;

/* renamed from: com.yandex.metrica.impl.bf */
public class C1889bf extends C1864aw {

    /* renamed from: e */
    private final String f3091e;

    public C1889bf(String str) {
        this.f3091e = str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public Bundle mo16889c() {
        Bundle c = super.mo16889c();
        CounterConfiguration counterConfiguration = new CounterConfiguration(mo16887b());
        counterConfiguration.mo16573b(this.f3091e);
        c.putParcelable("COUNTER_MIGRATION_CFG_OBJ", counterConfiguration);
        return c;
    }
}
