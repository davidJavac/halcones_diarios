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
import modelo_vo.VerazVO;

public class Jefe_calleDAO {
	
	private static final String sql_buscarJefeUsuario = "select * from jefe_calle where estado = ? and id_jefe = ?";
	
	private static final String sql_buscarJefecalleAll = "select * from jefe_calle where estado <> ?";
			
	private static final String sql_buscarJefeUsuario2 = "select * from empleados where estado = ? and puesto = ? and "
			+ " id = ?";
	
	private static final String sql_buscarJefecalleAll2 = "select * from empleados where puesto = ? and estado <> ?";	
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public String buscarJefeUsuario(short id_jefe) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarJefeUsuario2);
		ResultSet res;

		
		String jefe = "";
	
		sentencia.setBoolean(1, true);
		sentencia.setString(2, "jefe_calle");
		sentencia.setShort(3, id_jefe);
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
		
			existe = true;
		
			jefe = res.getString(4) + " " + res.getString(5);
		
		}	

		if(existe) 	return jefe;
		else return null;
		
	}
	
	
	public ArrayList<Object []> buscarJefe_calleAll() throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarJefecalleAll2);
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		
		sentencia.setString(1, "jefe_calle");
		sentencia.setString(2, "baja");
		
		res = sentencia.executeQuery();
		
		int cont = 0;
		
		boolean existe = false;
		
		while(res.next()){
		
			Object objetos [] = new Object[2];
			
			existe = true;	
			
			objetos [0] = res.getInt(1);

			objetos [1] = res.getString(4) + " " + res.getString(5);
			
			ar.add(objetos);
		}	

		if(existe) 	return ar;
		else return null;
		
	}
	
}
