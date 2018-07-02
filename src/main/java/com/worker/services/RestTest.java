package com.worker.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class RestTest {
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response test() {
		List<String> lista=new ArrayList<> ();
		lista.add("item 1");
		lista.add("item 2");
		lista.add("item 3");
		return Response.status(202).entity(lista).build();
	}

}
