package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public final class CommonUtils {
    public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }

    public static <T> T[] emptyIfNull(T[] tArr) {
        return tArr == null ? (Object[]) new Object[0] : tArr;
    }

    public static String convertListToSpaceDelimitedString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            String str = "";
            for (String next : list) {
                if (!TextUtils.isEmpty(next)) {
                    sb.append(str);
                    sb.append(next);
                    str = StringUtils.SPACE;
                }
            }
        }
        return sb.toString();
    }
}
