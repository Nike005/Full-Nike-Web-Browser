package androidx.media2.session;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media2.session.SessionToken;
import androidx.versionedparcelable.CustomVersionedParcelable;
import androidx.versionedparcelable.VersionedParcelable;

final class SessionTokenImplLegacy extends CustomVersionedParcelable implements SessionToken.SessionTokenImpl {
    ComponentName mComponentName;
    Bundle mExtras;
    private MediaSessionCompat.Token mLegacyToken;
    Bundle mLegacyTokenBundle;
    String mPackageName;
    int mType;
    int mUid;

    public boolean isLegacySession() {
        return true;
    }

    SessionTokenImplLegacy(MediaSessionCompat.Token token, String str, int i) {
        if (token == null) {
            throw new NullPointerException("token shouldn't be null");
        } else if (str == null) {
            throw new NullPointerException("packageName shouldn't be null");
        } else if (!TextUtils.isEmpty(str)) {
            this.mLegacyToken = token;
            this.mUid = i;
            this.mPackageName = str;
            this.mComponentName = null;
            this.mType = 100;
            this.mExtras = null;
        } else {
            throw new IllegalArgumentException("packageName shouldn't be empty");
        }
    }

    SessionTokenImplLegacy(ComponentName componentName, int i) {
        if (componentName != null) {
            this.mLegacyToken = null;
            this.mUid = i;
            this.mType = 101;
            this.mPackageName = componentName.getPackageName();
            this.mComponentName = componentName;
            this.mExtras = null;
            return;
        }
        throw new NullPointerException("serviceComponent shouldn't be null");
    }

    SessionTokenImplLegacy() {
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.mType), this.mComponentName, this.mLegacyToken);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplLegacy)) {
            return false;
        }
        SessionTokenImplLegacy sessionTokenImplLegacy = (SessionTokenImplLegacy) obj;
        int i = this.mType;
        if (i != sessionTokenImplLegacy.mType) {
            return false;
        }
        if (i == 100) {
            return ObjectsCompat.equals(this.mLegacyToken, sessionTokenImplLegacy.mLegacyToken);
        }
        if (i != 101) {
            return false;
        }
        return ObjectsCompat.equals(this.mComponentName, sessionTokenImplLegacy.mComponentName);
    }

    public String toString() {
        return "SessionToken {legacyToken=" + this.mLegacyToken + "}";
    }

    public int getUid() {
        return this.mUid;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getServiceName() {
        ComponentName componentName = this.mComponentName;
        if (componentName == null) {
            return null;
        }
        return componentName.getClassName();
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public int getType() {
        return this.mType != 101 ? 0 : 2;
    }

    public Bundle getExtras() {
        return this.mExtras == null ? Bundle.EMPTY : new Bundle(this.mExtras);
    }

    public Object getBinder() {
        return this.mLegacyToken;
    }

    public void onPreParceling(boolean z) {
        MediaSessionCompat.Token token = this.mLegacyToken;
        if (token != null) {
            VersionedParcelable session2Token = token.getSession2Token();
            this.mLegacyToken.setSession2Token((VersionedParcelable) null);
            this.mLegacyTokenBundle = this.mLegacyToken.toBundle();
            this.mLegacyToken.setSession2Token(session2Token);
            return;
        }
        this.mLegacyTokenBundle = null;
    }

    public void onPostParceling() {
        this.mLegacyToken = MediaSessionCompat.Token.fromBundle(this.mLegacyTokenBundle);
        this.mLegacyTokenBundle = null;
    }
}
