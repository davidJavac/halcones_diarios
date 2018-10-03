package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pago_diarioVO;

public class Despacho_diarioDAO {

	private static final String  sql_buscarpagodespacho_duplicado= "select * from despacho_diario where n_pedido = ?"
			+ " and fecha_registro = ?";
	private static final String sql_buscardespacho_porFecha = "select * from despacho_diario where fecha_registro= ?";
	private static final String sql_buscardespacho_entreFechas = 
			"select * from despacho_diario where fecha_registro between ? and ? order by fecha_registro desc";
	private static final String sql_buscardespachoAll = "select * from despacho_diario";
	private static final String sql_buscardespacho_porPedido = "select * from despacho_diario where n_pedido= ?";
	private static final String sql_buscardespacho_porEstadoFecha = "select * from despacho_diario where estado= ?"
			+ " and fecha_registro = ?";
	private static final String sql_buscaringreso_Hoy = "select dp.* from despacho_diario dp inner join pedidos p on "
			+ " dp.n_pedido = p.n_pedido inner join domicilio_comercial dc on p.idc = dc.idc"
			+ " inner join zonas z on"
			+ " dc.id_zona = z.id_zona inner join empleados e on z.id_cobrador = e.id inner join pago_diario pd"
			+ " on e.id = pd.id_cobrador where dp.n_pedido = ? and pd.fecha_registro = ? and e.puesto = ?";
	private static final String sql_insertDespacho = "insert into despacho_diario (n_pedido,entrega, nombre, estado, "
			+ "monto, observaciones, id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?,?,?,?)";
	private static final String sql_updateDespacho = "update despacho_diario set estado = ?, monto = ?, observaciones =?,"
			+ " id_usuario = ?, hora_registro = ? where n_pedido = ?";
	private static final String sql_select_seguirPagos = "select g.n_pedido, g.nombre_producto, g.importe, "
			+ "g.id_combinacion, c.nombre, c.apellido, u.nombre, g.fecha_registro, g.hora_registro"
			+ " from pago_diario g inner join cobradores c on g.id_cobrador = c.id_cobrador inner join usuarios u"
			+ " on g.id_usuario = u.id_usuario where g.n_pedido = ? order by g.fecha_registro asc";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	
	public boolean validacionEntrega(Despacho_diarioVO dpVO, java.sql.Date hoy) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscaringreso_Hoy);
			
			sentencia.setInt(1, dpVO.getN_pedido());
			sentencia.setDate(2, hoy);
			sentencia.setString(3, "cobrador");
			
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
	
	public boolean despacho_duplicado(int n_pedido, java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarpagodespacho_duplicado);
			
			sentencia.setInt(1, n_pedido);
			sentencia.setDate(2, fecha);
			
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
	
	public ArrayList<Despacho_diarioVO> buscarDespachoDiario_porFecha(java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Despacho_diarioVO> a = new ArrayList<Despacho_diarioVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardespacho_porFecha);
			
			sentencia.setDate(1, fecha);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Despacho_diarioVO _dpVO = new Despacho_diarioVO();
		
				
				_dpVO.setN_pedido(res.getInt(1));
				_dpVO.setEntrega(res.getString(2));
				_dpVO.setNombre(res.getString(3));
				_dpVO.setEstado(res.getString(4));
				_dpVO.setMonto(res.getDouble(5));
				_dpVO.setObservaciones(res.getString(6));
				_dpVO.setId_usuario(res.getShort(7));
				_dpVO.setFecha_registro(res.getDate(8));
				_dpVO.setHora_registro(res.getTime(9));
						
				a.add(_dpVO);
		
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
	public ArrayList<Despacho_diarioVO> buscarDespacho_entreFechas(java.sql.Date desde,
			java.sql.Date hasta) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Despacho_diarioVO> a = new ArrayList<Despacho_diarioVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardespacho_entreFechas);
			
			sentencia.setDate(1, desde);
			sentencia.setDate(2, hasta);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Despacho_diarioVO _dpVO = new Despacho_diarioVO();
				
				
				_dpVO.setN_pedido(res.getInt(1));
				_dpVO.setEntrega(res.getString(2));
				_dpVO.setNombre(res.getString(3));
				_dpVO.setEstado(res.getString(4));
				_dpVO.setMonto(res.getDouble(5));
				_dpVO.setObservaciones(res.getString(6));
				_dpVO.setId_usuario(res.getShort(7));
				_dpVO.setFecha_registro(res.getDate(8));
				_dpVO.setHora_registro(res.getTime(9));
				
				a.add(_dpVO);
				
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
	
	public ArrayList<Despacho_diarioVO> buscarDespachoDiario_porFechaEstado(java.sql.Date fecha,
			String estado) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Despacho_diarioVO> a = new ArrayList<Despacho_diarioVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardespacho_porEstadoFecha);
			
			sentencia.setString(1, estado);
			sentencia.setDate(2, fecha);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Despacho_diarioVO _dpVO = new Despacho_diarioVO();
		
				
				_dpVO.setN_pedido(res.getInt(1));
				_dpVO.setEntrega(res.getString(2));
				_dpVO.setNombre(res.getString(3));
				_dpVO.setEstado(res.getString(4));
				_dpVO.setMonto(res.getDouble(5));
				_dpVO.setObservaciones(res.getString(6));
				_dpVO.setId_usuario(res.getShort(7));
				_dpVO.setFecha_registro(res.getDate(8));
				_dpVO.setHora_registro(res.getTime(9));
						
				a.add(_dpVO);
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
			return a;
		}
		else return null;
		
		
	}
	
	public ArrayList<Despacho_diarioVO> buscarDespachoDiarioAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Despacho_diarioVO> a = new ArrayList<Despacho_diarioVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardespachoAll);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Despacho_diarioVO _dpVO = new Despacho_diarioVO();
		
				
				_dpVO.setN_pedido(res.getInt(1));
				_dpVO.setEntrega(res.getString(2));
				_dpVO.setNombre(res.getString(3));
				_dpVO.setEstado(res.getString(4));
				_dpVO.setMonto(res.getDouble(5));
				_dpVO.setObservaciones(res.getString(6));
				_dpVO.setId_usuario(res.getShort(7));
				_dpVO.setFecha_registro(res.getDate(8));
				_dpVO.setHora_registro(res.getTime(9));
						
				a.add(_dpVO);
		
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
			return a;
		}
		else return null;
		
		
	}
	
	public Despacho_diarioVO buscarDespachoDiario_porPedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		Despacho_diarioVO _dpVO = new Despacho_diarioVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardespacho_porPedido);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_dpVO.setN_pedido(res.getInt(1));
				_dpVO.setEntrega(res.getString(2));
				_dpVO.setNombre(res.getString(3));
				_dpVO.setEstado(res.getString(4));
				_dpVO.setMonto(res.getDouble(5));
				_dpVO.setObservaciones(res.getString(6));
				_dpVO.setId_usuario(res.getShort(7));
				_dpVO.setFecha_registro(res.getDate(8));
				_dpVO.setHora_registro(res.getTime(9));
				
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
			return _dpVO;
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
	
	public int ingresos(Despacho_diarioVO dpVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertDespacho);
			
			sentencia.setInt(1, dpVO.getN_pedido());
			sentencia.setString(2, dpVO.getEntrega());
			sentencia.setString(3, dpVO.getNombre());
			sentencia.setString(4, dpVO.getEstado());
			sentencia.setDouble(5, dpVO.getMonto());
			sentencia.setString(6, dpVO.getObservaciones());
			sentencia.setInt(7, dpVO.getId_usuario());
			sentencia.setDate(8, dpVO.getFecha_registro());
			sentencia.setTime(9, dpVO.getHora_registro());
			
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
	
	
	
	public int updateDespacho(Despacho_diarioVO dpVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateDespacho);
			sentencia.setString(1, dpVO.getEstado());
			sentencia.setDouble(2, dpVO.getMonto());
			sentencia.setString(3, dpVO.getObservaciones());
			sentencia.setInt(4, dpVO.getId_usuario());
			//sentencia.setDate(5, dpVO.getFecha_registro());
			sentencia.setTime(5, dpVO.getHora_registro());
			sentencia.setInt(6, dpVO.getN_pedido());
			
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
