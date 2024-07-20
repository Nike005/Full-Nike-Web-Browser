package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONObject;

public class OSSubscriptionStateChanges {
    OSSubscriptionState from;

    /* renamed from: to */
    OSSubscriptionState f301to;

    public OSSubscriptionState getTo() {
        return this.f301to;
    }

    public OSSubscriptionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f301to.toJSONObject());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
