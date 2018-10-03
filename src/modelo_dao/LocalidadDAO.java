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
import modelo_vo.LocalidadVO;
import modelo_vo.VerazVO;

public class LocalidadDAO {
	
	private static final String sql_buscarLocalidadPersonalizada = "select id_localidad, localidad from localidad"
			+ " where (localidad like concat('%', ?, '%')) "
			+ " and estado = ?";
	
	private static final String sql_buscarLocalidadAll = "select id_localidad, localidad from localidad where estado = ?";
	private static final String sql_buscarLocalidadUsuario = "select localidad from localidad where estado = ? "
			+ "and id_localidad = ?";
	
	private static final String sql_buscarLocalidad_porID = "select * from localidad where estado = ? "
			+ "and id_localidad = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public ArrayList<Object []> buscarLocalidadPersonalida(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		String localidad = "";
		int cont = 0;
		
		boolean existe = false;
		ArrayList<Object []> ar = new ArrayList<Object[]>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarLocalidadPersonalizada);
			
			sentencia.setString(1, busqueda);
			sentencia.setInt(2, 1);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				
				existe = true;
				
				localidad =  res.getString(2);	
						
				objetos [0] = res.getShort(1);
				
				objetos [1] = localidad;
				
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
	
	public String buscarLocalidadUsuario(short id_localidad) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarLocalidadUsuario);
		ResultSet res;

		
		String localidad = "";
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarLocalidadUsuario);
			
			sentencia.setBoolean(1, true);
			sentencia.setShort(2, id_localidad);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				localidad = res.getString(1);
				sqlConexion.commit();
			
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return localidad;
		else return null;
		
	}
	
	public LocalidadVO buscarLocalidad_porID(short id_localidad) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		LocalidadVO lVO = new LocalidadVO();
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarLocalidad_porID);
			
			sentencia.setBoolean(1, true);
			sentencia.setShort(2, id_localidad);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				lVO.setId_localidad(res.getShort(1));
				lVO.setLocalidad(res.getString(2));
				lVO.setEstado(res.getBoolean(3));
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return lVO;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarLocalidadAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
	
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarLocalidadAll);
			
			sentencia.setBoolean(1, true);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				Object objetos [] = new Object[2];
				
				objetos[0] = res.getShort(1);
				objetos[1] = res.getString(2);
				
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
