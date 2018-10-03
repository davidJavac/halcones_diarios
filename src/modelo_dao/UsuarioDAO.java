package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LogicaCliente;
import modelo.LogicaUsuario;
import modelo_conexion.Conexion;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.ContrasenasVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;

public class UsuarioDAO {

	private static final String sql_buscarusuario = "select * from usuarios where nombre = ? and "
			+ "cast(aes_decrypt(contrasena, ?)as char(50)) = ? "
			+ "and estado <> ?";
	private static final String sql_buscarContrasena = "select * from contrasenas where descripcion = ? and "
			+ "cast(aes_decrypt(contrasena, ?)as char(50)) = ?";
	
	private static final String sql_update_contrasena = "update usuarios set contrasena = aes_encrypt(?,?) where"
			+ " id_usuario = ?";
	private static final String sql_update_contrasena_in = "update contrasenas set contrasena = aes_encrypt(?,?)";
	
	private static final String sql_buscarusuario_porID = "select * from usuarios where id_usuario = ? "
			+ "and estado <> ?";
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public UsuariosVO buscarUsuario(UsuariosVO _usuarioVO, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		UsuariosVO _usVO = new UsuariosVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarusuario);
		
			sentencia.setString(1, _usuarioVO.getNombre());
			sentencia.setString(3, _usuarioVO.getContraseña());
			sentencia.setString(2, key);
			sentencia.setBoolean(4, false);
			
			System.out.println(_usuarioVO.getNombre() + " " + key + " " + _usuarioVO.getContraseña());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_usVO.setId_usuario(res.getShort(1));
				_usVO.setNombre(res.getString(2));
				_usVO.setContraseña(res.getString(3));
				_usVO.setPermiso(res.getShort(4));
				_usVO.setEstado(res.getBoolean(5));
				
				sqlConexion.commit();
			}
			
			System.out.println(_usVO.getNombre() + " " + _usVO.getContraseña());
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		if(existe){
			
			LogicaUsuario.validarBusqueda = true;
			return _usVO;
		}
		else{
			
			LogicaUsuario.validarBusqueda = false;
			
			return null;
		}
		
	}
	public boolean buscarContrasena(ContrasenasVO cVO, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarContrasena);
			
			sentencia.setString(1, cVO.getDescripcion());
			sentencia.setString(3, cVO.getContrasena());
			sentencia.setString(2, key);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				sqlConexion.commit();
			}
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return existe;
		
	}
	
	public UsuariosVO buscarUsuario_porID(short id_usuario) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		boolean existe = false;
		
		UsuariosVO _usVO = new UsuariosVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarusuario_porID);
		
			sentencia.setShort(1, id_usuario);
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_usVO.setId_usuario(res.getShort(1));
				_usVO.setNombre(res.getString(2));
				_usVO.setContraseña(res.getString(3));
				_usVO.setPermiso(res.getShort(4));
				_usVO.setEstado(res.getBoolean(5));
				sqlConexion.commit();
				
			}
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		if(existe){
			
			LogicaUsuario.validarBusqueda = true;
			return _usVO;
		}
		else{
			
			LogicaUsuario.validarBusqueda = false;
			
			return null;
		}
		
	}
	
	public int nueva_contrasena(UsuariosVO uVO,String contrasena, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_contrasena);
			
			sentencia.setString(1, contrasena);
			sentencia.setString(2, key);
			sentencia.setInt(3, uVO.getId_usuario());
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
	
		
		return sentencia.executeUpdate();
		
	}
	public int nueva_contrasena_interna(String contrasena, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_contrasena_in);
			
			sentencia.setString(1, contrasena);
			sentencia.setString(2, key);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		
		return sentencia.executeUpdate();
		
	}
}
