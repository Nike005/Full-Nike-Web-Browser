package com.appnext.core;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.core.C4956e;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: com.appnext.core.q */
public final class C4990q {

    /* renamed from: y */
    private static final String f4856y = "error_no_market";
    /* access modifiers changed from: private */
    public String banner = "";
    /* access modifiers changed from: private */
    public C4956e click;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */

    /* renamed from: gj */
    public String f4857gj = "";
    /* access modifiers changed from: private */
    public String guid = "";
    /* access modifiers changed from: private */

    /* renamed from: hH */
    public C4973h f4858hH;
    /* access modifiers changed from: private */

    /* renamed from: hI */
    public C4999a f4859hI;
    /* access modifiers changed from: private */

    /* renamed from: hJ */
    public C4956e.C4965a f4860hJ = new C4956e.C4965a() {
        public final void onMarket(String str) {
            AppnextAd f = C4990q.this.f4859hI.mo40518f();
            C4924Ad e = C4990q.this.f4859hI.mo40517e();
            if (e != null && f != null && C4990q.this.context != null) {
                if (!C4967f.m6842c(C4990q.this.context, f.getAdPackage())) {
                    try {
                        if (!str.startsWith("market://details?id=" + f.getAdPackage()) && !str.startsWith(Constants.HTTP)) {
                            C4990q.this.mo41314b(C4967f.m6846m("admin.appnext.com", "applink"), f.getBannerID(), e.getPlacementID(), e.getTID(), str, "SetROpenV1");
                        }
                    } catch (Throwable unused) {
                    }
                    if (C4990q.this.f4858hH == null) {
                        C4973h unused2 = C4990q.this.f4858hH = new C4973h();
                    }
                    C4973h h = C4990q.this.f4858hH;
                    String adPackage = f.getAdPackage();
                    String m = C4967f.m6846m("admin.appnext.com", "applink");
                    String bannerID = f.getBannerID();
                    String placementID = e.getPlacementID();
                    String tid = e.getTID();
                    String vid = e.getVID();
                    String auid = e.getAUID();
                    h.f4819am = adPackage;
                    h.f4820an = str;
                    h.guid = m;
                    h.f4821ao = bannerID;
                    h.f4822ap = placementID;
                    h.f4826at = null;
                    h.f4823aq = tid;
                    h.f4824ar = vid;
                    h.f4825as = auid;
                    C4990q.this.f4858hH.mo41295t(C4990q.this.context.getApplicationContext());
                } else if (str.startsWith("market://")) {
                    try {
                        Intent launchIntentForPackage = C4990q.this.context.getPackageManager().getLaunchIntentForPackage(f.getAdPackage());
                        launchIntentForPackage.addFlags(268435456);
                        C4990q.this.context.startActivity(launchIntentForPackage);
                    } catch (Throwable unused3) {
                        C4990q.this.f4859hI.report("error_no_market");
                    }
                } else {
                    try {
                        C4990q.m6906d(C4990q.this, str);
                    } catch (Throwable unused4) {
                        C4990q.this.f4859hI.report("error_no_market");
                    }
                }
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(5:6|7|8|9|20)(1:(1:11)(5:12|13|14|15|17))) */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0087 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x003a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0074 */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x007e  */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0052 A[SYNTHETIC, Splitter:B:6:0x0052] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void error(java.lang.String r8) {
            /*
                r7 = this;
                com.appnext.core.q r0 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x003a }
                java.lang.String r1 = "admin.appnext.com"
                java.lang.String r2 = "applink"
                java.lang.String r1 = com.appnext.core.C4967f.m6846m(r1, r2)     // Catch:{ all -> 0x003a }
                com.appnext.core.q r2 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r2 = r2.f4859hI     // Catch:{ all -> 0x003a }
                com.appnext.core.AppnextAd r2 = r2.mo40518f()     // Catch:{ all -> 0x003a }
                java.lang.String r2 = r2.getBannerID()     // Catch:{ all -> 0x003a }
                com.appnext.core.q r3 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r3 = r3.f4859hI     // Catch:{ all -> 0x003a }
                com.appnext.core.Ad r3 = r3.mo40517e()     // Catch:{ all -> 0x003a }
                java.lang.String r3 = r3.getPlacementID()     // Catch:{ all -> 0x003a }
                com.appnext.core.q r4 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x003a }
                com.appnext.core.q$a r4 = r4.f4859hI     // Catch:{ all -> 0x003a }
                com.appnext.core.Ad r4 = r4.mo40517e()     // Catch:{ all -> 0x003a }
                java.lang.String r4 = r4.getTID()     // Catch:{ all -> 0x003a }
                java.lang.String r6 = "SetDOpenV1"
                r5 = r8
                r0.mo41314b(r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x003a }
            L_0x003a:
                com.appnext.core.q r0 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r0 = r0.f4859hI     // Catch:{ all -> 0x0090 }
                com.appnext.core.p r0 = r0.mo40519g()     // Catch:{ all -> 0x0090 }
                java.lang.String r1 = "urlApp_protection"
                java.lang.String r0 = r0.get(r1)     // Catch:{ all -> 0x0090 }
                boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ all -> 0x0090 }
                java.lang.String r1 = "error_no_market"
                if (r0 == 0) goto L_0x007e
                com.appnext.core.q r8 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0074 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0074 }
                java.lang.String r2 = "market://details?id="
                r0.<init>(r2)     // Catch:{ all -> 0x0074 }
                com.appnext.core.q r2 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0074 }
                com.appnext.core.q$a r2 = r2.f4859hI     // Catch:{ all -> 0x0074 }
                com.appnext.core.AppnextAd r2 = r2.mo40518f()     // Catch:{ all -> 0x0074 }
                java.lang.String r2 = r2.getAdPackage()     // Catch:{ all -> 0x0074 }
                r0.append(r2)     // Catch:{ all -> 0x0074 }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0074 }
                com.appnext.core.C4990q.m6906d(r8, r0)     // Catch:{ all -> 0x0074 }
                goto L_0x0090
            L_0x0074:
                com.appnext.core.q r8 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r8 = r8.f4859hI     // Catch:{ all -> 0x0090 }
                r8.report(r1)     // Catch:{ all -> 0x0090 }
                goto L_0x0090
            L_0x007e:
                if (r8 != 0) goto L_0x0081
                return
            L_0x0081:
                com.appnext.core.q r0 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0087 }
                com.appnext.core.C4990q.m6906d(r0, r8)     // Catch:{ all -> 0x0087 }
                goto L_0x0090
            L_0x0087:
                com.appnext.core.q r8 = com.appnext.core.C4990q.this     // Catch:{ all -> 0x0090 }
                com.appnext.core.q$a r8 = r8.f4859hI     // Catch:{ all -> 0x0090 }
                r8.report(r1)     // Catch:{ all -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.C4990q.C49983.error(java.lang.String):void");
        }
    };

    /* renamed from: com.appnext.core.q$a */
    public interface C4999a {
        /* renamed from: e */
        C4924Ad mo40517e();

        /* renamed from: f */
        AppnextAd mo40518f();

        /* renamed from: g */
        C4986p mo40519g();

        void report(String str);
    }

    public C4990q(Context context2, C4999a aVar) {
        this.context = context2;
        this.click = C4956e.m6799k(context2);
        this.f4859hI = aVar;
    }

    /* renamed from: b */
    public final void mo41314b(String str, String str2, String str3, String str4, String str5, String str6) {
        C4956e eVar = this.click;
        if (eVar != null) {
            new Thread(new Runnable(str, str2, str3, str4, str5, str6) {
                public final void run(
/*
Method generation error in method: com.appnext.core.e.7.run():void, dex: classes.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appnext.core.e.7.run():void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            }).start();
        }
    }

    /* renamed from: a */
    public final void mo41311a(AppnextAd appnextAd, String str, C4956e.C4965a aVar) {
        C4956e eVar = this.click;
        if (eVar != null) {
            new Thread(new Runnable(appnextAd) {
                public final void run(
/*
Method generation error in method: com.appnext.core.e.6.run():void, dex: classes.dex
                jadx.core.utils.exceptions.JadxRuntimeException: Method args not loaded: com.appnext.core.e.6.run():void, class status: UNLOADED
                	at jadx.core.dex.nodes.MethodNode.getArgRegs(MethodNode.java:278)
                	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:116)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:313)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:91)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:697)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                
*/
            }).start();
        }
    }

    /* renamed from: b */
    public final void mo41313b(final AppnextAd appnextAd, final String str, final C4956e.C4965a aVar) {
        if (this.click != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    try {
                        C4990q.this.click.mo41258a(appnextAd.getAppURL(), appnextAd.getMarketUrl(), str + "&device=" + C4967f.m6834be() + "&ox=0", appnextAd.getBannerID(), new C4956e.C4965a() {
                            public final void onMarket(String str) {
                                StringBuilder sb = new StringBuilder("Vta - success - ");
                                sb.append(appnextAd.getAdTitle());
                                sb.append(" -- ");
                                sb.append(appnextAd.getBannerID());
                                String unused = C4990q.this.f4857gj = str;
                                String unused2 = C4990q.this.guid = C4967f.m6846m("admin.appnext.com", "applink");
                                String unused3 = C4990q.this.banner = appnextAd.getBannerID();
                                if (aVar != null) {
                                    aVar.onMarket(str);
                                }
                            }

                            public final void error(String str) {
                                StringBuilder sb = new StringBuilder("Vta - failed - ");
                                sb.append(appnextAd.getAdTitle());
                                sb.append(" -- ");
                                sb.append(appnextAd.getBannerID());
                                String unused = C4990q.this.f4857gj = "";
                                String unused2 = C4990q.this.guid = "";
                                String unused3 = C4990q.this.banner = "";
                                if (aVar != null) {
                                    aVar.error(str);
                                }
                            }
                        });
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public final void mo41312a(AppnextAd appnextAd, String str, String str2, C4956e.C4965a aVar) {
        final String str3 = str;
        final C4956e.C4965a aVar2 = aVar;
        final AppnextAd appnextAd2 = appnextAd;
        final String str4 = str2;
        new Thread(new Runnable() {
            public final void run() {
                if (!C4990q.this.mo41315bo()) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            C4956e.C4965a b = C4990q.this.f4860hJ;
                            b.error(str3 + "&device=" + C4967f.m6834be());
                            if (aVar2 != null) {
                                C4956e.C4965a aVar = aVar2;
                                aVar.error(str3 + "&device=" + C4967f.m6834be());
                            }
                        }
                    });
                } else if (appnextAd2 != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            if (C4990q.this.f4857gj.equals("") || !C4990q.this.f4857gj.contains(appnextAd2.getAdPackage())) {
                                new StringBuilder("click url ").append(str3);
                                String str = str3 + "&device=" + C4967f.m6834be();
                                String webview = appnextAd2.getWebview();
                                char c = 65535;
                                switch (webview.hashCode()) {
                                    case 48:
                                        if (webview.equals("0")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                    case 49:
                                        if (webview.equals("1")) {
                                            c = 1;
                                            break;
                                        }
                                        break;
                                    case 50:
                                        if (webview.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                                            c = 0;
                                            break;
                                        }
                                        break;
                                }
                                if (c == 0) {
                                    try {
                                        C4990q.this.f4860hJ.onMarket(str);
                                        if (aVar2 != null) {
                                            aVar2.onMarket(str);
                                        }
                                    } catch (Throwable unused) {
                                    }
                                } else if (c == 1) {
                                    Intent intent = new Intent(C4990q.this.context, ResultActivity.class);
                                    intent.putExtra("url", str);
                                    intent.putExtra("title", appnextAd2.getAdTitle());
                                    intent.addFlags(268435456);
                                    C4990q.this.context.startActivity(intent);
                                    if (aVar2 != null) {
                                        aVar2.onMarket(str);
                                    }
                                } else if (C4990q.this.click != null) {
                                    C4990q.this.click.mo41259a(appnextAd2.getAppURL(), appnextAd2.getMarketUrl(), str, appnextAd2.getBannerID(), (C4956e.C4965a) new C4956e.C4965a() {
                                        public final void onMarket(String str) {
                                            C4990q.this.f4860hJ.onMarket(str);
                                            if (aVar2 != null) {
                                                aVar2.onMarket(str);
                                            }
                                        }

                                        public final void error(String str) {
                                            C4990q.this.f4860hJ.error(str);
                                            if (aVar2 != null) {
                                                aVar2.error(str);
                                            }
                                        }
                                    }, 1000 * Long.parseLong(C4990q.this.f4859hI.mo40519g().get("resolve_timeout")));
                                }
                            } else {
                                new Thread(new Runnable() {
                                    public final void run() {
                                        try {
                                            C4967f.m6815a("https://admin.appnext.com/AdminService.asmx/SetRL?guid=" + C4990q.this.guid + "&bid=" + C4990q.this.banner + "&pid=" + str4, (HashMap<String, String>) null);
                                        } catch (Throwable unused) {
                                        }
                                    }
                                }).start();
                                C4990q.this.f4860hJ.onMarket(C4990q.this.f4857gj);
                                if (aVar2 != null) {
                                    aVar2.onMarket(C4990q.this.f4857gj);
                                }
                                String unused2 = C4990q.this.f4857gj = "";
                            }
                        }
                    });
                }
            }
        }).start();
    }

    private void openLink(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    /* renamed from: e */
    public final void mo41317e(AppnextAd appnextAd) {
        try {
            if (this.click != null) {
                this.click.mo41262e(appnextAd);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: bo */
    public final boolean mo41315bo() {
        try {
            if (this.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                C4967f.m6815a("http://www.appnext.com/myid.html", (HashMap<String, String>) null);
                return true;
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
            throw new IOException();
        } catch (Throwable unused) {
            return false;
        }
    }

    public final void destroy() {
        try {
            if (this.f4858hH != null) {
                this.f4858hH.mo40469a(this.context);
            }
            this.f4858hH = null;
        } catch (Throwable unused) {
        }
        this.context = null;
        if (this.click != null) {
            this.click = null;
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m6906d(C4990q qVar, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.addFlags(268435456);
        qVar.context.startActivity(intent);
    }
}
