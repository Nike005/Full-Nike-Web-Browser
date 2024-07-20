package androidx.media2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.util.Log;
import androidx.media.AudioAttributesCompat;

public class AudioFocusHandler {
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioFocusHandler";
    private final AudioFocusHandlerImpl mImpl;

    interface AudioFocusHandlerImpl {
        void close();

        void onPauseRequested();

        boolean onPlayRequested();

        void onPlayerStateChanged(int i);

        void sendIntent(Intent intent);
    }

    AudioFocusHandler(Context context, MediaSession2 mediaSession2) {
        this.mImpl = new AudioFocusHandlerImplBase(context, mediaSession2);
    }

    public boolean onPlayRequested() {
        return this.mImpl.onPlayRequested();
    }

    public void onPauseRequested() {
        this.mImpl.onPauseRequested();
    }

    public void onPlayerStateChanged(int i) {
        this.mImpl.onPlayerStateChanged(i);
    }

    public void close() {
        this.mImpl.close();
    }

    public void sendIntent(Intent intent) {
        this.mImpl.sendIntent(intent);
    }

    private static class AudioFocusHandlerImplBase implements AudioFocusHandlerImpl {
        private static final float VOLUME_DUCK_FACTOR = 0.2f;
        AudioAttributesCompat mAudioAttributes;
        private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioFocusListener();
        private final AudioManager mAudioManager;
        private final BroadcastReceiver mBecomingNoisyIntentReceiver = new NoisyIntentReceiver();
        private final Context mContext;
        private int mCurrentFocusGainType;
        boolean mHasRegisteredReceiver;
        private final IntentFilter mIntentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        final Object mLock = new Object();
        boolean mResumeWhenAudioFocusGain;
        final MediaSession2 mSession;

        AudioFocusHandlerImplBase(Context context, MediaSession2 mediaSession2) {
            this.mContext = context;
            this.mSession = mediaSession2;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }

        private AudioAttributesCompat getAudioAttributesNotLocked() {
            MediaPlayerConnector playerConnector = this.mSession.getPlayerConnector();
            if (playerConnector == null || (playerConnector instanceof BaseRemoteMediaPlayerConnector)) {
                return null;
            }
            return playerConnector.getAudioAttributes();
        }

        public boolean onPlayRequested() {
            boolean z;
            MediaPlayerConnector playerConnector;
            AudioAttributesCompat audioAttributesNotLocked = getAudioAttributesNotLocked();
            synchronized (this.mLock) {
                if (audioAttributesNotLocked == null) {
                    this.mAudioAttributes = null;
                    abandonAudioFocusLocked();
                    z = true;
                } else {
                    this.mAudioAttributes = audioAttributesNotLocked;
                    z = requestAudioFocusLocked();
                }
            }
            if (audioAttributesNotLocked == null && (playerConnector = this.mSession.getPlayerConnector()) != null) {
                playerConnector.setPlayerVolume(0.0f);
            }
            return z;
        }

