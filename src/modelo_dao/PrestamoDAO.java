package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Blob;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.PedidosVO;
import modelo_vo.UsuariosVO;

public class PrestamoDAO {
	
	private static final String sql_buscarPrestamoPersonalizada = "select codigo, nombre, descripcion from articulosp "
			+ " where (nombre like concat('%', ?, '%') or descripcion like concat('%', ?, '%'))";
	
	private static final String sql_buscar_prestamo_decrypt = "select codigo, cast(aes_decrypt(nombre, ?) as char(50)),"
			+ " cast(aes_decrypt(descripcion, ?) as char(50)), cast(aes_decrypt(dias, ?) as char(50)),"
			+ "cast(aes_decrypt(cuota, ?) as char(50)) from articulosp where codigo= ?";
	
	private static final String sql_buscar_prestamo = "select * "
			+ "from articulosp where codigo= ?";
	
	private static final String sql_buscarPrestamoAll = "select codigo, nombre,descripcion,dias,cuota from articulosp";
	
	private static final String sql_buscarPrestamoAll_decrypt = "select codigo, cast(aes_decrypt(nombre, ?) as char(50)),"
			+ " cast(aes_decrypt(descripcion, ?) as char(50)), cast(aes_decrypt(dias, ?) as char(50)),"
			+ "cast(aes_decrypt(cuota, ?) as char(50)) from articulosp";
	
	private static final String sql_insertarprestamo = "insert into articulosp (nombre, descripcion, dias, cuota) "
			+ "values (?,?,?,?)";
		
	private static final String sql_insertarprestamoEnc = "insert into articulosp (nombre, descripcion, dias, cuota)"
			+ " values (aes_encrypt(?,?),aes_encrypt(?,?),aes_encrypt(?,?),"
			+ " aes_encrypt(?,?))";
	
	private static final String sql_update_prestamoEnc = "update articulos "
			+ " set codigo = aes_encrypt(?,?), nombre =  aes_encrypt(?,?), descripcion = aes_encrypt(?,?), "
			+ "dias = aes_encrypt(?,?),"
			+ " cuota = aes_encrypt(?,?) where nombre = ?";

	private static final String sql_update_prestamoDes = "update articulosp "
			+ " set nombre =  aes_decrypt(aes_encrypt(?, ?), ?) , descripcion = "
			+ "aes_decrypt(aes_encrypt(?,?), ?),"
			+ " dias = aes_decrypt(aes_encrypt(?,?), ?), "
			+ " cuota = aes_decrypt(aes_encrypt(?,?), ?) where codigo = ?";
	
	private static final String sql_select_encriptar = "select hex(aes_encrypt('codigo',?)), hex(aes_encrypt('nombre',?)), "
			+ "hex(aes_encrypt('descripcion', ?)),"
			+ " hex(aes_encrypt('dias', ?)), hex(aes_encrypt('cuota', ?)) from articulosp";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArticulosPVO buscarPrestamo(int codigo) throws SQLException{
		
		ArticulosPVO _prestamoVO = new ArticulosPVO();
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscar_prestamo);
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<Object[]> a = new ArrayList<Object[]>();
		
		sentencia.setInt(1, codigo);
		
		res = sentencia.executeQuery();
		
		while(res.next()){
			
			existe = true;
			
			String descripcion, dias, cuota;
			double descD, cuotaD;
			int diasI;
			
			_prestamoVO.setCodigo(res.getInt(1));
			_prestamoVO.setMonto(res.getDouble(2));
				
		}
		
