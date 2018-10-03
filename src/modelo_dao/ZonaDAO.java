package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.CallableStatement;
import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;
import modelo_vo.ZonaVO;
import modelo_vo.Zona_localidadVO;

public class ZonaDAO {

	private static final String sql_cantidadID = "select distinct id_zona, count(*) as id from zonas group by id_zona";
	
	private static final String sql_buscarZona_porID = "select * from zonas where id_zona = ?";
	private static final String sql_buscarZona_porID_cobrador = "select * from zonas z inner join empleados e "
			+ " on z.id_cobrador = e.id where z.id_cobrador = ? and e.estado <> ?";
	private static final String sql_buscarZona_localidad = "select * from zona_localidad where id_zona = ? "
			+ "and id_localidad = ?";
	
	private static final String sql_buscarzonaPersonalizada = "select z.id_zona, e.nombre, e.apellido "
			+ "from zonas z inner join empleados e on "
			+ "z.id_cobrador = e.id where concat(e.nombre, ' ', e.apellido) like concat('%', ?, '%') "
			+ " and e.puesto = ?";
	
	private static final String sql_buscarzonaAll = "select * from zonas order by id_zona";
	private static final String sql_buscarzona_localidad_porID = "select * from zona_localidad where"
			+ " id_zona = ?";
	
	private static final String spModificarZona = "{call modificar_zona(?,?,?,?,?,?,?,?,?,?)}";
	private static final String spInsertZona = "{call insert_zona(?,?,?,?,?)}";
	private static final String sqlInsertZona = "insert into zonas (id_zona, id_cobrador) values(?,?)";
	private static final String sqlUpdateZona_cobrador = "update zonas set id_cobrador = ? where id_zona = ?";
	private static final String sqlDeleteAllZonas= "truncate zonas";
	private static final String sql_InsertLocalidad = " insert into zona_localidad(id_zona, id_localidad) "
			+ "values(?,?)";
	private static final String sql_DeleteLocalidad = " delete from zona_localidad "
			+ "where id_zona = ? and id_localidad = ?";
	

	private static final Connection sqlConexion = Conexion.getConexion();
	
