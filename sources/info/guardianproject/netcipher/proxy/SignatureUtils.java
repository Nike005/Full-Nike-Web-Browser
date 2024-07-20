package info.guardianproject.netcipher.proxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SignatureUtils {
    public static String getOwnSignatureHash(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        return getSignatureHash(context, context.getPackageName());
    }

    public static String getSignatureHash(Context context, String str) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        return toHexStringWithColons(MessageDigest.getInstance("SHA-256").digest(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray()));
    }

    public static String toHexStringWithColons(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[((bArr.length * 3) - 1)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 3;
            cArr2[i2] = cArr[b / 16];
            cArr2[i2 + 1] = cArr[b % 16];
            if (i < bArr.length - 1) {
                cArr2[i2 + 2] = ':';
            }
        }
        return new String(cArr2);
    }

    public static Intent validateBroadcastIntent(Context context, Intent intent, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return validateBroadcastIntent(context, intent, (List<String>) arrayList, z);
    }

    public static Intent validateBroadcastIntent(Context context, Intent intent, List<String> list, boolean z) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo next : queryBroadcastReceivers) {
                try {
                    if (list.contains(getSignatureHash(context, next.activityInfo.packageName))) {
                        return new Intent(intent).setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                    } else if (z) {
                        throw new SecurityException("Package has signature hash mismatch: " + next.activityInfo.packageName);
                    }
                } catch (NoSuchAlgorithmException e) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e2);
                }
            }
        }
        return null;
    }

    public static Intent validateActivityIntent(Context context, Intent intent, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return validateActivityIntent(context, intent, (List<String>) arrayList, z);
    }

    public static Intent validateActivityIntent(Context context, Intent intent, List<String> list, boolean z) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities != null) {
            for (ResolveInfo next : queryIntentActivities) {
                try {
                    if (list.contains(getSignatureHash(context, next.activityInfo.packageName))) {
                        return new Intent(intent).setComponent(new ComponentName(next.activityInfo.packageName, next.activityInfo.name));
                    } else if (z) {
                        throw new SecurityException("Package has signature hash mismatch: " + next.activityInfo.packageName);
                    }
                } catch (NoSuchAlgorithmException e) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e2);
                }
            }
        }
        return null;
    }

    public static Intent validateServiceIntent(Context context, Intent intent, String str, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return validateServiceIntent(context, intent, (List<String>) arrayList, z);
    }

    public static Intent validateServiceIntent(Context context, Intent intent, List<String> list, boolean z) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null) {
            for (ResolveInfo next : queryIntentServices) {
                try {
                    if (list.contains(getSignatureHash(context, next.serviceInfo.packageName))) {
                        return new Intent(intent).setComponent(new ComponentName(next.serviceInfo.packageName, next.serviceInfo.name));
                    } else if (z) {
                        throw new SecurityException("Package has signature hash mismatch: " + next.activityInfo.packageName);
                    }
                } catch (NoSuchAlgorithmException e) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e);
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.w("SignatureUtils", "Exception when computing signature hash", e2);
                }
            }
        }
        return null;
    }
}
