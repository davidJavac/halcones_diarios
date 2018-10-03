package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.AcumuladoVO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Despacho_diarioVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.PedidosVO;
import modelo_vo.Pedidos_diasVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VerazVO;

public class PedidoDAO {

	private static final String sql_buscar_porpedido = "select * from pedidos where n_pedido = ?";
	private static final String sql_buscarPedido_estado = "select * from pedidos where estado_pedido = ?";
	private static final String sql_buscarPedidos_finalizados_bajas= "select * from pedidos where estado_pedido "
			+ " in(?,?)";
	
	private static final String sql_updatePedido = "update pedidos set id_combinacion = ? "
			+ "where n_pedido = ?";
	
	private static final String spNuevo_pedido = "{call nuevo_pedido(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String spUpdatePedido_dias = "{call update_pedido_dias(?,?,?,?,?,?,?)}";
	private static final String spActualizarPedido_articulo = "{call update_pedido_articulo"
			+ "(?,?,?,?,?,?)}";
	private static final String sqlNuevo_pedido = "insert into pedidos(dni, idc,dias, cuota, "
			+ " estado_pedido,  id_usuario, "
		+ "fecha_registro, hora_registro) values(?, ?, ?,?,"
		+"?, ?, ?, ?)";
	
/*	private static final String sql_buscarPedidoArtPersonalizada = "select p.* from pedido_articulo"
			+ " p inner join"
			+ " articulos a on p.codigo_articulo = a.codigo where (a.nombre like concat('%', ?, '%'))"
			+ " and p.estado_pedido <> ?";*/
	
	private static final String sql_buscarPedidoArtPersonalizada = "select distinct p.* from pedidos"
			+ " p inner join"
			+ " pedido_articulo pa on p.n_pedido = pa.n_pedido inner join articulos a on "
			+ "pa.codigo_articulo = a.codigo where CONVERT(concat (a.nombre, ' ', a.descripcion)"
			+ " USING latin1) like concat('%', ?, '%')"
			+ " and p.estado_pedido <> ?";
	
	private static final String sql_buscar_combinaciondias = "select * from combinacion_dias "
			+ "where id_combinacion= ?";
	
	private static final String sql_buscarpedido_enDespacho = "select * from pedidos p inner "
			+ "join despacho_diario d on "
			+ " p.n_pedido = d.n_pedido  where p.n_pedido = ? and p.estado_pedido = ? and (d.estado = ? "
			+ "or d.estado = ?)";
	private static final String sql_buscarCombinacionAll = "select * from combinacion_dias";
	private static final String sql_buscarPedidoAll_xCliente = "select * from pedidos where dni = ? "
			+ "and estado_pedido <> ?";
	private static final String sql_buscarPedidoAll = "select * from pedidos p inner join domicilio_comercial dc"
			+ " on p.idc = dc.idc where "
			+ " p.estado_pedido not in (?,?) order by dc.id_zona asc";
	private static final String sql_buscarPedidoAll_todosEstados = "select * from pedidos";
	private static final String sql_buscarPedido_dias = "select * from pedido_dias where n_pedido = ?";
	private static final String sql_buscarPedidoAll_xCliente_todosEstados = "select * from pedidos where dni = ?";
	
	
	
	private static final String sql_buscarAltasBajas = "select * from pedidos p inner join "
			+ "despacho_diario dp on p.n_pedido = dp.n_pedido where (p.estado_pedido = ? and "
			+ "dp.fecha_registro = ? and dp.estado = ?)  or (p.estado_pedido = ? and p.fecha_termino = ?"
			+ " and dp.estado = ?)";
	
	
	private static final String sql_buscar_articulos_porNpedido = "select * from pedido_articulo "
			+ " where n_pedido = ? and estado = ?";// and p.estado_pedido not in(?,?) ";

	
/*	private static final String sql_update_estadoPedido = "update pedidos set estado_pedido = ? where n_pedido = ? and "
			+ "estado_pedido <> ?";*/
	private static final String sql_update_estadoPedido = "update pedidos set estado_pedido = ? where n_pedido = ?";
	private static final String sql_updateIdc = "update pedidos set idc = ? where n_pedido = ?";
	
