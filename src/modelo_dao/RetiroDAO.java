package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.RetirosVO;
import modelo_vo.UsuariosVO;

public class RetiroDAO {

	private static final String sql_buscar_retiros_entreFechas = "select * from retiros where "
			+ "(fecha_registro between ? and ?)";
	private static final String sql_buscar_retiro_porNpedido_hora = "select * from retiros where "
			+ "n_pedido = ? and hora_registro = ?";
	private static final String sql_buscar_retiro_porId_pa = "select * from retiros where "
			+ "id_pedido_articulo = ?";
	
	private static final String sql_deleteRetiro = "delete from retiros where n_retiro = ?";
	
	private static final String spNuevo_retiro = "{call nuevo_retiro(?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String spActualizacion_cambioPlan = "{call nuevo_plan(?,?,?,?,?,?,?,?)}";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<RetirosVO> buscarRetiros_entreFechas(java.sql.Date desde, 
			java.sql.Date hasta) throws SQLException{
		
		
		ArrayList<RetirosVO> ar = new ArrayList<RetirosVO>();
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_retiros_entreFechas);
		
			sentencia.setDate(1, desde);
			sentencia.setDate(2, hasta);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				RetirosVO _retirosVO = new RetirosVO();
				
				existe = true;
				
				_retirosVO.setN_retiro(res.getInt(1));
				_retirosVO.setId_pedido_articulo(res.getInt(2));
				_retirosVO.setN_pedido(res.getInt(3));
				_retirosVO.setCodigo(res.getInt(4));
				_retirosVO.setId_vendedor(res.getShort(5));
				_retirosVO.setObservaciones(res.getString(6));
				_retirosVO.setId_usuario(res.getShort(7));
				_retirosVO.setFecha_registro(res.getDate(8));
				_retirosVO.setHora_registro(res.getTime(9));
				
				ar.add(_retirosVO);
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
	
	public RetirosVO buscarRetiro_porNpedido_hora(int n_pedido, Time hora) throws SQLException{
		
		
		RetirosVO _retirosVO = new RetirosVO();
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_retiro_porNpedido_hora);
		
			sentencia.setInt(1, n_pedido);
			sentencia.setTime(2, hora);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_retirosVO.setN_retiro(res.getInt(1));
				_retirosVO.setId_pedido_articulo(res.getInt(2));
				_retirosVO.setN_pedido(res.getInt(3));
				_retirosVO.setCodigo(res.getInt(4));
				_retirosVO.setId_vendedor(res.getShort(5));
				_retirosVO.setObservaciones(res.getString(6));
				_retirosVO.setId_usuario(res.getShort(7));
				_retirosVO.setFecha_registro(res.getDate(8));
				_retirosVO.setHora_registro(res.getTime(9));
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
			return _retirosVO;
		}
		else return null;
	}
	public RetirosVO buscarRetiro_porId_pa(int id) throws SQLException{
		
		
		RetirosVO _retirosVO = new RetirosVO();
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_retiro_porId_pa);
			
			sentencia.setInt(1, id);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_retirosVO.setN_retiro(res.getInt(1));
				_retirosVO.setId_pedido_articulo(res.getInt(2));
				_retirosVO.setN_pedido(res.getInt(3));
				_retirosVO.setCodigo(res.getInt(4));
				_retirosVO.setId_vendedor(res.getShort(5));
				_retirosVO.setObservaciones(res.getString(6));
				_retirosVO.setId_usuario(res.getShort(7));
				_retirosVO.setFecha_registro(res.getDate(8));
				_retirosVO.setHora_registro(res.getTime(9));
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
			return _retirosVO;
		}
		else return null;
	}
	public int deleteRetiro(RetirosVO rVO) throws SQLException{
		
		
		RetirosVO _retirosVO = new RetirosVO();
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deleteRetiro);
			
			sentencia.setInt(1, rVO.getN_retiro());

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
		
	public int insertNuevo_Retiro(RetirosVO rVO, int dias, int cuota, String estado) throws SQLException{
		
		java.sql.CallableStatement stmt = null;

		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			int numero = 0;
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spNuevo_retiro);
		
			stmt.setInt(1, rVO.getId_pedido_articulo());
			stmt.setInt(2, rVO.getN_pedido());
			stmt.setInt(3, rVO.getCodigo());
			stmt.setInt(4, rVO.getId_vendedor());
			stmt.setString(5, rVO.getObservaciones());
			stmt.setInt(6, rVO.getId_usuario());
			stmt.setDate(7, rVO.getFecha_registro());
			stmt.setTime(8, rVO.getHora_registro());
			stmt.setInt(9, dias);
			stmt.setInt(10, cuota);
			stmt.setString(11, estado);
			
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
	
	/*public int actualizacionCambio_plan(ArticulosVO nuevoArticuloVO, 
			Cambio_planVO cVO) throws SQLException{
		
		CallableStatement stmt = (CallableStatement) sqlConexion.prepareCall(spActualizacion_cambioPlan);

		ResultSet res;
		
		boolean existe = false;
	
		stmt.setInt(1, nuevoArticuloVO.getCodigo());
		stmt.setInt(2, cVO.getN_pedido());
		stmt.setInt(3, cVO.getId_vendedor());
		stmt.setInt(4, cVO.getCodigo_anterior());
		stmt.setInt(5, cVO.getCodigo_posterior());
		stmt.setInt(6, cVO.getId_usuario());
		stmt.setDate(7, cVO.getFecha_registro());
		stmt.setTime(8, cVO.getHora_registro());
		
	
		return stmt.executeUpdate();	
		
	}*/
	
}
