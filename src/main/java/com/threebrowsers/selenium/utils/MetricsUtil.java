package com.threebrowsers.selenium.utils;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import java.util.ArrayList;
import java.util.List;

public class MetricsUtil {

    private static final List<Long> tiempos = new ArrayList<>();

    public static void addTime(long time) {
        tiempos.add(time);
    }

    public static List<Long> getTimes() {
        return new ArrayList<>(tiempos);
    }

    // --- Mínimo ---
    public static long getMin() {
        if (tiempos.isEmpty()) return 0;
        return tiempos.stream().min(Long::compare).orElse(0L);
    }

    // --- Máximo ---
    public static long getMax() {
        if (tiempos.isEmpty()) return 0;
        return tiempos.stream().max(Long::compare).orElse(0L);
    }

    // --- Percentil 95 ---
    public static double getP95() {
        return getPercentile(95);
    }

    // --- Mediana (Percentil 50) ---
    public static double getMedian() {
        return getPercentile(50);
    }

    // --- Método privado genérico para percentiles ---
    private static double getPercentile(double percentileValue) {
        if (tiempos.isEmpty()) return 0;

        double[] values = tiempos.stream()
                .mapToDouble(Long::doubleValue)
                .toArray();

        Percentile percentile = new Percentile()
                .withEstimationType(Percentile.EstimationType.R_7);

        double result = percentile.evaluate(values, percentileValue);
        return Math.round(result * 100.0) / 100.0;
    }

    // --- Limpieza de métricas ---
    public static void reset() {
        tiempos.clear();
    }
}
