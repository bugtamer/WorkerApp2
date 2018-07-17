package com.worker.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.worker.models.Manitas;
import com.worker.models.Ubicacion;
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



	public List<Manitas> getManitas(String terminoBusqueda, double distancia,
			Ubicacion ubicacion) {

		final char space = ' ';
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT m.fk_usu").append(space);
		sqlQuery.append("FROM manitas m").append(space);
		sqlQuery.append("LEFT JOIN ubicacion u ON (m.fk_usu = u.ubi_id)").append(space);
		sqlQuery.append("WHERE (m.profesion LIKE '%").append(terminoBusqueda).append("%')").append(space);
		sqlQuery.append(String.format("AND calcDistanciaEnKm(%f, %f, u.latitud, u.longitud) < %f",
				ubicacion.getLatitud(), ubicacion.getLongitud(), distancia));
		System.out.println("SQL=" + sqlQuery.toString());

		List<Manitas> listaManitas = new ArrayList<>();
		try {
			Session session = factory.openSession();
			Query query = session.createNativeQuery( sqlQuery.toString() );
			List<?> listaManitasId = query.getResultList();
			for (Object manitasId : listaManitasId) {
				Manitas manitas = getManitasById( manitasId.toString() );
				listaManitas.add( manitas );
			}
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
			Usuario usuarioBase = UsuarioEM.getInstance().getUsuarioById(manitasId);
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
	public Manitas getProfesionalByID(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Manitas man = session.createQuery("FROM Manitas WHERE id = :id", Manitas.class).setParameter("id", id).getSingleResult();
		tx.commit();
		session.close();
		return man;
	}


}
