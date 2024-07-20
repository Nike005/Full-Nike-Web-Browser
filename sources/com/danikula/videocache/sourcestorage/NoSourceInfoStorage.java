package com.danikula.videocache.sourcestorage;

import com.danikula.videocache.SourceInfo;

public class NoSourceInfoStorage implements SourceInfoStorage {
    public SourceInfo get(String str) {
        return null;
    }

    public void put(String str, SourceInfo sourceInfo) {
    }

    public void release() {
    }
}