	private static final String sql_insertPedido_articulo = "insert into pedido_articulo(n_pedido, "
			+ "codigo_articulo, dias, cuota, estado) values (?, ?,?,?,?)";
	private static final String sql_insertPedido_dias = "insert into pedido_dias(n_pedido, "
			+ "n_dia) values (?, ?)";
	
	private static final String sql_updateestadoPedido_articulo = "update pedido_articulo set estado = ? where id = ?";
	private static final String sql_updateEstadoPedido_articulo = "update pedido_articulo set estado = ? where id = ?";
	private static final String sql_deletePedido_dias = "delete from pedido_articulo where n_pedido = ? and"
			+ "	n_dia = ?";
	
	private static final String sql_deletePedido_articuloAll = "delete from pedido_articulo where n_pedido = ?";
	private static final String sql_eliminarPedido_articulo = "delete from pedido_articulo where id = ?";

	private static final String sql_quitarCantidadPedido_articulo = "update pedido_articulo set cantidad = "
			+ "cantidad - ? where n_pedido = ? and"
			+ " codigo_articulo = ?";
	
	private static final String sql_agregarCantidadPedido_articulo = "update pedido_articulo set cantidad = "
			+ "cantidad + ? where n_pedido = ? and"
			+ " codigo_articulo = ?";
	
/*	private static final String sql_update_fechatermino = "update pedidos set fecha_termino = ? where n_pedido = ? and "
			+ "estado_pedido <> ?";*/
	private static final String sql_update_fechatermino = "update pedidos set fecha_termino = ? where n_pedido = ?";
	private static final String sql_updatePlan_pedido = "update pedidos set dias = ?,"
			+ " cuota = ? where n_pedido = ?";
	
	private static final String sql_update_fechainicio = "update pedidos set fecha_inicio = ? where n_pedido = ? and "
			+ "estado_pedido <> ?";
	private static final String sql_buscarAcumulado = "select * from acumulado where n_pedido = ?";
	
	private static final String sql_insertDespacho = "insert into despacho_diario (n_pedido,entrega, nombre, estado, "
			+ "monto, observaciones, id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?,?,?,?)";
	private static final String sql_insertAcumulado = "insert into acumulado (n_pedido,monto)"
			+ " values(?,?)";
	private static final String sql_updateAcumulado = "update acumulado set monto = ?"
			+ " where n_pedido = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public boolean buscarPedido_enDespacho(int n_pedido)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarpedido_enDespacho);
			
			sentencia.setInt(1, n_pedido);
			sentencia.setString(2, "pendiente entrega");
			//sentencia.setString(3, "enviado");
			sentencia.setString(3, "entregado");
			sentencia.setString(4, "enviado");
			
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
	
	public Combinacion_diasVO buscarCombinacion(short id_combinacion) throws SQLException{
		
		Combinacion_diasVO _combinacion_diasVO = new Combinacion_diasVO();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_combinaciondias);
		
