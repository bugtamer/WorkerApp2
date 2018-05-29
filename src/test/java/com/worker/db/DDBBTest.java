package com.worker.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;

public class DDBBTest {
	
	private static final int ID_MANITAS_EXISTENTE = 4; // del 1 al 3 usuarios, del 4 al 9 manitas
	private static final int ID_NO_EXISTENTE = 1_000_000;

	
	
	@Test
	public void instanciacionDatabaseNoNulltest() {
		DDBB sut = DDBB.getInstance();
		assertTrue(sut != null);
	}

	
	
	@Test
	public void getManitasPorIdExistentetest() {
		DDBB sut = DDBB.getInstance();
		assertEquals(ID_MANITAS_EXISTENTE, sut.getManitas(ID_MANITAS_EXISTENTE).getId());
	}

	
	
	@Test
	public void getManitasPorIdNoExistentetest() {
		DDBB sut = DDBB.getInstance();
		assertNull(sut.getManitas(ID_NO_EXISTENTE));
	}
	
	
	
	@Test
	public void addMensajeTest() {
		final String MSG = "addMensajeTest()";
		// MOCKING
		// emisor
		Usuario emisorMock   = Mockito.mock(Usuario.class);
		when(emisorMock.getId()).thenReturn(10_567_455);
		// receptor
		Usuario receptorMock = Mockito.mock(Usuario.class);
		when(receptorMock.getId()).thenReturn(35_232_902);
		//mensaje
		Mensaje mensajeMock  = Mockito.mock(Mensaje.class);
		when(mensajeMock.getEmisor())  .thenReturn(emisorMock);
		when(mensajeMock.getReceptor()).thenReturn(receptorMock);
		when(mensajeMock.getTexto())   .thenReturn(MSG);
		
		// TEST
		DDBB sut = DDBB.getInstance();
		sut.addMensaje(mensajeMock);
		assertEquals(MSG, sut.getConversacion(emisorMock, receptorMock, 1).get(0).getTexto());
	}
	
	
	
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
	
	public void getConversacionTest() {
		final String MSG1 = "Mensaje 1";
		final String MSG2 = "Mensaje 2";
		final String MSG3 = "Mensaje 3";
		// MOCKING
		// emisor
		Usuario emisorMock   = Mockito.mock(Usuario.class);
		when(emisorMock.getId()).thenReturn(10_567_455);
		// receptor
		Usuario receptorMock = Mockito.mock(Usuario.class);
		when(receptorMock.getId()).thenReturn(35_232_902);
		//mensaje 1
		Mensaje mensajeMock1 = Mockito.mock(Mensaje.class);
		when(mensajeMock1.getEmisor())  .thenReturn(emisorMock);
		when(mensajeMock1.getReceptor()).thenReturn(receptorMock);
		when(mensajeMock1.getTexto())   .thenReturn(MSG1);
		//mensaje 2
		Mensaje mensajeMock2 = Mockito.mock(Mensaje.class);
		when(mensajeMock2.getEmisor())  .thenReturn(emisorMock);
		when(mensajeMock2.getReceptor()).thenReturn(receptorMock);
		when(mensajeMock2.getTexto())   .thenReturn(MSG2);
		//mensaje 3
		Mensaje mensajeMock3 = Mockito.mock(Mensaje.class);
		when(mensajeMock3.getEmisor())  .thenReturn(emisorMock);
		when(mensajeMock3.getReceptor()).thenReturn(receptorMock);
		when(mensajeMock3.getTexto())   .thenReturn(MSG3);
		
		// TEST
		DDBB sut = DDBB.getInstance();
		sut.addMensaje(mensajeMock1);
		sut.addMensaje(mensajeMock2);
		sut.addMensaje(mensajeMock3);
		assertEquals(2,    sut.getConversacion(emisorMock, receptorMock, 2).size());
		assertEquals(MSG2, sut.getConversacion(emisorMock, receptorMock, 2).get(0).getTexto());
		assertEquals(MSG3, sut.getConversacion(emisorMock, receptorMock, 2).get(1).getTexto());
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
