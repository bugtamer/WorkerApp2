package com.worker.services;

import java.util.List;

import javax.ws.rs.Consumes;
//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.worker.models.Usuario;
import com.worker.persistence.UsuarioEM;

import javax.ws.rs.core.Response.Status;

@Path("/usuarios")
public class UsuariosApi {

	@Path("/listausuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getList() {

		List<Usuario> listaUsuarios = UsuarioEM.getInstance().getList();
		Response response = null;
		String errorMsg = null;

		if (listaUsuarios == null || listaUsuarios.size() == 0) {
			errorMsg = "{\"error\":\"No hay resultados \"}";
			response = Response.status(Status.NOT_FOUND).entity(errorMsg).build();

		} else {
			response = Response.status(202).entity(listaUsuarios).build();

		}
		return response;
	}
	
//	
//	@Path("")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addUsuario(Usuario newUsuario){
//		try {
//			return Response.status(202).entity(UsuarioEM.getInstance().add(newUsuario)).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Response.status(500).entity("Ha habido un error al guardar el nuevo usuario").build();
//		}
//	}
}
