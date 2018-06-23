package com.worker.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.db.DDBB;
import com.worker.models.Manitas;
import com.worker.models.Ubicacion;


@WebServlet("/buscar")
public class BuscarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("BuscarServlet - doGet()");

		if (request.getAttribute("sonResultados") == null) {
			request.setAttribute("sonResultados", false);
		}
		Ubicacion ubicacionUsuario = new Ubicacion(41.40784461738553, 2.1700572967529297);
		request.setAttribute("ubicacionUsuario", ubicacionUsuario);
		request.getRequestDispatcher("buscar.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("BuscarServlet - doPost()");
		
		String busqueda= request.getParameter("terminosDeBusqueda");
		DDBB db = DDBB.getInstance();
		List<Manitas> resultado = db.getResultadosBusqueda(busqueda);
		request.setAttribute("sonResultados", true);
		request.setAttribute("listaManitas", resultado);
		request.setAttribute("terminoBusqueda", busqueda);
		
		System.out.println("terminosDeBusqueda = " + busqueda);
		
		doGet(request, response);
	}

}
