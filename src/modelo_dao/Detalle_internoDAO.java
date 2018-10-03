package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;

public class Detalle_internoDAO {

private static final String sql_buscarMotivo_inasistenciaUsuario = "select * from motivo_inasistencia where id_motivo = ?";
	
	private static final String sql_buscarDetalle_internoAll = "select * from detalle_motivo_interno "
			+ "where id_motivo = ?";
	
	private static final String sql_buscarDetalle_internaUsuario = "select * from detalle_motivo_interno where id_detalle = ?";
	
	private static final String sql_buscarMotivo_internaAll = "select * from motivo_caja_interna";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public Motivo_generalVO buscarMotivoInasistencia_porID(short id_motivo)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		sentencia.setShort(1, id_motivo);
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		Motivo_generalVO mVO = new Motivo_generalVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_inasistenciaUsuario);
			
			while(res.next()){
				
				existe = true;
				
				mVO.setId_motivo(res.getShort(1));
				mVO.setCategoria(res.getString(2));
				
			}	
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		

		if(existe) 	return mVO;
		else return null;
	}
	
	public String buscarMotivo_inasistenciaUsuario(short id_motivo) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		String motivo = "";
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_inasistenciaUsuario);
			
			sentencia.setShort(1, id_motivo);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
				motivo = res.getString(2);
				
				sqlConexion.commit();
			}	
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		

		if(existe) 	return motivo;
		else return null;
		
	}
	
	
	public Detalle_motivo_internoVO buscarDetalleInterna_porID(short id_motivo)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		Detalle_motivo_internoVO dVO = new Detalle_motivo_internoVO();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarDetalle_internaUsuario);
			sentencia.setShort(1, id_motivo);
			
			res = sentencia.executeQuery();
				
			while(res.next()){
				
				existe = true;
				
				dVO.setId_detalle(res.getShort(1));
				dVO.setId_motivo(res.getShort(2));
				dVO.setDescripcion(res.getString(3));
				
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		

		if(existe) 	return dVO;
		else return null;
	}
	
	public ArrayList<Object []> buscarDetalle_internoAll(short id_motivo) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		int cont = 0;
		String nombre = "";
		
		boolean existe = false;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarDetalle_internoAll);
			
			sentencia.setShort(1, id_motivo);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;	
				
				objetos [0] = res.getInt(1);

				objetos [1] = res.getString(3);
				
				ar.add(objetos);
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
}
