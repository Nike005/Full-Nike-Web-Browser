package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.MutableInt;

public class ArrayUtils {
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int INDEX_NOT_FOUND = -1;

    public static <T> T[] toArray(T... tArr) {
        return tArr;
    }

    public static String toString(Object obj) {
        return toString(obj, "{}");
    }

    public static String toString(Object obj, String str) {
        return obj == null ? str : new ToStringBuilder(obj, ToStringStyle.SIMPLE_STYLE).append(obj).toString();
    }

    public static int hashCode(Object obj) {
        return new HashCodeBuilder().append(obj).toHashCode();
    }

    @Deprecated
    public static boolean isEquals(Object obj, Object obj2) {
        return new EqualsBuilder().append(obj, obj2).isEquals();
    }

    public static Map<Object, Object> toMap(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        double length = (double) objArr.length;
        Double.isNaN(length);
        HashMap hashMap = new HashMap((int) (length * 1.5d));
        for (int i = 0; i < objArr.length; i++) {
            Map.Entry entry = objArr[i];
            if (entry instanceof Map.Entry) {
                Map.Entry entry2 = entry;
                hashMap.put(entry2.getKey(), entry2.getValue());
            } else if (entry instanceof Object[]) {
                Object[] objArr2 = (Object[]) entry;
                if (objArr2.length >= 2) {
                    hashMap.put(objArr2[0], objArr2[1]);
                } else {
                    throw new IllegalArgumentException("Array element " + i + ", '" + entry + "', has a length less than 2");
                }
            } else {
                throw new IllegalArgumentException("Array element " + i + ", '" + entry + "', is neither of type Map.Entry nor an Array");
            }
        }
        return hashMap;
    }

    public static <T> T[] clone(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (Object[]) tArr.clone();
    }

