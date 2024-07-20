package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.fr */
public class C2162fr extends Exception {

    /* renamed from: com.yandex.metrica.impl.ob.fr$a */
    public enum C2163a {
        DEFAULT,
        AUTH,
        NETWORK,
        NO_CONNECTION,
        PARSE,
        SERVER,
        TIMEOUT
    }

    public C2162fr() {
    }

    public C2162fr(byte b) {
    }

    public C2162fr(Throwable th) {
        super(th);
    }
}
