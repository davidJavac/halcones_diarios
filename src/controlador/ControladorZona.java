package controlador;

import java.util.ArrayList;

import javax.swing.JTextField;

import modelo.LogicaZona;
import modelo_vo.ZonaVO;
import modelo_vo.Zona_localidadVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;
import vista.VistaAltaZona;
import vista.VistaBuscarZona;
import vista.VistaEditarZona;
import vista.VistaResultadoZona;

public class ControladorZona {

	private LogicaZona _logica_zona;
	private VistaBuscarZona _vista_buscar_zona;
	private VistaResultadoZona _vista_resultado_zona;
	private VistaEditarZona _vista_editar_zona;
	private VistaAltaZona _vista_alta_zona;
	private VistaAltaCliente _vista_alta_cliente;
	private BusquedaEntities _busqueda_entities;
	
	public void setLogicaZona(LogicaZona _logica_zona){
		
		this._logica_zona = _logica_zona;
	}
	
	public LogicaZona getLogicaZona(){
		
		return _logica_zona;
	}
	
	public void setBusquedaEntities(BusquedaEntities _busqueda_entities){
		
		this._busqueda_entities = _busqueda_entities;
	}
	
	public void setVistaAltaCliente(VistaAltaCliente _vista_alta_cliente){
		
		this._vista_alta_cliente = _vista_alta_cliente;
	}
	
	public void setVistaBuscarZona(VistaBuscarZona _vista_buscar_zona){
		
		this._vista_buscar_zona = _vista_buscar_zona;
	}
	
	public VistaBuscarZona getVistaBuscarZona(){
		
		return _vista_buscar_zona;
	}
	
	public void setVistaResultadoZona(VistaResultadoZona _vista_resultado_zona){
		
		this._vista_resultado_zona = _vista_resultado_zona;
	}
	
	public VistaResultadoZona getVistaResultadoZona(){
		
		return _vista_resultado_zona;
	}
	
	public void setVistaEditarZona(VistaEditarZona _vista_editar_zona){
		
		this._vista_editar_zona = _vista_editar_zona;
	}
	
	public VistaEditarZona getVistaEditarZona(){
		
		return _vista_editar_zona;
	}
	
	public void setVistaAltaZona(VistaAltaZona _vista_alta_zona){
		
		this._vista_alta_zona = _vista_alta_zona;
	}
	
	public VistaAltaZona getVistaAltaZona(){
		
		return _vista_alta_zona;
	}
	
	public void mostrarResultadoZona(){
		
		
	}
	
	public void mostrarBusquedaEntities(){
		
		_busqueda_entities.crear_mostrar_ventana("Busqueda de zona");
	}
	
	public void busquedaPersonalizada(String busqueda){
		
		_logica_zona.validarBusquedaPersonalizada(busqueda);
	}
	
	public ZonaVO buscarZonaUsuario(String id_zona){
		
		return _logica_zona.validarBusquedaUsuario(id_zona);
	}
	
	public ZonaVO buscarZona_porId_cobrador(int id){
		
		
		return _logica_zona.buscarZona_porId_cobrador(id);
	}
	
	public int insertZona_cobrador(ZonaVO zVO){
		
		return _logica_zona.insertZona_cobrador(zVO);
	}
	public int modificarZona_cobrador(ZonaVO zVO){
		
		return _logica_zona.modificarZona_cobrador(zVO);
	}
	public int deleteAllZonas(){
		
		return _logica_zona.deleteAllZonas();
	}
	
	public void buscarZonaAll(){
		
		 _logica_zona.validarBusquedaAll();
	}
	
	public ArrayList<ZonaVO> buscarZonas(){
		
		 return _logica_zona.validarBusquedaZonas();
	}
	
	public ArrayList<Zona_localidadVO> buscarZona_localidad(short id_zona){
		
		 return _logica_zona.validarBusquedaZona_localidad(id_zona);
	}
	
	public boolean validarIngresoUsuario(JTextField cobradorTF, ArrayList<JTextField> ar){
		
		return _logica_zona.validarIngresoUsuario(cobradorTF, ar);
	}
	
	public int modificarZona(ZonaVO zVO, int aux_loc1, int aux_loc2,int aux_loc3,
			int aux_loc4, int loc1,int loc2,int loc3,int loc4){
		
		return _logica_zona.validarModificacionZona(zVO, aux_loc1, aux_loc2, aux_loc3,
				aux_loc4, loc1, loc2, loc3, loc4);
	}
	
	public int insertZona(ZonaVO zVO, int loc1,int loc2,int loc3,int loc4){
		
		return _logica_zona.insertZona(zVO, loc1, loc2, loc3, loc4);
	}
	
	public int insertLocalidad(short id_zona, String id_localidad){
		
		return _logica_zona.validarInsert_localidad(id_zona, id_localidad);
	}
	
	public int deleteLocalidad(short id_zona, String id_localidad){
		
		return _logica_zona.validarDelete_localidad(id_zona, id_localidad);
	}
	
	public boolean validarZona(JTextField zonaTF){
		
		return _logica_zona.validarZona(zonaTF);
	}
	
}
