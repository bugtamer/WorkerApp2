package com.worker.util.database;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;

public class JsonParser {
	
	private static Gson gson = new Gson();
	
	private JsonParser() { }
	
	
	public static List<Usuario> jsonToUsuarios(String json) {
		return Arrays.asList( gson.fromJson(json, Usuario[].class) );
	}
	
	
	public static List<Manitas> jsonToManitas(String json) {
		return Arrays.asList( gson.fromJson(json, Manitas[].class) );
	}
	
	
	public static List<Mensaje> jsonToMensajes(String json) {
		return Arrays.asList( gson.fromJson(json, Mensaje[].class) );
	}
	
}
