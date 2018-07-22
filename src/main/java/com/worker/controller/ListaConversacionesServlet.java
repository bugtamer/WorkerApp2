package com.worker.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Usuario;
import com.worker.persistence.dao.MensajeDAO;
import com.worker.util.LoginHelper;
import com.worker.util.Notificacion;
import com.worker.util.SessionHelper;

@WebServlet("/conversaciones")
public class ListaConversacionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Usuario usuario = SessionHelper.getUsuarioIdentificado(request);
		if (usuario == null) {
			LoginHelper.setAttributeURL(request, response);
			Notificacion.configuracionZonaPrivada(request);
			request.getRequestDispatcher( Notificacion.JSP ).forward(request, response);
		} else {
			List<Map<String, Object>> listaConversaciones = new ArrayList<>();
			try {
				listaConversaciones = MensajeDAO.getInstance().getListaConversaciones(usuario.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("listaConversaciones", listaConversaciones);
			request.getRequestDispatcher("chat-salas.jsp").forward(request, response);
		}
	}

}
