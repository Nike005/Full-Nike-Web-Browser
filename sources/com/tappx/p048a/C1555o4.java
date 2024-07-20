package com.tappx.p048a;

import java.util.regex.Pattern;

/* renamed from: com.tappx.a.o4 */
public class C1555o4 {

    /* renamed from: a */
    static String f2150a = "<script\\s+[^>]*\\bsrc\\s*=\\s*([\\\"\\'])mraid\\.js\\1[^>]*>\\s*</script>\\n*";

    /* renamed from: b */
    static String f2151b = "<script src=\"mraid.js\"></script>";

    /* renamed from: a */
    public static boolean m3177a(String str) {
        return Pattern.compile(f2150a, 2).matcher(str).find();
    }
}