    public static long[] clone(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) jArr.clone();
    }

    public static int[] clone(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public static short[] clone(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) sArr.clone();
    }

    public static char[] clone(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) cArr.clone();
    }

    public static byte[] clone(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public static double[] clone(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public static float[] clone(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return (float[]) fArr.clone();
    }

    public static boolean[] clone(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) zArr.clone();
    }

    public static <T> T[] nullToEmpty(T[] tArr, Class<T[]> cls) {
        if (cls != null) {
            return tArr == null ? (Object[]) cls.cast(Array.newInstance(cls.getComponentType(), 0)) : tArr;
        }
        throw new IllegalArgumentException("The type must not be null");
    }

    public static Object[] nullToEmpty(Object[] objArr) {
        return isEmpty(objArr) ? EMPTY_OBJECT_ARRAY : objArr;
    }

    public static Class<?>[] nullToEmpty(Class<?>[] clsArr) {
        return isEmpty((Object[]) clsArr) ? EMPTY_CLASS_ARRAY : clsArr;
    }

    public static String[] nullToEmpty(String[] strArr) {
        return isEmpty((Object[]) strArr) ? EMPTY_STRING_ARRAY : strArr;
    }

    public static long[] nullToEmpty(long[] jArr) {
        return isEmpty(jArr) ? EMPTY_LONG_ARRAY : jArr;
    }

    public static int[] nullToEmpty(int[] iArr) {
        return isEmpty(iArr) ? EMPTY_INT_ARRAY : iArr;
    }

    public static short[] nullToEmpty(short[] sArr) {
        return isEmpty(sArr) ? EMPTY_SHORT_ARRAY : sArr;
    }

    public static char[] nullToEmpty(char[] cArr) {
        return isEmpty(cArr) ? EMPTY_CHAR_ARRAY : cArr;
    }

    public static byte[] nullToEmpty(byte[] bArr) {
        return isEmpty(bArr) ? EMPTY_BYTE_ARRAY : bArr;
    }

    public static double[] nullToEmpty(double[] dArr) {
        return isEmpty(dArr) ? EMPTY_DOUBLE_ARRAY : dArr;
    }

    public static float[] nullToEmpty(float[] fArr) {
        return isEmpty(fArr) ? EMPTY_FLOAT_ARRAY : fArr;
    }

    public static boolean[] nullToEmpty(boolean[] zArr) {
        return isEmpty(zArr) ? EMPTY_BOOLEAN_ARRAY : zArr;
    }

    public static Long[] nullToEmpty(Long[] lArr) {
        return isEmpty((Object[]) lArr) ? EMPTY_LONG_OBJECT_ARRAY : lArr;
    }

    public static Integer[] nullToEmpty(Integer[] numArr) {
        return isEmpty((Object[]) numArr) ? EMPTY_INTEGER_OBJECT_ARRAY : numArr;
    }

    public static Short[] nullToEmpty(Short[] shArr) {
        return isEmpty((Object[]) shArr) ? EMPTY_SHORT_OBJECT_ARRAY : shArr;
    }

    public static Character[] nullToEmpty(Character[] chArr) {
        return isEmpty((Object[]) chArr) ? EMPTY_CHARACTER_OBJECT_ARRAY : chArr;
    }

    public static Byte[] nullToEmpty(Byte[] bArr) {
        return isEmpty((Object[]) bArr) ? EMPTY_BYTE_OBJECT_ARRAY : bArr;
    }

    public static Double[] nullToEmpty(Double[] dArr) {
        return isEmpty((Object[]) dArr) ? EMPTY_DOUBLE_OBJECT_ARRAY : dArr;
    }

    public static Float[] nullToEmpty(Float[] fArr) {
        return isEmpty((Object[]) fArr) ? EMPTY_FLOAT_OBJECT_ARRAY : fArr;
    }

    public static Boolean[] nullToEmpty(Boolean[] boolArr) {
        return isEmpty((Object[]) boolArr) ? EMPTY_BOOLEAN_OBJECT_ARRAY : boolArr;
    }

    public static <T> T[] subarray(T[] tArr, int i, int i2) {
        if (tArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > tArr.length) {
            i2 = tArr.length;
        }
        int i3 = i2 - i;
        Class<?> componentType = tArr.getClass().getComponentType();
        if (i3 <= 0) {
            return (Object[]) Array.newInstance(componentType, 0);
        }
        T[] tArr2 = (Object[]) Array.newInstance(componentType, i3);
        System.arraycopy(tArr, i, tArr2, 0, i3);
        return tArr2;
    }

    public static long[] subarray(long[] jArr, int i, int i2) {
        if (jArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > jArr.length) {
            i2 = jArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr2 = new long[i3];
        System.arraycopy(jArr, i, jArr2, 0, i3);
        return jArr2;
    }

    public static int[] subarray(int[] iArr, int i, int i2) {
        if (iArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > iArr.length) {
            i2 = iArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr2 = new int[i3];
        System.arraycopy(iArr, i, iArr2, 0, i3);
        return iArr2;
    }

    public static short[] subarray(short[] sArr, int i, int i2) {
        if (sArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > sArr.length) {
            i2 = sArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr2 = new short[i3];
        System.arraycopy(sArr, i, sArr2, 0, i3);
        return sArr2;
    }

    public static char[] subarray(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > cArr.length) {
            i2 = cArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr2 = new char[i3];
        System.arraycopy(cArr, i, cArr2, 0, i3);
        return cArr2;
    }

    public static byte[] subarray(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > bArr.length) {
            i2 = bArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    public static double[] subarray(double[] dArr, int i, int i2) {
        if (dArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > dArr.length) {
            i2 = dArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[i3];
        System.arraycopy(dArr, i, dArr2, 0, i3);
        return dArr2;
    }

    public static float[] subarray(float[] fArr, int i, int i2) {
        if (fArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > fArr.length) {
            i2 = fArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[i3];
        System.arraycopy(fArr, i, fArr2, 0, i3);
        return fArr2;
    }

    public static boolean[] subarray(boolean[] zArr, int i, int i2) {
        if (zArr == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 > zArr.length) {
            i2 = zArr.length;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr2 = new boolean[i3];
        System.arraycopy(zArr, i, zArr2, 0, i3);
        return zArr2;
    }

    public static boolean isSameLength(Object[] objArr, Object[] objArr2) {
        return getLength(objArr) == getLength(objArr2);
    }

    public static boolean isSameLength(long[] jArr, long[] jArr2) {
        return getLength(jArr) == getLength(jArr2);
    }

    public static boolean isSameLength(int[] iArr, int[] iArr2) {
        return getLength(iArr) == getLength(iArr2);
    }

    public static boolean isSameLength(short[] sArr, short[] sArr2) {
        return getLength(sArr) == getLength(sArr2);
    }

    public static boolean isSameLength(char[] cArr, char[] cArr2) {
        return getLength(cArr) == getLength(cArr2);
    }

    public static boolean isSameLength(byte[] bArr, byte[] bArr2) {
        return getLength(bArr) == getLength(bArr2);
    }

    public static boolean isSameLength(double[] dArr, double[] dArr2) {
        return getLength(dArr) == getLength(dArr2);
    }

    public static boolean isSameLength(float[] fArr, float[] fArr2) {
        return getLength(fArr) == getLength(fArr2);
    }

    public static boolean isSameLength(boolean[] zArr, boolean[] zArr2) {
        return getLength(zArr) == getLength(zArr2);
    }

    public static int getLength(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static boolean isSameType(Object obj, Object obj2) {
        if (obj != null && obj2 != null) {
            return obj.getClass().getName().equals(obj2.getClass().getName());
        }
        throw new IllegalArgumentException("The Array must not be null");
    }

    public static void reverse(Object[] objArr) {
        if (objArr != null) {
            reverse(objArr, 0, objArr.length);
        }
    }

    public static void reverse(long[] jArr) {
        if (jArr != null) {
            reverse(jArr, 0, jArr.length);
        }
    }

    public static void reverse(int[] iArr) {
        if (iArr != null) {
            reverse(iArr, 0, iArr.length);
        }
    }

    public static void reverse(short[] sArr) {
        if (sArr != null) {
            reverse(sArr, 0, sArr.length);
        }
    }

    public static void reverse(char[] cArr) {
        if (cArr != null) {
            reverse(cArr, 0, cArr.length);
        }
    }

    public static void reverse(byte[] bArr) {
        if (bArr != null) {
            reverse(bArr, 0, bArr.length);
        }
    }

    public static void reverse(double[] dArr) {
        if (dArr != null) {
            reverse(dArr, 0, dArr.length);
        }
    }

    public static void reverse(float[] fArr) {
        if (fArr != null) {
            reverse(fArr, 0, fArr.length);
        }
    }

    public static void reverse(boolean[] zArr) {
        if (zArr != null) {
            reverse(zArr, 0, zArr.length);
        }
    }

    public static void reverse(boolean[] zArr, int i, int i2) {
        if (zArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(zArr.length, i2) - 1;
            while (min > i) {
                boolean z = zArr[min];
                zArr[min] = zArr[i];
                zArr[i] = z;
                min--;
                i++;
            }
        }
    }

    public static void reverse(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(bArr.length, i2) - 1;
            while (min > i) {
                byte b = bArr[min];
                bArr[min] = bArr[i];
                bArr[i] = b;
                min--;
                i++;
            }
        }
    }

    public static void reverse(char[] cArr, int i, int i2) {
        if (cArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(cArr.length, i2) - 1;
            while (min > i) {
                char c = cArr[min];
                cArr[min] = cArr[i];
                cArr[i] = c;
                min--;
                i++;
            }
        }
    }

    public static void reverse(double[] dArr, int i, int i2) {
        if (dArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(dArr.length, i2) - 1;
            while (min > i) {
                double d = dArr[min];
                dArr[min] = dArr[i];
                dArr[i] = d;
                min--;
                i++;
            }
        }
    }

    public static void reverse(float[] fArr, int i, int i2) {
        if (fArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(fArr.length, i2) - 1;
            while (min > i) {
                float f = fArr[min];
                fArr[min] = fArr[i];
                fArr[i] = f;
                min--;
                i++;
            }
        }
    }

    public static void reverse(int[] iArr, int i, int i2) {
        if (iArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(iArr.length, i2) - 1;
            while (min > i) {
                int i3 = iArr[min];
                iArr[min] = iArr[i];
                iArr[i] = i3;
                min--;
                i++;
            }
        }
    }

    public static void reverse(long[] jArr, int i, int i2) {
        if (jArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(jArr.length, i2) - 1;
            while (min > i) {
                long j = jArr[min];
                jArr[min] = jArr[i];
                jArr[i] = j;
                min--;
                i++;
            }
        }
    }

    public static void reverse(Object[] objArr, int i, int i2) {
        if (objArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(objArr.length, i2) - 1;
            while (min > i) {
                Object obj = objArr[min];
                objArr[min] = objArr[i];
                objArr[i] = obj;
                min--;
                i++;
            }
        }
    }

    public static void reverse(short[] sArr, int i, int i2) {
        if (sArr != null) {
            if (i < 0) {
                i = 0;
            }
            int min = Math.min(sArr.length, i2) - 1;
            while (min > i) {
                short s = sArr[min];
                sArr[min] = sArr[i];
                sArr[i] = s;
                min--;
                i++;
            }
        }
    }

    public static void swap(Object[] objArr, int i, int i2) {
        if (objArr != null && objArr.length != 0) {
            swap(objArr, i, i2, 1);
        }
    }

    public static void swap(long[] jArr, int i, int i2) {
        if (jArr != null && jArr.length != 0) {
            swap(jArr, i, i2, 1);
        }
    }

    public static void swap(int[] iArr, int i, int i2) {
        if (iArr != null && iArr.length != 0) {
            swap(iArr, i, i2, 1);
        }
    }

    public static void swap(short[] sArr, int i, int i2) {
        if (sArr != null && sArr.length != 0) {
            swap(sArr, i, i2, 1);
        }
    }

    public static void swap(char[] cArr, int i, int i2) {
        if (cArr != null && cArr.length != 0) {
            swap(cArr, i, i2, 1);
        }
    }

    public static void swap(byte[] bArr, int i, int i2) {
        if (bArr != null && bArr.length != 0) {
            swap(bArr, i, i2, 1);
        }
    }

    public static void swap(double[] dArr, int i, int i2) {
        if (dArr != null && dArr.length != 0) {
            swap(dArr, i, i2, 1);
        }
    }

    public static void swap(float[] fArr, int i, int i2) {
        if (fArr != null && fArr.length != 0) {
            swap(fArr, i, i2, 1);
        }
    }

    public static void swap(boolean[] zArr, int i, int i2) {
        if (zArr != null && zArr.length != 0) {
            swap(zArr, i, i2, 1);
        }
    }

    public static void swap(boolean[] zArr, int i, int i2, int i3) {
        if (zArr != null && zArr.length != 0 && i < zArr.length && i2 < zArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, zArr.length - i), zArr.length - i2);
            while (i4 < min) {
                boolean z = zArr[i];
                zArr[i] = zArr[i2];
                zArr[i2] = z;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(byte[] bArr, int i, int i2, int i3) {
        if (bArr != null && bArr.length != 0 && i < bArr.length && i2 < bArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, bArr.length - i), bArr.length - i2);
            while (i4 < min) {
                byte b = bArr[i];
                bArr[i] = bArr[i2];
                bArr[i2] = b;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(char[] cArr, int i, int i2, int i3) {
        if (cArr != null && cArr.length != 0 && i < cArr.length && i2 < cArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, cArr.length - i), cArr.length - i2);
            while (i4 < min) {
                char c = cArr[i];
                cArr[i] = cArr[i2];
                cArr[i2] = c;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(double[] dArr, int i, int i2, int i3) {
        if (dArr != null && dArr.length != 0 && i < dArr.length && i2 < dArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, dArr.length - i), dArr.length - i2);
            while (i4 < min) {
                double d = dArr[i];
                dArr[i] = dArr[i2];
                dArr[i2] = d;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(float[] fArr, int i, int i2, int i3) {
        if (fArr != null && fArr.length != 0 && i < fArr.length && i2 < fArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, fArr.length - i), fArr.length - i2);
            while (i4 < min) {
                float f = fArr[i];
                fArr[i] = fArr[i2];
                fArr[i2] = f;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(int[] iArr, int i, int i2, int i3) {
        if (iArr != null && iArr.length != 0 && i < iArr.length && i2 < iArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, iArr.length - i), iArr.length - i2);
            while (i4 < min) {
                int i5 = iArr[i];
                iArr[i] = iArr[i2];
                iArr[i2] = i5;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(long[] jArr, int i, int i2, int i3) {
        if (jArr != null && jArr.length != 0 && i < jArr.length && i2 < jArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, jArr.length - i), jArr.length - i2);
            while (i4 < min) {
                long j = jArr[i];
                jArr[i] = jArr[i2];
                jArr[i2] = j;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(Object[] objArr, int i, int i2, int i3) {
        if (objArr != null && objArr.length != 0 && i < objArr.length && i2 < objArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int min = Math.min(Math.min(i3, objArr.length - i), objArr.length - i2);
            while (i4 < min) {
                Object obj = objArr[i];
                objArr[i] = objArr[i2];
                objArr[i2] = obj;
                i4++;
                i++;
                i2++;
            }
        }
    }

    public static void swap(short[] sArr, int i, int i2, int i3) {
        if (sArr != null && sArr.length != 0 && i < sArr.length && i2 < sArr.length) {
            int i4 = 0;
            if (i < 0) {
                i = 0;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            if (i != i2) {
                int min = Math.min(Math.min(i3, sArr.length - i), sArr.length - i2);
                while (i4 < min) {
                    short s = sArr[i];
                    sArr[i] = sArr[i2];
                    sArr[i2] = s;
                    i4++;
                    i++;
                    i2++;
                }
            }
        }
    }

    public static void shift(Object[] objArr, int i) {
        if (objArr != null) {
            shift(objArr, 0, objArr.length, i);
        }
    }

    public static void shift(long[] jArr, int i) {
        if (jArr != null) {
            shift(jArr, 0, jArr.length, i);
        }
    }

    public static void shift(int[] iArr, int i) {
        if (iArr != null) {
            shift(iArr, 0, iArr.length, i);
        }
    }

    public static void shift(short[] sArr, int i) {
        if (sArr != null) {
            shift(sArr, 0, sArr.length, i);
        }
    }

    public static void shift(char[] cArr, int i) {
        if (cArr != null) {
            shift(cArr, 0, cArr.length, i);
        }
    }

    public static void shift(byte[] bArr, int i) {
        if (bArr != null) {
            shift(bArr, 0, bArr.length, i);
        }
    }

    public static void shift(double[] dArr, int i) {
        if (dArr != null) {
            shift(dArr, 0, dArr.length, i);
        }
    }

    public static void shift(float[] fArr, int i) {
        if (fArr != null) {
            shift(fArr, 0, fArr.length, i);
        }
    }

    public static void shift(boolean[] zArr, int i) {
        if (zArr != null) {
            shift(zArr, 0, zArr.length, i);
        }
    }

    public static void shift(boolean[] zArr, int i, int i2, int i3) {
        if (zArr != null && i < zArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= zArr.length) {
                i2 = zArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(zArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(zArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(zArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(byte[] bArr, int i, int i2, int i3) {
        if (bArr != null && i < bArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= bArr.length) {
                i2 = bArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(bArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(bArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(bArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(char[] cArr, int i, int i2, int i3) {
        if (cArr != null && i < cArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= cArr.length) {
                i2 = cArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(cArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(cArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(cArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(double[] dArr, int i, int i2, int i3) {
        if (dArr != null && i < dArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= dArr.length) {
                i2 = dArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(dArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(dArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(dArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(float[] fArr, int i, int i2, int i3) {
        if (fArr != null && i < fArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= fArr.length) {
                i2 = fArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(fArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(fArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(fArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(int[] iArr, int i, int i2, int i3) {
        if (iArr != null && i < iArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= iArr.length) {
                i2 = iArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(iArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(iArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(iArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(long[] jArr, int i, int i2, int i3) {
        if (jArr != null && i < jArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= jArr.length) {
                i2 = jArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(jArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(jArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(jArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(Object[] objArr, int i, int i2, int i3) {
        if (objArr != null && i < objArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= objArr.length) {
                i2 = objArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(objArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(objArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(objArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static void shift(short[] sArr, int i, int i2, int i3) {
        if (sArr != null && i < sArr.length - 1 && i2 > 0) {
            if (i < 0) {
                i = 0;
            }
            if (i2 >= sArr.length) {
                i2 = sArr.length;
            }
            int i4 = i2 - i;
            if (i4 > 1) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                while (i4 > 1 && i5 > 0) {
                    int i6 = i4 - i5;
                    if (i5 > i6) {
                        swap(sArr, i, (i4 + i) - i6, i6);
                        int i7 = i5;
                        i5 -= i6;
                        i4 = i7;
                    } else if (i5 < i6) {
                        swap(sArr, i, i + i6, i5);
                        i += i5;
                        i4 = i6;
                    } else {
                        swap(sArr, i, i6 + i, i5);
                        return;
                    }
                }
            }
        }
    }

    public static int indexOf(Object[] objArr, Object obj) {
        return indexOf(objArr, obj, 0);
    }

    public static int indexOf(Object[] objArr, Object obj, int i) {
        if (objArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        if (obj == null) {
            while (i < objArr.length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < objArr.length) {
                if (obj.equals(objArr[i])) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static int lastIndexOf(Object[] objArr, Object obj) {
        return lastIndexOf(objArr, obj, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(Object[] objArr, Object obj, int i) {
        if (objArr == null || i < 0) {
            return -1;
        }
        if (i >= objArr.length) {
            i = objArr.length - 1;
        }
        if (obj == null) {
            while (i >= 0) {
                if (objArr[i] == null) {
                    return i;
                }
                i--;
            }
        } else if (objArr.getClass().getComponentType().isInstance(obj)) {
            while (i >= 0) {
                if (obj.equals(objArr[i])) {
                    return i;
                }
                i--;
            }
        }
        return -1;
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) != -1;
    }

    public static int indexOf(long[] jArr, long j) {
        return indexOf(jArr, j, 0);
    }

    public static int indexOf(long[] jArr, long j, int i) {
        if (jArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < jArr.length) {
            if (j == jArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(long[] jArr, long j) {
        return lastIndexOf(jArr, j, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(long[] jArr, long j, int i) {
        if (jArr == null || i < 0) {
            return -1;
        }
        if (i >= jArr.length) {
            i = jArr.length - 1;
        }
        while (i >= 0) {
            if (j == jArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(long[] jArr, long j) {
        return indexOf(jArr, j) != -1;
    }

    public static int indexOf(int[] iArr, int i) {
        return indexOf(iArr, i, 0);
    }

    public static int indexOf(int[] iArr, int i, int i2) {
        if (iArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < iArr.length) {
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int lastIndexOf(int[] iArr, int i) {
        return lastIndexOf(iArr, i, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(int[] iArr, int i, int i2) {
        if (iArr == null || i2 < 0) {
            return -1;
        }
        if (i2 >= iArr.length) {
            i2 = iArr.length - 1;
        }
        while (i2 >= 0) {
            if (i == iArr[i2]) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    public static boolean contains(int[] iArr, int i) {
        return indexOf(iArr, i) != -1;
    }

    public static int indexOf(short[] sArr, short s) {
        return indexOf(sArr, s, 0);
    }

    public static int indexOf(short[] sArr, short s, int i) {
        if (sArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < sArr.length) {
            if (s == sArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(short[] sArr, short s) {
        return lastIndexOf(sArr, s, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(short[] sArr, short s, int i) {
        if (sArr == null || i < 0) {
            return -1;
        }
        if (i >= sArr.length) {
            i = sArr.length - 1;
        }
        while (i >= 0) {
            if (s == sArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(short[] sArr, short s) {
        return indexOf(sArr, s) != -1;
    }

    public static int indexOf(char[] cArr, char c) {
        return indexOf(cArr, c, 0);
    }

    public static int indexOf(char[] cArr, char c, int i) {
        if (cArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < cArr.length) {
            if (c == cArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(char[] cArr, char c) {
        return lastIndexOf(cArr, c, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(char[] cArr, char c, int i) {
        if (cArr == null || i < 0) {
            return -1;
        }
        if (i >= cArr.length) {
            i = cArr.length - 1;
        }
        while (i >= 0) {
            if (c == cArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(char[] cArr, char c) {
        return indexOf(cArr, c) != -1;
    }

    public static int indexOf(byte[] bArr, byte b) {
        return indexOf(bArr, b, 0);
    }

    public static int indexOf(byte[] bArr, byte b, int i) {
        if (bArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < bArr.length) {
            if (b == bArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(byte[] bArr, byte b) {
        return lastIndexOf(bArr, b, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(byte[] bArr, byte b, int i) {
        if (bArr == null || i < 0) {
            return -1;
        }
        if (i >= bArr.length) {
            i = bArr.length - 1;
        }
        while (i >= 0) {
            if (b == bArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(byte[] bArr, byte b) {
        return indexOf(bArr, b) != -1;
    }

    public static int indexOf(double[] dArr, double d) {
        return indexOf(dArr, d, 0);
    }

    public static int indexOf(double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2);
    }

    public static int indexOf(double[] dArr, double d, int i) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < dArr.length) {
            if (d == dArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(double[] dArr, double d, int i, double d2) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        double d3 = d - d2;
        double d4 = d + d2;
        while (i < dArr.length) {
            if (dArr[i] >= d3 && dArr[i] <= d4) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d) {
        return lastIndexOf(dArr, d, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(double[] dArr, double d, double d2) {
        return lastIndexOf(dArr, d, Integer.MAX_VALUE, d2);
    }

    public static int lastIndexOf(double[] dArr, double d, int i) {
        if (isEmpty(dArr) || i < 0) {
            return -1;
        }
        if (i >= dArr.length) {
            i = dArr.length - 1;
        }
        while (i >= 0) {
            if (d == dArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static int lastIndexOf(double[] dArr, double d, int i, double d2) {
        if (isEmpty(dArr) || i < 0) {
            return -1;
        }
        if (i >= dArr.length) {
            i = dArr.length - 1;
        }
        double d3 = d - d2;
        double d4 = d + d2;
        while (i >= 0) {
            if (dArr[i] >= d3 && dArr[i] <= d4) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(double[] dArr, double d) {
        return indexOf(dArr, d) != -1;
    }

    public static boolean contains(double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2) != -1;
    }

    public static int indexOf(float[] fArr, float f) {
        return indexOf(fArr, f, 0);
    }

    public static int indexOf(float[] fArr, float f, int i) {
        if (isEmpty(fArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < fArr.length) {
            if (f == fArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(float[] fArr, float f) {
        return lastIndexOf(fArr, f, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(float[] fArr, float f, int i) {
        if (isEmpty(fArr) || i < 0) {
            return -1;
        }
        if (i >= fArr.length) {
            i = fArr.length - 1;
        }
        while (i >= 0) {
            if (f == fArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(float[] fArr, float f) {
        return indexOf(fArr, f) != -1;
    }

    public static int indexOf(boolean[] zArr, boolean z) {
        return indexOf(zArr, z, 0);
    }

    public static int indexOf(boolean[] zArr, boolean z, int i) {
        if (isEmpty(zArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < zArr.length) {
            if (z == zArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOf(boolean[] zArr, boolean z) {
        return lastIndexOf(zArr, z, Integer.MAX_VALUE);
    }

    public static int lastIndexOf(boolean[] zArr, boolean z, int i) {
        if (isEmpty(zArr) || i < 0) {
            return -1;
        }
        if (i >= zArr.length) {
            i = zArr.length - 1;
        }
        while (i >= 0) {
            if (z == zArr[i]) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean contains(boolean[] zArr, boolean z) {
        return indexOf(zArr, z) != -1;
    }

    public static char[] toPrimitive(Character[] chArr) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            cArr[i] = chArr[i].charValue();
        }
        return cArr;
    }

    public static char[] toPrimitive(Character[] chArr, char c) {
        char c2;
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return EMPTY_CHAR_ARRAY;
        }
        char[] cArr = new char[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            Character ch = chArr[i];
            if (ch == null) {
                c2 = c;
            } else {
                c2 = ch.charValue();
            }
            cArr[i] = c2;
        }
        return cArr;
    }

    public static Character[] toObject(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        if (cArr.length == 0) {
            return EMPTY_CHARACTER_OBJECT_ARRAY;
        }
        Character[] chArr = new Character[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            chArr[i] = Character.valueOf(cArr[i]);
        }
        return chArr;
    }

    public static long[] toPrimitive(Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }

    public static long[] toPrimitive(Long[] lArr, long j) {
        long j2;
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return EMPTY_LONG_ARRAY;
        }
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            Long l = lArr[i];
            if (l == null) {
                j2 = j;
            } else {
                j2 = l.longValue();
            }
            jArr[i] = j2;
        }
        return jArr;
    }

    public static Long[] toObject(long[] jArr) {
        if (jArr == null) {
            return null;
        }
        if (jArr.length == 0) {
            return EMPTY_LONG_OBJECT_ARRAY;
        }
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public static int[] toPrimitive(Integer[] numArr) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public static int[] toPrimitive(Integer[] numArr, int i) {
        int i2;
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return EMPTY_INT_ARRAY;
        }
        int[] iArr = new int[numArr.length];
        for (int i3 = 0; i3 < numArr.length; i3++) {
            Integer num = numArr[i3];
            if (num == null) {
                i2 = i;
            } else {
                i2 = num.intValue();
            }
            iArr[i3] = i2;
        }
        return iArr;
    }

    public static Integer[] toObject(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length == 0) {
            return EMPTY_INTEGER_OBJECT_ARRAY;
        }
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    public static short[] toPrimitive(Short[] shArr) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int i = 0; i < shArr.length; i++) {
            sArr[i] = shArr[i].shortValue();
        }
        return sArr;
    }

    public static short[] toPrimitive(Short[] shArr, short s) {
        short s2;
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return EMPTY_SHORT_ARRAY;
        }
        short[] sArr = new short[shArr.length];
        for (int i = 0; i < shArr.length; i++) {
            Short sh = shArr[i];
            if (sh == null) {
                s2 = s;
            } else {
                s2 = sh.shortValue();
            }
            sArr[i] = s2;
        }
        return sArr;
    }

    public static Short[] toObject(short[] sArr) {
        if (sArr == null) {
            return null;
        }
        if (sArr.length == 0) {
            return EMPTY_SHORT_OBJECT_ARRAY;
        }
        Short[] shArr = new Short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            shArr[i] = Short.valueOf(sArr[i]);
        }
        return shArr;
    }

    public static byte[] toPrimitive(Byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return bArr2;
    }

    public static byte[] toPrimitive(Byte[] bArr, byte b) {
        byte b2;
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            Byte b3 = bArr[i];
            if (b3 == null) {
                b2 = b;
            } else {
                b2 = b3.byteValue();
            }
            bArr2[i] = b2;
        }
        return bArr2;
    }

    public static Byte[] toObject(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return EMPTY_BYTE_OBJECT_ARRAY;
        }
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    public static double[] toPrimitive(Double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = dArr[i].doubleValue();
        }
        return dArr2;
    }

    public static double[] toPrimitive(Double[] dArr, double d) {
        double d2;
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_ARRAY;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            Double d3 = dArr[i];
            if (d3 == null) {
                d2 = d;
            } else {
                d2 = d3.doubleValue();
            }
            dArr2[i] = d2;
        }
        return dArr2;
    }

    public static Double[] toObject(double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return EMPTY_DOUBLE_OBJECT_ARRAY;
        }
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    public static float[] toPrimitive(Float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr[i].floatValue();
        }
        return fArr2;
    }

    public static float[] toPrimitive(Float[] fArr, float f) {
        float f2;
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_ARRAY;
        }
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            Float f3 = fArr[i];
            if (f3 == null) {
                f2 = f;
            } else {
                f2 = f3.floatValue();
            }
            fArr2[i] = f2;
        }
        return fArr2;
    }

    public static Float[] toObject(float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return EMPTY_FLOAT_OBJECT_ARRAY;
        }
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    public static Object toPrimitive(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<?> wrapperToPrimitive = ClassUtils.wrapperToPrimitive(obj.getClass().getComponentType());
        if (Integer.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Integer[]) obj);
        }
        if (Long.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Long[]) obj);
        }
        if (Short.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Short[]) obj);
        }
        if (Double.TYPE.equals(wrapperToPrimitive)) {
            return toPrimitive((Double[]) obj);
        }
        return Float.TYPE.equals(wrapperToPrimitive) ? toPrimitive((Float[]) obj) : obj;
    }

    public static boolean[] toPrimitive(Boolean[] boolArr) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            zArr[i] = boolArr[i].booleanValue();
        }
        return zArr;
    }

    public static boolean[] toPrimitive(Boolean[] boolArr, boolean z) {
        boolean z2;
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            Boolean bool = boolArr[i];
            if (bool == null) {
                z2 = z;
            } else {
                z2 = bool.booleanValue();
            }
            zArr[i] = z2;
        }
        return zArr;
    }

    public static Boolean[] toObject(boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        if (zArr.length == 0) {
            return EMPTY_BOOLEAN_OBJECT_ARRAY;
        }
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = zArr[i] ? Boolean.TRUE : Boolean.FALSE;
        }
        return boolArr;
    }

    public static boolean isEmpty(Object[] objArr) {
        return getLength(objArr) == 0;
    }

    public static boolean isEmpty(long[] jArr) {
        return getLength(jArr) == 0;
    }

    public static boolean isEmpty(int[] iArr) {
        return getLength(iArr) == 0;
    }

    public static boolean isEmpty(short[] sArr) {
        return getLength(sArr) == 0;
    }

    public static boolean isEmpty(char[] cArr) {
        return getLength(cArr) == 0;
    }

    public static boolean isEmpty(byte[] bArr) {
        return getLength(bArr) == 0;
    }

    public static boolean isEmpty(double[] dArr) {
        return getLength(dArr) == 0;
    }

    public static boolean isEmpty(float[] fArr) {
        return getLength(fArr) == 0;
    }

    public static boolean isEmpty(boolean[] zArr) {
        return getLength(zArr) == 0;
    }

    public static <T> boolean isNotEmpty(T[] tArr) {
        return !isEmpty((Object[]) tArr);
    }

    public static boolean isNotEmpty(long[] jArr) {
        return !isEmpty(jArr);
    }

    public static boolean isNotEmpty(int[] iArr) {
        return !isEmpty(iArr);
    }

    public static boolean isNotEmpty(short[] sArr) {
        return !isEmpty(sArr);
    }

    public static boolean isNotEmpty(char[] cArr) {
        return !isEmpty(cArr);
    }

    public static boolean isNotEmpty(byte[] bArr) {
        return !isEmpty(bArr);
    }

    public static boolean isNotEmpty(double[] dArr) {
        return !isEmpty(dArr);
    }

    public static boolean isNotEmpty(float[] fArr) {
        return !isEmpty(fArr);
    }

    public static boolean isNotEmpty(boolean[] zArr) {
        return !isEmpty(zArr);
    }

    public static <T> T[] addAll(T[] tArr, T... tArr2) {
        if (tArr == null) {
            return clone(tArr2);
        }
        if (tArr2 == null) {
            return clone(tArr);
        }
        Class<?> componentType = tArr.getClass().getComponentType();
        T[] tArr3 = (Object[]) Array.newInstance(componentType, tArr.length + tArr2.length);
        System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
        try {
            System.arraycopy(tArr2, 0, tArr3, tArr.length, tArr2.length);
            return tArr3;
        } catch (ArrayStoreException e) {
            Class<?> componentType2 = tArr2.getClass().getComponentType();
            if (!componentType.isAssignableFrom(componentType2)) {
                throw new IllegalArgumentException("Cannot store " + componentType2.getName() + " in an array of " + componentType.getName(), e);
            }
            throw e;
        }
    }

    public static boolean[] addAll(boolean[] zArr, boolean... zArr2) {
        if (zArr == null) {
            return clone(zArr2);
        }
        if (zArr2 == null) {
            return clone(zArr);
        }
        boolean[] zArr3 = new boolean[(zArr.length + zArr2.length)];
        System.arraycopy(zArr, 0, zArr3, 0, zArr.length);
        System.arraycopy(zArr2, 0, zArr3, zArr.length, zArr2.length);
        return zArr3;
    }

    public static char[] addAll(char[] cArr, char... cArr2) {
        if (cArr == null) {
            return clone(cArr2);
        }
        if (cArr2 == null) {
            return clone(cArr);
        }
        char[] cArr3 = new char[(cArr.length + cArr2.length)];
        System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
        System.arraycopy(cArr2, 0, cArr3, cArr.length, cArr2.length);
        return cArr3;
    }

    public static byte[] addAll(byte[] bArr, byte... bArr2) {
        if (bArr == null) {
            return clone(bArr2);
        }
        if (bArr2 == null) {
            return clone(bArr);
        }
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static short[] addAll(short[] sArr, short... sArr2) {
        if (sArr == null) {
            return clone(sArr2);
        }
        if (sArr2 == null) {
            return clone(sArr);
        }
        short[] sArr3 = new short[(sArr.length + sArr2.length)];
        System.arraycopy(sArr, 0, sArr3, 0, sArr.length);
        System.arraycopy(sArr2, 0, sArr3, sArr.length, sArr2.length);
        return sArr3;
    }

    public static int[] addAll(int[] iArr, int... iArr2) {
        if (iArr == null) {
            return clone(iArr2);
        }
        if (iArr2 == null) {
            return clone(iArr);
        }
        int[] iArr3 = new int[(iArr.length + iArr2.length)];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    public static long[] addAll(long[] jArr, long... jArr2) {
        if (jArr == null) {
            return clone(jArr2);
        }
        if (jArr2 == null) {
            return clone(jArr);
        }
        long[] jArr3 = new long[(jArr.length + jArr2.length)];
        System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
        System.arraycopy(jArr2, 0, jArr3, jArr.length, jArr2.length);
        return jArr3;
    }

    public static float[] addAll(float[] fArr, float... fArr2) {
        if (fArr == null) {
            return clone(fArr2);
        }
        if (fArr2 == null) {
            return clone(fArr);
        }
        float[] fArr3 = new float[(fArr.length + fArr2.length)];
        System.arraycopy(fArr, 0, fArr3, 0, fArr.length);
        System.arraycopy(fArr2, 0, fArr3, fArr.length, fArr2.length);
        return fArr3;
    }

    public static double[] addAll(double[] dArr, double... dArr2) {
        if (dArr == null) {
            return clone(dArr2);
        }
        if (dArr2 == null) {
            return clone(dArr);
        }
        double[] dArr3 = new double[(dArr.length + dArr2.length)];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr3, dArr.length, dArr2.length);
        return dArr3;
    }

    public static <T> T[] add(T[] tArr, T t) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass().getComponentType();
        } else if (t != null) {
            cls = t.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        T[] tArr2 = (Object[]) copyArrayGrow1(tArr, cls);
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    public static boolean[] add(boolean[] zArr, boolean z) {
        boolean[] zArr2 = (boolean[]) copyArrayGrow1(zArr, Boolean.TYPE);
        zArr2[zArr2.length - 1] = z;
        return zArr2;
    }

    public static byte[] add(byte[] bArr, byte b) {
        byte[] bArr2 = (byte[]) copyArrayGrow1(bArr, Byte.TYPE);
        bArr2[bArr2.length - 1] = b;
        return bArr2;
    }

    public static char[] add(char[] cArr, char c) {
        char[] cArr2 = (char[]) copyArrayGrow1(cArr, Character.TYPE);
        cArr2[cArr2.length - 1] = c;
        return cArr2;
    }

    public static double[] add(double[] dArr, double d) {
        double[] dArr2 = (double[]) copyArrayGrow1(dArr, Double.TYPE);
        dArr2[dArr2.length - 1] = d;
        return dArr2;
    }

    public static float[] add(float[] fArr, float f) {
        float[] fArr2 = (float[]) copyArrayGrow1(fArr, Float.TYPE);
        fArr2[fArr2.length - 1] = f;
        return fArr2;
    }

    public static int[] add(int[] iArr, int i) {
        int[] iArr2 = (int[]) copyArrayGrow1(iArr, Integer.TYPE);
        iArr2[iArr2.length - 1] = i;
        return iArr2;
    }

    public static long[] add(long[] jArr, long j) {
        long[] jArr2 = (long[]) copyArrayGrow1(jArr, Long.TYPE);
        jArr2[jArr2.length - 1] = j;
        return jArr2;
    }

    public static short[] add(short[] sArr, short s) {
        short[] sArr2 = (short[]) copyArrayGrow1(sArr, Short.TYPE);
        sArr2[sArr2.length - 1] = s;
        return sArr2;
    }

    private static Object copyArrayGrow1(Object obj, Class<?> cls) {
        if (obj == null) {
            return Array.newInstance(cls, 1);
        }
        int length = Array.getLength(obj);
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
        System.arraycopy(obj, 0, newInstance, 0, length);
        return newInstance;
    }

    public static <T> T[] add(T[] tArr, int i, T t) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass().getComponentType();
        } else if (t != null) {
            cls = t.getClass();
        } else {
            throw new IllegalArgumentException("Array and element cannot both be null");
        }
        return (Object[]) add(tArr, i, t, cls);
    }

    public static boolean[] add(boolean[] zArr, int i, boolean z) {
        return (boolean[]) add(zArr, i, Boolean.valueOf(z), Boolean.TYPE);
    }

    public static char[] add(char[] cArr, int i, char c) {
        return (char[]) add(cArr, i, Character.valueOf(c), Character.TYPE);
    }

    public static byte[] add(byte[] bArr, int i, byte b) {
        return (byte[]) add(bArr, i, Byte.valueOf(b), Byte.TYPE);
    }

    public static short[] add(short[] sArr, int i, short s) {
        return (short[]) add(sArr, i, Short.valueOf(s), Short.TYPE);
    }

    public static int[] add(int[] iArr, int i, int i2) {
        return (int[]) add(iArr, i, Integer.valueOf(i2), Integer.TYPE);
    }

    public static long[] add(long[] jArr, int i, long j) {
        return (long[]) add(jArr, i, Long.valueOf(j), Long.TYPE);
    }

    public static float[] add(float[] fArr, int i, float f) {
        return (float[]) add(fArr, i, Float.valueOf(f), Float.TYPE);
    }

    public static double[] add(double[] dArr, int i, double d) {
        return (double[]) add(dArr, i, Double.valueOf(d), Double.TYPE);
    }

    private static Object add(Object obj, int i, Object obj2, Class<?> cls) {
        if (obj != null) {
            int length = Array.getLength(obj);
            if (i > length || i < 0) {
                throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + length);
            }
            Object newInstance = Array.newInstance(cls, length + 1);
            System.arraycopy(obj, 0, newInstance, 0, i);
            Array.set(newInstance, i, obj2);
            if (i < length) {
                System.arraycopy(obj, i, newInstance, i + 1, length - i);
            }
            return newInstance;
        } else if (i == 0) {
            Object newInstance2 = Array.newInstance(cls, 1);
            Array.set(newInstance2, 0, obj2);
            return newInstance2;
        } else {
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: 0");
        }
    }

    public static <T> T[] remove(T[] tArr, int i) {
        return (Object[]) remove((Object) tArr, i);
    }

    public static <T> T[] removeElement(T[] tArr, Object obj) {
        int indexOf = indexOf((Object[]) tArr, obj);
        if (indexOf == -1) {
            return clone(tArr);
        }
        return remove(tArr, indexOf);
    }

    public static boolean[] remove(boolean[] zArr, int i) {
        return (boolean[]) remove((Object) zArr, i);
    }

    public static boolean[] removeElement(boolean[] zArr, boolean z) {
        int indexOf = indexOf(zArr, z);
        if (indexOf == -1) {
            return clone(zArr);
        }
        return remove(zArr, indexOf);
    }

    public static byte[] remove(byte[] bArr, int i) {
        return (byte[]) remove((Object) bArr, i);
    }

    public static byte[] removeElement(byte[] bArr, byte b) {
        int indexOf = indexOf(bArr, b);
        if (indexOf == -1) {
            return clone(bArr);
        }
        return remove(bArr, indexOf);
    }

    public static char[] remove(char[] cArr, int i) {
        return (char[]) remove((Object) cArr, i);
    }

    public static char[] removeElement(char[] cArr, char c) {
        int indexOf = indexOf(cArr, c);
        if (indexOf == -1) {
            return clone(cArr);
        }
        return remove(cArr, indexOf);
    }

    public static double[] remove(double[] dArr, int i) {
        return (double[]) remove((Object) dArr, i);
    }

    public static double[] removeElement(double[] dArr, double d) {
        int indexOf = indexOf(dArr, d);
        if (indexOf == -1) {
            return clone(dArr);
        }
        return remove(dArr, indexOf);
    }

    public static float[] remove(float[] fArr, int i) {
        return (float[]) remove((Object) fArr, i);
    }

    public static float[] removeElement(float[] fArr, float f) {
        int indexOf = indexOf(fArr, f);
        if (indexOf == -1) {
            return clone(fArr);
        }
        return remove(fArr, indexOf);
    }

    public static int[] remove(int[] iArr, int i) {
        return (int[]) remove((Object) iArr, i);
    }

    public static int[] removeElement(int[] iArr, int i) {
        int indexOf = indexOf(iArr, i);
        if (indexOf == -1) {
            return clone(iArr);
        }
        return remove(iArr, indexOf);
    }

    public static long[] remove(long[] jArr, int i) {
        return (long[]) remove((Object) jArr, i);
    }

    public static long[] removeElement(long[] jArr, long j) {
        int indexOf = indexOf(jArr, j);
        if (indexOf == -1) {
            return clone(jArr);
        }
        return remove(jArr, indexOf);
    }

    public static short[] remove(short[] sArr, int i) {
        return (short[]) remove((Object) sArr, i);
    }

    public static short[] removeElement(short[] sArr, short s) {
        int indexOf = indexOf(sArr, s);
        if (indexOf == -1) {
            return clone(sArr);
        }
        return remove(sArr, indexOf);
    }

    private static Object remove(Object obj, int i) {
        int length = getLength(obj);
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + length);
        }
        int i2 = length - 1;
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), i2);
        System.arraycopy(obj, 0, newInstance, 0, i);
        if (i < i2) {
            System.arraycopy(obj, i + 1, newInstance, i, (length - i) - 1);
        }
        return newInstance;
    }

    public static <T> T[] removeAll(T[] tArr, int... iArr) {
        return (Object[]) removeAll((Object) tArr, iArr);
    }

    public static <T> T[] removeElements(T[] tArr, T... tArr2) {
        if (isEmpty((Object[]) tArr) || isEmpty((Object[]) tArr2)) {
            return clone(tArr);
        }
        HashMap hashMap = new HashMap(tArr2.length);
        for (T t : tArr2) {
            MutableInt mutableInt = (MutableInt) hashMap.get(t);
            if (mutableInt == null) {
                hashMap.put(t, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < tArr.length; i++) {
            T t2 = tArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(t2);
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(t2);
                }
                bitSet.set(i);
            }
        }
        return (Object[]) removeAll((Object) tArr, bitSet);
    }

    public static byte[] removeAll(byte[] bArr, int... iArr) {
        return (byte[]) removeAll((Object) bArr, iArr);
    }

    public static byte[] removeElements(byte[] bArr, byte... bArr2) {
        if (isEmpty(bArr) || isEmpty(bArr2)) {
            return clone(bArr);
        }
        HashMap hashMap = new HashMap(bArr2.length);
        for (byte valueOf : bArr2) {
            Byte valueOf2 = Byte.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Byte.valueOf(b));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Byte.valueOf(b));
                }
                bitSet.set(i);
            }
        }
        return (byte[]) removeAll((Object) bArr, bitSet);
    }

    public static short[] removeAll(short[] sArr, int... iArr) {
        return (short[]) removeAll((Object) sArr, iArr);
    }

    public static short[] removeElements(short[] sArr, short... sArr2) {
        if (isEmpty(sArr) || isEmpty(sArr2)) {
            return clone(sArr);
        }
        HashMap hashMap = new HashMap(sArr2.length);
        for (short valueOf : sArr2) {
            Short valueOf2 = Short.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < sArr.length; i++) {
            short s = sArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Short.valueOf(s));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Short.valueOf(s));
                }
                bitSet.set(i);
            }
        }
        return (short[]) removeAll((Object) sArr, bitSet);
    }

    public static int[] removeAll(int[] iArr, int... iArr2) {
        return (int[]) removeAll((Object) iArr, iArr2);
    }

    public static int[] removeElements(int[] iArr, int... iArr2) {
        if (isEmpty(iArr) || isEmpty(iArr2)) {
            return clone(iArr);
        }
        HashMap hashMap = new HashMap(iArr2.length);
        for (int valueOf : iArr2) {
            Integer valueOf2 = Integer.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Integer.valueOf(i2));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Integer.valueOf(i2));
                }
                bitSet.set(i);
            }
        }
        return (int[]) removeAll((Object) iArr, bitSet);
    }

    public static char[] removeAll(char[] cArr, int... iArr) {
        return (char[]) removeAll((Object) cArr, iArr);
    }

    public static char[] removeElements(char[] cArr, char... cArr2) {
        if (isEmpty(cArr) || isEmpty(cArr2)) {
            return clone(cArr);
        }
        HashMap hashMap = new HashMap(cArr2.length);
        for (char valueOf : cArr2) {
            Character valueOf2 = Character.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Character.valueOf(c));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Character.valueOf(c));
                }
                bitSet.set(i);
            }
        }
        return (char[]) removeAll((Object) cArr, bitSet);
    }

    public static long[] removeAll(long[] jArr, int... iArr) {
        return (long[]) removeAll((Object) jArr, iArr);
    }

    public static long[] removeElements(long[] jArr, long... jArr2) {
        if (isEmpty(jArr) || isEmpty(jArr2)) {
            return clone(jArr);
        }
        HashMap hashMap = new HashMap(jArr2.length);
        for (long valueOf : jArr2) {
            Long valueOf2 = Long.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < jArr.length; i++) {
            long j = jArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Long.valueOf(j));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Long.valueOf(j));
                }
                bitSet.set(i);
            }
        }
        return (long[]) removeAll((Object) jArr, bitSet);
    }

    public static float[] removeAll(float[] fArr, int... iArr) {
        return (float[]) removeAll((Object) fArr, iArr);
    }

    public static float[] removeElements(float[] fArr, float... fArr2) {
        if (isEmpty(fArr) || isEmpty(fArr2)) {
            return clone(fArr);
        }
        HashMap hashMap = new HashMap(fArr2.length);
        for (float valueOf : fArr2) {
            Float valueOf2 = Float.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < fArr.length; i++) {
            float f = fArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Float.valueOf(f));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Float.valueOf(f));
                }
                bitSet.set(i);
            }
        }
        return (float[]) removeAll((Object) fArr, bitSet);
    }

    public static double[] removeAll(double[] dArr, int... iArr) {
        return (double[]) removeAll((Object) dArr, iArr);
    }

    public static double[] removeElements(double[] dArr, double... dArr2) {
        if (isEmpty(dArr) || isEmpty(dArr2)) {
            return clone(dArr);
        }
        HashMap hashMap = new HashMap(dArr2.length);
        for (double valueOf : dArr2) {
            Double valueOf2 = Double.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < dArr.length; i++) {
            double d = dArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Double.valueOf(d));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Double.valueOf(d));
                }
                bitSet.set(i);
            }
        }
        return (double[]) removeAll((Object) dArr, bitSet);
    }

    public static boolean[] removeAll(boolean[] zArr, int... iArr) {
        return (boolean[]) removeAll((Object) zArr, iArr);
    }

    public static boolean[] removeElements(boolean[] zArr, boolean... zArr2) {
        if (isEmpty(zArr) || isEmpty(zArr2)) {
            return clone(zArr);
        }
        HashMap hashMap = new HashMap(2);
        for (boolean valueOf : zArr2) {
            Boolean valueOf2 = Boolean.valueOf(valueOf);
            MutableInt mutableInt = (MutableInt) hashMap.get(valueOf2);
            if (mutableInt == null) {
                hashMap.put(valueOf2, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < zArr.length; i++) {
            boolean z = zArr[i];
            MutableInt mutableInt2 = (MutableInt) hashMap.get(Boolean.valueOf(z));
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    hashMap.remove(Boolean.valueOf(z));
                }
                bitSet.set(i);
            }
        }
        return (boolean[]) removeAll((Object) zArr, bitSet);
    }

    static Object removeAll(Object obj, int... iArr) {
        int i;
        int i2;
        int length = getLength(obj);
        int[] clone = clone(iArr);
        Arrays.sort(clone);
        if (isNotEmpty(clone)) {
            int length2 = clone.length;
            int i3 = length;
            i = 0;
            while (true) {
                length2--;
                if (length2 < 0) {
                    break;
                }
                i2 = clone[length2];
                if (i2 < 0 || i2 >= length) {
                } else if (i2 < i3) {
                    i++;
                    i3 = i2;
                }
            }
            throw new IndexOutOfBoundsException("Index: " + i2 + ", Length: " + length);
        }
        i = 0;
        int i4 = length - i;
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), i4);
        if (i < length) {
            int length3 = clone.length - 1;
            while (length3 >= 0) {
                int i5 = clone[length3];
                int i6 = length - i5;
                if (i6 > 1) {
                    int i7 = i6 - 1;
                    i4 -= i7;
                    System.arraycopy(obj, i5 + 1, newInstance, i4, i7);
                }
                length3--;
                length = i5;
            }
            if (length > 0) {
                System.arraycopy(obj, 0, newInstance, 0, length);
            }
        }
        return newInstance;
    }

    static Object removeAll(Object obj, BitSet bitSet) {
        int length = getLength(obj);
        Object newInstance = Array.newInstance(obj.getClass().getComponentType(), length - bitSet.cardinality());
        int i = 0;
        int i2 = 0;
        while (true) {
            int nextSetBit = bitSet.nextSetBit(i);
            if (nextSetBit == -1) {
                break;
            }
            int i3 = nextSetBit - i;
            if (i3 > 0) {
                System.arraycopy(obj, i, newInstance, i2, i3);
                i2 += i3;
            }
            i = bitSet.nextClearBit(nextSetBit);
        }
        int i4 = length - i;
        if (i4 > 0) {
            System.arraycopy(obj, i, newInstance, i2, i4);
        }
        return newInstance;
    }

    public static <T extends Comparable<? super T>> boolean isSorted(T[] tArr) {
        return isSorted(tArr, new Comparator<T>() {
            public int compare(T t, T t2) {
                return t.compareTo(t2);
            }
        });
    }

    public static <T> boolean isSorted(T[] tArr, Comparator<T> comparator) {
        if (comparator != null) {
            if (tArr != null && tArr.length >= 2) {
                T t = tArr[0];
                int length = tArr.length;
                int i = 1;
                while (i < length) {
                    T t2 = tArr[i];
                    if (comparator.compare(t, t2) > 0) {
                        return false;
                    }
                    i++;
                    t = t2;
                }
            }
            return true;
        }
        throw new IllegalArgumentException("Comparator should not be null.");
    }

    public static boolean isSorted(int[] iArr) {
        if (iArr != null && iArr.length >= 2) {
            int i = iArr[0];
            int length = iArr.length;
            int i2 = 1;
            while (i2 < length) {
                int i3 = iArr[i2];
                if (NumberUtils.compare(i, i3) > 0) {
                    return false;
                }
                i2++;
                i = i3;
            }
        }
        return true;
    }

    public static boolean isSorted(long[] jArr) {
        if (jArr != null && jArr.length >= 2) {
            long j = jArr[0];
            int length = jArr.length;
            int i = 1;
            while (i < length) {
                long j2 = jArr[i];
                if (NumberUtils.compare(j, j2) > 0) {
                    return false;
                }
                i++;
                j = j2;
            }
        }
        return true;
    }

    public static boolean isSorted(short[] sArr) {
        if (sArr != null && sArr.length >= 2) {
            short s = sArr[0];
            int length = sArr.length;
            int i = 1;
            while (i < length) {
                short s2 = sArr[i];
                if (NumberUtils.compare(s, s2) > 0) {
                    return false;
                }
                i++;
                s = s2;
            }
        }
        return true;
    }

    public static boolean isSorted(double[] dArr) {
        if (dArr != null && dArr.length >= 2) {
            double d = dArr[0];
            int length = dArr.length;
            int i = 1;
            while (i < length) {
                double d2 = dArr[i];
                if (Double.compare(d, d2) > 0) {
                    return false;
                }
                i++;
                d = d2;
            }
        }
        return true;
    }

    public static boolean isSorted(float[] fArr) {
        if (fArr != null && fArr.length >= 2) {
            float f = fArr[0];
            int length = fArr.length;
            int i = 1;
            while (i < length) {
                float f2 = fArr[i];
                if (Float.compare(f, f2) > 0) {
                    return false;
                }
                i++;
                f = f2;
            }
        }
        return true;
    }

    public static boolean isSorted(byte[] bArr) {
        if (bArr != null && bArr.length >= 2) {
            byte b = bArr[0];
            int length = bArr.length;
            int i = 1;
            while (i < length) {
                byte b2 = bArr[i];
                if (NumberUtils.compare(b, b2) > 0) {
                    return false;
                }
                i++;
                b = b2;
            }
        }
        return true;
    }

    public static boolean isSorted(char[] cArr) {
        if (cArr != null && cArr.length >= 2) {
            char c = cArr[0];
            int length = cArr.length;
            int i = 1;
            while (i < length) {
                char c2 = cArr[i];
                if (CharUtils.compare(c, c2) > 0) {
                    return false;
                }
                i++;
                c = c2;
            }
        }
        return true;
    }

    public static boolean isSorted(boolean[] zArr) {
        if (zArr != null && zArr.length >= 2) {
            boolean z = zArr[0];
            int length = zArr.length;
            int i = 1;
            while (i < length) {
                boolean z2 = zArr[i];
                if (BooleanUtils.compare(z, z2) > 0) {
                    return false;
                }
                i++;
                z = z2;
            }
        }
        return true;
    }

    public static boolean[] removeAllOccurences(boolean[] zArr, boolean z) {
        int indexOf = indexOf(zArr, z);
        if (indexOf == -1) {
            return clone(zArr);
        }
        int[] iArr = new int[(zArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(zArr, z, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(zArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static char[] removeAllOccurences(char[] cArr, char c) {
        int indexOf = indexOf(cArr, c);
        if (indexOf == -1) {
            return clone(cArr);
        }
        int[] iArr = new int[(cArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(cArr, c, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(cArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static byte[] removeAllOccurences(byte[] bArr, byte b) {
        int indexOf = indexOf(bArr, b);
        if (indexOf == -1) {
            return clone(bArr);
        }
        int[] iArr = new int[(bArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(bArr, b, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(bArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static short[] removeAllOccurences(short[] sArr, short s) {
        int indexOf = indexOf(sArr, s);
        if (indexOf == -1) {
            return clone(sArr);
        }
        int[] iArr = new int[(sArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(sArr, s, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(sArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static int[] removeAllOccurences(int[] iArr, int i) {
        int indexOf = indexOf(iArr, i);
        if (indexOf == -1) {
            return clone(iArr);
        }
        int[] iArr2 = new int[(iArr.length - indexOf)];
        iArr2[0] = indexOf;
        int i2 = 1;
        while (true) {
            int indexOf2 = indexOf(iArr, i, iArr2[i2 - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(iArr, Arrays.copyOf(iArr2, i2));
            }
            iArr2[i2] = indexOf2;
            i2++;
        }
    }

    public static long[] removeAllOccurences(long[] jArr, long j) {
        int indexOf = indexOf(jArr, j);
        if (indexOf == -1) {
            return clone(jArr);
        }
        int[] iArr = new int[(jArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(jArr, j, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(jArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static float[] removeAllOccurences(float[] fArr, float f) {
        int indexOf = indexOf(fArr, f);
        if (indexOf == -1) {
            return clone(fArr);
        }
        int[] iArr = new int[(fArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(fArr, f, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(fArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static double[] removeAllOccurences(double[] dArr, double d) {
        int indexOf = indexOf(dArr, d);
        if (indexOf == -1) {
            return clone(dArr);
        }
        int[] iArr = new int[(dArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf(dArr, d, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(dArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }

    public static <T> T[] removeAllOccurences(T[] tArr, T t) {
        int indexOf = indexOf((Object[]) tArr, (Object) t);
        if (indexOf == -1) {
            return clone(tArr);
        }
        int[] iArr = new int[(tArr.length - indexOf)];
        iArr[0] = indexOf;
        int i = 1;
        while (true) {
            int indexOf2 = indexOf((Object[]) tArr, (Object) t, iArr[i - 1] + 1);
            if (indexOf2 == -1) {
                return removeAll(tArr, Arrays.copyOf(iArr, i));
            }
            iArr[i] = indexOf2;
            i++;
        }
    }
}
