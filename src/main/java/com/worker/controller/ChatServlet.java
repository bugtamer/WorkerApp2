package com.worker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.db.DDBB;
import com.worker.models.Manitas;
import com.worker.models.Usuario;
import com.worker.util.SessionHelper;

// https://www.adictosaltrabajo.com/tutoriales/web-sockets-java-tomcat/

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Usuario usuario = SessionHelper.getUsuarioIdentificado(request);
		if (usuario == null) {
			response.sendRedirect("login");
		} else {
			Manitas manitas = getManitas(request);
			request.setAttribute("usuarioId", usuario.getId());
			request.setAttribute("manitasId", manitas.getId());
			request.getRequestDispatcher("chat.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	
	
	// DETALLES DE IMPLEMENTACIÓN DE BAJO NIVEL
	
	private Manitas getManitas(HttpServletRequest request) throws IOException {
		// FIX codigo duplicado respecto a FichaServlet.java
		
		final int MANITAS_NO_ENCONTRADO = -1;
		String idSolicitado = request.getParameter("manitasId");
		int id;
		try {
			id = Integer.parseInt(idSolicitado);
		} catch (Exception e) {
			id = MANITAS_NO_ENCONTRADO;
		}
		return DDBB.getInstance().getManitas(id);
	}

}
