package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.Vendedores_padre_hijoVO;
import modelo_vo.ZonaVO;
import modelo_vo.Zona_localidadVO;

public class Vendedor_phDAO {

	
	private static final String sql_buscarVendedoresHijos = "select * from vendedores_padre_hijo where id_padre = ?";
	
	private static final String sql_buscarVendedoresPadres = "select * from vendedores_padre_hijo where id_hijo = ?";

	private static final String sql_insertVendedoresPadres = "insert into vendedores_padre_hijo (id_padre, id_hijo)"
			+ "values(?,?)";
	
	private static final String sql_deleteVendedoresPadres = "delete from vendedores_padre_hijo where id_padre = ?"
			+ " and id_hijo= ?";

	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresHijos_porIdPadre(int id)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<Vendedores_padre_hijoVO> ar = new ArrayList<Vendedores_padre_hijoVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedoresHijos);
	
			sentencia.setInt(1, id);
			
			res = sentencia.executeQuery();

			
			while(res.next()){
			
				Vendedores_padre_hijoVO vVO = new Vendedores_padre_hijoVO();
				
				existe = true;
						
				vVO.setId_padre(res.getInt(1));
				vVO.setId_hijo(res.getInt(2));
				
				ar.add(vVO);
				
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
	
	public ArrayList<Vendedores_padre_hijoVO> buscarVendedoresPadres_porIdHijo(int id)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<Vendedores_padre_hijoVO> ar = new ArrayList<Vendedores_padre_hijoVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedoresPadres);
		
			sentencia.setInt(1, id);
			
			res = sentencia.executeQuery();

			
			while(res.next()){
			
				Vendedores_padre_hijoVO vVO = new Vendedores_padre_hijoVO();
				
				existe = true;
						
				vVO.setId_padre(res.getInt(1));
				vVO.setId_hijo(res.getInt(2));
				
				ar.add(vVO);
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
	
	public int insertVendedores_ph(Vendedores_padre_hijoVO vphVO)throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertVendedoresPadres);
			
			sentencia.setInt(1, vphVO.getId_padre());
			sentencia.setInt(2, vphVO.getId_hijo());
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
	
	public int deleteVendedores_ph(Vendedores_padre_hijoVO vphVO)throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deleteVendedoresPadres);
			sentencia.setInt(1, vphVO.getId_padre());
			sentencia.setInt(2, vphVO.getId_hijo());
			
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
