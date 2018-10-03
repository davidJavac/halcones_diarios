package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorZona;
import modelo_dao.ArticuloDAO;
import modelo_dao.RetiroDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.Pedido_articuloVO;
import modelo_vo.RetirosVO;
import vista.BusquedaEntities;

public class LogicaRetiro {

	
	ControladorArticulo _controladorArticulo;
	BusquedaEntities busqueda_entities;
	private int numero;
	private double numero_double;
	
	public ArticulosVO validarBusquedaUsuario(String codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

		if(validarInt(codigo)){
			
			try {
				ArticulosVO articuloVO = _articuloDAO .buscarArticulo(numero);
				return articuloVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		return null;
				
	}
	
	public RetirosVO buscarRetiro_porId_pa(int id){
		
		RetiroDAO rDAO = new RetiroDAO();
			
			try {
				return rDAO.buscarRetiro_porId_pa(id);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return null;
		
	}
	
	public int deleteRetiro(RetirosVO rVO){
		
		RetiroDAO rDAO = new RetiroDAO();
		
		try {
			return rDAO.deleteRetiro(rVO);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean validarRetiroUsuario(JTable tabla, JTextField diasTF, JTextField cuotaTF,
			JTextField porcentaje_daTF, JRadioButton da_si, JTextArea observaciones){
		
		if(tabla.getSelectedRowCount()==0){
			
			//Mensajes.getMensaje_sinFilasSeleccionadas();
			return false;
		}
		
		if(tabla.getRowCount()==tabla.getSelectedRowCount()){
			
			diasTF.setText("0");
			cuotaTF.setText("0");
		}
		else{
			
			if(!validarInt(diasTF.getText())) return false;
			if(!validarDouble(cuotaTF.getText())) return false;
			
			if(numero <= 0 || numero > 100000) return false;
			if(numero_double <= 0 || numero_double > 100000) return false;
			
		}
		
		if(da_si.isSelected()){
			
			if(!validarDouble(porcentaje_daTF.getText())) return false;
			if(numero_double <= 0 || numero_double > 100) return false;
			
		}
		
		if(observaciones.getText().length() < 30 || observaciones.getText().length() >200){
			//Mensajes.getMensaje_observaciones();
			return false;
		}
		
		return true;
	}
	
	public int validarInsert_nuevoRetiro(RetirosVO rVO, int dias, int cuota, String estado){
		
		RetiroDAO retiroDAO = new RetiroDAO();
	
			try {
				return retiroDAO .insertNuevo_Retiro(rVO, dias, cuota, estado);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	
	public boolean validarNuevo_articuloUsuario(ArrayList<JTextField> carac, JTextField diasTF, 
			JTextField cuotaTF){
		
		for(JTextField tf : carac){
			
			if(tf.getText().equals("")) return false;
		}
			
		if(!validarInt(diasTF.getText())) return false;
		if(Integer.parseInt(diasTF.getText()) <= 0) return false;
		if(diasTF.getText().length() > 10) return false;
		
		if(!validarDouble(cuotaTF.getText())) return false;
		if(Double.parseDouble(cuotaTF.getText()) <= 0) return false;
		if(cuotaTF.getText().length() > 10) return false;
		
		return true;
	}
	
	public boolean validarInt(String val_string){
		
		try{
			
			numero = Integer.parseInt(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}
	
	public boolean validarDouble(String val_string){
		
		try{
			
			numero_double = Double.parseDouble(val_string);
			
			return true;
			
		}catch(NumberFormatException e){
			
			return false;
		}
	}

	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}

}
