package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo_conexion.Conexion;
import modelo_vo.ArticulosVO;
import modelo_vo.ClienteVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.VerazVO;

public class ComboDAO {

	private static final String sql_buscarLocalidadPersonalizada = "select id_localidad, localidad from localidad"
			+ " where (localidad like concat('%', ?, '%')) "
			+ " and estado = ?";
	
	private static final String sql_buscarComboAll = "select * from combos";
	private static final String sql_buscar_Arraycombo = "select * from combos where codigo = ?";
	
	private static final String sql_buscar_combo = "select * from combos where codigo_combo = ?";
			
	private static final String sql_buscardni_modificarcliente = "select * from clientes where dni = ? and dni <> ?";
	private static final String sql_insertarcombo = "insert into combos(articulo1, articulo2, articulo3, articulo4,"
			+ " articulo5, dias, cuota_diaria) values(?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sql_buscarComboPersonalizada = "select * from combos where (articulo1 like concat('%', ?, '%') or articulo2 like concat('%', ?, '%')"
			+ " or articulo3 like concat('%', ?, '%') or articulo4 like concat('%', ?, '%') "
			+ " or articulo5 like concat('%', ?, '%'))";
	
	private static final String sql_updatecliente = "update clientes set dni =?,  nombre = ?, apellido = ?, nacionalidad = ?"
			+ " ,dni_conyugue = ?,"
			+ " nombre_conyugue = ?, apellido_conyugue = ?, telefono_linea = ?, telefono_movil = ?, email = ?,"
			+ "id_vendedor = ?, id_zona = ?, fecha_alta = ? where dni = ?";
	
	private static final String sql_bajacliente = "update clientes set estado = ? where dni = ?";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<Object []> buscarComboPersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarComboPersonalizada);
		ResultSet res;
		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		
		sentencia.setString(1, busqueda);
		sentencia.setString(2, busqueda);
		sentencia.setString(3, busqueda);
		sentencia.setString(4, busqueda);
		sentencia.setString(5, busqueda);
	
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
		
			existe = true;
			
			String articulos_combinados = "";
			
			Object objetos [] = new Object[2];
			
			articulos_combinados = res.getString(2) + " " + res.getString(3) + " " + res.getString(4)
			+ " " + res.getString(5) + " " + res.getString(6);
			
			objetos[0] = res.getShort(1);
			objetos[1] = articulos_combinados;
			
			ar.add(objetos);
		}	

		if(existe) 	return ar;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarComboAll() throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_buscarComboAll);
		ResultSet res;

		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		
		res = sentencia.executeQuery();
		
		boolean existe = false;
		
		while(res.next()){
		
			existe = true;
			
			String articulos_combinados = "";
			
			Object objetos [] = new Object[2];
			
			articulos_combinados = res.getString(2) + " " + res.getString(3) + " " + res.getString(4)
			+ " " + res.getString(5) + " " + res.getString(6);
			
			objetos[0] = res.getShort(1);
			objetos[1] = articulos_combinados;
			
			ar.add(objetos);
		}	

		if(existe) 	return ar;
		else return null;
		
	}
	
	public int buscarDni_modificarCliente(ClienteVO _clienteVO, int dni2) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_updatecliente);
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
		//ClienteVO _clienteVO = new ClienteVO();
		VerazVO v = new VerazVO();
		DomicilioParticularVO dp = new DomicilioParticularVO();
		DomicilioComercialVO dc = new DomicilioComercialVO();
	
		return sentencia.executeUpdate();
		
		
		
	}
	public int bajaCliente(ClienteVO _clienteVO) throws SQLException{
		
		PreparedStatement sentencia = sqlConexion.prepareStatement(sql_bajacliente);
		ResultSet res;
	
		sentencia.setString(1, "baja");
		sentencia.setInt(2, _clienteVO.getDni());
		
		return sentencia.executeUpdate();
		
	}

}
