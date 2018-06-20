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
	int intId = Integer.parseInt(id);
	Ubicacion ubicacion = new Ubicacion(41.34, 2.56);
	String url_imagen = "./imgs/Pablo-entrenador-personal.jpg";
	Usuario implementaEsteMetodo = new Usuario("Nombre mock", "apellidos mock", "email@mock.info", "password mock");
	implementaEsteMetodo.setId(intId);
	implementaEsteMetodo.setAvatar(url_imagen);
	implementaEsteMetodo.setUbicacion(ubicacion);
	return implementaEsteMetodo;
	}
	
}
