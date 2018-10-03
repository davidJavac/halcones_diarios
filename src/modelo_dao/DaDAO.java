package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;

public class DaDAO {

	
	private static final String sql_buscarDA_porNpedido = "select * from descuentos_administrativos"
			+ " where n_pedido = ?";
	private static final String sql_insertDA = "insert into descuentos_administrativos (n_pedido, monto)"
			+ " values(?,?)";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<DAVO> buscarDA_porNpedido (int n_pedido)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<DAVO> ar = new ArrayList<DAVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarDA_porNpedido);
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
				DAVO dVO = new DAVO();
				
				dVO.setNdescuento(res.getInt(1));
				dVO.setNpedido(res.getInt(2));
				dVO.setMonto(res.getDouble(3));
				
				ar.add(dVO);
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
	
	public int insertDA(DAVO dVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertDA);
			
			sentencia.setInt(1, dVO.getNpedido());
			sentencia.setDouble(2, dVO.getMonto());
			
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
