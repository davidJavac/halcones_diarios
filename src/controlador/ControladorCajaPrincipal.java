package controlador;

import modelo_vo.Caja_inasistenciaVO;
import modelo_vo.Caja_internaVO;
import modelo_vo.Caja_proveedoresVO;
import modelo_vo.Caja_zonasVO;
import vista.VistaAltaCaja;
import vista.VistaBuscarCaja;
import vista.VistaResultadoCaja;

public class ControladorCajaPrincipal {

	private VistaBuscarCaja _vista_buscar_caja;
	private VistaResultadoCaja _vista_resultado_caja;
	private VistaAltaCaja  _vista_alta_caja;
	
	
	public void setVistaBuscarCaja(VistaBuscarCaja _vista_buscar_caja){
		
		this._vista_buscar_caja = _vista_buscar_caja;
	}
	
	public VistaBuscarCaja getVistaBuscarCaja(){
		
		return _vista_buscar_caja;
	}
	
	public void setVistaResultadoCaja(VistaResultadoCaja _vista_resultado_caja){
		
		this._vista_resultado_caja = _vista_resultado_caja;
	}
	
	public VistaResultadoCaja getVistaResultadoCaja(){
		
		return _vista_resultado_caja;
	}
	
	public void setVistaAltaCaja(VistaAltaCaja _vista_alta_caja){
		
		this._vista_alta_caja = _vista_alta_caja;
	}
	
	public VistaAltaCaja getVistaAltaCaja(){
		
		return _vista_alta_caja;
	}
	
	public void mostrarResultadoCaja(){
		
		
	}
	
	public void mostrarEditarCaja(){
		
		
	}
	
	
}
