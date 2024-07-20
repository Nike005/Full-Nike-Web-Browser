package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NotificationOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<NotificationOptions> CREATOR = new zzq();
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;
    /* access modifiers changed from: private */
    public static final List<String> zzmc = Arrays.asList(new String[]{MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING});
    /* access modifiers changed from: private */
    public static final int[] zzmd = {0, 1};
    private final List<String> zzme;
    private final int[] zzmf;
    private final long zzmg;
    private final String zzmh;
    private final int zzmi;
    private final int zzmj;
    private final int zzmk;
    private final int zzml;
    private final int zzmm;
    private final int zzmn;
    private final int zzmo;
    private final int zzmp;
    private final int zzmq;
    private final int zzmr;
    private final int zzms;
    private final int zzmt;
    private final int zzmu;
    private final int zzmv;
    private final int zzmw;
    private final int zzmx;
    private final int zzmy;
    private final int zzmz;
    private final int zzna;
    private final int zznb;
    private final int zznc;
    private final int zznd;
    private final int zzne;
    private final int zznf;
    private final int zzng;
    private final int zznh;
    private final int zzni;
    private final zzf zznj;

    public static final class Builder {
        private List<String> zzme = NotificationOptions.zzmc;
        private int[] zzmf = NotificationOptions.zzmd;
        private long zzmg = 10000;
        private String zzmh;
        private int zzmi = C0069R.C0070drawable.cast_ic_notification_small_icon;
        private int zzmj = C0069R.C0070drawable.cast_ic_notification_stop_live_stream;
        private int zzmk = C0069R.C0070drawable.cast_ic_notification_pause;
        private int zzml = C0069R.C0070drawable.cast_ic_notification_play;
        private int zzmm = C0069R.C0070drawable.cast_ic_notification_skip_next;
        private int zzmn = C0069R.C0070drawable.cast_ic_notification_skip_prev;
        private int zzmo = C0069R.C0070drawable.cast_ic_notification_forward;
        private int zzmp = C0069R.C0070drawable.cast_ic_notification_forward10;
        private int zzmq = C0069R.C0070drawable.cast_ic_notification_forward30;
        private int zzmr = C0069R.C0070drawable.cast_ic_notification_rewind;
        private int zzms = C0069R.C0070drawable.cast_ic_notification_rewind10;
        private int zzmt = C0069R.C0070drawable.cast_ic_notification_rewind30;
        private int zzmu = C0069R.C0070drawable.cast_ic_notification_disconnect;
        private NotificationActionsProvider zznk;

        public final NotificationOptions build() {
            NotificationActionsProvider notificationActionsProvider = this.zznk;
            NotificationOptions notificationOptions = r2;
            NotificationOptions notificationOptions2 = new NotificationOptions(this.zzme, this.zzmf, this.zzmg, this.zzmh, this.zzmi, this.zzmj, this.zzmk, this.zzml, this.zzmm, this.zzmn, this.zzmo, this.zzmp, this.zzmq, this.zzmr, this.zzms, this.zzmt, this.zzmu, C0069R.dimen.cast_notification_image_size, C0069R.string.cast_casting_to_device, C0069R.string.cast_stop_live_stream, C0069R.string.cast_pause, C0069R.string.cast_play, C0069R.string.cast_skip_next, C0069R.string.cast_skip_prev, C0069R.string.cast_forward, C0069R.string.cast_forward_10, C0069R.string.cast_forward_30, C0069R.string.cast_rewind, C0069R.string.cast_rewind_10, C0069R.string.cast_rewind_30, C0069R.string.cast_disconnect, notificationActionsProvider == null ? null : notificationActionsProvider.zzbi().asBinder());
            return notificationOptions;
        }

        public final Builder setActions(List<String> list, int[] iArr) {
            if (list == null && iArr != null) {
                throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
            } else if (list == null || iArr != null) {
                if (list == null || iArr == null) {
                    this.zzme = NotificationOptions.zzmc;
                    this.zzmf = NotificationOptions.zzmd;
                } else {
                    int size = list.size();
                    if (iArr.length <= size) {
                        for (int i : iArr) {
                            if (i < 0 || i >= size) {
                                throw new IllegalArgumentException(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(size - 1)}));
                            }
                        }
                        this.zzme = new ArrayList(list);
                        this.zzmf = Arrays.copyOf(iArr, iArr.length);
                    } else {
                        throw new IllegalArgumentException(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", new Object[]{Integer.valueOf(iArr.length), Integer.valueOf(size)}));
                    }
                }
                return this;
            } else {
                throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
            }
        }

        public final Builder setDisconnectDrawableResId(int i) {
            this.zzmu = i;
            return this;
        }

        public final Builder setForward10DrawableResId(int i) {
            this.zzmp = i;
            return this;
        }

        public final Builder setForward30DrawableResId(int i) {
            this.zzmq = i;
            return this;
        }

        public final Builder setForwardDrawableResId(int i) {
            this.zzmo = i;
            return this;
        }

        public final Builder setNotificationActionsProvider(NotificationActionsProvider notificationActionsProvider) {
            if (notificationActionsProvider != null) {
                this.zznk = notificationActionsProvider;
                return this;
            }
            throw new IllegalArgumentException("notificationActionsProvider cannot be null.");
        }

        public final Builder setPauseDrawableResId(int i) {
            this.zzmk = i;
            return this;
        }

        public final Builder setPlayDrawableResId(int i) {
            this.zzml = i;
            return this;
        }

        public final Builder setRewind10DrawableResId(int i) {
            this.zzms = i;
            return this;
        }

        public final Builder setRewind30DrawableResId(int i) {
            this.zzmt = i;
            return this;
        }

        public final Builder setRewindDrawableResId(int i) {
            this.zzmr = i;
            return this;
        }

        public final Builder setSkipNextDrawableResId(int i) {
            this.zzmm = i;
            return this;
        }

        public final Builder setSkipPrevDrawableResId(int i) {
            this.zzmn = i;
            return this;
        }

        public final Builder setSkipStepMs(long j) {
            Preconditions.checkArgument(j > 0, "skipStepMs must be positive.");
            this.zzmg = j;
            return this;
        }

        public final Builder setSmallIconDrawableResId(int i) {
            this.zzmi = i;
            return this;
        }

        public final Builder setStopLiveStreamDrawableResId(int i) {
            this.zzmj = i;
            return this;
        }

        public final Builder setTargetActivityClassName(String str) {
            this.zzmh = str;
            return this;
        }
    }

    /* JADX WARNING: type inference failed for: r1v31, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NotificationOptions(java.util.List<java.lang.String> r7, int[] r8, long r9, java.lang.String r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, int r35, int r36, int r37, int r38, android.os.IBinder r39) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r39
            r6.<init>()
            r4 = 0
            if (r1 == 0) goto L_0x0013
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r7)
            r0.zzme = r5
            goto L_0x0015
        L_0x0013:
            r0.zzme = r4
        L_0x0015:
            if (r2 == 0) goto L_0x001f
            int r1 = r2.length
            int[] r1 = java.util.Arrays.copyOf(r8, r1)
            r0.zzmf = r1
            goto L_0x0021
        L_0x001f:
            r0.zzmf = r4
        L_0x0021:
            r1 = r9
            r0.zzmg = r1
            r1 = r11
            r0.zzmh = r1
            r1 = r12
            r0.zzmi = r1
            r1 = r13
            r0.zzmj = r1
            r1 = r14
            r0.zzmk = r1
            r1 = r15
            r0.zzml = r1
            r1 = r16
            r0.zzmm = r1
            r1 = r17
            r0.zzmn = r1
            r1 = r18
            r0.zzmo = r1
            r1 = r19
            r0.zzmp = r1
            r1 = r20
            r0.zzmq = r1
            r1 = r21
            r0.zzmr = r1
            r1 = r22
            r0.zzms = r1
            r1 = r23
            r0.zzmt = r1
            r1 = r24
            r0.zzmu = r1
            r1 = r25
            r0.zzmv = r1
            r1 = r26
            r0.zzmw = r1
            r1 = r27
            r0.zzmx = r1
            r1 = r28
            r0.zzmy = r1
            r1 = r29
            r0.zzmz = r1
            r1 = r30
            r0.zzna = r1
            r1 = r31
            r0.zznb = r1
            r1 = r32
            r0.zznc = r1
            r1 = r33
            r0.zznd = r1
            r1 = r34
            r0.zzne = r1
            r1 = r35
            r0.zznf = r1
            r1 = r36
            r0.zzng = r1
            r1 = r37
            r0.zznh = r1
            r1 = r38
            r0.zzni = r1
            if (r3 != 0) goto L_0x0092
            goto L_0x00a5
        L_0x0092:
            java.lang.String r1 = "com.google.android.gms.cast.framework.media.INotificationActionsProvider"
            android.os.IInterface r1 = r3.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.cast.framework.media.zzf
            if (r2 == 0) goto L_0x00a0
            r4 = r1
            com.google.android.gms.cast.framework.media.zzf r4 = (com.google.android.gms.cast.framework.media.zzf) r4
            goto L_0x00a5
        L_0x00a0:
            com.google.android.gms.cast.framework.media.zzh r4 = new com.google.android.gms.cast.framework.media.zzh
            r4.<init>(r3)
        L_0x00a5:
            r0.zznj = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.NotificationOptions.<init>(java.util.List, int[], long, java.lang.String, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, android.os.IBinder):void");
    }

    public List<String> getActions() {
        return this.zzme;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzmw;
    }

    public int[] getCompatActionIndices() {
        int[] iArr = this.zzmf;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public int getDisconnectDrawableResId() {
        return this.zzmu;
    }

    public int getForward10DrawableResId() {
        return this.zzmp;
    }

    public int getForward30DrawableResId() {
        return this.zzmq;
    }

    public int getForwardDrawableResId() {
        return this.zzmo;
    }

    public int getPauseDrawableResId() {
        return this.zzmk;
    }

    public int getPlayDrawableResId() {
        return this.zzml;
    }

    public int getRewind10DrawableResId() {
        return this.zzms;
    }

    public int getRewind30DrawableResId() {
        return this.zzmt;
    }

    public int getRewindDrawableResId() {
        return this.zzmr;
    }

    public int getSkipNextDrawableResId() {
        return this.zzmm;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzmn;
    }

    public long getSkipStepMs() {
        return this.zzmg;
    }

    public int getSmallIconDrawableResId() {
        return this.zzmi;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzmj;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzmx;
    }

    public String getTargetActivityClassName() {
        return this.zzmh;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 2, getActions(), false);
        SafeParcelWriter.writeIntArray(parcel, 3, getCompatActionIndices(), false);
        SafeParcelWriter.writeLong(parcel, 4, getSkipStepMs());
        SafeParcelWriter.writeString(parcel, 5, getTargetActivityClassName(), false);
        SafeParcelWriter.writeInt(parcel, 6, getSmallIconDrawableResId());
        SafeParcelWriter.writeInt(parcel, 7, getStopLiveStreamDrawableResId());
        SafeParcelWriter.writeInt(parcel, 8, getPauseDrawableResId());
        SafeParcelWriter.writeInt(parcel, 9, getPlayDrawableResId());
        SafeParcelWriter.writeInt(parcel, 10, getSkipNextDrawableResId());
        SafeParcelWriter.writeInt(parcel, 11, getSkipPrevDrawableResId());
        SafeParcelWriter.writeInt(parcel, 12, getForwardDrawableResId());
        SafeParcelWriter.writeInt(parcel, 13, getForward10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 14, getForward30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 15, getRewindDrawableResId());
        SafeParcelWriter.writeInt(parcel, 16, getRewind10DrawableResId());
        SafeParcelWriter.writeInt(parcel, 17, getRewind30DrawableResId());
        SafeParcelWriter.writeInt(parcel, 18, getDisconnectDrawableResId());
        SafeParcelWriter.writeInt(parcel, 19, this.zzmv);
        SafeParcelWriter.writeInt(parcel, 20, getCastingToDeviceStringResId());
        SafeParcelWriter.writeInt(parcel, 21, getStopLiveStreamTitleResId());
        SafeParcelWriter.writeInt(parcel, 22, this.zzmy);
        SafeParcelWriter.writeInt(parcel, 23, this.zzmz);
        SafeParcelWriter.writeInt(parcel, 24, this.zzna);
        SafeParcelWriter.writeInt(parcel, 25, this.zznb);
        SafeParcelWriter.writeInt(parcel, 26, this.zznc);
        SafeParcelWriter.writeInt(parcel, 27, this.zznd);
        SafeParcelWriter.writeInt(parcel, 28, this.zzne);
        SafeParcelWriter.writeInt(parcel, 29, this.zznf);
        SafeParcelWriter.writeInt(parcel, 30, this.zzng);
        SafeParcelWriter.writeInt(parcel, 31, this.zznh);
        SafeParcelWriter.writeInt(parcel, 32, this.zzni);
        zzf zzf = this.zznj;
        SafeParcelWriter.writeIBinder(parcel, 33, zzf == null ? null : zzf.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
