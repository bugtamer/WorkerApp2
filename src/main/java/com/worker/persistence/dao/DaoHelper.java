package com.worker.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelper {
	
	public static int getIndex(PreparedStatement st) throws SQLException {
		int index = DAO.NO_ID;
		ResultSet rs = st.getGeneratedKeys();
		while(rs.next()) {
			index = rs.getInt(1);
		}
		return index;
	}

}
