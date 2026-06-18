package com.threebrowsers.selenium.utils;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;

import java.util.ArrayList;
import java.util.List;

public class MetricsUtil {
    private static final ThreadLocal<List<Long>> tiemposThreadLocal = ThreadLocal.withInitial(ArrayList::new);

    private static List<Long> getTiempos() {
        return tiemposThreadLocal.get();
    }

    public static void addTime(long time) {
        getTiempos().add(time);
    }

    public static List<Long> getTimes() {
        return new ArrayList<>(getTiempos());
    }

    public static long getMin() {
        List<Long> tiempos = getTiempos();
        if (tiempos.isEmpty()) return 0;
        return tiempos.stream().min(Long::compare).orElse(0L);
    }

    public static long getMax() {
        List<Long> tiempos = getTiempos();
        if (tiempos.isEmpty()) return 0;
        return tiempos.stream().max(Long::compare).orElse(0L);
    }

    public static double getP95() {
        return getPercentile(95);
    }

    public static double getMedian() {
        return getPercentile(50);
    }

    private static double getPercentile(double percentileValue) {
        List<Long> tiempos = getTiempos();
        if (tiempos.isEmpty()) return 0;

        double[] values = tiempos.stream()
                .mapToDouble(Long::doubleValue)
                .toArray();

        Percentile percentile = new Percentile()
                .withEstimationType(Percentile.EstimationType.R_7);

        double result = percentile.evaluate(values, percentileValue);
        return Math.round(result * 100.0) / 100.0;
    }

    public static void reset() {
        tiemposThreadLocal.remove();
    }
}