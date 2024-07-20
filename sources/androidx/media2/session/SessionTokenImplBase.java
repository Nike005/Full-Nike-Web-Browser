package androidx.media2.session;

import android.content.ComponentName;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media2.session.SessionToken;

final class SessionTokenImplBase implements SessionToken.SessionTokenImpl {
    ComponentName mComponentName;
    Bundle mExtras;
    IBinder mISession;
    String mPackageName;
    String mServiceName;
    int mType;
    int mUid;

    public boolean isLegacySession() {
        return false;
    }

    SessionTokenImplBase(ComponentName componentName, int i, int i2) {
        if (componentName != null) {
            this.mComponentName = componentName;
            this.mPackageName = componentName.getPackageName();
            this.mServiceName = componentName.getClassName();
            this.mUid = i;
            this.mType = i2;
            this.mISession = null;
            this.mExtras = null;
            return;
        }
        throw new NullPointerException("serviceComponent shouldn't be null");
    }

    SessionTokenImplBase(int i, int i2, String str, IMediaSession iMediaSession, Bundle bundle) {
        this.mUid = i;
        this.mType = i2;
        this.mPackageName = str;
        this.mServiceName = null;
        this.mComponentName = null;
        this.mISession = iMediaSession.asBinder();
        this.mExtras = bundle;
    }

    SessionTokenImplBase() {
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mType), Integer.valueOf(this.mUid), this.mPackageName, this.mServiceName);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplBase)) {
            return false;
        }
        SessionTokenImplBase sessionTokenImplBase = (SessionTokenImplBase) obj;
        if (this.mUid != sessionTokenImplBase.mUid || !TextUtils.equals(this.mPackageName, sessionTokenImplBase.mPackageName) || !TextUtils.equals(this.mServiceName, sessionTokenImplBase.mServiceName) || this.mType != sessionTokenImplBase.mType || !ObjectsCompat.equals(this.mISession, sessionTokenImplBase.mISession)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "SessionToken {pkg=" + this.mPackageName + " type=" + this.mType + " service=" + this.mServiceName + " IMediaSession=" + this.mISession + " extras=" + this.mExtras + "}";
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getServiceName() {
        return this.mServiceName;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public int getType() {
        return this.mType;
    }

    public Bundle getExtras() {
        return this.mExtras == null ? Bundle.EMPTY : new Bundle(this.mExtras);
    }

    public Object getBinder() {
        return this.mISession;
    }
}
