package com.worker.models;

import com.worker.domain.Haversine;

public class Ubicacion {
	private double longitud;
	private double latitud;
	
	
	
	public Ubicacion(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}
	
	
	
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	
	
	public double getDistanciaKilometrica(Ubicacion otra) {
		double lat1 = this.getLatitud();
		double lon1 = this.getLongitud();
		double lat2 = otra.getLatitud();
		double lon2 = otra.getLongitud();
		return Haversine.calcDistantKm(lat1, lon1, lat2, lon2);
	}
	
}
