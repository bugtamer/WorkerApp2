package com.worker.controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.util.Confirmacion;
import com.worker.util.LoginHelper;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("LogoutServlet - doGet");
		
		if (LoginHelper.getUsuarioEnSesion(request) == null) {
			response.sendRedirect("buscar");
		} else {
			request.getSession().invalidate();
			String pageTitle = "Logout";
			String icono = "verified_user";
			String mensaje = "Se ha desconectado de forma segura";
			String urlDestino = "buscar";
			Confirmacion.configuracion(request, pageTitle, icono, mensaje, urlDestino);
			request.getRequestDispatcher( Confirmacion.JSP ).forward(request, response);
		}
	}

}
