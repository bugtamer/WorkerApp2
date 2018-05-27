package com.worker.domain;

/**
 * Source:
 * https://www.sunearthtools.com/es/tools/distance.php
 */
public class Haversine {
	
    public static final double EARTH_RADIUS_R_KM = 6372.795477598;
    
    public static double calcDistantKm(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        return EARTH_RADIUS_R_KM * Math.acos(
        		Math.sin(lat1) *
        		Math.sin(lat2) +
        		Math.cos(lat1) *
        		Math.cos(lat2) *
        		Math.cos(lat1-lat2)
        	);
    }
    
}