package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.VendedorVO;

public class VendedorDAO {

	private static final String sql_buscarVendedorPersonalizada = "select id_vendedor, nombre, apellido from vendedores "
			+ " where concat(nombre, ' ', apellido) like concat('%', ?, '%') and estado = ?";
	
	private static final String sql_buscarVendedorAll = "select id_vendedor, nombre, apellido from vendedores"
			+ " where estado = ? ";
	
	private static final String sql_buscarVendedorUsuario = "select nombre, apellido from vendedores where estado = ? "
			+ "and id_vendedor = ?";
	
	private static final String sql_buscarVendedor_porID = "select * from vendedores where id_vendedor = ?";
	
	private static final String sql_buscarVendedorPersonalizada2 = "select id, nombre, apellido from empleados "
			+ " where concat(nombre, ' ', apellido) like concat('%', ?, '%') and puesto = ? and estado = ?";
	
	private static final String sql_buscarVendedorAll2 = "select id, nombre, apellido from empleados"
			+ " where puesto = ? and estado = ? ";
	
	private static final String sql_buscarVendedorUsuario2 = "select nombre, apellido from empleados where puesto = ? "
			+ "and estado = ? "
			+ "and id = ?";
			
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public ArrayList<Object []> buscarVendedorPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<Object []> ar = new ArrayList<Object[]>();
		
		String vendedor = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedorPersonalizada2);
		
			sentencia.setString(1, busqueda);
			sentencia.setString(2, "vendedor");
			sentencia.setBoolean(2, true);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;	
		
				vendedor = res.getString(2) + " " + res.getString(3) + " ";
						
				objetos [0] = res.getInt(1);
						
				objetos [1] = vendedor;
				
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
	
	public String buscarVendedorUsuario(short id_vendedor) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		String vendedor = "";
	
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedorUsuario2);
			sentencia.setString(1, "vendedor");
			sentencia.setBoolean(2, true);
			sentencia.setShort(3, id_vendedor);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
				vendedor = res.getString(1) + " " + res.getString(2);
				
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		

		if(existe) 	return vendedor;
		else return null;
		
	}
	
	public VendedorVO buscarVendedor_porID(short id_vendedor) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		boolean existe = false;
		
		VendedorVO vendedorVO = new VendedorVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedor_porID);
			sentencia.setShort(1, id_vendedor);
			
			res = sentencia.executeQuery();
			
		
			while(res.next()){
				
				existe = true;
			
				vendedorVO.setId_vendedor(res.getShort(1));
				vendedorVO.setComision(res.getDouble(2));
				vendedorVO.setComision_prestamo(res.getDouble(3));
			
			}	
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return vendedorVO;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarVendedorAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVendedorAll2);
		
			sentencia.setString(1, "vendedor");
			sentencia.setBoolean(2, true);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				Object objetos [] = new Object[3];
				String nombre_apellido = "";
				
				nombre_apellido = res.getString(2) + " " + res.getString(3);
				
				objetos[0] = res.getShort(1);
				objetos[1] = nombre_apellido;
				
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
