package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.LogicaMonto_trasladado;
import modelo_vo.Monto_trasladadoVO;
import modelo_vo.PedidosVO;
import vista.VistaAltaMonto_trasladado;
import vista.VistaEditarMonto_trasladado;

public class ControladorMonto_trasladado {

	private LogicaMonto_trasladado _logica_montotrasladado;
	private VistaAltaMonto_trasladado  _vista_alta_montotrasladado;
	private VistaEditarMonto_trasladado  _vista_editar_montotrasladado;
	
	public void setLogicaMonto_trasladado (LogicaMonto_trasladado  _logica_montotrasladado){
		
		this._logica_montotrasladado = _logica_montotrasladado;
	}
	
	public LogicaMonto_trasladado  getLogicaMonto_trasladado (){
		
		return _logica_montotrasladado;
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

	
	public void altaMonto_t_usuario(ArrayList<JTextField> ar, JTextArea observaciones){
		
		_logica_montotrasladado.validarAltaMonto_t_usuario(ar, observaciones);
	}
	
	public int nuevoMonto(Monto_trasladadoVO _montotrasladado){
		
		return _logica_montotrasladado.validarNuevoMonto(_montotrasladado);
	}
	
	public ArrayList<Monto_trasladadoVO> buscar_pedido(int n_pedido){
		
		return _logica_montotrasladado.validar_busqueda_pedido(n_pedido);
	}
	
	public boolean comprobar_monto_t(Monto_trasladadoVO _montotrasladado){
		
		return _logica_montotrasladado.validarMonto_t(_montotrasladado);
	}
	
	
	public int anular_monto(Monto_trasladadoVO _montotrasladado){
		
		return _logica_montotrasladado.validarBaja(_montotrasladado);
	}
}
