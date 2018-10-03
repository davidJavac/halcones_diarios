package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.Caja_internaVO;
import modelo_vo.Monedas_egresosVO;
import modelo_vo.Monedas_ingresosVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.ProveedoresVO;

public class MonedasDAO {

private static final String sql_buscarMotivo_inasistenciaUsuario = "select * from motivo_inasistencia where id_motivo = ?";
	
	private static final String sql_buscarMotivo_inasistenciaAll = "select * from motivo_inasistencia";
	
	private static final String sql_select_buscarMonedasIngreso_porFecha = "select * from monedas_ingreso "
			+ "where fecha = ?";
	private static final String sql_select_buscarMonedasEgreso_porFecha = "select * from monedas_egreso "
			+ "where fecha = ?";
	private static final String sql_insertMonedas_ingreso = "insert into monedas_ingreso(fecha, id_zona,"
			+ "monto_ingreso) values(?,?,?)";
	
	private static final String sql_insertMonedas_egreso = "insert into monedas_egreso(fecha, "
			+ " monto_egreso) values(?,?)";
	
	private static final String spMonedasAcumulado_ingreso = "{call acumulado_ingreso_monedas(?)}";
	private static final String spMonedasAcumulado_egreso = "{call acumulado_egreso_monedas(?)}";
	
	private static final String sql_deleteEgreso_monedas = "delete from monedas_egreso where fecha = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public ArrayList<Monedas_ingresosVO> buscarMonedasIngreso_porFecha(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Monedas_ingresosVO> ar = new ArrayList<Monedas_ingresosVO>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarMonedasIngreso_porFecha);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Monedas_ingresosVO mVO = new Monedas_ingresosVO();
				
				existe = true;
				
				mVO.setFecha_registro(res.getDate(1));
				mVO.setId_zona(res.getShort(2));
				mVO.setMonto_ingreso(res.getDouble(3));
				
				ar.add(mVO);
		
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
	
	public ArrayList<Monedas_egresosVO> buscarMonedasEgreso_porFecha(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		ArrayList<Monedas_egresosVO> ar = new ArrayList<Monedas_egresosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarMonedasEgreso_porFecha);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Monedas_egresosVO mVO = new Monedas_egresosVO();
				
				existe = true;
				
				mVO.setFecha_registro(res.getDate(1));
				mVO.setMonto_egreso(res.getDouble(2));
				
				ar.add(mVO);
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
	
	public double buscarMonedasAcumulado_ingreso(java.sql.Date fecha) throws SQLException{

		java.sql.CallableStatement stmt =  null;

		ResultSet res;
		
		double acumulado = 0;
		boolean existe = false;
		
		System.out.println("fecha acumulado " + fecha);
		
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt =  sqlConexion.prepareCall(spMonedasAcumulado_ingreso);
			
			stmt.setDate(1, fecha);
			res =  stmt.executeQuery();	
			
			
			while(res.next()){
				System.out.println("resultset" + res.getDouble(1));
				existe = true;
				
				acumulado = res.getDouble(1);
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
			System.out.println("acumulado " + acumulado);	
			return acumulado;
		}
		else return 0;
	}
	
	public double buscarMonedasAcumulado_egreso(java.sql.Date fecha) throws SQLException{

		java.sql.CallableStatement stmt =  null;

		ResultSet res;
		double acumulado = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt =  sqlConexion.prepareCall(spMonedasAcumulado_egreso);
			
			stmt.setDate(1, fecha);
			res =  stmt.executeQuery();	
			
			
			while(res.next()){

				existe = true;
				
				acumulado = res.getDouble(1);
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
			return acumulado;
		}
		else return 0;
	}
	
	public int eliminarEgreso_monedas(java.sql.Date hoy) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		try{
			
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_deleteEgreso_monedas);
			sentencia.setDate(1, hoy);
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
	
	
	public int insert_monedas_ingreso(Monedas_ingresosVO mVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertMonedas_ingreso);
		
			sentencia.setDate(1, mVO.getFecha_registro());
			sentencia.setShort(2, mVO.getId_zona());
			sentencia.setDouble(3, mVO.getMonto_ingreso());
				
			
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
	
	public int insert_monedas_egreso(Monedas_egresosVO mVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertMonedas_egreso);
			
			sentencia.setDate(1, mVO.getFecha_registro());
			sentencia.setDouble(2, mVO.getMonto_egreso());
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
