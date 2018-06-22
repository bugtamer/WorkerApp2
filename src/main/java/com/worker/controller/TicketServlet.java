package com.worker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Usuario;
import com.worker.util.LoginHelper;
import com.worker.util.SessionHelper;

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Usuario usuario = SessionHelper.getUsuarioIdentificado(request);
		if (usuario == null) {
			LoginHelper.setAttributeURL(request, response);
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("ticket.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
