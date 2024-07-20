package com.appnext.core;

/* renamed from: com.appnext.core.b */
public final class C4946b {
    private String cat = "";
    private int cnt;

    /* renamed from: fM */
    private String f4726fM = "";

    /* renamed from: fN */
    private int f4727fN;

    /* renamed from: fO */
    private int f4728fO;
    private String pbk = "";

    public C4946b(C4924Ad ad) {
        this.f4726fM = ad.getPlacementID();
        this.cat = ad.getCategories();
        this.pbk = ad.getPostback();
        this.f4727fN = ad.getMinVideoLength();
        this.f4728fO = ad.getMaxVideoLength();
        this.cnt = ad.getCount();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!getClass().isInstance(obj) && !obj.getClass().isInstance(this)) {
            return false;
        }
        if (!(obj instanceof C4946b)) {
            return super.equals(obj);
        }
        C4946b bVar = (C4946b) obj;
        return bVar.f4726fM.equals(this.f4726fM) && bVar.cat.equals(this.cat) && bVar.pbk.equals(this.pbk) && bVar.f4727fN == this.f4727fN && bVar.f4728fO == this.f4728fO && bVar.cnt == this.cnt;
    }

    public final int hashCode() {
        return (((((((((this.f4726fM.hashCode() * 31) + this.cat.hashCode()) * 31) + this.pbk.hashCode()) * 31) + this.f4727fN) * 31) + this.f4728fO) * 31) + this.cnt;
    }
}
