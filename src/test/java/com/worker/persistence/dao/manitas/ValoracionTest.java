package com.worker.persistence.dao.manitas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ValoracionTest {
	
	private static ValoracionDAO valDao;
	private static Random rnd;
	private int valId;
	private static final int AUTOR = 1;
	private static final int RECEPTOR = 2;
	private String comentario;
	private int puntuacion;
	private Date timestamp;
	
	
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
		timestamp = new Date();
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		valDao.delete(valId);
	}
	
	
	
	@Test
	public void createAndReadRecibidasTest() throws SQLException {
		valId = valDao.create(comentario, puntuacion, timestamp, AUTOR, RECEPTOR);
		assertTrue( isFound(valId, valDao.readRecibidas(RECEPTOR)) );
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		boolean isDeleted = false;
		valId = valDao.create(comentario, puntuacion, timestamp, AUTOR, RECEPTOR);
		isDeleted = valDao.delete(valId);
		assertTrue(isDeleted);
		assertFalse( isFound(valId, valDao.readRecibidas(RECEPTOR)) );
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		valId = valDao.create(comentario, puntuacion, timestamp, AUTOR, RECEPTOR);
		String comentario2 = "UPDATED " + comentario;
		int puntuacion2 = 1 + rnd.nextInt(5);
		Date timestamp2 = new Date( timestamp.getTime() + 1 );
		valDao.update(valId, comentario2, puntuacion2, timestamp2, AUTOR, RECEPTOR);
		assertTrue( isFound(valId, comentario2, puntuacion2, timestamp2, AUTOR, RECEPTOR, valDao.readRecibidas(RECEPTOR)) );
	}
	
	
	
	private boolean isFound(int val_id, List<Map<String, Object>> source) {
		boolean isFound = false;
		for (Map<String, Object> candidate : source) {
			if (((Integer) candidate.get("val_id")) == val_id) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	
	
	
	private boolean isFound(int val_id, String comentario, int puntuacion, Date timestamp, int autor_id, int receptor_id, List<Map<String, Object>> source) {
		boolean isFound = false;
		//SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
		for (Map<String, Object> candidate : source) {
			if (((Integer) candidate.get("val_id")) == val_id) {
				boolean comentarioCorrecto = ((String) candidate.get("comentario")).equals(comentario);
				boolean puntuacionCorrecta = ((Integer) candidate.get("puntuacion")) == puntuacion;
				boolean timestampCorrecto = true;//(sdf.format((Date) candidate.get("timestamp"))).equals(sdf.format(timestamp)); // FIXME Date: SQL vs Util
				boolean autorCorrecto = ((Integer) candidate.get("autor_usu_id")) == autor_id;
				boolean receptorCorrecto = ((Integer) candidate.get("receptor_fk_usu")) == receptor_id;
				if (comentarioCorrecto && puntuacionCorrecta && timestampCorrecto && autorCorrecto && receptorCorrecto) {
					isFound = true;
					break;
				}
			}
		}
		return isFound;
	}

}
