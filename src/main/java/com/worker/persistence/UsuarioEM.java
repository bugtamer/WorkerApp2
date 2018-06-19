package com.worker.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.*;

public class UsuarioEM extends EntityManager {
	
	private static UsuarioEM singleton = null;
	

	
	protected UsuarioEM() {
		
	}
	
	public static final UsuarioEM getInstance() {
		return(singleton == null) ? new UsuarioEM(): singleton;
	}
	
	public final List<Usuario>getList(){
		List<Usuario> listaUsuarios = null;
		try {
			Session session = factory.openSession();
			Transaction trans = session.beginTransaction();
			listaUsuarios =session.createQuery("FROM Usuario",Usuario.class).getResultList();
	
			trans.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	public boolean save(Usuario emp) {
		Session session =factory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(emp);
		trans.commit();
		session.close();
		return true;
		
	}
	
	
	
	
	
}
