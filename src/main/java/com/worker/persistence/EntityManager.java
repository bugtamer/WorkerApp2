package com.worker.persistence;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class EntityManager {

	protected SessionFactory factory=null;

	protected EntityManager() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();

	}



	// http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
	protected boolean deleteById(Class<?> type, Serializable id) {
		boolean isDeleted = false;
		try {
			Session session = factory.openSession();
		    Object persistentInstance = session.load(type, id);
		    if (persistentInstance != null) {
				Transaction tx = session.beginTransaction();
		        session.delete(persistentInstance);
				tx.commit();
		        isDeleted = true;
		    }
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return isDeleted;
	}

}
