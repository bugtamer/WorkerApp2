package com.worker.util;

import javax.servlet.http.HttpServletRequest;

import com.worker.models.Usuario;

public class SessionHelper {
	
	
	public static Usuario getUsuarioIdentificado(HttpServletRequest request) {
		return (Usuario) request.getSession().getAttribute("usuario");
	}
	
	
	private SessionHelper() { }

}
