package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorLocalidad;
import controlador.ControladorVendedor;
import controlador.ControladorZona;
import modelo_dao.LocalidadDAO;
import modelo_dao.VendedorDAO;
import modelo_vo.VendedorVO;
import vista.BusquedaEntities;

public class LogicaVendedor {

	ControladorZona _controladorZona;
	ControladorVendedor _controladorVendedor;
	BusquedaEntities busqueda_entities;
	private short numero;
	
	public String validarBusquedaUsuario(String id_vendedor){
		
		VendedorDAO _vendedorDAO = new VendedorDAO();

		String vendedor = "";
		
		if(validarShort(id_vendedor)){
			
			try {
				vendedor = _vendedorDAO.buscarVendedorUsuario(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return vendedor;
		}
		
		return null;
				
	}
	
	public VendedorVO validarBusqueda_porID(short id_vendedor){
		
		VendedorDAO _vendedorDAO = new VendedorDAO();
			
			try {
				return _vendedorDAO.buscarVendedor_porID(id_vendedor);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return null;
				
	}
	
	public void validarBusquedaAll(){
		
		VendedorDAO _vendedorDAO = new VendedorDAO();
	
		busqueda_entities.setTipoBusqueda(4);
		busqueda_entities.limpiar();
		
		ArrayList<Object []> ar = new ArrayList<Object []>();
		try {
			ar = _vendedorDAO.buscarVendedorAll();
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
		
		VendedorDAO _vendedorDAO = new VendedorDAO();
			
			busqueda_entities.limpiar();
				
				ArrayList<Object[]> ar = new ArrayList<Object[]>();
				try {
					ar = _vendedorDAO.buscarVendedorPersonalizada(busqueda);
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
	
	public void setControladorVendedor(ControladorVendedor _controladorVendedor){
		
		this._controladorVendedor = _controladorVendedor;
		
	}
	
}
