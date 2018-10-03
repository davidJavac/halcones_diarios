package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.VerazVO;

public class Refinanciacion_exDAO {
	
	private static final String sql_anularRef = "update refinanciacion_ex set estado = ? where n_pedido = ?";
	private static final String sql_modificarRef = "update refinanciacion_ex set dias = ?, cuota_diaria = ?,"
			+ " estado = ? where n_pedido = ?";
	
	private static final String sql_buscar_ref = "select * from refinanciacion_ex where n_pedido = ? and"
			+ " estado <> ?";
		
	private static final String sql_insertarref = "insert into refinanciacion_ex("
			+ "n_pedido, dias, cuota_diaria, estado) values(?, ?, ?,?)";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public Refinanciacion_exVO buscarRef(int n_pedido) throws SQLException{
		
		Refinanciacion_exVO _refVO = new Refinanciacion_exVO();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_ref);
		
			sentencia.setInt(1, n_pedido);
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_refVO.setN_pedido(res.getInt(1));
				_refVO.setDias(res.getShort(2));
				_refVO.setCuota_diaria(res.getDouble(3));
				_refVO.setEstado(res.getBoolean(4));
				
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
			
			return _refVO;
		}
		else return null;
	}
	
	public int anularRef(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_anularRef);
			sentencia.setBoolean(1, false);
			sentencia.setInt(2, n_pedido);
			
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
	
	public int modificarRef(Refinanciacion_exVO _refVO) throws SQLException{
		
		PreparedStatement sentencia = null;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_modificarRef);
		
			sentencia.setInt(1, _refVO.getDias());
			sentencia.setDouble(2, _refVO.getCuota_diaria());
			sentencia.setBoolean(3, true);
			sentencia.setInt(4, _refVO.getN_pedido());
			
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
	
	public int insertarNuevaRef(Refinanciacion_exVO _refVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertarref);
			sentencia.setInt(1, _refVO.getN_pedido());
			sentencia.setShort(2, _refVO.getDias());
			sentencia.setDouble(3, _refVO.getCuota_diaria());
			sentencia.setBoolean(4, true);
			
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
