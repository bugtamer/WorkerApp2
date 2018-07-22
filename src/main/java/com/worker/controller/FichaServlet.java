package com.worker.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Manitas;
import com.worker.models.Ubicacion;
import com.worker.models.Usuario;
import com.worker.persistence.ManitasEM;
import com.worker.util.LoginHelper;
import com.worker.util.Notificacion;
import com.worker.util.SessionHelper;

@WebServlet("/ficha")
public class FichaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = SessionHelper.getUsuarioIdentificado(request);
		if (usuario == null) {
			LoginHelper.setAttributeURL(request, response);
			Notificacion.configuracionZonaPrivada(request);
			request.getRequestDispatcher( Notificacion.JSP ).forward(request, response);
		} else {
			Manitas manitas   = getManitas(request, response);
			String  distancia = getUbicacionFormateada(usuario.getUbicacion(), manitas.getUbicacion());
			request.setAttribute("manitasUbicacion", manitas.getUbicacion());
			request.setAttribute("usuarioUbicacion", usuario.getUbicacion());
			request.setAttribute("manitas",   manitas);
			request.setAttribute("distancia", distancia);
			request.getRequestDispatcher("ficha.jsp").forward(request, response);
		}
	}



	// DETALLES DE IMPLEMENTACION DE BAJO NIVEL

	private String getUbicacionFormateada(Ubicacion ubicacionUsuario, Ubicacion manitas) {
		double distancia = ubicacionUsuario.getDistanciaKilometrica(manitas);
		return String.format("%.1f", distancia);
	}


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
		return ManitasEM.getInstance().getManitasById("" + id);
	}

}