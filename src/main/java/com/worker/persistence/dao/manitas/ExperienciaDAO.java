package com.worker.persistence.dao.manitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.worker.persistence.dao.DAO;



public class ExperienciaDAO extends DAO {


	// ATTRIBUTES

	private static ExperienciaDAO singletonManitasExpDAO;



	// INSTANTIATION

	private ExperienciaDAO() throws Exception {
		super();
	}


	public static ExperienciaDAO getInstance() throws Exception {
		return (singletonManitasExpDAO == null) ? new ExperienciaDAO() : singletonManitasExpDAO;
	}



	// SERVICES
	
	public List<String> read(int id) throws SQLException {
		List<String> experiencia = new ArrayList<>();
		String query = "SELECT * FROM experiencia WHERE exp_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			experiencia.add( rs.getString("experiencia") );
		}
		stmt.close();
		conn.close();
		return experiencia;
	}
	
	
	
	public int update(int manitasID, String old_experiencia, String new_experiencia) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE experiencia SET experiencia = ? WHERE (exp_id = ?) AND (experiencia = ?)";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, new_experiencia);
		stmt.setInt   (2, manitasID);
		stmt.setString(3, old_experiencia);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(int manitasID, String experiencia) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO experiencia (exp_id, experiencia) VALUES (?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, manitasID);
		stmt.setString(2, experiencia);
		stmt.executeUpdate();
		index = manitasID; // no tiene su propio ID, utiliza el de manitas
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id, String experiencia) throws SQLException {
		String query = "DELETE FROM experiencia WHERE (exp_id = ?) AND (experiencia = ?)";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, id);
		stmt.setString(2, experiencia);
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}
	
	
	
	public boolean deleteAll(int id) throws SQLException {
		String query = "DELETE FROM experiencia WHERE exp_id = ?";
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
