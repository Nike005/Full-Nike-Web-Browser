package com.anthonycr.bonsai;

final class Preconditions {
    private Preconditions() {
        throw new UnsupportedOperationException("This class is not instantiable");
    }

    static void checkNonNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Object must not be null");
        }
    }
}
