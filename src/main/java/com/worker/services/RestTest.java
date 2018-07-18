package com.worker.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.persistence.dao.DAO;

@Path("/test")
public class RestTest {
	
	private static List<String> mockDB = new ArrayList<> ();
	
	static {
		mockDB.add("item 1");
		mockDB.add("item 2");
		mockDB.add("item 3");
	}
	
	
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		return getResponse();
	}

	
	
	
	@GET
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
	public Response tokenTest(@HeaderParam("token") String token) {
			// ¿AUTORIZADO?
			AuthService auth = new AuthService();
			int userTokenId = auth.getUsuarioIdFromToken(token);
			if(userTokenId == DAO.NO_ID) {
			return Response.status(Status.FORBIDDEN).build();
			}
			// PROCEDE:
			return getResponse();
	}
	
	
	
	private Response getResponse() {
		return Response.status(202).entity(mockDB).build();
	}

}
