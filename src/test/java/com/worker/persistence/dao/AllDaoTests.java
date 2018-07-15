package com.worker.persistence.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.worker.persistence.dao.manitas.EducacionTest;
import com.worker.persistence.dao.manitas.ExperienciaTest;
import com.worker.persistence.dao.manitas.ProfesionTest;
import com.worker.persistence.dao.manitas.ValoracionTest;

@RunWith(Suite.class)
@SuiteClasses({
	UbicacionTest.class,
	UsuarioTest.class,
	MensajeTest.class,
	// ManitasTest:
	ProfesionTest.class,
	EducacionTest.class,
	ExperienciaTest.class,
	ValoracionTest.class
})
public class AllDaoTests { }
