package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorZona;
import modelo_dao.CobradorDAO;
import modelo_dao.Jefe_calleDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.CobradorVO;
import vista.BusquedaEntities;

public class LogicaCobrador {

	ControladorZona _controladorZona;
	BusquedaEntities busqueda_entities;
	private short numero;
	
	public String validarBusquedaUsuario(String id_cobrador){
		
		CobradorDAO cobradorDAO = new CobradorDAO();

		String cobrador = "";
		
		if(validarShort(id_cobrador)){
			
			try {
				cobrador = cobradorDAO.buscarCobradorUsuario(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return cobrador;
		}
		
		return null;
				
	}
	
	public CobradorVO validarBusqueda_porID(short id){
		
		CobradorDAO cobradorDAO = new CobradorDAO();
			
			try {
				return cobradorDAO.buscarCobrador_porID(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return null;
				
	}
	
	
	
	public void validarBusquedaAll(){
		
		CobradorDAO cobradorDAO = new CobradorDAO();

		busqueda_entities.setTipoBusqueda(11);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = cobradorDAO.buscarCobradorAll();
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
