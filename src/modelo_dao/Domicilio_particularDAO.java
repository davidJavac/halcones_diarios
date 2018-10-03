package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;

public class Domicilio_particularDAO {

	/*private static final String sql_buscardomicilio_particular = "select * from domicilio_particular"
			+ " where dni = ?";*/
	private static final String sql_buscardomicilio_particular = "select * from domicilio_particular d inner join clientes c"
			+ " on d.dni = c.dni where c.dni = ?";
	
	private static final String sql_insertardomicilio_particular = "insert into domicilio_particular(dni, domicilio,"
			+ "entre_calle1, entre_calle2, barrio, cp, id_localidad, antiguedad,"
			+ "propio, dpto, piso)"
			+ " values(?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
	private static final String sql_updateDomPart = "update domicilio_particular set dni =?,  domicilio = ?,"
			+ " entre_calle1= ?"
			+ " ,entre_calle2 = ?,"
			+ " dpto = ?, piso = ?,  barrio = ?, "
			+ "cp = ?, id_localidad= ?,"
			+ "antiguedad = ?, propio = ? where dni = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public DomicilioParticularVO buscarDomicilio_particular(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
		DomicilioParticularVO dp = new DomicilioParticularVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardomicilio_particular);
			
			sentencia.setInt(1, dni);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				dp.setDni(res.getInt(1));
				dp.setDomicilio(res.getString(2));
				dp.setEntre_calle1(res.getString(3));
				dp.setEntre_calle2(res.getString(4));
				dp.setBarrio(res.getString(5));
				dp.setCp(res.getInt(6));
				dp.setId_localidad(res.getShort(7));
				dp.setDpto(res.getBoolean(8));
				dp.setPiso(res.getShort(9));
				dp.setAntiguedad(res.getDate(10));
				dp.setPropio(res.getBoolean(11));
				
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
			LogicaCliente.validaralta = false;
			return dp;
		}
		else return null;
		
	}
	
	public int modificarDomPart(DomicilioParticularVO _domicilio_particularVO,int dni, int dni2) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateDomPart);
					
			sentencia.setInt(1, dni);
			sentencia.setString(2, _domicilio_particularVO.getDomicilio());
			sentencia.setString(3, _domicilio_particularVO.getEntre_calle1());
			sentencia.setString(4, _domicilio_particularVO.getEntre_calle2());
			sentencia.setBoolean(5, _domicilio_particularVO.getDpto());
			sentencia.setShort(6, _domicilio_particularVO.getPiso());
			sentencia.setString(7, _domicilio_particularVO.getBarrio());
			sentencia.setInt(8, _domicilio_particularVO.getCp());
			sentencia.setShort(9, _domicilio_particularVO.getId_localidad());
			sentencia.setDate(10, _domicilio_particularVO.getAntiguedad());
			sentencia.setBoolean(11, _domicilio_particularVO.getPropio());
			sentencia.setInt(12, dni2);
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
	
	public int insertarDomicilio_particular(DomicilioParticularVO _domicilio_particularVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertardomicilio_particular);
			
			sentencia.setInt(1, _domicilio_particularVO.getDni());
			sentencia.setString(2, _domicilio_particularVO.getDomicilio());
			sentencia.setString(3, _domicilio_particularVO.getEntre_calle1());
			sentencia.setString(4, _domicilio_particularVO.getEntre_calle2());
			sentencia.setString(5, _domicilio_particularVO.getBarrio());
			sentencia.setInt(6, _domicilio_particularVO.getCp());
			sentencia.setShort(7, _domicilio_particularVO.getId_localidad());
			sentencia.setDate(8, _domicilio_particularVO.getAntiguedad());
			sentencia.setBoolean(9, _domicilio_particularVO.getPropio());
			sentencia.setBoolean(10, _domicilio_particularVO.getDpto());
			sentencia.setShort(11, _domicilio_particularVO.getPiso());
			
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
