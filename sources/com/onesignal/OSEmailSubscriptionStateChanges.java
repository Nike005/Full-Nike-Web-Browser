package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class OSEmailSubscriptionStateChanges {
    OSEmailSubscriptionState from;

    /* renamed from: to */
    OSEmailSubscriptionState f297to;

    public OSEmailSubscriptionState getTo() {
        return this.f297to;
    }

    public OSEmailSubscriptionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f297to.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
