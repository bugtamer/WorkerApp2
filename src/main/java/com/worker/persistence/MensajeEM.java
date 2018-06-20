package com.worker.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;

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

	public final boolean addMensaje(Mensaje mensaje) {
		boolean isAdded = true; 
		try {
			Session session = factory.openSession();
			session.save(mensaje);
			Transaction tx = session.beginTransaction();
			tx.commit();
			session.close();
		} catch (Exception e) {
			isAdded = false;
			e.printStackTrace();
		}
		return isAdded; // FIX devuelve boolean por simplificación 
	}

	
	public final List<Mensaje> getConversacionEntre(Usuario usuario, Manitas manitas) {
		List<Mensaje> listaMensajes = null;
		
		String fromUsuarioToManitas = String.format(
				"(M.emisor.id = %d and M.receptor.id = %d)",
				usuario.getId(), manitas.getId());
		
		String fromManitasToUsuario = String.format(
				"(M.emisor.id = %d and M.receptor.id = %d)",
				manitas.getId(), usuario.getId());
		
		StringBuilder hql = new StringBuilder();
		hql.append("FROM com.worker.models.Mensaje AS M WHERE ");
		hql.append(fromUsuarioToManitas);
		hql.append(" OR ");
		hql.append(fromManitasToUsuario);
		System.out.println("MensajeEM-HQL=" + hql.toString());
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			listaMensajes = session.createQuery(hql.toString(), Mensaje.class).getResultList();
			tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MensajeEM-size=" + listaMensajes.size());
		return listaMensajes;
	}
	
}
