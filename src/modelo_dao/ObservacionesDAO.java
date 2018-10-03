package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosVO;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.ObservacionesVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;

public class ObservacionesDAO {

	private static final String sql_buscarpedido_porCliente = "select p.n_pedido,a.nombre,"
			+ " a.codigo,a.dias,a.cuota_diaria,a.descripcion, p.dias_desde_inicio,"
			+ " p.dias_mora,p.resto_dias_mora, p.estado_deuda, p.estado_pedido, p.credito, p.saldo,p.interes,"
			+ " p.id_combinacion, p.fecha_inicio, p.fecha_termino, u.nombre, p.fecha_registro,p.hora_registro"
			+ "  from pedidos p inner join articulos a on p.codigo = a.codigo inner join usuarios u on p.id_usuario = "
			+ " u.id_usuario where p.dni = ?";
	
	private static final String sql_buscarArticuloPersonalizada = "select codigo, nombre, descripcion from articulos "
			+ " where (nombre like concat('%', ?, '%'))";
	
/*	private static final String sql_buscar_observacion_pedido = "select o.id, o.id_observacion, o.observacion,"
			+ " u.nombre, o.fecha_registro, o.hora_registro from observaciones_pedido o inner join usuarios u "
			+ " on o.id_usuario = u.id_usuario where o.id= ?";*/
	
	private static final String sql_buscar_observacion_pedido = "select * from observaciones_pedido	 where "
			+ " id = ? order by id_observacion desc";
	private static final String sql_buscar_observacion_cliente = "select * from observaciones_cliente where "
			+ " dni = ? order by id desc";
	private static final String sql_insertarobservacion_pedido = "insert into observaciones_pedido (id, observacion,"
			+ "fecha_registro, hora_registro, id_usuario) values(?, ?,?,?,?)";
	private static final String sql_insertarobservacion_cliente = "insert into observaciones_cliente"
			+ " (dni, observacion, id_usuario,"
			+ "fecha_registro, hora_registro) values(?, ?,?,?,?)";
	private static final String sql_modificarobservacion_pedido = "update observaciones_pedido"
			+ " set observacion = ?,"
			+ "fecha_registro = ?, hora_registro = ?, id_usuario = ? where id_observacion = ?";
	private static final String sql_modificarobservacion_cliente = "update observaciones_cliente"
			+ " set observacion = ?,"
			+ "fecha_registro = ?, hora_registro = ?, id_usuario = ? where id = ?";
	
	private static final String sql_buscarArticuloAll = "select * from articulos";
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public int 	modificarObservacionPedido(int id, ObservacionesVO obVO)throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_modificarobservacion_pedido);
			sentencia.setString(1, obVO.getObservacion());
			sentencia.setDate(2, obVO.getFecha_registro());
			sentencia.setTime(3, obVO.getHora_registro());
			sentencia.setShort(4, obVO.getId_usuario());
			sentencia.setInt(5, id);
			
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
	public int 	modificarObservacionCliente(int id, Observaciones_clienteVO obVO)throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_modificarobservacion_cliente);
			sentencia.setString(1, obVO.getObservacion());
			sentencia.setDate(2, obVO.getFecha_registro());
			sentencia.setTime(3, obVO.getHora_registro());
			sentencia.setShort(4, obVO.getId_usuario());
			sentencia.setInt(5, id);
			
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
	
	public int nuevaObservacionPedido(ObservacionesVO obVO) throws SQLException{
		
		PreparedStatement sentencia = null;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertarobservacion_pedido);
			sentencia.setInt(1, obVO.getId());
			sentencia.setString(2, obVO.getObservacion());
			sentencia.setDate(3, obVO.getFecha_registro());
			sentencia.setTime(4, obVO.getHora_registro());
			sentencia.setShort(5, obVO.getId_usuario());
			
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
	public int nuevaObservacionCliente(Observaciones_clienteVO obVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_insertarobservacion_cliente);
			sentencia.setInt(1, obVO.getDni());
			sentencia.setString(2, obVO.getObservacion());
			sentencia.setShort(3, obVO.getId_usuario());
			sentencia.setDate(4, obVO.getFecha_registro());
			sentencia.setTime(5, obVO.getHora_registro());
			
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
	
	public ArrayList<ObservacionesVO> buscarObservacionPedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<ObservacionesVO> a = new ArrayList<ObservacionesVO>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_observacion_pedido);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				ObservacionesVO oVO = new ObservacionesVO();
				
				oVO.setId(res.getInt(1));
				oVO.setId_observacion(res.getInt(2));
				oVO.setObservacion(res.getString(3));
				oVO.setId_usuario(res.getShort(4));
				oVO.setFecha_registro(res.getDate(5));
				oVO.setHora_registro(res.getTime(6));
				
				a.add(oVO);
		
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
	public ArrayList<Observaciones_clienteVO> buscarObservacionCliente(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Observaciones_clienteVO> a = new ArrayList<Observaciones_clienteVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_observacion_cliente);
		
			sentencia.setInt(1, dni);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Observaciones_clienteVO oVO = new Observaciones_clienteVO();
				
				oVO.setId_observacion(res.getInt(1));
				oVO.setDni(res.getInt(2));
				oVO.setObservacion(res.getString(3));
				oVO.setId_usuario(res.getShort(4));
				oVO.setFecha_registro(res.getDate(5));
				oVO.setHora_registro(res.getTime(6));
				
				a.add(oVO);
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
	
	public ArrayList<Object []> buscarArticuloPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<Object []> ar = new ArrayList<Object[]>();
		
		String articulo = "";
		int cont = 0;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarArticuloPersonalizada);

			sentencia.setString(1, busqueda);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				Object objetos [] = new Object[2];
				
				existe = true;	
		
				articulo = res.getString(2) + ": " + res.getString(3) + " ";
						
				objetos [0] = res.getInt(1);
						
				objetos [1] = articulo;
				
				ar.add(objetos);
			
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
	
}