	public boolean buscarZona_localidad(short id_zona, short id_localidad) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarZona_localidad);
			
			sentencia.setShort(1, id_zona);
			sentencia.setShort(2, id_localidad);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
			}	
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		

		if(existe) 	return existe;
		else return false;
		
	}
	
	public ZonaVO buscarZona_porID_cobrador(int id)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ZonaVO zVO = new ZonaVO();
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarZona_porID_cobrador);
		
			sentencia.setInt(1, id);
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				zVO.setId_zona(res.getShort(1));
				zVO.setId_cobrador(res.getShort(2));
				
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return zVO;
		else return null;
	}
	
	public ZonaVO buscarZona_porID(short id_zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ZonaVO zVO = new ZonaVO();
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarZona_porID);
		
			sentencia.setShort(1, id_zona);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				zVO.setId_zona(res.getShort(1));
				zVO.setId_cobrador(res.getShort(2));
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return zVO;
		else return null;
		
	}
	
	public int getCantidadZonas() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		int cantidad_zonas = 0;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_cantidadID);
		
			res = sentencia.executeQuery();
			

			while(res.next())
			{
				//Busco según el nombre de la columna en la tabla contactos
				cantidad_zonas = res.getInt(1);
			
			}
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return cantidad_zonas;
	}
	
	public ArrayList<Object []> buscarZonaPersonalida(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<Object []> ar = new ArrayList<Object[]>();
		String zona = "";
		String cobrador = "";
		
		boolean existe = false;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarzonaPersonalizada);
		
			sentencia.setString(1, busqueda);
			sentencia.setString(2, "cobrador");
					
			res = sentencia.executeQuery();

			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;
						
				objetos [0] = res.getInt(1);
				
				cobrador = res.getString(2) + " " + res.getString(3) ;
				
				objetos [1] = cobrador;
				
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
	
	
	
	public ArrayList<Zona_localidadVO> buscarZona_localidad_porID(short id_zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<Zona_localidadVO> ar = new ArrayList<Zona_localidadVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarzona_localidad_porID);
		
			sentencia.setShort(1, id_zona);
			
			res = sentencia.executeQuery();

			
			while(res.next()){
			
				existe = true;
				
				Zona_localidadVO zlVO = new Zona_localidadVO();
				
				zlVO.setId_zona(res.getShort(1));
				zlVO.setId_localidad(res.getShort(2));
				
				ar.add(zlVO);
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
	
	public ArrayList<ZonaVO> buscarZonaAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		ArrayList<ZonaVO> ar = new ArrayList<ZonaVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarzonaAll);

			res = sentencia.executeQuery();

			
			while(res.next()){
			
				existe = true;
				
				ZonaVO zVO = new ZonaVO();
				
				zVO.setId_zona(res.getShort(1));
				zVO.setId_cobrador(res.getShort(2));
				
				ar.add(zVO);
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
	
	public int  insertLocalidad(short id_zona, short id_localidad) throws SQLException{
		
		PreparedStatement stmt =  null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt =  sqlConexion.prepareStatement(sql_InsertLocalidad);
			stmt.setShort(1, id_zona);
			stmt.setShort(2, id_localidad);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		
		return stmt.executeUpdate();
	}
	
	public int  deleteLocalidad(short id_zona, short id_localidad) throws SQLException{
		
		PreparedStatement stmt =  null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt =  sqlConexion.prepareStatement(sql_DeleteLocalidad);
			stmt.setShort(1, id_zona);
			stmt.setShort(2, id_localidad);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		
		return stmt.executeUpdate();
	}
	
	public int modificarZona_cobrador(ZonaVO zVO)throws SQLException{
		
		PreparedStatement stmt = null;
		
		try{
			System.out.println("zona en dao" + zVO.getId_zona() + "id cobrador dao" + zVO.getId_cobrador());
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareStatement(sqlUpdateZona_cobrador);
		
			stmt.setInt(1, zVO.getId_cobrador());
			stmt.setInt(2, zVO.getId_zona());
			
		
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();
	}
	
	public int insertZona_cobrador(ZonaVO zVO) throws SQLException{
		
		PreparedStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareStatement(sqlInsertZona);
		
			stmt.setInt(1, zVO.getId_zona());
			stmt.setInt(2, zVO.getId_cobrador());
			
		
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();
		
	}
	public int deleteAllZonas() throws SQLException{
		
		PreparedStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareStatement(sqlDeleteAllZonas);
			
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();
		
	}
	
	public int  insertZona(ZonaVO zVO, int loc1,int loc2,int loc3,int loc4) throws SQLException{
		
		java.sql.CallableStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spInsertZona);
		
			stmt.setInt(1, zVO.getId_cobrador());
			stmt.setInt(2, loc1);
			stmt.setInt(3, loc2);
			stmt.setInt(4, loc3);
			stmt.setInt(5, loc4);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();
	}
	
	public int  modificarZona(ZonaVO zVO, int aux_loc1, int aux_loc2,int aux_loc3,
			int aux_loc4, int loc1,int loc2,int loc3,int loc4) throws SQLException{
		
		java.sql.CallableStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spModificarZona);
			
			stmt.setInt(1, zVO.getId_zona());
			stmt.setInt(2, zVO.getId_cobrador());
			stmt.setInt(3, aux_loc1);
			stmt.setInt(4, aux_loc2);
			stmt.setInt(5, aux_loc3);
			stmt.setInt(6, aux_loc4);
			stmt.setInt(7, loc1);
			stmt.setInt(8, loc2);
			stmt.setInt(9, loc3);
			stmt.setInt(10, loc4);
			
			sqlConexion.commit();
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		return stmt.executeUpdate();
	}
	
}
