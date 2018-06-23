package com.worker.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper para realizar notificaciones a pantalla completa y
 * redirección después de un timeout a otra página.
 */
public class Notificacion {
	
	private Notificacion() { }
	
	
	/**
	 * Plantilla o vista que maqueta el mensaje de notificación
	 */
	public static final String JSP = "/WEB-INF/notificacion.jsp";
	public static final String JAVASCRIPT = "./js/redireccionPostNotificacion.js";
	
	
	/**
	 * Permite personalizar un mensaje de notificación.
	 * 
	 * @param request donde se guardarán los parámetros para configurar la página de notificación.
	 * @param titulo es el nombre de la página de notificación en el elemento 'title'
	 * @param materialIcon icono que acompaña al mensaje (<a href="https://material.io/tools/icons/?style=baseline">material icon list</a>)
	 * @param mensaje de notificación
	 * @param urlDestino para después de la redirección automática realizada por 'redireccionPostComfirmacion.js'
	 */
	public static void configuracion(HttpServletRequest request,
			String titulo, String materialIcon, String mensaje, String urlDestino) {
		
		request.setAttribute("titulo", titulo);
		request.setAttribute("materialIcon", materialIcon);
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("urlDestino", urlDestino);
		request.setAttribute("javascript", JAVASCRIPT);
	}
	
	
	
	/**
	 * Configura una notificación para advertir que para
	 * acceder a una zona privada se requiere hacer login.
	 * Asigna 'login' como URL de redirección.
	 * 
	 * @param request donde se guardará la configuración de la notificación.
	 */
	public static void configuracionZonaPrivada(HttpServletRequest request) {
		String titulo = "Login requerido";
		String materialIcon = "block";
		String mensaje = "Exclusivo<br><b>usuarios registrados</b>";
		String urlDestino = "login";
		configuracion(request, titulo, materialIcon, mensaje, urlDestino);
	}

}
