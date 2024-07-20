package androidx.media2;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ResultReceiver;
import androidx.versionedparcelable.ParcelImpl;
import java.util.List;

public interface IMediaSession2 extends IInterface {
    void addPlaylistItem(IMediaController2 iMediaController2, int i, ParcelImpl parcelImpl) throws RemoteException;

    void adjustVolume(IMediaController2 iMediaController2, int i, int i2) throws RemoteException;

    void connect(IMediaController2 iMediaController2, String str) throws RemoteException;

    void fastForward(IMediaController2 iMediaController2) throws RemoteException;

    void getChildren(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) throws RemoteException;

    void getItem(IMediaController2 iMediaController2, String str) throws RemoteException;

    void getLibraryRoot(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException;

    void getSearchResult(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) throws RemoteException;

    void pause(IMediaController2 iMediaController2) throws RemoteException;

    void play(IMediaController2 iMediaController2) throws RemoteException;

    void playFromMediaId(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void playFromSearch(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void playFromUri(IMediaController2 iMediaController2, Uri uri, Bundle bundle) throws RemoteException;

    void prepare(IMediaController2 iMediaController2) throws RemoteException;

    void prepareFromMediaId(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(IMediaController2 iMediaController2, Uri uri, Bundle bundle) throws RemoteException;

    void release(IMediaController2 iMediaController2) throws RemoteException;

    void removePlaylistItem(IMediaController2 iMediaController2, ParcelImpl parcelImpl) throws RemoteException;

    void replacePlaylistItem(IMediaController2 iMediaController2, int i, ParcelImpl parcelImpl) throws RemoteException;

    void reset(IMediaController2 iMediaController2) throws RemoteException;

    void rewind(IMediaController2 iMediaController2) throws RemoteException;

    void search(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void seekTo(IMediaController2 iMediaController2, long j) throws RemoteException;

    void selectRoute(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException;

    void sendCustomCommand(IMediaController2 iMediaController2, ParcelImpl parcelImpl, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException;

    void setPlaybackSpeed(IMediaController2 iMediaController2, float f) throws RemoteException;

    void setPlaylist(IMediaController2 iMediaController2, List<ParcelImpl> list, Bundle bundle) throws RemoteException;

    void setRating(IMediaController2 iMediaController2, String str, ParcelImpl parcelImpl) throws RemoteException;

    void setRepeatMode(IMediaController2 iMediaController2, int i) throws RemoteException;

    void setShuffleMode(IMediaController2 iMediaController2, int i) throws RemoteException;

    void setVolumeTo(IMediaController2 iMediaController2, int i, int i2) throws RemoteException;

    void skipToNextItem(IMediaController2 iMediaController2) throws RemoteException;

    void skipToPlaylistItem(IMediaController2 iMediaController2, ParcelImpl parcelImpl) throws RemoteException;

    void skipToPreviousItem(IMediaController2 iMediaController2) throws RemoteException;

    void subscribe(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException;

    void subscribeRoutesInfo(IMediaController2 iMediaController2) throws RemoteException;

    void unsubscribe(IMediaController2 iMediaController2, String str) throws RemoteException;

    void unsubscribeRoutesInfo(IMediaController2 iMediaController2) throws RemoteException;

    void updatePlaylistMetadata(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession2 {
        private static final String DESCRIPTOR = "androidx.media2.IMediaSession2";
        static final int TRANSACTION_addPlaylistItem = 23;
        static final int TRANSACTION_adjustVolume = 4;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_fastForward = 9;
        static final int TRANSACTION_getChildren = 36;
        static final int TRANSACTION_getItem = 35;
        static final int TRANSACTION_getLibraryRoot = 34;
        static final int TRANSACTION_getSearchResult = 38;
        static final int TRANSACTION_pause = 6;
        static final int TRANSACTION_play = 5;
        static final int TRANSACTION_playFromMediaId = 18;
        static final int TRANSACTION_playFromSearch = 17;
        static final int TRANSACTION_playFromUri = 16;
        static final int TRANSACTION_prepare = 8;
        static final int TRANSACTION_prepareFromMediaId = 15;
        static final int TRANSACTION_prepareFromSearch = 14;
        static final int TRANSACTION_prepareFromUri = 13;
        static final int TRANSACTION_release = 2;
        static final int TRANSACTION_removePlaylistItem = 24;
        static final int TRANSACTION_replacePlaylistItem = 25;
        static final int TRANSACTION_reset = 7;
        static final int TRANSACTION_rewind = 10;
        static final int TRANSACTION_search = 37;
        static final int TRANSACTION_seekTo = 11;
        static final int TRANSACTION_selectRoute = 33;
        static final int TRANSACTION_sendCustomCommand = 12;
        static final int TRANSACTION_setPlaybackSpeed = 20;
        static final int TRANSACTION_setPlaylist = 21;
        static final int TRANSACTION_setRating = 19;
        static final int TRANSACTION_setRepeatMode = 29;
        static final int TRANSACTION_setShuffleMode = 30;
        static final int TRANSACTION_setVolumeTo = 3;
        static final int TRANSACTION_skipToNextItem = 28;
        static final int TRANSACTION_skipToPlaylistItem = 26;
        static final int TRANSACTION_skipToPreviousItem = 27;
        static final int TRANSACTION_subscribe = 39;
        static final int TRANSACTION_subscribeRoutesInfo = 31;
        static final int TRANSACTION_unsubscribe = 40;
        static final int TRANSACTION_unsubscribeRoutesInfo = 32;
        static final int TRANSACTION_updatePlaylistMetadata = 22;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMediaSession2 asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession2)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession2) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v54, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v60, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [android.os.ResultReceiver] */
        /* JADX WARNING: type inference failed for: r0v24, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v33, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v36, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v39, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v42, types: [androidx.versionedparcelable.ParcelImpl] */
        /* JADX WARNING: type inference failed for: r0v51 */
        /* JADX WARNING: type inference failed for: r0v57 */
        /* JADX WARNING: type inference failed for: r0v63 */
        /* JADX WARNING: type inference failed for: r0v64 */
        /* JADX WARNING: type inference failed for: r0v65 */
        /* JADX WARNING: type inference failed for: r0v66 */
        /* JADX WARNING: type inference failed for: r0v67 */
        /* JADX WARNING: type inference failed for: r0v68 */
        /* JADX WARNING: type inference failed for: r0v69 */
        /* JADX WARNING: type inference failed for: r0v70 */
        /* JADX WARNING: type inference failed for: r0v71 */
        /* JADX WARNING: type inference failed for: r0v72 */
        /* JADX WARNING: type inference failed for: r0v73 */
        /* JADX WARNING: type inference failed for: r0v74 */
        /* JADX WARNING: type inference failed for: r0v75 */
        /* JADX WARNING: type inference failed for: r0v76 */
        /* JADX WARNING: type inference failed for: r0v77 */
        /* JADX WARNING: type inference failed for: r0v78 */
        /* JADX WARNING: type inference failed for: r0v79 */
        /* JADX WARNING: type inference failed for: r0v80 */
        /* JADX WARNING: type inference failed for: r0v81 */
        /* JADX WARNING: type inference failed for: r0v82 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                java.lang.String r2 = "androidx.media2.IMediaSession2"
                if (r9 == r0) goto L_0x0447
                r0 = 0
                switch(r9) {
                    case 1: goto L_0x0434;
                    case 2: goto L_0x0425;
                    case 3: goto L_0x040e;
                    case 4: goto L_0x03f7;
                    case 5: goto L_0x03e8;
                    case 6: goto L_0x03d9;
                    case 7: goto L_0x03ca;
                    case 8: goto L_0x03bb;
                    case 9: goto L_0x03ac;
                    case 10: goto L_0x039d;
                    case 11: goto L_0x038a;
                    case 12: goto L_0x034c;
                    case 13: goto L_0x031e;
                    case 14: goto L_0x02fc;
                    case 15: goto L_0x02da;
                    case 16: goto L_0x02ac;
                    case 17: goto L_0x028a;
                    case 18: goto L_0x0268;
                    case 19: goto L_0x0246;
                    case 20: goto L_0x0233;
                    case 21: goto L_0x020f;
                    case 22: goto L_0x01f1;
                    case 23: goto L_0x01cf;
                    case 24: goto L_0x01b1;
                    case 25: goto L_0x018f;
                    case 26: goto L_0x0171;
                    case 27: goto L_0x0162;
                    case 28: goto L_0x0153;
                    case 29: goto L_0x0140;
                    case 30: goto L_0x012d;
                    case 31: goto L_0x011e;
                    case 32: goto L_0x010f;
                    case 33: goto L_0x00f1;
                    case 34: goto L_0x00d3;
                    case 35: goto L_0x00c0;
                    case 36: goto L_0x0094;
                    case 37: goto L_0x0072;
                    case 38: goto L_0x0046;
                    case 39: goto L_0x0024;
                    case 40: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r9 = super.onTransact(r9, r10, r11, r12)
                return r9
            L_0x0011:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r10 = r10.readString()
                r8.unsubscribe(r9, r10)
                return r1
            L_0x0024:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0042
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0042:
                r8.subscribe(r9, r11, r0)
                return r1
            L_0x0046:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r3 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r4 = r10.readString()
                int r5 = r10.readInt()
                int r6 = r10.readInt()
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x006c
                android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x006c:
                r7 = r0
                r2 = r8
                r2.getSearchResult(r3, r4, r5, r6, r7)
                return r1
            L_0x0072:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0090
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0090:
                r8.search(r9, r11, r0)
                return r1
            L_0x0094:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r3 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r4 = r10.readString()
                int r5 = r10.readInt()
                int r6 = r10.readInt()
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x00ba
                android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00ba:
                r7 = r0
                r2 = r8
                r2.getChildren(r3, r4, r5, r6, r7)
                return r1
            L_0x00c0:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r10 = r10.readString()
                r8.getItem(r9, r10)
                return r1
            L_0x00d3:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x00ed
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00ed:
                r8.getLibraryRoot(r9, r0)
                return r1
            L_0x00f1:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x010b
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x010b:
                r8.selectRoute(r9, r0)
                return r1
            L_0x010f:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.unsubscribeRoutesInfo(r9)
                return r1
            L_0x011e:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.subscribeRoutesInfo(r9)
                return r1
            L_0x012d:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r10 = r10.readInt()
                r8.setShuffleMode(r9, r10)
                return r1
            L_0x0140:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r10 = r10.readInt()
                r8.setRepeatMode(r9, r10)
                return r1
            L_0x0153:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.skipToNextItem(r9)
                return r1
            L_0x0162:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.skipToPreviousItem(r9)
                return r1
            L_0x0171:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x018b
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r11 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x018b:
                r8.skipToPlaylistItem(r9, r0)
                return r1
            L_0x018f:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x01ad
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r12 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x01ad:
                r8.replacePlaylistItem(r9, r11, r0)
                return r1
            L_0x01b1:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x01cb
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r11 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x01cb:
                r8.removePlaylistItem(r9, r0)
                return r1
            L_0x01cf:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x01ed
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r12 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x01ed:
                r8.addPlaylistItem(r9, r11, r0)
                return r1
            L_0x01f1:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x020b
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x020b:
                r8.updatePlaylistMetadata(r9, r0)
                return r1
            L_0x020f:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r11 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.util.ArrayList r11 = r10.createTypedArrayList(r11)
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x022f
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x022f:
                r8.setPlaylist(r9, r11, r0)
                return r1
            L_0x0233:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                float r10 = r10.readFloat()
                r8.setPlaybackSpeed(r9, r10)
                return r1
            L_0x0246:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0264
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r12 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                androidx.versionedparcelable.ParcelImpl r0 = (androidx.versionedparcelable.ParcelImpl) r0
            L_0x0264:
                r8.setRating(r9, r11, r0)
                return r1
            L_0x0268:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0286
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0286:
                r8.playFromMediaId(r9, r11, r0)
                return r1
            L_0x028a:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x02a8
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x02a8:
                r8.playFromSearch(r9, r11, r0)
                return r1
            L_0x02ac:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x02c6
                android.os.Parcelable$Creator r11 = android.net.Uri.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                android.net.Uri r11 = (android.net.Uri) r11
                goto L_0x02c7
            L_0x02c6:
                r11 = r0
            L_0x02c7:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x02d6
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x02d6:
                r8.playFromUri(r9, r11, r0)
                return r1
            L_0x02da:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x02f8
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x02f8:
                r8.prepareFromMediaId(r9, r11, r0)
                return r1
            L_0x02fc:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r11 = r10.readString()
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x031a
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x031a:
                r8.prepareFromSearch(r9, r11, r0)
                return r1
            L_0x031e:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x0338
                android.os.Parcelable$Creator r11 = android.net.Uri.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                android.net.Uri r11 = (android.net.Uri) r11
                goto L_0x0339
            L_0x0338:
                r11 = r0
            L_0x0339:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0348
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0348:
                r8.prepareFromUri(r9, r11, r0)
                return r1
            L_0x034c:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x0366
                android.os.Parcelable$Creator<androidx.versionedparcelable.ParcelImpl> r11 = androidx.versionedparcelable.ParcelImpl.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                androidx.versionedparcelable.ParcelImpl r11 = (androidx.versionedparcelable.ParcelImpl) r11
                goto L_0x0367
            L_0x0366:
                r11 = r0
            L_0x0367:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0376
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r10)
                android.os.Bundle r12 = (android.os.Bundle) r12
                goto L_0x0377
            L_0x0376:
                r12 = r0
            L_0x0377:
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0386
                android.os.Parcelable$Creator r0 = android.os.ResultReceiver.CREATOR
                java.lang.Object r10 = r0.createFromParcel(r10)
                r0 = r10
                android.os.ResultReceiver r0 = (android.os.ResultReceiver) r0
            L_0x0386:
                r8.sendCustomCommand(r9, r11, r12, r0)
                return r1
            L_0x038a:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                long r10 = r10.readLong()
                r8.seekTo(r9, r10)
                return r1
            L_0x039d:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.rewind(r9)
                return r1
            L_0x03ac:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.fastForward(r9)
                return r1
            L_0x03bb:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.prepare(r9)
                return r1
            L_0x03ca:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.reset(r9)
                return r1
            L_0x03d9:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.pause(r9)
                return r1
            L_0x03e8:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.play(r9)
                return r1
            L_0x03f7:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                int r10 = r10.readInt()
                r8.adjustVolume(r9, r11, r10)
                return r1
            L_0x040e:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                int r11 = r10.readInt()
                int r10 = r10.readInt()
                r8.setVolumeTo(r9, r11, r10)
                return r1
            L_0x0425:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                r8.release(r9)
                return r1
            L_0x0434:
                r10.enforceInterface(r2)
                android.os.IBinder r9 = r10.readStrongBinder()
                androidx.media2.IMediaController2 r9 = androidx.media2.IMediaController2.Stub.asInterface(r9)
                java.lang.String r10 = r10.readString()
                r8.connect(r9, r10)
                return r1
            L_0x0447:
                r11.writeString(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.IMediaSession2.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMediaSession2 {
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

            public void connect(IMediaController2 iMediaController2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void release(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setVolumeTo(IMediaController2 iMediaController2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void adjustVolume(IMediaController2 iMediaController2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void play(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void pause(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void reset(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepare(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void fastForward(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void rewind(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(10, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void seekTo(IMediaController2 iMediaController2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeLong(j);
                    this.mRemote.transact(11, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void sendCustomCommand(IMediaController2 iMediaController2, ParcelImpl parcelImpl, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
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
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromUri(IMediaController2 iMediaController2, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromSearch(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void prepareFromMediaId(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void playFromUri(IMediaController2 iMediaController2, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void playFromSearch(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void playFromMediaId(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRating(IMediaController2 iMediaController2, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
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

            public void setPlaybackSpeed(IMediaController2 iMediaController2, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeFloat(f);
                    this.mRemote.transact(20, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setPlaylist(IMediaController2 iMediaController2, List<ParcelImpl> list, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
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

            public void updatePlaylistMetadata(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
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

            public void addPlaylistItem(IMediaController2 iMediaController2, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(23, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void removePlaylistItem(IMediaController2 iMediaController2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(24, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void replacePlaylistItem(IMediaController2 iMediaController2, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(25, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToPlaylistItem(IMediaController2 iMediaController2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(26, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToPreviousItem(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(27, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void skipToNextItem(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(28, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setRepeatMode(IMediaController2 iMediaController2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(29, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setShuffleMode(IMediaController2 iMediaController2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(30, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void subscribeRoutesInfo(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(31, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unsubscribeRoutesInfo(IMediaController2 iMediaController2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    this.mRemote.transact(32, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void selectRoute(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(33, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getLibraryRoot(IMediaController2 iMediaController2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getItem(IMediaController2 iMediaController2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(35, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getChildren(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void search(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(37, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getSearchResult(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(38, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void subscribe(IMediaController2 iMediaController2, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unsubscribe(IMediaController2 iMediaController2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMediaController2 != null ? iMediaController2.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(40, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
