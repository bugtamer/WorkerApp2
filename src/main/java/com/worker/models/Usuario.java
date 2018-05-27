package com.worker.models;

public class Usuario {
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private String avatar;
	private Ubicacion ubicacion;
	private int id;


	public Usuario(String nombre, String apellidos, String email, String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
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
	
	@Override
	public String toString() {
		return String.format("{id=%d, n=%s, ap=%s, e=%s, p=%s, av=%s, u=%s}",
				id, nombre, apellidos, email, password, avatar, ubicacion);
	}

}
