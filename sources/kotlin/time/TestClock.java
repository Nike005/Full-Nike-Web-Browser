package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import org.apache.commons.lang3.ClassUtils;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, mo45501d2 = {"Lkotlin/time/TestClock;", "Lkotlin/time/AbstractLongClock;", "()V", "reading", "", "overflow", "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(D)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, mo45502k = 1, mo45503mv = {1, 1, 15})
/* compiled from: Clocks.kt */
public final class TestClock extends AbstractLongClock {
    private long reading;

    public TestClock() {
        super(TimeUnit.NANOSECONDS);
    }

    /* access modifiers changed from: protected */
    public long read() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m8238plusAssignLRDsOJo(double d) {
        long j;
        double r0 = Duration.m8223toDoubleimpl(d, getUnit());
        long j2 = (long) r0;
        if (j2 == Long.MIN_VALUE || j2 == Long.MAX_VALUE) {
            double d2 = (double) this.reading;
            Double.isNaN(d2);
            double d3 = d2 + r0;
            if (d3 > ((double) Long.MAX_VALUE) || d3 < ((double) Long.MIN_VALUE)) {
                m8237overflowLRDsOJo(d);
            }
            j = (long) d3;
        } else {
            long j3 = this.reading;
            j = j3 + j2;
            if ((j2 ^ j3) >= 0 && (j3 ^ j) < 0) {
                m8237overflowLRDsOJo(d);
            }
        }
        this.reading = j;
    }

    /* renamed from: overflow-LRDsOJo  reason: not valid java name */
    private final void m8237overflowLRDsOJo(double d) {
        throw new IllegalStateException("TestClock will overflow if its reading " + this.reading + "ns is advanced by " + Duration.m8229toStringimpl(d) + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }
}
