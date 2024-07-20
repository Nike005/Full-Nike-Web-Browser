package com.anthonycr.grant;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class PermissionsResultAction {
    private static final String TAG = PermissionsResultAction.class.getSimpleName();
    private Looper mLooper = Looper.getMainLooper();
    private final Set<String> mPermissions = new HashSet(1);

    public abstract void onDenied(String str);

    public abstract void onGranted();

    public PermissionsResultAction() {
    }

    public PermissionsResultAction(Looper looper) {
        this.mLooper = looper;
    }

    public synchronized boolean shouldIgnorePermissionNotFound(String str) {
        String str2 = TAG;
        Log.d(str2, "Permission not found: " + str);
        return true;
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean onResult(String str, int i) {
        if (i == 0) {
            return onResult(str, Permissions.GRANTED);
        }
        return onResult(str, Permissions.DENIED);
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean onResult(final String str, Permissions permissions) {
        this.mPermissions.remove(str);
        if (permissions == Permissions.GRANTED) {
            if (this.mPermissions.isEmpty()) {
                new Handler(this.mLooper).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onGranted();
                    }
                });
                return true;
            }
        } else if (permissions == Permissions.DENIED) {
            new Handler(this.mLooper).post(new Runnable() {
                public void run() {
                    PermissionsResultAction.this.onDenied(str);
                }
            });
            return true;
        } else if (permissions == Permissions.NOT_FOUND) {
            if (!shouldIgnorePermissionNotFound(str)) {
                new Handler(this.mLooper).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onDenied(str);
                    }
                });
                return true;
            } else if (this.mPermissions.isEmpty()) {
                new Handler(this.mLooper).post(new Runnable() {
                    public void run() {
                        PermissionsResultAction.this.onGranted();
                    }
                });
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final synchronized void registerPermissions(String[] strArr) {
        Collections.addAll(this.mPermissions, strArr);
    }
}
