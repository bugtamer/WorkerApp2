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
import com.worker.persistence.UsuarioEM;

@WebServlet("/registro_manitas")
public class RegistroProfesionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("registro_manitas.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 ManitasEM.getInstance();
		 String nombre =request.getParameter("name");
		 String apellido =request.getParameter("lastname");
		 String email =request.getParameter("email");
		 String pass =request.getParameter("pass");
		 String repass =request.getParameter("repass");
		 String profesion =request.getParameter("profetion");
		 //String educacion =request.getParameter("education");
		 //String experiencia =request.getParameter("experience");
		 //usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getPassword());
		 Usuario user = new Usuario(nombre, apellido, email, pass);
		 Ubicacion ubi= new Ubicacion(23.4,34.9);
		 Manitas mani = new Manitas(user, profesion);
		 user.setUbicacion(ubi);
		 //Manitas(Usuario usuario, String profesion) 
		
		 boolean agregado = ManitasEM.getInstance().save(mani);
		 System.out.println(agregado);
		 //boolean agregado = DDBB.getInstance().AddUsuario(user);
		 if(agregado==true)
		 {
			 if(pass==repass) {
				 response.sendRedirect("/login");
			 }else {
				 request.setAttribute("error", "Las contraseñas no coinciden");
			 }
		 }
		 else {request.setAttribute("error", "E-mail ya registrado");}
		 
		doGet(request, response);
	}
	

}
