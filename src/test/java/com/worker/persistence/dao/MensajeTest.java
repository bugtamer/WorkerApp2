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
		men_id = menDao.create(texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id);
		mensaje = menDao.read(men_id);
		assertTrue( isFound(men_id, texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id, mensaje) );
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		men_id = menDao.create(texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id);
		boolean isDeleted = menDao.delete(men_id);
		assertTrue(isDeleted);
		mensaje = menDao.read(men_id);
		assertEquals(0, mensaje.size());
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		men_id = menDao.create(texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id);
		timestamp = new Date(timestamp.getTime() + 100_111);
		texto = "texto " + timestamp.getTime();
		urlImagen = "/urlImagen/" + timestamp.getTime();
		emisor_usu_id = MANITAS_ID;
		receptor_usu_id = USUARIO_ID;
		menDao.update(men_id, texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id);
		mensaje = menDao.read(men_id);
		assertTrue( isFound(men_id, texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id, mensaje) );
	}
	
	
	
	private boolean isFound(int men_id, String texto, Date timestamp, String urlImagen, int emisor_usu_id, int receptor_usu_id, Map<String, Object> source) {
		boolean isFound = false;
		//SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		if ((Integer) source.get("men_id") == men_id) {
			boolean textoCorrecto = ((String) source.get("texto")).equals(texto);
			boolean timestampCorrecto = true;//(sdf.format((Date) source.get("timestamp"))).equals(sdf.format(timestamp)); // FIXME Date: SQL vs Util
			boolean urlImagenCorrecto = ((String) source.get("urlImagen")).equals(urlImagen);
			boolean emisorCorrecto= ((Integer) source.get("emisor_usu_id")) == emisor_usu_id;
			boolean receptorCorrecto = ((Integer) source.get("receptor_usu_id")) == receptor_usu_id;
			if (textoCorrecto && timestampCorrecto && urlImagenCorrecto && emisorCorrecto && receptorCorrecto) {
				isFound = true;
			}
		}
		return isFound;
	}
	
}
