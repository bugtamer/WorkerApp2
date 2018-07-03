package com.worker.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.worker.models.Manitas;
import com.worker.models.Ubicacion;
import com.worker.models.Usuario;
import com.worker.persistence.ManitasEM;

@Path("/profesional")
public class ManitasByServicioGeolocalizadoApiRest {

	@GET
	@Path("/geoservice")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getManitasPorServicioYUbicaion(@QueryParam(value = "servicio") String terminoBusqueda,
			@QueryParam(value = "distancia") double distancia, @QueryParam(value = "latitud") double latitud,
			@QueryParam(value = "longitud") double longitud) {

		// FIXME quitar hardcoded: no recupera los doubles
		distancia = 13.3;
		latitud = 41.3;
		longitud = 2.3;

		String errorMsg = null;
		Response response = null;
		System.out.println(
				String.format("servicio=%s, dist=%f, lat=%f, lon=%f", terminoBusqueda, distancia, latitud, longitud));
		try {
			Ubicacion ubicacion = new Ubicacion(longitud, latitud);
			ManitasEM em = ManitasEM.getInstance();
			List<Manitas> listaResultados = em.getManitas(terminoBusqueda, distancia, ubicacion);
			if (listaResultados.size() == 0) {
				errorMsg = "{\"error\":\"No hay resultados con esos parámetros de búsqueda\"}";
				response = Response.status(Status.NOT_FOUND).entity(errorMsg).build();
			} else {
				for (Manitas anonimizar : listaResultados) {
					anonimizar.setEducacion(null);
					anonimizar.setExperiencia(null);
					anonimizar.setValoracion(null);
				}
				response = Response.status(202).entity(listaResultados).build();
			}
		} catch (NullPointerException | NumberFormatException e) {
			errorMsg = "{\"error\":\"Formato erróneo de la request\"}";
			response = Response.status(Status.BAD_REQUEST).entity(errorMsg).build();
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = "{\"error\":\"Hubo un error en el servidor\"}";
			response = Response.status(500).entity(errorMsg).build();
		}
		return response;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfessional(@PathParam("id") int idp) {
		Response response = null;
		Manitas unManitas = null;
		String errorMsg = null;
		errorMsg = "{\"error\":\"No hay resultados con esos parámetros de búsqueda\"}";
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
		// return Response.status(200).entity("hol").build();

	}

}
