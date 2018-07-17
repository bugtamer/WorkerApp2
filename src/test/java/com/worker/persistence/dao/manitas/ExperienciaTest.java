package com.worker.persistence.dao.manitas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExperienciaTest {
	
	private static ExperienciaDAO expDao;
	private static final int MANITAS_ID = 2;
	private String sut; 
	
	
	@BeforeClass
	public static void db() {
		try {
			expDao = ExperienciaDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		sut = String.format("Experiencia %d", System.currentTimeMillis());
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		expDao.delete(MANITAS_ID, sut);
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		expDao.create(MANITAS_ID, sut);
		assertTrue( isFound(sut, expDao.read(MANITAS_ID)) );
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		boolean isDeleted = false;
		expDao.create(MANITAS_ID, sut);
		isDeleted = expDao.delete(MANITAS_ID, sut);
		assertTrue(isDeleted);
		assertFalse( isFound(sut, expDao.read(MANITAS_ID)) );
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		expDao.create(MANITAS_ID, sut);
		String updatedSut = "UPDATED " + sut;
		expDao.update(MANITAS_ID, sut, updatedSut);
		assertTrue( isFound(updatedSut, expDao.read(MANITAS_ID)) );
		sut = updatedSut; // to delete the created row after test (@After)
	}
	
	
	
	private boolean isFound(String target, List<String> source) {
		boolean isFound = false;
		for (String candidate : source) {
			if (candidate.equals(target)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}

}
