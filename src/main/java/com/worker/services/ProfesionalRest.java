package com.worker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
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
import javax.ws.rs.core.Response.Status;

import com.worker.models.Manitas;
import com.worker.models.Ubicacion;
import com.worker.models.Valoracion;
import com.worker.persistence.ManitasEM;
import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.UbicacionDAO;
import com.worker.persistence.dao.UsuarioDAO;
import com.worker.persistence.dao.manitas.EducacionDAO;
import com.worker.persistence.dao.manitas.ExperienciaDAO;
import com.worker.persistence.dao.manitas.ProfesionDAO;
import com.worker.persistence.dao.manitas.ValoracionDAO;

@Path("/profesional")
public class ProfesionalRest {
	
	// MANITAS =================================================================

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessional(
			@PathParam("id") int idp,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		Response response = null;
		Manitas unManitas = null;
		String errorMsg = null;
		errorMsg = "{\"error\":\"No hay resultados con esos parÃ¡metros de bÃºsqueda\"}";
		try {
			ManitasEM em=ManitasEM.getInstance();
			unManitas =em.getProfesionalByID(idp);
			if (unManitas != null) {
				System.out.println(unManitas);
				unManitas.setValoracion(null);	
				response = Response.status(200).entity(unManitas).build();
			} else {
				response = Response.status(404).entity(errorMsg).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "{\"error\":\"Hubo un error en el servidor\"}";
			response = Response.status(500).entity(errorMsg).build();

		}
		return response;
	}

	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProfesional(@PathParam(value = "id") int idp, Manitas man,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		System.out.println("put id=" + idp + ", man=" + man);
		Response response = null;
		boolean isOk = false;
		if (man.getId() == idp) {
			try {
				isOk = ManitasEM.getInstance().actualizar(man);
				if (isOk) {
					response =  Response.status(202).entity(isOk).build();
				} else {
					response =  Response.status(404).entity(isOk).build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response = Response.status(Status.FORBIDDEN.getStatusCode()).entity("Error").build();
		}
		return response;
	}

	
	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteProfesional(@PathParam(value = "id") int idp,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		System.out.println(String.format("deleteProfesional(%d)", idp));
		Response res = null;
		// FIXME deberia utilizar una transaccion para esto
		try {
			boolean expFueBorrada = ExperienciaDAO.getInstance().deleteAll(idp);
			boolean eduFueBorrada = EducacionDAO.getInstance().deleteAll(idp);
			boolean valFueBorrada = ValoracionDAO.getInstance().deleteAll(idp);
			boolean proFueBorrada = ProfesionDAO.getInstance().delete(idp);
			
			int ubiId = DAO.NO_ID;
			Map<String, Object> usuMap = UsuarioDAO.getInstance().read(idp);
			if (usuMap != null) {
				ubiId = (usuMap.get("ubi_id") == null) ? DAO.NO_ID : (int) usuMap.get("ubi_id");
			}
			
			boolean usuFueBorrada = UsuarioDAO.getInstance().delete(idp);
			boolean ubiFueBorrada = UbicacionDAO.getInstance().delete(ubiId);
			
			if (proFueBorrada) {
				if(expFueBorrada && eduFueBorrada && valFueBorrada &&
						usuFueBorrada && ubiFueBorrada ) {
					System.out.println(String.format("deleteProfesional(%d) = 202", idp));
					res = Response.status(202).build();
					System.out.println(String.format("deleteProfesional(%d)2 = 202", idp));
				}
			} else {
				res = Response.status(404).entity("").build();
			}
		} catch (Exception e) {
			System.out.println(String.format("deleteProfesional(%d) = 500", idp));
			e.printStackTrace();
			res = Response.status(500).entity( e.getMessage() ).build();
		}
		return res;
	}

	
	
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response newProfesional(Manitas man,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
		System.out.println("POST man = " + man);
		Response res = null;
		try {
			Ubicacion ubi = man.getUbicacion();
			int ubiID = UbicacionDAO.getInstance().create(ubi.getLatitud(), ubi.getLongitud());
			int usuID = UsuarioDAO.getInstance().create(man.getNombre(), man.getApellidos(), man.getEmail(),
					man.getPassword(), man.getAvatar(), ubiID);
			int profID = ProfesionDAO.getInstance().create(usuID, man.getProfesion());
			
			List<String> expList = man.getExperiencia();
			if ((expList != null) && (expList.size() > 0)) {
				for (String exp : expList) {
					ExperienciaDAO.getInstance().create(profID, exp);
				}
			}
			
			List<String> eduList = man.getEducacion();
			if ((eduList != null) && (eduList.size() > 0)) {
				for (String edu : eduList) {
					EducacionDAO.getInstance().create(profID, edu);
				}
			}
			
			List<Valoracion> valList = man.getValoraciones();
			if ((valList != null) && (valList.size() > 0)) {
				for (Valoracion val : valList) {
					ValoracionDAO.getInstance().create(
							val.getComentario(),
							val.getPuntuacion(),
							val.getAutor().getId(),
							profID);
				}
			}
			
			res = Response.status(202).entity(usuID).build();
		} catch (Exception e) {
			res = Response.status(500).entity(e.getMessage()).build();
			//e.printStackTrace();
		}
		return res;
	}
	
	
	
	
	
	// EDUCACION ===============================================================
	
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
	
	
	
	
	
	// EXPERIENCIA  ============================================================
	
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
	
	
	
	
	
	// PROFESION ===============================================================
	
	@GET
	@Path("/{id}/profesion")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfesionManitas(
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
	public Response getProfesionesManitasTerminoBusqueda(
			@QueryParam("s") String terminoBusqueda,
			@HeaderParam("token") String token) {
		
		// ¿AUTORIZADO?
		AuthService auth = new AuthService();
		int userTokenId = auth.getUsuarioIdFromToken(token);
		if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
		}
		// PROCEDE:
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
	public Response deleteProfesionManitas(
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
			@HeaderParam("profesion") String profesion,
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
			@HeaderParam("profesion") String profesion,
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
	
	
	
	
	
	// VALORACION ==============================================================
	
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
