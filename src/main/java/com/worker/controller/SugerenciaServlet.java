package com.worker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.db.DDBB;
import com.worker.models.Manitas;

public class SugerenciaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String terminoDeBusqueda = request.getParameter("terminoDeBusqueda");
		DDBB db = DDBB.getInstance();
		List<Manitas> manitasSugeridos = db.getResultadosBusqueda(terminoDeBusqueda);
		StringBuilder sugerencias = new StringBuilder();
		sugerencias.append("[");
		Set<String> profesiones = new HashSet<>();
		if (sugerencias != null) {
			for (int i = 0;   i < manitasSugeridos.size();   i++) {
				profesiones.add(manitasSugeridos.get(i).getProfesion());
			}
			Iterator<String> iterProfesionales = profesiones.iterator();
			while (iterProfesionales.hasNext()) {
				sugerencias.append('"');
				sugerencias.append(iterProfesionales.next());
				sugerencias.append('"');
				if (iterProfesionales.hasNext()) {
					sugerencias.append(",");
				}
			}
		}
		sugerencias.append("]");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(sugerencias.toString());
		out.flush();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
