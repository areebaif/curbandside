package com.curbandside.app.utils;


import java.util.List;

public class BoundingBox {
    private static double deg2rad(double degrees) {
        double pi = Math.PI;
        return degrees * (pi / 180);
    }

    private static double rad2deg(double radians) {
        double pi = Math.PI;
        return radians * (180 / pi);
    }

    public static List<Double> boundingBoxCalculation(double latitude, double longitude, Integer distanceInMiles) {
        List<Double> latLimits = List.of(deg2rad(-90), deg2rad(90));
        List<Double> lngLimits = List.of(deg2rad(-180), deg2rad(180));

        double radLat = deg2rad(latitude);
        double radLng = deg2rad(longitude);

        if (
                radLat < latLimits.getFirst() ||
                        radLat > latLimits.get(1) ||
                        radLng < lngLimits.getFirst() ||
                        radLng > lngLimits.get(1)
        ) {
            throw new Error("invalid arguments");
        }

        // Angular distance in radians on a great circle,
        // using Earth's radius in miles.
        double angular = distanceInMiles / 3958.762079;

        double minLat = radLat - angular;
        double maxLat = radLat + angular;

        double minLng;
        double maxLng;

        if (minLat > latLimits.getFirst() && maxLat < latLimits.get(1)) {
            double deltaLng = Math.asin(Math.sin(angular) / Math.cos(radLat));
            minLng = radLng - deltaLng;

            if (minLng < lngLimits.getFirst()) {

                minLng += 2 * Math.PI;
            }

            maxLng = radLng + deltaLng;

            if (maxLng > lngLimits.get(1)) {
                maxLng -= 2 * Math.PI;
            }
        } else {
            // A pole is contained within the distance.
            minLat = Math.max(minLat, latLimits.getFirst());
            maxLat = Math.min(maxLat, latLimits.get(1));
            minLng = lngLimits.getFirst();
            maxLng = lngLimits.get(1);
        }

        return List.of(rad2deg(minLat), rad2deg(minLng), rad2deg(maxLat), rad2deg(maxLng));

    }
}
