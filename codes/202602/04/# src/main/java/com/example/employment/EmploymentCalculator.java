package com.example.employment;

public class EmploymentCalculator {

    /**
     * Calculates the percentage difference between actual and expected values.
     * Formula: ((actual - expected) / expected) * 100
     *
     * @param actual The actual employment number.
     * @param expected The expected employment number.
     * @return The percentage difference. Returns Double.NaN if expected is zero to avoid division by zero.
     */
    public static double calculatePercentageDifference(double actual, double expected) {
        if (expected == 0) {
            return Double.NaN; // Avoid division by zero
        }
        return ((actual - expected) / expected) * 100.0;
    }

    /**
     * Provides a descriptive string for the difference.
     *
     * @param difference The calculated percentage difference.
     * @return A string indicating if the actual value was above, below, or equal to the expected value.
     */
    public static String getComparisonDescription(double difference) {
        if (Double.isNaN(difference)) {
            return "예상치가 0이므로 비교할 수 없습니다.";
        } else if (difference > 0) {
            return String.format("예상치보다 %.2f%% 높습니다.", Math.abs(difference));
        } else if (difference < 0) {
            return String.format("예상치보다 %.2f%% 낮습니다.", Math.abs(difference));
        } else {
            return "예상치와 동일합니다.";
        }
    }
}