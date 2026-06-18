package com.curbandside.app.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MedianOfArrayElements {

    public static Optional<BigDecimal> getMedian(List<BigDecimal> list) {
        if (list == null || list.isEmpty()) {
            return Optional.empty();
        }
        BigDecimal[] array = list.toArray(new BigDecimal[0]);
        Arrays.sort(array, BigDecimal::compareTo);
        BigDecimal median;
        if (array.length % 2 == 1) {
            // If the array length is odd, return the middle element
            double middle = (double) array.length / 2;
            int integerMiddle = (int) Math.floor(middle);
            median = array[integerMiddle];
        } else {
            // If the array length is even, return the average of the two middle elements
            BigDecimal middleLatitudeOne = array[(array.length / 2) - 1];
            BigDecimal middleLatitudeTwo = array[array.length / 2];
            median = middleLatitudeOne.add(middleLatitudeTwo).divide(new BigDecimal(2), 6, RoundingMode.CEILING);

        }

        return Optional.of(median);
    }

}