			sentencia.setShort(1, id_combinacion);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_combinacion_diasVO.setId_combinacion(res.getShort(1));
				_combinacion_diasVO.setCantidad_dias(res.getShort(2));
				_combinacion_diasVO.setLunes(res.getBoolean(3));
				_combinacion_diasVO.setMartes(res.getBoolean(4));
				_combinacion_diasVO.setMiercoles(res.getBoolean(5));
				_combinacion_diasVO.setJueves(res.getBoolean(6));
				_combinacion_diasVO.setViernes(res.getBoolean(7));
				_combinacion_diasVO.setSabado(res.getBoolean(8));
				
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
			return _combinacion_diasVO;
		}
		else return null;
	}
	
	public ArrayList<Pedido_articuloVO> buscarArticulos_porNPedido(int n_pedido, boolean estado) throws SQLException{
		
		ArrayList<Pedido_articuloVO> ar = new ArrayList<Pedido_articuloVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_articulos_porNpedido);
		
			sentencia.setInt(1, n_pedido);
			sentencia.setBoolean(2, estado);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Pedido_articuloVO paVO = new Pedido_articuloVO();
				
				paVO.setId(res.getInt(1));
				paVO.setN_pedido(res.getInt(2));
				paVO.setCodigo_articulo(res.getInt(3));
				paVO.setDias(res.getInt(4));
				paVO.setCuota(res.getDouble(5));
				paVO.setEstado(res.getBoolean(6));
				
				ar.add(paVO);
				
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
	public ArrayList<Pedido_articuloVO> buscarArticulos_porNPedido_retirados(int n_pedido) throws SQLException{
		
		ArrayList<Pedido_articuloVO> ar = new ArrayList<Pedido_articuloVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_articulos_porNpedido);
			
			sentencia.setInt(1, n_pedido);
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Pedido_articuloVO paVO = new Pedido_articuloVO();
				
				paVO.setId(res.getInt(1));
				paVO.setN_pedido(res.getInt(2));
				paVO.setCodigo_articulo(res.getInt(3));
				paVO.setDias(res.getInt(4));
				paVO.setCuota(res.getDouble(5));
				paVO.setEstado(res.getBoolean(6));
				
				ar.add(paVO);
				
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
	
	public int actualizarPedido_articulo(int n_pedido,
			int cod1, int cod2, int cod3,int  cod4, int cod5)throws SQLException{
		
		java.sql.CallableStatement sentencia =  null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia =  sqlConexion.prepareCall(spActualizarPedido_articulo);
			
			sentencia.setInt(1, n_pedido);
			sentencia.setInt(2, cod1);
			sentencia.setInt(3, cod2);
			sentencia.setInt(4, cod3);
			sentencia.setInt(5, cod4);
			sentencia.setInt(6, cod5);
	
			
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
	
	public ArrayList<PedidosVO> buscarPedidosCliente(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<PedidosVO> a = new ArrayList<PedidosVO>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidoAll_xCliente);
			
			sentencia.setInt(1, dni);
			sentencia.setString(2, "baja");
			//sentencia.setString(3, "finalizado");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				a.add(_pedidosVO);
		
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
	public ArrayList<PedidosVO> buscarPedidosAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<PedidosVO> a = new ArrayList<PedidosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidoAll);
		
			sentencia.setString(1, "baja");
			sentencia.setString(2, "finalizado");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				a.add(_pedidosVO);
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
	public ArrayList<PedidosVO> buscarPedidosAll_todosEstados() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<PedidosVO> a = new ArrayList<PedidosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidoAll_todosEstados);
		
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				a.add(_pedidosVO);
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
	public ArrayList<PedidosVO> buscarPedidosClienteTodos_estados(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<PedidosVO> a = new ArrayList<PedidosVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidoAll_xCliente_todosEstados);
			
			sentencia.setInt(1, dni);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				a.add(_pedidosVO);
				
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
	
	public PedidosVO buscar_porNpedido(int n_pedido) throws SQLException{
		
		PedidosVO _pedidosVO = new PedidosVO();
		
		ResultSet res;
		PreparedStatement sentencia = null;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_porpedido);
		
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
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
			
			return _pedidosVO;
		}
		else return null;
	}
	public AcumuladoVO buscarAcumulado(int n_pedido) throws SQLException{
		
		AcumuladoVO aVO = new AcumuladoVO();
		
		ResultSet res;
		PreparedStatement sentencia = null;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarAcumulado);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				aVO.setN_pedido(res.getInt(1));
				aVO.setMonto(res.getDouble(2));
				
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
			
			return aVO;
		}
		else return null;
	}
	
	public ArrayList<Combinacion_diasVO> buscarCombinacionAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<Combinacion_diasVO> ar = new ArrayList<Combinacion_diasVO>();
		
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarCombinacionAll);
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
			
				
				Combinacion_diasVO _combinacion_diasVO = new Combinacion_diasVO();
				Object objetos [] = new Object[3];
				
				_combinacion_diasVO.setId_combinacion(res.getShort(1));
				_combinacion_diasVO.setCantidad_dias(res.getShort(2));
				_combinacion_diasVO.setLunes(res.getBoolean(3));
				_combinacion_diasVO.setMartes(res.getBoolean(4));
				_combinacion_diasVO.setMiercoles(res.getBoolean(5));
				_combinacion_diasVO.setJueves(res.getBoolean(6));
				_combinacion_diasVO.setViernes(res.getBoolean(7));
				_combinacion_diasVO.setSabado(res.getBoolean(8));
				
				
				objetos[0] = res.getShort(1);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				objetos[1] = res.getShort(2);
				
				ar.add(_combinacion_diasVO);
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
	
	public ArrayList<PedidosVO> buscarPedidoArtPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidoArtPersonalizada);
		
			sentencia.setString(1, busqueda);
			sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
			
				PedidosVO _pedidosVO = new PedidosVO();
				
				existe = true;	
		
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				ar.add(_pedidosVO);
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
	
	public ArrayList<PedidosVO> buscarPedidos_porEstado(String estado)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
		
		int cont = 0;
		
		boolean existe = false;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedido_estado);
		
			sentencia.setString(1, estado);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				existe = true;	
		
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				ar.add(_pedidosVO);
			
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
	
	public ArrayList<PedidosVO> buscarPedidos_finalizados_bajas()throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
		
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidos_finalizados_bajas);
		
			sentencia.setString(1, "finalizado");
			sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				existe = true;	
				
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				ar.add(_pedidosVO);
				
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
	public ArrayList<PedidosVO> buscarAltasBajas(java.sql.Date hoy)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
		
		int cont = 0;
		
		boolean existe = false;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarAltasBajas);
		
			sentencia.setString(1, "activo");
			sentencia.setDate(2, hoy);
			sentencia.setString(3, "entregado");
			sentencia.setString(4, "baja");
			sentencia.setDate(5, hoy);
			sentencia.setString(6, "entregado");
			//sentencia.setString(6, "baja");
			
			res = sentencia.executeQuery();
			
		
			while(res.next()){
				
				PedidosVO _pedidosVO = new PedidosVO();
				
				existe = true;	
		
				_pedidosVO.setN_pedido(res.getInt(1));
				_pedidosVO.setDni(res.getInt(2));
				_pedidosVO.setIdc(res.getInt(3));
				_pedidosVO.setDias(res.getInt(4));
				_pedidosVO.setCuota_diaria(res.getDouble(5));
				_pedidosVO.setEstado_pedido(res.getString(6));
				//_pedidosVO.setId_combinacion(res.getShort(6));
				_pedidosVO.setFecha_inicio(res.getDate(7));
				_pedidosVO.setFecha_termino(res.getDate(8));
				_pedidosVO.setId_usuario(res.getShort(9));
				_pedidosVO.setFecha_registro(res.getDate(10));
				_pedidosVO.setHora_registro(res.getTime(11));
				
				
				
				ar.add(_pedidosVO);
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
	
	public int modificarIdc(PedidosVO _pedidoVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateIdc);
			sentencia.setInt(1, _pedidoVO.getIdc());
			sentencia.setInt(2, _pedidoVO.getN_pedido());
			
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
	public int modificarPedido_diasCobranza(int n_pedido, int lunes, int martes, 
			int miercoles, int jueves, int viernes, int sabado) throws SQLException{
		
		java.sql.CallableStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareCall(spUpdatePedido_dias);
			sentencia.setInt(1, n_pedido);
			sentencia.setInt(2, lunes);
			sentencia.setInt(3, martes);
			sentencia.setInt(4, miercoles);
			sentencia.setInt(5, jueves);
			sentencia.setInt(6, viernes);
			sentencia.setInt(7, sabado);
			
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
	
	public int updateEstadoPedido(int n_pedido, String estado) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_estadoPedido);
		
			sentencia.setString(1, estado);
			sentencia.setInt(2, n_pedido);
			
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
	public int updateEstadoPedido_articulo(int id, boolean estado) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateEstadoPedido_articulo);
			
			sentencia.setBoolean(1, estado);
			sentencia.setInt(2, id);
			
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
	
	public int updateFecha_termino(int n_pedido,java.sql.Date hoy) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_fechatermino);
			sentencia.setDate(1, hoy);
			sentencia.setInt(2, n_pedido);
			
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
	
	public int updateFecha_inicio(int n_pedido,java.sql.Date fecha) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_fechainicio);
			
			sentencia.setDate(1, fecha);
			sentencia.setInt(2, n_pedido);
			sentencia.setString(3, "baja");
			
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
	
	public int insertPedido_articulo(int n_pedido, int codigo, int dias, double cuota) throws SQLException{
		
			PreparedStatement sentencia =  null;
			int id = 0;
			int affectedRows = 0;
		
			try{
				
				sqlConexion.setAutoCommit(false);
				sentencia =  sqlConexion.prepareStatement(sql_insertPedido_articulo,
						Statement.RETURN_GENERATED_KEYS);
			
				sentencia.setInt(1, n_pedido);
				
				sentencia.setInt(2, codigo);
				sentencia.setInt(3, dias);
				sentencia.setDouble(4, cuota);
				sentencia.setBoolean(5, true);
				
				affectedRows = sentencia.executeUpdate();
	  
		        try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                id = generatedKeys.getInt(1);
		         
		            }
		            else {
		                throw new SQLException("Creating user failed, no ID obtained.");
		            }
		        }
		        sqlConexion.commit();
				
			}
			catch(SQLException e){
				
				if(sqlConexion!=null) sqlConexion.rollback();
			}
			finally{
				
				sqlConexion.setAutoCommit(true);
				
			}
			
			 if (affectedRows == 0) {
		            return affectedRows;
		        }
			 else return id;
	}
	public int insertPedido_dias(int n_pedido, int n_dia) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertPedido_dias);
			
			sentencia.setInt(1, n_pedido);
			
			sentencia.setInt(2, n_dia);
		
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
	
	public int insertDespachoMigracion(Despacho_diarioVO dpVO) throws SQLException{
		
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
	public int insertAcumuladoMigracion(int id, double acu) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertAcumulado);
			
			sentencia.setInt(1, id);
			sentencia.setDouble(2, acu);
			
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
	public int updateAcumuladoMigracion(int id, double acu) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateAcumulado);
			
			sentencia.setDouble(1, acu);
			sentencia.setInt(2, id);
			
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
	
	public ArrayList<Pedidos_diasVO> buscarPedido_dias(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<Pedidos_diasVO> ar = new ArrayList<Pedidos_diasVO>();
		
		int cont = 0;
		
		boolean existe = false;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPedido_dias);
		
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
		
			while(res.next()){
				
				Pedidos_diasVO pdVO = new Pedidos_diasVO();
				
				existe = true;	
		
				pdVO.setN_pedido(res.getInt(1));
				pdVO.setN_dia(res.getInt(2));
				
				
				ar.add(pdVO);
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
	
	public int deletePedido_articulo(int id) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sentencia = sqlConexion.prepareStatement(sql_updateestadoPedido_articulo);
			sqlConexion.setAutoCommit(false);
			sentencia.setBoolean(1, false);
			sentencia.setInt(2, id);
			
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
	public int eliminarPedido_articulo(int id) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sentencia = sqlConexion.prepareStatement(sql_eliminarPedido_articulo);
			sqlConexion.setAutoCommit(false);
	
			sentencia.setInt(1, id);
			
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
	public int deletePedido_dias(int n_pedido, int n_dia) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sentencia = sqlConexion.prepareStatement(sql_deletePedido_dias);
			sqlConexion.setAutoCommit(false);
			sentencia.setInt(1, n_pedido);
			sentencia.setInt(2, n_dia);
			
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
	
	public int deletePedido_articuloAll(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deletePedido_articuloAll);
			sentencia.setInt(1, n_pedido);
			
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
	
	public int quitarCantidadPedido_articulo(int n_pedido, int codigo, int cantidad)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_quitarCantidadPedido_articulo);
			sentencia.setInt(1, cantidad);
			sentencia.setInt(2, n_pedido);
			sentencia.setInt(3, codigo);
			
			
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
	
	public int agregarCantidadPedido_articulo(int n_pedido, int codigo, int cantidad)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_agregarCantidadPedido_articulo);
			sentencia.setInt(1, cantidad);
			sentencia.setInt(2, n_pedido);
			sentencia.setInt(3, codigo);
			
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
	public int cambiarPlan_pedido(int dias, double cuota, int n_pedido)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updatePlan_pedido);
			sentencia.setInt(1, dias);
			sentencia.setDouble(2, cuota);
			sentencia.setInt(3, n_pedido);
			
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
	
	public int insertPedido_mejorado(PedidosVO _pedidoVO) throws SQLException{
		
		PreparedStatement sentencia =  null;
		int id = 0;
		int affectedRows = 0;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia =  sqlConexion.prepareStatement(sqlNuevo_pedido, Statement.RETURN_GENERATED_KEYS);
		
			sentencia.setInt(1, _pedidoVO.getDni());
			sentencia.setInt(2, _pedidoVO.getIdc());
			sentencia.setInt(3, _pedidoVO.getDias());
			sentencia.setDouble(4, _pedidoVO.getCuota_diaria());
			sentencia.setString(5, _pedidoVO.getEstado_pedido());
			//sentencia.setShort(5, _pedidoVO.getId_combinacion());
			sentencia.setShort(6, _pedidoVO.getId_usuario());
			sentencia.setDate(7, _pedidoVO.getFecha_registro());
			sentencia.setTime(8, _pedidoVO.getHora_registro());
			
			affectedRows = sentencia.executeUpdate();
  
	        try (ResultSet generatedKeys = sentencia.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                id = generatedKeys.getInt(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	        sqlConexion.commit();
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			
			sqlConexion.setAutoCommit(true);
			
		}
		
		 if (affectedRows == 0) {
	            return affectedRows;
	        }
		 else return id;
		
		
	}
	public int insertPedido(PedidosVO _pedidoVO, int cod1, 
			int cod2, int cod3, int cod4, int cod5, int lunes, int martes, 
			int miercoles, int jueves, int viernes, int sabado) throws SQLException{
		
		java.sql.CallableStatement sentencia =  null;
		
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia =  sqlConexion.prepareCall(spNuevo_pedido);
			
			sentencia.setInt(1, _pedidoVO.getDni());
			sentencia.setInt(2, _pedidoVO.getDias());
			sentencia.setDouble(3, _pedidoVO.getCuota_diaria());
			sentencia.setString(4, _pedidoVO.getEstado_pedido());
			//sentencia.setShort(5, _pedidoVO.getId_combinacion());
			sentencia.setShort(5, _pedidoVO.getId_usuario());
			sentencia.setDate(6, _pedidoVO.getFecha_registro());
			sentencia.setTime(7, _pedidoVO.getHora_registro());
			sentencia.setInt(8, cod1);
			sentencia.setInt(9, cod2);
			sentencia.setInt(10, cod3);
			sentencia.setInt(11, cod4);
			sentencia.setInt(12, cod5);
			sentencia.setInt(13, lunes);
			sentencia.setInt(14, martes);
			sentencia.setInt(15, miercoles);
			sentencia.setInt(16, jueves);
			sentencia.setInt(17, viernes);
			sentencia.setInt(18, sabado);
			
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
