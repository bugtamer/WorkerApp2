package com.worker.persistence.dao.manitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.DaoHelper;



public class ProfesionDAO extends DAO {


	// ATTRIBUTES

	private static ProfesionDAO singletonProfesionDAO;



	// INSTANTIATION

	private ProfesionDAO() throws Exception {
		super();
	}


	public static ProfesionDAO getInstance() throws Exception {
		return (singletonProfesionDAO == null) ? new ProfesionDAO() : singletonProfesionDAO;
	}



	// SERVICES
	
	public String read(int id) throws SQLException {
		String profesion = "";
		String query = "SELECT * FROM manitas WHERE fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			profesion = rs.getString("profesion");
		}
		stmt.close();
		conn.close();
		return profesion;
	}
	
	
	
	public List<Map<String, Object>> read(String terminoBusqueda) throws SQLException {
		List<Map<String, Object>> profesiones = new ArrayList<>();
		String query = "SELECT * FROM manitas WHERE Lower(profesion) LIKE '%" +terminoBusqueda.toLowerCase()+ "%'";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> manitas = new HashMap<>();
			manitas.put("fk_usu", rs.getInt("fk_usu"));
			manitas.put("profesion", rs.getString("profesion"));
			profesiones.add(manitas);
		}
		stmt.close();
		conn.close();
		return profesiones;
	}
	
	
	
	public int update(int manitasID, String profesion) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE manitas SET profesion = ? WHERE fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, profesion);
		stmt.setInt   (2, manitasID);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(int manitasID, String profesion) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO manitas (fk_usu, profesion) VALUES (?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, manitasID);
		stmt.setString(2, profesion);
		stmt.executeUpdate();
		index = DaoHelper.getIndex(stmt);
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM manitas WHERE fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		return true;
	}

}
