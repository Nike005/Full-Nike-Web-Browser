package acr.browser.lightning.utils;

import com.anthonycr.bonsai.Subscription;

public final class SubscriptionUtils {
    private SubscriptionUtils() {
    }

    public static void safeUnsubscribe(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
