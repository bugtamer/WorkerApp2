package com.worker.util.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;
import com.worker.models.Valoracion;


/**
 * 
 * @author Bugtamer
 *
 */
public class DataRetriever {
	
	// INSTANCIACION
	
	private DataRetriever() { }
	
	// SERVICIOS
	
	
	
	public static List<Usuario> getUsuarios() {
		List<Usuario> usuarios;
		try {
			usuarios = JsonParser.jsonToUsuarios( JsonRetriever.getDataFrom(DataSource.usuarios) );
		} catch (Exception e) {
			usuarios = new ArrayList<>();
		}
		return usuarios;
	}
	
	
	
	public static List<Manitas> getManitas() {
		Random rnd = new Random();
		List<Usuario> usuarios = getUsuarios();
		List<Manitas> listaManitas = new ArrayList<>();
		Valoracion val;
		Usuario autor;
		Manitas manitas;
		String profesiones[] = {
				"Coach personal",
				"Fisioterapeuta deportivo",
				"Entrenador deportivo y coach personal",
				"Nutricionista deportivo",
				"Dietista personal y deportivo",
				"Consejero y coach personal y deportivo"};
		for (int i=3;   i < usuarios.size();   i++) {
			manitas = new Manitas(usuarios.get(i), profesiones[i-3]);
			// educacion
			for (int j=0, nEdu=rnd.nextInt(3);   j < nEdu;   j++) {
				manitas.addEducacion("Educacion " + j);
			}
			// experiencias
			for (int j=0, nExp=rnd.nextInt(5);   j < nExp;   j++) {
				manitas.addExperiencia("Experiencia " + j);
			}
			// valoraciones
			for (int j=0, nVal=rnd.nextInt(10);   j < nVal;   j++) {
				autor = usuarios.get(rnd.nextInt(3));
				val = new Valoracion(autor, manitas, "Lorem ipsum " + j, 1 + rnd.nextInt(5));
				manitas.addValoracion(val);
			}
			listaManitas.add(manitas);
		}
		return listaManitas;
	}
	
	
	public static List<Mensaje> getMensajes() {
		List<Mensaje> mensajes;
		try {
			mensajes = JsonParser.jsonToMensajes( JsonRetriever.getDataFrom(DataSource.chatMessages) );
		} catch (Exception e) {
			mensajes = new ArrayList<>();
		}
		return mensajes;
	}

}
