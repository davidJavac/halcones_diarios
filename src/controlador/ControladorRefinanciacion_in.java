package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaRefinanciacion_in;
import modelo_vo.Pago_diarioVO;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import modelo_vo.Refinanciacion_inVO;
import vista.VistaAltaRefinanciacion_in;
import vista.VistaEditarRefinanciacion_in;

public class ControladorRefinanciacion_in {

	private LogicaRefinanciacion_in _logica_refinanciacionin;
	private VistaAltaRefinanciacion_in _vista_alta_refinanciacionin;
	private VistaEditarRefinanciacion_in _vista_editar_refinanciacionin;
	
	public void setLogicaRefinanciacion_in(LogicaRefinanciacion_in _logica_refinanciacionin){
		
		this._logica_refinanciacionin = _logica_refinanciacionin;
	}
	
	public LogicaRefinanciacion_in getLogicaRefinanciacion_in(){
		
		return _logica_refinanciacionin;
	}
	
	public void setVistaAltaRefinanciacion_in(VistaAltaRefinanciacion_in _vista_alta_refinanciacionin){
		
		this._vista_alta_refinanciacionin = _vista_alta_refinanciacionin;
	}
	
	public VistaAltaRefinanciacion_in getVistaAltaRefinanciacion_in(){
		
		return _vista_alta_refinanciacionin;
	}
	
	public void setVistaEditarRefinanciacion_in(VistaEditarRefinanciacion_in _vista_editar_refinanciacionin){
		
		this._vista_editar_refinanciacionin = _vista_editar_refinanciacionin;
	}
	
	public VistaEditarRefinanciacion_in getVistaEditarRefinanciacion_in(){
		
		return _vista_editar_refinanciacionin;
	}
	
	public void altaRefinanciacion_in(ArrayList<JTextField> ar){
		
		_logica_refinanciacionin.validarAltaRefinanciacion_inUsuario(ar);
	}

	
	public int nuevaRefinanciacion(Refinanciacion_inVO refVO){
		
		return _logica_refinanciacionin.validarNuevo(refVO);
	}
	
	public int modificarRefinanciacion_in(Refinanciacion_inVO _refinanciacion_in){
		
		return _logica_refinanciacionin.validarModificacion(_refinanciacion_in);
	}
	
	public int anularRef_in(int n_pedido){
		
		return _logica_refinanciacionin.validarAnularRef(n_pedido);
	}
}
