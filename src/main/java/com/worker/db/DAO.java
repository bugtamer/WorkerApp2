package com.worker.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	
	protected static String url=null;
	private DataSource datasource;
	
	protected DAO() throws Exception {
	
	Context initContext = new InitialContext();
	Context envContext = (Context) initContext.lookup("java:/comp/env"); 
	datasource = (DataSource)envContext.lookup("jdbc/TestDB");

}
}

