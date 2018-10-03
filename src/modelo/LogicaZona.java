package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controlador.ControladorEmpleado;
import controlador.ControladorZona;
import modelo_dao.LocalidadDAO;
import modelo_dao.ZonaDAO;
import modelo_vo.EmpleadoVO;
import modelo_vo.ZonaVO;
import modelo_vo.Zona_localidadVO;
import vista.BusquedaEntities;
import vista.VistaAltaCliente;

public class LogicaZona {

	private ControladorZona _controladorZona;
	private ControladorEmpleado controladorEmpleado;
	BusquedaEntities busqueda_entities;
	private short numero;
	
	public ZonaVO validarBusquedaUsuario(String id_zona){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		if(validarShort(id_zona)){
			
			try {
				return _zonaDAO .buscarZona_porID(numero);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
		return null;
				
	}
	
	public ZonaVO buscarZona_porId_cobrador(int id){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
			
			try {
				return _zonaDAO .buscarZona_porID_cobrador(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return null;
		
	}
	
	public int validarInsert_localidad(short id_zona, String id_localidad){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		if(validarShort(id_localidad)){
			
			try {
				if(!_zonaDAO.buscarZona_localidad(id_zona, numero)){
					
					try {
						return _zonaDAO.insertLocalidad(id_zona, numero);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else return 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return 0;
	}
	
	public boolean validarZona(JTextField zonaTF){
		
		if(!validarShort(zonaTF.getText())) return false;
		if(numero <= 0 || numero > 1000) return false;
		
		return true;
	}
	
	public int modificarZona_cobrador(ZonaVO zVO){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		
		try {
			
			return _zonaDAO.modificarZona_cobrador(zVO);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		
		return 0;
		
	}
	
	public int insertZona_cobrador(ZonaVO zVO){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		
		try {
			
			return _zonaDAO.insertZona_cobrador(zVO);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		
		return 0;
		
	}
	public int deleteAllZonas(){
		
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		
		try {
			
			return _zonaDAO.deleteAllZonas();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
		
		return 0;
	}
	
	public int validarDelete_localidad(short id_zona, String id_localidad){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		if(validarShort(id_localidad)){
			
			try {
					
				return _zonaDAO.deleteLocalidad(id_zona, numero);
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		return 0;
	}
	
	
	public int insertZona(ZonaVO zVO, int loc1,int loc2,int loc3,int loc4){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
			
			try {
				return _zonaDAO .insertZona(zVO,  loc1, loc2, loc3, loc4);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
	}
	
	public int validarModificacionZona(ZonaVO zVO, int aux_loc1, int aux_loc2,int aux_loc3,
			int aux_loc4, int loc1,int loc2,int loc3,int loc4){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
			
			try {
				return _zonaDAO .modificarZona(zVO, aux_loc1, aux_loc2, aux_loc3,
						aux_loc4, loc1, loc2, loc3, loc4);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
	}
	
	public boolean validarIngresoUsuario(JTextField cobradorTF, ArrayList<JTextField> ar){

		if(cobradorTF.getText().equals("")) return false;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) tf.setText("0");
			
		}
			
		String codigo = "";
		boolean repite = false;
		
		for(int i = 0; i < ar.size(); i++){
			
			codigo = ar.get(i).getText();
			
			for(int j = 0; j < ar.size(); j++){
				
				if(j!=i && ar.get(j).getText().equals(codigo) && 
						!ar.get(j).getText().equals("0")) repite = true;
			}
		}
		
		if(repite) return false;
		
		return true;
	}
	
	public void validarBusquedaAll(){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		busqueda_entities.setTipoBusqueda(1);
		busqueda_entities.limpiar();
			
		try {		
	
			if(_zonaDAO.buscarZonaAll() != null){
				
				ArrayList<ZonaVO> ar = _zonaDAO.buscarZonaAll();
				
				for(ZonaVO zVO : ar){
    				
					Object [] o = new Object[2];
					
					o[0] = zVO.getId_zona();
					
    				EmpleadoVO eVO = controladorEmpleado.buscarEmpleado(Integer.
    						toString(zVO.getId_cobrador()));
    				
    				o[1] = eVO.getNombre() + " " + eVO.getApellido();
    				
    				busqueda_entities.getTabla().addRow(o);
				}
				
				busqueda_entities.setTablaModel();
			}
						
								
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
			
			
			
			
		
	}
	
	public ArrayList<ZonaVO> validarBusquedaZonas(){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		ArrayList<ZonaVO> ar = null;		
		
		
		try {
			return ar = _zonaDAO.buscarZonaAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<Zona_localidadVO> validarBusquedaZona_localidad(short id_zona){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		ArrayList<Zona_localidadVO> ar = null;		
			
		try {
			return ar = _zonaDAO.buscarZona_localidad_porID(id_zona);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	public void validarBusquedaPersonalizada(String busqueda){
		
		ZonaDAO _zonaDAO = new ZonaDAO();
		
		busqueda_entities.limpiar();
		
		ArrayList<Object[]> ar = new ArrayList<Object[]>();
		
		try {
				ar = _zonaDAO.buscarZonaPersonalida(busqueda);
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
					
			if(ar != null){
						
					for(Object [] o : ar){
							
						busqueda_entities.getTabla().addRow(o);
					}
			}			
			
			if(busqueda_entities.getTabla().getRowCount() > 0)
				busqueda_entities.setTablaModel();
			
	}
	
	public boolean validarShort(String val_string){
		
		try{
			
			numero = Short.parseShort(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorZona(ControladorZona _controladorZona){
		
		this._controladorZona = _controladorZona ;
		
	}
	
	public void setControladorEmpleado(ControladorEmpleado _controladorEmpleado){
		
		this.controladorEmpleado = _controladorEmpleado ;
		
	}
	
}
