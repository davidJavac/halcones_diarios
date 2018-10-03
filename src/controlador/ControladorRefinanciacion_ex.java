package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaRefinanciacion_ex;
import modelo_vo.PedidosVO;
import modelo_vo.Refinanciacion_exVO;
import vista.VistaAltaRefinanciacion_ex;
import vista.VistaAltaRefinanciacion_in;
import vista.VistaEditarRefinanciacion_ex;
import vista.VistaEditarRefinanciacion_in;

public class ControladorRefinanciacion_ex {

	private LogicaRefinanciacion_ex _logica_refinanciacionex;
	private VistaAltaRefinanciacion_ex _vista_alta_refinanciacionex;
	private VistaEditarRefinanciacion_ex _vista_editar_refinanciacionex;
	
	public void setLogicaRefinanciacion_ex(LogicaRefinanciacion_ex _logica_refinanciacionex){
		
		this._logica_refinanciacionex = _logica_refinanciacionex;
	}
	
	public LogicaRefinanciacion_ex getLogicaRefinanciacion_ex(){
		
		return _logica_refinanciacionex;
	}
	
	public void setVistaAltaRefinanciacion_ex(VistaAltaRefinanciacion_ex _vista_alta_refinanciacionex){
		
		this._vista_alta_refinanciacionex = _vista_alta_refinanciacionex;
	}
	
	public VistaAltaRefinanciacion_ex getVistaAltaRefinanciacion_ex(){
		
		return _vista_alta_refinanciacionex;
	}
	
	public void setVistaEditarRefinanciacion_ex(VistaEditarRefinanciacion_ex _vista_editar_refinanciacionex){
		
		this._vista_editar_refinanciacionex = _vista_editar_refinanciacionex;
	}
	
	public VistaEditarRefinanciacion_ex getVistaEditarRefinanciacion_ex(){
		
		return _vista_editar_refinanciacionex;
	}
	
	
	public void altaRefinanciacion_ex(ArrayList<JTextField> ar){
		
		_logica_refinanciacionex.validarAltaRefinanciacion_exUsuario(ar);
	}

	
	public int nuevaRefinanciacion(Refinanciacion_exVO refVO){
		
		return _logica_refinanciacionex.validarNuevo(refVO);
	}
	
	public int anularRef_ex(int n_pedido){
		
		return _logica_refinanciacionex.validarAnularRef(n_pedido);
	}
	
	
	public int modificarRefinanciacion_ex(Refinanciacion_exVO _refinanciacion_ex){
		
		return _logica_refinanciacionex.validarModificacion(_refinanciacion_ex);
	}
	
}
