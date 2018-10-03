package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.ControladorArticulo;
import controlador.ControladorPedidos;
import modelo_dao.ArticuloDAO;
import modelo_dao.CambioPlanDAO;
import modelo_vo.ArticulosPVO;
import modelo_vo.ArticulosVO;
import modelo_vo.Cambio_planVO;
import modelo_vo.DAVO;
import modelo_vo.Pedido_articuloVO;
import vista.BusquedaEntities;

public class LogicaCambio_plan {

	ControladorArticulo _controladorArticulo;
	private ControladorPedidos controladorPedido = new ControladorPedidos();
	private LogicaPedido logica_pedido = new LogicaPedido();
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
	public ArticulosVO buscarArticulo_porCodigo(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

			
			try {
				ArticulosVO articuloVO = _articuloDAO .buscarArticulo(codigo);
				return articuloVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	
		
		return null;
				
	}
	
	public ArticulosPVO buscarArticulo_enPrestamo(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();

			
			try {
				ArticulosPVO prestamoVO = _articuloDAO .buscarArt_enPrestamo(codigo);
				return prestamoVO;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	
		
		return null;
				
	}
	
	public int establecerNuevoPlan(Cambio_planVO cVO, int dias, double cuota, DAVO dVO){
	
		CambioPlanDAO cpDAO = new CambioPlanDAO();
		
		try {
			return cpDAO .establecerNuevoPlan(
					cVO,  dias,  cuota,dVO);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	return 0;
			
}
	
	public int validarModificacion(ArticulosVO artVO){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
	
			try {
				return _articuloDAO .modificar_articulo(artVO);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		return 0;
				
	}
	
	public int deleteCambio_plan(Cambio_planVO cVO){
		
		CambioPlanDAO cDAO = new CambioPlanDAO();
		
		try {
			return cDAO .deleteCambio_plan(cVO);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return 0;
		
	}
	
	public boolean validarNuevo_planUsuario(JTextField diasTF, JTextField cuotaTF){
		
		if(!validarInt(diasTF.getText())) return false;
		if(numero <= 0 || numero > 1000) return false;
		if(!validarDouble(cuotaTF.getText())) return false;
		if(numero_double <= 0 || numero_double > 1000) return false;
		
		return true;
	}
	
	public boolean validarNuevo_planPrincipalUsuario(ArrayList<JTextField> ar){
	
		int cont_vacios = 0;
		
		for(JTextField tf : ar){
			
			if(tf.getText().equals("")) cont_vacios++;
			if(!validarInt(tf.getText()) && !tf.getText().equals("")) return false;
			
			if(tf.getText().length() > 10) return false;
			if(numero <= 0 && !tf.getText().equals("")) return false;
		}
		if(cont_vacios > 4) return false;
		
		return true;
	}
		
	public boolean validarNuevo_descuentoUsuario(JTextField descuentoTF){
		
		if(!validarDouble(descuentoTF.getText())) return false;
		if(numero_double <= 0 || numero_double > 100) return false;
		
		return true;
	}
	
	public boolean validarModificacionUsuario(JTextField nombreTF,JTextArea descripcionTF, 
			JTextField diasTF,JTextField cuotaTF){
		
		if(nombreTF.getText().equals("")) return false;
		if(descripcionTF.getText().equals("")) return false;
		if(nombreTF.getText().length()>30) return false;
		if(descripcionTF.getText().length()>100) return false;
		
		if(!validarInt(diasTF.getText())) return false;
		if(!validarDouble(cuotaTF.getText())) return false;
		if(numero <= 0 || numero > 1000) return false;
		if(numero_double <= 0 || numero_double > 1000) return false;
		
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
	
	
	public ArticulosVO validarBusqueda(int codigo){
		
		ArticuloDAO _articuloDAO = new ArticuloDAO();
		
		try {
			return _articuloDAO.buscarArticulo(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setBusquedaEntities(BusquedaEntities busqueda_entities){
		
		this.busqueda_entities = busqueda_entities;
		
	}
	
	public void setControladorArticulo(ControladorArticulo _controladorArticulo){
		
		this._controladorArticulo = _controladorArticulo;
		
	}
}
