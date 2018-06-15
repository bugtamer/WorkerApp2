package com.worker.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EntityManager {

	protected SessionFactory factory=null;
	
	protected EntityManager() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		factory = cfg.buildSessionFactory();

	}
}
