package com.worker.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.worker.persistence.MensajeEM;
import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.MensajeDAO;

@Path("/mensajes")
public class MensajeApiRest extends JSONService {


	@GET
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMensajes(
			@QueryParam(value = "usuarioId") int usuId,
			@QueryParam(value = "manitasId") int manId,
			@QueryParam("from") int from,
			@QueryParam("limit") int limit) {
		
		Response response = null;

		//boolean isDeleted = MensajeEM.getInstance().removeMensaje(id);
//		if (isDeleted) {
//			response = Response.status(202).entity("\"msg\":\"Se borró correctamente\"").build();
//		} else {
//			response = Response.status(500).entity("\"msg\":\"Error: no se pudo borrar\"").build();
//		}

		return response;
	}


	@DELETE
	@Path("/{mensajeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMensaje(
			@PathParam(value = "mensajeId") int id) {
		Response response = null;

		boolean isDeleted = MensajeEM.getInstance().removeMensaje(id);
		if (isDeleted) {
			response = Response.status(202).entity("{\"msg\":\"Se borró correctamente\"}").build();
		} else {
			response = Response.status(500).entity("{\"msg\":\"Error: no se pudo borrar\"}").build();
		}

		return response;
	}




	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMensaje(
			@HeaderParam(value = "emisorId") int emisor_usu_id,
			@HeaderParam(value = "receptorId") int receptor_usu_id,
			@HeaderParam(value = "texto") String texto) {
		
		Response response = null;
		int newID = DAO.NO_ID;
		try {
			String urlImagen = null; // TODO upload image messages
			newID = MensajeDAO.getInstance().create(texto, urlImagen, emisor_usu_id, receptor_usu_id);
			response = Response.status(202).entity(String.format("{\"id\":%d}", newID)).build();
		} catch (Exception e) {
			response = Response.status(500).entity("�{\"msg\":\"Error en el servidor\"}").build();
			e.printStackTrace();
		}
		return response;
	}


//	@DELETE
//	@Path("/{mensajeId}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response removeMensaje(@PathParam(value = "mensajeId") int id, @HeaderParam("token") String token) {
//		Response response = null;
//
//
//		System.out.println("usuarioId-1");
//		int usuarioId = this.getUsuarioIdFromToken(token);
//		System.out.println("usuarioId-2=" + usuarioId);
//
//		if (usuarioId <= 0) {
//			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),
//					"Access Denied for this functionality !!!");
//			response = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
//		} else {
//
//
//			boolean isDeleted = MensajeEM.getInstance().removeMensaje(id);
//			if (isDeleted) {
//				response = Response.status(202).entity("\"msg\":\"Se borró correctamente\"").build();
//			} else {
//				response = Response.status(500).entity("\"msg\":\"Error: no se pudo borrar\"").build();
//			}
//
//
//		}
//		return response;
//	}

}
