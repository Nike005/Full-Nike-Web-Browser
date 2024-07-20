package com.firebase.jobdispatcher;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;

final class GooglePlayCallbackExtractor {
    private static final String BUNDLE_KEY_CALLBACK = "callback";
    private static final int BUNDLE_MAGIC = 1279544898;
    private static final String ERROR_INVALID_CALLBACK = "Bad callback received, terminating";
    private static final String ERROR_NULL_CALLBACK = "No callback received, terminating";
    private static final String PENDING_CALLBACK_CLASS = "com.google.android.gms.gcm.PendingCallback";
    private static final String TAG = "FJD.GooglePlayReceiver";
    private static final int VAL_PARCELABLE = 4;
    private static Boolean shouldReadKeysAsStringsCached;

    GooglePlayCallbackExtractor() {
    }

    public Pair<JobCallback, Bundle> extractCallback(Bundle bundle) {
        if (bundle != null) {
            return extractWrappedBinderFromParcel(bundle);
        }
        Log.e(TAG, ERROR_NULL_CALLBACK);
        return null;
    }

    private Pair<JobCallback, Bundle> extractWrappedBinderFromParcel(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Parcel parcel = toParcel(bundle);
        try {
            if (parcel.readInt() <= 0) {
                Log.w(TAG, ERROR_NULL_CALLBACK);
                return null;
            } else if (parcel.readInt() != BUNDLE_MAGIC) {
                Log.w(TAG, ERROR_NULL_CALLBACK);
                parcel.recycle();
                return null;
            } else {
                int readInt = parcel.readInt();
                GooglePlayJobCallback googlePlayJobCallback = null;
                for (int i = 0; i < readInt; i++) {
                    String readKey = readKey(parcel);
                    if (readKey != null) {
                        if (googlePlayJobCallback == null) {
                            if (BUNDLE_KEY_CALLBACK.equals(readKey)) {
                                if (parcel.readInt() != 4) {
                                    Log.w(TAG, ERROR_INVALID_CALLBACK);
                                    parcel.recycle();
                                    return null;
                                } else if (!PENDING_CALLBACK_CLASS.equals(parcel.readString())) {
                                    Log.w(TAG, ERROR_INVALID_CALLBACK);
                                    parcel.recycle();
                                    return null;
                                } else {
                                    googlePlayJobCallback = new GooglePlayJobCallback(parcel.readStrongBinder());
                                }
                            }
                        }
                        Object readValue = parcel.readValue((ClassLoader) null);
                        if (readValue instanceof String) {
                            bundle2.putString(readKey, (String) readValue);
                        } else if (readValue instanceof Boolean) {
                            bundle2.putBoolean(readKey, ((Boolean) readValue).booleanValue());
                        } else if (readValue instanceof Integer) {
                            bundle2.putInt(readKey, ((Integer) readValue).intValue());
                        } else if (readValue instanceof ArrayList) {
                            bundle2.putParcelableArrayList(readKey, (ArrayList) readValue);
                        } else if (readValue instanceof Bundle) {
                            bundle2.putBundle(readKey, (Bundle) readValue);
                        } else if (readValue instanceof Parcelable) {
                            bundle2.putParcelable(readKey, (Parcelable) readValue);
                        }
                    }
                }
                if (googlePlayJobCallback == null) {
                    Log.w(TAG, ERROR_NULL_CALLBACK);
                    parcel.recycle();
                    return null;
                }
                Pair<JobCallback, Bundle> create = Pair.create(googlePlayJobCallback, bundle2);
                parcel.recycle();
                return create;
            }
        } finally {
            parcel.recycle();
        }
    }

    private static Parcel toParcel(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        bundle.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        return obtain;
    }

    private String readKey(Parcel parcel) {
        if (shouldReadKeysAsStrings()) {
            return parcel.readString();
        }
        Object readValue = parcel.readValue((ClassLoader) null);
        if (readValue instanceof String) {
            return (String) readValue;
        }
        Log.w(TAG, ERROR_INVALID_CALLBACK);
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        shouldReadKeysAsStringsCached = java.lang.Boolean.FALSE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r1.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005c, code lost:
        throw r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0054 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized boolean shouldReadKeysAsStrings() {
        /*
            java.lang.Class<com.firebase.jobdispatcher.GooglePlayCallbackExtractor> r0 = com.firebase.jobdispatcher.GooglePlayCallbackExtractor.class
            monitor-enter(r0)
            java.lang.Boolean r1 = shouldReadKeysAsStringsCached     // Catch:{ all -> 0x0065 }
            if (r1 != 0) goto L_0x005d
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ all -> 0x0065 }
            r1.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r2 = "key"
            java.lang.String r3 = "value"
            r1.putString(r2, r3)     // Catch:{ all -> 0x0065 }
            android.os.Parcel r1 = toParcel(r1)     // Catch:{ all -> 0x0065 }
            int r2 = r1.readInt()     // Catch:{ RuntimeException -> 0x0054 }
            r3 = 0
            r4 = 1
            if (r2 <= 0) goto L_0x0021
            r2 = 1
            goto L_0x0022
        L_0x0021:
            r2 = 0
        L_0x0022:
            checkCondition(r2)     // Catch:{ RuntimeException -> 0x0054 }
            int r2 = r1.readInt()     // Catch:{ RuntimeException -> 0x0054 }
            r5 = 1279544898(0x4c444e42, float:5.146036E7)
            if (r2 != r5) goto L_0x0030
            r2 = 1
            goto L_0x0031
        L_0x0030:
            r2 = 0
        L_0x0031:
            checkCondition(r2)     // Catch:{ RuntimeException -> 0x0054 }
            int r2 = r1.readInt()     // Catch:{ RuntimeException -> 0x0054 }
            if (r2 != r4) goto L_0x003b
            r3 = 1
        L_0x003b:
            checkCondition(r3)     // Catch:{ RuntimeException -> 0x0054 }
            java.lang.String r2 = "key"
            java.lang.String r3 = r1.readString()     // Catch:{ RuntimeException -> 0x0054 }
            boolean r2 = r2.equals(r3)     // Catch:{ RuntimeException -> 0x0054 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ RuntimeException -> 0x0054 }
            shouldReadKeysAsStringsCached = r2     // Catch:{ RuntimeException -> 0x0054 }
        L_0x004e:
            r1.recycle()     // Catch:{ all -> 0x0065 }
            goto L_0x005d
        L_0x0052:
            r2 = move-exception
            goto L_0x0059
        L_0x0054:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0052 }
            shouldReadKeysAsStringsCached = r2     // Catch:{ all -> 0x0052 }
            goto L_0x004e
        L_0x0059:
            r1.recycle()     // Catch:{ all -> 0x0065 }
            throw r2     // Catch:{ all -> 0x0065 }
        L_0x005d:
            java.lang.Boolean r1 = shouldReadKeysAsStringsCached     // Catch:{ all -> 0x0065 }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x0065 }
            monitor-exit(r0)
            return r1
        L_0x0065:
            r1 = move-exception
            monitor-exit(r0)
            goto L_0x0069
        L_0x0068:
            throw r1
        L_0x0069:
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.jobdispatcher.GooglePlayCallbackExtractor.shouldReadKeysAsStrings():boolean");
    }

    private static void checkCondition(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }
}
