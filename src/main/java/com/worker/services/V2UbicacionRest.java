package com.worker.services;

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

import com.worker.models.Ubicacion;
import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.UbicacionDAO;

@Path("/v2/ubicacion")
public class V2UbicacionRest {
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUbicacionManitas(
			@PathParam("id") int ubiId,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		Ubicacion ubicacion = null;
		try {
			ubicacion = UbicacionDAO.getInstance().read(ubiId);
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		if (ubicacion == null) {
			response = Response.status(404).entity("Ubicación no encontrada").build();
		} else {
			response = Response.status(202).entity(ubicacion).build();
		}
		return response;
	}
	
	
	
	@DELETE
	@Path("/{id}")
	public Response deleteUbicacionManitas(
			@PathParam("id") int ubiId,
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
			boolean isDeleted = UbicacionDAO.getInstance().delete(ubiId);
			if (isDeleted) {
				response = Response.status(202).build();
			} else {
				response = Response.status(404).entity("Ubicación no encontrada").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@POST
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUbicacionManitas(
			@HeaderParam("latitud") double latitud,
			@HeaderParam("longitud") double longitud,
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
			int newId = UbicacionDAO.getInstance().create(latitud, longitud);
			response = Response.status(202).entity(newId).build();
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@PUT
	@Path("/{id}")
	public Response updateUbicacionManitas(
			@PathParam("id") int ubiId,
			@HeaderParam("latitud") double latitud,
			@HeaderParam("longitud") double longitud,
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
			int rows = UbicacionDAO.getInstance().update(ubiId, latitud, longitud);
			if (rows > 0) {
				response = Response.status(202).build();
			} else {
				response = Response.status(404).entity("Ubicación no encontrada").build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
}
