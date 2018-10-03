package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Caja_internaVO;
import modelo_vo.SueldosVO;

public class Caja_internaDAO {

	private static final String sql_select_buscarCaja_interna_porFecha = "select * from caja_interna"
			+ " where fecha_registro = ?";
	private static final String sql_insertCaja_interna = "insert into caja_interna(id_motivo, detalle, "
			+ "monto_ingreso, monto_egreso,  "
			+ "id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?, ?)";
	private static final String sql_bajaInterna = "delete from caja_interna where numero = ?";
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Caja_internaVO> buscarCaja_interna_porFecha(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Caja_internaVO> ar = new ArrayList<Caja_internaVO>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_interna_porFecha);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Caja_internaVO cVO = new Caja_internaVO();
				
				existe = true;
				
				cVO.setNumero(res.getInt(1));
				cVO.setId_motivo(res.getShort(2));
				cVO.setDetalle(res.getString(3));
				cVO.setMonto_ingreso(res.getDouble(4));
				cVO.setMonto_egreso(res.getDouble(5));
				cVO.setId_usuario(res.getShort(6));
				cVO.setFecha_registro(res.getDate(7));
				cVO.setHora_registro(res.getTime(8));
				
				ar.add(cVO);
		
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
			System.out.println("exite pedido");	
			return ar;
		}
		else return null;
	}
	
	public int eliminarInterna(int numero) throws SQLException{
		
		PreparedStatement sentencia = null;

		ResultSet res;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_bajaInterna);
			sentencia.setInt(1, numero);
			
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
	
	
	public int ingresos(Caja_internaVO cVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja_interna);
			sentencia.setShort(1, cVO.getId_motivo());
			sentencia.setString(2, cVO.getDetalle());
			sentencia.setDouble(3, cVO.getMonto_ingreso());
			sentencia.setDouble(4, cVO.getMonto_egreso());
			sentencia.setShort(5, cVO.getId_usuario());
			sentencia.setDate(6, cVO.getFecha_registro());
			sentencia.setTime(7, cVO.getHora_registro());
			
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
