package androidx.media2;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import java.util.List;

public interface IMediaController2 extends IInterface {
    void onAllowedCommandsChanged(ParcelImpl parcelImpl) throws RemoteException;

    void onBufferingStateChanged(ParcelImpl parcelImpl, int i, long j) throws RemoteException;

    void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException;

    void onConnected(IMediaSession2 iMediaSession2, ParcelImpl parcelImpl, int i, ParcelImpl parcelImpl2, long j, long j2, float f, long j3, ParcelImpl parcelImpl3, int i2, int i3, List<ParcelImpl> list, PendingIntent pendingIntent) throws RemoteException;

    void onCurrentMediaItemChanged(ParcelImpl parcelImpl) throws RemoteException;

    void onCustomCommand(ParcelImpl parcelImpl, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException;

    void onCustomLayoutChanged(List<ParcelImpl> list) throws RemoteException;

    void onDisconnected() throws RemoteException;

    void onError(int i, Bundle bundle) throws RemoteException;

    void onGetChildrenDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RemoteException;

    void onGetItemDone(String str, ParcelImpl parcelImpl) throws RemoteException;

    void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException;

    void onGetSearchResultDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RemoteException;

    void onPlaybackInfoChanged(ParcelImpl parcelImpl) throws RemoteException;

    void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException;

    void onPlayerStateChanged(long j, long j2, int i) throws RemoteException;

    void onPlaylistChanged(List<ParcelImpl> list, Bundle bundle) throws RemoteException;

    void onPlaylistMetadataChanged(Bundle bundle) throws RemoteException;

    void onRepeatModeChanged(int i) throws RemoteException;

    void onRoutesInfoChanged(List<Bundle> list) throws RemoteException;

    void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException;

    void onSeekCompleted(long j, long j2, long j3) throws RemoteException;

    void onShuffleModeChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController2 {
        private static final String DESCRIPTOR = "androidx.media2.IMediaController2";
        static final int TRANSACTION_onAllowedCommandsChanged = 16;
        static final int TRANSACTION_onBufferingStateChanged = 4;
        static final int TRANSACTION_onChildrenChanged = 20;
        static final int TRANSACTION_onConnected = 13;
        static final int TRANSACTION_onCurrentMediaItemChanged = 1;
        static final int TRANSACTION_onCustomCommand = 17;
        static final int TRANSACTION_onCustomLayoutChanged = 15;
        static final int TRANSACTION_onDisconnected = 14;
        static final int TRANSACTION_onError = 11;
        static final int TRANSACTION_onGetChildrenDone = 21;
        static final int TRANSACTION_onGetItemDone = 19;
        static final int TRANSACTION_onGetLibraryRootDone = 18;
        static final int TRANSACTION_onGetSearchResultDone = 23;
        static final int TRANSACTION_onPlaybackInfoChanged = 7;
        static final int TRANSACTION_onPlaybackSpeedChanged = 3;
        static final int TRANSACTION_onPlayerStateChanged = 2;
        static final int TRANSACTION_onPlaylistChanged = 5;
        static final int TRANSACTION_onPlaylistMetadataChanged = 6;
        static final int TRANSACTION_onRepeatModeChanged = 8;
        static final int TRANSACTION_onRoutesInfoChanged = 12;
        static final int TRANSACTION_onSearchResultChanged = 22;
        static final int TRANSACTION_onSeekCompleted = 10;
        static final int TRANSACTION_onShuffleModeChanged = 9;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaController2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaController2)) {
                return new Proxy(iBinder);
            }
            return (IMediaController2) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v41, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v46, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r2v1 */
        /* JADX WARNING: type inference failed for: r2v2, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v7, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v17, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v26, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v29, types: [android.os.ResultReceiver] */
        /* JADX WARNING: type inference failed for: r2v37, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r2v51 */
        /* JADX WARNING: type inference failed for: r2v52 */
        /* JADX WARNING: type inference failed for: r2v53 */
        /* JADX WARNING: type inference failed for: r2v54 */
        /* JADX WARNING: type inference failed for: r2v55 */
        /* JADX WARNING: type inference failed for: r2v56 */
        /* JADX WARNING: type inference failed for: r2v57 */
        /* JADX WARNING: type inference failed for: r2v58 */
        /* JADX WARNING: type inference failed for: r2v59 */
        /* JADX WARNING: type inference failed for: r2v60 */
        /* JADX WARNING: type inference failed for: r2v61 */
        /* JADX WARNING: type inference failed for: r2v62 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r22, android.os.Parcel r23, android.os.Parcel r24, int r25) throws android.os.RemoteException {
            /*
                r21 = this;
                r15 = r21
                r0 = r22
                r1 = r23
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r17 = 1
                java.lang.String r3 = "androidx.media2.IMediaController2"
                if (r0 == r2) goto L_0x02f2
                r2 = 0
                switch(r0) {
                    case 1: goto L_0x02db;
                    case 2: goto L_0x02c2;
                    case 3: goto L_0x02a9;
                    case 4: goto L_0x028a;
                    case 5: goto L_0x026d;
                    case 6: goto L_0x0256;
                    case 7: goto L_0x023f;
                    case 8: goto L_0x0233;
                    case 9: goto L_0x0227;
                    case 10: goto L_0x020e;
                    case 11: goto L_0x01f3;
                    case 12: goto L_0x01e4;
                    case 13: goto L_0x015a;
                    case 14: goto L_0x0153;
                    case 15: goto L_0x0146;
                    case 16: goto L_0x0130;
                    case 17: goto L_0x00fa;
                    case 18: goto L_0x00d0;
                    case 19: goto L_0x00b6;
                    case 20: goto L_0x0098;
                    case 21: goto L_0x0067;
                    case 22: goto L_0x0049;
                    case 23: goto L_0x0018;
                    default: goto L_0x0013;
                }
            L_0x0013:
                boolean r0 = super.onTransact(r22, r23, r24, r25)
                return r0
            L_0x0018:
                r1.enforceInterface(r3)
                java.lang.String r3 = r23.readString()
                int r4 = r23.readInt()
                int r5 = r23.readInt()
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r6 = r1.createTypedArrayList(r0)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x003d
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x003e
            L_0x003d:
                r7 = r2
            L_0x003e:
                r0 = r21
                r1 = r3
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r7
                r0.onGetSearchResultDone(r1, r2, r3, r4, r5)
                return r17
            L_0x0049:
                r1.enforceInterface(r3)
                java.lang.String r0 = r23.readString()
                int r3 = r23.readInt()
                int r4 = r23.readInt()
                if (r4 == 0) goto L_0x0063
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0063:
                r15.onSearchResultChanged(r0, r3, r2)
                return r17
            L_0x0067:
                r1.enforceInterface(r3)
                java.lang.String r3 = r23.readString()
                int r4 = r23.readInt()
                int r5 = r23.readInt()
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r6 = r1.createTypedArrayList(r0)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x008c
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x008d
            L_0x008c:
                r7 = r2
            L_0x008d:
                r0 = r21
                r1 = r3
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r7
                r0.onGetChildrenDone(r1, r2, r3, r4, r5)
                return r17
            L_0x0098:
                r1.enforceInterface(r3)
                java.lang.String r0 = r23.readString()
                int r3 = r23.readInt()
                int r4 = r23.readInt()
                if (r4 == 0) goto L_0x00b2
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00b2:
                r15.onChildrenChanged(r0, r3, r2)
                return r17
            L_0x00b6:
                r1.enforceInterface(r3)
                java.lang.String r0 = r23.readString()
                int r3 = r23.readInt()
                if (r3 == 0) goto L_0x00cc
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r2 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x00cc:
                r15.onGetItemDone(r0, r2)
                return r17
            L_0x00d0:
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x00e2
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                android.os.Bundle r0 = (android.os.Bundle) r0
                goto L_0x00e3
            L_0x00e2:
                r0 = r2
            L_0x00e3:
                java.lang.String r3 = r23.readString()
                int r4 = r23.readInt()
                if (r4 == 0) goto L_0x00f6
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00f6:
                r15.onGetLibraryRootDone(r0, r3, r2)
                return r17
            L_0x00fa:
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x010c
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                goto L_0x010d
            L_0x010c:
                r0 = r2
            L_0x010d:
                int r3 = r23.readInt()
                if (r3 == 0) goto L_0x011c
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r1)
                android.os.Bundle r3 = (android.os.Bundle) r3
                goto L_0x011d
            L_0x011c:
                r3 = r2
            L_0x011d:
                int r4 = r23.readInt()
                if (r4 == 0) goto L_0x012c
                android.os.Parcelable$Creator r2 = android.os.ResultReceiver.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.ResultReceiver r2 = (android.os.ResultReceiver) r2
            L_0x012c:
                r15.onCustomCommand(r0, r3, r2)
                return r17
            L_0x0130:
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0142
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r2 = r0
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0142:
                r15.onAllowedCommandsChanged(r2)
                return r17
            L_0x0146:
                r1.enforceInterface(r3)
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r0 = r1.createTypedArrayList(r0)
                r15.onCustomLayoutChanged(r0)
                return r17
            L_0x0153:
                r1.enforceInterface(r3)
                r21.onDisconnected()
                return r17
            L_0x015a:
                r1.enforceInterface(r3)
                android.os.IBinder r0 = r23.readStrongBinder()
                androidx.media2.IMediaSession2 r3 = androidx.media2.IMediaSession2.Stub.asInterface(r0)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0175
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r4 = r0
                goto L_0x0176
            L_0x0175:
                r4 = r2
            L_0x0176:
                int r5 = r23.readInt()
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x018a
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r6 = r0
                goto L_0x018b
            L_0x018a:
                r6 = r2
            L_0x018b:
                long r7 = r23.readLong()
                long r9 = r23.readLong()
                float r11 = r23.readFloat()
                long r12 = r23.readLong()
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x01ab
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
                r14 = r0
                goto L_0x01ac
            L_0x01ab:
                r14 = r2
            L_0x01ac:
                int r16 = r23.readInt()
                int r18 = r23.readInt()
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r19 = r1.createTypedArrayList(r0)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x01cb
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r20 = r0
                goto L_0x01cd
            L_0x01cb:
                r20 = r2
            L_0x01cd:
                r0 = r21
                r1 = r3
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r7
                r7 = r9
                r9 = r11
                r10 = r12
                r12 = r14
                r13 = r16
                r14 = r18
                r15 = r19
                r16 = r20
                r0.onConnected(r1, r2, r3, r4, r5, r7, r9, r10, r12, r13, r14, r15, r16)
                return r17
            L_0x01e4:
                r1.enforceInterface(r3)
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.util.ArrayList r0 = r1.createTypedArrayList(r0)
                r7 = r21
                r7.onRoutesInfoChanged(r0)
                return r17
            L_0x01f3:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                int r3 = r23.readInt()
                if (r3 == 0) goto L_0x020a
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x020a:
                r7.onError(r0, r2)
                return r17
            L_0x020e:
                r7 = r15
                r1.enforceInterface(r3)
                long r2 = r23.readLong()
                long r4 = r23.readLong()
                long r8 = r23.readLong()
                r0 = r21
                r1 = r2
                r3 = r4
                r5 = r8
                r0.onSeekCompleted(r1, r3, r5)
                return r17
            L_0x0227:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                r7.onShuffleModeChanged(r0)
                return r17
            L_0x0233:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                r7.onRepeatModeChanged(r0)
                return r17
            L_0x023f:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0252
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r2 = r0
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x0252:
                r7.onPlaybackInfoChanged(r2)
                return r17
            L_0x0256:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0269
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r2 = r0
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0269:
                r7.onPlaylistMetadataChanged(r2)
                return r17
            L_0x026d:
                r7 = r15
                r1.enforceInterface(r3)
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r0 = r1.createTypedArrayList(r0)
                int r3 = r23.readInt()
                if (r3 == 0) goto L_0x0286
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0286:
                r7.onPlaylistChanged(r0, r2)
                return r17
            L_0x028a:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x029d
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r2 = r0
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x029d:
                int r0 = r23.readInt()
                long r3 = r23.readLong()
                r7.onBufferingStateChanged(r2, r0, r3)
                return r17
            L_0x02a9:
                r7 = r15
                r1.enforceInterface(r3)
                long r2 = r23.readLong()
                long r4 = r23.readLong()
                float r6 = r23.readFloat()
                r0 = r21
                r1 = r2
                r3 = r4
                r5 = r6
                r0.onPlaybackSpeedChanged(r1, r3, r5)
                return r17
            L_0x02c2:
                r7 = r15
                r1.enforceInterface(r3)
                long r2 = r23.readLong()
                long r4 = r23.readLong()
                int r6 = r23.readInt()
                r0 = r21
                r1 = r2
                r3 = r4
                r5 = r6
                r0.onPlayerStateChanged(r1, r3, r5)
                return r17
            L_0x02db:
                r7 = r15
                r1.enforceInterface(r3)
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x02ee
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r0 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r2 = r0
                androidx.versionedparcelable.ParcelImpl r2 = (androidx.versionedparcelable.ParcelImpl) r2
            L_0x02ee:
                r7.onCurrentMediaItemChanged(r2)
                return r17
            L_0x02f2:
                r0 = r24
                r7 = r15
                r0.writeString(r3)
                return r17
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.IMediaController2.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaController2 {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void onCurrentMediaItemChanged(ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlayerStateChanged(long j, long j2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeFloat(f);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onBufferingStateChanged(ParcelImpl parcelImpl, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaylistChanged(List<ParcelImpl> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaylistMetadataChanged(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPlaybackInfoChanged(ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRepeatModeChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onShuffleModeChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSeekCompleted(long j, long j2, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeLong(j3);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onError(int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRoutesInfoChanged(List<Bundle> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onConnected(IMediaSession2 iMediaSession2, ParcelImpl parcelImpl, int i, ParcelImpl parcelImpl2, long j, long j2, float f, long j3, ParcelImpl parcelImpl3, int i2, int i3, List<ParcelImpl> list, PendingIntent pendingIntent) throws RemoteException {
                ParcelImpl parcelImpl4 = parcelImpl;
                ParcelImpl parcelImpl5 = parcelImpl2;
                ParcelImpl parcelImpl6 = parcelImpl3;
                PendingIntent pendingIntent2 = pendingIntent;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaSession2 != null ? iMediaSession2.asBinder() : null);
                    if (parcelImpl4 != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    int i4 = i;
                    obtain.writeInt(i);
                    if (parcelImpl5 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    long j4 = j;
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j3);
                    if (parcelImpl6 != null) {
                        obtain.writeInt(1);
                        parcelImpl6.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeTypedList(list);
                    if (pendingIntent2 != null) {
                        obtain.writeInt(1);
                        pendingIntent2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        this.mRemote.transact(13, obtain, (Parcel) null, 1);
                        obtain.recycle();
                    } catch (Throwable th) {
                        th = th;
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obtain.recycle();
                    throw th;
                }
            }

            public void onDisconnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomLayoutChanged(List<ParcelImpl> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAllowedCommandsChanged(ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCustomCommand(ParcelImpl parcelImpl, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (resultReceiver != null) {
                        obtain.writeInt(1);
                        resultReceiver.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onGetItemDone(String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onGetChildrenDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(21, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onGetSearchResultDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeTypedList(list);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
