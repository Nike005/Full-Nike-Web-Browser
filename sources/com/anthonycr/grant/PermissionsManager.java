package com.anthonycr.grant;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PermissionsManager {
    private static final String TAG = PermissionsManager.class.getSimpleName();
    private static PermissionsManager mInstance = null;
    private final List<WeakReference<PermissionsResultAction>> mPendingActions = new ArrayList(1);
    private final Set<String> mPendingRequests = new HashSet(1);
    private final Set<String> mPermissions = new HashSet(1);

    public static PermissionsManager getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionsManager();
        }
        return mInstance;
    }

    private PermissionsManager() {
        initializePermissionsMap();
    }

    private synchronized void initializePermissionsMap() {
        for (Field field : Manifest.permission.class.getFields()) {
            String str = null;
            try {
                str = (String) field.get("");
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Could not access field", e);
            }
            this.mPermissions.add(str);
        }
    }

    private synchronized String[] getManifestPermissions(Activity activity) {
        ArrayList arrayList;
        String[] strArr;
        PackageInfo packageInfo = null;
        arrayList = new ArrayList(1);
        try {
            Log.d(TAG, activity.getPackageName());
            packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "A problem occurred when retrieving permissions", e);
        }
        if (!(packageInfo == null || (strArr = packageInfo.requestedPermissions) == null)) {
            for (String str : strArr) {
                Log.d(TAG, "Manifest contained permission: " + str);
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private synchronized void addPendingAction(String[] strArr, PermissionsResultAction permissionsResultAction) {
        if (permissionsResultAction != null) {
            permissionsResultAction.registerPermissions(strArr);
            this.mPendingActions.add(new WeakReference(permissionsResultAction));
        }
    }

    private synchronized void removePendingAction(PermissionsResultAction permissionsResultAction) {
        Iterator<WeakReference<PermissionsResultAction>> it = this.mPendingActions.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (next.get() == permissionsResultAction || next.get() == null) {
                it.remove();
            }
        }
    }

    public synchronized boolean hasPermission(Context context, String str) {
        boolean z;
        if (context != null) {
            if (ActivityCompat.checkSelfPermission(context, str) == 0 || !this.mPermissions.contains(str)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean hasAllPermissions(Context context, String[] strArr) {
        if (context == null) {
            return false;
        }
        boolean z = true;
        for (String hasPermission : strArr) {
            z &= hasPermission(context, hasPermission);
        }
        return z;
    }

    public synchronized void requestAllManifestPermissionsIfNecessary(Activity activity, PermissionsResultAction permissionsResultAction) {
        if (activity != null) {
            requestPermissionsIfNecessaryForResult(activity, getManifestPermissions(activity), permissionsResultAction);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void requestPermissionsIfNecessaryForResult(android.app.Activity r3, java.lang.String[] r4, com.anthonycr.grant.PermissionsResultAction r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            r2.addPendingAction(r4, r5)     // Catch:{ all -> 0x0037 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0037 }
            r1 = 23
            if (r0 >= r1) goto L_0x0012
            r2.doPermissionWorkBeforeAndroidM(r3, r4, r5)     // Catch:{ all -> 0x0037 }
            goto L_0x0035
        L_0x0012:
            java.util.List r4 = r2.getPermissionsListToRequest(r3, r4, r5)     // Catch:{ all -> 0x0037 }
            boolean r0 = r4.isEmpty()     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0020
            r2.removePendingAction(r5)     // Catch:{ all -> 0x0037 }
            goto L_0x0035
        L_0x0020:
            int r5 = r4.size()     // Catch:{ all -> 0x0037 }
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ all -> 0x0037 }
            java.lang.Object[] r5 = r4.toArray(r5)     // Catch:{ all -> 0x0037 }
            java.lang.String[] r5 = (java.lang.String[]) r5     // Catch:{ all -> 0x0037 }
            java.util.Set<java.lang.String> r0 = r2.mPendingRequests     // Catch:{ all -> 0x0037 }
            r0.addAll(r4)     // Catch:{ all -> 0x0037 }
            r4 = 1
            androidx.core.app.ActivityCompat.requestPermissions(r3, r5, r4)     // Catch:{ all -> 0x0037 }
        L_0x0035:
            monitor-exit(r2)
            return
        L_0x0037:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anthonycr.grant.PermissionsManager.requestPermissionsIfNecessaryForResult(android.app.Activity, java.lang.String[], com.anthonycr.grant.PermissionsResultAction):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void requestPermissionsIfNecessaryForResult(androidx.fragment.app.Fragment r4, java.lang.String[] r5, com.anthonycr.grant.PermissionsResultAction r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()     // Catch:{ all -> 0x003b }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            r3.addPendingAction(r5, r6)     // Catch:{ all -> 0x003b }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003b }
            r2 = 23
            if (r1 >= r2) goto L_0x0016
            r3.doPermissionWorkBeforeAndroidM(r0, r5, r6)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0016:
            java.util.List r5 = r3.getPermissionsListToRequest(r0, r5, r6)     // Catch:{ all -> 0x003b }
            boolean r0 = r5.isEmpty()     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0024
            r3.removePendingAction(r6)     // Catch:{ all -> 0x003b }
            goto L_0x0039
        L_0x0024:
            int r6 = r5.size()     // Catch:{ all -> 0x003b }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ all -> 0x003b }
            java.lang.Object[] r6 = r5.toArray(r6)     // Catch:{ all -> 0x003b }
            java.lang.String[] r6 = (java.lang.String[]) r6     // Catch:{ all -> 0x003b }
            java.util.Set<java.lang.String> r0 = r3.mPendingRequests     // Catch:{ all -> 0x003b }
            r0.addAll(r5)     // Catch:{ all -> 0x003b }
            r5 = 1
            r4.requestPermissions(r6, r5)     // Catch:{ all -> 0x003b }
        L_0x0039:
            monitor-exit(r3)
            return
        L_0x003b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anthonycr.grant.PermissionsManager.requestPermissionsIfNecessaryForResult(androidx.fragment.app.Fragment, java.lang.String[], com.anthonycr.grant.PermissionsResultAction):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        r1.remove();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void notifyPermissionsChange(java.lang.String[] r7, int[] r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            int r0 = r7.length     // Catch:{ all -> 0x0043 }
            int r1 = r8.length     // Catch:{ all -> 0x0043 }
            if (r1 >= r0) goto L_0x0006
            int r0 = r8.length     // Catch:{ all -> 0x0043 }
        L_0x0006:
            java.util.List<java.lang.ref.WeakReference<com.anthonycr.grant.PermissionsResultAction>> r1 = r6.mPendingActions     // Catch:{ all -> 0x0043 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0043 }
        L_0x000c:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0043 }
            r3 = 0
            if (r2 == 0) goto L_0x0035
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0043 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0043 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0043 }
            com.anthonycr.grant.PermissionsResultAction r2 = (com.anthonycr.grant.PermissionsResultAction) r2     // Catch:{ all -> 0x0043 }
        L_0x001f:
            if (r3 >= r0) goto L_0x000c
            if (r2 == 0) goto L_0x0031
            r4 = r7[r3]     // Catch:{ all -> 0x0043 }
            r5 = r8[r3]     // Catch:{ all -> 0x0043 }
            boolean r4 = r2.onResult((java.lang.String) r4, (int) r5)     // Catch:{ all -> 0x0043 }
            if (r4 == 0) goto L_0x002e
            goto L_0x0031
        L_0x002e:
            int r3 = r3 + 1
            goto L_0x001f
        L_0x0031:
            r1.remove()     // Catch:{ all -> 0x0043 }
            goto L_0x000c
        L_0x0035:
            if (r3 >= r0) goto L_0x0041
            java.util.Set<java.lang.String> r8 = r6.mPendingRequests     // Catch:{ all -> 0x0043 }
            r1 = r7[r3]     // Catch:{ all -> 0x0043 }
            r8.remove(r1)     // Catch:{ all -> 0x0043 }
            int r3 = r3 + 1
            goto L_0x0035
        L_0x0041:
            monitor-exit(r6)
            return
        L_0x0043:
            r7 = move-exception
            monitor-exit(r6)
            goto L_0x0047
        L_0x0046:
            throw r7
        L_0x0047:
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.anthonycr.grant.PermissionsManager.notifyPermissionsChange(java.lang.String[], int[]):void");
    }

    private void doPermissionWorkBeforeAndroidM(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        for (String str : strArr) {
            if (permissionsResultAction != null) {
                if (!this.mPermissions.contains(str)) {
                    permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
                } else if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                    permissionsResultAction.onResult(str, Permissions.DENIED);
                } else {
                    permissionsResultAction.onResult(str, Permissions.GRANTED);
                }
            }
        }
    }

    private List<String> getPermissionsListToRequest(Activity activity, String[] strArr, PermissionsResultAction permissionsResultAction) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (!this.mPermissions.contains(str)) {
                if (permissionsResultAction != null) {
                    permissionsResultAction.onResult(str, Permissions.NOT_FOUND);
                }
            } else if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                if (!this.mPendingRequests.contains(str)) {
                    arrayList.add(str);
                }
            } else if (permissionsResultAction != null) {
                permissionsResultAction.onResult(str, Permissions.GRANTED);
            }
        }
        return arrayList;
    }
}
