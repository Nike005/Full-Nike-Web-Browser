package acr.browser.lightning.utils;

public class Preconditions {
    public static void checkNonNull(Object obj) {
        if (obj == null) {
            throw new RuntimeException("Object must not be null");
        }
    }
}
