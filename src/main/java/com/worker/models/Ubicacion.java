package com.worker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.worker.util.Haversine;

@Entity
@Table(name="ubicacion")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ubi_id")
	private int id;

	@Column(nullable = false)
	private double longitud;

	@Column(nullable = false)
	private double latitud;



	public Ubicacion() { }
	
	
	public Ubicacion(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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


	@Override
	public String toString() {
		return String.format("{lat=%.3f, lon=%.3f}", latitud, longitud);
	}

}
