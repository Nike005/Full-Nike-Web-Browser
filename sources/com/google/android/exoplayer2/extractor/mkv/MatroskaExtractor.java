package com.google.android.exoplayer2.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C5211C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.internal.ByteCompanionObject;

public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = $$Lambda$MatroskaExtractor$jNXW0tyYIOPE6N2jicocV6rRvBs.INSTANCE;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private final ParsableByteArray blockAdditionalData;
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* access modifiers changed from: protected */
    public int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case ID_PIXEL_WIDTH /*176*/:
            case ID_CUE_TIME /*179*/:
            case ID_PIXEL_HEIGHT /*186*/:
            case ID_TRACK_NUMBER /*215*/:
            case ID_TIME_CODE /*231*/:
            case ID_BLOCK_ADD_ID /*238*/:
            case ID_CUE_CLUSTER_POSITION /*241*/:
            case ID_REFERENCE_BLOCK /*251*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
            case ID_EBML_READ_VERSION /*17143*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
            case ID_SEEK_POSITION /*21420*/:
            case ID_STEREO_MODE /*21432*/:
            case ID_DISPLAY_WIDTH /*21680*/:
            case ID_DISPLAY_UNIT /*21682*/:
            case ID_DISPLAY_HEIGHT /*21690*/:
            case ID_FLAG_FORCED /*21930*/:
            case ID_COLOUR_RANGE /*21945*/:
            case ID_COLOUR_TRANSFER /*21946*/:
            case ID_COLOUR_PRIMARIES /*21947*/:
            case ID_MAX_CLL /*21948*/:
            case ID_MAX_FALL /*21949*/:
            case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
            case ID_CODEC_DELAY /*22186*/:
            case ID_SEEK_PRE_ROLL /*22203*/:
            case ID_AUDIO_BIT_DEPTH /*25188*/:
            case ID_PROJECTION_TYPE /*30321*/:
            case ID_DEFAULT_DURATION /*2352003*/:
            case ID_TIMECODE_SCALE /*2807729*/:
                return 2;
            case 134:
            case 17026:
            case ID_NAME /*21358*/:
            case ID_LANGUAGE /*2274716*/:
                return 3;
            case 160:
            case ID_BLOCK_MORE /*166*/:
            case ID_TRACK_ENTRY /*174*/:
            case ID_CUE_TRACK_POSITIONS /*183*/:
            case ID_CUE_POINT /*187*/:
            case 224:
            case ID_AUDIO /*225*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
            case ID_SEEK /*19899*/:
            case ID_CONTENT_COMPRESSION /*20532*/:
            case ID_CONTENT_ENCRYPTION /*20533*/:
            case ID_COLOUR /*21936*/:
            case ID_MASTERING_METADATA /*21968*/:
            case ID_CONTENT_ENCODING /*25152*/:
            case ID_CONTENT_ENCODINGS /*28032*/:
            case ID_BLOCK_ADDITIONS /*30113*/:
            case ID_PROJECTION /*30320*/:
            case ID_SEEK_HEAD /*290298740*/:
            case 357149030:
            case ID_TRACKS /*374648427*/:
            case ID_SEGMENT /*408125543*/:
            case ID_EBML /*440786851*/:
            case ID_CUES /*475249515*/:
            case ID_CLUSTER /*524531317*/:
                return 1;
            case ID_BLOCK /*161*/:
            case ID_SIMPLE_BLOCK /*163*/:
            case ID_BLOCK_ADDITIONAL /*165*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
            case ID_SEEK_ID /*21419*/:
            case ID_CODEC_PRIVATE /*25506*/:
            case ID_PROJECTION_PRIVATE /*30322*/:
                return 4;
            case ID_SAMPLING_FREQUENCY /*181*/:
            case ID_DURATION /*17545*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
            case ID_LUMNINANCE_MAX /*21977*/:
            case ID_LUMNINANCE_MIN /*21978*/:
            case ID_PROJECTION_POSE_YAW /*30323*/:
            case ID_PROJECTION_POSE_PITCH /*30324*/:
            case ID_PROJECTION_POSE_ROLL /*30325*/:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    public final void release() {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (i & 1) != 0 ? false : true;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.blockAdditionalData = new ParsableByteArray();
    }

    public final boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.blockHasReferenceBlock = false;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == ID_CUE_POINT) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 == -1 || j3 == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
                return;
            }
            throw new ParserException("Multiple Segment elements not supported");
        } else if (i == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i != ID_CLUSTER || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    /* access modifiers changed from: protected */
    public void endMasterElement(int i) throws ParserException {
        if (i != 160) {
            if (i == ID_TRACK_ENTRY) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    Track track = this.currentTrack;
                    track.initializeOutput(this.extractorOutput, track.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i == ID_SEEK) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == ID_CUES) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (i != ID_CONTENT_ENCODING) {
                if (i != ID_CONTENT_ENCODINGS) {
                    if (i == 357149030) {
                        if (this.timecodeScale == -9223372036854775807L) {
                            this.timecodeScale = 1000000;
                        }
                        long j2 = this.durationTimecode;
                        if (j2 != -9223372036854775807L) {
                            this.durationUs = scaleTimecodeToUs(j2);
                        }
                    } else if (i != ID_TRACKS) {
                        if (i == ID_CUES && !this.sentSeekMap) {
                            this.extractorOutput.seekMap(buildSeekMap());
                            this.sentSeekMap = true;
                        }
                    } else if (this.tracks.size() != 0) {
                        this.extractorOutput.endTracks();
                    } else {
                        throw new ParserException("No valid tracks were found");
                    }
                } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
            } else if (!this.currentTrack.hasContentEncryption) {
            } else {
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C5211C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
            }
        } else if (this.blockState == 2) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.blockSampleCount; i4++) {
                i3 += this.blockSampleSizes[i4];
            }
            Track track2 = this.tracks.get(this.blockTrackNumber);
            for (int i5 = 0; i5 < this.blockSampleCount; i5++) {
                long j3 = ((long) ((track2.defaultSampleDurationNs * i5) / 1000)) + this.blockTimeUs;
                int i6 = this.blockFlags;
                if (i5 == 0 && !this.blockHasReferenceBlock) {
                    i6 |= 1;
                }
                int i7 = this.blockSampleSizes[i5];
                i3 -= i7;
                commitSampleToOutput(track2, j3, i6, i7, i3);
            }
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void integerElement(int i, long j) throws ParserException {
        if (i != ID_CONTENT_ENCODING_ORDER) {
            if (i != ID_CONTENT_ENCODING_SCOPE) {
                boolean z = false;
                switch (i) {
                    case 131:
                        this.currentTrack.type = (int) j;
                        return;
                    case 136:
                        Track track = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track.flagDefault = z;
                        return;
                    case 155:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case 159:
                        this.currentTrack.channelCount = (int) j;
                        return;
                    case ID_PIXEL_WIDTH /*176*/:
                        this.currentTrack.width = (int) j;
                        return;
                    case ID_CUE_TIME /*179*/:
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case ID_PIXEL_HEIGHT /*186*/:
                        this.currentTrack.height = (int) j;
                        return;
                    case ID_TRACK_NUMBER /*215*/:
                        this.currentTrack.number = (int) j;
                        return;
                    case ID_TIME_CODE /*231*/:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case ID_BLOCK_ADD_ID /*238*/:
                        this.blockAdditionalId = (int) j;
                        return;
                    case ID_CUE_CLUSTER_POSITION /*241*/:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case ID_REFERENCE_BLOCK /*251*/:
                        this.blockHasReferenceBlock = true;
                        return;
                    case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                        if (j != 3) {
                            throw new ParserException("ContentCompAlgo " + j + " not supported");
                        }
                        return;
                    case ID_DOC_TYPE_READ_VERSION /*17029*/:
                        if (j < 1 || j > 2) {
                            throw new ParserException("DocTypeReadVersion " + j + " not supported");
                        }
                        return;
                    case ID_EBML_READ_VERSION /*17143*/:
                        if (j != 1) {
                            throw new ParserException("EBMLReadVersion " + j + " not supported");
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                        if (j != 5) {
                            throw new ParserException("ContentEncAlgo " + j + " not supported");
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                        if (j != 1) {
                            throw new ParserException("AESSettingsCipherMode " + j + " not supported");
                        }
                        return;
                    case ID_SEEK_POSITION /*21420*/:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case ID_STEREO_MODE /*21432*/:
                        int i2 = (int) j;
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DISPLAY_WIDTH /*21680*/:
                        this.currentTrack.displayWidth = (int) j;
                        return;
                    case ID_DISPLAY_UNIT /*21682*/:
                        this.currentTrack.displayUnit = (int) j;
                        return;
                    case ID_DISPLAY_HEIGHT /*21690*/:
                        this.currentTrack.displayHeight = (int) j;
                        return;
                    case ID_FLAG_FORCED /*21930*/:
                        Track track2 = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track2.flagForced = z;
                        return;
                    case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
                        this.currentTrack.maxBlockAdditionId = (int) j;
                        return;
                    case ID_CODEC_DELAY /*22186*/:
                        this.currentTrack.codecDelayNs = j;
                        return;
                    case ID_SEEK_PRE_ROLL /*22203*/:
                        this.currentTrack.seekPreRollNs = j;
                        return;
                    case ID_AUDIO_BIT_DEPTH /*25188*/:
                        this.currentTrack.audioBitDepth = (int) j;
                        return;
                    case ID_PROJECTION_TYPE /*30321*/:
                        int i3 = (int) j;
                        if (i3 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i3 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i3 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i3 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DEFAULT_DURATION /*2352003*/:
                        this.currentTrack.defaultSampleDurationNs = (int) j;
                        return;
                    case ID_TIMECODE_SCALE /*2807729*/:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case ID_COLOUR_RANGE /*21945*/:
                                int i4 = (int) j;
                                if (i4 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i4 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_COLOUR_TRANSFER /*21946*/:
                                int i5 = (int) j;
                                if (i5 != 1) {
                                    if (i5 == 16) {
                                        this.currentTrack.colorTransfer = 6;
                                        return;
                                    } else if (i5 == 18) {
                                        this.currentTrack.colorTransfer = 7;
                                        return;
                                    } else if (!(i5 == 6 || i5 == 7)) {
                                        return;
                                    }
                                }
                                this.currentTrack.colorTransfer = 3;
                                return;
                            case ID_COLOUR_PRIMARIES /*21947*/:
                                this.currentTrack.hasColorInfo = true;
                                int i6 = (int) j;
                                if (i6 == 1) {
                                    this.currentTrack.colorSpace = 1;
                                    return;
                                } else if (i6 == 9) {
                                    this.currentTrack.colorSpace = 6;
                                    return;
                                } else if (i6 == 4 || i6 == 5 || i6 == 6 || i6 == 7) {
                                    this.currentTrack.colorSpace = 2;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_MAX_CLL /*21948*/:
                                this.currentTrack.maxContentLuminance = (int) j;
                                return;
                            case ID_MAX_FALL /*21949*/:
                                this.currentTrack.maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                throw new ParserException("ContentEncodingScope " + j + " not supported");
            }
        } else if (j != 0) {
            throw new ParserException("ContentEncodingOrder " + j + " not supported");
        }
    }

    /* access modifiers changed from: protected */
    public void floatElement(int i, double d) throws ParserException {
        if (i == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case ID_PROJECTION_POSE_YAW /*30323*/:
                            this.currentTrack.projectionPoseYaw = (float) d;
                            return;
                        case ID_PROJECTION_POSE_PITCH /*30324*/:
                            this.currentTrack.projectionPosePitch = (float) d;
                            return;
                        case ID_PROJECTION_POSE_ROLL /*30325*/:
                            this.currentTrack.projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: protected */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == ID_NAME) {
                this.currentTrack.name = str;
            } else if (i == ID_LANGUAGE) {
                String unused = this.currentTrack.language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw new ParserException("DocType " + str + " not supported");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0220, code lost:
        throw new com.google.android.exoplayer2.ParserException("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r21, int r22, com.google.android.exoplayer2.extractor.ExtractorInput r23) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r20 = this;
            r7 = r20
            r0 = r21
            r1 = r22
            r8 = r23
            r2 = 161(0xa1, float:2.26E-43)
            r3 = 163(0xa3, float:2.28E-43)
            r4 = 2
            r9 = 0
            r10 = 1
            if (r0 == r2) goto L_0x00b5
            if (r0 == r3) goto L_0x00b5
            r2 = 165(0xa5, float:2.31E-43)
            if (r0 == r2) goto L_0x009f
            r2 = 16981(0x4255, float:2.3795E-41)
            if (r0 == r2) goto L_0x0090
            r2 = 18402(0x47e2, float:2.5787E-41)
            if (r0 == r2) goto L_0x0080
            r2 = 21419(0x53ab, float:3.0014E-41)
            if (r0 == r2) goto L_0x0060
            r2 = 25506(0x63a2, float:3.5742E-41)
            if (r0 == r2) goto L_0x0051
            r2 = 30322(0x7672, float:4.249E-41)
            if (r0 != r2) goto L_0x003a
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.projectionData = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.projectionData
            r8.readFully(r0, r9, r1)
            goto L_0x02e3
        L_0x003a:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected id: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>((java.lang.String) r0)
            throw r1
        L_0x0051:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.codecPrivate = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.codecPrivate
            r8.readFully(r0, r9, r1)
            goto L_0x02e3
        L_0x0060:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.data
            java.util.Arrays.fill(r0, r9)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.data
            int r2 = 4 - r1
            r8.readFully(r0, r2, r1)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            r0.setPosition(r9)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            long r0 = r0.readUnsignedInt()
            int r1 = (int) r0
            r7.seekEntryId = r1
            goto L_0x02e3
        L_0x0080:
            byte[] r0 = new byte[r1]
            r8.readFully(r0, r9, r1)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r7.currentTrack
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r2 = new com.google.android.exoplayer2.extractor.TrackOutput$CryptoData
            r2.<init>(r10, r0, r9, r9)
            r1.cryptoData = r2
            goto L_0x02e3
        L_0x0090:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.sampleStrippedBytes = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.sampleStrippedBytes
            r8.readFully(r0, r9, r1)
            goto L_0x02e3
        L_0x009f:
            int r0 = r7.blockState
            if (r0 == r4) goto L_0x00a4
            return
        L_0x00a4:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r0 = r7.tracks
            int r2 = r7.blockTrackNumber
            java.lang.Object r0 = r0.get(r2)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r0
            int r2 = r7.blockAdditionalId
            r7.handleBlockAdditionalData(r0, r2, r8, r1)
            goto L_0x02e3
        L_0x00b5:
            int r2 = r7.blockState
            r5 = 8
            if (r2 != 0) goto L_0x00da
            com.google.android.exoplayer2.extractor.mkv.VarintReader r2 = r7.varintReader
            long r11 = r2.readUnsignedVarint(r8, r9, r10, r5)
            int r2 = (int) r11
            r7.blockTrackNumber = r2
            com.google.android.exoplayer2.extractor.mkv.VarintReader r2 = r7.varintReader
            int r2 = r2.getLastLength()
            r7.blockTrackNumberLength = r2
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7.blockDurationUs = r11
            r7.blockState = r10
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r7.scratch
            r2.reset()
        L_0x00da:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r2 = r7.tracks
            int r6 = r7.blockTrackNumber
            java.lang.Object r2 = r2.get(r6)
            r11 = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r11 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r11
            if (r11 != 0) goto L_0x00f1
            int r0 = r7.blockTrackNumberLength
            int r0 = r1 - r0
            r8.skipFully(r0)
            r7.blockState = r9
            return
        L_0x00f1:
            int r2 = r7.blockState
            if (r2 != r10) goto L_0x029a
            r2 = 3
            r7.readScratch(r8, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r7.scratch
            byte[] r6 = r6.data
            byte r6 = r6[r4]
            r6 = r6 & 6
            int r6 = r6 >> r10
            r12 = 255(0xff, float:3.57E-43)
            if (r6 != 0) goto L_0x0118
            r7.blockSampleCount = r10
            int[] r6 = r7.blockSampleSizes
            int[] r6 = ensureArrayCapacity(r6, r10)
            r7.blockSampleSizes = r6
            int r13 = r7.blockTrackNumberLength
            int r1 = r1 - r13
            int r1 = r1 - r2
            r6[r9] = r1
            goto L_0x0234
        L_0x0118:
            r13 = 4
            r7.readScratch(r8, r13)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r7.scratch
            byte[] r14 = r14.data
            byte r14 = r14[r2]
            r14 = r14 & r12
            int r14 = r14 + r10
            r7.blockSampleCount = r14
            int[] r15 = r7.blockSampleSizes
            int[] r14 = ensureArrayCapacity(r15, r14)
            r7.blockSampleSizes = r14
            if (r6 != r4) goto L_0x013c
            int r2 = r7.blockTrackNumberLength
            int r1 = r1 - r2
            int r1 = r1 - r13
            int r2 = r7.blockSampleCount
            int r1 = r1 / r2
            java.util.Arrays.fill(r14, r9, r2, r1)
            goto L_0x0234
        L_0x013c:
            if (r6 != r10) goto L_0x0173
            r2 = 0
            r6 = 0
        L_0x0140:
            int r14 = r7.blockSampleCount
            int r15 = r14 + -1
            if (r2 >= r15) goto L_0x0167
            int[] r14 = r7.blockSampleSizes
            r14[r2] = r9
        L_0x014a:
            int r13 = r13 + r10
            r7.readScratch(r8, r13)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r7.scratch
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            r14 = r14 & r12
            int[] r15 = r7.blockSampleSizes
            r16 = r15[r2]
            int r16 = r16 + r14
            r15[r2] = r16
            if (r14 == r12) goto L_0x014a
            r14 = r15[r2]
            int r6 = r6 + r14
            int r2 = r2 + 1
            goto L_0x0140
        L_0x0167:
            int[] r2 = r7.blockSampleSizes
            int r14 = r14 - r10
            int r15 = r7.blockTrackNumberLength
            int r1 = r1 - r15
            int r1 = r1 - r13
            int r1 = r1 - r6
            r2[r14] = r1
            goto L_0x0234
        L_0x0173:
            if (r6 != r2) goto L_0x0283
            r2 = 0
            r6 = 0
        L_0x0177:
            int r14 = r7.blockSampleCount
            int r15 = r14 + -1
            if (r2 >= r15) goto L_0x0229
            int[] r14 = r7.blockSampleSizes
            r14[r2] = r9
            int r13 = r13 + 1
            r7.readScratch(r8, r13)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r7.scratch
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            if (r14 == 0) goto L_0x0221
            r16 = 0
            r14 = 0
        L_0x0193:
            if (r14 >= r5) goto L_0x01ed
            int r18 = 7 - r14
            int r18 = r10 << r18
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r7.scratch
            byte[] r3 = r3.data
            byte r3 = r3[r15]
            r3 = r3 & r18
            if (r3 == 0) goto L_0x01e4
            int r13 = r13 + r14
            r7.readScratch(r8, r13)
            com.google.android.exoplayer2.util.ParsableByteArray r3 = r7.scratch
            byte[] r3 = r3.data
            int r16 = r15 + 1
            byte r3 = r3[r15]
            r3 = r3 & r12
            r15 = r18 ^ -1
            r3 = r3 & r15
            long r9 = (long) r3
            r3 = r16
        L_0x01b6:
            r16 = r9
            if (r3 >= r13) goto L_0x01d0
            long r9 = r16 << r5
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r7.scratch
            byte[] r15 = r15.data
            int r16 = r3 + 1
            byte r3 = r15[r3]
            r3 = r3 & r12
            r19 = r13
            long r12 = (long) r3
            long r9 = r9 | r12
            r3 = r16
            r13 = r19
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x01b6
        L_0x01d0:
            r19 = r13
            if (r2 <= 0) goto L_0x01df
            int r14 = r14 * 7
            int r14 = r14 + 6
            r9 = 1
            long r12 = r9 << r14
            long r12 = r12 - r9
            long r16 = r16 - r12
        L_0x01df:
            r9 = r16
            r13 = r19
            goto L_0x01ef
        L_0x01e4:
            int r14 = r14 + 1
            r3 = 163(0xa3, float:2.28E-43)
            r9 = 0
            r10 = 1
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x0193
        L_0x01ed:
            r9 = r16
        L_0x01ef:
            r14 = -2147483648(0xffffffff80000000, double:NaN)
            int r3 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r3 < 0) goto L_0x0219
            r14 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r3 > 0) goto L_0x0219
            int r3 = (int) r9
            int[] r9 = r7.blockSampleSizes
            if (r2 != 0) goto L_0x0203
            goto L_0x0208
        L_0x0203:
            int r10 = r2 + -1
            r10 = r9[r10]
            int r3 = r3 + r10
        L_0x0208:
            r9[r2] = r3
            int[] r3 = r7.blockSampleSizes
            r3 = r3[r2]
            int r6 = r6 + r3
            int r2 = r2 + 1
            r3 = 163(0xa3, float:2.28E-43)
            r9 = 0
            r10 = 1
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x0177
        L_0x0219:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "EBML lacing sample size out of range."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0221:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "No valid varint length mask found"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0229:
            int[] r2 = r7.blockSampleSizes
            r3 = 1
            int r14 = r14 - r3
            int r3 = r7.blockTrackNumberLength
            int r1 = r1 - r3
            int r1 = r1 - r13
            int r1 = r1 - r6
            r2[r14] = r1
        L_0x0234:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.data
            r2 = 0
            byte r1 = r1[r2]
            int r1 = r1 << r5
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r7.scratch
            byte[] r2 = r2.data
            r3 = 1
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & r3
            r1 = r1 | r2
            long r2 = r7.clusterTimecodeUs
            long r9 = (long) r1
            long r9 = r7.scaleTimecodeToUs(r9)
            long r2 = r2 + r9
            r7.blockTimeUs = r2
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.data
            byte r1 = r1[r4]
            r1 = r1 & r5
            if (r1 != r5) goto L_0x025c
            r1 = 1
            goto L_0x025d
        L_0x025c:
            r1 = 0
        L_0x025d:
            int r2 = r11.type
            if (r2 == r4) goto L_0x0273
            r2 = 163(0xa3, float:2.28E-43)
            if (r0 != r2) goto L_0x0271
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r7.scratch
            byte[] r2 = r2.data
            byte r2 = r2[r4]
            r3 = 128(0x80, float:1.794E-43)
            r2 = r2 & r3
            if (r2 != r3) goto L_0x0271
            goto L_0x0273
        L_0x0271:
            r2 = 0
            goto L_0x0274
        L_0x0273:
            r2 = 1
        L_0x0274:
            if (r1 == 0) goto L_0x0279
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x027a
        L_0x0279:
            r1 = 0
        L_0x027a:
            r1 = r1 | r2
            r7.blockFlags = r1
            r7.blockState = r4
            r1 = 0
            r7.blockSampleIndex = r1
            goto L_0x029a
        L_0x0283:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unexpected lacing value: "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x029a:
            r1 = 163(0xa3, float:2.28E-43)
            if (r0 != r1) goto L_0x02cc
        L_0x029e:
            int r0 = r7.blockSampleIndex
            int r1 = r7.blockSampleCount
            if (r0 >= r1) goto L_0x02c8
            int[] r1 = r7.blockSampleSizes
            r0 = r1[r0]
            int r5 = r7.writeSampleData(r8, r11, r0)
            long r0 = r7.blockTimeUs
            int r2 = r7.blockSampleIndex
            int r3 = r11.defaultSampleDurationNs
            int r2 = r2 * r3
            int r2 = r2 / 1000
            long r2 = (long) r2
            long r2 = r2 + r0
            int r4 = r7.blockFlags
            r6 = 0
            r0 = r20
            r1 = r11
            r0.commitSampleToOutput(r1, r2, r4, r5, r6)
            int r0 = r7.blockSampleIndex
            r1 = 1
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x029e
        L_0x02c8:
            r0 = 0
            r7.blockState = r0
            goto L_0x02e3
        L_0x02cc:
            int r0 = r7.blockSampleIndex
            int r1 = r7.blockSampleCount
            if (r0 >= r1) goto L_0x02e3
            int[] r1 = r7.blockSampleSizes
            r2 = r1[r0]
            int r2 = r7.writeSampleData(r8, r11, r2)
            r1[r0] = r2
            int r0 = r7.blockSampleIndex
            r1 = 1
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x02cc
        L_0x02e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    /* access modifiers changed from: protected */
    public void handleBlockAdditionalData(Track track, int i, ExtractorInput extractorInput, int i2) throws IOException, InterruptedException {
        if (i != 4 || !CODEC_ID_VP9.equals(track.codecId)) {
            extractorInput.skipFully(i2);
            return;
        }
        this.blockAdditionalData.reset(i2);
        extractorInput.readFully(this.blockAdditionalData.data, 0, i2);
    }

    private void commitSampleToOutput(Track track, long j, int i, int i2, int i3) {
        if (track.trueHdSampleRechunker != null) {
            track.trueHdSampleRechunker.sampleMetadata(track, j, i, i2, i3);
        } else {
            if (CODEC_ID_SUBRIP.equals(track.codecId) || CODEC_ID_ASS.equals(track.codecId)) {
                if (this.blockSampleCount > 1) {
                    Log.m9w(TAG, "Skipping subtitle sample in laced block.");
                } else if (this.durationUs == -9223372036854775807L) {
                    Log.m9w(TAG, "Skipping subtitle sample with no duration.");
                } else {
                    setSubtitleEndTime(track.codecId, this.durationUs, this.subtitleSample.data);
                    TrackOutput trackOutput = track.output;
                    ParsableByteArray parsableByteArray = this.subtitleSample;
                    trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
                    i2 += this.subtitleSample.limit();
                }
            }
            if ((268435456 & i) != 0) {
                if (this.blockSampleCount > 1) {
                    i &= -268435457;
                } else {
                    int limit = this.blockAdditionalData.limit();
                    track.output.sampleData(this.blockAdditionalData, limit);
                    i2 += limit;
                }
            }
            track.output.sampleMetadata(j, i, i2, i3, track.cryptoData);
        }
        this.haveOutputSample = true;
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private int writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
            return finishWriteSampleData();
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & ByteCompanionObject.MIN_VALUE) != 128) {
                            this.sampleSignalByte = this.scratch.data[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i3 = 0;
                            }
                            bArr[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.data, 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                if (track.maxBlockAdditionId > 0) {
                    this.blockFlags |= 268435456;
                    this.blockAdditionalData.reset();
                    this.scratch.reset(4);
                    this.scratch.data[0] = (byte) ((i >> 24) & 255);
                    this.scratch.data[1] = (byte) ((i >> 16) & 255);
                    this.scratch.data[2] = (byte) ((i >> 8) & 255);
                    this.scratch.data[3] = (byte) (i & 255);
                    trackOutput.sampleData(this.scratch, 4);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit) {
                        break;
                    }
                    int writeToOutput = writeToOutput(extractorInput, trackOutput, limit - i9);
                    this.sampleBytesRead += writeToOutput;
                    this.sampleBytesWritten += writeToOutput;
                }
            } else {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        writeToTarget(extractorInput, bArr2, i11, i10);
                        this.sampleBytesRead += i10;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput2 = writeToOutput(extractorInput, trackOutput, i12);
                        this.sampleBytesRead += writeToOutput2;
                        this.sampleBytesWritten += writeToOutput2;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput2;
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void setSubtitleEndTime(java.lang.String r4, long r5, byte[] r7) {
        /*
            int r0 = r4.hashCode()
            r1 = 738597099(0x2c0618eb, float:1.9056378E-12)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001b
            r1 = 1422270023(0x54c61e47, float:6.807292E12)
            if (r0 == r1) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r0 = "S_TEXT/UTF8"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0025
            r4 = 0
            goto L_0x0026
        L_0x001b:
            java.lang.String r0 = "S_TEXT/ASS"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0025
            r4 = 1
            goto L_0x0026
        L_0x0025:
            r4 = -1
        L_0x0026:
            if (r4 == 0) goto L_0x003b
            if (r4 != r3) goto L_0x0035
            r0 = 10000(0x2710, double:4.9407E-320)
            java.lang.String r4 = "%01d:%02d:%02d:%02d"
            byte[] r4 = formatSubtitleTimecode(r5, r4, r0)
            r5 = 21
            goto L_0x0045
        L_0x0035:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            r4.<init>()
            throw r4
        L_0x003b:
            r0 = 1000(0x3e8, double:4.94E-321)
            java.lang.String r4 = "%02d:%02d:%02d,%03d"
            byte[] r4 = formatSubtitleTimecode(r5, r4, r0)
            r5 = 19
        L_0x0045:
            int r6 = r4.length
            java.lang.System.arraycopy(r4, r2, r7, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.setSubtitleEndTime(java.lang.String, long, byte[]):void");
    }

    private static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Assertions.checkArgument(j != -9223372036854775807L);
        int i = (int) (j / 3600000000L);
        long j3 = j - (((long) (i * 3600)) * 1000000);
        int i2 = (int) (j3 / 60000000);
        long j4 = j3 - (((long) (i2 * 60)) * 1000000);
        int i3 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - (((long) i3) * 1000000)) / j2))}));
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft <= 0) {
            return trackOutput.sampleData(extractorInput, i, false);
        }
        int min = Math.min(i, bytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, min);
        return min;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_AV1.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_ASS.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class TrueHdSampleRechunker {
        private int chunkFlags;
        private int chunkOffset;
        private int chunkSampleCount;
        private int chunkSize;
        private long chunkTimeUs;
        private boolean foundSyncframe;
        private final byte[] syncframePrefix = new byte[10];

        public void reset() {
            this.foundSyncframe = false;
            this.chunkSampleCount = 0;
        }

        public void startSample(ExtractorInput extractorInput) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 10);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                    this.foundSyncframe = true;
                }
            }
        }

        public void sampleMetadata(Track track, long j, int i, int i2, int i3) {
            if (this.foundSyncframe) {
                int i4 = this.chunkSampleCount;
                this.chunkSampleCount = i4 + 1;
                if (i4 == 0) {
                    this.chunkTimeUs = j;
                    this.chunkFlags = i;
                    this.chunkSize = 0;
                }
                this.chunkSize += i2;
                this.chunkOffset = i3;
                if (this.chunkSampleCount < 16) {
                    outputPendingSampleMetadata(track);
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.chunkSampleCount > 0) {
                track.output.sampleMetadata(this.chunkTimeUs, this.chunkFlags, this.chunkSize, this.chunkOffset, track.cryptoData);
                this.chunkSampleCount = 0;
            }
        }
    }

    private static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int maxBlockAdditionId;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public float projectionPosePitch;
        public float projectionPoseRoll;
        public float projectionPoseYaw;
        public int projectionType;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionType = -1;
            this.projectionPoseYaw = 0.0f;
            this.projectionPosePitch = 0.0f;
            this.projectionPoseRoll = 0.0f;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v52, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v11, resolved type: java.lang.String} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x01de, code lost:
            r26 = r1;
            r4 = "audio/raw";
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x0257, code lost:
            r4 = r16;
            r1 = null;
            r26 = -1;
            r31 = 4096;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:128:0x0303, code lost:
            r1 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:129:0x0304, code lost:
            r4 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:139:0x0320, code lost:
            r4 = r16;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:140:0x0322, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:141:0x0324, code lost:
            r26 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:142:0x0326, code lost:
            r31 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:143:0x0328, code lost:
            r2 = r0.flagDefault | 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:144:0x032d, code lost:
            if (r0.flagForced == false) goto L_0x0331;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x032f, code lost:
            r3 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:146:0x0331, code lost:
            r3 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:147:0x0332, code lost:
            r2 = r2 | r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x0337, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isAudio(r4) == false) goto L_0x035f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:149:0x0339, code lost:
            r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r31, r0.channelCount, r0.sampleRate, r26, r1, r0.drmInitData, r2, r0.language);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:151:0x0363, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isVideo(r4) == false) goto L_0x0465;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x0367, code lost:
            if (r0.displayUnit != 0) goto L_0x037b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:154:0x0369, code lost:
            r2 = r0.displayWidth;
            r3 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x036c, code lost:
            if (r2 != -1) goto L_0x0370;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:156:0x036e, code lost:
            r2 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:157:0x0370, code lost:
            r0.displayWidth = r2;
            r2 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:158:0x0374, code lost:
            if (r2 != -1) goto L_0x0378;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:159:0x0376, code lost:
            r2 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:160:0x0378, code lost:
            r0.displayHeight = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:161:0x037b, code lost:
            r3 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:162:0x037c, code lost:
            r5 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:163:0x0380, code lost:
            if (r5 == r3) goto L_0x0394;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:164:0x0382, code lost:
            r8 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:165:0x0384, code lost:
            if (r8 == r3) goto L_0x0394;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:166:0x0386, code lost:
            r37 = ((float) (r0.height * r5)) / ((float) (r0.width * r8));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:167:0x0394, code lost:
            r37 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:169:0x0398, code lost:
            if (r0.hasColorInfo == false) goto L_0x03ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x039a, code lost:
            r40 = new com.google.android.exoplayer2.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x03ac, code lost:
            r40 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x03ba, code lost:
            if ("htc_video_rotA-000".equals(r0.name) == false) goto L_0x03be;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x03bc, code lost:
            r9 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x03c6, code lost:
            if ("htc_video_rotA-090".equals(r0.name) == false) goto L_0x03cb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x03c8, code lost:
            r9 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x03d3, code lost:
            if ("htc_video_rotA-180".equals(r0.name) == false) goto L_0x03d8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x03d5, code lost:
            r9 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x03e0, code lost:
            if ("htc_video_rotA-270".equals(r0.name) == false) goto L_0x03e5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:183:0x03e2, code lost:
            r9 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x03e5, code lost:
            r9 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x03e8, code lost:
            if (r0.projectionType != 0) goto L_0x043a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x03f1, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x043a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x03f9, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x043a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x0401, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x0406;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x0403, code lost:
            r36 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x040e, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 90.0f) != 0) goto L_0x0413;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x0410, code lost:
            r36 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:198:0x041b, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -180.0f) == 0) goto L_0x0437;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x0425, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 180.0f) != 0) goto L_0x0428;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x0430, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -90.0f) != 0) goto L_0x043a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:203:0x0432, code lost:
            r36 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x0437, code lost:
            r36 = 180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:205:0x043a, code lost:
            r36 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x043c, code lost:
            r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r31, r0.width, r0.height, -1.0f, r1, r36, r37, r0.projectionData, r0.stereoMode, r40, r0.drmInitData);
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:208:0x0469, code lost:
            if ("application/x-subrip".equals(r4) == false) goto L_0x047a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x046b, code lost:
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r44), r4, r2, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:210:0x0477, code lost:
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:212:0x047e, code lost:
            if ("text/x-ssa".equals(r4) == false) goto L_0x04b3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x0480, code lost:
            r1 = new java.util.ArrayList(2);
            r1.add(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$300());
            r1.add(r0.codecPrivate);
            r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r2, r0.language, -1, r0.drmInitData, Long.MAX_VALUE, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:215:0x04b7, code lost:
            if ("application/vobsub".equals(r4) != false) goto L_0x04ce;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:217:0x04bd, code lost:
            if ("application/pgs".equals(r4) != false) goto L_0x04ce;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:219:0x04c3, code lost:
            if ("application/dvbsubs".equals(r4) == false) goto L_0x04c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:221:0x04cd, code lost:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:222:0x04ce, code lost:
            r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(java.lang.Integer.toString(r44), r4, (java.lang.String) null, -1, r2, r1, r0.language, r0.drmInitData);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:223:0x04e9, code lost:
            r2 = r43.track(r0.number, r5);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:224:0x04f6, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r43, int r44) throws com.google.android.exoplayer2.ParserException {
            /*
                r42 = this;
                r0 = r42
                java.lang.String r1 = r0.codecId
                int r2 = r1.hashCode()
                r3 = 4
                r5 = 1
                r6 = 2
                r7 = 0
                r8 = 3
                switch(r2) {
                    case -2095576542: goto L_0x015f;
                    case -2095575984: goto L_0x0155;
                    case -1985379776: goto L_0x014a;
                    case -1784763192: goto L_0x013f;
                    case -1730367663: goto L_0x0134;
                    case -1482641358: goto L_0x0129;
                    case -1482641357: goto L_0x011e;
                    case -1373388978: goto L_0x0113;
                    case -933872740: goto L_0x0108;
                    case -538363189: goto L_0x00fd;
                    case -538363109: goto L_0x00f2;
                    case -425012669: goto L_0x00e6;
                    case -356037306: goto L_0x00da;
                    case 62923557: goto L_0x00ce;
                    case 62923603: goto L_0x00c2;
                    case 62927045: goto L_0x00b6;
                    case 82318131: goto L_0x00ab;
                    case 82338133: goto L_0x00a0;
                    case 82338134: goto L_0x0095;
                    case 99146302: goto L_0x0089;
                    case 444813526: goto L_0x007d;
                    case 542569478: goto L_0x0071;
                    case 725957860: goto L_0x0065;
                    case 738597099: goto L_0x0059;
                    case 855502857: goto L_0x004d;
                    case 1422270023: goto L_0x0041;
                    case 1809237540: goto L_0x0036;
                    case 1950749482: goto L_0x002a;
                    case 1950789798: goto L_0x001e;
                    case 1951062397: goto L_0x0012;
                    default: goto L_0x0010;
                }
            L_0x0010:
                goto L_0x0169
            L_0x0012:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 12
                goto L_0x016a
            L_0x001e:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 22
                goto L_0x016a
            L_0x002a:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 17
                goto L_0x016a
            L_0x0036:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 3
                goto L_0x016a
            L_0x0041:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 25
                goto L_0x016a
            L_0x004d:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 8
                goto L_0x016a
            L_0x0059:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 26
                goto L_0x016a
            L_0x0065:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 24
                goto L_0x016a
            L_0x0071:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 20
                goto L_0x016a
            L_0x007d:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 10
                goto L_0x016a
            L_0x0089:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 28
                goto L_0x016a
            L_0x0095:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 1
                goto L_0x016a
            L_0x00a0:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 0
                goto L_0x016a
            L_0x00ab:
                java.lang.String r2 = "V_AV1"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 2
                goto L_0x016a
            L_0x00b6:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 19
                goto L_0x016a
            L_0x00c2:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 16
                goto L_0x016a
            L_0x00ce:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 13
                goto L_0x016a
            L_0x00da:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 21
                goto L_0x016a
            L_0x00e6:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 27
                goto L_0x016a
            L_0x00f2:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 7
                goto L_0x016a
            L_0x00fd:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 5
                goto L_0x016a
            L_0x0108:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 29
                goto L_0x016a
            L_0x0113:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 9
                goto L_0x016a
            L_0x011e:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 15
                goto L_0x016a
            L_0x0129:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 14
                goto L_0x016a
            L_0x0134:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 11
                goto L_0x016a
            L_0x013f:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 18
                goto L_0x016a
            L_0x014a:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 23
                goto L_0x016a
            L_0x0155:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 4
                goto L_0x016a
            L_0x015f:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0169
                r1 = 6
                goto L_0x016a
            L_0x0169:
                r1 = -1
            L_0x016a:
                java.lang.String r2 = ". Setting mimeType to "
                java.lang.String r10 = "Unsupported PCM bit depth: "
                java.lang.String r11 = "application/dvbsubs"
                java.lang.String r12 = "application/pgs"
                java.lang.String r13 = "application/vobsub"
                java.lang.String r14 = "text/x-ssa"
                java.lang.String r15 = "application/x-subrip"
                java.lang.String r16 = "audio/raw"
                r17 = 4096(0x1000, float:5.74E-42)
                java.lang.String r9 = "MatroskaExtractor"
                java.lang.String r4 = "audio/x-unknown"
                r18 = 0
                switch(r1) {
                    case 0: goto L_0x031e;
                    case 1: goto L_0x031b;
                    case 2: goto L_0x0318;
                    case 3: goto L_0x0315;
                    case 4: goto L_0x0307;
                    case 5: goto L_0x0307;
                    case 6: goto L_0x0307;
                    case 7: goto L_0x02f0;
                    case 8: goto L_0x02dc;
                    case 9: goto L_0x02c6;
                    case 10: goto L_0x02c3;
                    case 11: goto L_0x02b1;
                    case 12: goto L_0x026b;
                    case 13: goto L_0x0261;
                    case 14: goto L_0x0255;
                    case 15: goto L_0x0252;
                    case 16: goto L_0x024e;
                    case 17: goto L_0x024a;
                    case 18: goto L_0x023f;
                    case 19: goto L_0x023b;
                    case 20: goto L_0x023b;
                    case 21: goto L_0x0237;
                    case 22: goto L_0x022d;
                    case 23: goto L_0x01e6;
                    case 24: goto L_0x01ba;
                    case 25: goto L_0x01b7;
                    case 26: goto L_0x01b4;
                    case 27: goto L_0x01ab;
                    case 28: goto L_0x01a8;
                    case 29: goto L_0x018d;
                    default: goto L_0x0185;
                }
            L_0x0185:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unrecognized codec identifier."
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x018d:
                byte[] r1 = new byte[r3]
                byte[] r2 = r0.codecPrivate
                byte r3 = r2[r7]
                r1[r7] = r3
                byte r3 = r2[r5]
                r1[r5] = r3
                byte r3 = r2[r6]
                r1[r6] = r3
                byte r2 = r2[r8]
                r1[r8] = r2
                java.util.List r1 = java.util.Collections.singletonList(r1)
                r4 = r11
                goto L_0x0324
            L_0x01a8:
                r4 = r12
                goto L_0x0322
            L_0x01ab:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                r4 = r13
                goto L_0x0324
            L_0x01b4:
                r4 = r14
                goto L_0x0322
            L_0x01b7:
                r4 = r15
                goto L_0x0322
            L_0x01ba:
                int r1 = r0.audioBitDepth
                int r1 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x01de
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r10)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.m9w(r9, r1)
                goto L_0x0322
            L_0x01de:
                r26 = r1
                r4 = r16
                r1 = r18
                goto L_0x0326
            L_0x01e6:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r1.<init>((byte[]) r3)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x0217
                int r1 = r0.audioBitDepth
                int r1 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r1)
                if (r1 != 0) goto L_0x01de
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                r1.append(r10)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.m9w(r9, r1)
                goto L_0x0322
            L_0x0217:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.m9w(r9, r1)
                goto L_0x0322
            L_0x022d:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r16 = "audio/flac"
                goto L_0x0304
            L_0x0237:
                java.lang.String r16 = "audio/vnd.dts.hd"
                goto L_0x0320
            L_0x023b:
                java.lang.String r16 = "audio/vnd.dts"
                goto L_0x0320
            L_0x023f:
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r1 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r16 = "audio/true-hd"
                goto L_0x0320
            L_0x024a:
                java.lang.String r16 = "audio/eac3"
                goto L_0x0320
            L_0x024e:
                java.lang.String r16 = "audio/ac3"
                goto L_0x0320
            L_0x0252:
                java.lang.String r16 = "audio/mpeg"
                goto L_0x0257
            L_0x0255:
                java.lang.String r16 = "audio/mpeg-L2"
            L_0x0257:
                r4 = r16
                r1 = r18
                r26 = -1
                r31 = 4096(0x1000, float:5.74E-42)
                goto L_0x0328
            L_0x0261:
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r16 = "audio/mp4a-latm"
                goto L_0x0304
            L_0x026b:
                r17 = 5760(0x1680, float:8.071E-42)
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r8)
                byte[] r2 = r0.codecPrivate
                r1.add(r2)
                r2 = 8
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r2)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r9 = r0.codecDelayNs
                java.nio.ByteBuffer r3 = r3.putLong(r9)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)
                java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r2 = r2.order(r3)
                long r3 = r0.seekPreRollNs
                java.nio.ByteBuffer r2 = r2.putLong(r3)
                byte[] r2 = r2.array()
                r1.add(r2)
                java.lang.String r16 = "audio/opus"
                r4 = r16
                r26 = -1
                r31 = 5760(0x1680, float:8.071E-42)
                goto L_0x0328
            L_0x02b1:
                r17 = 8192(0x2000, float:1.14794E-41)
                byte[] r1 = r0.codecPrivate
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r16 = "audio/vorbis"
                r4 = r16
                r26 = -1
                r31 = 8192(0x2000, float:1.14794E-41)
                goto L_0x0328
            L_0x02c3:
                java.lang.String r16 = "video/x-unknown"
                goto L_0x0320
            L_0x02c6:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r2 = r1.first
                r16 = r2
                java.lang.String r16 = (java.lang.String) r16
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
                goto L_0x0304
            L_0x02dc:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                com.google.android.exoplayer2.video.HevcConfig r1 = com.google.android.exoplayer2.video.HevcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r1 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r1
                java.lang.String r16 = "video/hevc"
                goto L_0x0303
            L_0x02f0:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>((byte[]) r2)
                com.google.android.exoplayer2.video.AvcConfig r1 = com.google.android.exoplayer2.video.AvcConfig.parse(r1)
                java.util.List<byte[]> r2 = r1.initializationData
                int r1 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r1
                java.lang.String r16 = "video/avc"
            L_0x0303:
                r1 = r2
            L_0x0304:
                r4 = r16
                goto L_0x0324
            L_0x0307:
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x030e
                r1 = r18
                goto L_0x0312
            L_0x030e:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x0312:
                java.lang.String r16 = "video/mp4v-es"
                goto L_0x0304
            L_0x0315:
                java.lang.String r16 = "video/mpeg2"
                goto L_0x0320
            L_0x0318:
                java.lang.String r16 = "video/av01"
                goto L_0x0320
            L_0x031b:
                java.lang.String r16 = "video/x-vnd.on2.vp9"
                goto L_0x0320
            L_0x031e:
                java.lang.String r16 = "video/x-vnd.on2.vp8"
            L_0x0320:
                r4 = r16
            L_0x0322:
                r1 = r18
            L_0x0324:
                r26 = -1
            L_0x0326:
                r31 = -1
            L_0x0328:
                boolean r2 = r0.flagDefault
                r2 = r2 | r7
                boolean r3 = r0.flagForced
                if (r3 == 0) goto L_0x0331
                r3 = 2
                goto L_0x0332
            L_0x0331:
                r3 = 0
            L_0x0332:
                r2 = r2 | r3
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r4)
                if (r3 == 0) goto L_0x035f
                java.lang.String r19 = java.lang.Integer.toString(r44)
                r21 = 0
                r22 = -1
                int r3 = r0.channelCount
                int r6 = r0.sampleRate
                com.google.android.exoplayer2.drm.DrmInitData r7 = r0.drmInitData
                java.lang.String r8 = r0.language
                r20 = r4
                r23 = r31
                r24 = r3
                r25 = r6
                r27 = r1
                r28 = r7
                r29 = r2
                r30 = r8
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
                goto L_0x04e9
            L_0x035f:
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r4)
                if (r3 == 0) goto L_0x0465
                int r2 = r0.displayUnit
                if (r2 != 0) goto L_0x037b
                int r2 = r0.displayWidth
                r3 = -1
                if (r2 != r3) goto L_0x0370
                int r2 = r0.width
            L_0x0370:
                r0.displayWidth = r2
                int r2 = r0.displayHeight
                if (r2 != r3) goto L_0x0378
                int r2 = r0.height
            L_0x0378:
                r0.displayHeight = r2
                goto L_0x037c
            L_0x037b:
                r3 = -1
            L_0x037c:
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r5 = r0.displayWidth
                if (r5 == r3) goto L_0x0394
                int r8 = r0.displayHeight
                if (r8 == r3) goto L_0x0394
                int r2 = r0.height
                int r2 = r2 * r5
                float r2 = (float) r2
                int r5 = r0.width
                int r5 = r5 * r8
                float r5 = (float) r5
                float r2 = r2 / r5
                r37 = r2
                goto L_0x0396
            L_0x0394:
                r37 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x0396:
                boolean r2 = r0.hasColorInfo
                if (r2 == 0) goto L_0x03ac
                byte[] r2 = r42.getHdrStaticInfo()
                com.google.android.exoplayer2.video.ColorInfo r5 = new com.google.android.exoplayer2.video.ColorInfo
                int r8 = r0.colorSpace
                int r9 = r0.colorRange
                int r10 = r0.colorTransfer
                r5.<init>(r8, r9, r10, r2)
                r40 = r5
                goto L_0x03ae
            L_0x03ac:
                r40 = r18
            L_0x03ae:
                java.lang.String r2 = r0.name
                java.lang.String r5 = "htc_video_rotA-000"
                boolean r2 = r5.equals(r2)
                r5 = 180(0xb4, float:2.52E-43)
                r8 = 90
                if (r2 == 0) goto L_0x03be
                r9 = 0
                goto L_0x03e6
            L_0x03be:
                java.lang.String r2 = r0.name
                java.lang.String r9 = "htc_video_rotA-090"
                boolean r2 = r9.equals(r2)
                if (r2 == 0) goto L_0x03cb
                r9 = 90
                goto L_0x03e6
            L_0x03cb:
                java.lang.String r2 = r0.name
                java.lang.String r9 = "htc_video_rotA-180"
                boolean r2 = r9.equals(r2)
                if (r2 == 0) goto L_0x03d8
                r9 = 180(0xb4, float:2.52E-43)
                goto L_0x03e6
            L_0x03d8:
                java.lang.String r2 = r0.name
                java.lang.String r9 = "htc_video_rotA-270"
                boolean r2 = r9.equals(r2)
                if (r2 == 0) goto L_0x03e5
                r9 = 270(0x10e, float:3.78E-43)
                goto L_0x03e6
            L_0x03e5:
                r9 = -1
            L_0x03e6:
                int r2 = r0.projectionType
                if (r2 != 0) goto L_0x043a
                float r2 = r0.projectionPoseYaw
                r3 = 0
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x043a
                float r2 = r0.projectionPosePitch
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x043a
                float r2 = r0.projectionPoseRoll
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0406
                r36 = 0
                goto L_0x043c
            L_0x0406:
                float r2 = r0.projectionPosePitch
                r3 = 1119092736(0x42b40000, float:90.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0413
                r36 = 90
                goto L_0x043c
            L_0x0413:
                float r2 = r0.projectionPosePitch
                r3 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 == 0) goto L_0x0437
                float r2 = r0.projectionPosePitch
                r3 = 1127481344(0x43340000, float:180.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x0428
                goto L_0x0437
            L_0x0428:
                float r2 = r0.projectionPosePitch
                r3 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r2 = java.lang.Float.compare(r2, r3)
                if (r2 != 0) goto L_0x043a
                r7 = 270(0x10e, float:3.78E-43)
                r36 = 270(0x10e, float:3.78E-43)
                goto L_0x043c
            L_0x0437:
                r36 = 180(0xb4, float:2.52E-43)
                goto L_0x043c
            L_0x043a:
                r36 = r9
            L_0x043c:
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                int r2 = r0.width
                int r3 = r0.height
                r34 = -1082130432(0xffffffffbf800000, float:-1.0)
                byte[] r5 = r0.projectionData
                int r7 = r0.stereoMode
                com.google.android.exoplayer2.drm.DrmInitData r8 = r0.drmInitData
                r28 = r4
                r32 = r2
                r33 = r3
                r35 = r1
                r38 = r5
                r39 = r7
                r41 = r8
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41)
                r5 = 2
                goto L_0x04e9
            L_0x0465:
                boolean r3 = r15.equals(r4)
                if (r3 == 0) goto L_0x047a
                java.lang.String r1 = java.lang.Integer.toString(r44)
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r5 = r0.drmInitData
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r1, r4, r2, r3, r5)
            L_0x0477:
                r5 = 3
                goto L_0x04e9
            L_0x047a:
                boolean r3 = r14.equals(r4)
                if (r3 == 0) goto L_0x04b3
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r6)
                byte[] r3 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                r1.add(r3)
                byte[] r3 = r0.codecPrivate
                r1.add(r3)
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                java.lang.String r3 = r0.language
                r33 = -1
                com.google.android.exoplayer2.drm.DrmInitData r5 = r0.drmInitData
                r35 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r28 = r4
                r31 = r2
                r32 = r3
                r34 = r5
                r37 = r1
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34, r35, r37)
                goto L_0x0477
            L_0x04b3:
                boolean r3 = r13.equals(r4)
                if (r3 != 0) goto L_0x04ce
                boolean r3 = r12.equals(r4)
                if (r3 != 0) goto L_0x04ce
                boolean r3 = r11.equals(r4)
                if (r3 == 0) goto L_0x04c6
                goto L_0x04ce
            L_0x04c6:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unexpected MIME type."
                r1.<init>((java.lang.String) r2)
                throw r1
            L_0x04ce:
                java.lang.String r27 = java.lang.Integer.toString(r44)
                r29 = 0
                r30 = -1
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r5 = r0.drmInitData
                r28 = r4
                r31 = r2
                r32 = r1
                r33 = r3
                r34 = r5
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(r27, r28, r29, r30, r31, r32, r33, r34)
                goto L_0x0477
            L_0x04e9:
                int r2 = r0.number
                r3 = r43
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r3.track(r2, r5)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            order.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>("video/divx", (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] bArr = parsableByteArray.data;
                    for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                        if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                            return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length)));
                        }
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.m9w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>("video/x-unknown", (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    int i4 = i2 + bArr[i];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != MatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
