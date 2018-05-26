package com.worker.db;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 * @author Bugtamer
 *
 */
public class DataRetrieverTest {

	@Test
	public void testDataRetriever() throws Exception {
		String sut = DataRetriever.getDataFrom(DataSource.testOK);
		
		assertEquals(sut, "{\"test\":\"OK\"}");
	}

}
