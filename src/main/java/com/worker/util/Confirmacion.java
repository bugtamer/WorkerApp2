package com.worker.util;

import javax.servlet.http.HttpServletRequest;

public class Confirmacion {
	
	private Confirmacion() { }
	
	
	/**
	 * Plantilla o vista que maqueta el mensaje de confirmación
	 */
	public static final String JSP = "confirmacion.jsp";
	
	
	/**
	 * Permite personalizar un mensaje de confirmación.
	 * 
	 * @param request donde se guardarán los parámetros para configurar la página de confirmación.
	 * @param titulo es el nombre de la página de confirmación en el elemento 'title'
	 * @param icono que acompaña al mensaje
	 * @param mensaje de confirmación
	 * @param urlDestino para después de la redirección automática realizada por 'redireccionPostComfirmacion.js'
	 */
	public static void configuracion(HttpServletRequest request,
			String titulo, String icono, String mensaje, String urlDestino) {
		
		request.setAttribute("titulo", titulo);
		request.setAttribute("icono", icono);
		request.setAttribute("xTuvoExito", mensaje);
		request.setAttribute("urlDestino", urlDestino);
	}

}
