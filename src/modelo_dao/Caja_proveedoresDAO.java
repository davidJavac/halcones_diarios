package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.Caja_internaVO;
import modelo_vo.Caja_proveedoresVO;

public class Caja_proveedoresDAO {

	private static final String sql_select_buscarCaja_proveedores_porFecha = "select * from caja_proveedores"
			+ " where fecha_registro = ?";
	private static final String sql_insertCaja_proveedores = "insert into caja_proveedores(id_proveedor, "
			+ "detalle, "
			+ "monto,  "
			+ "id_usuario, fecha_registro, hora_registro) values(?,?,?,?,?,?)";
	
	private static final String sql_bajaPago_proveedores = "delete from caja_proveedores where numero = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Caja_proveedoresVO> buscarCaja_proveedores_porFecha(java.sql.Date hoy) throws SQLException{

		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Caja_proveedoresVO> ar = new ArrayList<Caja_proveedoresVO>();
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_select_buscarCaja_proveedores_porFecha);
			
			sentencia.setDate(1, hoy);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Caja_proveedoresVO cVO = new Caja_proveedoresVO();
				
				existe = true;
				
				cVO.setNumero(res.getInt(1));
				cVO.setId_proveedor(res.getShort(2));
				cVO.setDetalle(res.getString(3));
				cVO.setMonto(res.getDouble(4));
				cVO.setId_usuario(res.getShort(5));
				cVO.setFecha_registro(res.getDate(6));
				cVO.setHora_registro(res.getTime(7));
				
				ar.add(cVO);
		
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
			return ar;
		}
		else return null;
	}
	
	public int eliminarPago_proveedor(int numero_pago) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_bajaPago_proveedores);
			sentencia.setInt(1, numero_pago);
			
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
	
	
	public int ingresos(Caja_proveedoresVO cVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertCaja_proveedores);
			sentencia.setShort(1, cVO.getId_proveedor());
			sentencia.setString(2, cVO.getDetalle());
			sentencia.setDouble(3, cVO.getMonto());
			sentencia.setShort(4, cVO.getId_usuario());
			sentencia.setDate(5, cVO.getFecha_registro());
			sentencia.setTime(6, cVO.getHora_registro());
			
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
