package com.worker.persistence;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;


import com.worker.persistence.UsuarioEM;

public class UsuarioEMTest extends com.worker.persistence.ContextMocking {

	private static Logger logger = Logger.getLogger("UsuarioEMTest");

	@BeforeClass
	public static void setUpClass() {
		setUpContext();
	}
	@Test
	public void testIdUsuario() {
		//logger.log(Level.INFO);//Entrando a test

		try {
			UsuarioEM emplEM = UsuarioEM.getInstance();




		} catch (Exception ex) {
			ex.printStackTrace();

			fail();
		}

		logger.log(Level.INFO, "Fin de test...");
	}

}
