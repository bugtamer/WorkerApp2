package com.worker.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import com.worker.models.Manitas;



public class ManitasDAO {
	

		private static ManitasDAO instance=null;
		private DataSource datasource;
		
		public static final ManitasDAO getInstance() throws Exception {
			if(instance==null) instance=new ManitasDAO();
			return instance;
		}
		
		private ManitasDAO() throws Exception {
			
		}
		
		
		public List<Manitas> getLista() {
			List<Manitas> resultado =new ArrayList<Manitas>();
			try {
				Connection conn = datasource.getConnection();
				Statement stmt=conn.createStatement();
			 	
				ResultSet rs=stmt.executeQuery("SELECT m.Name, m.Code FROM manitas m ");
			 	
		

						
					
	
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			} 
			return resultado;
		}

}
