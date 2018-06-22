package com.worker.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		request.setAttribute(URL_DESTINO, getURL(request));
	}
	
	
	
	public static void setAttributeURL(HttpServletRequest request, HttpServletResponse response) {
		String url = getURL(request);
		HttpSession sesion = request.getSession();
		if (sesion != null) {
			sesion.setAttribute(URL_DESTINO, url);
		}
		System.out.println("LoginHelper - setAttributeURL(request, response) = " + url);
	}
	
	
	
	public static String getAttributeURL(HttpServletRequest request) {
		String url = (String) request.getAttribute(URL_DESTINO);
		url = (url == null) ? "buscar" : url;
		System.out.println("LoginHelper - getAttributeURL() = " + url);
		return url;
	}
	
	
	
	public static String getAttributeURL(HttpServletRequest request, HttpServletResponse response) {
		String url = "buscar";
		HttpSession sesion = request.getSession();
		if (sesion != null) {
			url = (String) sesion.getAttribute(URL_DESTINO);
		}
		System.out.println("LoginHelper - getAttributeURL(request, response) = " + url);
		return url;
	}

	
	
	// DETALLES DE IMPLEMENTACION MAS BAJO NIVEL
	
	private static String getURL(HttpServletRequest request) {
		String queryParams = request.getQueryString();
		String url = request.getRequestURL().append('?').append(queryParams).toString();
		System.out.println("LoginHelper - setAttributeURL(request) = " + url);
		return url;
	}
	
}