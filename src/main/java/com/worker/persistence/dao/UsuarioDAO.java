package com.worker.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.worker.models.Usuario;



public class UsuarioDAO extends DAO {


	// ATTRIBUTES

	private static UsuarioDAO singletonUsuarioDAO;



	// INSTANTIATION

	private UsuarioDAO() throws Exception {
		super();
	}


	public static UsuarioDAO getInstance() throws Exception {
		return (singletonUsuarioDAO == null) ? new UsuarioDAO() : singletonUsuarioDAO;
	}



	// SERVICES
	
	public Map<String, Object> read(int usu_id) throws SQLException {
		Map<String, Object> usuario = new HashMap<>();
		String query = "SELECT * FROM usuario WHERE usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt   (1, usu_id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			usuario.put("usu_id", rs.getInt("usu_id"));
			usuario.put("nombre", rs.getString("nombre") );
			usuario.put("apellidos", rs.getString("apellidos") );
			usuario.put("email", rs.getString("email") );
			usuario.put("url_avatar", rs.getString("url_avatar") );
			usuario.put("ubi_id", rs.getInt("fk_ubi") );
		}
		stmt.close();
		conn.close();
		return usuario;
	}
	
	
	
	public int updatePassword(int usu_id, String password) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE usuario SET password = ? WHERE usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, password);
		stmt.setInt   (2, usu_id);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
	
	public int update(int usu_id, String nombre, String apellidos, String email, String url_avatar, int fk_ubi) throws SQLException {
		int rowCount = 0;
		String query = "UPDATE usuario SET apellidos=?, url_avatar=?, email=?, nombre=?, fk_ubi=? WHERE usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, apellidos);
		stmt.setString(2, url_avatar);
		stmt.setString(3, email);
		stmt.setString(4, nombre);
		stmt.setInt   (5, fk_ubi);
		stmt.setInt   (6, usu_id);
		rowCount = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return rowCount;
	}
	
	
//	public int update2(Usuario usuario) throws SQLException {
//		int rowCount = 0;
//		String query = "UPDATE usuario SET apellidos=?, url_avatar=?, email=?, nombre=?, fk_ubi=? WHERE usu_id = ?";
//		Connection conn = DriverManager.getConnection(URL);
//		PreparedStatement stmt = conn.prepareStatement(query);
//		stmt.setString(usuario.getNombre(nom));
//		stmt.setString(2, url_avatar);
//		stmt.setString(3, email);
//		stmt.setString(4, nombre);
//		stmt.setInt   (5, fk_ubi);
//		stmt.setInt   (6, usu_id);
//		rowCount = stmt.executeUpdate();
//		stmt.close();
//		conn.close();
//		return rowCount;
//	}
	
	
	
	public int create(String nombre, String apellidos, String email, String password, String url_avatar, int fk_ubi) throws SQLException {
		int index = DAO.NO_ID;
		String query = "INSERT INTO usuario (apellidos, url_avatar, email, nombre, password, fk_ubi) VALUES (?, ?, ?, ?, ?, ?);";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, apellidos);
		stmt.setString(2, url_avatar);
		stmt.setString(3, email);
		stmt.setString(4, nombre);
		stmt.setString(5, password);
		stmt.setInt   (6, fk_ubi);
		stmt.executeUpdate();
		index = DaoHelper.getIndex(stmt);
		stmt.close();
		conn.close();
		return index;
	}
	
	
	
	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM usuario WHERE usu_id = ?";
		Connection conn = DriverManager.getConnection(URL);
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
		conn.close();
		return true;
	}


	public int update(Usuario unUsuario) {
		// TODO Auto-generated method stub
		return 0;
	}

}
