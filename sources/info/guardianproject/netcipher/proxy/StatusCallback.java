package info.guardianproject.netcipher.proxy;

import android.content.Intent;

public interface StatusCallback {
    void onDisabled();

    void onEnabled(Intent intent);

    void onNotYetInstalled();

    void onStarting();

    void onStatusTimeout();

    void onStopping();
}
