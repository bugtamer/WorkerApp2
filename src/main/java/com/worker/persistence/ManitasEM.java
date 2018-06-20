package com.worker.persistence;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;

import com.worker.models.Manitas;
import com.worker.models.Usuario;

public class ManitasEM extends EntityManager{
	
	//Atributos
	
	private static ManitasEM singleton = null;
	
	//Instanciación
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
		Manitas M = null;
		try {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			M = (Manitas) session.createQuery("FROM manitas m WHERE m.id LIKE '%" + id +"%'", Manitas.class);
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return M;
			
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
