package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;

public class CobradorDAO {

private static final String sql_cantidadID = "select distinct id_zona, count(*) as id from zonas group by id_zona";
	
	private static final String sql_buscarzonaPersonalizada = "select z.id_zona, l.localidad, c.nombre, c.apellido "
			+ "from zonas z inner join localidad l on z.id_localidad = l.id_localidad inner join cobradores c on "
			+ "z.id_cobrador = c.id_cobrador where concat(c.nombre, ' ', c.apellido) like concat('%', ?, '%') "
			+ "and z.id_zona = ? and l.estado = ?";
	
	private static final String sql_buscarzonaPersonalizada2 = "select z.id_zona, l.localidad, e.nombre, e.apellido "
			+ "from zonas z inner join localidad l on z.id_localidad = l.id_localidad inner join empleados e on "
			+ "z.id_cobrador = e.id where concat(e.nombre, ' ', e.apellido) like concat('%', ?, '%') "
			+ "and z.id_zona = ? and e.puesto = ? and l.estado = ?";
	
	private static final String sql_buscarCobradorUsuario = "select * from cobradores where estado = ? and id_cobrador = ?";
	private static final String sql_buscarCobrador_porID = "select * from cobradores where id_cobrador = ?";
	private static final String sql_buscarEmpleadoUsuario = "select * from empleados where estado = ? and id = ?";
	private static final String sql_buscarEmpleadoAll = "select * from empleados where puesto = ? and estado <> ?";
	private static final String sql_buscarCobradorAll = "select * from cobradores where estado <> ?";

	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public String buscarCobradorUsuario(short id_cobrador) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		String cobrador = "";
	
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleadoUsuario);
			sentencia.setBoolean(1, true);
			sentencia.setShort(2, id_cobrador);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				cobrador = res.getString(4) + " " + res.getString(5);
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return cobrador;
		else return null;
		
	}
	
	public CobradorVO buscarCobrador_porID(short id_cobrador) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		boolean existe = false;
		CobradorVO cVO = new CobradorVO();
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarCobrador_porID);
			
			sentencia.setShort(1, id_cobrador);
			
			res = sentencia.executeQuery();
			
			
			
			while(res.next()){
			
				existe = true;
		
				cVO.setId_cobrador(res.getShort(1));
				cVO.setAuto_modelo(res.getString(2));
				cVO.setPatente(res.getString(3));
				cVO.setPremio(res.getDouble(4));
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		

		if(existe) 	return cVO;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarZonaPersonalida(String busqueda, short id_zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		Object objetos [] = new Object[2];
		ArrayList<Object []> ar = new ArrayList<Object[]>();
		String zona = "";
		String descripcion = "";
		String cobrador = "";
		String localidad = "";

		int cont = 0;
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarzonaPersonalizada2);
			
			sentencia.setString(1, busqueda);
			sentencia.setShort(2, id_zona);
			sentencia.setString(3, "cobrador");
			sentencia.setInt(4, 1);
			
			
			res = sentencia.executeQuery();
			
			
			
			while(res.next()){
			
				existe = true;
				
				localidad = " " + res.getString(2) + " ";	
				
				if(cont == 0){
					
					zona = Integer.toString( res.getInt(1));
					cobrador = res.getString(3) + " " + res.getString(4) + " ";
					descripcion = cobrador + "Localidades: " + localidad;
						
					objetos [0] = res.getInt(1);
					
				}
				else{
					descripcion = descripcion + localidad;
					
				}
				
				objetos [1] = descripcion;
				
				cont++;	
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		
		ar.add(objetos);
		
		if(existe) 	return ar;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarCobradorAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		int cont = 0;
		
		boolean existe = false;
		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleadoAll);
			
			sentencia.setString(1, "cobrador");
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;	
				
				objetos [0] = res.getInt(1);

				objetos [1] = res.getString(4) + " " + res.getString(5);
				
				ar.add(objetos);
			}	
			
			sqlConexion.commit();
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
