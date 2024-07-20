package androidx.media2.exoplayer.external.extractor.p069ts;

import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.p069ts.TsPayloadReader;
import androidx.media2.exoplayer.external.util.ParsableByteArray;

/* renamed from: androidx.media2.exoplayer.external.extractor.ts.ElementaryStreamReader */
public interface ElementaryStreamReader {
    void consume(ParsableByteArray parsableByteArray) throws ParserException;

    void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator);

    void packetFinished();

    void packetStarted(long j, int i);

    void seek();
}
