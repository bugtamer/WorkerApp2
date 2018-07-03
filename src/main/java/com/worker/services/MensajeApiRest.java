package com.worker.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.models.StatusMessage;
import com.worker.persistence.MensajeEM;

@Path("/mensajes")
public class MensajeApiRest extends JSONService {


	@DELETE
	@Path("/{mensajeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeMensaje(@PathParam(value = "mensajeId") int id) {
		Response response = null;

		boolean isDeleted = MensajeEM.getInstance().removeMensaje(id);
		if (isDeleted) {
			response = Response.status(202).entity("\"msg\":\"Se borró correctamente\"").build();
		} else {
			response = Response.status(500).entity("\"msg\":\"Error: no se pudo borrar\"").build();
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
