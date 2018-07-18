package com.worker.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="manitas")
@DiscriminatorValue("Manitas")
@PrimaryKeyJoinColumn(name="fk_usu") // id heredado de Usuario, pero mapeado como indica la anotación
public class Manitas extends Usuario {

	@Column(nullable = false)
	private String profesion;
	//@LazyCollection(LazyCollectionOption.FALSE)
	//@ElementCollection(fetch=FetchType.EAGER)

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name = "experiencia",   joinColumns = @JoinColumn(name="exp_id"))
	@Column(nullable=false)
	private List<String> experiencia;

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@CollectionTable(name = "educacion",   joinColumns = @JoinColumn(name="edu_id"))
	@Column(nullable=false)
	private List<String> educacion;

	@OneToMany(mappedBy = "receptor", fetch=FetchType.EAGER)
	@JsonManagedReference
	private List<Valoracion> valoraciones;



	public Manitas() {
		this.educacion = new ArrayList<>();
	}


	public Manitas(Usuario usuario, String profesion) {
		super(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getPassword());
		this.educacion = new ArrayList<>();
		setId( usuario.getId() );
		setUbicacion( usuario.getUbicacion() );
		setAvatar( usuario.getAvatar() );
		this.profesion = profesion;
	}



	public int getId() {
		return id;
	}


	public String getProfesion() {
		return profesion;
	}




	public void setProfesion(String profesion) {
		this.profesion = profesion;
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



	public void setId(int id) {
		this.id = id;
	}

	public void setExperiencia(List<String> experiencia) {
		this.experiencia = experiencia;
	}

	public void setEducacion(List<String> educacion) {
		this.educacion = educacion;
	}

	public void setValoracion(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
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
