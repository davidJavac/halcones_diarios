package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.SueldosVO;

public class SueldosDAO {

	private static final String sql_select_buscarSueldo_porFecha = "select * from sueldos where fecha_registro = ?";
	private static final String sql_select_buscarSueldo_entreFechas = "select * from sueldos "
			+ "where fecha_registro between ? and ?";
	private static final String sql_insertCaja_sueldo = "insert into sueldos(id_empleado, monto, concepto, "
			+ "id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?)";
	private static final String sql_bajaPago_sueldo = "delete from sueldos where codigo_sueldo = ?";
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<SueldosVO> buscarSueldo_entreFechas
			(java.sql.Date lunes, java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<SueldosVO> ar = new ArrayList<SueldosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarSueldo_entreFechas);
		
			sentencia.setDate(1, lunes);
			sentencia.setDate(2, fecha);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				SueldosVO sVO = new SueldosVO();
				
				existe = true;
				
				sVO.setCodigo_sueldo(res.getInt(1));
				sVO.setId_empleado(res.getShort(2));
				sVO.setMonto(res.getDouble(3));
				sVO.setConcepto(res.getString(4));
				sVO.setId_usuario(res.getShort(5));
				sVO.setFecha_registro(res.getDate(6));
				sVO.setHora_registro(res.getTime(7));
				
				ar.add(sVO);
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
	
	public ArrayList<SueldosVO> buscarSueldo_porFecha(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<SueldosVO> ar = new ArrayList<SueldosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarSueldo_porFecha);
		
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				SueldosVO sVO = new SueldosVO();
				
				existe = true;
				
				sVO.setCodigo_sueldo(res.getInt(1));
				sVO.setId_empleado(res.getShort(2));
				sVO.setMonto(res.getDouble(3));
				sVO.setConcepto(res.getString(4));
				sVO.setId_usuario(res.getShort(5));
				sVO.setFecha_registro(res.getDate(6));
				sVO.setHora_registro(res.getTime(7));
				
				ar.add(sVO);
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
	
	public int eliminarPago_sueldo(int codigo_sueldo) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_bajaPago_sueldo);
			sentencia.setInt(1, codigo_sueldo);
			
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
	
	
	public int ingresos(SueldosVO sVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja_sueldo);
		
			sentencia.setShort(1, sVO.getId_empleado());
			sentencia.setDouble(2, sVO.getMonto());
			sentencia.setString(3, sVO.getConcepto());
			sentencia.setShort(4, sVO.getId_usuario());
			sentencia.setDate(5, sVO.getFecha_registro());
			sentencia.setTime(6, sVO.getHora_registro());
			
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
