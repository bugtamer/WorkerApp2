package com.worker.util;

/**
 * Source:
 * https://www.sunearthtools.com/es/tools/distance.php
 */
public class Haversine {
	
    public static final double EARTH_RADIUS_R_KM = 6372.795477598;
    
    public static double calcDistantKm(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = lat2 * Math.PI/180;
        double radLat2 = lat1 * Math.PI/180;
        double theta = lon2 - lon1;
        double radTheta = theta * Math.PI/180;
        double dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
        dist = (dist > 1) ? 1 : dist;
        dist = Math.acos(dist);
        dist = dist * 180/Math.PI;
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344; // parse to km
        return dist;
    }
    
}