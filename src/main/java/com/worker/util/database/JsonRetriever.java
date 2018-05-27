package com.worker.util.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonRetriever {
	
	// INSTANCIACION
	
	private JsonRetriever() { }
	
	// SERVICIOS
	
	
	/**
	 * Permite recuperar las respuestas proporcionadas por un servidor web remoto
	 * como los datos que tenemos en mocky.io
	 * 
	 * @param origenDatos es la URL del servidor remoto
	 * @return String con la respuesta del origen de datos
	 * @throws Exception
	 */
	public static String getDataFrom(DataSource origenDatos) throws Exception {
		URL url = new URL(origenDatos.getURL());
		
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		final String USER_AGENT = "Mozilla/5.0";
		conexion.setRequestProperty("User-Agent", USER_AGENT); // header
		
		int code = conexion.getResponseCode();
		
		StringBuffer respuesta = null;
		if (code == 200) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader( conexion.getInputStream() )  );
			String line;
			respuesta = new StringBuffer();
			while ((line = in.readLine()) != null) {
				respuesta.append(line);
			}
			in.close();
		}
		return (respuesta == null) ? null : respuesta.toString();
	}

}
