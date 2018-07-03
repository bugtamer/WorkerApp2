package com.worker.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class ExperienciaEM extends EntityManager {
	
	// ATTRIBUTES

	private static ExperienciaEM singleton = null;
	
	
	
	// INSTANTIATION

	protected ExperienciaEM() {
		super();
	}
	
	
	public static final ExperienciaEM getInstance() {
		return (singleton == null) ? new ExperienciaEM() : singleton;
	}
	
	
	
	// SERVICES

	public final List<String> getExperienciaDelManitas(String id) {
		List<String> listaExperiencia = new ArrayList<>();
		Session session = null;
		try {
			session = factory.openSession();
			String sqlQuery = "SELECT experiencia FROM experiencia WHERE exp_id = " + id;
			Query query = session.createNativeQuery( sqlQuery );
			List<?> rows = query.getResultList();
			for (Object experiencia : rows) {
				listaExperiencia.add( experiencia.toString() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listaExperiencia;
	}
	
}
