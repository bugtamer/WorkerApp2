package com.worker.models;

import java.util.Date;

/**
 *
 * @author bugtamer
 *
 */
public class Mensaje {

	// ATRIBUTOS


	private Usuario emisor;
	private Usuario receptor;
	private String  texto;
	private String  urlImagen;
	private Date    timestamp;


	// INSTANCIACIÓN


	public Mensaje(Usuario emisor, Usuario receptor) {
		this.emisor = emisor;
		this.receptor = receptor;
		this.timestamp = new Date(); // ahora mismo
	}


	// GETTERS


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
