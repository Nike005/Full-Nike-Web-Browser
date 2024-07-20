package androidx.media2.exoplayer.external;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.media2.exoplayer.external.audio.AudioProcessor;
import androidx.media2.exoplayer.external.audio.AudioRendererEventListener;
import androidx.media2.exoplayer.external.drm.DrmSessionManager;
import androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto;
import androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector;
import androidx.media2.exoplayer.external.metadata.MetadataOutput;
import androidx.media2.exoplayer.external.metadata.MetadataRenderer;
import androidx.media2.exoplayer.external.text.TextOutput;
import androidx.media2.exoplayer.external.text.TextRenderer;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.video.MediaCodecVideoRenderer;
import androidx.media2.exoplayer.external.video.VideoRendererEventListener;
import androidx.media2.exoplayer.external.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    protected static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private long allowedVideoJoiningTimeMs;
    private final Context context;
    private DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private int extensionRendererMode;
    private MediaCodecSelector mediaCodecSelector;
    private boolean playClearSamplesWithoutKeys;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    /* access modifiers changed from: protected */
    public AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }

    /* access modifiers changed from: protected */
    public void buildMiscellaneousRenderers(Context context2, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context2) {
        this.context = context2;
        this.extensionRendererMode = 0;
        this.allowedVideoJoiningTimeMs = 5000;
        this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2) {
        this(context2, drmSessionManager2, 0);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, int i) {
        this(context2, i, 5000);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i) {
        this(context2, drmSessionManager2, i, 5000);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, int i, long j) {
        this(context2, (DrmSessionManager<FrameworkMediaCrypto>) null, i, j);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i, long j) {
        this.context = context2;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
        this.drmSessionManager = drmSessionManager2;
        this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
    }

    public DefaultRenderersFactory setExtensionRendererMode(int i) {
        this.extensionRendererMode = i;
        return this;
    }

    public DefaultRenderersFactory setPlayClearSamplesWithoutKeys(boolean z) {
        this.playClearSamplesWithoutKeys = z;
        return this;
    }

    public DefaultRenderersFactory setMediaCodecSelector(MediaCodecSelector mediaCodecSelector2) {
        this.mediaCodecSelector = mediaCodecSelector2;
        return this;
    }

    public DefaultRenderersFactory setAllowedVideoJoiningTimeMs(long j) {
        this.allowedVideoJoiningTimeMs = j;
        return this;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2) {
        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager3 = drmSessionManager2 == null ? this.drmSessionManager : drmSessionManager2;
        ArrayList arrayList = new ArrayList();
        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager4 = drmSessionManager3;
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, drmSessionManager4, this.playClearSamplesWithoutKeys, handler, videoRendererEventListener, this.allowedVideoJoiningTimeMs, arrayList);
        buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, drmSessionManager4, this.playClearSamplesWithoutKeys, buildAudioProcessors(), handler, audioRendererEventListener, arrayList);
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, arrayList);
        Handler handler2 = handler;
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }

    /* access modifiers changed from: protected */
    public void buildVideoRenderers(Context context2, int i, MediaCodecSelector mediaCodecSelector2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, long j, ArrayList<Renderer> arrayList) {
        int i2 = i;
        ArrayList<Renderer> arrayList2 = arrayList;
        arrayList2.add(new MediaCodecVideoRenderer(context2, mediaCodecSelector2, j, drmSessionManager2, z, handler, videoRendererEventListener, 50));
        if (i2 != 0) {
            int size = arrayList.size();
            if (i2 == 2) {
                size--;
            }
            try {
                arrayList2.add(size, (Renderer) Class.forName("androidx.media2.exoplayer.external.ext.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE}).newInstance(new Object[]{Long.valueOf(j), handler, videoRendererEventListener, 50}));
                Log.m6208i(TAG, "Loaded LibvpxVideoRenderer.");
            } catch (ClassNotFoundException unused) {
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating VP9 extension", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0061, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0069, code lost:
        throw new java.lang.RuntimeException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a6, code lost:
        throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0061 A[ExcHandler: Exception (r0v7 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009e A[ExcHandler: Exception (r0v6 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:20:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildAudioRenderers(android.content.Context r14, int r15, androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector r16, androidx.media2.exoplayer.external.drm.DrmSessionManager<androidx.media2.exoplayer.external.drm.FrameworkMediaCrypto> r17, boolean r18, androidx.media2.exoplayer.external.audio.AudioProcessor[] r19, android.os.Handler r20, androidx.media2.exoplayer.external.audio.AudioRendererEventListener r21, java.util.ArrayList<androidx.media2.exoplayer.external.Renderer> r22) {
        /*
            r13 = this;
            r0 = r15
            r10 = r22
            java.lang.String r11 = "DefaultRenderersFactory"
            androidx.media2.exoplayer.external.audio.MediaCodecAudioRenderer r12 = new androidx.media2.exoplayer.external.audio.MediaCodecAudioRenderer
            androidx.media2.exoplayer.external.audio.AudioCapabilities r8 = androidx.media2.exoplayer.external.audio.AudioCapabilities.getCapabilities(r14)
            r1 = r12
            r2 = r14
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r20
            r7 = r21
            r9 = r19
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r10.add(r12)
            if (r0 != 0) goto L_0x0022
            return
        L_0x0022:
            int r1 = r22.size()
            r2 = 2
            if (r0 != r2) goto L_0x002b
            int r1 = r1 + -1
        L_0x002b:
            r0 = 0
            r3 = 3
            r4 = 1
            java.lang.String r5 = "androidx.media2.exoplayer.external.ext.opus.LibopusAudioRenderer"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioRendererEventListener> r7 = androidx.media2.exoplayer.external.audio.AudioRendererEventListener.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioProcessor[]> r7 = androidx.media2.exoplayer.external.audio.AudioProcessor[].class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.reflect.Constructor r5 = r5.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            r6[r0] = r20     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            r6[r4] = r21     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            r6[r2] = r19     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            java.lang.Object r5 = r5.newInstance(r6)     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            androidx.media2.exoplayer.external.Renderer r5 = (androidx.media2.exoplayer.external.Renderer) r5     // Catch:{ ClassNotFoundException -> 0x006a, Exception -> 0x0061 }
            int r6 = r1 + 1
            r10.add(r1, r5)     // Catch:{ ClassNotFoundException -> 0x005f, Exception -> 0x0061 }
            java.lang.String r1 = "Loaded LibopusAudioRenderer."
            androidx.media2.exoplayer.external.util.Log.m6208i(r11, r1)     // Catch:{ ClassNotFoundException -> 0x005f, Exception -> 0x0061 }
            goto L_0x006b
        L_0x005f:
            r1 = r6
            goto L_0x006a
        L_0x0061:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating Opus extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x006a:
            r6 = r1
        L_0x006b:
            java.lang.String r1 = "androidx.media2.exoplayer.external.ext.flac.LibflacAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r5[r0] = r7     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioRendererEventListener> r7 = androidx.media2.exoplayer.external.audio.AudioRendererEventListener.class
            r5[r4] = r7     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioProcessor[]> r7 = androidx.media2.exoplayer.external.audio.AudioProcessor[].class
            r5[r2] = r7     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            r5[r0] = r20     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            r5[r4] = r21     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            r5[r2] = r19     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            java.lang.Object r1 = r1.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            androidx.media2.exoplayer.external.Renderer r1 = (androidx.media2.exoplayer.external.Renderer) r1     // Catch:{ ClassNotFoundException -> 0x00a7, Exception -> 0x009e }
            int r5 = r6 + 1
            r10.add(r6, r1)     // Catch:{ ClassNotFoundException -> 0x009c, Exception -> 0x009e }
            java.lang.String r1 = "Loaded LibflacAudioRenderer."
            androidx.media2.exoplayer.external.util.Log.m6208i(r11, r1)     // Catch:{ ClassNotFoundException -> 0x009c, Exception -> 0x009e }
            goto L_0x00a8
        L_0x009c:
            r6 = r5
            goto L_0x00a7
        L_0x009e:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FLAC extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00a7:
            r5 = r6
        L_0x00a8:
            java.lang.String r1 = "androidx.media2.exoplayer.external.ext.ffmpeg.FfmpegAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioRendererEventListener> r7 = androidx.media2.exoplayer.external.audio.AudioRendererEventListener.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Class<androidx.media2.exoplayer.external.audio.AudioProcessor[]> r7 = androidx.media2.exoplayer.external.audio.AudioProcessor[].class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            r3[r0] = r20     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            r3[r4] = r21     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            r3[r2] = r19     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.Object r0 = r1.newInstance(r3)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            androidx.media2.exoplayer.external.Renderer r0 = (androidx.media2.exoplayer.external.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            r10.add(r5, r0)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            java.lang.String r0 = "Loaded FfmpegAudioRenderer."
            androidx.media2.exoplayer.external.util.Log.m6208i(r11, r0)     // Catch:{ ClassNotFoundException -> 0x00e0, Exception -> 0x00d7 }
            goto L_0x00e0
        L_0x00d7:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FFmpeg extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, int, androidx.media2.exoplayer.external.mediacodec.MediaCodecSelector, androidx.media2.exoplayer.external.drm.DrmSessionManager, boolean, androidx.media2.exoplayer.external.audio.AudioProcessor[], android.os.Handler, androidx.media2.exoplayer.external.audio.AudioRendererEventListener, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    public void buildTextRenderers(Context context2, TextOutput textOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildMetadataRenderers(Context context2, MetadataOutput metadataOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildCameraMotionRenderers(Context context2, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new CameraMotionRenderer());
    }
}
