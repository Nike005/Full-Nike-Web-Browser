package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@17.3.0 */
final class zabg implements Runnable {
    private final /* synthetic */ zabh zaa;

    zabg(zabh zabh) {
        this.zaa = zabh;
    }

    public final void run() {
        this.zaa.zaa.zac.disconnect(String.valueOf(this.zaa.zaa.zad.getClass().getName()).concat(" disconnecting because it was signed out."));
    }
}
