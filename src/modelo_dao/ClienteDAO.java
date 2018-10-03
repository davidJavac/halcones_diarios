package modelo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import com.mysql.jdbc.CallableStatement;

import modelo.LogicaCliente;
import modelo_conexion.Conexion;
import modelo_vo.ClienteVO;
import modelo_vo.CobradorVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.EmpleadoVO;
import modelo_vo.LocalidadVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.PedidosVO;
import modelo_vo.VerazVO;

public class ClienteDAO {

	/*private static final String sql_buscarClientePersonalizada = "select dni, nombre, apellido from clientes "
			+ " where estado <> ? and (nombre like concat('%', ?, '%') "
			+ "and apellido like concat('%', ?, '%'))";*/
	
	private static final String sql_buscarClientePersonalizada = "select dni, nombre, apellido from clientes "
			+ " where concat(nombre, ' ', apellido) like concat('%', ?, '%') order by apellido asc";
	
	private static final String sql_buscarClienteAll = "select * from clientes order by apellido asc";
	private static final String sql_buscardnicliente = "select * from clientes where dni = ?";
	private static final String sql_buscarFNcliente = "select * from clientes where "
			+ " DATE_FORMAT(fecha_nacimiento, '%m-%d') = DATE_FORMAT(?, '%m-%d')";
	private static final String sql_buscardni_modificarcliente = "select * from clientes where dni = ? and dni <> ?";
	private static final String sql_buscarPedidos_porEstado = "select * from pedidos where "
			+ "estado_pedido = ? "
			+ "and dni = ?";
	private static final String sql_insertarcliente = "insert into clientes(dni, nombre, apellido, nacionalidad,"
			+ " dni_conyugue,"
			+ "nombre_conyugue, apellido_conyugue, telefono_conyugue, mail_conyugue, "
			+ "estado_civil, telefono_linea, telefono_movil, email,"
			+ "id_vendedor, estado, fecha_nacimiento, fecha_alta, id_usuario, fecha_registro,"
			+ " hora_registro) values(?, ?, ?, ?, ?, ?, ?, ?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sql_insertarOrden_planilla = "insert into orden_planilla(n_orden) "
			+ "values(?) where dni = ? and id_zona = ?";
	
	private static final String sql_updatecliente = "update clientes set dni =?,  nombre = ?, apellido = ?, nacionalidad = ?"
			+ " ,dni_conyugue = ?,"
			+ " nombre_conyugue = ?, apellido_conyugue = ?, telefono_conyugue = ?, mail_conyugue = ?,"
			+ "fecha_nacimiento = ?,  telefono_linea = ?, telefono_movil = ?, email = ?,"
			+ "id_vendedor = ?, estado_civil = ? where dni = ?";
	
	private static final String sql_updateReordenar = "update domicilio_comercial set n_orden_planilla = ? "
			+ "where idc = ?";
	private static final String sql_updateEstado = "update clientes set estado = ? where dni = ?";
	private static final String sql_buscarComercios = "select * from comercios";
	
	
	private static final String sql_buscarClientes_porZona = " select distinct e.*, co.auto_modelo, "
			+ "co.patente, c.*, dp.*, dc.*, l.*,  from empleados e"
			+ " inner join cobradores co on e.id = co.id_cobrador inner join zonas z on co.id_cobrador =  "
			+ "z.id_cobrador inner join clientes c"
			+ " on z.id_zona = c.id_zona inner join domicilio_particular dp "
			+ " on c.dni = dp.dni inner join domicilio_comercial dc on dc.dni = dp.dni inner join localidad l"
			+ " on dc.id_localidad = l.id_localidad where e.puesto = ? and c.estado <> ? and c.id_zona = ? and l.estado <> ? "
			+ "order by c.n_orden_planilla asc";
	
