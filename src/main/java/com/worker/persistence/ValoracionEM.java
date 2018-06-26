package com.worker.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.Valoracion;

public class ValoracionEM extends EntityManager {
	
	// ATTRIBUTES

	private static ValoracionEM singleton = null;
	
	
	
	// INSTANTIATION

	protected ValoracionEM() {
		super();
	}
	
	
	public static final ValoracionEM getInstance() {
		return (singleton == null) ? new ValoracionEM() : singleton;
	}
	
	
	
	// SERVICES

	public final List<Valoracion> getValorecionesDelManitas(String id) {
		List<Valoracion> listaValoraciones = new ArrayList<Valoracion>();
		Session session = null;
		try {
			int receptor_fk_usu = Integer.parseInt(id);
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("FROM Valoracion WHERE receptor_fk_usu = :id", Valoracion.class);
			query.setParameter("id", receptor_fk_usu);
			listaValoraciones = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listaValoraciones;
	}
	
}
