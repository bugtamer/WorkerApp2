package com.worker.controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.util.Notificacion;
import com.worker.models.Usuario;
import com.worker.util.LoginHelper;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("LogoutServlet - doGet");
		
		Usuario usuarioLogueado = LoginHelper.getUsuarioEnSesion(request);
		if (usuarioLogueado == null) {
			response.sendRedirect("buscar");
		} else {
			request.getSession().invalidate();
			String pageTitle = "Logout";
			String icono = "lock";
			String mensaje = String.format("¡Adios <b>%s</b>!", usuarioLogueado.getNombre());
			String urlDestino = "buscar";
			Notificacion.configuracion(request, pageTitle, icono, mensaje, urlDestino);
			request.getRequestDispatcher( Notificacion.JSP ).forward(request, response);
		}
	}

}
