package modelo;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorCliente;
import controlador.ControladorDomicilioParticular;
import modelo_dao.ClienteDAO;
import modelo_dao.Domicilio_particularDAO;
import modelo_vo.ClienteVO;
import modelo_vo.DomicilioParticularVO;
import vista.VistaAltaCliente;
import vista.VistaBuscarCliente;

public class LogicaDomicilioParticular {

	private ControladorDomicilioParticular _controladorDomPart;
	private int numero = 0;
	public static int resultado_insertDomPart;
	private VistaBuscarCliente vista_buscar_cliente;
	
	public void setControladorDomicilioParticular(ControladorDomicilioParticular _controladorDomPart){
		
		this._controladorDomPart = _controladorDomPart;
	}
	
	public void setVistaBuscarCliente(VistaBuscarCliente vista_buscar_cliente){
		
		this.vista_buscar_cliente = vista_buscar_cliente;
	}
	
	public DomicilioParticularVO validarBusqueda(String dni){
		
		Domicilio_particularDAO _domicilio_particularDAO = new Domicilio_particularDAO();
		boolean val_num;
		boolean val_cant;
	
		if(LogicaCliente.validarbusquedaUsuario){
			
			try {
				return _domicilio_particularDAO.buscarDomicilio_particular(Integer.parseInt(dni));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		
		return null;		
	}
	
	public void validarAltaUsuario(VistaAltaCliente _vista_alta_cliente){
		
		boolean val_num = false;
		/*vacio = false;
		bexede_caracteres = false;
		no_entero = false;*/
		
		//LogicaCliente.validaraltaUsuario = true;
		
		//Validacion DNI
		
		for(JTextField tf : _vista_alta_cliente.getArrayStringDomPart()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validaraltaUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_alta_cliente.getArrayIntDomPart()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validaraltaUsuario = false;
			}
		}
			
		for(JTextField tf : _vista_alta_cliente.getArrayStringDomPart()){
				
			if(tf.getText().length() > 100){
						
				LogicaCliente.excede_caracteres = true;
						
				LogicaCliente.validaraltaUsuario = false;
			}
		}
				
		for(JTextField tf : _vista_alta_cliente.getArrayIntDomPart()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  LogicaCliente.no_entero = true; 
		
		}
				
		if(LogicaCliente.no_entero) LogicaCliente.validaraltaUsuario = false;
		
	}
	
	public void validarAlta(DomicilioParticularVO _domicilio_particularVO){
		
		Domicilio_particularDAO _domicilio_particularDAO = new Domicilio_particularDAO();
		
		if(LogicaCliente.validaralta){
			
			try {
				resultado_insertDomPart = _domicilio_particularDAO.insertarDomicilio_particular(_domicilio_particularVO);
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
		
		for(JTextField tf : _vista_buscar_cliente.getArrayStringDomPart()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validarmodificacionUsuario = false;
			}
		}
		
		for(JTextField tf : _vista_buscar_cliente.getArrayIntDomPart()){
			
			if(tf.getText().equals("")){
				
				LogicaCliente.vacio = true;
				
				LogicaCliente.validarmodificacionUsuario = false;
			}
		}
			
		for(JTextField tf : _vista_buscar_cliente.getArrayStringDomPart()){
				
			if(tf.getText().length() > 100){
						
				LogicaCliente.excede_caracteres = true;
						
				LogicaCliente.validarmodificacionUsuario  = false;
			}
		}
				
		for(JTextField tf : _vista_buscar_cliente.getArrayIntDomPart()){
					
			val_num = validarInt(tf.getText());
					
			if(!val_num || numero < 0 || numero > 100000000)  LogicaCliente.no_entero = true; 
		
		}
				
		if(LogicaCliente.no_entero) LogicaCliente.validarmodificacionUsuario = false;
		
	}
	
	public int validarModificacion(DomicilioParticularVO _domicilio_particularVO, int dni){
		
		Domicilio_particularDAO _domicilio_particularDAO = new Domicilio_particularDAO();
		
		try {
			
			return _domicilio_particularDAO.modificarDomPart
					(_domicilio_particularVO,dni, vista_buscar_cliente.getDni_anterior());
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
}
