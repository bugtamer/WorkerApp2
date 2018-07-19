package com.worker.persistence.dao.manitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.worker.persistence.dao.DAO;



public class EducacionDAO extends DAO {


	// ATTRIBUTES

	private static EducacionDAO singletonManitasEduDAO;



	// INSTANTIATION

	private EducacionDAO() throws Exception {
		super();
	}


	public static EducacionDAO getInstance() throws Exception {
		return (singletonManitasEduDAO == null) ? new EducacionDAO() : singletonManitasEduDAO;
	}



	// SERVICES
	
	public List<String> read(int id) throws SQLException {
		List<String> educacion = new ArrayList<>();
		String query = "SELECT * FROM educacion WHERE edu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			educacion.add( rs.getString("educacion") );
		}
		stmt.close();
		conn.close();
		return educacion;
	}
	
	
	
	public int update(int manitasID, String old_educacion, String new_educacion) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE educacion SET educacion = ? WHERE (edu_id = ?) AND (educacion = ?)";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, new_educacion);
		stmt.setInt   (2, manitasID);
		stmt.setString(3, old_educacion);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(int manitasID, String educacion) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO educacion (edu_id, educacion) VALUES (?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, manitasID);
		stmt.setString(2, educacion);
		stmt.executeUpdate();
		index = manitasID; // no tiene su propio ID, utiliza el de manitas
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id, String educacion) throws SQLException {
		String query = "DELETE FROM educacion WHERE (edu_id = ?) AND (educacion = ?)";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, id);
		stmt.setString(2, educacion);
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}
	
	
	
	public boolean deleteAll(int id) throws SQLException {
		String query = "DELETE FROM educacion WHERE edu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, id);
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}

}
