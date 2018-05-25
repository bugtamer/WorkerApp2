package com.worker.models;

public class Ubicacion {
	private double longitud;
	private double latitud;
	public Ubicacion(double longitud, double latitud) {
		super();
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
	

}
