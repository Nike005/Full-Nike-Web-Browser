package info.guardianproject.netcipher.client;

import android.content.Intent;
import info.guardianproject.netcipher.client.StrongBuilder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.TrustManager;

public interface StrongBuilder<T extends StrongBuilder, C> {

    public interface Callback<C> {
        void onConnected(C c);

        void onConnectionException(Exception exc);

        void onInvalid();

        void onTimeout();
    }

    C build(Intent intent) throws Exception;

    void build(Callback<C> callback);

    boolean supportsHttpProxy();

    boolean supportsSocksProxy();

    T withBestProxy();

    T withHttpProxy();

    T withSocksProxy();

    T withTorValidation();

    T withTrustManagers(TrustManager[] trustManagerArr) throws NoSuchAlgorithmException, KeyManagementException;

    T withWeakCiphers();
}