	private static final String sql_buscarClientes_porZona2 = " select  * from clientes c inner join "
			+ "domicilio_comercial dc on c.dni = dc.dni where dc.id_zona = ? and dc.idc in(select idc from"
			+ " pedidos) order by dc.n_orden_planilla asc";
	
	private static final String sql_buscarClientes_porPedido = " select distinct  c.*, dp.*, dc.*, l.*  from pedidos p"
			+ "  inner join clientes c"
			+ " on p.dni = c.dni inner join domicilio_particular dp "
			+ " on c.dni = dp.dni inner join domicilio_comercial dc on dc.dni = dp.dni inner join localidad l"
			+ " on dc.id_localidad = l.id_localidad where c.estado <> ? and p.n_pedido = ? and l.estado <> ? "
			+ "order by c.n_orden_planilla asc";
	
	private static final String sql_buscarCliente_porPedido = "select * from clientes where dni "
			+ "in(select dni from pedidos where n_pedido = ?)";
	
	//private static final String sql_buscarClientes_porZona = " select c.*, dp.* from clientes c inner join domicilio_particular dp "
		//	+ " on c.dni = dp.dni where c.estado <> ? and c.id_zona = ?";
	
	private static final String sp_bajacliente = "{call baja_cliente(?,?,?,?,?,?)}";
	private static final String spClientes_porFiltro = "{call clientes_porFiltro(?,?,?,?,?,?,?)}";
	
	private static final Connection sqlConexion = Conexion.getConexion();
	
