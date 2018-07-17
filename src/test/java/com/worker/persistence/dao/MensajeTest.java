package com.worker.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.worker.util.Timestamp;

public class MensajeTest {
	
	private static MensajeDAO menDao;
	private static final int USUARIO_ID = 1;
	private static final int MANITAS_ID = 2;
	private int men_id;
	private String texto;
	private Date timestamp;
	private String urlImagen;
	private int emisor_usu_id;
	private int receptor_usu_id;
	private Map<String, Object> mensaje;
	
	
	@BeforeClass
	public static void db() {
		try {
			menDao = MensajeDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		timestamp = new Date();
		texto = "texto " + timestamp.getTime();
		urlImagen = "/urlImagen/" + timestamp.getTime();
		emisor_usu_id = USUARIO_ID;
		receptor_usu_id = MANITAS_ID;
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		menDao.delete(men_id);
		men_id = 0;
		emisor_usu_id = 0;
		receptor_usu_id = 0;
		timestamp = null;
		urlImagen = null;
		mensaje = null;
		texto = null;
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		men_id = menDao.create(texto, urlImagen, emisor_usu_id, receptor_usu_id);
		mensaje = menDao.read(men_id);
		System.out.println("--->\n\n\n");
		assertTrue( esComponentesIgualAlMensaje(men_id, texto, urlImagen, emisor_usu_id, receptor_usu_id, mensaje) );
		System.out.println("\n\n\n<---");
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		men_id = menDao.create(texto, urlImagen, emisor_usu_id, receptor_usu_id);
		boolean isDeleted = menDao.delete(men_id);
		assertTrue(isDeleted);
		mensaje = menDao.read(men_id);
		assertEquals(0, mensaje.size());
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		men_id = menDao.create(texto, urlImagen, emisor_usu_id, receptor_usu_id);
		timestamp = new Date(timestamp.getTime() + 100_111);
		texto = "texto " + timestamp.getTime();
		urlImagen = "/urlImagen/" + timestamp.getTime();
		emisor_usu_id = MANITAS_ID;
		receptor_usu_id = USUARIO_ID;
		menDao.update(men_id, texto, urlImagen, emisor_usu_id, receptor_usu_id);
		mensaje = menDao.read(men_id);
		assertTrue( esComponentesIgualAlMensaje(men_id, texto, urlImagen, emisor_usu_id, receptor_usu_id, mensaje) );
	}
	
	
	
	private boolean esComponentesIgualAlMensaje(int men_id, String texto, String urlImagen, int emisor_usu_id, int receptor_usu_id, Map<String, Object> source) {
		boolean esEquivalente = false;
		if ((Integer) source.get("men_id") == men_id) {
			// atributos obligatorios (no nullable)
			int emisorSrc = (Integer) source.get("emisor_usu_id");
			int receptorSrc = (Integer) source.get("receptor_usu_id");
			boolean emisorCorrecto = (emisorSrc == emisor_usu_id);
			boolean receptorCorrecto = (receptorSrc == receptor_usu_id);
			
			// atributos opcionales (nullable)
			String textoSrc = (String) source.get("texto");
			String urlImagenSrc = (String) source.get("urlImagen");
			boolean textoCorrecto;
			boolean urlImagenCorrecto;
			if (texto == null) {
				textoCorrecto = (textoSrc == null);
			} else {
				textoCorrecto = texto.equals(textoSrc);
			}
			if (urlImagen == null) {
				urlImagenCorrecto = (urlImagenSrc == null);
			} else {
				urlImagenCorrecto = urlImagen.equals(urlImagenSrc);
			}
			
			if (textoCorrecto && urlImagenCorrecto && emisorCorrecto && receptorCorrecto) {
				esEquivalente = true;
			}
		}
		System.out.println(String.format("return=%b, mid=%d, txt=%s, t=%s, img=%s, eid=%d, rid=%d, msg=%s", esEquivalente, men_id, texto, Timestamp.format(timestamp), urlImagen, emisor_usu_id, receptor_usu_id, source));
		return esEquivalente;
	}
	
}
