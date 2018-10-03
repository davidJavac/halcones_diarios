package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;

public class Domicilio_comercialDAO {

	private static final String sql_buscarComercioPersonalizada = "select * from comercios "
			+ " where descripcion like concat('%', ?, '%')";
	
	private static final String sql_buscardomicilio_comercial = "select * from domicilio_comercial"
			+ " d inner join clientes c"
			+ " on d.dni = c.dni where c.dni = ?";
	private static final String sql_buscarComercio = "select * from comercios order by descripcion asc";
	private static final String sql_buscarComercio_porCodigo = "select * from comercios "
			+ "where codigo = ?";
	private static final String sql_buscardomicilio_comercial_porIdc = "select * from domicilio_comercial "
			+ "where idc = ?";
	private static final String sql_insertardomicilio_comercial = "insert into domicilio_comercial(dni, "
			+ "domicilio,"
			+ "entre_calle1, entre_calle2, barrio, cp, id_zona,id_localidad,n_orden_planilla,"
			+ " antiguedad,comercio,"
			+ "propio, horario_atencion)"
			+ " values(?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?,?,?)";
	
	private static final String sql_updateDomCom = "update domicilio_comercial set dni =?, id_zona=?,"
			+ " domicilio = ?,"
			+ " entre_calle1= ?"
			+ " ,entre_calle2 = ?,"
			+ "  barrio = ?, "
			+ "cp = ?, id_localidad= ?,"
			+ "antiguedad = ?, comercio = ?, propio = ?, horario_atencion = ? where idc = ?";
	
	private static final String sql_buscarDC_porZona = " select  * from domicilio_comercial "
			+ " where id_zona = ? and idc in(select idc from"
			+ " pedidos) order by n_orden_planilla asc";
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Object []> buscarComercioPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<Object []> ar = new ArrayList<Object[]>();
		
