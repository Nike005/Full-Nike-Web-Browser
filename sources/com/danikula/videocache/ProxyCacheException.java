package com.danikula.videocache;

public class ProxyCacheException extends Exception {
    private static final String LIBRARY_VERSION = ". Version: 2.7.0";

    public ProxyCacheException(String str) {
        super(str + LIBRARY_VERSION);
    }

    public ProxyCacheException(String str, Throwable th) {
        super(str + LIBRARY_VERSION, th);
    }

    public ProxyCacheException(Throwable th) {
        super("No explanation error. Version: 2.7.0", th);
    }
}
