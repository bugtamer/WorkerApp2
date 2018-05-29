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
		setId( usuario.getId() );
		setUbicacion( usuario.getUbicacion() );
		setAvatar( usuario.getAvatar() );
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
	
	public int getMediaValoraciones() {
		int avg = 0;
		List<Valoracion> valoraciones = getValoraciones();
		if (valoraciones != null) {
			for (Valoracion v : valoraciones) {
				avg += v.getPuntuacion();
			}
		}
		if (valoraciones != null) {
			avg = (valoraciones.size() > 0) ? (avg / valoraciones.size()) : 0;
		}
		return avg;
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
	
	
	@Override
	public String toString() {
		StringBuilder edu = new StringBuilder();
		StringBuilder exp = new StringBuilder();
		if (educacion != null) {
			for (String item: educacion) {
				edu.append(item).append('/');
			}
		}
		if (experiencia != null) {
			for (String item: experiencia) {
				exp.append(item).append('/');
			}
		}
		
		return super.toString() + String.format("+{p=%s, ed=%s, ex=%s, v=%d}",
				profesion, edu.toString(), exp.toString(), getMediaValoraciones());
	}



	
}
