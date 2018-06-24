package com.worker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Usuario;
import com.worker.util.Notificacion;
import com.worker.util.forms.RegistroDataForm;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("RegistroServlet - doGet()");
		
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("RegistroServlet - doPost()");
		
		String urlRedireccion = null;
		RegistroDataForm formRequest = new RegistroDataForm(request);
		Usuario nuevoUsuario = formRequest.parseToUsuario();
		try {
			formRequest.persist();
			request.getSession().setAttribute("usuario", nuevoUsuario);
			configuracionNotificacion(request, nuevoUsuario);
			urlRedireccion = Notificacion.JSP;
		} catch(Exception e) {
			urlRedireccion = "registro.jsp";
			request.setAttribute("revisaUsuario", nuevoUsuario);
			request.setAttribute("revisaRepass", formRequest.getRepassword());
			formRequest.setErrors();
			e.printStackTrace();
		}
		request.getRequestDispatcher( urlRedireccion ).forward(request, response);
	}
	
	
	
	// DETALLES DE IMPLEMENTACION DE MAS BAJO NIVEL
	
	private void configuracionNotificacion(HttpServletRequest request, Usuario nuevoUsuario) {
		String titulo = "Registro";
		String materialIcon = "how_to_reg";
		String mensaje = String.format("¡Bienvenido <b>%s</b>! a", nuevoUsuario.getNombre());
		String urlDestino = "buscar";
		Notificacion.configuracion(request, titulo, materialIcon, mensaje, urlDestino);
	}

}
