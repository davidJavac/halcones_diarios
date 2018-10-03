package modelo_dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LogicaCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;

public class VerazDAO {

	//private static final String sql_buscardnicliente = "select * from veraz v inner join domicilio_particular p"
			//+ " on v.dni = p.dni inner join domicilio_comercial c on v.dni = c.dni where v.dni = ?";
	private static final String sql_buscardnicliente = "select * from veraz where dni = ?";
	private static final String sql_buscardniconyugue = "select * from veraz where dni in(select dni from clientes"
			+ " where dni_conyugue = ?)";
	private static final String sql_buscardireccion = "select * from veraz where dni in(select dni from "
			+ "domicilio_particular where domicilio = ? and id_localidad = ?)";
		
	private static final String sql_buscarVeraz_all = "select * from veraz";
	
	private static final String sql_buscardireccionComercial ="select * from veraz where dni in(select dni from "
			+ "domicilio_comercial where domicilio = ? and id_localidad = ?)";
	
	private static final String sql_comprobarVeraz = "select * from veraz where dni in (select dni from clientes"
			+ " where dni_conyugue = ?) "
			+ " or dni in (select dni from domicilio_particular "
			+ " where domicilio = ? and id_localidad = ?) or "
			+ " dni in (select dni from domicilio_comercial "
			+ " where domicilio = ? and id_localidad = ?)";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public VerazVO comprobarRelacion_enVeraz(ClienteVO _clienteVO,
					DomicilioParticularVO dpVO, 
						DomicilioComercialVO dcVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		
		ArrayList<VerazVO> ar = new ArrayList<VerazVO>();
		VerazVO v = new VerazVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_comprobarVeraz);
		
			sentencia.setInt(1, _clienteVO.getDni());
			sentencia.setString(2, dpVO.getDomicilio());
			sentencia.setInt(3, dpVO.getId_localidad());
			sentencia.setString(4, dcVO.getDomicilio());
			sentencia.setInt(5, dcVO.getId_localidad());
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
				
				ar.add(v);
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
			
			return v;
		}
		else return null;
		
	}
	
	public VerazVO comprobarVeraz_porDni(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		VerazVO v = new VerazVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardnicliente);
		
			sentencia.setInt(1, dni);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
				
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
		
			return v;
		}
		else return null;
		
	}
	
	public  VerazVO buscarDniConyugue(ClienteVO _clienteVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		VerazVO v = new VerazVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardniconyugue);
		
			sentencia.setInt(1, _clienteVO.getDni());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
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
			
			return v;
		}
		else return null;
	}
	public  ArrayList<VerazVO> buscarVerazAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<VerazVO> ar = new ArrayList<VerazVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVeraz_all);
		
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				VerazVO v = new VerazVO();
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
				
				ar.add(v);
				
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
			
			return ar;
		}
		else return null;
	}
	
	public  VerazVO buscarDireccion(DomicilioParticularVO _domicilio_particularVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		
		VerazVO v = new VerazVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardireccion);
		
			sentencia.setString(1, _domicilio_particularVO.getDomicilio());
			sentencia.setInt(2, _domicilio_particularVO.getId_localidad());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
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
			
			
			return v;
		}
		else return null;
	}
	
	public  VerazVO buscarDireccion_comercial
	(DomicilioComercialVO _domicilio_comercialVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		
		VerazVO v = new VerazVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardireccionComercial);
		
			sentencia.setString(1, _domicilio_comercialVO.getDomicilio());
			sentencia.setInt(2, _domicilio_comercialVO.getId_localidad());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				v.setNregistro(res.getInt(1));
				v.setDni(res.getInt(2));
				v.setObservacion(res.getString(3));
				v.setId_usuario(res.getShort(4));
				v.setFecha_registro(res.getDate(5));
				v.setHora_registro(res.getTime(6));
				
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
			
			
			return v;
		}
		else return null;
	}
}
