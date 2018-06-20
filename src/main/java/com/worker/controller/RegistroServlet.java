package com.worker.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.worker.models.Ubicacion;
import com.worker.models.Usuario;
import com.worker.persistence.UsuarioEM;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("registro.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("name");
		String apellido = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String repass = request.getParameter("repass");
	
		Usuario user = new Usuario(nombre, apellido, email, pass);
		Ubicacion ubi = new Ubicacion(25.5, 27.4);
		user.setUbicacion(ubi);
		boolean agregado = UsuarioEM.getInstance().save(user);
		System.out.println("agregado:"+agregado);
		// boolean agregado = DDBB.getInstance().AddUsuario(user);
		if (agregado = false) {
			request.setAttribute("error", "E-mail ya registrado");
		} else {
			response.sendRedirect("login");
		}

	}

}
