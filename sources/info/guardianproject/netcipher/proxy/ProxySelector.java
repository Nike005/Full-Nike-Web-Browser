package info.guardianproject.netcipher.proxy;

import android.util.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ProxySelector extends java.net.ProxySelector {
    private ArrayList<Proxy> listProxies = new ArrayList<>();

    public void addProxy(Proxy.Type type, String str, int i) {
        this.listProxies.add(new Proxy(type, new InetSocketAddress(str, i)));
    }

    public void connectFailed(URI uri, SocketAddress socketAddress, IOException iOException) {
        Log.w("ProxySelector", "could not connect to " + socketAddress.toString() + ": " + iOException.getMessage());
    }

    public List<Proxy> select(URI uri) {
        return this.listProxies;
    }
}