	public ArrayList<PedidosVO> buscarPedidos_porEstado(String estado, int dni)throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<PedidosVO> ar = new ArrayList<PedidosVO>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_buscarPedidos_porEstado);
			sentencia.setString(1, estado);
			sentencia.setInt(2, dni);
			
			res = sentencia.executeQuery();
			
			
			int cont = 0;
			
			
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
	public ArrayList<String> buscarComercios()throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		ArrayList<String> ar = new ArrayList<String>();
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_buscarComercios);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				String s = new String();
				
				s = res.getString(2);
				
				ar.add(s);
				
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
	
	public ClienteVO buscarDniCliente(int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ClienteVO _clienteVO = new ClienteVO();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscardnicliente);
			
			sentencia.setInt(1, dni);
			//sentencia.setString(2, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
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
			return _clienteVO;
		}
		else return null;
		
	}
	
	public ArrayList<Object []> buscarCliente_porZona(int zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarClientes_porZona);
			
			sentencia.setString(1, "cobrador");
			sentencia.setString(2, "baja");
			sentencia.setInt(3, zona);
			sentencia.setBoolean(4, false);
			
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				EmpleadoVO empleadoVO = new EmpleadoVO();
				CobradorVO cobradorVO = new CobradorVO();
				ClienteVO _clienteVO = new ClienteVO();
				VerazVO v = new VerazVO();
				DomicilioParticularVO dp = new DomicilioParticularVO();
				DomicilioComercialVO dc = new DomicilioComercialVO();
				LocalidadVO lVO = new LocalidadVO();
				
				Object o [] = new Object[6];
				
				existe = true;
				
				empleadoVO.setId_usuario(res.getShort(1));
				empleadoVO.setDni(res.getInt(2));
				empleadoVO.setPuesto(res.getString(3));
				empleadoVO.setNombre(res.getString(4));
				empleadoVO.setApellido(res.getString(5));
				empleadoVO.setSalario_semanal(res.getDouble(6));
				empleadoVO.setCalle(res.getString(7));
				empleadoVO.setNumero(res.getInt(8));
				empleadoVO.setLocalidad(res.getString(9));
				empleadoVO.setTelefono(res.getString(10));
				empleadoVO.setEmail(res.getString(11));
				empleadoVO.setEstado(res.getBoolean(12));	
				//System.out.println(res.getDate(12));
				empleadoVO.setFecha_nacimiento(res.getDate(13));
				empleadoVO.setFecha_alta(res.getDate(14));
				cobradorVO.setAuto_modelo(res.getString(15));
				cobradorVO.setPatente(res.getString(16));
				
				_clienteVO.setDni(res.getInt(17));
				_clienteVO.setNombre(res.getString(18));
				_clienteVO.setApellido(res.getString(19));
				_clienteVO.setNacionalidad(res.getString(20));
				_clienteVO.setDni_conyugue(res.getInt(21));
				_clienteVO.setNombre_conyugue(res.getString(22));
				_clienteVO.setApellido_conyugue(res.getString(23));
				_clienteVO.setTelefono_conyugue(res.getString(24));
				_clienteVO.setEmail_conyugue(res.getString(25));
				_clienteVO.setEstado_civil(res.getBoolean(26));
				_clienteVO.setTelefono_movil(res.getString(27));
				_clienteVO.setTelefono_linea(res.getString(28));
				_clienteVO.setEmail(res.getString(29));
				_clienteVO.setId_vendedor(res.getShort(30));
				
				_clienteVO.setN_orden_planilla(res.getShort(32));
				_clienteVO.setEstado(res.getString(33));
				_clienteVO.setFecha_nacimiento(res.getDate(34));
				_clienteVO.setFecha_alta(res.getDate(35));
				_clienteVO.setId_usuario(res.getShort(36));
				_clienteVO.setFecha_registro(res.getDate(37));
				_clienteVO.setHora_registro(res.getTime(38));
				
				dp.setDni(res.getInt(39));
				dp.setDomicilio(res.getString(40));
				dp.setEntre_calle1(res.getString(41));
				dp.setEntre_calle2(res.getString(42));
				dp.setBarrio(res.getString(43));
				dp.setCp(res.getInt(44));
				dp.setId_localidad(res.getShort(45));
				dp.setPropio(res.getBoolean(46));
				dp.setDpto(res.getBoolean(47));
				dp.setAntiguedad(res.getDate(48));
				dp.setPiso(res.getShort(49));
				
				dc.setDni(res.getInt(50));
				dc.setDomicilio(res.getString(51));
				dc.setEntre_calle1(res.getString(52));
				dc.setEntre_calle2(res.getString(53));
				dc.setBarrio(res.getString(54));
				dc.setCp(res.getInt(55));
				dc.setId_localidad(res.getShort(56));
				dc.setAntiguedad(res.getDate(57));
				dc.setComercio(res.getInt(58));
				dc.setPropio(res.getBoolean(59));
				dc.setHorario_atencion(res.getString(60));				
				lVO.setId_localidad(res.getShort(61));
				lVO.setLocalidad(res.getString(62));
				lVO.setEstado(res.getBoolean(63));
				
				o[0]= empleadoVO;
				o[1] = cobradorVO;
				o[2]=_clienteVO;
				o[3]= dp;
				o[4]= dc;
				o[5]= lVO;
				
				a.add(o);
		
				sqlConexion.commit();
			}
			
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
			
		}
		finally{
			
			sqlConexion.setAutoCommit(false);
			
		}
		
		if(existe){
			
			LogicaCliente.validaralta = false;
			return a;
		}
		else return null;
		
	}
	public ArrayList<ClienteVO> buscarCliente_porZona2(int zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList<ClienteVO> a = new ArrayList<ClienteVO>();
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarClientes_porZona2);
			
			sentencia.setInt(1, zona);
		
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				ClienteVO _clienteVO = new ClienteVO();
				
				existe = true;
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
				a.add(_clienteVO);
				
				sqlConexion.commit();
			}
			
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
			
		}
		finally{
			
			sqlConexion.setAutoCommit(false);
			
		}
		
		if(existe){
			
			return a;
		}
		else return null;
		
	}
	
	public ArrayList<Object []> buscarCliente_porPedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ArrayList a = new ArrayList();
	
		try{
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarClientes_porPedido);
			
			sentencia.setString(1, "baja");
			sentencia.setInt(2, n_pedido);
			sentencia.setBoolean(3, false);
			
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				ClienteVO _clienteVO = new ClienteVO();
				DomicilioParticularVO dp = new DomicilioParticularVO();
				DomicilioComercialVO dc = new DomicilioComercialVO();
				LocalidadVO lVO = new LocalidadVO();
				
				Object o [] = new Object[4];
				
				existe = true;
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
				dp.setDni(res.getInt(21));
				dp.setDomicilio(res.getString(22));
				dp.setEntre_calle1(res.getString(23));
				dp.setEntre_calle2(res.getString(24));
				dp.setBarrio(res.getString(25));
				dp.setCp(res.getInt(26));
				dp.setId_localidad(res.getShort(27));
				dp.setPropio(res.getBoolean(28));
				dp.setDpto(res.getBoolean(29));
				dp.setAntiguedad(res.getDate(30));
				dp.setPiso(res.getShort(31));
				
				dc.setDni(res.getInt(32));
				dc.setDomicilio(res.getString(33));
				dc.setEntre_calle1(res.getString(34));
				dc.setEntre_calle2(res.getString(35));
				dc.setBarrio(res.getString(36));
				dc.setCp(res.getInt(37));
				dc.setId_localidad(res.getShort(38));
				dc.setAntiguedad(res.getDate(39));
				dc.setComercio(res.getInt(40));
				dc.setPropio(res.getBoolean(41));
				dc.setHorario_atencion(res.getString(42));
				
				lVO.setId_localidad(res.getShort(43));
				lVO.setLocalidad(res.getString(44));
				lVO.setEstado(res.getBoolean(45));
				
				o[0]=_clienteVO;
				o[1]= dp;
				o[2]= dc;
				o[3]= lVO;
				
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
	
	public ArrayList<ClienteVO> buscarClienteFiltros(int codigo,int codigo2, int codigo3, String ex,
			String operando, String nuevo)throws SQLException{
		
		java.sql.CallableStatement sentencia = null;
		ResultSet res;
		
		 ArrayList<ClienteVO> ar = new  ArrayList<ClienteVO>();
		
		boolean existe = false;
		
		try{
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareCall(spClientes_porFiltro);
			
			sentencia.setInt(1, codigo);
			sentencia.setInt(2, codigo2);
			sentencia.setInt(3, codigo3);
			sentencia.setString(4, ex);
			sentencia.setString(5, operando);
			sentencia.setString(6, nuevo);
			sentencia.setString(7, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				ClienteVO _clienteVO = new ClienteVO();
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
				ar.add(_clienteVO);
				
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
		
			return ar;
		}
		else return null;
		
	}
	
	public ClienteVO buscarCliente_porNPedido(int n_pedido) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		
		boolean existe = false;
		
		ClienteVO _clienteVO = new ClienteVO();
		try{
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_buscarCliente_porPedido);
			
			sentencia.setInt(1, n_pedido);
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
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
		
			return _clienteVO;
		}
		else return null;
		
	}
	
	public int buscarDni_modificarCliente(ClienteVO _clienteVO, int dni2) throws SQLException{
		
		PreparedStatement sentencia = null;

		ResultSet res;
		
		boolean existe = false;
		
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updatecliente);
			
			sentencia.setInt(1, _clienteVO.getDni());
			sentencia.setString(2, _clienteVO.getNombre());
			sentencia.setString(3, _clienteVO.getApellido());
			sentencia.setString(4, _clienteVO.getNacionalidad());
			sentencia.setInt(5, _clienteVO.getDni_conyugue());
			sentencia.setString(6, _clienteVO.getNombre_conyugue());
			sentencia.setString(7, _clienteVO.getApellido_conyugue());
			sentencia.setString(8, _clienteVO.getTelefono_conyugue());
			sentencia.setString(9, _clienteVO.getEmail_conyugue());
			sentencia.setDate(10, _clienteVO.getFecha_nacimiento());
			sentencia.setString(11, _clienteVO.getTelefono_linea());
			sentencia.setString(12, _clienteVO.getTelefono_movil());
			sentencia.setString(13, _clienteVO.getEmail());
			sentencia.setShort(14, _clienteVO.getId_vendedor());
			sentencia.setBoolean(15, _clienteVO.getEstado_civil());
			sentencia.setInt(16, dni2);
			
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
	
	public ArrayList<Object []> buscarClienteAll() throws SQLException{
		
		PreparedStatement sentencia = null;
		boolean existe = false;
		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarClienteAll);
			ResultSet res;
			
			
			
			//sentencia.setString(1, "baja");
			
			res = sentencia.executeQuery();
			
			while(res.next()){
				
				existe = true;
				
				Object objetos [] = new Object[3];
				String nombre_apellido = "";
				
				nombre_apellido = res.getString(2) + " " + res.getString(3);
				
				objetos[0] = res.getInt(1);
				objetos[1] = nombre_apellido;
				
				ar.add(objetos);
				
				sqlConexion.commit();
			}	
		}
		catch(SQLException e){
			
			if(sqlConexion!=null){
				
				sqlConexion.rollback();
				
			}
			
		}
		finally{
			
			if(sentencia!=null) sentencia.close();
			
			sqlConexion.setAutoCommit(true);
		}
		
		
		if(existe) 	return ar;
		else return null;
		
	}
	
	public ArrayList<Object []> buscarClientePersonalizada(String busqueda) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		boolean existe = false;
		ArrayList<Object []> ar = new ArrayList<Object[]>();
	
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_buscarClientePersonalizada);
			
			String cliente = "";
			
			sentencia.setString(1, busqueda);
			
			res = sentencia.executeQuery();
			
			int cont = 0;
			
			
			while(res.next()){
				
				Object objetos [] = new Object[2];
				
				existe = true;	
				
				cliente = res.getString(2) + " " + res.getString(3) + " ";
				
				objetos [0] = res.getInt(1);
				
				objetos [1] = cliente;
				
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
	
	public int bajaCliente(ClienteVO _clienteVO, VerazVO vVO,
			Observaciones_clienteVO ocVO) throws SQLException{
		
		java.sql.CallableStatement sentencia =  null;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia =  sqlConexion.prepareCall(sp_bajacliente);
			
			sentencia.setString(1, "baja");
			sentencia.setInt(2, _clienteVO.getDni());
			sentencia.setInt(3, vVO.getId_usuario());
			sentencia.setDate(4, vVO.getFecha_registro());
			sentencia.setTime(5, vVO.getHora_registro());
			sentencia.setString(6, ocVO.getObservacion());
			
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
	
	public int updateReordenar(DomicilioComercialVO dcVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		try{
			
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateReordenar);
			
			sentencia.setInt(1, dcVO.getN_orden_planilla());
			sentencia.setInt(2, dcVO.getIdc());
			
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
	
	public int updateEstado(String estado, int dni) throws SQLException{
		
		PreparedStatement sentencia = null;
		ResultSet res;
		try{
		
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_updateEstado);
			sentencia.setString(1, estado);
			sentencia.setInt(2, dni);
			
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
	
	public int insertarCliente(ClienteVO _clienteVO) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertarcliente);
			
			sentencia.setInt(1, _clienteVO.getDni());
			sentencia.setString(2, _clienteVO.getNombre());
			sentencia.setString(3, _clienteVO.getApellido());
			sentencia.setString(4, _clienteVO.getNacionalidad());
			sentencia.setInt(5, _clienteVO.getDni_conyugue());
			sentencia.setString(6, _clienteVO.getNombre_conyugue());
			sentencia.setString(7, _clienteVO.getApellido_conyugue());
			sentencia.setString(8, _clienteVO.getTelefono_conyugue());
			sentencia.setString(9, _clienteVO.getEmail_conyugue());
			sentencia.setBoolean(10, _clienteVO.getEstado_civil());
			sentencia.setString(11, _clienteVO.getTelefono_linea());
			sentencia.setString(12, _clienteVO.getTelefono_movil());
			sentencia.setString(13, _clienteVO.getEmail());
			sentencia.setInt(14, _clienteVO.getId_vendedor());
			sentencia.setString(15, _clienteVO.getEstado());
			sentencia.setDate(16, _clienteVO.getFecha_nacimiento());
			sentencia.setDate(17, _clienteVO.getFecha_alta());
			sentencia.setShort(18, _clienteVO.getId_usuario());
			sentencia.setDate(19, _clienteVO.getFecha_registro());
			sentencia.setTime(20, _clienteVO.getHora_registro());
			
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
	public int insertarOrden_planilla(ClienteVO _clienteVO, int id_zona) throws SQLException{
		
		PreparedStatement sentencia = null;
		
		try{
			sqlConexion.setAutoCommit(false);
			sentencia = sqlConexion.prepareStatement(sql_insertarOrden_planilla);
			
			sentencia.setInt(1, 1);
			sentencia.setInt(2, _clienteVO.getDni());
			sentencia.setInt(3, id_zona);
			
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
	
	public ArrayList<ClienteVO> comprobarFecha_nacimiento(java.sql.Date hoy) throws SQLException{
		
		boolean existe = false;
		PreparedStatement sentencia = null;
		ArrayList<ClienteVO> ar = new ArrayList<ClienteVO>();
		
		try{
			sqlConexion.setAutoCommit(false);
			
			sentencia = sqlConexion.prepareStatement(sql_buscarFNcliente);
			ResultSet res;
			
			ArrayList a = new ArrayList();
			VerazVO v = new VerazVO();
			DomicilioParticularVO dp = new DomicilioParticularVO();
			DomicilioComercialVO dc = new DomicilioComercialVO();
			
			sentencia.setDate(1, hoy);
				
			
			res = sentencia.executeQuery();
			
			
			while(res.next()){
				
				existe = true;
				
				ClienteVO _clienteVO = new ClienteVO();
				
				_clienteVO.setDni(res.getInt(1));
				_clienteVO.setNombre(res.getString(2));
				_clienteVO.setApellido(res.getString(3));
				_clienteVO.setNacionalidad(res.getString(4));
				_clienteVO.setDni_conyugue(res.getInt(5));
				_clienteVO.setNombre_conyugue(res.getString(6));
				_clienteVO.setApellido_conyugue(res.getString(7));
				_clienteVO.setTelefono_conyugue(res.getString(8));
				_clienteVO.setEmail_conyugue(res.getString(9));
				_clienteVO.setEstado_civil(res.getBoolean(10));
				_clienteVO.setTelefono_movil(res.getString(11));
				_clienteVO.setTelefono_linea(res.getString(12));
				_clienteVO.setEmail(res.getString(13));
				_clienteVO.setId_vendedor(res.getShort(14));
				_clienteVO.setEstado(res.getString(15));
				_clienteVO.setFecha_nacimiento(res.getDate(16));
				_clienteVO.setFecha_alta(res.getDate(17));
				_clienteVO.setId_usuario(res.getShort(18));
				_clienteVO.setFecha_registro(res.getDate(19));
				_clienteVO.setHora_registro(res.getTime(20));
				
				ar.add(_clienteVO);
				
				sqlConexion.commit();
			}
			
		}
		catch(SQLException e){
			
			if(sqlConexion!=null) sqlConexion.rollback();
		}
		finally{
			if(sentencia!=null) sentencia.close();
			sqlConexion.setAutoCommit(true);
		}
		
		
		
		if(existe){
			
			return ar;
		}
		else return null;
		
	}
	
}
