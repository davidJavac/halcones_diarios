package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorZona;
import modelo_dao.AdministrativoDAO;
import modelo_dao.Detalle_internoDAO;
import modelo_dao.Detalle_proveedorDAO;
import modelo_dao.MotivoDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.Detalle_motivo_internoVO;
import modelo_vo.Detalle_proveedoresVO;
import modelo_vo.Motivo_generalVO;
import vista.BusquedaEntities;

public class LogicaDetalle_proveedor {

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
	
	public void validarBusqueda_detalleProvAll(){
		
		Detalle_proveedorDAO dDAO = new Detalle_proveedorDAO();

		busqueda_entities.setTipoBusqueda(19);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = dDAO.buscarDetalle_proveedorAll();
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
	
	public Detalle_proveedoresVO validarBusqueda_detalleUsuario(String id_detalle){
		
		Detalle_proveedorDAO dDAO = new Detalle_proveedorDAO();

		try {
			if(validarShort(id_detalle))
			
				return dDAO.buscarDetalleProveedor_porID(numero);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public boolean validarBusqueda_detalle_internoAll(String id_motivo){
		
		if(validarShort(id_motivo)){
			
			Detalle_internoDAO dDAO = new Detalle_internoDAO();

			busqueda_entities.setTipoBusqueda(17);
			busqueda_entities.limpiar();
			
			ArrayList<Object[]> ar = null;
			try {
				ar = dDAO.buscarDetalle_internoAll(numero);
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
			
			return true;
		}
		else{
			
			Mensajes.getMensaje_altaErrorGenerico();
			return false;
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