		if(existe){
		
			return _prestamoVO;
		}
		else return null;
	}
	
	
	
	public ArrayList<ArticulosPVO> select_encriptar(String key) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_select_encriptar);
		ResultSet res;
		ArrayList<ArticulosPVO> ar = new ArrayList<ArticulosPVO>();
		sentencia.setString(1, key);
		sentencia.setString(2, key);
		sentencia.setString(3, key);
		sentencia.setString(4, key);
		sentencia.setString(5, key);
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
			
			ArticulosPVO presVO = new ArticulosPVO();
			/*presVO.setCodigo(res.getInt(1));
			presVO.setNombre(res.getString(2));
			presVO.setDescripcion(res.getDouble(3));
			presVO.setDias(res.getShort(4));
			presVO.setCuota(res.getDouble(5));
			existe = true;	*/
			
			ar.add(presVO);

		}	
		
		if(existe) 	return ar;
		else return null;
		
	}
	
	public int encriptar(ArticulosPVO _prestamoVO, String key) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_insertarprestamoEnc);
		
		/*sentencia.setString(1, _prestamoVO.getNombre());
		sentencia.setString(2, key);
		sentencia.setDouble(3, _prestamoVO.getDescripcion());
		sentencia.setString(4, key);
		sentencia.setInt(5, _prestamoVO.getDias());
		sentencia.setString(6, key);
		sentencia.setDouble(7, _prestamoVO.getCuota());
		sentencia.setString(8, key);*/
		
		return sentencia.executeUpdate();
		
	}
	
	public int update_encriptar(ArticulosPVO _prestamoVO, String key) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_update_prestamoEnc);
		
		/*sentencia.setString(1, _prestamoVO.getNombre());
		sentencia.setString(2, key);
		sentencia.setDouble(3, _prestamoVO.getDescripcion());
		sentencia.setString(4, key);
		sentencia.setInt(5, _prestamoVO.getDias());
		sentencia.setString(6, key);
		sentencia.setDouble(7, _prestamoVO.getCuota());
		sentencia.setString(8, key);
		sentencia.setInt(9, _prestamoVO.getCodigo());*/
		
		return sentencia.executeUpdate();
		
	}
	
	/*public int desencriptar(String key){
		
		
	}*/
	
	public ArrayList<ArticulosPVO> buscarPrestamoAlldes(String key) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarPrestamoAll_decrypt);
		ResultSet res;
		ArrayList<ArticulosPVO> ar = new ArrayList<ArticulosPVO>();
		sentencia.setString(1, key);
		sentencia.setString(2, key);
		sentencia.setString(3, key);
		sentencia.setString(4, key);
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
			
			/*ArticulosPVO presVO = new ArticulosPVO();
			presVO.setCodigo(res.getInt(1));
			presVO.setNombre(res.getString(2));
			presVO.setDescripcion(res.getDouble(3));
			presVO.setDias(res.getShort(4));
			presVO.setCuota(res.getDouble(5));
			existe = true;	
			
			ar.add(presVO);*/

		}	
		
		if(existe) 	return ar;
		else return null;
		
	}
	
	public int update_desencriptar(ArticulosPVO _prestamoVO, String key) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_update_prestamoDes);
		
		//sentencia.setString(1, _prestamoVO.getNombre());
		/*sentencia.setString(1, _prestamoVO.getNombre());
		//sentencia.setDouble(3, _prestamoVO.getDescripcion());
		sentencia.setString(2, key);
		//sentencia.setInt(5, _prestamoVO.getDias());
		sentencia.setString(3, key);
		//sentencia.setDouble(7, _prestamoVO.getCuota());
		sentencia.setDouble(4, _prestamoVO.getDescripcion());
		sentencia.setString(5, key);
		sentencia.setString(6, key);
		sentencia.setShort(7, _prestamoVO.getDias());
		sentencia.setString(8, key);
		sentencia.setString(9, key);
		sentencia.setDouble(10, _prestamoVO.getCuota());
		sentencia.setString(11, key);
		sentencia.setString(12, key);
		sentencia.setInt(13, _prestamoVO.getCodigo());*/
		
		return sentencia.executeUpdate();
		
	}
	
	
	public ArrayList<Object []> buscarPrestamoPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarPrestamoPersonalizada);
		ResultSet res;

		ArrayList<Object []> ar = new ArrayList<Object[]>();
		
		sentencia.setString(1, busqueda);
		sentencia.setString(2, busqueda);
		
		res = sentencia.executeQuery();
		
		int cont = 0;
		
		boolean existe = false;
		
		while(res.next()){
		
			Object objetos [] = new Object[2];
			
			String prestamo = "";
			
			existe = true;	
	
			prestamo = res.getString(2) + ": $" + res.getString(3) + " ";
					
			objetos [0] = res.getInt(1);
					
			objetos [1] = prestamo;
			
			ar.add(objetos);
		
		}	
		
		if(existe) 	return ar;
		else return null;
		
	}
}
