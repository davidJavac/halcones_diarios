package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Pago_diarioVO;

public class Caja_inasistenciaDAO {

	private static final String sql_select_buscarCaja_inasistencia = "select * from caja_inasistencia where fecha_registro= ?";
	private static final String sql_select_buscarCaja_inasistencia_entreFechas = "select * from "
			+ "caja_inasistencia where fecha_registro between ? and ?";
	private static final String sql_insertCaja_inasistencia = "insert into caja_inasistencia (id_empleado, id_motivo, "
			+ "id_usuario, fecha_registro"
			+ ", hora_registro) values(?,?,?,?,?)";
	
	private static final String sql_bajaInasistencia = "delete from caja_inasistencia where id_empleado = ?"
			+ "  and fecha_registro = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Caja_inasistenciaVO> buscarCaja_inasistencia_entreFechas 
	(java.sql.Date lunes, java.sql.Date fecha)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Caja_inasistenciaVO> ar = new ArrayList<Caja_inasistenciaVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_inasistencia_entreFechas);
			
			sentencia.setDate(1, lunes);
			sentencia.setDate(2, fecha);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Caja_inasistenciaVO cVO = new Caja_inasistenciaVO();
				
				existe = true;
				
				cVO.setId_empleado(res.getShort(1));
				cVO.setId_motivo(res.getShort(2));
				cVO.setId_usuario(res.getShort(3));
				cVO.setFecha_registro(res.getDate(4));
				cVO.setHora_registro(res.getTime(5));
				
				ar.add(cVO);
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
	
	public ArrayList<Caja_inasistenciaVO> buscarCaja_inasistencia(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Caja_inasistenciaVO> ar = new ArrayList<Caja_inasistenciaVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_inasistencia);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Caja_inasistenciaVO cVO = new Caja_inasistenciaVO();
				
				existe = true;
				
				cVO.setId_empleado(res.getShort(1));
				cVO.setId_motivo(res.getShort(2));
				cVO.setId_usuario(res.getShort(3));
				cVO.setFecha_registro(res.getDate(4));
				cVO.setHora_registro(res.getTime(5));
				
				ar.add(cVO);
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
	
	public int eliminarInasistencia(short id_empleado, java.sql.Date hoy) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_bajaInasistencia);
			sentencia.setShort(1, id_empleado);
			sentencia.setDate(2, hoy);
			
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
	
	public int ingresos(Caja_inasistenciaVO cVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja_inasistencia);
			sentencia.setShort(1, cVO.getId_empleado());
			sentencia.setShort(2, cVO.getId_motivo());
			sentencia.setShort(3, cVO.getId_usuario());
			sentencia.setDate(4, cVO.getFecha_registro());
			sentencia.setTime(5, cVO.getHora_registro());
			
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
