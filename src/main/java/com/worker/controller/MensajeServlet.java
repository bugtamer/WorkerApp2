package com.worker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;
import com.worker.persistence.ManitasEM;
import com.worker.persistence.MensajeEM;
import com.worker.persistence.UsuarioEM;
import com.worker.util.LoginHelper;


@WebServlet("/getMensajes")
public class MensajeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String json = "[]";
		boolean hayUsuarioLogueado = (LoginHelper.getUsuarioEnSesion(request) != null);
		if (hayUsuarioLogueado) {
			String usuarioId = request.getParameter("uid");
			String manitasId = request.getParameter("manitasId");

			List<Mensaje> conversacion = getConversacion(usuarioId, manitasId);
			json = parseJson(usuarioId, conversacion);
		}

		response.setContentType( "application/json" );
		PrintWriter out = response.getWriter();
		out.print( json );
		out.flush();
	}





	// DETALLES DE IMPLEMENTACION DE MAS BAJO NIVEL

	private List<Mensaje> getConversacion(String usuarioId, String manitasId) {
		Usuario usuario = UsuarioEM.getInstance().getUsuarioById(Integer.parseInt(usuarioId));
		Manitas manitas = ManitasEM.getInstance().getManitasById(manitasId);
		return MensajeEM.getInstance().getConversacionEntre(usuario, manitas);
	}



	private String parseJson(String usuarioId, List<Mensaje> conversacion) {
		System.out.println("MensajeSerlet conversacion.size() = " + conversacion.size());
		System.out.println("MensajeSerlet usuarioId = " + usuarioId);
		int uid = Integer.parseInt(usuarioId);

		StringBuilder json = new StringBuilder();
		json.append("[");
		for (int i = 0;   i < conversacion.size();   i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

			Mensaje m = conversacion.get(i);
			String autor = (uid == m.getEmisor().getId()) ? "self" : "other";
			String nombre = m.getEmisor().getNombre();
			String mensaje = m.getTexto(); // puede ser null
			String imagen = m.getImagen(); // puede ser null
			String hora = sdf.format( m.getTimestamp() );

			System.out.println("MensajeSerlet emisorId = " + m.getEmisor().getId());

			json.append("{");
			json.append( keyVaule("autor", autor) ).append(','); // CSS class: self / other
			json.append( keyVaule("nombre", nombre) ).append(',');
			json.append( keyVaule("mensaje", mensaje) ).append(',');
			json.append( keyVaule("imagen", imagen) ).append(',');
			json.append( keyVaule("hora", hora) ); // formato: hh:mm
			json.append("}");

			int proximaPosicion = i + 1;
			if (proximaPosicion < conversacion.size()) {
				json.append(',');
			}
		}
		json.append("]");
		System.out.println("MensajeSerlet JSON = " + json.toString());
		return json.toString();
	}



	private StringBuilder keyVaule(String key, String value) {
		StringBuilder entry = new StringBuilder();
		entry.append('"').append(key).append('"');
		entry.append(':');
		if (value == null) {
			entry.append("null");
		} else {
			entry.append('"').append(value).append('"');
		}
		return entry;
	}

}
