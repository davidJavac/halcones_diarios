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

public class AdministrativoDAO {
	
	private static final String sql_buscarAdministrativoUsuario = "select * from administrativos where estado = ? and "
			+ "id_administrativo = ?";
	
	private static final String sql_buscarAdministrativoAll = "select * from administrativos where estado <> ?";
			
	private static final String sql_buscarAdministrativoUsuario2 = "select * from empleados where estado = ? and "
			+ " puesto = ? and id = ?";
	
	private static final String sql_buscarAdministrativoAll2 = "select * from empleados where puesto = ? and estado <> ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public String buscarAdministrativoUsuario(short id_administrativo) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarAdministrativoUsuario2);
		ResultSet res;

		
		String administrativo = "";
	
		sentencia.setBoolean(1, true);
		sentencia.setString(2, "administrativo");
		sentencia.setShort(3, id_administrativo);
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
		
			existe = true;
		
			administrativo = res.getString(4) + " " + res.getString(5);
		
		}	

		if(existe) 	return administrativo;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarAdministrativoAll() throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarAdministrativoAll2);
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		
		sentencia.setString(1, "administrativo");
		sentencia.setBoolean(2, false);
		
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
