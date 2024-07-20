package androidx.media2.player.exoplayer;

import android.content.Context;
import android.os.Handler;
import androidx.media2.exoplayer.external.Renderer;
import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;
import androidx.media2.exoplayer.external.audio.AudioSink;
import androidx.media2.exoplayer.external.audio.MediaCodecAudioRenderer;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector;
import androidx.media2.exoplayer.external.metadata.MetadataOutput;
import androidx.media2.exoplayer.external.metadata.MetadataRenderer;
import androidx.media2.exoplayer.external.text.TextOutput;
import androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;

final class RenderersFactory implements androidx.media2.exoplayer.external.RenderersFactory {
    public static final int AUDIO_RENDERER_INDEX = 1;
    private static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    private static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    public static final int METADATA_RENDERER_INDEX = 3;
    public static final int TEXT_RENDERER_INDEX = 2;
    public static final int VIDEO_RENDERER_INDEX = 0;
    private final AudioSink mAudioSink;
    private final Context mContext;
    private final TextRenderer mTextRenderer;

    RenderersFactory(Context context, AudioSink audioSink, TextRenderer textRenderer) {
        this.mContext = context;
        this.mAudioSink = audioSink;
        this.mTextRenderer = textRenderer;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        return new Renderer[]{new MediaCodecVideoRenderer(this.mContext, MediaCodecSelector.DEFAULT, 5000, drmSessionManager, false, handler, videoRendererEventListener, 50), new MediaCodecAudioRenderer(this.mContext, MediaCodecSelector.DEFAULT, drmSessionManager, false, handler, audioRendererEventListener, this.mAudioSink), this.mTextRenderer, new MetadataRenderer(metadataOutput, handler.getLooper(), new Id3MetadataDecoderFactory())};
    }
}
