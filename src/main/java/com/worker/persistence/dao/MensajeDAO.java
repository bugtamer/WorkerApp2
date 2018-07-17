package com.worker.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class MensajeDAO extends DAO {


	// ATTRIBUTES

	private static MensajeDAO singletonMensajeDAO;



	// INSTANTIATION

	private MensajeDAO() throws Exception {
		super();
	}


	public static MensajeDAO getInstance() throws Exception {
		return (singletonMensajeDAO == null) ? new MensajeDAO() : singletonMensajeDAO;
	}



	// SERVICES
	
	public Map<String, Object> read(int id) throws SQLException {//
		Map<String, Object> mensaje = new HashMap<>();
		String query = "SELECT * FROM mensaje WHERE men_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println("QUERY >>> " + query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			mensaje.put("men_id", rs.getInt("men_id") );
			mensaje.put("texto", rs.getString("texto") );
			mensaje.put("timestamp", rs.getDate("timestamp") );
			mensaje.put("urlImagen", rs.getString("urlImagen") );
			mensaje.put("emisor_usu_id", rs.getInt("emisor_usu_id") );
			mensaje.put("receptor_usu_id", rs.getInt("receptor_usu_id") );
		}
		stmt.close();
		conn.close();
		System.out.println("read >> " + mensaje);
		return mensaje;
	}
	
	
	
	public int update(int men_id, String texto, Date timestamp, String urlImagen, int emisor_usu_id, int receptor_usu_id) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE mensaje SET texto=?, timestamp=?, urlImagen=?, emisor_usu_id=?, receptor_usu_id=? WHERE men_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, texto);
		stmt.setDate  (2, new java.sql.Date(timestamp.getTime())); // XXX java.sql.Date vs java.util.Date 
		stmt.setString(3, urlImagen);
		stmt.setInt   (4, emisor_usu_id);
		stmt.setInt   (5, receptor_usu_id);
		stmt.setInt   (6, men_id);
		System.out.println("QUERY >>> " + query);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(String texto, Date timestamp, String urlImagen, int emisor_usu_id, int receptor_usu_id) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO mensaje (texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id) VALUES (?, ?, ?, ?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, texto);
		stmt.setDate  (2, new java.sql.Date(timestamp.getTime())); // XXX java.sql.Date vs java.util.Date 
		stmt.setString(3, urlImagen);
		stmt.setInt   (4, emisor_usu_id);
		stmt.setInt   (5, receptor_usu_id);
		System.out.println("QUERY >>> " + query);
		stmt.executeUpdate();
		index = DaoHelper.getIndex(stmt);
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM mensaje WHERE men_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println("QUERY >>> " + query);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		return true;
	}

}
