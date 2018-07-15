package com.worker.persistence.dao.manitas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.worker.persistence.dao.UbicacionDAO;
import com.worker.persistence.dao.UsuarioDAO;

public class ProfesionTest {
	
	private static UbicacionDAO ubiDao;
	private static UsuarioDAO usuDao;
	private static ProfesionDAO proDao;
	private String terminoBusqueda = "xYz";
	private String profesion;
	private int usuarioBaseId;
	private int ubicacionId;
	
	
	@BeforeClass
	public static void db() {
		try {
			proDao = ProfesionDAO.getInstance();
			usuDao = UsuarioDAO.getInstance();
			ubiDao = UbicacionDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		profesion = String.format("Profesion %d", System.currentTimeMillis());
		try {
			ubicacionId = ubiDao.create(41.2, 2.3);
			usuarioBaseId = usuDao.create("nombre", "apellidos", "email", "password", "url_avatar", ubicacionId);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		proDao.delete(usuarioBaseId);
		usuDao.delete(usuarioBaseId);
		ubiDao.delete(ubicacionId);
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		proDao.create(usuarioBaseId, profesion);
		assertEquals(profesion, proDao.read(usuarioBaseId) );
	}
	
	
	
	@Test
	public void createAndFindBeginTest() throws SQLException {
		proDao.create(usuarioBaseId, terminoBusqueda + "more random text");
		assertTrue(count(terminoBusqueda, proDao.read(terminoBusqueda)) > 0);
	}
	
	
	
	@Test
	public void createAndFindMiddleTest() throws SQLException {
		proDao.create(usuarioBaseId, "random text" + terminoBusqueda + "more random text");
		assertTrue(count(terminoBusqueda, proDao.read(terminoBusqueda)) > 0);
	}
	
	
	
	@Test
	public void createAndFindEndTest() throws SQLException {
		proDao.create(usuarioBaseId, "random text" + terminoBusqueda);
		assertTrue(count(terminoBusqueda, proDao.read(terminoBusqueda)) > 0);
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		boolean isDeleted = false;
		proDao.create(usuarioBaseId, profesion);
		isDeleted = proDao.delete(usuarioBaseId);
		assertTrue(isDeleted);
		assertEquals("", proDao.read(usuarioBaseId) );
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		proDao.create(usuarioBaseId, profesion);
		String updatedProfesion = "UPDATED " + profesion;
		proDao.update(usuarioBaseId, updatedProfesion);
		assertEquals(updatedProfesion, proDao.read(usuarioBaseId));
	}
	
	
	
	private int count(String target, List<Map<String, Object>> source) {
		int founds = 0;
		for (Map<String, Object> candidate : source) {
			String profesion = (String) candidate.get("profesion");
			if (profesion.toLowerCase().contains(target.toLowerCase())) {
				founds++;
			}
		}
		return founds;
	}

}
