package com.worker.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.worker.models.Ubicacion;



public class UbicacionDAO extends DAO {


	// ATTRIBUTES

	private static UbicacionDAO singletonUbicacionDAO;



	// INSTANTIATION

	private UbicacionDAO() throws Exception {
		super();
	}


	public static UbicacionDAO getInstance() throws Exception {
		return (singletonUbicacionDAO == null) ? new UbicacionDAO() : singletonUbicacionDAO;
	}



	// SERVICES
	
	public Ubicacion read(int id) throws SQLException {
		Ubicacion ubicacion = new Ubicacion();
		String query = "SELECT * FROM ubicacion WHERE ubi_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ubicacion.setId( rs.getInt("ubi_id") );
			ubicacion.setLatitud( rs.getDouble("latitud") );
			ubicacion.setLongitud( rs.getDouble("longitud") );
		}
		stmt.close();
		conn.close();
		return ubicacion;
	}
	
	
	
	public int update(int ubi_id, double latitud, double longitud) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE ubicacion SET latitud=?, longitud=? WHERE ubi_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setDouble(1, latitud);
		stmt.setDouble(2, longitud);
		stmt.setInt   (3, ubi_id);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(double latitud, double longitud) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO ubicacion (latitud, longitud) VALUES (?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setDouble(1, latitud);
		stmt.setDouble(2, longitud);
		stmt.executeUpdate();
		index = DaoHelper.getIndex(stmt);
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM ubicacion WHERE ubi_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		return true;
	}

}
