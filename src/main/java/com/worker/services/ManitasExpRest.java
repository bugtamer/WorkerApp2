package com.worker.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.manitas.ExperienciaDAO;

@Path("/proexp") // FIXME mover los metodos a la clase con @Path("/Profesional")
public class ManitasExpRest {
	
	@GET
	@Path("/{id}/experiencia")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getExperienciaManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		List<String> experiencia = new ArrayList<>();
		try {
			experiencia = ExperienciaDAO.getInstance().read(manitasId);
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		if (experiencia.size() == 0) {
			response = Response.status(404).entity(experiencia).build();
		} else {
			response = Response.status(202).entity(experiencia).build();
		}
		return response;
	}
	
	
	
	@DELETE
	@Path("/{id}/experiencia")
	public Response deleteExperienciaManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("experiencia") String experiencia,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		try {
			boolean isDeleted = ExperienciaDAO.getInstance().delete(manitasId, experiencia);
			if (isDeleted) {
				response = Response.status(202).build();
			} else {
				response = Response.status(404).build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@POST
	@Path("/{id}/experiencia")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addExperienciaManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("experiencia") String experiencia,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		try {
			ExperienciaDAO.getInstance().create(manitasId, experiencia);
			response = Response.status(202).entity(manitasId).build();
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@PUT
	@Path("/{id}/experiencia")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateExperienciaManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("old_experiencia") String oldExperiencia,
			@HeaderParam("new_experiencia") String newExperiencia,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		try {
			int rows = ExperienciaDAO.getInstance().update(manitasId, oldExperiencia, newExperiencia);
			if (rows > 0) {
				response = Response.status(202).build();
			} else {
				response = Response.status(404).build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
}
