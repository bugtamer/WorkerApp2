package com.worker.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.worker.db.DDBB;
import com.worker.models.Usuario;
import com.worker.util.Notificacion;
import com.worker.util.LoginHelper;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		System.out.println("LoginServlet - doGet");
		
		boolean noHayUsuarioEnSesion = (LoginHelper.getUsuarioEnSesion(request) == null);
		if (noHayUsuarioEnSesion) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			String urlAnteriorLogin = LoginHelper.getAttributeURL(request, response);
			response.sendRedirect( urlAnteriorLogin );
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("LoginServlet - doPost");
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		System.out.println(email+":"+pass+":");

		Usuario usuarioEncontrado = DDBB.getInstance().getUsuarios(email, pass);
		System.out.println("usuarioEncontrado = " + usuarioEncontrado);
		
		boolean noExisteEsaCredencial = (usuarioEncontrado == null);
		if (noExisteEsaCredencial) {
			request.setAttribute("error", "ATENCIÓN: email y/o password incorrectos.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuarioEncontrado);
			String pageTitle = "Login";
			String icono = "how_to_reg";
			String mensaje = String.format("¡Hola <b>%s</b>!", usuarioEncontrado.getNombre());
			String urlDestino = LoginHelper.getAttributeURL(request, response);
			urlDestino = (urlDestino == null) ? "buscar" : urlDestino;
			urlDestino = (urlDestino.contains("login")) ? "buscar" : urlDestino;
			Notificacion.configuracion(request, pageTitle, icono, mensaje, urlDestino);
			request.getRequestDispatcher( Notificacion.JSP ).forward(request, response);
		}
		
	}

}
