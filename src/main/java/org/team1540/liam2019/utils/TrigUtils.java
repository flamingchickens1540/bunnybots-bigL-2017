package org.team1540.liam2019.utils;

public class TrigUtils {
    public static double signedAngleError(double target, double source) {
        double diff = (target - source + Math.PI) % (Math.PI * 2) - Math.PI;
        return diff < -Math.PI ? diff + (Math.PI * 2) : diff;
    }
}
