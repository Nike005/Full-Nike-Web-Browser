package androidx.media2.exoplayer.external.audio;

public class AudioDecoderException extends Exception {
    public AudioDecoderException(String str) {
        super(str);
    }

    public AudioDecoderException(String str, Throwable th) {
        super(str, th);
    }
}
