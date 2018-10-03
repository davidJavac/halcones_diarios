package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_zonasVO;
import modelo_vo.Pago_diarioVO;

public class Caja_zonaDAO {

	private static final String  sql_buscarcajazona_duplicado= "select * from caja_zonas where fecha_registro = ? and id_zona = ?";
	private static final String sql_buscarpagodiario_porPedido = "select * from pago_diario where n_pedido = ?";
	private static final String sql_select_buscarCaja_zona = "select * from caja_zonas where fecha_registro = ?";
	private static final String sql_select_buscarCaja_zonaEntreFechas = "select * from caja_zonas where"
			+ " id_zona = ? and (fecha_registro between ? and ?)";
	
	
	private static final String sql_insertCaja_zona = "insert into caja_zonas (id_zona, detalle, ingresos, "
			+ "faltante, sobrante,monto_ideal, efectividad, observaciones, _1000, _500, _200, _100, _50, _20, _10, _5, _2 ,_1 ,_0_50, _0_25, id_usuario,"
			+ "fecha_registro"
			+ ", hora_registro) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sql_select_seguirPagos = "select g.n_pedido, g.nombre_producto, g.importe, "
			+ "g.id_combinacion, c.nombre, c.apellido, u.nombre, g.fecha, g.fecha_registro, g.hora_registro"
			+ " from pago_diario g inner join cobradores c on g.id_cobrador = c.id_cobrador inner join usuarios u"
			+ " on g.id_usuario = u.id_usuario where g.n_pedido = ? order by g.fecha asc";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public boolean pago_duplicado(Caja_zonasVO _caja_zonaVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarcajazona_duplicado);
			sentencia.setDate(1, _caja_zonaVO.getFecha());
			sentencia.setShort(2, _caja_zonaVO.getId_zona());
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
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
			return true;
		}
		else return false;
	}
	
	public ArrayList<Caja_zonasVO> caja_zonaEntreFechas(short id_zona, 
			java.sql.Date desde, java.sql.Date hasta) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		ArrayList<Caja_zonasVO> ar = new ArrayList<Caja_zonasVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_zonaEntreFechas);
			
			sentencia.setShort(1, id_zona);
			sentencia.setDate(2, desde);
			sentencia.setDate(3, hasta);
			
			res = sentencia.executeQuery();
			
			System.out.println(desde + " " + hasta);
			
			while(res.next()){
				
				Caja_zonasVO cVO = new Caja_zonasVO();
				
				existe = true;
				
				cVO.setId_zona(res.getShort(1));
				cVO.setDetalle(res.getString(2));
				cVO.setIngresos(res.getDouble(3));
				cVO.setIFaltante(res.getDouble(4));
				cVO.setSobrante(res.getDouble(5));
				cVO.setMonto_ideal(res.getDouble(6));
				cVO.setEfectividad(res.getDouble(7));
				cVO.setObservaciones(res.getString(8));
				cVO.set_1000(res.getShort(9));
				cVO.set_500(res.getShort(10));
				cVO.set_200(res.getShort(11));
				cVO.set_100(res.getShort(12));
				cVO.set_50(res.getShort(13));
				cVO.set_20(res.getShort(14));
				cVO.set_10(res.getShort(15));
				cVO.set_5(res.getShort(16));
				cVO.set_2(res.getShort(17));
				cVO.set_1(res.getShort(18));
				cVO.set_0_50(res.getShort(19));
				cVO.set_0_25(res.getShort(20));
				cVO.setId_usuario(res.getShort(21));
				cVO.setFecha_registro(res.getDate(22));
				cVO.setHora_registro(res.getTime(22));
				
				ar.add(cVO);
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
			System.out.println("exite pedido");	
			return ar;
		}
		else return null;
	}
	
	public ArrayList<Caja_zonasVO> buscarCaja_zona(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		ArrayList<Caja_zonasVO> ar = new ArrayList<Caja_zonasVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_zona);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Caja_zonasVO cVO = new Caja_zonasVO();
				
				existe = true;
				
				cVO.setId_zona(res.getShort(1));
				cVO.setDetalle(res.getString(2));
				cVO.setIngresos(res.getDouble(3));
				cVO.setIFaltante(res.getDouble(4));
				cVO.setSobrante(res.getDouble(5));
				cVO.setMonto_ideal(res.getDouble(6));
				cVO.setEfectividad(res.getDouble(7));
				cVO.setObservaciones(res.getString(8));
				cVO.set_1000(res.getShort(9));
				cVO.set_500(res.getShort(10));
				cVO.set_200(res.getShort(11));
				cVO.set_100(res.getShort(12));
				cVO.set_50(res.getShort(13));
				cVO.set_20(res.getShort(14));
				cVO.set_10(res.getShort(15));
				cVO.set_5(res.getShort(16));
				cVO.set_2(res.getShort(17));
				cVO.set_1(res.getShort(18));
				cVO.set_0_50(res.getShort(19));
				cVO.set_0_25(res.getShort(20));
				cVO.setId_usuario(res.getShort(21));
				cVO.setFecha_registro(res.getDate(22));
				cVO.setHora_registro(res.getTime(23));
				
				ar.add(cVO);
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
			System.out.println("exite pedido");	
			return ar;
		}
		else return null;
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
			System.out.println("exite pedido");	
			return a;
		}
		else return null;
		
		
	}
	
	public int ingresos(Caja_zonasVO _caja_zonaVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja_zona);
			

			sentencia.setShort(1, _caja_zonaVO.getId_zona());
			sentencia.setString(2, _caja_zonaVO.getDetalle());
			sentencia.setDouble(3, _caja_zonaVO.getIngresos());
			sentencia.setDouble(4, _caja_zonaVO.getFaltante());
			sentencia.setDouble(5, _caja_zonaVO.getSobrante());
			sentencia.setDouble(6, _caja_zonaVO.getMonto_ideal());
			sentencia.setDouble(7, _caja_zonaVO.getEfectividad());
			sentencia.setString(8, _caja_zonaVO.getObservaciones());
			sentencia.setShort(9, _caja_zonaVO.get_1000());
			sentencia.setShort(10, _caja_zonaVO.get_500());
			sentencia.setShort(11, _caja_zonaVO.get_200());
			sentencia.setShort(12, _caja_zonaVO.get_100());
			sentencia.setShort(13, _caja_zonaVO.get_50());
			sentencia.setShort(14, _caja_zonaVO.get_20());
			sentencia.setShort(15, _caja_zonaVO.get_10());
			sentencia.setShort(16, _caja_zonaVO.get_5());
			sentencia.setShort(17, _caja_zonaVO.get_2());
			sentencia.setShort(18, _caja_zonaVO.get_1());
			sentencia.setShort(19, _caja_zonaVO.get_0_50());
			sentencia.setShort(20, _caja_zonaVO.get_0_25());
			sentencia.setShort(21, _caja_zonaVO.getId_usuario());
			sentencia.setDate(22, _caja_zonaVO.getFecha_registro());
			sentencia.setTime(23, _caja_zonaVO.getHora_registro());
			
			
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
