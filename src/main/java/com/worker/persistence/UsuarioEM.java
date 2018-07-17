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



	public Usuario getUsuario(String email, String password) {
		Usuario usuario = null;
		Session session = null;
		try {
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			String hql = "FROM Usuario WHERE (email = :email) AND (password = :psswd)";
			Query query = session.createQuery(hql, Usuario.class);
			query.setParameter("email", email);
			query.setParameter("psswd", password);
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

		System.out.println(String.format("UsuarioEM.getUsuario(%s, %s) = %s",
				email, password, usuario));

		return usuario;
	}



	public boolean save(Usuario usuario) {
		Session session = factory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(usuario);
		trans.commit();
		session.close();
		return true;
	}



	public Usuario getUsuarioById(int id) {
		Usuario usuario = null;
		Session session = null;
		try {
			int usuarioId = id ;
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
	
	

	
	public boolean deleteUsuario(int id) {
		boolean result = false;
		Session session = null;

		session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Usuario uDelete = new Usuario();
		uDelete= session.load(Usuario.class, id);
		uDelete.setId(id);

		session.delete(uDelete);
		tx.commit();
		session.close();
		System.out.println("Usuario eliminado" + result);

		return result;

	}
	
	public Usuario add(){
		Usuario usuario = null;
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		session.update(usuario);
		System.out.println("entra usuarioEM"+usuario);
		t.commit();
		session.close();
	
		return usuario;
}


}
