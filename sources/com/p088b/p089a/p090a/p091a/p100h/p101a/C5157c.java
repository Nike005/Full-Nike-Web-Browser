package com.p088b.p089a.p090a.p091a.p100h.p101a;

import com.p088b.p089a.p090a.p091a.p100h.p101a.C5154b;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.b.a.a.a.h.a.c */
public class C5157c implements C5154b.C5155a {

    /* renamed from: a */
    private final BlockingQueue<Runnable> f5034a = new LinkedBlockingQueue();

    /* renamed from: b */
    private final ThreadPoolExecutor f5035b = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.f5034a);

    /* renamed from: c */
    private final ArrayDeque<C5154b> f5036c = new ArrayDeque<>();

    /* renamed from: d */
    private C5154b f5037d = null;

    /* renamed from: a */
    private void m7201a() {
        C5154b poll = this.f5036c.poll();
        this.f5037d = poll;
        if (poll != null) {
            poll.mo41935a(this.f5035b);
        }
    }

    /* renamed from: a */
    public void mo41937a(C5154b bVar) {
        this.f5037d = null;
        m7201a();
    }

    /* renamed from: b */
    public void mo41940b(C5154b bVar) {
        bVar.mo41933a((C5154b.C5155a) this);
        this.f5036c.add(bVar);
        if (this.f5037d == null) {
            m7201a();
        }
    }
}
