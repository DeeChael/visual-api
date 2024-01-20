package net.deechael.visual.math;

public final class Calculation {

    public static double square(double number) {
        return number * number;
    }

    public static double cubic(double number) {
        return square(number) * number;
    }

    public static boolean almostZero(double number, double accuracy) {
        return Math.abs(number) <= accuracy;
    }

    public static boolean smallerOrAlmostZero(double number, double accuracy) {
        return number <= accuracy || number < 0;
    }

    public static boolean largerOrAlmostZero(double number, double accuracy) {
        return number >= 0;
    }

    private Calculation() {
    }

}
