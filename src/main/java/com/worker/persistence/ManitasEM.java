package com.worker.persistence;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.Manitas;
import com.worker.models.Usuario;
import com.worker.models.Valoracion;

public class ManitasEM extends EntityManager{

	//Atributos

	private static ManitasEM singleton = null;

	//Instanciaci�n
	protected ManitasEM() {
		super();
	}

	public static final ManitasEM getInstance() {
		return (singleton == null) ? new ManitasEM() : singleton;
	}

	//Servicios

	// 1-Buscar por profesion
	public List<Manitas> getListaByProfession(String filter){
		List<Manitas> listaManitas = null;
		try {
		Session session = factory.openSession(); // like %contenido%
		Transaction tx = session.beginTransaction();
		listaManitas = session.createQuery("FROM Manitas m WHERE m.profesion LIKE '%" + filter + "%'", Manitas.class).getResultList();
		session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listaManitas;
	}

	// 2-Buscar por nombre
	public List<Manitas> getListaByName(String filter){
		List<Manitas> listaManitas = null;
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			listaManitas = session.createQuery("FROM Manitas m WHERE m.nombre LIKE '%" + filter + "%'", Manitas.class).getResultList();
			session.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return listaManitas;
	}

	
	
	// 3-Obtener manitas por ID
	public Manitas getManitasById(String id) {
		System.out.println(String.format("ManitasEM.getManitasById(%s)", id));
		Manitas manitas = null;
		Session session = null;
		String profesion = null;
		int manitasId;
		try {
			manitasId = Integer.parseInt( id );
			session = factory.openSession();
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("SELECT profesion FROM Manitas WHERE id = :id", String.class);
			query.setParameter("id", manitasId);
			profesion = (String) query.getSingleResult();
			tx.commit();
			Usuario usuarioBase = UsuarioEM.getInstance().getUsuarioById(id);
			if ((usuarioBase != null) && (profesion != null)) {
				manitas = new Manitas(usuarioBase, profesion);
				List<Valoracion> listaValoraciones = ValoracionEM.getInstance().getValorecionesDelManitas(id);
				for (Valoracion valoracion : listaValoraciones) {
					manitas.addValoracion(valoracion);
				}
				// TODO educacion + experiencia
			}
			System.out.println(String.format("ManitasEM.getManitasById(%s) >>> manitasAttributes = %s", id, profesion));
			System.out.println(String.format("ManitasEM.getManitasById(%s) >>> usuarioBase = %s", id, usuarioBase));
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		System.out.println(String.format("ManitasEM.getManitasById(%s) = %s", id, manitas));
		return manitas;
	}
	
	
	
	public boolean save(Manitas emp) {
		Session session =factory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(emp);
		trans.commit();
		session.close();
		return true;
		
	}
	

}
