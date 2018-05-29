package com.worker.db;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.worker.models.Manitas;
import com.worker.models.Usuario;

public class DDBBTest {

	@Test
	public void getResultadosBusquedaNuloTest() {
		DDBB sut = DDBB.getInstance();
		assertEquals(0, (sut.getResultadosBusqueda(null).size()));
	}

	@Test
	public void getResultadosBusquedaVacioTest() {
		DDBB sut = DDBB.getInstance();
		assertEquals(0, (sut.getResultadosBusqueda("").size()));
	}

	@Test
	public void getResultadosBusquedaQueryNoEncontradaTest() {
		DDBB sut = DDBB.getInstance();
		assertEquals(0, (sut.getResultadosBusqueda("///").size()));
	}

	@Test
	public void getResultadosBusquedaQuerySiencontradaTest() {
		String profe = "***";
		Usuario pepeU = new Usuario("Pepe", "apellidos", "email", "password");
		Manitas pepeM = new Manitas(pepeU, profe);
		DDBB sut = DDBB.getInstance();
		sut.addManitas(pepeM);
		List<Manitas> m = sut.getResultadosBusqueda(profe);
		assertEquals(1, sut.getResultadosBusqueda(profe).size());
		assertEquals(profe, m.get(0).getProfesion());
	}
	
	@Test
	public void addManitasTest() {
		String profe = "???";
		DDBB sut = DDBB.getInstance();
		assertEquals(0, sut.getResultadosBusqueda(profe).size());
		Usuario pepeU = new Usuario("Pepe", "apellidos", "email", "password");
		Manitas pepeM = new Manitas(pepeU, profe);
		sut.addManitas(pepeM);
		assertEquals(1, sut.getResultadosBusqueda(profe).size());
		List<Manitas> m = sut.getResultadosBusqueda(profe);
		assertEquals(profe, m.get(0).getProfesion());
	}
	
	
}
