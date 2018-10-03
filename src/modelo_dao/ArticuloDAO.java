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
import modelo_vo.Combinacion_diasVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.SeccionVO;
import modelo_vo.UsuariosVO;

public class ArticuloDAO {
	
	private static final String sql_buscarArticuloPersonalizada = "select * from articulos "
			+ " where CONVERT(concat (nombre, ' ', descripcion) USING latin1) like concat('%', ?, '%') order by nombre asc";
	
	private static final String sql_modificarArticulo = "update articulos set nombre = ?, descripcion = ?,"
			+ "sec = ?"
			+ " where codigo= ?";
	private static final String sql_buscar_articulo = "select * from articulos where codigo= ?";
	private static final String sql_buscar_seccion = "select * from seccion where codigo= ?";
	private static final String sql_buscar_articulo_enPrestamo = "select * from articulosp where codigo= ?";
	private static final String sql_buscar_articulo_enPrestamoAll = "select * from articulos where codigo in "
			+ " (select codigo from articulosp)";
	private static final String sql_buscar_articulo_enCombo = "select * from combos where codigo= ?";
	private static final String sql_buscarArticuloAll = "select * from articulos";
	private static final String sql_buscarSeccionAll = "select * from seccion";
	private static final String spNuevo_articulo = "{call nuevo_articulo(?,?,?,?,?)}";
	private static final String sql_insert_Nuevo_articulo = "insert into articulos (nombre, descripcion) "
			+ "values(?,?)";
	
	private static final String sql_update_prestamoEnc = "update articulos "
			+ " set nombre =  aes_encrypt(?,?), "
			+ "descripcion = aes_encrypt(?,?) where codigo in(select codigo from"
			+ " articulosp)";
	
	private static final String sql_update_prestamoDes = "update articulos "
			+ " set nombre =  aes_decrypt(aes_encrypt(?, ?), ?) , descripcion = "
			+ "aes_decrypt(aes_encrypt(?,?), ?)  where codigo = ?";
	
	private static final String sql_buscarPrestamoAll_decrypt = "select codigo, "
			+ "cast(aes_decrypt(nombre, ?) as char(50)),"
			+ " cast(aes_decrypt(descripcion, ?) as char(50))"
			+ " from articulos where codigo in (select codigo from articulosp)";
	/*private static final String sql_buscarPrestamoAll_decrypt = "select codigo, "
			+ "aes_decrypt(nombre, ?),"
			+ " aes_decrypt(descripcion, ?), "
			+ "dias,"
			+ "cuota from articulos where codigo in (select codigo from articulosp)";*/
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArticulosPVO buscarArt_enPrestamo(int codigo) throws SQLException{
		
		ArticulosPVO prestamoVO = new ArticulosPVO();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
			
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_articulo_enPrestamo);
			
