package controlador;

import modelo.LogicaSueldos;
import modelo_vo.Motivo_caja_internaVO;
import vista.VistaAltaGastosInternos;
import vista.VistaBuscarGastosInternos;
import vista.VistaEditarGastosInternos;

public class ControladorSueldos {

	private LogicaSueldos _logica_sueldos;
	
	public void setLogicaSueldos(LogicaSueldos _logica_sueldos){
		
		this._logica_sueldos = _logica_sueldos;
	}
	
	public LogicaSueldos getLogicaSueldos(){
		
		return _logica_sueldos;
	}
	
}