        public void onPauseRequested() {
            synchronized (this.mLock) {
                this.mResumeWhenAudioFocusGain = false;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x007d, code lost:
            if (r0 != null) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
            r0 = r6.mSession.getPlayerConnector();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
            if (r0 == null) goto L_0x008b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0087, code lost:
            r0.setPlayerVolume(0.0f);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x008b, code lost:
            if (r2 == false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x008d, code lost:
            r6.mSession.pause();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void onPlayingNotLocked() {
            /*
                r6 = this;
                androidx.media.AudioAttributesCompat r0 = r6.getAudioAttributesNotLocked()
                java.lang.Object r1 = r6.mLock
                monitor-enter(r1)
                int r2 = convertAudioAttributesToFocusGain(r0)     // Catch:{ all -> 0x0093 }
                androidx.media.AudioAttributesCompat r3 = r6.mAudioAttributes     // Catch:{ all -> 0x0093 }
                boolean r3 = androidx.core.util.ObjectsCompat.equals(r3, r0)     // Catch:{ all -> 0x0093 }
                if (r3 == 0) goto L_0x001e
                int r3 = r6.mCurrentFocusGainType     // Catch:{ all -> 0x0093 }
                if (r2 != r3) goto L_0x001e
                if (r0 == 0) goto L_0x001c
                r6.registerReceiverLocked()     // Catch:{ all -> 0x0093 }
            L_0x001c:
                monitor-exit(r1)     // Catch:{ all -> 0x0093 }
                return
            L_0x001e:
                java.lang.String r3 = "AudioFocusHandler"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
                r4.<init>()     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = "Expected "
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                androidx.media.AudioAttributesCompat r5 = r6.mAudioAttributes     // Catch:{ all -> 0x0093 }
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = " and audioFocusGainType="
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                int r5 = r6.mCurrentFocusGainType     // Catch:{ all -> 0x0093 }
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = " when playback is started, but actually "
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                r4.append(r0)     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = " and audioFocusGainType="
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                int r5 = r6.mCurrentFocusGainType     // Catch:{ all -> 0x0093 }
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = ". Use"
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                java.lang.String r5 = " MediaSession2#play() for starting playback."
                r4.append(r5)     // Catch:{ all -> 0x0093 }
                java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0093 }
                android.util.Log.w(r3, r4)     // Catch:{ all -> 0x0093 }
                r6.mAudioAttributes = r0     // Catch:{ all -> 0x0093 }
                int r3 = r6.mCurrentFocusGainType     // Catch:{ all -> 0x0093 }
                if (r3 == r2) goto L_0x007b
                if (r2 != 0) goto L_0x0068
                r6.abandonAudioFocusLocked()     // Catch:{ all -> 0x0093 }
                goto L_0x007b
            L_0x0068:
                boolean r2 = r6.requestAudioFocusLocked()     // Catch:{ all -> 0x0093 }
                if (r2 == 0) goto L_0x0072
                r6.registerReceiverLocked()     // Catch:{ all -> 0x0093 }
                goto L_0x007b
            L_0x0072:
                java.lang.String r2 = "AudioFocusHandler"
                java.lang.String r3 = "Playback is started without audio focus, and requesting audio focus is failed. Forcefully pausing playback"
                android.util.Log.e(r2, r3)     // Catch:{ all -> 0x0093 }
                r2 = 1
                goto L_0x007c
            L_0x007b:
                r2 = 0
            L_0x007c:
                monitor-exit(r1)     // Catch:{ all -> 0x0093 }
                if (r0 != 0) goto L_0x008b
                androidx.media2.MediaSession2 r0 = r6.mSession
                androidx.media2.MediaPlayerConnector r0 = r0.getPlayerConnector()
                if (r0 == 0) goto L_0x008b
                r1 = 0
                r0.setPlayerVolume(r1)
            L_0x008b:
                if (r2 == 0) goto L_0x0092
                androidx.media2.MediaSession2 r0 = r6.mSession
                r0.pause()
            L_0x0092:
                return
            L_0x0093:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0093 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.onPlayingNotLocked():void");
        }

        public void onPlayerStateChanged(int i) {
            if (i == 0) {
                synchronized (this.mLock) {
                    abandonAudioFocusLocked();
                }
            } else if (i == 1) {
                synchronized (this.mLock) {
                    unregisterReceiverLocked();
                }
            } else if (i == 2) {
                onPlayingNotLocked();
            } else if (i == 3) {
                close();
            }
        }

        public void close() {
            synchronized (this.mLock) {
                unregisterReceiverLocked();
                abandonAudioFocusLocked();
            }
        }

        public void sendIntent(Intent intent) {
            this.mBecomingNoisyIntentReceiver.onReceive(this.mContext, intent);
        }

        private boolean requestAudioFocusLocked() {
            int convertAudioAttributesToFocusGain = convertAudioAttributesToFocusGain(this.mAudioAttributes);
            if (convertAudioAttributesToFocusGain == 0) {
                return true;
            }
            int requestAudioFocus = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, this.mAudioAttributes.getVolumeControlStream(), convertAudioAttributesToFocusGain);
            if (requestAudioFocus == 1) {
                this.mCurrentFocusGainType = convertAudioAttributesToFocusGain;
            } else {
                Log.w(AudioFocusHandler.TAG, "requestAudioFocus(" + convertAudioAttributesToFocusGain + ") failed (return=" + requestAudioFocus + ") playback wouldn't start.");
                this.mCurrentFocusGainType = 0;
            }
            this.mResumeWhenAudioFocusGain = false;
            if (this.mCurrentFocusGainType != 0) {
                return true;
            }
            return false;
        }

        private void abandonAudioFocusLocked() {
            if (this.mCurrentFocusGainType != 0) {
                this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
                this.mCurrentFocusGainType = 0;
                this.mResumeWhenAudioFocusGain = false;
            }
        }

        private void registerReceiverLocked() {
            if (!this.mHasRegisteredReceiver) {
                this.mContext.registerReceiver(this.mBecomingNoisyIntentReceiver, this.mIntentFilter);
                this.mHasRegisteredReceiver = true;
            }
        }

        private void unregisterReceiverLocked() {
            if (this.mHasRegisteredReceiver) {
                this.mContext.unregisterReceiver(this.mBecomingNoisyIntentReceiver);
                this.mHasRegisteredReceiver = false;
            }
        }

        private static int convertAudioAttributesToFocusGain(AudioAttributesCompat audioAttributesCompat) {
            if (audioAttributesCompat == null) {
                return 0;
            }
            switch (audioAttributesCompat.getUsage()) {
                case 0:
                    Log.w(AudioFocusHandler.TAG, "Specify a proper usage in the audio attributes for audio focus handling. Using AUDIOFOCUS_GAIN by default.");
                    return 1;
                case 1:
                case 14:
                    return 1;
                case 2:
                case 4:
                    return 2;
                case 3:
                    return 0;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 12:
                case 13:
                    break;
                case 11:
                    if (audioAttributesCompat.getContentType() == 1) {
                        return 2;
                    }
                    break;
                case 16:
                    return 4;
                default:
                    Log.w(AudioFocusHandler.TAG, "Unidentified AudioAttribute " + audioAttributesCompat);
                    return 0;
            }
            return 3;
        }

        private class NoisyIntentReceiver extends BroadcastReceiver {
            NoisyIntentReceiver() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
                r2 = r1.this$0.mLock;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
                monitor-enter(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
                if (r1.this$0.mAudioAttributes != null) goto L_0x0027;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
                monitor-exit(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
                r3 = r1.this$0.mAudioAttributes.getUsage();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
                monitor-exit(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
                if (r3 == 1) goto L_0x004f;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
                if (r3 == 14) goto L_0x0038;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:23:0x0038, code lost:
                r2 = r1.this$0.mSession.getPlayerConnector();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:24:0x0040, code lost:
                if (r2 == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x0042, code lost:
                r2.setPlayerVolume(r2.getPlayerVolume() * androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.VOLUME_DUCK_FACTOR);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:0x004f, code lost:
                r1.this$0.mSession.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
                if ("android.media.AUDIO_BECOMING_NOISY".equals(r3.getAction()) == false) goto L_?;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onReceive(android.content.Context r2, android.content.Intent r3) {
                /*
                    r1 = this;
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r2 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r2 = r2.mLock
                    monitor-enter(r2)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x005b }
                    boolean r0 = r0.mHasRegisteredReceiver     // Catch:{ all -> 0x005b }
                    if (r0 != 0) goto L_0x000d
                    monitor-exit(r2)     // Catch:{ all -> 0x005b }
                    return
                L_0x000d:
                    monitor-exit(r2)     // Catch:{ all -> 0x005b }
                    java.lang.String r2 = "android.media.AUDIO_BECOMING_NOISY"
                    java.lang.String r3 = r3.getAction()
                    boolean r2 = r2.equals(r3)
                    if (r2 == 0) goto L_0x005a
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r2 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r2 = r2.mLock
                    monitor-enter(r2)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r3 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x0057 }
                    androidx.media.AudioAttributesCompat r3 = r3.mAudioAttributes     // Catch:{ all -> 0x0057 }
                    if (r3 != 0) goto L_0x0027
                    monitor-exit(r2)     // Catch:{ all -> 0x0057 }
                    return
                L_0x0027:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r3 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x0057 }
                    androidx.media.AudioAttributesCompat r3 = r3.mAudioAttributes     // Catch:{ all -> 0x0057 }
                    int r3 = r3.getUsage()     // Catch:{ all -> 0x0057 }
                    monitor-exit(r2)     // Catch:{ all -> 0x0057 }
                    r2 = 1
                    if (r3 == r2) goto L_0x004f
                    r2 = 14
                    if (r3 == r2) goto L_0x0038
                    goto L_0x005a
                L_0x0038:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r2 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r2 = r2.mSession
                    androidx.media2.MediaPlayerConnector r2 = r2.getPlayerConnector()
                    if (r2 == 0) goto L_0x005a
                    float r3 = r2.getPlayerVolume()
                    r0 = 1045220557(0x3e4ccccd, float:0.2)
                    float r3 = r3 * r0
                    r2.setPlayerVolume(r3)
                    goto L_0x005a
                L_0x004f:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r2 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r2 = r2.mSession
                    r2.pause()
                    goto L_0x005a
                L_0x0057:
                    r3 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x0057 }
                    throw r3
                L_0x005a:
                    return
                L_0x005b:
                    r3 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x005b }
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.NoisyIntentReceiver.onReceive(android.content.Context, android.content.Intent):void");
            }
        }

        private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
            private float mPlayerDuckingVolume;
            private float mPlayerVolumeBeforeDucking;

            AudioFocusListener() {
            }

            /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
                return;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:67:0x009e, code lost:
                if (r1 == false) goto L_0x00a8;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:68:0x00a0, code lost:
                r3.this$0.mSession.pause();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:69:0x00a8, code lost:
                r4 = r3.this$0.mSession.getPlayerConnector();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:70:0x00b0, code lost:
                if (r4 == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:71:0x00b2, code lost:
                r0 = r4.getPlayerVolume();
                r1 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.VOLUME_DUCK_FACTOR * r0;
                r2 = r3.this$0.mLock;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:72:0x00bf, code lost:
                monitor-enter(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
                r3.mPlayerVolumeBeforeDucking = r0;
                r3.mPlayerDuckingVolume = r1;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:75:0x00c4, code lost:
                monitor-exit(r2);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:76:0x00c5, code lost:
                r4.setPlayerVolume(r1);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onAudioFocusChange(int r4) {
                /*
                    r3 = this;
                    r0 = -3
                    r1 = 0
                    r2 = 1
                    if (r4 == r0) goto L_0x0085
                    r0 = -2
                    if (r4 == r0) goto L_0x0070
                    r0 = -1
                    if (r4 == r0) goto L_0x005b
                    if (r4 == r2) goto L_0x000f
                    goto L_0x00cc
                L_0x000f:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    int r4 = r4.getPlayerState()
                    if (r4 != r2) goto L_0x0034
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x0031 }
                    boolean r0 = r0.mResumeWhenAudioFocusGain     // Catch:{ all -> 0x0031 }
                    if (r0 != 0) goto L_0x0027
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    goto L_0x00cc
                L_0x0027:
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    r4.play()
                    goto L_0x00cc
                L_0x0031:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x0031 }
                    throw r0
                L_0x0034:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    androidx.media2.MediaPlayerConnector r4 = r4.getPlayerConnector()
                    if (r4 == 0) goto L_0x00cc
                    float r0 = r4.getPlayerVolume()
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r1 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r1 = r1.mLock
                    monitor-enter(r1)
                    float r2 = r3.mPlayerDuckingVolume     // Catch:{ all -> 0x0058 }
                    int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r0 == 0) goto L_0x0050
                    monitor-exit(r1)     // Catch:{ all -> 0x0058 }
                    goto L_0x00cc
                L_0x0050:
                    float r0 = r3.mPlayerVolumeBeforeDucking     // Catch:{ all -> 0x0058 }
                    monitor-exit(r1)     // Catch:{ all -> 0x0058 }
                    r4.setPlayerVolume(r0)
                    goto L_0x00cc
                L_0x0058:
                    r4 = move-exception
                    monitor-exit(r1)     // Catch:{ all -> 0x0058 }
                    throw r4
                L_0x005b:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    r4.pause()
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x006d }
                    r0.mResumeWhenAudioFocusGain = r1     // Catch:{ all -> 0x006d }
                    monitor-exit(r4)     // Catch:{ all -> 0x006d }
                    goto L_0x00cc
                L_0x006d:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x006d }
                    throw r0
                L_0x0070:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    r4.pause()
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x0082 }
                    r0.mResumeWhenAudioFocusGain = r2     // Catch:{ all -> 0x0082 }
                    monitor-exit(r4)     // Catch:{ all -> 0x0082 }
                    goto L_0x00cc
                L_0x0082:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x0082 }
                    throw r0
                L_0x0085:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r4 = r4.mLock
                    monitor-enter(r4)
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x00cd }
                    androidx.media.AudioAttributesCompat r0 = r0.mAudioAttributes     // Catch:{ all -> 0x00cd }
                    if (r0 != 0) goto L_0x0092
                    monitor-exit(r4)     // Catch:{ all -> 0x00cd }
                    goto L_0x00cc
                L_0x0092:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r0 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this     // Catch:{ all -> 0x00cd }
                    androidx.media.AudioAttributesCompat r0 = r0.mAudioAttributes     // Catch:{ all -> 0x00cd }
                    int r0 = r0.getContentType()     // Catch:{ all -> 0x00cd }
                    if (r0 != r2) goto L_0x009d
                    r1 = 1
                L_0x009d:
                    monitor-exit(r4)     // Catch:{ all -> 0x00cd }
                    if (r1 == 0) goto L_0x00a8
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    r4.pause()
                    goto L_0x00cc
                L_0x00a8:
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r4 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    androidx.media2.MediaSession2 r4 = r4.mSession
                    androidx.media2.MediaPlayerConnector r4 = r4.getPlayerConnector()
                    if (r4 == 0) goto L_0x00cc
                    float r0 = r4.getPlayerVolume()
                    r1 = 1045220557(0x3e4ccccd, float:0.2)
                    float r1 = r1 * r0
                    androidx.media2.AudioFocusHandler$AudioFocusHandlerImplBase r2 = androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.this
                    java.lang.Object r2 = r2.mLock
                    monitor-enter(r2)
                    r3.mPlayerVolumeBeforeDucking = r0     // Catch:{ all -> 0x00c9 }
                    r3.mPlayerDuckingVolume = r1     // Catch:{ all -> 0x00c9 }
                    monitor-exit(r2)     // Catch:{ all -> 0x00c9 }
                    r4.setPlayerVolume(r1)
                    goto L_0x00cc
                L_0x00c9:
                    r4 = move-exception
                    monitor-exit(r2)     // Catch:{ all -> 0x00c9 }
                    throw r4
                L_0x00cc:
                    return
                L_0x00cd:
                    r0 = move-exception
                    monitor-exit(r4)     // Catch:{ all -> 0x00cd }
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.AudioFocusHandler.AudioFocusHandlerImplBase.AudioFocusListener.onAudioFocusChange(int):void");
            }
        }
    }
}
