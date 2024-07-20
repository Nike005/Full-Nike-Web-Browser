package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

class GetAuthTokenListener implements StateListener {
    private final TaskCompletionSource<InstallationTokenResult> resultTaskCompletionSource;
    private final C0191Utils utils;

    public GetAuthTokenListener(C0191Utils utils2, TaskCompletionSource<InstallationTokenResult> taskCompletionSource) {
        this.utils = utils2;
        this.resultTaskCompletionSource = taskCompletionSource;
    }

    public boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        if (!persistedInstallationEntry.isRegistered() || this.utils.isAuthTokenExpired(persistedInstallationEntry)) {
            return false;
        }
        this.resultTaskCompletionSource.setResult(InstallationTokenResult.builder().setToken(persistedInstallationEntry.getAuthToken()).setTokenExpirationTimestamp(persistedInstallationEntry.getExpiresInSecs()).setTokenCreationTimestamp(persistedInstallationEntry.getTokenCreationEpochInSecs()).build());
        return true;
    }

    public boolean onException(PersistedInstallationEntry persistedInstallationEntry, Exception exc) {
        if (!persistedInstallationEntry.isErrored() && !persistedInstallationEntry.isNotGenerated() && !persistedInstallationEntry.isUnregistered()) {
            return false;
        }
        this.resultTaskCompletionSource.trySetException(exc);
        return true;
    }
}
