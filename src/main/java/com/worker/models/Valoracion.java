package com.worker.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="valoracion")
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "val_id")
	private int id;
	
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn//(name = "fk_usu")
	private Usuario autor;
	
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn//(name = "fk_man")
	private Manitas receptor;

	@Column(nullable = false)
	private String comentario;

	@Column(nullable = false)
	private int puntuacion;

	@Column(nullable = false)
	private Date timestamp;
	


	public Valoracion() { }
	
	
	public Valoracion(Usuario autor, Manitas receptor, String comentario, int puntuacion) {
		this.autor = autor;
		this.receptor = receptor;
		this.comentario = comentario;
		this.puntuacion = puntuacion;
		this.timestamp = new Date();
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}


	public Manitas getReceptor() {
		return receptor;
	}
	public void setReceptor(Manitas receptor) {
		this.receptor = receptor;
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
