package com.worker.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.Mensaje;

public class MensajeEM extends EntityManager {

	// ATTRIBUTES

	private static MensajeEM singleton = null;



	// INSTANTIATION

	protected MensajeEM() {
		super();
	}


	public static final MensajeEM getInstance() {
		return (singleton == null) ? new MensajeEM() : singleton;
	}



	// SERVICES

	public final List<Mensaje> getList() {
		List<Mensaje> listaMensajes = null;
		try {
			Session session = factory.openSession();
//			Transaction tx = session.beginTransaction();
//			listaMensajes = session.createQuery("FROM Mensaje", Mensaje.class).getResultList();
//			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaMensajes;
	}
}
