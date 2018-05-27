package com.worker.models;
import java.util.ArrayList;
import java.util.List;

public class Manitas extends Usuario {
	private String profesion;
	private List<String> experiencia = new ArrayList <String>();
	private List<String> educacion = new ArrayList <String>();
	private List<Valoracion> valoraciones = new ArrayList <>();
	
	
	
	public Manitas(Usuario usuario, String profesion) {
		super(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getPassword());
		this.profesion = profesion;
	}
	
	
	
	public String getProfesion() {
		return profesion;
	}

	public List<String> getExperiencia() {
		return experiencia;
	}

	public List<String> getEducacion() {
		return educacion;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}
	
	
	
	public boolean addExperiencia(String experiencia) {
		return this.experiencia.add(experiencia);
	}
	
	public boolean addEducacion(String educacion) {
		return this.educacion.add(educacion);
	}
	
	public boolean addValoracion(Valoracion valoracion) {
		return this.valoraciones.add(valoracion);
	}

}
