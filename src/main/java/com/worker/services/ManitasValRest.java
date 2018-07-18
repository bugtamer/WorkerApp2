package com.worker.services;

import java.util.List;
import java.util.Map;

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
import com.worker.persistence.dao.manitas.ValoracionDAO;

@Path("/proval") // FIXME mover los metodos a la clase con @Path("/Profesional")
public class ManitasValRest {
	
	@GET
	@Path("/{id}/valoracion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getValoracionManitas(
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
		List<Map<String, Object>> valoraciones;
		try {
			valoraciones = ValoracionDAO.getInstance().readRecibidas(manitasId);
			if (valoraciones.size() == 0) {
				response = Response.status(404).entity(valoraciones).build();
			} else {
				response = Response.status(202).entity(valoraciones).build();
			}
		} catch (Exception e) {
			response = Response.status(500).entity("Error en el servidor").build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@GET
	@Path("/{id}/valoracion/media")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getValoracionMediaManitas(
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
		int avg = -1;
		try {
			avg = ValoracionDAO.getInstance().avg(manitasId);
		} catch (Exception e) {
			response = Response.status(500).entity("Error en el servidor").build();
			e.printStackTrace();
		}
		if (avg == -1) {
			response = Response.status(404).entity("Profesional no encontrado").build();
		} else {
			response = Response.status(202).entity(avg).build();
		}
		return response;
	}
	
	
	
	@DELETE
	@Path("/{id}/valoracion")
	public Response deleteValoracionManitas(
			@PathParam("id") int valId,
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
			boolean isDeleted = ValoracionDAO.getInstance().delete(valId);
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
	@Path("/{id}/valoracion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addValoracionManitas(
			@HeaderParam("comentario") String comentario,
			@HeaderParam("puntuacion") int puntuacion,
			@HeaderParam("autor_id") int autor_usu_id,
			@PathParam("id") int receptor_fk_usu,
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
			int newId = ValoracionDAO.getInstance().create(comentario, puntuacion, autor_usu_id, receptor_fk_usu);
			response = Response.status(202).entity(newId).build();
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@PUT
	@Path("/{id}/valoracion")
	public Response updateValoracionManitas(
			@HeaderParam("val_id") int val_id,
			@HeaderParam("comentario") String comentario,
			@HeaderParam("puntuacion") int puntuacion,
			@PathParam("id") int receptor_fk_usu,
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
			Object receptorIdObj = ValoracionDAO.getInstance().read(val_id).get(ValoracionDAO.RECEPTOR_ID);
			int receptorIdDB = (int) (receptorIdObj == null ? DAO.NO_ID : receptorIdObj);
			if (receptor_fk_usu != receptorIdDB) {
				response = Response.status(404).build();
			} else {
				int rows = ValoracionDAO.getInstance().update(val_id, comentario, puntuacion);
				if (rows > 0) {
					response = Response.status(202).build();
				} else {
					response = Response.status(404).build();
				}
			}
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
}
