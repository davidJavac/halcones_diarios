package modelo_vo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorZona;
import modelo_dao.AdministrativoDAO;
import modelo_dao.CobradorDAO;
import modelo_dao.ZonaDAO;
import vista.BusquedaEntities;

public class LogicaAdministrativo {

	ControladorZona _controladorZona;
	BusquedaEntities busqueda_entities;
	private short numero;
	
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
	
	public void validarBusquedaAll(){
		
		AdministrativoDAO administrativoDAO = new AdministrativoDAO();

		busqueda_entities.setTipoBusqueda(13);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = administrativoDAO.buscarAdministrativoAll();
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
