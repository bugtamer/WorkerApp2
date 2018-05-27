package com.worker.util.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * 
 * @author Bugtamer
 *
 */
public class DataRetrieverTest {

	@Test
	public void testDataRetriever() throws Exception {
		String sut = JsonRetriever.getDataFrom(DataSource.testOK);
		
		assertEquals(sut, "{\"test\":\"OK\"}");
	}

}
