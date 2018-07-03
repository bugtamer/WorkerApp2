package com.worker.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.worker.persistence.MensajeEM;

@Path("/mensajes")
public class MensajeApiRest {


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

}
