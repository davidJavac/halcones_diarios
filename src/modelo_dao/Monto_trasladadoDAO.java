package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_inVO;

public class Monto_trasladadoDAO {

	private static final String sql_updatePedidosMonto = "update pedidos set monto_trasladado = ? where n_pedido = ?";
	
	private static final String sql_buscar_pedidos = "select * from monto_trasladado where n_pedido_origen = ? or"
			+ " n_pedido_destino = ?";
	private static final String sql_buscar_pedidosComprobacion = "select * from monto_trasladado where n_pedido_origen = ?"
			+ " and n_pedido_destino = ?";
	
	private static final String sql_buscar_monto_destino = "select * from monto_trasladado where n_pedido_destino = ?";
	private static final String sql_buscar_monto_origen = "select * from monto_trasladado where n_pedido_origen = ?";
			
	private static final String sql_insertarmonto = "insert into monto_trasladado("
			+ "n_pedido_origen, n_pedido_destino, monto, observaciones, estado, id_usuario, fecha, hora)"
			+ " values(?, ?, ?,?,?, ?, ?, ?)";
	
	private static final String sql_baja_monto = "delete from monto_trasladado  where n_pedido_origen = ? "
			+ "and n_pedido_destino = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	

	public boolean buscarMonto(Monto_trasladadoVO mtVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_pedidosComprobacion);
		
			sentencia.setInt(1, mtVO.getN_pedido_origen());
			sentencia.setInt(2, mtVO.getN_pedido_destino());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
			}
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		if(existe){
			
			return true;
		}
		else return false;
	}
	
	
	public ArrayList<Monto_trasladadoVO> buscarMontoDestino(int n_pedido) throws SQLException{
		
		ArrayList<Monto_trasladadoVO> ar = new ArrayList<Monto_trasladadoVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_monto_destino);
		
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Monto_trasladadoVO mtVO = new Monto_trasladadoVO();
				
				mtVO.setN_pedido_origen(res.getInt(1));
				mtVO.setN_pedido_destino(res.getInt(2));
				mtVO.setMonto(res.getDouble(3));
				mtVO.setObservaciones(res.getString(4));
				mtVO.setEstado(res.getString(5));
				mtVO.setId_usuario(res.getShort(6));
				mtVO.setFecha(res.getDate(7));
				mtVO.setHora(res.getTime(8));
				
				ar.add(mtVO);
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
			
			return ar;
		}
		else return null;
	}
	
	public ArrayList<Monto_trasladadoVO> buscarMontoOrigen(int n_pedido) throws SQLException{
		
		ArrayList<Monto_trasladadoVO> ar = new ArrayList<Monto_trasladadoVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_monto_origen);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Monto_trasladadoVO mtVO = new Monto_trasladadoVO();
				
				mtVO.setN_pedido_origen(res.getInt(1));
				mtVO.setN_pedido_destino(res.getInt(2));
				mtVO.setMonto(res.getDouble(3));
				mtVO.setObservaciones(res.getString(4));
				mtVO.setEstado(res.getString(5));
				mtVO.setId_usuario(res.getShort(6));
				mtVO.setFecha(res.getDate(7));
				mtVO.setHora(res.getTime(8));
				
				ar.add(mtVO);
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
			
			return ar;
		}
		else return null;
	}

	public ArrayList<Monto_trasladadoVO> buscar_pedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		ArrayList<Monto_trasladadoVO> ar = new ArrayList<Monto_trasladadoVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_pedidos);
		
			sentencia.setInt(1, n_pedido);
			sentencia.setInt(2, n_pedido);
			//sentencia.setString(3, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Monto_trasladadoVO mt = new Monto_trasladadoVO();
				
				mt.setN_pedido_origen(res.getInt(1));
				mt.setN_pedido_destino(res.getInt(2));
				mt.setMonto(res.getDouble(3));
				mt.setObservaciones(res.getString(4));
				//.setEstado(res.getString(5));
				mt.setId_usuario(res.getShort(6));
				mt.setFecha(res.getDate(7));
				mt.setHora(res.getTime(8));
				
				ar.add(mt);
				sqlConexion.commit();
			}	

			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		if(existe) 	return ar;
		else return null;
	}
	
	
	public int updatePedidosMonto(int n_pedido, boolean estado) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_updatePedidosMonto);
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updatePedidosMonto);
			sentencia.setBoolean(1, estado);
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
	
	public int anularMonto(Monto_trasladadoVO mt) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_baja_monto);
	
			sentencia.setInt(1, mt.getN_pedido_origen());
			sentencia.setInt(2, mt.getN_pedido_destino());
			
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
	
	public int insertarNuevoMt(Monto_trasladadoVO mtVO) throws SQLException{
		
		PreparedStatement sentencia = null;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_insertarmonto);
			sentencia.setInt(1, mtVO.getN_pedido_origen());
			sentencia.setInt(2, mtVO.getN_pedido_destino());
			sentencia.setDouble(3, mtVO.getMonto());
			sentencia.setString(4, mtVO.getObservaciones());
			sentencia.setString(5, mtVO.getEstado());
			sentencia.setShort(6, mtVO.getId_usuario());
			sentencia.setDate(7, mtVO.getFecha());
			sentencia.setTime(8, mtVO.getHora());
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
