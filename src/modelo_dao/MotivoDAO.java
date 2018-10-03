package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.VerazVO;

public class MotivoDAO {


	
	private static final String sql_buscarMotivo_generalUsuario = "select * from motivo_general where id_motivo = ?";
	
	private static final String sql_buscarMotivo_generalAll = "select * from motivo_general";
	
	private static final String sql_buscarMotivo_internaUsuario = "select * from motivo_caja_interna where id_motivo = ?";
	
	private static final String sql_buscarMotivo_internaAll = "select * from motivo_caja_interna";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public Motivo_generalVO buscarMotivoGeneral_porID(short id_motivo)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		Motivo_generalVO mVO = new Motivo_generalVO();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_generalUsuario);
		
			sentencia.setShort(1, id_motivo);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				mVO.setId_motivo(res.getShort(1));
				mVO.setCategoria(res.getString(2));
			
				sqlConexion.commit();
			}	
			
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
	
	public String buscarMotivo_generalUsuario(short id_motivo) throws SQLException{
		
		PreparedStatement sentencia = null;

		ResultSet res;
		String motivo = "";
	
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_generalUsuario);
		
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
	
	
	
	public ArrayList<Object []> buscarMotivo_generalAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_generalAll);
		
			res = sentencia.executeQuery();
					
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;	
				
				objetos [0] = res.getInt(1);

				objetos [1] = res.getString(2);
				
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
	
	public Motivo_caja_internaVO buscarMotivoInterna_porID(short id_motivo)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		Motivo_caja_internaVO mVO = new Motivo_caja_internaVO();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_internaUsuario);
		
			sentencia.setShort(1, id_motivo);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				mVO.setId_motivo(res.getShort(1));
				mVO.setIngreso(res.getBoolean(2));
				mVO.setDescripcion(res.getString(3));

				sqlConexion.commit();
			}	
			
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
	
	public ArrayList<Object []> buscarMotivo_internaAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_internaAll);
	
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
