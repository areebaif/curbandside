package com.curbandside.app.utils;

public class DistanceBwTwoCoordinates {

    public static double distanceBetweenTwoCoords(Double latitudeOne, Double longitudeOne, Double latitudeTwo, Double longitudeTwo) {

        long R = 6371L; // Radius of the earth in km
        double dLat = deg2rad(latitudeTwo - latitudeOne);
        double dLng = deg2rad(longitudeTwo - longitudeOne);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(latitudeOne)) *
                                Math.cos(deg2rad(latitudeTwo)) *
                                Math.sin(dLng / 2) *
                                Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c; // Distance in km
        return d * 0.621371;

    }

    private static double deg2rad(double degrees) {
        double pi = Math.PI;
        return degrees * (pi / 180);
    }

    private static double rad2deg(double radians) {
        double pi = Math.PI;
        return radians * (180 / pi);
    }

}
