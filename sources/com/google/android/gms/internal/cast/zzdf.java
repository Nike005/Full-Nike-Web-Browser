package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public final class zzdf {
    public static final Api.ClientKey<zzcn> zzwd = new Api.ClientKey<>();
    public static final Api.ClientKey<zzea> zzwe = new Api.ClientKey<>();
    public static final Api.ClientKey<zzdz> zzwf = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzwg = new Api.ClientKey<>();
    private static final Api.ClientKey<Object> zzwh = new Api.ClientKey<>();
    private static final Charset zzwi;
    private static final String zzwj = zzcu.zzp("com.google.cast.multizone");

    static {
        Charset charset;
        try {
            charset = Charset.forName("UTF-8");
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            charset = null;
        }
        zzwi = charset;
    }
}
