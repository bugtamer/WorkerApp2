package com.worker.services;

import javax.ws.rs.PathParam;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.models.Manitas;
import com.worker.models.Ubicacion;
import com.worker.models.Usuario;
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

	public static Manitas unManitas;

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProfesional(@PathParam(value = "id") int idp, Manitas man) {
		boolean isOk = false;
		if (man.getId() == idp) {
			try {
				isOk = ManitasEM.getInstance().actualizar(man);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(202).entity(isOk).build();
		} else {
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity("Error").build();
		}
	}

	
	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@DELETE
	public Response deleteProfesional(@PathParam(value = "id") int idp) {
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
				ubiId = (int) usuMap.get("ubi_id");
			}
			
			boolean usuFueBorrada = UsuarioDAO.getInstance().delete(idp);
			boolean ubiFueBorrada = UbicacionDAO.getInstance().delete(ubiId);
			
			if(expFueBorrada && eduFueBorrada && valFueBorrada &&
					proFueBorrada && usuFueBorrada && ubiFueBorrada ) {
				System.out.println(String.format("deleteProfesional(%d) = 202", idp));
				res = Response.status(202).build();
				System.out.println(String.format("deleteProfesional(%d)2 = 202", idp));
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
	public Response newProfesional(Manitas man) {

		Response res = null;
		try {
			Ubicacion ubi = man.getUbicacion();
			int ubiID = UbicacionDAO.getInstance().create(ubi.getLatitud(), ubi.getLongitud());
			int usuID = UsuarioDAO.getInstance().create(man.getNombre(), man.getApellidos(), man.getEmail(),
					man.getPassword(), null, man.getId());
			int profID = ProfesionDAO.getInstance().create(man.getId(), man.getProfesion());
			int expID = ExperienciaDAO.getInstance().create(man.getId(), null);
			int eduID = EducacionDAO.getInstance().create(man.getId(), null);
			res = Response.status(202).entity(usuID).build();
		} catch (Exception e) {
			res = Response.status(500).entity(e.getMessage()).build();
			//e.printStackTrace();
		}
		return res;
	}

}