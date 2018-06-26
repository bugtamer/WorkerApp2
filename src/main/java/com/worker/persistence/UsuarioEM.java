package com.worker.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.*;

public class UsuarioEM extends EntityManager {
	
	
	// INSTANCIACION
	
	private static UsuarioEM singleton = null;
	
	
	protected UsuarioEM() { }
	
	
	public static final UsuarioEM getInstance() {
		return(singleton == null) ? new UsuarioEM() : singleton;
	}
	
	
	
	// SERVICIOS
	
	public final List<Usuario>getList(){
		List<Usuario> listaUsuarios = null;
		try {
			Session session = factory.openSession();
			Transaction trans = session.beginTransaction();
			listaUsuarios = session.createQuery("FROM Usuario", Usuario.class).getResultList();
			trans.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	
	
	public boolean save(Usuario usuario) {
		Session session = factory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(usuario);
		trans.commit();
		session.close();
		return true;
	}
	
	
	
	public Usuario getUsuarioById(String id) {
		Usuario usuario = null;
		Session session = null;
		try {
			int usuarioId = Integer.parseInt( id );
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("FROM Usuario WHERE id = :id", Usuario.class);
			query.setParameter("id", usuarioId);
			usuario = (Usuario) query.getSingleResult();
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return usuario;
	}
	
}
