package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.ProveedoresVO;

public class ProveedorDAO {

	
private static final String sql_buscarMotivo_inasistenciaUsuario = "select * from motivo_inasistencia where id_motivo = ?";
	
	private static final String sql_buscarMotivo_inasistenciaAll = "select * from motivo_inasistencia";
	
	private static final String sql_buscarProveedorUsuario = "select * from proveedores where id_proveedor = ?";
	
	private static final String sql_buscarProveedorAll = "select * from proveedores";
	private static final String sql_updateProveedor = "update proveedores set nombre =?, razon_social =?,"
			+ " domicilio = ?,"
			+ "ciudad = ?, cuit = ?, telefono = ?, email = ?, fecha_alta = ? where id_proveedor = ?";
	
	private static final String sql_insertProveedor = "insert into proveedores (nombre, razon_social,"
			+ " domicilio,"
			+ "ciudad, cuit, telefono, email, fecha_alta) values(?,?,?,?,?,?,?,?)";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public Motivo_generalVO buscarMotivoInasistencia_porID(short id_motivo)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		Motivo_generalVO mVO = new Motivo_generalVO();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_inasistenciaUsuario);
		
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
	
	
	
	public ArrayList<Object []> buscarMotivo_inasistenciaAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		int cont = 0;
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarMotivo_inasistenciaAll);
		
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
	
	public ProveedoresVO buscarProveedor_porID(short id_proveedor)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		ProveedoresVO pVO = new ProveedoresVO();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarProveedorUsuario);
		
			sentencia.setShort(1, id_proveedor);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				pVO.setId_proveedores(res.getShort(1));
				pVO.setNombre(res.getString(2));
				pVO.setRazon_social(res.getString(3));
				pVO.setDomicilio(res.getString(4));
				pVO.setCiudad(res.getString(5));
				pVO.setCuit(res.getString(6));
				pVO.setTelefono(res.getString(7));
				pVO.setEmail(res.getString(8));
				pVO.setFecha_alta(res.getDate(9));

				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return pVO;
		else return null;
	}
	
	public ArrayList<Object []> buscarProveedorAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarProveedorAll);
		
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
	public int modificarProveedor(ProveedoresVO pVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateProveedor);
		
			
			sentencia.setString(1, pVO.getNombre());
			sentencia.setString(2, pVO.getRazon_social());
			sentencia.setString(3, pVO.getDomicilio());
			sentencia.setString(4, pVO.getCiudad());
			sentencia.setString(5, pVO.getCuit());
			sentencia.setString(6, pVO.getTelefono());
			sentencia.setString(7, pVO.getEmail());
			sentencia.setDate(8, pVO.getFecha_alta());
			sentencia.setInt(9, pVO.getId_proveedor());
			
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
	public int nuevoProveedor(ProveedoresVO pVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertProveedor);
		
			sentencia.setString(1, pVO.getNombre());
			sentencia.setString(2, pVO.getRazon_social());
			sentencia.setString(3, pVO.getDomicilio());
			sentencia.setString(4, pVO.getCiudad());
			sentencia.setString(5, pVO.getCuit());
			sentencia.setString(6, pVO.getTelefono());
			sentencia.setString(7, pVO.getEmail());
			sentencia.setDate(8, pVO.getFecha_alta());
			
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
