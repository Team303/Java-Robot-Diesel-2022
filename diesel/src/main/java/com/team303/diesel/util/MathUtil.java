package com.team303.diesel.util;

public class MathUtil {
    private MathUtil() {
        throw new UnsupportedOperationException("Cannot instantiate helper class");
    }

    public static double map(double input, double lower1, double higher1, double lower2, double higher2) {
        return higher1 + ((input - lower1)*(higher2 - higher1))/(lower2 - lower1);
    }

    public static double clamp(double input, double lowest, double highest) {
        if (input < lowest) return lowest;
        else if (input > highest) return highest;
        return input;
    }
}
