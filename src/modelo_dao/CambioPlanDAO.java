package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.DAVO;
import modelo_vo.RetirosVO;

public class CambioPlanDAO {

	private static final String sql_buscar_cambios_entreFechas = "select * from cambio_plan where "
			+ "(fecha_registro between ? and ?)";
	private static final String spNuevo_retiro = "{call nuevo_retiro(?,?,?,?,?,?,?,?)}";
	private static final String sql_deleteCP = "delete from cambio_plan where n_cambio = ?";
	private static final String spActualizacion_cambioPlan = "{call nuevo_plan("
			+ "?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Cambio_planVO> buscarCambio_entreFechas(java.sql.Date desde, 
			java.sql.Date hasta) throws SQLException{
			
		ArrayList<Cambio_planVO> ar = new ArrayList<Cambio_planVO>();
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_cambios_entreFechas);
			
			sentencia.setDate(1, desde);
			sentencia.setDate(2, hasta);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Cambio_planVO _cambio_planVO = new Cambio_planVO();
				
				existe = true;
				
				_cambio_planVO.setN_cambio(res.getInt(1));
				_cambio_planVO.setN_pedido(res.getInt(2));
				_cambio_planVO.setId_vendedor(res.getShort(3));
				_cambio_planVO.setPlan_anterior(res.getString(4));
				_cambio_planVO.setPlan_posterior(res.getString(5));
				_cambio_planVO.setCredito_anterior(res.getDouble(6));
				_cambio_planVO.setCredito_posterior(res.getDouble(7));
				_cambio_planVO.setId_usuario(res.getShort(8));
				_cambio_planVO.setFecha_registro(res.getDate(9));
				_cambio_planVO.setHora_registro(res.getTime(10));
				
				ar.add(_cambio_planVO);
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
			System.out.println("exite pedido");	
			return ar;
		}
		else return null;
	}
		
	public int deleteCambio_plan(Cambio_planVO cVO) throws SQLException{
		
		java.sql.PreparedStatement stmt = null;
		
		ResultSet res;
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareStatement(sql_deleteCP);
			stmt.setInt(1, cVO.getN_cambio());
			
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();	
		
	}
	public int insertNuevo_Retiro(RetirosVO rVO, String estado) throws SQLException{
		
		java.sql.CallableStatement stmt = null;

		ResultSet res;
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spNuevo_retiro);
			stmt.setInt(1, rVO.getN_pedido());
			stmt.setInt(2, rVO.getCodigo());
			stmt.setInt(3, rVO.getId_vendedor());
			stmt.setString(4, rVO.getObservaciones());
			stmt.setInt(5, rVO.getId_usuario());
			stmt.setDate(6, rVO.getFecha_registro());
			stmt.setTime(7, rVO.getHora_registro());
			stmt.setString(8, estado);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		
	
		return stmt.executeUpdate();	
		
	}
	
	public int establecerNuevoPlan(Cambio_planVO cVO, int dias, double cuota, DAVO dVO)throws SQLException{
		
		java.sql.CallableStatement stmt = null;

		ResultSet res;
		
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spActualizacion_cambioPlan);
		
			stmt.setInt(1, dias);
			stmt.setDouble(2, cuota);
			
			stmt.setInt(3, cVO.getN_pedido());
			stmt.setInt(4, cVO.getId_vendedor());
			stmt.setString(5, cVO.getPlan_anterior());
			stmt.setString(6, cVO.getPlan_posterior());
			stmt.setDouble(7, cVO.getCredito_anterior());
			stmt.setDouble(8, cVO.getCredito_posterior());
			stmt.setInt(9, cVO.getId_usuario());
			stmt.setDate(10, cVO.getFecha_registro());
			stmt.setTime(11, cVO.getHora_registro());
			
			stmt.setInt(12, dVO.getNpedido());
			stmt.setDouble(13, dVO.getMonto());
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
	
		return stmt.executeUpdate();	
		
	}
}
