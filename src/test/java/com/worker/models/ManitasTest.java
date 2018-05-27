package com.worker.models;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ManitasTest {

	@Test
	public void testGetMediaValoraciones() {
		Usuario usuarioMock = mock(Usuario.class);
		when(usuarioMock.getNombre()).thenReturn("Nombre");
		when(usuarioMock.getApellidos()).thenReturn("Apellidos");
		when(usuarioMock.getEmail()).thenReturn("Email");
		when(usuarioMock.getPassword()).thenReturn("Password");
		
		Manitas sut = new Manitas(usuarioMock, "Valoración mediar tester");
		
		Valoracion valoracionMock1 = mock(Valoracion.class);
		Valoracion valoracionMock2 = mock(Valoracion.class);
		Valoracion valoracionMock3 = mock(Valoracion.class);
		when(valoracionMock1.getPuntuacion()).thenReturn(4);
		when(valoracionMock2.getPuntuacion()).thenReturn(1);
		when(valoracionMock3.getPuntuacion()).thenReturn(4);

		sut.addValoracion(valoracionMock1);
		sut.addValoracion(valoracionMock2);
		sut.addValoracion(valoracionMock3);

		assertEquals(3, sut.getMediaValoraciones());
	}

}
