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

public class EducacionTest {
	
	private static EducacionDAO eduDao;
	private static final int MANITAS_ID = 2;
	private String sut; 
	
	
	@BeforeClass
	public static void db() {
		try {
			eduDao = EducacionDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() {
		sut = String.format("Educacion %d", System.currentTimeMillis());
	}
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		eduDao.delete(MANITAS_ID, sut);
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		eduDao.create(MANITAS_ID, sut);
		assertTrue( isFound(sut, eduDao.read(MANITAS_ID)) );
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		boolean isDeleted = false;
		eduDao.create(MANITAS_ID, sut);
		isDeleted = eduDao.delete(MANITAS_ID, sut);
		assertTrue(isDeleted);
		assertFalse( isFound(sut, eduDao.read(MANITAS_ID)) );
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		eduDao.create(MANITAS_ID, sut);
		String updatedSut = "UPDATED " + sut;
		eduDao.update(MANITAS_ID, sut, updatedSut);
		assertTrue( isFound(updatedSut, eduDao.read(MANITAS_ID)) );
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
