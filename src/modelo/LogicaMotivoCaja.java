package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import controlador.ControladorZona;
import modelo_dao.AdministrativoDAO;
import modelo_dao.MotivoDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.Motivo_caja_internaVO;
import modelo_vo.Motivo_generalVO;
import vista.BusquedaEntities;

public class LogicaMotivoCaja {

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
	
	public Motivo_generalVO validarBusqueda_general(short id_motivo){
		
		MotivoDAO motivoDAO = new MotivoDAO();

	
		try {
			return motivoDAO.buscarMotivoGeneral_porID(id_motivo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusqueda_inasistenciaAll(){
		
		MotivoDAO motivoDAO = new MotivoDAO();

		busqueda_entities.setTipoBusqueda(14);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = motivoDAO.buscarMotivo_generalAll();
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
	
	public Motivo_caja_internaVO validarBusqueda_interna(String id_motivo){
		
		MotivoDAO motivoDAO = new MotivoDAO();

	
		try {
			if(validarShort(id_motivo))
			return motivoDAO.buscarMotivoInterna_porID(numero);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public void validarBusqueda_internaAll(){
		
		MotivoDAO motivoDAO = new MotivoDAO();

		busqueda_entities.setTipoBusqueda(16);
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = null;
		try {
			ar = motivoDAO.buscarMotivo_internaAll();
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
