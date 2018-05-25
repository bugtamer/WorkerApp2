package com.worker.models;

import java.sql.Date;

public class Valoracion {
	private Usuario autor;
	private String comentario;
	private int puntuacion;
	private Date timestamp;
	public Valoracion(Usuario autor, String comentario, int puntuacion, Date timestamp) {
		super();
		this.autor = autor;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.timestamp = timestamp;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
