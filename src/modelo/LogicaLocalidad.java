package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controlador.ControladorLocalidad;
import controlador.ControladorZona;
import modelo_dao.LocalidadDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.LocalidadVO;
import vista.BusquedaEntities;

public class LogicaLocalidad {

	ControladorZona _controladorZona;
	ControladorLocalidad _controladorLocalidad;
	BusquedaEntities busqueda_entities;
	private short numero;
	
	public String validarBusquedaUsuario(String id_localidad){
		
		LocalidadDAO _logicaDAO = new LocalidadDAO();

		String localidad = "";
		
		if(validarShort(id_localidad)){
			
			try {
				localidad = _logicaDAO.buscarLocalidadUsuario(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return localidad;
		}
		
		return null;
				
	}
	
	public LocalidadVO validarBusqueda(String id_localidad){
		
		LocalidadDAO _logicaDAO = new LocalidadDAO();
		
		if(validarShort(id_localidad)){
			
			try {
				 return _logicaDAO.buscarLocalidad_porID(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		return null;
				
	}
	
	public void validarBusquedaAll(){
		
		LocalidadDAO _logicaDAO = new LocalidadDAO();
	
		busqueda_entities.limpiar();
		
		ArrayList<Object []> ar = new ArrayList<Object []>();
		try {
			ar = _logicaDAO.buscarLocalidadAll();
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
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		LocalidadDAO _localidadDAO = new LocalidadDAO();
			
			busqueda_entities.limpiar();
				
			ArrayList<Object[]> ar = new ArrayList<Object[]>();
			try {
				ar = _localidadDAO.buscarLocalidadPersonalida(busqueda);
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
	
	public void setControladorLocalidad(ControladorLocalidad _controladorLocalidad){
		
		this._controladorLocalidad = _controladorLocalidad;
		
	}
	
}
