package org.jdom2.internal;

import java.lang.reflect.InvocationTargetException;

public class ReflectionConstructor {
    public static final <E> E construct(String str, Class<E> cls) {
        try {
            Class<?> cls2 = Class.forName(str);
            if (cls.isAssignableFrom(cls2)) {
                return cls.cast(cls2.getConstructor(new Class[0]).newInstance(new Object[0]));
            }
            throw new ClassCastException("Class '" + str + "' is not assignable to '" + cls.getName() + "'.");
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to locate class '" + str + "'.", e);
        } catch (NoSuchMethodException e2) {
            throw new IllegalArgumentException("Unable to locate class no-arg constructor '" + str + "'.", e2);
        } catch (SecurityException e3) {
            throw new IllegalStateException("Unable to access class constructor '" + str + "'.", e3);
        } catch (IllegalAccessException e4) {
            throw new IllegalStateException("Unable to access class constructor '" + str + "'.", e4);
        } catch (InstantiationException e5) {
            throw new IllegalStateException("Unable to instantiate class '" + str + "'.", e5);
        } catch (InvocationTargetException e6) {
            throw new IllegalStateException("Unable to call class constructor '" + str + "'.", e6);
        }
    }
}
