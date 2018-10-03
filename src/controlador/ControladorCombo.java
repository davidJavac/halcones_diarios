package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaCombo;
import modelo_vo.Pedido_articuloVO;
import vista.BusquedaEntities;
import vista.VistaAltaCombo;
import vista.VistaEditarCombo;

public class ControladorCombo {

	private LogicaCombo _logica_combo;
	private VistaAltaCombo _vista_alta_combo;
	private VistaEditarCombo _vista_editar_combo;
	private BusquedaEntities busqueda_entities;
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
	}
	
	public void setLogicaCombo(LogicaCombo _logica_combo){
		
		this._logica_combo = _logica_combo;
	}
	
	public LogicaCombo getLogicaCombo(){
		
		return _logica_combo;
	}
	
	public void setVistaAltaCombo(VistaAltaCombo _vista_alta_combo){
		
		this._vista_alta_combo = _vista_alta_combo;
	}
	
	public VistaAltaCombo getVistaAltaCombo(){
		
		return _vista_alta_combo;
	}
	
	public void setVistaEditarCombo(VistaEditarCombo _vista_editar_combo){
		
		this._vista_editar_combo = _vista_editar_combo;
	}
	
	public VistaEditarCombo getVistaEditarCombo(){
		
		return _vista_editar_combo;
	}
	
	public void mostrarBusquedaEntities(){
		
		busqueda_entities.crear_mostrar_ventana("Busqueda de Combinacion de articulos");
	}
	
	
	public void buscarComboAll(){
		
		 _logica_combo.validarBusquedaAll();
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_combo.validarBusquedaPersonalizada(busqueda);
	}
	
	
	public boolean nuevoComboUsuario(ArrayList<JTextField> ar, JTextField diasTF, JTextField cuotaTF){
		
		return _logica_combo.validarNuevoUsuario(ar, diasTF, cuotaTF);
	}
	

}
