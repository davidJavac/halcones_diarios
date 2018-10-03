package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorZona;
import modelo_dao.Jefe_calleDAO;
import modelo_dao.ZonaDAO;
import vista.BusquedaEntities;

public class LogicaJefe_calle {

	ControladorZona _controladorZona;
	BusquedaEntities busqueda_entities;
	private short numero;
	
	public String validarBusquedaUsuario(String id_jefe){
		
		Jefe_calleDAO _jcDAO = new Jefe_calleDAO();

		String jefe = "";
		
		if(validarShort(id_jefe)){
			
			try {
				jefe = _jcDAO .buscarJefeUsuario(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return jefe;
		}
		
		return null;
				
	}
	
	
	public void validarBusquedaAll(){
		
		Jefe_calleDAO _jcDAO = new Jefe_calleDAO();

		busqueda_entities.setTipoBusqueda(12);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = _jcDAO.buscarJefe_calleAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if( ar!= null){
				
			for(Object[] o: ar){
					
				busqueda_entities.getTabla().addRow(o);
			}
		}
			
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
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona ;
		
	}
}
