package com.startapp.android.publish.adsCommon.p034g.p038d;

import com.startapp.common.p043a.C1270g;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* renamed from: com.startapp.android.publish.adsCommon.g.d.b */
/* compiled from: StartAppSDK */
public class C1147b {
    /* renamed from: a */
    public static Map<String, String> m1565a(String str) {
        C1270g.m2078a("MraidParser", 3, "parseCommandUrl " + str);
        String substring = str.substring(8);
        HashMap hashMap = new HashMap();
        int indexOf = substring.indexOf(63);
        if (indexOf != -1) {
            String substring2 = substring.substring(0, indexOf);
            for (String str2 : substring.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61);
                hashMap.put(str2.substring(0, indexOf2), str2.substring(indexOf2 + 1));
            }
            substring = substring2;
        }
        if (!m1567b(substring)) {
            C1270g.m2076a(5, "command " + substring + " is unknown");
            return null;
        } else if (!m1566a(substring, hashMap)) {
            C1270g.m2076a(5, "command URL " + str + " is missing parameters");
            return null;
        } else {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("command", substring);
            hashMap2.putAll(hashMap);
            return hashMap2;
        }
    }

    /* renamed from: b */
    public static boolean m1567b(String str) {
        return Arrays.asList(new String[]{"close", "createCalendarEvent", "expand", AbstractCircuitBreaker.PROPERTY_NAME, "playVideo", "resize", "setOrientationProperties", "setResizeProperties", "storePicture", "useCustomClose"}).contains(str);
    }

    /* renamed from: a */
    private static boolean m1566a(String str, Map<String, String> map) {
        if (str.equals("createCalendarEvent")) {
            return map.containsKey("eventJSON");
        }
        if (str.equals(AbstractCircuitBreaker.PROPERTY_NAME) || str.equals("playVideo") || str.equals("storePicture")) {
            return map.containsKey("url");
        }
        if (str.equals("setOrientationProperties")) {
            if (!map.containsKey("allowOrientationChange") || !map.containsKey("forceOrientation")) {
                return false;
            }
            return true;
        } else if (str.equals("setResizeProperties")) {
            if (!map.containsKey("width") || !map.containsKey("height") || !map.containsKey("offsetX") || !map.containsKey("offsetY") || !map.containsKey("customClosePosition") || !map.containsKey("allowOffscreen")) {
                return false;
            }
            return true;
        } else if (str.equals("useCustomClose")) {
            return map.containsKey("useCustomClose");
        } else {
            return true;
        }
    }
}
