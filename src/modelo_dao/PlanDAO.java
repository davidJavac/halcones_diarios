package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Historial_planesVO;
import modelo_vo.PlanesVO;
import modelo_vo.SeccionVO;

public class PlanDAO {

	private static final String sql_deletePlan = "delete p.* from planes p inner join historial_planes h"
			+ " on p.n_plan = h.n_plan where h.id = ?";
	private static final String sql_deleteHistorial_planes = "delete from historial_planes where id = ?";
	private static final String sql_insertPlan = "insert into planes (n_pedido) "
			+ "values(?)";
	private static final String sql_buscarPlanes_porPedido = "select * from planes "
			+ " where n_pedido = ? and n_plan in(select n_plan from historial_planes h inner join "
			+ " pedido_articulo p on h.id = p.id where p.estado = ?)";
	private static final String sql_buscarHistorialPlanes_porNplan = "select * from historial_planes "
			+ " where n_plan = ?";

	private static final String sql_insertHistorial_planes= "insert into historial_planes (id,n_plan,"
			+ "n_pedido, codigo_articulo, dias, cuota) "
			+ "values(?, ?,?,?,?,?)";
	
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<PlanesVO> buscarPlanes_porPedido(int n_pedido) throws SQLException{
		
		ArrayList<PlanesVO> ar = new ArrayList<PlanesVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarPlanes_porPedido);
			
			sentencia.setInt(1, n_pedido);
			sentencia.setBoolean(2, false);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				PlanesVO pVO = new PlanesVO();
				
				existe = true;
				
				pVO.setN_plan(res.getInt(1));
				pVO.setN_pedido(res.getInt(2));
				
				ar.add(pVO);
				
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
	
	public ArrayList<Historial_planesVO> buscarHistorial_planes_porNplan(int n_plan) throws SQLException{
		
		ArrayList<Historial_planesVO> ar = new ArrayList<Historial_planesVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarHistorialPlanes_porNplan);
			
			sentencia.setInt(1, n_plan);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				Historial_planesVO hVO = new Historial_planesVO();
				
				existe = true;
				
				hVO.setId(res.getInt(1));
				hVO.setN_plan(res.getInt(2));
				hVO.setN_pedido(res.getInt(3));
				hVO.setCodigo_articulo(res.getInt(4));
				hVO.setDias(res.getInt(5));
				hVO.setCuota(res.getDouble(6));
				
				ar.add(hVO);
				
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
	
	public int insertPlan(PlanesVO pVO) throws SQLException{
		
		PreparedStatement sentencia =  null;
		int id = 0;
		int affectedRows = 0;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia =  sqlConexion.prepareStatement(sql_insertPlan,
					Statement.RETURN_GENERATED_KEYS);
		
			sentencia.setInt(1, pVO.getN_pedido());
			
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
		System.out.println("filas afectadas " + affectedRows);
		 if (affectedRows == 0) {
	            return affectedRows;
	        }
		 else return id;
	}
	
	public int insertHistorial_planes(Historial_planesVO hVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertHistorial_planes);
			
			sentencia.setInt(1, hVO.getId());
			sentencia.setInt(2, hVO.getN_plan());
			sentencia.setInt(3, hVO.getN_pedido());
			sentencia.setInt(4, hVO.getCodigo_articulo());
			sentencia.setInt(5, hVO.getDias());
			sentencia.setDouble(6, hVO.getCuota());
			
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
	
	public int deletePlan(int id) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deletePlan);
			
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
	public int deleteHistorial_planes(int id) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_deleteHistorial_planes);
			
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
}
