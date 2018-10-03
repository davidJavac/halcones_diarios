package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.CajaDiariaTotalVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Pago_diarioVO;

public class CajaDiariaTotalDAO {

	
	private static final String  sql_buscarcaja_duplicado= "select * from caja_diaria_total where fecha_registro = ?";
	private static final String sql_buscarpagodiario_porPedido = "select * from pago_diario where n_pedido = ?";
	private static final String sql_select_buscarCaja_zona = "select * from caja_zonas where fecha = ?";
	private static final String sql_select_buscarCaja_diaria_total = "select * from caja_diaria_total"
			+ " where fecha_registro = ?";
	
	private static final String sql_insertCaja = "insert into caja_diaria_total (fecha_registro, ingresos, "
			+ "egresos, sobrante, faltante, observaciones, _1000, _500, _200, _100, _50,"
			+ " _20, _10, _5, _2 ,_1 ,_0_50, _0_25,"
			+ " id_usuario, "
			+ " hora_registro) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?,?)";
	private static final String sql_select_seguirPagos = "select g.n_pedido, g.nombre_producto, g.importe, "
			+ "g.id_combinacion, c.nombre, c.apellido, u.nombre, g.fecha, g.fecha_registro, g.hora_registro"
			+ " from pago_diario g inner join cobradores c on g.id_cobrador = c.id_cobrador inner join usuarios u"
			+ " on g.id_usuario = u.id_usuario where g.n_pedido = ? order by g.fecha asc";
	
	private static final String spBuscar_ingresosCajaDiariaTotal = "{call ingresos_cajaDiariaTotal(?)}";
	private static final String spBuscar_egresosCajaDiariaTotal = "{call egresos_cajaDiariaTotal(?)}";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public CajaDiariaTotalVO buscarCajaDiariaTotal_porFecha(java.sql.Date fecha)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		CajaDiariaTotalVO cVO = new CajaDiariaTotalVO();
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_diaria_total);
			
			sentencia.setDate(1, fecha);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				
				cVO.setFecha_registro(res.getDate(1));
				cVO.setIngresos(res.getDouble(2));
				cVO.setEgresos(res.getDouble(3));
				cVO.setSobrante(res.getDouble(4));
				cVO.setFaltante(res.getDouble(5));
				cVO.setObservaciones(res.getString(6));
				cVO.set_1000(res.getShort(7));
				cVO.set_500(res.getShort(8));
				cVO.set_200(res.getShort(9));
				cVO.set_100(res.getShort(10));
				cVO.set_50(res.getShort(11));
				cVO.set_20(res.getShort(12));
				cVO.set_10(res.getShort(13));
				cVO.set_5(res.getShort(14));
				cVO.set_2(res.getShort(15));
				cVO.set_1(res.getShort(16));
				cVO.set_0_50(res.getShort(17));
				cVO.set_0_25(res.getShort(18));
				cVO.setId_usuario(res.getShort(19));
				cVO.setHora_registro(res.getTime(20));
				
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
		
		if(existe){
			return cVO;
		}
		else return null;
	}
	
	public boolean pago_duplicado(java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarcaja_duplicado);
			
			sentencia.setDate(1, fecha);
			
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
		
		
		if(existe){
			return true;
		}
		else return false;
	}
	
	public double buscar_ingresosCajaDiariaTotal(java.sql.Date fecha) throws SQLException{
		
		CallableStatement stmt = null;

		ResultSet res;
		
		boolean existe = false;
		
		double acumulado = 0;
		
		double acu_zonas = 0;
		double acu_interna = 0;
		double acu_despacho = 0;
		double acu_sobrante = 0;
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = (CallableStatement) sqlConexion.prepareCall(spBuscar_ingresosCajaDiariaTotal);
			
			stmt.setDate(1, fecha);
			stmt.execute();
			res =  stmt.getResultSet();	
			
			
			while(res.next()){

				existe = true;
				
				acu_zonas = res.getDouble(1);
			
			}
			
			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){

				existe = true;
				
				acu_interna = res.getDouble(1);
				
			}
			
			
			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){

				existe = true;
				
				acu_despacho = res.getDouble(1);
				
			}
			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){
				
				existe = true;
				
				acu_sobrante = res.getDouble(1);
				
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
			
			acumulado = acu_zonas + acu_interna + acu_despacho + acu_sobrante;	
			return acumulado;
		}
		else return 0;
	}
	
	public double buscar_egresosCajaDiariaTotal(java.sql.Date fecha)
			throws SQLException{

		CallableStatement stmt = null;

		ResultSet res;
		
		boolean existe = false;
		double acumulado = 0;
		
		double acu_sueldos = 0;

		double acu_proveedores = 0;
		double acu_monedas_egreso = 0;
		double acu_interna = 0;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = (CallableStatement) sqlConexion.prepareCall(spBuscar_egresosCajaDiariaTotal);
			
			stmt.setDate(1, fecha);
		
			res =  stmt.executeQuery();	
			
			
			while(res.next()){

				existe = true;
				
				acu_sueldos = res.getDouble(1);
				System.out.println("sueldos" + acu_sueldos);
			
			}

			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){

				existe = true;
				
				acu_proveedores = res.getDouble(1);
				System.out.println("prov" + acu_proveedores);
				
			}
			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){
				
				existe = true;
				
				acu_monedas_egreso = res.getDouble(1);
				System.out.println("monedas" + acu_monedas_egreso);
				
			}
			
			stmt.getMoreResults();
			res =  stmt.getResultSet();
			
			while(res.next()){

				existe = true;
				
				acu_interna = res.getDouble(1);
				System.out.println("interna" + acu_interna);
				
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
			
			acumulado = acu_sueldos + acu_proveedores +
					acu_monedas_egreso + acu_interna;
		
			
			System.out.println("acumulado" + acumulado);
			return acumulado;
		}
		else return 0;
	}
	
	public ArrayList<Object[]> seguirPagos(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_seguirPagos);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Object [] datos = new Object[10];
						
				for(int i = 0; i < 10; i++){
					
					datos[i] = res.getObject(i + 1);
				}
				
				a.add(datos);
		
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
				
			return a;
		}
		else return null;
		
		
	}
	
	public int ingresos(CajaDiariaTotalVO _cajaVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja);
			
			sentencia.setDate(1, _cajaVO.getFecha_registro());
			sentencia.setDouble(2, _cajaVO.getIngresos());
			sentencia.setDouble(3, _cajaVO.getEgresos());
			sentencia.setDouble(4, _cajaVO.getSobrante());
			sentencia.setDouble(5, _cajaVO.getFaltante());
			sentencia.setString(6, _cajaVO.getObservaciones());
			sentencia.setShort(7, _cajaVO.get_1000());
			sentencia.setShort(8, _cajaVO.get_500());
			sentencia.setShort(9, _cajaVO.get_200());
			sentencia.setShort(10, _cajaVO.get_100());
			sentencia.setShort(11, _cajaVO.get_50());
			sentencia.setShort(12, _cajaVO.get_20());
			sentencia.setShort(13, _cajaVO.get_10());
			sentencia.setShort(14, _cajaVO.get_5());
			sentencia.setShort(15, _cajaVO.get_2());
			sentencia.setShort(16, _cajaVO.get_1());
			sentencia.setShort(17, _cajaVO.get_0_50());
			sentencia.setShort(18, _cajaVO.get_0_25());
			sentencia.setShort(19, _cajaVO.getId_usuario());
			sentencia.setTime(20, _cajaVO.getHora_registro());
			
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
