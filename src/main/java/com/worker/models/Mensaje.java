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

/**
 *
 * @author bugtamer
 *
 */
@Entity
@Table(name="mensaje")
public class Mensaje {

	// ATRIBUTOS


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "men_id")
	private int id;
	
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn//(name = "fk_usu_emisor")
	private Usuario emisor;
	
	@ManyToOne(optional=false)
	@PrimaryKeyJoinColumn//(name = "fk_usu_receptor")
	private Usuario receptor;
	
	@Column(nullable = true)
	private String  texto;
	
	@Column(nullable = true)
	private String  urlImagen;
	
	@Column(nullable = false)
	private Date    timestamp;


	// INSTANCIACIÓN


	public Mensaje() { }
	
	
	public Mensaje(Usuario emisor, Usuario receptor) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.timestamp = new Date(); // ahora mismo
	}


	// GETTERS


	public int getId() {
		return id;
	}


	public Usuario getEmisor() {
		return emisor;
	}


	public Usuario getReceptor() {
		return receptor;
	}


	public String getTexto() {
		return texto;
	}


	/**
	 * obtine la URL de la imagen
	 *
	 * @return URL de la imagen o null si no hay imagen
	 */
	public String getImagen() {
		return urlImagen;
	}


	public Date getTimestamp() {
		return timestamp;
	}


	// SETTERS


	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}


	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	/**
	 * Establece la URL de la imagen
	 *
	 * @param urlImagen
	 */
	public void setImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	@Override
	public String toString() {
		String time = (timestamp == null) ? "" : timestamp.toString();
		return String.format("{t=%s, i=%s, t=%s e=%s, r=%s}",
				texto, urlImagen, time, emisor, receptor);
	}
}
