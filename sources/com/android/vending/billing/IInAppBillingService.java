package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IInAppBillingService extends IInterface {
    Bundle acknowledgePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException;

    int consumePurchase(int i, String str, String str2) throws RemoteException;

    Bundle consumePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException;

    Bundle getBuyIntent(int i, String str, String str2, String str3, String str4) throws RemoteException;

    Bundle getBuyIntentExtraParams(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException;

    Bundle getBuyIntentToReplaceSkus(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException;

    Bundle getPurchaseHistory(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException;

    Bundle getPurchases(int i, String str, String str2, String str3) throws RemoteException;

    Bundle getPurchasesExtraParams(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException;

    Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException;

    Bundle getSkuDetailsExtraParams(int i, String str, String str2, Bundle bundle, Bundle bundle2) throws RemoteException;

    Bundle getSubscriptionManagementIntent(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException;

    int isBillingSupported(int i, String str, String str2) throws RemoteException;

    int isBillingSupportedExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException;

    int stub(int i, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IInAppBillingService {
        private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingService";
        static final int TRANSACTION_acknowledgePurchaseExtraParams = 902;
        static final int TRANSACTION_consumePurchase = 5;
        static final int TRANSACTION_consumePurchaseExtraParams = 12;
        static final int TRANSACTION_getBuyIntent = 3;
        static final int TRANSACTION_getBuyIntentExtraParams = 8;
        static final int TRANSACTION_getBuyIntentToReplaceSkus = 7;
        static final int TRANSACTION_getPurchaseHistory = 9;
        static final int TRANSACTION_getPurchases = 4;
        static final int TRANSACTION_getPurchasesExtraParams = 11;
        static final int TRANSACTION_getSkuDetails = 2;
        static final int TRANSACTION_getSkuDetailsExtraParams = 901;
        static final int TRANSACTION_getSubscriptionManagementIntent = 801;
        static final int TRANSACTION_isBillingSupported = 1;
        static final int TRANSACTION_isBillingSupportedExtraParams = 10;
        static final int TRANSACTION_stub = 6;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInAppBillingService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IInAppBillingService)) {
                return new Proxy(iBinder);
            }
            return (IInAppBillingService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v30, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v34, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v40, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v26, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r0 = 801(0x321, float:1.122E-42)
                r1 = 0
                r2 = 0
                r3 = 1
                java.lang.String r4 = "com.android.vending.billing.IInAppBillingService"
                if (r12 == r0) goto L_0x02a3
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r12 == r0) goto L_0x029f
                r0 = 901(0x385, float:1.263E-42)
                if (r12 == r0) goto L_0x025a
                r0 = 902(0x386, float:1.264E-42)
                if (r12 == r0) goto L_0x0228
                switch(r12) {
                    case 1: goto L_0x020e;
                    case 2: goto L_0x01dc;
                    case 3: goto L_0x01b0;
                    case 4: goto L_0x0189;
                    case 5: goto L_0x016f;
                    case 6: goto L_0x0155;
                    case 7: goto L_0x0125;
                    case 8: goto L_0x00e9;
                    case 9: goto L_0x00b1;
                    case 10: goto L_0x0088;
                    case 11: goto L_0x0050;
                    case 12: goto L_0x001e;
                    default: goto L_0x0019;
                }
            L_0x0019:
                boolean r12 = super.onTransact(r12, r13, r14, r15)
                return r12
            L_0x001e:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r0 = r13.readString()
                int r4 = r13.readInt()
                if (r4 == 0) goto L_0x003c
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r1.createFromParcel(r13)
                r1 = r13
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x003c:
                android.os.Bundle r12 = r11.consumePurchaseExtraParams(r12, r15, r0, r1)
                r14.writeNoException()
                if (r12 == 0) goto L_0x004c
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x004f
            L_0x004c:
                r14.writeInt(r2)
            L_0x004f:
                return r3
            L_0x0050:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0072
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r1 = r12
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0072:
                r9 = r1
                r4 = r11
                android.os.Bundle r12 = r4.getPurchasesExtraParams(r5, r6, r7, r8, r9)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0084
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x0087
            L_0x0084:
                r14.writeInt(r2)
            L_0x0087:
                return r3
            L_0x0088:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r0 = r13.readString()
                int r2 = r13.readInt()
                if (r2 == 0) goto L_0x00a6
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r1.createFromParcel(r13)
                r1 = r13
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x00a6:
                int r12 = r11.isBillingSupportedExtraParams(r12, r15, r0, r1)
                r14.writeNoException()
                r14.writeInt(r12)
                return r3
            L_0x00b1:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x00d3
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r1 = r12
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x00d3:
                r9 = r1
                r4 = r11
                android.os.Bundle r12 = r4.getPurchaseHistory(r5, r6, r7, r8, r9)
                r14.writeNoException()
                if (r12 == 0) goto L_0x00e5
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x00e8
            L_0x00e5:
                r14.writeInt(r2)
            L_0x00e8:
                return r3
            L_0x00e9:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                java.lang.String r9 = r13.readString()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x010f
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r1 = r12
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x010f:
                r10 = r1
                r4 = r11
                android.os.Bundle r12 = r4.getBuyIntentExtraParams(r5, r6, r7, r8, r9, r10)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0121
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x0124
            L_0x0121:
                r14.writeInt(r2)
            L_0x0124:
                return r3
            L_0x0125:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.util.ArrayList r7 = r13.createStringArrayList()
                java.lang.String r8 = r13.readString()
                java.lang.String r9 = r13.readString()
                java.lang.String r10 = r13.readString()
                r4 = r11
                android.os.Bundle r12 = r4.getBuyIntentToReplaceSkus(r5, r6, r7, r8, r9, r10)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0151
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x0154
            L_0x0151:
                r14.writeInt(r2)
            L_0x0154:
                return r3
            L_0x0155:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r13 = r13.readString()
                int r12 = r11.stub(r12, r15, r13)
                r14.writeNoException()
                r14.writeInt(r12)
                return r3
            L_0x016f:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r13 = r13.readString()
                int r12 = r11.consumePurchase(r12, r15, r13)
                r14.writeNoException()
                r14.writeInt(r12)
                return r3
            L_0x0189:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r0 = r13.readString()
                java.lang.String r13 = r13.readString()
                android.os.Bundle r12 = r11.getPurchases(r12, r15, r0, r13)
                r14.writeNoException()
                if (r12 == 0) goto L_0x01ac
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x01af
            L_0x01ac:
                r14.writeInt(r2)
            L_0x01af:
                return r3
            L_0x01b0:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                java.lang.String r9 = r13.readString()
                r4 = r11
                android.os.Bundle r12 = r4.getBuyIntent(r5, r6, r7, r8, r9)
                r14.writeNoException()
                if (r12 == 0) goto L_0x01d8
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x01db
            L_0x01d8:
                r14.writeInt(r2)
            L_0x01db:
                return r3
            L_0x01dc:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r0 = r13.readString()
                int r4 = r13.readInt()
                if (r4 == 0) goto L_0x01fa
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r1.createFromParcel(r13)
                r1 = r13
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x01fa:
                android.os.Bundle r12 = r11.getSkuDetails(r12, r15, r0, r1)
                r14.writeNoException()
                if (r12 == 0) goto L_0x020a
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x020d
            L_0x020a:
                r14.writeInt(r2)
            L_0x020d:
                return r3
            L_0x020e:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r13 = r13.readString()
                int r12 = r11.isBillingSupported(r12, r15, r13)
                r14.writeNoException()
                r14.writeInt(r12)
                return r3
            L_0x0228:
                r13.enforceInterface(r4)
                int r12 = r13.readInt()
                java.lang.String r15 = r13.readString()
                java.lang.String r0 = r13.readString()
                int r4 = r13.readInt()
                if (r4 == 0) goto L_0x0246
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r13 = r1.createFromParcel(r13)
                r1 = r13
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0246:
                android.os.Bundle r12 = r11.acknowledgePurchaseExtraParams(r12, r15, r0, r1)
                r14.writeNoException()
                if (r12 == 0) goto L_0x0256
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x0259
            L_0x0256:
                r14.writeInt(r2)
            L_0x0259:
                return r3
            L_0x025a:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0279
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                android.os.Bundle r12 = (android.os.Bundle) r12
                r8 = r12
                goto L_0x027a
            L_0x0279:
                r8 = r1
            L_0x027a:
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0289
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r1 = r12
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0289:
                r9 = r1
                r4 = r11
                android.os.Bundle r12 = r4.getSkuDetailsExtraParams(r5, r6, r7, r8, r9)
                r14.writeNoException()
                if (r12 == 0) goto L_0x029b
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x029e
            L_0x029b:
                r14.writeInt(r2)
            L_0x029e:
                return r3
            L_0x029f:
                r14.writeString(r4)
                return r3
            L_0x02a3:
                r13.enforceInterface(r4)
                int r5 = r13.readInt()
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x02c5
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r1 = r12
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x02c5:
                r9 = r1
                r4 = r11
                android.os.Bundle r12 = r4.getSubscriptionManagementIntent(r5, r6, r7, r8, r9)
                r14.writeNoException()
                if (r12 == 0) goto L_0x02d7
                r14.writeInt(r3)
                r12.writeToParcel(r14, r3)
                goto L_0x02da
            L_0x02d7:
                r14.writeInt(r2)
            L_0x02da:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.vending.billing.IInAppBillingService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IInAppBillingService {
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

            public int isBillingSupported(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSkuDetails(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntent(int i, String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchases(int i, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int consumePurchase(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int stub(int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntentToReplaceSkus(int i, String str, List<String> list, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringList(list);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getBuyIntentExtraParams(int i, String str, String str2, String str3, String str4, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchaseHistory(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int isBillingSupportedExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSubscriptionManagementIntent(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(801, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getPurchasesExtraParams(int i, String str, String str2, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle consumePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSkuDetailsExtraParams(int i, String str, String str2, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(901, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle acknowledgePurchaseExtraParams(int i, String str, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(902, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
