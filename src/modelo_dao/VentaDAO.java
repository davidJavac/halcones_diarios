package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;
import modelo_vo.VentasVO;

public class VentaDAO {
	
	private static final String sql_buscarVentas_entreFechas = "select * from ventas where "
			+ "(fecha_registro between ? and ?)";
	
	private static final String sql_insertNueva_venta = "insert into ventas (n_pedido, plan, credito,id_vendedor,"
			+ "id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?,?)";
	private static final String sql_deleteVenta_porNpedido = "delete from ventas where n_pedido = ?";
	private static final String sql_updateVentas = "update ventas set plan = ?, credito = ?,"
			+ " id_vendedor = ?"
			+ " where n_venta = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<VentasVO> buscarVentas_entreFechas(java.sql.Date desde,
			java.sql.Date hasta) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<VentasVO> ar = new ArrayList<VentasVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarVentas_entreFechas);
		
			sentencia.setDate(1, desde);
			sentencia.setDate(2, hasta);
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
			
				existe = true;
				
				VentasVO _ventasVO = new VentasVO();
				
				_ventasVO.setN_venta(res.getInt(1));
				_ventasVO.setN_pedido(res.getInt(2));
				_ventasVO.setPlan(res.getString(3));
				_ventasVO.setCredito(res.getDouble(4));
				_ventasVO.setId_vendedor(res.getShort(5));
				_ventasVO.setId_usuario(res.getShort(6));
				_ventasVO.setFecha_registro(res.getDate(7));
				_ventasVO.setHora_registro(res.getTime(8));
				
				ar.add(_ventasVO);
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
	
	public int insertNueva_venta(VentasVO vVO) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_insertNueva_venta);

		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertNueva_venta);
			sentencia.setInt(1, vVO.getN_pedido());
			sentencia.setString(2, vVO.getPlan());
			sentencia.setDouble(3, vVO.getCredito());
			sentencia.setInt(4, vVO.getId_vendedor());
			sentencia.setInt(5, vVO.getId_usuario());
			sentencia.setDate(6, vVO.getFecha_registro());
			sentencia.setTime(7, vVO.getHora_registro());
			
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

	public int updateVentas(VentasVO vVO) throws SQLException{
		
		PreparedStatement sentencia = null;
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateVentas);
			sentencia.setString(1, vVO.getPlan());
			sentencia.setDouble(2, vVO.getCredito());
			sentencia.setInt(3, vVO.getId_vendedor());
			sentencia.setInt(4, vVO.getN_venta());
			
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
	public int deleteVenta_porNpedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deleteVenta_porNpedido);
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
}
