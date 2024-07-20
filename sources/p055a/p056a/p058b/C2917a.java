package p055a.p056a.p058b;

import p055a.p056a.C2969h;
import p055a.p056a.p058b.p060b.C2922b;
import p055a.p056a.p058b.p060b.C2928h;
import p055a.p056a.p062d.C2944b;

/* renamed from: a.a.b.a */
/* compiled from: StartAppSDK */
public final class C2917a {
    /* renamed from: a */
    public static final <T> Class<T> m6136a(C2944b<T> bVar) {
        C2928h.m6157b(bVar, "$receiver");
        Class a = ((C2922b) bVar).mo24966a();
        if (a.isPrimitive()) {
            String name = a.getName();
            if (name != null) {
                switch (name.hashCode()) {
                    case -1325958191:
                        if (name.equals("double")) {
                            a = Double.class;
                            break;
                        }
                        break;
                    case 104431:
                        if (name.equals("int")) {
                            a = Integer.class;
                            break;
                        }
                        break;
                    case 3039496:
                        if (name.equals("byte")) {
                            a = Byte.class;
                            break;
                        }
                        break;
                    case 3052374:
                        if (name.equals("char")) {
                            a = Character.class;
                            break;
                        }
                        break;
                    case 3327612:
                        if (name.equals("long")) {
                            a = Long.class;
                            break;
                        }
                        break;
                    case 3625364:
                        if (name.equals("void")) {
                            a = Void.class;
                            break;
                        }
                        break;
                    case 64711720:
                        if (name.equals("boolean")) {
                            a = Boolean.class;
                            break;
                        }
                        break;
                    case 97526364:
                        if (name.equals("float")) {
                            a = Float.class;
                            break;
                        }
                        break;
                    case 109413500:
                        if (name.equals("short")) {
                            a = Short.class;
                            break;
                        }
                        break;
                }
            }
            if (a != null) {
                return a;
            }
            throw new C2969h("null cannot be cast to non-null type java.lang.Class<T>");
        } else if (a != null) {
            return a;
        } else {
            throw new C2969h("null cannot be cast to non-null type java.lang.Class<T>");
        }
    }
}
