package controlador;


import modelo_vo.VerazVO;
import vista.VistaAltaVeraz;

public class ControladorVeraz {


	private VistaAltaVeraz _vista_alta_veraz;

	public void setVistaAltaVeraz(VistaAltaVeraz _vista_alta_veraz){
		
		this._vista_alta_veraz = _vista_alta_veraz;
	}
	
	public VistaAltaVeraz getVistaAltaVeraz(){
		
		return _vista_alta_veraz;
	}

}
