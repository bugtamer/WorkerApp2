package com.worker.services;

import java.util.List;

import javax.ws.rs.DELETE;
//import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
//import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.models.Usuario;
import com.worker.persistence.UsuarioEM;
import com.worker.persistence.dao.DAO;





@Path("/usuarios")
public class UsuariosApi {

	@Path("/listausuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getList(@HeaderParam("token") String token) {
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
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
	
	
	// recuperar un usuario por su id
	
	@Path("{idusuario}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getUsuario(
			@PathParam(value = "idusuario") int id,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Usuario unUsuario = null;

		try {
			unUsuario = UsuarioEM.getInstance().getUsuarioById(id);
//			logger.log(Level.INFO, "user:" + unUsuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (unUsuario != null)
			return Response.status(202).entity(unUsuario).build();
		else
			return Response.status(404).entity("El objeto no existe").build();
	}

	
	
	
	// elimino un usuario

		@Path("/{idusuario}")
		@Produces(MediaType.APPLICATION_JSON)
		@DELETE
		public Response deleteUsuario(
				@PathParam(value = "idusuario") int id,
				@HeaderParam("token") String token) {
			
			// ¿AUTORIZADO?
			AuthService auth = new AuthService();
			int userTokenId = auth.getUsuarioIdFromToken(token);
			if(userTokenId == DAO.NO_ID) {
				return Response.status(Status.FORBIDDEN).build();
			}
			// PROCEDE:
			boolean unUsuario = false;
			try {
				unUsuario = UsuarioEM.getInstance().deleteUsuario(id);
			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("El objeto es 500").build();
			}

			if (unUsuario != true)
				return Response.status(202).entity(unUsuario).build();
			else
				return Response.status(404).entity("El objeto es 404").build();

		}
		
		
		//actualizo usuario
		
//		@Path("/{idusuario}")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		@PUT
//		public Response actualizarUsuario(@PathParam(value = "idusuario") int id, Usuario usuarioActualizar) {
//	
//				UsuarioEM usuario = null;
//				usuario = UsuarioEM.getInstance();
//				
//	
//				if (usuarioActualizar.getId() == id) {
//					try {
//						System.out.println("booleano:  "+usuario);
//						
//					} catch (Exception e) {
//					e.printStackTrace();
//					
//				}
//				return Response.status(202).entity(usuario).build();
//			} else {
//				return Response.status(Status.FORBIDDEN.getStatusCode()).entity("Error de 404").build();
//				}
//			
//	
//		}


}
