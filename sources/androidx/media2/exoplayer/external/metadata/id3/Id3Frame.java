package androidx.media2.exoplayer.external.metadata.id3;

import androidx.media2.exoplayer.external.metadata.Metadata;

public abstract class Id3Frame implements Metadata.Entry {

    /* renamed from: id */
    public final String f4091id;

    public int describeContents() {
        return 0;
    }

    public Id3Frame(String str) {
        this.f4091id = str;
    }

    public String toString() {
        return this.f4091id;
    }
}
