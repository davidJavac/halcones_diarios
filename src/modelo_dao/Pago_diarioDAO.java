package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LocalidadVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;
import modelo_vo.ZonaVO;

public class Pago_diarioDAO {
	
	private static final String  sql_buscarpagodiario_duplicado= "select * from pago_diario pd inner join pedidos p"
			+ " on pd.n_pedido = p.n_pedido inner join domicilio_comercial dc on p.idc = dc.idc inner join zonas z on "
			+ " dc.id_zona = z.id_zona where pd.fecha_registro = ? and z.id_zona = ?";
	private static final String sql_buscarpagodiario_porPedido = "select * from pago_diario where n_pedido = ?";
	private static final String sql_insertPagos = "insert into pago_diario (n_pedido, nombre_producto, importe,cuota, "
			+ " id_cobrador, id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?,?,?)";
	
	private static final String sql_updatePagos = "update pago_diario set importe = ? where n_pedido = ? and "
			+ "fecha_registro = ?";
	private static final String sql_select_seguirPagos = "select g.n_pedido, g.nombre_producto, g.importe, "
			+ " e.nombre, e.apellido, u.nombre, g.fecha_registro, g.hora_registro"
			+ " from pago_diario g inner join empleados e on g.id_cobrador = e.id inner join usuarios u"
			+ " on g.id_usuario = u.id_usuario where g.n_pedido = ? and e.puesto = ? order by g.fecha_registro asc";
	
	private static final String sql_select_seguirPagos_porFecha = "select g.n_pedido, g.nombre_producto, g.importe, "
			+ " e.nombre, e.apellido, u.nombre, g.fecha_registro, g.hora_registro"
			+ " from pago_diario g inner join zonas z on g.id_cobrador = z.id_cobrador inner join "
			+ "empleados e on z.id_cobrador = e.id inner join usuarios u"
			+ " on g.id_usuario = u.id_usuario where g.n_pedido = ? and e.puesto = ? order by g.fecha_registro asc";
	
	private static final String sql_select_seguirPagos_porFecha2 = " select distinct e.id,e.nombre, e.apellido,"
			+ " pg.*, c.dni, c.nombre,"
			+ "c.apellido, c.id_zona, c.n_orden_planilla, dc.domicilio, dc.horario_atencion, l.localidad from "
			+ " zonas z inner join empleados e on z.id_cobrador = e.id inner join pago_diario pg on "
			+ "  e.id = pg.id_cobrador inner join pedidos p on pg.n_pedido = p.n_pedido "
			+ "inner join domicilio_comercial dc on p.idc = dc.idc inner join clientes c on dc.dni = c.dni inner join "
			+ "localidad l on dc.id_localidad = l.id_localidad "
			+ "where z.id_zona = ? and pg.fecha_registro = ? and e.puesto = ? order by c.n_orden_planilla";
	
	private static final String sql_select_buscarPagos_porFecha = "select * from pago_diario pg inner join"
			+ " pedidos p on pg.n_pedido = p.n_pedido inner join domicilio_comercial dc on p.idc = dc.idc "
			+ " where pg.fecha_registro = ? and dc.id_zona = ? order by dc.n_orden_planilla";
	
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
  public ArrayList<Object []> buscarPagos_porFecha(short zona, java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
		int cont = 0;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_seguirPagos_porFecha2);
			
			sentencia.setShort(1, zona);
			sentencia.setDate(2, fecha);
			sentencia.setString(3, "cobrador");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				cont++;
				PedidosVO _pedidosVO = new PedidosVO();
				Pago_diarioVO _pago_diarioVO = new Pago_diarioVO();
				ZonaVO zVO = new ZonaVO();
				EmpleadoVO empleadoVO = new EmpleadoVO();
				ClienteVO _clienteVO = new ClienteVO();
				VerazVO v = new VerazVO();
				DomicilioParticularVO dp = new DomicilioParticularVO();
				DomicilioComercialVO dc = new DomicilioComercialVO();
				LocalidadVO lVO = new LocalidadVO();
				
				Object o [] = new Object[5];
				
				existe = true;
				
				empleadoVO.setId_usuario(res.getShort(1));
				empleadoVO.setNombre(res.getString(2));
				empleadoVO.setApellido(res.getString(3));
				
				_pago_diarioVO.setN_pedido(res.getInt(4));
				_pago_diarioVO.setnombre_producto(res.getString(5));
				_pago_diarioVO.setImporte(res.getDouble(6));
				_pago_diarioVO.setCuota(res.getDouble(7));
				//_pago_diarioVO.setId_combinacion(res.getShort(8));
				_pago_diarioVO.setId_cobrador(res.getShort(8));
				_pago_diarioVO.setId_usuario(res.getShort(9));
				_pago_diarioVO.setFecha_registro(res.getDate(10));
				_pago_diarioVO.setHora_registro(res.getTime(11));
			
				
				_clienteVO.setDni(res.getInt(12));
				_clienteVO.setNombre(res.getString(13));
				_clienteVO.setApellido(res.getString(14));
				_clienteVO.setN_orden_planilla(res.getShort(15));
				
