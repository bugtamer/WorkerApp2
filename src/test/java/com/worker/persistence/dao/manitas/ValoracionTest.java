package com.worker.persistence.dao.manitas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.worker.persistence.dao.UbicacionDAO;
import com.worker.persistence.dao.UsuarioDAO;

public class ValoracionTest {
	
	private static ValoracionDAO valDao;
	private static Random rnd;
	private int valId;
	private static final int AUTOR = 1;
	private static final int RECEPTOR = 2;
	private String comentario;
	private int puntuacion;
	
	
	@BeforeClass
	public static void db() {
		rnd = new Random();
		try {
			valDao = ValoracionDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		comentario = String.format("Valoración %d", System.currentTimeMillis());
		puntuacion = 1 + rnd.nextInt(5);
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		valDao.delete(valId);
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		valId = valDao.create(comentario, puntuacion, AUTOR, RECEPTOR);
		assertTrue( sonEquivalentes(valId, comentario, puntuacion, AUTOR, RECEPTOR, valDao.read(valId)) );
	}
	
	
	
	@Test
	public void createAndReadRecibidasTest() throws SQLException {
		valId = valDao.create(comentario, puntuacion, AUTOR, RECEPTOR);
		assertTrue( isFound(valId, valDao.readRecibidas(RECEPTOR)) );
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		boolean isDeleted = false;
		valId = valDao.create(comentario, puntuacion, AUTOR, RECEPTOR);
		isDeleted = valDao.delete(valId);
		assertTrue(isDeleted);
		assertFalse( isFound(valId, valDao.readRecibidas(RECEPTOR)) );
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		valId = valDao.create(comentario, puntuacion, AUTOR, RECEPTOR);
		String comentario2 = "UPDATED " + comentario;
		int puntuacion2 = 1 + rnd.nextInt(5);
		valDao.update(valId, comentario2, puntuacion2);
		assertTrue( sonEquivalentes(valId, comentario2, puntuacion2, AUTOR, RECEPTOR, valDao.read(valId)) );
	}
	
	
	
	@Test
	public void avgTest() throws Exception {
		int ubiId = UbicacionDAO.getInstance().create(1, 2);
		int usuId = UsuarioDAO.getInstance().create("nombre", "apellidos", "email", "password", "url_avatar", ubiId);
		ProfesionDAO.getInstance().create(usuId, "profesion");
		int val1 = ValoracionDAO.getInstance().create("comentario1", 1, 1, usuId);
		int val2 = ValoracionDAO.getInstance().create("comentario2", 2, 1, usuId);
		int val3 = ValoracionDAO.getInstance().create("comentario3", 5, 1, usuId);
		
		assertEquals(3, ValoracionDAO.getInstance().avg(usuId));
		
		ValoracionDAO.getInstance().delete(val3);
		ValoracionDAO.getInstance().delete(val2);
		ValoracionDAO.getInstance().delete(val1);
		ProfesionDAO.getInstance().delete(usuId);
		UsuarioDAO.getInstance().delete(usuId);
		UbicacionDAO.getInstance().delete(ubiId);
	}
	
	
	
	// DETALLES DE IMPLEMENTACION DE MAS BAJO NIVEL
	
	private boolean isFound(int val_id, List<Map<String, Object>> source) {
		boolean isFound = false;
		for (Map<String, Object> candidate : source) {
			if (((Integer) candidate.get(ValoracionDAO.VALORACION_ID)) == val_id) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	
	
	
	private boolean sonEquivalentes(int val_id, String comentario, int puntuacion, int autor_id, int receptor_id, Map<String, Object> source) {
		boolean areEquals = false;
		int val_idSrc = (Integer) source.get(ValoracionDAO.VALORACION_ID);
		String comentarioSrc = (String) source.get(ValoracionDAO.COMENTARIO);
		int puntuacionSrc = (Integer) source.get(ValoracionDAO.PUNTUACION);
		int autor_idSrc = (Integer) source.get(ValoracionDAO.AUTOR_ID);
		int receptor_idSrc = (Integer) source.get(ValoracionDAO.RECEPTOR_ID);
		if (val_idSrc == val_id) {
			boolean comentarioCorrecto = comentarioSrc.equals(comentario);
			boolean puntuacionCorrecta = (puntuacionSrc == puntuacion);
			boolean autorCorrecto = (autor_idSrc == autor_id);
			boolean receptorCorrecto = (receptor_idSrc == receptor_id);
			if (comentarioCorrecto && puntuacionCorrecta && autorCorrecto && receptorCorrecto) {
				areEquals = true;
			}
		}
		System.out.println(String.format("sonEquivalentes() >>> val=%d, co=%s, punt=%s, aut=%d, recp=%d == src=%s", val_id, comentario, puntuacion, autor_id, receptor_id, source));
		return areEquals;
	}
	
}
