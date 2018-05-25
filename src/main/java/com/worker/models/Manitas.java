package com.worker.models;
import java.util.ArrayList;
import java.util.List;

import com.worker.models.Usuario;

public class Manitas {
	private String profesion;
	private List<String> experiencia = new ArrayList <String>();
	private List<String> educacion = new ArrayList <String>();
	private List<String> valoraciones = new ArrayList <String>();
	public Manitas(String profesion) {
		this.profesion = profesion;
	}
	public String getProfesion() {
		return profesion;
	}
	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}


}
