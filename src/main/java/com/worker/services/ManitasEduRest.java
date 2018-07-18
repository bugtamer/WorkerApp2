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
import com.worker.persistence.dao.manitas.EducacionDAO;

@Path("/proedu") // FIXME mover los metodos a la clase con @Path("/Profesional")
public class ManitasEduRest {
	
	@GET
	@Path("/{id}/educacion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEducacionManitas(
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
		List<String> educacion = new ArrayList<>();
		try {
			educacion = EducacionDAO.getInstance().read(manitasId);
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		if (educacion.size() == 0) {
			response = Response.status(404).entity(educacion).build();
		} else {
			response = Response.status(202).entity(educacion).build();
		}
		return response;
	}
	
	
	
	@DELETE
	@Path("/{id}/educacion")
	public Response deleteEducacionManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("educacion") String educacion,
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
			boolean isDeleted = EducacionDAO.getInstance().delete(manitasId, educacion);
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
	@Path("/{id}/educacion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEducacionManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("educacion") String educacion,
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
			int newId = EducacionDAO.getInstance().create(manitasId, educacion);
			response = Response.status(202).entity(newId).build();
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@PUT
	@Path("/{id}/educacion")
	public Response updateEducacionManitas(
			@PathParam("id") int manitasId,
			@HeaderParam("old_educacion") String oldEducacion,
			@HeaderParam("new_educacion") String newEducacion,
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
			int rows = EducacionDAO.getInstance().update(manitasId, oldEducacion, newEducacion);
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
