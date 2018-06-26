package com.worker.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.worker.models.Ubicacion;

public class UbicacionHelper {
	
	public static final String GEO_ATTRIBUTE_NAME = "geolocalizacion";
	
	private UbicacionHelper() { }
	
	
	public static Ubicacion getUbicacion(HttpServletRequest request) {
		Ubicacion ubicacion = null;
		HttpSession sesion = request.getSession();
		if (sesion != null) {
			ubicacion = (Ubicacion) sesion.getAttribute(GEO_ATTRIBUTE_NAME);
		}
		return ubicacion;
	}
	
	
	public static void setUbicacion(HttpServletRequest request, Ubicacion ubicacion) {
		HttpSession sesion = request.getSession();
		if (sesion != null) {
			if (ubicacion != null) {
				sesion.setAttribute(GEO_ATTRIBUTE_NAME, ubicacion);
				request.setAttribute(GEO_ATTRIBUTE_NAME, ubicacion);
				System.out.println(String.format("%s=%s", GEO_ATTRIBUTE_NAME, ubicacion));
			} else {
				System.out.println(String.format("%s=%s", GEO_ATTRIBUTE_NAME, "n/d"));
			}
		}
	}
	
	
	public static void setUbicacion(HttpServletRequest request) {
		setUbicacion(request, parseGeoDataFormIntoUbicacion(request));
	}
	
	
	/**
	 * 
	 * @param request
	 * @return un modelo Ubicacion o null si no hay datos de latitud y longitud.
	 */
	public static Ubicacion parseGeoDataFormIntoUbicacion(HttpServletRequest request) {
		Ubicacion ubicacion = null;
		String latitud = request.getParameter("latitud");
		String longitud = request.getParameter("longitud");
		if ((latitud != null) && (longitud != null)) {
			try {
				double lat = Double.parseDouble( latitud );
				double lon = Double.parseDouble( longitud );
				ubicacion = new Ubicacion(lat, lon);
			} catch(Exception e) {
				System.out.println("ERROR: parseGeoDataFormIntoUbicacion(request)");
				//e.printStackTrace();
			}
		}
		return ubicacion;
	}

}