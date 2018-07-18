package com.worker.services;

import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.worker.persistence.dao.manitas.ProfesionDAO;

@Path("/propro") // FIXME mover los metodos a la clase con @Path("/Profesional")
public class ManitasProRest {
	
	@GET
	@Path("/{id}/profesion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfesionManitas(@PathParam("id") int manitasId) {
		Response response = null;
		String profesion = null;
		try {
			profesion = ProfesionDAO.getInstance().read(manitasId);
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		if (profesion == null) {
			response = Response.status(404).entity("Profesional no encontrado").build();
		} else {
			response = Response.status(202).entity(profesion).build();
		}
		return response;
	}
	
	
	
	@GET
	@Path("/profesion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfesionesManitasTerminoBusqueda(@QueryParam("s") String terminoBusqueda) {
		
		Response response = null;
		List<Map<String, Object>> coincidenciasEncontradas = new ArrayList<>();
		try {
			coincidenciasEncontradas = ProfesionDAO.getInstance().read(terminoBusqueda);
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		if (coincidenciasEncontradas.size() == 0) {
			response = Response.status(404).entity("No se ha encontrado nada con esa búsqueda").build();
		} else {
			response = Response.status(202).entity(coincidenciasEncontradas).build();
		}
		return response;
	}
	
	
	
	@DELETE
	@Path("/{id}/profesion")
	public Response deleteProfesionManitas(@PathParam("id") int manitasId) {
		Response response = null;
		try {
			boolean isDeleted = ProfesionDAO.getInstance().delete(manitasId);
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
	@Path("/{id}/profesion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfesionManitas(
			@PathParam("id") int usuarioBaseId,
			@HeaderParam("profesion") String profesion) {
		
		Response response = null;
		try {
			int newId = ProfesionDAO.getInstance().create(usuarioBaseId, profesion);
			response = Response.status(202).entity(newId).build();
		} catch (Exception e) {
			response = Response.status(500).entity(e.getMessage()).build();
			e.printStackTrace();
		}
		return response;
	}
	
	
	
	@PUT
	@Path("/{id}/profesion")
	public Response updateProfesionManitas(
			@PathParam("id") int usuarioBaseId,
			@HeaderParam("profesion") String profesion) {
		
		Response response = null;
		try {
			int rows = ProfesionDAO.getInstance().update(usuarioBaseId, profesion);
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
