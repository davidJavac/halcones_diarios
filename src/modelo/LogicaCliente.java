package modelo;

import java.awt.Component;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import controlador.ControladorArticulo;
import controlador.ControladorCliente;
import controlador.ControladorPagoDiario;
import controlador.ControladorPedidos;
import modelo_dao.ClienteDAO;
import modelo_dao.VendedorDAO;
import modelo_dao.VerazDAO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import modelo_vo.Observaciones_clienteVO;
import modelo_vo.PedidosVO;
import modelo_vo.VerazVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;
import vista.VistaBuscarCliente;

public class LogicaCliente {

	private ControladorCliente _controladorCliente;
	private ControladorPedidos _controladorPedidos;
	private static int numero = 0;
	private static double numero_double = 0;
	private static final String EMAIL_PATRON = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static boolean validarbusquedaUsuario;
	public static boolean validaraltaUsuario;
	public static boolean validarmodificacionUsuario;
	public static boolean validaraltaVeraz;
	public static boolean validaralta;
	public static boolean validarmodificacion;
	public static boolean validarbaja;
	public static boolean vacio;
	public static boolean excede_caracteres;
	public static boolean no_entero;
	public static boolean error_email;
	public static boolean error_fecha;
	public static int resultado_insertCliente;
	private BusquedaEntities busqueda_entities;
	private VistaBuscarCliente vista_buscar_cliente;
	
