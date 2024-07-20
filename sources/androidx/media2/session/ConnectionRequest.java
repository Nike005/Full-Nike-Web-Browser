package androidx.media2.session;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

class ConnectionRequest implements VersionedParcelable {
    Bundle mConnectionHints;
    String mPackageName;
    int mPid;
    int mVersion;

    ConnectionRequest() {
    }

    ConnectionRequest(String str, int i, Bundle bundle) {
        this.mVersion = 0;
        this.mPackageName = str;
        this.mPid = i;
        this.mConnectionHints = bundle;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public int getPid() {
        return this.mPid;
    }

    public Bundle getConnectionHints() {
        return this.mConnectionHints;
    }
}
