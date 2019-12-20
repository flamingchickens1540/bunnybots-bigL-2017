package org.team1540.liam2019.utils;

public class TrigUtils {
    public static double signedAngleError(double target, double source) {
        return Math.atan2(Math.sin(target - source), Math.cos(target - source));
    }
}
