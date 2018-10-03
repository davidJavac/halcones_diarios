package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaMonedas;
import modelo.LogicaMonto_trasladado;
import modelo_vo.Monto_trasladadoVO;
import vista.VistaAltaMonto_trasladado;
import vista.VistaEditarMonto_trasladado;

public class ControladorMonedas {

	private LogicaMonedas _logica_monedas;
	private VistaAltaMonto_trasladado  _vista_alta_montotrasladado;
	private VistaEditarMonto_trasladado  _vista_editar_montotrasladado;
	
	public void setLogicaMonedas (LogicaMonedas  _logica_monedas){
		
		this._logica_monedas = _logica_monedas;
	}
	
	public LogicaMonedas  getLogicaMonedas(){
		
		return _logica_monedas;
	}
	
	public void setVistaAltaMonto_trasladado (VistaAltaMonto_trasladado _vista_alta_montotrasladado){
		
		this._vista_alta_montotrasladado = _vista_alta_montotrasladado;
	}
	
	public VistaAltaMonto_trasladado getVistaAltaMonto_trasladado (){
		
		return _vista_alta_montotrasladado;
	}
	
	public void setVistaEditarMonto_trasladado (VistaEditarMonto_trasladado _vista_editar_montotrasladado){
		
		this._vista_editar_montotrasladado = _vista_editar_montotrasladado;
	}
	
	public VistaEditarMonto_trasladado getVistaEditarMonto_trasladado (){
		
		return _vista_editar_montotrasladado;
	}

}
