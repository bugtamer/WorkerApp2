package com.worker.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class EducacionEM extends EntityManager {
	
	// ATTRIBUTES

	private static EducacionEM singleton = null;
	
	
	
	// INSTANTIATION

	protected EducacionEM() {
		super();
	}
	
	
	public static final EducacionEM getInstance() {
		return (singleton == null) ? new EducacionEM() : singleton;
	}
	
	
	
	// SERVICES

	public final List<String> getEducacionDelManitas(String id) {
		List<String> listaEducacion = new ArrayList<>();
		Session session = null;
		try {
			session = factory.openSession();
			String sqlQuery = "SELECT educacion FROM educacion WHERE edu_id = " + id;
			Query query = session.createNativeQuery( sqlQuery );
			List<?> rows = query.getResultList();
			for (Object educaion : rows) {
				listaEducacion.add( educaion.toString() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listaEducacion;
	}
	
}
