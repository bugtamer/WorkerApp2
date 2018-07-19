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
import com.worker.util.Timestamp;



public class ValoracionDAO extends DAO {


	// ATTRIBUTES

	public static final String VALORACION_ID = "val_id";
	public static final String COMENTARIO = "comentario";
	public static final String PUNTUACION = "puntuacion";
	public static final String TIMESTAMP = "timestamp";
	public static final String AUTOR_ID = "autor_usu_id";
	public static final String RECEPTOR_ID = "receptor_fk_usu";
	
	private static ValoracionDAO singletonManitasValDAO;



	// INSTANTIATION

	private ValoracionDAO() throws Exception {
		super();
	}


	public static ValoracionDAO getInstance() throws Exception {
		return (singletonManitasValDAO == null) ? new ValoracionDAO() : singletonManitasValDAO;
	}



	// SERVICES
	
	public Map<String, Object> read(int valId) throws SQLException {
		Map<String, Object> valoracion = new HashMap<>();
		String query = "SELECT * FROM valoracion WHERE val_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, valId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			valoracion = parseToData(rs);
		}
		stmt.close();
		conn.close();
		return valoracion;
	}
	
	
	
	public List<Map<String, Object>> readHechas(int id) throws SQLException {
		List<Map<String, Object>> valoraciones = new ArrayList<>();
		String query = "SELECT * FROM valoracion WHERE autor_usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			valoraciones.add( parseToData(rs) );
		}
		stmt.close();
		conn.close();
		return valoraciones;
	}
	
	
	
	public List<Map<String, Object>> readRecibidas(int receptorId) throws SQLException {
		List<Map<String, Object>> valoraciones = new ArrayList<>();
		String query = "SELECT * FROM valoracion WHERE receptor_fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, receptorId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			valoraciones.add( parseToData(rs) );
		}
		stmt.close();
		conn.close();
		return valoraciones;
	}	
	
	
	public int avg(int id) throws SQLException {
		int avg = -1;
		String query = "SELECT Round(Avg(puntuacion)) AS avg FROM valoracion WHERE receptor_fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			avg = rs.getInt("avg");
		}
		stmt.close();
		conn.close();
		return avg;
	}
	
	
	
	public int update(int val_id, String comentario, int puntuacion) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE valoracion SET comentario=?, puntuacion=?, timestamp=? WHERE val_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, comentario);
		stmt.setInt   (2, puntuacion);
		stmt.setString(3, Timestamp.getCurrentTime());
		stmt.setInt   (4, val_id);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int create(String comentario, int puntuacion, int autor_usu_id, int receptor_fk_usu) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO valoracion (comentario, puntuacion, timestamp, autor_usu_id, receptor_fk_usu) VALUES (?, ?, ?, ?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, comentario);
		stmt.setInt   (2, puntuacion);
		stmt.setString(3, Timestamp.getCurrentTime());
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
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}
	
	
	
	public boolean deleteAll(int id) throws SQLException {
		String query = "DELETE FROM valoracion WHERE receptor_fk_usu = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		System.out.println("delete query=" + stmt.toString());
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}
	
	
	
	// DETALLES DE IMPLEMENTACION DE MAS BAJO NIVEL
	
	private Map<String, Object> parseToData(ResultSet rs) throws SQLException {
		Map<String, Object> valoracion = new HashMap<String, Object>();
		valoracion.put(VALORACION_ID, rs.getInt("val_id"));
		valoracion.put(COMENTARIO, rs.getString("comentario"));
		valoracion.put(PUNTUACION, rs.getInt("puntuacion"));
		valoracion.put(TIMESTAMP, Timestamp.trimDateFromDB(rs.getString("timestamp")));
		valoracion.put(AUTOR_ID, rs.getInt("autor_usu_id"));
		valoracion.put(RECEPTOR_ID, rs.getInt("receptor_fk_usu"));
		return valoracion;
	}
	
}
