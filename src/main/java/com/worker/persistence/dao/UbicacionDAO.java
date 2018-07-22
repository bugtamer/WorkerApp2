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
	
	public List<Map<String, Object>> getServicios(String terminoBusqueda, int valoracionMedia, double distancia, double latitud, double longitud) throws SQLException {
		List<Map<String, Object>> listaResultadosBusqueda = new ArrayList<Map<String, Object>>();
		StringBuilder query = new StringBuilder();
		query.append("SELECT usu.nombre, usu.apellidos, usu.url_avatar,");
		query.append(" man.profesion, man.fk_usu AS 'proId',");
		query.append(" Round(Avg(val.puntuacion)) AS 'valoracionMedia',");
		query.append(" calcDistanciaEnKm("+latitud+", "+longitud+", ubi.latitud, ubi.longitud) AS 'distancia' ");
		query.append("FROM manitas man");
		query.append(" LEFT JOIN usuario usu ON (usu.usu_id = man.fk_usu)");
		query.append(" LEFT JOIN ubicacion ubi ON (ubi_id = fk_ubi)");
		query.append(" LEFT JOIN valoracion val ON (val.receptor_fk_usu = man.fk_usu) ");
		query.append("GROUP BY usu.nombre ");
		query.append("HAVING (distancia <= "+distancia+")");
		query.append(" AND (valoracionMedia >= "+valoracionMedia+")");
		query.append(" AND (man.profesion LIKE '%"+terminoBusqueda+"%')");
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query.toString());
		System.out.println("QUERY = " + query.toString());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Map<String, Object> resultadoBusqueda = new HashMap<>();
			resultadoBusqueda.put("proId", rs.getInt("proId"));
			resultadoBusqueda.put("nombre", rs.getString("nombre"));
			resultadoBusqueda.put("apellidos", rs.getString("apellidos"));
			resultadoBusqueda.put("url_avatar", rs.getString("url_avatar"));
			resultadoBusqueda.put("profesion", rs.getString("profesion"));
			resultadoBusqueda.put("valoracionMedia", rs.getInt("valoracionMedia"));
			resultadoBusqueda.put("distancia", rs.getDouble("distancia"));
			listaResultadosBusqueda.add(resultadoBusqueda);
		}
		stmt.close();
		conn.close();
		return listaResultadosBusqueda;
	}
	
	
	
	public Ubicacion read(int id) throws SQLException {
		Ubicacion ubicacion = null;
		String query = "SELECT * FROM ubicacion WHERE ubi_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ubicacion = new Ubicacion();
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
		int rows = stmt.executeUpdate();
		stmt.close();
		conn.close();
		boolean isDeleted = (rows > 0);
		return isDeleted;
	}

}
