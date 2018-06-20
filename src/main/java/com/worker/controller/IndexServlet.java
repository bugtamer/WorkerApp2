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
import com.worker.models.Usuario;
import com.worker.persistence.ManitasEM;
import com.worker.persistence.MensajeEM;


@WebServlet("/buscar")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;





	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");

		MensajeEM.getInstance();
		//ManitasEM.getInstance().getListaByProfession("d");
		
		if (request.getAttribute("sonResultados") == null) {
			request.setAttribute("sonResultados", false);
		}
		

//		Ubicacion ubi = new Ubicacion(41.7, 50.5);
//		Usuario usernew = new Usuario("Pancrasio", "Gomez", "ag@.es", "123");
//		usernew.setUbicacion(ubi);
//		Manitas manitas = new Manitas(usernew, "plomero");
//		ManitasEM.getInstance().save(manitas);
	
		
		request.getRequestDispatcher("index_searchresult.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		String busqueda= request.getParameter("terminosDeBusqueda");
		System.out.println("busqueda=" + busqueda);
		DDBB db = DDBB.getInstance();
		List<Manitas> resultado = db.getResultadosBusqueda(busqueda);
		request.setAttribute("sonResultados", true);
		request.setAttribute("listaManitas", resultado);
		request.setAttribute("terminoBusqueda", busqueda);
		doGet(request, response);


//		String json = String.format("{\"busqueda\": \"%s\",", busqueda);
//		json += "\"resultados\": [\n";
//		for(int i=0;   i < resultado.size();   i++) {
//			json += "{";
//			json += String.format("\"nombre\": \"%s\",\n\t", resultado.get(i).getNombre());
//			json += String.format("\"actividad\": \"%s\",\n\t", resultado.get(i).getProfesion());
//			json += String.format("\"distancia\": \"%d\",\n\t", 3);
//			json += String.format("\"valoracion\": \"%s\",\n\t", resultado.get(i).getMediaValoraciones());
//			json += String.format("\"detalle\": \"./perfil-manitas?id=%d\",\n\t", resultado.get(i).getId());
//			json += String.format("\"imagen\": \"%s\"\n\t", resultado.get(i).getAvatar());
//			json += (i+1 == resultado.size()) ? "}]" : "},";
//		}
//		json += "}";
//		System.out.println("json= " + json);
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		out.print(json);
//		out.flush();
	}

}
