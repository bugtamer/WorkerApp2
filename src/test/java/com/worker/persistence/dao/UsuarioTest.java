package com.worker.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {
	
	private static UsuarioDAO usuDao;
	private static UbicacionDAO ubiDao;
	private String nombre;
	private String apellidos;
	private String url_avatar;
	private String email;
	private String password;
	private int usu_id;
	private int fk_ubi;
	private int ubiId;
	private Map<String, Object> sut;
	
	
	@BeforeClass
	public static void db() {
		try {
			usuDao = UsuarioDAO.getInstance();
			ubiDao = UbicacionDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		long timestamp = System.currentTimeMillis();
		
		nombre = String.format("nombre %d", timestamp);
		apellidos = String.format("apellidos %d", timestamp);
		url_avatar = String.format("url_avatar %d", timestamp);
		email = String.format("email %d", timestamp);
		password = String.format("password %d", timestamp);
		
		try {
			ubiId = ubiDao.create(41.3, 2.3);
			fk_ubi = ubiId;
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		usuDao.delete(usu_id);
		ubiDao.delete(ubiId);
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		usu_id = usuDao.create(nombre, apellidos, email, password, url_avatar, fk_ubi);
		sut = usuDao.read(usu_id);
		assertEquals(nombre, (String) sut.get("nombre"));
		assertEquals(apellidos, (String) sut.get("apellidos"));
		assertEquals(url_avatar, (String) sut.get("url_avatar"));
		assertEquals(email, (String) sut.get("email"));
		assertEquals(usu_id, (int) sut.get("usu_id"));
		assertEquals(fk_ubi, (int) sut.get("ubi_id"));
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		usu_id = usuDao.create(nombre, apellidos, email, password, url_avatar, fk_ubi);
		boolean isDeleted = usuDao.delete(usu_id);
		sut = usuDao.read(usu_id);
		assertTrue(isDeleted);
		assertTrue(sut.get("usu_id") == null);
		assertTrue(sut.get("nombre") == null);
		assertTrue(sut.get("apellidos") == null);
		assertTrue(sut.get("email") == null);
		assertTrue(sut.get("password") == null);
		assertTrue(sut.get("url_avatar") == null);
		assertTrue(sut.get("ubi_id") == null);
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		usu_id = usuDao.create(nombre, apellidos, email, password, url_avatar, fk_ubi);
		
		nombre = String.format("%s %d", nombre, 2);
		apellidos = String.format("%s %d", apellidos, 2);
		url_avatar = String.format("%s %d", url_avatar, 2);
		email = String.format("%s %d", email, 2);
		int new_fk_ubi = ubiDao.create(41.4, 2.4);
		
		usuDao.update(usu_id, nombre, apellidos, email, url_avatar, new_fk_ubi);
		sut = usuDao.read(usu_id);
		assertEquals(nombre, (String) sut.get("nombre"));
		assertEquals(apellidos, (String) sut.get("apellidos"));
		assertEquals(url_avatar, (String) sut.get("url_avatar"));
		assertEquals(email, (String) sut.get("email"));
		assertEquals(new_fk_ubi, (int) sut.get("ubi_id"));
		
		usuDao.update(usu_id, apellidos, url_avatar, email, nombre, fk_ubi);
		ubiDao.delete(new_fk_ubi);
	}
	
	
	
	@Test
	public void createAndUpdatePasswordTest() throws SQLException {
		usu_id = usuDao.create(nombre, apellidos, email, password, url_avatar, fk_ubi);
		password = String.format("%s %d", password, 2);
		try {
			usuDao.updatePassword(usu_id, password);
		} catch (SQLException e) {
			e.printStackTrace();
			fail("updated password failed"); // usuDao.read(id); no devuelve passwords
		}
	}

}
