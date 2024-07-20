package acr.browser.lightning.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MemoryLeakUtils {
    private static final String TAG = "MemoryLeakUtils";
    private static Method sFinishInputLocked;

    public static abstract class LifecycleAdapter implements Application.ActivityLifecycleCallbacks {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    public static void clearNextServedView(Activity activity, Application application) {
        boolean z;
        Method method;
        if (Build.VERSION.SDK_INT <= 23) {
            InputMethodManager inputMethodManager = (InputMethodManager) application.getSystemService("input_method");
            if (sFinishInputLocked == null) {
                try {
                    sFinishInputLocked = InputMethodManager.class.getDeclaredMethod("finishInputLocked", new Class[0]);
                } catch (NoSuchMethodException e) {
                    Log.d(TAG, "Unable to find method in clearNextServedView", e);
                }
            }
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField("mNextServedView");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(inputMethodManager);
                if ((obj instanceof View) && ((View) obj).getContext() == activity) {
                    z = true;
                    method = sFinishInputLocked;
                    if (method != null && z) {
                        method.setAccessible(true);
                        try {
                            sFinishInputLocked.invoke(inputMethodManager, new Object[0]);
                            return;
                        } catch (Exception e2) {
                            Log.d(TAG, "Unable to invoke method in clearNextServedView", e2);
                            return;
                        }
                    }
                }
            } catch (NoSuchFieldException e3) {
                Log.d(TAG, "Unable to get mNextServedView field", e3);
            } catch (IllegalAccessException e4) {
                Log.d(TAG, "Unable to access mNextServedView field", e4);
            }
            z = false;
            method = sFinishInputLocked;
            if (method != null) {
            }
        }
    }
}