				dc.setDomicilio(res.getString(16));
				dc.setHorario_atencion(res.getString(17));
				
				lVO.setLocalidad(res.getString(18));
				
				o[0]=_pago_diarioVO;
				o[1]= empleadoVO;
				o[2]=_clienteVO;
				o[3]= dc;
				o[4]= lVO;
				
				a.add(o);
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
			
			LogicaCliente.validaralta = false;
			return a;
		}
		else return null;
		
	}
  public ArrayList<Pago_diarioVO> busquedaPagos_porFecha_zona(int id_zona, java.sql.Date fecha) throws SQLException{
	  
	  PreparedStatement sentencia = null;
	  ResultSet res;
	  
	  boolean existe = false;
	  
	  ArrayList<Pago_diarioVO> a = new ArrayList<Pago_diarioVO>();
	  int cont = 0;
	  
	  try{
		  
		  sqlConexion.setAutoCommit(false);
		  sentencia = sqlConexion.prepareStatement(sql_select_buscarPagos_porFecha);
		  
		  sentencia.setDate(1, fecha);
		  sentencia.setInt(2, id_zona);
		  
		  
		  res = sentencia.executeQuery();
		  
		  while(res.next()){
			  
			  Pago_diarioVO _pago_diarioVO = new Pago_diarioVO();
			  
			  existe = true;
		  
			  _pago_diarioVO.setN_pedido(res.getInt(1));
			  _pago_diarioVO.setnombre_producto(res.getString(2));
			  _pago_diarioVO.setImporte(res.getDouble(3));
			  _pago_diarioVO.setCuota(res.getDouble(4));
			  //_pago_diarioVO.setId_combinacion(res.getShort(8));
			  _pago_diarioVO.setId_cobrador(res.getShort(5));
			  _pago_diarioVO.setId_usuario(res.getShort(6));
			  _pago_diarioVO.setFecha_registro(res.getDate(7));
			  _pago_diarioVO.setHora_registro(res.getTime(8));
			  
			  a.add(_pago_diarioVO);
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
	
	
	public boolean pago_duplicado(java.sql.Date sql_fecha, short cast_idzona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarpagodiario_duplicado);
			
			sentencia.setDate(1, sql_fecha);
			sentencia.setShort(2, cast_idzona);
			
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
	
	public ArrayList<Pago_diarioVO> buscarPagoDiario_porPedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Pago_diarioVO> a = new ArrayList<Pago_diarioVO>();
		Object [] datos = new Object[3];
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarpagodiario_porPedido);
		
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Pago_diarioVO _pago_diarioVO = new Pago_diarioVO();
		
				
				_pago_diarioVO.setN_pedido(res.getInt(1));
				_pago_diarioVO.setnombre_producto(res.getString(2));
				_pago_diarioVO.setImporte(res.getDouble(3));
				_pago_diarioVO.setCuota(res.getDouble(4));
				//_pago_diarioVO.setId_combinacion(res.getShort(5));
				_pago_diarioVO.setId_cobrador(res.getShort(5));
				_pago_diarioVO.setId_usuario(res.getShort(6));
				_pago_diarioVO.setFecha_registro(res.getDate(7));
				_pago_diarioVO.setHora_registro(res.getTime(8));
						
				a.add(_pago_diarioVO);
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
	
	public ArrayList<Object[]> seguirPagos(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_seguirPagos);
		
			sentencia.setInt(1, n_pedido);
			sentencia.setString(2, "cobrador");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Object [] datos = new Object[8];
						
				for(int i = 0; i < 8; i++){
					
					datos[i] = res.getObject(i + 1);
					System.out.println("pagos " + res.getObject(i + 1).toString() + "///////////////////////////////");
				}
				
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
			System.out.println("exite pedido");	
			return a;
		}
		else return null;
		
		
	}
	
	public int ingresos(Pago_diarioVO _pago_diarioVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertPagos);
			
			sentencia.setInt(1, _pago_diarioVO.getN_pedido());
			sentencia.setString(2, _pago_diarioVO.getnombre_producto());
			sentencia.setDouble(3, _pago_diarioVO.getImporte());
			sentencia.setDouble(4, _pago_diarioVO.getCuota());
			//sentencia.setInt(5, _pago_diarioVO.getId_combinacion());
			sentencia.setInt(5, _pago_diarioVO.getId_cobrador());
			sentencia.setInt(6, _pago_diarioVO.getId_usuario());
			sentencia.setDate(7, _pago_diarioVO.getFecha_registro());
			sentencia.setTime(8, _pago_diarioVO.getHora_registro());
			
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
	
	public int updates(Pago_diarioVO _pago_diarioVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updatePagos);
			sentencia.setDouble(1, _pago_diarioVO.getImporte());
			sentencia.setInt(2, _pago_diarioVO.getN_pedido());
			sentencia.setDate(3, _pago_diarioVO.getFecha_registro());
			
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
