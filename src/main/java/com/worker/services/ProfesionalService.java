package com.worker.services;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

@Path("/prof")
public class ProfesionalService {

//	public static Manitas unManitas;

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

}