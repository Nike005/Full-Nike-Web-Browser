package dagger.internal;

import acr.browser.lightning.utils.UrlUtils;

public final class Preconditions {
    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static <T> T checkNotNull(T t, String str, Object obj) {
        String str2;
        if (t != null) {
            return t;
        }
        if (!str.contains(UrlUtils.QUERY_PLACE_HOLDER)) {
            throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
        } else if (str.indexOf(UrlUtils.QUERY_PLACE_HOLDER) == str.lastIndexOf(UrlUtils.QUERY_PLACE_HOLDER)) {
            if (obj instanceof Class) {
                str2 = ((Class) obj).getCanonicalName();
            } else {
                str2 = String.valueOf(obj);
            }
            throw new NullPointerException(str.replace(UrlUtils.QUERY_PLACE_HOLDER, str2));
        } else {
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        }
    }

    public static <T> void checkBuilderRequirement(T t, Class<T> cls) {
        if (t == null) {
            throw new IllegalStateException(cls.getCanonicalName() + " must be set");
        }
    }

    private Preconditions() {
    }
}
