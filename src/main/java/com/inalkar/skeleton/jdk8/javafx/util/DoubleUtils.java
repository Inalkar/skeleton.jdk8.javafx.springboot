package com.inalkar.skeleton.jdk8.javafx.util;

public class DoubleUtils {

    private static final double EPSILON = 0.0000001d;

    public static boolean isEqualWithEpsilon(final double a, final double b) {
        if (a == b) return true;
        return Math.abs(a - b) < EPSILON;
    }

    public static int compareWithEpsilon(final double a, final double b) {
        return isEqualWithEpsilon(a, b) ? 0 : (a < b) ? -1 : +1;
    }

}
