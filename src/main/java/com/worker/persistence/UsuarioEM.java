package com.worker.persistence;

import java.util.List;

import javax.persistence.Query;

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
	
//	public Usuario getUsuarioById(String id) {
//		Usuario Usu = null;
//		try {
//			Session session = factory.openSession();
//			Transaction tx = session.beginTransaction();
//			Usu = (Usuario) session.createQuery("FROM Usuario  WHERE id= "+id, Usuario.class).getSingleResult();
//			session.close();
//			tx.commit();
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return Usu;
//			
//	}
	
	/* TODO implementad también este método.
	 * Ahora devuelve un mock para poder trabajar
	 * hasta que exista la implementación real
	 */
	
	
	public Usuario getUsuarioById(String id) {
		Usuario usuario = null;
		try {
			int usuarioId = Integer.parseInt( id );
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("FROM Usuario WHERE id = :id", Usuario.class);
			query.setParameter("id", usuarioId);
			usuario = (Usuario) query.getSingleResult();
			session.close();
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
