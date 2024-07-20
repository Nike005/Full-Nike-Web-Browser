package acr.browser.lightning.utils;

import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.anthonycr.bonsai.Schedulers;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class FileUtils {
    private static final String TAG = "FileUtils";

    public static long megabytesToBytes(long j) {
        return j * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static void writeBundleToStorage(final Application application, final Bundle bundle, final String str) {
        Schedulers.m6232io().execute(new Runnable() {
            public void run() {
                FileOutputStream fileOutputStream = null;
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(application.getFilesDir(), str));
                    try {
                        Parcel obtain = Parcel.obtain();
                        obtain.writeBundle(bundle);
                        fileOutputStream2.write(obtain.marshall());
                        fileOutputStream2.flush();
                        obtain.recycle();
                        C3245Utils.close((Closeable) fileOutputStream2);
                    } catch (IOException unused) {
                        fileOutputStream = fileOutputStream2;
                        try {
                            Log.e(FileUtils.TAG, "Unable to write bundle to storage");
                            C3245Utils.close((Closeable) fileOutputStream);
                        } catch (Throwable th) {
                            th = th;
                            C3245Utils.close((Closeable) fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        C3245Utils.close((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (IOException unused2) {
                    Log.e(FileUtils.TAG, "Unable to write bundle to storage");
                    C3245Utils.close((Closeable) fileOutputStream);
                }
            }
        });
    }

    public static void deleteBundleInStorage(Application application, String str) {
        File file = new File(application.getFilesDir(), str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static Bundle readBundleFromStorage(Application application, String str) {
        FileInputStream fileInputStream;
        File file = new File(application.getFilesDir(), str);
        try {
            fileInputStream = new FileInputStream(file);
            try {
                Parcel obtain = Parcel.obtain();
                int size = (int) fileInputStream.getChannel().size();
                byte[] bArr = new byte[size];
                fileInputStream.read(bArr, 0, size);
                obtain.unmarshall(bArr, 0, size);
                obtain.setDataPosition(0);
                Bundle readBundle = obtain.readBundle(ClassLoader.getSystemClassLoader());
                readBundle.putAll(readBundle);
                obtain.recycle();
                file.delete();
                C3245Utils.close((Closeable) fileInputStream);
                return readBundle;
            } catch (FileNotFoundException unused) {
                Log.e(TAG, "Unable to read bundle from storage");
                file.delete();
                C3245Utils.close((Closeable) fileInputStream);
                return null;
            } catch (IOException e) {
                e = e;
                try {
                    e.printStackTrace();
                    file.delete();
                    C3245Utils.close((Closeable) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                }
            }
        } catch (FileNotFoundException unused2) {
            fileInputStream = null;
            Log.e(TAG, "Unable to read bundle from storage");
            file.delete();
            C3245Utils.close((Closeable) fileInputStream);
            return null;
        } catch (IOException e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            file.delete();
            C3245Utils.close((Closeable) fileInputStream);
            return null;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            fileInputStream = null;
            th = th3;
            file.delete();
            C3245Utils.close((Closeable) fileInputStream);
            throw th;
        }
    }

    public static void writeCrashToStorage(Throwable th) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), th.getClass().getSimpleName() + '_' + System.currentTimeMillis() + ".txt");
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                th.printStackTrace(new PrintStream(fileOutputStream2));
                fileOutputStream2.flush();
                C3245Utils.close((Closeable) fileOutputStream2);
            } catch (IOException unused) {
                fileOutputStream = fileOutputStream2;
                try {
                    Log.e(TAG, "Unable to write bundle to storage");
                    C3245Utils.close((Closeable) fileOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    C3245Utils.close((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                C3245Utils.close((Closeable) fileOutputStream);
                throw th;
            }
        } catch (IOException unused2) {
            Log.e(TAG, "Unable to write bundle to storage");
            C3245Utils.close((Closeable) fileOutputStream);
        }
    }

    public static String readStringFromStream(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return sb.toString();
            }
            sb.append(readLine);
        }
    }
}
