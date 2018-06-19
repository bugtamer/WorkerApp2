package com.worker.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	protected int id;

	@Column(nullable = false)
	protected String nombre;

	@Column(nullable = false)
	protected String apellidos;

	@Column(nullable = false)
	protected String email;

	@Column(nullable = false)
	protected String password;

	@Column(name = "url_avatar",   nullable = true)
	protected String avatar;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="fk_ubi")
	protected Ubicacion ubicacion;
	
	@OneToMany(mappedBy = "autor")
	protected List<Valoracion> valoracionesHechas;


	public Usuario() { }
	
	
	public Usuario(String nombre, String apellidos, String email, String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.valoracionesHechas = new ArrayList<>();
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public List<Valoracion> getValoracionesHechas() {
		return valoracionesHechas;
	}
	public void addValoracionesHechas(Valoracion valoracion) {
		this.valoracionesHechas.add(valoracion);
	}


	@Override
	public String toString() {
		return String.format("{id=%d, n=%s, ap=%s, e=%s, p=%s, av=%s, u=%s}",
				id, nombre, apellidos, email, password, avatar, ubicacion);
	}

}