			sentencia.setInt(1, codigo);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				prestamoVO.setCodigo(res.getInt(1));
				prestamoVO.setMonto(res.getDouble(2));
				
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
			return prestamoVO;
		}
		else return null;
	}
	public ArrayList<ArticulosVO> buscarArt_enPrestamosAll() throws SQLException{
		
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_articulo_enPrestamoAll);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				ArticulosVO _articulosVO = new ArticulosVO();
				
				existe = true;
				
				_articulosVO.setCodigo(res.getInt(1));
				_articulosVO.setNombre(res.getString(2));
				_articulosVO.setDescripcion(res.getString(3));
				_articulosVO.setSeccion(res.getInt(4));
				/*_articulosVO.setDias(res.getInt(5));
				_articulosVO.setCuota(res.getDouble(6));*/
				
				ar.add(_articulosVO);
				
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
	
	public ArticulosVO buscarArticulo(int codigo) throws SQLException{
		
		ArticulosVO _articulosVO = new ArticulosVO();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
			
		
		try{
			
			sqlConexion.setAutoCommit(false);
		    sentencia = sqlConexion.prepareStatement(sql_buscar_articulo);
		    sentencia.setInt(1, codigo);
			
		    res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_articulosVO.setCodigo(res.getInt(1));
				_articulosVO.setNombre(res.getString(2));
				_articulosVO.setDescripcion(res.getString(3));
				_articulosVO.setSeccion(res.getInt(4));
				/*_articulosVO.setDias(res.getInt(5));
				_articulosVO.setCuota(res.getDouble(6));*/
				
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
			return _articulosVO;
		}
		else return null;
	}
	public SeccionVO buscarSeccion(int codigo) throws SQLException{
		
		SeccionVO sVO = new SeccionVO();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscar_seccion);
			sentencia.setInt(1, codigo);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				sVO.setCodigo(res.getInt(1));
				sVO.setDescripcion(res.getString(2));
			
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
			
			return sVO;
		}
		else return null;
	}
	
	public ArrayList<ArticulosVO> buscarArticulo_desencriptado(String key) throws SQLException{
		
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.
					prepareStatement(sql_buscarPrestamoAll_decrypt);
			
			sentencia.setString(1, key);
			sentencia.setString(2, key);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				ArticulosVO _articulosVO = new ArticulosVO();
				
				_articulosVO.setCodigo(res.getInt(1));
				_articulosVO.setNombre(res.getString(2));
				_articulosVO.setDescripcion(res.getString(3));
				_articulosVO.setSeccion(res.getInt(4));
				/*_articulosVO.setDias(res.getInt(5));
				_articulosVO.setCuota(res.getDouble(6));*/
				
				System.out.println(_articulosVO.getCodigo() + " " + _articulosVO.getNombre());
				
				ar.add(_articulosVO);
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
	
	public ArrayList<ArticulosVO> buscarArticuloPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		
		String articulo = "";
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarArticuloPersonalizada);
			
			sentencia.setString(1, busqueda);
			
			res = sentencia.executeQuery();
			
			int cont = 0;
			
			
			while(res.next()){
				
				existe = true;	
				
				ArticulosVO _articulosVO = new ArticulosVO();
				
				_articulosVO.setCodigo(res.getInt(1));
				_articulosVO.setNombre(res.getString(2));
				_articulosVO.setDescripcion(res.getString(3));
				_articulosVO.setSeccion(res.getInt(4));
				/*_articulosVO.setDias(res.getInt(5));
				_articulosVO.setCuota(res.getDouble(6));*/
				
				ar.add(_articulosVO);
			
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
	
	public ArrayList<ArticulosVO> buscarArticuloAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;

		
		ArrayList<ArticulosVO> ar = new ArrayList<ArticulosVO>();
		
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarArticuloAll);
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				ArticulosVO _articulosVO = new ArticulosVO();
				Object objetos [] = new Object[3];
				
				_articulosVO.setCodigo(res.getInt(1));
				_articulosVO.setNombre(res.getString(2));
				_articulosVO.setDescripcion(res.getString(3));
				_articulosVO.setSeccion(res.getInt(4));
				/*_articulosVO.setDias(res.getInt(5));
				_articulosVO.setCuota(res.getDouble(6));*/
				
				ar.add(_articulosVO);
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
	public ArrayList<SeccionVO> buscarSeccionAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		
		ArrayList<SeccionVO> ar = new ArrayList<SeccionVO>();
		
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarSeccionAll);
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				SeccionVO sVO = new SeccionVO();
				
				sVO.setCodigo(res.getInt(1));		
				sVO.setDescripcion(res.getString(2));
		
				ar.add(sVO);
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
	
	public int modificar_articulo(ArticulosVO artVO) throws SQLException{
		
		PreparedStatement stmt = null;

		ResultSet res;
		
		boolean existe = false;
	
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareStatement(sql_modificarArticulo);
			
			stmt.setString(1, artVO.getNombre());
			stmt.setString(2, artVO.getDescripcion());
			stmt.setInt(3, artVO.getSeccion());
			/*stmt.setInt(4, artVO.getDias());
			stmt.setDouble(5, artVO.getCuota());*/
			stmt.setInt(4, artVO.getCodigo());
			
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
	
	public int insertNuevo_articulo(String tipo, String nombre, String descripcion,int seccion,
			double monto ) throws SQLException{
		
		java.sql.CallableStatement stmt = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			stmt = sqlConexion.prepareCall(spNuevo_articulo);
			System.out.println("seccion " + seccion);
			stmt.setString(1, tipo);
			stmt.setString(2, nombre);
			stmt.setString(3, descripcion);
			stmt.setInt(4, seccion);
			stmt.setDouble(5, monto);
			
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
	
	public int update_encriptar(ArticulosVO artVO, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_prestamoEnc);
			
			sentencia.setString(1, artVO.getNombre());
			sentencia.setString(2, key);
			sentencia.setString(3, artVO.getDescripcion());
			sentencia.setString(4, key);
			
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
	public int update_desencriptar(ArticulosVO artVO, String key) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_update_prestamoDes);
			
			System.out.println("dentro de update desencriptar" + artVO.getCodigo() + " " + artVO.getNombre());
			
			sentencia.setString(1, artVO.getNombre());
			sentencia.setString(2, key);
			sentencia.setString(3, key);
			sentencia.setString(4, artVO.getDescripcion());
			sentencia.setString(5, key);
			sentencia.setString(6, key);
			sentencia.setInt(7, artVO.getCodigo());
			
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
