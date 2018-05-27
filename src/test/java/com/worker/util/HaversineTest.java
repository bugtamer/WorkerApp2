package com.worker.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.worker.util.Haversine;

public class HaversineTest {
	
	@Test
	public void testDistancia() {
		double expectedKM = 0.01383;
		double margenKM   = .010; 
		double sut = Haversine.calcDistantKm(43.5291675, -5.6391020, 43.5292752, -5.6390161);
		assertEquals(expectedKM, sut, margenKM);
	}

}
