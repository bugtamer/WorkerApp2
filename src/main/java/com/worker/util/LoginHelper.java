package com.worker.util;

import javax.servlet.http.HttpServletRequest;

import com.worker.models.Usuario;

/**
 * Permite continuar en la página previa a la página de login
 * por haber accedido a una página privada  
 */
public class LoginHelper {
	
	private LoginHelper() { }
	
	
	public static final String URL_DESTINO = "urlDestino";
	
	
	public static Usuario getUsuarioEnSesion(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		System.out.println("LoginHelper - getUsuarioEnSesion() = " + usuario);
		return usuario;
	}
	
	
	
	public static void setAttributeURL(HttpServletRequest request) {
		String queryParams = request.getQueryString();
		String url = request.getRequestURL().append('?').append(queryParams).toString();
		request.setAttribute(URL_DESTINO, url);
		System.out.println("LoginHelper - setAttributeURL() = " + url);
	}
	
	
	
	public static void setAttributeCurrentUrl(HttpServletRequest request, String url) {
		request.setAttribute(URL_DESTINO, url);
		System.out.println("LoginHelper - setAttributeURL() = " + url);
	}
	
	
	
	public static String getAttributeURL(HttpServletRequest request) {
		String url = (String) request.getAttribute(URL_DESTINO);
		url = (url == null) ? "buscar" : url;
		System.out.println("LoginHelper - getAttributeURL() = " + url);
		return url;
	}
	
}