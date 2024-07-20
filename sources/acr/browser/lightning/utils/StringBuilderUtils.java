package acr.browser.lightning.utils;

public class StringBuilderUtils {
    private static final String EMPTY = "";
    private static final String SPACE = " ";

    public static void replace(StringBuilder sb, String str, String str2) {
        int indexOf = sb.indexOf(str);
        if (indexOf >= 0) {
            sb.replace(indexOf, str.length() + indexOf, str2);
        }
    }

    public static void trim(StringBuilder sb) {
        while (sb.indexOf(" ") == 0) {
            sb.replace(0, 1, "");
        }
        while (sb.lastIndexOf(" ") == sb.length() - 1) {
            sb.replace(sb.length() - 1, sb.length(), "");
        }
    }

    public static boolean isEmpty(StringBuilder sb) {
        return sb.length() == 0;
    }

    public static boolean startsWith(StringBuilder sb, String str) {
        return sb.indexOf(str) == 0;
    }

    public static boolean contains(StringBuilder sb, String str) {
        return sb.indexOf(str) >= 0;
    }

    public static boolean equals(StringBuilder sb, String str) {
        int length = sb.length();
        if (length != str.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (sb.charAt(i) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder substring(StringBuilder sb, int i, int i2) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.replace(i2, sb.length(), "");
        sb2.replace(0, i, "");
        return sb2;
    }
}
