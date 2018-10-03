package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import controlador.ControladorZona;
import modelo_dao.AdministrativoDAO;
import modelo_dao.MotivoDAO;
import modelo_dao.ProveedorDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import modelo_vo.ProveedoresVO;
import vista.BusquedaEntities;

public class LogicaProveedores {

	ControladorZona _controladorZona;
	BusquedaEntities busqueda_entities;
	private short numero;
	private int numero_int;
	
	private static final String EMAIL_PATRON = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	public String validarBusquedaUsuario(String id_cobrador){
		
		AdministrativoDAO administrativoDAO = new AdministrativoDAO();

		String administrativo = "";
		
		if(validarShort(id_cobrador)){
			
			try {
				administrativo = administrativoDAO.buscarAdministrativoUsuario(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return administrativo;
		}
		
		return null;
				
	}
	
	public ProveedoresVO validarBusqueda_proveedorUsuario(String id_proveedor){
		
		ProveedorDAO proveedorDAO = new ProveedorDAO();

		try {
			if(validarShort(id_proveedor))
			return proveedorDAO.buscarProveedor_porID(numero);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public int modificarProveedor(ProveedoresVO pVO){
		
		ProveedorDAO proveedorDAO = new ProveedorDAO();

		try {
			
			return proveedorDAO.modificarProveedor(pVO);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
	}
	public int nuevoProveedor(ProveedoresVO pVO){
		
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		
		try {
			
			return proveedorDAO.nuevoProveedor(pVO);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
	}
	
	public void validarBusqueda_proveedorAll(){
		
		ProveedorDAO proveedorDAO = new ProveedorDAO();

		busqueda_entities.setTipoBusqueda(18);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = proveedorDAO.buscarProveedorAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( ar!= null){
				
			for(Object[] o: ar){
					
				busqueda_entities.getTabla().addRow(o);
			}
			busqueda_entities.setTablaModel();	
		}
				
	}
	
	public boolean modificar_altaProveedorUsuario(ArrayList<JTextField> ar, boolean editar){
		
		int i = 0;
		for(JTextField tf : ar){
			
			if(i == 0){
				
				if(editar){
					
					if(!validarInt(tf.getText())) {
						
						return false;
					}
					if(numero_int < 0 || numero_int > 100000000) return false;
					
				}
				
			}
			else if(i == 7 && validar_email(tf.getText())) return false; 
			else if((tf.getText().equals("")) || tf.getText().length() > 30) {
				
				return false;
			}
			
			i++;
		}
		
		return true;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero_int = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	
	public boolean validar_email(String email){
		
		boolean error = false;
		
		Pattern p = Pattern.compile(EMAIL_PATRON);
		Matcher m = p.matcher(email);
				
		if(!m.matches()) error = true;
		
		return error;
	}
	public boolean validarShort(String val_string){
		
		try{
			
			numero = Short.parseShort(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona ;
		
	}
}
