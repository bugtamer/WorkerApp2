package com.worker.services;

import java.util.List;
import java.util.Map;

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
import javax.ws.rs.core.Response.Status;

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
			@QueryParam("limit") int limit,
			@HeaderParam("token") String token) {
		
		// �AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		try {
//			List<Mensaje> conversacion = MensajeEM.getInstance().getConversacionEntre(usuId, manId);
			List<Map<String, Object>> conversacion = MensajeDAO.getInstance().getConversacion(usuId, manId, from, limit);
			if (conversacion.size() > 0) {
				response = Response.status(202).entity(conversacion).build();
			} else {
				response = Response.status(404).entity("No existe la conversación solicitada").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}


	@DELETE
	@Path("/{mensajeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMensaje(
			@PathParam(value = "mensajeId") int id,
			@HeaderParam("token") String token) {
		
		// �AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
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
			@HeaderParam(value = "texto") String texto,
			@HeaderParam("token") String token) {
		
		// �AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
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
