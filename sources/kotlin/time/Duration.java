package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u0013\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\r\u00103\u001a\u000200¢\u0006\u0004\b4\u00105J\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00105J\r\u00108\u001a\u000200¢\u0006\u0004\b9\u00105J\r\u0010:\u001a\u000200¢\u0006\u0004\b;\u00105J\u001b\u0010<\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010,J\u001b\u0010>\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010,J\u0017\u0010@\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bA\u0010(J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010,J\u001b\u0010B\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010-J\u0001\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2u\u0010F\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0GH\b¢\u0006\u0004\bO\u0010PJx\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2`\u0010F\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0QH\b¢\u0006\u0004\bO\u0010RJc\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E2K\u0010F\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(L\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0SH\b¢\u0006\u0004\bO\u0010TJN\u0010D\u001a\u0002HE\"\u0004\b\u0000\u0010E26\u0010F\u001a2\u0012\u0013\u0012\u00110V¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002HE0UH\b¢\u0006\u0004\bO\u0010WJ\u0019\u0010X\u001a\u00020\u00032\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b\\\u0010]J\u0019\u0010^\u001a\u00020\t2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\b_\u0010`J\r\u0010a\u001a\u00020b¢\u0006\u0004\bc\u0010dJ\u0019\u0010e\u001a\u00020V2\n\u0010Y\u001a\u00060Zj\u0002`[¢\u0006\u0004\bf\u0010gJ\r\u0010h\u001a\u00020V¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020V¢\u0006\u0004\bl\u0010jJ\u000f\u0010m\u001a\u00020bH\u0016¢\u0006\u0004\bn\u0010dJ#\u0010m\u001a\u00020b2\n\u0010Y\u001a\u00060Zj\u0002`[2\b\b\u0002\u0010o\u001a\u00020\t¢\u0006\u0004\bn\u0010pJ\u0013\u0010q\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006t"}, mo45501d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-impl", "hoursComponent", "", "hoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "minutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "nanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "secondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-impl", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "hashCode", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-impl", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-impl", "Companion", "kotlin-stdlib"}, mo45502k = 1, mo45503mv = {1, 1, 15})
/* compiled from: Duration.kt */
public final class Duration implements Comparable<Duration> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final double INFINITE = m8191constructorimpl(DoubleCompanionObject.INSTANCE.getPOSITIVE_INFINITY());
    /* access modifiers changed from: private */
    public static final double ZERO = m8191constructorimpl(0.0d);
    private final double value;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m8189boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static double m8191constructorimpl(double d) {
        return d;
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m8192divLRDsOJo(double d, double d2) {
        return d / d2;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m8195equalsimpl(double d, Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).m8234unboximpl()) == 0;
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m8196equalsimpl0(double d, double d2) {
        throw null;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m8209hashCodeimpl(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public static /* synthetic */ void hoursComponent$annotations() {
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m8212isNegativeimpl(double d) {
        return d < ((double) 0);
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m8213isPositiveimpl(double d) {
        return d > ((double) 0);
    }

    public static /* synthetic */ void minutesComponent$annotations() {
    }

    public static /* synthetic */ void nanosecondsComponent$annotations() {
    }

    /* renamed from: precision-impl  reason: not valid java name */
    private static final int m8216precisionimpl(double d, double d2) {
        if (d2 < ((double) 1)) {
            return 3;
        }
        if (d2 < ((double) 10)) {
            return 2;
        }
        return d2 < ((double) 100) ? 1 : 0;
    }

    public static /* synthetic */ void secondsComponent$annotations() {
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m8233compareToLRDsOJo(double d) {
        return m8190compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object obj) {
        return m8195equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m8209hashCodeimpl(this.value);
    }

    public String toString() {
        return m8229toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ double m8234unboximpl() {
        return this.value;
    }

    private /* synthetic */ Duration(double d) {
        this.value = d;
    }

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return m8233compareToLRDsOJo(((Duration) obj).m8234unboximpl());
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo45501d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE", "()D", "D", "ZERO", "getZERO", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, mo45502k = 1, mo45503mv = {1, 1, 15})
    /* compiled from: Duration.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final double getZERO() {
            return Duration.ZERO;
        }

        public final double getINFINITE() {
            return Duration.INFINITE;
        }

        public final double convert(double d, TimeUnit timeUnit, TimeUnit timeUnit2) {
            Intrinsics.checkParameterIsNotNull(timeUnit, "sourceUnit");
            Intrinsics.checkParameterIsNotNull(timeUnit2, "targetUnit");
            return DurationUnitKt.convertDurationUnit(d, timeUnit, timeUnit2);
        }
    }

    /* renamed from: unaryMinus-impl  reason: not valid java name */
    public static final double m8232unaryMinusimpl(double d) {
        return m8191constructorimpl(-d);
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final double m8215plusLRDsOJo(double d, double d2) {
        return m8191constructorimpl(d + d2);
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final double m8214minusLRDsOJo(double d, double d2) {
        return m8191constructorimpl(d - d2);
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m8218timesimpl(double d, int i) {
        double d2 = (double) i;
        Double.isNaN(d2);
        return m8191constructorimpl(d * d2);
    }

    /* renamed from: times-impl  reason: not valid java name */
    public static final double m8217timesimpl(double d, double d2) {
        return m8191constructorimpl(d * d2);
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m8194divimpl(double d, int i) {
        double d2 = (double) i;
        Double.isNaN(d2);
        return m8191constructorimpl(d / d2);
    }

    /* renamed from: div-impl  reason: not valid java name */
    public static final double m8193divimpl(double d, double d2) {
        return m8191constructorimpl(d / d2);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m8211isInfiniteimpl(double d) {
        return Double.isInfinite(d);
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m8210isFiniteimpl(double d) {
        return !Double.isInfinite(d) && !Double.isNaN(d);
    }

    /* renamed from: getAbsoluteValue-impl  reason: not valid java name */
    public static final double m8197getAbsoluteValueimpl(double d) {
        return m8212isNegativeimpl(d) ? m8232unaryMinusimpl(d) : d;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m8190compareToLRDsOJo(double d, double d2) {
        return Double.compare(d, d2);
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m8222toComponentsimpl(double d, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function5) {
        Intrinsics.checkParameterIsNotNull(function5, "action");
        return function5.invoke(Integer.valueOf((int) m8199getInDaysimpl(d)), Integer.valueOf(m8198getHoursComponentimpl(d)), Integer.valueOf(m8206getMinutesComponentimpl(d)), Integer.valueOf(m8208getSecondsComponentimpl(d)), Integer.valueOf(m8207getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m8221toComponentsimpl(double d, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> function4) {
        Intrinsics.checkParameterIsNotNull(function4, "action");
        return function4.invoke(Integer.valueOf((int) m8200getInHoursimpl(d)), Integer.valueOf(m8206getMinutesComponentimpl(d)), Integer.valueOf(m8208getSecondsComponentimpl(d)), Integer.valueOf(m8207getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m8220toComponentsimpl(double d, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> function3) {
        Intrinsics.checkParameterIsNotNull(function3, "action");
        return function3.invoke(Integer.valueOf((int) m8203getInMinutesimpl(d)), Integer.valueOf(m8208getSecondsComponentimpl(d)), Integer.valueOf(m8207getNanosecondsComponentimpl(d)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m8219toComponentsimpl(double d, Function2<? super Long, ? super Integer, ? extends T> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "action");
        return function2.invoke(Long.valueOf((long) m8205getInSecondsimpl(d)), Integer.valueOf(m8207getNanosecondsComponentimpl(d)));
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m8198getHoursComponentimpl(double d) {
        double r2 = m8200getInHoursimpl(d);
        double d2 = (double) 24;
        Double.isNaN(d2);
        return (int) (r2 % d2);
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m8206getMinutesComponentimpl(double d) {
        double r2 = m8203getInMinutesimpl(d);
        double d2 = (double) 60;
        Double.isNaN(d2);
        return (int) (r2 % d2);
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m8208getSecondsComponentimpl(double d) {
        double r2 = m8205getInSecondsimpl(d);
        double d2 = (double) 60;
        Double.isNaN(d2);
        return (int) (r2 % d2);
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m8207getNanosecondsComponentimpl(double d) {
        return (int) (m8204getInNanosecondsimpl(d) % 1.0E9d);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m8223toDoubleimpl(double d, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return DurationUnitKt.convertDurationUnit(d, DurationKt.getStorageUnit(), timeUnit);
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m8226toLongimpl(double d, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return (long) m8223toDoubleimpl(d, timeUnit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m8224toIntimpl(double d, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return (int) m8223toDoubleimpl(d, timeUnit);
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m8199getInDaysimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m8200getInHoursimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m8203getInMinutesimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m8205getInSecondsimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m8202getInMillisecondsimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m8201getInMicrosecondsimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m8204getInNanosecondsimpl(double d) {
        return m8223toDoubleimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m8228toLongNanosecondsimpl(double d) {
        return m8226toLongimpl(d, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m8227toLongMillisecondsimpl(double d) {
        return m8226toLongimpl(d, TimeUnit.MILLISECONDS);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x009f  */
    /* renamed from: toString-impl  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m8229toStringimpl(double r8) {
        /*
            boolean r0 = m8211isInfiniteimpl(r8)
            if (r0 == 0) goto L_0x000c
            java.lang.String r8 = java.lang.String.valueOf(r8)
            goto L_0x00c0
        L_0x000c:
            r0 = 0
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x0016
            java.lang.String r8 = "0s"
            goto L_0x00c0
        L_0x0016:
            double r0 = m8197getAbsoluteValueimpl(r8)
            double r0 = m8204getInNanosecondsimpl(r0)
            r2 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            r4 = 0
            r5 = 1
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x002e
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
        L_0x002b:
            r1 = 0
            r4 = 1
            goto L_0x008f
        L_0x002e:
            double r2 = (double) r5
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0037
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            r1 = 7
            goto L_0x008f
        L_0x0037:
            r2 = 4652007308841189376(0x408f400000000000, double:1000.0)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0044
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
        L_0x0042:
            r1 = 0
            goto L_0x008f
        L_0x0044:
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0050
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MICROSECONDS
            goto L_0x0042
        L_0x0050:
            r2 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x005c
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            goto L_0x0042
        L_0x005c:
            r2 = 4786511204640096256(0x426d1a94a2000000, double:1.0E12)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0068
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS
            goto L_0x0042
        L_0x0068:
            r2 = 4813020802404319232(0x42cb48eb57e00000, double:6.0E13)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0074
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MINUTES
            goto L_0x0042
        L_0x0074:
            r2 = 4839562400168542208(0x4329945ca2620000, double:3.6E15)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0080
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.HOURS
            goto L_0x0042
        L_0x0080:
            r2 = 4920018990336211136(0x44476b344f2a78c0, double:8.64E20)
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x008c
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L_0x0042
        L_0x008c:
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.DAYS
            goto L_0x002b
        L_0x008f:
            double r2 = m8223toDoubleimpl(r8, r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            if (r4 == 0) goto L_0x009f
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatScientific(r2)
            goto L_0x00b2
        L_0x009f:
            if (r1 <= 0) goto L_0x00a6
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatUpToDecimals(r2, r1)
            goto L_0x00b2
        L_0x00a6:
            double r6 = java.lang.Math.abs(r2)
            int r8 = m8216precisionimpl(r8, r6)
            java.lang.String r8 = kotlin.time.FormatToDecimalsKt.formatToExactDecimals(r2, r8)
        L_0x00b2:
            r5.append(r8)
            java.lang.String r8 = kotlin.time.DurationUnitKt.shortName(r0)
            r5.append(r8)
            java.lang.String r8 = r5.toString()
        L_0x00c0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.time.Duration.m8229toStringimpl(double):java.lang.String");
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m8231toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m8230toStringimpl(d, timeUnit, i);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m8230toStringimpl(double d, TimeUnit timeUnit, int i) {
        String str;
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + i).toString());
        } else if (m8211isInfiniteimpl(d)) {
            return String.valueOf(d);
        } else {
            double r6 = m8223toDoubleimpl(d, timeUnit);
            StringBuilder sb = new StringBuilder();
            if (Math.abs(r6) < 1.0E14d) {
                str = FormatToDecimalsKt.formatToExactDecimals(r6, RangesKt.coerceAtMost(i, 12));
            } else {
                str = FormatToDecimalsKt.formatScientific(r6);
            }
            sb.append(str);
            sb.append(DurationUnitKt.shortName(timeUnit));
            return sb.toString();
        }
    }

    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m8225toIsoStringimpl(double d) {
        StringBuilder sb = new StringBuilder();
        if (m8212isNegativeimpl(d)) {
            sb.append('-');
        }
        sb.append("PT");
        double r7 = m8197getAbsoluteValueimpl(d);
        int r1 = (int) m8200getInHoursimpl(r7);
        int r2 = m8206getMinutesComponentimpl(r7);
        int r3 = m8208getSecondsComponentimpl(r7);
        int r72 = m8207getNanosecondsComponentimpl(r7);
        boolean z = true;
        boolean z2 = r1 != 0;
        boolean z3 = (r3 == 0 && r72 == 0) ? false : true;
        if (r2 == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(r1);
            sb.append('H');
        }
        if (z) {
            sb.append(r2);
            sb.append('M');
        }
        if (z3 || (!z2 && !z)) {
            sb.append(r3);
            if (r72 != 0) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                String padStart = StringsKt.padStart(String.valueOf(r72), 9, '0');
                if (r72 % 1000000 == 0) {
                    sb.append(padStart, 0, 3);
                } else if (r72 % 1000 == 0) {
                    sb.append(padStart, 0, 6);
                } else {
                    sb.append(padStart);
                }
            }
            sb.append('S');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}