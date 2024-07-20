package androidx.media2.player.exoplayer;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

final class FileDescriptorUtil {
    private static final int SEEK_SET = 0;
    private static Method sCloseMethodV14;
    private static Method sDupMethodV14;
    private static Method sLseekMethodV14;
    private static final Object sPosixLockV14 = new Object();
    private static Object sPosixObjectV14;

    public static FileDescriptor dup(FileDescriptor fileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            return dupV21(fileDescriptor);
        }
        return dupV14(fileDescriptor);
    }

    public static void seek(FileDescriptor fileDescriptor, long j) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            seekV21(fileDescriptor, j);
        } else {
            seekV14(fileDescriptor, j);
        }
    }

    public static void close(FileDescriptor fileDescriptor) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            closeV21(fileDescriptor);
        } else {
            closeV14(fileDescriptor);
        }
    }

    private static FileDescriptor dupV21(FileDescriptor fileDescriptor) throws IOException {
        try {
            return Os.dup(fileDescriptor);
        } catch (Exception e) {
            throw new IOException("Failed to dup the file descriptor", e);
        }
    }

    private static FileDescriptor dupV14(FileDescriptor fileDescriptor) throws IOException {
        Object obj;
        Method method;
        try {
            synchronized (sPosixLockV14) {
                ensurePosixObjectsInitialized();
                obj = sPosixObjectV14;
                method = sDupMethodV14;
            }
            return (FileDescriptor) method.invoke(obj, new Object[]{fileDescriptor});
        } catch (Exception e) {
            throw new IOException("Failed to dup the file descriptor", e);
        }
    }

    private static void seekV21(FileDescriptor fileDescriptor, long j) throws IOException {
        try {
            Os.lseek(fileDescriptor, j, OsConstants.SEEK_SET);
        } catch (Exception e) {
            throw new IOException("Failed to seek the file descriptor", e);
        }
    }

    private static void seekV14(FileDescriptor fileDescriptor, long j) throws IOException {
        Object obj;
        Method method;
        try {
            synchronized (sPosixLockV14) {
                ensurePosixObjectsInitialized();
                obj = sPosixObjectV14;
                method = sLseekMethodV14;
            }
            method.invoke(obj, new Object[]{fileDescriptor, Long.valueOf(j), 0});
        } catch (Exception e) {
            throw new IOException("Failed to seek the file descriptor", e);
        }
    }

    private static void closeV21(FileDescriptor fileDescriptor) throws IOException {
        try {
            Os.close(fileDescriptor);
        } catch (Exception e) {
            throw new IOException("Failed to close the file descriptor", e);
        }
    }

    private static FileDescriptor closeV14(FileDescriptor fileDescriptor) throws IOException {
        Object obj;
        Method method;
        try {
            synchronized (sPosixLockV14) {
                ensurePosixObjectsInitialized();
                obj = sPosixObjectV14;
                method = sCloseMethodV14;
            }
            return (FileDescriptor) method.invoke(obj, new Object[]{fileDescriptor});
        } catch (Exception e) {
            throw new IOException("Failed to close the file descriptor", e);
        }
    }

    private static void ensurePosixObjectsInitialized() throws Exception {
        synchronized (sPosixLockV14) {
            if (sPosixObjectV14 == null) {
                Class<?> cls = Class.forName("libcore.io.Posix");
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                sLseekMethodV14 = cls.getMethod("lseek", new Class[]{FileDescriptor.class, Long.TYPE, Integer.TYPE});
                sDupMethodV14 = cls.getMethod("dup", new Class[]{FileDescriptor.class});
                sCloseMethodV14 = cls.getMethod("close", new Class[]{FileDescriptor.class});
                sPosixObjectV14 = declaredConstructor.newInstance(new Object[0]);
            }
        }
    }

    private FileDescriptorUtil() {
    }
}