		String vendedor = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarComercioPersonalizada);
		
			sentencia.setString(1, busqueda);
			
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
	
	public ComercioVO buscarComercio(int codigo) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
		ComercioVO dc = new ComercioVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarComercio_porCodigo);
			
			sentencia.setInt(1, codigo);
			//sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				dc.setCodigo(res.getInt(1));
				dc.setDescripcion(res.getString(2));
				
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

			return dc;
		}
		else return null;
		
	}
	
	public DomicilioComercialVO buscarDomicilioComercial_porIdc(int idc)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
	
		DomicilioComercialVO dc = new DomicilioComercialVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardomicilio_comercial_porIdc);
			
			sentencia.setInt(1, idc);
			//sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				dc.setIdc(res.getInt(1));
				dc.setDni(res.getInt(2));
				dc.setDomicilio(res.getString(3));
				dc.setEntre_calle1(res.getString(4));
				dc.setEntre_calle2(res.getString(5));
				dc.setBarrio(res.getString(6));
				dc.setCp(res.getInt(7));
				dc.setId_zona(res.getInt(8));
				dc.setId_localidad(res.getShort(9));
				dc.setN_orden_planilla(res.getInt(10));
				dc.setAntiguedad(res.getDate(11));
				dc.setComercio(res.getInt(12));
				dc.setPropio(res.getBoolean(13));
				dc.setHorario_atencion(res.getString(14));
				
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
			System.out.println("existe");
			return dc;
		}
		else return null;
		
	}
	
	public ArrayList<DomicilioComercialVO> buscarDomicilio_comercial(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<DomicilioComercialVO> a = new ArrayList<DomicilioComercialVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardomicilio_comercial);
			
			sentencia.setInt(1, dni);
			//sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				DomicilioComercialVO dc = new DomicilioComercialVO();
				
				dc.setIdc(res.getInt(1));
				dc.setDni(res.getInt(2));
				dc.setDomicilio(res.getString(3));
				dc.setEntre_calle1(res.getString(4));
				dc.setEntre_calle2(res.getString(5));
				dc.setBarrio(res.getString(6));
				dc.setCp(res.getInt(7));
				dc.setId_zona(res.getInt(8));
				dc.setId_localidad(res.getShort(9));
				dc.setN_orden_planilla(res.getInt(10));
				dc.setAntiguedad(res.getDate(11));
				dc.setComercio(res.getInt(12));
				dc.setPropio(res.getBoolean(13));
				dc.setHorario_atencion(res.getString(14));
				
				a.add(dc);
				
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
			System.out.println("existe");
			return a;
		}
		else return null;
		
	}
	
	public ArrayList<DomicilioComercialVO> buscarDomicilio_comercial_porZona(int zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<DomicilioComercialVO> a = new ArrayList<DomicilioComercialVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarDC_porZona);
			
			sentencia.setInt(1, zona);
			//sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				DomicilioComercialVO dc = new DomicilioComercialVO();
				
				dc.setIdc(res.getInt(1));
				dc.setDni(res.getInt(2));
				dc.setDomicilio(res.getString(3));
				dc.setEntre_calle1(res.getString(4));
				dc.setEntre_calle2(res.getString(5));
				dc.setBarrio(res.getString(6));
				dc.setCp(res.getInt(7));
				dc.setId_zona(res.getInt(8));
				dc.setId_localidad(res.getShort(9));
				dc.setN_orden_planilla(res.getInt(10));
				dc.setAntiguedad(res.getDate(11));
				dc.setComercio(res.getInt(12));
				dc.setPropio(res.getBoolean(13));
				dc.setHorario_atencion(res.getString(14));
				
				a.add(dc);
				
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
			System.out.println("existe");
			return a;
		}
		else return null;
		
	}
	public ArrayList<Object[]> buscarComercioAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarComercio);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Object [] datos = new Object[2];
				
				datos[0] = res.getInt(1);
				datos[1] = res.getString(2);
				
				a.add(datos);
				
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
			return a;
		}
		else return null;
		
	}
	
	public int modificarDomCom(DomicilioComercialVO _domicilio_comercialVO,int dni, int dni2) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateDomCom);
			
			
			sentencia.setInt(1, dni);
			sentencia.setInt(2, _domicilio_comercialVO.getId_zona());			
			sentencia.setString(3, _domicilio_comercialVO.getDomicilio());
			sentencia.setString(4, _domicilio_comercialVO.getEntre_calle1());
			sentencia.setString(5, _domicilio_comercialVO.getEntre_calle2());
			sentencia.setString(6, _domicilio_comercialVO.getBarrio());
			sentencia.setInt(7, _domicilio_comercialVO.getCp());
			sentencia.setShort(8, _domicilio_comercialVO.getId_localidad());
			sentencia.setDate(9, _domicilio_comercialVO.getAntiguedad());
			sentencia.setInt(10, _domicilio_comercialVO.getComercio());
			sentencia.setBoolean(11, _domicilio_comercialVO.getPropio());
			sentencia.setString(12, _domicilio_comercialVO.getHorario_atencion());
			sentencia.setInt(13, _domicilio_comercialVO.getIdc());
			
			//sentencia.setInt(2, dni2);

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
	
	public int insertarDomicilio_comercial(DomicilioComercialVO _domicilio_comercialVO) throws SQLException{
		
		PreparedStatement sentencia = null;

		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertardomicilio_comercial);
			
			sentencia.setInt(1, _domicilio_comercialVO.getDni());
			sentencia.setString(2, _domicilio_comercialVO.getDomicilio());
			sentencia.setString(3, _domicilio_comercialVO.getEntre_calle1());
			sentencia.setString(4, _domicilio_comercialVO.getEntre_calle2());
			sentencia.setString(5, _domicilio_comercialVO.getBarrio());
			sentencia.setInt(6, _domicilio_comercialVO.getCp());
			sentencia.setInt(7, _domicilio_comercialVO.getId_zona());
			sentencia.setShort(8, _domicilio_comercialVO.getId_localidad());
			sentencia.setInt(9, _domicilio_comercialVO.getN_orden_planilla());
			sentencia.setDate(10, _domicilio_comercialVO.getAntiguedad());
			sentencia.setInt(11, _domicilio_comercialVO.getComercio());
			sentencia.setBoolean(12, _domicilio_comercialVO.getPropio());
			sentencia.setString(13, _domicilio_comercialVO.getHorario_atencion());
			
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
