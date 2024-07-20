package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONObject;

public class OSPermissionStateChanges {
    OSPermissionState from;

    /* renamed from: to */
    OSPermissionState f300to;

    public OSPermissionState getTo() {
        return this.f300to;
    }

    public OSPermissionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f300to.toJSONObject());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
