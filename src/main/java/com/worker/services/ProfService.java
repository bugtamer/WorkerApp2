package com.worker.services;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;

//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.Status;

//import org.apache.catalina.manager.StatusManagerServlet;

import com.worker.models.Manitas;
import com.worker.persistence.ManitasEM;
import com.worker.persistence.dao.DAO;

@Path("/profesionales")
public class ProfService {
	//private static Logger logger = Logger.getLogger("Profesionales");
	
	public static Manitas unManitas;
	
	static {
		//unManitas = ManitasEM.getInstance().getManitasById(id);
	}
	
	
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
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
		Response mResponse=null;
		Manitas unManitas = null;
		unManitas = ManitasEM.getInstance().getProfesionalByID(idp);
		if(unManitas!=null) {
		System.out.println(unManitas);
		mResponse =Response.status(200).entity(unManitas).build();
		}else{
			
			mResponse =Response.status(404).entity(unManitas).build();
		}
		return mResponse;
//		return Response.status(200).entity("hol").build();
	
	}
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@DELETE
//	public Response deleteProfessional(@PathParam(value="id") int id) {
//		boolean isOk = ManitasEM.getInstance().deleteProfesionalById(id);
//		if(!isOk) {
//			
//		}
//		return ;
//	}
//	
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	@PUT
//	public Response actualizarProfessional(@PathParam(value="id") int idp, Manitas man) {
//		boolean isOk=false;
//		if(man.getId()==idp) {
//			try {
//				isOk=ManitasEM.getInstance().actualizar(man);
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//			return Response.status(202).entity(isOk).build();
//		}else {
//			return Response.status(Status.FORBIDDEN.getStatusCode()).entity("Error").build();
//		}
//	}
//	
//	
	
}
