package com.appsgeyser.sdk.server.network;

public abstract class OnNetworkStateChangedListener {

    /* renamed from: id */
    private String f4926id;

    public abstract void networkIsDown();

    public abstract void networkIsUp();

    protected OnNetworkStateChangedListener(String str) {
        this.f4926id = str;
    }

    public String getId() {
        return this.f4926id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f4926id.equals(((OnNetworkStateChangedListener) obj).f4926id);
    }

    public int hashCode() {
        return this.f4926id.hashCode();
    }
}
