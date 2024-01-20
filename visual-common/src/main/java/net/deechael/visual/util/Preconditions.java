package net.deechael.visual.util;

public final class Preconditions {

    public static void range(double value, double min, double max, String message) {
        if (value < min)
            throw new RuntimeException(message);
        if (value > max)
            throw new RuntimeException(message);
    }

    public static void notNull(Object any, String message) {
        if (any == null)
            throw new RuntimeException(message);
    }

    private Preconditions() {
    }

}
