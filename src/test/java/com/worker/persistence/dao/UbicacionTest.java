package com.worker.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.worker.models.Ubicacion;

public class UbicacionTest {
	
	private static UbicacionDAO ubiDao;
	private static final double LAT = 41.3;
	private static final double LON = 2.4;
	private Ubicacion sut;
	private int id;
	
	
	@BeforeClass
	public static void db() {
		try {
			ubiDao = UbicacionDAO.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	
	
	@Before
	public void setData() { }
	
	
	
	@After
	public void deleteCreatedRows() throws SQLException {
		ubiDao.delete(id);
		sut = null;
		id = 0;
	}
	
	
	
	@Test
	public void createAndReadTest() throws SQLException {
		id = ubiDao.create(LAT, LON);
		sut = ubiDao.read(id);
		assertEquals(LAT, sut.getLatitud(), .01);
		assertEquals(LON, sut.getLongitud(), .01);
	}
	
	
	
	@Test
	public void createAndDeleteTest() throws SQLException {
		id = ubiDao.create(LAT, LON);
		boolean isDeleted = ubiDao.delete(id);
		assertTrue(isDeleted);
		sut = ubiDao.read(id);
		assertTrue(sut == null);
	}
	
	
	
	@Test
	public void createAndUpdateTest() throws SQLException {
		id = ubiDao.create(LAT, LON);
		ubiDao.update(id, 1, 1);
		sut = ubiDao.read(id);
		assertEquals(1, sut.getLatitud(), .1);
		assertEquals(1, sut.getLongitud(), .1);
	}

}
