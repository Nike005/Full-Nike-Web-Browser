package androidx.media2;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media2.SessionToken2;

final class SessionToken2ImplLegacy implements SessionToken2.SessionToken2Impl {
    private final ComponentName mComponentName;
    private final String mId;
    private final MediaSessionCompat.Token mLegacyToken;
    private final String mPackageName;
    private final int mType;
    private final int mUid;

    public int getUid() {
        return -1;
    }

    public boolean isLegacySession() {
        return true;
    }

    SessionToken2ImplLegacy(MediaSessionCompat.Token token, String str, int i) {
        if (token == null) {
            throw new IllegalArgumentException("token shouldn't be null.");
        } else if (!TextUtils.isEmpty(str)) {
            this.mLegacyToken = token;
            this.mUid = i;
            this.mPackageName = str;
            this.mComponentName = null;
            this.mType = 100;
            this.mId = "";
        } else {
            throw new IllegalArgumentException("packageName shouldn't be null.");
        }
    }

    SessionToken2ImplLegacy(ComponentName componentName, int i, String str) {
        if (componentName != null) {
            this.mLegacyToken = null;
            this.mUid = i;
            this.mType = 101;
            this.mPackageName = componentName.getPackageName();
            this.mComponentName = componentName;
            this.mId = str;
            return;
        }
        throw new IllegalArgumentException("serviceComponent shouldn't be null.");
    }

    public int hashCode() {
        return this.mLegacyToken.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2ImplLegacy)) {
            return false;
        }
        SessionToken2ImplLegacy sessionToken2ImplLegacy = (SessionToken2ImplLegacy) obj;
        if (this.mLegacyToken == null && sessionToken2ImplLegacy.mLegacyToken == null) {
            return ObjectsCompat.equals(this.mComponentName, sessionToken2ImplLegacy.mComponentName);
        }
        return ObjectsCompat.equals(this.mLegacyToken, sessionToken2ImplLegacy.mLegacyToken);
    }

    public String toString() {
        return "SessionToken2 {legacyToken=" + this.mLegacyToken + "}";
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

    public String getSessionId() {
        return this.mId;
    }

    public int getType() {
        return this.mType != 101 ? 0 : 2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        MediaSessionCompat.Token token = this.mLegacyToken;
        String str = null;
        bundle.putBundle("android.media.token.LEGACY", token == null ? null : token.toBundle());
        bundle.putInt("android.media.token.uid", this.mUid);
        bundle.putInt("android.media.token.type", this.mType);
        bundle.putString("android.media.token.package_name", this.mPackageName);
        ComponentName componentName = this.mComponentName;
        if (componentName != null) {
            str = componentName.getClassName();
        }
        bundle.putString("android.media.token.service_name", str);
        bundle.putString("android.media.token.session_id", this.mId);
        return bundle;
    }

    public Object getBinder() {
        return this.mLegacyToken;
    }

    public static SessionToken2ImplLegacy fromBundle(Bundle bundle) {
        int i = bundle.getInt("android.media.token.type");
        if (i == 100) {
            return new SessionToken2ImplLegacy(MediaSessionCompat.Token.fromBundle(bundle.getBundle("android.media.token.LEGACY")), bundle.getString("android.media.token.package_name"), bundle.getInt("android.media.token.uid"));
        }
        if (i != 101) {
            return null;
        }
        return new SessionToken2ImplLegacy(new ComponentName(bundle.getString("android.media.token.package_name"), bundle.getString("android.media.token.service_name")), bundle.getInt("android.media.token.uid"), bundle.getString("android.media.token.session_id"));
    }
}
