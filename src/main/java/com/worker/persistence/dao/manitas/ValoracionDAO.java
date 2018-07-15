package com.worker.persistence.dao.manitas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.worker.persistence.dao.DAO;
import com.worker.persistence.dao.DaoHelper;



public class ValoracionDAO extends DAO {


	// ATTRIBUTES

	private static ValoracionDAO singletonManitasValDAO;



	// INSTANTIATION

	private ValoracionDAO() throws Exception {
		super();
	}


	public static ValoracionDAO getInstance() throws Exception {
		return (singletonManitasValDAO == null) ? new ValoracionDAO() : singletonManitasValDAO;
	}



	// SERVICES
	
	public List<Map<String, Object>> readHechas(int id) throws SQLException {
		List<Map<String, Object>> valoraciones = new ArrayList<>();
		String query = "SELECT * FROM valoracion WHERE autor_usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> valoracion = new HashMap<String, Object>();
			valoracion.put("val_id", rs.getInt("val_id"));
			valoracion.put("comentario", rs.getString("comentario"));
			valoracion.put("puntuacion", rs.getInt("puntuacion"));
			valoracion.put("timestamp", rs.getDate("timestamp"));
			valoracion.put("autor_usu_id", rs.getInt("autor_usu_id"));
			valoracion.put("receptor_fk_usu", rs.getInt("receptor_fk_usu"));
			valoraciones.add(valoracion);
		}
		stmt.close();
		conn.close();
		return valoraciones;
	}
	
	
	
	public List<Map<String, Object>> readRecibidas(int id) throws SQLException {
		List<Map<String, Object>> valoraciones = new ArrayList<>();
		String query = "SELECT * FROM valoracion WHERE receptor_fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> valoracion = new HashMap<String, Object>();
			valoracion.put("val_id", rs.getInt("val_id"));
			valoracion.put("comentario", rs.getString("comentario"));
			valoracion.put("puntuacion", rs.getInt("puntuacion"));
			valoracion.put("timestamp", rs.getDate("timestamp"));
			valoracion.put("autor_usu_id", rs.getInt("autor_usu_id"));
			valoracion.put("receptor_fk_usu", rs.getInt("receptor_fk_usu"));
			valoraciones.add(valoracion);
		}
		stmt.close();
		conn.close();
		return valoraciones;
	}
	
	
	
	public int update(int val_id, String comentario, int puntuacion, Date timestamp, int autor_usu_id, int receptor_fk_usu) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE valoracion SET comentario=?, puntuacion=?, timestamp=?, autor_usu_id=?, receptor_fk_usu=? WHERE val_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, comentario);
		stmt.setInt   (2, puntuacion);
		stmt.setDate  (3, new java.sql.Date( timestamp.getTime() )); // XXX java.sql.Date vs java.util.Date
		stmt.setInt   (4, autor_usu_id);
		stmt.setInt   (5, receptor_fk_usu);
		stmt.setInt   (6, val_id);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(String comentario, int puntuacion, Date timestamp, int autor_usu_id, int receptor_fk_usu) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO valoracion (comentario, puntuacion, timestamp, autor_usu_id, receptor_fk_usu) VALUES (?, ?, ?, ?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, comentario);
		stmt.setInt   (2, puntuacion);
		stmt.setDate  (3, new java.sql.Date( timestamp.getTime() )); // XXX java.sql.Date vs java.util.Date
		stmt.setInt   (4, autor_usu_id);
		stmt.setInt   (5, receptor_fk_usu);
		stmt.executeUpdate();
		index = DaoHelper.getIndex(stmt);
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM valoracion WHERE val_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println("delete query=" + stmt.toString());
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		return true;
	}
	
}
