package com.worker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.db.DDBB;
import com.worker.models.Manitas;

@WebServlet("/ficha")
public class FichaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO verificar si esta autorizado a zonas privadas
		
		Manitas manitas = getManitas(request, response);
		System.out.println("ficha=" + manitas);
		request.setAttribute("manitas", manitas);
		request.getRequestDispatcher("ficha.jsp").forward(request, response);
	}
	
	
	// DETALLES DE IMPLEMENTACION DE BAJO NIVEL
	
	
	private Manitas getManitas(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		final int MANITAS_NO_ENCONTRADO = -1; 
		String idSolicitado = request.getParameter("id");
		int id;
		try {
			id = Integer.parseInt(idSolicitado);
		} catch (Exception e) {
			id = MANITAS_NO_ENCONTRADO;
		}
		return DDBB.getInstance().getManitas(id);
	}

}
