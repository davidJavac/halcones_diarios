package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.VendedorVO;
import modelo_vo.Vendedores_padre_hijoVO;
import modelo_vo.VerazVO;

public class EmpleadoDAO {
	
	private static final String sql_buscarEmpleadoUsuario = "select * from empleados where estado = ? and id = ?";
	
	private static final String sql_buscarEmpleado = "select * from empleados where estado = ? and id = ?";
	
	private static final String sql_buscarEmpleados_porPuesto = "select * from empleados "
			+ "where estado = ? and puesto = ?";
	
	private static final String sql_buscarEmpleadosAll = "select * from empleados where estado <> ?";
	private static final String sql_buscarFNempleado = "select * from empleados where"
			+ " DATE_FORMAT(fecha_nacimiento, '%m-%d') = DATE_FORMAT(?, '%m-%d')";
	private static final String spModificar_empleado = "{call modificar_empleado(?,?,?,?,?,?,?,?,"
																					+ "?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String spModificarEstado_empleado = "update empleados set estado = ? where id = ?";
	
	private static final String spAlta_empleado = "{call alta_empleado(?,?,?,?,?,?,?,"
			+ "?,?,?,?,?,?,?,?,?,?, ?)}";
	
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public String buscarEmpleadoUsuario(short id_empleado) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		boolean existe = false;
		String empleado = "";
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleadoUsuario);
			
			sentencia.setBoolean(1, true);
			sentencia.setShort(2, id_empleado);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
			
				empleado = res.getString(4) + " " + res.getString(5);
			
				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return empleado;
		else return null;
		
	}
	
	public EmpleadoVO buscarEmpleado(short id_empleado) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		
		EmpleadoVO eVO = new EmpleadoVO();
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleado);
			sentencia.setBoolean(1, true);
			sentencia.setShort(2, id_empleado);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
			
				eVO.setId_usuario(res.getShort(1));
				eVO.setDni(res.getInt(2));
				eVO.setPuesto(res.getString(3));
				eVO.setNombre(res.getString(4));
				eVO.setApellido(res.getString(5));
				eVO.setSalario_semanal(res.getDouble(6));
				eVO.setCalle(res.getString(7));
				eVO.setNumero(res.getInt(8));
				eVO.setLocalidad(res.getString(9));
				eVO.setTelefono(res.getString(10));
				eVO.setEmail(res.getString(11));
				eVO.setEstado(res.getBoolean(12));
				//eVO.setId_usuario(res.getShort(12));
				eVO.setFecha_nacimiento(res.getDate(13));
				eVO.setFecha_alta(res.getDate(14));

				sqlConexion.commit();
			}	
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}

		if(existe) 	return eVO;
		else return null;
		
	}
	
	public ArrayList<EmpleadoVO> buscarEmpleados_porPuesto(String puesto) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		ArrayList<EmpleadoVO> ar = new ArrayList<EmpleadoVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleados_porPuesto);
			
			sentencia.setBoolean(1, true);
			sentencia.setString(2, puesto);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				EmpleadoVO eVO = new EmpleadoVO();
				
				existe = true;
			
				eVO.setId_usuario(res.getShort(1));
				eVO.setDni(res.getInt(2));
				eVO.setPuesto(res.getString(3));
				eVO.setNombre(res.getString(4));
				eVO.setApellido(res.getString(5));
				eVO.setSalario_semanal(res.getDouble(6));
				eVO.setCalle(res.getString(7));
				eVO.setNumero(res.getInt(8));
				eVO.setLocalidad(res.getString(9));
				eVO.setTelefono(res.getString(10));
				eVO.setEmail(res.getString(11));
				eVO.setEstado(res.getBoolean(12));
				//eVO.setId_usuario(res.getShort(12));
				eVO.setFecha_nacimiento(res.getDate(13));
				eVO.setFecha_alta(res.getDate(14));

				ar.add(eVO);
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
	

	public ArrayList<Object []> buscarEmpleadoAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		String nombre = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarEmpleadosAll);
			
			sentencia.setBoolean(1, false);
			
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
	
	public int modificarEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO) throws SQLException{
		
		CallableStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = (CallableStatement) sqlConexion.prepareCall(spModificar_empleado);
			
			stmt.setInt(1, empleadoVO.getId_usuario());
			stmt.setInt(2, empleadoVO.getDni());
			stmt.setString(3, empleadoVO.getPuesto());
			stmt.setString(4, empleadoVO.getNombre() );
			stmt.setString(5, empleadoVO.getApellido());
			stmt.setDouble(6, empleadoVO.getSalario_semanal());
			stmt.setString(7, empleadoVO.getCalle());
			stmt.setInt(8, empleadoVO.getNumero());
			stmt.setString(9, empleadoVO.getLocalidad());
			stmt.setString(10, empleadoVO.getTelefono());
			stmt.setString(11, empleadoVO.getEmail());
		
			if(empleadoVO.getEstado()) {
				short i= 1;
				stmt.setShort(12, i);
			}
			if(!empleadoVO.getEstado()) {
				short i= 0;
				stmt.setShort(12, i);
			}
			
			stmt.setDate(13, empleadoVO.getFecha_nacimiento());
			stmt.setDate(14, empleadoVO.getFecha_alta());		
			stmt.setDouble(15, cobradorVO.getPremio());
			stmt.setString(16, cobradorVO.getAuto_modelo());
			stmt.setString(17, cobradorVO.getPatente());
			stmt.setDouble(18, vendedorVO.getComision());
			stmt.setDouble(19, vendedorVO.getComision_prestamo());
			
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
	public int modificarEstadoEmpleado(short id, short estado) throws SQLException{
		
		java.sql.CallableStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spModificarEstado_empleado);
			stmt.setShort(1, estado);
			stmt.setShort(2, id);
			
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

	public int nuevoEmpleado(EmpleadoVO empleadoVO, CobradorVO cobradorVO,
			VendedorVO vendedorVO) throws SQLException{
		
		java.sql.CallableStatement stmt =  null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt =  sqlConexion.prepareCall(spAlta_empleado);
			
			stmt.setInt(1, empleadoVO.getDni());
			stmt.setString(2, empleadoVO.getPuesto());
			stmt.setString(3, empleadoVO.getNombre() );
			stmt.setString(4, empleadoVO.getApellido());
			stmt.setDouble(5, empleadoVO.getSalario_semanal());
			stmt.setString(6, empleadoVO.getCalle());
			stmt.setInt(7, empleadoVO.getNumero());
			stmt.setString(8, empleadoVO.getLocalidad());
			stmt.setString(9, empleadoVO.getTelefono());
			stmt.setString(10, empleadoVO.getEmail());
			
			short i= 1;
			stmt.setShort(11, i);
			
			stmt.setDate(12, empleadoVO.getFecha_nacimiento());
			stmt.setDate(13, empleadoVO.getFecha_alta());		
			stmt.setDouble(14, cobradorVO.getPremio());
			stmt.setString(15, cobradorVO.getAuto_modelo());
			stmt.setString(16, cobradorVO.getPatente());
			stmt.setDouble(17, vendedorVO.getComision());
			stmt.setDouble(18, vendedorVO.getComision_prestamo());
			
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
	public ArrayList<EmpleadoVO> comprobarFecha_nacimiento(java.sql.Date hoy) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<EmpleadoVO> ar = new ArrayList<EmpleadoVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarFNempleado);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				EmpleadoVO eVO = new EmpleadoVO();
				
				eVO.setId_usuario(res.getShort(1));
				eVO.setDni(res.getInt(2));
				eVO.setPuesto(res.getString(3));
				eVO.setNombre(res.getString(4));
				eVO.setApellido(res.getString(5));
				eVO.setSalario_semanal(res.getDouble(6));
				eVO.setCalle(res.getString(7));
				eVO.setNumero(res.getInt(8));
				eVO.setLocalidad(res.getString(9));
				eVO.setTelefono(res.getString(10));
				eVO.setEmail(res.getString(11));
				eVO.setEstado(res.getBoolean(12));
				//eVO.setId_usuario(res.getShort(12));
				eVO.setFecha_nacimiento(res.getDate(13));
				eVO.setFecha_alta(res.getDate(14));
			
				ar.add(eVO);
			}
			
			sqlConexion.commit();
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
	
}
