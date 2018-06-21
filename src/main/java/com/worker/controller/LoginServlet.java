package com.worker.controller;


import com.worker.db.DDBB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.worker.models.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session=request.getSession();
		if(session.getAttribute("email")!=null) {
			response.sendRedirect("login_confirm");
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		System.out.println(email+":"+pass+":");
		// nombre del input
//		System.out.println("value=" + request.getParameter("values"));

		Usuario unUsuario = DDBB.getInstance().getUsuarios(email, pass);
		
//		System.out.println(email+":"+pass+":"+unUsuario);
		
		
		response.setContentType("application/json");
		
		if(unUsuario!=null) {
			HttpSession session =request.getSession();
			session.setAttribute("usuario", unUsuario);
			response.sendRedirect("buscar");
			
		}else {
			request.setAttribute("error", "Los datos son incorrectos!!!");
			response.getWriter().append("{\"result\":false}").flush();
		}
		
		
	}

}
