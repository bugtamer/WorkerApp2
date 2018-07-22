package com.worker.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.worker.util.Timestamp;



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
			mensaje.put("timestamp", Timestamp.trimDateFromDB(rs.getString("timestamp")) );
			mensaje.put("urlImagen", rs.getString("urlImagen") );
			mensaje.put("emisor_usu_id", rs.getInt("emisor_usu_id") );
			mensaje.put("receptor_usu_id", rs.getInt("receptor_usu_id") );
		}
		stmt.close();
		conn.close();
		System.out.println("read >> " + mensaje);
		return mensaje;
	}
	
	
	
	public List<Map<String, Object>> getConversacion(int emisorId, int receptorId, int from, int limit) throws SQLException {
		List<Map<String, Object>> conversacion = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT * ");
		query.append("FROM mensaje ");
		query.append("WHERE ((emisor_usu_id = ?) AND (receptor_usu_id = ?))");
		query.append("   OR ((emisor_usu_id = ?) AND (receptor_usu_id = ?))");
		query.append("   LIMIT ? OFFSET ?");
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement( query.toString() );
		stmt.setInt(1, emisorId);
		stmt.setInt(2, receptorId);
		stmt.setInt(3, from);
		stmt.setInt(4, limit);
		System.out.println("QUERY >>> " + query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> mensaje = new HashMap<>();
			mensaje.put("men_id", rs.getInt("men_id") );
			mensaje.put("texto", rs.getString("texto") );
			mensaje.put("timestamp", Timestamp.trimDateFromDB(rs.getString("timestamp")) );
			mensaje.put("urlImagen", rs.getString("urlImagen") );
			mensaje.put("emisor_usu_id", rs.getInt("emisor_usu_id") );
			mensaje.put("receptor_usu_id", rs.getInt("receptor_usu_id") );
			conversacion.add(mensaje);
		}
		stmt.close();
		conn.close();
		System.out.println("conversacion >> " + conversacion);
		return conversacion;
	}
	
	
	
	public List<Map<String, Object>> getListaConversaciones(int id) throws SQLException {
		List<Map<String, Object>> conversacion = new ArrayList<>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT emisor_usu_id, receptor_usu_id, concat(u.nombre, ' ',  u.apellidos) AS emisor ");
		query.append("FROM mensaje m LEFT JOIN usuario u ON (m.receptor_usu_id = u.usu_id) ");
		query.append("GROUP BY emisor_usu_id, receptor_usu_id ");
		query.append("HAVING (emisor_usu_id = "+id+")");
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement( query.toString() );
		System.out.println("QUERY >>> " + query);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> mensaje = new HashMap<>();
			mensaje.put("emisor_usu_id", rs.getInt("emisor_usu_id") );
			mensaje.put("receptor_usu_id", rs.getString("receptor_usu_id") );
			mensaje.put("emisor", rs.getString("emisor") );
			conversacion.add(mensaje);
		}
		stmt.close();
		conn.close();
		System.out.println("conversacion >> " + conversacion);
		return conversacion;
	}

	
	
	public int update(int men_id, String texto, String urlImagen, int emisor_usu_id, int receptor_usu_id) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE mensaje SET texto=?, timestamp=?, urlImagen=?, emisor_usu_id=?, receptor_usu_id=? WHERE men_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, texto);
		stmt.setString(2, Timestamp.getCurrentTime()); 
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
	
	
	
	public int create(String texto, String urlImagen, int emisor_usu_id, int receptor_usu_id) throws SQLException {
		int index = DAO.NO_ID;
		String query = String.format("INSERT INTO mensaje (texto, timestamp, urlImagen, emisor_usu_id, receptor_usu_id) VALUES ('%s', '%s', '%s', %d, %d);",
				texto, Timestamp.getCurrentTime(), urlImagen, emisor_usu_id, receptor_usu_id);
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
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
