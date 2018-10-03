package modelo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePickerImpl;

import controlador.ControladorDomicilioComercial;
import modelo_dao.Domicilio_comercialDAO;
import modelo_dao.Domicilio_particularDAO;
import modelo_dao.VendedorDAO;
import modelo_vo.ClienteVO;
import modelo_vo.ComercioVO;
import modelo_vo.DomicilioComercialVO;
import modelo_vo.DomicilioParticularVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;
import vista.VistaBuscarCliente;

public class LogicaDomicilioComercial {

	private ControladorDomicilioComercial _controladorDomCom;
	private int numero = 0;
	public static int resultado_insertDomCom;
	private VistaBuscarCliente vista_buscar_cliente;
	private BusquedaEntities be;
	
	public void setControladorDomicilioComercial(ControladorDomicilioComercial _controladorDomCom){
		
		this._controladorDomCom = _controladorDomCom;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public ArrayList<DomicilioComercialVO> validarBusqueda(String dni){
		
		Domicilio_comercialDAO _domicilio_comercialDAO;
		boolean val_num;
		boolean val_cant;
		
		_domicilio_comercialDAO = new Domicilio_comercialDAO();
		
		if(LogicaCliente.validarbusquedaUsuario){
			
			try {
				return _domicilio_comercialDAO.buscarDomicilio_comercial(Integer.parseInt(dni));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;		
	}
	public ArrayList<DomicilioComercialVO> validarBusqueda2(String dni){
		
		if(validarInt(dni)){
			
			Domicilio_comercialDAO _domicilio_comercialDAO;
			
			_domicilio_comercialDAO = new Domicilio_comercialDAO();
			
			try {
				return _domicilio_comercialDAO.buscarDomicilio_comercial(numero);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
		return null;		
	}

	public ArrayList<DomicilioComercialVO> buscarDomicilio_comercial_porZona(int zona){
		
			
			Domicilio_comercialDAO _domicilio_comercialDAO;
			
			_domicilio_comercialDAO = new Domicilio_comercialDAO();
			
			try {
				return _domicilio_comercialDAO.buscarDomicilio_comercial_porZona(zona);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return null;		
		
	}
	
	public DomicilioComercialVO buscarDomicilioComercial_porIdc(int idc){
		
		Domicilio_comercialDAO _domicilio_comercialDAO;
		
		_domicilio_comercialDAO = new Domicilio_comercialDAO();
		
		try {
			return _domicilio_comercialDAO.buscarDomicilioComercial_porIdc(idc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;		
		
	}
	
	public ComercioVO validarBusquedaComercio(String codigo){
		
		if(!validarInt(codigo)) return null;
		
		Domicilio_comercialDAO _domicilio_comercialDAO;

		_domicilio_comercialDAO = new Domicilio_comercialDAO();
			
			try {
				return _domicilio_comercialDAO.buscarComercio(numero);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return null;		
	}
	
	
	public void validarBusquedaAll(){
		
		Domicilio_comercialDAO dDAO = new Domicilio_comercialDAO();
	
		be.setTipoBusqueda(21);
		be.limpiar();
		
		ArrayList<Object []> ar = new ArrayList<Object []>();
		try {
			ar = dDAO.buscarComercioAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(ar!=null){
			
			for(Object [] o : ar){
				System.out.println(o[1]);
				be.getTabla().addRow(o);
					
			}
			
			be.setTablaModel();
			
		
		if(be.getTabla().getRowCount() > 0)
			be.setTablaModel();
		}
				
	}
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		Domicilio_comercialDAO dDAO = new Domicilio_comercialDAO();

			be.limpiar();
				
				ArrayList<Object[]> ar = new ArrayList<Object[]>();
				try {
					ar = dDAO.buscarComercioPersonalizada(busqueda);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(ar != null){
						
						for(Object [] o : ar){
							
							be.getTabla().addRow(o);
						}
				}			

			if(be.getTabla().getRowCount() > 0)
				be.setTablaModel();
			
	}
	public void validarAltaUsuario(VistaAltaCliente _vista_alta_cliente){
		
		boolean val_num = false;
		/*vacio = false;
		bexede_caracteres = false;
		no_entero = false;*/
		
		//LogicaCliente.validaraltaUsuario = true;
		
		//Validacion DNI
		
		for(JTextField tf : _vista_alta_cliente.getArrayStringDomCom()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validaraltaUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_alta_cliente.getArrayIntDomCom()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validaraltaUsuario = false;
			}
		}
			
		for(JTextField tf : _vista_alta_cliente.getArrayStringDomCom()){
				
			if(tf.getText().length() > 100){
						
				LogicaCliente.excede_caracteres = true;
						
				LogicaCliente.validaraltaUsuario = false;
			}
		}
				
		for(JTextField tf : _vista_alta_cliente.getArrayIntDomCom()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  LogicaCliente.no_entero = true; 
		
		}
				
		if(LogicaCliente.no_entero) LogicaCliente.validaraltaUsuario = false;
		
		
	}
	public boolean validarAltaUsuario(ArrayList<JTextField> stringdc, ArrayList<JTextField> 
											intdc, JDatePickerImpl dp ){
		
		boolean val_num = false;
		
		for(JTextField tf : stringdc){
			
			if(tf.getText().equals("")){
				
				return false;
			}
		}
		
		for(JTextField tf : intdc){
			
			if(tf.getText().equals("")){
				
				return false;
			}
		}
		
		for(JTextField tf : stringdc){
			
			if(tf.getText().length() > 100){
				
				return false;
			}
		}
		
		for(JTextField tf : intdc){
			
			val_num = validarInt(tf.getText());
			
			if(!val_num || numero < 0 || numero > 100000000) return false; 
			
		}
	
			String dpS = dp.getJFormattedTextField().getText();;
			
			if(!dpS.equals("")){
				
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
				LocalDate date = LocalDate.parse(dpS, formato);
				
				LocalDate hoyf = LocalDate.now();//For reference
				DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);		
				String formattedString = hoyf.format(formato2);
				LocalDate hoy = LocalDate.parse(formattedString, formato2);
				
				if(date.isAfter(hoy)){
					
					return false;
				}
				
			}
			else return false;

		
		return true;
	}
	
	public void validarAlta(DomicilioComercialVO _domicilio_comercialVO){
		
		Domicilio_comercialDAO _domicilio_comercialDAO = new Domicilio_comercialDAO();
		
		if(LogicaCliente.validaralta){
			
			try {
				resultado_insertDomCom = _domicilio_comercialDAO.insertarDomicilio_comercial(_domicilio_comercialVO);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void validarModificacionUsuario(VistaBuscarCliente _vista_buscar_cliente){
		
		boolean val_num = false;
		/*vacio = false;
		bexede_caracteres = false;
		no_entero = false;*/
		
		//LogicaCliente.validaraltaUsuario = true;
		
		//Validacion DNI
		
		for(JTextField tf : _vista_buscar_cliente.getArrayStringDomCom()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validarmodificacionUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_buscar_cliente.getArrayIntDomCom()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validarmodificacionUsuario  = false;
			}
		}
			
		for(JTextField tf : _vista_buscar_cliente.getArrayStringDomCom()){
				
			if(tf.getText().length() > 100){
						
				LogicaCliente.excede_caracteres = true;
						
				LogicaCliente.validarmodificacionUsuario  = false;
			}
		}
				
		for(JTextField tf : _vista_buscar_cliente.getArrayIntDomCom()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  LogicaCliente.no_entero = true; 
		
		}
				
		if(LogicaCliente.no_entero) LogicaCliente.validarmodificacionUsuario  = false;
		
		
	}
	
	public int validarModificacion(DomicilioComercialVO _domicilio_comercialVO, int dni){
		
		Domicilio_comercialDAO _domicilio_comercialDAO = new Domicilio_comercialDAO();
		
		try {
			
			return _domicilio_comercialDAO.modificarDomCom
					(_domicilio_comercialVO,dni, vista_buscar_cliente.getDni_anterior());
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
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
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
	
	public void setBusquedaEntities(BusquedaEntities be){
		
		this.be = be;
	}
}
