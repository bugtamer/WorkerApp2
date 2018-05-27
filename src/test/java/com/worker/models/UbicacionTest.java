package com.worker.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class UbicacionTest {
	
	@Test
	public void testDistanciaKM() {
		double expectedKM = 0.01383;
		double margenKM   = .010; 
		Ubicacion igualada = new Ubicacion(43.5291675, -5.6391020);
		Ubicacion granada  = new Ubicacion(43.5292752, -5.6390161);
		double sut = igualada.getDistanciaKilometrica(granada);
		assertEquals(expectedKM, sut, margenKM);
	}

}