	public void setControladorCliente(ControladorCliente _controladorCliente){
		
		this._controladorCliente = _controladorCliente;
	}
	public void setControladorPedidos(ControladorPedidos _controladorPedidos){
		
		this._controladorPedidos = _controladorPedidos;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public void validarBusquedaAll(){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
	
		busqueda_entities.limpiar();
		
		ArrayList<Object []> ar = new ArrayList<Object []>();
		try {
			ar = _clienteDAO .buscarClienteAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(Object [] o : ar){
				
				busqueda_entities.getTabla().addRow(o);
					
			}
			
			busqueda_entities.setTablaModel();
			
		
		if(busqueda_entities.getTabla().getRowCount() > 0)
			busqueda_entities.setTablaModel();
		}
				
	}
	public ArrayList<VerazVO> validarBusquedaVerazAll(){
		
		VerazDAO vDAO = new VerazDAO();

		try {
			return  vDAO .buscarVerazAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	public ArrayList<Object []> validarBusqueda_porZona(int id_zona){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarCliente_porZona(id_zona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<ClienteVO> validarBusqueda_porZona2(int id_zona){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarCliente_porZona2(id_zona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Object []> validarBusqueda_porPedido(int n_pedido){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarCliente_porPedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ClienteVO validarBusqueda_porNPedido(int n_pedido){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarCliente_porNPedido(n_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<String> buscarComercios(){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarComercios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
			
			busqueda_entities.limpiar();
				
				ArrayList<Object[]> ar = new ArrayList<Object[]>();
				try {
					ar = _clienteDAO.buscarClientePersonalizada(busqueda);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(ar != null){
						
						for(Object [] o : ar){
							
							busqueda_entities.getTabla().addRow(o);
						}
				}			

			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
			
	}
	
	public ClienteVO validarBusqueda(String dni){
		
		ClienteDAO _clienteDAO;
		boolean val_num;
		boolean val_cant;
		
		validarbusquedaUsuario = true;
		
		if(dni.equals("")){
			
			JOptionPane.showMessageDialog(null, "No puedes dejar el campo vacio", "Error",
					JOptionPane.ERROR_MESSAGE, null);
			
			validarbusquedaUsuario = false;
			
			return null;
		}
		
		val_num = validarInt(dni);
		
		val_cant = validarCantidadCaracteres(dni);
		
		if(!val_num){
			
			JOptionPane.showMessageDialog(null, "Debes ingresar numeros en el campo DNI", "Error",
					JOptionPane.ERROR_MESSAGE, null);	
			
			validarbusquedaUsuario = false;
			return null;
		}
				
		if(!val_cant){
			
			JOptionPane.showMessageDialog(null, "Has excedido el limite de caracteres para este campo",
					"Error",JOptionPane.ERROR_MESSAGE, null);
			
			validarbusquedaUsuario = false;
			return null;
		}
		
		_clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.buscarDniCliente(numero);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
				
	}
	
	public void validarAltaUsuario(VistaAltaCliente _vista_alta_cliente){
		
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		error_email = false;
		error_fecha = false;
		ArrayList<Character> array_email = new ArrayList<Character>();
		String email = _vista_alta_cliente.getEmail().getText();
		
		validaraltaUsuario = true;
		
		for(JTextField tf : _vista_alta_cliente.getArrayString()){
			
			if(tf.getText().equals("")){
				
				vacio = true;
				
				validaraltaUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_alta_cliente.getArrayInt()){
			
			if(tf.getText().equals("")){
				
				vacio = true;
				
				validaraltaUsuario = false;
			}
		}
		
		int i = 0;
		
		for(JTextField tf : _vista_alta_cliente.getArrayConyugue()){
			
			if(_vista_alta_cliente.getCasado().isSelected()){
				
				if(i==0){
					
					if(!validarInt(tf.getText())) no_entero = true;
					if( numero < 0 || numero > 100000000)  no_entero = true; 
					
				}
				
				if(tf.getText().equals("")){
					
					vacio = true;
					
					validaraltaUsuario = false;
				}
				
				if(i==5){
					
					if(!validar_email(tf.getText())){
						
						error_email = true;
						validaraltaUsuario =false;
					}
				}
			}
			
			i++;
		}
			
		for(JTextField tf : _vista_alta_cliente.getArrayString()){
				
			if(tf.getText().length() > 100){
						
				excede_caracteres = true;
						
				validaraltaUsuario = false;
			}
		}
				
		for(JTextField tf : _vista_alta_cliente.getArrayInt()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  no_entero = true; 
		
		}
				
		if(no_entero) validaraltaUsuario = false;
		
		error_email = validar_email(email);
		
		if(error_email) validaraltaUsuario = false;
		
		/*Date d = new java.util.Date();
		java.sql.Date hoy = new java.sql.Date(d.getTime());*/
		
		if(!vacio){
			
			for(JDatePickerImpl dp : _vista_alta_cliente.getArrayDatePicker()){
				
				String dpS = dp.getJFormattedTextField().getText();;
				
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(dpS, formato);
				
				LocalDate hoyf = LocalDate.now();//For reference
				DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);		
				String formattedString = hoyf.format(formato2);
				LocalDate hoy = LocalDate.parse(formattedString, formato2);
				
				if(date.isAfter(hoy)){
					
					error_fecha = true;
					validaraltaUsuario = false;
				}
				System.out.println(date + " "  + hoy);
			}
		}
		
		
	}
	
	public VerazVO comprobarRelacion_enVeraz(ClienteVO _clienteVO,
			DomicilioParticularVO dpVO, 
				DomicilioComercialVO dcVO){
		
		VerazDAO _verazDAO = new VerazDAO();
		
		try {
			return _verazDAO.comprobarRelacion_enVeraz(_clienteVO, dpVO, dcVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public VerazVO comprobarVeraz_porDni(int dni){
		
		VerazDAO _verazDAO = new VerazDAO();
		
		try {
			return _verazDAO.comprobarVeraz_porDni(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public VerazVO comprobarVeraz_porDni_conyugue(ClienteVO cVO){
		
		VerazDAO _verazDAO = new VerazDAO();
		
		try {
			return _verazDAO.buscarDniConyugue(cVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public VerazVO comprobarVeraz_porDom_part(DomicilioParticularVO dpVO){
		
		VerazDAO _verazDAO = new VerazDAO();
		
		try {
			return _verazDAO.buscarDireccion(dpVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	public VerazVO comprobarVeraz_porDom_com(DomicilioComercialVO dpVO){
		
		VerazDAO _verazDAO = new VerazDAO();
		
		try {
			return _verazDAO.buscarDireccion_comercial(dpVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void actualizarEstado(ClienteVO cVO){
		
		ClienteDAO cDAO = new ClienteDAO();
		
		ArrayList<PedidosVO> ar = null;
		
		try {
			ar = cDAO.buscarPedidos_porEstado("activo", cVO.getDni());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ar!=null){
			
			int cont = 0;
			for(PedidosVO pVO : ar){
				System.out.println("Pedido " + pVO.getN_pedido());
				cont++; 
			}
			
			System.out.println("cliente " + cVO.getNombre() + " " + cVO.getApellido() + " cantidad p " + cont);
			if((cont>1 && cVO.getEstado().equals("nuevo") || cVO.getEstado().equals("ex"))){
				
				_controladorCliente.updateEstado("operando", cVO.getDni());
				cVO.setEstado("operando");
				
			}
			
		}
		else{
			
			if(cVO.getEstado().equals("operando")){
				
				_controladorCliente.updateEstado("ex", cVO.getDni());
				cVO.setEstado("ex");
				
			}
		}
		
		 //_controladorCliente.buscarCliente(Integer.toString(cVO.getDni()));
	}
	
	public void validarAlta(ClienteVO _clienteVO){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		validaralta = true;
		
		try {
			_clienteDAO.buscarDniCliente(_clienteVO.getDni());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!validaralta) Mensajes.getMensaje_clienteExiste();
		else{
			
			try {
				resultado_insertCliente = _clienteDAO.insertarCliente(_clienteVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	
	}
	
	public void validarModificacionUsuario(VistaBuscarCliente _vista_buscar_cliente){
		
		boolean val_num = false;
		vacio = false;
		excede_caracteres = false;
		no_entero = false;
		error_email = false;
		ArrayList<Character> array_email = new ArrayList<Character>();
		String email = _vista_buscar_cliente.getEmail().getText();
		
		validarmodificacionUsuario = true;
		
		for(JTextField tf : _vista_buscar_cliente.getArrayString()){
			
			if(tf.getText().equals("")){
				
				vacio = true;
				
				validarmodificacionUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_buscar_cliente.getArrayInt()){
			
			if(tf.getText().equals("")){
				
				vacio = true;
				
				validarmodificacionUsuario = false;
			}
		}
		
		int i = 0;
		
		for(JTextField tf : vista_buscar_cliente.getArrayConyugue()){
			
			if(vista_buscar_cliente.getCasado().isSelected()){
				
				System.out.println("casado true");
				
				if(i==0){
					
					if(!validarInt(tf.getText())) no_entero = true;
					if( numero < 0 || numero > 100000000)  no_entero = true; 
					
				}
				
				if(tf.getText().equals("")){
					
					vacio = true;
					
					validarmodificacionUsuario = false;
				}
				
				if(i==5){
					
					if(!validar_email(tf.getText())){
						
						error_email = true;
						validarmodificacionUsuario =false;
					}
				}
				
			}
			
			i++;
		}
			
		for(JTextField tf : _vista_buscar_cliente.getArrayString()){
				
			if(tf.getText().length() > 100){
						
				excede_caracteres = true;
						
				validarmodificacionUsuario = false;
			}
		}
				
		for(JTextField tf : _vista_buscar_cliente.getArrayInt()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  no_entero = true; 
		
		}
				
		if(no_entero) validarmodificacionUsuario = false;
		
		error_email = validar_email(email);
		
		if(error_email) validarmodificacionUsuario = false;
	}
	
	public int validarModificacion(ClienteVO _clienteVO){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		validarmodificacion = true;
		
		try {
			
			return _clienteDAO.buscarDni_modificarCliente(_clienteVO, vista_buscar_cliente.getDni_anterior());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		//if(!validaralta) Mensajes.getMensaje_clienteExiste();
		/*else
			try {
				resultado_insertCliente = _clienteDAO.insertarCliente(_clienteVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	
	}
	public int updateEstado(String estado, int dni){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			
			return _clienteDAO.updateEstado(estado, dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	public int insertarOrden_planilla(ClienteVO cVO, int id_zona){
		
		ClienteDAO _clienteDAO = new ClienteDAO();	
		try {
			
			return _clienteDAO.insertarOrden_planilla(cVO, id_zona);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	public int validarReordenar_planilla(DomicilioComercialVO dcVO){
		
		ClienteDAO _clienteDAO = new ClienteDAO();	
		try {
			
			return _clienteDAO.updateReordenar(dcVO);
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
	
	public ArrayList<ClienteVO> buscarClienteFiltros(JTextField efectividad, JTextField comercioTF,
			JTextField comercio2TF, JTextField comercio3TF, String ex,
			String operando, String nuevo){
		
		if(!validarInt(comercioTF.getText())) comercioTF.setText("0");
		if(!validarInt(comercio2TF.getText())) comercio2TF.setText("0");
		if(!validarInt(comercio3TF.getText())) comercio3TF.setText("0");
		
		ClienteDAO _clienteDAO = new ClienteDAO();	
		try {
			
			ArrayList<ClienteVO> ar =  _clienteDAO.buscarClienteFiltros(Integer.parseInt(comercioTF.getText()),
					Integer.parseInt(comercio2TF.getText()),
					Integer.parseInt(comercio3TF.getText()), ex, operando, nuevo);
				
				if(efectividad.getText().equals(""))efectividad.setText("0");
			
				if(validarDouble(efectividad.getText())){
					
					if(ar!=null){
						
						ArrayList<ClienteVO> ar_efec = new ArrayList<ClienteVO>();
						
						for(ClienteVO cVO : ar){
							
							ArrayList<PedidosVO> arpVO = _controladorPedidos.
									buscarPedidos_porClienteTodos_estados(cVO.getDni());
							
							double acu_fac_ideal = 0;
							double acu_fac = 0;
							
							if(arpVO!=null){
								
								for(PedidosVO pVO : arpVO){
									
									acu_fac_ideal += pVO.getDias_desde_inicio() * pVO.getCuota_diaria();
									acu_fac += pVO.getFacturacion();
								}
								
								double efec = Math.round( acu_fac *100 / acu_fac_ideal);
								
								if(efec >= numero_double){
									
									cVO.setEfectividad(efec);
									ar_efec.add(cVO);
								}
							}
							else if(efectividad.getText().equals("0")){
								ar_efec.add(cVO);
								
							}
								
						}
						
						return ar_efec;
					}
					
				}
				else efectividad.setText("");
			
			return ar;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<ClienteVO> comprobarFecha_nacimiento(java.sql.Date hoy){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		
		try {
			return _clienteDAO.comprobarFecha_nacimiento(hoy);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int validarBaja(ClienteVO _clienteVO, VerazVO vVO, Observaciones_clienteVO ocVO){
		
		ClienteDAO _clienteDAO = new ClienteDAO();
		validarbaja = true;
		
		try {
			
			return _clienteDAO.bajaCliente(_clienteVO, vVO, ocVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	
	}
	
	public boolean validar_email(String email){
		
		boolean error = false;
		
		Pattern p = Pattern.compile(EMAIL_PATRON);
		Matcher m = p.matcher(email);
				
		if(!m.matches()) error = true;
		
		return error;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}

	
	public boolean validarCantidadCaracteres(String dni){
		
		int cantidad;
		
		cantidad = dni.length();
		
		if(cantidad > 30) return false;
		else return true;
	}
	
}
