package controlador;

import modelo_vo.Motivo_caja_internaVO;
import vista.VistaAltaGastosInternos;
import vista.VistaEditarGastosInternos;
import vista.VistaBuscarGastosInternos;

public class ControladorGastosInternos {

	
	private VistaAltaGastosInternos _vista_alta_gastosinternos;
	private VistaBuscarGastosInternos _vista_buscar_gastosinternos;
	private VistaEditarGastosInternos _vista_editar_gastosinternos;

	
	public void setVistaAltaGastosInternos(VistaAltaGastosInternos _vista_alta_gastosinternos){
		
		this._vista_alta_gastosinternos = _vista_alta_gastosinternos;
	}
	
	public VistaAltaGastosInternos getVistaAltaGastosInternos(){
		
		return _vista_alta_gastosinternos;
	}
	
	public void setVistaBuscarGastosInternos(VistaBuscarGastosInternos _vista_buscar_gastosinternos){
		
		this._vista_buscar_gastosinternos = _vista_buscar_gastosinternos;
	}
	
	public VistaBuscarGastosInternos getVistaBuscarGastosInternos(){
		
		return _vista_buscar_gastosinternos;
	}
	
	public void setVistaEditarGastosInternos(VistaEditarGastosInternos _vista_editar_gastosinternos){
		
		this._vista_editar_gastosinternos = _vista_editar_gastosinternos;
	}
	
	public VistaEditarGastosInternos getVistaEditarGastosInternos(){
		
		return _vista_editar_gastosinternos;
	}
	
}
