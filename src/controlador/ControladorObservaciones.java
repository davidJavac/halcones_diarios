package controlador;

import java.util.ArrayList;

import javax.swing.JTextArea;

import modelo.LogicaObservaciones;
import modelo_vo.ObservacionesVO;
import modelo_vo.Observaciones_clienteVO;
import vista.VistaAltaObservaciones_cliente;
import vista.VistaEditarObservaciones_cliente;

public class ControladorObservaciones {

	private LogicaObservaciones _logica_observaciones;
	private VistaAltaObservaciones_cliente _vista_alta_observacionescliente;
	private VistaEditarObservaciones_cliente _vista_editar_observacionescliente;
	
	public void setLogicaObservaciones(LogicaObservaciones _logica_observaciones){
		
		this._logica_observaciones = _logica_observaciones;
	}
	
	
	public void setVistaAltaObservaciones_cliente(VistaAltaObservaciones_cliente _vista_alta_observacionescliente){
		
		this._vista_alta_observacionescliente = _vista_alta_observacionescliente;
	}
	
	public VistaAltaObservaciones_cliente getVistaAltaObservaciones_cliente(){
		
		return _vista_alta_observacionescliente;
	}
	
	public void setVistaEditarObservaciones_cliente(VistaEditarObservaciones_cliente _vista_editar_observacionescliente){
		
		this._vista_editar_observacionescliente = _vista_editar_observacionescliente;
	}
	
	public VistaEditarObservaciones_cliente getVistaEditarObservaciones_cliente(){
		
		return _vista_editar_observacionescliente;
	}
	
	public boolean validarObservacionesBajaCliente(JTextArea observaciones){
		
		return _logica_observaciones.validarObservacionesBajaCliente(observaciones);
	}
	public boolean validarObservaciones(JTextArea observaciones){
		
		return _logica_observaciones.validarObservaciones(observaciones);
	}
	
	public int 	modificarObservacionPedido(int id, ObservacionesVO obVO){
		
		return  _logica_observaciones.modificarObservacionPedido(id, obVO);
		
	}
	public int 	modificarObservacionCliente(int id, Observaciones_clienteVO obVO){
		
		return  _logica_observaciones.modificarObservacionCliente(id, obVO);
		
	}
	
	public int altaObservacionPedido(ObservacionesVO obVO){
		
		return  _logica_observaciones.validarAltaObservacionesPedido(obVO);
	}
	public int altaObservacionCliente(Observaciones_clienteVO obVO){
		
		return  _logica_observaciones.validarAltaObservacionesCliente(obVO);
	}
	
	public ArrayList<ObservacionesVO> buscarObservacionesPedido(int n_pedido){
		
		return _logica_observaciones.validarBuscarObservacionesPedido(n_pedido);
	}
	
	public ArrayList<Observaciones_clienteVO> buscarObservacionesCliente(int dni){
		
		return _logica_observaciones.validarBuscarObservacionesCliente(dni);
	}
	
}
