package com.worker.persistence;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;

import com.worker.models.Manitas;
import com.worker.models.Mensaje;
import com.worker.models.Ubicacion;
import com.worker.models.Usuario;

public class MensajeEMTest extends com.worker.persistence.ContextMocking {

	private static Logger logger = Logger.getLogger("MensajeEMTest");

	@BeforeClass
	public static void setUpClass() {
		setUpContext();
	}

	@Test
	public void testAddMensajeAndGetConversacionEntre() {
		logger.log(Level.INFO, "Entrando al testAddMensaje()");
		logger.log(Level.INFO, "Emisor");
		Ubicacion ubicacionEmisor = new Ubicacion(41.11, 2.11);
		Usuario emisor = new Usuario("nombre1", "apellidos1", "email@email.com", "password");
		emisor.setId(1);
		emisor.setUbicacion(ubicacionEmisor);

		logger.log(Level.INFO, "Receptor");
		Ubicacion ubicacionReceptor = new Ubicacion(41.22, 2.22);
		Usuario usuarioReceptor = new Usuario("nombre2", "apellidos2", "email@email.com", "password");
		usuarioReceptor.setId(7);
		usuarioReceptor.setUbicacion(ubicacionReceptor);
		Manitas receptor = new Manitas(usuarioReceptor, "profesion");

		logger.log(Level.INFO, "Mensaje");
		final String comentario = "It works!";
		Mensaje mensaje = new Mensaje(emisor, receptor);
		mensaje.setTimestamp(new Date());
		mensaje.setTexto(comentario);

		logger.log(Level.INFO, "SUT");
		MensajeEM sut = MensajeEM.getInstance();
		boolean outcome = sut.addMensaje(mensaje);

		logger.log(Level.INFO, "pruebas");
		assertTrue("¿Añadido?", outcome);
		boolean isFound = false;
		List<Mensaje> listaMensajes = sut.getConversacionEntre(emisor, receptor);
		if ( listaMensajes != null) {
			for (Mensaje m : listaMensajes) {
				if ((m.getTexto() != null) && m.getTexto().equals(comentario)) {
					isFound = true;
					break;
				}
			}
		}
		assertTrue("¿Encontrado?", isFound);
		logger.log(Level.INFO, "Fin de testAddMensaje()");
	}

}
