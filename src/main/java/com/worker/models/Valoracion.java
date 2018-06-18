package com.worker.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
//@Entity
//@Table(name="valoracion")
public class Valoracion {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "val_id")
	private int id;


//	@Column
	private Usuario autor;

//	@Column
	private String comentario;

//	@Column
	private int puntuacion;

//	@Column
	private Date timestamp;

	//@ManyToMany(cascade = { CascadeType.ALL })
	//@JoinTable(name = "usuario", joinColumns = { @JoinColumn(name = "usu_id") }, inverseJoinColumns = {
	//@JoinColumn(name = "val_id") })


	public Valoracion(Usuario autor, String comentario, int puntuacion) {
		this.autor = autor;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.timestamp = new Date();
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
