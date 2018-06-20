package com.worker.persistence;

import java.util.List;

import org.hibernate.Session;

import com.worker.models.Manitas;
import com.worker.models.Usuario;
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

	public final List<Valoracion> getList() {
		List<Valoracion> listaValoraciones = null;
		try {
			Session session = factory.openSession();
//				Transaction tx = session.beginTransaction();
//				listaMensajes = session.createQuery("FROM Mensaje", Mensaje.class).getResultList();
//				tx.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaValoraciones;
	}
	
//	public boolean Asignarvaloracion(Usuario user, Manitas manitas){
//		
////		if (arrayList != null && !arrayList.isEmpty()) {
////			  T item = arrayList.get(arrayList.size()-1);
////			}
////
////		 Valoracion i= user.getValoracionesHechas();
////		manitas.addValoracion(i);			
//		return false;	
//		
//		
//	}

}
